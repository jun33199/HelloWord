package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxba.web.QyqssdsWxBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdswxbagl.web.QyqssdsWxBaglForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsWxBaglProcessor implements Processor {
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
		case CodeConstant.SMSB_ADDACTION:
			result = doAdd(vo);
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

		QyqssdsWxBaglForm form = (QyqssdsWxBaglForm) vo.getData();
		UserData ud = vo.getUserData();
		Connection conn = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = SfDBResource.getConnection();

			list = new ArrayList();
			String sql = "select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm = ud.getSsdwdm();
			String yhjb = ud.getYhjb();

			if (yhjb.equals("50")) {
				sql += " and ccbs='1' ";
			}
			if (yhjb.equals("40")) {
				sql += " and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"
						+ ssdwdm.substring(0, 2) + "%'";
			}
			if (yhjb.equals("30")) {
				sql += " and swjgzzjgdm ='" + ssdwdm + "'";
			}
			sql += " order by swjgzzjgdm";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String swjgzzjgdm = rs.getString("SWJGZZJGDM");
				String swjgzzjgmc = rs.getString("SWJGZZJGMC");
				DmVo dmvo = new DmVo();
				dmvo.setDm(swjgzzjgdm);
				dmvo.setMc(swjgzzjgmc);
				list.add(dmvo);
			}
			form.setFilter_zgswjgList(list);
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

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

		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) vo.getData();
		UserData ud = vo.getUserData();
		String ssdwdm = ud.getSsdwdm();
		String yhjb = ud.getYhjb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			String check = "<a href=\"javascript:doOperate('''||a.jsjdm||''',''"
					+ CodeConstant.QYQSSDSBAGL_CZLX_CHECK + "'')\">审核</a>";
			String view = "<a href=\"javascript:doView('''||a.jsjdm||''')\">查看</a>";
			String delete = "<a href=\"javascript:doDelete('''||a.jsjdm||''')\">删除</a>";
			conn = SfDBResource.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.jsjdm || decode(c.yhdllx, '02', '(证书用户)', '') jsjdm, ");
			sb.append(" a.nsrmc nsrmc,  ");
			sb.append(" d.swjgzzjgmc swjgzzjgmc, ");
			sb.append(" decode(a.sfwxjxba,'0','是','1','否') sfwxjxba, ");
			sb.append(" '"+view+"&nbsp;"+delete+"' cz ");
			sb.append(" from  SBDB.SB_JL_QYQSSDS_WXBADJB a ,djdb.dj_jl_jbsj b,aqdb.zk_jl_wsyh c ,dmdb.gy_dm_swjgzzjg d ");
			sb.append(" where a.jsjdm=b.jsjdm and a.jsjdm=c.yhid  and a.swjgzzjgdm=d.swjgzzjgdm ");

			String jsjdm = qyqssdsWxBaglForm.getFilter_jsjdm();
			String nsrmc = qyqssdsWxBaglForm.getFilter_nsrmc();
			String zgswjg = qyqssdsWxBaglForm.getFilter_zgswjg();

			if (jsjdm != null && jsjdm.trim().length() > 0) {
				sb.append(" and a.jsjdm='" + jsjdm + "' ");
			}
			if (nsrmc != null && nsrmc.trim().length() > 0) {
				sb.append(" and b.nsrmc like '%" + nsrmc + "%' ");
			}
			if (yhjb.equals("50")) {
				if (zgswjg != null && zgswjg.trim().length() > 0)
					sb.append(" and a.swjgzzjgdm like '"
							+ zgswjg.substring(0, 2) + "%' ");
			}
			if (yhjb.equals("40")) {

				if (zgswjg != null && zgswjg.trim().length() > 0)
					sb.append(" and a.swjgzzjgdm = '" + zgswjg + "' ");
				else
					sb.append(" and a.swjgzzjgdm like '"
							+ ssdwdm.substring(0, 2) + "%' ");
			}
			if (yhjb.equals("30")) {
				sb.append(" and a.swjgzzjgdm = '" + ssdwdm + "' ");
			}

			System.out.println(sb.toString());

			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				Map map = new HashMap();
				map.put("COL_1", rs.getString("JSJDM"));
				map.put("COL_2", rs.getString("NSRMC"));
				map.put("COL_3", rs.getString("SWJGZZJGMC"));
				map.put("COL_4", rs.getString("sfwxjxba"));
				map.put("COL_5", rs.getString("CZ"));
				list.add(map);
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return list;
	}

	private Object doAdd(VOPackage vo) throws BaseException {

		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String showSql = "select JSJDM from  SBDB.SB_JL_QYQSSDS_WXBADJB  where jsjdm=?";
			ps = conn.prepareStatement(showSql);
			ps.setString(1, qyqssdsWxBaglForm.getFilter_jsjdm());
			rs = ps.executeQuery();
			if (rs.next()) {
				qyqssdsWxBaglForm.setIsExistedBa(true);
				return qyqssdsWxBaglForm;

			} else {

				qyqssdsWxBaglForm.setIsExistedBa(false);
				return qyqssdsWxBaglForm;
			}

		} catch (Exception ex) {
			// 抛出异常
			System.out.println("catch exception  .............." + ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

	}

	private Object doDelete(VOPackage vo) throws BaseException {
		QyqssdsWxBaglForm qyqssdsWxBaglForm = (QyqssdsWxBaglForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String jsjdm = qyqssdsWxBaglForm.getJsjdm();
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsWxBaglForm);

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
			String Sql = "delete  from  SBDB.SB_JL_QYQSSDS_WXBADJB  where jsjdm=?";
			ps = conn.prepareStatement(Sql);
			ps.setString(1, jsjdm);
			// 插入历史数据
			stmt = conn.createStatement();
			StringBuffer bf = new StringBuffer();
			bf.delete(0, bf.length());
			/*---修改历史表中录入人为当前操作人，修改备案审核状态标识为5（删除）---*/
			/*--modified by huohb 2014-06-18--*/
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
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");
			System.out.println("企业清算所得税-基本信息插入历史数据SQL...............................................................");
			System.out.println(bf.toString());
			stmt.executeUpdate(bf.toString());
			rs = ps.executeQuery();

		} catch (Exception ex) {
			// 抛出异常
			System.out.println("catch exception  .............." + ex);
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			try {
				if (ps != null) {			
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsWxBaglForm;
	}

}
