/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.main.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsMainProcessor implements Processor {

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_CHECKACTION:
			result = doCheck(vo);
			break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {

		QysdsNewForm form = (QysdsNewForm) vo.getData();

		// 初始化FORM：设置申报日期、申报期间
		initForm(form);

		return form;
	}

	/**
	 * doQuery 查询企业的基本信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		QysdsNewForm form = (QysdsNewForm) vo.getData();

		Connection conn = null;

		try {
			conn = SfDBResource.getConnection();
			// form.setSknd(this.getSbnd(form.getSbrq()));
			/* 通过税款所属日期取得税款年度 */
			form.setSknd(form.getSkssksrq().substring(0, 4));
			form = (QysdsNewForm) QysdsNewUtil.queryQysdsJbxx(conn, form);

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	private Object doCheck(VOPackage vo) throws BaseException {
		QysdsNewForm form = (QysdsNewForm) vo.getData();
		UserData userData = vo.getUserData();
		Connection conn = null;
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			form.setQh("1");
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			QysdsNewUtil.setQysdsReport(report, form);
			System.out.println("Appid-" + report.getAppid());
			System.out.println("Bbqlx-" + report.getBbqlx());
			System.out.println("Nsrjsjdm-" + report.getNsrjsjdm());
			System.out.println("Nsrmc-" + report.getNsrmc());
			System.out.println("Qh-" + report.getQh());
			System.out.println("Version-" + report.getVersion());

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			report = (QysdsReportsDeclare) iApp.query(report);
			System.out.println(report.getTableContentList().size());
			System.out.println("-----------------------------------------");
			System.out.println(report.getTableContentList().size());
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);

			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List checkList = checker.checkMain(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			if (checkList == null) {
				System.out
						.println("-----------------------------------null------");

			} else {

				System.out.println("-----------------------------------------"
						+ checkList.size());
			}

			// 全表审核通过
			if (checkList == null
					|| (checkList != null && checkList.size() == 0)) {
				Timestamp t1, t2;

				t1 = new Timestamp(System.currentTimeMillis());

				// 插入减免表
				// this.insertJm(declare);
				this.insertJmProce(report, userData);

				t2 = new Timestamp(System.currentTimeMillis());

				System.out.println("插入减免申报表数据耗时："
						+ (t2.getTime() - t1.getTime()));
			}
			form.setCheckList(checkList);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

	/**
	 * 初始化
	 * 
	 * @param form
	 *            主表数据
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private void initForm(QysdsNewForm form) throws BaseException {

		form.setSbrq(SfDateUtil.getDate());
		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setZsfsdm("");
		// 申报期间
		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
		try {
			String ksrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSKSRQ), DateTimeUtil.JAVA_DATEFORMAT);

			String jsrq = DateTimeUtil.timestampToString((Timestamp) qj
					.get(Skssrq.SKSSJSRQ), DateTimeUtil.JAVA_DATEFORMAT);
			form.setSknd((String) qj.get(Skssrq.SKSSRQ_ND));
			form.setSkssksrq(ksrq);
			form.setSkssjsrq(jsrq);
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * 计算申报年度
	 * 
	 * @param sbrq
	 *            申报日期
	 * @return String 年度
	 */

	private String getSbnd(String sbrq) {

		Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
		System.out.println("------------------------getSbnd---"
				+ (String) qj.get(Skssrq.SKSSRQ_ND));
		return (String) qj.get(Skssrq.SKSSRQ_ND);
	}

	/**
	 * 根据申报日期取得当前前年0101-1231
	 * 
	 * @param curSbrq
	 *            申报日期
	 * @return dateMap
	 */
	private Map getSbrl(String curSbrq) {
		Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(sbrq);
		calendar.add(calendar.YEAR, -1);
		int year = calendar.get(calendar.YEAR);
		String nd = new Integer(year).toString();
		Timestamp ksrq;
		Timestamp jsrq;
		Map retMap = new HashMap();
		ksrq = new Timestamp(new GregorianCalendar(year, 0, 1).getTime()
				.getTime());
		jsrq = new Timestamp(new GregorianCalendar(year, 11, 31).getTime()
				.getTime());

		Map dateMap = new HashMap();
		dateMap.put("ksrq", ksrq);
		dateMap.put("jsrq", jsrq);
		return dateMap;
	}

	/**
	 * 插入减免数据
	 * 
	 * @param table
	 * @return
	 */
	public void insertJmProce(QysdsReportsDeclare declarein, UserData userData)
			throws BaseException {

		// 报表对象
		QysdsReportsDeclare declare = declarein;

		Connection con = null;
		CallableStatement st = null;
		String sql = "";

		Timestamp now = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		try {
			String jsjdm = declare.getNsrjsjdm();
			String jmlx = CodeConstant.JMLX_SP;
			String szsmdm = CodeConstant.SZSM_QYSDSCODE;
			// String sbrq = df.format(now);
			Timestamp sbrq = now;
			String fsdm = CodeConstant.FSDM_SMSB;
			String jzbz = CodeConstant.SMSB_JZBZ;
			// String lrr = declare.getNsrjsjdm();
			String lrr = userData.getYhid();
			// String skssjsrq = df.format(declare.getSkssjsrq());
			// String skssksrq = df.format(declare.getSkssksrq());
			java.sql.Date skssjsrq = declare.getSkssjsrq();
			java.sql.Date skssksrq = declare.getSkssksrq();
			String swjgzzjgdm = declare.getSwjgzzjgdm();
			String qxdm = declare.getSwjgzzjgdm().substring(0, 2);
			String djzclxdm = declare.getJbxx().getSsjjlx();
			String gjbzhydm = declare.getJbxx().getSshy();
			String nd = declare.getSknd();

			Ysjc ysjc = null;
			try {
				ysjc = JksUtil
						.getYsjc(jsjdm, CodeConstant.SZSM_QYSDSCODE, sbrq);
			} catch (Exception e) {
				throw new ApplicationException("该计算机代码得预算级次代码没有纪录！");
			}
			if (ysjc != null) {
				Debug.out("级次 =" + ysjc.getYsjcdm());
			} else {
				throw new ApplicationException("该计算机代码的预算级次代码没有纪录！");
			}
			String ysjcdm = ysjc.getYsjcdm();

			com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
			try {
				System.out.println("djsj.getDjzclxdm()--" + djzclxdm);
				System.out.println("djsj.getGjbzhydm()--" + gjbzhydm);
				System.out.println("ysjc.getYsjcdm()--" + ysjc.getYsjcdm());
				yskm = JKAdapter.getInstance().getYskm(
						CodeConstant.SZSM_QYSDSCODE, djzclxdm, gjbzhydm,
						ysjc.getYsjcdm());

			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
			}
			if (yskm != null) {
				Debug.out("预算科目 =" + yskm.getYskmdm());
			} else {
				throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
			}

			String yskmdm = yskm.getYskmdm();

			// 创建数据库连接
			con = SfDBResource.getConnection();
			sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjmproce(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

			st = con.prepareCall(sql);

			System.out.println("-------------调用插入减免申报存储过程参数----------------");
			System.out.println("1-jsjdm--" + jsjdm);
			System.out.println("2-jmlx--" + jmlx);
			System.out.println("3-szsmdm--" + szsmdm);
			System.out.println("4-sbrq--" + sbrq);
			System.out.println("5-fsdm--" + fsdm);
			System.out.println("6-jzbz--" + jzbz);
			System.out.println("7-lrr--" + lrr);
			System.out.println("8-skssjsrq--" + skssjsrq);
			System.out.println("9-skssksrq--" + skssksrq);
			System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
			System.out.println("11-qxdm--" + qxdm);
			System.out.println("12-djzclxdm--" + djzclxdm);
			System.out.println("13-gjbzhydm--" + gjbzhydm);
			System.out.println("14-nd--" + nd);
			System.out.println("15-ysjcdm--" + ysjcdm);
			System.out.println("16-yskmdm--" + yskmdm);

			st.setString(1, jsjdm);
			st.setString(2, jmlx);
			st.setString(3, szsmdm);
			st.setTimestamp(4, sbrq);
			st.setString(5, fsdm);

			st.setString(6, jzbz);
			st.setString(7, lrr);
			st.setDate(8, skssjsrq);
			st.setDate(9, skssksrq);
			st.setString(10, swjgzzjgdm);

			st.setString(11, qxdm);
			st.setString(12, djzclxdm);
			st.setString(13, gjbzhydm);
			st.setString(14, nd);
			st.setString(15, ysjcdm);

			st.setString(16, yskmdm);

			st.execute();

		} catch (Exception e) {

			e.printStackTrace();
			throw new ApplicationException("插入减免申报数据出错.");

		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				st = null;
			}

			// 释放数据库连接
			SfDBResource.freeConnection(con);
		}

	}

}
