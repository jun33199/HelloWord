package com.lscdz.qysds.application.nsrjbxx2014.extDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yangjian.frame.util.FrameException;
import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;

public class NsrjbxxExtDao {
	/**
	 * 查询纳税人基本信息（2014）
	 * @param conn
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<sb_jl_qysds_nsrjbxxb_2014> readNsrjbxxRecords(Connection conn,String sqlWhere) throws FrameException
	{
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_nsrjbxxb_2014 vo = null;
		String buf = null;
		List<sb_jl_qysds_nsrjbxxb_2014> ar = new ArrayList<sb_jl_qysds_nsrjbxxb_2014>();

		try
		{
			st = conn.createStatement();
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
				vo.setSqlx(rs.getString("SQLX"));
				ar.add(vo);
			}
			return ar;
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
		}
	}
	
	
	/**
	 * 查询主要股东信息
	 * @param conn
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<sb_jl_qysds_qyzygd> readZygdRecords(Connection conn,String sqlWhere) throws FrameException
	{
		//Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_qyzygd vo = null;
		String buf = null;
		List<sb_jl_qysds_qyzygd> ar = new ArrayList<sb_jl_qysds_qyzygd>();

		try
		{
			//con = ResourceManager.getConnectionEx1();
			st = conn.createStatement();
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
		}
	}
	/**
	 * 查询对外投资信息
	 * @param conn
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<sb_jl_qysds_dwtz> readDwtzRecords(Connection conn,String sqlWhere) throws FrameException
	{
		Statement st = null;
		ResultSet rs = null;
		sb_jl_qysds_dwtz vo = null;
		String buf = null;
		List<sb_jl_qysds_dwtz> ar = new ArrayList<sb_jl_qysds_dwtz>();
		try
		{
			st = conn.createStatement();
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
		}
	}
	
}
