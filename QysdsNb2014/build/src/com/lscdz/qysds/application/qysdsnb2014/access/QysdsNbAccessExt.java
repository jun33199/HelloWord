package com.lscdz.qysds.application.qysdsnb2014.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.qysdsnb2014.vo.inner.QysdsZgswjg;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNsrjbxxVo2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;

public class QysdsNbAccessExt {
	public static List<QysdsZgswjg>  getZgswjgList(Yh userData)throws FrameException {
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<QysdsZgswjg> list = new ArrayList<QysdsZgswjg>();
		try {
			
			conn=ResourceManager.getConnection();
			String sqlWhere="select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm=userData.getSsdwdm();		
			String yhjb=userData.getJbdm();
			if(yhjb.equals("50")){
				sqlWhere+=" and ccbs='1' ";
			}
			if(yhjb.equals("40")){
				sqlWhere+=" and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"+ssdwdm.substring(0,2)+"%'" ; 
			}
			if(yhjb.equals("30")){
				sqlWhere+=" and swjgzzjgdm ='"+ssdwdm+"'";
			}
			
			sqlWhere+=" order by swjgzzjgdm";
			ps = conn.prepareStatement(sqlWhere);
			rs = ps.executeQuery();
			while (rs.next()) {
				QysdsZgswjg zgswjg = new QysdsZgswjg();
				zgswjg.setZgswjgDm(rs.getString("swjgzzjgdm"));
				zgswjg.setZgswjgMc(rs.getString("swjgzzjgmc"));
				list.add(zgswjg);
			}

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw new FrameException("获取主管税务机关列表失败" + ex.getMessage());
		} finally {
			if(ps!=null){
				try {ps.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return list;
	}
	/**
	 * 获取纳税人基本信息列表
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	/**
	 * 查询纳税人基本信息（2014）
	 * @param conn
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<QysdsNsrjbxxVo2014> readNsrjbxxRecords(Connection conn,String sqlWhere) throws FrameException
	{
		Statement st = null;
		ResultSet rs = null;
		QysdsNsrjbxxVo2014 vo = null;
		List<QysdsNsrjbxxVo2014> ar = new ArrayList<QysdsNsrjbxxVo2014>();

		try
		{
			st = conn.createStatement();
			rs = st.executeQuery(sqlWhere);

			while(rs.next())
			{
				vo = new QysdsNsrjbxxVo2014();
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
	 * 获取基本信息（网上）
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List<QysdsNsrjbxxVo2014> readRecords(String sqlWhere,Connection conn) throws FrameException
	{
		//Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		QysdsNsrjbxxVo2014 vo = null;
		String buf = null;
		List<QysdsNsrjbxxVo2014> ar = new ArrayList<QysdsNsrjbxxVo2014>();

		try
		{
		//	con = ResourceManager.getConnectionEx1();
			st = conn.createStatement();
			buf = "SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new QysdsNsrjbxxVo2014();
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
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: SBDB.SB_JL_QYSDS_NSRJBXXB_2014");
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			//try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}
}
