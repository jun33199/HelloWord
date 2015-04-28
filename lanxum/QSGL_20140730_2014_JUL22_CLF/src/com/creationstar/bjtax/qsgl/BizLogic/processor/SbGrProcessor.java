package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbGrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
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
 * @author not attributable
 * @version 1.0
 */
public class SbGrProcessor implements Processor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SbgrProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doInsert(vo);

            break;
        case ActionType.QUERY:
            result = doQuery(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 生成一条个人申报信息
     * 1.生成申报主表数据（首先获取新的申报表号）
     * 2.生成个人信息
     * 3.返回生成的申报表号
     * @param vo VOPackage
     * @return sbbh
     * @throws BaseException
     */
    private String doInsert(VOPackage vo) throws BaseException {
        SbGrBo bo = null;
        int oklevel = 0;
        String sbbh = null;
        Connection conn = null;

        try {
            bo = (SbGrBo) vo.getData();
            Debug.out("SbGrProcessor bo nsrmc: " + bo.nsrmc);
            conn = QSDBUtil.getConnection();

            UserData ud = vo.getUserData();

            //获取申报表号
            sbbh = CommonUtil.getSBBH(ud.getXtsbm1(), conn);
            Debug.out("get sbbh: " + sbbh);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            //构造申报主表数据
            Sbzb sbzb = new Sbzb();
            if (bo.isBl() == true) {
                sbzb.blbs = Constants.ZB_BLBS_BL;
                sbzb.sbrq = bo.getSbrq();
            } else {
                sbzb.blbs = Constants.ZB_BLBS_FBL;
                sbzb.sbrq = now;
            }
            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BFHBLTJ;
            sbzb.bz = bo.getBz();
            //增加建委业务编号 modify by fujx,20081125
            sbzb.setJwywbh(bo.getJwywbh());
            //增加合同编号 modify by fujx,20081125
            sbzb.setHtbh(bo.getHtbh());

            sbzb.cjr = ud.getYhmc();
            sbzb.cjrq = now;
            sbzb.fwtdbmdm = bo.getFcjslh();
            sbzb.jsfsdm = bo.getJkfsdm();
            sbzb.jsfsmc = bo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.nsrlxdm = Constants.NSRLX_GR;
            sbzb.sbbh = sbbh;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            sbzb.yhbs = Constants.YHBZ_GR; //个人
            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();

            //insert sbzb
            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            oklevel = 1;
            //构造个人信息
            List nsrList = bo.getNsrList();
            List l = new ArrayList();
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                grxx.cjr = ud.getYhmc();
                grxx.cjrq = now;
                grxx.fwjhbs = Constants.FFWJHBS;
                grxx.lrr = ud.getYhmc();
                grxx.lrrq = now;
                Debug.out("SbGrProcessor bo nsrmc22: " + grxx.nsrmc);
                grxx.sbbh = sbbh;
                l.add(grxx);
            }

            //获取登记信息,如果存在登记信息,则替换,否则按照录入的保存
            Debug.out("获取纳税人信息开始");
            l = CommonUtil.handleZRR(l, ud);
            Debug.out("S获取纳税人信息结束....");
            bo.setNsrList(l);
            oklevel = 2;
            //insert grxx
            DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            oklevel = 3;
            //构造申批结果数据
            if (bo.getHdtzszh() != null && !bo.getHdtzszh().equals("")) {
                Spjgxx spjgxx = new Spjgxx();
                spjgxx.cjr = ud.getYhmc();
                spjgxx.cjrq = now;
                spjgxx.hdtzszh = bo.getHdtzszh();
                if ((bo.getJmlydm() == null) || (bo.getJmlydm().equals(""))) {
                    spjgxx.jmlydm = "00";
                } else {
                    spjgxx.jmlydm = bo.jmlydm;
                }

                spjgxx.jmsje = bo.jmsje;
                spjgxx.lrr = ud.getYhmc();
                spjgxx.lrrq = now;
                spjgxx.sbbh = sbbh;

                Debug.out("jmje: " + bo.getJmsje());
                Debug.out("hdtzszh: " + bo.getHdtzszh());

                DAOFactory.getInstance().getSpjgxxDAO().insert(spjgxx, conn);
            }
        } catch (Exception ex) {
            Debug.out("OKLevel: " + oklevel);
            ex.printStackTrace();
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("插入申报主表出错!");
                case 1:
                    throw new ApplicationException(
                            "获取个人计算机代码出错，或者录入的纳税人名称与自然人登记的纳税人名称不匹配!");
                case 2:
                    throw new ApplicationException("插入个人信息出错!");
                case 3:
                    if (ex.getMessage().indexOf("ORA-00001") != -1) {
                        throw new ApplicationException("审批结果表编号已经存在!");
                    } else {
                        throw new ApplicationException("插入减免审批结果信息出错!");
                    }
                }

            } catch (ApplicationException e) {
                // 处理失败信息:控制台 ＋ 日志
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbGRProcessor，操作出错",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP).getStackMsg(), "失败");
                throw ExceptionUtil.getBaseException(e);
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return sbbh;
    }
    
    
    /**
     * 根据合同编号查询买方信息
     * @param vo VOPackage
     * @return SbGrBo
     * @throws BaseException
     */
    private SbGrBo doQuery(VOPackage vo) throws BaseException {
    	SbGrBo bo = null;
    	bo = (SbGrBo) vo.getData();
        int oklevel = 0;
        String htbh=bo.getHtbh();
        Connection conn = null;
    	if (htbh == null || "".equals(htbh)) {
			throw new ApplicationException("获取对应房屋采集信息出错，无查询条件!");
		}

		// 拼接查询SQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");
		
		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}
        try {
          
            Debug.out("SbGrProcessor bo htbh: " + bo.getHtbh());
            conn = QSDBUtil.getConnection();
            oklevel = 1;
            // 获得房屋信息
            List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwListForQs(conn, sqlBuff.toString());
         	if (fwxxList.size() <= 0) {
         		bo.setHtbh("");
         		bo.setTime("");
         		bo.setAddress("");
         		bo.setDivertType("");
         		bo.setArea("");
         		bo.setRmbPrice("");
         		bo.setAll_buyerInfo("");
         		throw new ApplicationException("无查询结果，无对应房屋采集信息!");
         	}
         			
         	for (int index = 0; index < fwxxList.size(); index++) {
         		Fwxx fwxx = (Fwxx) fwxxList.get(index);
         		
         		bo.setHtbh(fwxx.htbh);
         		bo.setTime(DataConvert.TimeStamp2String(fwxx.getHtwsqyrq()));
         		bo.setAddress(fwxx.fwzldz);
         		bo.setDivertType(fwxx.fwqszylx);
         		bo.setArea(fwxx.fwjzmj.toString());
         		bo.setRmbPrice(fwxx.htzj.toString());
         	}
            // 获得买方信息
         	List buyer_list = DAOFactory.getInstance().getMfgrxxBuyerDAO().queryMfgrxxBuyerListForQs(conn, sqlBuff.toString());
         	StringBuffer buyerBuf = new StringBuffer();
         
         	for (int index = 0; index < buyer_list.size(); index++) {
         		MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
         		mfgrxxBuyerItem = (MfgrxxBuyer) buyer_list.get(index);
         		if (index > 0) {
         			buyerBuf.append("^");
         		}
         		buyerBuf.append(mfgrxxBuyerItem.getNsrmc());
         		buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLxdh());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjlxdm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjhm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getQlrfe());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLb());
				
         	}
         	System.out.println("买方信息：：：：" + buyerBuf.toString());
         	bo.setAll_buyerInfo(buyerBuf.toString());
        } catch (Exception ex) {
            Debug.out("OKLevel: " + oklevel);
            ex.printStackTrace();
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("根据合同编号查询买方信息出错!");
                case 1:
                    throw new ApplicationException("无查询结果，无对应房屋采集信息!");
                }

            } catch (ApplicationException e) {
                // 处理失败信息:控制台 ＋ 日志
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SbGRProcessor，操作出错",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP).getStackMsg(), "失败");
                throw ExceptionUtil.getBaseException(e);
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }
}
