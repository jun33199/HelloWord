package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.HzJksBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
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
 * <p>Title:补录汇总生成的缴款书的processor </p>
 *
 * <p>Description: 补录汇总生成的缴款书的processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class HzJksProcessor implements Processor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = getJksInfo(vo);
            break;

        case ActionType.INSERT:
            createJks(vo);
            break;

        case ActionType.UPDATE:
            result = updateWszhz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;

    }

    /**
     * 根据UserData自动带出缴款书的相关信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object getJksInfo(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Sbjkzb jkzb = new Sbjkzb();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            Yskm yskm = null; //预算科目

            //得到汇总的征收人员的所属税务机关的基本信息
            SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(jsjdm);
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            if (swdjJbsj == null || swjgzzjg == null) {
                throw new ApplicationException("不能得到汇总征收人员的所属税务机关的基本信息！");
            }

            //为缴款申报主表补全数据
            jkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //处理标记代码
            jkzb.setDjzclxdm(swdjJbsj.getDjzclxdm()); //登记注册类型代码
            jkzb.setFsdm(Constants.WSZ_FSDM); //登记申报方式代码
            jkzb.setGjbzhydm(swdjJbsj.getGjbzhydm()); //国家标准行业代码

            jkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //国库组织机构代码
            jkzb.setGkzzjgmc(swjgzzjg.skgk); //国库组织机构名称
            jkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //帐务标识

            jkzb.setJsjdm(jsjdm); //计算机代码
            jkzb.setJydzlxdm(swdjJbsj.getJydzlxdm()); //经营地址联系电话
            jkzb.setLrr(ud.getYhid()); //录入人
            jkzb.setLsgxdm(swdjJbsj.getLsgxdm()); //隶属关系代码
            jkzb.setNd(DateUtil.getDate().substring(0, 4)); //年度
            jkzb.setQxdm(CommonUtil.getQxdm(ud)); //区县代码

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
            jkzb.setGkjhh(swjgzzjg.getGkjhh()); //国库交换号

            //得到银行信息
            ArrayList yhList = (ArrayList) CommonUtil.getYHZH(jsjdm);
            for (Iterator it = yhList.iterator(); it.hasNext(); ) {
                YHZH yhzh = (YHZH) it.next();
                if (Constants.JKS_JBZHBS.equalsIgnoreCase(yhzh.getJbzhbs())) {
                    jkzb.setYhdm(yhzh.getYhdm());
                    jkzb.setYhmc(yhzh.getKhyhmc());
                    jkzb.setZh(yhzh.getZh());
                }
            }

            bo.setSbjkzb(jkzb);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－HzJksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void createJks(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //得到当前时间
            Timestamp now = CommonUtil.getDBtime(conn);

            Sbjkzb sbjkzb = bo.getSbjkzb();
            sbjkzb.setLrrq(now);
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 7)); //契税限缴日期
            sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //申报表编号
            sbjkzb.setZyrq(now); //创建日期

            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("补录汇总生成的缴款书：已经将sbjkzb的数据插入到数据库中....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("插入申报缴款主表出错！");
            }

            Sbjkmx sbjkmx = new Sbjkmx();
            //生成申报汇总明细表的数据
            sbjkmx.setCjrq(sbjkzb.getCjrq());
            sbjkmx.setLrrq(sbjkzb.getLrrq());
            sbjkmx.setJkpzh(sbjkzb.getJkpzh());
            sbjkmx.setJsjdm(sbjkzb.getJsjdm());
            sbjkmx.setNd(sbjkzb.getNd());
            sbjkmx.setQxdm(sbjkzb.getQxdm());
            sbjkmx.setSkssksrq(sbjkzb.getSkssksrq());
            sbjkmx.setSkssjsrq(sbjkzb.getSkssjsrq());
            sbjkmx.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
            sbjkmx.setSwjgzzjgmc(sbjkzb.getSwjgzzjgmc());
            sbjkmx.setYsjcdm(sbjkzb.getYsjcdm());
            sbjkmx.setYsjcmc(sbjkzb.getYsjcmc());
            sbjkmx.setYskmdm(sbjkzb.getYskmdm());
            sbjkmx.setYskmmc(sbjkzb.getYskmmc());
            sbjkmx.setSbbh(sbjkzb.getSbbh()); //申报编号

            int size = bo.getMxxmdm().length;
            for (int i = 0; i < size; i++) {
                if (bo.getMxsjse()[i] != null && !bo.getMxsjse()[i].equals("")) {
                    sbjkmx.setJsje(DataConvert.String2BigDecimal(bo.getMxjsje()[
                            i]));
                    sbjkmx.setKssl(DataConvert.String2BigDecimal(bo.getMxkssl()[
                            i]));
                    sbjkmx.setSjse(DataConvert.String2BigDecimal(bo.getMxsjse()[
                            i]));
                    sbjkmx.setRkje(sbjkmx.getSjse());

                    Szsm szsm = CommonUtil.getSZSMDM(bo.getMxxmdm()[i], conn);
                    sbjkmx.setSzsmdm(szsm.getSzsmdm()); //税种税目代码
                    sbjkmx.setSzsmmc(szsm.getSzsmmc()); //税种税目名称

                    try {
                        DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkmx,
                                conn);
                        Debug.out("补录汇总生成的缴款书：已经将sbjkmx的数据插入到数据库中....");
                    } catch (Exception ex) {
                        Debug.out(ex);
                        throw new ApplicationException("插入申报缴款明细出错！");
                    }
                }
            }

            Qswszhz qswszhz = new Qswszhz();
            //生成完税证汇总表的数据
            qswszhz.setCjr(sbjkzb.getLrr());
            qswszhz.setCjrq(sbjkzb.getCjrq());
            qswszhz.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
            qswszhz.setHzfs(bo.getHzfsdm());
            qswszhz.setHzfsmc(bo.getHzfsmc());
            qswszhz.setHzksrq(sbjkzb.getSkssksrq());
            qswszhz.setHzjsrq(sbjkzb.getSkssjsrq());
            qswszhz.setHzrq(sbjkzb.getCjrq());
            qswszhz.setJkpzh(sbjkzb.getJkpzh());
            qswszhz.setJsjdm(sbjkzb.getJsjdm());
            qswszhz.setLrr(sbjkzb.getLrr());
            qswszhz.setLrrq(sbjkzb.getLrrq());
            qswszhz.setNd(sbjkzb.getNd());
            qswszhz.setSjse(sbjkzb.getSjje());
            qswszhz.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());

            qswszhz.setZsddm(ud.xtsbm1);
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            qswszhz.setZsdmc(zh.getZhmc());

            if (bo.getFp().equals("0")) { //如果没有分票的情况
                //取得申报汇总单号
                String sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);
                qswszhz.setSbhzdh(sbhzdh);
            } else {
                qswszhz.setSbhzdh(Constants.WSZ_SBHZDH_DEFAULT);
            }

            try {
                DAOFactory.getInstance().getQswszhzDAO().insert(qswszhz, conn);
                Debug.out("补录汇总生成的缴款书：已经将qswszhz的数据插入到数据库中....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("插入完税证汇总表出错！");
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－HzJksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    private Object updateWszhz(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String jkpzh[] = bo.getJkpzh();
            String sbhzdh = "";
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            int size = jkpzh.length;

            for (int i = 0; i < size; i++) {
                sbhzdh = DAOFactory.getInstance().getQswszhzDAO().get(jsjdm,
                        jkpzh[i], conn);
                if (!Constants.WSZ_SBHZDH_DEFAULT.equals(sbhzdh)) {
                    throw new ApplicationException(jkpzh[i] +
                            "号缴款书没有分票的情况或者已经完成分票!");
                }

            }

            //生成申报汇总单号
            sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);

            //更新jkpzh对应的完税证汇总表数据
            for (int i = 0; i < size; i++) {
                try {
                    DAOFactory.getInstance().getQswszhzDAO().update(jsjdm,
                            sbhzdh, jkpzh[i],
                            conn);
                    Debug.out("补录汇总生成的缴款书：已经将生成的sbhzdh : " + sbhzdh +
                              "更新到数据库qswszhz表中....");
                } catch (Exception ex) {
                    Debug.out(ex);
                    throw new ApplicationException("更新完税证汇总表出错！");
                }
            }

            return sbhzdh;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－HzJksProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}
