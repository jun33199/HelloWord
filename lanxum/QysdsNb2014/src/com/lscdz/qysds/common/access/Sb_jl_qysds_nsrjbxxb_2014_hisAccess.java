package com.lscdz.qysds.common.access;

/**
 * Created by CodeGenerator at Wed Jan 07 16:36:34 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS
 * Comments: 2014版企业所得税纳税人基本信息历史表
 * Any question, ask author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.math.*;
import java.util.*;
import java.util.Date;

import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014_his;

import yangjian.frame.util.*;

public class Sb_jl_qysds_nsrjbxxb_2014_hisAccess
{
	public static void insertRecord(Connection con, List<sb_jl_qysds_nsrjbxxb_2014_his> list) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS(ID, JSJDM, ND, NSRZT, SBND, SYJDLX, BBMSF, CHCBJJFF, CJR, CJRQ, CSGJFXZHJZHY, CYRS, CZJWGLJY, DWTZQYID, FZJGSFFTQYSDS, GDZCZJFF, HZNSQY, HZNSQYLX, HZXSHSFF, JWZZKGJMQY, JZBWB, KJDACFD, KJHSRJ, KJZCHGJSFFSBH, LRR, LRRQ, NSRMC, NSRSBH, QTSYKJZZHKJZZ, QYZYGDID, SBLX, SDSJSFF, SFJRQSQ, SFWCQS, SKSSSQQ, SKSSSQZ, SNDSFXXWLQY, SSGS, SSGSLX, SSHY, SSHYMC, SWJGZZJGDM, SWJGZZJGMC, SYKJZZHKJZZ, SYQDJZRQ, SYQDQSRQ, VERSION, ZCZBJE, ZCZE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int i, iSize = list.size();
		sb_jl_qysds_nsrjbxxb_2014_his vo;

		try
		{
			st = con.prepareStatement(buf);
			for (i = 0;i < iSize;i++)
			{
				vo = list.get(i);
				st.setString(1, vo.getId());
				st.setString(2, vo.getJsjdm());
				st.setString(3, vo.getNd());
				st.setString(4, vo.getNsrzt());
				st.setString(5, vo.getSbnd());
				st.setString(6, vo.getSyjdlx());
				st.setString(7, vo.getBbmsf());
				st.setString(8, vo.getChcbjjff());
				st.setString(9, vo.getCjr());
				st.setTimestamp(10, vo.getCjrq());
				st.setString(11, vo.getCsgjfxzhjzhy());
				st.setLong(12, vo.getCyrs());
				st.setString(13, vo.getCzjwgljy());
				st.setString(14, vo.getDwtzqyid());
				st.setString(15, vo.getFzjgsfftqysds());
				st.setString(16, vo.getGdzczjff());
				st.setString(17, vo.getHznsqy());
				st.setString(18, vo.getHznsqylx());
				st.setString(19, vo.getHzxshsff());
				st.setString(20, vo.getJwzzkgjmqy());
				st.setString(21, vo.getJzbwb());
				st.setString(22, vo.getKjdacfd());
				st.setString(23, vo.getKjhsrj());
				st.setString(24, vo.getKjzchgjsffsbh());
				st.setString(25, vo.getLrr());
				st.setTimestamp(26, vo.getLrrq());
				st.setString(27, vo.getNsrmc());
				st.setString(28, vo.getNsrsbh());
				st.setString(29, vo.getQtsykjzzhkjzz());
				st.setString(30, vo.getQyzygdid());
				st.setString(31, vo.getSblx());
				st.setString(32, vo.getSdsjsff());
				st.setString(33, vo.getSfjrqsq());
				st.setString(34, vo.getSfwcqs());
				st.setTimestamp(35, vo.getSksssqq());
				st.setTimestamp(36, vo.getSksssqz());
				st.setString(37, vo.getSndsfxxwlqy());
				st.setString(38, vo.getSsgs());
				st.setString(39, vo.getSsgslx());
				st.setString(40, vo.getSshy());
				st.setString(41, vo.getSshymc());
				st.setString(42, vo.getSwjgzzjgdm());
				st.setString(43, vo.getSwjgzzjgmc());
				st.setString(44, vo.getSykjzzhkjzz());
				st.setTimestamp(45, vo.getSyqdjzrq());
				st.setTimestamp(46, vo.getSyqdqsrq());
				st.setString(47, vo.getVersion());
				st.setBigDecimal(48, vo.getZczbje());
				st.setBigDecimal(49, vo.getZcze());

				st.execute();
			}
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS");
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
	public static void insertRecord(Connection con, sb_jl_qysds_nsrjbxxb_2014_his vo) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS(ID, JSJDM, ND, NSRZT, SBND, SYJDLX, BBMSF, CHCBJJFF, CJR, CJRQ, CSGJFXZHJZHY, CYRS, CZJWGLJY, DWTZQYID, FZJGSFFTQYSDS, GDZCZJFF, HZNSQY, HZNSQYLX, HZXSHSFF, JWZZKGJMQY, JZBWB, KJDACFD, KJHSRJ, KJZCHGJSFFSBH, LRR, LRRQ, NSRMC, NSRSBH, QTSYKJZZHKJZZ, QYZYGDID, SBLX, SDSJSFF, SFJRQSQ, SFWCQS, SKSSSQQ, SKSSSQZ, SNDSFXXWLQY, SSGS, SSGSLX, SSHY, SSHYMC, SWJGZZJGDM, SWJGZZJGMC, SYKJZZHKJZZ, SYQDJZRQ, SYQDQSRQ, VERSION, ZCZBJE, ZCZE,SQLX) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getId());
			st.setString(2, vo.getJsjdm());
			st.setString(3, vo.getNd());
			st.setString(4, vo.getNsrzt());
			st.setString(5, vo.getSbnd());
			st.setString(6, vo.getSyjdlx());
			st.setString(7, vo.getBbmsf());
			st.setString(8, vo.getChcbjjff());
			st.setString(9, vo.getCjr());
			st.setTimestamp(10, vo.getCjrq());
			st.setString(11, vo.getCsgjfxzhjzhy());
			st.setLong(12, vo.getCyrs());
			st.setString(13, vo.getCzjwgljy());
			st.setString(14, vo.getDwtzqyid());
			st.setString(15, vo.getFzjgsfftqysds());
			st.setString(16, vo.getGdzczjff());
			st.setString(17, vo.getHznsqy());
			st.setString(18, vo.getHznsqylx());
			st.setString(19, vo.getHzxshsff());
			st.setString(20, vo.getJwzzkgjmqy());
			st.setString(21, vo.getJzbwb());
			st.setString(22, vo.getKjdacfd());
			st.setString(23, vo.getKjhsrj());
			st.setString(24, vo.getKjzchgjsffsbh());
			st.setString(25, vo.getLrr());
			st.setTimestamp(26, vo.getLrrq());
			st.setString(27, vo.getNsrmc());
			st.setString(28, vo.getNsrsbh());
			st.setString(29, vo.getQtsykjzzhkjzz());
			st.setString(30, vo.getQyzygdid());
			st.setString(31, vo.getSblx());
			st.setString(32, vo.getSdsjsff());
			st.setString(33, vo.getSfjrqsq());
			st.setString(34, vo.getSfwcqs());
			st.setTimestamp(35, vo.getSksssqq());
			st.setTimestamp(36, vo.getSksssqz());
			st.setString(37, vo.getSndsfxxwlqy());
			st.setString(38, vo.getSsgs());
			st.setString(39, vo.getSsgslx());
			st.setString(40, vo.getSshy());
			st.setString(41, vo.getSshymc());
			st.setString(42, vo.getSwjgzzjgdm());
			st.setString(43, vo.getSwjgzzjgmc());
			st.setString(44, vo.getSykjzzhkjzz());
			st.setTimestamp(45, vo.getSyqdjzrq());
			st.setTimestamp(46, vo.getSyqdqsrq());
			st.setString(47, vo.getVersion());
			st.setBigDecimal(48, vo.getZczbje());
			st.setBigDecimal(49, vo.getZcze());
			st.setString(50, vo.getSqlx());
			st.execute();
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS");
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
	public static void updateRecord(Connection con, sb_jl_qysds_nsrjbxxb_2014_his vo, String sqlWhere) throws FrameException
	{
		PreparedStatement st = null;
		String buf = "UPDATE SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS SET ID = ?, JSJDM = ?, ND = ?, NSRZT = ?, SBND = ?, SYJDLX = ?, BBMSF = ?, CHCBJJFF = ?, CJR = ?, CJRQ = ?, CSGJFXZHJZHY = ?, CYRS = ?, CZJWGLJY = ?, DWTZQYID = ?, FZJGSFFTQYSDS = ?, GDZCZJFF = ?, HZNSQY = ?, HZNSQYLX = ?, HZXSHSFF = ?, JWZZKGJMQY = ?, JZBWB = ?, KJDACFD = ?, KJHSRJ = ?, KJZCHGJSFFSBH = ?, LRR = ?, LRRQ = ?, NSRMC = ?, NSRSBH = ?, QTSYKJZZHKJZZ = ?, QYZYGDID = ?, SBLX = ?, SDSJSFF = ?, SFJRQSQ = ?, SFWCQS = ?, SKSSSQQ = ?, SKSSSQZ = ?, SNDSFXXWLQY = ?, SSGS = ?, SSGSLX = ?, SSHY = ?, SSHYMC = ?, SWJGZZJGDM = ?, SWJGZZJGMC = ?, SYKJZZHKJZZ = ?, SYQDJZRQ = ?, SYQDQSRQ = ?, VERSION = ?, ZCZBJE = ?, ZCZE = ?  " + sqlWhere;

		try
		{
			st = con.prepareStatement(buf);
			st.setString(1, vo.getId());
			st.setString(2, vo.getJsjdm());
			st.setString(3, vo.getNd());
			st.setString(4, vo.getNsrzt());
			st.setString(5, vo.getSbnd());
			st.setString(6, vo.getSyjdlx());
			st.setString(7, vo.getBbmsf());
			st.setString(8, vo.getChcbjjff());
			st.setString(9, vo.getCjr());
			st.setTimestamp(10, vo.getCjrq());
			st.setString(11, vo.getCsgjfxzhjzhy());
			st.setLong(12, vo.getCyrs());
			st.setString(13, vo.getCzjwgljy());
			st.setString(14, vo.getDwtzqyid());
			st.setString(15, vo.getFzjgsfftqysds());
			st.setString(16, vo.getGdzczjff());
			st.setString(17, vo.getHznsqy());
			st.setString(18, vo.getHznsqylx());
			st.setString(19, vo.getHzxshsff());
			st.setString(20, vo.getJwzzkgjmqy());
			st.setString(21, vo.getJzbwb());
			st.setString(22, vo.getKjdacfd());
			st.setString(23, vo.getKjhsrj());
			st.setString(24, vo.getKjzchgjsffsbh());
			st.setString(25, vo.getLrr());
			st.setTimestamp(26, vo.getLrrq());
			st.setString(27, vo.getNsrmc());
			st.setString(28, vo.getNsrsbh());
			st.setString(29, vo.getQtsykjzzhkjzz());
			st.setString(30, vo.getQyzygdid());
			st.setString(31, vo.getSblx());
			st.setString(32, vo.getSdsjsff());
			st.setString(33, vo.getSfjrqsq());
			st.setString(34, vo.getSfwcqs());
			st.setTimestamp(35, vo.getSksssqq());
			st.setTimestamp(36, vo.getSksssqz());
			st.setString(37, vo.getSndsfxxwlqy());
			st.setString(38, vo.getSsgs());
			st.setString(39, vo.getSsgslx());
			st.setString(40, vo.getSshy());
			st.setString(41, vo.getSshymc());
			st.setString(42, vo.getSwjgzzjgdm());
			st.setString(43, vo.getSwjgzzjgmc());
			st.setString(44, vo.getSykjzzhkjzz());
			st.setTimestamp(45, vo.getSyqdjzrq());
			st.setTimestamp(46, vo.getSyqdqsrq());
			st.setString(47, vo.getVersion());
			st.setBigDecimal(48, vo.getZczbje());
			st.setBigDecimal(49, vo.getZcze());

			st.execute();
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS");
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
		String buf = "DELETE FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS " + sqlWhere;

		try
		{
			st = con.createStatement();
			st.executeUpdate(buf);
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014_HIS");
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
