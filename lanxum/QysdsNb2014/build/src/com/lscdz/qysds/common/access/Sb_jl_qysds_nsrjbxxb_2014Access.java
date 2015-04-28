package com.lscdz.qysds.common.access;

/**
 * Created by CodeGenerator at Wed Jan 07 16:12:37 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_NSRJBXXB_2014
 * Comments: 2014版企业所得税纳税人基本信息表
 * Any question, ask author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;
import java.util.*;

import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;

import yangjian.frame.util.*;


public class Sb_jl_qysds_nsrjbxxb_2014Access
{
	public static void insertRecord(Connection con, List<sb_jl_qysds_nsrjbxxb_2014> list) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_NSRJBXXB_2014(JSJDM, ND, NSRZT, SBND, SYJDLX, BBMSF, CHCBJJFF, CJR, CJRQ, CSGJFXZHJZHY, CYRS, CZJWGLJY, DWTZQYID, FZJGSFFTQYSDS, GDZCZJFF, HZNSQY, HZNSQYLX, HZXSHSFF, JWZZKGJMQY, JZBWB, KJDACFD, KJHSRJ, KJZCHGJSFFSBH, LRR, LRRQ, NSRMC, NSRSBH, QTSYKJZZHKJZZ, QYZYGDID, SBLX, SDSJSFF, SFJRQSQ, SFWCQS, SKSSSQQ, SKSSSQZ, SNDSFXXWLQY, SSGS, SSGSLX, SSHY, SSHYMC, SWJGZZJGDM, SWJGZZJGMC, SYKJZZHKJZZ, SYQDJZRQ, SYQDQSRQ, VERSION, ZCZBJE, ZCZE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int i, iSize = list.size();
		sb_jl_qysds_nsrjbxxb_2014 vo;

		try
		{
			st = con.prepareStatement(buf);
			for (i = 0;i < iSize;i++)
			{
				vo = list.get(i);
				st.setString(1, vo.getJsjdm());
				st.setString(2, vo.getNd());
				st.setString(3, vo.getNsrzt());
				st.setString(4, vo.getSbnd());
				st.setString(5, vo.getSyjdlx());
				st.setString(6, vo.getBbmsf());
				st.setString(7, vo.getChcbjjff());
				st.setString(8, vo.getCjr());
				st.setTimestamp(9, vo.getCjrq());
				st.setString(10, vo.getCsgjfxzhjzhy());
				st.setLong(11, vo.getCyrs());
				st.setString(12, vo.getCzjwgljy());
				st.setString(13, vo.getDwtzqyid());
				st.setString(14, vo.getFzjgsfftqysds());
				st.setString(15, vo.getGdzczjff());
				st.setString(16, vo.getHznsqy());
				st.setString(17, vo.getHznsqylx());
				st.setString(18, vo.getHzxshsff());
				st.setString(19, vo.getJwzzkgjmqy());
				st.setString(20, vo.getJzbwb());
				st.setString(21, vo.getKjdacfd());
				st.setString(22, vo.getKjhsrj());
				st.setString(23, vo.getKjzchgjsffsbh());
				st.setString(24, vo.getLrr());
				st.setTimestamp(25, vo.getLrrq());
				st.setString(26, vo.getNsrmc());
				st.setString(27, vo.getNsrsbh());
				st.setString(28, vo.getQtsykjzzhkjzz());
				st.setString(29, vo.getQyzygdid());
				st.setString(30, vo.getSblx());
				st.setString(31, vo.getSdsjsff());
				st.setString(32, vo.getSfjrqsq());
				st.setString(33, vo.getSfwcqs());
				st.setTimestamp(34, vo.getSksssqq());
				st.setTimestamp(35, vo.getSksssqz());
				st.setString(36, vo.getSndsfxxwlqy());
				st.setString(37, vo.getSsgs());
				st.setString(38, vo.getSsgslx());
				st.setString(39, vo.getSshy());
				st.setString(40, vo.getSshymc());
				st.setString(41, vo.getSwjgzzjgdm());
				st.setString(42, vo.getSwjgzzjgmc());
				st.setString(43, vo.getSykjzzhkjzz());
				st.setTimestamp(44, vo.getSyqdjzrq());
				st.setTimestamp(45, vo.getSyqdqsrq());
				st.setString(46, vo.getVersion());
				st.setBigDecimal(47, vo.getZczbje());
				st.setBigDecimal(48, vo.getZcze());

				st.execute();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
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
	public static void insertRecord(Connection con, sb_jl_qysds_nsrjbxxb_2014 vo) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_NSRJBXXB_2014(JSJDM, ND, NSRZT, SBND, SYJDLX, BBMSF, CHCBJJFF, CJR, CJRQ, CSGJFXZHJZHY, CYRS, CZJWGLJY, DWTZQYID, FZJGSFFTQYSDS, GDZCZJFF, HZNSQY, HZNSQYLX, HZXSHSFF, JWZZKGJMQY, JZBWB, KJDACFD, KJHSRJ, KJZCHGJSFFSBH, LRR, LRRQ, NSRMC, NSRSBH, QTSYKJZZHKJZZ, QYZYGDID, SBLX, SDSJSFF, SFJRQSQ, SFWCQS, SKSSSQQ, SKSSSQZ, SNDSFXXWLQY, SSGS, SSGSLX, SSHY, SSHYMC, SWJGZZJGDM, SWJGZZJGMC, SYKJZZHKJZZ, SYQDJZRQ, SYQDQSRQ, VERSION, ZCZBJE, ZCZE,SQLX) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getJsjdm());
			st.setString(2, vo.getNd());
			st.setString(3, vo.getNsrzt());
			st.setString(4, vo.getSbnd());
			st.setString(5, vo.getSyjdlx());
			st.setString(6, vo.getBbmsf());
			st.setString(7, vo.getChcbjjff());
			st.setString(8, vo.getCjr());
			st.setTimestamp(9, vo.getCjrq());
			st.setString(10, vo.getCsgjfxzhjzhy());
			st.setLong(11, vo.getCyrs());
			st.setString(12, vo.getCzjwgljy());
			st.setString(13, vo.getDwtzqyid());
			st.setString(14, vo.getFzjgsfftqysds());
			st.setString(15, vo.getGdzczjff());
			st.setString(16, vo.getHznsqy());
			st.setString(17, vo.getHznsqylx());
			st.setString(18, vo.getHzxshsff());
			st.setString(19, vo.getJwzzkgjmqy());
			st.setString(20, vo.getJzbwb());
			st.setString(21, vo.getKjdacfd());
			st.setString(22, vo.getKjhsrj());
			st.setString(23, vo.getKjzchgjsffsbh());
			st.setString(24, vo.getLrr());
			st.setTimestamp(25, vo.getLrrq());
			st.setString(26, vo.getNsrmc());
			st.setString(27, vo.getNsrsbh());
			st.setString(28, vo.getQtsykjzzhkjzz());
			st.setString(29, vo.getQyzygdid());
			st.setString(30, vo.getSblx());
			st.setString(31, vo.getSdsjsff());
			st.setString(32, vo.getSfjrqsq());
			st.setString(33, vo.getSfwcqs());
			st.setTimestamp(34, vo.getSksssqq());
			st.setTimestamp(35, vo.getSksssqz());
			st.setString(36, vo.getSndsfxxwlqy());
			st.setString(37, vo.getSsgs());
			st.setString(38, vo.getSsgslx());
			st.setString(39, vo.getSshy());
			st.setString(40, vo.getSshymc());
			st.setString(41, vo.getSwjgzzjgdm());
			st.setString(42, vo.getSwjgzzjgmc());
			st.setString(43, vo.getSykjzzhkjzz());
			st.setTimestamp(44, vo.getSyqdjzrq());
			st.setTimestamp(45, vo.getSyqdqsrq());
			st.setString(46, vo.getVersion());
			st.setBigDecimal(47, vo.getZczbje());
			st.setBigDecimal(48, vo.getZcze());
			st.setString(49,vo.getSqlx());
			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
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
	public static void updateRecord(Connection con, sb_jl_qysds_nsrjbxxb_2014 vo, String sqlWhere) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "UPDATE SBDB.SB_JL_QYSDS_NSRJBXXB_2014 SET JSJDM = ?, ND = ?, NSRZT = ?, SBND = ?, SYJDLX = ?, BBMSF = ?, CHCBJJFF = ?, CJR = ?, CJRQ = ?, CSGJFXZHJZHY = ?, CYRS = ?, CZJWGLJY = ?, DWTZQYID = ?, FZJGSFFTQYSDS = ?, GDZCZJFF = ?, HZNSQY = ?, HZNSQYLX = ?, HZXSHSFF = ?, JWZZKGJMQY = ?, JZBWB = ?, KJDACFD = ?, KJHSRJ = ?, KJZCHGJSFFSBH = ?, LRR = ?, LRRQ = ?, NSRMC = ?, NSRSBH = ?, QTSYKJZZHKJZZ = ?, QYZYGDID = ?, SBLX = ?, SDSJSFF = ?, SFJRQSQ = ?, SFWCQS = ?, SKSSSQQ = ?, SKSSSQZ = ?, SNDSFXXWLQY = ?, SSGS = ?, SSGSLX = ?, SSHY = ?, SSHYMC = ?, SWJGZZJGDM = ?, SWJGZZJGMC = ?, SYKJZZHKJZZ = ?, SYQDJZRQ = ?, SYQDQSRQ = ?, VERSION = ?, ZCZBJE = ?, ZCZE = ?,SQLX = ?  " + sqlWhere;

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getJsjdm());
			st.setString(2, vo.getNd());
			st.setString(3, vo.getNsrzt());
			st.setString(4, vo.getSbnd());
			st.setString(5, vo.getSyjdlx());
			st.setString(6, vo.getBbmsf());
			st.setString(7, vo.getChcbjjff());
			st.setString(8, vo.getCjr());
			st.setTimestamp(9, vo.getCjrq());
			st.setString(10, vo.getCsgjfxzhjzhy());
			st.setLong(11, vo.getCyrs());
			st.setString(12, vo.getCzjwgljy());
			st.setString(13, vo.getDwtzqyid());
			st.setString(14, vo.getFzjgsfftqysds());
			st.setString(15, vo.getGdzczjff());
			st.setString(16, vo.getHznsqy());
			st.setString(17, vo.getHznsqylx());
			st.setString(18, vo.getHzxshsff());
			st.setString(19, vo.getJwzzkgjmqy());
			st.setString(20, vo.getJzbwb());
			st.setString(21, vo.getKjdacfd());
			st.setString(22, vo.getKjhsrj());
			st.setString(23, vo.getKjzchgjsffsbh());
			st.setString(24, vo.getLrr());
			st.setTimestamp(25, vo.getLrrq());
			st.setString(26, vo.getNsrmc());
			st.setString(27, vo.getNsrsbh());
			st.setString(28, vo.getQtsykjzzhkjzz());
			st.setString(29, vo.getQyzygdid());
			st.setString(30, vo.getSblx());
			st.setString(31, vo.getSdsjsff());
			st.setString(32, vo.getSfjrqsq());
			st.setString(33, vo.getSfwcqs());
			st.setTimestamp(34, vo.getSksssqq());
			st.setTimestamp(35, vo.getSksssqz());
			st.setString(36, vo.getSndsfxxwlqy());
			st.setString(37, vo.getSsgs());
			st.setString(38, vo.getSsgslx());
			st.setString(39, vo.getSshy());
			st.setString(40, vo.getSshymc());
			st.setString(41, vo.getSwjgzzjgdm());
			st.setString(42, vo.getSwjgzzjgmc());
			st.setString(43, vo.getSykjzzhkjzz());
			st.setTimestamp(44, vo.getSyqdjzrq());
			st.setTimestamp(45, vo.getSyqdqsrq());
			st.setString(46, vo.getVersion());
			st.setBigDecimal(47, vo.getZczbje());
			st.setBigDecimal(48, vo.getZcze());
			st.setString(49, vo.getSqlx());
			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
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
	public static void updateRecord(Connection con,String bbmsf, String sqlWhere) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "UPDATE SBDB.SB_JL_QYSDS_NSRJBXXB_2014 SET BBMSF = ? " + sqlWhere;

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, bbmsf);
			st.execute();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
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
		String buf = "DELETE FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 " + sqlWhere;

		try
		{
			st = con.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
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
			buf = "SELECT NVL(COUNT(*), 0) VAL FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 " + sqlWhere;
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
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_nsrjbxxb_2014> readRecords(String sqlWhere) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_nsrjbxxb_2014 vo = null;
		String buf = null;
		List<sb_jl_qysds_nsrjbxxb_2014> ar = new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new sb_jl_qysds_nsrjbxxb_2014();
				vo.setJsjdm(rs.getString("JSJDM"));
				vo.setNd(rs.getString("ND"));
				vo.setNsrzt(rs.getString("NSRZT"));
				vo.setSbnd(rs.getString("SBND"));
				vo.setSyjdlx(rs.getString("SYJDLX"));
				vo.setBbmsf(rs.getString("BBMSF"));
				vo.setChcbjjff(rs.getString("CHCBJJFF"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setCsgjfxzhjzhy(rs.getString("CSGJFXZHJZHY"));
				vo.setCyrs(rs.getLong("CYRS"));
				vo.setCzjwgljy(rs.getString("CZJWGLJY"));
				vo.setDwtzqyid(rs.getString("DWTZQYID"));
				vo.setFzjgsfftqysds(rs.getString("FZJGSFFTQYSDS"));
				vo.setGdzczjff(rs.getString("GDZCZJFF"));
				vo.setHznsqy(rs.getString("HZNSQY"));
				vo.setHznsqylx(rs.getString("HZNSQYLX"));
				vo.setHzxshsff(rs.getString("HZXSHSFF"));
				vo.setJwzzkgjmqy(rs.getString("JWZZKGJMQY"));
				vo.setJzbwb(rs.getString("JZBWB"));
				vo.setKjdacfd(rs.getString("KJDACFD"));
				vo.setKjhsrj(rs.getString("KJHSRJ"));
				vo.setKjzchgjsffsbh(rs.getString("KJZCHGJSFFSBH"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setNsrmc(rs.getString("NSRMC"));
				vo.setNsrsbh(rs.getString("NSRSBH"));
				vo.setQtsykjzzhkjzz(rs.getString("QTSYKJZZHKJZZ"));
				vo.setQyzygdid(rs.getString("QYZYGDID"));
				vo.setSblx(rs.getString("SBLX"));
				vo.setSdsjsff(rs.getString("SDSJSFF"));
				vo.setSfjrqsq(rs.getString("SFJRQSQ"));
				vo.setSfwcqs(rs.getString("SFWCQS"));
				vo.setSksssqq(rs.getTimestamp("SKSSSQQ"));
				vo.setSksssqz(rs.getTimestamp("SKSSSQZ"));
				vo.setSndsfxxwlqy(rs.getString("SNDSFXXWLQY"));
				vo.setSsgs(rs.getString("SSGS"));
				vo.setSsgslx(rs.getString("SSGSLX"));
				vo.setSshy(rs.getString("SSHY"));
				vo.setSshymc(rs.getString("SSHYMC"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setSykjzzhkjzz(rs.getString("SYKJZZHKJZZ"));
				vo.setSyqdjzrq(rs.getTimestamp("SYQDJZRQ"));
				vo.setSyqdqsrq(rs.getTimestamp("SYQDQSRQ"));
				vo.setVersion(rs.getString("VERSION"));
				vo.setZczbje(rs.getBigDecimal("ZCZBJE"));
				vo.setZcze(rs.getBigDecimal("ZCZE"));

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
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
	public static List<sb_jl_qysds_nsrjbxxb_2014> readRecords(String sqlWhere, int pageSize, int page) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_nsrjbxxb_2014 vo = null;
		String buf = null;
		List<sb_jl_qysds_nsrjbxxb_2014> ar = new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();

		try
		{
			con = ResourceManager.getConnectionEx1();
			st = con.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 " + sqlWhere;
			rs = st.executeQuery(buf);

			int iCount = 0;
			int iSize = pageSize * (page - 1);
			while(rs.next())
			{
				if (iCount++ < iSize) continue;
				if (iCount > (iSize + pageSize)) break;

				vo = new sb_jl_qysds_nsrjbxxb_2014();
				vo.setJsjdm(rs.getString("JSJDM"));
				vo.setNd(rs.getString("ND"));
				vo.setNsrzt(rs.getString("NSRZT"));
				vo.setSbnd(rs.getString("SBND"));
				vo.setSyjdlx(rs.getString("SYJDLX"));
				vo.setBbmsf(rs.getString("BBMSF"));
				vo.setChcbjjff(rs.getString("CHCBJJFF"));
				vo.setCjr(rs.getString("CJR"));
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setCsgjfxzhjzhy(rs.getString("CSGJFXZHJZHY"));
				vo.setCyrs(rs.getLong("CYRS"));
				vo.setCzjwgljy(rs.getString("CZJWGLJY"));
				vo.setDwtzqyid(rs.getString("DWTZQYID"));
				vo.setFzjgsfftqysds(rs.getString("FZJGSFFTQYSDS"));
				vo.setGdzczjff(rs.getString("GDZCZJFF"));
				vo.setHznsqy(rs.getString("HZNSQY"));
				vo.setHznsqylx(rs.getString("HZNSQYLX"));
				vo.setHzxshsff(rs.getString("HZXSHSFF"));
				vo.setJwzzkgjmqy(rs.getString("JWZZKGJMQY"));
				vo.setJzbwb(rs.getString("JZBWB"));
				vo.setKjdacfd(rs.getString("KJDACFD"));
				vo.setKjhsrj(rs.getString("KJHSRJ"));
				vo.setKjzchgjsffsbh(rs.getString("KJZCHGJSFFSBH"));
				vo.setLrr(rs.getString("LRR"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setNsrmc(rs.getString("NSRMC"));
				vo.setNsrsbh(rs.getString("NSRSBH"));
				vo.setQtsykjzzhkjzz(rs.getString("QTSYKJZZHKJZZ"));
				vo.setQyzygdid(rs.getString("QYZYGDID"));
				vo.setSblx(rs.getString("SBLX"));
				vo.setSdsjsff(rs.getString("SDSJSFF"));
				vo.setSfjrqsq(rs.getString("SFJRQSQ"));
				vo.setSfwcqs(rs.getString("SFWCQS"));
				vo.setSksssqq(rs.getTimestamp("SKSSSQQ"));
				vo.setSksssqz(rs.getTimestamp("SKSSSQZ"));
				vo.setSndsfxxwlqy(rs.getString("SNDSFXXWLQY"));
				vo.setSsgs(rs.getString("SSGS"));
				vo.setSsgslx(rs.getString("SSGSLX"));
				vo.setSshy(rs.getString("SSHY"));
				vo.setSshymc(rs.getString("SSHYMC"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setSwjgzzjgmc(rs.getString("SWJGZZJGMC"));
				vo.setSykjzzhkjzz(rs.getString("SYKJZZHKJZZ"));
				vo.setSyqdjzrq(rs.getTimestamp("SYQDJZRQ"));
				vo.setSyqdqsrq(rs.getTimestamp("SYQDQSRQ"));
				vo.setVersion(rs.getString("VERSION"));
				vo.setZczbje(rs.getBigDecimal("ZCZBJE"));
				vo.setZcze(rs.getBigDecimal("ZCZE"));

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
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
}
