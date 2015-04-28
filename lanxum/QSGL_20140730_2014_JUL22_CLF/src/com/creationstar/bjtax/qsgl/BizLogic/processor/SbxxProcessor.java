package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SbState;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SbxxProcessor implements Processor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo
     *            the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException
     *             抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SbxxProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQuery(vo);
            break;
        case ActionType.GET:
            result = doGet(vo);
            break;
        case ActionType.MODIFY:
            result = doModify(vo);
            break;
        case ActionType.ROLLBACK:
            doRollBack(vo);
            break;
        case ActionType.CONFIRM:
            doConfirm(vo);
            break;
        case ActionType.REJECT:
            doReject(vo);
            break;
        case ActionType.CANCEL:
            doCancel(vo);
            break;
        case ActionType.DELETE:
            doDelete(vo);
            break;
        case ActionType.PRINT_SBB:
            result = doPrintSbb(vo);
            break;
        case ActionType.PRINT_HDTZS:
            result = doPrintHdtzs(vo);
            break;
        case ActionType.Query_HDTZS:
            result = doQueryHdtzs(vo);
            break;
        case ActionType.UPDATE_HDTZS:
            doUpdateHdtzs(vo);
            break;
        case ActionType.Query_HDTZSBYFWHM:
            result = doQueryHdtzsbyFwhm(vo);
            break;
        case ActionType.QUERY_SBZT:
            result = doQuerySbzt(vo);
            break;

        default:
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 一般查询方法
     *
     * @param vo
     *            VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        JsblBo bo = new JsblBo();
        try {
            // vo.getUserData();
            bo = (JsblBo) vo.getData();
            // String conditions = CommonUtil.getSqlQueryCondition(bo);
            String conditions = "";
            if ((bo.getZtbs() != null) && (!bo.getZtbs().equals(""))) {
                conditions += " and a.ztbs = '" + bo.getZtbs() + "'";
            }
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and a.drpch = '" + bo.getDrpch() + "'";
            }
            if ((bo.getJmsbs() != null) && (!bo.getJmsbs().equals(""))) {
                conditions += " and a.bljmsbs = '" + bo.getJmsbs() + "'";
            }
            if ((bo.getSbbh() != null) && (!bo.getSbbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.sbbh = '" + bo.getSbbh() + "'";
            }
            //增加建委业务编号查询条件,modify by fujx,
            if ((bo.getJwywbh() != null) && (!bo.getJwywbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.jwywbh = '" + bo.getJwywbh() + "'";
            }
            //增加合同编号查询条件,modify by fujx
            if ((bo.getHtbh() != null) && (!bo.getHtbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.htbh = '" + bo.getHtbh() + "'";
            }

            if ((bo.getSbrq() != null) && (!bo.getSbrq().equals(""))) {
                conditions += " and to_char(a.sbrq,'yyyyMMdd') = '"
                        + bo.getSbrq() + "'";
            }
            if ((bo.getJsjdm() != null) && (!bo.getJsjdm().equals(""))) {
                conditions += " and d.jsjdm = '" + bo.getJsjdm() + "'";
            }
            if ((bo.getNsrmc() != null) && (!bo.getNsrmc().equals(""))) {
                conditions += " and d.nsrmc like '%" + bo.getNsrmc() + "%'";
            }

            if ((bo.getNsrlxdm() != null) && (!bo.getNsrlxdm().equals(""))) {
                if (bo.getNsrlxdm().equals(Constants.NSRLX_GR)) {
                    if ((bo.getSfzjlx() != null)
                        && (!bo.getSfzjlx().equals(""))) {
                        conditions += " and d.sfzjlx = '" + bo.getSfzjlx()
                                + "'";
                    }
                    if ((bo.getSfzjhm() != null)
                        && (!bo.getSfzjhm().equals(""))) {
                        conditions += " and d.sfzjhm = '" + bo.getSfzjhm()
                                + "'";
                    }
                } else {
                    conditions += " and d.nsrlxdm = '" + bo.getNsrlxdm() + "'";
                }
            }

            // 增加业务过滤条件
            String ywfilter = getYwFilter(bo);
            if ((ywfilter != null) && (!ywfilter.equals(""))) {
                conditions += " and " + ywfilter;
            }

            // 增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
                    "QSDB", "QS_JL_SBZB");
            Debug.out("datafilter: " + datafilter);
            conditions += " and " + datafilter;

            conn = QSDBUtil.getConnection();
            if ((bo.getNsrlxdm() != null) && (!bo.getNsrlxdm().equals(""))) {

                resultList = DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, bo.ifPersonal());
            } else { // 没有选择纳税人类型
                // 先查个人
                resultList = DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, true);
                // 在加上非个人
                resultList.addAll(DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, false));
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }

    /**
     * 实现以SbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        SbxxBo Bo = new SbxxBo();
        SbxxBo queryBo = (SbxxBo) vo.getData();
        String sbbh = queryBo.getSbbh();
        // 获得不包含房屋交换信息的申报bo
        Bo = (SbxxBo) doGetSbxxBo(sbbh);
        if (!Bo.isBZ()) { // 不征数据不取交换信息
            // 如果为房屋交换信息,则取出对方的房屋信息
            String dfSbbh = Bo.getVoSbzb().getDfsbbh();
            if (dfSbbh != null && !dfSbbh.equals("")) {
                Bo.setDfSbxxBo((FwjhxxBo) doGetFwjhxxBo(dfSbbh));
            }
        }
        return Bo;
    }

    /**
     * 实现以SbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGetHdtzs(VOPackage vo) throws BaseException {
        /**
         * 通过取出来得SbxxBo 构造查询条件
         *
         * 1、使用SbxxBo构造申报主表Vo，DAO反回结果申报主表Vo
         *
         * 2、构造个人、非个人信息Vo，DAO.get反回结果个人、非个人信息Vo的ArrayList
         *
         * 3、构造房屋土地基本信息conditions,DAO.query反回结果房屋土地基本信息Vo的ArrayList
         *
         * 4、构造拆迁信息conditions,DAO.query反回结果拆迁信息Vo的ArrayList
         *
         * 5、构造公有住房信息conditions,DAO.query反回结果公有住房信息Vo的ArrayList
         */

        HdtzsBo hdtzsBo = new HdtzsBo();
        SbxxBo sbxxBo = (SbxxBo) vo.getData();

        HashMap nrMap = new HashMap();

        Connection conn = null;
        try {
            Debug.out("getHdtzs sbxx sbbh: " + sbxxBo.getSbbh());
            conn = QSDBUtil.getConnection();

            // 获取核定通知书主表数据
            Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                          .getBySbbh(sbxxBo.getSbbh(), conn);
            hdtzsBo.setVoHdtzs(hdtzs);

            // 获取核定减免明细
            if (!sbxxBo.isBZ()) {
                String condition = " where HDTZSH = '" + hdtzs.getHdtzsh()
                                   + "'";
                ArrayList jmList = (ArrayList) DAOFactory.getInstance()
                                   .getHdjmmxDAO().query(condition, conn);
                for (int i = 0; i < jmList.size(); i++) {
                    Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                    nrMap.put(hdjmmx.getZcbh(), hdjmmx);
                }
                hdtzsBo.setJmnrMap(nrMap);
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return hdtzsBo;
    }

    /**
     * 修改
     *
     * @param vo
     *            VOPackage
     */
    private SbxxBo doModify(VOPackage vo) throws BaseException {
        int oklevel = 0;
        SbxxBo bo = new SbxxBo();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doModify... ");
        Connection conn = null;

        try {
            bo = (SbxxBo) vo.getData();
            conn = QSDBUtil.getConnection();

            // 获取申报主表信息
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            Sbzb sbzbVo = bo.getVoSbzb();

            sbzbDao.update(sbzbVo, conn);
            Timestamp now = new Timestamp(System.currentTimeMillis());

            oklevel = 1;
            // 获取更新个人/非个人信息
            if (bo.isPerson()) {

                // 删除当前申报表号对应的个人信息
                DAOFactory.getInstance().getGrxxDAO().delete(sbzbVo.getSbbh(),
                        conn);

                // 插入修改后的个人信息
                // 获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                List nsrList = bo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FFWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("SbxxProcessor bo nsrmc22: " + grxx.nsrmc);
                    grxx.sbbh = sbzbVo.getSbbh();
                    l.add(grxx);
                }

                // 获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                l = CommonUtil.handleZRR(l, ud);
                bo.setNsrList(l);

                // insert grxx
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
                oklevel = 2;
            } else {
                // 得到非个人信息
                Fgrxx fgrexxVo = bo.getVoFgrxx();
                DAOFactory.getInstance().getFgrxxDAO().update(fgrexxVo, conn);
                oklevel = 3;
            }
            // 更新审批结果信息
            Spjgxx spjgxx = bo.getVoSpjgxx();
            // 删除原先的记录
            String sql = "delete from qsdb.qs_jl_spjgxx where sbbh = '"
                         + sbzbVo.sbbh + "'";
            BaseDAO.execute(sql, conn);
            // 增加新的记录
            if ((spjgxx != null) && (spjgxx.getHdtzszh() != null)
                && (!spjgxx.getHdtzszh().equals(""))) {
                spjgxx.cjr = ud.getYhmc();
                spjgxx.cjrq = now;
                if ((spjgxx.getJmlydm() == null)
                    || (spjgxx.getJmlydm().equals(""))) {
                    spjgxx.jmlydm = "00";
                }

                spjgxx.lrr = ud.getYhmc();
                spjgxx.lrrq = now;
                spjgxx.sbbh = sbzbVo.sbbh;

                Debug.out("jmje: " + spjgxx.getJmsje());
                Debug.out("hdtzszh: " + spjgxx.getHdtzszh());

                DAOFactory.getInstance().getSpjgxxDAO().insert(spjgxx, conn);
            }
        } catch (Exception ex) {
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("更新申报主表出错!");
                case 1:
                    throw new ApplicationException(
                            "取个人计算机代码出错，或者录入的纳税人名称与自然人登记的纳税人名称不匹配,更新个人信息或者非个人信息出错!");
                default:
                    if (ex.getMessage().indexOf("ORA-00001") != -1) {
                        throw new ApplicationException("审批结果表编号已经存在!");
                    } else {
                        throw new ApplicationException("更新减免审批结果信息出错!");
                    }
                }
            } catch (ApplicationException e) {
                // 处理失败信息:控制台 ＋ 日志
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP)
                                 .getStackMsg(), "失败");

                throw ExceptionUtil.getBaseException(e);
            }
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }

    /**
     * 作废
     *
     * @param vo
     *            VOPackage
     */
    private void doCancel(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doCancel... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            // 更新状态
            sbzbDao.update(Constants.ZB_ZTBS_ZF, bo.getSbbh(), conn);

            // 如果此申请有拆迁信息,将拆迁使用补偿额恢复

            if (bo.getCqList() != null) {
                DAOFactory.getInstance().getJsblcqDAO().updateBcsye(
                        bo.getSbbh(), bo.getCqList(), conn);
            }

            // 如果有公有住房信息,将抵扣额恢复
            if (bo.getGyzfList() != null) {
                DAOFactory.getInstance().getJsblgyzfDAO().updateDksye(
                        bo.getSbbh(), bo.getGyzfList(), conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "作废申报失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 撤销上次操作，恢复状态
     *
     * @param vo
     *            VOPackage
     */
    private void doRollBack(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doRollBack... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            Debug.out("current state: " + SbState.getStateName(bo.getState()));
            Debug.out("rollback state: "
                      + SbState.getStateName(SbState.getCancelStateCode(bo
                    .getState(), bo.getVoSbzb().getBljmsbs())));
            // 更新状态
            sbzbDao.update(SbState.getCancelStateCode(bo.getState(), bo
                    .getVoSbzb().getBljmsbs()), bo.getSbbh(), conn);
            // 审核同意撤销：
            // 删除核定通知书项。
            String ztbs = bo.getVoSbzb().getZtbs();
            String jmsbs = bo.getVoSbzb().getBljmsbs();
            String sbbh = bo.getVoSbzb().getSbbh();
            Debug.out("ztbs: " + ztbs);
            Debug.out("jmsbs: " + jmsbs);
            if (ztbs.equals(Constants.ZB_ZTBS_JS_TY)) {
                DAOFactory.getInstance().getHdtzsDAO().deleteHdtzsBySbbh(sbbh,
                        conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 打印申报表
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintSbb(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doPrintSbb... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            bo = (SbxxBo) doGet(vo);
            Debug.out("sbxxprocessor doPrintSbb bo.sbzb: "
                      + bo.getVoSbzb().getSbbh());
            // 只有新增的情况下，才更新状态
            // HashMap map = (HashMap)
            // CommonUtil.getJZSE(bo.getVoSbzb().getSbbh(),
            // conn);
            JghdsjBo hdbo = CommonUtil.getJZSE(bo, conn);

            bo.setHdbo(hdbo);

            // 获取已购公有住房项
            BigDecimal sjyz = hdbo.getSjyz(); // 应征契税
            BigDecimal kcqyzfx = hdbo.getGyzfjmje();
            // 更新主表办理减免税标识,当其计征税额抵扣出售公房收入后实际应征税额为0时

            if (kcqyzfx != null && kcqyzfx.doubleValue() > 0) {
                if (sjyz != null
                    && sjyz.doubleValue() <= 0
                    && !Constants.ZB_BLJMSBS_FHBL_WLR.equals(bo.getVoSbzb()
                        .getBljmsbs())) {
                    sbzbDao.updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR, bo
                                        .getSbbh(), conn);
                }
            }

            if (bo.getState().equals(Constants.ZB_ZTBS_BC)) {
                // 更新状态
                sbzbDao.update(Constants.ZB_ZTBS_DY, bo.getSbbh(), conn);
            }

            // 增加对契税迁移数据的处理，申报编号从迁移表中取
            bo.setPrintSbbh(bo.getSbbh());
            if (bo.getVoSbzb().getBlbs().equals(Constants.ZB_BLBS_SJQYBL)) {
                bo.setPrintSbbh(sbzbDao.getDrsbbh(bo.getSbbh(), conn));
            }

            return bo;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 打印核定通知书 参数只有一个申报表号
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintHdtzs(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doPrintHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报数据
            bo = (SbxxBo) doGet(vo);
            // 只有审核同意的情况下，才更新状态
            if (bo.getState().equals(Constants.ZB_ZTBS_JS_TY)) {
                // 更新状态
                DAOFactory.getInstance().getSbzbDAO().update(
                        Constants.ZB_ZTBS_DY_HD, bo.getSbbh(), conn);
            }

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            if (bo.isBZ()) {
                hdtzsBo.setFwlxmc(bo.getVoTufwxx().getFlmc());
                hdtzsBo.setFwlxdm(bo.getVoTufwxx().getFldm());
            }

            hdtzsBo.setVoTufwxx(bo.getVoTufwxx());

            return hdtzsBo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 审核同意
     *
     * @param vo
     *            VOPackage
     */
    private void doConfirm(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();

        Connection conn = null;

        try {
            UserData ud = vo.getUserData();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            // update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(
                    Constants.ZB_ZTBS_JS_TY, bo.getSbbh(), conn);

            // 插入核定通知书
            Hdtzs hdtzs = new Hdtzs();
            if (bo.isBZ()) {
                hdtzs.bzbs = Constants.BZBS_BZ;
            } else {
                hdtzs.bzbs = Constants.BZBS_JM;
            }
            hdtzs.cjr = ud.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            // modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH(ud, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            // end modified

            hdtzs.jbr = ud.yhmc;
            /**
             * 计算审核及收款时候需要用到的计征税额 Constants 中的定义： public static final String
             * JE_CJJG = "JE_CJJG"; //成交价格 public static final String JE_JSYJ =
             * "JE_JSYJ"; //计税依据 public static final String JE_JZQS = "JE_JZQS";
             * //计征契税 public static final String JE_SJYZ = "JE_SJYZ"; //实际应征
             * public static final String JE_JZSE = "JE_JZSE"; //计征税额 public
             * static final String JE_QYZFBCDKE =
             * "JE_QYZFBCDKE";//出售已购公有住房的本次抵扣额 public static final String
             * JE_FWJHJG = "JE_FWJHJG"; //房屋交换价格 public static final String
             * JE_CQJMJE = "JE_CQJMJE"; //拆迁减免金额 public static final String
             * JE_PTZZJMJE = "JE_PTZZJMJE";//普通住宅减税金额 public static final String
             * JE_JMSZE = "JE_JMSZE";//减免税总金额 public static final String JE_YNSE =
             * "JE_YNSE"; //应纳税额
             *
             * @param String
             *            申报表号
             * @return HashMap
             */
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getVoSbzb().getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); // 应征契税
            // 获取已购公有住房项
            hdtzs.kcqyzfx = hdbo.getGyzfjmje();
            hdtzs.lrr = ud.yhmc;
            hdtzs.lrrq = now;
            // 获取联系电话
            if (bo.isPerson()) {
                // hdtzs.sqr = bo.getVoZcqrxx().getNsrmc();
                hdtzs.sqr = ActionUtil.getNsrmc(bo.getNsrList(), null);
            } else {
                hdtzs.sqr = bo.getVoFgrxx().getNsrmc();
            }
            //
            hdtzs.sbbh = bo.getVoSbzb().getSbbh();
            hdtzs.spfxmmc = bo.getVoTufwxx().getFdcxmmc();
            hdtzs.zldi = bo.getVoTufwxx().getTdfwzldz();

            // 插入一条核定通知书记录
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            // 如果有公有住房，插入减免明细表
            if ((bo.getGyzfList() != null) & bo.getGyzfList().size() > 0) {
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);

		//判断房屋类型是公有住房还是经济适用房	 modiby	by zhangyj 20090223		
		ArrayList list = DAOFactory.getInstance().getHdtzsDAO().getFwlx(bo.getSbbh(), conn);
		HashMap map = new HashMap();	
		String  lx= "";
		for(int i=0;i<list.size();i++)
		{
			map=(HashMap)list.get(i);
			lx= map.get("lx").toString();				
			if(lx.equals("1"))
			{
				hdjmmx.setZcbh(Constants.JMZC_GYZF);
				hdjmmx.setJmje(new BigDecimal(map.get("je").toString()));
			}else if(lx.equals("2"))
			{
				hdjmmx.setZcbh(Constants.JMZC_JJSYZF);
				hdjmmx.setJmje(new BigDecimal(map.get("je").toString()));
			}
			DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);					
		}
            }

            // 如果有拆迁住房，插入减免明细表
            if ((bo.getCqList() != null) & bo.getCqList().size() > 0) {
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(hdbo.getCqjmje());
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_CQFW);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

            BigDecimal ptzzjmje = hdbo.getPtzzjmje();
            if (ptzzjmje.doubleValue() > 0) { // 有普通住宅减免金额
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(ptzzjmje);
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_PTZZ);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 审核不同意
     *
     * @param vo
     *            VOPackage
     */
    private void doReject(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            DAOFactory.getInstance().getSbzbDAO().update(
                    Constants.ZB_ZTBS_JS_BTY, bo.getVoSbzb().getSbbh(), conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 删除
     *
     * @param vo
     *            VOPackage
     */
    private void doDelete(VOPackage vo) throws BaseException {

        ArrayList delList = (ArrayList) vo.getData();
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            int size = delList.size();
            for (int i = 0; i < size; i++) {
                String sbbh = (String) delList.get(i);
                String sql = "";
                Debug.out("delete sbbh: " + sbbh);

                // 得到主表的相关信息
                Sbzb sbzbVo = new Sbzb();
                sbzbVo.setSbbh(sbbh);
                sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(
                        sbzbVo, conn);

                deleteSbxx(sbbh, conn);
                if ((sbzbVo.getDfsbbh() != null)
                    && (!sbzbVo.getDfsbbh().equals(""))) {
                    deleteSbxx(sbzbVo.getDfsbbh(), conn);
                }
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 实现以SbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGetSbxxBo(String sbbh) throws BaseException {
        /**
         * 通过取出来得JsblBo 构造查询条件
         *
         * 1、使用SbxxBo构造申报主表Vo，DAO反回结果申报主表Vo
         *
         * 2、构造个人、非个人信息Vo，DAO.get反回结果个人、非个人信息Vo的ArrayList
         *
         * 3、构造房屋土地基本信息conditions,DAO.query反回结果房屋土地基本信息Vo的ArrayList
         *
         * 4、构造拆迁信息conditions,DAO.query反回结果拆迁信息Vo的ArrayList
         *
         * 5、构造公有住房信息conditions,DAO.query反回结果公有住房信息Vo的ArrayList
         */

        SbxxBo Bo = new SbxxBo();

        Tufwxx voTufwxx = new Tufwxx();
        ArrayList cqList = new ArrayList();
        ArrayList gyList = new ArrayList();
        List nsrList = new ArrayList();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // 得到主表的相关信息
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setVoSbzb(sbzbVo);

            // if (Constants.YHBS_GR.equals(bo.getNsrlxdm()))
            if (Bo.isPerson()) {
                // 得到个人信息
                List l = null;
                l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                        Bo.getSbbh(), conn);

                // Debug.out("sbxxProcessor get grxx nsrmc: " +
                // grxxVo.getNsrmc());
                // Bo.setVoGrxx(grxxVo);
                Bo.setNsrList(l);
            } else {
                // 得到非个人信息
                Fgrxx fgrexxVo = null;
                fgrexxVo = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO()
                           .getBySbbh(Bo.getSbbh(), conn);

                Bo.setVoFgrxx(fgrexxVo);
            }

            // 土地房屋信息
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                            .getBySbbh(sbbh, conn);

            if (tufwxx != null) {
                Bo.setVoTufwxx(tufwxx);
            } else {
                Bo.setVoTufwxx(null);
            }

            // 拆迁
            cqList = (ArrayList) DAOFactory.getInstance().getJsblcqDAO()
                     .queryBySbbh(sbbh, conn);
            Bo.setCqList(cqList);

            // 公有住房
            gyList = (ArrayList) DAOFactory.getInstance().getJsblgyzfDAO()
                     .getBySbbh(sbbh, conn);
            Bo.setGyzfList(gyList);

            // 获取审批结果信息
            Spjgxx spjgxx = (Spjgxx) DAOFactory.getInstance().getSpjgxxDAO()
                            .getBySbbh(sbbh, conn);
            if (spjgxx == null) {
                spjgxx = new Spjgxx();
            }
            Bo.setVoSpjgxx(spjgxx);

        } catch (Exception ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return Bo;

    }

    /**
     * 实现以SbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGetFwjhxxBo(String sbbh) throws BaseException {
        /**
         * 通过取出来得JsblBo 构造查询条件
         *
         * 1、使用SbxxBo构造申报主表Vo，DAO反回结果申报主表Vo
         *
         * 2、构造个人、非个人信息Vo，DAO.get反回结果个人、非个人信息Vo的ArrayList
         *
         * 3、构造房屋土地基本信息conditions,DAO.query反回结果房屋土地基本信息Vo的ArrayList
         *
         * 4、构造拆迁信息conditions,DAO.query反回结果拆迁信息Vo的ArrayList
         *
         * 5、构造公有住房信息conditions,DAO.query反回结果公有住房信息Vo的ArrayList
         */

        FwjhxxBo Bo = new FwjhxxBo();

        Tufwxx voTufwxx = new Tufwxx();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // 得到主表的相关信息
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setJkfsdm(sbzbVo.jsfsdm);
            Bo.setJkfsmc(sbzbVo.jsfsmc);
            Bo.setBz(sbzbVo.getBz());
            Bo.setFcjslh(sbzbVo.getFwtdbmdm());

            Bo.setJkfsdm(sbzbVo.getJsfsdm());
            Bo.setJkfsmc(sbzbVo.getJsfsmc());
            if (sbzbVo.getYhbs().equals(Constants.YHBZ_GR)) {
                // 得到个人信息
                List l = null;
                l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                        Bo.getSbbh(), conn);

                // Debug.out("sbxxProcessor get grxx nsrmc: " +
                // grxxVo.getNsrmc());
                Bo.setJhperson("0");
                Bo.setNsrList(l);

            } else {
                // 得到非个人信息
                Fgrxx fgrexxVo = null;
                fgrexxVo = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO()
                           .getBySbbh(Bo.getSbbh(), conn);
                Bo.setJhperson("1");
                Bo.setFgrxx(fgrexxVo);
            }

            // 土地房屋信息
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                            .getBySbbh(sbbh, conn);

            if (tufwxx != null) {
                Bo.setTufwxx(tufwxx);
            } else {
                Bo.setTufwxx(null);
            }
        } catch (Exception ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return Bo;

    }

    /**
     * 根据查询来自哪个页面,拼接相应的查询条件
     *
     * @param fromPage
     *            int 0：申报查询 1：不征查询 2：审核查询 3：复核查询 4: 减免申报查询
     * @return String
     */
    private String getYwFilter(JsblBo bo) {
        int fromPage = bo.getFromPage();
        StringBuffer sb = new StringBuffer();
        switch (fromPage) {
        case 0:

            // sb.append("(a.bljmsbs != '" + Constants.ZB_BLJMSBS_BZ + "')");
            sb.append("(a.bljmsbs not in ( '" + Constants.ZB_BLJMSBS_BZ + "','"
                      + Constants.ZB_BLJMSBS_CXXJM + "'))");
            break;
        case 1:
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_BZ + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='')");
            break;
        case 2:
            sb
                    .append("(a.ztbs = '1') and (a.bljmsbs ='1' or (a.bljmsbs='99' and (a.dfsbbh is null or a.dfsbbh ='')))");
            break;
        case 3:
            if ((bo.getZtbs() == null) || (bo.getZtbs().equals(""))) {
                if ((bo.getFztbs() == null) || (bo.getFztbs().equals(""))) {
                    // 查询全部已复核或需要复核的申报数据
                    sb
                            .append("((a.ztbs = '1' and a.bljmsbs = '0') or (a.ztbs = '4' and a.bljmsbs = '1') or a.ztbs = '7')"); //
                } else if (bo.getFztbs().equals("4")) { // 未复核
                    sb
                            .append(
                            "((a.ztbs = '1' and a.bljmsbs = '0') or (a.ztbs = '4' and a.bljmsbs = '1'))");
                } else if (bo.getFztbs().equals("7")) { // 已复核
                    // 此时ztbs不可能为空
                }
            }
            break;
        case 4:
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_CXXJM + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='')");
            break;
        default:
            break;
        }
        return sb.toString();

    }

    /**
     * 删除一条申报记录
     */
    private void deleteSbxx(String sbbh, Connection conn) throws Exception {
        String sql = "";
        // 非个人信息
        sql = "delete from qsdb.qs_jl_fgrxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete fgrxx ");
        // 个人信息
        sql = "delete from qsdb.qs_jl_grxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete grxx ");
        // 拆迁信息
        JsblcqDAO cqdao = DAOFactory.getInstance().getJsblcqDAO();
        ArrayList cqList = (ArrayList) cqdao.queryBySbbh(sbbh, conn);
        // 恢复拆迁补偿额
        DAOFactory.getInstance().getJsblcqDAO().updateBcsye(sbbh, cqList, conn);
        // 删除申报拆迁对照信息
        sql = "delete from qsdb.qs_jl_sbcqgl where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbcqgl ");
        // 检查每个拆迁信息，是否还有申报表关联，如果没有删除拆迁信息
        ArrayList delcqList = new ArrayList();
        for (int j = 0; j < cqList.size(); j++) {
            Jsblcq jsblcq = (Jsblcq) cqList.get(j);
            ArrayList sbcqList = DAOFactory.getInstance().getSbcqglDAO()
                                 .queryByCqxyh(jsblcq.cqxyh, conn);
            if ((sbcqList == null) || (sbcqList.size() == 0)) {
                delcqList.add(jsblcq);
            }
        }
        cqdao.delete(delcqList, conn);
        Debug.out("after delete jsblcqxx ");
        // 公有住房信息
        JsblgyzfDAO gyzfdao = DAOFactory.getInstance().getJsblgyzfDAO();
        ArrayList gyzfList = (ArrayList) gyzfdao.getBySbbh(sbbh, conn);
        // 恢复已购公有住房的抵扣额
        gyzfdao.updateDksye(sbbh, gyzfList, conn);
        // 申报公有对照信息
        sql = "delete from qsdb.qs_jl_sbgyzf where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbgyzf ");
        // 检查每个共有住房信息，是否还有申报表关联，如果没有删除共有住房信息
        ArrayList delgyList = new ArrayList();
        for (int k = 0; k < gyzfList.size(); k++) {
            Jsblgyzf yggyzf = (Jsblgyzf) gyzfList.get(k);
            ArrayList sbgyzfList = DAOFactory.getInstance().getSbgyzfDAO()
                                   .queryByHth(yggyzf.yggyzfqszsh, conn);
            if ((sbgyzfList == null) || (sbgyzfList.size() == 0)) {
                delgyList.add(yggyzf);
            }
        }
        gyzfdao.delete(delgyList, conn);

        Debug.out("after delete jsblgyzf ");
        // 房屋基本信息
        TufwxxDAO fwdao = DAOFactory.getInstance().getTufwxxDAO();
        Tufwxx fw = (Tufwxx) fwdao.getBySbbh(sbbh, conn);
        // 申报房屋信息对照
        sql = "delete from qsdb.qs_jl_sbtdfwgl where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbtdfwgl ");
        if (fw != null) {
            ArrayList delfwList = new ArrayList();
            delfwList.add(fw);
            fwdao.delete(delfwList, conn);
        }
        Debug.out("after delete tufwxx ");
        // 删除审批结果信息
        sql = "delete from qsdb.qs_jl_spjgxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        // Debug.out("after delete spjgxx ");

        // 申报主表信息
        sql = "delete from qsdb.qs_jl_sbzb where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbzb  ");
    }

    /**
     * 查询申报状态
     *
     * @param vo
     *            VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuerySbzt(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        QuerySbzbBo bo = new QuerySbzbBo();
        try {
            // vo.getUserData();
            bo = (QuerySbzbBo) vo.getData();
            System.out.println("bo.getSbbh()" + bo.getSbbh());
            // 获取连接
            conn = QSDBUtil.getConnection();
            // 查询主表，获取申报表号，申报状态，个人非个人标识，是否交换
            Sbzb sbzb = new Sbzb();
            // 从bo中取出查询条件申报表号，然后赋值给申报主表
            sbzb.sbbh = bo.getSbbh();
            // 调用SbzbDAO的get方法
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            if (sbzb != null) {
                // 将从SbzbDAO中返回的sbzb值赋给bo
                bo.setSbbh(sbzb.sbbh);
                Debug.out("sbzb.sbbh:" + sbzb.sbbh);
                bo.setSbrq(sbzb.sbrq);
                bo.setZtbs(sbzb.ztbs);
                if (sbzb.dfsbbh == sbzb.sbbh) {
                    bo.setExistFwjh(true);
                } else {
                    bo.setExistFwjh(false);
                }
                if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)) {
                    bo.setPerson(true);
                } else {
                    bo.setPerson(false);
                }
                // 查询申报土地关联表，判断是否录入房土信息
                Tufwxx tufwxx = new Tufwxx();
                tufwxx.sbbh = bo.getSbbh();
                Debug.out("tufwxx.sbbh 1:" + tufwxx.sbbh);
                tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                         .getBySbbh(tufwxx.sbbh, conn);
                Debug.out("tufwxx:" + tufwxx);
                if (tufwxx == null) {
                    bo.setExistFtxx(false);
                } else {
                    bo.setExistFtxx(true);
                }

                // 查询申报拆迁关联表，判断是否录入拆迁信息
                ArrayList jsblcq = new ArrayList();

                jsblcq = DAOFactory.getInstance().getJsblcqDAO().queryBySbbh(
                        bo.getSbbh(), conn);
                Debug.out("jsblcq.size():" + jsblcq.size());
                if (jsblcq.size() == 0) {
                    bo.setExistCqxx(false);
                } else {
                    bo.setExistCqxx(true);
                }

                // 查询申报公有住房关联表，判断是否录入公有住房信息
                ArrayList jsblgyzf = new ArrayList();
                jsblgyzf = DAOFactory.getInstance().getJsblgyzfDAO().getBySbbh(
                        bo.getSbbh(), conn);
                Debug.out("jsblgyzf:" + jsblgyzf);
                Debug.out("jsblgyzf.size():" + jsblgyzf.size());
                if (jsblgyzf.size() == 0) {
                    bo.setExistGyzf(false);
                } else {
                    bo.setExistGyzf(true);
                }
                resultList.add(bo);
            }
        }

        catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }

    /**
     * 查询核定通知书 参数只有一个申报表号
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzs(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doQueryHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报数据
            bo = (SbxxBo) doGet(vo);

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            if (bo.isBZ()) {
                hdtzsBo.setFwlxmc(bo.getVoTufwxx().getFlmc());
                hdtzsBo.setFwlxdm(bo.getVoTufwxx().getFldm());
            }
            return hdtzsBo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 查询核定通知书 参数只有一个申报表号
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyFwhm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doQueryHdtzsbyFwhm... " + bo.getFwhm());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("查询该防伪号码的核定通知书是否存在");
            condition.append(" where FWHM='").append(bo.getFwhm()).append("'");
            // 查询该防伪号码的核定通知书是否存在
            hdtzsList = DAOFactory.getInstance().getHdtzsDAO().query(
                    condition.toString(), conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return hdtzsList;

    }

    /**
     * 更新核定通知书
     *
     * @param vo
     *            VOPackage
     */
    private void doUpdateHdtzs(VOPackage vo) throws BaseException {
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doUpdateHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("更新核定通知书主表数据防伪号码");
            // 更新核定通知书主表数据防伪号码
            DAOFactory.getInstance().getHdtzsDAO().updatefwhm(bo, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }
}
