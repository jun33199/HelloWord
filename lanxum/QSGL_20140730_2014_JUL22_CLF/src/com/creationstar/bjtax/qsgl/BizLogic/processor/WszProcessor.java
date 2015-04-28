package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
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
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.Helper;
import com.creationstar.bjtax.qsgl.util.JksUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
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
 * 完税证的processor，所有与完税证有关的后台逻辑和数据库操作，都在此Processor中进行
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
public class WszProcessor implements Processor {
    /**
     *
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is WszProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }
        switch (vo.getAction()) {
        //查询完税证
        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.CREATE_WSZ:
            result = doSave(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

            //撤销完税证
        case ActionType.CX_WSZ:
            cxWsz(vo);
            break;

            //汇总完税证生成缴款书
        case ActionType.HZ_WSZ:
            result = doHzWsz(vo);
            break;

            //撤销汇总、撤销缴款书
        case ActionType.CX_HZWSZ:
            cxJks(vo);
            break;

        case ActionType.CHANGE_WSZ_STATUS:
            doUpdate(vo);
            break;

            //打印完税证
        case ActionType.PRINT_WSZ:
            result = doPrint(vo);
            break;

            //变号打印
        case ActionType.CHANGE_WSZH_PRINT:
            result = doChangePrint(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 查询完税证
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();

        QueryWszBo bo = (QueryWszBo) vo.getData();

        StringBuffer condition = new StringBuffer("");

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            condition.append(" AND a.jsjdm = '").
            	append(CommonUtil.getWszJsjdm(vo.getUserData(), conn)).
                    append("' ");

            if (bo.getNdzb() != null && !bo.getNdzb().equals("")) {
                condition.append(" AND a.ndzb = '").append(bo.getNdzb()).append(
                        "' ");
            }
            if (bo.getStartWszh() != null && !bo.getStartWszh().equals("")) {
                condition.append(" AND a.wszh >= '").append(bo.getStartWszh()).
                        append("' ");
            }
            if (bo.getEndWszh() != null && !bo.getEndWszh().equals("")) {
                condition.append(" AND a.wszh <= '").append(bo.getEndWszh()).
                        append("' ");
            }
            if (bo.getStartCjrq() != null && !bo.getStartCjrq().equals("")) {
                condition.append(" AND a.cjrq >= TO_DATE('").append(bo.
                        getStartCjrq()).
                        append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getEndCjrq() != null && !bo.getEndCjrq().equals("")) {
                condition.append(" AND a.cjrq <= TO_DATE('").append(bo.
                        getEndCjrq()).
                        append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                condition.append(" AND a.nsrmc like '%").append(bo.getNsrmc()).
                        append("%' ");
            }

            //增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_QSWSZZ");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);
            resultList = DAOFactory.getInstance().getQswszzDAO().queryAll(
                    condition.toString(), conn);

            //查询已汇总的完税证被汇总到了哪个缴款书中
            for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
                bo = (QueryWszBo) iter.next();
                //如果是已汇总的完税证
                if (Constants.WSZ_CLBJDM_YJZ.equals(bo.getQswszzVo().getClbjdm())) {
                    //查询已汇总的完税证对应的缴款书
                    String jkpzh = (String) DAOFactory.getInstance().
                                   getSbjkzbDAO().query(bo.getQswszzVo(),
                            bo.getMxVo(), conn);
                    //取得缴款凭证号
                    bo.getQswszzVo().setJkpzh(jkpzh);
                }
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(),
                             "失败" + condition.toString());

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //由于完税证主表和子表中的数据是1对多的情况，为了使用方便，需要重新组织
        //查询结果有1条主表数据对应多条子表数据的情况，在bo中主表数据放在一个主表vo中，对应的子表数据放在ArrayList中
        //以下代码是重新组织查询的数据，把组织好的bo放到ArrayList里面返回

        ArrayList returnList = new ArrayList(); //最终返回给前台的查询结果集
        ArrayList tempList = new ArrayList(); //临时存放查询结果bo中的明细vo
        QueryWszBo resultBo = new QueryWszBo(); //最终返回给前台的查询结果集中的每一条记录
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            //这个bo代表DAO.queryAll()方法查询返回的ArrayList中的每一条记录，所以每次都要new
            bo = (QueryWszBo) iter.next();

            if (returnList.isEmpty()) { //如果返回给前台的查询结果为空，说明是第一条数据
                resultBo.setQswszzVo(bo.getQswszzVo());
                //resultBo.setJkpzh(bo.getJkpzh() );
                tempList.add(bo.getMxVo()); //在查询结果bo中加入相应的子表vo
                resultBo.setResultList(tempList); //付给查询结果bo中存储子表结果的ArrayList

                returnList.add(resultBo); //将重新组织的查询结果放到返回给前台的ArrayList中

            } else {
                //如果本条查询结果的完税证号、年度字别、票证种类代码与上条相同
                //说明主表数据相同只是明细表不同，也就是1条主表数据对应多条明细表数据的情况
                //则把该条的明细数据放到相同的resultBo的明细ArrayList中
                if (bo.getQswszzVo().getWszh().equals(resultBo.getQswszzVo().
                        getWszh())
                    &&
                    bo.getQswszzVo().getNdzb().equals(resultBo.getQswszzVo().getNdzb())
                    &&
                    bo.getQswszzVo().getPzzldm().equals(resultBo.getQswszzVo().getPzzldm())) {
                    //因为没有构造新的resultBo，所以ArrayList中的当前resultBo就是新的resultBo
                    tempList.add(bo.getMxVo());
                    resultBo.setResultList(tempList);

                    //returnList.add(resultBo);
                } else {
                    //因为是一条不同的数据，所以重新构造resultBo和tempList
                    resultBo = new QueryWszBo();
                    tempList = new ArrayList();

                    resultBo.setQswszzVo(bo.getQswszzVo());
                    //resultBo.setJkpzh(bo.getJkpzh());
                    tempList.add(bo.getMxVo());
                    resultBo.setResultList(tempList);

                    returnList.add(resultBo);
                }
            }
        }

        return returnList;
    }


    /**
     * 撤销完税证时查询完税证的方法
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {
        Qswszz wszVo = new Qswszz();
        QueryWszBo bo = (QueryWszBo) vo.getData();
        StringBuffer condition = new StringBuffer("");
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            if (bo.getStartWszh() != null && !bo.getStartWszh().equals("")) {
                condition.append(" AND a.wszh = '").append(bo.getStartWszh()).
                        append("' ");
            }
            if (bo.getNdzb() != null && !bo.getNdzb().equals("")) {
                condition.append(" AND a.ndzb = '").append(bo.getNdzb()).append(
                        "' ");
            }
            //增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_QSWSZZ");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);
            wszVo = (Qswszz) DAOFactory.getInstance().getQswszzDAO().get(
                    condition, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return wszVo;
    }

    /**
     * 汇总完税证
     * @param vo VOPackage
     * @throws BaseException
     */
    private ArrayList doHzWsz(VOPackage vo) throws BaseException {
        ArrayList mxList = new ArrayList(); //汇总的数据集
        ArrayList fpList = new ArrayList(); //汇总数据后生成缴款书主表的数据集

        UserData ud = (UserData) vo.getUserData();

        //从前台传过来的bo中，取出汇总条件
        WszBo bo = new WszBo();
        bo = (WszBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //取得税务机关计算机代码
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //取得税务机关组织机构代码
            String swjgzzjgDm = ud.getSsdwdm();

            String drpch = bo.getDrpch();

            //1、汇总方式：个人
            //2、现金 or 刷卡 or 支票
            //3、汇总起始日期和截止日期，如果没有起始日期，则汇总的时间条件为 完税证的填发日期 <＝ 汇总截止日期
            //否则汇总的时间条件为 汇总起始日期 <＝ 完税证的填发日期 <＝ 汇总截止日期
            String hzqsrq = bo.getHzqsrq();
            String hzjzrq = bo.getHzjzrq();

            StringBuffer condition = new StringBuffer("");
            if (bo.getJsfs() != null && !bo.getJsfs().equals("")) {
                condition.append(" AND a.JSFSDM = '").append(bo.getJsfs()).
                        append("' ");
            }

            //根据查询条件按个人汇总完税证
            if (Constants.WSZ_HZFS_GR.equals(bo.getHzfs())) {
                if (hzqsrq != null && !hzqsrq.equals("")) {
                    condition.append(" AND a.cjrq >= TO_DATE('").append(hzqsrq)
                            .append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
                }
                if (hzjzrq != null && !hzjzrq.equals("")) {
                    condition.append(" AND a.cjrq <= TO_DATE('").append(hzjzrq)
                            .append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
                }
                condition.append(" AND a.lrr = '").append(ud.getYhid()).append(
                        "' ");
                condition.append(" AND substr(a.sbbh,0,1) != 'P'");

                mxList = DAOFactory.getInstance().getQswszzDAO().HzWszGr(
                        condition.toString(), conn);
                Debug.out("成功完成完税证明细的汇总提取工作....");

                if (mxList.isEmpty()) {
                    throw new Exception("您所选定的时间段内的完税证都已汇总!!!");
                }
                //给按个人方式汇总的完税证汇总vo补充数据
                for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                    Sbjkmx mxVo = new Sbjkmx();
                    mxVo = (Sbjkmx) iter.next();

                    mxVo.setCjrq(now);
                    mxVo.setJsjdm(jsjdm);
                    mxVo.setLrrq(now);
                    mxVo.setNd(DateUtil.getDate().substring(0, 4));
                    /** @todo 这两个如何取值？ */
                    //mxVo.setQjfc();
                    mxVo.setQxdm(CommonUtil.getQxdm(ud));
                    //mxVo.setSjfc();
                    mxVo.setSkssjsrq(DataConvert.String2Timestamp(hzjzrq));
                    mxVo.setSkssksrq(DataConvert.String2Timestamp(hzqsrq));
                    //mxVo.setSl();    这个是不需要的，申报缴款明细表中的税率没有用到
                    mxVo.setSwjgzzjgdm(swjgzzjgDm);
                    mxVo.setYsjcdm(Constants.YSJCDM_DF);

                    //得到税种税目名称
                    Szsm szsm = CommonUtil.getSZSM(mxVo.getSzsmdm(), conn);
                    mxVo.setSzsmmc(szsm.getSzsmmc());
                }

                fpList = this.JksFP(mxList, vo);
                Debug.out("fpList 的长度：" + fpList.size());
                for (Iterator iter = fpList.iterator(); iter.hasNext(); ) {
                    //将汇总完税证的数据insert到汇总完税证表中
                    DAOFactory.getInstance().getQswszhzDAO().insert((Qswszhz)
                            iter.next(), conn);
                    Debug.out("将汇总数据插入汇总表成功...");
                }

                //给缴款书明细数据设置缴款凭证号
                for (int i = 0; i < mxList.size(); i++) {
                    if (mxList.size() <= 4) {
                        ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.get(
                                0)).getJkpzh());
                    } else {
                        if (i < 4) {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(0)).getJkpzh());
                        } else {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(1)).getJkpzh());
                        }
                    }
                }

                //汇总完税证后返填处理标记代码、申报汇总单号给完税证主表
                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).
                        append("', a.sbhzdh = '")
                        .append(((Qswszhz) fpList.get(0)).getSbhzdh())
                        .append(
                        "' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                        .append(" a.clbjdm = '").append(Constants.
                        WSZ_CLBJDM_YWS).append("' ")
                        .append(condition);

                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                Debug.out("汇总完税证后返填处理标记代码、申报汇总单号给完税证主表....");

            } else {
                //根据查询条件按两种单位汇总方式生成的完税证
                if (Constants.WSZ_HZFS_SWS.equals(bo.getHzfs())) { //税务机关汇总
                    condition.append(" AND a.jsjdm = '").append(jsjdm).append(
                            "' ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().
                             HzWszSwjg(condition.toString(), conn);
                } else if (Constants.WSZ_HZFS_ZSD.equals(bo.getHzfs())) { //征收点汇总
                    condition.append(" AND substr(a.zsddm,0,8) = '").append(ud.
                            getXtsbm1().substring(0, 8)).append("' ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().
                             HzWszZsd(condition.toString(), conn);
                }

                //批次导入的完税证汇总
                if (drpch != null && !drpch.equals("")) {
                    condition.append(
                            " AND a.sbbh in (SELECT sbbh FROM QSDB.QS_JL_SBZB WHERE drpch = '").
                            append(drpch).append("') ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().HzWszPl(bo,
                            conn);
                }

                if (mxList.isEmpty() && (drpch == null || drpch.equals(""))) {
                    throw new Exception("您所选定的时间段内的完税证都已汇总!!!");
                } else if (mxList.isEmpty() &&
                           (drpch != null && !drpch.equals(""))) {
                    throw new Exception("与您所输入的批次号相符的完税证都已汇总!!!");
                }

                //给按汇总的完税证汇总vo补充数据
                for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                    Sbjkmx mxVo = new Sbjkmx();
                    mxVo = (Sbjkmx) iter.next();

                    mxVo.setCjrq(now);
                    mxVo.setJsjdm(jsjdm);
                    mxVo.setLrrq(now);
                    mxVo.setNd(DateUtil.getDate().substring(0, 4));
                    /** @todo 这两个如何取值？ */
                    //mxVo.setQjfc();
                    mxVo.setQxdm(CommonUtil.getQxdm(ud));
                    //mxVo.setSjfc();
                    /** @todo 这两个数据如何设置？有待确定 */
//                    mxVo.setSkssjsrq(DataConvert.String2Timestamp(hzjzrq));
//                    mxVo.setSkssksrq(DataConvert.String2Timestamp(hzqsrq));
                    mxVo.setSkssjsrq(now);
                    mxVo.setSkssksrq(now);
                    //mxVo.setSl();这个是不需要的，申报缴款明细表中的税率没有用到
                    mxVo.setSwjgzzjgdm(swjgzzjgDm);
                    mxVo.setYsjcdm(Constants.YSJCDM_DF);

                    //得到税种税目名称
                    Szsm szsm = CommonUtil.getSZSM(mxVo.getSzsmdm(), conn);
                    mxVo.setSzsmmc(szsm.getSzsmmc());
                }

                fpList = this.JksFP(mxList, vo);
                for (Iterator iter = fpList.iterator(); iter.hasNext(); ) {
                    //将汇总完税证的数据insert到汇总完税证表中
                    DAOFactory.getInstance().getQswszhzDAO().insert((Qswszhz)
                            iter.next(), conn);
                }

                //给缴款书明细数据设置缴款凭证号
                for (int i = 0; i < mxList.size(); i++) {
                    if (mxList.size() <= 4) {
                        ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.get(
                                0)).getJkpzh());
                    } else {
                        if (i < 4) {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(0)).getJkpzh());
                        } else {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(1)).getJkpzh());
                        }
                    }
                }

                //汇总完税证后返填处理标记代码、申报汇总单号给完税证主表
                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).
                        append("', a.sbhzdh = '")
                        .append(((Qswszhz) fpList.get(0)).getSbhzdh())
                        .append(
                        "' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                        .append(" a.clbjdm = '").append(Constants.
                        WSZ_CLBJDM_YWS).append("' ")
                        .append(condition);
                Debug.out("申报汇总单号给完税证主表 " + sql.toString());
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
            }

            return doCreateJks(vo, fpList, mxList);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    /**
     * 汇总方式生成的缴款书
     * @param vo VOPackage
     * @param fplist ArrayList   分好票的完税证汇总数据集合
     * @param mxList ArrayList   缴款书明细数据集合
     * @throws BaseException
     */
    private ArrayList doCreateJks(VOPackage vo, ArrayList fplist,
                                  ArrayList mxList) throws BaseException {
        UserData ud = (UserData) vo.getUserData();
        ArrayList jkzbList = new ArrayList();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            Yskm yskm = null; //预算科目

            //得到汇总的征收人员的所属税务机关的基本信息
            SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(jsjdm);
            if (swdjJbsj == null) {
                throw new ApplicationException("不能得到汇总征收人员的所属税务机关的基本信息！");
            }
            //得到当前时间
            Timestamp now = CommonUtil.getDBtime(conn);

            //added by zhaobo at 20050711
            //cause zwbs modified to jk
            WszBo bo = (WszBo) vo.getData();
            String zwbs;
            if (Constants.WSZ_JSFS_XJ.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_XJ;
            } else if (Constants.WSZ_JSFS_ZP.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_ZP;
            } else if (Constants.WSZ_JSFS_SK.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_SK;
            } else {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }
            //added end

            //根据分票的数据，insert申报缴款主表记录
            for (Iterator iter = fplist.iterator(); iter.hasNext(); ) {
                Qswszhz fpVo = (Qswszhz) iter.next();
                Sbjkzb jkzb = new Sbjkzb();

                //为缴款申报主表补全数据
                jkzb.setCjrq(now); //创建日期
                jkzb.setXjrq(CommonUtil.getXjrq(jkzb.getCjrq(), 7)); //契税限缴日期
                jkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //处理标记代码
                jkzb.setDjzclxdm(swdjJbsj.getDjzclxdm()); //登记注册类型代码
                jkzb.setFsdm(Constants.WSZ_FSDM); //登记申报方式代码
                jkzb.setGjbzhydm(swdjJbsj.getGjbzhydm()); //国家标准行业代码
                Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
                jkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //国库组织机构代码
                jkzb.setGkzzjgmc(swjgzzjg.skgk); //国库组织机构名称
                jkzb.setZwbs(zwbs); //帐务标识

                jkzb.setJsjdm(jsjdm); //计算机代码
                jkzb.setJydzlxdm(swdjJbsj.getJydzlxdm()); //经营地址联系电话
                jkzb.setLrr(ud.getYhid()); //录入人
                jkzb.setLrrq(now); //录入日期
                jkzb.setLsgxdm(swdjJbsj.getLsgxdm()); //隶属关系代码
                jkzb.setNd(DateUtil.getDate().substring(0, 4)); //年度
                jkzb.setQxdm(CommonUtil.getQxdm(ud)); //区县代码
                //原来sbbh以为是申报编号，现在改为申报表号，汇总没有申报表号
                //jkzb.setSbbh(JksUtil.getSbbh(jsjdm)); //申报编号
                jkzb.setSbrq(now); //申报日期

                jkzb.setSjly(Constants.JKS_SJLY_HZ); //数据来源——汇总方式生成
                jkzb.setSklxdm(Constants.JKS_SKLXDM_HZJK); //税款类型代码
                jkzb.setSwjgzzjgdm(ud.getSsdwdm()); //税务机关组织机构代码
                jkzb.setSzdm(Constants.WSZ_QSSZDM); //税种代码
                jkzb.setZsswjgzzjgdm(ud.getSsdwdm()); //征收机关代码
                jkzb.setYsjcdm(Constants.YSJCDM_DF); //预算级次
                //得到预算科目代码
                yskm = CommonUtil.getYskm(jkzb.getSzdm(), conn);
                jkzb.setYskmdm(yskm.yskmdm);

                jkzb.setSwjgzzjgmc(swdjJbsj.getSwjgzzjgmc()); //税务机关组织机构名称
                jkzb.setLsgxmc(swdjJbsj.getLsgxmc()); //隶属关系名称
                jkzb.setDjzclxmc(swdjJbsj.getDjzclxmc()); //登记注册类型名称
                jkzb.setGjbzhymc(swdjJbsj.getGjbzhymc()); //国家标准行业名称
                jkzb.setNsrmc(swdjJbsj.getNsrmc()); //纳税人名称
                jkzb.setZsswjgzzjgmc(ud.ssdwmc); //征收税务机关组织机构名称
                jkzb.setSwjgzzjgmc(ud.ssdwmc); //税务机关组织机构名称
                jkzb.setSklxmc(Constants.JKS_SKLXDM_HZJK_MC); //税款类型名称
                jkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //预算级次名称
                jkzb.setSzmc(Constants.WSZ_QSSZMC); //税种名称
                jkzb.setYskmmc(yskm.yskmmc); //预算科目名称
                jkzb.setZyrq(now); //创建日期

                // 汇总生成缴款书不保存银行信息 Modifyed by wuyouzhi 2006.2.8
//                //得到银行信息
//                ArrayList yhList = (ArrayList) CommonUtil.getYHZH(jsjdm);
//                for (Iterator it = yhList.iterator(); it.hasNext(); )
//                {
//                    YHZH yhzh = (YHZH) it.next();
//                    if (Constants.JKS_JBZHBS.equalsIgnoreCase(yhzh.getJbzhbs()))
//                    {
//                        jkzb.setYhdm(yhzh.getYhdm());
//                        jkzb.setYhmc(yhzh.getKhyhmc());
//                        jkzb.setZh(yhzh.getZh());
//                    }
//                }

                jkzb.setJkpzh(fpVo.getJkpzh()); //缴款凭证号;
                jkzb.setSjje(fpVo.getSjse()); //实缴金额
                jkzb.setRkje(fpVo.getSjse()); //入库金额
                Sbjkmx mx = (Sbjkmx) mxList.get(0);
                jkzb.setSkssjsrq(mx.getSkssjsrq()); //税款所属截止日期
                jkzb.setSkssksrq(mx.getSkssksrq()); //税款所属开始日期
                jkzb.setBz("缴税方式：" + fpVo.getJsfsmc());
                //end modified
                jkzb.setSbbh(CommonUtil.getJksSbbh(jsjdm)); //申报表编号

                DAOFactory.getInstance().getSbjkzbDAO().insert(jkzb, conn);

                //将生成好的缴款书对象返回到前台
                jkzbList.add(jkzb);
            }

            //根据sjbkmx vo的数据，insert申报缴款明细表记录
            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Sbjkmx mxVo = new Sbjkmx();
                mxVo = (Sbjkmx) iter.next();

                //得到预算科目代码，目前契税所有税种税目对应的预算科目代码都是一样的
                //所以用申报缴款主表vo中的税种去取就可以了
                mxVo.setYskmdm(yskm.yskmdm);
                mxVo.setYskmmc(yskm.yskmmc);
                mxVo.setSbbh(CommonUtil.getJksSbbh(jsjdm)); //申报表编号
                mxVo.setYskmfcbl(com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(yskm.yskmdm, mxVo.getSzsmdm(), mxVo.getSwjgzzjgdm()));//分成比例
                DAOFactory.getInstance().getSbjkmxDAO().insert(mxVo, conn);
            }

            int kssl = 0; //完税证数量

            //组织返回给前台的数据
            //如果没有分票的情况，也就是只有一张缴款书
            int mxListsize = mxList.size();
            if (jkzbList.size() == 1) {
                //将缴款明细的数据放入到申报缴款主表的vo中
                ((Sbjkzb) jkzbList.get(0)).setMxList(mxList);
                for (int i = 0; i < mxListsize; i++) {
                    //该缴款书的完税证数量等于明细数据中的课税数量的合
                    kssl += ((Sbjkmx) mxList.get(i)).getKssl().intValue();
                }
                ((Sbjkzb) jkzbList.get(0)).setKssl(kssl); //设置完税证数量
            } else if (jkzbList.size() > 1) { //如果有分票的情况
                ArrayList tempList = new ArrayList();
                //现在的业务是一张缴款书中最多有4个税种税目，最多有5个税目，以后可能会有变化
                //把第一张缴款书的明细数据加入到申报缴款主表的vo中
                for (int i = 0; i < 4; i++) {
                    tempList.add(mxList.get(i));

                    //该缴款书的完税证数量等于明细数据中的课税数量的合
                    kssl += ((Sbjkmx) mxList.get(i)).getKssl().intValue();
                }
                ((Sbjkzb) jkzbList.get(0)).setMxList(tempList);
                ((Sbjkzb) jkzbList.get(0)).setKssl(kssl); //设置完税证数量

                //把第二张缴款书的明细数据加入到申报缴款主表的vo中
                tempList = new ArrayList();
                for (int i = 1; i <= mxListsize - 4; i++) {
                    tempList.add(mxList.get(mxListsize - i));

                    //该缴款书的完税证数量等于明细数据中的课税数量的合
                    kssl = ((Sbjkmx) mxList.get(mxListsize - i)).getKssl().
                           intValue();
                }
                ((Sbjkzb) jkzbList.get(1)).setMxList(tempList);
                ((Sbjkzb) jkzbList.get(1)).setKssl(kssl); //设置完税证数量
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return jkzbList;
    }

    /**
     * 撤销汇总缴款书
     * @param vo VOPackage
     * @throws BaseException
     */
    private void cxJks(VOPackage vo) throws BaseException {
        ArrayList fpList = new ArrayList();

        WszBo wszbo = (WszBo) vo.getData();
        String jkpzh = wszbo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);
        Sbjkzb jkzbVo = new Sbjkzb();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //查询缴款书主表
            jkzbVo.setJkpzh(jkpzh);
//            jkzbVo.setJsjdm(CommonUtil.getWszJsjdm(vo.getUserData(), conn) );
            jkzbVo.setJsjdm(jsjdm);
            wszbo.setJsjdm(jsjdm);

            jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    jkzbVo, conn);

            if (jkzbVo == null) {
                throw new Exception("对不起，没有找到与该缴款书号匹配的记录！！！");
            }

            //如果该缴款书已入库
//            if(!Constants.JKS_ZWBS_DEFAULT.equals(jkzbVo.getZwbs()) )
//            {
//                throw new Exception("该缴款书已入库，不能撤销！！！");
//            }
            String zwbs = jkzbVo.getZwbs();
            if (zwbs == null) {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }

            if (!Constants.JKS_ZWBS_DEFAULT.substring(0,
                1).equals(zwbs.substring(0, 1))
                ||
                !Constants.JKS_ZWBS_DEFAULT.substring(19).equals(zwbs.
                    substring(19))) {
                throw new Exception("该缴款书已入库，不能撤销！！！");
            }

            //如果是汇总生成的缴款书，还要判断汇总的完税证的jbdh字段是否为null
            //完税证主表的jbdh字段为null，说明尚未结报，可以撤销缴款书
            if (jkzbVo.getSbbh() == null || jkzbVo.getSbbh().equals("")) {
                ArrayList resultList = new ArrayList(); //查询结果集，存储的都是完税证主表vo
                resultList = DAOFactory.getInstance().getQswszzDAO().query(
                        jkzbVo,
                        conn);

                //按理说汇总生成的缴款书不可能查询不出完税证
                //但现在有个问题，汇总后的完税证的clbjdm会莫名其妙的从15变为14，所以加了这个判断
                if (resultList.isEmpty()) {
                    throw new Exception("没有找到汇总生成该缴款书的完税证记录！！！");
                }

                Qswszz qswszz = (Qswszz) resultList.get(0);
                Debug.out("撤销汇总的缴款书，要判断完税证是否已结报，完税证号：" + qswszz.getWszh());
                if (qswszz.getJbdh() != null && !qswszz.getJbdh().equals("")) {
                    Debug.out("结报单号：" + qswszz.getJbdh());
                    throw new Exception("完税证已结报，不能撤销缴款书！！！");
                }

            }

            StringBuffer condition = new StringBuffer("");
            condition.append("jkpzh = '").append(jkpzh)
                    .append("' AND jsjdm = '").append(jsjdm).append("' ");
            Debug.out("已经构造完成撤销汇总的查询条件！");

            //通过计算机代码和缴款书号在完税证汇总表中查询是否有分票的情况
            //如果有，只要有一张缴款书已入库就都不能撤销，否则全部撤销
            Debug.out("撤销的征收人员的计算机代码：" + jsjdm);
            fpList = DAOFactory.getInstance().getQswszhzDAO().query(jsjdm,
                    jkpzh, conn);

            //如果只有一个缴款凭证号，说明没有分票
            if (fpList.size() == 0) {
                throw new ApplicationException("根据条件没有查询出来需要撤销的缴款书！");
            } else if (fpList.size() == 1) {
                //删除缴款书主表、缴款书明细表和完税证汇总表的相关记录
                DAOFactory.getInstance().getSbjkmxDAO().delete(condition, conn);
                DAOFactory.getInstance().getSbjkzbDAO().delete(condition, conn);
                DAOFactory.getInstance().getQswszhzDAO().delete(condition, conn);
                Debug.out("如果只有一个缴款凭证号，说明没有分票！");
            }
            //如果有分票的情况
            else {
                for (int i = 0; i < fpList.size(); i++) {
                    condition = new StringBuffer("");
                    condition.append("jkpzh = '")
                            .append(((Qswszhz) fpList.get(i)).getJkpzh())
                            .append("' AND jsjdm = '").append(wszbo.getJsjdm()).
                            append("' ");

                    //删除缴款书主表、缴款书明细表和完税证汇总表的相关记录
                    DAOFactory.getInstance().getSbjkmxDAO().delete(condition,
                            conn);
                    DAOFactory.getInstance().getSbjkzbDAO().delete(condition,
                            conn);
                    DAOFactory.getInstance().getQswszhzDAO().delete(condition,
                            conn);
                    Debug.out("如果有分票的情况！");
                }
            }

            Debug.out("将完税证主表中的相关记录的申报汇总单号字段update为null！");
            //将完税证主表中的相关记录的申报汇总单号字段update为null
            StringBuffer sql = new StringBuffer("");
            sql.append("UPDATE sbdb.sb_jl_qswszz a ")
                    .append("SET a.sbhzdh = null, a.clbjdm = '")
                    .append(Constants.WSZ_CLBJDM_YWS).append(
                    "' WHERE a.sbhzdh = '")
                    .append(((Qswszhz) fpList.get(0)).getSbhzdh()).append("' ");

            DAOFactory.getInstance().getQswszzDAO().update(sql.toString(), conn);
            Debug.out("将完税证主表中的相关记录的申报汇总单号字段update为null.......成功！");
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 生成并保存完税证
     * VOPackage中的Data属性，从前台传过来的是ScDyWszBo的数据对象
     * 当然数据只有一部分，就是申报主表中的申报数据
     * @param vo 数据集对象（包括Qswszz值对象和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData(); //取到当前用户对象
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = null; //构造完税证主表的数据
        Qswszmx wszMx = null; //构造完税证明细的数据

        ArrayList wszList = new ArrayList();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //1、通过查询申报主表、房屋土地基本信息得到完税证的基本信息部分
            Sbzb sbzb = new Sbzb();
            sbzb.sbbh = bo.getSbbh();
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                    conn);
            Debug.out("取得申报主表的完整信息....");
            //判断是否已经做过完税证的打印操作了
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("申报表号为" + sbzb.sbbh +
                                               "已经打印过完税证了！");
            }
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            Debug.out("取得房屋信息的完整信息....");
            if (tufwxx == null) {
                throw new ApplicationException("房屋、土地的基本信息不能为空！");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //取得减免税金额
            //BigDecimal jmszje = (BigDecimal) map.get(Constants.JE_JMSZE);
            BigDecimal jmszje = hdbo.getJmzje();
            //取得合计计税金额
            //double total_je = ((BigDecimal) map.get(Constants.JE_YNSE)).doubleValue();
            double total_je = hdbo.getYnse().doubleValue();
            //用合计计税金额除以每张完税证的最高金额，得出要分几张票
            int tmp = (int) Math.ceil(total_je /
                                      Double.parseDouble(Constants.WSZ_FPJE));

            Debug.out("完税证共有：" + tmp + "张");

            //开始分票
            for (int i = 0; i < tmp; i++) {

                wszZb = new Qswszz();

                //开始构造完税证数据
                wszZb.setCjr(ud.getYhmc());
                wszZb.setCjrq(now);
                wszZb.setLrr(ud.getYhid()); //为了以后汇总的时候的汇总依据，按照个人汇总的就是用这个字段
                wszZb.setLrrq(now);
                wszZb.setNd(DateUtil.getDate().substring(0, 4));
                wszZb.setClbjdm(Constants.WSZ_CLBJDM_YSB);
                wszZb.setSwjgzzjgdm(ud.getSsdwdm());
                wszZb.setSwjgzzjgmc(ud.getSsdwmc());
                wszZb.setPzzldm(Constants.WSZ_PZZLDM);
                wszZb.setJsjdm(CommonUtil.getWszJsjdm(ud, conn)); //获得完税证主表的税务机关的计算机代码
                wszZb.setFsdm(Constants.WSZ_FSDM); //只有一种方式代码——到税务机关办理方式
                wszZb.setSbbh(bo.getSbbh());
                wszZb.setSwjgzzjgmc(ud.ssdwmc); //征收机关名称

                //继续构造完税证主表对象
                wszZb.setJsfsdm(sbzb.getJsfsdm()); //缴税方式代码
                wszZb.setJsfsmc(sbzb.getJsfsmc()); //缴税名称
                //获得票证帐户的完整信息
                Zh zh = CommonUtil.getPzzhVo(ud, conn);
                wszZb.setZsddm(ud.xtsbm1); //票证帐户代码
                wszZb.setZsdmc(zh.zhmc); //征收点名称
                Debug.out("WszProcessor.doSave 征收点代码：" + ud.xtsbm1 + "张");

                wszZb.setFdcwz(tufwxx.getTdfwzldz()); //房地产位置
                wszZb.setHtclrq(tufwxx.getHtqdsj()); //房屋合同签订时间

                //区分个人还是非个人（企业）生成完税证
                //如果是个人购买的房产
                if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                    //打印完税证要按照选定的产权人打印其纳税人名称，多个纳税人名称以“，”分隔；身份证件号码和计算计代码按照选定的产权人打印，如果多个产权人按照主产权人打印
                    Grxx grxx = new Grxx();
                    if (bo.getJsjdm() == null || bo.getJsjdm().equals("")) {
                        List l = (List) DAOFactory.getInstance().getGrxxDAO().
                                 getAllBySbbh(sbzb.sbbh, conn);
                        grxx = Helper.getZcqr(l);
                        bo.setNsrmc(ActionUtil.getNsrmc(l, null));
                    } else {
                        grxx.setJsjdm(bo.getJsjdm());
                        grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                               get(grxx, conn);
                    }

                    wszZb.setNsrjsjdm(grxx.getJsjdm()); //纳税人计算机代码
                    wszZb.setNsrmc(bo.getNsrmc()); //纳税人名称
                    wszZb.setNsrdm(grxx.sfzjhm); //纳税人代码
                    wszZb.setZjhm(grxx.getSfzjhm()); //证件号码
                    wszZb.setZjlxdm(grxx.getSfzjlx()); //证件类型代码
                    wszZb.setZjlxmc(grxx.sfzjlxmc); //证件类型名称
                    wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //获得国家标准行业代码
                    wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //获得国家标准行业名称
                    wszZb.setDjzclxdm(Constants.WSZ_DJZCLX); //登记注册类型——登记注册类型
                    wszZb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //登记注册类型——登记注册类型
                    wszZb.setFsmc(Constants.WSZ_FSMC); //申报办理方式名称--常量：到税务机关办理
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
                wszMx.setNd(DateUtil.getDate().substring(0, 4));
                //获得预算级次
                wszMx.setYsjcdm(Constants.YSJCDM_DF); //给定的预算级次代码，21 地方级
                wszMx.setYsjcmc(Constants.YSJCDM_DF_MC); //给定的预算级次代码，21 地方级
                wszMx.setSkssjsrq(wszZb.getHtclrq()); //税款所属日期
                wszMx.setSkssksrq(wszZb.getHtclrq()); //税款所属日期

                //added by zhaobo 20041228
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

                System.out.println("yskm.yskmdm()契税管理通过帐务取得预算科目代码：" +
                                   yskm.yskmdm);
                System.out.println("wszMx.getYskmdm()契税管理通过帐务取得预算科目代码：" +
                                   wszMx.getYskmdm());

                //如果是只有一张完税证
                if (tmp == 1) {
                    wszZb.setYqts(new BigDecimal(0)); //逾期天数
                    wszZb.setZnjzje(new BigDecimal(0)); //滞纳金总金额
                    wszZb.setJsje(jmszje); //获得减免税总额
                    //  wszZb.setHjsjje((BigDecimal) map.get(Constants.JE_YNSE)); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”
                    wszZb.setHjsjje(hdbo.getYnse()); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”

                    wszMx.setYjhkc(wszZb.getJsje()); //由于明细中只有一条数据，所以已缴或扣除字段等于主表的减税金额字段
                    wszMx.setJsje(hdbo.getJsyj()); //计税金额等于计税依据
                    wszMx.setSjse(hdbo.getYnse()); //实纳税额就是应纳税额（复核及收款中的项）

                }
                //如果是分票的第一张完税证
                else if (i == 0 && tmp != 1) {
                    wszZb.setYqts(new BigDecimal(0)); //逾期天数
                    wszZb.setZnjzje(new BigDecimal(0)); //滞纳金总金额
                    wszZb.setJsje(jmszje); //获得减免税总额
                    wszZb.setHjsjje(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”

                    wszMx.setYjhkc(wszZb.getJsje()); //由于明细中只有一条数据，所以已缴或扣除字段等于主表的减税金额字段
                    wszMx.setJsje(hdbo.getJsyj()); //计税金额等于计税依据
                    wszMx.setSjse(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //实纳税额就是应纳税额（复核及收款中的项）
                }
                //如果是分票的最后一张完税证
                else if (i != 0 && i == (tmp - 1)) {
                    double x = (hdbo.getYnse()).
                               doubleValue() -
                               Double.parseDouble(Constants.WSZ_FPJE) * i;
                    wszZb.setHjsjje(new BigDecimal(x)); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”
                    wszMx.setSjse(new BigDecimal(x)); //实纳税额就是应纳税额（复核及收款中的项）
                }
                //如果是分票的中间几张完税证，既不是第一张也不是最后一张
                else {
                    wszZb.setHjsjje(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”
                    wszMx.setSjse(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //实纳税额就是应纳税额（复核及收款中的项）
                }

                wszZb.getMxList().add(wszMx);

                wszList.add(wszZb);
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
                    throw new ApplicationException("插入税费的减免申报信息的时候报错！");
                }
                Debug.out("插入减免申报成功！");
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return wszList;
    }


    /**
     * 撤销完税证
     * @param vo VOPackage
     * @throws BaseException
     */
    private void cxWsz(VOPackage vo) throws BaseException {
        QueryWszBo bo = new QueryWszBo();
        UserData ud = vo.getUserData();
        bo = (QueryWszBo) vo.getData();

        Qswszz wszVo = new Qswszz();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //查询完税证主表
            wszVo.setWszh(bo.getStartWszh());
            wszVo.setNdzb(bo.getNdzb());
            //wszVo.setPzzldm(Constants.WSZ_PZZLDM);
            wszVo.setPzzldm(bo.getPzzldm());
            System.out.println("wszProcessor.cxWsz 传入参数：wszh=="
                               + wszVo.getWszh() + " ndzb==" + wszVo.getNdzb() +
                               " pzzldm==" + wszVo.getPzzldm());
            System.out.println("wszProcessor.cxWsz 传入参数：pzzhdm=="
                               + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2);

            wszVo = (Qswszz) DAOFactory.getInstance().getQswszzDAO().get(wszVo,
                    conn);

            if (wszVo == null) {
                throw new Exception("对不起，没有找到与该完税证号匹配的记录！！！");
            }

            //如果该完税证已汇总
            if (wszVo.getSbhzdh() != null && !wszVo.getSbhzdh().equals("")) {
                throw new Exception("该完税证已汇总，请先撤销汇总缴款书再撤销完税证！！！");
            }

            if (wszVo.getJbdh() != null && !wszVo.getJbdh().equals("")) {
                throw new Exception("该完税证已结报，不能撤销！！！");
            }

            //获得明细完税数据
            Qswszmx mxVo = new Qswszmx();
            String conditions = "where ndzb = '" + wszVo.getNdzb() +
                                "'  and pzzldm = '" + wszVo.getPzzldm() +
                                "'  and wszh = '" + wszVo.getWszh() + "' ";
            ArrayList mxList = (ArrayList) DAOFactory.getInstance().
                               getQswszmxDAO().query(conditions, conn);
            if (mxList != null && mxList.size() > 0) {
                mxVo = (Qswszmx) mxList.get(0);
                if (!mxVo.getJzbz().equals("000000")) {
                    throw new Exception("该完税证已记帐，不能撤销！！！");
                }
            }

            System.out.println("wszProcessor.cxWsz 查询条件：" + conditions);
            //查询分票的完税证，如果有，一起撤销删除
            ArrayList wszList = (ArrayList) DAOFactory.getInstance().
                                getQswszzDAO().query(wszVo, conn);
            for (Iterator iter = wszList.iterator(); iter.hasNext(); ) {
                wszVo = (Qswszz) iter.next();
                //作废撤销的完税证号
                try {
                    System.out.println(
                            "wszProcessor.cxWsz 传入setCancellation参数：wszh=="
                            + wszVo.getWszh() + " ndzb==" + wszVo.getNdzb() +
                            " pzzldm==" + wszVo.getPzzldm());
                    ServiceProxy.setCancellation(ud, wszVo.getPzzldm(),
                                                 wszVo.getNdzb() +
                                                 wszVo.getWszh(),
                                                 DataConvert.round(wszVo.
                            getHjsjje(), 2).doubleValue(), "1", "0", "1");
                    Debug.out("作废成功....，作废撤销的完税证号！ wszh==" + wszVo.getWszh() +
                              " ndzb==" + wszVo.getNdzb());
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                    Debug.out(ex);
                    throw new ApplicationException("撤销完税证号码的同时，作废票证管理中的该票出错！");
                }

                StringBuffer condition = new StringBuffer("");
                condition.append("wszh = '").append(wszVo.getWszh())
                        .append("' AND ndzb = '").append(wszVo.getNdzb())
                        .append("' AND pzzldm = '").append(wszVo.getPzzldm()).
                        append("' ");
                DAOFactory.getInstance().getQswszmxDAO().delete(condition, conn);
                Debug.out("撤销完税证序号为" + wszVo.wszh + "明细成功");
                DAOFactory.getInstance().getQswszzDAO().delete(condition, conn);
                Debug.out("撤销完税证序号为" + wszVo.wszh + "主表成功");

            }

            //修改申报主表的状态标识
            //恢复标记为已复核
            DAOFactory.getInstance().getSbzbDAO().update(Constants.ZB_ZTBS_YFH,
                    wszVo.getSbbh(), conn);

            //删除减免申报表的数据
            HashMap map = new HashMap();
            map.put("jsjdm", wszVo.jsjdm);
            map.put("szsmdm", mxVo.getSzsmdm());
            map.put("cjrq", wszVo.cjrq);
            //删除减免申报表的数据
            if (!CommonUtil.deleteSBJM(map)) {
                throw new ApplicationException("无法删除减免申报表的数据，调用接口出错！");
            }
            Debug.out("撤销减免申报成功！");
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            ex.printStackTrace();
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * 将汇总的完税证分票，计算实纳金额和取缴款书号
     * @param list ArrayList
     * @return ArrayList
     */
    private ArrayList JksFP(ArrayList list, VOPackage vo) throws Exception {
        ArrayList fpList = new ArrayList(); //返回的已分好票的契税完税证汇总信息集合

        Qswszhz fpVo = new Qswszhz(); //已分好票的完税证汇总表vo，用于生成缴款书主表

        WszBo bo = new WszBo();
        bo = (WszBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

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

            if (list.size() <= 4) {
                //给完税证汇总表vo补充数据，并分票
                fpVo.setCjrq(now);
                fpVo.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
                Debug.out("bo.getHzfs() is '" + bo.getHzfs() + "'");
                fpVo.setHzfs(bo.getHzfs());
                fpVo.setHzjsrq(DataConvert.String2Timestamp(bo.getHzjzrq()));
                fpVo.setHzksrq(DataConvert.String2Timestamp(bo.getHzqsrq()));
                fpVo.setHzrq(now);
                fpVo.setJsjdm(jsjdm);
                Debug.out("jsjdm is " + jsjdm);
                fpVo.setCjr(ud.getYhid());
                fpVo.setLrr(ud.getYhid());
                fpVo.setLrrq(now);
                fpVo.setNd(DateUtil.getDate().substring(0, 4));
                fpVo.setSbhzdh(sbhzdh);
                fpVo.setSwjgzzjgdm(swjgzzjgDm);
                fpVo.setHzfsmc(bo.getHzfsmc());
                fpVo.setJsfsmc(bo.getJsfsmc());
                //获得票证帐户的完整信息
                Zh zh = CommonUtil.getPzzhVo(ud, conn);
                fpVo.setZsddm(ud.xtsbm1); //票证帐户代码
                fpVo.setZsdmc(zh.zhmc); //征收点名称

                //如果税种税目小于等于4，也就是一张缴款书
                double sjse = 0.0;
                for (int i = 0; i < list.size(); i++) {
                    sjse += ((Sbjkmx) list.get(i)).getSjse().doubleValue();
                }
                fpVo.setSjse(new BigDecimal(sjse)); //实缴金额
                //取得缴款凭证号
                String jkpzh = JksUtil.getJkpzh(fpVo.getJsjdm());
                Debug.out("取得缴款凭证号 : " + jkpzh);
                fpVo.setJkpzh(jkpzh);

                fpList.add(fpVo);
            } else { //如果税种税目等于5
                for (int j = 0; j <= 1; j++) {
                    fpVo = new Qswszhz();

                    //给完税证汇总表vo补充数据，并分票
                    fpVo.setCjrq(now);
                    fpVo.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
                    fpVo.setHzfs(bo.getHzfs());
                    fpVo.setHzjsrq(DataConvert.String2Timestamp(bo.getHzjzrq()));
                    fpVo.setHzksrq(DataConvert.String2Timestamp(bo.getHzqsrq()));
                    fpVo.setHzrq(now);
                    fpVo.setJsjdm(jsjdm);
                    fpVo.setCjr(ud.getYhid());
                    fpVo.setLrr(ud.getYhid());
                    fpVo.setLrrq(now);
                    fpVo.setNd(DateUtil.getDate().substring(0, 4));
                    fpVo.setSbhzdh(sbhzdh);
                    fpVo.setSwjgzzjgdm(swjgzzjgDm);
                    fpVo.setHzfsmc(bo.getHzfsmc());
                    fpVo.setJsfsmc(bo.getJsfsmc());
                    //获得票证帐户的完整信息
                    Zh zh = CommonUtil.getPzzhVo(ud, conn);
                    fpVo.setZsddm(ud.xtsbm1); //票证帐户代码
                    fpVo.setZsdmc(zh.zhmc); //征收点名称

                    double sjse = 0.0;
                    if (j == 0) {
                        for (int i = 0; i < 4; i++) { //先取前4种组成一张缴款书
                            sjse += ((Sbjkmx) list.get(i)).getSjse().
                                    doubleValue();
                        }
                    } else {
                        sjse += ((Sbjkmx) list.get(4)).getSjse().doubleValue();
                    }

                    //实缴金额
                    fpVo.setSjse(new BigDecimal(sjse));

                    //取得缴款凭证号
                    String jkpzh = JksUtil.getJkpzh(fpVo.getJsjdm());
                    Debug.out("缴款凭证号：" + jkpzh);
                    fpVo.setJkpzh(jkpzh);

                    fpList.add(fpVo);
                }
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw new Exception("缴款书分票发生错误！！！");
        }

        return fpList;
    }

    /**
     * 完税证打印后update处理标记代码的方法
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Qswszz wszVo = new Qswszz();
        wszVo = (Qswszz) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer(" WHERE ");

            condition.append("wszh = '").append(wszVo.getWszh()).append("' ")
                    .append("AND ndzb = '").append(wszVo.getNdzb()).append("' ")
                    .append(" AND pzzldm = '").append(wszVo.getPzzldm()).
                    append("' ");

            DAOFactory.getInstance().getQswszzDAO().update(condition, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    private Object doPrint(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData(); //取到当前用户对象
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = bo.getQswszzVo(); //构造完税证主表的数据
        Qswszmx wszMx = bo.getMxVo(); //构造完税证明细的数据

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            //获得完税证号
            try {
                System.out.println("wszProcessor.doPrint 传入参数：pzzhdm=="
                                   + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2 +
                                   " pzzldm==" + wszZb.getPzzldm());
                String retResult = ServiceProxy.getNumber(ud,
                        wszZb.getPzzldm(),
                        DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                        "1", "1");
                wszZb.setNdzb(retResult.substring(0, 4)); //年度字别
                wszZb.setWszh(retResult.substring(4)); //完税证号
                wszMx.setWszh(wszZb.getWszh());
                wszMx.setNdzb(wszZb.getNdzb()); //年度字别
                Debug.out("取得契税完税证号...." + wszZb.wszh + "  " + wszZb.ndzb);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("获取完税证失败！");
            }

            //更新主表数据
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("插入完税证主表成功....");

                DAOFactory.getInstance().getQswszmxDAO().insert(wszMx, conn);
                Debug.out("插入完税证明细成功....");

                //更新申报主表状态位
                String ztbs = Constants.ZB_ZTBS_YRK;
                DAOFactory.getInstance().getSbzbDAO().update(ztbs, wszZb.sbbh,
                        conn);
                Debug.out("更新申报主表状态位成功，标记为“已入库”....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("保存完税证到数据库失败！");
            }
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);
        } catch (Exception ex) {
            //保存不成功，则作废刚刚取出的完税证号！
            try {
                //只有当已经取得完税证号后，如果上面的操作有问题才需要作废取来的完税证号
                if (wszZb.getWszh() != null && !wszZb.getWszh().equals("")) {
                    ServiceProxy.setCancellation(
                            ud, wszZb.getPzzldm(),
                            wszZb.getNdzb() + wszZb.getWszh(),
                            DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                            "1", "0", "1");
                    Debug.out("保存不成功....，作废取得完税证号！");

                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }

            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，打印操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private Object doChangePrint(VOPackage vo) throws BaseException {

        UserData ud = vo.getUserData(); //取到当前用户对象
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = bo.getQswszzVo(); //构造完税证主表的数据

        String old_wszh = wszZb.getWszh();
        String old_ndzb = wszZb.getNdzb();
        String old_pzzldm = wszZb.getPzzldm();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            try {
                System.out.println("wszProcessor.doChangePrint 传入参数：old_wszh=="
                                   + old_wszh + " old_ndzb==" + old_ndzb +
                                   " pzzhdm=="
                                   + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2 +
                                   " pzzldm==" + old_pzzldm);
                //只有当已经取得完税证号后，如果上面的操作有问题才需要作废取来的完税证号
                if (old_wszh != null && !old_wszh.equals("")) {
                    ServiceProxy.setCancellation(
                            ud, old_pzzldm, old_ndzb + old_wszh,
                            DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                            "1",
                            "0", "1");
                    Debug.out("保存不成功....，作废取得完税证号！");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("变号打印时作废完税证失败！");
            }

            try {
                //获得完税证号
                String retResult = ServiceProxy.getNumber(ud,
                        wszZb.getPzzldm(),
                        DataConvert.round(wszZb.
                                          getHjsjje(), 2).doubleValue(), "1",
                        "1");

                wszZb.setNdzb(retResult.substring(0, 4)); //年度字别
                wszZb.setWszh(retResult.substring(4)); //完税证号

                Debug.out("取得契税完税证号...." + wszZb.wszh + "  " + wszZb.ndzb);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("变号打印时获取完税证失败！");
            }

            //插入一条新的主表数据
            try {
                //由于有外键，要先在完税证主表中insert一条新的数据（换号后的完税证）
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("完税证换号插入完税证主表成功....old_wszh==" + old_wszh +
                          " new_wszh==" + wszZb.wszh);
            } catch (BaseException ex1) {
                ex1.printStackTrace();
                Debug.out(ex1);
                throw new ApplicationException("变号打印时完税证换号插入主表信息出错！");
            }

            String sql = "UPDATE sbdb.sb_jl_qswszmx t SET t.wszh = '" +
                         wszZb.getWszh() + "' WHERE t.wszh = '" + old_wszh
                         + "' AND t.ndzb = '" + old_ndzb + "' AND t.pzzldm = '" +
                         old_pzzldm + "' ";
            //更新明细表数据
            try {
                //然后更新明细表中的数据
                DAOFactory.getInstance().getQswszmxDAO().update(sql, conn);
                Debug.out("完税证换号更新完税证明细成功....");
                Debug.out(sql);

            } catch (BaseException ex6) {
                ex6.printStackTrace();
                throw new ApplicationException("变号打印时完税证换号更新明细表信息出错！");
            }

            sql = "DELETE sbdb.sb_jl_qswszz t WHERE t.wszh = '" + old_wszh
                  + "' AND t.ndzb = '" + old_ndzb + "' AND t.pzzldm = '" +
                  old_pzzldm + "' ";
            //删掉主表中原来的数据
            try {
                //最后删掉主表中原来的数据
                DAOFactory.getInstance().getQswszzDAO().update(sql, conn);
                Debug.out("完税证换号删除完税证主表成功....");
                Debug.out(sql);
            } catch (BaseException ex1) {
                ex1.printStackTrace();
                Debug.out(ex1);
                throw new ApplicationException("变号打印时完税证换号删除主表信息出错！");
            }

        } catch (Exception ex) {
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－WszProcessor，变号打印操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");
            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    public static void main(String[] args) {
        VOPackage vo = new VOPackage();
        //测试汇总的bo
        /*
                 HzWszBo bo = new HzWszBo();
                 bo.setHzfs("1");
                 bo.setJsfs("1");
                 bo.setHzqsrq("2004-12-01");
                 bo.setHzjzrq("2004-12-31");*/

        //测试撤销缴款书的bo
        /*CxJksBo bo = new CxJksBo();
                 bo.setJkpzh("069990100412018");
                 bo.setJsjdm("06999010");*/

        //测试撤销完税证的bo
        WszBo bo = new WszBo();
        bo.setNdzb("2004");
        bo.setWszh("22222222");

        UserData ud = new UserData();
        ud.yhid = "hanl";
        ud.ssdwdm = "0601";
        ud.yhmc = "韩雷";
        ud.xtsbm1 = "0106010017";

        vo.setData(bo);
        vo.setUserData(ud);

        WszProcessor p = new WszProcessor();
        try {
            p.cxWsz(vo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
