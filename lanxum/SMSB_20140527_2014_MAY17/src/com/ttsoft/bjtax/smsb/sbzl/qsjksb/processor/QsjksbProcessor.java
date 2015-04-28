/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.db.v_kj_qsmx;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

import java.sql.*;
import java.io.*;
import oracle.jdbc.driver.*;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description: 实现欠税缴款申报功能：包括缴款书录入，维护。
 * </p>
 * 
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbProcessor implements Processor {

	public QsjksbProcessor() {
	}

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常
	 */

	public Object process(VOPackage vo)
			throws com.ttsoft.framework.exception.BaseException {
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			return doShow(vo);
		case CodeConstant.SMSB_QUERYACTION:
			return doQuery(vo);
		case CodeConstant.SMSB_SAVEACTION:
			return doSave(vo);

		default:
			return null;
		}

	}

	private Object doShow(VOPackage vo) throws BaseException {

		// 获取form对象
		QsjksbForm form = (QsjksbForm) vo.getData();

		// 定义数据库连接
		Connection conn = null;
		ResultSet rs = null;

		// 欠税申报金额调整权限 默认值0为自动配比生成滞纳金不可修改 1为无固定配比可修改滞纳金金额
		String xgqx = "0";
		try {
			// 获得数据库连接
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);

			String sql = "select * from dmdb.sf_jl_qsglkzpz t where t.dyz='"
					+ form.getYhdm() + "' and t.pzlx='03' and t.sfyx='1'";
			rs = da.querySQL(sql);
			while (rs.next()) {
				xgqx = "1";
			}
			form.setXgqx(xgqx);
			return form;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private Object doQuery(VOPackage vo) throws BaseException {

		// 获取form对象
		QsjksbForm form = (QsjksbForm) vo.getData();
		// 获得UserData
		UserData ud = vo.getUserData();
		String yhid = ud.yhid;
		String ssdwdm = ud.ssdwdm;
		// 处理业务标志 1 申报 2 退税 3 查询 9全部， 如果为空，默认1
		String clywbz = "1";
		// 滞纳金计算日期
		String znjjsrq = "";
		// 欠税金额合计
		double qshj = 0;
		ResultSet rs = null;
		// 定义数据库连接
		Connection conn = null;
		if (form.getXgqx() == null) {

			// 欠税申报金额调整权限 默认值0为自动配比生成滞纳金不可修改 1为无固定配比可修改滞纳金金额
			String xgqx = "0";
			try {
				// 获得数据库连接
				conn = SfDBResource.getConnection();
				SfDBAccess da = new SfDBAccess(conn);

				String sql = "select * from dmdb.sf_jl_qsglkzpz t where t.dyz='"
						+ form.getYhdm() + "' and t.pzlx='03' and t.sfyx='1'";
				rs = da.querySQL(sql);
				while (rs.next()) {
					System.out.println("RS1    next");
					xgqx = "1";
				}
				form.setXgqx(xgqx);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			} finally {
				SfDBResource.freeConnection(conn);
			}
		}
		// 调取计会接口获取欠税数据
		ArrayList datalist = new ArrayList();
		Map map = this.getSzmc();
		Timestamp now = new Timestamp((new java.util.Date()).getTime());

		String qsycz = "";
		// 滞纳金计算日期
		String znjycz = "";

		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			String sql2 = "select * from dmdb.sf_jl_qsglkzpz t where t.pzlx='11' and t.sfyx='1' ";
			rs = da.querySQL(sql2);
			while (rs.next()) {
				znjjsrq = rs.getString("DYZ").replaceAll("-", "");
			}

			/*
			 * 参数： 输入值：jsjdm： 计算机代码 是纳税人的计算机代码 author: 操作人员代码 ssjg: 操作人员所在机关
			 * clywbz: 处理业务标志，1 申报 2 退税 3 查询 9全部， 如果为空，默认1 tsszdm: 退税税种代码
			 * 输出值：qsye游标： 欠税余额游标
			 */
			String querysql = "BEGIN jkdb.sb_pkg_tools.getnsrqianshuishuju(?,?,?,?,?,?); END;";
			// String querysql= "call
			// jkdb.sb_pkg_tools.getnsrqianshuishuju(?,?,?,?,?.?)";

			CallableStatement cs = conn.prepareCall(querysql); // 调用存储过程

			cs.setString(1, form.getJsjdm());
			cs.setString(2, yhid);
			cs.setString(3, ssdwdm);
			cs.setString(4, clywbz);
			cs.setString(5, "");
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();

			ResultSet rs1 = (ResultSet) cs.getObject(6);

			// print the results
			String dqxtsj = this.dateFormat(new Date());
			while (rs1.next()) {
				HashMap tmp = new HashMap();
				// 计算滞纳金天数
				String skxjrq = this.dateFormat(rs1.getDate("skxjrq"));
				// 当前系统时间，如果当前系统时间大于限缴日期，则不计算滞纳金

				if (Integer.parseInt(dqxtsj) <= Integer.parseInt(skxjrq)) {
					tmp.put("znjts", "0");
				} else if (znjjsrq == ""
						|| znjjsrq == null
						|| Integer.parseInt(skxjrq) >= Integer
								.parseInt(znjjsrq)) {
					// 计算滞纳金天数，从限缴日期的下一天开始计算
					tmp.put("znjts", String.valueOf(this.getZnjts(getDate(rs1
							.getDate("skxjrq"), 0))));
				} else {
					tmp.put("znjts", String.valueOf(this.getZnjts(new Date(
							Integer.parseInt(znjjsrq.substring(0, 4)) - 1900,
							Integer.parseInt(znjjsrq.substring(4, 6)) - 1,
							Integer.parseInt(znjjsrq.substring(6, 8)))) + 1));
				}
				tmp.put("szmc", map
						.get(rs1.getString("szsmdm").substring(0, 2)));
				tmp.put("szsmmc", rs1.getString("szsmmc"));
				// 欠税形成类型
				tmp.put("qsxclxdm", rs1.getString("qsxclxdm"));
				tmp.put("qsxclxmc", rs1.getString("qsxclxmc"));
				tmp.put("skssqsrq", this.dateFormat(rs1.getDate("skssqsrq")));
				tmp.put("skssjzrq", this.dateFormat(rs1.getDate("skssjzrq")));
				tmp.put("yskxjrq", this.dateFormat(rs1.getDate("skxjrq")));
				tmp.put("qjje", this.round(rs1.getDouble("qjje")));
				qshj = qshj + rs1.getDouble("qjje");
				// 最后一位为活滞纳金标识，活滞纳金为1，其它为0
				qsycz = String.valueOf(rs1.getString("qsxzdm") + ":"
						+ this.dateFormat(rs1.getDate("skxjrq")) + ":"
						+ rs1.getString("xh"))
						+ ":"
						+ this.round(rs1.getFloat("qjje"))
						+ ":"
						+ rs1.getString("szsmdm").substring(0, 2)
						+ ":"
						+ rs1.getString("szsmdm")
						+ ":"
						+ this.dateFormat(rs1.getDate("skssqsrq"))
						+ ":"
						+ this.dateFormat(rs1.getDate("skssjzrq"))
						+ ":"
						+ ""
						+ ":" + rs1.getString("yspzh") + ":" + "0";

				znjycz = String.valueOf(rs1.getString("qsxzdm") + ":"
						+ this.dateFormat(rs1.getDate("skxjrq")) + ":"
						+ rs1.getString("xh"))
						+ ":"
						+ this.round(rs1.getFloat("qjje"))
						+ ":"
						+ rs1.getString("szsmdm").substring(0, 2)
						+ ":"
						+ rs1.getString("znjszsmdm")
						+ ":"
						+ this.getXskssrq(rs1.getDate("skxjrq"), 1)
						+ ":"
						+ this.dateFormat(now)
						+ ":"
						+ this.getZnjts(rs1.getDate("skxjrq"))
						+ ":"
						+ rs1.getString("yspzh") + ":" + "1";
				tmp.put("qsycz", qsycz);
				tmp.put("znjycz", znjycz);
				tmp.put("szsmdm", rs1.getString("szsmdm"));
				tmp.put("znjsl", rs1.getString("znjsl"));
				// 欠税类型
				tmp.put("qslxdm", rs1.getString("qslxdm"));
				datalist.add(tmp);
			}

			rs1.close();
			cs.close();

			form.setDataList(datalist);
			form.setQshj(this.round(qshj));
			rs.close();
			return form;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
	}

	/**
	 * doSave 保存录入数据
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		// ormapping对象
		Sbjkzb orObj = new Sbjkzb();
		// 获取form对象
		QsjksbForm form = (QsjksbForm) vo.getData();

		Connection conn = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// 当前日期
		String dqrq = formatter.format(new Date());

		// 获得UserData
		UserData ud = vo.getUserData();
		// 定义与form中的columns 一样的变量。其中的名称为名细的字段名
		String names[] = { "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm",
				"sklxmc", "sbrq" };
		// 得到录入的明细数据
		String[] columns = { "xclx", "xjrq", "qsxh", "qsje", "szdm", "szsmdm",
				"skssksrq", "skssjsrq", "kssl", "yspzh", "hznjbz", "jsje",
				"sjse" };
		try {

			BeanUtil.copyBeanToBean(names, form, orObj);
			
			System.out.println("================增加自然人登记处理=================");
			SWDJJBSJ jbsj = null;
			ZRRJBSJ zrrJbsj = null;
			
			/*// 通过登记接口取得纳税人相关信息
			SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);*/
			
			if (TinyTools.isCompany(form.getJsjdm())) {
				// 通过登记接口取得纳税人相关信息
				jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
			} else {
				// 通过自然人登记接口取得纳税人相关信息
				zrrJbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm(), ud);
			}

			Timestamp now = new Timestamp((new java.util.Date()).getTime());
			// added by qinw 20070517 分票
			String strs = form.getMxstrings();
			String[] mxstrs = strs.split(";");
			List keyList = new ArrayList();
			Map sjMap = new HashMap();
			for (int i = 0; i < mxstrs.length; i++) {
				String key = processString(mxstrs[i], sjMap);
				// key.substring(0,3);
				if (key != null && key != "") {
					keyList.add(key);
				}
			}
			//System.out.println("keyKist =================" + keyList.size());
			for (int j = 0; j < keyList.size(); j++) {
				
				if (TinyTools.isCompany(form.getJsjdm())) {
					// 补充主表信息
					// 登记注册类型
					orObj.setDjzclxdm(jbsj.getDjzclxdm());
					// 国家标准行业代码
					orObj.setGjbzhydm(jbsj.getGjbzhydm());
					// 税务机关组织机构
					orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
					// 征收机关
					orObj.setZsswjgzzjgdm(ud.getSsdwdm());
					// 隶属关系
					orObj.setLsgxdm(jbsj.getLsgxdm());
					// 经营地址联系电话
					orObj.setJydzlxdm(jbsj.getJydzlxdm());
				} else {
					// 补充主表信息
					// 登记注册类型,自然人为：410
					orObj.setDjzclxdm("410");
					// 国家标准行业代码,自然人为：8190 其他未列明的服务
					orObj.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM);
					// 税务机关组织机构
					orObj.setSwjgzzjgdm(zrrJbsj.getSwjgzzjgdm());
					// 征收机关
					orObj.setZsswjgzzjgdm(ud.getSsdwdm());
					// 隶属关系
					orObj.setLsgxdm("");
					// 经营地址联系电话
					orObj.setJydzlxdm("");
				}
				
				// 录入人
				orObj.setLrr("smsb");
				if (ud != null) {
					orObj.setLrr(ud.getYhid());
				}
				// 录入日期
				orObj.setLrrq(now);
				// 创建日期
				orObj.setCjrq(now);
				// 税款类型，取key的前三位
				String sklx = (String) keyList.get(j);
				//System.out.println(sklx);
				// 欠税明细里的限缴日期
				String xjrq = sklx.substring(3, 11);
				// 比较当前日期与限缴日期
				if (Integer.parseInt(xjrq) <= Integer.parseInt(dqrq)) {
					orObj.setBz(dqrq);
				} else {
					orObj.setBz(xjrq);
				}
				// form.setSklxdm(sklx.substring(0,3));
				// orObj.setSklxdm(form.getSklxdm());
				orObj.setSklxdm(sklx.substring(0, 3));
				// 申报方式代码
				orObj.setFsdm(CodeConstant.FSDM_SMSB);
				// 数据来源
				orObj.setSjly(CodeConstant.SMSB_SJLY_BJQS);
				
				/*// 区县代码
				orObj.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));*/
				
				System.out.println("==========增加自然人的区县代码==========");
				if (TinyTools.isCompany(form.getJsjdm())) {
					// 区县代码
					orObj.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
				} else {
					// 区县代码
					orObj.setQxdm(zrrJbsj.getSwjgzzjgdm().substring(0, 2));
				}
				
				// pxList 已经排好序的明细list
				List pxList = (List) sjMap.get(keyList.get(j));
				Object[] mxObject = pxList.toArray();
				ArrayList list = new ArrayList();
				int rows = mxObject.length;
				for (int i = 0; i < rows; i++) {
					String[] tmp = mxObject[i].toString().split(":");
					HashMap map = new HashMap();
					for (int k = 0; k < columns.length; k++) {
						map.put(columns[k], tmp[k]);
					}
					list.add(map);
				}
				// form.setDataList(list);
				conn = SfDBResource.getConnection();
				ArrayList dataList = this.getDataList(orObj, list, form
						.getJksType());

				// 根据dataList处理计算修改回填欠税明细表的数据
				ArrayList qsList = this.getQsmxList(dataList);

				// 根据dataList处理整理插入申报欠税对照表的数据
				ArrayList dzList = this.getDzList(dataList);

				// 根据dataList批量插入申报缴款主表明细表数据
				// 注：申报金额为零时不做插入操作
				String sbzbsql = "";
				String sbmxsql = "";
				String ajzxjlmxsql = "";
				String ajbh = "";
				String wfwzlxdm = "";
				for (int i = 0; i < dataList.size(); i++) {
					Map tmp = (Map) (dataList.get(i));
					PreparedStatement statement = null;
					// sjse为0变更 张一军 2014.09.15
					if (!(tmp.get("sjse").equals("0")||tmp.get("sjse").equals("0.00"))) {
						// 数据保存到申报缴款主表
						sbzbsql = "insert into sbdb.sb_jl_sbjkzb (JKPZH, SKLXDM, JSJDM, FSDM, LSGXDM, YHDM, YHMC, ZH, DJZCLXDM, SWJGZZJGDM, ZSSWJGZZJGDM, GJBZHYDM, GKZZJGDM, YSKMDM, YSJCDM, SZDM, LRRQ, SBRQ, XJRQ, CLBJDM, SJJE, ZYRQ, RKJE, ZWBS, LRR, SBBH, JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY, ND, CJRQ, QXDM, SPHM, BZ) values ('"
								+ tmp.get("jkpzh")
								+ "','"
								+ tmp.get("sklxdm")
								+ "','"
								+ tmp.get("jsjdm")
								+ "','"
								+ tmp.get("fsdm")
								+ "','"
								+ tmp.get("lsgxdm")
								+ "','"
								+ tmp.get("yhdm")
								+ "','"
								+ tmp.get("yhmc")
								+ "','"
								+ tmp.get("zh")
								+ "','"
								+ tmp.get("djzclxdm")
								+ "','"
								+ tmp.get("swjgzzjgdm")
								+ "','"
								+ tmp.get("zsswjgzzjgdm")
								+ "','"
								+ tmp.get("gjbzhydm")
								+ "','"
								+ tmp.get("gkzzjgdm")
								+ "','"
								+ tmp.get("yskmdm")
								+ "','"
								+ tmp.get("ysjcdm")
								+ "','"
								+ tmp.get("szdm")
								+ "',to_date('"
								+ tmp.get("lrrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),to_date('"
								+ tmp.get("sbrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("xjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("clbjdm")
								+ "','"
								+ tmp.get("sjse")
								+ "',to_date('"
								+ tmp.get("zyrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("rkje")
								+ "','"
								+ tmp.get("zwbs")
								+ "','"
								+ tmp.get("lrr")
								+ "','"
								+ tmp.get("sbbh")
								+ "','"
								+ tmp.get("jydzlxdm")
								+ "',to_date('"
								+ tmp.get("skssksrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("skssjsrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("sjly")
								+ "','"
								+ tmp.get("nd")
								+ "',to_date('"
								+ tmp.get("cjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("qxdm")
								+ "','"
								+ tmp.get("sphm")
								+ "','" + tmp.get("bz") + "')";
						statement = conn.prepareStatement(sbzbsql);
						statement.executeUpdate();

						// 数据保存到申报缴款明细表
						sbmxsql = "insert into sbdb.sb_jl_sbjkmx (SZSMDM, JKPZH, JSJDM, YSKMDM, YSJCDM, KSSL, JSJE, SJSE, SKSSKSRQ, SKSSJSRQ, RKJE, SBBH, SJFC, QJFC, SWJGZZJGDM, ND, CJRQ, LRRQ, QXDM) values ('"
								+ tmp.get("szsmdm")
								+ "','"
								+ tmp.get("jkpzh")
								+ "','"
								+ tmp.get("jsjdm")
								+ "','"
								+ tmp.get("yskmdm")
								+ "','"
								+ tmp.get("ysjcdm")
								+ "','"
								+ tmp.get("kssl")
								+ "','"
								+ tmp.get("jsje")
								+ "','"
								+ tmp.get("sjse")
								+ "',to_date('"
								+ tmp.get("skssksrq")
								+ "','yyyy-MM-dd'),to_date('"
								+ tmp.get("skssjsrq")
								+ "','yyyy-MM-dd'),'"
								+ tmp.get("rkje")
								+ "','"
								+ tmp.get("sbbh")
								+ "','"
								+ tmp.get("sjfc")
								+ "','"
								+ tmp.get("qjfc")
								+ "','"
								+ tmp.get("swjgzzjgdm")
								+ "','"
								+ tmp.get("nd")
								+ "',to_date('"
								+ tmp.get("cjrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),to_date('"
								+ tmp.get("lrrq")
								+ "','yyyy-MM-dd hh24:mi:ss'),'"
								+ tmp.get("qxdm") + "')";

						statement = conn.prepareStatement(sbmxsql);
						statement.executeUpdate();

						// 税款类型为500的申报数据保存到案件执行记录明细
						
						//System.out.println("tmp.get('yspzh')==============="+tmp.get("yspzh"));
						// jcdb.jc_jl_ajzxjlmx
						if (tmp.get("sklxdm").equals("500")&& !(tmp.get("yspzh").equals("null"))) {
							ajbh = tmp.get("yspzh").toString().substring(0,
									tmp.get("yspzh").toString().indexOf(","));
							wfwzlxdm = tmp.get("yspzh").toString().substring(
									tmp.get("yspzh").toString()
											.lastIndexOf(",") + 1,
									tmp.get("yspzh").toString().length());

							ajzxjlmxsql = "insert into jcdb.jc_jl_ajzxjlmx (AJBH,SZSMDM,JE,JKPZH,PMZE,SKLXDM,ZXFSDM,ZXBM,ZXRY,ZXRQ,XH,XJRQ,ZHGXSJ,WFWZLXDM,CZRQ,CZRY,CJRQ,LRRQ,SWJGZZJGDM,QXDM,BZ,RKPZH,RKBS,HZNJBZ) select '"
									+ ajbh
									+ "','"
									+ tmp.get("szsmdm")
									+ "',"
									+ tmp.get("sjse")
									+ ",'"
									+ tmp.get("jkpzh")
									+ "',"
									+ tmp.get("sjse")
									+ ",'500','01','"
									+ tmp.get("zsswjgzzjgdm")
									+ "','"
									+ ud.yhmc
									+ "',to_date(to_char(SYSDATE,'yyyymmdd'),'yyyymmdd')"
									+ ",decode(max(b.xh),null,0,max(b.xh)+1)"
									+ ",to_date('"
									+ tmp.get("xjrq")
									+ "','yyyy-mm-dd'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"
									+ wfwzlxdm
									+ "',sysdate,'"
									+ ud.yhmc
									+ "',sysdate,sysdate,'"
									+ tmp.get("swjgzzjgdm")
									+ "','"
									+ tmp.get("qxdm")
									+ "','"
									+ "SBZS-BJQJSK"
									+ "','"
									+ tmp.get("jkpzh")
									+ "','"
									+ "0"
									+ "','"
									+ tmp.get("hznjbz")
									+ "' from jcdb.jc_jl_ajzxjlmx b where b.ajbh='"
									+ ajbh
									+ "' and b.szsmdm='"
									+ tmp.get("szsmdm")
									+ "' and b.wfwzlxdm='"
									+ wfwzlxdm + "'";
							//System.out.println("ajzxjlmxsql:"+ajzxjlmxsql);
							statement = conn.prepareStatement(ajzxjlmxsql);
							statement.executeUpdate();
						}
						statement.close();
					}
				}

				// 根据qsList批量修改欠税明细表数据
				String qsmxsql = "";
				for (int i = 0; i < qsList.size(); i++) {
					Map tmp = (Map) (qsList.get(i));
					PreparedStatement statement = null;

					// 修改欠税明细表数据
					qsmxsql = "update jkdb.kj_jl_qsmx set qjje=qjje-'"
							+ tmp.get("sbje") + "' ,sbje=sbje+'"
							+ tmp.get("sbje") + "'where xh='" + tmp.get("xh")
							+ "'";
					statement = conn.prepareStatement(qsmxsql);

					statement.executeUpdate();
					statement.close();

				}

				// 根据dzList批量插入申报欠税对照表数据
				String qssbdzsql = "";
				for (int i = 0; i < dzList.size(); i++) {
					Map tmp = (Map) (dzList.get(i));
					PreparedStatement statement = null;

					// 修改欠税明细表数据
					qssbdzsql = "insert into jkdb.kj_jl_sb_qs_dz (JSJDM, JKPZH, QS_XH, SBBH, SBJE, ZNJ, ZNJJKPZH, QXDM) values ('"
							+ tmp.get("jsjdm")
							+ "','"
							+ tmp.get("jkpzh")
							+ "','"
							+ tmp.get("qsxh")
							+ "','"
							+ tmp.get("sbbh")
							+ "','"
							+ tmp.get("sbje")
							+ "','"
							+ tmp.get("znj")
							+ "','"
							+ tmp.get("znjjkpzh")
							+ "','"
							+ tmp.get("qxdm") + "')";
					statement = conn.prepareStatement(qssbdzsql);

					statement.executeUpdate();
					statement.close();
				}

				// 根据dataList取得sbbhList列表集合
				ArrayList sbbhList = this.getSbbhList(dataList);
				form.setSbbhList(sbbhList);
			}

		} catch (Exception ex1) {
			ex1.printStackTrace();
			throw new ApplicationException("保存数据失败！");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * 生成插入申报缴款主表，申报缴款明细表的数据List。
	 * 
	 * @param
	 * @return 数据list
	 * @throws Exception
	 */
	private ArrayList getDataList(Sbjkzb orObj, ArrayList list, int jkstype)
			throws Exception {
		HashMap tmp = new HashMap();
		// 限缴天数常数
		int a = 15;
		int sbbhnum = 0;
		int jkpzhnum = 0;
		Date now = new Date();

		String tmpsbbh = this.getSbbh(orObj.getJsjdm());
		String tmpsbjkpzh = this.getSbJkpzh(orObj.getJsjdm());
		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			tmp.put("sklxdm", orObj.getSklxdm());
			tmp.put("jsjdm", orObj.getJsjdm());
			tmp.put("fsdm", orObj.getFsdm());
			tmp.put("lsgxdm", orObj.getLsgxdm());
			tmp.put("yhdm", orObj.getYhdm());
			tmp.put("yhmc", orObj.getYhmc());
			tmp.put("zh", orObj.getZh());
			tmp.put("djzclxdm", orObj.getDjzclxdm());
			tmp.put("swjgzzjgdm", orObj.getSwjgzzjgdm());
			tmp.put("zsswjgzzjgdm", orObj.getZsswjgzzjgdm());
			tmp.put("gjbzhydm", orObj.getGjbzhydm());
			tmp.put("lrrq", this.datestrFormat(now));
			tmp.put("sbrq", this.dateFormat(orObj.getLrrq()));
			tmp.put("xjrq", orObj.getBz());
			tmp.put("zyrq", this.dateFormat(now));
			tmp.put("rkje", tmp.get("sjse"));
			tmp.put("lrr", orObj.getLrr());
			tmp.put("jydzlxdm", orObj.getJydzlxdm());
			tmp.put("sjly", orObj.getSjly());
			tmp.put("nd", String.valueOf(TinyTools.getYear(orObj.getSbrq())));
			tmp.put("cjrq", this.datestrFormat(now));
			tmp.put("qxdm", orObj.getQxdm());
			tmp.put("gkzzjgdm", CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
					"swjgzzjgdm", orObj.getSwjgzzjgdm(), "gkjhh"));
			tmp.put("clbjdm", "10");
			Ysjc ysjc = getYsjc(tmp.get("jsjdm").toString(), tmp.get("szsmdm")
					.toString(), this.datestrFormat1(tmp.get("skssjsrq")
					.toString()));
			tmp.put("ysjcdm", ysjc.getYsjcdm());
			Yskm yskm = null;
			if (!tmp.get("szsmdm").equals("null")) {
				yskm = JKAdapter.getInstance().getYskm(
						tmp.get("szsmdm").toString(),
						tmp.get("djzclxdm").toString(),
						tmp.get("gjbzhydm").toString(), ysjc.getYsjcdm());
			}
			if (yskm != null) {
				tmp.put("yskmdm", yskm.getYskmdm());
				if (tmp.get("ysjcdm").equals("21")) { // 如果是地方级
					BigDecimal big = new BigDecimal(tmp.get("sjse").toString());
					tmp.put("sjfc", this.getFc(big, yskm.getSjfcbl())); // 设置市局级分成金额
					tmp.put("qjfc", this.getFc(big, yskm.getQxfcbl())); // 设置区县级分成金额
				}
			} else {
				tmp.put("yskmdm", "");
				tmp.put("sjfc", "0"); // 设置市局级分成金额
				tmp.put("qjfc", "0"); // 设置区县级分成金额
			}
			tmp.put("zwbs", "00000000000000000000");
			tmp.put("bz", "限" + orObj.getBz().substring(0, 4) + "年"
					+ orObj.getBz().substring(4, 6) + "月"
					+ orObj.getBz().substring(6, 8) + "日（含当日）前缴款");


			// 每8条记录生成一个申报编号
			sbbhnum = sbbhnum + 1;
			if (sbbhnum % 8 == 0) {
				tmp.put("sbbh", tmpsbbh);
				tmpsbbh = this.getSbbh(tmp.get("jsjdm").toString());
				sbbhnum = 0;
			} else {
				tmp.put("sbbh", tmpsbbh);
			}
			if (jkstype == 2) {
				// 每8条记录生成一个15位申报缴款号 不含流水号
				jkpzhnum = jkpzhnum + 1;
				if (jkpzhnum % 8 == 0) {
					tmp.put("jkpzh", tmpsbjkpzh + String.valueOf(jkpzhnum));
					tmpsbjkpzh = this.getSbJkpzh(tmp.get("jsjdm").toString());
					jkpzhnum = 0;
				} else {
					tmp.put("jkpzh", tmpsbjkpzh + String.valueOf(jkpzhnum));
				}
				tmp.put("sphm", tmp.get("jkpzh"));
			} else {
				// 一票一税缴款凭证号第16位流水号为0
				tmp.put("jkpzh", this.getSbJkpzh(tmp.get("jsjdm").toString())
						+ "0");
				tmp.put("sphm", tmp.get("jkpzh"));
			}
		}
		return list;
	}

	/**
	 * 生成修改欠税明细表的数据List。
	 * 
	 * @param
	 * @return 数据list
	 * @throws Exception
	 */
	private ArrayList getQsmxList(ArrayList list) throws Exception {
		HashMap tmp = new HashMap();
		ArrayList qsList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			HashMap map = new HashMap();
			if (i % 2 == 0) {
				map.put("xh", tmp.get("qsxh"));
				map.put("sbje", tmp.get("sjse"));
				qsList.add(map);
			}
		}
		return qsList;
	}

	/**
	 * 生成修改欠税明细表的数据List。
	 * 
	 * @param
	 * @return 数据list
	 * @throws Exception
	 */
	private ArrayList getDzList(ArrayList list) throws Exception {
		HashMap tmp1 = new HashMap();
		HashMap tmp2 = new HashMap();
		ArrayList dzList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			tmp1 = (HashMap) list.get(i);
			HashMap map = new HashMap();
			if (i % 2 == 0) {
				tmp2 = (HashMap) list.get(i + 1);
				if (tmp1.get("qsxh").toString().equals(
						tmp2.get("qsxh").toString())) {
					map.put("jsjdm", tmp1.get("jsjdm"));
					map.put("jkpzh", tmp1.get("jkpzh"));
					map.put("qsxh", tmp1.get("qsxh"));
					map.put("sbbh", tmp1.get("sbbh"));
					map.put("sbje", tmp1.get("sjse"));
					if (!tmp2.get("sjse").toString().equals("0")) {
						map.put("znj", tmp2.get("sjse"));
						map.put("znjjkpzh", tmp2.get("jkpzh"));
					} else {
						map.put("znj", "");
						map.put("znjjkpzh", "");
					}
					map.put("qxdm", tmp1.get("qxdm"));
				}
				dzList.add(map);
			}
		}
		return dzList;
	}

	/**
	 * 生成申报编号的数据List。
	 * 
	 * @param
	 * @return 数据list
	 * @throws Exception
	 */
	private ArrayList getSbbhList(ArrayList list) throws Exception {
		HashMap tmp = new HashMap();
		ArrayList sbbhList = new ArrayList();
		String sbbh = "";

		for (int i = 0; i < list.size(); i++) {
			tmp = (HashMap) list.get(i);
			if (!tmp.get("sbbh").equals("") && tmp.get("sbbh") != null) {
				if (!tmp.get("sbbh").equals(sbbh)) {
					sbbhList.add(tmp.get("sbbh"));
					sbbh = tmp.get("sbbh").toString();
				}

			}
		}
		return sbbhList;
	}

	/**
	 * getSzmc 将查询的所有税种名称存入map
	 * 
	 * @param null
	 * @return map
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Map getSzmc() throws BaseException {
		Connection conn = null;
		ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			String sql = "select t.szsmdm,t.szsmmc from dmdb.sb_dm_szsm t where length(t.szsmdm)=2 order by t.szsmdm";
			rs = da.querySQL(sql);
			while (rs.next()) {
				map.put(rs.getString("szsmdm"), rs.getString("szsmmc"));
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return map;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	private String round(double v) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("###0.00");
		String strValue = df.format(v);
		return strValue;
	}

	/**
	 * 格式化日期类型至字符串类型。
	 * 
	 * @param date
	 *            需要格式化的日期型date
	 * @return 格式化后的结果
	 */
	private String dateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * 格式化字符串类型至日期类型。
	 * 
	 * @param date
	 *            需要格式化的日期型date
	 * @return 格式化后的结果
	 */
	private String datestrFormat(Date dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dateStr);
	}

	private Date datestrFormat1(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Timestamp.valueOf(sdf.format(SfDateUtil.getDate(dateStr)));
	}

	/**
	 * 计算滞纳金天数。
	 * 
	 * @param date
	 *            原税款限缴日期
	 * @return 滞纳金天数
	 */
	private int getZnjts(Date yxjrq) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp now = new Timestamp((new java.util.Date()).getTime());
		Date startTime = formatter.parse(formatter.format(yxjrq),
				new ParsePosition(0));
		Date endTime = formatter.parse(now.toString(), new ParsePosition(0));
		long l = Math.abs(endTime.getTime() - startTime.getTime());
		return (int) (l / 86400000);
	}

	/**
	 * 计算新税款限缴日期。
	 * 
	 * @param
	 * @return 新税款限缴日期
	 */
	private String getXskssrq(Date date, int i) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, i);
		Date d = c.getTime();
		return this.dateFormat(d);
	}

	/**
	 * 通过税种税目代码来确定预算级次<br>
	 * 当税种为文化事业建设费或外商投资企业土地使用费时 通过税费接口取得相应的预算级次其他情况为地方级
	 * 
	 * @param jsjdm
	 *            计算机代码
	 * @param szsmdm
	 *            税种税目代码
	 * @param rq
	 *            日期
	 * @return Ysjc 预算级次信息
	 * @throws Exception
	 */
	private Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) throws Exception {
		Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
		// 调用税费管理接口得到预算级次
		try {
			com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
			com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy
					.getYsjcInfo(jsjdm, szsmdm, rq);
			if (sfysjc == null) {
				// 如果没有得到认定级次则认定为地方级
				ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
			} else {
				ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
			}

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询预算级次失败!");
		}

		return ysjc;
	}

	/**
	 * 计算分成金额
	 * 
	 * @param je
	 *            实缴金额
	 * @param bl
	 *            分成比例（默认是0。00）
	 * @return 分成金额(保留4位小数)，如果实缴金额或分成比例为null，则放回0
	 */
	private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
		// 计会接口没有返回分成比率
		if (je == null || bl == null) {
			return new BigDecimal(0);
		}
		BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
		return fc;
	}

	/**
	 * 得到缴款凭证号 16位<br>
	 * 规则如下:<br>
	 * ----计算机代码(8位)＋年（2位）＋月（2位）＋3位顺序号＋1位流水号<br>
	 * ----顺序号为当前计算机代码本月的记录数＋1<br>
	 * ----流水号为一票多税情况下一张凭证中多个科目的顺序号<br>
	 * 
	 * @param jsjdm
	 *            计算机代码
	 * @param time
	 *            要求查找的时间
	 * @return String 不含最后一位流水号的缴款凭证号
	 * @throws Exception
	 *             操作异常
	 */

	private String getSbJkpzh(String jsjdm) throws BaseException {
		String sbJkpzh = null;
		try {
			sbJkpzh = ServiceProxy.getJkpzh(jsjdm);

		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "调用申报生成缴款凭证号失败！");
		}
		return sbJkpzh;
	}

	/**
	 * 申报编号的生成,申报编号的生成规则为：计算机代码加上服务器的当前时间的十八位字符串
	 * 
	 * 使用申报的接口生成申报编号。
	 * 
	 * @param jsjdm
	 *            计算机代码
	 * @return sbbh
	 */
	private String getSbbh(String jsjdm) throws BaseException {
		String sbbh;
		try {
			sbbh = ServiceProxy.getSbbh(jsjdm);
		} catch (BaseException ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "调用申报生成申报表号失败！");
		}
		return sbbh;
	}

	private String processString(String a, Map map) {
		String[] strArray = a.split(":");
		String tmp = strArray[0];
		//for (int i = 0; i < strArray.length; i++) {
		//	System.out.println(strArray[i]);
		//}
		if (!tmp.equals("03")) {
			tmp = "700";
		} else {
			tmp = "500";
		}
		String key = tmp + strArray[1];
		//System.out.println("key===" + key);
		// int[] intArray = new int[strArray.length];
		// for (int i = 0; i < strArray.length; i++) {
		// intArray[i] = Integer.parseInt(strArray[i]);
		// }

		return putIntoMap(key, a, map);
	}

	private String putIntoMap(String key, String tmp, Map map) {
		List list = (List) map.get(key);
		if (list != null) {
			list.add(tmp);
			return "";
		} else {
			List newList = new ArrayList();
			newList.add(tmp);
			map.put(key, newList);
			//System.out.println(key);
			return key;
		}

	}

	// 计算日期后延时间，date为要计算的日期， i为要延后的天数
	private Date getDate(Date date, int i) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(date);
		g.add(g.DATE, i);
		return g.getTime();

	}

}
