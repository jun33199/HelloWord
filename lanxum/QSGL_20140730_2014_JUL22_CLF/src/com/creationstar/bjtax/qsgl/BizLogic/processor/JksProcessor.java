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
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
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
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
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
 * @author 赵博
 * @version 1.0
 */
public class JksProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is JksProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        //通过缴款书的缴税方式生成的缴款书
        case ActionType.CREATE_JKS:
            result = doCBPJks(vo);
            break;

        case ActionType.CX_JKS:
            doCxJks(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.JKS_QUERY_WSZ:
            result = doQueryWsz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 根据前台传来的申报表号创建缴款书数据
     * 并保存缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Sbjkzb doCBPJks(VOPackage vo) throws BaseException {
        //获取前台传来的申报表号
        JksBo bo = (JksBo) vo.getData();
        String sbbh = bo.getSbbh();
        //获得UserData
        UserData ud = vo.getUserData();
        //构造缴款书主表数据
        Sbjkzb sbjkzb = new Sbjkzb();
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Timestamp nowTime = CommonUtil.getDBtime(conn);
            //税务机关的计算机代码
            //由于这个方法是为税务分局所辖的企业通过银行转帐的方式缴税，
            //而生成缴款书的。计算机代码就应该是该所辖企业的计算机代码
            //如果不是所辖的企业，则应该通过征收人员手工的方式，按照零散征收的方式录入生成缴款书
            //就不应该走这个方法了；
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //纳税人计算机代码
            String nsrjsjdm = "";
            //通过构造申报主表，赋值申报表号，获得完整的申报主表数据
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            Debug.out("直接生成缴款书方式：已经取得了申报主表的完整数据.... sbbh " + sbzb.sbbh);

            //一定要排除已经生成过缴款书的
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("该笔申报已经打印缴款书了，不能够再次生成缴款书！");
            }

            //得到土地房屋申报的基本信息
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            Debug.out("直接生成缴款书方式：已经取得了房屋基本信息的完整数据....");

            //定位Swjgzzjg数据
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);

            //如果该申报表为个人申报
            Debug.out("直接生成缴款书方式：判断个人还是非个人....");
            if (Constants.YHBZ_GR.equals(sbzb.getYhbs())) {
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

                nsrjsjdm = grxx.getJsjdm();
                //得到纳税人的登记基本数据
                sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //登记注册类型
                sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //登记注册类型名称
                sbjkzb.setJydzlxdm(grxx.getLxdh()); //联系电话
                sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //国家标准行业代码
                sbjkzb.setNsrmc(bo.getNsrmc()); //纳税人名称
                sbjkzb.setJsjdm(nsrjsjdm);
                sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //得到缴款凭证号
                Debug.out("直接生成缴款书方式：个人方式、并且已经将个人方式的属性值赋到Sbjkzb中....");
            }
            //如果为非个人申报
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbbh, conn);
                sbjkzb.setYhdm(fgrxx.getKhyhdm()); //银行代码
                sbjkzb.setYhmc(fgrxx.getKhyhmc()); //银行名称
                sbjkzb.setZh(fgrxx.getYhzh()); //银行帐号
                sbjkzb.setJydzlxdm(fgrxx.getLxdh()); //联系电话
                nsrjsjdm = fgrxx.getJsjdm();
                //得到纳税人的登记基本数据
                SWDJJBSJ jbsj = null;
                try {
                    jbsj = CommonUtil.getFgrJBSJ(nsrjsjdm);
                } catch (Exception ex1) {
                    Debug.out(ex1);
                    throw ExceptionUtil.getBaseException(ex1, "获取法人的登记信息出错！");
                }
                if (jbsj == null) {
                    throw new ApplicationException("获取法人登记信息出错！");
                }
                sbjkzb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm()); //税务机关组织机构代码
                sbjkzb.setSwjgzzjgmc(jbsj.getSwjgzzjgmc()); //税务机关组织机构名称
                sbjkzb.setLsgxdm(jbsj.getLsgxdm()); //隶属关系
                sbjkzb.setLsgxmc(jbsj.getLsgxmc()); //隶属关系名称
                sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //登记注册类型
                sbjkzb.setDjzclxmc(jbsj.getDjzclxmc()); //登记注册类型名称
                sbjkzb.setJydzlxdm(jbsj.getJydzlxdm()); //经营地址联系电话
                sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //国家标准行业代码
                sbjkzb.setGjbzhymc(jbsj.getGjbzhymc()); //国家标准行业名称
                sbjkzb.setNsrmc(jbsj.getNsrmc()); //纳税人名称
                //判断是否非本局的企业用户
                String nsrSsQxdm = jbsj.getSwjgzzjgdm().substring(0, 2);
                Debug.out("判断是否非本局的企业用户 is " + nsrSsQxdm);
                if (swjgzzjg.qxfjdm != null &&
                    swjgzzjg.qxfjdm.substring(0, 2).equals(nsrSsQxdm)) {
                    //对于非个人来说，本局的所辖企业的缴款书的计算机代码就是纳税人的计算机代码
                    sbjkzb.setJsjdm(nsrjsjdm);
                    sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //得到缴款凭证号
                } else { //非本区所辖企业的，缴款书的计算机代码就是当前税务所的计算机代码
                    sbjkzb.setJsjdm(swjgzzjg.jsjdm);
                    sbjkzb.setJkpzh(JksUtil.getJkpzh(swjgzzjg.jsjdm)); //得到缴款凭证号
                }
                Debug.out("征收税务机关组织机构名称 in JksProcessor is " +
                          sbjkzb.getZsswjgzzjgmc());
                Debug.out("直接生成缴款书方式：非个人方式、并且已经将非个人的属性值赋到Sbjkzb中....");
            }
            sbjkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //国库组织机构代码
            sbjkzb.setGkzzjgmc(swjgzzjg.skgk); //国库组织机构名称
            sbjkzb.setZsswjgzzjgdm(ud.ssdwdm); //征收税务机关组织机构代码
            sbjkzb.setZsswjgzzjgmc(ud.ssdwmc); //征收税务机关组织机构名称
            sbjkzb.setSwjgzzjgdm(ud.ssdwdm); //税务机关组织机构代码
            sbjkzb.setSwjgzzjgmc(ud.ssdwmc); //税务机关组织机构名称
            sbjkzb.setSklxdm(Constants.JKS_SKLXDM_ZCJK); //税款类型
            sbjkzb.setSklxmc(Constants.JKS_SKLXDM_ZCJK_MC); //税款类型名称
            sbjkzb.setFsdm(Constants.WSZ_FSDM); //申报方式代码——都为税务机关上门方式
            sbjkzb.setSbbh(sbbh); //申报表编号
            sbjkzb.setYsjcdm(Constants.YSJCDM_DF); //预算级次
            sbjkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //预算级次名称
            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            sbjkzb.setSjje(hdbo.getYnse()); //实缴金额
            sbjkzb.setRkje(hdbo.getYnse()); //入库金额
            //保存减免税总金额到申报缴款的备注字段
            BigDecimal jmszje = hdbo.getJmzje();
            sbjkzb.setBz("减免税总金额：" + DataConvert.BigDecimal2String(jmszje, 2) +
                         "  缴税方式：" + sbzb.getJsfsmc());
            sbjkzb.setZsswjgzzjgdm(ud.ssdwdm); //征收机关代码
            sbjkzb.setSzdm(Constants.WSZ_QSSZDM); //税种代码
            sbjkzb.setSzmc(Constants.WSZ_QSSZMC); //税种名称
            Yskm yskm = CommonUtil.getYskm(sbjkzb.getSzdm(), conn);
            sbjkzb.setYskmdm(yskm.yskmdm); //预算科目
            sbjkzb.setYskmmc(yskm.yskmmc); //预算科目名称
            sbjkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //处理标记代码
            sbjkzb.setLrr(ud.getYhid()); //录入人代码
            sbjkzb.setSbrq(sbzb.getSbrq()); //申报日期
            sbjkzb.setCjrq(nowTime); //创建日期
            sbjkzb.setLrrq(nowTime); //录入日期
            sbjkzb.setZyrq(nowTime); //创建日期
            sbjkzb.setQxdm(CommonUtil.getQxdm(ud)); //区县代码
            sbjkzb.setSkssksrq(tfxx.getHtqdsj()); //税款所属开始日期
            sbjkzb.setSkssjsrq(tfxx.getHtqdsj()); //税款所属结束日期
            sbjkzb.setFdcwz(tfxx.getTdfwzldz()); //房地产位置
            sbjkzb.setNd(DateUtil.getDate().substring(0, 4)); //年度
            sbjkzb.setSjly(Constants.JKS_SJLY_FHZ); //数据来源——非汇总方式
            sbjkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //帐务标识
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //契税限缴日期
//            sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //申报表编号


            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("直接生成缴款书方式：已经将sbjkzb的数据插入到数据库中....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("插入申报缴款主表出错！");
            }

            //对数据进行分票保存
            //因为这个方法针对的是用支票缴税的纳税人，所以他们的税目只能是一个
            //因此不必分票
            //构造申报缴款明细数据
            Sbjkmx sbjkMx = new Sbjkmx();
            Szsm szsm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn);
            sbjkMx.setSzsmdm(szsm.szsmdm); //税种税目代码
            Debug.out("缴款书明细中的税种税目名称：" + szsm.szsmmc);
            sbjkMx.setSzsmmc(szsm.szsmmc); //税种税目名称
            sbjkMx.setJkpzh(sbjkzb.getJkpzh()); //缴款凭证号
            Debug.out("缴款凭证号 : " + sbjkMx.getJkpzh());
            sbjkMx.setJsjdm(sbjkzb.getJsjdm()); //计算机代码
            sbjkMx.setYsjcdm(Constants.YSJCDM_DF); //预算级次代码
            sbjkMx.setYsjcmc(Constants.YSJCDM_DF_MC); //预算级次名称
            sbjkMx.setYskmdm(yskm.yskmdm); //预算科目
            sbjkMx.setYskmmc(yskm.yskmmc); //预算科目
            sbjkMx.setKssl(new BigDecimal(1)); //课税数量
            sbjkMx.setYskmfcbl(com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(yskm.yskmdm, szsm.szsmdm, ud.ssdwdm));//分成比例
            sbjkMx.setJsje(hdbo.getJsyj()); //计税金额
            sbjkMx.setSjse(hdbo.getYnse()); //实缴税额
            sbjkMx.setSkssksrq(nowTime); //税款所属开始日期
            sbjkMx.setSkssjsrq(nowTime); //税款所属结束日期
            sbjkMx.setRkje(hdbo.getYnse()); //入库金额
            sbjkMx.setSbbh(sbjkzb.getSbbh()); //申报编号
            /** @todo 缴款书分成的 */
            //市级分成
            //区级分成
            sbjkMx.setSwjgzzjgdm(ud.ssdwdm); //税务机关组织机构代码
            sbjkMx.setNd(sbjkzb.getNd()); //年度
            sbjkMx.setSl(sbzb.getSl()); //税率
            sbjkMx.setCjrq(nowTime); //创建时间
            sbjkMx.setLrrq(nowTime); //录入时间
            sbjkMx.setQxdm(sbjkzb.getQxdm()); //区县代码

            try {
                DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkMx, conn);
                Debug.out("直接生成缴款书方式：已经将sbjkMx的数据插入到数据库中....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("插入申报缴款明细出错！");
            }

            //将明细数据放入到主表VO中
            sbjkzb.getMxList().add(sbjkMx);
            //修改申报主表的状态标识
            //标记为已入库
            sbzb.setZtbs(Constants.ZB_ZTBS_YRK);
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);

            //符合减免办理条件的，并且已经复合了的申报表需要插入减免的申报信息
            //以备作减免的统计和记账
            Debug.out("申报主表中的办理减免税标识：" + sbzb.bljmsbs);
            Debug.out("申报主表中的状态：" + sbzb.ztbs);
//             if (Constants.ZB_BLJMSBS_FHBL_YLR.equals(sbzb.bljmsbs) &&
//                 Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs))
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
                HashMap map = new HashMap();
                map.put("jsjdm", swjgzzjg.jsjdm);
                map.put("szsmdm", sbjkMx.szsmdm);
                map.put("jsje", hdbo.getJzse());
                map.put("jmse", jmszje);
                map.put("lrr", sbjkzb.lrr);
                map.put("jmxmdm", Constants.JMXMDM); //减免项目代码
                map.put("jmxzdm", jmxzdm); //减免项目代码
                map.put("cjrq", sbjkzb.cjrq);
                map.put("skssjsrq", sbjkzb.skssjsrq);
                map.put("skssksrq", sbjkzb.skssksrq);
                if (!CommonUtil.insertSBJM(map)) {
                    throw new ApplicationException("插入税费的减免申报信息的时候报错！");
                }
                Debug.out("插入减免申报成功！");
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        Debug.out("直接生成缴款书方式：缴款书生成并且数据插入到数据库中....，操作成功！");
        Debug.out("征收税务机关组织机构名称 in JksProcessor is " + sbjkzb.getZsswjgzzjgmc());

        return sbjkzb;
    }

    /**
     * 撤销完税证
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doCxJks(VOPackage vo) throws BaseException {
        /**
         * 前台传过来的条件
         */
        JksBo bo = (JksBo) vo.getData();
        Sbjkzb jkzbVo = new Sbjkzb();
        String jkpzh = bo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);
        Debug.out("确定的jsjdm为 " + jsjdm);
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //查询缴款书主表
            jkzbVo.setJkpzh(jkpzh);
            jkzbVo.setJsjdm(jsjdm);
            bo.setJsjdm(jsjdm);
            jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    jkzbVo, conn);

            //获得缴款明细数据
            Sbjkmx mxVo = new Sbjkmx();
            mxVo.setJsjdm(jsjdm);
            mxVo.setJkpzh(jkpzh);
            mxVo = (Sbjkmx) DAOFactory.getInstance().getSbjkmxDAO().get(mxVo,
                    conn);

            if (jkzbVo == null) {
                throw new Exception("对不起，没有找到与该缴款书号匹配的记录！！！");
            }

            //如果该缴款书已入库
//          if(!Constants.JKS_ZWBS_DEFAULT.equals(jkzbVo.getZwbs()) )
//          {
//              throw new Exception("该缴款书已入库，不能撤销！！！");
//          }
            String zwbs = jkzbVo.getZwbs();
            if (zwbs == null) {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }

            // 如果zwbs第一位或第二位不为0则不能撤销  modify by wuyz
            if (!Constants.JKS_ZWBS_DEFAULT.substring(0, 1).equals(
                    zwbs.substring(0, 1))
                || !Constants.JKS_ZWBS_DEFAULT.substring(19).equals(
                        zwbs.substring(19))) {
                throw new Exception("该缴款书已入库，不能撤销！！！");
            }

            StringBuffer condition = new StringBuffer("");
            condition.append("jkpzh = '").append(bo.getJkpzh())
                    .append("' AND jsjdm = '").append(bo.getJsjdm()).append(
                    "' ");

            //删除缴款书主表、缴款书明细表和完税证汇总表的相关记录
            DAOFactory.getInstance().getSbjkmxDAO().delete(condition, conn);
            DAOFactory.getInstance().getSbjkzbDAO().delete(condition, conn);
            //修改申报主表的状态标识
            //恢复标记为已复核
            DAOFactory.getInstance().getSbzbDAO().update(Constants.ZB_ZTBS_YFH,
                    jkzbVo.getSbbh(), conn);

            //删除减免申报表的数据
            HashMap map = new HashMap();
            map.put("jsjdm", jkzbVo.jsjdm);
            map.put("szsmdm", mxVo.getSzsmdm());
            map.put("cjrq", jkzbVo.cjrq);

            //删除减免申报表的数据
            if (!CommonUtil.deleteSBJM(map)) {
                throw new ApplicationException("无法删除减免申报表的数据，调用接口出错！");
            }
            Debug.out("撤销减免申报成功！");
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 查询缴款书的方法，包含汇总方式和非汇总方式
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {
        JksBo bo = (JksBo) vo.getData();
        UserData ud = vo.getUserData();
        String jkpzh = bo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);

        StringBuffer condition = new StringBuffer("");
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("查询需要撤销的缴款书的计算机代码：" + jsjdm);

            if (jkpzh != null && !jkpzh.equals("")) {
                condition.append(" AND a.jkpzh = '").append(jkpzh).append("' ");
            } else {
                throw new ApplicationException("缴款凭证号不能为空！");
            }

            if (jsjdm != null && !jsjdm.equals("")) {
                condition.append(" AND a.jsjdm = '").append(jsjdm).append("' ");
            } else {
                throw new ApplicationException("查询的计算机代码不能为空！");
            }

            //增加数据权限控制
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_SBJKZB");
            Debug.out("datafilter: " + datafilter);
            condition.append(" AND ").append(datafilter);

            //汇总方式、非汇总方式是数据来源的字段
            condition.append(" AND a.sjly = '").append(bo.getHzfs()).append(
                    "' ");
            Sbjkzb jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().
                            get(condition, conn);

            return jkzbVo;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 缴款书打印成功后update缴款书的状态
     * @param vo VOPackage
     * @throws BaseException
     */
    public void doUpdate(VOPackage vo) throws BaseException {
        Sbjkzb jkzb = new Sbjkzb();

        jkzb = (Sbjkzb) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer(" WHERE 1 = 1 ");
            if (jkzb.getJkpzh() != null && !jkzb.getJkpzh().equals("")) {
                condition.append("AND jkpzh = '").append(jkzb.getJkpzh()).
                        append("' ");
            }
            if (jkzb.getJsjdm() != null && !jkzb.getJsjdm().equals("")) {
                condition.append("AND jsjdm = '").append(jkzb.getJsjdm()).
                        append("' ");
            }

            DAOFactory.getInstance().getSbjkzbDAO().update(condition, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 根据缴款书的内容,查询该缴款书中被汇总的完税证的情况
     * 返回一个ArrayList
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object doQueryWsz(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集

        JksBo bo = (JksBo) vo.getData();
        Sbjkzb sbjkzb = bo.getSbjkzb();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getQswszzDAO().query(sbjkzb,
                    conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * 查询缴款书
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集

        JksBo bo = (JksBo) vo.getData();
        String jkpzh = bo.getJkpzh();
        String jsjdm = "";

        Connection conn = null;
        UserData ud = vo.getUserData();

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer("");

            condition.append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM).
                    append("' ")
                    .append(" AND a.qxdm = '")
                    .append(CommonUtil.getQxdm(ud)).append("' ")
                    .append(" AND a.SWJGZZJGDM = '")
                    .append(ud.ssdwdm).append("' ");

            if (bo.getJkpzh() != null && !bo.getJkpzh().equals("")) {
                condition.append(" AND a.jkpzh = '").append(bo.getJkpzh()).
                        append("' ");
            }
            if (bo.getSbqsrq() != null && !bo.getSbqsrq().equals("")) {
                condition.append(" AND a.sbrq >= TO_DATE('").append(bo.
                        getSbqsrq()).
                        append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getSbjzrq() != null && !bo.getSbjzrq().equals("")) {
                condition.append(" AND a.sbrq <= TO_DATE('").append(bo.
                        getSbjzrq()).
                        append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
            }

            condition.append(" AND a.sjly='").append(bo.hzfs).append("'"); //数据来源
            //增加数据权限控制
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_SBJKZB");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);

            //如果是汇总方式生成的缴款书
            if (Constants.JKS_SJLY_HZ.equals(bo.hzfs) ||
                Constants.JKS_SJLY_XH.equals(bo.hzfs)) {
                jsjdm = CommonUtil.getWszJsjdm(ud, conn);
                condition.append(" AND a.jsjdm='").append(jsjdm).append("'");
                resultList = DAOFactory.getInstance().getSbjkzbDAO().queryAll(
                        condition, conn);
            }
            //如果是直接生成的缴款书
            else {
                //如果用户录入了缴款书号作为查询条件，那么取前8位作为计算机代码
                if (jkpzh != null && !jkpzh.equals("")) {
                    jsjdm = jkpzh.substring(0, 8);
                    condition.append(" AND (a.jsjdm = t1.jsjdm or a.jsjdm = '").
                            append(jsjdm).append("')");
                } else {
                    jsjdm = CommonUtil.getWszJsjdm(ud, conn);
                    condition.append(" AND (a.jsjdm = t1.jsjdm or a.jsjdm = '").
                            append(jsjdm).append("')");
                }

                //查询申报主表、个人信息、非个人信息得到jsjdm的where条件
                StringBuffer sbzb_condition = new StringBuffer("");
                sbzb_condition.append(" AND t.SWJGZZJGDM = '").append(ud.ssdwdm)
                        .append("' ").append("");
                if (bo.getSbqsrq() != null && !bo.getSbqsrq().equals("")) {
                    sbzb_condition.append(" AND t.sbrq >= TO_DATE('").append(bo.
                            getSbqsrq()).
                            append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
                }
                if (bo.getSbjzrq() != null && !bo.getSbjzrq().equals("")) {
                    sbzb_condition.append(" AND t.sbrq <= TO_DATE('").append(bo.
                            getSbjzrq()).
                            append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
                }

                resultList = DAOFactory.getInstance().getSbjkzbDAO().queryAll(
                        condition, sbzb_condition, conn);
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JksProcessor，操作出错",
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
        JksBo resultBo = new JksBo(); //最终返回给前台的查询结果集中的每一条记录
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            bo = new JksBo();
            bo = (JksBo) iter.next();

            if (tempList.isEmpty()) { //如果返回给前台的查询结果为空，说明是第一条数据
                resultBo.setSbjkzb(bo.getSbjkzb());
                //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().intValue());
                tempList.add(bo.getSbjkmx()); //在查询结果bo中加入相应的子表vo
                resultBo.setResultList(tempList); //付给查询结果bo中存储子表结果的ArrayList

                returnList.add(resultBo); //将重新组织的查询结果放到返回给前台的ArrayList中
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
                    resultBo.setResultList(tempList);
                } else {
                    //因为是一条不同的数据，所以重新构造resultBo和tempList
                    resultBo = new JksBo();
                    tempList = new ArrayList();

                    resultBo.setSbjkzb(bo.getSbjkzb());
                    //将申报缴款明细表中的课税数量付给申报缴款主表vo，方便查询页面显示用
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                    tempList.add(bo.getSbjkmx());
                    resultBo.setResultList(tempList);

                    returnList.add(resultBo); //将重新组织的查询结果放到返回给前台的ArrayList中
                }
            }
        }

        return returnList;
    }

}
