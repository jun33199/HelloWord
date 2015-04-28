package com.lscdz.qysds.common.access;

/**
 * Created by CodeGenerator at Tue Jan 06 09:22:39 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_QYZYGD
 * Comments: 
 * Any question, ask author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.util.*;

import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;

import yangjian.frame.util.*;

public class Sb_jl_qysds_qyzygdAccess
{
	public static void insertRecord(Connection con, List<sb_jl_qysds_qyzygd> list) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_QYZYGD(ID, QYZYGDID, CJR, CJRQ, GDMC, GJ, JJXZ, LRR, LRRQ, SWJGZZJGDM, SWJGZZJGMC, TZBL, ZJHM, ZJZL) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int i, iSize = list.size();
		sb_jl_qysds_qyzygd vo;

		try
		{
			st = con.prepareStatement(buf);
			for (i = 0;i < iSize;i++)
			{
				vo = list.get(i);
				st.setString(1, vo.getId());
				st.setString(2, vo.getQyzygdid());
				st.setString(3, vo.getCjr());
				st.setTimestamp(4, vo.getCjrq());
				st.setString(5, vo.getGdmc());
				st.setString(6, vo.getGj());
				st.setString(7, vo.getJjxz());
				st.setString(8, vo.getLrr());
				st.setTimestamp(9, vo.getLrrq());
				st.setString(10, vo.getSwjgzzjgdm());
				st.setString(11, vo.getSwjgzzjgmc());
				st.setBigDecimal(12, vo.getTzbl());
				st.setString(13, vo.getZjhm());
				st.setString(14, vo.getZjzl());

				st.execute();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
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
	public static void insertRecord(Connection con, sb_jl_qysds_qyzygd vo) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_QYZYGD(ID, QYZYGDID, CJR, CJRQ, GDMC, GJ, JJXZ, LRR, LRRQ, SWJGZZJGDM, SWJGZZJGMC, TZBL, ZJHM, ZJZL) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getId());
			st.setString(2, vo.getQyzygdid());
			st.setString(3, vo.getCjr());
			st.setTimestamp(4, vo.getCjrq());
			st.setString(5, vo.getGdmc());
			st.setString(6, vo.getGj());
			st.setString(7, vo.getJjxz());
			st.setString(8, vo.getLrr());
			st.setTimestamp(9, vo.getLrrq());
			st.setString(10, vo.getSwjgzzjgdm());
			st.setString(11, vo.getSwjgzzjgmc());
			st.setBigDecimal(12, vo.getTzbl());
			st.setString(13, vo.getZjhm());
			st.setString(14, vo.getZjzl());

			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
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
	public static void updateRecord(Connection con, sb_jl_qysds_qyzygd vo, String sqlWhere) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "UPDATE SBDB.SB_JL_QYSDS_QYZYGD SET ID = ?, QYZYGDID = ?, CJR = ?, CJRQ = ?, GDMC = ?, GJ = ?, JJXZ = ?, LRR = ?, LRRQ = ?, SWJGZZJGDM = ?, SWJGZZJGMC = ?, TZBL = ?, ZJHM = ?, ZJZL = ?  " + sqlWhere;

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getId());
			st.setString(2, vo.getQyzygdid());
			st.setString(3, vo.getCjr());
			st.setTimestamp(4, vo.getCjrq());
			st.setString(5, vo.getGdmc());
			st.setString(6, vo.getGj());
			st.setString(7, vo.getJjxz());
			st.setString(8, vo.getLrr());
			st.setTimestamp(9, vo.getLrrq());
			st.setString(10, vo.getSwjgzzjgdm());
			st.setString(11, vo.getSwjgzzjgmc());
			st.setBigDecimal(12, vo.getTzbl());
			st.setString(13, vo.getZjhm());
			st.setString(14, vo.getZjzl());

			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
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
		String buf = "DELETE FROM SBDB.SB_JL_QYSDS_QYZYGD " + sqlWhere;

		try
		{
			st = con.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
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
			buf = "SELECT NVL(COUNT(*), 0) VAL FROM SBDB.SB_JL_QYSDS_QYZYGD " + sqlWhere;
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
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_qyzygd> readRecords(String sqlWhere) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_qyzygd vo = null;
		String buf = null;
		List<sb_jl_qysds_qyzygd> ar = new ArrayList<sb_jl_qysds_qyzygd>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_QYZYGD " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new sb_jl_qysds_qyzygd();
				vo.setId(rs.getString("ID"));
				vo.setQyzygdid(rs.getString("QYZYGDID"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setGdmc(rs.getString("GDMC"));
				vo.setGj(rs.getString("GJ"));
				vo.setJjxz(rs.getString("JJXZ"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setTzbl(rs.getBigDecimal("TZBL"));
				vo.setZjhm(rs.getString("ZJHM"));
				vo.setZjzl(rs.getString("ZJZL"));

				ar.add(vo);
			}
			return ar;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_qyzygd> readRecords(String sqlWhere, int pageSize, int page) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_qyzygd vo = null;
		String buf = null;
		List<sb_jl_qysds_qyzygd> ar = new ArrayList<sb_jl_qysds_qyzygd>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_QYZYGD " + sqlWhere;
			rs = st.executeQuery(buf);

			int iCount = 0;
			int iSize = pageSize * (page - 1);
			while(rs.next())
			{
				if (iCount++ < iSize) continue;
				if (iCount > (iSize + pageSize)) break;

				vo = new sb_jl_qysds_qyzygd();
				vo.setId(rs.getString("ID"));
				vo.setQyzygdid(rs.getString("QYZYGDID"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setGdmc(rs.getString("GDMC"));
				vo.setGj(rs.getString("GJ"));
				vo.setJjxz(rs.getString("JJXZ"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setTzbl(rs.getBigDecimal("TZBL"));
				vo.setZjhm(rs.getString("ZJHM"));
				vo.setZjzl(rs.getString("ZJZL"));

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
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_QYZYGD");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
}
