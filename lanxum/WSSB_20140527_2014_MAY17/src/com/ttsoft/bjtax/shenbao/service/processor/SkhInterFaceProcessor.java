package com.ttsoft.bjtax.shenbao.service.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.jiekou.InterFaceCoding;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.service.constant.SkhConstant;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbResult;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzMx;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbSzsmmx;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.TypeChecker;
import java.sql.*;

/**
 * <p>
 * Title: 税库行接口业务处理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: SYAX
 * </p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */

public class SkhInterFaceProcessor extends InterFaceProcessor {

	/**
	 * 无申报银行缴款根据申报明细生成一票多税缴款书 在生成税票以后需要将账务标志锁定为最后一位为9，以防止出现误删除的情况
	 *
	 * @param yhsbInfo
	 *            申报数据
	 * @param hjzse
	 *            申报合计总应纳税额
	 * @return 返回值处理YhsbResult对象
	 * @throws java.lang.Exception
	 *             应用异常
	 */
	private static String NULL_ZERO = "";
        protected static String getYpysJksQueryOrderByPostfixFoSKH() {
          StringBuffer sb = new StringBuffer();
          sb.append(" ORDER BY a.sklxdm,a.zsswjgzzjgdm,a.ysjcdm,a.szdm,a.yskmdm,a.skssksrq,a.skssjsrq,a.xjrq"); //此行进行排序
          return sb.toString();
   }
	public static YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo,
			double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.句柄申明
		String jsjdm = null;
		YhsbSzMx yhsbSzmx = null;
		YhsbSzsmmx yhsbSzsmmx = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		// 1.入口参数校验
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null) {
			throw new Exception("无申报银行缴款申报数数据非法,请检查!");
		}
		// 2.初始化
		jsjdm = yhsbInfo.getJsjdm();
		yhsbResult.setJsjdm(jsjdm);
		// 3.业务过程
		try {
			// /3.-1.获取资源
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.0.调用登记接口获取纳税人基本数据
			ServiceProxy serviceProxy = new ServiceProxy();
			String swjgzzjgdm = "";
			String qxdm = "";
			String djzclxdm = "";
			String gjbzhydm = "";
			String lsgxdm = "";
			String nsrmc = "";
			String jydzlxdm = "";
			//
			TypeChecker tc = new TypeChecker();
			String flag = TypeChecker.isQyyh(jsjdm);
			//
			if (ApplicationConstant.JSJDM_LX_QY.equals(flag)) { // 企业用户
				Map map = serviceProxy.getDjInfo(jsjdm);
				SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
				if (swdjjbsj == null) {
					throw ExceptionUtil
							.getBaseException(new ApplicationException(
									"无法得到该纳税人的登记数据！" + jsjdm));
				}
				swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); // 税务机关
				qxdm = swjgzzjgdm.substring(0, 2);
				djzclxdm = swdjjbsj.getDjzclxdm();
				gjbzhydm = swdjjbsj.getGjbzhydm();
				lsgxdm = swdjjbsj.getLsgxdm();
				nsrmc = swdjjbsj.getNsrmc();
				jydzlxdm = swdjjbsj.getJydzlxdm();
				// /3.1.判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
				if (!FriendHelper.checkSwjgForJks(CodeConstant.JKS_JIEKOU_SBSJ,
						swdjjbsj)
						|| !FriendHelper.checkNsrztForJks(
								CodeConstant.JKS_JIEKOU_SBSJ, swdjjbsj)) {
					// 非正常户
					throw ExceptionUtil
							.getBaseException(new ApplicationException(
									"该纳税人状态不为正常，不能生成缴款书！" + jsjdm));
				}
			} else if (ApplicationConstant.JSJDM_LX_ZRR.equals(flag)) { // 自然人用
				Map map = serviceProxy.getZrrDjInfo(jsjdm);
				ZRRJBSJ zrrJbsj = (ZRRJBSJ) map.get("zrrjbsj");
				if (zrrJbsj == null) {
					throw new Exception("不存在该自然人信息");
				}

				swjgzzjgdm = zrrJbsj.getSwjgzzjgdm(); // 税务机关
				qxdm = swjgzzjgdm.substring(0, 2);
				if (zrrJbsj.getGjdm().equals("CHN")) {
					djzclxdm = CodeConstant.DEFAULT_CHINA_ZRR_DJZCLXDM; // 登记注册类型代码
				} else {
					djzclxdm = CodeConstant.DEFAULT_FOREIGN_ZRR_DJZCLXDM; // 登记注册类型代码
				}
				gjbzhydm = CodeConstant.DEFAULT_ZRR_GJBZHYDM;
				lsgxdm = "";
				nsrmc = zrrJbsj.getNsrmc();
				jydzlxdm = zrrJbsj.getZzdh();

			} else {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"该纳税人无法判定登记类型！" + jsjdm));
			}
			// /3.1.5 判断是否联网 add by hazhengze 2006-01-22 18:00
			if (!InterFaceCoding.getInstance().isLw(swjgzzjgdm,
					yhsbInfo.getYhdm(), conn)) {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"该纳税人系非联网区县纳税人或该银行系非联网银行！"));
			}

			// /3.2.判断纳税人税务机关是否符合
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			yhsbResult.setQxdm(qxdm);
			log("税库行征收机关区县代码: ======= " + yhsbInfo.getQxdm());
			log("登记征收机关代码: ======= " + swjgzzjgdm);
			// /3.3.生成准备数据
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String nd = new SimpleDateFormat("yyyy").format(now);
			String sklxdm = yhsbInfo.getSklxdm();
			if (sklxdm == null || sklxdm.length() <= 0) {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"税款类型代码不能为空！"));
			}
			String sjly = ApplicationConstant.SJLY_YHSB_YHLR;
			String sbfsdm = ApplicationConstant.FSDM_YHSB_YHLR;
			String bz = yhsbInfo.getBz();
			String yhdm = yhsbInfo.getYhdm();
			String yhmc = yhsbInfo.getYhmc();
			;
			String zh = yhsbInfo.getZh();
			String lrr = yhsbInfo.getLrr();
			BigDecimal sjjehj = new BigDecimal(hjzse);
			// /3.4.生成缴款数据
			List sbjkmxList = new ArrayList();
			// /3.4.0.生成缴款数据填写申报明细信息
			for (int i = 0; i < yhsbInfo.getSzList().size(); i++) {
				yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(i);
				for (int j = 0; j < yhsbSzmx.getSzsmList().size(); j++) {
					yhsbSzsmmx = (YhsbSzsmmx) yhsbSzmx.getSzsmList().get(j);
					String szsmdm = yhsbSzsmmx.getSzsmdm();
					if (szsmdm == null) {
						throw ExceptionUtil
								.getBaseException(new ApplicationException(
										"金额不能为空！"));
					}
					BigDecimal sjje = yhsbSzsmmx.getSjje();
					if (sjje == null) {
						throw ExceptionUtil
								.getBaseException(new ApplicationException(
										"金额不能为空！"));
					}
					BigDecimal jsje = yhsbSzsmmx.getJsje();
					BigDecimal kssl = yhsbSzsmmx.getKssl();
					BigDecimal sl = yhsbSzsmmx.getSl();
					//
					Szsm szsm = getSzsm(szsmdm);
					// 生成申报缴款明细
					Sbjkmx sbjkmx = new Sbjkmx();
					sbjkmx.setSzsmdm(szsmdm);
					sbjkmx.setZqlxdm(szsm.getZqlxdm()); // 征期类型代码
					sbjkmx.setJsjdm(jsjdm);
					sbjkmx.setSjse(sjje);
					sbjkmx.setRkje(sjje);
					sbjkmx.setKssl(kssl);
					sbjkmx.setSl(sl);
					sbjkmx.setJsje(jsje);
					sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
					sbjkmx.setNd(nd);
					sbjkmx.setCjrq(now); // 创建日期
					sbjkmx.setLrrq(now); // 录入日期
					sbjkmx.setQxdm(qxdm); // 区县代码
					// 税款所属开始日期
					if (yhsbSzmx.getSkssksrq() != null) {
						sbjkmx.setSkssksrq(yhsbSzmx.getSkssksrq());
					}
					// 税款所属结束日期
					if (yhsbSzmx.getSkssjsrq() != null) {
						sbjkmx.setSkssjsrq(yhsbSzmx.getSkssjsrq());
					}
					sbjkmxList.add(sbjkmx);
				}
			}
			// /3.4.0.生成缴款数据填写申报主数据
			Sbjkzb sbjkzb = new Sbjkzb();
			sbjkzb.setJsjdm(jsjdm);
			// 从税务登记得到的数据
			sbjkzb.setDjzclxdm(djzclxdm);
			sbjkzb.setGjbzhydm(gjbzhydm);
			sbjkzb.setLsgxdm(lsgxdm);
			sbjkzb.setNsrmc(nsrmc);
			sbjkzb.setSwjgzzjgdm(swjgzzjgdm);
			sbjkzb.setLrrq(now);
			sbjkzb.setCjrq(now);
			sbjkzb.setFsdm(sbfsdm); // 征收方式为银行申报缴款,代码值为7
			if (lrr != null && (!"".equals(lrr))) {
				sbjkzb.setLrr(lrr);
			} else {
				sbjkzb.setLrr(jsjdm);
			}
			sbjkzb.setRkje(sjjehj);
			sbjkzb.setSbrq(TinyTools.second2Day(now)); // 申报日期
			sbjkzb.setZyrq(TinyTools.second2Day(now)); // 帐页日期，初始为申报日期
			sbjkzb.setSjje(sjjehj);
			sbjkzb.setSklxdm(sklxdm);
			sbjkzb.setZsswjgzzjgdm(swjgzzjgdm);
			sbjkzb.setNd(nd);
			sbjkzb.setBz(bz);
			sbjkzb.setSjly(sjly); // 数据来源
			sbjkzb.setQxdm(qxdm); // 区县代码
			sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB); // 默认为‘已申报’
			sbjkzb.setJydzlxdm(jydzlxdm); // 联系电话

			// 限缴日期
			if (yhsbInfo.getXjrq() != null) {
				sbjkzb.setXjrq(yhsbInfo.getXjrq());
			}

			if (yhsbInfo.getYhdm() != null && yhsbInfo.getYhmc() != null) {
				sbjkzb.setYhdm(yhsbInfo.getYhdm()); // 银行代码
				sbjkzb.setYhmc(yhsbInfo.getYhmc()); // 银行名称
				sbjkzb.setZh(yhsbInfo.getZh()); // 银行账号
			} else {
				throw ExceptionUtil.getBaseException(new ApplicationException(
						"银行信息不能为空！"));
			}
			// 创建数据对象
			DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
			declareInfor.setIsReturnPaymentInfo(true); // 返回数据
			declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM); // 默认为一票一税

			// 把生成的数据插入数据表中并返回
			ZhsbProcessor zhsbPro = new ZhsbProcessor();
			String sbbh;
			try {
				sbbh = zhsbPro.getSbbh(sbjkzb.getJsjdm());
			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			// 生成申报缴款数据
			List dataList = (List) zhsbPro.createJkInfor(declareInfor, sbbh);
			// /3.5.根据申报编号生成一票多税缴款书,并且回写税票号码到数据库
			// //3.5.0.获取一票多税缴款书
			rtnList = getYpdsJks(sbbh);
			// //3.5.1.生成税票号码并回写至数据库
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.printContent();
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(",yhmc=(select yhmc from dmdb.gy_dm_yh where yhdm = "
						+ StringUtils.getSQLStr(yhsbInfo.getYhdm()) + ")");
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND swjgzzjgdm=");
				sb.append(StringUtils.getSQLStr(swjgzzjgdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(jks.getSbbh()));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		yhsbResult.setRnObjectClassName("java.util.List");
		yhsbResult.setRnObject(rtnList);
		// 99.
		return yhsbResult;
	}

	/**
	 * 有申报银行缴款根据申报明细生成一票多税缴款书
	 *
	 * @param yhsbInfo
	 *            申报数据
	 * @param hjzse
	 *            申报合计总应纳税额
	 * @return 返回值处理YhsbResult对象
	 * @throws java.lang.Exception
	 *             应用异常
	 */
	public static YhsbResult generateYpdsJksWithSbInfo(YhsbInfo yhsbInfo,
			double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.句柄申明
		String jsjdm = null;
		String swjgzzjgdm = null;
		String qxdm = null;
		String sbbh = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		// 1.入口参数校验
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null
				|| yhsbInfo.getSbbh() == null || yhsbInfo.getQxdm() == null) {
			throw new Exception("有申报银行缴款根据申报明细生成一票多税缴款书接口调用入口参数非法.");
		}
		// 2.初始化
		jsjdm = yhsbInfo.getJsjdm();
		yhsbResult.setJsjdm(jsjdm);
		sbbh = yhsbInfo.getSbbh();
		// 3.业务过程
		try {
			// /3.0.获取资源
			conn = db.getConnection();
			st = conn.createStatement();

			// /3.1.根据申报编号生成一票多税缴款书
			rtnList = getYpdsJks(sbbh);
			// 3.1.1.获取属性
			if (rtnList.size() == 0) {
				throw new Exception("有申报银行缴款根据申报明细生成一票多税缴款书接口无法生成缴款书数据.");
			}
			// 3.1.2.获取属性
			swjgzzjgdm = ((YPDSJKS) rtnList.get(0)).getSwjgzzjgdm();
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			qxdm = swjgzzjgdm.substring(0, 2);
			yhsbResult.setQxdm(qxdm);
			//
			yhsbResult.setRnObjectClassName("java.util.List");
			yhsbResult.setRnObject(rtnList);
			// /3.2.更新库中申报数据
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(sbbh));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

	/**
	 * 有申报银行缴款根据申报明细生成一票多税缴款书,可选税种
	 *
	 * @param yhsbInfo
	 *            申报数据,应当包括选择的所有税种税目信息
	 * @param hjzse
	 *            申报合计总应纳税额
	 * @return 返回值处理YhsbResult对象
	 * @throws java.lang.Exception
	 *             应用异常
	 */
	public static YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(
			YhsbInfo yhsbInfo, double hjzse) throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.句柄申明
		String jsjdm = null;
		String sbbh = null;
		String swjgzzjgdm = null;
		String qxdm = null;
		List rtnList = null;
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		DeclareInfor declareInfo = null;
		// 1.入口参数校验
		if (yhsbInfo == null || hjzse <= 0 || yhsbInfo.getJsjdm() == null
				|| yhsbInfo.getSbbh() == null || yhsbInfo.getQxdm() == null
				|| (!yhsbInfo.checkValidation())) {
			throw new Exception("有申报银行缴款申报数据非法,请检查");
		}
		// 2.初始化
		jsjdm = yhsbInfo.getJsjdm();
		sbbh = yhsbInfo.getSbbh();
		// 3.业务过程
		try {
			// /3.0.获取资源
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.1.根据申报编号和计算机代码生成一票多税缴款书
			// //3.1.0.根据申报编号查询数据
			List declareInfos = queryDataBySBBH(jsjdm, sbbh, conn);
			// //3.1.1.
			swjgzzjgdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getSwjgzzjgdm();
			qxdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb().getQxdm();
			yhsbResult.setJsjdm(jsjdm);
			yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
			yhsbResult.setQxdm(qxdm);

			/** *************modify by hezy 增加一次申报的税款必须一次缴纳的限制*************** */
			if (getSzNumbyDeclareInfo(declareInfos) > yhsbInfo.getSzList()
					.size()) {
				throw new Exception("一次申报的税款必须一次性缴纳,不能分次缴");
			}

			// //3.1.1.根据入口的选择税种过滤数据
			YhsbSzMx yhsbSzmx = null;
			for (int i = 0; i < declareInfos.size(); i++) {
				declareInfo = (DeclareInfor) declareInfos.get(i);
				boolean delFlag = true;
				for (int j = 0; j < yhsbInfo.getSzList().size(); j++) {
					yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(j);
					if (declareInfo.getSbjkzb().getSzdm().equals(
							yhsbSzmx.getSzdm())) {
						delFlag = false;
						break;
					}
				}
				if (delFlag) {
					declareInfos.remove(i--);
				}
			}
			// //3.1.2.根据入口的选择税种进行校验
                        double szje = 0.00D;
			for (int j = 0; j < yhsbInfo.getSzList().size(); j++) {
				yhsbSzmx = (YhsbSzMx) yhsbInfo.getSzList().get(j);
//				double sjzse = getSzje(declareInfos, yhsbSzmx.getSzdm());
				szje += yhsbSzmx.getSjje().doubleValue();

				log("szje=" + szje);
			}
                        double sjzse = getHjSzje(declareInfos);
                        double res = szje - sjzse;
                        if ((new BigDecimal(res)).abs().doubleValue() > ApplicationConstant.JE_ERROR_OFFSET) {
                                yhsbSzmx.printContent();
                                throw new Exception("缴款金额和库中金额不符!");
                        }
			/** *************modify by hezy 判断税票状态*************** */
			String zwbs = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getZwbs();
			String zh = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getZh();
			String yhdm = ((DeclareInfor) declareInfos.get(0)).getSbjkzb()
					.getYhdm();
			if (zh == null) {
				zh = "";
			}
			if (zwbs != null && !zwbs.trim().equals("")) {
				if (zwbs.charAt(0) != '0') {
					throw new Exception("此申报已缴款!");

				} else if (zwbs.substring(17, 20).equals(
						ApplicationConstant.ZWBS_YHKK_SUCCESS)) {
					throw new Exception("此申报已缴款!");
				} else if (zwbs.substring(19, 20).equals(
						ApplicationConstant.ZWBS_YHKK_LOCK)) {

					if (!yhsbInfo.getYhdm().equals(yhdm)) {
						throw new Exception("此申报已在其他银行进行缴款中!");
					}
					if (!zh.trim().equals(yhsbInfo.getZh().trim())) {
						if (yhsbInfo.getZh() != null
								&& !yhsbInfo.getZh().trim().equals("")
								&& !zh.trim().equals("")) {
							throw new Exception("本次银行申报请求的账号与税票中的账号不符!");
						}
					}

					// 重新生成税票数据
					sb = new StringBuffer();
					sb.append(getYpysJksQueryPrefix());
					sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
					sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
					sb.append(" and a.jsjdm=");
					sb.append(StringUtils.getSQLStr(jsjdm));
					sb.append(" and a.sbbh=");
					sb.append(StringUtils.getSQLStr(sbbh));
					sb.append(" AND a.zwbs LIKE '0%9'");
					sb.append(" AND a.jkpzh in (");
					for (int i = 0; i < declareInfos.size(); i++) {
						declareInfo = (DeclareInfor) declareInfos.get(i);
						if (i != 0) {
							sb.append(",");
						}
						sb.append(StringUtils.getSQLStr(declareInfo.getSbjkzb()
								.getJkpzh()));
					}
					sb.append(")");
					sb.append(getYpysJksQueryOrderByPostfixFoSKH());
					sql = sb.toString();
					// /3.2.进行查询获取结果集
					InterFaceProcessor.log(sql);
					rs = st.executeQuery(sql);

					List tmpList = new ArrayList();
					Map jksmx = null;
					while (rs.next()) {
						jksmx = new HashMap();
						for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
							jksmx
									.put(
											ApplicationConstant.JKS_YPYS_MX_KEYS[i],
											rs
													.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
						}
						InterFaceProcessor.log("第一轮查询的一票一税明细==" + jksmx);
						tmpList.add(jksmx);
					}
					rs.close();
					// /3.4.调用子方法进行分票
					rtnList = InterFaceProcessor.getYpdsJksForSkh(tmpList);

					yhsbResult.setRnObjectClassName("java.util.List");
					yhsbResult.setRnObject(rtnList);

					/**/
					String sphm = declareInfo.getSbjkzb().getSphm();
					for (int i = 0; i < rtnList.size(); i++) {
						jks = (YPDSJKS) rtnList.get(i);
						jks.setSphm(sphm);
					}

					// 98.
					yhsbResult
							.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
									+ SkhConstant.RESULT_CODE_MID_DEFAULT
									+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
					yhsbResult
							.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
					// 99.
					return yhsbResult;

				}
			} else {
				throw new Exception("申报数据存在错误!");
			}

			// //3.1.3.根据过滤后的数据生成一票多税缴款书
			sb = new StringBuffer();
			sb.append(getYpysJksQueryPrefix());
			sb.append(" FROM SBDB.SB_JL_SBJKZB a,SBDB.SB_JL_SBJKMX b");
			sb.append(" WHERE b.jkpzh=a.jkpzh and b.jsjdm=a.jsjdm");
			sb.append(" and a.jsjdm=");
			sb.append(StringUtils.getSQLStr(jsjdm));
			sb.append(" and a.sbbh=");
			sb.append(StringUtils.getSQLStr(sbbh));
			sb.append(" AND a.zwbs LIKE '0%0'");
			sb.append(" AND a.jkpzh in (");
			for (int i = 0; i < declareInfos.size(); i++) {
				declareInfo = (DeclareInfor) declareInfos.get(i);
				if (i != 0) {
					sb.append(",");
				}
				sb.append(StringUtils.getSQLStr(declareInfo.getSbjkzb()
						.getJkpzh()));
			}
			sb.append(")");
			sb.append(getYpysJksQueryOrderByPostfix());
			sql = sb.toString();
			// /3.2.进行查询获取结果集
			InterFaceProcessor.log(sql);
			rs = st.executeQuery(sql);
			// /3.3.进行数据整理获取数据容器
			List tmpList = new ArrayList();
			Map jksmx = null;
			while (rs.next()) {
				jksmx = new HashMap();
				for (int i = 0; i < ApplicationConstant.JKS_YPYS_MX_KEYS.length; i++) {
					jksmx
							.put(
									ApplicationConstant.JKS_YPYS_MX_KEYS[i],
									rs
											.getString(ApplicationConstant.JKS_YPYS_MX_KEYS[i]));
				}
				InterFaceProcessor.log("第一轮查询的一票一税明细==" + jksmx);
				tmpList.add(jksmx);
			}
			rs.close();
			// /3.4.调用子方法进行分票
			rtnList = InterFaceProcessor.getYpdsJks(tmpList);
			//
			yhsbResult.setRnObjectClassName("java.util.List");
			yhsbResult.setRnObject(rtnList);
			// /3.2.更新库中申报数据
			Map tmpMap = null;
			for (int i = 0; i < rtnList.size(); i++) {
				String sphm = getSphm(jsjdm);
				jks = (YPDSJKS) rtnList.get(i);
				jks.setSphm(sphm);
				sb = new StringBuffer();
				sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
				sb.append(StringUtils.getSQLStr(sphm));
				sb.append(",zwbs=SUBSTR(zwbs, 1, 19)||");
				sb.append(StringUtils
						.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
				sb.append(",yhdm=");
				sb.append(StringUtils.getSQLStr(yhsbInfo.getYhdm()));
				/** *modified by liutg 20051230** */
				sb.append(",yhmc=(select yhmc from dmdb.gy_dm_yh where yhdm = "
						+ StringUtils.getSQLStr(yhsbInfo.getYhdm()) + ")");
				// sb.append(StringUtils.getSQLStr(yhsbInfo.getYhmc()));
				sb.append(",zh=");
				sb.append(StringUtils.getSQLStr(yhsbInfo.getZh()));
				sb.append(" WHERE jkpzh IN (");
				for (int j = 0; j < jks.getYpysJksMx().size(); j++) {
					tmpMap = (Map) jks.getYpysJksMx().get(j);
					if (j != 0) {
						sb.append(",");
					}
					sb.append(StringUtils.getSQLStr((String) tmpMap
							.get("jkpzh")));
				}
				sb.append(")");
				sb.append(" AND jsjdm=");
				sb.append(StringUtils.getSQLStr(jsjdm));
				sb.append(" AND sbbh=");
				sb.append(StringUtils.getSQLStr(sbbh));
				sb.append(" AND zwbs LIKE '0%0'");
				sql = sb.toString();
				log(sql);
				st.addBatch(sql);
			}
			st.executeBatch();
			// /3.99.
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

	private static int getSzNumbyDeclareInfo(List declareInfos) {
		DeclareInfor declareInfo = null;
		java.util.Set szSet = new java.util.HashSet();
		for (int i = 0; i < declareInfos.size(); i++) {
			declareInfo = (DeclareInfor) declareInfos.get(i);
			String szdm = declareInfo.getSbjkzb().getSzdm();
			szSet.add(szdm);
		}

		return szSet.size();
	}

	private static double getSzje(List declareInfos, String szdm)
			throws Exception {
		// 0.句柄申明
		double hjzje = 0.00;
		DeclareInfor declareInfo = null;
		// 1.业务过程
		for (int i = 0; i < declareInfos.size(); i++) {
			declareInfo = (DeclareInfor) declareInfos.get(i);
			if (szdm.equals(declareInfo.getSbjkzb().getSzdm())) {
				hjzje += declareInfo.getSbjkzb().getSjje().doubleValue();
			}
		}
		// 2.合法性检查
		if (hjzje == 0.00) {
			throw new Exception("根据税种获取同一笔申报内的税种总金额错误!" + szdm);
		}
		// 99.返回值
		return hjzje;

	}

        private static double getHjSzje(List declareInfos)
                        throws Exception {
                // 0.句柄申明
                double hjzje = 0.00;
                DeclareInfor declareInfo = null;
                // 1.业务过程
                for (int i = 0; i < declareInfos.size(); i++) {
                        declareInfo = (DeclareInfor) declareInfos.get(i);
                                hjzje += declareInfo.getSbjkzb().getSjje().doubleValue();
                }
                // 2.合法性检查
                if (hjzje == 0.00) {
                        throw new Exception("获取同一笔申报内的税种总金额错误!");
                }
                // 99.返回值
                return hjzje;

        }

	/**
	 * 有申报和无申报银行缴款扣款结果 判断是否有无申报通过具体的申报数据的数据来源进行判定，数据来源为91的是无申报缴款，
	 * 其他情况为有申报缴款，对于成功或者失败，都需要限定申报缴款数据的账务标志为缴款状态锁定
	 * 才可以进入最后一步的状态修改。如果是失败的情况，对于有申报的数据需要回滚SPHM和ZWBS字段
	 *
	 * @param kkFlag
	 *            扣款成功标记，true-扣款成功，false-扣款失败
	 * @param ypdsJkss
	 *            一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
	 * @return 返回值处理YhsbResult对象
	 * @throws java.lang.Exception
	 *             应用异常
	 */
	public static YhsbResult WsbYhJkKkResult(boolean kkFlag, List ypdsJkss)
			throws Exception {
		YhsbResult yhsbResult = new YhsbResult();
		// 0.句柄申明
		DBResource db = null;
		Statement st = null;
		Connection conn = null;
		ResultSet rs = null;
		YPDSJKS jks = null;
		StringBuffer sb = null;
		String sql = null;
		String jsjdm = null;
		// String _swjgzzjgdm = null;
		String sbbh = null;
		String sphm = null;
		String jksj = null;
		Map tmpMap = null;
		List tmpList = null;
		// 1.入口参数检查
		if (ypdsJkss == null || ypdsJkss.size() == 0) {
			throw new Exception("无申报银行缴款扣款结果接口调用入口参数非法.");
		}
		// 2.初始化
		// 3.业务方法
		try {
			// /3.-2.获取资源
			conn = db.getConnection();
			st = conn.createStatement();
			// /3.-1.判定该笔数据是有申报还是无申报
			// //3.-1.0.执行查询获取结果
			sb = new StringBuffer();
			jsjdm = ((YPDSJKS) ypdsJkss.get(0)).getJsjdm();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sphm IN (");
			for (int i = 0; i < ypdsJkss.size(); i++) {
				jks = (YPDSJKS) ypdsJkss.get(i);
				if (i != 0) {
					sb.append(",");
				}
				sb.append(StringUtils.getSQLStr(jks.getSphm()));
			}
			sb.append(")");
			sb.append(" AND jsjdm=");
			sb.append(StringUtils.getSQLStr(jsjdm));
			sql = sb.toString();
			log(sql);
			rs = st.executeQuery(sql);
			// //3.-1.1.值对象转换
			Sbjkzb sbjkzb = null;
			List zbList = new ArrayList();
			while (rs.next()) {
				sbjkzb = new Sbjkzb();
				sbjkzb.setJkpzh(rs.getString("jkpzh"));
				sbjkzb.setSklxdm(rs.getString("sklxdm"));
				sbjkzb.setJsjdm(rs.getString("jsjdm"));
				sbjkzb.setFsdm(rs.getString("fsdm"));
				sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
				sbjkzb.setYhdm(rs.getString("yhdm"));
				sbjkzb.setYhmc(rs.getString("yhmc"));
				sbjkzb.setZh(rs.getString("zh"));
				sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
				sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
				sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
				sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
				sbjkzb.setYskmdm(rs.getString("yskmdm"));
				sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
				sbjkzb.setSzdm(rs.getString("szdm"));
				sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
				sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
				sbjkzb.setJksj(rs.getTimestamp("jksj"));
				sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
				sbjkzb.setClbjdm(rs.getString("clbjdm"));
				sbjkzb.setSjje(rs.getBigDecimal("sjje"));
				sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
				sbjkzb.setRkje(rs.getBigDecimal("rkje"));
				sbjkzb.setZwbs(rs.getString("zwbs"));
				sbjkzb.setHxrdm(rs.getString("hxrdm"));
				sbjkzb.setHxrmc(rs.getString("hxrmc"));
				sbjkzb.setLrr(rs.getString("lrr"));
				sbjkzb.setBz(rs.getString("bz"));
				sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
				sbjkzb.setSbbh(rs.getString("sbbh"));
				sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
				sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkzb.setSjly(rs.getString("sjly"));
				sbjkzb.setNd(rs.getString("nd"));
				sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
				sbjkzb.setQxdm(rs.getString("qxdm"));
				sbjkzb.setSphm(rs.getString("sphm"));
				zbList.add(sbjkzb);
			}
			rs.close();
			// //3.-1.2.数据检查
			if (zbList.size() == 0) {
				throw new Exception("无法查找到税票对应的数据！sphm sql=" + sql);
			}
			//
			sbjkzb = (Sbjkzb) zbList.get(0);
			yhsbResult.setJsjdm(sbjkzb.getJsjdm());
			yhsbResult.setQxdm(sbjkzb.getQxdm());
			yhsbResult.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
			//
			String zwbsPostFix = "0";
			String sjly = null;
			List sjlyList = new ArrayList();
			for (int i = 0; i < zbList.size(); i++) {
				sbjkzb = (Sbjkzb) zbList.get(i);
				zwbsPostFix = sbjkzb.getZwbs().substring(19, 20);
				// 判断账务标志是否为扣款锁定
				if (!ApplicationConstant.ZWBS_YHKK_LOCK.equals(zwbsPostFix)) {
					throw new Exception("税票对应的数据扣款状态错误！sphm="
							+ sbjkzb.getSphm());
				}
				// 收集数据来源
				if (!sjlyList.contains(sbjkzb.getSjly())) {
					sjlyList.add(sbjkzb.getSjly());
				}
			}
			// //3.-1.2.数据来源判定
			int ly = -1; // 0--无申报，1--有申报
			if (sjlyList.size() != 1) {
				throw new Exception("税票对应的数据来源不统一，系非法数据！sphm="
						+ sbjkzb.getSphm());
			} else {
				if (ApplicationConstant.SJLY_YHSB_YHLR
						.equals(((String) sjlyList.get(0)))) {
					ly = 0;
					System.out
							.println("==========================税库银接口实时业务,税票数据为无申报银行缴款生成==========================");
				} else {
					ly = 1;
					System.out
							.println("==========================税库银接口实时业务,税票数据为有申报银行缴款生成==========================");
				}
			}
			// /3.1.处理不同情况的扣款返回结果
			if (ly == 0) { // 无申报情况
				for (int i = 0; i < ypdsJkss.size(); i++) {
					// //3.1.0.获取值对象
					jks = (YPDSJKS) ypdsJkss.get(i);
					jsjdm = jks.getJsjdm();
					sphm = jks.getSphm();
					sbbh = jks.getSbbh();
					jksj = jks.getSbrq();
					// //3.1.1.处理扣款成功
					if (kkFlag) { // 扣款成功，按照计算机代码、税务机关组织机构代码、申报编号、税票号更新记录的账务标志和入库时间,并根据税种更新入库金额
						sb = new StringBuffer();
						sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
						sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
						sb
								.append(StringUtils
										.getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
						sb.append(",lrrq=sysdate");
						sb.append(",jksj=");
						sb.append(StringUtils.getSQLDate(jksj));
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sql = sb.toString();
						log(sql);
						st.executeUpdate(sql);
						System.out
								.println("==========================税库银接口实时业务成功，更新税票账务数据==========================");
					}
					// //3.1.2.处理扣款失败
					else { // 扣款失败,按照计算机代码、税务机关组织机构代码、申报编号、税票号删除所有账务标志允许删除数据
						// ///3.1.2.0.删除明细数据
						sb = new StringBuffer();
						sb.append("DELETE");
						sb.append(" FROM SBDB.SB_JL_SBJKMX b");
						sb.append(" WHERE b.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND b.jkpzh IN (");
						sb
								.append("SELECT JKPZH FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sb.append(")");
						sql = sb.toString();
						log(sql);
						int rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("税票对应的申报明细数据无法找到，系非法数据！sphm="
									+ sphm);
						}
						// ///3.1.2.0.删除主数据
						sb = new StringBuffer();
						sb
								.append("DELETE FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sql = sb.toString();
						log(sql);
						rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("税票对应的申报主数据数据无法找到，系非法数据！sphm="
									+ sphm);
						}
						System.out
								.println("==========================税库银接口实时业务失败，删除税票数据==========================");
					}
				}
			} else if (ly == 1) { // 有申报情况
				for (int i = 0; i < ypdsJkss.size(); i++) {
					// //3.1.0.获取值对象
					jks = (YPDSJKS) ypdsJkss.get(i);
					jsjdm = jks.getJsjdm();
					sphm = jks.getSphm();
					sbbh = jks.getSbbh();
					jksj = jks.getSbrq();
					// //3.1.1.处理扣款成功
					if (kkFlag) { // 扣款成功，按照计算机代码、税务机关组织机构代码、申报编号、税票号更新记录的账务标志和入库时间,并根据税种更新入库金额
						sb = new StringBuffer();
						sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
						sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
						sb
								.append(StringUtils
										.getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
						sb.append(",lrrq=sysdate");
						sb.append(",jksj=");
						sb.append(StringUtils.getSQLDate(jksj));
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sql = sb.toString();
						log(sql);
						st.executeUpdate(sql);
						System.out
								.println("==========================税库银接口实时业务成功，更新税票账务数据==========================");
					}
					// //3.1.2.处理扣款失败
					else { // 扣款失败,按照计算机代码、税务机关组织机构代码、申报编号、税票号回滚所有账务标志及恢复税票号码的值
						// ///3.1.2.0.回滚主数据
						sb = new StringBuffer();
						sb
								.append("UPDATE SBDB.SB_JL_SBJKZB a SET zwbs=SUBSTR(zwbs,1,19)||'0',sphm=jkpzh");
						sb.append(" WHERE a.jsjdm=");
						sb.append(StringUtils.getSQLStr(jsjdm));
						sb.append(" AND a.sphm=");
						sb.append(StringUtils.getSQLStr(sphm));
						sb.append(" AND SUBSTR(a.zwbs,20,1)=");
						sb.append(StringUtils
								.getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
						sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
						sql = sb.toString();
						log(sql);
						int rc = st.executeUpdate(sql);
						if (!(rc > 0)) {
							throw new Exception("税票对应的申报主数据数据无法找到，系非法数据！sphm="
									+ sphm);
						}
						System.out
								.println("==========================税库银接口实时业务失败，删除税票数据==========================");
					}
				}
			} else {
				throw new Exception("税票对应的数据非法！");
			}
			// /3.99.释放资源
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			db.destroyConnection(conn);
		}
		// 98.
		yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
				+ SkhConstant.RESULT_CODE_MID_DEFAULT
				+ SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
		yhsbResult
				.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
		// 99.
		return yhsbResult;
	}

        /**
         * 有申报和无申报银行缴款扣款结果 判断是否有无申报通过具体的申报数据的数据来源进行判定，数据来源为91的是无申报缴款，
         * 其他情况为有申报缴款，对于成功或者失败，都需要限定申报缴款数据的账务标志为缴款状态锁定
         * 才可以进入最后一步的状态修改。如果是失败的情况，对于有申报的数据需要回滚SPHM和ZWBS字段
         *
         * @param kkFlag
         *            扣款成功标记，true-扣款成功，false-扣款失败
         * @param ypdsJkss
         *            一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
         * @return 返回值处理YhsbResult对象
         * @throws java.lang.Exception
         *             应用异常
         */
        public static YhsbResult yhJkKkResult(boolean kkFlag, List ypdsJkss)
                        throws Exception {
                YhsbResult yhsbResult = new YhsbResult();
                // 0.句柄申明
                DBResource db = null;
                Statement st = null;
                Connection conn = null;
                ResultSet rs = null;
                YPDSJKS jks = null;
                StringBuffer sb = null;
                String sql = null;
                String jsjdm = null;
                // String _swjgzzjgdm = null;
                String sbbh = null;
                String sphm = null;
                String jksj = null;
                Map tmpMap = null;
                List tmpList = null;
                // 1.入口参数检查
                if (ypdsJkss == null || ypdsJkss.size() == 0) {
                        throw new Exception("无申报银行缴款扣款结果接口调用入口参数非法.");
                }
                // 2.初始化
                // 3.业务方法
                try {
                        // /3.-2.获取资源
                        conn = db.getConnection();
                        st = conn.createStatement();
                        // /3.-1.判定该笔数据是有申报还是无申报
                        // //3.-1.0.执行查询获取结果
                        sb = new StringBuffer();
                        jsjdm = ((YPDSJKS) ypdsJkss.get(0)).getJsjdm();
                        sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sphm IN (");
                        for (int i = 0; i < ypdsJkss.size(); i++) {
                                jks = (YPDSJKS) ypdsJkss.get(i);
                                if (i != 0) {
                                        sb.append(",");
                                }
                                sb.append(StringUtils.getSQLStr(jks.getSphm()));
                        }
                        sb.append(")");
                        sb.append(" AND jsjdm=");
                        sb.append(StringUtils.getSQLStr(jsjdm));
                        sql = sb.toString();
                        log(sql);
                        rs = st.executeQuery(sql);
                        // //3.-1.1.值对象转换
                        Sbjkzb sbjkzb = null;
                        List zbList = new ArrayList();
                        while (rs.next()) {
                                sbjkzb = new Sbjkzb();
                                sbjkzb.setJkpzh(rs.getString("jkpzh"));
                                sbjkzb.setSklxdm(rs.getString("sklxdm"));
                                sbjkzb.setJsjdm(rs.getString("jsjdm"));
                                sbjkzb.setFsdm(rs.getString("fsdm"));
                                sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
                                sbjkzb.setYhdm(rs.getString("yhdm"));
                                sbjkzb.setYhmc(rs.getString("yhmc"));
                                sbjkzb.setZh(rs.getString("zh"));
                                sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
                                sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
                                sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
                                sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
                                sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
                                sbjkzb.setYskmdm(rs.getString("yskmdm"));
                                sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
                                sbjkzb.setSzdm(rs.getString("szdm"));
                                sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
                                sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
                                sbjkzb.setJksj(rs.getTimestamp("jksj"));
                                sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
                                sbjkzb.setClbjdm(rs.getString("clbjdm"));
                                sbjkzb.setSjje(rs.getBigDecimal("sjje"));
                                sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
                                sbjkzb.setRkje(rs.getBigDecimal("rkje"));
                                sbjkzb.setZwbs(rs.getString("zwbs"));
                                sbjkzb.setHxrdm(rs.getString("hxrdm"));
                                sbjkzb.setHxrmc(rs.getString("hxrmc"));
                                sbjkzb.setLrr(rs.getString("lrr"));
                                sbjkzb.setBz(rs.getString("bz"));
                                sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
                                sbjkzb.setSbbh(rs.getString("sbbh"));
                                sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
                                sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
                                sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
                                sbjkzb.setSjly(rs.getString("sjly"));
                                sbjkzb.setNd(rs.getString("nd"));
                                sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
                                sbjkzb.setQxdm(rs.getString("qxdm"));
                                sbjkzb.setSphm(rs.getString("sphm"));
                                zbList.add(sbjkzb);
                        }
                        rs.close();
                        // //3.-1.2.数据检查
                        if (zbList.size() == 0) {

                            yhsbResult.setResultCode("99999");
                            yhsbResult
                                            .setResultDescription("更新失败");
                                    return yhsbResult;
                        }
                        //
                        sbjkzb = (Sbjkzb) zbList.get(0);
                        yhsbResult.setJsjdm(sbjkzb.getJsjdm());
                        yhsbResult.setQxdm(sbjkzb.getQxdm());
                        yhsbResult.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
                        //
                        String zwbsPostFix = "0";
                        String sjly = null;
                        List sjlyList = new ArrayList();
                        for (int i = 0; i < zbList.size(); i++) {
                                sbjkzb = (Sbjkzb) zbList.get(i);
                                zwbsPostFix = sbjkzb.getZwbs().substring(19, 20);
                                // 判断账务标志是否为扣款锁定
                                if (!ApplicationConstant.ZWBS_YHKK_LOCK.equals(zwbsPostFix)) {
                                        throw new Exception("税票对应的数据扣款状态错误！sphm="
                                                        + sbjkzb.getSphm());
                                }
                                // 收集数据来源
                                if (!sjlyList.contains(sbjkzb.getSjly())) {
                                        sjlyList.add(sbjkzb.getSjly());
                                }
                        }
                        // //3.-1.2.数据来源判定
                        int ly = -1; // 0--无申报，1--有申报
                        if (sjlyList.size() != 1) {
                                throw new Exception("税票对应的数据来源不统一，系非法数据！sphm="
                                                + sbjkzb.getSphm());
                        } else {
                                if (ApplicationConstant.SJLY_YHSB_YHLR
                                                .equals(((String) sjlyList.get(0)))) {
                                        ly = 0;
                                        System.out
                                                        .println("==========================税库银接口实时业务,税票数据为无申报银行缴款生成==========================");
                                } else {
                                        ly = 1;
                                        System.out
                                                        .println("==========================税库银接口实时业务,税票数据为有申报银行缴款生成==========================");
                                }
                        }
                        // /3.1.处理不同情况的扣款返回结果
                        if (ly == 0) { // 无申报情况
                                for (int i = 0; i < ypdsJkss.size(); i++) {
                                        // //3.1.0.获取值对象
                                        jks = (YPDSJKS) ypdsJkss.get(i);
                                        jsjdm = jks.getJsjdm();
                                        sphm = jks.getSphm();
                                        sbbh = jks.getSbbh();
                                        jksj = jks.getSbrq();
                                        // //3.1.1.处理扣款成功
                                        if (kkFlag) { // 扣款成功，按照计算机代码、税务机关组织机构代码、申报编号、税票号更新记录的账务标志和入库时间,并根据税种更新入库金额
                                                sb = new StringBuffer();
                                                sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
                                                sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
                                                sb
                                                                .append(StringUtils
                                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
                                                sb.append(",lrrq=sysdate");
                                                sb.append(",jksj=");
                                                sb.append(StringUtils.getSQLDate(jksj));
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sql = sb.toString();
                                                log(sql);
                                                st.executeUpdate(sql);
                                                System.out
                                                                .println("==========================税库银接口实时业务成功，更新税票账务数据==========================");
                                        }
                                        // //3.1.2.处理扣款失败
                                        else { // 扣款失败,按照计算机代码、税务机关组织机构代码、申报编号、税票号删除所有账务标志允许删除数据
                                                // ///3.1.2.0.删除明细数据
                                                sb = new StringBuffer();
                                                sb.append("DELETE");
                                                sb.append(" FROM SBDB.SB_JL_SBJKMX b");
                                                sb.append(" WHERE b.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND b.jkpzh IN (");
                                                sb
                                                                .append("SELECT JKPZH FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sb.append(")");
                                                sql = sb.toString();
                                                log(sql);
                                                int rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("税票对应的申报明细数据无法找到，系非法数据！sphm="
                                                                        + sphm);
                                                }
                                                // ///3.1.2.0.删除主数据
                                                sb = new StringBuffer();
                                                sb
                                                                .append("DELETE FROM SBDB.SB_JL_SBJKZB a WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sql = sb.toString();
                                                log(sql);
                                                rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("税票对应的申报主数据数据无法找到，系非法数据！sphm="
                                                                        + sphm);
                                                }
                                                System.out
                                                                .println("==========================税库银接口实时业务失败，删除税票数据==========================");
                                        }
                                }
                        } else if (ly == 1) { // 有申报情况
                                for (int i = 0; i < ypdsJkss.size(); i++) {
                                        // //3.1.0.获取值对象
                                        jks = (YPDSJKS) ypdsJkss.get(i);
                                        jsjdm = jks.getJsjdm();
                                        sphm = jks.getSphm();
                                        sbbh = jks.getSbbh();
                                        jksj = jks.getSbrq();
                                        // //3.1.1.处理扣款成功
                                        if (kkFlag) { // 扣款成功，按照计算机代码、税务机关组织机构代码、申报编号、税票号更新记录的账务标志和入库时间,并根据税种更新入库金额
                                                sb = new StringBuffer();
                                                sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
                                                sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
                                                sb
                                                                .append(StringUtils
                                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));
                                                sb.append(",lrrq=sysdate");
                                                sb.append(",jksj=");
                                                sb.append(StringUtils.getSQLDate(jksj));
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sql = sb.toString();
                                                log(sql);
                                                st.executeUpdate(sql);
                                                System.out
                                                                .println("==========================税库银接口实时业务成功，更新税票账务数据==========================");
                                        }
                                        // //3.1.2.处理扣款失败
                                        else { // 扣款失败,按照计算机代码、税务机关组织机构代码、申报编号、税票号回滚所有账务标志及恢复税票号码的值
                                                // ///3.1.2.0.回滚主数据
                                                sb = new StringBuffer();
                                                sb
                                                                .append("UPDATE SBDB.SB_JL_SBJKZB a SET zwbs=SUBSTR(zwbs,1,19)||'0',sphm=jkpzh");
                                                sb.append(" WHERE a.jsjdm=");
                                                sb.append(StringUtils.getSQLStr(jsjdm));
                                                sb.append(" AND a.sphm=");
                                                sb.append(StringUtils.getSQLStr(sphm));
                                                sb.append(" AND SUBSTR(a.zwbs,20,1)=");
                                                sb.append(StringUtils
                                                                .getSQLStr(ApplicationConstant.ZWBS_YHKK_LOCK));
                                                sb.append(" AND SUBSTR(a.zwbs,0,1)='0'");
                                                sql = sb.toString();
                                                log(sql);
                                                int rc = st.executeUpdate(sql);
                                                if (!(rc > 0)) {
                                                        throw new Exception("税票对应的申报主数据数据无法找到，系非法数据！sphm="
                                                                        + sphm);
                                                }
                                                System.out
                                                                .println("==========================税库银接口实时业务失败，删除税票数据==========================");
                                        }
                                }
                        } else {
                                throw new Exception("税票对应的数据非法！");
                        }
                        // /3.99.释放资源
                        st.close();
                } catch (Exception e) {
                        e.printStackTrace();
                        throw ExceptionUtil.getBaseException(e);
                } finally {
                        db.destroyConnection(conn);
                }
                // 98.
                yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
                                + SkhConstant.RESULT_CODE_MID_DEFAULT
                                + SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
                yhsbResult
                                .setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
                // 99.
                return yhsbResult;
        }


	/**
	 * 根据申报编号查询未入库数据
	 *
	 * @param sbbh
	 *            缴款凭证号
	 * @param conn
	 *            数据库连接
	 * @return ArrayList 返回数据列表 成员变量为DeclareInfor
	 * @throws java.lang.Exception
	 */
	protected static List queryDataBySBBH(String jsjdm, String sbbh,
			Connection conn) throws Exception {
		// 0.句柄申明
		List rnList = null;
		StringBuffer sb = null;
		String sql = null;
		Statement stat = null;
		ResultSet rs = null;
		boolean exFlag = false;
		DeclareInfor declareInfor = null;
		Sbjkzb sbjkzb = null;
		Sbjkmx sbjkmx = null;
		ArrayList jksmxList = null;
		// 1.入口参数检查
		if (sbbh == null || NULL_ZERO.equals(sbbh)) {
			throw new Exception("入口参数非法(sbbh=" + sbbh + ")");
		} else if (conn == null) {
			throw new Exception("入口数据库连接为空！");
		}
		// 2.初始化
		rnList = new ArrayList();
		// 3.业务过程
		// /3.0.创建相关对象
		// /3.1.获取数据
		try {
			// //3.1.0.初始化数据库
			stat = conn.createStatement();
			// //3.1.1.创建查询申报缴款主表的SQL
			sb = new StringBuffer();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
			sb.append(sbbh);
			sb.append("'");
			sb.append(" and jsjdm = '");
			sb.append(jsjdm);
			sb.append("'");
			/** **************modfiy by hezy 同一银行可以再次处理***************** */
			// sb.append("' AND zwbs like '0%0'");
			sql = sb.toString();
			// //3.1.1.执行查询
			log(sql);
			rs = stat.executeQuery(sql);
			// //3.1.2.整理申报缴款数据并加入数据集
			while (rs.next()) {
				declareInfor = new DeclareInfor();
				sbjkzb = new Sbjkzb();
				jksmxList = new ArrayList();
				declareInfor.setSbjkzb(sbjkzb);
				declareInfor.setSbjkmxInfo(jksmxList);
				// 值对象转换
				sbjkzb.setJkpzh(rs.getString("jkpzh"));
				sbjkzb.setSklxdm(rs.getString("sklxdm"));
				sbjkzb.setJsjdm(rs.getString("jsjdm"));
				sbjkzb.setFsdm(rs.getString("fsdm"));
				sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
				sbjkzb.setYhdm(rs.getString("yhdm"));
				sbjkzb.setYhmc(rs.getString("yhmc"));
				sbjkzb.setZh(rs.getString("zh"));
				sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
				sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
				sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
				sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
				sbjkzb.setYskmdm(rs.getString("yskmdm"));
				sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
				sbjkzb.setSzdm(rs.getString("szdm"));
				sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
				sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
				sbjkzb.setJksj(rs.getTimestamp("jksj"));
				sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
				sbjkzb.setClbjdm(rs.getString("clbjdm"));
				sbjkzb.setSjje(rs.getBigDecimal("sjje"));
				sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
				sbjkzb.setRkje(rs.getBigDecimal("rkje"));
				sbjkzb.setZwbs(rs.getString("zwbs"));
				sbjkzb.setHxrdm(rs.getString("hxrdm"));
				sbjkzb.setHxrmc(rs.getString("hxrmc"));
				sbjkzb.setLrr(rs.getString("lrr"));
				sbjkzb.setBz(rs.getString("bz"));
				sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
				sbjkzb.setSbbh(rs.getString("sbbh"));
				sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
				sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkzb.setSjly(rs.getString("sjly"));
				sbjkzb.setNd(rs.getString("nd"));
				sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
				sbjkzb.setQxdm(rs.getString("qxdm"));
				sbjkzb.setSphm(rs.getString("sphm"));
				rnList.add(declareInfor);
			}
			rs.close();
			// //3.1.2.检查主数据集合的合法性
			if (rnList.size() == 0) {
				throw new Exception("(sbbh=" + sbbh + ")无此申报数据");
			}
			// //3.1.2.创建查询申报缴款主表的SQL
			sb = new StringBuffer();
			sb.append("SELECT * FROM SBDB.SB_JL_SBJKMX b WHERE b.jkpzh in (");
			sb.append("SELECT a.jkpzh FROM SBDB.SB_JL_SBJKZB a WHERE a.sbbh=");
			sb.append(StringUtils.getSQLStr(sbbh));
			sb.append(" and jsjdm = '");
			sb.append(jsjdm);
			sb.append("'");
			/** **************modfiy by hezy 同一银行可以再次处理***************** */
			// sb.append(" AND a.zwbs like '0%0'");
			sb.append(")");
			sql = sb.toString();
			log(sql);
			// //3.1.1.执行查询
			rs = stat.executeQuery(sql);
			boolean mxFlag = false;
			while (rs.next()) {
				// ///3.1.1.0.生成新的值对象
				sbjkmx = new Sbjkmx();
				mxFlag = false;
				// ///3.1.1.1.数据转换
				sbjkmx.setSzsmdm(rs.getString("szsmdm"));
				sbjkmx.setJkpzh(rs.getString("jkpzh"));
				sbjkmx.setJsjdm(rs.getString("jsjdm"));
				sbjkmx.setYskmdm(rs.getString("yskmdm"));
				sbjkmx.setYsjcdm(rs.getString("ysjcdm"));
				sbjkmx.setKssl(rs.getBigDecimal("kssl"));
				sbjkmx.setJsje(rs.getBigDecimal("jsje"));
				sbjkmx.setSjse(rs.getBigDecimal("sjse"));
				sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
				sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
				sbjkmx.setRkje(rs.getBigDecimal("rkje"));
				sbjkmx.setSbbh(rs.getString("sbbh"));
				sbjkmx.setSjfc(rs.getBigDecimal("sjfc"));
				sbjkmx.setQjfc(rs.getBigDecimal("qjfc"));
				sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				sbjkmx.setNd(rs.getString("nd"));
				sbjkmx.setSl(rs.getBigDecimal("sl"));
				sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
				sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
				sbjkmx.setQxdm(rs.getString("qxdm"));
				// ///3.1.1.2.判定属于哪一个数据集并加入该数据集,如果找不到则抛出异常
				for (int i = 0; i < rnList.size(); i++) {
					declareInfor = (DeclareInfor) rnList.get(i);
					if (sbjkmx.getJkpzh().equals(
							declareInfor.getSbjkzb().getJkpzh())
							&& sbjkmx.getJsjdm().equals(
									declareInfor.getSbjkzb().getJsjdm())) {
						mxFlag = true;
						declareInfor.getSbjkmxInfo().add(sbjkmx);
						break;
					}
				}
				if (!mxFlag) {
					throw new Exception("无法找到申报缴款明细[" + sbjkmx.getJkpzh() + "]");
				}
			}
			rs.close();
			// //3.1.2.对数据集合进行合法性检查
			if (rnList.size() == 0) {
				throw new Exception("无法找到申报缴款数据!sbbh=" + sbbh);
			}
			for (int i = 0; i < rnList.size(); i++) {
				declareInfor = (DeclareInfor) rnList.get(i);
				if (declareInfor.getSbjkmxInfo().size() == 0) {
					throw new Exception("无法找到对应的申报缴款明细["
							+ declareInfor.getSbjkzb().getJkpzh() + "]");
				}
			}
			// //3.1.99.关闭数据库连接
			stat.close();
		} catch (Exception ex) {
			log(ex.getMessage());
			throw ex;
		}
		// 99.返回值
		return rnList;
	}

	public static boolean deleteSbjkDataBySphm(String sphm) throws Exception {
		// 0. 句柄申明
		boolean rnFlag = true;
		PreparedStatement pStat = null;
		// 1.参数检查
		if (sphm == null) {
			throw new Exception("入口参数错误！sphm=" + sphm);
		}
		// 2.初始化
		// 3.业务过程
		try {
			/**
			 * @todo 删除税票号码定义的数据，并且在删除之前确认状态为已发送扣款
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 99.返回值
		return rnFlag;
	}
        public static YhsbResult yhkkBySbbh(String sbbh, String sphm, String jsjdm, String jksj) throws Exception {
            DBResource db = null;
            Statement st = null;
            Connection conn = null;
            conn = db.getConnection();
            st = conn.createStatement();

            StringBuffer sb = new StringBuffer();
            sb.append("UPDATE SBDB.SB_JL_SBJKZB a");
            sb.append(" SET a.zwbs=SUBSTR(zwbs, 1, 17)||");
            sb
                            .append(StringUtils

                                            .getSQLStr(ApplicationConstant.ZWBS_YHKK_SUCCESS));

            sb.append(",lrrq=sysdate");
            sb.append(",sphm=");
            sb.append(StringUtils.getSQLStr(sphm));
            sb.append(",jksj=");
            sb.append(StringUtils.getSQLDate(jksj));
            sb.append(" WHERE a.jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND a.sbbh=");
            sb.append(StringUtils.getSQLStr(sbbh));
            sb.append(" AND a.zwbs like '0%0'");
            String sql = sb.toString();
            System.out.println("YHSBSQL ==== : " + sb.toString());
            try {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                throw new Exception("银行端缴款更新税票信息失败.");
            }
            System.out
                            .println("==========================税库银接口实时业务成功，更新税票账务数据==========================");

                    YhsbResult yhsbResult = new YhsbResult();
                    // 98.
                    yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_SUCCESS
                                    + SkhConstant.RESULT_CODE_MID_DEFAULT
                                    + SkhConstant.RESULT_CODE_POSTFIX_SUCCESS);
                    yhsbResult
                                    .setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_SUCCESS);
                    // 99.
                    return yhsbResult;

        }

	public static void gtgshYhkkhz(Connection conn) throws Exception {
		// 0. 句柄申明
		boolean rnFlag = true;
		PreparedStatement pStat = null;
		// 1.参数检查
		if (conn == null) {
			throw new Exception("数据库连接错误！");
		}
		// 2.初始化
		// 3.业务过程
		try {
			/**
			 * @todo 批量处理个体工商户扣款回执
			 */
			/**
			 * BEGIN LOOP 1.根据税票号码查询SFDB.SF_JL_GTGSH_YHKKHZXX_LW的扣款数据，条件为已发送
			 * 2.根据扣款回执信息的扣款结果处理数据，成功则生成申报缴款数据
			 * 3.根据扣款回执信息的扣款结果处理数据，成功则标记为已扣款，失败则标记为扣款失败 END LOOP
			 * 4.本批次税票回执处理完毕以后，检查区县月度扣款数据是否全部处理完毕，如果全部处理完毕则标记状态表区县月度为已完成，否则标记为进行中
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 99.返回值
	}

	public static String SQL_GTGSH_YHKKHZXX_SELECT = "";

}
