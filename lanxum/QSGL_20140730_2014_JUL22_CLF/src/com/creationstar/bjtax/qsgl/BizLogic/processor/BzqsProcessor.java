package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BzqsBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBzqsBo;
import com.creationstar.bjtax.qsgl.util.Arith;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 不征契税的processor
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
public class BzqsProcessor implements Processor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is BzqsProcessor.process(vo)");

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

        case ActionType.DELETE:

            doDelete(vo);

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
    private BzqsBo doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        BzqsBo bo = (BzqsBo) vo.getData();
        try {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();
            //从前台传送来的bo在此组织成后台各个表要用的数据，分别进行保存
            /**
             * 1先获取主表sbzb需要的信息
             * 2获取个人信息表grxx的信息
             * 3获取土地房屋信息表tdfwxx
             * 4获取申报主表与土地、房屋信息表的关联表SBTDFWGL信息
             * 5执行插入操作
             */
            Sbzb sbzb = getSbzb(bo, user);
            sbzb.setCjrq(new Timestamp(System.currentTimeMillis()));
            //从commonutil中获取申报表号和当前时间,设到各个vo中
            //此3个表有申报表号
            sbzb.setSbbh(CommonUtil.getBZBH(user.getXtsbm1(), conn));
            List l = getGrxx(bo, user, sbzb.getSbbh(), sbzb.getCjrq());
            Tufwxx tufwxx = getTufwxx(bo, user);
            Sbtdfwgl sbtdfwgl = getSbtdfwgl(bo, user);
            Fgrxx fgrxx = getFgrxx(bo, user);
            sbtdfwgl.setSbbh(sbzb.getSbbh());
            fgrxx.setSbbh(sbzb.getSbbh());
            //设置时间
            sbzb.setLrrq(sbzb.getCjrq());
            sbzb.setSbrq(sbzb.getCjrq());
            fgrxx.setLrrq(sbzb.getCjrq());
            fgrxx.setCjrq(sbzb.getCjrq());
            tufwxx.setCjrq(sbzb.getCjrq());
            tufwxx.setLrrq(sbzb.getCjrq());
            sbtdfwgl.setCjrq(sbzb.getCjrq());
            sbtdfwgl.setLrrq(sbzb.getCjrq());
            //录入人 创建人
            sbzb.setCjr(user.getYhmc());
            sbzb.setLrr(user.getYhmc());
            fgrxx.setLrr(user.getYhmc());
            fgrxx.setCjr(user.getYhmc());
            tufwxx.setCjr(user.getYhmc());
            tufwxx.setLrr(user.getYhmc());
            sbtdfwgl.setCjr(user.getYhmc());
            sbtdfwgl.setLrr(sbtdfwgl.getCjr());

            //获取土地房屋唯一标示,放入土地房屋信息表tdfwxx
            //和申报主表与土地、房屋信息表的关联表SBTDFWGL
            tufwxx.setTdfwid(CommonUtil.getTDFWID(conn));
            Debug.out("tufwxx.setTdfwid in BzqsProcessor is " +
                      tufwxx.getTdfwid());
            sbtdfwgl.setTdfwid(tufwxx.getTdfwid());

            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                Debug.out("个人信息插入");
                //个人信息获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                //计算机代码
                //grxx = CommonUtil.handleZRR(grxx,user);
                //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
                l = CommonUtil.handleZRR(l, user);
                bo.setNsrList(l);

                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("非个人信息插入");
                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
            }
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
            //将view页面需要显示的信息返回到前台
            bo.setSbbh(sbzb.getSbbh());
            bo.setCjrq(DataConvert.TimeStamp2String(sbzb.getCjrq()));

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BzqsProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }


    /**
     * 获取申报主表信息
     */
    public Sbzb getSbzb(BzqsBo bo, UserData user) {
        Sbzb sbzb = new Sbzb();
        //设置建委业务编号，modify by fujx。20081126
        sbzb.setJwywbh(bo.getJwywbh());
        //设置合同编号，modify by fujx。20081126
        sbzb.setHtbh(bo.getHtbh());
        //补录标识
        sbzb.setBlbs(Constants.ZB_BLBS_FBL);
        //办理减免税标识
        sbzb.setBljmsbs(Constants.ZB_BLJMSBS_BZ);
        //备注
        sbzb.setBz(bo.getBeizhu());
        //房屋土地部门受理号
        sbzb.setFwtdbmdm(bo.getFwtdbmslh());
        //缴税方式
        sbzb.setJsfsdm(" ");
        //缴税方式
        sbzb.setJsfsmc(" ");
        //计税金额 (免征为0)
        sbzb.setJsje(new BigDecimal(0));
        //用户标识
        sbzb.setYhbs(bo.getYhbs());
        //纳税人类型代码
        if (sbzb.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            sbzb.setNsrlxdm("99");
        } else {
            sbzb.setNsrlxdm(bo.getNsrlx());
        }

        //税率(免征为0)
        sbzb.setSl(new BigDecimal(0));
        //税务机关组织机构代码
        sbzb.setSwjgzzjgdm(user.getSsdwdm());

        //应纳税额(免征为0)
        sbzb.setYnse(new BigDecimal(0));
        //征收人员名称
        sbzb.setZsrymc(user.getYhmc());
        //状态标识
        sbzb.setZtbs(bo.getZtbs());
        //票证帐户代码
        sbzb.setPzzhdm(user.getXtsbm1());
        //票证帐户名称
        sbzb.setPzzhmc(user.getXtsbm2());
        return sbzb;
    }


    /**
     * 获取个人信息表信息
     */
    public List getGrxx(BzqsBo bo, UserData user, String sbbh, Timestamp now) throws
            BaseException {
        //构造个人信息
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

        //联系电话
        //grxx.setLxdh(bo.getLxdh());
        //纳税人名称
        //grxx.setNsrmc(bo.getNsrmc());
        //证件号码
        //grxx.setSfzjhm(bo.getSfzjhm());
        //证件类型
        //grxx.setSfzjlx(bo.getSfzjlx());
        //证件类型名称
        //grxx.setSfzjlxmc(bo.getSfzjlxmc());
        //国籍代码,国籍名称
        //grxx.setGjdm(bo.getGjdm());
        //grxx.setGjmc(bo.getGjmc());


        return l;
    }

    /**
     * 得到非个人信息的vo
     * @param bo BzqsBo
     * @param user UserData
     * @return Fgrxx
     */
    public Fgrxx getFgrxx(BzqsBo bo, UserData user) {
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
    public Tufwxx getTufwxx(BzqsBo bo, UserData user) {
        Tufwxx tufwxx = new Tufwxx();
        try {
            //备注
            tufwxx.setBz(bo.getBeizhu());
            //币种代码
            tufwxx.setBzdm(bo.getBz());
            //成交价格（人民币）
            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()));
            //成交价格（外币）
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()));
            //房地产项目名称
            tufwxx.setFdcxmmc(bo.getFdcxmmc());
            //分类
            tufwxx.setFldm(bo.getFl());
            //分类名称
            tufwxx.setFlmc(bo.getFlmc());
            //房屋建筑面积
            //修改为小数点三位modify by fujx，20081126
            if (null == bo.getFwjzmj() || "".equals(bo.getFwjzmj())) {
                tufwxx.setFwjzmj(DataConvert.String2BigDecimal(Arith.roundStr(
                        "0.000", 3)));
            } else {
                tufwxx.setFwjzmj(DataConvert.String2BigDecimal(Arith.roundStr(
                        bo.getFwjzmj(), 3)));
            }

            //房屋类别
            tufwxx.setFwlxdm(bo.getFwlb());
            //汇率
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()));
            //合同（契约）签订时间
            String date = bo.getHyqdsj();
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(date));
            //年度
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));
            //评估价格（人民币）
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()));
            //土地、房屋权属转移类型
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx());
            //土地、房屋权属转移面积
            //修改为小数点三位modify by fujx，20081126
            if (null == bo.getTdfwqszymj() || "".equals(bo.getTdfwqszymj())) {
                tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(Arith.
                        roundStr("0.000", 3)));
            } else {
                tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(Arith.
                        roundStr(bo.getTdfwqszymj(), 3)));
            }
            //土地、房屋座落地址
            tufwxx.setTdfwzldz(bo.getTdfwzldz());
            Debug.out("bo.getZhyrmb()" + bo.getZhyrmb());
            //折合价格（人民币）
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()));
            //分类名称
            tufwxx.setFlmc(bo.getFlmc());
            //币种，房屋类别，土地房屋权属转移
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
    public Sbtdfwgl getSbtdfwgl(BzqsBo bo, UserData user) {
        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
        //创建人
        sbtdfwgl.setCjr(user.getYhmc());
        //录入人
        sbtdfwgl.setLrr(sbtdfwgl.getCjr());

        return sbtdfwgl;
    }


    /**
     * 条件: 申报表号\纳税人类型\纳税人名称\房地产项目名称\身份证件类型代码\身份证件号码
     * 1、如果申报表号有，则
     *    优先根据申报表号查询申报主表，从而关联查询个人、非个人信息和房屋土地的信息
     *
     * 2、如果申报表号查询条件为空，状态标识为个人，则：
     *    按照纳税人类型、纳税人名称、身份证件类型、身份证件号码、房地产项目名称 5个条件查询，从而关联其他
     *
     * 3、如果申报表号为空，状态标识为非个人
     *    按照按照纳税人类型、纳税人名称、房地产项目名称、在非个人的表中进行查询，从而关联其他
     *
     * 4、如果申报表号为空，状态标识为空，则：
     *    根据\纳税人类型\纳税人名称\房地产项目名称\身份证件类型代码\身份证件号码
     *
     * 如果以后有其他的查询条件，只要在四种不同的情况中加入更多地代码就可以了
     *
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        /** @todo 需要补充一个查询条件--主表中的作废标识 */
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        QueryBzqsBo bo = (QueryBzqsBo) vo.getData();
        try {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();
            //从前台传送来的bo在此组织成后台各个表要用的数据，分别进行保存

            //根据bo中的sbbh查询
            if (bo.getSbbh() != null && !bo.getSbbh().equals("")) {
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(bo.getSbbh());
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);
                bo.setSbzb(sbzb);

                //如果主表中的状态不是不征契税，则返回一个空的arraylist
                if (!(sbzb.getBljmsbs().equalsIgnoreCase(Constants.
                        ZB_BLJMSBS_BZ))) {
                    return resultList;
                }

                //查询个人信息
                if (Constants.ZB_NSRLX_GR.equals(sbzb.getNsrlxdm())) {
                    List l = (List) DAOFactory.getInstance().getGrxxDAO().
                             getAllBySbbh(sbzb.getSbbh(), conn);
                    bo.setNsrList(l);
                }
                //查询非个人信息
                else {
                    Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                                  getBySbbh(sbzb.getSbbh(), conn);
                    bo.setFgrxx(fgrxx);
                }

                //查询土地、房屋信息
                Tufwxx tf = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.getSbbh(), conn);
                bo.setTufwxx(tf);
                resultList.add(bo);
            }
            //如果申报表号查询条件为空，则：
            else {
                //查询条件
                StringBuffer condition = new StringBuffer("");
                condition.append(" AND a.bljmsbs = '").append(Constants.
                        ZB_BLJMSBS_BZ)
                        .append("' "); //代表不征契税
                Debug.out("nsrlx=" + bo.getNsrlx());
                //纳税人类型为个人：
                //按照纳税人名称、身份证件类型、身份证件号码、计算机代码、房地产项目名称查询
                if (Constants.ZB_NSRLX_GR.equals(bo.getNsrlx())) {

                    if (bo.getJsjdm() != null && !bo.getJsjdm().equals("")) {
                        condition.append(" AND b.jsjdm = '").append(bo.getJsjdm()).
                                append("' ");
                    }
                    if (bo.getZjlxdm() != null && !bo.getZjlxdm().equals("")) {
                        condition.append(" AND b.sfzjlx = '").append(bo.
                                getZjlxdm())
                                .append("' ");
                    }
                    if (bo.getSfzjhm() != null && !bo.getSfzjhm().equals("")) {
                        condition.append(" AND b.sfzjhm = '").append(bo.
                                getSfzjhm())
                                .append("' ");
                    }
                    if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                        condition.append(" AND b.nsrmc LIKE '%").append(bo.
                                getNsrmc())
                                .append("%' ");
                    }
                    if (bo.getFdcxmmc() != null && !bo.getFdcxmmc().equals("")) {
                        condition.append(" AND d.fdcxmmc LIKE '%").append(bo.
                                getFdcxmmc())
                                .append("%' ");
                    }
                    resultList = (ArrayList) DAOFactory.getInstance().
                                 getSbzbDAO()
                                 .queryBzqs(condition.toString(), conn, true);

                }
                //纳税人类型为非个人，则：
                else {
                    //按照纳税人名称、纳税人类型、计算机代码、房地产项目名称查询
                    if (bo.getJsjdm() != null && !bo.getJsjdm().equals("")) {
                        condition.append(" AND b.jsjdm = '").append(bo.getJsjdm()).
                                append("' ");
                    }
                    if (bo.getNsrlx() != null && !bo.getNsrlx().equals("")) {
                        condition.append(" AND b.nsrlxdm = '").append(bo.
                                getNsrlx()).
                                append("' ");
                    }
                    if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                        condition.append(" AND b.nsrmc LIKE '%").append(bo.
                                getNsrmc())
                                .append("%' ");
                    }
                    if (bo.getFdcxmmc() != null && !bo.getFdcxmmc().equals("")) {
                        condition.append(" AND d.fdcxmmc LIKE '%").append(bo.
                                getFdcxmmc())
                                .append("%' ");
                    }
                    resultList = (ArrayList) DAOFactory.getInstance().
                                 getSbzbDAO()
                                 .queryBzqs(condition.toString(), conn, false);

                }
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BzqsProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * 处理不征契税modify的方法
     * @param vo VOPackage
     */
    /* public void doModify(VOPackage vo) throws BaseException
         {
        ArrayList list = new ArrayList();

        BzqsBo bo = (BzqsBo)vo.getData();

        Sbzb sbzb = new Sbzb();
        Grxx grxx = new Grxx();
        List l=new ArrayList();
        Fgrxx fgrxx = new Fgrxx();
        Tufwxx tufwxx = new Tufwxx();

        Connection conn = null;

        try
        {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            //根据申报表号取得相应的主表vo
            sbzb.setSbbh(bo.getSbbh());
            sbzb = (Sbzb)DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //根据申报表号取得相应的土地房屋信息vo
            tufwxx = (Tufwxx)DAOFactory.getInstance().getTufwxxDAO().getBySbbh(bo.getSbbh(), conn);

            //根据修改页面传过来的bo，更新主表vo和土地房屋信息表中的值以update数据库
            sbzb.setFwtdbmdm(bo.getFwtdbmslh() );
            sbzb.setBz(bo.getBeizhu() );

            tufwxx.setFdcxmmc(bo.getFdcxmmc() );
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(bo.getHyqdsj()) );
            tufwxx.setFldm(bo.getFl() );
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx() );
            tufwxx.setFwlxdm(bo.getFwlb() );
            tufwxx.setTdfwzldz(bo.getTdfwzldz() );
     tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(bo.getTdfwqszymj()) );
            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(bo.getFwjzmj()) );
     tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()) );
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()) );
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()) );
            tufwxx.setBzdm(bo.getBz() );
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()) );
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()) );

            if (Constants.ZB_NSRLX_GR.equals(bo.getNsrlx()))
            {
                //根据申报表号，取得个人信息表的vo
     l = (List)DAOFactory.getInstance().getGrxxDAO().getBySbbh(bo.getSbbh(), conn);
                //根据修改页面传过来的bo，更新vo中的值以update数据库
                grxx.setNsrmc(bo.getNsrmc());
                grxx.setLxdh(bo.getLxdh() );
                grxx.setSfzjlx(bo.getSfzjlx() );
                grxx.setSfzjhm(bo.getSfzjhm() );
            }
            else
            {
                fgrxx = (Fgrxx)DAOFactory.getInstance().getFgrxxDAO().getBySbbh(bo.getSbbh(), conn);

            }

            //更新主表的记录
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            //更新土地房屋信息表的记录
            DAOFactory.getInstance().getTufwxxDAO().update(tufwxx, conn);

            if(grxx != null )
            {
                DAOFactory.getInstance().getGrxxDAO().update(grxx, conn);
            }
            else if(fgrxx != null )
            {
                DAOFactory.getInstance().getFgrxxDAO().update(fgrxx, conn);
            }
        }
        catch (Exception ex)
        {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BzqsProcessor，操作出错",
     new StackMsg2StringUtil(ex,Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            QSDBUtil.freeConnection(conn);
        }
         }
     */

    /**
     *删除符合条件的记录
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doDelete(VOPackage vo) throws BaseException {
        QueryBzqsBo bo = new QueryBzqsBo();
        Connection conn = null;

        try {
            //vo.getUserData();
            ArrayList list = (ArrayList) vo.getData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            DAOFactory.getInstance().getSbzbDAO().deleteBZQS(list, conn,
                    Constants.ZB_BLJMSBS_BZ);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BzqsProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}
