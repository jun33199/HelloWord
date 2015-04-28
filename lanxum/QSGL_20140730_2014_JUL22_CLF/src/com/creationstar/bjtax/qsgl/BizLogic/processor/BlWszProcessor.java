package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlWszBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
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
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title: 补录契税完税证的processor</p>
 *
 * <p>Description: 补录契税完税证的processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class BlWszProcessor implements Processor {

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
        }

        switch (vo.getAction()) {
        case ActionType.GET:
            result = createWsz(vo);
            break;

        case ActionType.INSERT:
            saveWsz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;

    }

    private Object createWsz(VOPackage vo) throws BaseException {
        BlWszBo bo = (BlWszBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = bo.getQswszz();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //根据用户输入的条件查询申报主表的相关信息，如果符合补录条件，则返回一个申报主表的vo
            Sbzb sbzb = checkSbzb(bo, conn);
            //根据用户输入的条件，查询完税证是否已存在
            Qswszz haveOne = (Qswszz) DAOFactory.getInstance().getQswszzDAO().
                             get(qswszz, conn);
            if (haveOne != null) {
                throw new ApplicationException("该完税证已存在");
            }
            //如果用户选择了补录已汇总的完税证，则要检查申报缴款主表以及完税证汇总表的相关信息
            if (Constants.WSZ_CLBJDM_YJZ.equals(qswszz.getClbjdm())) {
                String sbhzdh = checkHzWsz(bo, jsjdm, conn);
                bo.setSbhzdh(sbhzdh);
            }

            bo = getWszInfo(bo, ud, sbzb, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BlWszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void saveWsz(VOPackage vo) throws BaseException {
        BlWszBo bo = (BlWszBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = bo.getQswszz();
            Qswszmx qswsmx = bo.getQswszmx();

            //插入完税证主表
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(qswszz, conn);
                Debug.out("补录完税证：插入完税证主表成功....");
            } catch (BaseException ex) {
                ex.printStackTrace();
                throw new ApplicationException("补录完税证：新增完税证主表信息出错！");
            }

            //插入完税证明细表
            try {
                DAOFactory.getInstance().getQswszmxDAO().insert(qswsmx, conn);
                Debug.out("补录完税证：插入完税证明细成功....");
            } catch (BaseException ex) {
                ex.printStackTrace();
                throw new ApplicationException("补录完税证：新增明细表信息出错！");
            }

            //修改申报主表的状态标识
            //标记为已入库
            String sbbh = bo.getSbbh();
            try {
                DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("补录完税证：更新申报主表状态出错！");
            }

            //插入减免申报
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //取得减免税金额
            BigDecimal jmszje = hdbo.getJmzje();
            
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
                
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
                map_jmsb.put("jsjdm", qswszz.jsjdm);
                map_jmsb.put("szsmdm", qswsmx.szsmdm);
                map_jmsb.put("jsje", hdbo.getJzse());
                map_jmsb.put("jmse", jmszje);
                map_jmsb.put("lrr", qswszz.lrr);
                /** @todo jmxmdm 结构上有问题！ */
                map_jmsb.put("jmxmdm", Constants.JMXMDM); //项目代码
                map_jmsb.put("jmxzdm", jmxzdm); //减免项目代码
                map_jmsb.put("cjrq", qswszz.cjrq);
                map_jmsb.put("skssjsrq", qswsmx.getSkssjsrq());
                map_jmsb.put("skssksrq", qswsmx.getSkssksrq());
                if (!CommonUtil.insertSBJM(map_jmsb)) {
                    throw new ApplicationException("插入税费的减免申报信息的时候报错！");
                }
                Debug.out("插入减免申报成功！");
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－BlWszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 补录契税完税证，根据用户录入的申报表号检查申报主表的相关数据，如果通过检查则返回申报主表vo
     * @param bo BlWszBo
     * @param conn Connection
     * @return Sbzb
     * @throws BaseException
     */
    private Sbzb checkSbzb(BlWszBo bo, Connection conn) throws BaseException {
        //通过构造申报主表，赋值申报表号，获得完整的申报主表数据
        Sbzb sbzb = new Sbzb();
        sbzb.setSbbh(bo.getSbbh());

        try {
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //必须是申报主表中的补录标识（blbs）为1并且状态标识为7的才能补录非汇总缴款书
            if (sbzb == null) {
                throw new ApplicationException("未找到相匹配的申报记录，请先补录申报信息，再补录缴款书！");
            } else if (Constants.ZB_BLBS_FBL.equals(sbzb.getBlbs())) {
                throw new ApplicationException("该笔申报不是补录的，不能补录缴款书！");
            }
            //只有是分票的情况或者已汇总的情况（因为补录的缴款书已经改变了申报主表的状态）
            //才能在申报状态为已入库(ztbs为99)的情况下补录完税证
            else if (Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs()) &&
                     (bo.getFp().equals("0") ||
                      bo.getClbjdm().equals(Constants.WSZ_CLBJDM_YJK))) {
                throw new ApplicationException("该笔申报已入库，不能再次补录缴款书！");
            } else if (!Constants.ZB_ZTBS_YFH.equals(sbzb.getZtbs())
                       && !Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs())) {
                throw new ApplicationException("请先完成复合及收款操作，再补录缴款书！");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return sbzb;
    }

    /**
     * 如果用户选择了补录已汇总的完税证，则需检查申报缴款主表以及完税证汇总表的数据
     * @param bo BlWszBo
     * @param jsjdm String
     * @param conn Connection
     * @return String   返回申报汇总单号
     * @throws BaseException
     */
    private String checkHzWsz(BlWszBo bo, String jsjdm, Connection conn) throws
            BaseException {
        Sbjkzb sbjkzb = new Sbjkzb();

        sbjkzb.setJkpzh(bo.getJkpzh());
        sbjkzb.setJsjdm(jsjdm);

        String sbhzdh = null;

        try {
            sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    sbjkzb, conn);

            //必须是申报主表中的补录标识（blbs）为1并且状态标识为7的才能补录非汇总缴款书
            if (sbjkzb == null) {
                throw new ApplicationException(
                        "未找到相匹配的缴款书，请先补录汇总方式生成的缴款书，再补录完税证！");
            }
            //如果缴款书不是汇总方式生成的
            else if (!sbjkzb.getSjly().equals(Constants.JKS_SJLY_HZ)
                     || sbjkzb.getSbbh() != null) {
                throw new ApplicationException("该缴款书不是汇总方式生成的！");
            }
            //如果税种不是契税
            else if (!sbjkzb.getSzdm().equals(Constants.WSZ_QSSZDM)) {
                throw new ApplicationException("该缴款书不是契税缴款书！");
            }

            //检查完税证汇总表，缴款书是否完成了分票，用户输入的申报汇总单号是否存在
            sbhzdh = DAOFactory.getInstance().getQswszhzDAO().get(jsjdm,
                    bo.getJkpzh(), conn);
            if (Constants.WSZ_SBHZDH_DEFAULT.equals(sbhzdh)) {
                throw new ApplicationException("请您先完成缴款书的分票!");
            } else if (sbhzdh == null || sbhzdh.equals("")) {
                throw new ApplicationException("没有在完税证汇总表中找到相匹配的记录!");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return sbhzdh;
    }


    /**
     * 生成完税证主表和明细表的数据，并把vo赋给bo返回
     * @param bo BlWszBo
     * @param ud UserData
     * @param sbzb Sbzb
     * @param conn Connection
     * @return BlWszBo
     * @throws BaseException
     */
    private BlWszBo getWszInfo(BlWszBo bo, UserData ud, Sbzb sbzb,
                               Connection conn) throws BaseException {
        Qswszz wszZb = null; //构造完税证主表的数据
        Qswszmx wszMx = null; //构造完税证明细的数据

        try {
            Timestamp now = CommonUtil.getDBtime(conn);

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            if (tufwxx == null) {
                throw new ApplicationException("房屋、土地的基本信息不能为空！");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //取得减免税金额
            BigDecimal jmszje = hdbo.getJmzje();
            //取得合计计税金额
            double total_je = hdbo.getYnse().doubleValue();

            wszZb = new Qswszz();

            //开始构造完税证数据
            wszZb.setWszh(bo.getWszh());
            wszZb.setNdzb(bo.getNdzb());
            wszZb.setPzzldm(bo.getPzzldm());
            wszZb.setClbjdm(bo.getClbjdm());
            wszZb.setNd(DateUtil.getDate().substring(0, 4));
            wszZb.setCjr(ud.getYhmc());
            wszZb.setCjrq(now);
            wszZb.setLrr(ud.getYhid()); //为了以后汇总的时候的汇总依据，按照个人汇总的就是用这个字段
            wszZb.setLrrq(now);
            wszZb.setSwjgzzjgdm(ud.getSsdwdm());
            wszZb.setSwjgzzjgmc(ud.getSsdwmc());
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
            wszZb.setFdcwz(tufwxx.getTdfwzldz()); //房地产位置
            wszZb.setHtclrq(tufwxx.getHtqdsj()); //房屋合同签订时间

            //区分个人还是非个人（企业）生成完税证
            //如果是个人购买的房产
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(grxx.getJsjdm()); //纳税人计算机代码
                ZRRJBSJ grjb = CommonUtil.getGrJBSJ(grxx);
                wszZb.setDjzclxdm(grjb.getSwdjlx()); //登记注册类型——税务登记类型
                wszZb.setDjzclxmc(grjb.getSwdjlx()); //登记注册类型——税务登记类型
                wszZb.setFsmc(Constants.WSZ_DJZCLX_MC);
                wszZb.setZjhm(grxx.getSfzjhm()); //证件号码
                wszZb.setZjlxdm(grxx.getSfzjlx()); //证件类型代码
                wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //获得国家标准行业代码
                wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //获得国家标准行业名称
                wszZb.setNsrmc(grxx.getNsrmc()); //纳税人名称
                wszZb.setNsrdm(grxx.sfzjhm); //纳税人代码
                wszZb.setZjlxmc(grxx.sfzjlxmc); //证件类型名称
                Debug.out("补录契税完税证：取得个人的登记信息....");
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
                Debug.out("补录契税完税证：取得非个人的完整信息....");
            }

            //插入明细表
            //初始化明细
            wszMx = new Qswszmx();

            //设置其它值，表结构有问题，等修改后需修改此处的信息
            wszMx.setWszh(wszZb.getWszh());
            wszMx.setNdzb(wszZb.getNdzb());
            wszMx.setPzzldm(wszZb.getPzzldm());
            wszMx.setSzdm(Constants.WSZ_QSSZDM); //默认税种代码为“契税74”
            wszMx.setSzmc(Constants.WSZ_QSSZMC); //默认税种代码为“契税74”

            wszMx.setCjrq(now); //创建日期
            wszMx.setLrrq(now); //录入日期
            wszMx.setLrr(ud.getYhid());
            wszMx.setCjr(ud.getYhmc());
            //wszMx.setQxdm(qxdm); //区县代码
            wszMx.setJsjdm(wszZb.getJsjdm()); //计算机代码
            wszMx.setSwjgzzjgdm(wszZb.getSwjgzzjgdm());
            wszMx.setJzbz(Constants.WSZ_JZBZ_BL); //记账标志，默认六个0
            //设置年度
            wszMx.setNd(DateUtil.getDate().substring(0, 4));
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

            wszZb.setYqts(new BigDecimal(0)); //逾期天数
            wszZb.setZnjzje(new BigDecimal(0)); //滞纳金总金额
            wszZb.setJsje(jmszje); //获得减免税总额
            wszZb.setHjsjje(hdbo.getYnse()); //完税证主表中的"合计实缴税额"在这里赋得是“应纳税额”

            wszMx.setYjhkc(wszZb.getJsje()); //由于明细中只有一条数据，所以已缴或扣除字段等于主表的减税金额字段
            wszMx.setJsje(hdbo.getJsyj()); //计税金额等于计税依据
            wszMx.setSjse(hdbo.getYnse()); //实纳税额就是应纳税额（复核及收款中的项）

            //如果用户补录的是已汇总的完税证，则给申报汇总单号赋值
            if (bo.getClbjdm().equals(Constants.WSZ_CLBJDM_YJZ)) {
                wszZb.setSbhzdh(bo.getSbhzdh());
            }

            bo.setQswszz(wszZb);
            bo.setQswszmx(wszMx);

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return bo;
    }

}
