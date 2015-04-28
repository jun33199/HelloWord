package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * 项目名称：smsb 类名称：QyqssdsMainProcessor2014 类描述： 企业所得税清算申报　上门模块 创建人：wangcy
 * 创建时间：2014-2-14 下午4:08:41 修改人：wangcy 修改时间：2014-2-14 下午4:08:41 修改备注：
 * 
 * @version 1.0
 */
public class QyqssdsMainProcessor2014 implements Processor {

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
		case 10:
			result = doAccept(vo);
			break;
		case 11:
			result = doRefuse(vo);
			break;
		case 12:
			result = doSave(vo);
			break;
		case 13:
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

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
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

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
		initForm(form);

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		System.out.println("================="+form.getJsjdm()+"=================");
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		form.setNsrmc(djsj.getNsrmc());
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();
			
			form = (QyqssdsBaseForm) QyqssdsUtil2014.queryQyqssdsJbxx(conn,form);
			if(form.getSbShztbs().equals("")){
				form.setSbShztMessage("未提交");
			}else if(form.getSbShztbs().equals("1")){
				form.setSbShztMessage("已提交未审核");
			}else if(form.getSbShztbs().equals("2")){
				form.setSbShztMessage("审核已通过");
			}else if(form.getSbShztbs().equals("3")){
				form.setSbShztMessage("审核被驳回");
			}else if(form.getSbShztbs().equals("4")){
				form.setSbShztMessage("撤销");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		

		return form;
	}
	
	/**
	 * doDelete 作废申报信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		//插入历史表statement
		Statement stmt = null;
		//删除数据statement
		Statement st = null;
		conn = SfDBResource.getConnection();
		// 获取计算机代码
		String jsjdm = form.getJsjdm();
		/**
		 * 删除数据
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set SBSHZTBS=null,SBSHTGRQ=null,QSSBKSRQ=null,QSSBJSRQ=null WHERE NSRJSJDM='"
				+ jsjdm+"'";
		try {

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd,form);

			// 循环删除客户端填写的所有表
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// 企业所得税报表内单表申明对象
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				//System.out.println(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.setTableContentList(new HashMap());
				qd.getTableContentList().put(table.getTableId(), table);
				// 调用delete方法进行数据删除
				iApp.deleteSingleTable(qd);
				iApp.updateCheckStatus(qd, "");
			}
			stmt = conn.createStatement();
			bf.delete(0, bf.length());
			/*--修改历史表中存入的录入人为当前人，申报审核状态标识为6（作废）--*/
			/*--modified by huohb 2014-06-18--*/
			// 插入历史数据
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
					.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
							+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(jsjdm))
					.append(",")
					.append(" nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,CJR,CJSJ,'"+vo.getUserData().getYhid()+"',LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,"
							+ "BASHTGRQ,'6',SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());
			/*--先插入历史表再执行删除数据--*/
			stmt.executeUpdate(bf.toString());
			// 删除数据
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	
	/**
	 * doSave 保存申报信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doSave(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();
		StringBuffer sql = new StringBuffer();
//		sql.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set qssbksrq=to_date(?,'YYYY-MM-DD'),qssbjsrq=to_date(?,'YYYY-MM-DD') where nsrjsjdm=?");
		Connection conn = null;
		//PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
//			ps = conn.prepareStatement(sql.toString());
//			ps.setString(1, form.getQssbksrq());//清算申报开始日期
//			ps.setString(2, form.getQssbjsrq());//清算申报结束日期
//			ps.setString(3, form.getJsjdm());//计算机代码
//			int count =ps.executeUpdate();
			QyqssdsUtil2014.updateAllDate(conn, form);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
	/**
	 * doAccept 接受申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doAccept(VOPackage vo) throws BaseException {

		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();
		StringBuffer sql = new StringBuffer();
		sql.append("update SBDB.SB_JL_QYQSSDSBA_NSRJBXXB set SBSHTGRQ=sysdate,SBSHZTBS='2' where nsrjsjdm=?");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
			/*--modified by huohb 2014-06-13--*/
			//调用全表校验
			form=(QyqssdsBaseForm)doCheck(vo);
			//checkList不为空的情况下表示校验没有通过，需要return掉，表示不能审核通过
			if((form!=null)&&(form.getCheckList()==null||form.getCheckList().size()==0)){
				
			}else{
				//return掉，不执行审核通过
				return form;
			}
			
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, form.getJsjdm());
			int count =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
	/**
	 * doRefuse拒绝申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doRefuse(VOPackage vo) throws BaseException {

		QyqssdsBaseForm qyqssdsBaForm = (QyqssdsBaseForm) vo.getData();

		// 获取计算机代码
		String jsjdm = qyqssdsBaForm.getJsjdm();
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET SBSHZTBS='3'  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			int count=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
	}

	private Object doCheck(VOPackage vo) throws BaseException {
		QyqssdsBaseForm form = (QyqssdsBaseForm) vo.getData();
		UserData userData = vo.getUserData();
		Connection conn = null;
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			form.setQh("1");
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			QyqssdsUtil2014.setQyqssdsReport(report, form);
			Debug.out("-----------计算机代码 " + form.getJsjdm()
					+ "全表校验 报表数据信息--------");
			Debug.out("Appid-" + report.getAppid());
			Debug.out("Bbqlx-" + report.getBbqlx());
			Debug.out("Nsrjsjdm-" + report.getNsrjsjdm());
			Debug.out("Nsrmc-" + report.getNsrmc());
			Debug.out("Qh-" + report.getQh());
			Debug.out("Version-" + report.getVersion());

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);

			report = (QyqssdsReportsDeclare) iApp.query(report);
			Debug.out(report.getTableContentList().size());
			Debug.out(report.getTableContentList().size());
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);

			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List checkList = checker.checkMain(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			if (checkList == null) {
				Debug.out("纳税人" + form.getJsjdm() + "企业所得税年度申报数据全表校验通过。");
			} else {
				Debug.out("纳税人" + form.getJsjdm() + "企业所得税年度申报数据 共 "
						+ checkList.size() + " 条公式未审核通过。 ");
			}

			// 全表审核通过
			if (checkList == null
					|| (checkList != null && checkList.size() == 0)) {

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

	private void initForm(QyqssdsBaseForm form) throws BaseException {

		form.setTbrq(SfDateUtil.getDate());
		form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		form.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
		// 清算申报期间
		
		//需人工填写
		form.setQssbksrq(TinyTools.Date2String(new Date(), "yyyy-MM-dd"));
		form.setQssbjsrq(TinyTools.Date2String(new Date(), "yyyy-MM-dd"));
		
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
		Debug.out("------------------------getSbnd---"
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

}