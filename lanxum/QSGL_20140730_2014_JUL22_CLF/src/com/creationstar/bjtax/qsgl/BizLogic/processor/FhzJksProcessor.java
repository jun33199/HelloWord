package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;

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
import com.creationstar.bjtax.qsgl.model.bo.FhzJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.JksUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title:补录非汇总生成的缴款书的processor </p>
 *
 * <p>Description: 补录非汇总生成的缴款书的processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class FhzJksProcessor implements Processor {

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
        }

        switch (vo.getAction()) {
        case ActionType.GET:
            result = createJks(vo);
            break;

        case ActionType.INSERT:
            saveJks(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;

    }

    /**
     * 根据申报表号自动带出缴款书的相关信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */

    private Object createJks(VOPackage vo) throws BaseException {
        FhzJksBo bo = (FhzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String sbbh = bo.getSbbh();
            Sbjkzb sbjkzb = new Sbjkzb();

            Timestamp nowTime = CommonUtil.getDBtime(conn);

            //纳税人计算机代码
            String nsrjsjdm = "";
            //通过构造申报主表，赋值申报表号，获得完整的申报主表数据
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);

            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //必须是申报主表中的补录标识（blbs）为1并且状态标识为7的才能补录非汇总缴款书
            if (sbzb == null) {
                throw new ApplicationException("未找到相匹配的申报记录，请先补录申报信息，再补录缴款书！");
            } else if (Constants.ZB_BLBS_FBL.equals(sbzb.getBlbs())) {
                throw new ApplicationException("该笔申报不是补录的，不能补录缴款书！");
            } else if (Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs())) {
                throw new ApplicationException("该笔申报已入库，不能再次补录缴款书！");
            } else if (!Constants.ZB_ZTBS_YFH.equals(sbzb.getZtbs())) {
                throw new ApplicationException("请先完成复合及收款操作，再补录缴款书！");
            }

            //得到土地房屋申报的基本信息
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            Debug.out("补录非汇总生成缴款书：已经取得了房屋基本信息的完整数据....");

            //定位Swjgzzjg数据
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);

            //如果该申报表为个人申报
            Debug.out("补录非汇总生成缴款书：判断个人还是非个人....");
            if (Constants.YHBZ_GR.equals(sbzb.getYhbs())) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbbh, conn);
                nsrjsjdm = grxx.getJsjdm();
                //得到纳税人的登记基本数据
                sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //登记注册类型
                sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //登记注册类型名称
                sbjkzb.setJydzlxdm(grxx.getLxdh()); //联系电话
                sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //国家标准行业代码
                sbjkzb.setNsrmc(grxx.getNsrmc()); //纳税人名称
                sbjkzb.setJsjdm(nsrjsjdm);
                sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //得到缴款凭证号
                Debug.out("补录非汇总生成缴款书：个人方式、并且已经将个人方式的属性值赋到Sbjkzb中....");
            }
            //如果为非个人申报
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(
                                      sbbh, conn);
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
                sbjkzb.setDjzclxdm(jbsj.getSwdjlx()); //登记注册类型
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
                Debug.out("征收税务机关组织机构名称 in FhzJksProcessor is " +
                          sbjkzb.getZsswjgzzjgmc());
                Debug.out("补录非汇总生成缴款书：非个人方式、并且已经将非个人的属性值赋到Sbjkzb中....");
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
            sbjkzb.setSbrq(nowTime); //申报日期
            sbjkzb.setCjrq(nowTime); //创建日期
            sbjkzb.setLrrq(nowTime); //录入日期
            sbjkzb.setQxdm(CommonUtil.getQxdm(ud)); //区县代码
            sbjkzb.setSkssksrq(tfxx.getHtqdsj()); //税款所属开始日期
            sbjkzb.setSkssjsrq(tfxx.getHtqdsj()); //税款所属结束日期
            sbjkzb.setFdcwz(tfxx.getTdfwzldz()); //房地产位置
            sbjkzb.setNd(DateUtil.getDate().substring(0, 4)); //年度
            sbjkzb.setSjly(Constants.JKS_SJLY_FHZ); //数据来源——非汇总方式
            sbjkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //帐务标识
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //契税限缴日期
            //sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //申报表编号
            sbjkzb.setZyrq(nowTime); //创建日期

            //因为这个方法针对的是用支票缴税的纳税人，所以他们的税目只能是一个
            //因此不必分票
            //构造申报缴款明细数据
            Sbjkmx sbjkMx = new Sbjkmx();
            Szsm szsm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn);
            sbjkMx.setSzsmdm(szsm.szsmdm); //税种税目代码
            Debug.out("缴款书明细中的税种税目名称：" + szsm.szsmmc);
            sbjkMx.setSzsmmc(szsm.szsmmc); //税种税目名称
            sbjkMx.setJkpzh(sbjkzb.getJkpzh()); //缴款凭证号
            sbjkMx.setJsjdm(sbjkzb.getJsjdm()); //计算机代码
            sbjkMx.setYsjcdm(Constants.YSJCDM_DF); //预算级次代码
            sbjkMx.setYsjcmc(Constants.YSJCDM_DF_MC); //预算级次名称
            sbjkMx.setYskmdm(yskm.yskmdm); //预算科目
            sbjkMx.setYskmmc(yskm.yskmmc); //预算科目
            sbjkMx.setKssl(new BigDecimal(1)); //课税数量

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

            bo.setSbjkzb(sbjkzb);
            bo.setSbjkmx(sbjkMx);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FhzJksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void saveJks(VOPackage vo) throws BaseException {
        FhzJksBo bo = (FhzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String sbbh = bo.getSbbh();

            Sbjkzb sbjkzb = bo.getSbjkzb();
            Sbjkmx sbjkmx = bo.getSbjkmx();

            //因为填发日期在前台可以修改，所以需要重新计算一下契税限缴日期
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //契税限缴日期

            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("补录非汇总生成缴款书：已经将sbjkzb的数据插入到数据库中....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("插入申报缴款主表出错！");
            }

            try {
                DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkmx, conn);
                Debug.out("补录非汇总生成缴款书：已经将sbjkMx的数据插入到数据库中....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("插入申报缴款明细出错！");
            }

            //修改申报主表的状态标识
            //标记为已入库
            try {
                DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("更新申报主表状态出错！");
            }

            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            //保存减免税总金额到申报缴款的备注字段
            BigDecimal jmszje = hdbo.getJmzje();

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
                map.put("szsmdm", sbjkmx.szsmdm);
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
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－FhzJksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}
