package com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.xmlvo.QysdsjbVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author He Zhiyong
 * @version 1.0 企业所得税亏损申报后台processor类
 */
public class QysdsksProcessor implements Processor
{
    public QysdsksProcessor()
    {
    }

    /**
     * 查询登记信息和亏损申报数据
     * 
     * @param pData
     *            Map
     * @return Map
     * @throws BaseException
     */
    private Map doQuery(Map pData) throws BaseException
    {
        // 数据库连接对象
        Connection conn = null;
        // OR实例
        ORManager orManager = null;
        try
        {
            // 计算机代码
            String jsjdm = null;
            // 当前日期
            Timestamp curDate = null;
            // 税务登记基本数据值对象
            SWDJJBSJ djjbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
            // 企业所得税亏损数据值对象List
            List qysdsjdList = new ArrayList();
            // 返回数据Map
            Map retMap = new HashMap();
            // 取得计算机代码
            jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
            // 取得日期参数
            curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);

            // 取得所在的季度
            String jd = Skssrq.preQuarter(curDate);
            // 取得税款所属日期Map
            Map skssrq = Skssrq.otherSkssrq(curDate);
            // 取得税款所属开始和结束日期
            // Timestamp skssksrq = (Timestamp)skssrq.get(Skssrq.SKSSKSRQ);
            // Timestamp skssjsrq = (Timestamp)skssrq.get(Skssrq.SKSSJSRQ);

            // 取得年度
            String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);

            // 获得数据库连接
            conn = DBResource.getConnection();

            // 获得 ORManager
            orManager = DBResource.getORManager();

            // 构造企业所得税亏损查询条件
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("nd = '" + nd + "'");
            v.add("jd = '" + jd + "'");
            v.add("qxdm = '" + djjbsj.getSwjgzzjgdm().substring(0, 2) + "'"); // 区县代码

            ORContext qysdsjdContext = new ORContext(Qysdsjd.class.getName(), v);

            // 查询企业所得税亏损数据
            qysdsjdList = orManager.query(0, conn, qysdsjdContext);

            // 构造返回数据
            Qysdsjd qysdsjd = null;
            if (qysdsjdList != null && qysdsjdList.size() > 0)
            {
                qysdsjd = (Qysdsjd) qysdsjdList.get(0);
            }
            // 构造返回数居Map
            retMap.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);

            return retMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询企业所得税年报数据失败");
        }
        finally
        {
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 保存企业所得税亏损数据
     * 
     * @param data
     *            Map
     * @throws BaseException
     */
    private Map doSave(VOPackage vop) throws BaseException
    {
        DzyjHelper dh = new DzyjHelper();
        Map retData = new HashMap();
        Map data = (Map) vop.getData();
        UserData ud = vop.getUserData();
        Connection conn = null;
        ORManager orManager = null;
        QysdsjbVO qyjb = (QysdsjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        /*
         * if (ud.getCaflag()) { try { dzyj = dh.saveDzyjsj(dzyj,
         * qyjb.getSbxx().getNd(), qyjb.getSbxx().getJd(), "0", "0"); } catch
         * (com.syax.frame.exception.ApplicationException e) {
         * e.printStackTrace(); throw ExceptionUtil.getBaseException(e); } catch
         * (Exception ex) { ex.printStackTrace(); throw
         * ExceptionUtil.getBaseException(ex); }
         * retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
         * //System.out.println("============保存签名数据结束=============="); }
         */
        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj, qyjb.getSbxx().getNd(), qyjb.getSbxx().getJd(), "0", "0");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

        try
        {
            // 取得企业所得税亏损值对象
            Qysdsjd vo = (Qysdsjd) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
            SWDJJBSJ djsj = (SWDJJBSJ) data.get(QysdsksMapConstant.STRING_KEY_JBSJ);

            // 取得计算机代码
            String jsjdm = vo.getJsjdm();
            // 取得年度
            String nd = vo.getNd();
            // 取得季度
            String jd = vo.getJd();

            // 获得数据库连接
            conn = DBResource.getConnection();
            // 获得 ORManager
            orManager = DBResource.getORManager();

            // 先删除,后保存
            // 构造删除条件
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from SBDB.SB_JL_QYSDSJD ");
            sqlBuffer.append("where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and jd = ?");
            sqlBuffer.append("and qxdm = ?");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, jd);
            pstmt.setString(4, djsj.getSwjgzzjgdm().substring(0, 2));
            // 删除
            int num = pstmt.executeUpdate();

            // 保存
            orManager.makePersistent(0, conn, vo);

            try
            {
                // 保存减免数据
                Jm jm = null;
                if ((jm = (Jm) data.get("QYSDSJB_JM")) != null)
                {
                    // 先查询减免数据,满足条件后保存
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Vector v = new Vector();
                    v.add("jsjdm = '" + jsjdm + "'");
                    v.add("nd = '" + nd + "'");
                    v.add("qxdm = '" + djsj.getSwjgzzjgdm().substring(0, 2) + "'"); // 区县代码
                    v.add("szsmdm = '" + jm.getSzsmdm() + "'");
                    v.add("jmlx = '" + jm.getJmlx() + "'");
                    v.add("fsdm='" + jm.getFsdm() + "'");
                    v.add("SKSSKSRQ = to_date('" + sdf.format(jm.getSkssksrq()) + "','yyyy-MM-dd')");
                    v.add("SKSSJSRQ = to_date('" + sdf.format(jm.getSkssjsrq()) + "','yyyy-MM-dd')");
                    System.out.println("1.1 QysdsksProcessor.doSave:JMSQL=" + v.toString());

                    ORContext jmContext = new ORContext(Jm.class.getName(), v);

                    // 查询企业所得税亏损数据
                    List jmList = orManager.query(0, conn, jmContext);

                    // 构造返回数据
                    Jm theJm = null;
                    if (jmList != null && jmList.size() > 0)
                    {
                        theJm = (Jm) jmList.get(0);
                        System.out.println("QysdsksProcessor.doSave:JM.jzbz=" + jm.getJzbz());
                    }

                    if (theJm == null)
                    {
                        String ysjcdm = null;
                        String yskmdm = null;
                        ysjcdm = FriendHelper.getYsjc(jm.getJsjdm(), jm.getSzsmdm(), jm.getSkssjsrq()).getYsjcdm();
                        yskmdm = JKAdapter.getInstance().getYskm(jm.getSzsmdm(), jm.getDjzclxdm(), jm.getGjbzhydm(),
                                ysjcdm).getYskmdm();

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
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "企业所得税季度申报表保存失败");
        }
        finally
        {
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
        return retData;
    }

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
                // 查询
                case QysdsksActionConstant.INT_ACTION_QUERY:
                {
                    vo.setData(doQuery((Map) vo.getData()));
                    break;
                }

                // 保存
                case QysdsksActionConstant.INT_ACTION_SAVE:
                {
                    return doSave(vo);
                }
                // 删除
                case QysdsksActionConstant.INT_ACTION_DELETE:
                {
                    doDelete(vo);
                    return null;
                }

                default:
                    throw new SystemException("no such method");
            }
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return vo;
    }

    /**
     * 删除亏损申报数据
     * 
     * @param data
     *            Map
     * @throws BaseException
     */
    private void doDelete(VOPackage vop) throws BaseException
    {
        Connection conn = null;
        try
        {
            Map data = (Map) vop.getData();
            Qysdsjd vo = (Qysdsjd) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
            SWDJJBSJ djsj = (SWDJJBSJ) data.get(QysdsksMapConstant.STRING_KEY_JBSJ);
            String jsjdm = vo.getJsjdm();
            String nd = vo.getNd();
            String jd = vo.getJd();
            UserData ud = vop.getUserData();
            QysdsjbVO qyjb = (QysdsjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
            DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            try
            {
                dzyj = CAUtils.saveDzyjsj(ud, dzyj, qyjb.getSbxx().getNd(), qyjb.getSbxx().getJd(), "0", "0");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }

            /*
             * if (ud.getCaflag()) { DzyjsjVO dzyj = (DzyjsjVO)
             * data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
             * dzyj.setYwczlx(qyjb.getYwczlx()); dzyj.setYwdm(qyjb.getYwlx());
             * try { dzyj = (new DzyjHelper()).saveDzyjsj(dzyj,
             * qyjb.getSbxx().getNd(), qyjb.getSbxx().getJd(), "0", "0"); }
             * catch (com.syax.frame.exception.ApplicationException e) {
             * e.printStackTrace(); throw ExceptionUtil.getBaseException(e); }
             * catch (Exception ex) { ex.printStackTrace(); throw
             * ExceptionUtil.getBaseException(ex); }
             * //retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
             * //System.out.println("============保存签名数据结束=============="); }
             * else { DzyjHelper dh = new DzyjHelper(); List ret =
             * dh.queryDzyjsj(jsjdm,CAcodeConstants.YWDM_SB_WS_QYSDS_JB,qyjb.getSbxx().getNd(),
             * qyjb.getSbxx().getJd(), "0", "0"); if (ret.size() > 0) { throw
             * new ApplicationException("要删除企业所得税季度申报表有电子原件数据，请到税务所办理删除。"); } }
             */
            // 获得数据库连接
            conn = DBResource.getConnection();

            // 构造删除条件
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from SBDB.SB_JL_QYSDSJD ");
            sqlBuffer.append("where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and jd = ?");
            sqlBuffer.append("and qxdm = ?");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, jd);
            pstmt.setString(4, djsj.getSwjgzzjgdm().substring(0, 2));
            // 删除
            int num = pstmt.executeUpdate();

            // 删除相关减免数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from SBDB.SB_JL_JM ");
            sqlBuffer.append("where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            sqlBuffer.append("and szsmdm = '300000' ");
            sqlBuffer.append("and jmlx = '1' and fsdm='5' ");
            sqlBuffer.append("and SKSSKSRQ = to_date('" + sdf.format(vo.getSkssksrq()) + "','yyyy-MM-dd') ");
            sqlBuffer.append("and SKSSJSRQ = to_date('" + sdf.format(vo.getSkssjsrq()) + "','yyyy-MM-dd') ");
            sqlBuffer.append("and jzbz like '0%'");
            sql = sqlBuffer.toString();
            System.out.println("QysdsksProcessor.doDelete:JMSQL=" + sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jsjdm);
            pstmt.setString(2, nd);
            pstmt.setString(3, djsj.getSwjgzzjgdm().substring(0, 2));
            // 删除
            pstmt.execute();

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除企业所得税季度申报表失败");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
}
