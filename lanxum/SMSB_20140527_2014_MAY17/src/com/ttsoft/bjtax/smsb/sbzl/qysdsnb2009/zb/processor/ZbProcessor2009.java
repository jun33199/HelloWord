/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.web.ZbForm2009;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年度纳税申报表
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class ZbProcessor2009 implements Processor {

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
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
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
		// 获取Action传递过来ZbForm对象
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 创建QysdsReportsDeclare对象
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// 将form中的基本信息转入QysdsReportsDeclare report 中
			QysdsUtil2009.setQysdsReport(report, zbForm);
			// 设置QysdsReportsTableDeclare的基本信息
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
			table.setTableName(CodeConstant.TABLE_NAME_2009_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_ZB);
			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 42 };
			zbForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,
					arrs)));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回czqysdsjbForm
		return zbForm;
	}

	/**
	 * doSave 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// 得到Action传递过来ZbForm对象
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);
			// 更新审核状态为“保存成功”
			iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回zbForm
		return zbForm;
	}

	/**
	 * doCheck 用于校验表内关系
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		// 得到Action传递过来ZbForm对象
		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			zbForm.setCheckList(listSingle);
			/* 如果校验通过，调用接口保存数据 */
			if (listSingle == null
					|| (listSingle != null && listSingle.size() == 0)) {
				iApp.saveSingleTable(report);
				// 更新审核状态为“单表审核通过”
				iApp
						.updateCheckStatus(report,
								Constants.QYSDS_SHZT_SINGLE_PASS);

				// 审核通过之后保存减免数据
				// this.saveJM(vo);
			} else if (listSingle.size() > 0) {
				// 单表审核未通过
				iApp.updateCheckStatus(report, "");
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 检验成功返回cbmxbybqyForm
		return zbForm;
	}

	/**
	 * doDelete 用于删除页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		ZbForm2009 zbForm = (ZbForm2009) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(zbForm);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
			table.setTableName(CodeConstant.TABLE_NAME_ZB);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2009_ZB);
			// 取list
			int[] arrs = { 1, 35 };
			zbForm.setDataList(this.translate2Page(QysdsUtil2009.putSpace(table,
					arrs)));

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return zbForm;
	}

	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param zbForm
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(ZbForm2009 form) {

		// 企业所得税报表申明对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// 将form中的基本信息转入QysdsReportsDeclare对象中
		QysdsUtil2009.setQysdsReport(report, form);
		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2009_ZB);
		table.setTableName(CodeConstant.TABLE_NAME_2009_ZB);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// 把页面数据翻译成数据库接口的数据格式
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2009.cleanSpace(table));
		return report;
	}

	/**
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		// 创建List对象，用来存放页面表单数据
		ArrayList pagelist = new ArrayList();
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		Debug.out("----start---2009版 企业所得税 年度申报主表----translate2Page");
		while (it.hasNext()) {
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QysdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			if(item.getItemID().equals("26")){
				map.put("hc", item.getItemID());
				map.put("ljje", "25");
			}else{
				map.put("hc", item.getItemID());
				map.put("ljje", item.getItemValue());
			}
			pagelist.add(map);
			Debug.out("行次："+item.getItemID()+"，域值："+item.getItemValue());
		}
		Debug.out("----over---2009版 企业所得税 年度申报主表----translate2Page");
		return pagelist;
	}

	/**
	 * 沿用以前年报的减免数据保存方法
	 * 
	 * @param vo
	 * @throws BaseException
	 */
	private void saveJM(VOPackage vo) throws BaseException {

		ZbForm2009 form = (ZbForm2009) vo.getData();

		ServiceProxy proxy = new ServiceProxy();
		SfDBAccess dbAgent = null;
		Connection conn = null;

		conn = SfDBResource.getConnection();
		dbAgent = new SfDBAccess(conn);

		// 页面数据List
		List list = form.getDataList();

		/*
		 * 准备参数
		 */

		// 减免金额
		String jmje = form.getJmlx();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String hc = (String) map.get("hc");
			if (hc.equals("29")) {
				jmje = (String) map.get("ljje");
				break;
			}
		}

		// 税费认定信息
		java.util.Date time = SfDateUtil.getDate(form.getSbrq());
		// QysdsSet sdsInfo = proxy.getQysdsInfo(
		// form.getJsjdm(),
		// time,
		// SfDateUtil.getDate(form.getSkssksrq()),
		// SfDateUtil.getDate(form.getSkssjsrq()),
		// CodeConstant.SFGL_QYSDS_BBFS_NB);
		QysdsSet sdsInfo = new QysdsSet();

		// 操作员信息
		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = new SWDJJBSJ();
		/*
		 * 企业所得税通过基本信息表管理企业基本信息表，不通过登记 故从form中取得其基本信息 2007.01.18 by wofei
		 */
		djsj.setGjbzhydm(form.getSshy());
		djsj.setSwjgzzjgdm(form.getSwjgzzjgdm());
		djsj.setDjzclxdm(form.getSsjjlx());

		// try {
		// // 获得企业登记基本信息
		// djsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
		// }
		// catch (Exception ex1) {
		// ex1.printStackTrace();
		// }
		// if (djsj == null) {
		// throw new ApplicationException("保存减免数据时，未获取到该户企业的登记信息！");
		// }

		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		if (form.getCjrq() == null || "".equals(form.getCjrq())) {
			form.setCjrq(TinyTools.Date2String(new Date(),
					CodeConstant.DATETYPE));
		}

//		Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(
//				form.getCjrq(), CodeConstant.DATETYPE).getTime());
//		Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(
//				form.getLrrq(), CodeConstant.DATETYPE).getTime());
//		Timestamp ts_sbrq = new Timestamp(TinyTools.stringToDate(
//				form.getSbrq(), "yyyyMMdd").getTime());
//		Timestamp ts_Skssjsrq = new Timestamp(TinyTools.stringToDate(
//				form.getSkssjsrq(), "yyyyMMdd").getTime());
//		Timestamp ts_Skssksrq = new Timestamp(TinyTools.stringToDate(
//				form.getSkssksrq(), "yyyyMMdd").getTime());

//		this.putJm(jmje, sdsInfo, form, dbAgent, ud, proxy, djsj, ts_cjrq,
//				ts_lrrq, ts_sbrq, ts_Skssjsrq, ts_Skssksrq, time);

	}

	/**
	 * 保存减免数据
	 * 
	 * @param jmje
	 *            减免金额
	 * @param sdsInfo
	 *            税费认定信息
	 * @param form
	 *            录入数据
	 * @param dbAgent
	 *            数据库链接
	 * @param ud
	 *            操作员信息
	 * @param proxy
	 *            接口
	 * @param djsj
	 *            登记基本信息
	 * @param ts_cjrq
	 *            创建日期
	 * @param ts_lrrq
	 *            录入日期
	 * @param ts_sbrq
	 *            申报日期
	 * @param ts_Skssjsrq
	 *            税款所属结束日期
	 * @param ts_Skssksrq
	 *            税款所属开始日期
	 * @param time
	 *            当前时间
	 */
	private void putJm(String jmje, QysdsSet sdsInfo, ZbForm2009 form,
			SfDBAccess dbAgent, UserData ud, ServiceProxy proxy, SWDJJBSJ djsj,
			Timestamp ts_cjrq, Timestamp ts_lrrq, Timestamp ts_sbrq,
			Timestamp ts_Skssjsrq, Timestamp ts_Skssksrq, java.util.Date time) {
		try {
			String jmzg = form.getJmlx();
			if (form.getJmlx() != null && !form.equals("")) {
				jmzg = "yes";
			} else {
				jmzg = "no";
			}
			if (!"".equals(jmje) && jmzg.equals("yes")) {
				// 当企业所得税季报和年报的减免字段有值且纳税人有减免资格的时候，要往减免申报表插入数据：
				// 控制修改：
				// 如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
				// 如果本征期已经插入过数据了，且数据记账标识为已记账，则不用再插入数据；
				// 如果本征期没有插入过数据，则插入一条数据；
				//
				// 且网上减免税申报的时候控制不可录入企业所得税的减免数据，即在申报的税种税目下过滤掉企业所得税的税种税目；

				Debug.out("进入减免");
				Jm jm = new Jm();
				Map dateMap = getSbrl(form.getSbrq());
				Vector vZb = new Vector();

				// vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
				vZb.add("qxdm='" + djsj.getSwjgzzjgdm().substring(0, 2) + "'");

				vZb.add("SKSSKSRQ = to_date('"
						+ String.valueOf(dateMap.get("ksrq")).substring(0, 10)
						+ "','yyyy-MM-dd')");
				vZb.add("SKSSJSRQ = to_date('"
						+ String.valueOf(dateMap.get("jsrq")).substring(0, 10)
						+ "','yyyy-MM-dd')");
				vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB + "' OR FSDM='"
						+ CodeConstant.FSDM_SMSB + "') ");

				// 不对jzbz进行判断，只有没有减免数据的时候才插入新的数据，如果有减免数据在根据jzbz进行处理
				// vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_UNEDIT + "%"
				// + "'");

				vZb.add("szsmdm ='" + CodeConstant.SZSM_QYSDSCODE + "'");
				vZb.add("jsjdm='" + form.getJsjdm() + "' ");
				List zbData = dbAgent.query(Jm.class, vZb);
				Debug.out("查到的数据为" + zbData.size());
				if (zbData.size() <= 0) {
					// 本征期没有插入过数据，插入一条数据；
					try {
						// 删除明细数据
						// dbAgent.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
						dbAgent
								.delete(
										" qxdm='"
												+ djsj.getSwjgzzjgdm()
														.substring(0, 2)
												+ "' and SKSSKSRQ = to_date('"
												+ String.valueOf(
														dateMap.get("ksrq"))
														.substring(0, 10)
												+ "','yyyy-MM-dd') and SKSSJSRQ = to_date('"
												+ String.valueOf(
														dateMap.get("jsrq"))
														.substring(0, 10)
												+ "','yyyy-MM-dd') "
												+ "and jzbz like '"
												+ CodeConstant.SMSB_JZBZ_EDIT
												+ "%" + "'" + "and ( FSDM='"
												+ CodeConstant.FSDM_WSSB
												+ "' OR FSDM='"
												+ CodeConstant.FSDM_SMSB
												+ "') " + "and jsjdm='"
												+ form.getJsjdm() + "'",
										new Jm());

						Debug.out("减免数据库删除原有数据");
					} catch (BaseException ex1) {
						throw new ApplicationException("数据库操作失败，请您找管理员联系！");
					}
					Ysjc ysjc = null;
					try {
						ysjc = JksUtil.getYsjc(form.getJsjdm(),
								CodeConstant.SZSM_QYSDS, SfDateUtil
										.getDate(form.getSbrq()));
					} catch (Exception e) {
						throw new ApplicationException("该计算机代码得预算级次代码没有纪录！");
					}
					if (ysjc != null) {
						Debug.out("级次 =" + ysjc.getYsjcdm());
					} else {
						throw new ApplicationException("该计算机代码的预算级次代码没有纪录！");
					}

					com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
					try {
						System.out.println("djsj.getDjzclxdm()--"
								+ djsj.getDjzclxdm());
						System.out.println("djsj.getGjbzhydm()--"
								+ djsj.getGjbzhydm());
						System.out.println("ysjc.getYsjcdm()--"
								+ ysjc.getYsjcdm());
						yskm = JKAdapter.getInstance().getYskm(
								CodeConstant.SZSM_QYSDSCODE,
								djsj.getDjzclxdm(), djsj.getGjbzhydm(),
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
					Date date = TinyTools.stringToDate(form.getSbrq(),
							"yyyyMMdd");
					// 减免税情况接口
					// Update Start Zhou kejing 20031212
					String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
							CodeConstant.SZSM_QYSDS,
							// time);
							SfDateUtil.getDate(form.getSkssksrq()), SfDateUtil
									.getDate(form.getSkssjsrq()));
					// Update End Zhou kejing 20031212
					Debug.out("减免类别代码 =" + jmxmdm);
					// 减免值对象插入
					jm.setCjrq(ts_cjrq);
					jm.setJsjdm(form.getJsjdm());
					jm.setJmlx(CodeConstant.JMLX_SP);
					jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
					jm.setSbrq(ts_sbrq);
					jm.setLrrq(ts_lrrq);
					jm.setFsdm(CodeConstant.FSDM_SMSB);
					jm.setJzbz(CodeConstant.SMSB_JZBZ);
					jm.setJmse(new BigDecimal(jmje));
					jm.setJsje(new BigDecimal(jmje));
					jm.setJmxmdm(jmxmdm);
					jm.setLrr(ud.getYhid());
					jm.setSkssjsrq(ts_Skssjsrq);
					jm.setSkssksrq(ts_Skssksrq);
					jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
					// jm.setQxdm(InterfaceDj.getQxdm(ud));
					jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
					jm.setDjzclxdm(djsj.getDjzclxdm());
					jm.setGjbzhydm(djsj.getGjbzhydm());
					jm.setNd(form.getSknd());
					jm.setYsjcdm(ysjc.getYsjcdm());
					jm.setYskmdm(yskm.getYskmdm());
					try {
						// 插入减免表数据
						dbAgent.insert(jm);
					} catch (BaseException ex4) {
						throw new ApplicationException("数据库操作失败，请您找管理员联系！");
					}
				} else {
					// 如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
					Jm jmTemp = (Jm) zbData.get(0);
					if (jmTemp.getJzbz().substring(0, 1).equals(
							CodeConstant.SMSB_JZBZ_EDIT)) {
						// 未记账，则更新jmse
						jmTemp.setJmse(new BigDecimal(jmje));
						dbAgent.update(jmTemp);
					}

				}
			}

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
		}
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
}
