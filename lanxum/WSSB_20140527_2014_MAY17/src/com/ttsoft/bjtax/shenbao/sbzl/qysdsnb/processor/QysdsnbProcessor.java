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
 * @version 1.0 企业所得税年报
 */
public class QysdsnbProcessor implements Processor
{

    /**
     * 根据业务操作类型值来做业务操作
     * 
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // 根据业务操作类型值来做业务操作
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
     * 查询登记数据和企业所得税年报数据，相关的项目代码数据
     * 
     * @param pData
     *            Map
     * @param type
     *            查询类型 :查询全部数据，1:查询企业财务指标数据
     * @return QysdsnbData 企业所得税年报数据
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private QysdsnbData doQuery(Map pData, int type) throws BaseException
    {
        // 企业所得税年报data(include all)
        QysdsnbData qysdsnbData = new QysdsnbData();

        // 数据库连接对象
        Connection conn = null;
        // OR实例
        ORManager orManager = null;

        try
        {
            // 取得计算机代码
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            // 取得日期参数
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // 计算税款所属日期年份
            String strYear = getCurssrqYear(curDate);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            qysdsnbData.setDjJbsj(jbsj);

            // 从登记基本数据中取得登记注册类型代码
            String djzclxdm = jbsj.getDjzclxdm();

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            if (type == QysdsnbActionConstant.INT_ACTION_QUERY_All)
            {
                // 查询全部数据
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
                // 查询财务指标数据
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
     * 查询
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param nbData
     *            年报数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            登记数据
     * @return QysdsnbData.NBData
     * @throws BaseException
     */
    private QysdsnbData.NBData doQueryQysdsnb(String jsjdm, String strYear, QysdsnbData.NBData nbData, Connection conn,
            ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List nbList = null;
        List xmdmList = null;

        // 构造企业所得税年报查询条件
        Vector v = new Vector();

        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
        ORContext qysdsnbContext = new ORContext(Qysdsnb.class.getName(), v);

        try
        {
            // 查询企业所得税年报数据
            nbList = orManager.query(0, conn, qysdsnbContext);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询企业所得税年报数据失败");
        }

        // 查询项目代码数据
        xmdmList = getSbbbxm("QYSDSNB", conn, orManager);
        // 构造返回数据
        nbData.setDefineList(xmdmList);
        nbData.setNbData(nbList);

        return nbData;
    }

    /**
     * 查询
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param nbData
     *            年报数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            登记数据
     * @return QysdsnbData.NBData
     * @throws BaseException
     */
    private List doQueryQysdsnbData(String jsjdm, String strYear, Connection conn, ORManager orManager, SWDJJBSJ djSj)
            throws BaseException
    {
        List nbList = null;

        // 构造企业所得税年报查询条件
        Vector v = new Vector();

        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
        ORContext qysdsnbContext = new ORContext(Qysdsnb.class.getName(), v);

        try
        {
            // 查询企业所得税年报数据
            nbList = orManager.query(0, conn, qysdsnbContext);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询企业所得税年报数据失败");
        }

        return nbList;
    }

    /**
     * 查询财务指标
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param cwzbData
     *            年报数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            登记数据
     * @return QysdsnbData.CwzbData
     * @throws BaseException
     */
    private QysdsnbData.CwzbData doQueryCwzb(String jsjdm, String strYear, QysdsnbData.CwzbData cwzbData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List cwzbList = null;
        List xmdmList = null;

        // 构造查询条件
        Vector v = new Vector();
        v.add("jsjdm = '" + jsjdm + "'");
        v.add("nd = '" + strYear + "'");
        v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
        v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");

        try
        {
            // 查询
            ORContext contextCwzb = new ORContext(Qyjbcwzb.class.getName(), v);
            cwzbList = orManager.query(0, conn, contextCwzb);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询企业企业财务指标数据失败");
        }

        // 查询项目代码数据
        xmdmList = getSbbbxm("QYCWZB", conn, orManager);

        cwzbData.setDefineList(xmdmList);
        cwzbData.setCwzbData(cwzbList);

        return cwzbData;
    }

    /**
     * 查询联营股份
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param lygfData
     *            QysdsnbData.LygfData
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            登记数据
     * @return QysdsnbData.LygfData
     * @throws BaseException
     */
    private QysdsnbData.LygfData doQueryLygf(String jsjdm, String strYear, QysdsnbData.LygfData lygfData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        List lygfList = new ArrayList();
        try
        {
            // 构造查询条件
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
            v.add("qxdm = '" + djSj.getSwjgzzjgdm().substring(0, 2) + "'");
            v.add("nd = '" + strYear + "' ORDER BY to_number(FL), to_number(XH)");

            // 查询
            ORContext cntLygf = new ORContext(Lygf.class.getName(), v);
            lygfList = orManager.query(0, conn, cntLygf);
            lygfData.setLygfData(lygfList);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "查询联营股份数据失败");
        }

        return lygfData;
    }

    /**
     * 查询事业单位
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param sydwData
     *            QysdsnbData.SydwData
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param djSj
     *            登记数据
     * @return QysdsnbData.SydwData
     * @throws BaseException
     */
    private QysdsnbData.SydwData doQuerySydw(String jsjdm, String strYear, QysdsnbData.SydwData sydwData,
            Connection conn, ORManager orManager, SWDJJBSJ djSj) throws BaseException
    {
        // 事业单位主表List
        List sydwList = new ArrayList();
        // 事业单位明细List
        List mxList = new ArrayList();
        // 事业单位文号数据List
        List whList = new ArrayList();
        // 项目代码表数据List
        List xmdmList = new ArrayList();

        try
        {
            // 构造查询条件
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

            // 查询
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
            throw ExceptionUtil.getBaseException(e, "查询事业单位，社会团体数据失败");
        }
        try
        {
            xmdmList = getSbbbxm("SYDW", conn, orManager);
            sydwData.setDefineList(xmdmList);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "查询项目代码表错误！（事业单位）");
        }

        return sydwData;
    }

    /**
     * 根据项目所属取得项目代码表数据
     * 
     * @param xmss
     *            项目所属
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @return Sbbbxm值对象List
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
            throw ExceptionUtil.getBaseException(e, "查找项目代码表错误！");
        }
    }

    /**
     * 查询登记数据和企业所得税年报数据，相关的项目代码数据
     * 
     * @param pData
     *            Map
     * @param type
     *            查询类型 :查询全部数据，1:查询企业财务指标数据
     * @return QysdsnbData 企业所得税年报数据
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
            // 取得计算机代码
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            // 取得日期参数
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // 企业所得税年报data(include all)
            qysdsnbData = (QysdsnbData) pData.get(QysdsnbMapConstant.STRING_KEY_QYSDSNB_DATA);

            // 计算税款所属日期年份
            String strYear = getCurssrqYear(curDate);

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            /**
             * modified by hazhengze 20060418 start
             * 增加对sfdb.sf_jl_qysdszsfs_his的nsrzt的修改
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
                // 抛出异常
                ex1.printStackTrace();
                new ApplicationException("数据库更新记录失败！" + jsjdm + ":" + ex1.getMessage());
            }
            /**
             * modified by hazhengze 20060418 end
             */

            if (type == QysdsnbActionConstant.INT_ACTION_SAVE_ALL)
            {
                // save全部数据
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
                // save财务指标数据
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
            // 先查询减免数据,满足条件后保存
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Vector v = new Vector();
            v.add("jsjdm = '" + jm.getJsjdm() + "'");
            v.add("nd = '" + jm.getNd() + "'");
            v.add("qxdm = '" + jm.getQxdm() + "'"); // 区县代码
            v.add("szsmdm = '" + jm.getSzsmdm() + "'");
            v.add("jmlx = '" + jm.getJmlx() + "'");
            v.add("fsdm='" + jm.getFsdm() + "'");
            v.add("SKSSKSRQ = to_date('" + sdf.format(jm.getSkssksrq()) + "','yyyy-MM-dd')");
            v.add("SKSSJSRQ = to_date('" + sdf.format(jm.getSkssjsrq()) + "','yyyy-MM-dd')");
            System.out.println("QysdsnbProcessor.doSave:JMSQL=" + v.toString());

            ORContext jmContext = new ORContext(Jm.class.getName(), v);

            // 查询企业所得税亏损数据
            List jmList = orManager.query(0, conn, jmContext);

            // 构造返回数据
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
     * 保存年报数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param nbData
     *            年报数据
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveNB(String jsjdm, String strYear, QysdsnbData.NBData nbData, SWDJJBSJ jbsj, Connection conn,
            ORManager orManager) throws BaseException
    {
        // 先删除
        doDeleteQysdsnb(jsjdm, strYear, jbsj, conn);

        List qysdsnbList = nbData.getNbData();
        try
        {
            if (qysdsnbList != null && qysdsnbList.size() > 0)
            {
                // 保存企业所得税年报数据
                for (int i = 0; i < qysdsnbList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Qysdsnb) qysdsnbList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存企业所得税年报数据错误");
        }
    }

    /**
     * 保存财务指标
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param cwzbData
     *            QysdsnbData.CwzbData
     * @param jbsj
     *            登记基本数据
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

        // 先删除
        doDeleteCwzb(vo, jsjdm, strYear, jbsj, conn, "save");

        List cwzbList = cwzbData.getCwzbData();
        try
        {
            if (cwzbList != null && cwzbList.size() > 0)
            {
                // 保存企业所得税年报数据
                for (int i = 0; i < cwzbList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Qyjbcwzb) cwzbList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存企业基本财务指标数据错误");
        }
        return dzyj;
    }

    /**
     * 保存事业单位
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param sydwData
     *            QysdsnbData.SydwData
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveSydw(String jsjdm, String strYear, QysdsnbData.SydwData sydwData, SWDJJBSJ jbsj,
            Connection conn, ORManager orManager) throws BaseException
    {

        // 先删除
        doDeleteSydw(jsjdm, strYear, jbsj, conn);

        Sydwshttsrzb zbData = sydwData.getSydwshttsrzb();
        // 如果主表数据为空，则不保存
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
            throw ExceptionUtil.getBaseException(ex, "保存事业单位，社会团体主表数据错误");
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
            throw ExceptionUtil.getBaseException(ex, "保存事业单位，社会团体明细数据错误");
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
            throw ExceptionUtil.getBaseException(ex, "保存事业单位，社会团体文号数据错误");
        }
    }

    /**
     * 保存联营股份
     * 
     * @param jsjdm
     *            计算机代码
     * @param strYear
     *            年度
     * @param lygfData
     *            QysdsnbData.LygfData
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @throws BaseException
     */
    private void doSaveLygf(String jsjdm, String strYear, QysdsnbData.LygfData lygfData, SWDJJBSJ jbsj,
            Connection conn, ORManager orManager) throws BaseException
    {
        // 先删除
        doDeleteLygf(jsjdm, strYear, jbsj, conn);

        List lygfList = lygfData.getLygfData();
        try
        {
            if (lygfList != null && lygfList.size() > 0)
            {
                // 保存联营股份数据
                for (int i = 0; i < lygfList.size(); i++)
                {
                    orManager.makePersistent(0, conn, (Lygf) lygfList.get(i));
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存联营股份数据错误");
        }
    }

    /**
     * 查询登记数据和企业所得税年报数据，相关的项目代码数据
     * 
     * @param pData
     *            Map
     * @param type
     *            查询类型 :查询全部数据，1:查询企业财务指标数据
     * @return QysdsnbData 企业所得税年报数据
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doDelete(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        int type = vo.getAction();
        // 数据库连接对象
        Connection conn = null;
        // OR实例
        ORManager orManager = null;
        DzyjsjVO dzyj = null;
        Map retData = new HashMap();

        try
        {
            // 取得计算机代码
            String jsjdm = (String) pData.get(QysdsnbMapConstant.STRING_KEY_JSJDM);

            SWDJJBSJ jbsj = (SWDJJBSJ) pData.get(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA);

            // 取得日期参数
            Timestamp curDate = (Timestamp) pData.get(QysdsnbMapConstant.STRING_KEY_DATE);

            // 计算税款所属日期年份
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
                // 删除全部数据
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
                // 删除财务指标数据
                dzyj = doDeleteCwzb(vo, jsjdm, strYear, jbsj, conn, "delete");
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统异常");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        return retData;
    }

    /**
     * 删除减免申报数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param nd
     *            年度
     * @param jbsj
     *            登记基本数据
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

            // 删除相关减免数据

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
            // 删除
            pstmt.execute();

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除企业所得税年报数据错误");
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
     * 删除企业所得税年报数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param nd
     *            年度
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @throws BaseException
     */
    private void doDeleteQysdsnb(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        PreparedStatement pstmt = null;
        try
        {
            // 删除企业所得税年报数据
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
            throw ExceptionUtil.getBaseException(ex, "删除企业所得税年报数据错误");
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
     * 删除财务指标数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param nd
     *            年度
     * @param jbsj
     *            登记基本数据
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
            // 删除财务指标数据
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
            throw ExceptionUtil.getBaseException(ex, "删除财务指标数据错误");
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
     * 删除事业单位社会团体收入主表，明细数据和事业单位社会团体收入文号数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param nd
     *            年度
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private void doDeleteSydw(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        String qxdm = jbsj.getSwjgzzjgdm().substring(0, 2);
        try
        {
            // 删除事业单位明细数据
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
            throw ExceptionUtil.getBaseException(ex, "删除事业单位明细数据错误");
        }

        try
        {
            // 删除事业单位文号数据
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
            throw ExceptionUtil.getBaseException(ex, "删除事业单位文号数据错误");
        }

        try
        {
            // 删除事业单位主表数据
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
            throw ExceptionUtil.getBaseException(ex, "删除事业单位主表数据错误");
        }
    }

    /**
     * 删除联营、股份、中外合资、投资企业申报数据
     * 
     * @param jsjdm
     *            计算机代码
     * @param nd
     *            年度
     * @param jbsj
     *            登记基本数据
     * @param conn
     *            Connection
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private void doDeleteLygf(String jsjdm, String nd, SWDJJBSJ jbsj, Connection conn) throws BaseException
    {
        try
        {
            // 删除联营股份数据
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
            throw ExceptionUtil.getBaseException(ex, "删除联营股份数据错误");
        }
    }

    /**
     * 获得税款所属日期年份
     * 
     * @param curDate
     *            日期
     * @return String
     */
    private String getCurssrqYear(Timestamp curDate)
    {
        // 计算税款所属日期年份
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
