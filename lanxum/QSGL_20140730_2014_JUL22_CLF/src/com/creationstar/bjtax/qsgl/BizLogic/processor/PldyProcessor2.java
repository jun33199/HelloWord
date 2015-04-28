package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.FgrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.GrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SpjgxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 *
 * 说明：该Processor是针对页面中"批量受理(税务人员)"模块
 */
public class PldyProcessor2 extends CommonProcessor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;
        PldyBo2 bo = (PldyBo2) vo.getData();
        // 批次号
        String pch = bo.getPch();
        // 提供者名称
        String sjtgzmc = bo.getTgzgjmc();
        // 提供者计算机代码
        String sjtgzjsjdm = bo.getTgzjsjdm();
        // 导入时间起
        String drsjBegin = bo.getDrsjBegin();
        // 导入时间止
        String drsjEnd = bo.getDrsjEnd();
        Connection conn = null;

        String where =
                " where rownum<202 and  a.sjly='01' and c.sjly='04' and a.drpch=c.drpch";
        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
        }
        try {
            conn = QSDBUtil.getConnection();
            PreparedStatement ps = null;
            switch (vo.getAction()) {
            // 取得批次信息
            case ActionType.QUERY:

                // 数据提供者名称
                if (sjtgzmc != null && !sjtgzmc.trim().equals("")) {
                    where += (" and a.tgzmc='" + sjtgzmc + "'");
                }

                // 提供者计算机代码
                if (sjtgzjsjdm != null && !sjtgzjsjdm.trim().equals("")) {
                    where += (" and a.tgzjsjdm='" + sjtgzjsjdm + "'");
                }

                // 批量号
                if (pch != null && !pch.trim().equals("")) {
                    where += (" and a.drpch='" + pch + "'");
                }

                // 导入时间段
                if (drsjBegin != null && !drsjBegin.trim().equals("")) {
                    where += (" and a.drsj>=TO_DATE('" + drsjBegin +
                              "','yyyy-mm-dd hh24:mi:ss')");
                }
                if (drsjEnd != null && !drsjEnd.trim().equals("")) {
                    where += (" and a.drsj<=TO_DATE('" + drsjEnd +
                              " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
                }
                try {
                    String sql =
                            "select distinct a.* from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ";
                    //增加数据权限控制
                    UserData ud = vo.getUserData();
                    String datafilter = ZKAdapter.getInstance().
                                        getDataFilterString(
                                                ud, "QSDB", "QS_JL_DRPCINFO");
                    Debug.out("datafilter: " + datafilter);
                    where += (" and " + datafilter);

                    ps = conn.prepareStatement(sql + where);
                    ResultSet rs = ps.executeQuery();
                    ArrayList resultList = new ArrayList();
                    while (rs.next()) {
                        Drpcinfo tmpDrpcinfo = new Drpcinfo();
                        tmpDrpcinfo.setDrpch(rs.getString("drpch"));
                        tmpDrpcinfo.setTgzmc(rs.getString("tgzmc"));
                        tmpDrpcinfo.setTgzgjmc(rs.getString("tgzgjmc"));
                        tmpDrpcinfo.setTgzjsjdm(rs.getString("tgzjsjdm"));
                        tmpDrpcinfo.setTjsj(rs.getTimestamp("tjsj"));
                        tmpDrpcinfo.setDrsj(rs.getTimestamp("drsj"));
                        tmpDrpcinfo.setJsfmc(rs.getString("jsfmc"));
                        resultList.add(tmpDrpcinfo);
                    }
                    result = resultList;
                } catch (SQLException e) {
                    throw e;
                }
                finally {
                    ps.close();
                }
                return result;
                // 取得申报主表信息
            case ActionType.GET:
                result = getPldrList(pch, conn);
                return result;
                // 打印后更改状态标识
            case ActionType.UPDATE:
                String sql =
                        "UPDATE qsdb.qs_jl_sbzb SET ztbs = ? WHERE drpch = ? ";
                ps = null;
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, Constants.ZB_ZTBS_DY);
                    ps.setString(2, pch);
                    ps.execute();
                } catch (SQLException e) {
                    throw e;
                }
                finally {
                    ps.close();
                }
                return null;
            default:
                throw new ApplicationException(
                        "ActionType有错误，processor中找不到相应的方法.");
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PldyProcessor2，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private ArrayList getPldrList(String pch, Connection conn) throws
            SQLException, ApplicationException {
        ArrayList sbzbList = SbzbDAO.query(" where drpch='" + pch +
                                           "'", conn);
        ArrayList resultList = new ArrayList();
        for (int i = 0; i < sbzbList.size(); i++) {
            Sbzb tmpSbzb = (Sbzb) sbzbList.get(i);
            // 取得申报表号
            String sbbh = tmpSbzb.getSbbh();
            // 生成需要返回的PldrBo
            PldrBo2 tmpPldrBo = new PldrBo2();
            // 设置sbzb
            tmpPldrBo.setSbzb(tmpSbzb);
            // 设置批次号
            tmpPldrBo.setPch(pch);

            // 判断个人或非个人
            if (tmpSbzb.getYhbs().equals(Constants.YHBZ_GR)) {
                tmpPldrBo.setGrxx((Grxx) GrxxDAO.getOneBySbbh(sbbh, conn));
            } else {
                tmpPldrBo.setFgrxx((Fgrxx) FgrxxDAO.getBySbbh(sbbh, conn));
            }
            //审批结果信息
            Spjgxx spjgxx = (Spjgxx) SpjgxxDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setSpjgxx(spjgxx);
            //土地房屋信息
            Tufwxx tufwxx = (Tufwxx) TufwxxDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setTufwxx(tufwxx);
            //拆迁信息(Arryalist中的每个元素都是jsblcq对象)
            ArrayList cqxxList = JsblcqDAO.queryBySbbh(sbbh, conn);
            tmpPldrBo.setCqxxList(cqxxList);
            //公有住房信息(Arryalist中的每个元素都是jsblgyzf对象)
            ArrayList gyzfxxList = JsblgyzfDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setGyzfxxList(gyzfxxList);
            //房屋交换信息(Arryalist中的每个元素都是PldrBo对象,但是没有拆迁和公房)
            /** @todo 没有房屋交换信息 */
            tmpPldrBo.setFwjhxxList(new ArrayList());
//          ArrayList fwjhxxList;

            tmpPldrBo.setHdbo(CommonUtil.getJZSE2(tmpPldrBo, conn));

            resultList.add(tmpPldrBo);
        }
        return resultList;
    }
}
