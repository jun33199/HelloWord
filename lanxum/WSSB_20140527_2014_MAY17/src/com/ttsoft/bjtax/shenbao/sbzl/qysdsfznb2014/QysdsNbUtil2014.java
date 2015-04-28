package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.CzzsfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.ZfjgfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.CzzsNbSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.CzzssdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.FzjgNbList2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.FzjgNbhj2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.FzjgxxNb2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.NsrxxVO_HDZSNb;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.SbxxNb2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.ZfjgqysdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.ZjgxxNb2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.DateTimeUtil;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.PreparedStatement;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.bjtax.shenbao.util.DBResource;

/**
 * 
 * Description : 企业所得税2014版年报工具类
 * @author zhangj
 * @version 2014-9-17 上午11:49:43
 */
public class QysdsNbUtil2014 {
	// 企业所得税税率
	private static final String QYSDS_SL = "0.25";

	public QysdsNbUtil2014() {
	}

	
	/**
	 * 查帐征收企业 将旧的VO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsNbVO Czzsconvert2XMLVO(CzzsfzjgNbBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws com.syax.frame.exception.BaseException {
		SbxxVO sbxx = new SbxxVO();
		CzzsNbSbsjVO sbsj = new CzzsNbSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZSNb nsrxx = new NsrxxVO_HDZSNb();
		CzzssdsNbVO qysdsnb = new CzzssdsNbVO();
		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 填充VO。
		// 纳税人信息。
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
		nsrxx.setNsrsbh(djJbsj.getSwdjzh());
		//获取税源标识 
		nsrxx.setSybs(FriendHelper.getNsrSybs(djJbsj));
		
		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// 核定信息，包含一部分申报信息
		getHdxx_cz(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);
		// 信息
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// 申报数据
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		sbsj.setNsfs(qysdsndbo.getNsfs());
		sbsj.setZfjg(qysdsndbo.getZfjg());
		sbsj.setYysr(qysdsndbo.getYysr());
		sbsj.setYycb(qysdsndbo.getYycb());
		sbsj.setLrze(qysdsndbo.getLrze());
		sbsj.setTdjsynssde(qysdsndbo.getTdjsynssde());
		sbsj.setBzsr(qysdsndbo.getBzsr());
		sbsj.setMssr(qysdsndbo.getMssr());
		sbsj.setMbyqndks(qysdsndbo.getMbyqndks());
		sbsj.setSjlre(qysdsndbo.getSjlre());
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setJmsdse(qysdsndbo.getJmsdse());
		sbsj.setSjyyjsdse(qysdsndbo.getSjyyjsdse());
		sbsj.setTdywyjsdse(qysdsndbo.getTdywyjsdse());
		sbsj.setYbtsdse(qysdsndbo.getYbtsdse());
		sbsj.setYqnddjsdse(qysdsndbo.getYqnddjsdse());
		sbsj.setBqsjybtsdse(qysdsndbo.getBqsjybtsdse());
		sbsj.setZjgyftsdse(qysdsndbo.getZjgyftsdse());
		sbsj.setCzjzfpsdse(qysdsndbo.getCzjzfpsdse());
		sbsj.setFzjgyftsdse(qysdsndbo.getFzjgyftsdse());
		sbsj.setZjgdlscjybmyftsdse(qysdsndbo.getZjgdlscjybmyftsdse());
//		sbsj.setZjgycxfzjgyftsdse(qysdsndbo.getZjgycxfzjgyftsdse());
		sbsj.setFpbl(qysdsndbo.getFpbl());
		sbsj.setFpsdse(qysdsndbo.getFpsdse());
		sbsj.setJmzynssde(qysdsndbo.getJmzynssde());
		sbsj.setXwqyjmsdse(qysdsndbo.getXwqyjmsdse());
		sbsj.setZczbje(qysdsndbo.getZczbje());
		sbsj.setZcze(qysdsndbo.getZcze());
		sbsj.setQueryFlag(qysdsndbo.getQueryFlag());
		// 企业所得税年报
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML文档信息
		qysdsnb.setXsltVersion(QysdsNbConstant2014.CA_XSLTDM_CZZSSDSNB_2014);
		qysdsnb.setSchemaVersion(QysdsNbConstant2014.CA_SCHEMADM_CZZSSDSNB_2014);
		qysdsnb.setYwlx(QysdsNbConstant2014.CA_YWLXDM_CZZSSDSNB_2014);

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}
	/**
	 * 查帐征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public CzzsfzjgNbBO Czzsconvert2VO(CzzssdsNbVO qysdsnbvo) throws BaseException {
		CzzsfzjgNbBO qysdsnbbo = new CzzsfzjgNbBO();
		// 纳税人信息
		qysdsnbbo.setJsjdm(qysdsnbvo.getNsrxx().getJsjdm());
		qysdsnbbo.setNsrmc(qysdsnbvo.getNsrxx().getNsrmc());
		qysdsnbbo.setSwjgzzjgdm(qysdsnbvo.getNsrxx().getSwjgzzjgdm());
		// 核定信息
		qysdsnbbo.setCyl(qysdsnbvo.getHdxx().getCyl());
		qysdsnbbo.setDezsse(qysdsnbvo.getHdxx().getDezsse());
		qysdsnbbo.setJmzg(qysdsnbvo.getHdxx().getJmzg());
		qysdsnbbo.setQyzslx(qysdsnbvo.getHdxx().getQyzslx());
		qysdsnbbo.setXzqy(qysdsnbvo.getHdxx().getXzqy());
		qysdsnbbo.setYbjmsl(qysdsnbvo.getHdxx().getYbjmsl());
		// 申报信息
		qysdsnbbo.setFsdm(qysdsnbvo.getSbxx().getFsdm());
		qysdsnbbo.setJd(qysdsnbvo.getSbxx().getJd());
		qysdsnbbo.setNd(qysdsnbvo.getSbxx().getNd());
		qysdsnbbo.setSbrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx().getSbrq())));
		qysdsnbbo.setSkssjsrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx().getSkssjsrq())));
		qysdsnbbo.setSkssksrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsnbvo.getSbxx().getSkssksrq())));

		// 申报数据。
		qysdsnbbo.setSbrqshow(qysdsnbvo.getSbsj().getSbrqshow());
		qysdsnbbo.setNsfs(qysdsnbvo.getSbsj().getNsfs());
		qysdsnbbo.setZfjg(qysdsnbvo.getSbsj().getZfjg());
		qysdsnbbo.setYysr(qysdsnbvo.getSbsj().getYysr());
		qysdsnbbo.setYycb(qysdsnbvo.getSbsj().getYycb());
		qysdsnbbo.setLrze(qysdsnbvo.getSbsj().getLrze());
		qysdsnbbo.setTdjsynssde(qysdsnbvo.getSbsj().getTdjsynssde());
		qysdsnbbo.setBzsr(qysdsnbvo.getSbsj().getBzsr());
		qysdsnbbo.setMssr(qysdsnbvo.getSbsj().getMssr());
		qysdsnbbo.setMbyqndks(qysdsnbvo.getSbsj().getMbyqndks());
		qysdsnbbo.setSjlre(qysdsnbvo.getSbsj().getSjlre());
		qysdsnbbo.setSl(qysdsnbvo.getSbsj().getSl());
		qysdsnbbo.setYnsdse(qysdsnbvo.getSbsj().getYnsdse());
		qysdsnbbo.setJmsdse(qysdsnbvo.getSbsj().getJmsdse());
        qysdsnbbo.setSjyyjsdse(qysdsnbvo.getSbsj().getSjyyjsdse());
        qysdsnbbo.setTdywyjsdse(qysdsnbvo.getSbsj().getTdywyjsdse());
		qysdsnbbo.setYbtsdse(qysdsnbvo.getSbsj().getYbtsdse());
		qysdsnbbo.setYqnddjsdse(qysdsnbvo.getSbsj().getYqnddjsdse());
		qysdsnbbo.setBqsjybtsdse(qysdsnbvo.getSbsj().getBqsjybtsdse());
		qysdsnbbo.setZjgyftsdse(qysdsnbvo.getSbsj().getZjgyftsdse());
		qysdsnbbo.setCzjzfpsdse(qysdsnbvo.getSbsj().getCzjzfpsdse());
		qysdsnbbo.setFzjgyftsdse(qysdsnbvo.getSbsj().getFzjgyftsdse());
		qysdsnbbo.setZjgdlscjybmyftsdse(qysdsnbvo.getSbsj().getZjgdlscjybmyftsdse());
//		qysdsnbbo.setZjgycxfzjgyftsdse(qysdsnbvo.getSbsj().getZjgycxfzjgyftsdse());
		qysdsnbbo.setFpbl(qysdsnbvo.getSbsj().getFpbl());
		qysdsnbbo.setFpsdse(qysdsnbvo.getSbsj().getFpsdse());
		qysdsnbbo.setJmzynssde(qysdsnbvo.getSbsj().getJmzynssde());
		qysdsnbbo.setXwqyjmsdse(qysdsnbvo.getSbsj().getXwqyjmsdse());
		qysdsnbbo.setZczbje(qysdsnbvo.getSbsj().getZczbje());
		qysdsnbbo.setZcze(qysdsnbvo.getSbsj().getZcze());
		return qysdsnbbo;
	}
	/**
	 * 2014企业所得税汇总纳税分支机构分配表 将旧的VO对象转换为XML-VO对象
	 * @param qysdsbo
	 * @param djJbsj
	 * @param cvo
	 * @param nszf
	 * @return
	 * @throws BaseException
	 */
	public ZfjgqysdsNbVO zfjgxxGetDataFromAconvert2XMLVO(ZfjgfzjgNbBO qysdsbo,SWDJJBSJ djJbsj, CzzssdsNbVO cvo, String nszf) throws BaseException 
	{
		CzzssdsNbVO czzsNbvo = (CzzssdsNbVO)cvo;
		ZfjgqysdsNbVO qysdsvo = new ZfjgqysdsNbVO();
		SbxxNb2014VO sbxx = new SbxxNb2014VO();
		ZjgxxNb2014VO zjgxx = new ZjgxxNb2014VO();
		FzjgNbList2014VO fzjgList = new FzjgNbList2014VO();
		FzjgNbhj2014VO fzjghj = new FzjgNbhj2014VO();

		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// ///////////////////////////////////
		HashMap sbsj = (HashMap) qysdsbo.getSbsj();
		System.out.println("==========2014========");
		System.out.println(sbsj.toString());
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(qysdsbo.getSbrqshow());
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		// sbxx.setFpblyxqq((String) sbsj.get("1"));
		// sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh(((String) sbsj.get("2")) == null ? "" : (String) sbsj.get("2"));
		zjgxx.setNsrmc(((String) sbsj.get("1")) == null ? "" : (String) sbsj.get("1"));
		zjgxx.setYnsdse(((String) sbsj.get("3")) == null ? "0.00" : (String) sbsj.get("3"));
		zjgxx.setFtsdse(((String) sbsj.get("4")) == null ? "0.00" : (String) sbsj.get("4"));
		zjgxx.setFpsdse(((String) sbsj.get("5")) == null ? "0.00" : (String) sbsj.get("5"));
		zjgxx.setFzjgftse(((String) sbsj.get("6")) == null ? "0.00" : (String) sbsj.get("6"));
		
		//用主表数据覆盖防止主表进行了修改
//		zjgxx.setFzjgftse(czzsNbvo.getSbsj().getFzjgyftsdse());
		
		//数据库已经保存的分支机构分配比例
		String fzjgFpbl_old="0.00";
		//主表新填写的分支机构分配比例
		String fzjgFpbl_new=((String) czzsNbvo.getSbsj().getFpbl()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpbl();
		
		//数据库已经保存的分支机构分配比例
		String fzjgFpsdse_old="0.00";
		//主表新填写的分支机构分配比例
		String fzjgFpsdse_new=((String) czzsNbvo.getSbsj().getFpsdse()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpsdse();

		// fzjgxx
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj.get(QysdsNbConstant2014.ZFJGSDSNB_2014_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			FzjgxxNb2014VO fzjg = new FzjgxxNb2014VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i);
				// System.out.println("util id = " + id);
				// System.out.println("util value = " + (String) sbsj.get(id));
				switch (j) {
				case 12:
					// 分支机构纳税人识别号
					fzjg.setNsrsbh(((String) sbsj.get(id)) == null ? "" : (String) sbsj.get(id));
					break;
				case 13:
					// 分支机构名称
					fzjg.setNsrmc(((String) sbsj.get(id)) == null ? "" : (String) sbsj.get(id));
					break;
				case 14:
					// 分支机构收入总额
					fzjg.setSrze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 15:
					// 分支机构工资总额
					fzjg.setGzze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 16:
					// 分支机构资产总额
					fzjg.setZcze(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
					break;
				case 17:
					// 分支机构分配比例
					fzjg.setFpbl(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
//					//应客户要求不在重新赋值
//					if(id.equals("17.1")){
//						fzjgFpbl_old=fzjg.getFpbl();
//						fzjg.setFpbl(fzjgFpbl_new+"%");
//					}
					break;
				case 18:
					// 分支机构分配税额
					fzjg.setFpse(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
//					//应客户要求不在重新赋值
//					if(id.equals("18.1")){
//						fzjgFpsdse_old=fzjg.getFpse();
//						fzjg.setFpse(fzjgFpsdse_new);
//					}
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);

		// 2014分支机构合计行
		fzjghj.setSrehj(((String) sbsj.get("7")) == null ? "0.00" : (String) sbsj.get("7"));
		fzjghj.setGzehj(((String) sbsj.get("8")) == null ? "0.00" : (String) sbsj.get("8"));
		fzjghj.setZcehj(((String) sbsj.get("9")) == null ? "0.00" : (String) sbsj.get("9"));
		fzjghj.setFpblhj(((String) sbsj.get("10")) == null ? "0" : (String) sbsj.get("10"));
		fzjghj.setFpsehj(((String) sbsj.get("11")) == null ? "0.00" : (String) sbsj.get("11"));
		
		//重新计算分支机构分配比例和分支机构分配税额   应客户要求不在重新赋值

//		DecimalFormat deFormat = new DecimalFormat("#0.00");
//		
//		BigDecimal fpblhj_old=new BigDecimal(fzjghj.getFpblhj().replaceAll("%", ""));
//		BigDecimal fpblhj_new=fpblhj_old.add(new BigDecimal(fzjgFpbl_new)).subtract(new BigDecimal(fzjgFpbl_old.replaceAll("%",""))) ;
//		fzjghj.setFpblhj(deFormat.format(fpblhj_new)+"%");
//		
//		BigDecimal fpsehj_old=new BigDecimal(fzjghj.getFpsehj());       
//        BigDecimal fpsehj_new=fpsehj_old.add(new BigDecimal(fzjgFpsdse_new)).subtract(new BigDecimal(fzjgFpsdse_old)) ;
//        fzjghj.setFpsehj(deFormat.format(fpsehj_new));
		   

		
		// 企业所得税年报
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML文档信息
		qysdsvo.setXsltVersion(QysdsNbConstant2014.CA_XSLTDM_ZFJGSDSNB_2014);
		qysdsvo.setSchemaVersion(QysdsNbConstant2014.CA_SCHEMADM_ZFJGSDSNB_2014);
		qysdsvo.setYwlx(QysdsNbConstant2014.CA_YWLXDM_ZFJGSDSNB_2014);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}

	/**
	 * 2014企业所得税汇总纳税分支机构分配表 将旧的VO对象转换为XML-VO对象。
	 * 第一次录入页面数据 进行默认拼装页面数据
	 * @param djJbsj
	 * @param cvo
	 * @return
	 * @throws BaseException
	 */
	public ZfjgqysdsNbVO CzzssdsNbVOGetDataFromAconvert2XMLVO(SWDJJBSJ djJbsj, CzzssdsNbVO cvo) throws BaseException 
	{
		CzzssdsNbVO czzsNbvo = (CzzssdsNbVO)cvo;
		ZfjgqysdsNbVO qysdsvo = new ZfjgqysdsNbVO();
		SbxxNb2014VO sbxx = new SbxxNb2014VO();
		ZjgxxNb2014VO zjgxx = new ZjgxxNb2014VO();
		FzjgNbList2014VO fzjgList = new FzjgNbList2014VO();
		FzjgNbhj2014VO fzjghj = new FzjgNbhj2014VO();

		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// ///////////////////////////////////
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(czzsNbvo.getSbxx().getJd());
		sbxx.setNd(czzsNbvo.getSbxx().getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(czzsNbvo.getSbsj().getSbrqshow());
		sbxx.setSkssksrq(czzsNbvo.getSbxx().getSkssksrq());
		sbxx.setSkssjsrq(czzsNbvo.getSbxx().getSkssjsrq());
		// sbxx.setFpblyxqq((String) sbsj.get("1"));
		// sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh("");
		zjgxx.setNsrmc("");
		zjgxx.setYnsdse("0.00");
		zjgxx.setFtsdse("0.00");
		zjgxx.setFpsdse("0.00");
		//zjgxx.setFzjgftse(czzsNbvo.getSbsj().getFzjgyftsdse());
		zjgxx.setFzjgftse("0.00");
		
		//分支机构分摊所得税额
		String fzjgFtsdse=((String) czzsNbvo.getSbsj().getFzjgyftsdse()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFzjgyftsdse();
		
		System.out.println(".........ZHANGJ......分支机构分摊所得税额:"+fzjgFtsdse);
		if(zjgxx.getYnsdse()==null||"0.00".equals(zjgxx.getYnsdse())){
			zjgxx.setYnsdse(Double.parseDouble(fzjgFtsdse)*2+"");
		}
		if(zjgxx.getFtsdse()==null||"0.00".equals(zjgxx.getFtsdse())){
			zjgxx.setFtsdse(Double.parseDouble(fzjgFtsdse)/2+"");
		}
		if(zjgxx.getFpsdse()==null||"0.00".equals(zjgxx.getFpsdse())){
			zjgxx.setFpsdse(Double.parseDouble(fzjgFtsdse)/2+"");
		}
		if(zjgxx.getFzjgftse()==null||"0.00".equals(zjgxx.getFzjgftse())){
			zjgxx.setFzjgftse(fzjgFtsdse);
		}
		// fzjgxx
		
		ArrayList fzjgxxList = new ArrayList();
		int rows = QysdsNbConstant2014.ZFJGSDSNB_2014_DEFAULT_MX_ROW_NUMBER;
		
		FzjgxxNb2014VO fzjg_new = new FzjgxxNb2014VO();
		fzjg_new.setIndex(String.valueOf(1));
		fzjg_new.setNsrsbh(czzsNbvo.getNsrxx().getNsrsbh());
		fzjg_new.setNsrmc(czzsNbvo.getNsrxx().getNsrmc());
		fzjg_new.setSrze("0.00");
		fzjg_new.setZcze("0.00");
		fzjg_new.setGzze("0.00");
		//fzjg_new.setFpbl(((String) czzsNbvo.getSbsj().getFpbl()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpbl());
		//fzjg_new.setFpse(((String) czzsNbvo.getSbsj().getFpsdse()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpsdse());
		fzjg_new.setFpbl("0.00");
		fzjg_new.setFpse("0.00");
		fzjgxxList.add(fzjg_new);
		
		for (int i = 2; i <= rows; i++) {
			FzjgxxNb2014VO fzjg = new FzjgxxNb2014VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 12; j < 19; j++) {
				switch (j) {
				case 12:
					// 分支机构纳税人识别号
					fzjg.setNsrsbh("");
					break;
				case 13:
					// 分支机构名称
					fzjg.setNsrmc("");
					break;
				case 14:
					// 分支机构收入总额
					fzjg.setSrze( "0.00" );
					break;
				case 15:
					// 分支机构工资总额
					fzjg.setGzze("0.00" );
					break;
				case 16:
					// 分支机构资产总额
					fzjg.setZcze( "0.00");
					break;
				case 17:
					// 分支机构分配比例
					fzjg.setFpbl("0.00");
					break;
				case 18:
					// 分支机构分配税额
					fzjg.setFpse("0.00");
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);

		// 2012分支机构合计行
		fzjghj.setSrehj("0.00");
		fzjghj.setGzehj("0.00");
		fzjghj.setZcehj("0.00");
		
		//fzjghj.setFpblhj(((String) czzsNbvo.getSbsj().getFpbl()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpbl()+"%");
		//fzjghj.setFpsehj(((String) czzsNbvo.getSbsj().getFpsdse()) == null ? "0.00" : (String) czzsNbvo.getSbsj().getFpsdse());
		
		fzjghj.setFpblhj("0.00");
		fzjghj.setFpsehj("0.00");
		
		// 企业所得税年报
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML文档信息
		qysdsvo.setXsltVersion(QysdsNbConstant2014.CA_XSLTDM_ZFJGSDSNB_2014);
		qysdsvo.setSchemaVersion(QysdsNbConstant2014.CA_SCHEMADM_ZFJGSDSNB_2014);
		qysdsvo.setYwlx(QysdsNbConstant2014.CA_YWLXDM_ZFJGSDSNB_2014);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}
	
	/**
	 * 取得核定信息
	 * @param jsjdm
	 * @param hdvo
	 * @param sbsj
	 * @param swdjjbsj
	 * @param bblx
	 * @throws com.syax.frame.exception.BaseException
	 */
	public void getHdxx_cz(String jsjdm, HdxxVO hdvo, CzzsNbSbsjVO sbsj,
			SWDJJBSJ swdjjbsj, String bblx)
			throws com.syax.frame.exception.BaseException {
		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		// 应纳所得税额
		String ynsdse = "0.00";
		// 定额征收税额
		String dezsse = "0.00";

		// 当前时间
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// 征期
		Map skssrq = new HashMap();
		// 税款所属开始和结束日期
		Timestamp skssksrq;
		Timestamp skssjsrq;
		// 取得所在的季度
		String jd = Skssrq.preQuarter(sbrq);
		ServiceProxy proxy = new ServiceProxy();
		// 查询税费接口
		QysdsSet qysdsSet = null;
		// 数据库连接对象
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// 减免资格标识
		boolean jm_type = false;
		try {

			if ("4".equals(jd)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// 取得税款所属开始和结束日期
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// 如果为第4季度的时候调用年报的接口
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,"01");// 年报
			} else {

				skssrq = Skssrq.otherSkssrq(sbrq);

				// 取得税款所属开始和结束日期
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// 如果不为第4季度的时候调用季报的接口
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"00");// 季报
                //重载Zsfs
                Zsfs zsfs = getZsfsInfo(jsjdm, skssjsrq);
                qysdsSet.setZsfs(zsfs);
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();

		Date gxqyrdrq = qysdsSet.getGxjsqy();
		System.out.println("高新技术企业:" + gxqyrdrq);

		// 初值
		hdvo.setCyl("0");
		hdvo.setXzqy("0");
		hdvo.setDezsse("0.00");
		hdvo.setYbjmsl("0.00");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// 纯益率征收
					qyzssllx = "2";
				} else {
					// 高新技术和纯益率企业
					qyzssllx = "5";
					qyzssl = "0.15";
					// hdvo.setJmzg("1"); // 有减免资格
				}
				hdvo.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				hdvo.setDezsse(dezsse);
			}
		}

		// 2、查询是否是高新技术企业
		if (gxqyrdrq != null) {
			if (zsfs != null && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
				// 高新技术和纯益率企业
				qyzssllx = "5";
			} else {
				// 类型为高新技术企业
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			// hdvo.setJmzg("1"); // 有减免资格
		}

		// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
		else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY)) {
			if (qysdsSet.isXzqy()) {
				hdvo.setXzqy("1");
				// hdvo.setJmzg("1"); // 有减免资格
			}
		}

		if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// 非乡镇企业的减免情况
			// hdvo.setJmzg("1"); // 有减免资格
			DecimalFormat ft = new DecimalFormat("0.00");
			hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		hdvo.setQyzslx(qyzssllx);
		hdvo.setJmzg("1");// 默认设置为有减免资格
	}
    /**
     * 获取征收方式
     * @param jsjdm String
     * @param rq Date
     * @return Zsfs
     * @throws BaseException
     */
    public Zsfs getZsfsInfo(String jsjdm, Date rq) throws BaseException, com.syax.frame.exception.BaseException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Zsfs ret = new Zsfs();
        QysdsZsfs q = new QysdsZsfs();
        StringBuffer sql = new StringBuffer();
        int rdnd = getYear(rq);
        try {
            con = DBResource.getConnection();
            sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) <= ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            //取最后核定的一条记录
            if(rs.next())
            {
                q.setZsfsdm(rs.getString("ZSFSDM"));
                q.setDl(new BigDecimal(rs.getDouble("DL")));
                q.setCyl(new BigDecimal(rs.getDouble("CYL")));
                q.setDe(new BigDecimal(rs.getDouble("DE")));
            }else{
                return null;
            }
            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_UP)));
            }else {
                ret.setCyl(String.valueOf(q.getCyl()));
            }
            ret.setDe(String.valueOf(q.getDe()));
            //关闭数据库对象
            rs.close();
            pstmt.close();
            con.close();
            return ret;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }finally{
        	DBResource.destroyConnection(con);
        }
    }
    
    /**
     * 得到给定日期的年份 为int型
     * @param date 给定的日期
     * @return int 年份值
     */
    public static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR));
    }
    
    public String getZsfsmc(String zsfsdm, Connection con)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String ret = "";
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='" + zsfsdm + "'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            if (rs.next())
            {
                ret = rs.getString("zsfsmc");
            }
            rs.close();
            return ret;
        }
        catch (Exception ex)
        {
            return "";
        }
    }
	/**
	 * 格式化日期
	 *
	 * @param datetime
	 *            String
	 * @return String
	 */
	private String getFormatDatetime(String datetime) {
		try {
			String retStr = new String();
			StringTokenizer st = new StringTokenizer(datetime.trim());
			retStr = st.nextToken();
			// 先将日期部分格式化
			if (retStr.substring(6, 7).equals("-")) {
				retStr = retStr.substring(0, 5) + "0" + retStr.substring(5);
			}
			if (retStr.length() < 10) {
				retStr = retStr.substring(0, 8) + "0" + retStr.substring(8);
				// 处理时间部分
			}
			if (st.countTokens() < 1) {
				return retStr + " 00:00:00.000"; // 仅有日期
			}
			String s = new String();
			s = st.nextToken();
			st = new StringTokenizer(s, ":");
			int count = st.countTokens();
			if (count == 1) {
				retStr += " " + s + ":00:00.000"; // 时间中只有小时
			}
			if (count == 2) {
				retStr += " " + s + ":00.000"; // 时间中只有小时、分钟
			}
			if (count == 3) {
				retStr += " " + s + ".000"; // 时间中无毫秒
			}
			return retStr;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询A类表中纳税方法与总分机构
	 * @param pData
	 * @return
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public HashMap getNsfsAndZfjg(Map pData) throws BaseException, ApplicationException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		HashMap nszf = new HashMap();
		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			CzzsfzjgNbBO czzsbo = (CzzsfzjgNbBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

			sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
			sql.append("nsrjsjdm = '").append(czzsbo.getJsjdm()).append("' ");
			sql.append("and bbqlx = '").append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			sql.append("and qh = '").append((String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX)).append("' ");
			sql.append("and sknd = '").append(czzsbo.getNd()).append("' ");
			sql.append("and sbdm = '").append(QysdsNbConstant2014.TABLE_ID_CZZSSDSNB_2014).append("' ");
			sql.append("and hc in ('1', '2') ");
			
			System.out.println("sql:\n" + sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());

			while (rs.next()) {
				// nszf.put("hc", rs.getString("hc"));
				// nszf.put("yz", rs.getString("yz"));

				if (rs.getString("hc").equals("1")) {
					nszf.put("nsfs", rs.getString("yz"));
				}
				if (rs.getString("hc").equals("2")) {
					nszf.put("zfjg", rs.getString("yz"));
				}
			}
	
			System.out.println("nsfs====================" + nszf.get("nsfs"));
			System.out.println("zfjg====================" + nszf.get("zfjg"));
			
			czzsbo.setNsfs_fsjg(nszf);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 关闭数据库对象
			this.releaseAll(rs, pstmt, conn);
		}
		return nszf;
	}
     
    /**
	 * @decription 级联删除分配表数据
	 * @author wangcy
	 * @modify_date 2013-12-03
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public void deleteCascadeZfjgData(Map pData) throws BaseException,ApplicationException 
    {
		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存申报主数据");
		}

		Connection conn = null;

		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		CzzsfzjgNbBO czzsbo = (CzzsfzjgNbBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// 取得税款所属日期Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.yearSkssrq(curDate);

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();

			PreparedStatement detelePstmtZb = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			PreparedStatement detelePstmtCb = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			
			detelePstmtZb.setString(1, czzsbo.getJsjdm());
			detelePstmtZb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			detelePstmtZb.setString(4, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
			detelePstmtZb.setString(5, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
			detelePstmtZb.addBatch();

			detelePstmtCb.setString(1, czzsbo.getJsjdm());
			detelePstmtCb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			detelePstmtCb.setString(4, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
			detelePstmtCb.setString(5, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
			detelePstmtCb.addBatch();
			
			detelePstmtZb.executeBatch();
			detelePstmtCb.executeBatch();
			
			System.out.println("deleteCascadeZfjgData()方法，级联处理删除数据完毕。。。");

			detelePstmtZb.close();
			detelePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(localException);
		}
	}
    

    /**
     * @description 级联保存分配表中不可修改部分数据
     * @param pData
     * @throws BaseException
     * @throws ApplicationException
     */
    public void saveCascadeZfjgData(Map pData) throws BaseException, ApplicationException {

		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存数据");
		}
		
		Connection conn = null;
		CzzsfzjgNbBO qysdsndbo = (CzzsfzjgNbBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		
		System.out.println("进入saveCascadeZfjgData()处理数据。。。。。。。。。。。。。。");

		// Date sbrq = new Date(qysdsndbo.getSbrq().getTime());
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// Date sbrq =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSbrq()));
		// Date sksssqq =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSkssksrq()));
		// Date sksssqz =
		// format.parse(DateTimeUtil.timestampToString(qysdsndbo.getSkssjsrq()));
		
		// 取得税款所属日期Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.yearSkssrq(curDate);

		String sbrq = DateTimeUtil.timestampToString(curDate);
		String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSKSRQ));
		String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSJSRQ));
		
		BigDecimal divisor = new BigDecimal(100);
		DecimalFormat percentFormat = new DecimalFormat("#########.00%");

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			
			HashMap queryResult = this.queryCascadeZfjgData(pData);
			String queryFlagZb = (String) queryResult.get("queryFlagZb");
			String queryFlagCb = (String) queryResult.get("queryFlagCb");

			//String nsfs_old = (String) nsfs_zfjg.get("nsfs_old");
			//String zfjg_old = (String) nsfs_zfjg.get("zfjg_old");

			// 获取申报数据
			String[] hcZb = { "1", "2", "3", "4", "5", "6", "10", "11" };
			String[] hcCb = { "12", "13", "17", "18", "14", "15", "16" };
			String[] yzZb = new String[8];
			String[] yzCb = new String[7];
			
			//System.out.println("djJbsj.getNsrmc()==="+ djJbsj.getNsrmc());
			//System.out.println("djJbsj.getNsrsbh()==="+ djJbsj.getSwdjzh());
			
			if(qysdsndbo.getZfjg().equals("1")){
				for (int i = 0; i < yzZb.length; i++) {
					switch (i) {
						case 0:
							yzZb[i] = djJbsj.getNsrmc();
							break;
						case 1:
							yzZb[i] = djJbsj.getSwdjzh();
							break;
						case 2:
							yzZb[i] = qysdsndbo.getYnsdse();
							break;
						case 3:
							yzZb[i] = qysdsndbo.getZjgyftsdse();
							break;
						case 4:
							yzZb[i] = qysdsndbo.getCzjzfpsdse();
							break;
						case 5:
							yzZb[i] = qysdsndbo.getFzjgyftsdse();
							break;
						case 6:
							yzZb[i] = "0";
							break;
						case 7:
							yzZb[i] = "0.00";
							break;
						default:
							throw new SystemException("不在赋值范围！！");
					}
				}
			}
			
			
			if(qysdsndbo.getZfjg().equals("2")){
				if (queryFlagZb.equals("0")) {
					BigDecimal fpblZb = new BigDecimal(qysdsndbo.getFpbl());
			        System.out.println("fpblCbResult======"+fpblZb.divide(divisor, 4, 2));
					for (int i = 0; i < yzZb.length; i++) {
						switch (i) {
							case 0:
								yzZb[i] = "";
								break;
							case 1:
								yzZb[i] = "";
								break;
							case 2:
								yzZb[i] = "0.00";
								break;
							case 3:
								yzZb[i] = "0.00";
								break;
							case 4:
								yzZb[i] = "0.00";
								break;
							case 5:
//								yzZb[i] = qysdsndbo.getFzjgyftsdse();
								yzZb[i] = (String) pData.get("fpb_fzjgftse");
								break;
							case 6:
								yzZb[i] = percentFormat.format(fpblZb.divide(divisor, 4, 2)).toString();
								break;
							case 7:
								yzZb[i] = qysdsndbo.getFpsdse();
								break;		
							default:
								throw new SystemException("不在赋值范围！！");
						}
						System.out.println("hcZb[" + i + "]" + "========" + hcZb[i] + "======" + "yzZb[" + i + "]" + "======" + yzZb[i]);
					}
				}
				
				if (queryFlagCb.equals("0")) {
					BigDecimal fpblCb = new BigDecimal(qysdsndbo.getFpbl());
			        System.out.println("fpblCbResult======"+fpblCb.divide(divisor, 4, 2));
					for (int i = 0; i < yzCb.length; i++) {
						switch (i) {
							case 0:
								yzCb[i] = djJbsj.getSwdjzh();
								break;
							case 1:
								yzCb[i] = djJbsj.getNsrmc();
								break;
							case 2:
								yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
								break;
							case 3:
								yzCb[i] = qysdsndbo.getFpsdse();
								break;
							case 4:
								yzCb[i] = "0.00";
								break;
							case 5:
								yzCb[i] = "0.00";
								break;
							case 6:
								yzCb[i] = "0.00";
								break;
							
							default:
								throw new SystemException("不在赋值范围！！");
						}
					}
				}
				
				if (queryFlagZb.equals("1")) {
					HashMap sbsjZbMap = (HashMap) queryResult.get("sbsjZbMap");
					HashMap sbsjCbMap = (HashMap) queryResult.get("sbsjCbMap");
					
					String fpblHjOld = (String)sbsjZbMap.get("fpbl");
					String fpblOld = (String)sbsjCbMap.get("fpbl");			
					
					double tmpFpblHj=0.00;
					if(fpblHjOld.endsWith("%")){
						tmpFpblHj= Double.parseDouble(fpblHjOld.substring(0,fpblHjOld.indexOf("%")));
					}else{
						tmpFpblHj= Double.parseDouble(fpblHjOld);
					}
					
					double tmpFpblOld = Double.parseDouble(fpblOld)*100;
					double tmpFpblNew = Double.parseDouble(qysdsndbo.getFpbl());
					
					double tmpFpsdseHj = Double.parseDouble(((String)sbsjZbMap.get("fpsdse"))==null ?"0.00":(String)sbsjZbMap.get("fpsdse"));			
					double tmpFpsdseOld = Double.parseDouble((String)sbsjCbMap.get("fpsdse"));			
					double tmpFpsdseNew = Double.parseDouble(qysdsndbo.getFpsdse());
					
					double fpblHjZb = tmpFpblHj+tmpFpblNew-tmpFpblOld;
					double fpsdseZb = tmpFpsdseHj+tmpFpsdseNew-tmpFpsdseOld;
					
					BigDecimal updateFpblHj = new BigDecimal(fpblHjZb);
					String updateFpsdseHj = ""+fpsdseZb;
					
					System.out.println("updateFpblHj============="+updateFpblHj);
					System.out.println("updateFpsdseHj============="+updateFpsdseHj);
					
					for (int i = 5; i < yzZb.length; i++) {
						switch (i) {
							case 5:
//								yzZb[i] = qysdsndbo.getFzjgyftsdse();
								yzZb[i] = (String) pData.get("fpb_fzjgftse");
								break;
							case 6:
								yzZb[i] = percentFormat.format(updateFpblHj.divide(divisor, 4, 2)).toString();
								break;
							case 7:
								yzZb[i] = updateFpsdseHj;
								break;
							default:
								throw new SystemException("不在赋值范围！！");
						}
					}				
				}
				
				if (queryFlagCb.equals("1")) {
					BigDecimal fpblCb = new BigDecimal(qysdsndbo.getFpbl());
					for (int i = 0; i < yzCb.length-3; i++) {
						switch (i) {
							case 0:
								yzCb[i] = djJbsj.getSwdjzh();
								break;
							case 1:
								yzCb[i] = djJbsj.getNsrmc();
								break;
							case 2:
								yzCb[i] = fpblCb.divide(divisor, 4, 2).toString();
								break;
							case 3:
								yzCb[i] = qysdsndbo.getFpsdse();
								break;
							default:
								throw new SystemException("不在赋值范围！！");
						}
					}
				}
			}
			

			PreparedStatement insertPstmtZb = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_ZB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement insertPstmtCb = conn.prepareStatement("INSERT INTO SBDB.SB_JL_QYSDSSBB_CB_JD (NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			PreparedStatement updatePstmtZb = conn.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_ZB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=?");
			PreparedStatement updatePstmtCb = conn.prepareStatement("UPDATE SBDB.SB_JL_QYSDSSBB_CB_JD SET YZ=?, LRSJ=? WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=? AND HC=? AND ZHS=?");

			if(qysdsndbo.getZfjg().equals("1")){
				if (queryFlagZb.equals("0")) {
					System.out.println("111111111111111111111111111111111111111111111");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, djJbsj.getJsjdm());
						insertPstmtZb.setString(2, djJbsj.getNsrmc());
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, QysdsNbConstant2014.QYSDSNB_VERSION_2014);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtZb.setString(9, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						insertPstmtZb.setString(10, QysdsNbConstant2014.TABLE_NAME_ZFJGSDSNB_2014);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(12, new java.sql.Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setDate(13, new java.sql.Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(14, QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, "");
						insertPstmtZb.setString(17, djJbsj.getSwjgzzjgdm());
						insertPstmtZb.setString(18, djJbsj.getJsjdm());
						insertPstmtZb.setDate(19, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(20, djJbsj.getJsjdm());
						insertPstmtZb.setDate(21, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(22, djJbsj.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagZb.equals("1")) {
					System.out.println("2222222222222222222222222222222222222222222222222222222");
					for (int i = 0; i < hcZb.length-2; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtZb.setString(3, djJbsj.getJsjdm());
						updatePstmtZb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						updatePstmtZb.setString(7, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
			}

			if(qysdsndbo.getZfjg().equals("2")){
				if (queryFlagZb.equals("0")) {
					System.out.println("3333333333333333333333333333333333333333333333333333333333333333");
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, djJbsj.getJsjdm());
						insertPstmtZb.setString(2, djJbsj.getNsrmc());
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, QysdsNbConstant2014.QYSDSNB_VERSION_2014);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtZb.setString(9, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						insertPstmtZb.setString(10, QysdsNbConstant2014.TABLE_NAME_ZFJGSDSNB_2014);
						insertPstmtZb.setString(11, hcZb[i]);
						insertPstmtZb.setDate(12, new java.sql.Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setDate(13, new java.sql.Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(14, QysdsReportsConstants.CREPORTS_ITEM_FIELD_FLAG_SINGLELINE);
						insertPstmtZb.setString(15, yzZb[i]);
						insertPstmtZb.setString(16, "");
						insertPstmtZb.setString(17, djJbsj.getSwjgzzjgdm());
						insertPstmtZb.setString(18, djJbsj.getJsjdm());
						insertPstmtZb.setDate(19, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(20, djJbsj.getJsjdm());
						insertPstmtZb.setDate(21, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(22, djJbsj.getQxdm());
						insertPstmtZb.addBatch();
					}
					insertPstmtZb.executeBatch();
				}
				if (queryFlagCb.equals("0")) {
					System.out.println("444444444444444444444444444444444444444444444444444444");
					for (int i = 0; i < 7; i++) {
						insertPstmtCb.setString(1, djJbsj.getJsjdm());
						insertPstmtCb.setString(2, djJbsj.getNsrmc());
						insertPstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtCb.setString(4, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						insertPstmtCb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtCb.setString(6, QysdsNbConstant2014.QYSDSNB_VERSION_2014);
						insertPstmtCb.setString(7, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						insertPstmtCb.setString(8, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						insertPstmtCb.setString(9, QysdsNbConstant2014.TABLE_NAME_ZFJGSDSNB_2014);
						insertPstmtCb.setString(10, hcCb[i]);
						insertPstmtCb.setString(11, "1");
						insertPstmtCb.setString(12, "");
						insertPstmtCb.setString(13, yzCb[i]);
						insertPstmtCb.setString(14, djJbsj.getSwjgzzjgdm());
						insertPstmtCb.setString(15, djJbsj.getJsjdm());
						insertPstmtCb.setDate(16, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtCb.setString(17, djJbsj.getJsjdm());
						insertPstmtCb.setDate(18, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtCb.addBatch();
					}
					insertPstmtCb.executeBatch();
				}
				
				
				if (queryFlagZb.equals("1")) {
					System.out.println("555555555555555555555555555555555555555555555555555555555");
					for (int i = 5; i < hcZb.length; i++) {
						updatePstmtZb.setString(1, yzZb[i]);
						updatePstmtZb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtZb.setString(3, djJbsj.getJsjdm());
						updatePstmtZb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						updatePstmtZb.setString(7, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
				
				if (queryFlagCb.equals("1")) {
					System.out.println("6666666666666666666666666666666666666666666666666666666666666");
					for (int i = 0; i < hcCb.length-3; i++) {
						updatePstmtCb.setString(1, yzCb[i]);
						updatePstmtCb.setDate(2, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						updatePstmtCb.setString(3, djJbsj.getJsjdm());
						updatePstmtCb.setString(4, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						updatePstmtCb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
						updatePstmtCb.setString(6, (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX));
						updatePstmtCb.setString(7, QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014);
						updatePstmtCb.setString(8, hcCb[i]);
						updatePstmtCb.setString(9, "1");
						updatePstmtCb.addBatch();
					}
					updatePstmtCb.executeBatch();
				}
			}
			
			
			
//			insertPstmtZb.executeBatch();
//			insertPstmtCb.executeBatch();			
//			updatePstmtZb.executeBatch();
//			updatePstmtCb.executeBatch();
			
			System.out.println("saveCascadeZfjgData()方法，级联处理保存数据完毕。。。");

			insertPstmtZb.close();
			insertPstmtCb.close();
			updatePstmtZb.close();
			updatePstmtCb.close();
			conn.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil.getBaseException(localException);
		}
	}
   	
    /**
	 * @decription 级联查询分配表中所有数据
	 * @author wangcy
	 * @modify_date 2013-12-03
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
	public HashMap queryCascadeZfjgData(Map pData) throws BaseException, ApplicationException {
		
		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法查询申报主数据");
		}

		Connection conn = null;
		PreparedStatement queryPstmtZb = null;
		PreparedStatement queryPstmtCb = null;
		ResultSet queryRsZb = null;
		ResultSet queryRsCb = null;
		HashMap rsMap = new HashMap();

		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);

		// 取得税款所属日期Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.yearSkssrq(curDate);

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();

			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM= '");

			querySqlZb.append(djJbsj.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append((String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX)).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlZb.append(" AND SBDM = '");
			querySqlZb.append(QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014).append("' ");

			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM= '");
			querySqlCb.append(djJbsj.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append((String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX)).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlCb.append(" AND SBDM = '");
			querySqlCb.append(QysdsNbConstant2014.TABLE_ID_ZFJGSDSNB_2014).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			HashMap sbsjZbMap = new HashMap();
			HashMap sbsjCbMap = new HashMap();
			
			
			while (queryRsZb.next()) {
				queryFlagZb = "1";
				if (queryRsZb.getString("hc").equals("10")) {
					sbsjZbMap.put("fpbl", queryRsZb.getString("yz"));
				}
				if (queryRsZb.getString("hc").equals("11")) {
					sbsjZbMap.put("fpsdse", queryRsZb.getString("yz"));
				}
			}
			
			while (queryRsCb.next()) {
				queryFlagCb = "1";
				if (queryRsCb.getString("hc").equals("17") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpbl", queryRsCb.getString("yz"));
				}
				if (queryRsCb.getString("hc").equals("18") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpsdse", queryRsCb.getString("yz"));
				}
			}
			
			rsMap.put("queryFlagZb", queryFlagZb);
			rsMap.put("queryFlagCb", queryFlagCb);
			
			rsMap.put("sbsjZbMap", sbsjZbMap);
			rsMap.put("sbsjCbMap", sbsjCbMap);
			
			System.out.println("queryCascadeZfjgData()方法，级联处理查询数据完毕。。。");

		} catch (Exception localException) {
			localException.printStackTrace();
			throw com.ttsoft.framework.exception.ExceptionUtil
					.getBaseException(localException);
		} finally {
			this.release(queryRsZb, queryPstmtZb);
			this.release(queryRsCb, queryPstmtCb);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return rsMap;
	}
	
	
	/**
	 * 查账征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 * @author wangcy,2013-12-07 
	 */
	public ZfjgfzjgNbBO Zfjgconvert2BO(ZfjgqysdsNbVO qysdsvo) throws BaseException {
		ZfjgfzjgNbBO qysdsbo = new ZfjgfzjgNbBO();
		HashMap sbsj = new HashMap();
		/**
		 * 申报信息
		 */
		// fsdm
		qysdsbo.setFsdm(qysdsvo.getSbxx().getFsdm());
		// jd
		qysdsbo.setJd(qysdsvo.getSbxx().getJd());
		// nd
		qysdsbo.setNd(qysdsvo.getSbxx().getNd());
		// sbrq
		qysdsbo.setSbrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx().getSbrq())));
		// skssksrq
		qysdsbo.setSkssjsrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx().getSkssjsrq())));
		// skssjsrq
		qysdsbo.setSkssksrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx().getSkssksrq())));
		/**
		 * 总机构信息
		 */
		// jsjdm
		qysdsbo.setJsjdm(qysdsvo.getZjgxx().getJsjdm());
		// nsrsbh
		qysdsbo.setNsrsbh(qysdsvo.getZjgxx().getNsrsbh());
		sbsj.put("2", qysdsvo.getZjgxx().getNsrsbh());
		// nsrmc
		qysdsbo.setNsrmc(qysdsvo.getZjgxx().getNsrmc());
		sbsj.put("1", qysdsvo.getZjgxx().getNsrmc());
		// srze
		sbsj.put("3", qysdsvo.getZjgxx().getYnsdse());
		// gzze
		sbsj.put("4", qysdsvo.getZjgxx().getFtsdse());
		// zcze
		sbsj.put("5", qysdsvo.getZjgxx().getFpsdse());
		// ftse
		sbsj.put("6", qysdsvo.getZjgxx().getFzjgftse());

		/**
		 * 分支机构明细信息
		 */
		ArrayList fzjgmxList = (ArrayList) qysdsvo.getFzjgxx().getMxxx();
		// System.out.println("fzjgmxList size = " + fzjgmxList.size());
		for (int i = 0; i < fzjgmxList.size(); i++) {
			FzjgxxNb2014VO fzjgxx = (FzjgxxNb2014VO) fzjgmxList.get(i);
			// System.out.println("index = " + fzjgxx.getIndex());
			// 将id从10~17单元域的值放入Map中
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i + 1);
				switch (j) {
				case 12:
					// 分支机构纳税人识别号
					sbsj.put(id, fzjgxx.getNsrsbh());
					break;
				case 13:
					// 分支机构名称
					sbsj.put(id, fzjgxx.getNsrmc());
					break;
				case 14:
					// 分支机构收入总额
					sbsj.put(id, fzjgxx.getSrze());
					break;
				case 15:
					// 分支机构工资总额
					sbsj.put(id, fzjgxx.getGzze());
					break;
				case 16:
					// 分支机构资产总额
					sbsj.put(id, fzjgxx.getZcze());
					break;
//				case 15:
//					// 分支机构合计
//					sbsj.put(id, fzjgxx.getHj());
//					break;
				case 17:
					// 分支机构分配比例
					sbsj.put(id, fzjgxx.getFpbl());
					break;
				case 18:
					// 分支机构分配税额
					sbsj.put(id, fzjgxx.getFpse());
					break;
				}
			}
		}
		
		//2012版分支机构合计行
		FzjgNbhj2014VO fzjghj = qysdsvo.getFzjghj();
		sbsj.put("7", fzjghj.getSrehj());
		sbsj.put("8", fzjghj.getGzehj());
		sbsj.put("9", fzjghj.getZcehj());
		sbsj.put("10", fzjghj.getFpblhj());
		sbsj.put("11", fzjghj.getFpsehj());

		qysdsbo.setSbsj(sbsj);

		return qysdsbo;
	}
	
	
   	/**
  	 * @description 释放数据库资源
  	 * @author wangcy
  	 * @modify_date 2013-12-03
  	 * @param rs
  	 * @param stmt
  	 * @param con
  	 */
     public void release(ResultSet rs, Statement stmt){
  		if(rs!=null){
  			  try{
  			      rs.close();
  			  }catch (Exception ex) {
  				  ex.printStackTrace();
  			  }
  			}
  		if(stmt!=null){
  			  try{
  			      stmt.close();
  			  }catch (Exception ex) {
  				  ex.printStackTrace();
  			  }
  			}
  	}
    /**
     * 释放数据库资源
     * @param rs
     * @param stmt
     * @param con
     */
    public void releaseAll(ResultSet rs, Statement stmt, Connection con){
		if(rs!=null){
			  try{
			      rs.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(stmt!=null){
			  try{
			      stmt.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		if(con!=null){
		  try{
		      con.close();
		  }catch (Exception ex) {
			  ex.printStackTrace();
		  }
		}
	}
}
