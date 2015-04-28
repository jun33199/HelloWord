package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsWxBaProcessor implements Processor {

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
		case 7:
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

		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		qyqssdsWxBaForm.setCjr(ud.yhid);
		qyqssdsWxBaForm.setLrr(ud.yhid);
		// qyqssdsWxBaForm.setBaShztMessage("请输入计算机代码进行查询！");
		return qyqssdsWxBaForm;
	}

	/**
	 * 根据计算机代码查询备案信息
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 * @throws com.syax.creports.exception.BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {

		QyqssdsWxBaForm requestForm = (QyqssdsWxBaForm) vo.getData();
		QyqssdsWxBaForm form = new QyqssdsWxBaForm();

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(requestForm.getJsjdm(), ud);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		// 进行数据权限过滤
		QyqssdsUtil2014.getAlertStrWhenAdd(requestForm.getJsjdm(), ud);
		// 计算机代码
		form.setJsjdm(requestForm.getJsjdm());
		// 纳税人名称
		form.setNsrmc(djsj.getNsrmc());
		// 纳税人识别号
		form.setNsrsbh(djsj.getSwdjzh());
		//form.setLxdh(djsj.getZcdzlxdh());
		/*-以下几项默认是选择否-*/
		
		form.setCzlx(requestForm.getCzlx());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = "SELECT * FROM  SBDB.SB_JL_QYQSSDS_WXBADJB WHERE JSJDM='"
					+ requestForm.getJsjdm() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// 计算机代码
				form.setJsjdm(rs.getString("JSJDM") == null ? "" : rs
						.getString("JSJDM"));
				// 纳税人识别号
				form.setNsrsbh(rs.getString("NSRSBH") == null ? "" : rs
						.getString("NSRSBH"));
				// 纳税人名称
				form.setNsrmc(rs.getString("NSRMC") == null ? "" : rs
						.getString("NSRMC"));
				// 填表日期
				form.setQsbaksrq(rs.getString("TBRQ").substring(0, 10) == null ? ""
						: rs.getString("TBRQ").substring(0, 10));
				form.setSfwxjxba(rs.getString("SFWXJXBA") == null ? "" : rs
						.getString("SFWXJXBA"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	/**
	 * 保存备案信息
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		QyqssdsWxBaForm form = (QyqssdsWxBaForm) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		SWDJJBSJ djsj = null;
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		Connection conn = null;
		int count = 0;
		try {			
			conn = SfDBResource.getConnection();
			StringBuffer bf = new StringBuffer();
			/**
			 * 插入历史表
			 */
			Statement stmt = conn.createStatement();
			bf.delete(0, bf.length());
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDS_WXBADJB_HIS ")
					.append("(xh,jsjdm,nsrsbh,nsrmc,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
							+ "TBRQ) ")
					.append(" (select ").append(TinyTools.getXh(form.getJsjdm()))
					.append(" ,jsjdm,nsrsbh,nsrmc,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
							+ "TBRQ ")
					.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
					.append(" where  t1.jsjdm = ")
					.append(SBStringUtils.getSQLStr(form.getJsjdm())).append(")");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());

			stmt.executeUpdate(bf.toString());
			if(stmt!=null){
				stmt.close();
			}
			
			/**
			 * 删除数据
			 */

			Statement st = conn.createStatement();

			bf.delete(0, bf.length());
			bf.append(" delete ")
					.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
					.append(" where  t1.jsjdm ='").append(form.getJsjdm())
					.append("'");

			System.out.println("企业所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());


			if (st != null) {
				st.close();
			}

			// 插入历史数据
			String sql = "insert into  SBDB.SB_JL_QYQSSDS_WXBADJB(jsjdm,"
					+ "nsrsbh,"
					+ "nsrmc,"
					+ "SSJJLX,"
					+ "SSJJLXMC,"
					+ "LXDH,"
					+ "JYDZ,"
					+ "SSHY,"
					+ "SSHYMC,"
					+ "SWJGZZJGDM,"
					+ "SWJGZZJGMC,"
					+ "SFWXJXBA,"
					+ "CJR,"
					+ "CJRQ,"
					+ "LRR,"
					+ "LRRQ,"
					+ "TBRQ"					
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,sysdate)";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, form.getJsjdm());// 计算机代码
			pst.setString(i++, djsj.getSwdjzh());// 纳税人识别号
			pst.setString(i++, djsj.getNsrmc());// 纳税人名称
			pst.setString(i++, djsj.getDjzclxdm());// 所属经济类型
			pst.setString(i++, djsj.getDjzclxmc());// 所属经济类型名称
			pst.setString(i++, djsj.getZcdzlxdh());// 联系电话
			pst.setString(i++, djsj.getJydz());// 经营地址
			pst.setString(i++, djsj.getGjbzhydm());// 所属行业
			pst.setString(i++, djsj.getGjbzhymc());// 所属行业名称
			pst.setString(i++, djsj.getSwjgzzjgdm());// 税务机关组织机构代码
			pst.setString(i++, djsj.getSwjgzzjgmc());// 税务机关组织机构名称
			pst.setString(i++, form.getSfwxjxba());// 联系电话
			pst.setString(i++, ud.yhid);// 创建人
			pst.setString(i++, ud.yhid);// 录入人
			
			System.out.println(sql);
			count = pst.executeUpdate();
			if (pst != null) {
				pst.close();
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
	 * doDelete删除
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsWxBaForm qyqssdsWxBaForm = (QyqssdsWxBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		Statement stmt = null;
		Statement st = null;
		conn = SfDBResource.getConnection();
		// 获取计算机代码
		String jsjdm = qyqssdsWxBaForm.getJsjdm();			
		/**
		 * 删除数据
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "DELETE FROM  SBDB.SB_JL_QYQSSDS_WXBADJB  WHERE JSJDM='"
				+ jsjdm+"'";
		try {

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsWxBaForm);

			// 循环删除客户端填写的所有表，外加新添加的分配表，
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// 企业所得税报表内单表申明对象
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
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
			/*---修改历史表中录入人为当前操作人，修改备案审核状态标识为6（作废）---*/
			/*--modified by huohb 2014-06-18--*/
			// 插入历史数据
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDS_WXBADJB_HIS ")
			.append("(xh,jsjdm,nsrsbh,nsrmc,SSJJLX,SSJJLXMC,LXDH,"
					+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
					+ "TBRQ) ")
			.append(" (select ").append(TinyTools.getXh(jsjdm))
			.append(" ,jsjdm,nsrsbh,nsrmc,SSJJLX,"
					+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
					+ "SWJGZZJGMC,SFWXJXBA,CJR,CJRQ,LRR,LRRQ,"
					+ "TBRQ ")
			.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB t1 ")
			.append(" where  t1.jsjdm = ")
			.append(SBStringUtils.getSQLStr(jsjdm)).append(")");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());
			/*--先插入历史表再删除数据--*/
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
		return qyqssdsWxBaForm;
	}
}
