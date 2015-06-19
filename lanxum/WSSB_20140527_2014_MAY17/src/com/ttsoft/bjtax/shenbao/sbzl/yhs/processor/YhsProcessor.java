package com.ttsoft.bjtax.shenbao.sbzl.yhs.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.YhsbgmxVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author zhiyong He
 * @version 1.0 ӡ��˰Processor
 */
public class YhsProcessor implements Processor
{
    /**
     * SessionID����
     */
    private final long SESSIONID = 0;

    /**
     * ǰ̨�������û���Ϣ�����ݶ���
     */
    private UserData userData = null;

    public YhsProcessor()
    {
    }

    /**
     * ����ҵ���������ֵ����ҵ�����
     * 
     * @param voPackage
     *            VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // ����ҵ���������ֵ����ҵ�����
        try
        {
            switch (voPackage.getAction())
            {
                // ���ݲ�ѯ
                case YhsActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map) voPackage.getData()));
                    break;

                // ����
                case YhsActionConstant.INT_ACTION_SAVE:
                    voPackage.setData(doSave(voPackage));
                    break;

                // ɾ��
                case YhsActionConstant.INT_ACTION_DELETE:
                    voPackage.setData(doDelete(voPackage, "delete"));
                    break;

                // ���û�ж�ӦAction���׳�SystemException
                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        // ���ݷ���
        return voPackage;
    }

    /**
     * ����ӡ��˰��������
     * 
     * @param data
     *            Map
     * @return Object
     * @throws BaseException
     */
    private Map doSave(VOPackage vo) throws BaseException
    {
        Map data = (Map) vo.getData();
        DzyjsjVO dzyj = saveSignData(vo);
        data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

        // ���ݿ�����
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;

        try
        {
            // ȡ��ӡ��˰��������ֵ����
            Yhsbgz yhsbgz = (Yhsbgz) data.get(YhsMapConstant.YHSBGZ);
            // �����걨����
            yhsbgz.setSbrq(TinyTools.second2Day(new Timestamp(System.currentTimeMillis())));

            // ȡ��ӡ��˰������ϸ��ֵ����List
            List YhsbgmxList = (List) data.get(YhsMapConstant.LIST_YHSBGMX);

            // ɾ��ӡ��˰��������
            this.doDelete(vo, "save");

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // ����ӡ��˰������������
            orManager.makePersistent(this.SESSIONID, conn, yhsbgz);

            // ѭ������ӡ��˰������ϸ����
            for (int i = 0; i < YhsbgmxList.size(); i++)
            {
                orManager.makePersistent(this.SESSIONID, conn, YhsbgmxList.get(i));
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ӡ��˰��ȱ������ʧ��");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return data;
    }

    /**
     * ȡ�õǼ����ݡ�˰��˰Ŀ�������ݡ�ӡ��˰������걨���� 1.���ݼ��������ӵǼǽӿڲ���Ǽ���Ϣ 2.��˰��˰Ŀ�����ȡ��˰Ŀ��˰�ʣ���λ˰�
     * 3.���ݼ��������͵�ǰ���¼����Ƿ��Ѵ��ڴ���˰�˵�ӡ��˰�����˰�걨���� 4.���ز�ѯ���
     * 
     * @param data
     *            Map
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doQuery(Map data) throws BaseException
    {
        Connection conn = null;
        ORManager orManager = null;

        // ��������Map
        Map retMap = null;
        try
        {
            // ȡ�ü��������
            String jsjdm = (String) data.get(YhsMapConstant.JSJDM);
            // ȡ��˰�������������
            String nd = (String) ((Map) Skssrq.yearSkssrq(new Date())).get(Skssrq.SKSSRQ_ND);
            // Timestamp curTime = new Timestamp(System.currentTimeMillis());
            // String nd = new SimpleDateFormat("yyyy").format(curTime);
            // System.out.println(nd + "nnnnnnnnnnnnn");
            // �Ǽ�����Map
            Map djsjMap = (Map) data.get(YhsMapConstant.MAP_DJSJ);

            // ȡ�õǼǻ�������
            SWDJJBSJ djInfo = (SWDJJBSJ) djsjMap.get(YhsMapConstant.JBSJ);
            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // ȡ��ӡ��˰˰��˰Ŀ����
            List yhsSzsmList = this.getYhsSzsm(conn, orManager);
            // ��ѯӡ��˰������������
            Yhsbgz yhsbgz = this.getYhsbzsj(conn, orManager, jsjdm, nd, djInfo);

            // ӡ��˰������ϸ����
            List yhsbgmxList = null;
            // �ж��Ƿ��걨��ӡ��˰
            String ifSB = "wsb";
            // ���û���걨����ӡ��˰������������
            if (yhsbgz == null)
            {
                // �����µ�ӡ��˰������������
                yhsbgz = this.createNewYhsbgz(djInfo, nd);
                // �����µ�ӡ��˰������ϸ����
                yhsbgmxList = this.createNewYhsgm(jsjdm, nd, yhsSzsmList);
            }
            else
            {
                ifSB = "ysb";
                // �����ݿ��в���ӡ��˰������ϸ����
                yhsbgmxList = this.getYhsbgmx(conn, orManager, jsjdm, nd, djInfo);
            }
            // ���췵������Map
            retMap = new HashMap();
            retMap.put(YhsMapConstant.YHSBGZ, yhsbgz);
            retMap.put(YhsMapConstant.LIST_YHSBGMX, yhsbgmxList);
            retMap.put(YhsMapConstant.JBSJ, djInfo);
            retMap.put(YhsMapConstant.LIST_SZSM, yhsSzsmList);
            retMap.put(YhsMapConstant.IFSB, ifSB);
            return retMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ");
        }
        finally
        {
            // �ر����ݿ�����
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * ɾ��ӡ��˰��������
     * 
     * @param data
     *            Map
     * @return Object
     * @throws BaseException
     */
    private Map doDelete(VOPackage vo, String aType) throws BaseException
    {
        Map data = (Map) vo.getData();
        Connection conn = null;
        ORManager orManager = null;
        DzyjsjVO dzyj = null;
        if (aType.equals("delete"))
        {
            dzyj = saveSignData(vo);
            data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        try
        {
            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // ȡ��ӡ��˰��������ֵ����
            SWDJJBSJ jbsj = (SWDJJBSJ) data.get(YhsMapConstant.JBSJ);

            String nd = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
            // /System.out.println(nd + "ddddddddddddddddddd");
            // Timestamp curTime = new Timestamp(System.currentTimeMillis());
            // String nd = new SimpleDateFormat("yyyy").format(curTime);
            // ��ѯӡ��˰������������
            Yhsbgz yhsbgz = this.getYhsbzsj(conn, orManager, jbsj.getJsjdm(), nd, jbsj);

            if (yhsbgz == null)
            {
                // �����µ�ӡ��˰������������
                return null;
            }

            // ɾ��ӡ��˰������ϸ����
            // ����ɾ��SQL��ɾ������
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_yhsbgmx");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jbsj.getJsjdm());
            pstmt.setString(2, nd);
            pstmt.setString(3, jbsj.getSwjgzzjgdm().substring(0, 2));

            // ɾ��ӡ��˰��ȱ�������
            pstmt.execute();

            // ɾ��ӡ��˰������������
            sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_yhsbgz");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            sql = sqlBuffer.toString();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jbsj.getJsjdm());
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, jbsj.getSwjgzzjgdm().substring(0, 2));

            // ɾ��ӡ��˰��ȱ�������
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return data;
    }

    /**
     * ȡ��ӡ��˰��˰��˰Ŀ
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @return List
     * @throws BaseException
     */
    private List getYhsSzsm(Connection conn, ORManager orManager) throws BaseException
    {
        // ӡ��˰˰��˰ĿList
        List yhsSzsmList = null;
        try
        {
            // �����ѯ����
            Vector v = new Vector();

            v.add("SZSMDM LIKE '16__%' ");
            v.add("ZXBS = '0'");
            v.add("SZSMDM <> '160091' ");
            v.add("SZSMDM <> '160092'" + " ORDER BY SZSMDM");

            ORContext szsmContext = new ORContext(Szsm.class.getName(), v);
            yhsSzsmList = orManager.query(SESSIONID, conn, szsmContext);

            return yhsSzsmList;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡӡ��˰������������
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @return Yhsbgz
     * @throws BaseException
     */
    private Yhsbgz getYhsbzsj(Connection conn, ORManager orManager, String jsjdm, String nd, SWDJJBSJ jbsj)
            throws BaseException
    {
        try
        {
            // ӡ��˰��������ֵ����List
            List yhsbgzList = null;

            // �����ѯ����
            Vector v = new Vector();
            v.add("JSJDM = '" + jsjdm + "' ");
            v.add("ND = '" + nd + "' ");
            v.add("FSDM = '" + CodeConstant.FSDM_WSSB + "'");
            v.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");

            ORContext yhsbgzContext = new ORContext(Yhsbgz.class.getName(), v);
            yhsbgzList = orManager.query(this.SESSIONID, conn, yhsbgzContext);
            if (yhsbgzList == null || yhsbgzList.size() == 0)
            {
                // ���ȡ�������ݣ��򷵻�null
                return null;
            }
            // ��������
            return (Yhsbgz) yhsbgzList.get(0);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡӡ��˰������ϸ����
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @return List
     * @throws BaseException
     */
    private List getYhsbgmx(Connection conn, ORManager orManager, String jsjdm, String nd, SWDJJBSJ jbsj)
            throws BaseException
    {
        try
        {
            // ӡ��˰������ϸ��ֵ����List
            List yhsbgmxList = null;

            // �����ѯ����
            Vector v = new Vector();
            v.add("JSJDM = '" + jsjdm + "'");
            v.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");
            v.add("ND = '" + nd + "'  ORDER BY SZSMDM");

            ORContext yhsbgmxContext = new ORContext(Yhsbgmx.class.getName(), v);
            // ����ORMapping��ѯӡ��˰������ϸ��
            yhsbgmxList = orManager.query(this.SESSIONID, conn, yhsbgmxContext);
            if (yhsbgmxList == null || yhsbgmxList.size() == 0)
            {
                // ���ȡ�������ݣ��׳��쳣
                throw new SystemException("���������ݣ�û����ϸ���ݣ�");
            }
            // ����ӡ��˰������ϸ��ֵ����List
            return yhsbgmxList;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����µ�ӡ��˰�걨����
     * 
     * @param djInfo
     *            SWDJJBSJ
     * @param nd
     *            String
     * @return Yhsbgz
     */
    private Yhsbgz createNewYhsbgz(SWDJJBSJ djInfo, String nd)
    {
        Yhsbgz yhsbgz = new Yhsbgz();
        // ����˰����������
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        // ����ӡ��˰��������ֵ�������Ϥ
        yhsbgz.setJsjdm(djInfo.getJsjdm());
        yhsbgz.setNd(nd);
        yhsbgz.setFsdm(CodeConstant.FSDM_WSSB);
        yhsbgz.setLrr(this.userData.yhid);
        yhsbgz.setSkssjsrq((Timestamp) skssrqMap.get(Skssrq.SKSSJSRQ));
        yhsbgz.setSkssksrq((Timestamp) skssrqMap.get(Skssrq.SKSSKSRQ));
        yhsbgz.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
        yhsbgz.setHjfs(new BigDecimal(0));
        yhsbgz.setHjjsje(new BigDecimal(0.00));
        yhsbgz.setHjynse(new BigDecimal(0.00));
        // ����ӡ��˰��������ֵ����
        return yhsbgz;
    }

    /**
     * ����ӡ��˰������ϸ����List
     * 
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @param yhsSzsmInfo
     *            List
     * @return List
     */
    private List createNewYhsgm(String jsjdm, String nd, List yhsSzsmInfo)
    {
        List yhsbgmxList = new ArrayList();
        // ���ɳ�ʼֵ
        BigDecimal defaultValue = new BigDecimal("0");
        // ����ӡ��˰˰��˰Ŀ�ô�С������ӡ��˰������ϸ���ݣ�����������
        for (int i = 0; i < yhsSzsmInfo.size(); i++)
        {
            Yhsbgmx yhsbgmx = new Yhsbgmx();
            yhsbgmx.setJsjdm(jsjdm);
            yhsbgmx.setNd(nd);
            yhsbgmx.setSzsmdm(((Szsm) yhsSzsmInfo.get(i)).getSzsmdm());
            yhsbgmx.setFs(defaultValue);
            yhsbgmx.setJsje(defaultValue);
            yhsbgmx.setSl(((Szsm) yhsSzsmInfo.get(i)).getSl());
            yhsbgmx.setSjse(defaultValue);
            // ��ӵ�ӡ��˰������ϸֵ����List
            yhsbgmxList.add(yhsbgmx);
        }
        // ����ӡ��˰������ϸֵ����List
        return yhsbgmxList;
    }

    private DzyjsjVO saveSignData(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        YhsbgmxVO yhs = (YhsbgmxVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        UserData ud = vo.getUserData();
        DzyjsjVO dzyj = null;
        dzyj = (DzyjsjVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj,yhs.getSbxx().getNd(), "", yhs.getSbxx().getSkssksrq(), "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return dzyj;
    }
    /**
     * ɾ��ӡ��˰������������
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param yhsbgz
     *            Yhsbgz
     * @throws BaseException
     */
    // private void deleteYhsbgz(Connection conn, ORManager orManager, Yhsbgz
    // yhsbgz)
    // throws BaseException
    // {
    // try
    // {
    // // ɾ��ӡ��˰������������
    // orManager.deleteObject(this.SESSIONID, conn, yhsbgz);
    // }
    // catch(Exception ex)
    // {
    // throw ExceptionUtil.getBaseException(ex, "ɾ��ӡ��˰�����������ݴ���");
    // }
    // }
    /**
     * ɾ��ӡ��˰������ϸ����
     * 
     * @param conn
     *            Connection
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @throws BaseException
     */
    // private void deleteYhsbgmx(Connection conn, String jsjdm, String nd)
    // throws BaseException
    // {
    // try
    // {
    // // ����ɾ��SQL��ɾ������
    // StringBuffer sqlBuffer = new StringBuffer();
    // sqlBuffer.append("delete ");
    // sqlBuffer.append("from sbdb.sb_jl_yhsbgmx");
    // sqlBuffer.append(" where jsjdm = ? ");
    // sqlBuffer.append("and nd = ? ");
    // String sql = sqlBuffer.toString();
    //
    // PreparedStatement pstmt = conn.prepareStatement(sql);
    // pstmt.setString(1, jsjdm);
    // pstmt.setString(2, nd);
    // // ɾ��ӡ��˰��ȱ�������
    // pstmt.execute();
    //
    // }
    // catch(Exception ex)
    // {
    // throw ExceptionUtil.getBaseException(ex, "ɾ��ӡ��˰������ϸ���ݴ���");
    // }
    // }
}