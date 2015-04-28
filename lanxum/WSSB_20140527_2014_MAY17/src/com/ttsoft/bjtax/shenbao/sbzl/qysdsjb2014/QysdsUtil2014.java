package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 08企业所得税申报工具类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.GdzcjszjyjqkjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbSbsjListVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbJmxmVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.DmVo;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.FzjgList2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.Fzjghj2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.Fzjgxx2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.HdzsSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.HdzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.NsrxxVO_HDZS;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.Sbxx2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.Zjgxx2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbNsrxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjblistVO;
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
import com.ttsoft.framework.exception.*;;

public class QysdsUtil2014 {
	// 企业所得税税率
	private static final String QYSDS_SL = "0.25";

	public QysdsUtil2014() {
	}

	/**
	 * 08企业所得税汇总纳税分支机构分配表 将旧的VO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 * @author Guoxh,2014-03-06,修改整体思路：不改变原来的代码架构，只修改数据项
	 */
	public ZfjgqysdsjbVO Zfjgconvert2XMLVO(ZfjgqysdsjbBO qysdsbo,
			SWDJJBSJ djJbsj) throws BaseException {
		ZfjgqysdsjbVO qysdsvo = new ZfjgqysdsjbVO();
		Sbxx2014VO sbxx = new Sbxx2014VO();
		Zjgxx2014VO zjgxx = new Zjgxx2014VO();
		FzjgList2014VO fzjgList = new FzjgList2014VO();
		Fzjghj2014VO fzjghj = new Fzjghj2014VO();

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
//		sbxx.setFpblyxqq((String) sbsj.get("1"));
//		sbxx.setFpblyxqz((String) sbsj.get("2"));

		// zjgxx
		zjgxx.setJsjdm(djJbsj.getJsjdm());
		zjgxx.setNsrsbh(qysdsbo.getNsrsbh());
		zjgxx.setNsrmc(qysdsbo.getNsrmc());
		zjgxx.setNsrsbh((String) sbsj.get("2"));
		zjgxx.setNsrmc((String) sbsj.get("1"));
		zjgxx.setYnsdse(((String) sbsj.get("3")) == null ? "0.00" : (String) sbsj
				.get("3"));
		zjgxx.setFtsdse(((String) sbsj.get("4")) == null ? "0.00" : (String) sbsj
				.get("4"));
		zjgxx.setFpsdse(((String) sbsj.get("5")) == null ? "0.00" : (String) sbsj
				.get("5"));
		zjgxx.setFzjgftse(((String) sbsj.get("6")) == null ? "0.00" : (String) sbsj
				.get("6"));

		// fzjgxx
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj
				.get(Constant.ZFJGSDSJB_2014_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			Fzjgxx2014VO fzjg = new Fzjgxx2014VO();
			fzjg.setIndex(String.valueOf(i));
			for (int j = 12; j < 19; j++) {
				String id = String.valueOf(j) + "." + String.valueOf(i);
				// System.out.println("util id = " + id);
				// System.out.println("util value = " + (String) sbsj.get(id));
				switch (j) {
				case 12:
					// 分支机构纳税人识别号
					fzjg.setNsrsbh((String) sbsj.get(id));
					break;
				case 13:
					// 分支机构名称
					fzjg.setNsrmc((String) sbsj.get(id));
					break;
				case 14:
					// 分支机构收入总额
					fzjg.setSrze((String) sbsj.get(id));
					break;
				case 15:
					// 分支机构工资总额
					fzjg.setGzze((String) sbsj.get(id));
					break;
				case 16:
					// 分支机构资产总额
					fzjg.setZcze((String) sbsj.get(id));
					break;
				case 17:
					// 分支机构分配比例
					fzjg.setFpbl((String) sbsj.get(id));
					break;
				case 18:
					// 分支机构分配税额
					fzjg.setFpse((String) sbsj.get(id));
					break;
				}
			}
			fzjgxxList.add(fzjg);
		}
		fzjgList.setMxxx(fzjgxxList);
		
		//2014分支机构合计行
		fzjghj.setSrehj((String)sbsj.get("7"));
		fzjghj.setGzehj((String)sbsj.get("8"));
		fzjghj.setZcehj((String)sbsj.get("9"));
		fzjghj.setFpblhj((String)sbsj.get("10"));
		fzjghj.setFpsehj((String)sbsj.get("11"));

		// 企业所得税年报
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML文档信息
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_ZFJGSDSJB_2014);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_ZFJGSDSJB_2014);
		qysdsvo.setYwlx(Constant.CA_YWLXDM_ZFJGSDSJB_2014);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}
	
	/**
	 * 2014企业所得税汇总纳税分支机构分配表 将旧的VO对象转换为XML-VO对象。
	 * 
	 * @param qysdsjd
	 * @param djJbsj
	 * @param cvo
	 * @return ZfjgqysdsjbVO
	 * @throws BaseException
	 * @author gaoyh,2014-03-16,修改整体思路：不改变原来的代码架构，数据项取自A类表
	 */
	public ZfjgqysdsjbVO zfjgxxGetDataFromAconvert2XMLVO(ZfjgqysdsjbBO qysdsbo,
			SWDJJBSJ djJbsj, CzzssdsjbVO cvo, String nszf) throws BaseException {
		
		CzzssdsjbVO czzsjbvo = (CzzssdsjbVO)cvo;
		ZfjgqysdsjbVO qysdsvo = new ZfjgqysdsjbVO();
		Sbxx2014VO sbxx = new Sbxx2014VO();
		Zjgxx2014VO zjgxx = new Zjgxx2014VO();
		FzjgList2014VO fzjgList = new FzjgList2014VO();
		Fzjghj2014VO fzjghj = new Fzjghj2014VO();

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
		// fzjgxx
		
		ArrayList fzjgxxList = new ArrayList();
		int rows = Integer.parseInt((String) sbsj
				.get(Constant.ZFJGSDSJB_2014_MAX_ROW));
		for (int i = 1; i <= rows; i++) {
			Fzjgxx2014VO fzjg = new Fzjgxx2014VO();
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
					break;
				case 18:
					// 分支机构分配税额
					fzjg.setFpse(((String) sbsj.get(id)) == null ? "0.00" : (String) sbsj.get(id));
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
		
		
		// 企业所得税年报
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setZjgxx(zjgxx);
		qysdsvo.setFzjgxx(fzjgList);
		qysdsvo.setFzjghj(fzjghj);

		// XML文档信息
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_ZFJGSDSJB_2014);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_ZFJGSDSJB_2014);
		qysdsvo.setYwlx(Constant.CA_YWLXDM_ZFJGSDSJB_2014);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}

	/**
	 * 查账征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 * @author Guoxh,2014-03-06,修改整体思路：不改变原来的代码架构，只修改数据项
	 */
	public ZfjgqysdsjbBO Zfjgconvert2BO(ZfjgqysdsjbVO qysdsvo)
			throws BaseException {
		ZfjgqysdsjbBO qysdsbo = new ZfjgqysdsjbBO();
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
		qysdsbo.setSbrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSbrq())));
		// skssksrq
		qysdsbo.setSkssjsrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssjsrq())));
		// skssjsrq
		qysdsbo.setSkssksrq(DateTimeUtil
				.stringToTimestamp(getFormatDatetime(qysdsvo.getSbxx()
						.getSkssksrq())));
		//2014版fpblyxq已不存在,modified by guoxh,2014-03-06
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqq());
		// qysdsbo.setFpblyxqq(qysdsvo.getSbxx().getFpblyxqz());
		// fpblyxqq
		//sbsj.put("1", qysdsvo.getSbxx().getFpblyxqq());
		// fpblyxqz
		//sbsj.put("2", qysdsvo.getSbxx().getFpblyxqz());

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
			Fzjgxx2014VO fzjgxx = (Fzjgxx2014VO) fzjgmxList.get(i);
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
		
		//2014版分支机构合计行
		Fzjghj2014VO fzjghj = qysdsvo.getFzjghj();
		sbsj.put("7", fzjghj.getSrehj());
		sbsj.put("8", fzjghj.getGzehj());
		sbsj.put("9", fzjghj.getZcehj());
		sbsj.put("10", fzjghj.getFpblhj());
		sbsj.put("11", fzjghj.getFpsehj());

		qysdsbo.setSbsj(sbsj);

		return qysdsbo;
	}

	/**
	 * 获取季度
	 *
	 * @return String
	 */
	public String getJbDM() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		String jd = Skssrq.preQuarter(curDate);
		return jd;
	}

	/**
	 * 将传入Map的key值进行排序
	 *
	 * @param declareMap
	 *            传入的Map
	 * @return 排序后的List
	 */
	public ArrayList arrangeMapKey(HashMap declareMap) {
		// 定义keyList
		ArrayList keyList = new ArrayList();

		Iterator it = declareMap.keySet().iterator();
		while (it.hasNext()) {
			// 获取当前key
			String key = (String) it.next();
			keyList.add(key);
		}

		int size = keyList.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				double curKey = Double.parseDouble((String) keyList.get(j));
				double nextKey = Double
						.parseDouble((String) keyList.get(j + 1));
				if (curKey > nextKey) {
					double tmp = curKey;
					keyList.set(j, convertStr(nextKey));
					keyList.set((j + 1), convertStr(tmp));
				}
			}
		}
		return keyList;
	}

	/**
	 * 将传入的double值转换成字符串输出 如果double为*.0，则只要整数位
	 *
	 * @param d
	 *            需要转换的double值
	 * @return 转换后的字符串
	 */
	public String convertStr(double d) {
		String str = String.valueOf(d);
		if (str.endsWith(".0")) {
			str = str.substring(0, (str.indexOf(".")));
		}
		return str;
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
	 * 取得核定信息
	 *
	 * @param jsjdm
	 * @param hdvo
	 * @param sbsj
	 * @param swdjjbsj
	 * @param bblx
	 * @throws com.syax.frame.exception.BaseException
	 */
	public void getHdxx(String jsjdm, HdxxVO hdvo, HdzsSbsjVO sbsj,
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
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

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

//		// 数据库连接对象
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		// 减免资格标识
//		boolean jm_type = false;

		try {

			if ("4".equals(jd)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// 取得税款所属开始和结束日期
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// 如果为第4季度的时候调用年报的接口
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// 年报
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

		System.out.println("当前申报的季度jd:" + jd);

		System.out.println("当前申报的申报日期sbrq:" + sbrq);

		System.out.println("当前申报的税款所属开始日期skssksrq:" + skssksrq);

		System.out.println("当前申报的税款所属结束日期skssjsrq:" + skssjsrq);

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
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
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
	 * 核定征收企业 将旧的VO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsjbVO Hdzsconvert2XMLVO(HdzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws com.syax.frame.exception.BaseException {
		SbxxVO sbxx = new SbxxVO();
		HdzsSbsjVO sbsj = new HdzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();
		HdzssdsjbVO qysdsnb = new HdzssdsjbVO();
		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// 纳税人信息。
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
		nsrxx.setNsrsbh(djJbsj.getSwdjzh());

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
		// 核定信息，包含一部分申报信息
		getHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj, bblx);

		// 信息
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsndbo.getJd());
		sbxx.setNd(qysdsndbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSkssjsrq(sdf.format(qysdsndbo.getSkssjsrq()));
		sbxx.setSkssksrq(sdf.format(qysdsndbo.getSkssksrq()));

		// 申报数据
		sbsj.setSyze(qysdsndbo.getSyze());
		sbsj.setBzssr(qysdsndbo.getBzssr());
		sbsj.setMssr(qysdsndbo.getMssr());
		sbsj.setYssre(qysdsndbo.getYssre());
		sbsj.setYssdl(qysdsndbo.getYssdl());
		sbsj.setYnssde(qysdsndbo.getYnssde());
		sbsj.setSl(qysdsndbo.getSl());
		sbsj.setYnsdse(qysdsndbo.getYnsdse());
		sbsj.setYyjsdse(qysdsndbo.getYyjsdse());
		sbsj.setYbsdse(qysdsndbo.getYbsdse());
		sbsj.setSbrqshow(qysdsndbo.getSbrqshow());
		sbsj.setXwqyjmsdse(qysdsndbo.getXwqyjmsdse());
		sbsj.setSwjghdynsdse(qysdsndbo.getSwjghdynsdse());
		
		sbsj.setZczb(qysdsndbo.getZczb());
		sbsj.setZcze(qysdsndbo.getZcze());
		sbsj.setZgrs(qysdsndbo.getZgrs());
		sbsj.setSshydm(
				(null==qysdsndbo.getSshydm()||"".equals(qysdsndbo.getSshydm())||"0.00".equals(qysdsndbo.getSshydm()))
				?djJbsj.getGjbzhydm():qysdsndbo.getSshydm());
		sbsj.setSshy(this.getSshymc(sbsj.getSshydm()));
		
		
		//获取是否是新开户  是：Y 否：N 
		sbsj.setSfxkh(getSfxkh(qysdsndbo.getSkssjsrq(),djJbsj,qysdsndbo.getJd()));
		//获取税款所属期所在年度上一年度征收方式
		sbsj.setSyndZsfsdm(this.getSyndZsfsDm(djJbsj.getJsjdm(), qysdsndbo.getSkssjsrq(),qysdsndbo.getJd()));
		//获取税款所属期所在年度上一年度的汇算清缴申报信息 主表行9、25 附表五45、46、47
		Map map=getSyndHsqjSbxx(sbsj.getSyndZsfsdm(),djJbsj.getJsjdm(), qysdsndbo.getSkssjsrq(),qysdsndbo.getJd());
		sbsj.setSyndZbh6(map.get("syndZbh6").toString());
		sbsj.setSyndZbh25(map.get("syndZbh25").toString());
		sbsj.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
		
		// 企业所得税年报
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML文档信息
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_HDZSSDSJB_2014);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_HDZSSDSJB_2014);
		qysdsnb.setYwlx(Constant.CA_YWLXDM_HDZSSDSJB_2014);

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		return qysdsnb;
	}

	/**
	 * 查帐征收企业 将旧的VO对象转换为XML-VO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsjbVO Czzsconvert2XMLVO(CzzssdsBO qysdsndbo, SWDJJBSJ djJbsj,
			String bblx) throws com.syax.frame.exception.BaseException {
		SbxxVO sbxx = new SbxxVO();
		CzzsSbsjVO sbsj = new CzzsSbsjVO();
		HdxxVO hdxx = new HdxxVO();
		NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();
		CzzssdsjbVO qysdsnb = new CzzssdsjbVO();
		CzzsCbJmxmVO cbSbsj = new CzzsCbJmxmVO();
		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
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
		
		//获取是否是新开户  是：Y 否：N 
		sbsj.setSfxkh(getSfxkh(qysdsndbo.getSkssjsrq(),djJbsj,qysdsndbo.getJd()));		
		//20040212 end
		//获取税款所属期所在年度上一年度征收方式
		sbsj.setSyndZsfsdm(this.getSyndZsfsDm(djJbsj.getJsjdm(), qysdsndbo.getSkssjsrq(),qysdsndbo.getJd()));
		//获取税款所属期所在年度上一年度的汇算清缴申报信息 主表行9、25 附表五45、46、47
		Map map=getSyndHsqjSbxx(sbsj.getSyndZsfsdm(),djJbsj.getJsjdm(), qysdsndbo.getSkssjsrq(),qysdsndbo.getJd());
		sbsj.setSyndZbh6(map.get("syndZbh6").toString());
		sbsj.setSyndZbh25(map.get("syndZbh25").toString());
		sbsj.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
		
		System.out.println("qysdsndbo.getNsfs()========="+qysdsndbo.getNsfs());
		System.out.println("qysdsndbo.getZfjg()========="+qysdsndbo.getZfjg());
		// 企业所得税年报
		qysdsnb.setNsrxx(nsrxx);
		qysdsnb.setSbsj(sbsj);
		qysdsnb.setSbxx(sbxx);
		qysdsnb.setHdxx(hdxx);

		// XML文档信息
		qysdsnb.setXsltVersion(Constant.CA_XSLTDM_CZZSSDSJB_2014);
		qysdsnb.setSchemaVersion(Constant.CA_SCHEMADM_CZZSSDSJB_2014);
		qysdsnb.setYwlx(Constant.CA_YWLXDM_CZZSSDSJB_2014);

		qysdsnb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
				
//		int size=qysdsndbo.getCbSbsj().size();
//        String [] cbhcArray=new String[size];
//        String [] cbsjArray=new String[size];
//        for(int i=0;i<size;i++){
//        	cbhcArray[i]="999."+(i+1);
//        	cbsjArray[i]=(String) qysdsndbo.getCbSbsj().get("999."+(i+1));
//        }
//        cbSbsj.setCbhc(cbhcArray);
//		cbSbsj.setAutoText(cbsjArray);
//		qysdsnb.setCbsbsj(cbSbsj);
		
		//从表数据赋值,2014.01.12,zhangj
		CzzsCbSbsjListVO cbSbsjList=new CzzsCbSbsjListVO();
		HashMap cbSbsjMap=qysdsndbo.getCbSbsj();
		cbSbsjList.setCbMssrxmList((ArrayList)cbSbsjMap.get(Constant.CZZSSDSJB_CBSJ_MSSRXM));
		cbSbsjList.setCbJzmzxmList((ArrayList)cbSbsjMap.get(Constant.CZZSSDSJB_CBSJ_JZMZXM));
		cbSbsjList.setCbJmxmList((ArrayList)cbSbsjMap.get(Constant.CZZSSDSJB_CBSJ_JMXM));
		qysdsnb.setCbsbsj(cbSbsjList);
		return qysdsnb;
	}

	/**
	 * 判断是否是新开户
	 * @param skssksrq
	 * @param djJbsj
	 * @return
	 */
	private String getSfxkh(Timestamp skssksrq,SWDJJBSJ djJbsj,String qh){
		String sfxkh="N";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(skssksrq);
        //税款所属年度
        int skssnd = calendar.get(calendar.YEAR);
        calendar.setTime(djJbsj.getKydjrq());
        //开业登记年度
        int kydjnd = calendar.get(calendar.YEAR);
        if(kydjnd==skssnd){
        	sfxkh="Y";;
        }else{
        	sfxkh="N";
        }
        
		//2014版改动，zhangj，20150212 start
		if(2015==skssnd&&"1".equals(qh)){
			if ((kydjnd == skssnd)||(kydjnd == (skssnd-1))) {
				sfxkh = "Y";
			}
		}
		//zhangj，20150212 end
		
		return sfxkh;
	}
	
    /**
     * 获取税款所属期所在年度的上一年度的征收方式代码
     * @param jsjdm String
     * @param skssrqq Date
     * @return String
     * @throws BaseException
     */
    public String getSyndZsfsDm(String jsjdm, Date skssrqq,String qh) throws com.syax.frame.exception.BaseException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        String zsfsdm=CodeConstant.ZSFSDM_CZZS_03;
        int rdnd = getYear(skssrqq);
        
        //2014版需求改动，zhangj，20150212 start
        if(2015==rdnd&&"1".equals(qh)){
        	rdnd=2014;
        }
        //20150212 end
        
        try {
            con = DBResource.getConnection();
            sql.append("select * from sfdb.sf_jl_qysdszsfs_his where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) < ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            
            if(rs.next())
            {
            	zsfsdm=rs.getString("ZSFSDM")==null ? CodeConstant.ZSFSDM_CZZS_03:rs.getString("ZSFSDM");
            }
            //关闭数据库对象
            rs.close();
            pstmt.close();
            con.close();
            return zsfsdm;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }finally{
        	DBResource.destroyConnection(con);
        }
    }
	
    /**
     * 查询查帐征收表关联分配表数据
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private Map getSyndHsqjSbxx(String zsfsdm,String jsjdm, Date skssrqq,String qh) throws com.syax.frame.exception.BaseException
    {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs1 = null;
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();
        int sknd = getYear(skssrqq);
        
        //2014版需求改动，zhangj，20150212 start
        if(2015==sknd&&"1".equals(qh)){
        	sknd=2014;
        }
        //20150212 end
        try
        {
        	//获取上一年度核定征收行6数据
        	String syndZbh6="0.00";
        	//获取上一年度汇算清缴主表行9数据
       	 	String syndZbh25="0.00";
       	 	//获取上一年度汇算清缴附表5行45、46、47的校验结果
       	 	String syndFb5jyjg="N";
        	conn = DBResource.getConnection();
        	if (CodeConstant.ZSFSDM_CZZS_03.equals(zsfsdm)) {
	            sql.append("select * from sbdb.sb_jl_qysdssbb_zb_nd where ");
	            sql.append("nsrjsjdm = '").append(jsjdm).append("' ");
	            sql.append("and bbqlx = '2' ");
	            sql.append("and qh = '1' ");
	            sql.append("and sknd = '").append(sknd-1).append("' ");
	            sql.append("and ((sbdm='1' and hc in ('25')) or (sbdm='10' and hc in ('45','46','47'))) ");
	            System.out.println("sql:\n" + sql.toString());
	
	            pstmt = conn.prepareStatement(sql.toString());
	            rs = pstmt.executeQuery(sql.toString());
	
	            String zbh25="0.00";
	            String fb5h45="0.00";
	            String fb5h46 = "0.00";
	            String fb5h47 = "";
	            while(rs.next())
	            {
	            	if(rs.getString("hc").equals("25")){
	            		zbh25=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
	            	}
	            	if(rs.getString("hc").equals("45")){
	            		fb5h45=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
	            	}
	            	if(rs.getString("hc").equals("46")){
	            		fb5h46=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
	            	}
	            	if(rs.getString("hc").equals("47")){
	            		fb5h47=rs.getString("yz") == null ? "" : rs.getString("yz");
	            	}
	            }
           
            	syndZbh25=zbh25;
            	if(Double.parseDouble(zbh25)<=30*10000){
            		if(fb5h47.equals("01")){//工业企业
            			if(Double.parseDouble(fb5h45)<=100 && Double.parseDouble(fb5h46)<=3000*10000){
            				syndFb5jyjg="Y";
            			}else{
            				syndFb5jyjg="N";
            			}
            		}else if(fb5h47.equals("02")){//其他企业
            			if(Double.parseDouble(fb5h45)<=80 && Double.parseDouble(fb5h46)<=1000*10000){
            				syndFb5jyjg="Y";
            			}else{
            				syndFb5jyjg="N";
            			}
            		}else{
            			syndFb5jyjg="N";
            		}
            	}else{
            		syndFb5jyjg="N";
            	}
            	//关闭数据库对象
                rs.close();
                pstmt.close();
			}else{
//				syndZbh9=zbh9;
				sql1.append("select * from sbdb.sb_jl_qysdssbb_zb_jd t where nsrjsjdm='").append(jsjdm)
				.append("' and  sbdm='24' and hc='6'").append("and sknd = '").append(sknd-1).append("' ");
				pstmt1 = conn.prepareStatement(sql1.toString());
				rs1 = pstmt1.executeQuery();
				 while(rs1.next())
		            {
		            	if(rs1.getString("hc").equals("6")){
		            		syndZbh6=rs1.getString("yz") == null ? "0.00" : rs1.getString("yz");
		            	}
		            }
				 rs1.close();
	             pstmt1.close();
            }
         
            Map map=new HashMap();
            map.put("syndZbh6", syndZbh6);
            map.put("syndZbh25", syndZbh25);
            map.put("syndFb5jyjg", syndFb5jyjg);
            return map;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }finally{
        	DBResource.destroyConnection(conn);
        }
    }
    
	/**
	 * 取得核定信息
	 *
	 * @param jsjdm
	 * @param hdvo
	 * @param sbsj
	 * @param swdjjbsj
	 * @param bblx
	 * @throws com.syax.frame.exception.BaseException
	 */
	public void getHdxx_cz(String jsjdm, HdxxVO hdvo, CzzsSbsjVO sbsj,
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
		// Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj
		// .getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
		// CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

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

//		// 数据库连接对象
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		// 减免资格标识
//		boolean jm_type = false;

		try {

			if ("4".equals(jd)) {

				skssrq = Skssrq.yearSkssrq(sbrq);

				// 取得税款所属开始和结束日期
				skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

				// 如果为第4季度的时候调用年报的接口
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						"01");// 年报
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

		System.out.println("当前申报的季度jd:" + jd);

		System.out.println("当前申报的申报日期sbrq:" + sbrq);

		System.out.println("当前申报的税款所属开始日期skssksrq:" + skssksrq);

		System.out.println("当前申报的税款所属结束日期skssjsrq:" + skssjsrq);

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
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
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
	 * 核定征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public HdzssdsBO Hdzsconvert2VO(HdzssdsjbVO qysdsjbvo) throws BaseException {
		HdzssdsBO qysdsjbbo = new HdzssdsBO();
		// 纳税人信息
		qysdsjbbo.setJsjdm(qysdsjbvo.getNsrxx().getJsjdm());
		qysdsjbbo.setNsrmc(qysdsjbvo.getNsrxx().getNsrmc());
		qysdsjbbo.setSwjgzzjgdm(qysdsjbvo.getNsrxx().getSwjgzzjgdm());
		// 核定信息
		qysdsjbbo.setCyl(qysdsjbvo.getHdxx().getCyl());
		qysdsjbbo.setDezsse(qysdsjbvo.getHdxx().getDezsse());
		qysdsjbbo.setJmzg(qysdsjbvo.getHdxx().getJmzg());
		qysdsjbbo.setQyzslx(qysdsjbvo.getHdxx().getQyzslx());
		qysdsjbbo.setXzqy(qysdsjbvo.getHdxx().getXzqy());
		qysdsjbbo.setYbjmsl(qysdsjbvo.getHdxx().getYbjmsl());
		// 申报信息
		qysdsjbbo.setFsdm(qysdsjbvo.getSbxx().getFsdm());
		qysdsjbbo.setJd(qysdsjbvo.getSbxx().getJd());
		qysdsjbbo.setNd(qysdsjbvo.getSbxx().getNd());
		qysdsjbbo.setSbrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSbrq())));
		qysdsjbbo.setSkssjsrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSkssjsrq())));
		qysdsjbbo.setSkssksrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSkssksrq())));

		// 申报数据。
		qysdsjbbo.setSbrqshow(qysdsjbvo.getSbsj().getSbrqshow());
		qysdsjbbo.setSyze(qysdsjbvo.getSbsj().getSyze());
		qysdsjbbo.setBzssr(qysdsjbvo.getSbsj().getBzssr());
		qysdsjbbo.setMssr(qysdsjbvo.getSbsj().getMssr());
		qysdsjbbo.setYssre(qysdsjbvo.getSbsj().getYssre());
		qysdsjbbo.setYssdl(qysdsjbvo.getSbsj().getYssdl());
		qysdsjbbo.setYnssde(qysdsjbvo.getSbsj().getYnssde());
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setXwqyjmsdse(qysdsjbvo.getSbsj().getXwqyjmsdse());
		qysdsjbbo.setYyjsdse(qysdsjbvo.getSbsj().getYyjsdse());
		qysdsjbbo.setYbsdse(qysdsjbvo.getSbsj().getYbsdse());
		qysdsjbbo.setSwjghdynsdse(qysdsjbvo.getSbsj().getSwjghdynsdse());

		qysdsjbbo.setZczb(qysdsjbvo.getSbsj().getZczb());
		qysdsjbbo.setZcze(qysdsjbvo.getSbsj().getZcze());
		qysdsjbbo.setZgrs(qysdsjbvo.getSbsj().getZgrs());
		qysdsjbbo.setSshy(qysdsjbvo.getSbsj().getSshy());
		qysdsjbbo.setSshydm(qysdsjbvo.getSbsj().getSshydm());
		
		return qysdsjbbo;
	}

	/**
	 * 查帐征收企业 将XML-VO对象转换为旧的VO对象。
	 *
	 * @param qysdsjb
	 * @return
	 * @throws BaseException
	 */
	public CzzssdsBO Czzsconvert2VO(CzzssdsjbVO qysdsjbvo) throws BaseException {
		CzzssdsBO qysdsjbbo = new CzzssdsBO();
		// 纳税人信息
		qysdsjbbo.setJsjdm(qysdsjbvo.getNsrxx().getJsjdm());
		qysdsjbbo.setNsrmc(qysdsjbvo.getNsrxx().getNsrmc());
		qysdsjbbo.setSwjgzzjgdm(qysdsjbvo.getNsrxx().getSwjgzzjgdm());
		// 核定信息
		qysdsjbbo.setCyl(qysdsjbvo.getHdxx().getCyl());
		qysdsjbbo.setDezsse(qysdsjbvo.getHdxx().getDezsse());
		qysdsjbbo.setJmzg(qysdsjbvo.getHdxx().getJmzg());
		qysdsjbbo.setQyzslx(qysdsjbvo.getHdxx().getQyzslx());
		qysdsjbbo.setXzqy(qysdsjbvo.getHdxx().getXzqy());
		qysdsjbbo.setYbjmsl(qysdsjbvo.getHdxx().getYbjmsl());
		// 申报信息
		qysdsjbbo.setFsdm(qysdsjbvo.getSbxx().getFsdm());
		qysdsjbbo.setJd(qysdsjbvo.getSbxx().getJd());
		qysdsjbbo.setNd(qysdsjbvo.getSbxx().getNd());
		qysdsjbbo.setSbrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSbrq())));
		qysdsjbbo.setSkssjsrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSkssjsrq())));
		qysdsjbbo.setSkssksrq(DateTimeUtil.stringToTimestamp(getFormatDatetime(qysdsjbvo.getSbxx().getSkssksrq())));

		// 申报数据。
		qysdsjbbo.setSbrqshow(qysdsjbvo.getSbsj().getSbrqshow());
		qysdsjbbo.setNsfs(qysdsjbvo.getSbsj().getNsfs());
		qysdsjbbo.setZfjg(qysdsjbvo.getSbsj().getZfjg());
		qysdsjbbo.setYysr(qysdsjbvo.getSbsj().getYysr());
		qysdsjbbo.setYycb(qysdsjbvo.getSbsj().getYycb());
		qysdsjbbo.setLrze(qysdsjbvo.getSbsj().getLrze());
		qysdsjbbo.setTdjsynssde(qysdsjbvo.getSbsj().getTdjsynssde());
		qysdsjbbo.setBzsr(qysdsjbvo.getSbsj().getBzsr());
		qysdsjbbo.setMssr(qysdsjbvo.getSbsj().getMssr());
		qysdsjbbo.setMbyqndks(qysdsjbvo.getSbsj().getMbyqndks());
		qysdsjbbo.setSjlre(qysdsjbvo.getSbsj().getSjlre());
		qysdsjbbo.setSl(qysdsjbvo.getSbsj().getSl());
		qysdsjbbo.setYnsdse(qysdsjbvo.getSbsj().getYnsdse());
		qysdsjbbo.setJmsdse(qysdsjbvo.getSbsj().getJmsdse());
        qysdsjbbo.setSjyyjsdse(qysdsjbvo.getSbsj().getSjyyjsdse());
        qysdsjbbo.setTdywyjsdse(qysdsjbvo.getSbsj().getTdywyjsdse());
		qysdsjbbo.setYbtsdse(qysdsjbvo.getSbsj().getYbtsdse());
		qysdsjbbo.setYqnddjsdse(qysdsjbvo.getSbsj().getYqnddjsdse());
		qysdsjbbo.setBqsjybtsdse(qysdsjbvo.getSbsj().getBqsjybtsdse());
		qysdsjbbo.setZjgyftsdse(qysdsjbvo.getSbsj().getZjgyftsdse());
		qysdsjbbo.setCzjzfpsdse(qysdsjbvo.getSbsj().getCzjzfpsdse());
		qysdsjbbo.setFzjgyftsdse(qysdsjbvo.getSbsj().getFzjgyftsdse());
		qysdsjbbo.setZjgdlscjybmyftsdse(qysdsjbvo.getSbsj().getZjgdlscjybmyftsdse());
//		qysdsjbbo.setZjgycxfzjgyftsdse(qysdsjbvo.getSbsj().getZjgycxfzjgyftsdse());
		qysdsjbbo.setFpbl(qysdsjbvo.getSbsj().getFpbl());
		qysdsjbbo.setFpsdse(qysdsjbvo.getSbsj().getFpsdse());
		qysdsjbbo.setJmzynssde(qysdsjbvo.getSbsj().getJmzynssde());
		qysdsjbbo.setXwqyjmsdse(qysdsjbvo.getSbsj().getXwqyjmsdse());
		
		
		//从表申报数据，zhangj，2014.01.12
		HashMap cbSbsjMap=new HashMap();
		cbSbsjMap.put(Constant.CZZSSDSJB_CBSJ_MSSRXM, qysdsjbvo.getCbsbsj().getCbMssrxmList());
		cbSbsjMap.put(Constant.CZZSSDSJB_CBSJ_JZMZXM, qysdsjbvo.getCbsbsj().getCbJzmzxmList());
		cbSbsjMap.put(Constant.CZZSSDSJB_CBSJ_JMXM, qysdsjbvo.getCbsbsj().getCbJmxmList());
		qysdsjbbo.setCbSbsj(cbSbsjMap);
		
		return qysdsjbbo;
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
//            SfDBAccess db = new SfDBAccess(con);
//            Vector v = new Vector();
            sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) <= ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            System.out.println("zsfs query sql:\n" + sql.toString());

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            //取最后核定的一条记录
            if(rs.next())
            {
                q.setZsfsdm(rs.getString("ZSFSDM"));
                q.setDl(new BigDecimal(rs.getDouble("DL")));
                q.setCyl(new BigDecimal(rs.getDouble("CYL")));
                q.setDe(new BigDecimal(rs.getDouble("DE")));
                //ret.setZsfsdm(rs.getString("ZSFSDM"));
                //ret.setSl(String.valueOf(rs.getDouble("DL")));
                //ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
            }
            else
            {
                return null;
            }

            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),
                    4,
                    BigDecimal.ROUND_HALF_UP)));
            }
            else {
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

    public java.lang.String getZsfsmc(String zsfsdm, Connection con)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String ret = "";
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
                + zsfsdm +
                "'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
//            SfDBAccess db = new SfDBAccess(con);
//            ResultSet rs = db.querySQL(sql);
            if (rs.next())
            {
                //rs.first();
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
     * @decription 查询A类表中纳税方法与总分机构
     * @author gaoyh
     * @modify_date 2014-03-16
     * @param pData
     * @throws BaseException
     * @throws ApplicationException 
     * 
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
			CzzssdsBO czzsbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

			sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
			sql.append("nsrjsjdm = '").append(czzsbo.getJsjdm()).append("' ");
			sql.append("and bbqlx = '").append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			sql.append("and qh = '").append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			sql.append("and sknd = '").append(czzsbo.getNd()).append("' ");
			sql.append("and sbdm = '").append(Constant.TABLE_ID_CZZSSDS_2014).append("' ");
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
	 * @decription 级联查询分配表中所有数据
	 * @author gaoyh
	 * @modify_date 2014-03-16
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
		skssrq = Skssrq.otherSkssrq(curDate);

		StringBuffer querySqlZb = new StringBuffer();
		StringBuffer querySqlCb = new StringBuffer();

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();

			querySqlZb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBRQ,SBBBBH,TBBLX,SBDM,SBBM,HC,SKSSSQQ,SKSSSQZ,YZBS,YZ,SWJSJDM,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ,QXDM FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM= '");

			querySqlZb.append(djJbsj.getJsjdm()).append("' ");
			querySqlZb.append(" AND BBQLX= '");
			querySqlZb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			querySqlZb.append(" AND QH= '");
			querySqlZb.append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			querySqlZb.append(" AND SKND= '");
			querySqlZb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlZb.append(" AND SBDM = '");
			querySqlZb.append(Constant.TABLE_ID_ZFJGSDS_2014).append("' ");

			queryPstmtZb = conn.prepareStatement(querySqlZb.toString());
			queryRsZb = queryPstmtZb.executeQuery(querySqlZb.toString());

			querySqlCb.append("SELECT NSRJSJDM,NSRMC,BBQLX,QH,SKND,SBBBBH,TBBLX,SBDM,SBBM,HC,ZHS,YZBS,YZ,SWJGZZJGDM,CJR,CJSJ,LRR,LRSJ FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM= '");
			querySqlCb.append(djJbsj.getJsjdm()).append("' ");
			querySqlCb.append(" AND BBQLX= '");
			querySqlCb.append(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR).append("' ");
			querySqlCb.append(" AND QH= '");
			querySqlCb.append((String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX)).append("' ");
			querySqlCb.append(" AND SKND= '");
			querySqlCb.append((String) skssrq.get(Skssrq.SKSSRQ_ND)).append("' ");
			querySqlCb.append(" AND SBDM = '");
			querySqlCb.append(Constant.TABLE_ID_ZFJGSDS_2014).append("' ");

			queryPstmtCb = conn.prepareStatement(querySqlCb.toString());
			queryRsCb = queryPstmtCb.executeQuery(querySqlCb.toString());
			
			String queryFlagZb = "0";
			String queryFlagCb = "0";
			
			if(queryRsZb.next()){ queryFlagZb = "1"; }
			if(queryRsCb.next()){ queryFlagCb = "1"; }

			rsMap.put("queryFlagZb", queryFlagZb);
			rsMap.put("queryFlagCb", queryFlagCb);
			
			HashMap sbsjZbMap = new HashMap();
			HashMap sbsjCbMap = new HashMap();
			
			while (queryRsZb.next()) {
				if (queryRsZb.getString("hc").equals("10")) {
					sbsjZbMap.put("fpbl", queryRsZb.getString("yz"));
				}
				if (queryRsZb.getString("hc").equals("11")) {
					sbsjZbMap.put("fpsdse", queryRsZb.getString("yz"));
				}
			}
			while (queryRsCb.next()) {
				if (queryRsCb.getString("hc").equals("17") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpbl", queryRsCb.getString("yz"));
				}
				if (queryRsCb.getString("hc").equals("18") && queryRsCb.getString("zhs").equals("1")) {
					sbsjCbMap.put("fpsdse", queryRsCb.getString("yz"));
				}
			}
			
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
	 * @description 级联保存分配表中不可修改部分数据
	 * @author gaoyh
	 * @modify_date 2014-03-16
	 * @param pData
	 * @param nsfs_zfjg
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public void saveCascadeZfjgData(Map pData) throws BaseException, ApplicationException {

		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存数据");
		}
		
		Connection conn = null;
		CzzssdsBO qysdsndbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
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
		skssrq = Skssrq.otherSkssrq(curDate);

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
								yzZb[i] = qysdsndbo.getFzjgyftsdse();
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
					//double tmpFpblHj = Double.parseDouble(fpblHjOld.substring(0,fpblHjOld.indexOf("%")));
					double tmpFpblOld = Double.parseDouble(fpblOld)*100;
					double tmpFpblNew = Double.parseDouble(qysdsndbo.getFpbl());
					
					double tmpFpsdseHj = Double.parseDouble((String)sbsjZbMap.get("fpsdse"));			
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
								yzZb[i] = qysdsndbo.getFzjgyftsdse();
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
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, Constant.QYSDSJB_VERSION_2014);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9, Constant.TABLE_ID_ZFJGSDS_2014);
						insertPstmtZb.setString(10, Constant.TABLE_NAME_ZFJGSDS_2014);
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
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtZb.setString(7, Constant.TABLE_ID_ZFJGSDS_2014);
						updatePstmtZb.setString(8, hcZb[i]);
						updatePstmtZb.addBatch();
					}
					updatePstmtZb.executeBatch();
				}
			}

			if(qysdsndbo.getZfjg().equals("2")){
				if (queryFlagZb.equals("0")) {
					for (int i = 0; i < hcZb.length; i++) {
						insertPstmtZb.setString(1, djJbsj.getJsjdm());
						insertPstmtZb.setString(2, djJbsj.getNsrmc());
						insertPstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtZb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtZb.setDate(6, new java.sql.Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
						insertPstmtZb.setString(7, Constant.QYSDSJB_VERSION_2014);
						insertPstmtZb.setString(8, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtZb.setString(9, Constant.TABLE_ID_ZFJGSDS_2014);
						insertPstmtZb.setString(10, Constant.TABLE_NAME_ZFJGSDS_2014);
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
					for (int i = 0; i < 7; i++) {
						insertPstmtCb.setString(1, djJbsj.getJsjdm());
						insertPstmtCb.setString(2, djJbsj.getNsrmc());
						insertPstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						insertPstmtCb.setString(5, (String) skssrq.get(Skssrq.SKSSRQ_ND));
						insertPstmtCb.setString(6, Constant.QYSDSJB_VERSION_2014);
						insertPstmtCb.setString(7, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						insertPstmtCb.setString(8, Constant.TABLE_ID_ZFJGSDS_2014);
						insertPstmtCb.setString(9, Constant.TABLE_NAME_ZFJGSDS_2014);
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
						updatePstmtZb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtZb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtZb.setString(7, Constant.TABLE_ID_ZFJGSDS_2014);
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
						updatePstmtCb.setString(5, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
						updatePstmtCb.setString(6, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
						updatePstmtCb.setString(7, Constant.TABLE_ID_ZFJGSDS_2014);
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
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    /**
	 * @decription 级联删除分配表数据
	 * @author gaoyh
	 * @modify_date 2014-03-16
	 * @param pData
	 * @throws BaseException
	 * @throws ApplicationException
	 */
    public void deleteCascadeZfjgData(Map pData) throws BaseException,
			ApplicationException {
		if (pData == null) {
			throw new ApplicationException("传入保存参数为空！无法保存申报主数据");
		}

		Connection conn = null;

//		SWDJJBSJ djJbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		CzzssdsBO czzsbo = (CzzssdsBO) pData.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// 取得税款所属日期Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();
		skssrq = Skssrq.otherSkssrq(curDate);

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();

			PreparedStatement detelePstmtZb = conn.prepareStatement("DELETE  FROM SBDB.SB_JL_QYSDSSBB_ZB_JD t WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			PreparedStatement detelePstmtCb = conn.prepareStatement("DELETE FROM SBDB.SB_JL_QYSDSSBB_CB_JD WHERE NSRJSJDM=? AND SKND=? AND BBQLX=? AND QH=? AND SBDM=?");
			
			detelePstmtZb.setString(1, czzsbo.getJsjdm());
			detelePstmtZb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtZb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtZb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
			detelePstmtZb.setString(5, Constant.TABLE_ID_ZFJGSDS_2014);
			detelePstmtZb.addBatch();

			detelePstmtCb.setString(1, czzsbo.getJsjdm());
			detelePstmtCb.setString(2, (String) skssrq.get(Skssrq.SKSSRQ_ND));
			detelePstmtCb.setString(3, QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			detelePstmtCb.setString(4, (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX));
			detelePstmtCb.setString(5, Constant.TABLE_ID_ZFJGSDS_2014);
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
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    /**
	 * @description 释放数据库资源
	 * @author gaoyh
	 * @modify_date 2014-03-15
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
    
	/**
	 * 2014固定资产加速折旧（扣除）预缴情况统计表将旧的VO对象转换为XML-VO对象。
	 * 
	 * @param qysdsjd
	 * @param djJbsj
	 * @param cvo
	 * @return ZfjgqysdsjbVO
	 * @throws BaseException
	 * @author zhangj
	 */
	public GdzcjszjyjqkjbVO gdzcxxGetDataFromAconvert2XMLVO(GdzcjszjyjqkjbBO qysdsbo,SWDJJBSJ djJbsj) throws BaseException {
		
		GdzcjszjyjqkjbVO qysdsvo = new GdzcjszjyjqkjbVO();
		Sbxx2014VO sbxx = new Sbxx2014VO();
		GdzcjszjyjqkjbNsrxxVO gdzcNsrxxVO=new GdzcjszjyjqkjbNsrxxVO();
		GdzcjszjyjqkjblistVO gdzclistVO= new GdzcjszjyjqkjblistVO();

		// 获得系统当前日期
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// ////////////////////////////////////
		// 填充VO。
		// ///////////////////////////////////
//		HashMap sbsj = (HashMap) qysdsbo.getSbsj();
//		System.out.println("==========2014========");
//		System.out.println(sbsj.toString());
		// sbxx
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setJd(qysdsbo.getJd());
		sbxx.setNd(qysdsbo.getNd());
		sbxx.setSbrq(sdf.format(curDate));
		sbxx.setSbrqShow(qysdsbo.getSbrqshow());
		sbxx.setSkssksrq(sdf.format(qysdsbo.getSkssksrq()));
		sbxx.setSkssjsrq(sdf.format(qysdsbo.getSkssjsrq()));
		
		
		System.out.println(sbxx.getJd());
		System.out.println(sbxx.getSbrq());
		System.out.println(sbxx.getSbrqShow());
		System.out.println(sbxx.getSkssksrq());
		System.out.println(sbxx.getSkssjsrq());
		System.out.println(djJbsj.getJsjdm());
		
		gdzclistVO=qysdsbo.getSbsj();
		
		gdzcNsrxxVO.setNsrsbh(qysdsbo.getNsrsbh());
		gdzcNsrxxVO.setNsrmc(qysdsbo.getNsrmc());
		gdzcNsrxxVO.setJsjdm(qysdsbo.getJsjdm());
		if(qysdsbo.getSshy()==null||qysdsbo.getSshy().equals("")){
			gdzcNsrxxVO.setSshy(djJbsj.getGjbzhymc());
			gdzcNsrxxVO.setSshydm(djJbsj.getGjbzhydm());
		}else{
			gdzcNsrxxVO.setSshy(qysdsbo.getSshy());
			gdzcNsrxxVO.setSshydm(qysdsbo.getSshydm());
		}
		System.out.println(".................sshy: "+gdzcNsrxxVO.getSshy());
//		sbxx.setSbrq("2014-12-02");
//		sbxx.setSbrqShow("2014-12-02");
//		sbxx.setSkssksrq("2014-01-01");
//		sbxx.setSkssjsrq("2014-12-01");		
		
		
		
		// 企业所得税年报
		qysdsvo.setSbxx(sbxx);
		qysdsvo.setNsrxx(gdzcNsrxxVO);
		qysdsvo.setSbsjlist(gdzclistVO);
		
		// XML文档信息
		qysdsvo.setXsltVersion(Constant.CA_XSLTDM_GDZCJSZJYJQKJB_2014);
		qysdsvo.setSchemaVersion(Constant.CA_SCHEMADM_GDZCJSZJYJQKJB_2014);
		qysdsvo.setYwlx(Constant.CA_YWLXDM_GDZCJSZJYJQKJB_2014);
		qysdsvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return qysdsvo;
	}   
    
    
    public String getSshymc(String sshydm){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sshymc="";
		String sql="select GJBZHYMC from DMDB.GY_DM_GJBZHY where GJBZHYDM='"+sshydm+"'";
		try{	
			con = DBResource.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery(sql.toString());
			if (rs.next()) {
				sshymc=rs.getString("GJBZHYMC")==null?"":rs.getString("GJBZHYMC");
			}			
	        //关闭数据库对象
	        rs.close();
	        pstmt.close();
	        con.close();
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }finally{
	    	DBResource.destroyConnection(con);
	    }
	    System.out.println(".....sshymc: "+sshymc);
		return sshymc;
    }
  
	/**
	 * 查账征收季报A类初始化代码表
	 * @param czzssdsjbForm
	 */
	public static Map getCodeTable(){
		HashMap codeMap=new HashMap();
		//初始化免税收入项目代码表
		codeMap.put(Constant.CZZSSDSJB_DM_MSSRXM, getMssrxmCodeList());		
		//初始化减征、免征项目代码表
		codeMap.put(Constant.CZZSSDSJB_DM_JZMZXM, getJzmzxmCodeList());
		//初始化减征、免征项目代码表
		codeMap.put(Constant.CZZSSDSJB_DM_JMXM, getJmxmCodeList());
		return codeMap;
	}   
	/**
	 * 初始化免税收入项目代码表
	 * @param czzssdsjbForm
	 */
    public static List getMssrxmCodeList(){
    	String sql="SELECT MSSRXMDM,LPAD('  ',2*(LEVEL - 1)) || MSSRXMMC MSSRXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_MSSRXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR MSSRXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List mssrxmList=new ArrayList();
    	try {
			conn=DBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("MSSRXMDM");
				String mc=rs.getString("MSSRXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				mssrxmList.add(dmvo);	
			}
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		return mssrxmList;
    }
    /**
     * 初始化减征、免征项目代码表
     * @param czzssdsjbForm
     */
    public static List getJzmzxmCodeList(){
    	String sql="SELECT JZMZXMDM,LPAD('  ',2*(LEVEL - 1)) || JZMZXMMC JZMZXMMC, level "+
      "FROM DMDB.SB_DM_QYSDS_JZMZXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR JZMZXMDM = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jzmzxmList=new ArrayList();
    	try {
			conn=DBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("JZMZXMDM");
				String mc=rs.getString("JZMZXMMC");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jzmzxmList.add(dmvo);	
			}
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		return jzmzxmList;
    }
    /**
     * 初始化减免项目代码表
     * @param czzssdsjbForm
     */
    public static List getJmxmCodeList(){
    	String sql="SELECT jmxmdm,LPAD('  ',2*(LEVEL - 1)) || jmxmmc jmxmmc, level "+
      "FROM DMDB.SB_DM_QYSDS_JMXM where zfbs='0' START WITH fjddm IS NULL CONNECT BY PRIOR jmxmdm = fjddm";
    	Statement st=null;
    	ResultSet rs=null;
    	Connection conn=null;
    	List jmxmList=new ArrayList();
    	try {
			conn=DBResource.getConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				String dm=rs.getString("jmxmdm");
				String mc=rs.getString("jmxmmc");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				jmxmList.add(dmvo);	
			}
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				  try{
				      rs.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(st!=null){
				  try{
					  st.close();
				  }catch (Exception ex) {
					  ex.printStackTrace();
				  }
				}
			if(conn!=null){
			  try{
				  conn.close();
			  }catch (Exception ex) {
				  ex.printStackTrace();
			  }
			}
		}
		return jmxmList;
    }

}
