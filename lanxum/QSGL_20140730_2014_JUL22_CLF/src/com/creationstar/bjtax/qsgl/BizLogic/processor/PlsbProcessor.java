package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.DrpcInfoDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.DrzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.FgrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.GrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsfsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbcqglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbtdfwglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SpjgxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbcqgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class PlsbProcessor implements Processor {


    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is PlsbProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.QUERY_DRZB:
            result = doQueryDrzb(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.INSERT:
            result = doAdd(vo);
            break;
        case ActionType.CHECK:
            result = doCheck(vo);
            break;
        case ActionType.DELETE:
            doDelete(vo);
            break;
        case ActionType.DELETE_ALLDR:
            doDeletePc(vo);
            break;

        case ActionType.RECEIVE_DRZB:
            doReceive(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 根据查询条件查询批量导入表里的数据
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        PlsbBo bo = new PlsbBo();
        try {
            //vo.getUserData();
            bo = (PlsbBo) vo.getData();
            //String conditions = CommonUtil.getSqlQueryCondition(bo);
            String conditions = "";
            conditions += " and rownum<202 ";
            conditions += " and a.drpch =" + " b.drpch ";
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and a.drpch = '" + bo.getDrpch() + "'";
            }
            if ((bo.getXh() != null) && (!bo.getXh().equals(""))) {
                conditions += " and a.xh = '" + bo.getXh() + "'";
            }
            if ((bo.getNsrmc() != null) && (!bo.getNsrmc().equals(""))) {
                conditions += " and a.nsrmc like '" + bo.getNsrmc() + "%'";
            }
            if ((bo.getDrsj() != null) && (!bo.getDrsj().equals(""))) {
                conditions += " and to_char(a.drsj,'yyyyMMdd') = '" +
                        bo.getDrsj() + "'";
            }
            if ((bo.getTgzjsjdm() != null) && (!bo.getTgzjsjdm().equals(""))) {
                conditions += " and b.tgzjsjdm = '" + bo.getTgzjsjdm() + "'";
            }
            if ((bo.getTgzmc() != null) && (!bo.getTgzmc().equals(""))) {
                conditions += " and b.tgzmc like '%" + bo.getTgzmc() + "%'";
            }
            if ((bo.getJsfsdm() != null) && (!bo.getJsfsdm().equals(""))) {
                conditions += " and b.jsfsdm = '" + bo.getJsfsdm() + "'";
            }

            if ((bo.getSl() != null) && (!bo.getSl().equals(""))) {
                if (bo.getSl().equals("unreceived")) {
                    conditions += " and a.ztbs != '" + Constants.DRZB_ZT_RECIVE +
                            "'";
                }
                if (bo.getSl().equals("received")) {
                    conditions += " and a.ztbs = '" + Constants.DRZB_ZT_RECIVE +
                            "'";
                }

            }
            //增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "QSDB", "QS_JL_DRZB");
            Debug.out("datafilter: " + datafilter);
            conditions += " and " + datafilter;
            conditions += "order by a.drsj desc";
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getDrzbDAO().queryDrxx(
                    conditions, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PlsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }


    /**
     * 根据批次信息查询批量导入主表里的数据(尚未受理)
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQueryDrzb(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        PlsbBo bo = new PlsbBo();
        try {
            //UserData ud = vo.getUserData();
            //vo.getUserData();
            bo = (PlsbBo) vo.getData();
            String conditions = "";
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and drpch = '" + bo.getDrpch() + "'";
            }
            conditions += " and ztbs = '" + Constants.DRZB_ZT_CHECKED + "'";
            //增加数据权限控制
//            UserData ud = vo.getUserData();
//            String datafilter = ZKAdapter.getInstance().getDataFilterString(
//                    ud, "QSDB", "QS_JL_SBZB");
//            Debug.out("datafilter: " + datafilter);
//            conditions += " and " + datafilter;

            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getDrzbDAO().query(
                    conditions, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税批量处理－PlsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }


    /**
     * 获得单条信息
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {

        return null;
    }

    /**
     * 将数据从批量处理表导入正式处理表
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doAdd(VOPackage vo) throws BaseException {

        return null;
    }

    /**
     * 对批量处理表里的批量数据进行逻辑验证
     * 验证用户身份信息(通过commonutil.handleZRR)，
     * 验证拆迁信息(先从正式表中getcqxx,没得到同号的验证通过，如果有和现有信息进行比对，检查内容是否匹配和此次使用额是否超出)
     * 验证公有住房信息(先从正式表中getcqxx,没得到同号的验证通过，如果有和现有信息进行比对，检查内容是否匹配和此次使用额是否超出)，
     * 验证审批减免信息()
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     * TODO A 需要修改check方法，得到批量导入的PldrBo后，校验是否有问题，然后把有问题的PldrBo放到list中返回。
     */

    private Object doCheck(VOPackage vo) throws BaseException {
        Connection conn = null;
        conn = QSDBUtil.getConnection();
        HashMap errorMap = new HashMap();
        try {
            UserData ud = vo.getUserData();
            ArrayList checkList = new ArrayList();
            //list中每个对象都为pldrBo
            checkList = (ArrayList) vo.getData();

            //保存pldrBo中的对象
            ArrayList cqxxList = new ArrayList();
            ArrayList gyzfxxList = new ArrayList();
            ArrayList fwjhxxList = new ArrayList();
            Grxx grxx = null;
            Fgrxx fgrxx = null;
            Spjgxx spjgxx = null;
            Tufwxx tufwxx = null;
            Jsblcq cq = null;
            Jsblgyzf gf = null;

            //审批结果的提示信息和错误信息
            ArrayList spjeErrList = new ArrayList(); //获得审批信息则验证减免税金额和此次是否相符
            ArrayList spyyErrList = new ArrayList(); //契税的审批结果中验证此审批结果是否已经使用
            ArrayList sptsList = new ArrayList();

            //身份验证错误信息list、拆迁、公房、房屋交换错误信息list
            ArrayList grnsrxxErrList = new ArrayList(); //个人信息中纳税人名称和此次不符
            ArrayList fgrnsrxxdmErrList = new ArrayList(); //非个人信息中计算机代码错误
            ArrayList fgrnsrxxmcErrList = new ArrayList(); //非个人信息中根据此计算机代码获得纳税人名称与此次不符

            ArrayList cqxxbceErrList = new ArrayList(); //拆迁协议号获取拆迁信息，验证库中保存的拆迁补偿额和此次录入的拆迁补偿额是否相等
            ArrayList cqxxsyeErrList = new ArrayList(); //验证库中保存的拆迁补偿剩余额大于此次拆迁使用额，小于则异常
            ArrayList gyzfcjjgErrList = new ArrayList(); //根据公有住房协议号获取公有住房信息，验证库中保存的公有住房成交价格和此次录入的成交价格是否相等
            ArrayList gyzfsyeErrList = new ArrayList(); //验证库中保存的公有住房剩余额大于此次抵扣额，小于则异常
            ArrayList fgrspErrList = new ArrayList(); //非个人申报纳税，其纳税人类型为国家机关、事业单位、社会团体或军事单位，且购房原因属于办公、教学、医疗、科研或军事设施等情况的，系统应提示“纳税人应先办理减免税申报审核手续再办理税款缴纳手续”，并拒绝受理。
            ArrayList tufwxxErrList = new ArrayList();
            ArrayList fwjhxxErrList = new ArrayList();

            //正常数据的arraylist
            ArrayList okList = new ArrayList();
            //删除数据的list
            ArrayList delList = new ArrayList();
            //状态
            String zt = new String();

            //定义dao
            SpjgxxDAO spDao = DAOFactory.getInstance().getSpjgxxDAO();
            JsblcqDAO cqDao = DAOFactory.getInstance().getJsblcqDAO();
            JsblgyzfDAO gfDao = DAOFactory.getInstance().getJsblgyzfDAO();
            DrzbDAO drzbDao = DAOFactory.getInstance().getDrzbDAO();

            //pch
            String pch = "";
            if (checkList != null && checkList.size() > 0) {
                for (int i = 0; i < checkList.size(); i++) {
                    //信息验证状态 0(正常) 1(异常)
                    zt = "0";
                    //获取传递过来的对象
                    PldrBo bo = (PldrBo) checkList.get(i);
                    //个人信息
                    ArrayList grxxList = bo.getGrxxList();
                    //非个人信息
                    ArrayList fgrxxList = bo.getFgrxxList();
                    grxx = bo.getGrxx();
                    fgrxx = bo.getFgrxx();
                    spjgxx = bo.getSpjgxx();
                    tufwxx = bo.getTufwxx();
                    cqxxList = bo.getCqxxList();
                    gyzfxxList = bo.getGyzfxxList();
                    fwjhxxList = bo.getFwjhxxList();
                    pch = bo.getPch();

                    //错误数据内容值对象
                    PlsbErrBo plsbErrBo = new PlsbErrBo();

                    //对信息进行验证
                    //个人信息(验证纳税人姓名和登记是否相符)
                    for (int m = 0; m < grxxList.size(); m++) {
                        grxx = (Grxx) grxxList.get(m);
                        if (grxx != null && zt.equals("0")) {
                            grxx.setCjr(ud.getYhmc());
                            Timestamp now = new Timestamp(System.
                                    currentTimeMillis());
                            grxx.setCjrq(now);
                            try {
                                CommonUtil.handleZRR(grxx, ud);
                            } catch (ApplicationException ex) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                grnsrxxErrList.add(plsbErrBo);
                                zt = "1";
                            }
                        }
                    }
                    //非个人信息(验证纳税人姓名和登记是否相符)
                    for (int n = 0; n < fgrxxList.size(); n++) {
                        fgrxx = (Fgrxx) fgrxxList.get(n);
                        if (fgrxx != null && zt.equals("0")) {
                            try {
                                HashMap djinfo = CommonUtil.getFgrDjInfo(fgrxx.
                                        getJsjdm());
                                SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
                                if (!jbsj.getNsrmc().equals(fgrxx.getNsrmc())) {
                                    //输入的计算机代码获取的纳税人姓名和此次不符
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                    fgrnsrxxmcErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                            //输入的计算机代码不存在对应的非个人信息
                            catch (Exception ex) {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                fgrnsrxxdmErrList.add(plsbErrBo);
                                zt = "1";
                            }
                            if (!fgrxx.nsrlxdm.equals("05") && zt.equals("0")) {
                                //纳税人类型不是其他
                                if (tufwxx.fldm.equals("01") ||
                                    tufwxx.fldm.equals("02") ||
                                    tufwxx.fldm.equals("03") ||
                                    tufwxx.fldm.equals("04") ||
                                    tufwxx.fldm.equals("05")) {
                                    //购房原因属于办公、教学、医疗、科研或军事设施等情况
                                    if (spjgxx == null) {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                        plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                        plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                        fgrspErrList.add(plsbErrBo);
                                        zt = "1";
                                    }
                                }
                            }
                        }
                    }
                    if (tufwxx != null && zt.equals("0")) {
                        Date nowTime = new Date(System.currentTimeMillis());
                        Timestamp inputTime = tufwxx.getHtqdsj();
                        String now = CommonUtil.getDatetime(nowTime, "yyyyMMdd");
                        String input = DataConvert.TimeStamp2String(inputTime);
                        if (input.compareTo(now) > 0) {
                            //大于当前时间
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                            tufwxxErrList.add(plsbErrBo);
                            zt = "1";

                        }
                    }
                    //审批结果信息
                    if (spjgxx != null && zt.equals("0")) {
                        HashMap map = CommonUtil.getZcspjg(spjgxx.getHdtzszh());

                        //接口获得此信息但和用户填写不符
                        if (map != null) {
                            spjgxx.setJmlydm((String) map.get("qtjmly"));
                            if (spjgxx.getJmsje().doubleValue() !=
                                (((BigDecimal) map.get("jmse")).doubleValue())) {
                                //和接口取来的数据减免金额不一致
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                spjeErrList.add(plsbErrBo);
                                zt = "1";
                            }
                        }
                        //数据库中此信息已经使用
                        Spjgxx spjgxx1 = (Spjgxx) spDao.get(spjgxx, conn);
                        if (spjgxx1.getHdtzszh() != null && !zt.equals("1")) {
                            //数据库中此审批信息已经使用
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                            spyyErrList.add(plsbErrBo);
                            zt = "1";
                        }

                        //接口没得到减免信息
                        if (map == null && !zt.equals("1")) {
                            //从接口没有获取到此审批号,提示核对审批信息
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                            sptsList.add(plsbErrBo);
                        }

                    }

                    //拆迁信息
                    if (cqxxList != null && cqxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int j = 0; j < cqxxList.size(); j++) {
                            cq = (Jsblcq) cqxxList.get(j);
                            Jsblcq cq1 = (Jsblcq) cqDao.get(
                                    cq, conn);
                            if (cq1 == null) {
                                //新的拆迁信息
                                break;
                            }
                            if (cq1.cqbce.doubleValue() !=
                                cq.cqbce.doubleValue()) {
                                //此次的拆迁补偿额和库中保存的不相符
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                cqxxbceErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }

                            if (cq1.cqbcsye.doubleValue() <
                                cq.bcsybce.doubleValue()) {
                                //此次的拆迁使用额超过了剩余的补偿额
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                cqxxsyeErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }
                        }
                    }
                    //共有住房信息
                    if (gyzfxxList != null && gyzfxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int k = 0; k < gyzfxxList.size(); k++) {
                            gf = (Jsblgyzf) gyzfxxList.get(k);
                            Jsblgyzf gf1 = (Jsblgyzf) gfDao.get(
                                    gf, conn);
                            if (gf1 == null) {
                                //新的公房信息
                                break;
                            }

                            if (gf1.cjjg.doubleValue() != gf.cjjg.doubleValue()) {
                                //此次的公房成交价格和库中保存的不相符
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }

                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                gyzfcjjgErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }

                            if (gf1.sye.doubleValue() < gf.bcdke.doubleValue()) {
                                //此次的使用补偿额超过了剩余的补偿额
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                gyzfsyeErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }
                        }
                    }
                    //房屋交换信息
                    if (fwjhxxList != null && fwjhxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int m = 0; m < fwjhxxList.size(); m++) {
                            //获取传递过来的对象
                            PldrBo bo1 = (PldrBo) fwjhxxList.get(i);
                            Grxx grxx1 = bo1.getGrxx();
                            Fgrxx fgrxx1 = bo1.getFgrxx();
                            //个人信息(验证纳税人姓名和登记是否相符)
                            if (grxx1 != null && zt.equals("0")) {
                                try {
                                    CommonUtil.handleZRR(grxx1, ud);
                                } catch (ApplicationException ex) {
                                    if (grxx != null) {
                                        plsbErrBo.setNsrmc(grxx.getNsrmc());
                                    } else {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    }
                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                    fwjhxxErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                            //非个人信息(验证纳税人姓名和登记是否相符)
                            if (fgrxx1 != null && zt.equals("0")) {
                                try {
                                    HashMap djinfo = CommonUtil.getFgrDjInfo(
                                            fgrxx1.
                                            getJsjdm());
                                    SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get(
                                            "JBSJ");
                                    if (!jbsj.getNsrmc().equals(fgrxx1.getNsrmc())) {
                                        //输入的计算机代码获取的纳税人姓名和此次不符
                                        if (grxx != null) {
                                            plsbErrBo.setNsrmc(grxx.getNsrmc());
                                        } else {
                                            plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                        }
                                        plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                        plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                        fwjhxxErrList.add(plsbErrBo);
                                        zt = "1";
                                    }
                                }
                                //输入的计算机代码不存在对应的非个人信息
                                catch (Exception ex) {
                                    if (grxx != null) {
                                        plsbErrBo.setNsrmc(grxx.getNsrmc());
                                    } else {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    }

                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                    fwjhxxErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                        }
                    }

                    //check 完成，更新临时表里的数据状态
                    if (zt.equals("0")) {
                        Drzb drzb = new Drzb();
                        drzb.setDrpch(bo.getPch());
                        drzb.setXh(bo.getXh());
                        drzb.setZtbs(Constants.DRZB_ZT_CHECKED);
                        drzbDao.updateZt(drzb, conn);
                        okList.add(bo);
                    }
                    //删除临时表中的数据
                    if (zt.equals("1")) {
                        Drzb drzb = new Drzb();
                        drzb.setDrpch(bo.getPch());
                        drzb.setXh(bo.getXh());
                        delList.add(drzb);
                    }
                }
            }
            errorMap.put("nsrgr", grnsrxxErrList);
            errorMap.put("nsrfgrdm", fgrnsrxxdmErrList);
            errorMap.put("nsrfgrmc", fgrnsrxxmcErrList);
            errorMap.put("spts", sptsList);
            errorMap.put("spje", spjeErrList);
            errorMap.put("spyy", spyyErrList);
            errorMap.put("cqbce", cqxxbceErrList);
            errorMap.put("cqsye", cqxxsyeErrList);
            errorMap.put("gfcjjg", gyzfcjjgErrList);
            errorMap.put("gfsye", gyzfsyeErrList);
            errorMap.put("fgrsp", fgrspErrList);
            errorMap.put("fwjh", fwjhxxErrList);
            errorMap.put("tufwxx", tufwxxErrList);
            errorMap.put("ok", okList);
            //根据导入批次号，删除临时表中的数据
            /**TODO  B 批量导入需要修改，修改为有错误的不删除，打上审核不通过的标志，待修改后更新标志，
             * 同时往okList中放入数据、删除errorMap中相关的数据
             * 在生成申报信息的时候，不取审核不通过的
             *
             **/
            if (delList != null && delList.size() > 0) {
                drzbDao.delete(delList, conn);
                String conditions = "";
                conditions += " and drpch = '" + pch + "'";
                ArrayList list = DAOFactory.getInstance().getDrzbDAO().query(
                        conditions, conn);
                if (list.size() == 0) {
                    Drpcinfo df = new Drpcinfo();
                    df.setDrpch(pch);
                    ArrayList pcinfoList = new ArrayList();
                    pcinfoList.add(df);
                    DAOFactory.getInstance().getDrpcInfoDAO().delete(pcinfoList,
                            conn);
                }
            }
            return errorMap;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税批量处理－PlsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex,
                                                 "契税批量处理－PlsbProcessor核对数据失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * 删除批量处理表里的批量数据
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException {

        Connection conn = null;

        try {
            //vo.getUserData();
            //PlsbBo bo = (PlsbBo) vo.getData();
            ArrayList delList = new ArrayList();
            ArrayList zbList = new ArrayList();
            delList = (ArrayList) vo.getData();
            Drzb zb = new Drzb();
            UserData user = vo.getUserData();
            PlsbBo bo = new PlsbBo();
            bo = (PlsbBo) delList.get(0);
            zb = bo.getDrzb();
            String pch = zb.getDrpch();
            conn = QSDBUtil.getConnection();

            if (delList != null && delList.size() > 0) {
                for (int i = 0; i < delList.size(); i++) {
                    bo = (PlsbBo) delList.get(i);
                    zb = (Drzb) bo.getDrzb();
                    zbList.add(zb);
                }
                DAOFactory.getInstance().getDrzbDAO().delete(zbList, conn);
            }
            String conditions = "";
            conditions += " and drpch = '" + pch + "'";
            ArrayList list = DAOFactory.getInstance().getDrzbDAO().query(
                    conditions, conn);
            if (list.size() == 0) {
                Drpcinfo df = new Drpcinfo();
                df.setDrpch(pch);
                ArrayList pcinfoList = new ArrayList();
                pcinfoList.add(df);
                DAOFactory.getInstance().getDrpcInfoDAO().delete(pcinfoList,
                        conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PlsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return null;
    }

    /**
     * 受理批量信息
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doReceive(VOPackage vo) throws BaseException {
        Connection conn = null;
        conn = QSDBUtil.getConnection();
        try {
            UserData ud = vo.getUserData();
            ArrayList receiveList = new ArrayList();
            //list中每个对象都为pldrBo
            receiveList = (ArrayList) vo.getData();

            //保存pldrBo的对象
            ArrayList cqxxList = new ArrayList();
            ArrayList gyzfxxList = new ArrayList();
            ArrayList fwjhxxList = new ArrayList();
            ArrayList grxxList = new ArrayList();
            ArrayList fgrxxList = new ArrayList();
            Grxx grxx = null;
            Fgrxx fgrxx = null;
            Spjgxx spjgxx = null;
            Tufwxx tufwxx = null;
            Jsblcq cq = null;
            Jsblgyzf gf = null;

            PldrBo bo = null;
            Sbzb sbzb = null;

            //系统所用到的当前时间,录入人等信息
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String sbbh = null;
            String yh = ud.yhmc;
            String nd = String.valueOf(Calendar.getInstance().get(
                    Calendar.YEAR));

            //定义dao
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            GrxxDAO grxxDao = DAOFactory.getInstance().getGrxxDAO();
            FgrxxDAO fgrxxDao = DAOFactory.getInstance().getFgrxxDAO();
            SpjgxxDAO spjgxxDao = DAOFactory.getInstance().getSpjgxxDAO();
            TufwxxDAO tufwxxDao = DAOFactory.getInstance().getTufwxxDAO();
            SbtdfwglDAO sbtdfwglDao = DAOFactory.getInstance().getSbtdfwglDAO();
            JsblcqDAO cqDao = DAOFactory.getInstance().getJsblcqDAO();
            JsblgyzfDAO gfDao = DAOFactory.getInstance().getJsblgyzfDAO();
            DrzbDAO drzbDao = DAOFactory.getInstance().getDrzbDAO();
            DrpcInfoDAO drpcDao = DAOFactory.getInstance().getDrpcInfoDAO();
            SbcqglDAO sbcqglDao = DAOFactory.getInstance().getSbcqglDAO();
            SbgyzfDAO sbgyzfDao = DAOFactory.getInstance().getSbgyzfDAO();
            JsfsDAO jsfsDao = DAOFactory.getInstance().getJsfsDAO();

            String pch = new String();

            if (receiveList != null && receiveList.size() > 0) {

                for (int i = 0; i < receiveList.size(); i++) {
                    //信息验证状态 0(正常) 1(异常)
                    //获取传递过来的对象
                    bo = (PldrBo) receiveList.get(i);
                    grxxList = bo.getGrxxList();
                    fgrxxList = bo.getFgrxxList();
                    grxx = bo.getGrxx();
                    fgrxx = bo.getFgrxx();
                    spjgxx = bo.getSpjgxx();
                    tufwxx = bo.getTufwxx();
                    cqxxList = bo.getCqxxList();
                    gyzfxxList = bo.getGyzfxxList();
                    fwjhxxList = bo.getFwjhxxList();
                    pch = bo.getPch();
                    //根据批次号获取缴税方式代码和名称
                    Drpcinfo drpcInfo = new Drpcinfo();
                    drpcInfo.setDrpch(pch);
                    drpcInfo = (Drpcinfo) drpcDao.get(drpcInfo, conn);
                    String jsfsdm = drpcInfo.getJsfsdm();
                    String jsfsmc = drpcInfo.getJsfmc();

                    //获取申报表号
                    sbbh = CommonUtil.getPLBH(ud.getXtsbm1(), conn);
                    //申报主表信息
                    //构造申报主表数据
                    sbzb = new Sbzb();

                    sbzb.blbs = Constants.ZB_BLBS_FBL;
                    sbzb.bljmsbs = Constants.ZB_BLJMSBS_BFHBLTJ;
                    sbzb.bz = tufwxx.getBz();
                    sbzb.cjr = yh;
                    sbzb.cjrq = now;
                    sbzb.fwtdbmdm = tufwxx.getFwtdbmdm();
                    //----add by jiq at 20061211-------------
                    if("1".equals(tufwxx.getSetz())||"5".equals(tufwxx.getSetz())||"6".equals(tufwxx.getSetz())){
                    	sbzb.setz = tufwxx.getSetz();
                    }else{
                    	sbzb.setz = bo.getSetz();
                    }
                    
                    //---------------------------------------
                    sbzb.jsfsdm = jsfsdm;
                    sbzb.jsfsmc = jsfsmc;
                    //按照业务判断确定成交价格
                    sbzb.jsje = new BigDecimal(CommonUtil.getCjjg(tufwxx));
                    sbzb.lrr = yh;
                    sbzb.lrrq = now;
                    if (grxx != null) {
                        sbzb.nsrlxdm = Constants.NSRLX_GR;
                    } else {
                        sbzb.nsrlxdm = fgrxx.getNsrlxdm();
                    }

                    sbzb.sbbh = sbbh;
                    sbzb.sbrq = now;

                    //----add by zhangyj at 20081030-------------
                    if ((grxx != null) && (sbzb.getSetz().equals("5"))) {
                        sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.
                                ZCWH_SCGMPTZFSL, conn));
                    } else {
                        sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.
                                ZCWH_SL, conn));
                    }
                    //---------------------------------------

                    sbzb.swjgzzjgdm = ud.getSsdwdm();
                    if (grxx != null) {
                        sbzb.yhbs = Constants.YHBZ_GR; //个人
                    } else {
                        sbzb.yhbs = Constants.ZB_YHBS_FGR; ;
                    }
                    sbzb.ynse = new BigDecimal(0);
                    sbzb.zsrymc = ud.getYhmc();
                    sbzb.ztbs = Constants.ZB_ZTBS_DY;
                    sbzb.pzzhdm = ud.getXtsbm1();
                    sbzb.pzzhmc = ud.getXtsbm2();
                    sbzb.drpch = bo.getPch();
                    //insert sbzb
                    sbzb.setSjly("05");
                    sbzbDao.insert(sbzb, conn);

                    //对信息进行构造
                    //个人信息(插入个人信息)
                    for (int m = 0; m < grxxList.size(); m++) {
                        grxx = (Grxx) grxxList.get(m);
                        if (grxx != null) {
                            grxx.cjr = yh;
                            grxx.cjrq = now;
                            grxx.fwjhbs = Constants.FFWJHBS;
                            grxx.lrr = yh;
                            grxx.lrrq = now;
                            grxx.sbbh = sbbh;
                            //产权人类型，默认主产权人
                            //grxx.cqrlx = Constants.CQRLX_ZCQR;

                            //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                            grxx = CommonUtil.handleZRR(grxx, ud);
                            grxxDao.insert(grxx, conn);

                        }
                    }
                    //非个人信息(验证纳税人姓名和登记是否相符)
                    for (int k = 0; k < fgrxxList.size(); k++) {
                        fgrxx = (Fgrxx) fgrxxList.get(k);
                        if (fgrxx != null) {
                            fgrxx.cjr = yh;
                            fgrxx.cjrq = now;
                            fgrxx.fwjhbs = Constants.FFWJHBS;
                            fgrxx.lrr = yh;
                            fgrxx.lrrq = now;

                            fgrxx.sbbh = sbbh;
                            //insert fgrxx
                            fgrxxDao.insert(fgrxx, conn);
                        }
                    }
                    //审批结果信息
                    if (spjgxx != null) {
                        spjgxx.cjr = yh;
                        spjgxx.cjrq = now;
                        //spjgxx.hdtzszh = bo.getHdtzszh();
                        if ((spjgxx.getJmlydm() == null) ||
                            (spjgxx.getJmlydm().equals(""))) {
                            spjgxx.jmlydm = "00";
                        }
                        //spjgxx.jmsje = bo.jmsje;
                        spjgxx.lrr = yh;
                        spjgxx.lrrq = now;
                        spjgxx.sbbh = sbbh;
                        spjgxxDao.insert(spjgxx, conn);
                    }

                    //土地房屋信息
                    if (tufwxx != null) {
                        tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
                        tufwxx.nd = String.valueOf(Calendar.getInstance().get(
                                Calendar.YEAR));
                        tufwxx.setLrr(yh);
                        tufwxx.setCjr(yh);
                        tufwxx.setLrrq(now);
                        tufwxx.setCjrq(now);

                        //insert tufwxx
                        //System.out.println("tufwxx.rjl"+tufwxx.rjl);
                        tufwxxDao.insert(tufwxx, conn);
                        //insert sbzbftgl
                        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                        sbtdfwgl.cjr = yh;
                        sbtdfwgl.cjrq = now;
                        sbtdfwgl.lrr = yh;
                        sbtdfwgl.lrrq = now;
                        sbtdfwgl.sbbh = sbbh;
                        sbtdfwgl.tdfwid = tufwxx.tdfwid;
                        sbtdfwglDao.insert(sbtdfwgl, conn);
                    }

                    //拆迁信息
                    if (cqxxList != null && cqxxList.size() > 0) {
                        for (int j = 0; j < cqxxList.size(); j++) {
                            cq = (Jsblcq) cqxxList.get(j);
                            Jsblcq existJsblcq = (Jsblcq) cqDao.get(
                                    cq, conn);
                            if (existJsblcq != null) {
                                //update
                                existJsblcq.cqbcsye = new BigDecimal(
                                        existJsblcq.cqbcsye.doubleValue() -
                                        cq.bcsybce.doubleValue());
                                cqDao.update(existJsblcq, conn);
                            } else {
                                cq.cqbcsye = cq.cqbce.subtract(cq.bcsybce);
                                if (grxx != null) {
                                    cq.yhbs = Constants.YHBZ_GR; //个人
                                } else {
                                    cq.yhbs = Constants.YHBZ_FGR; //非个人
                                }
                                cq.ztbs = "0";
                                cq.cjr = ud.yhmc;
                                cq.lrr = ud.yhmc;
                                cq.cjrq = now;
                                cq.lrrq = now;
                                cq.nd = nd;
                                cqDao.insert(cq, conn);
                            }

                            //construct the sbcqgl and insert
                            Sbcqgl sbcqgl = new Sbcqgl();
                            sbcqgl.cjr = yh;
                            sbcqgl.lrr = yh;
                            sbcqgl.cjrq = now;
                            sbcqgl.lrrq = now;
                            sbcqgl.cqxyh = cq.cqxyh;
                            sbcqgl.sbbh = sbbh;
                            sbcqgl.bcsybce = cq.bcsybce;
                            sbcqglDao.insert(sbcqgl, conn);

                            //更新主表办理减免税标识
                            sbzbDao.updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR,
                                                sbbh, conn);
                        }
                    }
                    //共有住房信息
                    if (gyzfxxList != null && gyzfxxList.size() > 0) {
                        for (int k = 0; k < gyzfxxList.size(); k++) {
                            gf = (Jsblgyzf) gyzfxxList.get(k);
                            Jsblgyzf existGyzf = (Jsblgyzf) DAOFactory.
                                                 getInstance().
                                                 getJsblgyzfDAO().get(
                                    gf, conn);

                            if (existGyzf != null) {
                                existGyzf.setSye(new BigDecimal(existGyzf.
                                        getSye().doubleValue() -
                                        gf.bcdke.doubleValue()));

                                gfDao.update(existGyzf, conn);
                            } else {
                                //construct the jsblgyzf and insert
                                gf.sye = gf.cjjg.subtract(gf.bcdke);
                                gf.ztbs = "0";
                                gf.cjr = yh;
                                gf.lrr = yh;
                                gf.cjrq = now;
                                gf.lrrq = now;
                                gf.nd = nd;

                                gfDao.insert(gf, conn);
                            }
                            //construct the sbgyzf and insert
                            Sbgyzf sbgyzf = new Sbgyzf();
                            sbgyzf.yggyzfqszsh = gf.yggyzfqszsh;
                            sbgyzf.bcdke = gf.bcdke;
                            sbgyzf.lrr = yh;
                            sbgyzf.cjr = yh;
                            sbgyzf.cjrq = now;
                            sbgyzf.lrrq = now;
                            sbgyzf.sbbh = sbbh;
                            sbgyzfDao.insert(sbgyzf, conn);

                            //更新主表办理减免税标识
                            bo.setSbzb(sbzb);
                            JghdsjBo hdbo = CommonUtil.getJZSE(bo, conn);
                            //获取已购公有住房项
                            BigDecimal sjyz = hdbo.getSjyz(); //应征契税
                            BigDecimal kcqyzfx = hdbo.getGyzfjmje();
                            //更新主表办理减免税标识,当其计征税额抵扣出售公房收入后实际应征税额为0时
                            if (kcqyzfx != null && kcqyzfx.doubleValue() > 0) {
                                if (sjyz != null && sjyz.doubleValue() <= 0 &&
                                    !
                                    Constants.ZB_BLJMSBS_FHBL_WLR.equals(bo.getSbzb().
                                        getBljmsbs())) {
                                    sbzbDao.updateJmsbs(Constants.
                                            ZB_BLJMSBS_FHBL_WLR, sbbh, conn);
                                }
                            }

                        }
                    }
                    //房屋交换信息
                    if (fwjhxxList != null && fwjhxxList.size() > 0) {
                        for (int m = 0; m < fwjhxxList.size(); m++) {
                            //获取传递过来的对象
                            PldrBo bo1 = (PldrBo) fwjhxxList.get(i);

                            //构造一个Sbzb的值对象
                            sbzb = new Sbzb();

                            sbzb.sbbh = CommonUtil.getPLBH(ud.getXtsbm1(), conn);
                            sbzb.blbs = Constants.ZB_BLBS_FBL;
                            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BZ;
                            sbzb.bz = tufwxx.getBz();
                            sbzb.cjr = yh;
                            sbzb.cjrq = now;
                            sbzb.fwtdbmdm = tufwxx.getFwtdbmdm();
                            sbzb.jsfsdm = Constants.WSZ_JSFS_XJ;
                            sbzb.jsfsmc = "现金";
                            sbzb.jsje = new BigDecimal(0);
                            sbzb.lrr = yh;
                            sbzb.lrrq = now;
                            sbzb.sbrq = now;
                            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(
                                    Constants.ZCWH_SL, conn));
                            sbzb.swjgzzjgdm = ud.getSsdwdm();
                            if (bo1.getGrxx() != null) {
                                sbzb.yhbs = Constants.YHBZ_GR; //个人
                                sbzb.nsrlxdm = Constants.NSRLX_GR;
                            }
                            if (bo1.getFgrxx() != null) {
                                sbzb.yhbs = Constants.YHBZ_FGR; //非个人
                                sbzb.nsrlxdm = bo1.getFgrxx().getNsrlxdm();
                            }

                            sbzb.ynse = new BigDecimal(0);
                            sbzb.zsrymc = yh;
                            sbzb.ztbs = Constants.ZB_ZTBS_DY;
                            sbzb.pzzhdm = ud.getXtsbm1();
                            sbzb.pzzhmc = ud.getXtsbm2();
                            sbzb.setDfsbbh(sbbh);
                            DAOFactory.getInstance().getSbzbDAO().insert(sbzb,
                                    conn);

                            if (bo1.getGrxx() != null) {

                                //个人信息部分
                                grxx = bo1.getGrxx();
                                grxx.sbbh = sbzb.getSbbh();
                                grxx.fwjhbs = Constants.FWJHBS;
                                grxx.setLrr(yh);
                                grxx.setCjr(yh);
                                grxx.setLrrq(now);
                                grxx.setCjrq(now);
                                //产权人类型，默认主产权人
                                grxx.cqrlx = Constants.CQRLX_ZCQR;
                                //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                                grxx = CommonUtil.handleZRR(grxx, ud);
                                grxxDao.insert(grxx, conn);
                            }
                            if (bo1.getFgrxx() != null) {

                                //非个人信息部分
                                fgrxx = bo1.getFgrxx();
                                fgrxx.sbbh = sbzb.getSbbh();
                                fgrxx.fwjhbs = Constants.FWJHBS;
                                fgrxx.setLrr(yh);
                                fgrxx.setCjr(yh);
                                fgrxx.setLrrq(now);
                                fgrxx.setCjrq(now);

                                fgrxxDao.insert(
                                        fgrxx, conn);

                            }

                            //插入土地房屋信息
                            tufwxx = bo1.getTufwxx();
                            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);

                            tufwxx.setLrr(yh);
                            tufwxx.setCjr(yh);
                            tufwxx.setLrrq(now);
                            tufwxx.setCjrq(now);
                            tufwxx.setNd(nd);

                            tufwxxDao.insert(
                                    tufwxx, conn);

                            //插入“申报主表与土地房屋关联表”的信息
                            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                            sbtdfwgl.sbbh = sbzb.getSbbh();
                            sbtdfwgl.tdfwid = tufwxx.tdfwid;
                            sbtdfwgl.setLrr(yh);
                            sbtdfwgl.setCjr(yh);
                            sbtdfwgl.setLrrq(now);
                            sbtdfwgl.setCjrq(now);

                            sbtdfwglDao.insert(
                                    sbtdfwgl, conn);
                            //更新原申报表中的对方申报表号
                            DAOFactory.getInstance().getSbzbDAO().updateDfsbbh(
                                    sbbh, sbzb.getSbbh(), conn);
                            if (bo1.getGrxx() != null) {
                                //更新原申报表个人的房屋交换标识
                                grxxDao.
                                        updateFwjhbs(Constants.
                                        FWJHBS,
                                        sbbh, conn);
                            } else {
                                //更新原申报表个人的房屋交换标识
                                fgrxxDao.
                                        updateFwjhbs(Constants.
                                        FWJHBS,
                                        sbbh, conn);
                            }
                        }
                    }

                    //受理完成回填申报表号和更新状态标示
                    Drzb drzb = new Drzb();
                    drzb.setDrpch(bo.getPch());
                    drzb.setXh(bo.getXh());
                    drzb.setSbbh(sbbh);
                    drzb.setZtbs(Constants.DRZB_ZT_RECIVE);
                    drzbDao.updateAll(drzb, conn);
                    if (drpcInfo.getTjsj() == null) {
                        //update批次信息中的提交时间
                        drpcInfo.setTjsj(now);
                        drpcDao.updateTjsj(drpcInfo, conn);
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税批量处理－PlsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex,
                                                 "契税批量处理－PlsbProcessor受理数据失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * 删除批量处理表里的批量数据
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doDeletePc(VOPackage vo) throws BaseException {

        Connection conn = null;

        try {
            String pch = (String) vo.getData();
            conn = QSDBUtil.getConnection();
            DAOFactory.getInstance().getDrzbDAO().deletePc(pch, conn);
            DAOFactory.getInstance().getDrpcInfoDAO().deletePc(pch,
                    conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PlsbProcessor，删除数据操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return null;
    }

}
