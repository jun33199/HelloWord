package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.processor;

import java.util.*;
import java.sql.*;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.*;

import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.QycwzbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.*;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.YhsbgmxVO;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.model.domain.Sbbbxm;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsnb;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.model.domain.Qyjbcwzb;
import com.ttsoft.bjtax.shenbao.model.domain.Lygf;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttwh;

import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.service.processor.*;
import com.ttsoft.bjtax.jikuai.zwcl.inf.*;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import java.text.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;

/**
 * @author He Zhiyong
 * @version 1.0 ��ҵ����˰�걨
 */
public class QysdsnbProcessor implements Processor
{

    /**
     * ����ҵ���������ֵ����ҵ�����
     * 
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // ����ҵ���������ֵ����ҵ�����
        try
        {
            switch (vo.getAction())
            {
                case QysdsnbActionConstant.INT_ACTION_QUERY_All:
                    return doQuery((Map) vo.getData(), vo.getAction());

                case QysdsnbActionConstant.INT_ACTION_QUERY_CWZB:
                    return doQuery((Map) vo.getData(), vo.getAction());

                case QysdsnbActionConstant.INT_ACTION_SAVE_ALL:
                    return doSave(vo);

                case QysdsnbActionConstant.INT_ACTION_SAVE_CWZB:
                    return doSave(vo);

                case QysdsnbActionConstant.INT_ACTION_DELETE_ALL:
                    return doDelete(vo);

                case QysdsnbActionConstant.INT_ACTION_DELETE_CWZB:
                    return doDelete(vo);

                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ�Ǽ����ݺ���ҵ����˰�걨���ݣ���ص���Ŀ��������
     * 
     * @param pData
     *            Map
     * @param type
     *            ��ѯ���� :��ѯȫ�����ݣ�1:��ѯ��ҵ����ָ������
     * @return QysdsnbData ��ҵ����˰�걨����
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private QysdsnbData doQuery(Map pData, int type) throws BaseException
    {
        // ��ҵ����˰�걨data(include all)
        QysdsnbData qysdsnbData = new QysdsnbData();

        // ���ݿ����Ӷ���
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;

        try
        {
            // ȡ�ü��������
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            // ȡ�����ڲ���
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // ����˰�������������
            String strYear = getCurssrqYear(curDate);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            qysdsnbData.setDjJbsj(jbsj);

            // �ӵǼǻ���������ȡ�õǼ�ע�����ʹ���
            String djzclxdm = jbsj.getDjzclxdm();

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            if (type == QysdsnbActionConstant.INT_ACTION_QUERY_All)
            {
                // ��ѯȫ������
                doQueryQysdsnb(jsjdm, strYear, qysdsnbData.getNbData(), conn, orManager, jbsj);
                doQueryCwzb(jsjdm, strYear, qysdsnbData.getCwzbData(), conn, orManager, jbsj);

                if (djzclxdm.equals(CodeConstant.DJZCLXDM_SYDW) || djzclxdm.equals(CodeConstant.DJZCLXDM_SHTT))
                {
                    doQuerySydw(jsjdm, strYear, qysdsnbData.getSydwData(), conn, orManager, jbsj);
                }
                doQueryLygf(jsjdm, strYear, qysdsnbData.getLygfData(), conn, orManager, jbsj);
            }
            else if (type == QysdsnbActionConstant.INT_ACTION_QUERY_CWZB)
            {
                // ��ѯ����ָ������
                doQueryCwzb(jsjdm, strYear, qysdsnbData.getCwzbData(), conn, orManager, jbsj);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }

        return qysdsnbData;
    }

    /**
     * ��ѯ
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param nbData
     *            �걨����
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            �Ǽ�����
     * @return QysdsnbData.NBData
     * @throws BaseException
     */
    private QysdsnbData.NBData doQueryQysdsnb(String jsjdm, String strYear, QysdsnbData.NBData nbData, Connection conn,
            ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List nbList = null;
        List xmdmList = null;

        // ������ҵ����˰�걨��ѯ����
        Vector v = new Vector();

        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
        ORContext qysdsnbContext = new ORContext(Qysdsnb.class.getName(), v);

        try
        {
            // ��ѯ��ҵ����˰�걨����
            nbList = orManager.query(0, conn, qysdsnbContext);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰�걨����ʧ��");
        }

        // ��ѯ��Ŀ��������
        xmdmList = getSbbbxm("QYSDSNB", conn, orManager);
        // ���췵������
        nbData.setDefineList(xmdmList);
        nbData.setNbData(nbList);

        return nbData;
    }

    /**
     * ��ѯ
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param nbData
     *            �걨����
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            �Ǽ�����
     * @return QysdsnbData.NBData
     * @throws BaseException
     */
    private List doQueryQysdsnbData(String jsjdm, String strYear, Connection conn, ORManager orManager, SWDJJBSJ djSj)
            throws BaseException
    {
        List nbList = null;

        // ������ҵ����˰�걨��ѯ����
        Vector v = new Vector();

        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
        ORContext qysdsnbContext = new ORContext(Qysdsnb.class.getName(), v);

        try
        {
            // ��ѯ��ҵ����˰�걨����
            nbList = orManager.query(0, conn, qysdsnbContext);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰�걨����ʧ��");
        }

        return nbList;
    }

    /**
     * ��ѯ����ָ��
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param cwzbData
     *            �걨����
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            �Ǽ�����
     * @return QysdsnbData.CwzbData
     * @throws BaseException
     */
    private QysdsnbData.CwzbData doQueryCwzb(String jsjdm, String strYear, QysdsnbData.CwzbData cwzbData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List cwzbList = null;
        List xmdmList = null;

        // �����ѯ����
        Vector v = new Vector();
        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");

        try
        {
            // ��ѯ
            ORContext contextCwzb = new ORContext(Qyjbcwzb.class.getName(), v);
            cwzbList = orManager.query(0, conn, contextCwzb);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ��ҵ����ָ������ʧ��");
        }

        // ��ѯ��Ŀ��������
        xmdmList = getSbbbxm("QYCWZB", conn, orManager);

        cwzbData.setDefineList(xmdmList);
        cwzbData.setCwzbData(cwzbList);

        return cwzbData;
    }

    /**
     * ��ѯ��Ӫ�ɷ�
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param lygfData
     *            QysdsnbData.LygfData
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            �Ǽ�����
     * @return QysdsnbData.LygfData
     * @throws BaseException
     */
    private QysdsnbData.LygfData doQueryLygf(String jsjdm, String strYear, QysdsnbData.LygfData lygfData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List lygfList = new ArrayList();
        try
        {
            // �����ѯ����
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
            v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
            v.add("nd = '" + strYear + "' ORDER BY to_number(FL), to_number(XH)");

            // ��ѯ
            ORContext cntLygf = new ORContext(Lygf.class.getName(), v);
            lygfList = orManager.query(0, conn, cntLygf);
            lygfData.setLygfData(lygfList);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "��ѯ��Ӫ�ɷ�����ʧ��");
        }

        return lygfData;
    }

    /**
     * ��ѯ��ҵ��λ
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param sydwData
     *            QysdsnbData.SydwData
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            �Ǽ�����
     * @return QysdsnbData.SydwData
     * @throws BaseException
     */
    private QysdsnbData.SydwData doQuerySydw(String jsjdm, String strYear, QysdsnbData.SydwData sydwData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        // ��ҵ��λ����List
        List sydwList = new ArrayList();
        // ��ҵ��λ��ϸList
        List mxList = new ArrayList();
        // ��ҵ��λ�ĺ�����List
        List whList = new ArrayList();
        // ��Ŀ���������List
        List xmdmList = new ArrayList();

        try
        {
            // �����ѯ����
            Vector v = new Vector();
            String strSqlJsjdm = "jsjdm = '" + jsjdm + "'";
            String strSqlYear = "nd = '" + strYear + "'";
            String strSqlQxdm = "qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'";
            v.add(strSqlJsjdm);
            v.add(strSqlYear);
            v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
            v.add(strSqlQxdm);

            Vector v2 = new Vector();
            v2.add(strSqlJsjdm);
            v2.add(strSqlQxdm);
            v2.add(strSqlYear + " order by to_number(hc)");

            // ��ѯ
            ORContext CntZb = new ORContext(Sydwshttsrzb.class.getName(), v);
            ORContext CntMx = new ORContext(Sydwshttsrmx.class.getName(), v2);
            ORContext CntWh = new ORContext(Sydwshttwh.class.getName(), v2);

            sydwList = orManager.query(0, conn, CntZb);
            if (sydwList.size() != 0)
            {
                mxList = orManager.query(0, conn, CntMx);
                whList = orManager.query(0, conn, CntWh);
                sydwData.setSydwshttsrzb((Sydwshttsrzb) sydwList.get(0));
                sydwData.setMxList(mxList);
                sydwData.setWhList(whList);
            }
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "��ѯ��ҵ��λ�������������ʧ��");
        }
        try
        {
            xmdmList = getSbbbxm("SYDW", conn, orManager);
            sydwData.setDefineList(xmdmList);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "��ѯ��Ŀ�������󣡣���ҵ��λ��");
        }

        return sydwData;
    }

    /**
     * ������Ŀ����ȡ����Ŀ���������
     * 
     * @param xmss
     *            ��Ŀ����
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @return Sbbbxmֵ����List
     * @throws BaseException
     */
    private List getSbbbxm(String xmss, Connection conn, ORManager orManager) throws BaseException
    {

        List result = new ArrayList();
        try
        {
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("select max(SJKJ) ");
            sqlBuffer.append(" from sbdb.SB_DM_SBBBXM ");
            sqlBuffer.append(" where XMSS = ? ");

            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, xmss);

            ResultSet rs = pstmt.executeQuery();
            String sjkj = null;
            if (rs.next())
            {
                sjkj = rs.getString(1);
            }
            else
            {
                throw new Exception();
            }

            try
            {
                rs.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
            }

            Vector v = new Vector();
            String strSqlXmss = "xmss = '" + xmss + "'";
            String strSql = "SJKJ= '" + sjkj + "' ORDER BY to_number(HC)";
            v.add(strSqlXmss);
            v.add(strSql);

            ORContext qysdsnbContext = new ORContext(Sbbbxm.class.getName(), v);
            result = orManager.query(0, conn, qysdsnbContext);
            return result;
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "������Ŀ��������");
        }
    }

    /**
     * ��ѯ�Ǽ����ݺ���ҵ����˰�걨���ݣ���ص���Ŀ��������
     * 
     * @param pData
     *            Map
     * @param type
     *            ��ѯ���� :��ѯȫ�����ݣ�1:��ѯ��ҵ����ָ������
     * @return QysdsnbData ��ҵ����˰�걨����
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doSave(VOPackage vo) throws BaseException
    {

        Map pData = (Map) vo.getData();
        int type = vo.getAction();
        Connection conn = null;
        ORManager orManager = null;
        QysdsnbData qysdsnbData = null;
        DzyjsjVO dzyj = null;
        Map retData = new HashMap();
        try
        {
            // ȡ�ü��������
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            // ȡ�����ڲ���
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // ��ҵ����˰�걨data(include all)
            qysdsnbData = (QysdsnbData) pData.get(QysdsnbMapConstant.STRING_KEY_QYSDSNB_DATA);

            // ����˰�������������
            String strYear = getCurssrqYear(curDate);

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            /**
             * modified by hazhengze 20060418 start
             * ���Ӷ�sfdb.sf_jl_qysdszsfs_his��nsrzt���޸�
             */
            try
            {
                String sql = "update sfdb.sf_jl_qysdszsfs_his set nsrzt='10',lrrq=sysdate where jsjdm='" + jsjdm + "'";
                Statement stat = conn.createStatement();
                stat.executeUpdate(sql);
                stat.close();
            }
            catch (Exception ex1)
            {
                // �׳��쳣
                ex1.printStackTrace();
                new ApplicationException("���ݿ���¼�¼ʧ�ܣ�" + jsjdm + ":" + ex1.getMessage());
            }
            /**
             * modified by hazhengze 20060418 end
             */

            if (type == QysdsnbActionConstant.INT_ACTION_SAVE_ALL)
            {
                // saveȫ������
                doSaveNB(jsjdm, strYear, qysdsnbData.getNbData(), jbsj, conn, orManager);
                dzyj = doSaveCwzb(vo, jsjdm, strYear, qysdsnbData.getCwzbData(), jbsj, conn, orManager);
                doSaveSydw(jsjdm, strYear, qysdsnbData.getSydwData(), jbsj, conn, orManager);
                doSaveLygf(jsjdm, strYear, qysdsnbData.getLygfData(), jbsj, conn, orManager);
                if (qysdsnbData.getJm() != null)
                {
                    doSaveJm(qysdsnbData.getJm(), conn, orManager);
                }
            }
            else if (type == QysdsnbActionConstant.INT_ACTION_SAVE_CWZB)
            {
                // save����ָ������
                dzyj = doSaveCwzb(vo, jsjdm, strYear, qysdsnbData.getCwzbData(), jbsj, conn, orManager);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        retData.put(CAcodeConstants.YWDM_SB_WS_QYSDS_NB, qysdsnbData);
        retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        return retData;
    }

    private void doSaveJm(Jm jm, Connection conn, ORManager orManager)
    {
        String ysjcdm = null;
        String yskmdm = null;
        try
        {
            // �Ȳ�ѯ��������,���������󱣴�
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Vector v = new Vector();
            v.add("jsjdm = '" + jm.getJsjdm() + "'");
            v.add("nd = '" + jm.getNd() + "'");
            v.add("qxdm = '" + jm.getQxdm() + "'"); // ���ش���
            v.add("szsmdm = '" + jm.getSzsmdm() + "'");
            v.add("jmlx = '" + jm.getJmlx() + "'");
            v.add("fsdm='" + jm.getFsdm() + "'");
            v.add("SKSSKSRQ = to_date('" + sdf.format(jm.getSkssksrq()) + "','yyyy-MM-dd')");
            v.add("SKSSJSRQ = to_date('" + sdf.format(jm.getSkssjsrq()) + "','yyyy-MM-dd')");
            System.out.println("QysdsnbProcessor.doSave:JMSQL=" + v.toString());

            ORContext jmContext = new ORContext(Jm.class.getName(), v);

            // ��ѯ��ҵ����˰��������
            List jmList = orManager.query(0, conn, jmContext);

            // ���췵������
            Jm theJm = null;
            if (jmList != null && jmList.size() > 0)
            {
                theJm = (Jm) jmList.get(0);
                System.out.println("QysdsnbProcessor.doSave:JM.jzbz=" + jm.getJzbz());
            }

            if (theJm == null)
            {
                ysjcdm = FriendHelper.getYsjc(jm.getJsjdm(), jm.getSzsmdm(), jm.getSkssjsrq()).getYsjcdm();
                yskmdm = JKAdapter.getInstance().getYskm(jm.getSzsmdm(), jm.getDjzclxdm(), jm.getGjbzhydm(), ysjcdm)
                        .getYskmdm();

                jm.setYskmdm(yskmdm);
                jm.setYsjcdm(ysjcdm);

                orManager.makePersistent(0, conn, jm);

            }
            else
            {
                if (theJm.getJzbz() != null && theJm.getJzbz().substring(0, 1).equals("0"))
                {
                    theJm.setJmse(jm.getJmse());
                    theJm.setJsje(jm.getJsje());
                    theJm.setLrrq(jm.getLrrq());
                    orManager.updateObject(0, conn, theJm);

                }
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * �����걨����
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param nbData
     *            �걨����
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveNB(String jsjdm, String strYear, QysdsnbData.NBData nbData, SWDJJBSJ jbsj, Connection conn,
            ORManager orManager) throws BaseException
    {
        // ��ɾ��
        doDeleteQysdsnb(jsjdm, strYear, jbsj, conn);

        List qysdsnbList = nbData.getNbData();
        try
        {
            if (qysdsnbList != null && qysdsnbList.size() > 0)
            {
                // ������ҵ����˰�걨����
                for (int i = 0; i < qysdsnbList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Qysdsnb) qysdsnbList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������ҵ����˰�걨���ݴ���");
        }
    }

    /**
     * �������ָ��
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param cwzbData
     *            QysdsnbData.CwzbData
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private DzyjsjVO doSaveCwzb(VOPackage vo, String jsjdm, String strYear, QysdsnbData.CwzbData cwzbData,
            SWDJJBSJ jbsj, Connection conn, ORManager orManager) throws BaseException
    {
        DzyjsjVO dzyj = saveSignData(vo);

        // ��ɾ��
        doDeleteCwzb(vo, jsjdm, strYear, jbsj, conn, "save");

        List cwzbList = cwzbData.getCwzbData();
        try
        {
            if (cwzbList != null && cwzbList.size() > 0)
            {
                // ������ҵ����˰�걨����
                for (int i = 0; i < cwzbList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Qyjbcwzb) cwzbList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������ҵ��������ָ�����ݴ���");
        }
        return dzyj;
    }

    /**
     * ������ҵ��λ
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param sydwData
     *            QysdsnbData.SydwData
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveSydw(String jsjdm, String strYear, QysdsnbData.SydwData sydwData, SWDJJBSJ jbsj,
            Connection conn, ORManager orManager) throws BaseException
    {

        // ��ɾ��
        doDeleteSydw(jsjdm, strYear, jbsj, conn);

        Sydwshttsrzb zbData = sydwData.getSydwshttsrzb();
        // �����������Ϊ�գ��򲻱���
        if (zbData == null)
        {
            return;
        }

        Timestamp curTime = new Timestamp(System.currentTimeMillis());

        try
        {
            orManager.makePersistent(0, conn, sydwData.getSydwshttsrzb());
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������ҵ��λ����������������ݴ���");
        }

        try
        {
            for (int i = 0; i < sydwData.getMxList().size(); i++)
            {
                orManager.makePersistent(0, conn, sydwData.getMxList().get(i));
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������ҵ��λ�����������ϸ���ݴ���");
        }

        try
        {
            for (int i = 0; i < sydwData.getWhList().size(); i++)
            {
                orManager.makePersistent(0, conn, sydwData.getWhList().get(i));
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������ҵ��λ����������ĺ����ݴ���");
        }
    }

    /**
     * ������Ӫ�ɷ�
     * 
     * @param jsjdm
     *            ���������
     * @param strYear
     *            ���
     * @param lygfData
     *            QysdsnbData.LygfData
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveLygf(String jsjdm, String strYear, QysdsnbData.LygfData lygfData, SWDJJBSJ jbsj,
            Connection conn, ORManager orManager) throws BaseException
    {
        // ��ɾ��
        doDeleteLygf(jsjdm, strYear, jbsj, conn);

        List lygfList = lygfData.getLygfData();
        try
        {
            if (lygfList != null && lygfList.size() > 0)
            {
                // ������Ӫ�ɷ�����
                for (int i = 0; i < lygfList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Lygf) lygfList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "������Ӫ�ɷ����ݴ���");
        }
    }

    /**
     * ��ѯ�Ǽ����ݺ���ҵ����˰�걨���ݣ���ص���Ŀ��������
     * 
     * @param pData
     *            Map
     * @param type
     *            ��ѯ���� :��ѯȫ�����ݣ�1:��ѯ��ҵ����ָ������
     * @return QysdsnbData ��ҵ����˰�걨����
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doDelete(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        int type = vo.getAction();
        // ���ݿ����Ӷ���
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;
        DzyjsjVO dzyj = null;
        Map retData = new HashMap();

        try
        {
            // ȡ�ü��������
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            // ȡ�����ڲ���
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // ����˰�������������
            String strYear = getCurssrqYear(curDate);

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            if (type == QysdsnbActionConstant.INT_ACTION_DELETE_ALL)
            {

                List nbData = doQueryQysdsnbData(jsjdm, strYear, conn, orManager, jbsj);
                if (nbData == null || nbData.size() <= 0)
                {
                    return null;
                }
                // ɾ��ȫ������
                doDeleteQysdsnb(jsjdm, strYear, jbsj, conn);
                doDeleteJm(jsjdm, strYear, jbsj, curDate, conn);
                // System.out.println("delete jm ok");
                dzyj = doDeleteCwzb(vo, jsjdm, strYear, jbsj, conn, "delete");
                // System.out.println("delete cwzb ok");
                doDeleteSydw(jsjdm, strYear, jbsj, conn);
                // System.out.println("delete sydw ok");
                doDeleteLygf(jsjdm, strYear, jbsj, conn);
                // System.out.println("delete lygf ok");
            }
            else if (type == QysdsnbActionConstant.INT_ACTION_DELETE_CWZB)
            {
                // ɾ������ָ������
                dzyj = doDeleteCwzb(vo, jsjdm, strYear, jbsj, conn, "delete");
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�쳣");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        return retData;
    }

    /**
     * ɾ�������걨����
     * 
     * @param jsjdm
     *            ���������
     * @param nd
     *            ���
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @throws BaseException
     */
    private void doDeleteJm(String jsjdm, String nd, SWDJJBSJ jbsj, Timestamp curDate, Connection conn)
            throws BaseException
    {
        PreparedStatement pstmt = null;
        try
        {
            StringBuffer sqlBuffer = new StringBuffer();

            // ɾ����ؼ�������

            Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curDate);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
            String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));

            sqlBuffer.append("delete ");
            sqlBuffer.append("from SBDB.SB_JL_JM ");
            sqlBuffer.append("where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            sqlBuffer.append("and szsmdm = '300000' ");
            sqlBuffer.append("and jmlx = '1' and fsdm='5' ");
            sqlBuffer.append("and SKSSKSRQ = to_date('" + skssksrq + "','yyyy-MM-dd') ");
            sqlBuffer.append("and SKSSJSRQ = to_date('" + skssjsrq + "','yyyy-MM-dd') ");
            sqlBuffer.append("and jzbz like '0%'");
            String sql = sqlBuffer.toString();
            System.out.println("QysdsnbProcessor.doDelete:JMSQL=" + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, jbsj.getSwjgzzjgdm().substring(0, 2));
            // ɾ��
            pstmt.execute();

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����ҵ����˰�걨���ݴ���");
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * ɾ����ҵ����˰�걨����
     * 
     * @param jsjdm
     *            ���������
     * @param nd
     *            ���
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @throws BaseException
     */
    private void doDeleteQysdsnb(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        PreparedStatement pstmt = null;
        try
        {
            // ɾ����ҵ����˰�걨����
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_qysdsnb");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, jbsj.getSwjgzzjgdm().substring(0, 2));
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����ҵ����˰�걨���ݴ���");
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * ɾ������ָ������
     * 
     * @param jsjdm
     *            ���������
     * @param nd
     *            ���
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private DzyjsjVO doDeleteCwzb(VOPackage vo, String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn, String aType)
            throws BaseException
    {
        DzyjsjVO dzyj = null;
        if (aType.equals("delete"))
        {
            dzyj = saveSignData(vo);
        }

        PreparedStatement pstmt = null;
        try
        {
            // ɾ������ָ������
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append(" from sbdb.sb_jl_qyjbcwzb ");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, jbsj.getSwjgzzjgdm().substring(0, 2));
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ������ָ�����ݴ���");
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch (Exception e)
            {
            }
        }
        return dzyj;
    }

    /**
     * ɾ����ҵ��λ�����������������ϸ���ݺ���ҵ��λ������������ĺ�����
     * 
     * @param jsjdm
     *            ���������
     * @param nd
     *            ���
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private void doDeleteSydw(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        String qxdm = jbsj.getSwjgzzjgdm().substring(0, 2);
        try
        {
            // ɾ����ҵ��λ��ϸ����
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append(" from sbdb.sb_jl_sydwshttsrmx ");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, qxdm);
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����ҵ��λ��ϸ���ݴ���");
        }

        try
        {
            // ɾ����ҵ��λ�ĺ�����
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append(" from sbdb.sb_jl_sydwshttwh ");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, qxdm);
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����ҵ��λ�ĺ����ݴ���");
        }

        try
        {
            // ɾ����ҵ��λ��������
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append(" from sbdb.sb_jl_sydwshttsrzb ");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, qxdm);
            pstmt.execute();
            pstmt.close();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����ҵ��λ�������ݴ���");
        }
    }

    /**
     * ɾ����Ӫ���ɷݡ�������ʡ�Ͷ����ҵ�걨����
     * 
     * @param jsjdm
     *            ���������
     * @param nd
     *            ���
     * @param jbsj
     *            �Ǽǻ�������
     * @param conn
     *            Connection
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private void doDeleteLygf(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        try
        {
            // ɾ����Ӫ�ɷ�����
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_lygf");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, jbsj.getSwjgzzjgdm().substring(0, 2));
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ����Ӫ�ɷ����ݴ���");
        }
    }

    /**
     * ���˰�������������
     * 
     * @param curDate
     *            ����
     * @return String
     */
    private String getCurssrqYear(Timestamp curDate)
    {
        // ����˰�������������
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int year = calendar.get(calendar.YEAR) - 1;
        return String.valueOf(year);
    }

    private DzyjsjVO saveSignData(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        QycwzbVO qycw = (QycwzbVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        UserData ud = vo.getUserData();
        DzyjsjVO dzyj = null;
        dzyj = (DzyjsjVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj, qycw.getSbxx().getNd(), qycw.getSbxx().getJd(), qycw.getSbxx()
                    .getSkssksrq(), "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return dzyj;
    }

}
