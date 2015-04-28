package com.ttsoft.bjtax.shenbao.gzsxfk.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ekernel.db.or.ORManager; //import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
//import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
//import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
//import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.gzsx.web.GzsxForm;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx_fk;
import com.ttsoft.bjtax.shenbao.util.DBResource; //import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm; //import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class GzsxfkProcessor implements Processor {
	public GzsxfkProcessor (){
		
	}
	public Object process(VOPackage vo) throws BaseException {
		try {
			//System.out.println("-----------action---"+vo.getAction());
			if (vo.getAction() == ActionConstant.INT_ACTION_SAVEORUPDATEFKNR) {
				// 查询告知信息
				return doSaveOrUpdateNsrfk(vo);
			} else if (vo.getAction() == ActionConstant.INT_ACTION_YDSJ) {
				// 查询告知信息
				return docznsrydbs(vo);
			} else if (vo.getAction() == ActionConstant.INT_ACTION_FKNRQUERY) {
				//System.out.println("--------33333333333333333333");
				// 查询告知信息
				return dogetNsrfk(vo);
			} else {
				throw ExceptionUtil.getBaseException(new Exception(), "参数不合法!");
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询失败!");
		}
	}

	public Object docznsrydbs(VOPackage vo) throws Exception {
		//System.out.println("baocunshijian来到了processor");
		if (((String) getNsryd((Map) vo.getData())).equals("2"))
			return SaveNsryd((Map) vo.getData());
			return null;
		
	}

	/**
	 * 保存或修改纳税人反馈
	 */
	public Object doSaveOrUpdateNsrfk(VOPackage vo) throws Exception {
		Map map = (Map) vo.getData();
		Connection con = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			if ("update".equals(((String)map.get("savetype")))) {
				sql = "update SBDB.SB_JL_GZFK a set a.fknr=?,a.fksj=? where a.jsjdm='"
						+ (String)map.get("jsjdm") + "' and a.gzsx_id='"
						+ (String)map.get("gzsx_id") + "'";
				//System.out.println("--------updatesql"+sql);
				con = DBResource.getConnection();
				cstmt = con.prepareStatement(sql);
				cstmt.setString(1, (String)map.get("nsrfk"));
				cstmt.setDate(2, (java.sql.Date)map.get("fksj"));
				cstmt.execute();
				return new String("1");
			} else {
				sql = "insert into  SBDB.SB_JL_GZFK (jsjdm,gzsx_id,nsrmc,fknr,ydsj,fksj) values(?,?,?,?,?,?)";
				//System.out.println("--------insetsql"+sql);
				con = DBResource.getConnection();
				cstmt = con.prepareStatement(sql);
				cstmt.setString(1, (String)map.get("jsjdm"));
				cstmt.setString(2, (String)map.get("gzsx_id"));
				cstmt.setString(3, (String)map.get("nsrmc"));
				cstmt.setString(4, (String)map.get("nsrfk"));
				cstmt.setString(5, (String)map.get("ydsj"));
				cstmt.setDate(6, (java.sql.Date)map.get("fksj"));
				cstmt.execute();
				return new String("1");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new String("2");
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

	/**
	 * 获得纳税人反馈
	 */
	public Object dogetNsrfk(VOPackage vo) throws Exception {
		Map map = (Map) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		Statement cstmt = null;
		try {
			//System.out.println("*********************************************");
			sql = "select a.fksj,a.ydsj ,a.fknr from SBDB.SB_JL_GZFK a where a.jsjdm ='"
					+ (String)map.get("jsjdm") + "' and a.gzsx_id='"
					+ (String)map.get("gzsx_id") + "'";
			//System.out.println("--------sql"+sql);
			//System.out.println("*********************************************");
			con = DBResource.getConnection();
			//System.out.println("*********************"+con);
			cstmt = con.createStatement();
			rs = cstmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("*******rs.getString(1)********"+rs.getString(1));
				// 反馈内容和阅读时间至少有一项
				if (rs.getDate(1)== null&&rs.getString(3)==null)
					return new String("2");
				return rs.getString(3);
			}
			return new String("2");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new String("2");
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
	public Object SaveNsryd(Map map) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		try {
			sql = "insert into  SBDB.SB_JL_GZFK (jsjdm,gzsx_id,nsrmc,ydsj,fksj) values(?,?,?,?,?)";
			//System.out.println("--------insertydbssql---"+sql);
			con = DBResource.getConnection();
			cstmt = con.prepareStatement(sql);
			cstmt.setString(1, (String)map.get("jsjdm"));
			cstmt.setString(2, (String)map.get("gzsx_id"));
			cstmt.setString(3, (String)map.get("nsrmc"));
			cstmt.setString(4, (String)map.get("ydsj"));
			cstmt.setDate(5, (Date)map.get("fksj"));
			cstmt.execute();
			return new String("1");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new String("2");
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

//	/**
//	 * 修改纳税人阅读标示
//	 */
//	public Object UpdateNsryd(GzsxForm theForm) throws Exception {
//		Connection con = null;
//		ResultSet rs = null;
//		String sql = "";
//		PreparedStatement cstmt = null;
//		try {
//			sql = "update 数据反馈表 a set a.ydsj where a.jsjdm='"
//					+ theForm.getJsjdm() + "',and gzsx_id='"
//					+ theForm.getGzsx_id() + "'";
//			con = DBResource.getConnection();
//			cstmt = con.prepareStatement(sql);
//			cstmt.setString(1, theForm.getYdsj());
//			cstmt.execute();
//			return new String("1");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return new String("2");
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (Exception exx) {
//			}
//			try {
//				if (cstmt != null) {
//					cstmt.close();
//				}
//			} catch (Exception exx) {
//			}
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (Exception exx) {
//			}
//		}
//	}

	/**
	 * 获得纳税人阅读
	 */
	public Object getNsryd(Map map) throws Exception {
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		Statement cstmt = null;
		try {
			sql = "select a.ydsj from SBDB.SB_JL_GZFK a where a.jsjdm ='"
					+ (String)map.get("jsjdm") + "' and a.gzsx_id='"
					+ (String)map.get("gzsx_id") + "'";
			//System.out.println("--------selecttdbssql--"+sql);
			con = DBResource.getConnection();
			cstmt = con.createStatement();
			rs = cstmt.executeQuery(sql);
			while (rs.next()) {
				//theForm.setNsrfk(rs.getString(1));
				return rs.getString(1);
			}
			return new String("2");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new String("2");
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

}
