package com.lscdz.qysds.common.access;

/**
 * Created by CodeGenerator at Tue Jan 06 09:22:39 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_QYZYGD
 * Comments: 
 * Any question, ask author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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


}
