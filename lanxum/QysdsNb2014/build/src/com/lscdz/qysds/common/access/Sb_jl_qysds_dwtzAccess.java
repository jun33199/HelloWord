package com.lscdz.qysds.common.access;

/**
 * Created by CodeGenerator at Tue Jan 06 09:22:45 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_DWTZ
 * Comments: 
 * Any question, ask author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.util.*;

import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;

import yangjian.frame.util.*;

public class Sb_jl_qysds_dwtzAccess
{
	public static void insertRecord(Connection con, List<sb_jl_qysds_dwtz> list) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_DWTZ(DWTZQYID, ID, BTZZMC, CJR, CJRQ, JJXZ, LRR, LRRQ, NSRSBH, SWJGZZJGDM, SWJGZZJGMC, TZBL, TZJE, ZCDZ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int i, iSize = list.size();
		sb_jl_qysds_dwtz vo;

		try
		{
			st = con.prepareStatement(buf);
			for (i = 0;i < iSize;i++)
			{
				vo = list.get(i);
				st.setString(1, vo.getDwtzqyid());
				st.setString(2, vo.getId());
				st.setString(3, vo.getBtzzmc());
				st.setString(4, vo.getCjr());
				st.setTimestamp(5, vo.getCjrq());
				st.setString(6, vo.getJjxz());
				st.setString(7, vo.getLrr());
				st.setTimestamp(8, vo.getLrrq());
				st.setString(9, vo.getNsrsbh());
				st.setString(10, vo.getSwjgzzjgdm());
				st.setString(11, vo.getSwjgzzjgmc());
				st.setBigDecimal(12, vo.getTzbl());
				st.setBigDecimal(13, vo.getTzje());
				st.setString(14, vo.getZcdz());

				st.execute();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try
			{
				if (st != null) st.close();
			}
			catch(Exception exx)
			{
			}
		}
	}
	public static void insertRecord(Connection con, sb_jl_qysds_dwtz vo) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_DWTZ(DWTZQYID, ID, BTZZMC, CJR, CJRQ, JJXZ, LRR, LRRQ, NSRSBH, SWJGZZJGDM, SWJGZZJGMC, TZBL, TZJE, ZCDZ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getDwtzqyid());
			st.setString(2, vo.getId());
			st.setString(3, vo.getBtzzmc());
			st.setString(4, vo.getCjr());
			st.setTimestamp(5, vo.getCjrq());
			st.setString(6, vo.getJjxz());
			st.setString(7, vo.getLrr());
			st.setTimestamp(8, vo.getLrrq());
			st.setString(9, vo.getNsrsbh());
			st.setString(10, vo.getSwjgzzjgdm());
			st.setString(11, vo.getSwjgzzjgmc());
			st.setBigDecimal(12, vo.getTzbl());
			st.setBigDecimal(13, vo.getTzje());
			st.setString(14, vo.getZcdz());

			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try
			{
				if (st != null) st.close();
			}
			catch(Exception exx)
			{
			}
		}
	}
	public static void updateRecord(Connection con, sb_jl_qysds_dwtz vo, String sqlWhere) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "UPDATE SBDB.SB_JL_QYSDS_DWTZ SET DWTZQYID = ?, ID = ?, BTZZMC = ?, CJR = ?, CJRQ = ?, JJXZ = ?, LRR = ?, LRRQ = ?, NSRSBH = ?, SWJGZZJGDM = ?, SWJGZZJGMC = ?, TZBL = ?, TZJE = ?, ZCDZ = ?  " + sqlWhere;

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getDwtzqyid());
			st.setString(2, vo.getId());
			st.setString(3, vo.getBtzzmc());
			st.setString(4, vo.getCjr());
			st.setTimestamp(5, vo.getCjrq());
			st.setString(6, vo.getJjxz());
			st.setString(7, vo.getLrr());
			st.setTimestamp(8, vo.getLrrq());
			st.setString(9, vo.getNsrsbh());
			st.setString(10, vo.getSwjgzzjgdm());
			st.setString(11, vo.getSwjgzzjgmc());
			st.setBigDecimal(12, vo.getTzbl());
			st.setBigDecimal(13, vo.getTzje());
			st.setString(14, vo.getZcdz());

			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try
			{
				if (st != null) st.close();
			}
			catch(Exception exx)
			{
			}
		}
	}
	public static void deleteRecord(Connection con, String sqlWhere) throws FrameException
	{
		Statement st = null;
		String buf = "DELETE FROM SBDB.SB_JL_QYSDS_DWTZ " + sqlWhere;

		try
		{
			st = con.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try
			{
				if (st != null) st.close();
			}
			catch(Exception exx)
			{
			}
		}
	}
	public static int getRecordsCount(String sqlWhere) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String buf = null;

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT NVL(COUNT(*), 0) VAL FROM SBDB.SB_JL_QYSDS_DWTZ " + sqlWhere;
			rs = st.executeQuery(buf);

			if(!rs.next()) return 0;
			return rs.getInt("VAL");
		}
		catch(FrameException e)
		{
			throw e;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_dwtz> readRecords(String sqlWhere) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_dwtz vo = null;
		String buf = null;
		List<sb_jl_qysds_dwtz> ar = new ArrayList<sb_jl_qysds_dwtz>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_DWTZ " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new sb_jl_qysds_dwtz();
				vo.setDwtzqyid(rs.getString("DWTZQYID"));
				vo.setId(rs.getString("ID"));
				vo.setBtzzmc(rs.getString("BTZZMC"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setJjxz(rs.getString("JJXZ"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setNsrsbh(rs.getString("NSRSBH"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setTzbl(rs.getBigDecimal("TZBL"));
				vo.setTzje(rs.getBigDecimal("TZJE"));
				vo.setZcdz(rs.getString("ZCDZ"));

				ar.add(vo);
			}
			return ar;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_dwtz> readRecords(String sqlWhere, int pageSize, int page) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_dwtz vo = null;
		String buf = null;
		List<sb_jl_qysds_dwtz> ar = new ArrayList<sb_jl_qysds_dwtz>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_DWTZ " + sqlWhere;
			rs = st.executeQuery(buf);

			int iCount = 0;
			int iSize = pageSize * (page - 1);
			while(rs.next())
			{
				if (iCount++ < iSize) continue;
				if (iCount > (iSize + pageSize)) break;

				vo = new sb_jl_qysds_dwtz();
				vo.setDwtzqyid(rs.getString("DWTZQYID"));
				vo.setId(rs.getString("ID"));
				vo.setBtzzmc(rs.getString("BTZZMC"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setJjxz(rs.getString("JJXZ"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setNsrsbh(rs.getString("NSRSBH"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setTzbl(rs.getBigDecimal("TZBL"));
				vo.setTzje(rs.getBigDecimal("TZJE"));
				vo.setZcdz(rs.getString("ZCDZ"));

				ar.add(vo);
			}
			return ar;
		}
		catch(FrameException e)
		{
			throw e;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_DWTZ");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
}
