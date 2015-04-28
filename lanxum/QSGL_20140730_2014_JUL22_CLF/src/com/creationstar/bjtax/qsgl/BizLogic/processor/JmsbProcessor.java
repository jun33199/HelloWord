package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.OperationType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
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
 * 不征契税的processor
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
public class JmsbProcessor implements Processor {
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

        Debug.out("--Debug-- Info : Here is JmsbProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doAdd(vo);

            break;

        case ActionType.QUERY:

            result = doQuery(vo);

            break;
        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.DELETE:

            doDelete(vo);

            break;
        case ActionType.PRINT_SBB:
            result = doPrintJmsbb(vo);
            break;

            // case ActionType.CONFIRM:
            // doConfirm(vo);
            // break;

            // case ActionType.REJECT:
            // doReject(vo);
            // break;
        case ActionType.PRINT_HDTZS:
            result = doPrintHdtzs(vo);
            break;
        case ActionType.Query_HDTZS:
            result = doQueryHdtzs(vo);
            break;
        case ActionType.Query_HDTZSBYFWHM:
            result = doQueryHdtzsbyFwhm(vo);
            break;
        case ActionType.UPDATE_HDTZS:
            doUpdateHdtzs(vo);
            break;
        case ActionType.CANCEL:
            doCancel(vo);
            break;
        case ActionType.Query_HDTZSBYHDTZSHM:
            result = doQueryHdtzsbyHdtzshm(vo);
            break;
        case ActionType.UPDATE_HDTZSHM_BY_HDTZSHM:
            doUpdateHdtzshmByHdtzshm(vo);
            break;
        case ActionType.ROLLBACK:
            doRollBack(vo);
            break;
        case ActionType.SAVE_CXXJMSP:
            doSavesp(vo);
            break;
        default:
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
    private JmsbBo doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        JmsbBo bo = (JmsbBo) vo.getData();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        try {

            // vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            // 原申报表号
            String sbbh_old = bo.getSbbh();

            // 从前台传送来的bo在此组织成后台各个表要用的数据，分别进行保存
            /**
             * 1先获取主表sbzb需要的信息 2获取个人信息表grxx的信息 3获取土地房屋信息表tdfwxx
             * 4获取申报主表与土地、房屋信息表的关联表SBTDFWGL信息 5执行插入操作
             */
            // 申报主表
            Sbzb sbzb = getSbzb(bo, user);
            sbzb.setCjrq(now);

            // 从commonutil中获取申报表号和当前时间,设到各个vo中
            // 此3个表有申报表号
            sbzb.setSbbh(CommonUtil.getJMSBBH2(user.getXtsbm1(), conn));

            // 如果是修改则
            // 1、保存税务机关组织机构代码、征收人员名称、票证帐户代码、票证帐户名称不变
            // 2、先删除后再插入原申报表号的申报表
            if (bo.getYmczlxdm() == OperationType.MODIFY) {

                // 1、保存税务机关组织机构代码、征收人员名称、票证帐户代码、票证帐户名称不变
                JmsbxxBo jmsbxxBo = new JmsbxxBo();
                jmsbxxBo = (JmsbxxBo) doGetSbxxBo(sbbh_old);
                // 税务机关组织机构代码
                sbzb.setSwjgzzjgdm(jmsbxxBo.getVoSbzb().getSwjgzzjgdm());
                // 征收人员名称
                sbzb.setZsrymc(jmsbxxBo.getVoSbzb().getZsrymc());
                // 票证帐户代码
                sbzb.setPzzhdm(jmsbxxBo.getVoSbzb().getPzzhdm());
                // 票证帐户名称
                sbzb.setPzzhmc(jmsbxxBo.getVoSbzb().getPzzhmc());

                // 2、先删除后再插入原申报表号的申报表
                deleteSbxx(sbbh_old, conn);
                sbzb.setSbbh(sbbh_old);

            }

            // 个人信息
            List l = getGrxx(bo, user, sbzb.getSbbh(), sbzb.getCjrq());
            // 土地房屋信息
            Tufwxx tufwxx = getTufwxx(bo, user);
            // 申报土地房屋关联
            Sbtdfwgl sbtdfwgl = getSbtdfwgl(bo, user);
            // 非个人信息
            Fgrxx fgrxx = getFgrxx(bo, user);
            // 契税减免申报表
            List al_jmsbb = getJmsbb(bo, user, sbzb);
            // Jmsbb jmsbb = getJmsbb(bo, user);

            // 设置申报表号
            sbtdfwgl.setSbbh(sbzb.getSbbh());
            fgrxx.setSbbh(sbzb.getSbbh());

            // 设置时间
            sbzb.setLrrq(sbzb.getCjrq());
            sbzb.setSbrq(bo.getSbrq());
            fgrxx.setLrrq(sbzb.getCjrq());
            fgrxx.setCjrq(sbzb.getCjrq());
            tufwxx.setCjrq(sbzb.getCjrq());
            tufwxx.setLrrq(sbzb.getCjrq());
            sbtdfwgl.setCjrq(sbzb.getCjrq());
            sbtdfwgl.setLrrq(sbzb.getCjrq());

            // 录入人 创建人
            sbzb.setCjr(user.getYhmc());
            sbzb.setLrr(user.getYhmc());
            fgrxx.setCjr(user.getYhmc());
            fgrxx.setLrr(user.getYhmc());
            tufwxx.setCjr(user.getYhmc());
            tufwxx.setLrr(user.getYhmc());
            sbtdfwgl.setCjr(user.getYhmc());
            sbtdfwgl.setLrr(sbtdfwgl.getCjr());

            // 获取减免申报表号
            // jmsbb.setJmsbbh(CommonUtil.getJMSBBH(user.getXtsbm1(),
            // conn));//减免申报表号与申报主表的申报表号一致
            // jmsbb.setjmsbbh(jmsbb.getSbbh());

            // 获取土地房屋唯一标示,放入土地房屋信息表tdfwxx
            // 和申报主表与土地、房屋信息表的关联表SBTDFWGL
            tufwxx.setTdfwid(CommonUtil.getTDFWID(conn));
            Debug.out("tufwxx.setTdfwid in JmsbProcessor is "
                      + tufwxx.getTdfwid());
            sbtdfwgl.setTdfwid(tufwxx.getTdfwid());

            // 按照业务判断确定成交价格
            double cjjgrmb = CommonUtil.getCjjg(tufwxx);
            sbzb.setJsje(new BigDecimal(cjjgrmb));

            // 税率
            sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL,
                    conn)));

            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                Debug.out("个人信息插入");
                // 个人信息获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                // 计算机代码
                // grxx = CommonUtil.handleZRR(grxx,user);
                // 获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                l = CommonUtil.handleZRR(l, user);
                bo.setNsrList(l);

                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("非个人信息插入");
                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
            }

            // 插入土地房屋信息
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);

            // 插入减免申报信息
            for (int i = 0; i < al_jmsbb.size(); i++) {

                // 减免申报表
                Jmsbb jmsbb = (Jmsbb) al_jmsbb.get(i);

                DAOFactory.getInstance().getJmsbbDAO().insert(jmsbb, conn);

            }

            // 将view页面需要显示的信息返回到前台
            bo.setSbbh(sbzb.getSbbh());
            bo.setCjrq(DataConvert.TimeStamp2String(sbzb.getCjrq()));

            // 生成核定通知书-start -
            // 2007-07-17不用审核即可以打印核定通知书，故将生成核定通知书的操作放在生成申报表时即生成

            Hdtzs hdtzs = new Hdtzs();

            String nd = DateUtil.getDate().substring(0, 4);

            // 插入核定通知书
            hdtzs.bzbs = Constants.BZBS_JM;
            hdtzs.cjr = user.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            // modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH_JMSB(user, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            // end modified

            hdtzs.jbr = user.yhmc;
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
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); // 应征契税
            hdtzs.lrr = user.yhmc;
            hdtzs.lrrq = now;
            // 获取联系电话
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                // hdtzs.sqr = bo.getVoZcqrxx().getNsrmc();
                hdtzs.sqr = ActionUtil.getNsrmc(bo.getNsrList(), null);
            } else {
                hdtzs.sqr = bo.getNsrmc();
            }
            //
            hdtzs.sbbh = bo.getSbbh();
            hdtzs.spfxmmc = bo.getFdcxmmc();
            hdtzs.zldi = bo.getTdfwzldz();

            // 插入一条核定通知书记录
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            // 插入核定通知书明细表
            for (int i = 0; i < bo.getQsjmlbSelect().length; i++) {

                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(user.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(hdbo.getSjyz());
                hdjmmx.setLrr(user.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(bo.getQsjmlbSelect()[i]);

                // 其它类减免的理由写入备注
                if (Constants.CXXJM_JMXMDM_QT.equals(bo.getQsjmlbSelect()[i])) {
                    hdjmmx.setBz(bo.getQtjmlybeizhu());
                }

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

            // 生成核定通知书-end

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JmsbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }

    /**
     * 获取申报主表信息
     */
    private Sbzb getSbzb(JmsbBo bo, UserData user) {
        Sbzb sbzb = new Sbzb();
        // 补录标识
        sbzb.setBlbs(Constants.ZB_BLBS_FBL);
        // 办理减免税标识
        sbzb.setBljmsbs(Constants.ZB_BLJMSBS_CXXJM);
        // 备注
        sbzb.setBz(bo.getBeizhu());
        // 房屋土地部门受理号
        sbzb.setFwtdbmdm(bo.getFwtdbmslh());
        // 缴税方式
        sbzb.setJsfsdm(" ");
        // 缴税方式
        sbzb.setJsfsmc(" ");
        // 计税金额 (默认构造为0)
        sbzb.setJsje(new BigDecimal(0));
        // 用户标识
        sbzb.setYhbs(bo.getYhbs());
        // 纳税人类型代码
        if (sbzb.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            sbzb.setNsrlxdm("99");
        } else {
            sbzb.setNsrlxdm(bo.getNsrlx());
        }

        // 税率(默认构造为0)
        sbzb.setSl(new BigDecimal(0));

        // 税务机关组织机构代码
        sbzb.setSwjgzzjgdm(user.getSsdwdm());
        // 征收人员名称
        sbzb.setZsrymc(user.getYhmc());
        // 票证帐户代码
        sbzb.setPzzhdm(user.getXtsbm1());
        // 票证帐户名称
        sbzb.setPzzhmc(user.getXtsbm2());

        // 应纳税额(默认构造为0)
        sbzb.setYnse(new BigDecimal(0));
        // 状态标识
        sbzb.setZtbs(bo.getZtbs());

        return sbzb;
    }

    /**
     * 获取个人信息表信息
     */
    private List getGrxx(JmsbBo bo, UserData user, String sbbh, Timestamp now) throws
            BaseException {
        // 构造个人信息
        List nsrList = bo.getNsrList();
        List l = new ArrayList();
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx grxx = (Grxx) nsrList.get(i);
            grxx.cjr = user.getYhmc();
            grxx.cjrq = now;

            if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
                grxx.setFwjhbs(Constants.FWJHBS);
            } else {
                grxx.setFwjhbs(Constants.FFWJHBS);
            }
            grxx.lrr = user.getYhmc();
            grxx.lrrq = now;
            Debug.out("BzProcessor bo nsrmc22: " + grxx.nsrmc);
            grxx.sbbh = sbbh;
            l.add(grxx);
        }

        // 联系电话
        // grxx.setLxdh(bo.getLxdh());
        // 纳税人名称
        // grxx.setNsrmc(bo.getNsrmc());
        // 证件号码
        // grxx.setSfzjhm(bo.getSfzjhm());
        // 证件类型
        // grxx.setSfzjlx(bo.getSfzjlx());
        // 证件类型名称
        // grxx.setSfzjlxmc(bo.getSfzjlxmc());
        // 国籍代码,国籍名称
        // grxx.setGjdm(bo.getGjdm());
        // grxx.setGjmc(bo.getGjmc());

        return l;
    }

    /**
     * 得到非个人信息的vo
     *
     * @param bo
     *            JmsbBo
     * @param user
     *            UserData
     * @return Fgrxx
     */
    private Fgrxx getFgrxx(JmsbBo bo, UserData user) {
        Fgrxx fgrxx = new Fgrxx();
        if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
            fgrxx.setFwjhbs(Constants.FWJHBS);
        } else {
            fgrxx.setFwjhbs(Constants.FFWJHBS);
        }

        fgrxx.setJsjdm(bo.getJsjdm());
        fgrxx.setKhyhdm(bo.getKhyhdm());
        fgrxx.setKhyhmc(bo.getKhyhmc());
        fgrxx.setLxrxm(bo.getLxrxm());
        fgrxx.setNsrlxdm(bo.getNsrlx());
        fgrxx.setNsrlxmc(bo.getNsrlxmc());
        fgrxx.setNsrmc(bo.getNsrmc());
        fgrxx.setYhzh(bo.getYhzh());
        fgrxx.setLxdh(bo.getLxdh());
        return fgrxx;
    }

    /**
     * 获取获取土地房屋信息表tdfwxx
     */
    private Tufwxx getTufwxx(JmsbBo bo, UserData user) {
        Tufwxx tufwxx = new Tufwxx();
        try {
            // 备注
            tufwxx.setBz(bo.getBeizhu());
            // 币种代码
            tufwxx.setBzdm(bo.getBz());
            // 成交价格（人民币）
            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()));
            // 成交价格（外币）
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()));
            // 房地产项目名称
            tufwxx.setFdcxmmc(bo.getFdcxmmc());
            // 分类
            tufwxx.setFldm(bo.getFl());
            // 分类名称
            tufwxx.setFlmc(bo.getFlmc());
            // 房屋建筑面积
            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(bo.getFwjzmj()));
            // 房屋类别
            tufwxx.setFwlxdm(bo.getFwlb());
            // 汇率
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()));
            // 合同（契约）签订时间
            String date = bo.getHyqdsj();
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(date));
            // 年度
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));
            // 评估价格（人民币）
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()));
            // 土地、房屋权属转移类型
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx());
            // 土地、房屋权属转移面积
            tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(bo
                    .getTdfwqszymj()));
            // 土地、房屋座落地址
            tufwxx.setTdfwzldz(bo.getTdfwzldz());
            Debug.out("bo.getZhyrmb()" + bo.getZhyrmb());
            // 折合价格（人民币）
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()));
            // 分类名称
            tufwxx.setFlmc(bo.getFlmc());
            // 币种，房屋类别，土地房屋权属转移
            tufwxx.setBzmc(bo.getBzmc());
            tufwxx.setFwlxmc(bo.getFwlbmc());
            tufwxx.setTdfwqszymc(bo.getTdfwqszylxmc());

            tufwxx.setRjl(bo.getRjl());
            tufwxx.setTdjc(bo.getTdjc());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tufwxx;

    }

    /**
     * 获取申报主表与土地、房屋信息表的关联表SBTDFWGL信息
     */
    private Sbtdfwgl getSbtdfwgl(JmsbBo bo, UserData user) {
        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
        // 创建人
        sbtdfwgl.setCjr(user.getYhmc());
        // 录入人
        sbtdfwgl.setLrr(sbtdfwgl.getCjr());

        return sbtdfwgl;
    }

    /**
     * 得到契税减免申报表信息的vo
     *
     * @param bo
     *            JmsbBo
     * @param user
     *            UserData
     * @return Jmsbb
     */
    private List getJmsbb(JmsbBo bo, UserData user, Sbzb sbzb) {
        List al_jmsbb = new ArrayList();

        for (int i = 0; i < bo.getQsjmlbSelect().length; i++) {
            Jmsbb jmsbb = new Jmsbb();
            // 政策代码
            // jmsbb.setJmzcdm(bo.getQsjmlb());
            jmsbb.setJmzcdm(bo.getQsjmlbSelect()[i]);
            
            jmsbb.setJmxzdm(bo.getQsjmxzdm());
            // jmsbb.setJmzcdmSelect(bo.getQsjmlbSelect());
            // 打印标识
            jmsbb.setDybs(Constants.JMSBB_ZTBS_BC);
            // 状态标识
            jmsbb.setZtbs(Constants.JMSBB_ZTBS_BC);
            // 年度
            jmsbb.setNd(DateUtil.getDate().substring(0, 4));
            // 设置申报表号
            jmsbb.setSbbh(sbzb.getSbbh());
            // 设置时间
            jmsbb.setCjrq(sbzb.getCjrq());
            jmsbb.setLrrq(sbzb.getCjrq());
            // 录入人 创建人
            jmsbb.setCjr(user.getYhmc());
            jmsbb.setLrr(user.getYhmc());
            // 其它类减免的理由写入备注
            if (Constants.CXXJM_JMXMDM_QT.equals(bo.getQsjmlbSelect()[i])) {
                jmsbb.setBz(bo.getQtjmlybeizhu());
            }
            // 加入list
            al_jmsbb.add(jmsbb);

        }
        return al_jmsbb;
    }

    /**
     * 查询方法
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        JmsbblBo bo = new JmsbblBo();
        try {
            // vo.getUserData();
            bo = (JmsbblBo) vo.getData();
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
            if ((bo.getHdtzsh() != null) && (!bo.getHdtzsh().equals(""))) {
                conditions += " and f.hdtzsh = '" + bo.getHdtzsh() + "'";
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

                resultList = DAOFactory.getInstance().getSbzbDAO().queryJmsb(
                        conditions, conn, bo.ifPersonal());
            } else { // 没有选择纳税人类型
                // 先查个人
                resultList = DAOFactory.getInstance().getSbzbDAO().queryJmsb(
                        conditions, conn, true);
                // 在加上非个人
                resultList.addAll(DAOFactory.getInstance().getSbzbDAO()
                                  .queryJmsb(conditions, conn, false));
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JmsbProcessor，操作出错",
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
     * 删除符合条件的记录
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
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

                deleteSbxx(sbbh, conn);
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

        // 删除核定通知书项。
        DAOFactory.getInstance().getHdtzsDAO().deleteHdtzsBySbbh(sbbh, conn);
        Debug.out("after delete hdtzs ");

        // 根据申报表号删除契税减免申报表记录
        DAOFactory.getInstance().getJmsbbDAO().delete(sbbh, conn);
        Debug.out("after delete jmsbb ");

        // 申报主表信息
        sql = "delete from qsdb.qs_jl_sbzb where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbzb  ");
    }

    /**
     * 根据查询来自哪个页面,拼接相应的查询条件
     *
     * @param fromPage
     *            int 0：申报查询 1：不征查询 2：审核查询 3：复核查询 4: 减免申报查询 5: 减免申报审核查询
     *            6:减免申报审批查询
     * @return String
     */
    private String getYwFilter(JmsbblBo bo) {
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

            // 办理减免税标识为3的
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_CXXJM + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='') ");
            break;
        case 5:

            // 只能查询已打印申报表，但不包含审批同意的申报数据，办理减免税标识为3的
            sb.append("(a.ztbs = '2' and a.ztbs != '5') and (a.bljmsbs='"
                      + Constants.ZB_BLJMSBS_CXXJM
                      + "' and (a.dfsbbh is null or a.dfsbbh =''))");
            break;
        case 6:

            // 只能查询已打印核定通知书、审批不同意、审批同意的申报数据，办理减免税标识为3的
            sb
                    .append(
                    "(a.ztbs = '4' or a.ztbs = '6' or a.ztbs = '5') and (a.bljmsbs='"
                    + Constants.ZB_BLJMSBS_CXXJM
                    + "' and (a.dfsbbh is null or a.dfsbbh =''))");
            break;
        default:
            break;
        }
        return sb.toString();

    }

    /**
     * 实现以SbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        JmsbxxBo Bo = new JmsbxxBo();
        JmsbxxBo queryBo = (JmsbxxBo) vo.getData();
        String sbbh = queryBo.getSbbh();
        UserData ud = vo.getUserData();

        Connection conn = null;

        // 获得不包含房屋交换信息的申报bo
        Bo = (JmsbxxBo) doGetSbxxBo(sbbh);
        if (!Bo.isBZ()) { // 不征数据不取交换信息
            // 如果为房屋交换信息,则取出对方的房屋信息
            String dfSbbh = Bo.getVoSbzb().getDfsbbh();
            if (dfSbbh != null && !dfSbbh.equals("")) {
                Bo.setDfSbxxBo((FwjhxxBo) doGetFwjhxxBo(dfSbbh));
            }
        }

        try {
            conn = QSDBUtil.getConnection();
            // 如果是审批进来的需要设置审批机关、审批状态list
            // 写死
            ArrayList spjgList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                Swjgzzjg swjgzzjg = new Swjgzzjg();
                spjgList.add(swjgzzjg);
            }
            Swjgzzjg swjgzzjg = new Swjgzzjg();
            // 查询所属分局代码与名称
            swjgzzjg = CommonUtil.getSwjgzzjg(ud.getSsdwdm().substring(0, 2)
                                              + "00", conn);
            ((Swjgzzjg) spjgList.get(1))
                    .setSwjgzzjgdm(swjgzzjg.getSwjgzzjgdm());
            ((Swjgzzjg) spjgList.get(1))
                    .setSwjgzzjgmc(swjgzzjg.getSwjgzzjgmc());
            ((Swjgzzjg) spjgList.get(0)).setSwjgzzjgdm("9000");
            ((Swjgzzjg) spjgList.get(0)).setSwjgzzjgmc("市局");
            Bo.setSpjgList(spjgList);

            ArrayList spztList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                Jmsbb jmsbb_spzt = new Jmsbb();
                spztList.add(jmsbb_spzt);
            }
            ((Jmsbb) spztList.get(1)).setZtbs(Constants.ZB_ZTBS_JS_TY);
            ((Jmsbb) spztList.get(1)).setBz("同意");
            ((Jmsbb) spztList.get(0)).setZtbs(Constants.ZB_ZTBS_JS_BTY);
            ((Jmsbb) spztList.get(0)).setBz("不同意");
            Bo.setSpztList(spztList);
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

        JmsbxxBo Bo = new JmsbxxBo();

        Tufwxx voTufwxx = new Tufwxx();
        ArrayList cqList = new ArrayList();
        ArrayList gyList = new ArrayList();
        List nsrList = new ArrayList();

        ArrayList jmsbbList = new ArrayList();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // 得到主表的相关信息
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Debug.out("sbzbVo.getYhbs(): " + sbzbVo.getYhbs());
            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setVoSbzb(sbzbVo);
            Debug.out("Bo.getVoSbzb().getYhbs(): " + Bo.getVoSbzb().getYhbs());
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

            // 获取减免申报信息
            jmsbbList = (ArrayList) DAOFactory.getInstance().getJmsbbDAO()
                        .query(" where jmsbbh = '" + sbbh + "'", conn);
            Bo.setJmsbbList(jmsbbList);

            if (jmsbbList != null && jmsbbList.size() > 0) {
                // Bo.setJmsbb((Jmsbb) jmsbbList.get(0));
                Bo.setSpjg(((Jmsbb) jmsbbList.get(0)).getSpjg());
                Bo.setSprq(((Jmsbb) jmsbbList.get(0)).getSprq());
                if (sbzbVo.getZtbs().equals(Constants.ZB_ZTBS_JS_TY)
                    || sbzbVo.getZtbs().equals(Constants.ZB_ZTBS_JS_BTY)) {
                    Bo.setSpzt(sbzbVo.getZtbs());
                }

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
     * 打印减免申报表
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintJmsbb(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doPrintSbb... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            bo = (JmsbxxBo) doGet(vo);
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
                sbzbDao.update(Constants.ZB_ZTBS_DY_JMSBB, bo.getSbbh(), conn);
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
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报征收－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    // /**
     // * 审核同意
     // * 1、更新申报表标识（即审批结果）
     // * 2、更新减免申报表中的审批机关、审批日期
     // * 3、插入减免申报表
     // * @param vo
     // * VOPackage
     // */
    // private void doConfirm(VOPackage vo) throws BaseException {
    // JmsbxxBo bo = (JmsbxxBo) vo.getData();
    //
    // Connection conn = null;
    // Timestamp now = new Timestamp(System.currentTimeMillis());
    // Hdtzs hdtzs = new Hdtzs();
    // ArrayList jmsbbList = new ArrayList();
    //
    // try {
    // UserData ud = vo.getUserData();
    // String nd = DateUtil.getDate().substring(0, 4);
    // conn = QSDBUtil.getConnection();
    //
    // // update status of zb
    // DAOFactory.getInstance().getSbzbDAO().update(
    // Constants.ZB_ZTBS_JS_TY, bo.getSbbh(), conn);
    //			
    //			
    // String spjgdm = bo.getSpjg();
    // Timestamp sprq = bo.getSprq();
    // String sbbh = bo.getSbbh();
    // // 根据申报表号更新减免申报表审批结果
    // DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq, sbbh,
    // conn);
    //
    // } catch (Exception ex) {
    // // 处理失败信息:控制台 ＋ 日志
    // Debug.printException(ex);
    // ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
    // new StackMsg2StringUtil(ex, Constants.STACK_MSG_CAP)
    // .getStackMsg(), "失败");
    //
    // throw ExceptionUtil.getBaseException(ex);
    // } finally {
    // QSDBUtil.freeConnection(conn);
    // }
    // }

    // /**
     // * 审核不同意
     // * 1、更新申报表标识（即审批结果）
     // * 2、更新减免申报表中的审批机关、审批日期
     // * 3、删除减免申报表
     // * @param vo
     // * VOPackage
     // */
    // private void doReject(VOPackage vo) throws BaseException {
    // JmsbxxBo bo = (JmsbxxBo) vo.getData();
    //
    // Connection conn = null;
    // ArrayList jmsbbList = new ArrayList();
    //
    // try {
    // conn = QSDBUtil.getConnection();
    //
    // // update status of zb
    // DAOFactory.getInstance().getSbzbDAO().update(
    // Constants.ZB_ZTBS_JS_BTY, bo.getVoSbzb().getSbbh(), conn);
    //			
    // String spjgdm = bo.getSpjg();
    // Timestamp sprq = bo.getSprq();
    // String sbbh = bo.getSbbh();
    // // 根据申报表号更新减免申报表审批结果
    // DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq, sbbh,
    // conn);
    //
    // } catch (Exception ex) {
    // // 处理失败信息:控制台 ＋ 日志
    // Debug.printException(ex);
    // ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
    // new StackMsg2StringUtil(ex, Constants.STACK_MSG_CAP)
    // .getStackMsg(), "失败");
    //
    // throw ExceptionUtil.getBaseException(ex);
    // } finally {
    // QSDBUtil.freeConnection(conn);
    // }
    // }

    /**
     * 打印核定通知书 参数只有一个申报表号
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintHdtzs(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doPrintHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报数据
            bo = (JmsbxxBo) doGet(vo);
            // 只有打印申报表的情况下，才更新状态
            if (bo.getState().equals(Constants.ZB_ZTBS_DY_JMSBB)) {
                // 更新状态
                DAOFactory.getInstance().getSbzbDAO().update(
                        Constants.ZB_ZTBS_DY_HD, bo.getSbbh(), conn);
            }

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);

            hdtzsBo.setVoTufwxx(bo.getVoTufwxx());

            return hdtzsBo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 实现以JmsbxxBo的属性作为条件，查询单条记录
     *
     * @return Object
     */
    private Object doGetHdtzs(VOPackage vo) throws BaseException {
        /**
         * 通过取出来得JmsbxxBo 构造查询条件
         *
         * 1、使用JmsbxxBo构造申报主表Vo，DAO反回结果申报主表Vo
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
        JmsbxxBo sbxxBo = (JmsbxxBo) vo.getData();

        HashMap nrMap = new HashMap();

        Connection conn = null;
        try {
            Debug.out("getHdtzs sbxx sbbh: " + sbxxBo.getSbbh());
            conn = QSDBUtil.getConnection();

            // 获取核定通知书主表数据
            Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                          .getBySbbh(sbxxBo.getSbbh(), conn);
            hdtzsBo.setVoHdtzs(hdtzs);

            String condition = " where HDTZSH = '" + hdtzs.getHdtzsh() + "'";
            ArrayList jmList = (ArrayList) DAOFactory.getInstance()
                               .getHdjmmxDAO().query(condition, conn);
            for (int i = 0; i < jmList.size(); i++) {
                Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                nrMap.put(hdjmmx.getZcbh(), hdjmmx);
            }
            hdtzsBo.setJmnrMap(nrMap);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
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
     * 查询核定通知书 参数只有一个申报表号
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzs(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            return hdtzsBo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 查询核定通知书 参数只有一个防伪号码
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyFwhm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzsbyFwhm... " + bo.getFwhm());
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
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
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
        Debug.out("JmsbxxProcessor doUpdateHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("更新核定通知书主表数据防伪号码");
            // 更新核定通知书主表数据防伪号码
            DAOFactory.getInstance().getHdtzsDAO().updatefwhm(bo, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * 作废
     *
     * @param vo
     *            VOPackage
     */
    private void doCancel(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doCancel... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            // 更新状态
            sbzbDao.update(Constants.ZB_ZTBS_ZF, bo.getSbbh(), conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "作废申报失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 查询核定通知书 参数只有一个核定通知书号码
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyHdtzshm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        HdtzsBo bo = (HdtzsBo) vo.getData();
        String hdtzsh_xg = bo.getHdtzsh_xg();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzsbyHdtzshm... " + hdtzsh_xg);
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("查询该核定通知书号码的核定通知书是否存在");
            condition.append(" where HDTZSH='").append(hdtzsh_xg).append("'");
            // 查询该防伪号码的核定通知书是否存在
            hdtzsList = DAOFactory.getInstance().getHdtzsDAO().query(
                    condition.toString(), conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
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
     * 根据核定通知书号码更新核定通知书号码
     *
     * @param vo
     *            VOPackage
     */
    private void doUpdateHdtzshmByHdtzshm(VOPackage vo) throws BaseException {
        HdtzsBo bo = (HdtzsBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("更新核定通知书主表明细表的核定通知书号码");

            // 更新核定通知书主表数据防伪号码
            String hdtzsh = bo.getVoHdtzs().getHdtzsh(); // 原核定通知书号
            String hdtzsh_xg = bo.getHdtzsh_xg(); // 修改后的核定通知书号

            Debug
                    .out(
                    "JmsbxxProcessor doUpdateHdtzshmByHdtzshm...modify hdtzsh "
                    + hdtzsh_xg);

            DAOFactory.getInstance().getHdtzsDAO().updateHdtzsHm(hdtzsh,
                    hdtzsh_xg, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
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
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doRollBack... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // 获取申报主表DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            String state_cur = bo.getState();
            String state_rol = SbState.getCancelStateCode4Jmsb(state_cur, bo
                    .getVoSbzb().getBljmsbs());

            String state_cur_name = SbState.getStateName(state_cur);
            String state_rol_name = SbState.getStateName(state_rol);

            Debug.out("current state: " + state_cur_name);
            Debug.out("rollback state: " + state_rol_name);
            // 返回的状态不为空则更新状态
            if (state_rol != null && !("".equals(state_rol))) {
                sbzbDao.update(state_rol, bo.getSbbh(), conn);
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
     * 保存程序性减免审批结果 1、更新申报表标识（即审批结果） 2、更新减免申报表中的审批机关、审批日期 3、插入减免申报表
     *
     * @param vo
     *            VOPackage
     */
    private void doSavesp(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();

        Connection conn = null;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Hdtzs hdtzs = new Hdtzs();
        ArrayList sbjmList = new ArrayList();

        String jzbs = "000000"; // 写死 记帐标识初始值

        try {
            UserData ud = vo.getUserData();
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            String spjgdm = bo.getSpjg();
            String spzt = bo.getSpzt();
            Timestamp sprq = bo.getSprq();
            Timestamp cjrq = bo.getVoSbzb().getCjrq();
            String sbbh = bo.getSbbh();

            // 税种税目代码
            Szsm szsm = CommonUtil.getSZSMDM(bo.getVoTufwxx().getTdfwqszylx(),
                                             conn);

            HashMap map = new HashMap();
            // 个人
            if (bo.isPerson()) {
                // 个人计算机代码
                String jsjdm_gr = bo.getVoZcqrxx().getJsjdm();
                map.put("jsjdm", jsjdm_gr);
            }
            // 非个人
            if (!bo.isPerson()) {
                // 非个人计算机代码
                String jsjdm_fgr = bo.getVoFgrxx().getJsjdm();
                map.put("jsjdm", jsjdm_fgr);
            }

            map.put("szsmdm", szsm.szsmdm);

            map.put("cjrq", cjrq);

            // 获得对应申报减免表数据
            sbjmList = CommonUtil.getJM(conn, map);
            Debug.out("获得对应申报减免表数据成功！");
            // 如果有申报减免数据且已经记帐，则不允许修改、删除
            if (sbjmList != null && sbjmList.size() > 0) {

                Debug.out("获得对应申报减免表数据sbjmList.size()=" + sbjmList.size());

                for (int index = 0; index < sbjmList.size(); index++) {
                    Jm jm = new Jm();
                    jm = (Jm) sbjmList.get(index);

                    Debug.out("获得对应申报减免表数据jm.getJzbz()=" + jm.getJzbz());

                    if (!jm.getJzbz().equals(jzbs)) {
                        throw new ApplicationException("该笔减免申报数据，计会已记帐，不允许修改！");
                    }
                }
            }

            Debug.out("判断记帐成功！");

            Debug.out("审批状态spzt=" + spzt);

            // 审批同意，需要插入申报减免表
            if (spzt != null && spzt.equals(Constants.ZB_ZTBS_JS_TY)) {
                Debug.out("审批同意，需要插入申报减免表！");
                // 获取减免项目代码
                String jmxmdm = "";
                //增加减免性质代码
                String jmxzdm = "";
                if (bo.getJmsbbList() != null || bo.getJmsbbList().size() > 0) {
                    for (int i = 0; i < bo.getJmsbbList().size(); i++) {
                        Jmsbb jmsbb = (Jmsbb) bo.getJmsbbList().get(i);
                        jmxmdm = jmsbb.getJmzcdm();
                        jmxzdm = jmsbb.getJmxzdm();
                    }
                }

                if (jmxmdm == null || "".equals(jmxmdm)) {
                    jmxmdm = Constants.JMXMDM;
                }
                
                if(jmxzdm == null || "".equals(jmxzdm)){
                    PreparedStatement ps = null;
                    ResultSet rs = null;

                    try {
                        ps = conn.prepareStatement("select jmxzdm from dmdb.sf_dm_qsjmlb where qsjmlbdm = '"+jmxmdm+"'");
                        rs = ps.executeQuery();
                        if (rs.next()) {
                        	jmxzdm = rs.getString(1);
                        }
                    } catch (Exception e) {
                    } finally {
                    	if(rs!=null){
                    		rs.close();
                    	}
                    	if(ps!=null){
                    		ps.close();
                    	}
                    }
                }
                
    

                // 获取核定通知书主表数据
                hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                        .getBySbbh(bo.getSbbh(), conn);

                map.put("jsje", hdtzs.getJzqs());
                map.put("jmse", hdtzs.getJzqs());
                map.put("lrr", ud.getYhid());
                map.put("jmxmdm", jmxmdm); // 减免项目代码
                map.put("jmxzdm", jmxzdm); // 减免性质代码
                map.put("skssjsrq", bo.getVoTufwxx().getHtqdsj());
                map.put("skssksrq", bo.getVoTufwxx().getHtqdsj());
                
                if (!CommonUtil.insertSBJM(map)) {
                    throw new ApplicationException("插入计会用申报减免表的时候报错！");
                }
                Debug.out("插入减免申报成功！");
            }

            // 审批不同意，需要删除申报减免表
            // 已记帐则不允许修改
            if (spzt != null && spzt.equals(Constants.ZB_ZTBS_JS_BTY)) {
                Debug.out("审批不同意，需要删除申报减免表！");
                // 删除申报减免表的数据
                if (!CommonUtil.deleteSBJM(map)) {
                    throw new ApplicationException("无法删除申报减免表的数据，调用接口出错！");
                }
                Debug.out("撤销减免申报成功！");

            }

            // update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(spzt, sbbh, conn);

            // 根据申报表号更新减免申报表审批结果
            DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq,
                    sbbh, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税减免申报－JmsbxxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}
