package com.ttsoft.bjtax.smsb.grsds.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.grsds.model.DmObject;
import com.ttsoft.bjtax.smsb.grsds.model.GrVO;
import com.ttsoft.bjtax.smsb.grsds.model.Tzf;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsProcessor implements Processor {

	public Object process(VOPackage vo) throws BaseException {
		switch (vo.getAction()) {
		// 获取投资方列表
		case CodeConstant.SMSB_GRSDS_CXTZF:
			return this.getTzfList();
		case CodeConstant.SMSB_GRSDS_CXZJLX:
			return this.getZjlxList();
		case CodeConstant.SMSB_GRSDS_ADD_TZF:
			return this.addZjl(vo);
		case CodeConstant.SMSB_GRSDS_DELETE_TZF:
			return this.doDelete(vo);
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}
	}
	
	/**
	 * @Description: 删除条目
	 * @param vo
	 * @return 
	 * @throws Exception 
	 */
	private Object doDelete(VOPackage vo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			GrVO grVO = (GrVO)vo.getData();
			String sql ="delete from sbdb.SB_JL_TZZSJ2014 where JSJDM=? and ZJLXDM=? and ZJHM=? ";
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grVO.getJsjdm());
			ps.setString(2, grVO.getGr_sfzjlx());
			ps.setString(3, grVO.getGr_sfzjhm());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 添加投资方
	 * @param vo
	 * @return
	 */
	private Object addZjl(VOPackage vo) {
		Connection conn = null;
		PreparedStatement ps = null;
		UserData ud = vo.getUserData();
		GrVO grVO = (GrVO)vo.getData();
		String sql ="insert into sbdb.SB_JL_TZZSJ2014 (JSJDM ,ZJLXDM ,ZJHM ,TZZXM ,CJR ,CJSJ ,FPBL) values (?,?,?,?,?,sysdate,?)";
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, grVO.getJsjdm());
			ps.setString(2, grVO.getGr_sfzjlx());
			ps.setString(3, grVO.getGr_sfzjhm());
			ps.setString(4, grVO.getGr_tzzxm());
			ps.setString(5, ud.getYhid());
			String fpbl = grVO.getGr_fpbl();
			ps.setBigDecimal(6, new BigDecimal(null==fpbl||"".equals(fpbl)?"0.00":fpbl));
			ps.execute();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 查询投资方列表
	 * @return
	 */
	private List getTzfList() {
		String sql = "select t.*, t.rowid from sbdb.sb_jl_tzzsj2014 t";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List tzfList = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			Tzf tzf = null;
			while(rs.next()) {
				tzf = new Tzf();
				tzf.setCjr(rs.getString("cjr"));
				tzf.setCjsj(rs.getDate("cjsj"));
				tzf.setFpbl(rs.getDouble("fpbl"));
				tzf.setGj(rs.getString("gj"));
				tzf.setJsjdm(rs.getString("jsjdm"));
				tzf.setNsrsbh(rs.getString("nsrsbh"));
				tzf.setTzzxm(rs.getString("tzzxm"));
				tzf.setZjhm(rs.getString("zjhm"));
				tzf.setZjlxdm(rs.getString("zjlxdm"));
				String txztFlag = this.getTxzt(rs.getString("jsjdm"), rs.getString("zjhm"), new SimpleDateFormat("yyyy").format(new Date()), conn);
				if("1".equals(txztFlag)) {
					tzf.setTxzt("已填写完成");
				} else if("0".equals(txztFlag)) {
					tzf.setTxzt("未填写完成");
				}
				tzfList.add(tzf);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}

			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}
		}
		return tzfList;
	}

	
	/**
	 * 查询投资方列表
	 * @return
	 */
	private List getZjlxList() {
		String sql = "select * from DMDB.Gy_Dm_Zjlx";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List zjlxList = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			DmObject dmObj = null;
			while(rs.next()) {
				dmObj = new DmObject();
				dmObj.setDm(rs.getString("zjlxdm"));
				dmObj.setName(rs.getString("zjlxmc"));
				zjlxList.add(dmObj);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}

			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception exx) {
				exx.printStackTrace();
			}
		}
		return zjlxList;
	}
	
	/**
	 * @Description: 获取填写状态 0未完成 1已完成
	 * @param zjlxdm
	 * @param zjhm
	 * @return
	 * @throws SQLException 
	 */
	private String getTxzt(String zjlxdm ,String zjhm ,String nd ,Connection conn) throws SQLException
	{
		String result = "0";
		String sql = "select nb.sfzjhm from SBDB.SB_JL_GRSDS_NDSBB_TZZ nb,sbdb.SB_JL_GRSDS_JCXXB_TZF jcxx where jcxx.jsjdm=nb.jsjdm and jcxx.sfzjlxdm = nb.sfzjlxdm and jcxx.sfzjhm = nb.sfzjhm and jcxx.nd =nb.nd and jcxx.sfzjlxdm =? and jcxx.sfzjhm=? and jcxx.nd=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, zjlxdm);
		ps.setString(2, zjhm);
		ps.setString(3, nd);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			result = "1";
		}
		return result;
	}

}
