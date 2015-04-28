package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.web.HdzssdsnbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsHdzsNbUtil2014;


/**
 * 2014核定征收企业所得税季报
 * 项目名称：企业所得税   
 * 类名称：HdzssdsnbProcessor   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-4-10 下午3:37:12   
 * 修改人：wangcy   
 * 修改时间：2014-4-10 下午3:37:12   
 * 修改备注：   
 * @version  1.0
 */
public class HdzssdsnbProcessor implements Processor {

	// 企业所得税税率
	private static final String QYSDS_SL = "0.25";

    private QysdsHdzsNbUtil2014 util = new QysdsHdzsNbUtil2014();

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
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
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
		// 得到Action传递过来HdzssdsnbForm对象
		HdzssdsnbForm form = (HdzssdsnbForm) vo.getData();
		// 得到当前时间的所属月
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		//Map getsbjd = this.quarterSkssrq(curTime);
		//Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		//Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		Map nbqj = Skssrq.yearSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) nbqj.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) nbqj.get(Skssrq.SKSSJSRQ);
		// 税款所属开始日期
		form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// 税款所属结束日期
		form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// 税款申报日期
		form.setSbrq(SfDateUtil.getDate());
		
		form.setSknd((String) nbqj.get(Skssrq.SKSSRQ_ND));
		form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		return form;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		// 得到Action传递过来HdzssdsnbForm对象
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			// 获取税款所属季度
			String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(hdzssdsnbForm.getSkssjsrq()));

			System.out.println(hdzssdsnbForm.getJsjdm() + "hdzssdsnbForm.getSbrq()：" + hdzssdsnbForm.getSbrq());
			System.out.println(hdzssdsnbForm.getJsjdm() + "hdzssdsnbForm.getSkssjsrq()：" + hdzssdsnbForm.getSkssjsrq());
			System.out.println(hdzssdsnbForm.getJsjdm() + "的jd：" + jd);

			// 获取税款所属年度
			// String sknd = this.getNd(jd, getsbnd, hdzssdsnbForm.getSbrq());
			// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
			// 从页面中取得税款所属期和年度
			String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
			// 设置季度
			//hdzssdsnbForm.setQh(jd);
			hdzssdsnbForm.setQh("1");
			// 设置年度
			hdzssdsnbForm.setSknd(nd);

			// 设置form中其它所需属性
			hdzssdsnbForm = (HdzssdsnbForm) QysdsNewUtil.queryDjxxByInterfaceDJ(conn, hdzssdsnbForm, vo.getUserData());
			SWDJJBSJ djsj = null;
			try {
				// 获得企业登记基本信息
				djsj = InterfaceDj.getJBSJ_New(hdzssdsnbForm.getJsjdm(), vo.getUserData());
			} catch (Exception ex1) {
				throw new ApplicationException("没有该纳税人的登记信息或者没有权限查看该纳税人信息！");
			}
			//获取是否是新开户  是：Y 否：N 
			hdzssdsnbForm.setSfxkh(util.getSfxkh(nd,djsj));
			//获取税款所属期所在年度上一年度征收方式
			hdzssdsnbForm.setSyndZsfsdm(util.getSyndZsfsDm(hdzssdsnbForm.getJsjdm(), nd));
			//获取税款所属期所在年度上一年度的汇算清缴申报信息 主表行9、25 附表五45、46、47
			Map map=util.getSyndHsqjSbxx(hdzssdsnbForm.getSyndZsfsdm(),hdzssdsnbForm.getJsjdm(), nd);
			hdzssdsnbForm.setSyndZbh6(map.get("syndZbh6").toString());
			hdzssdsnbForm.setSyndZbh25(map.get("syndZbh25").toString());
			hdzssdsnbForm.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
			System.out.println("企业征收类型1_" + hdzssdsnbForm.getQyzslx());
			// 税费核定信息
			this.getHdxx(hdzssdsnbForm);
			System.out.println("企业征收类型2_" + hdzssdsnbForm.getQyzslx());
			/* 征收方式 */
			String zsfs = hdzssdsnbForm.getZsfs();

			System.out.println(hdzssdsnbForm.getJsjdm() + "的征收方式代码：" + zsfs);

			if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
				throw new ApplicationException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
			}
			if (CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
				throw new ApplicationException(
						"该企业已认定为查帐征收户，不能在此录入，请录入查帐征收季度申报表！");
			}

			// 创建QysdsReportsDeclare对象
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// 将form中的基本信息转入QysdsReportsDeclare report 中
			QysdsHdzsNbUtil2014.setQysdsReport(report, hdzssdsnbForm);
			// 设置QysdsReportsTableDeclare的基本信息
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(QysdsConstant2014.TABLE_ID_2014_24);
			table.setTableName(QysdsConstant2014.TABLE_NAME_2014_24);
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
					.get(QysdsConstant2014.TABLE_ID_2014_24);

			if (table.getCellContentList().size() > 0) {
				hdzssdsnbForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
				hdzssdsnbForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
				hdzssdsnbForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
			}

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 19 };
			hdzssdsnbForm.setQysdsnbList(this.translate2Page(putSpace(table, arrs), zsfs));

			// 测试用，完成后要删除
//			System.out.println("计算机代码-" + hdzssdsnbForm.getJsjdm());
//			System.out.println("申报日期-" + hdzssdsnbForm.getSbrq());
//			System.out.println("纳税人名称-" + hdzssdsnbForm.getNsrmc());
//			System.out.println("税款年度-" + hdzssdsnbForm.getSknd());
//			System.out.println("期号-" + hdzssdsnbForm.getQh());
//			System.out.println("报表期类型-" + hdzssdsnbForm.getBbqlx());
//			System.out.println("税款所属开始日期-" + hdzssdsnbForm.getSkssksrq());
//			System.out.println("税款所属结束日期-" + hdzssdsnbForm.getSkssjsrq());
//			System.out.println("税务机关组织机构代码-" + hdzssdsnbForm.getSwjgzzjgdm());
//			System.out.println("区县代码-" + hdzssdsnbForm.getQxdm());
//			System.out.println("录入人-" + hdzssdsnbForm.getLrr());
//			System.out.println("税务所计算机代码-" + hdzssdsnbForm.getSwjsjdm());
//			System.out.println("企业征收类型_" + hdzssdsnbForm.getQyzslx());

		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回hdzssdsnbForm
		return hdzssdsnbForm;
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
		// 得到Action传递过来HdzssdsnbForm对象

		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();
		Connection conn = null;

		// Map getsbnd = this.quarterSkssrq(SfDateUtil.getDate(hdzssdsnbForm.getSbrq()));
		// 获取税款所属季度
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(hdzssdsnbForm
				.getSkssjsrq()));
		// 获取税款所属年度
		// String sknd = this.getNd(jd, getsbnd, hdzssdsnbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// 从页面中取得税款所属期和年度
		String nd = hdzssdsnbForm.getSkssksrq().substring(0, 4);
		// 设置季度
		//hdzssdsnbForm.setQh(jd);
		hdzssdsnbForm.setQh("1");
		// 设置年度
		hdzssdsnbForm.setSknd(nd);
		System.out.println("=====doSave=======");
		try {

			// /* 征收方式 */
			String zsfs = hdzssdsnbForm.getZsfs();
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将hdzssdsnbForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QysdsReportsDeclare report = this.translate2Interface(hdzssdsnbForm, zsfs);
			report.setVersion(QysdsConstant2014.QYSDS_VERSION_HDZSQYSDSNB_2014);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);

			// 获取一个具有空值的QysdsReportsTableDeclare对象
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(QysdsConstant2014.TABLE_ID_2014_24);
			table.getCellContentList().clear();

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 17 };

			hdzssdsnbForm.setQysdsnbList(null);

			hdzssdsnbForm = (HdzssdsnbForm) this.doShow(vo);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回hdzssdsnbForm
		return hdzssdsnbForm;
	}

	/**
	 * 计算季报类型的税款所属日期
	 *
	 * @param curDate
	 *            日期
	 * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp 使用Key ＝
	 *         Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp 使用Key ＝ Skssrq.SKSSRQ_ND 得到
	 *         税款所属日期所在的年度 String
	 */
	public Map quarterSkssrq(Date curDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				(jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
		Map retMap = new HashMap();
		retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
		retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
		retMap.put(Skssrq.SKSSRQ_ND, nd);
		return retMap;
	}

	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 *
	 * @param form
	 * @return 企业所得税报表申明对象
	 */
	private QysdsReportsDeclare translate2Interface(HdzssdsnbForm form,String zsfs) {

		// 创建QysdsReportsDeclare对象
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// 将form中的基本信息转入QysdsReportsDeclare对象中
		QysdsNewUtil.setQysdsReport(report, form);

		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(QysdsConstant2014.TABLE_ID_2014_24);
		table.setTableName(QysdsConstant2014.TABLE_NAME_2014_24);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// 把页面数据翻译成数据库接口所需的数据格式
		List list = form.getQysdsnbList();

		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc = (String) map.get("hc");
			if(zsfs.equals(CodeConstant.ZSFSDM_CYLZS)){
				if ("1".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("1");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje1"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("2".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("2");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje2"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("3".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("3");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje3"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("4".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("4");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje4"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("5".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("5");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje5"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}
				if ("6".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("6");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje6"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);
				}				
				if ("10".equals(hc)) {
					QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
					item_1.setItemID("10");
					item_1.setItemValue((String) map.get("lje"));
					System.out.println("保存lje10"+(String) map.get("lje"));
					item_1.setItemType("11");
					table.getCellContentList().put(item_1.getItemID(), item_1);

				}
			}
			if ("11".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("11");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje11"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("12".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("12");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje12"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("13".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("13");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje13"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("14".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("14");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje14"+(String) map.get("lje"));
				item_1.setItemType("11");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			
			if ("16".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("16");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje16"+(String) map.get("lje"));
				item_1.setItemType("16");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("17".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("17");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje17"+(String) map.get("lje"));
				item_1.setItemType("17");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("18".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("18");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje18"+(String) map.get("lje"));
				item_1.setItemType("18");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
			if ("19".equals(hc)) {
				QysdsReportsItemDeclare item_1 = new QysdsReportsItemDeclare();
				item_1.setItemID("19");
				item_1.setItemValue((String) map.get("lje"));
				System.out.println("保存lje19"+(String) map.get("lje"));
				item_1.setItemType("19");
				table.getCellContentList().put(item_1.getItemID(), item_1);
			}
		}

		report.getTableContentList().put(table.getTableId(), QysdsHdzsNbUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * 将接口数据结构中的数据转换，置入页面要求的数据结构 接口数据结构-->页面数据结构
	 *
	 * @param QysdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QysdsReportsTableDeclare table,String zsfs) {

		// 创建每个行所对应的map
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		HashMap map3 = new HashMap();
		HashMap map4 = new HashMap();
		HashMap map5 = new HashMap();
		HashMap map6 = new HashMap();
		HashMap map7 = new HashMap();
		HashMap map8 = new HashMap();
		HashMap map9 = new HashMap();
		HashMap map10 = new HashMap();
		HashMap map11 = new HashMap();
		HashMap map12 = new HashMap();
		HashMap map13 = new HashMap();
		HashMap map14 = new HashMap();
		
		HashMap map16 = new HashMap();
		HashMap map17 = new HashMap();
		HashMap map18 = new HashMap();
		HashMap map19 = new HashMap();
		// 创建List对象，用来存放页面表单数据
		ArrayList pagelist = new ArrayList();
		// 转入表单各行的数据
		//纯益率征收
		if(zsfs.equals(CodeConstant.ZSFSDM_CYLZS)){
			map1.put("hc", "1");
			map1.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("1")).getItemValue());
			pagelist.add(map1);

			map2.put("hc", "2");
			map2.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("2")).getItemValue());
			pagelist.add(map2);

			map3.put("hc", "3");
			map3.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("3")).getItemValue());
			pagelist.add(map3);

			map4.put("hc", "4");
			map4.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("4")).getItemValue());
			pagelist.add(map4);

			map5.put("hc", "5");
			map5.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("5")).getItemValue());
			pagelist.add(map5);

			map6.put("hc", "6");
			map6.put("lje", ((QysdsReportsItemDeclare) table.getCellContentList()
					.get("6")).getItemValue());
			pagelist.add(map6);
			
			map10.put("hc", "10");
			map10.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
					.get("10")).getItemValue());
			pagelist.add(map10);

		}
		//定额征收
		if(zsfs.equals(CodeConstant.ZSFSDM_DEZS)){
			map1.put("hc", "1");
			map1.put("lje","*");
			pagelist.add(map1);

			map2.put("hc", "2");
			map2.put("lje", "*");
			pagelist.add(map2);

			map3.put("hc", "3");
			map3.put("lje", "*");
			pagelist.add(map3);
			
			map4.put("hc", "4");
			map4.put("lje","*");
			pagelist.add(map4);

			map5.put("hc", "5");
			map5.put("lje", "*");
			pagelist.add(map5);

			map6.put("hc", "6");
			map6.put("lje", "*");
			pagelist.add(map6);
			
			map10.put("hc", "10");
			map10.put("lje","*");
			pagelist.add(map10);
		}

		map7.put("hc", "7");
		map7.put("lje","*");
		pagelist.add(map7);

		map8.put("hc", "8");
		map8.put("lje","*");
		pagelist.add(map8);

		map9.put("hc", "9");
		map9.put("lje","*");
		pagelist.add(map9);



		map11.put("hc","11");
		map11.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("11")).getItemValue());
		pagelist.add(map11);

		map12.put("hc","12");
		map12.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("12")).getItemValue());
		pagelist.add(map12);

		map13.put("hc","13");
		map13.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("13")).getItemValue());
		pagelist.add(map13);
		
		map14.put("hc","14");
		map14.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("14")).getItemValue());
		pagelist.add(map14);
		
		map16.put("hc","16");
		map16.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("16")).getItemValue());
		pagelist.add(map16);
		
		map17.put("hc","17");
		map17.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("17")).getItemValue());
		pagelist.add(map17);
		
		map18.put("hc","18");
		map18.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("18")).getItemValue());
		pagelist.add(map18);
		
		map19.put("hc","19");
		map19.put("lje",((QysdsReportsItemDeclare) table.getCellContentList()
				.get("19")).getItemValue());
		pagelist.add(map19);
		// 返回List对象
		return pagelist;
	}
	/**
	 * 取从一率和企业征税类型,用于页面校验
	 *
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(HdzssdsnbForm form) throws BaseException {

		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		// 应纳所得税额
		String ynsdse = "0.00";
		// 定额征收税额
		String dezsse = "0.00";

		// 当前时间
		// Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// 从申报页面取得申报日期和税款所属日期
		Timestamp sbrq = QysdsNewUtil.getNxetTimestamp(form.getSkssjsrq());

		// Map getsbjd = this.quarterSkssrq(sbrq);
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(form.getSkssksrq());
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq：" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq：" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq：" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh：" + form.getQh());

		ServiceProxy proxy = new ServiceProxy();

		String bblx = form.getBbqlx();
		String jsjdm = form.getJsjdm();

		// 查询税费接口
		QysdsSet qysdsSet = null;

		// 数据库连接对象
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		// 减免资格标识
		boolean jm_type = false;

		try {
			if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq,
						CodeConstant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
				/* 如果为第四季度，或取企业所得税认定信息时按年报来获取 */

				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* 期号不能为空，如果为空抛出异常 */
					throw new ApplicationException("系统发生异常，期号为空，请与系统管理员联系！");
				}
				if ("4".equals(form.getQh())) {
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
				} else {
					qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq,
							skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
                    //重载Zsfs
                    //Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
                    //qysdsSet.setZsfs(zsfs);
				}
			}

		} catch (com.ttsoft.framework.exception.BaseException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}

		// 1、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS
					: zsfs.getZsfsdm());
		} else {
			// form.setZsfs("");
			// 20070208征收方式如果取出为空则认为是查账征收企业的。
			form.setZsfs(CodeConstant.ZSFSDM_CZZS);
		}

		/* 高新技术企业认定日期 */
		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// 初值
		form.setCyl("0");
		form.setXzqy("0");
		form.setDezsse("0.00");
		form.setYbjmsl("0.00");

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
					form.setJmzg("1"); // 有减免资格
				}
				form.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				form.setDezsse(dezsse);
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
			form.setJmzg("1"); // 有减免资格
		} else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
			// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
			if (qysdsSet.isXzqy()) {
				form.setXzqy("1");
				form.setJmzg("1"); // 有减免资格
			}
		}

		if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// 非乡镇企业的减免情况
			form.setJmzg("1"); // 有减免资格
			DecimalFormat ft = new DecimalFormat("0.00");
			form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		form.setQyzslx(qyzssllx);
		form.setSysl(qyzssl);



		/* 核定信息输出 */
		System.out.println("-------------核定信息--------------");
		System.out.println("企业征收税率类型-" + qyzssllx);
		System.out.println("减免资格-" + form.getJmzg());
		System.out.println("一般减免税率-" + form.getYbjmsl());
		System.out.println("征收方式-" + form.getZsfs());
		System.out.println("纯益率-" + form.getCyl());
		System.out.println("定额-" + form.getDezsse());
		System.out.println("适用税率-" + form.getSysl());
		System.out.println("-------------核定信息--------------");
	}

	/**
	 * 把存放数据时过滤掉的空格复原
	 *
	 * @param table
	 * @param a
	 * @return
	 */
	public static QysdsReportsTableDeclare putSpace(
			QysdsReportsTableDeclare table, int arrs[]) {

		String flag = null;

		if (table.getCellContentList().size() == 0) {
			flag = "0.00";
		} else {
			flag = "";
		}

		System.out.println("**显示qysdsNewUtil中的putSpace()**");

		for (int j = 1; j <= arrs.length; j = j + 2) {
			System.out.println("j___  " + j + "***" + arrs.length);
			for (int i = arrs[j - 1]; i <= arrs[j]; i++) {
				QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
						.getCellContentList().get(String.valueOf(i));
				if (item == null) {
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue(flag);
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				} else if (item != null && item.getItemValue() != null
						&& "".equals(item.getItemValue().trim())) {
					QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
					item1.setItemID(String.valueOf(i));
					item1.setItemValue(flag);
					item1.setItemType("11");
					table.getCellContentList().put(String.valueOf(i), item1);
				}
			}
		}
		return table;
	}

	/**
	 * 减免操作
	 *
	 * @param jmje
	 *            减免金额
	 * @param form
	 *            申报信息
	 * @param dbAgent
	 *            数据库连接
	 * @param ud
	 *            操作员信息
	 * @param djsj
	 *            登记信息
	 * @param ts_cjrq
	 *            创建日起
	 * @param ts_lrrq
	 *            录入日起
	 * @param ts_sbrq
	 *            申报日期
	 * @param ts_Skssjsrq
	 *            税款所属结束日期
	 * @param ts_Skssksrq
	 *            税款所属开始日期
	 * @param nd
	 *            年度
	 */
//	private void insertJmJb(String jmje, hdzssdsnbForm form,
//			SfDBAccess dbAgent, UserData ud, SWDJJBSJ djsj, Timestamp ts_cjrq,
//			Timestamp ts_lrrq, Timestamp ts_sbrq, Timestamp ts_Skssjsrq,
//			Timestamp ts_Skssksrq, String nd) {
//		Connection con = null;
//		CallableStatement st = null;
//		String sql = "";
//
//		Timestamp t1, t2;
//
//		t1 = new Timestamp(System.currentTimeMillis());
//
//		try {
//			// 当企业所得税季报和年报的减免字段有值且纳税人有减免资格的时候，要往减免申报表插入数据：
//			// 控制修改：
//			// 如果本征期已经插入过数据了，且数据记账标识为未记账，则要修改减免申报表的减免金额字段；
//			// 如果本征期已经插入过数据了，且数据记账标识为已记账，则不用再插入数据；
//			// 如果本征期没有插入过数据，则插入一条数据；
//			//
//			// 且网上减免税申报的时候控制不可录入企业所得税的减免数据，即在申报的税种税目下过滤掉企业所得税的税种税目；
//			// 当页面数据的减免数额不为空或0并且具备一般减免资格的进入
//			// if (!"0".equals(jmje) && !"".equals(jmje) && form.getYbjmsl() !=
//			// null &&
//			// !form.getYbjmsl().equals(""))
//			// {
//			if ((jmje == null) || ("0.00".equals(jmje)) || ("".equals(jmje))) {
//				jmje="0";
//			}
//				Jm jm = new Jm();
//				// 减免值对象插入
//				jm.setCjrq(ts_cjrq);
//				jm.setJsjdm(form.getJsjdm());
//				jm.setJmlx(CodeConstant.JMLX_SP);
//				jm.setSzsmdm(CodeConstant.SZSM_QYSDSCODE);
//				jm.setSbrq(ts_sbrq);
//				jm.setLrrq(ts_lrrq);
//				jm.setFsdm(CodeConstant.FSDM_SMSB);
//				jm.setJzbz(CodeConstant.SMSB_JZBZ);
//				jm.setJmse(new BigDecimal(jmje));
//				jm.setJsje(new BigDecimal(jmje));
//				jm.setLrr(ud.getYhid());
//				jm.setSkssjsrq(ts_Skssjsrq);
//				jm.setSkssksrq(ts_Skssksrq);
//				jm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
//				// jm.setQxdm(InterfaceDj.getQxdm(ud));
//				jm.setQxdm(djsj.getSwjgzzjgdm().substring(0, 2));
//				jm.setDjzclxdm(djsj.getDjzclxdm());
//				jm.setGjbzhydm(djsj.getGjbzhydm());
//				jm.setNd(nd);
//
//				Date date = TinyTools.stringToDate(form.getSbrq(), "yyyyMMdd");
//				// 减免类别代码
//				ServiceProxy proxy = new ServiceProxy();
//				String jmxmdm = proxy.getJmsbs(form.getJsjdm(),
//						CodeConstant.SZSM_QYSDS, ts_Skssksrq, ts_Skssjsrq);
//				Debug.out("减免类别代码 =" + jmxmdm);
//				System.out.println("减免类别代码 ="+jmxmdm);
//				// 减免项目代码为空，从表单中取得企业的征收类型判断减免方式
//				if (jmxmdm == null) {
//
//					System.out
//							.println("**************   取得的减免项目代码为空，上门申报的季报表中putJm中的企业征收类型form.getQyzslx()为:"
//									+ form.getQyzslx());
//
//					jmxmdm = CodeConstant.JMLXOTHER;
//					jm.setJmlx(CodeConstant.JMLX_FD);//法定性减免类型
//
//
//				}
//
//				if (jmxmdm != null && !("".equals(jmxmdm))) {
//
//					jm.setJmxmdm(jmxmdm);
//					// 取得预算级次代码
//					Ysjc ysjc = null;
//					try {
//						ysjc = JksUtil.getYsjc(form.getJsjdm(),
//								CodeConstant.SZSM_QYSDS, SfDateUtil
//										.getDate(form.getSbrq()));
//					} catch (Exception e) {
//						throw new ApplicationException("该计算机代码得预算级次代码没有纪录！");
//					}
//					if (ysjc != null) {
//						Debug.out("级次 =" + ysjc.getYsjcdm());
//					} else {
//						throw new ApplicationException("该计算机代码的预算级次代码没有纪录！");
//					}
//					// 取得预算科目代码
//					com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = null;
//					try {
//						yskm = JKAdapter.getInstance().getYskm(
//								CodeConstant.SZSM_QYSDSCODE,
//								djsj.getDjzclxdm(), djsj.getGjbzhydm(),
//								ysjc.getYsjcdm());
//					} catch (Exception e) {
//						throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
//					}
//					if (yskm != null) {
//						Debug.out("预算科目 =" + yskm.getYskmdm());
//					} else {
//						throw new ApplicationException("该计算机代码的预算科目代码没有纪录！");
//					}
//
//					jm.setYsjcdm(ysjc.getYsjcdm());
//					jm.setYskmdm(yskm.getYskmdm());
//
//					try {
//
//						String jsjdm = jm.getJsjdm();
//						String jmlx = jm.getJmlx();
//						String szsmdm = jm.getSzsmdm();
//						// String sbrq = df.format(now);
//						Timestamp sbrq = jm.getSbrq();
//						String fsdm = jm.getFsdm();
//						String jzbz = jm.getJzbz();
//						String lrr = jm.getLrr();
//						// String skssjsrq = df.format(declare.getSkssjsrq());
//						// String skssksrq = df.format(declare.getSkssksrq());
//						Timestamp skssjsrq = jm.getSkssjsrq();
//						Timestamp skssksrq = jm.getSkssksrq();
//						String swjgzzjgdm = jm.getSwjgzzjgdm();
//						String qxdm = jm.getQxdm();
//						String djzclxdm = jm.getDjzclxdm();
//						String gjbzhydm = jm.getGjbzhydm();
//						// String nd = jm.getNd();
//						String ysjcdm = jm.getYsjcdm();
//						String yskmdm = jm.getYskmdm();
//						// String jmxmdm = jm.getJmxmdm();
//						BigDecimal jmse = jm.getJmse();
//
//						// 创建数据库连接
//						con = SfDBResource.getConnection();
//						sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";
//
//						st = con.prepareCall(sql);
//
//						System.out
//								.println("-------------调用插入减免申报存储过程参数----------------");
//						System.out.println("1-jsjdm--" + jsjdm);
//						System.out.println("2-jmlx--" + jmlx);
//						System.out.println("3-szsmdm--" + szsmdm);
//						System.out.println("4-sbrq--" + sbrq);
//						System.out.println("5-fsdm--" + fsdm);
//						System.out.println("6-jzbz--" + jzbz);
//						System.out.println("7-lrr--" + lrr);
//						System.out.println("8-skssjsrq--" + skssjsrq);
//						System.out.println("9-skssksrq--" + skssksrq);
//						System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
//						System.out.println("11-qxdm--" + qxdm);
//						System.out.println("12-djzclxdm--" + djzclxdm);
//						System.out.println("13-gjbzhydm--" + gjbzhydm);
//						System.out.println("14-nd--" + nd);
//						System.out.println("15-ysjcdm--" + ysjcdm);
//						System.out.println("16-yskmdm--" + yskmdm);
//						System.out.println("17-jmxmdm--" + jmxmdm);
//						System.out.println("18-jmse--" + jmse);
//
//						st.setString(1, jsjdm);
//						st.setString(2, jmlx);
//						st.setString(3, szsmdm);
//						st.setTimestamp(4, sbrq);
//						st.setString(5, fsdm);
//
//						st.setString(6, jzbz);
//						st.setString(7, lrr);
//						st.setTimestamp(8, skssjsrq);
//						st.setTimestamp(9, skssksrq);
//						st.setString(10, swjgzzjgdm);
//
//						st.setString(11, qxdm);
//						st.setString(12, djzclxdm);
//						st.setString(13, gjbzhydm);
//						st.setString(14, nd);
//						st.setString(15, ysjcdm);
//
//						st.setString(16, yskmdm);
//						st.setString(17, jmxmdm);
//						st.setBigDecimal(18, jmse);
//
//						st.execute();
//
//					} catch (Exception ex4) {
//						throw new ApplicationException(
//								"插入减免申报表失败，数据库操作失败，请您找管理员联系！");
//					}
//
//				}
//
//
//		} catch (Exception ex) {
//			// 抛出异常
//			ex.printStackTrace();
//		} finally {
//			SfDBResource.freeConnection(con);
//		}
//
//		t2 = new Timestamp(System.currentTimeMillis());
//
//		System.out.println("插入减免申报表数据耗时：" + (t2.getTime() - t1.getTime()));
//
//	}
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

		// 得到Action传递过来hdzssdsnbForm对象
		HdzssdsnbForm hdzssdsnbForm = (HdzssdsnbForm) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			/* 征收方式 */
			String zsfs = hdzssdsnbForm.getZsfs();
			// 将ActionForm中的基本信息转入QysdsReportsDeclare对象中
			QysdsReportsDeclare report = this.translate2Interface(
					hdzssdsnbForm, zsfs);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// 调用deleteSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);

			// 获取一个具有空值的QysdsReportsTableDeclare对象
			QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report
					.getTableContentList().get(
							QysdsConstant2014.TABLE_ID_2014_24);
			table.getCellContentList().clear();

			// 将数据库中的数据翻译成页面所需数据格式
			int[] arrs = { 1, 15 };
			hdzssdsnbForm.setQysdsnbList(null);

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 删除成功返回hdzssdsnbForm
		return hdzssdsnbForm;
	}
	
}
