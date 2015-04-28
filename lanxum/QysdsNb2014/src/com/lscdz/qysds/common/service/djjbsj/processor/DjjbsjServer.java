package com.lscdz.qysds.common.service.djjbsj.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.common.service.djjbsj.IDjjbsjService;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;

/**
 * 登记基本数据相关操作服务类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-17 下午02:34:51
 */
public class DjjbsjServer implements IDjjbsjService{

	
	/**
	 * 查询符合条件的登记基本数据列表（根据计算机代码和税务机关组织机构代码）
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	@Override
	public Djjbsj query(Connection con,String jsjdm,String swjgzzjgdm) throws FrameException{
		if(jsjdm==null || swjgzzjgdm==null || jsjdm.equals("") || swjgzzjgdm.equals("")){
			throw new FrameException("计算机代码和税务机关组织机构代码不能为空！");
		}
		String sqlWhere="jsjdm='"+jsjdm+"' and swjgzzjgdm='"+swjgzzjgdm+"'";
		List<Djjbsj> djjbsjList=this.readRecords(con,sqlWhere);	
		return djjbsjList.size()>0?djjbsjList.get(0):null;
	}
	/**
	 * 查询符合条件的登记基本数据列表（根据计算机代码）
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	@Override
	public Djjbsj query(Connection con,String jsjdm) throws FrameException{
		if(jsjdm==null  || jsjdm.equals("")){
			throw new FrameException("计算机代码不能为空！");
		}
		String sqlWhere="jsjdm='"+jsjdm+"'";		
		List<Djjbsj> djjbsjList=this.readRecords(con,sqlWhere);	
		return djjbsjList.size()>0?djjbsjList.get(0):null;
	}
	
	/**
	 * 查询符合条件的登记基本数据列表
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	private  List<Djjbsj> readRecords(Connection con,String sqlWhere) throws FrameException
	{
		Statement st = null;
		ResultSet rs = null;
		Djjbsj vo = null;
		String buf = null;
		List<Djjbsj> ar = new ArrayList<Djjbsj>();

		try
		{
			st = con.createStatement();
			buf = "SELECT * FROM DJDB.DJ_JL_JBSJ where " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new Djjbsj();
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setDjsllx(rs.getString("DJSLLX"));
				vo.setDjzclxdm(rs.getString("DJZCLXDM"));
				vo.setDjzzldm(rs.getString("DJZZLDM"));
				vo.setGdsgghbs(rs.getInt("GDSGGHBS"));
				vo.setGjbzhydm(rs.getString("GJBZHYDM"));
				vo.setHsxsdm(rs.getString("HSXSDM"));
				vo.setJsjdm(rs.getString("JSJDM"));
				vo.setJydz(rs.getString("JYDZ"));
				vo.setJydzyb(rs.getString("JYDZYB"));
				vo.setKjzddm(rs.getString("KJZDDM"));
				vo.setKydjrq(rs.getTimestamp("KYDJRQ"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setLsgxdm(rs.getString("LSGXDM"));
				vo.setNsrmc(rs.getString("NSRMC"));
				vo.setNsrzt(rs.getString("NSRZT"));
				vo.setQrrq(rs.getTimestamp("QRRQ"));
				vo.setQrry(rs.getString("QRRY"));
				vo.setScjxdm(rs.getString("SCJXDM"));
				vo.setSlrq(rs.getTimestamp("SLRQ"));
				vo.setSlry(rs.getString("SLRY"));
				vo.setSwdjlx(rs.getString("SWDJLX"));
				vo.setSwdjzh(rs.getString("SWDJZH"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setZcdz(rs.getString("ZCDZ"));
				vo.setZcdzyb(rs.getString("ZCDZYB"));
				vo.setZczbbzdm(rs.getString("ZCZBBZDM"));
				vo.setZczbje(rs.getBigDecimal("ZCZBJE"));
				vo.setZgbmdm(rs.getString("ZGBMDM"));
				vo.setCjsj(rs.getTimestamp("CJSJ"));
				vo.setHzfhrq(rs.getTimestamp("HZFHRQ"));
				vo.setHzfhry(rs.getString("HZFHRY"));
				vo.setJydzlxdm(rs.getString("JYDZLXDM"));
				vo.setJyfw(rs.getString("JYFW"));
				vo.setQyzy(rs.getString("QYZY"));
				vo.setSbdm(rs.getString("SBDM"));
				vo.setScbs(rs.getString("SCBS"));
				vo.setSfzjhm(rs.getString("SFZJHM"));
				vo.setSjlylxdm(rs.getString("SJLYLXDM"));
				vo.setWzztzblhj(rs.getBigDecimal("WZZTZBLHJ"));
				vo.setXgsj(rs.getTimestamp("XGSJ"));
				vo.setYhzrq(rs.getTimestamp("YHZRQ"));
				vo.setYhzry(rs.getString("YHZRY"));
				vo.setYyzzh(rs.getString("YYZZH"));
				vo.setZcdzlxdh(rs.getString("ZCDZLXDH"));
				vo.setZhgxsj(rs.getString("ZHGXSJ"));
				vo.setZrrtzblhj(rs.getBigDecimal("ZRRTZBLHJ"));
				vo.setZzjgdm(rs.getString("ZZJGDM"));

				ar.add(vo);
			}
			return ar;
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: DJDB.DJ_JL_JBSJ");
		}
		finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
	}

}
