package com.ttsoft.bjtax.shenbao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.syax.frame.exception.BaseException; //import com.syax.sjsh.util.SwjcDBUtil;
import com.ttsoft.bjtax.shenbao.gzsx.web.GzsxForm;
import com.ttsoft.bjtax.shenbao.util.DBResource;

//import com.syax.sjsh.ydsz.web.YdszActionForm;

public class GzsxDbAccess {
	public GzsxDbAccess() {

	}

	/**
	 * 获得纳税人反馈
	 */
	public static boolean getNsrfk(GzsxForm theForm) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		Statement cstmt = null;
		try {
			sql = "select a.fknr,a.ydsj from 数据反馈表 a where jsjdm ='"
					+ theForm.getJsjdm() + "',and gzsx_id='"
					+ theForm.getGzsx_id() + "'";
			con = DBResource.getConnection();
			cstmt = con.createStatement();
			rs = cstmt.executeQuery(sql);
			while (rs.next()) {
				// 反馈内容和阅读时间至少有一项
				if (rs.getString(1) == null)
					return false;
				theForm.setNsrfk(rs.getString(1));
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}
	}

	/**
	 * 获得纳税人阅读
	 */
	public static boolean getNsryd(GzsxForm theForm) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		Statement cstmt = null;
		try {
			sql = "select a.ydsj,  from 数据反馈表 a where jsjdm ='"
					+ theForm.getJsjdm() + "',and gzsx_id='"
					+ theForm.getGzsx_id() + "'";
			con = DBResource.getConnection();
			cstmt = con.createStatement();
			rs = cstmt.executeQuery(sql);
			while (rs.next()) {
				theForm.setNsrfk(rs.getString(1));
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}
	}

	/**
	 * 保存纳税人阅读标示
	 */
	public static boolean SaveNsryd(GzsxForm theForm) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			sql = "insert into  数据反馈表(jsjsdm,gzsx_id,nsrmc,ydsj) values(?,?,?,?)";
			con = DBResource.getConnection();
			cstmt = con.prepareStatement(sql);
			cstmt.setString(1, theForm.getJsjdm());
			cstmt.setString(2, theForm.getGzsx_id());
			cstmt.setString(3, theForm.getNsrmc());
			cstmt.setString(4, theForm.getYdsj());
			cstmt.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}
	}

	/**
	 * 修改纳税人阅读标示
	 */
	public static boolean UpdateNsryd(GzsxForm theForm) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			sql = "update 数据反馈表 a set a.ydsj where a.jsjdm='"
					+ theForm.getJsjdm() + "',and gzsx_id='"
					+ theForm.getGzsx_id() + "'";
			con = DBResource.getConnection();
			cstmt = con.prepareStatement(sql);
			cstmt.setString(1, theForm.getYdsj());
			cstmt.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}
	}

	/**
	 * 保存或修改纳税人反馈
	 */
	public static boolean SaveOrUpdateNsrfk(GzsxForm theForm) throws Exception {
		Connection con = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			if (theForm.getSavetype() == "update") {
				sql = "update 数据反馈表 a set a.fknr=?where a.jsjdm='"
						+ theForm.getJsjdm() + "',and gzsx_id='"
						+ theForm.getGzsx_id() + "'";

				con = DBResource.getConnection();
				cstmt = con.prepareStatement(sql);
				cstmt.setString(1, theForm.getNsrfk());
				cstmt.execute();
				return true;
			} else {
				sql = "insert into  数据反馈表(jsjsdm,gzsx_id,nsrmc,fknr,ydsj) values(?,?,?,?,?)";
				con = DBResource.getConnection();
				cstmt = con.prepareStatement(sql);
				cstmt.setString(1, theForm.getJsjdm());
				cstmt.setString(2, theForm.getGzsx_id());
				cstmt.setString(3, theForm.getNsrmc());
				cstmt.setString(4, theForm.getNsrfk());
				cstmt.setString(5, theForm.getYdsj());
				cstmt.execute();
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}
	}
}
