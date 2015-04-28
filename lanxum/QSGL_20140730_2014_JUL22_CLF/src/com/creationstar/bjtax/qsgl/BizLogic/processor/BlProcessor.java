package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
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
 */
public class BlProcessor extends CommonProcessor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.BL_QUERY_JKS:
            result = doQueryJks(vo); //查询补录的申报缴款书
            break;

        case ActionType.BL_CHECK_SBJKS:
            result = doCheckSbJks(vo); //通过申报表号，和缴款凭证号，核对两者的应纳税额是否一致
            break;

        case ActionType.BL_CHECK_HZJKS:
            result = doCheckHzJks(vo); //通过申报表号，和缴款凭证号，核对两者的应纳税额是否一致
            break;

        case ActionType.BL_CREATECONNECT_SBJKS:
            result = doCreateConnectSbJks(vo);
            break;

        case ActionType.BL_CREATECONNECT_HZJKS:
            result = doCreateConnectHzJks(vo);
            break;

        case ActionType.GET: //撤销补录时根据用户输入的缴款凭证号得到缴款书的数据
            result = doGetJks(vo);
            break;

        case ActionType.BL_REMOVECONNECT: //撤销补录，也就是断开关联
            removeConnect(vo);
            break;

        default:
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }


    /**
     * 查询补录的缴款书，将补录的缴款书放在ArrayList中返回给前台
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryJks(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集
        QueryBlJksBo qbo = (QueryBlJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            StringBuffer baseCondition = new StringBuffer("");
            baseCondition.append(" AND a.jsjdm = '").append(jsjdm).append("' ")
                    .append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM)
                    .append("' AND a.SWJGZZJGDM = '").append(ud.ssdwdm).append(
                    "' ");
            //得到前台页面的查询条件，jsje代表计税金额，sjse代表实缴税额
            String sjse = qbo.getSjse();
            String jsje = qbo.getJsje();

            StringBuffer condition = baseCondition;
            //将用户输入的查询条件加入到where语句中
            if (sjse != null && !sjse.equals("")) {
                condition.append(" AND b.sjse = ").append(sjse);
            }
            if (jsje != null && !jsje.equals("")) {
                condition.append(" AND b.jsje = ").append(jsje);
            }

            //

            //在申报缴款主表中查询缴款书
            DAOFactory.getInstance().getBlQueryJksDAO().queryJks(
                    condition, resultList, conn);
            //在调帐的表中查询缴款书
            //如果用户没有输入查询条件
            if ((sjse == null || sjse.equals(""))) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryDzJks(
                        baseCondition, resultList, conn);
            } else {
                DAOFactory.getInstance().getBlQueryJksDAO().queryDzJks(
                        baseCondition, sjse, resultList, conn);
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报补录－BlProcessor，查询补录缴款书操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //由于申报缴款主表和申报缴款明细表中的数据是1对多的情况，为了使用方便，需要重新组织
        //查询结果有1条主表数据对应多条子表数据的情况，在bo中主表数据放在一个主表vo中，对应的子表数据放在ArrayList中
        //以下代码是重新组织查询的数据，把组织好的bo放到ArrayList里面返回

        ArrayList returnList = new ArrayList(); //重新组织数据后，返回给前台的结果集
        ArrayList tempList = new ArrayList(); //临时存放查询结果bo中的明细vo
        QueryBlJksBo resultBo = new QueryBlJksBo(); //最终返回给前台的查询结果集中的每一条记录
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            QueryBlJksBo bo = new QueryBlJksBo();
            bo = (QueryBlJksBo) iter.next();

            if (tempList.isEmpty()) { //如果返回给前台的查询结果为空，说明是第一条数据
                resultBo = bo;
                //resultBo.setSbjkzb(bo.getSbjkzb());
                //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                if (bo.getSbjkmx().getKssl() == null) {
                    resultBo.getSbjkzb().setKssl(0);
                } else {
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                }
                //补全缴款书的数据，主要是那些名称
                getSbjkzbInfo(resultBo.getSbjkzb(), ud, conn);
                getSzsmmc(bo.getSbjkmx()); //得到项目名称
                tempList.add(bo.getSbjkmx()); //在查询结果bo中加入相应的子表vo
                resultBo.setMxList(tempList); //付给查询结果bo中存储子表结果的ArrayList

                returnList.add(resultBo); //将重新组织的查询结果放到返回给前台的ArrayList中
            } else {
                //如果本条查询结果的缴款凭证号、计算机代码与上条相同
                //说明主表数据相同只是明细表不同，也就是1条主表数据对应多条明细表数据的情况
                //则把该条的明细数据放到相同的resultBo的明细ArrayList中
                if (bo.getSbjkzb().getJkpzh().equals(resultBo.getSbjkzb().
                        getJkpzh()) &&
                    bo.getSbjkzb().getJsjdm().equals(resultBo.getSbjkzb().
                        getJsjdm())) {
                    getSzsmmc(bo.getSbjkmx()); //得到项目名称
                    tempList.add(bo.getSbjkmx());
                    //将申报缴款明细表中的课税数量相加，付给申报缴款主表vo，方便查询页面显示用
                    int kssl = 0;
                    if (bo.getSbjkmx().getKssl() != null) {
                        kssl = bo.getSbjkmx().getKssl().intValue();
                    }
                    kssl = resultBo.getSbjkzb().getKssl() +
                           kssl;
                    resultBo.getSbjkzb().setKssl(kssl);
                    resultBo.setMxList(tempList);
                } else {
                    //因为是一条不同的数据，所以重新构造resultBo和tempList
                    resultBo = new QueryBlJksBo();
                    tempList = new ArrayList();

                    resultBo = bo;
                    //resultBo.setSbjkzb(bo.getSbjkzb());
                    //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                    if (bo.getSbjkmx().getKssl() == null) {
                        resultBo.getSbjkzb().setKssl(0);
                    } else {
                        resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                intValue());
                    }

                    //补全缴款书的数据，主要是那些名称
                    getSbjkzbInfo(resultBo.getSbjkzb(), ud, conn);
                    getSzsmmc(bo.getSbjkmx()); //得到项目名称
                    tempList.add(bo.getSbjkmx());
                    resultBo.setMxList(tempList);

                    returnList.add(resultBo); //将重新组织的查询结果放到返回给前台的ArrayList中
                }
            }
        }

        return returnList;
    }


    private Object doGetJks(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集
        UserData ud = vo.getUserData();

        QueryBlJksBo queryBo = (QueryBlJksBo) vo.getData();
        String jkpzh = queryBo.getJkpzh();
        String type = queryBo.getType();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            StringBuffer condition = new StringBuffer("");
            condition.append(" AND a.jkpzh = '").append(jkpzh).append("' ")
                    .append(" AND a.jsjdm = '").append(jsjdm).append("' ")
                    .append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM)
//                .append("' ").append(" AND a.qxdm = '")   //modified by zhaobo 20050630
//                .append(CommonUtil.getQxdm(ud)).append("' ")
                    .append("' ")
                    .append(" AND a.SWJGZZJGDM = '").append(ud.ssdwdm).append(
                    "' ");
            //

            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryBlJks(
                        condition, resultList, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryBlDzJks(
                        condition, resultList, conn);
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报补录－BlProcessor，查询补录缴款书操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //由于申报缴款主表和申报缴款明细表中的数据是1对多的情况，为了使用方便，需要重新组织
        //查询结果有1条主表数据对应多条子表数据的情况，在bo中主表数据放在一个主表vo中，对应的子表数据放在ArrayList中
        //以下代码是重新组织查询的数据，把组织好的bo放到ArrayList里面返回
        if (resultList.isEmpty()) {
            return null;
        }

        ArrayList tempList = new ArrayList(); //临时存放查询结果bo中的明细vo
        QueryBlJksBo resultBo = new QueryBlJksBo(); //最终返回给前台的查询结果集中的每一条记录
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            QueryBlJksBo bo = new QueryBlJksBo();
            bo = (QueryBlJksBo) iter.next();

            if (tempList.isEmpty()) { //如果返回给前台的查询结果为空，说明是第一条数据
                resultBo = bo;
                //resultBo.setSbjkzb(bo.getSbjkzb());
                //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().intValue());
                tempList.add(bo.getSbjkmx()); //在查询结果bo中加入相应的子表vo
                resultBo.setMxList(tempList); //付给查询结果bo中存储子表结果的ArrayList
            } else {
                //如果本条查询结果的缴款凭证号、计算机代码与上条相同
                //说明主表数据相同只是明细表不同，也就是1条主表数据对应多条明细表数据的情况
                //则把该条的明细数据放到相同的resultBo的明细ArrayList中
                if (bo.getSbjkzb().getJkpzh().equals(resultBo.getSbjkzb().
                        getJkpzh()) &&
                    bo.getSbjkzb().getJsjdm().equals(resultBo.getSbjkzb().
                        getJsjdm())) {
                    tempList.add(bo.getSbjkmx());
                    //将申报缴款明细表中的课税数量相加，付给申报缴款主表vo，方便查询页面显示用
                    int kssl = resultBo.getSbjkzb().getKssl() +
                               bo.getSbjkmx().getKssl().intValue();
                    resultBo.getSbjkzb().setKssl(kssl);
                    resultBo.setMxList(tempList);
                } else {
                    //因为是一条不同的数据，所以重新构造resultBo和tempList
                    resultBo = new QueryBlJksBo();
                    tempList = new ArrayList();

                    resultBo = bo;
                    //resultBo.setSbjkzb(bo.getSbjkzb());
                    //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                    tempList.add(bo.getSbjkmx());
                    resultBo.setMxList(tempList);
                }
            }
        }
        return resultBo;
    }


    /**
     * 根据前台传过来的申报表号和缴款凭证号，比对两者的应纳税额是否一致，一致返回true
     * @param vo VOPackage
     * @return Boolean
     * @throws BaseException
     */
    private Boolean doCheckSbJks(VOPackage vo) throws BaseException {
        Boolean legal = new Boolean(false);

        QueryBlJksBo bo = (QueryBlJksBo) vo.getData();
        UserData ud = vo.getUserData();

        String jkpzh = bo.getJkpzh();
        String sbbh = bo.getSbbh();
        String type = bo.getType();
        String zbxh = bo.getZbxh();

        Connection conn = null;
        try {
            if (sbbh.charAt(0) == 'B' || sbbh.charAt(0) == 'b') {
                throw new ApplicationException("申报表" + sbbh +
                                               "属于不征的申报，不能进行补录操作！");
            }

            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //通过构造申报主表，赋值申报表号，获得完整的申报主表数据
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            if (sbzb.getSbbh() == null || sbzb.getSbbh().equals("")) {
                throw new ApplicationException("您输入的申报表" + sbbh + "在数据库中不存在！");
            }

            if (Constants.ZB_ZTBS_ZF.equals(sbzb.getZtbs())) {
                throw new ApplicationException("您输入的申报表" + sbbh + "已作废！");
            }

            //一定要排除已经生成过缴款书的
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("该笔申报已经打印缴款书了，不能够再次生成缴款书！");
            }

            //比对申报表的权属转移类型代码和缴款书的税种税目代码是否一致
            ArrayList sbjkmxList = null;
            if (Constants.PT_JKS.equals(type)) {
                sbjkmxList = doGetSbjkmx(jkpzh, jsjdm, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                sbjkmxList = doGetDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
            }
            if (sbjkmxList.isEmpty()) {
                throw new ApplicationException("没有找到缴款凭证号为：" + jkpzh +
                                               "的缴款书！");
            }
            //得到申报表权属转移类型对应的税种税目代码
            //首先得到土地房屋申报的基本信息
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            if (tfxx == null) {
                throw new ApplicationException("没有找到申报表：" + sbbh +
                                               "所对应的土地房屋基本信息！");
            }
            //通过权属转移类型代码得到税种税目代码
            String szsmdm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn).
                            getSzsmdm();
            //得到缴款书明细表中保存的税种税目代码
            String jks_szsmdm = ((Sbjkmx) sbjkmxList.get(0)).getSzsmdm();
            if (!jks_szsmdm.equals(szsmdm)) {
                throw new ApplicationException("申报表：" + sbbh +
                                               "所对应的税种税目与缴款书" + jkpzh +
                                               "保存的税种税目不相符！");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            //根据申报主表的数据，算出实缴金额
            double sb_ynse = hdbo.getYnse().doubleValue();
            //数据库里面缴款书中存储的实缴税额
            double jks_ynse = 0.0d;

            Sbjkzb sbjkzb = new Sbjkzb();
            sbjkzb.setJkpzh(jkpzh);
            sbjkzb.setJsjdm(jsjdm);
            //如果是普通缴款书，则去申报缴款主表里面查
            if (Constants.PT_JKS.equals(type)) {
                sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                        sbjkzb, conn);
                if (sbjkzb != null) {
                    jks_ynse = ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }

                //如果是调帐的缴款书，则去调帐的里面查
            } else if (Constants.DZ_JKS.equals(type)) {
                ArrayList dzList = (ArrayList) DAOFactory.getInstance().
                                   getBlQueryJksDAO().getDzJks(
                                           sbjkzb, conn);
                for (Iterator iter = dzList.iterator(); iter.hasNext(); ) {
                    sbjkzb = (Sbjkzb) iter.next();
                    jks_ynse += ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }
            }

            //如果金额相符
            if (sb_ynse == jks_ynse) {
                legal = Boolean.TRUE;
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报补录－BlProcessor，补录申报缴款书操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return legal;
    }

    /**
     * 根据前台传过来的存放申报表号的ArrayList和缴款凭证号，比对两者的应纳税额是否一致，一致返回true
     * @param vo VOPackage
     * @return Boolean
     * @throws BaseException
     */
    private Boolean doCheckHzJks(VOPackage vo) throws BaseException {
        Boolean legal = new Boolean(false);
        UserData ud = vo.getUserData();

        ArrayList blList = (ArrayList) vo.getData();
        String jkpzh = ((BlJksBo) blList.get(0)).getJkpzh(); //前台传过来的缴款凭证号（汇总生成的），跟传过来的sbbh对应
        String type = ((BlJksBo) blList.get(0)).getType(); //缴款书的类型，普通的还是调帐的

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //首先对用户熟入的申报表号和完税证号的关系进行校验
            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                BlJksBo bo = (BlJksBo) iter.next();
                //用户输入的完税证主键
                String userWszh = bo.getWszh();
                String userNdzb = bo.getNdzb();
                String userPzzldm = bo.getPzzldm();

                //根据用户输入的申报表号得到申报表的数据
                String sbbh = bo.getSbbh();
                if (sbbh.charAt(0) == 'B' || sbbh.charAt(0) == 'b') {
                    throw new ApplicationException("您输入的申报表" + sbbh +
                            "属于不征的申报，不能进行补录操作！");
                }
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(sbbh);
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);

                if (sbzb.getSbbh() == null || sbzb.getSbbh().equals("")) {
                    throw new ApplicationException("您输入的申报表" + sbbh +
                            "在数据库中不存在！");
                }

                if (Constants.ZB_ZTBS_ZF.equals(sbzb.getZtbs())) {
                    throw new ApplicationException("您输入的申报表" + sbbh + "已作废！");
                }

                //判断是否该申报表是否已经生成了完税证
                //如果已经生成了完税证，判断该完税证号与用户录入的完税证号是否一致
                if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                    StringBuffer condition = new StringBuffer(" sbbh = '");
                    condition.append(sbbh).append("' ");
                    Qswszz qswszz = (Qswszz) DAOFactory.getInstance().
                                    getBlQueryJksDAO()
                                    .getQswszz(condition.toString(), conn);
                    //如果用户输入的完税证号与数据库中的不符
                    if (qswszz == null || !userWszh.equals(qswszz.getWszh())) {
                        throw new ApplicationException("申报表号为" + sbbh +
                                "的申报表在数据库中对应的完税证号与您输入的完税证号" + userWszh + "不符!");
                    }
                    //如果完税证的处理标记代码是已申报，则更新为已申报，只有已申报的状态才能汇总
                    if (Constants.WSZ_CLBJDM_YSB.equals(qswszz.getClbjdm())) {
                        StringBuffer sql = new StringBuffer(
                                "UPDATE sbdb.sb_jl_qswszz a ");
                        sql.append(" SET a.clbjdm = '").append(Constants.
                                WSZ_CLBJDM_YWS)
                                .append("' WHERE a.wszh = '").append(qswszz.
                                getWszh())
                                .append("' AND ndzb = '").append(qswszz.getNdzb())
                                .append("' AND pzzldm = '").append(qswszz.
                                getPzzldm())
                                .append("' ");
                        DAOFactory.getInstance().getQswszzDAO().update(sql.
                                toString(), conn);
                    }
                } else { //如果没有生成完税证，则判断用户录入的完税证号是否在数据库中已经存在了
                    StringBuffer condition = new StringBuffer(" wszh = '");
                    condition.append(userWszh).append("' AND ndzb = '")
                            .append(userNdzb).append("' AND pzzldm = '")
                            .append(userPzzldm).append("' ");
                    //如果用户输入的完税证号在数据库中已存在
                    Qswszz qswszz = (Qswszz) DAOFactory.getInstance().
                                    getBlQueryJksDAO()
                                    .getQswszz(condition.toString(), conn);
                    if (qswszz != null) {
                        throw new ApplicationException("您所输入的完税证号" + userWszh +
                                "在数据库中已存在");
                    }
                }
            }

            double total_ynse = 0.0d; //申报表的应纳税额的总和
            double jks_ynse = 0.0d; //数据库中缴款书存储的实缴税额
            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                BlJksBo bo = (BlJksBo) iter.next();
                String sbbh = bo.getSbbh();

                JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
                //根据申报主表的数据，算出实缴金额
                double sb_ynse = hdbo.getYnse().doubleValue();
                total_ynse += sb_ynse;
            }

            Sbjkzb sbjkzb = new Sbjkzb();
            sbjkzb.setJkpzh(jkpzh);
            sbjkzb.setJsjdm(jsjdm);
            //如果是普通缴款书1，则去申报缴款主表里面查
            //如果是调帐缴款书2，则去申报缴款主表里面查
            Debug.out("jks type==" + type + " jkpzh==" + jkpzh + " jsjdm==" +
                      jsjdm);

            if (Constants.PT_JKS.equals(type)) {
                sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                        sbjkzb, conn);
                if (sbjkzb != null) {
                    jks_ynse = ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                } else {
                    throw new ApplicationException("没有找到缴款凭证号为：" + jkpzh +
                            "的缴款书！");
                }
                //如果是调帐的缴款书，则去调帐的里面查
            } else if (Constants.DZ_JKS.equals(type)) {
                ArrayList dzList = (ArrayList) DAOFactory.getInstance().
                                   getBlQueryJksDAO().getDzJks(
                                           sbjkzb, conn);
                if (dzList.isEmpty()) {
                    throw new ApplicationException("没有找到缴款凭证号为：" + jkpzh +
                            "的缴款书！");
                }
                for (Iterator iter = dzList.iterator(); iter.hasNext(); ) {
                    sbjkzb = (Sbjkzb) iter.next();
                    jks_ynse += ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }
            }

            total_ynse = DataConvert.round(total_ynse, 2);
            jks_ynse = DataConvert.round(jks_ynse, 2);
            Debug.out("总金额 total_ynse" + total_ynse + "元");
            Debug.out("总金额 jks_ynse" + jks_ynse + "元");

            if (total_ynse == jks_ynse) {
                legal = Boolean.TRUE;
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报补录－BlProcessor，补录汇总缴款书操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return legal;
    }


    private ArrayList doCreateConnectSbJks(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData();

        BlJksBo bo = (BlJksBo) vo.getData();
        String jkpzh = bo.getJkpzh();
        String zbxh = bo.getZbxh();
        String sbbh = bo.getSbbh();
        String type = bo.getType();
        String zwbs = bo.getZwbs();

        ArrayList hdtzsList = new ArrayList();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            //判断是否有拆迁
            ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                   .queryBySbbh(sbzb.getSbbh(), conn);
            //如果有拆迁信息
            if (!jsblcqList.isEmpty()) {
                //获取核定通知书主表数据
                Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                              getBySbbh(sbbh, conn);
                //检查是否生成了核定通知书，如果没有则生成核定通知书并insert到数据库中
                if (hdtzs == null) {
                    hdtzs = doCreateHdtzs(ud, sbzb, conn);
                    hdtzsList.add(hdtzs);
                }
            }

            //将补录后的缴款书的zwbs的第11位从0—〉1
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '1';
            zwbs = String.copyValueOf(zwbschr);
            //将申报表号反添到申报缴款主表中
            //如果是普通缴款书，则更新申报缴款主表
            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        sbbh, jsjdm, zwbs, conn);
                //如果是调帐的缴款书，则更新调帐表
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        sbbh, jsjdm, zbxh, zwbs, conn);
            }
            //更新补录的申报主板的状态为已入库
            DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "契税申报补录－BlProcessor，补录申报缴款书建立关联时出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return hdtzsList;
    }


    private ArrayList doCreateConnectHzJks(VOPackage vo) throws BaseException {
        ArrayList blList = (ArrayList) vo.getData();
        UserData ud = vo.getUserData();

        ArrayList hdtzsList = new ArrayList();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            ArrayList sbbhList = new ArrayList();
            ArrayList wszhList = new ArrayList();

            BlJksBo bo = (BlJksBo) blList.get(0);
            String jkpzh = bo.getJkpzh();
            String type = bo.getType();
            String zbxh = bo.getZbxh();
            String zwbs = bo.getZwbs();

            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                bo = (BlJksBo) iter.next();
                String sbbh = bo.getSbbh();

                //通过构造申报主表，赋值申报表号，获得完整的申报主表数据
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(sbbh);
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);

                //判断是否有拆迁
                ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                       .queryBySbbh(sbzb.getSbbh(), conn);
                //如果有拆迁信息
                if (!jsblcqList.isEmpty()) {
                    //获取核定通知书主表数据
                    Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                                  getBySbbh(sbbh, conn);
                    //检查是否生成了核定通知书，如果没有则生成核定通知书并insert到数据库中
                    if (hdtzs == null) {
                        hdtzs = doCreateHdtzs(ud, sbzb, conn);
                        hdtzsList.add(hdtzs);
                    }
                }

                Qswszz qswszz = new Qswszz();
                qswszz.setWszh(bo.getWszh());
                qswszz.setNdzb(bo.getNdzb());
                qswszz.setPzzldm(bo.getPzzldm());
                qswszz.setSbbh(sbbh);

                //判断是否该申报表是否已经生成了完税证
                if (!Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                    doCreateQswszz(sbzb, qswszz, ud, conn);
                }

                sbbhList.add(sbbh);
                wszhList.add(bo.getWszh());
            }

            ArrayList hzMxList = doHzWsz(sbbhList, conn);
            ArrayList sbjkmxList = null;
            if (Constants.PT_JKS.equals(type)) {
                sbjkmxList = doGetSbjkmx(jkpzh, jsjdm, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                sbjkmxList = doGetDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
            }
            //把汇总出来的申报缴款明细数据和已经补录的申报缴款明细数据进行比对
            int size = sbjkmxList.size();
            if (size != hzMxList.size()) {
                throw new ApplicationException("汇总出来的税种税目的数量与补录的缴款书不相符！");
            }
            System.out.println("size : " + size);
            for (int i = 0; i < size; i++) {
                Sbjkmx hzMx = (Sbjkmx) hzMxList.get(i);
                Sbjkmx sbjkMx = (Sbjkmx) sbjkmxList.get(i);

                if (hzMx.getSzsmdm().equals(sbjkMx.getSzsmdm())) {
                    if (hzMx.getSjse().doubleValue() !=
                        sbjkMx.getSjse().doubleValue()) {
                        System.out.println("汇总出来的税种税目为" + hzMx.getSzsmdm() +
                                           "的金额： " + hzMx.getSjse());
                        System.out.println("缴款书中税种税目为" + sbjkMx.getSzsmdm() +
                                           "的金额： " + sbjkMx.getSjse());
                        throw new ApplicationException(
                                "汇总出来的税种税目中的实缴金额与补录的缴款书不相符！");
                    }
                } else {
                    throw new ApplicationException("汇总出来的税种税目名称与补录的缴款书不相符！");
                }
            }

            String sbhzdh = doCreateQswszhz(ud, jkpzh, hzMxList, conn);
            //汇总完税证后返填处理标记代码、申报汇总单号给完税证主表
            StringBuffer condition = new StringBuffer("");
            for (Iterator iter = wszhList.iterator(); iter.hasNext(); ) {
                String wszh = (String) iter.next();
                condition.append("'").append(wszh).append("',");
            }
            int end = condition.length() - 1; //找到最后一个字符的位置

            StringBuffer sql = new StringBuffer("UPDATE sbdb.sb_jl_qswszz a ");
            sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ)
                    .append("', a.sbhzdh = '")
                    .append(sbhzdh)
                    .append("' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                    .append(" a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS)
                    .append("' AND a.wszh in (").append(condition.substring(0,
                    end))
                    .append(") ");
            DAOFactory.getInstance().getQswszzDAO().update(sql.toString(), conn);

            //将补录后的缴款书的zwbs的第11位从0—〉1
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '1';
            zwbs = String.copyValueOf(zwbschr);
            //将申报表号反添到申报缴款主表中
            //如果是普通缴款书，则更新申报缴款主表
            //
            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        null, jsjdm, zwbs, conn);
                //如果是调帐的缴款书，则更新调帐表
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        null, jsjdm, zbxh, zwbs, conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "契税申报补录－BlProcessor，补录申报缴款书建立关联时出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return hdtzsList;
    }

    /**
     * 创建完税证
     * @param sbzb Sbzb    申报主表vo
     * @param wszZb Qswszz    完税证主表vo
     * @param ud UserData     UserData
     * @param conn Connection  数据库连接
     * @throws BaseException
     */
    private void doCreateQswszz(Sbzb sbzb, Qswszz wszZb, UserData ud,
                                Connection conn) throws BaseException {
        Qswszmx wszMx = null; //构造完税证明细的数据

        try {
            Timestamp now = CommonUtil.getDBtime(conn);

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            Debug.out("取得房屋信息的完整信息....");
            if (tufwxx == null) {
                throw new ApplicationException("房屋、土地的基本信息不能为空！");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(sbzb.getSbbh(), conn);
            //取得减免税金额
            BigDecimal jmszje = hdbo.getJmzje();
            //取得合计计税金额
            BigDecimal total_je = hdbo.getYnse();

            //开始构造完税证数据
            wszZb.setCjr(ud.getYhmc());
            wszZb.setCjrq(now);
            wszZb.setLrr(ud.getYhid()); //为了以后汇总的时候的汇总依据，按照个人汇总的就是用这个字段
            wszZb.setLrrq(now);
            wszZb.setNd(DateUtil.getDate().substring(0, 4));
            wszZb.setClbjdm(Constants.WSZ_CLBJDM_YWS);
            wszZb.setSwjgzzjgdm(ud.getSsdwdm());
            wszZb.setSwjgzzjgmc(ud.getSsdwmc());
            wszZb.setJsjdm(CommonUtil.getWszJsjdm(ud, conn)); //获得完税证主表的税务机关的计算机代码
            wszZb.setFsdm(Constants.WSZ_FSDM); //只有一种方式代码——到税务机关办理方式
            wszZb.setSwjgzzjgmc(ud.ssdwmc); //征收机关名称
            wszZb.setSbbh(sbzb.sbbh); //添加申报表号

            //继续构造完税证主表对象
            wszZb.setJsfsdm(sbzb.getJsfsdm()); //缴税方式代码
            wszZb.setJsfsmc(sbzb.getJsfsmc()); //缴税名称
            //获得票证帐户的完整信息
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            wszZb.setZsddm(ud.xtsbm1); //票证帐户代码
            wszZb.setZsdmc(zh.zhmc); //征收点名称
            wszZb.setFdcwz(tufwxx.getTdfwzldz()); //房地产位置
            wszZb.setHtclrq(tufwxx.getHtqdsj()); //房屋合同签订时间
            wszZb.setYqts(new BigDecimal(0)); //逾期天数
            wszZb.setZnjzje(new BigDecimal(0)); //滞纳金总金额
            wszZb.setJsje(jmszje); //获得减免税总额
            wszZb.setHjsjje(total_je); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”

            //区分个人还是非个人（企业）生成完税证
            //如果是个人购买的房产
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(grxx.getJsjdm()); //纳税人计算机代码
//                    ZRRJBSJ grjb = CommonUtil.getGrJBSJ(grxx);
                wszZb.setDjzclxdm(Constants.WSZ_DJZCLX); //登记注册类型——登记注册类型
                wszZb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //登记注册类型——登记注册类型
                wszZb.setFsmc(Constants.WSZ_FSMC); //申报办理方式名称--常量：到税务机关办理
                wszZb.setZjhm(grxx.getSfzjhm()); //证件号码
                wszZb.setZjlxdm(grxx.getSfzjlx()); //证件类型代码
                wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //获得国家标准行业代码
                wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //获得国家标准行业名称
                wszZb.setNsrmc(grxx.getNsrmc()); //纳税人名称
                wszZb.setNsrdm(grxx.sfzjhm); //纳税人代码
                wszZb.setZjlxmc(grxx.sfzjlxmc); //证件类型名称
                Debug.out("取得个人的登记信息....");
            }
            //如果是非个人信息，取得国家行业代码等不同
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(fgrxx.getJsjdm());
                SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(fgrxx.jsjdm);
                wszZb.setDjzclxdm(swdjJbsj.getDjzclxdm());
                wszZb.setNsrjsjdm(fgrxx.jsjdm);
                wszZb.setSwjgzzjgdm2(swdjJbsj.getSwjgzzjgdm()); //将非个人的所属税务机关代码插入
                wszZb.setGjbzhydm(swdjJbsj.getGjbzhydm());
                wszZb.setNsrmc(fgrxx.getNsrmc()); //纳税人名称
                wszZb.setNsrdm(fgrxx.jsjdm);
                Debug.out("取得非个人的完整信息....");
            }

            //插入明细表
            //初始化明细
            wszMx = new Qswszmx();

            //设置其它值，表结构有问题，等修改后需修改此处的信息
            wszMx.setWszh(wszZb.getWszh());
            wszMx.setNdzb(wszZb.getNdzb()); //年度字别
            wszMx.setPzzldm(wszZb.getPzzldm());
            wszMx.setSzdm(Constants.WSZ_QSSZDM); //默认税种代码为“契税74”
            wszMx.setSzmc(Constants.WSZ_QSSZMC); //默认税种代码为“契税74”

            wszMx.setCjrq(now); //创建日期
            wszMx.setLrrq(now); //录入日期
            wszMx.setLrr(ud.getYhid());
            wszMx.setCjr(ud.getYhmc());
//          wszMx.setQxdm(qxdm); //区县代码
            wszMx.setJsjdm(wszZb.getJsjdm()); //计算机代码
            wszMx.setSwjgzzjgdm(wszZb.getSwjgzzjgdm());
            wszMx.setJzbz(Constants.WSZ_JZBZ_DEFAULT); //记账标志，默认六个0
            //设置年度
            wszMx.setNd(wszZb.getNd());
            //获得预算级次
            wszMx.setYsjcdm(Constants.YSJCDM_DF); //给定的预算级次代码，21 地方级
            wszMx.setYsjcmc(Constants.YSJCDM_DF_MC); //给定的预算级次代码，21 地方级
            wszMx.setSkssjsrq(wszZb.getHtclrq()); //税款所属日期
            wszMx.setSkssksrq(wszZb.getHtclrq()); //税款所属日期

            if (tufwxx.getTdfwqszymj() != null &&
                tufwxx.getTdfwqszymj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getTdfwqszymj()); //权属转移面积
            } else if (tufwxx.getFwjzmj() != null &&
                       tufwxx.getFwjzmj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getFwjzmj()); //权属转移面积
            } else {
                wszMx.setQszymj(new BigDecimal(0));
            }
            //end added

            wszMx.setSl(sbzb.getSl()); //税率
            Szsm szsm = CommonUtil.getSZSMDM(tufwxx.getTdfwqszylx(), conn);
            wszMx.setSzsmdm(szsm.szsmdm); //获得税种税目代码
            wszMx.setSzsmmc(szsm.szsmmc); //获得税种税目名称
            Yskm yskm = CommonUtil.getYskm(wszMx.getSzdm(), conn);
            if (yskm == null) {
                throw new ApplicationException("没有预算科目代码！");
            }
            //获得预算科目
            wszMx.setYskmdm(yskm.yskmdm);
            wszMx.setYskmmc(yskm.yskmmc);
            wszMx.setYjhkc(wszZb.getJsje()); //由于明细中只有一条数据，所以已缴或扣除字段等于主表的减税金额字段
            wszMx.setJsje(hdbo.getJsyj()); //计税金额等于计税依据
            wszMx.setSjse(hdbo.getYnse()); //实纳税额就是应纳税额（复核及收款中的项）
            wszMx.setJzbz("000001");

            //更新主表数据
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("插入完税证主表成功....完税证号为：" + wszZb.getWszh());

                DAOFactory.getInstance().getQswszmxDAO().insert(wszMx, conn);
                Debug.out("插入完税证明细成功....");

                //更新申报主表状态位
                String ztbs = Constants.ZB_ZTBS_YRK;
                DAOFactory.getInstance().getSbzbDAO().update(ztbs, wszZb.sbbh,
                        conn);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("补录时保存完税证到数据库失败！");
            }

            //符合减免办理条件的，并且已经复合了的申报表需要插入减免的申报信息
            //以备作减免的统计和记账
//          if (Constants.ZB_BLJMSBS_FHBL_YLR.equals(sbzb.bljmsbs) &&
//              Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs))
            //增加减免处理
            String jmxzdm = Constants.JSJM_JMXZDM_SLJM;
            if(Constants.SETZ_QEZS.equals(sbzb.getSetz())){
            	 jmxzdm = Constants.JSJM_JMXZDM_CQJM;
            }
            
            if (jmszje == null || jmszje.doubleValue() == 0) {
            	BigDecimal jbsl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            	
            	BigDecimal jmsl = jbsl.subtract(hdbo.getSl());
            	if(jmsl.doubleValue()>0.001){
            		BigDecimal jmje = jmsl.multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	}else if(Constants.SETZ_JBZS.equals(sbzb.getSetz())) {
            		BigDecimal jmje = jbsl.multiply(new BigDecimal(0.5)).multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	} 	
            }  
            
            if (jmszje != null && jmszje.doubleValue() > 0) {
                HashMap map_jmsb = new HashMap();
                map_jmsb.put("jsjdm", wszZb.jsjdm);
                map_jmsb.put("szsmdm", wszMx.szsmdm);
                map_jmsb.put("jsje", hdbo.getJzse());
                map_jmsb.put("jmse", jmszje);
                map_jmsb.put("lrr", wszZb.lrr);
                map_jmsb.put("jmxmdm", Constants.JMXMDM); //项目代码
                map_jmsb.put("jmxzdm", jmxzdm); //减免项目代码
                map_jmsb.put("cjrq", wszZb.cjrq);
                map_jmsb.put("skssjsrq", wszMx.getSkssjsrq());
                map_jmsb.put("skssksrq", wszMx.getSkssksrq());
                if (!CommonUtil.insertSBJM(map_jmsb)) {
                    throw new ApplicationException("补录时插入税费的减免申报信息的时候报错！");
                }
                Debug.out("插入减免申报成功！");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }


    private ArrayList doHzWsz(ArrayList sbbhList, Connection conn) throws
            BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            StringBuffer condition = new StringBuffer("");
            for (Iterator iter = sbbhList.iterator(); iter.hasNext(); ) {
                String sbbh = (String) iter.next();
                condition.append("'").append(sbbh).append("',");
            }
            int end = condition.length() - 1; //找到最后一个字符的位置
            //传的参数中截取StringBuffer是为了去掉最后一个","
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO()
                         .HzWsz(condition.substring(0, end), conn);
        } catch (Exception ex) {
            throw new ApplicationException("汇总补录的完税证时出错！");
        }

        return sbjkmxList;
    }


    private ArrayList doGetSbjkmx(String jkpzh, String jsjdm, Connection conn) throws
            BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO().
                         querySbjkmx(jkpzh, jsjdm, conn);
        } catch (Exception ex) {
            throw new ApplicationException("查询补录缴款书数据的时候出错！");
        }

        return sbjkmxList;
    }


    private ArrayList doGetDzSbjkmx(String jkpzh, String jsjdm, String zbxh,
                                    Connection conn) throws BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO().
                         queryDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
        } catch (Exception ex) {
            throw new ApplicationException("查询补录缴款书数据的时候出错！");
        }

        return sbjkmxList;
    }


    private String doCreateQswszhz(UserData ud, String jkpzh,
                                   ArrayList sbjkmxList, Connection conn) throws
            BaseException {
        Qswszhz qswszhz = new Qswszhz();

        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //取得税务机关计算机代码
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //取得税务机关组织机构代码
            String swjgzzjgDm = ud.getSsdwdm();
            //取得申报汇总单号
            String sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);
            Debug.out("取得申报汇总单号 : " + sbhzdh);

            //给完税证汇总表vo补充数据
            qswszhz.setCjrq(now);
            qswszhz.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
            qswszhz.setHzfs(Constants.WSZ_HZFS_GR);
            qswszhz.setHzjsrq(now);
            qswszhz.setHzksrq(now);
            qswszhz.setHzrq(now);
            qswszhz.setJsjdm(jsjdm);
            qswszhz.setCjr(ud.getYhid());
            qswszhz.setLrr(ud.getYhid());
            qswszhz.setLrrq(now);
            qswszhz.setNd(DateUtil.getDate().substring(0, 4));
            qswszhz.setSbhzdh(sbhzdh);
            qswszhz.setSwjgzzjgdm(swjgzzjgDm);
            qswszhz.setHzfsmc("按个人汇总");
            //qswszhz.setJsfsmc(bo.getJsfsmc());
            //获得票证帐户的完整信息
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            qswszhz.setZsddm(ud.xtsbm1); //票证帐户代码
            qswszhz.setZsdmc(zh.zhmc); //征收点名称

            //如果税种税目小于等于4，也就是一张缴款书
            double sjse = 0.0;
            for (int i = 0; i < sbjkmxList.size(); i++) {
                sjse += ((Sbjkmx) sbjkmxList.get(i)).getSjse().doubleValue();
            }
            qswszhz.setSjse(new BigDecimal(sjse)); //实缴金额
            qswszhz.setJkpzh(jkpzh);

            DAOFactory.getInstance().getQswszhzDAO().insert(qswszhz, conn);
        } catch (Exception ex) {
            throw new ApplicationException("生成完税证汇总表数据时发生错误！！！");
        }

        return qswszhz.getSbhzdh();
    }

    private void removeConnect(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData();

        QueryBlJksBo bo = (QueryBlJksBo) vo.getData();
        Sbjkzb sbjkzb = bo.getSbjkzb();
        String type = bo.getType();
        String sjly = bo.getSjly();
        String zbxh = bo.getZbxh();
        String zwbs = sbjkzb.getZwbs();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jkpzh = sbjkzb.getJkpzh();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //如果是申报缴款书
            if (Constants.JKS_SJLY_FHZ.equals(sjly)) {
                //更新申报主表状态位为已复核
                DAOFactory.getInstance().getSbzbDAO().update(Constants.
                        ZB_ZTBS_YFH, sbjkzb.getSbbh(), conn);
            }
            //如果是汇总生成的缴款书
            else if (Constants.JKS_SJLY_HZ.equals(sjly)) {
                ArrayList qswszhzList = DAOFactory.getInstance().getQswszhzDAO().
                                        query(jsjdm, jkpzh, conn);
                if (qswszhzList.isEmpty()) {
                    throw new ApplicationException("没有找到完税证汇总记录！");
                }
                Qswszhz qswszhz = (Qswszhz) qswszhzList.get(0);
                //取消汇总的关联，删除查询出来的完税证汇总表数据
                DAOFactory.getInstance().getBlQueryJksDAO().
                        removeQswszhzConnect(qswszhz, conn);

                //得到汇总的完税证号
                ArrayList queryList = DAOFactory.getInstance().getBlQueryJksDAO().
                                      queryWszh(qswszhz.getSbhzdh(), conn);
                ArrayList wszList = (ArrayList) queryList.get(0);
                StringBuffer condition = new StringBuffer("");
                for (Iterator iter = wszList.iterator(); iter.hasNext(); ) {
                    String wszh = (String) iter.next();
                    condition.append("'").append(wszh).append("',");
                    Debug.out("撤销时的wszh BlProcessor.removeConnect is" + wszh);
                }
                int end = condition.length() - 1; //找到最后一个字符的位置

                //删除完税证子表数据
                StringBuffer sql = new StringBuffer(
                        "DELETE FROM sbdb.sb_jl_qswszmx ");
                sql.append("WHERE wszh in(").append(condition.substring(0, end))
                        .append(") ");
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                //删除完税证主表数据
                sql = new StringBuffer("DELETE FROM sbdb.sb_jl_qswszz ");
                sql.append("WHERE wszh in(").append(condition.substring(0, end))
                        .append(") ");
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);

                //修改申报主表的状态标识
                //恢复标记为已复核
                ArrayList sbbhList = (ArrayList) queryList.get(1);
                for (Iterator it = sbbhList.iterator(); it.hasNext(); ) {
                    String s = (String) it.next();
                    DAOFactory.getInstance().getSbzbDAO().update(Constants.
                            ZB_ZTBS_YFH, s, conn);
                    Debug.out("恢复的sbbh in BlProcessor.removeConnect is" + s);
                }
            }

            //撤销缴款书关联的时候，缴款书的zwbs的第11位从1修改为0
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '0';
            zwbs = String.copyValueOf(zwbschr);

            String sbbh = null;

            if (Constants.PT_JKS.equals(type)) { //如果是普通的缴款书，把sbbh字段置为null
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        sbbh, jsjdm, zwbs, conn);
            } else if (Constants.DZ_JKS.equals(type)) { //如果是调帐的缴款书，把sbbh字段置为null
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        sbbh, jsjdm, zbxh, zwbs, conn);
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报补录－BlProcessor，撤销补录时出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private Hdtzs doCreateHdtzs(UserData ud, Sbzb sbzb, Connection conn) throws
            BaseException {
        Hdtzs hdtzs = new Hdtzs();
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            //update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(Constants.
                    ZB_ZTBS_JS_TY, sbzb.getSbbh(), conn);

            //插入核定通知书

            hdtzs.sbbh = sbzb.getSbbh();
            hdtzs.bzbs = Constants.BZBS_JM;
            hdtzs.cjr = ud.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            //modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH(ud, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            //end modified

            hdtzs.jbr = ud.yhmc;
            /**
             * 计算审核及收款时候需要用到的计征税额
             * Constants 中的定义：
              public static final String JE_CJJG = "JE_CJJG";   //成交价格
              public static final String JE_JSYJ = "JE_JSYJ";   //计税依据
              public static final String JE_JZQS = "JE_JZQS";   //计征契税
              public static final String JE_SJYZ = "JE_SJYZ";   //实际应征
              public static final String JE_JZSE = "JE_JZSE";   //计征税额
             public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE";//出售已购公有住房的本次抵扣额
              public static final String JE_FWJHJG = "JE_FWJHJG";  //房屋交换价格
              public static final String JE_CQJMJE = "JE_CQJMJE";  //拆迁减免金额
              public static final String JE_PTZZJMJE = "JE_PTZZJMJE";//普通住宅减税金额
              public static final String JE_JMSZE = "JE_JMSZE";//减免税总金额
              public static final String JE_YNSE = "JE_YNSE";    //应纳税额
             * @param String 申报表号
             * @return HashMap
             */
            JghdsjBo hdbo = CommonUtil.getJZSE(sbzb.getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); //应征契税
            //获取已购公有住房项
            hdtzs.kcqyzfx = hdbo.getGyzfjmje();
            hdtzs.lrr = ud.yhmc;
            hdtzs.lrrq = now;
            //得到纳税人名称
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                hdtzs.sqr = grxx.getNsrmc();
            } else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbzb.sbbh, conn);
                hdtzs.sqr = fgrxx.getNsrmc();
            }

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.getSbbh(), conn);
            hdtzs.spfxmmc = tufwxx.getFdcxmmc();
            hdtzs.zldi = tufwxx.getTdfwzldz();

            //插入一条核定通知书记录
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            //如果有拆迁住房，插入减免明细表
            ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                   .queryBySbbh(sbzb.getSbbh(), conn);
            if (!jsblcqList.isEmpty()) {
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

            BigDecimal ptzzjmje = (hdbo.getPtzzjmje());
            if (ptzzjmje.doubleValue() > 0) { //有普通住宅减免金额
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
            throw new ApplicationException("插入核定通知书时出错！");
        }
        return hdtzs;
    }

    private void getSbjkzbInfo(Sbjkzb sbjkzb, UserData ud, Connection conn) {
        try {

            Yskm yskm = CommonUtil.getYskm(sbjkzb.getSzdm(), conn);
            sbjkzb.setYskmdm(yskm.yskmdm); //预算科目
            sbjkzb.setYskmmc(yskm.yskmmc); //预算科目名称

            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            sbjkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //国库组织机构代码
            sbjkzb.setGkzzjgmc(swjgzzjg.skgk); //国库组织机构名称

            //得到纳税人的登记基本数据
            SWDJJBSJ jbsj = null;
            ZRRJBSJ zrrJbsj = null;
            try {
                jbsj = CommonUtil.getFgrJBSJ(sbjkzb.getJsjdm());
            } catch (Exception ex1) {
                Debug.out(ex1);
            }

            if (jbsj == null) { //再去查自然人登记
                try {
                    zrrJbsj = CommonUtil.getGrJBSJ(sbjkzb.getJsjdm());
                    if (zrrJbsj != null) {
                        sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //登记注册类型
                        sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //登记注册类型名称
                        sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //国家标准行业代码
                        sbjkzb.setNsrmc(zrrJbsj.getNsrmc()); //纳税人名称
                    }
                } catch (Exception ex1) {
                    Debug.out(ex1);
                }
            } else {
                sbjkzb.setLsgxdm(jbsj.getLsgxdm()); //隶属关系
                sbjkzb.setLsgxmc(jbsj.getLsgxmc()); //隶属关系名称
                sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //登记注册类型
                sbjkzb.setDjzclxmc(jbsj.getDjzclxmc()); //登记注册类型名称
                sbjkzb.setJydzlxdm(jbsj.getJydzlxdm()); //经营地址联系电话
                sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //国家标准行业代码
                sbjkzb.setGjbzhymc(jbsj.getGjbzhymc()); //国家标准行业名称
                sbjkzb.setNsrmc(jbsj.getNsrmc()); //纳税人名称
            }

            if (Constants.JKS_SKLXDM_XHJK.equals(sbjkzb.getSklxdm())) {
                sbjkzb.setSklxmc(Constants.JKS_SKLXDM_XHJK_MC); //税款类型名称
            } else if (Constants.JKS_SKLXDM_ZCJK.equals(sbjkzb.getSklxdm())) {
                sbjkzb.setSklxmc(Constants.JKS_SKLXDM_ZCJK_MC); //税款类型名称
            }
            sbjkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //预算级次名称
            sbjkzb.setSzmc(Constants.WSZ_QSSZMC); //税种名称
            sbjkzb.setZsswjgzzjgmc(ud.ssdwmc); //征收税务机关组织机构名称
            sbjkzb.setSwjgzzjgmc(ud.ssdwmc); //税务机关组织机构名称

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getSzsmmc(Sbjkmx sbjkmx) {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Szsm szsm = CommonUtil.getSZSM(sbjkmx.getSzsmdm(), conn);
            sbjkmx.setSzsmmc(szsm.getSzsmmc());
        } catch (Exception ex) {
            ex.printStackTrace();
            sbjkmx.setSzsmmc("没有找到该项目名称");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }
}
