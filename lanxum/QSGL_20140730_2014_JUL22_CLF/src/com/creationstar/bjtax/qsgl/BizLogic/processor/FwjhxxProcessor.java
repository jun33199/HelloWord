package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 *
 * <p>Description: 政策维护的Processor类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class FwjhxxProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is FtxxProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.INSERT:
            result = doAdd(vo);
            break;
        case ActionType.MODIFY:
            result = doModify(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            FwjhxxBo fwjhxxBo = (FwjhxxBo) vo.getData();
            UserData ud = vo.getUserData();
            //构造一个Sbzb的值对象
            Sbzb sbzb = new Sbzb();

            //将现有的信息付给Sbzb的值对象
            //获取申报表号
            String sbbh = CommonUtil.getSBBH(ud.getXtsbm1(), conn);
            Debug.out("get sbbh: " + sbbh);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sbzb.sbbh = sbbh;
            sbzb.blbs = Constants.ZB_BLBS_FBL;
            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BZ;
            sbzb.bz = fwjhxxBo.getBz();
            sbzb.cjr = ud.getYhmc();
            sbzb.cjrq = now;
            sbzb.fwtdbmdm = fwjhxxBo.getFcjslh();
            sbzb.jsfsdm = fwjhxxBo.getJkfsdm();
            sbzb.jsfsmc = fwjhxxBo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.sbrq = now;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            if (fwjhxxBo.getJhperson().equals("0")) {
                sbzb.yhbs = Constants.YHBZ_GR; //个人
                sbzb.nsrlxdm = Constants.NSRLX_GR;
            }
            if (fwjhxxBo.getJhperson().equals("1")) {
                sbzb.yhbs = Constants.YHBZ_FGR; //非个人
                sbzb.nsrlxdm = fwjhxxBo.getFgrxx().getNsrlxdm();
            }

            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();
            Debug.out("对方申报表号: " + fwjhxxBo.getSbbh());
            sbzb.setDfsbbh(fwjhxxBo.getSbbh());
            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);

            if (fwjhxxBo.getJhperson().equals("0")) {
                Debug.out("个人");
                //个人信息部分
                List nsrList = fwjhxxBo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("fwjhProcessor bo nsrmc55: " + grxx.nsrmc);
                    grxx.sbbh = sbbh;
                    l.add(grxx);
                }

                //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                l = CommonUtil.handleZRR(l, ud);
                fwjhxxBo.setNsrList(l);
                //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("非个人");
                //非个人信息部分
                Fgrxx fgrxx = fwjhxxBo.getFgrxx();
                fgrxx.sbbh = sbbh;
                fgrxx.fwjhbs = Constants.FWJHBS;
                fgrxx.setLrr(ud.yhmc);
                fgrxx.setCjr(ud.yhmc);
                fgrxx.setLrrq(new Timestamp(System.currentTimeMillis()));
                fgrxx.setCjrq(new Timestamp(System.currentTimeMillis()));

                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
                Debug.out("非个人 ok");
            }

            //插入土地房屋信息
            Tufwxx tufwxx = fwjhxxBo.getTufwxx();
            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
            Debug.out("get tdfwid: " + tufwxx.tdfwid);
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));

            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);

            //插入“申报主表与土地房屋关联表”的信息
            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
            sbtdfwgl.sbbh = sbbh;
            sbtdfwgl.tdfwid = tufwxx.tdfwid;
            sbtdfwgl.setLrr(ud.yhmc);
            sbtdfwgl.setCjr(ud.yhmc);
            sbtdfwgl.setLrrq(new Timestamp(System.currentTimeMillis()));
            sbtdfwgl.setCjrq(new Timestamp(System.currentTimeMillis()));

            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
            //更新原申报表中的对方申报表号
            DAOFactory.getInstance().getSbzbDAO().updateDfsbbh(
                    fwjhxxBo.getSbbh(), sbzb.getSbbh(), conn);
            if (fwjhxxBo.getJhperson().equals("0")) {
                //更新原申报表个人的房屋交换标识
                DAOFactory.getInstance().getGrxxDAO().updateFwjhbs(Constants.
                        FWJHBS,
                        fwjhxxBo.getSbbh(), conn);
            } else {
                //更新原申报表个人的房屋交换标识
                DAOFactory.getInstance().getFgrxxDAO().updateFwjhbs(Constants.
                        FWJHBS,
                        fwjhxxBo.getSbbh(), conn);
            }

            return fwjhxxBo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FwjhxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doModify(VOPackage vo) throws BaseException {
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            FwjhxxBo fwjhxxBo = (FwjhxxBo) vo.getData();
            UserData ud = vo.getUserData();
            //构造一个Sbzb的值对象
            Sbzb sbzb = new Sbzb();
            sbzb.sbbh = fwjhxxBo.sbbh;
            Debug.out("FwjhProcessor update sbzb sbbh: " + sbzb.sbbh);
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            sbzb = (Sbzb) sbzbDao.get(sbzb, conn);
            //更新申报主表
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sbzb.fwtdbmdm = fwjhxxBo.getFcjslh();
            sbzb.jsfsdm = fwjhxxBo.getJkfsdm();
            sbzb.jsfsmc = fwjhxxBo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.sbrq = now;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            if (fwjhxxBo.getJhperson().equals("0")) {
                sbzb.yhbs = Constants.YHBZ_GR; //个人
                sbzb.nsrlxdm = Constants.NSRLX_GR;
            }
            if (fwjhxxBo.getJhperson().equals("1")) {
                sbzb.yhbs = Constants.YHBZ_FGR; //非个人
                sbzb.nsrlxdm = fwjhxxBo.getFgrxx().getNsrlxdm();
            }

            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();

            sbzbDao.update(sbzb, conn);

            if (fwjhxxBo.getJhperson().equals("0")) {
                Debug.out("个人");
                //得到个人信息,如果有非个人信息变为个人信息,就会不能删除原始信息??????
                DAOFactory.getInstance().getGrxxDAO().delete(fwjhxxBo.getSbbh(),
                        conn);

                //个人信息部分
                List nsrList = fwjhxxBo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FFWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("fwjhProcessor bo nsrmc55: " + grxx.nsrmc);
                    grxx.sbbh = fwjhxxBo.getSbbh();
                    l.add(grxx);
                }

                //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                l = CommonUtil.handleZRR(l, ud);
                fwjhxxBo.setNsrList(l);
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("非个人");
                //非个人信息部分
                Fgrxx fgrxx = fwjhxxBo.getFgrxx();

                //删除当前申报表号对应的非个人信息
                ArrayList delList = new ArrayList();
                delList.add(fgrxx);
                DAOFactory.getInstance().getFgrxxDAO().delete(delList, conn);

                fgrxx.sbbh = fwjhxxBo.sbbh;
                fgrxx.fwjhbs = Constants.FWJHBS;
                fgrxx.setLrr(ud.yhmc);
                fgrxx.setCjr(ud.yhmc);
                fgrxx.setLrrq(new Timestamp(System.currentTimeMillis()));
                fgrxx.setCjrq(new Timestamp(System.currentTimeMillis()));

                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
                Debug.out("非个人 ok");
            }

            //更新土地房屋信息
            Tufwxx tufwxx = fwjhxxBo.getTufwxx();
            Debug.out("get tdfwid: " + tufwxx.tdfwid);
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));

            DAOFactory.getInstance().getTufwxxDAO().update(tufwxx, conn);
            return fwjhxxBo;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FwjhxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}
