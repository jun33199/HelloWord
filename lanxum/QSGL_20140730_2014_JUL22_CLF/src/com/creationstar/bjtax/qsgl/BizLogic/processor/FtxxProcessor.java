package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryFtxxBo;
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
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 *
 * <p>Description: 房屋土地基础信息的Processor类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class FtxxProcessor extends CommonProcessor {
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
            doAdd(vo);
            break;
        case ActionType.MODIFY:
            doUpdate(vo);
            break;
        case ActionType.QUERY_USAGE:
            return doQueryUsage(vo);
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
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            //vo.getUserData();
            Tufwxx tufwxx = (Tufwxx) vo.getData();
            UserData ud = vo.getUserData();
            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
            //按照业务判断确定成交价格
            double cjjgrmb = CommonUtil.getCjjg(tufwxx);
            //更新申报主表的计税金额
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(tufwxx.sbbh);

            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            sbzb.setJsje(new BigDecimal(cjjgrmb));
            System.out.println("FtxxProcessor 房屋成交价格cjjgrmb=" + cjjgrmb);
            sbzb.setSetz(tufwxx.getSetz());
            System.out.println("FtxxProcessor 税额调整Setz=" + tufwxx.getSetz());
            //----add by zhangyj at 20081028-------------
            if (sbzb.getSetz().equals("5")) {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.
                        ZCWH_SCGMPTZFSL, conn)));
            }
            //---------------------------------------
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);

            tufwxx.nd = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));

            //insert tufwxx
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            //insert sbzbftgl
            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
            sbtdfwgl.cjr = ud.yhmc;
            sbtdfwgl.cjrq = tufwxx.cjrq;
            sbtdfwgl.lrr = ud.yhmc;
            sbtdfwgl.lrrq = tufwxx.cjrq;
            sbtdfwgl.sbbh = tufwxx.sbbh;
            sbtdfwgl.tdfwid = tufwxx.tdfwid;
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FtxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.update a record of Jsblcq
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Debug.out("into ftxxprocessor update....");
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the tufwxx and insert
            Tufwxx tufwxx = (Tufwxx) vo.getData();

            Debug.out("before get connection...");
            conn = QSDBUtil.getConnection();
            Debug.out("after get connection...");

            TufwxxDAO dao = DAOFactory.getInstance().getTufwxxDAO();
            Debug.out("after get dao...");

            Tufwxx existData = (Tufwxx) dao.get(tufwxx, conn);
            Debug.out("after get tufwxx...");

            //设置更新的字段
            existData.sbbh = tufwxx.sbbh;
            existData.tdfwid = tufwxx.tdfwid;
            existData.fdcxmmc = tufwxx.fdcxmmc;
            existData.htqdsj = tufwxx.htqdsj;
            existData.fldm = tufwxx.fldm;
            existData.tdfwqszylx = tufwxx.tdfwqszylx;
            existData.fwlxdm = tufwxx.fwlxdm;
            existData.tdfwzldz = tufwxx.tdfwzldz;
            existData.tdfwqszymj = tufwxx.tdfwqszymj;
            existData.cjjgrmb = tufwxx.cjjgrmb;
            existData.pgjgrmb = tufwxx.pgjgrmb;
            existData.cjjgwb = tufwxx.cjjgwb;
            existData.bzdm = tufwxx.bzdm;
            existData.hldm = tufwxx.hldm;
            existData.zhjgrmb = tufwxx.zhjgrmb;
            existData.bz = tufwxx.bz;
            existData.bzmc = tufwxx.bzmc;
            existData.flmc = tufwxx.flmc;
            existData.fwjzmj = tufwxx.fwjzmj;
            existData.fwlxmc = tufwxx.fwlxmc;
            existData.tdfwqszymc = tufwxx.tdfwqszymc;
            existData.rjl = tufwxx.rjl;
            existData.tdjc = tufwxx.tdjc;
            existData.sfesf = tufwxx.sfesf;
            //更新
            Debug.out("before update ftxx...");
            dao.update(existData, conn);
            Debug.out("after update ftxx...");

            //按照业务判断确定成交价格
            Debug.out("before get cjjg.");
            double cjjgrmb = CommonUtil.getCjjg(existData);
            Debug.out("after get cjjg.");

            //更新申报主表的计税金额
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(existData.sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            sbzb.setJsje(new BigDecimal(cjjgrmb));
            sbzb.setSetz(tufwxx.getSetz());
            //----add by zhangyj at 20081029-------------
            if (sbzb.getSetz().equals("5")) {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.
                        ZCWH_SCGMPTZFSL, conn)));
            } else {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL,
                        conn)));
            }
            //---------------------------------------
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            Debug.out("after update sbzb.");

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FtxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private QueryFtxxBo doQueryUsage(VOPackage vo) throws BaseException {
        Connection conn = null;
        QueryFtxxBo bo = null;
        try {
            //construct the jsblcq and insert
            Tufwxx tufwxx = (Tufwxx) vo.getData();

            conn = QSDBUtil.getConnection();
            TufwxxDAO dao = DAOFactory.getInstance().getTufwxxDAO();
            System.out.println("--:" + tufwxx.tdfwid);

            Tufwxx existData = (Tufwxx) dao.get(tufwxx, conn);
            System.out.println("--2:" + existData);
            if (existData == null) {

                return null;
            } else {
                //构造QueryFtxxBo
                bo = new QueryFtxxBo();
                bo.setTdfwid(existData.getTdfwid());
                bo.setFdcxmmx(existData.getFdcxmmc());
                bo.setHtqdsj(existData.getHtqdsj());

                bo.setFlmc(existData.getFlmc());
                bo.setFldm(existData.getFldm());

                bo.setTdfwqszylxmc(existData.getTdfwqszymc());
                bo.setTdfwqszylx(existData.getTdfwqszylx());

                bo.setFwlxmc(existData.getFwlxmc());
                bo.setFwlxdm(existData.getFwlxdm());

                bo.setTdfwzldz(existData.getTdfwzldz());
                bo.setTdfwqszymj(existData.getTdfwqszymj());
                bo.setFwjzmj(existData.getFwjzmj());
                bo.setCjjgrmb(existData.getCjjgrmb());
                bo.setPgjgrmb(existData.getPgjgrmb());
                bo.setCjjgwb(existData.getCjjgwb());

                bo.setBzmc(existData.getBzmc());
                bo.setBzdm(existData.getBzdm());

                bo.setHl(existData.getHldm());
                bo.setZhjgrmb(existData.getZhjgrmb());
                bo.setCjr(existData.getCjr());
                bo.setCjrq(existData.getCjrq());

                bo.setRjl(existData.getRjl());
                bo.setTdjc(existData.getTdjc());

                //获取使用土地房屋的申报信息
                ArrayList list = (ArrayList) DAOFactory.getInstance().
                                 getSbtdfwglDAO().queryByTdfwid(tufwxx.tdfwid,
                        conn);
                if ((list != null) && (list.size() > 0)) {
                    bo.setListSbxx(list);
                }
            }

            return bo;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FtxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "获取土地房屋使用信息失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}
