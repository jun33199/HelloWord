package com.ttsoft.bjtax.shenbao.sbzl.grsds.processor;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsz;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsMapConstant;
import java.util.HashMap;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsmx;
import com.ekernel.db.or.ORTable;
import com.ekernel.db.or.ORManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ttsoft.bjtax.dj.DjOuterConstant;

/**
 * @author Ding Chenggang
 * @version 1.0
 */
public class GrsdsProcessor implements Processor
{

    public GrsdsProcessor()
    {
    }

    /**
     * @param vo VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        Object data = vo.getData();
        switch(vo.getAction())
        {
            case GrsdsActionConstant.ACTION_QUERY:
                // ��ѯ��ʷ����
                Grsdsz z = (Grsdsz)vo.getData();
                return doQueryHistoryData((Grsdsz)data);

            case GrsdsActionConstant.ACTION_DELETE:
                // ɾ�������걨����
                doDelete((Grsdsz)((Map)data).get(GrsdsMapConstant.OLDZ),
                         (List)((Map)data).get(GrsdsMapConstant.OLDMX));
                return null;

//            case GrsdsActionConstant.ACTION_INSERT:
//                // �����걨����
//                doInsert((Grsdsz)((Map)data).get(GrsdsMapConstant.NEWZ),
//                         (List)((Map)data).get(GrsdsMapConstant.NEWMX));
//                return null;

            case GrsdsActionConstant.ACTION_REFRESH:
                // �����걨����
                doRefresh((Grsdsz)((Map)data).get(GrsdsMapConstant.OLDZ),
                          (List)((Map)data).get(GrsdsMapConstant.OLDMX),
                          (Grsdsz)((Map)data).get(GrsdsMapConstant.NEWZ),
                          (List)((Map)data).get(GrsdsMapConstant.NEWMX));
                return null;

            default:
                throw new ApplicationException("NoSuchMethod");
        }
    }

    /**
     * ��ѯ����������
     *
     * @param z Grsdsz
     * @return java.util.Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doQueryHistoryData(Grsdsz z) throws BaseException
    {
        Connection con = null;
        try
        {
            Map result = new HashMap(2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Vector cri = new Vector();
            cri.add("JSJDM = '" + z.getJsjdm() + "'");
            cri.add("SKSSKSRQ = TO_DATE('" + sdf.format(z.getSkssksrq()) + "', 'YYYY-MM-DD')");
            cri.add("SKSSJSRQ = TO_DATE('" + sdf.format(z.getSkssjsrq()) + "', 'YYYY-MM-DD')");

            ORManager orMgr = DBResource.getORManager();

            DBAccess db = new DBAccess(con = DBResource.getConnection(), orMgr);
            List ret = db.query(Grsdsz.class, cri); // ��ѯ���� jsjdm, ����, ��ǰʱ��
            if(ret.size() == 0)
            {
                return null;
            }

            Grsdsz oldZ = (Grsdsz)ret.get(0);

            List oldMx = getGrsdsmx(orMgr, con, oldZ);
            result.put(GrsdsMapConstant.OLDZ, ret.get(0));
            result.put(GrsdsMapConstant.OLDMX, oldMx);

            return result;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(con);
        }
    }

    /**
     * ��ѯ��������˰��ϸ
     * @param orMgr ORManager
     * @param con Connection
     * @param z Grsdsz
     * @return List
     * @throws BaseException
     */
    private List getGrsdsmx(ORManager orMgr, Connection con, Grsdsz z) throws BaseException
    {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try
        {
            ORTable orTable = orMgr.getTable(Grsdsmx.class);
            String mxTableName = orTable.getTableName();

            String sql = "select * from " + mxTableName + " where jsjdm=? and cjrq=?";

            pst = con.prepareStatement(sql);

            // ���ò�ѯ����
            pst.setString(1, z.getJsjdm());   // ���������
            pst.setTimestamp(2, z.getCjsj()); // ����ʱ��

            List result = new ArrayList();
            rs = pst.executeQuery();
            while(rs.next())
            {
                Grsdsmx mx = new Grsdsmx();
                mx.setZjlxdm(rs.getString("ZJLXDM"));
                mx.setZjhm(rs.getString("ZJHM"));
                mx.setGjdm(rs.getString("GJDM"));
                mx.setSzsmdm(rs.getString("SZSMDM"));
                mx.setJsjdm(rs.getString("JSJDM"));
                mx.setCjsj(rs.getTimestamp("CJRQ"));
                mx.setSbrq(rs.getTimestamp("SBRQ"));
                mx.setLrrq(rs.getTimestamp("LRRQ"));
                mx.setXm(rs.getString("XM"));
                mx.setZydm(rs.getString("ZYDM"));
                mx.setSre(rs.getBigDecimal("SRE"));
                mx.setYnssde(rs.getBigDecimal("YNSSDE"));
                mx.setSl(rs.getBigDecimal("SL"));
                mx.setJmje(rs.getBigDecimal("JMJE"));
                mx.setBqskse(rs.getBigDecimal("BQSKSE"));
                mx.setWszh(rs.getString("WSZH"));

                result.add(mx);
            }

            return result;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            try   {   rs.close();    }   catch(Exception ex)     {  }
            try   {   pst.close();   }   catch(Exception ex)     {  }
        }
    }

    /**
     * �����˰�����ݣ���ϸ����
     * @param oldz Grsdsz
     * @param oldMx List
     * @param newz Grsdsz
     * @param newMx List
     * @throws BaseException
     */
    private void doRefresh(Grsdsz oldz, List oldMx, Grsdsz newz, List newMx)
        throws BaseException
    {
        doDelete(oldz, oldMx);
        doInsert(newz, newMx);
    }

    /**
     * ɾ������
     * @param z Grsdsz
     * @param mxList List
     * @throws BaseException
     */
    private void doDelete(Grsdsz z, List mxList) throws BaseException
    {
        Connection con = null;
        try
        {
            if (z == null)
            {
                return;
            }

            con = DBResource.getConnection();

            ORManager orMgr = DBResource.getORManager();
            DBAccess db = new DBAccess(con, orMgr);

            // ɾ����ϸ����
            for (int i=0; i<mxList.size(); i++)
            {
                orMgr.deleteObject(0, con, mxList.get(i));
            }

            orMgr.deleteObject(0, con, z);  // ɾ��������
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(con) ;
        }
    }

    /**
     * ��������
     * @param z Grsdsz
     * @param mxList List
     * @throws BaseException
     */
    private void doInsert(Grsdsz z, List mxList) throws BaseException
    {
        Connection con = null;

        try
        {
            if (z == null)
            {
                return;
            }

            con = DBResource.getConnection();
            ORManager orMgr = DBResource.getORManager();

            orMgr.makePersistent(0, con, z);  // ����������

            // ������ϸ����
            for (int i=0; i<mxList.size(); i++)
            {
                Grsdsmx mx = (Grsdsmx)mxList.get(i);
                orMgr.makePersistent(0, con, mx);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(con) ;
        }
    }
}