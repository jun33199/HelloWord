/**
 * @Title:       GrlistAction.java
 * @Description: TODO
 * @date:        2014-11-27上午11:13:23
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web.GrListActionForm;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.GrxxModel;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-27
 */
public class GrlistProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		try {
			switch (vo.getAction()) {
			
			// 查询条目
			case Grsds2014Constant.PROCESSOR_ACTION_QUERY: {
				return doQuery(vo);
			}
			
			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}	
	}

	/**
	 * @Description: TODO 查询条目
	 * @param vo
	 * @return
	 * @throws BaseException 
	 * @throws SQLException 
	 */
	private Object doQuery(VOPackage vo) throws BaseException, SQLException {
		
		GrListActionForm grListForm = (GrListActionForm)vo.getData();
		String jsjdm = grListForm.getJsjdm();
		String sfzjlx = grListForm.getSfzjlx();
		String sfzjhm = grListForm.getSfzjhm();
		return doQuery(jsjdm ,sfzjlx ,sfzjhm);
	}
	
	/**
	 * @Description: TODO真的查询
	 * @param jsjdm
	 * @return
	 * @throws BaseException
	 * @throws SQLException
	 */
	private List doQuery(String jsjdm ,String sfzjlx ,String sfzjhm) throws BaseException, SQLException{
		Connection conn =null;
		List ls = new ArrayList();
		try{
			conn = SfDBResource.getConnection();	
			//处理特殊情况  第一次 或全都删除了
			//operateTzzInf(jsjdm, conn);
			StringBuffer sb = new StringBuffer();
			sb.append("select TZZXM ,ZJLXDM ,ZJHM , FPBL from SBDB.SB_JL_TZZSJ2014 where jsjdm= ? ");
			if(!(sfzjlx==null || "".equals(sfzjlx))){
				sb.append(" and  zjlxdm = ? ");
			}
			if(!(sfzjhm==null || "".equals(sfzjhm))){
				sb.append(" and  zjhm = ? ");
			}
			sb.append(" order by cjsj desc");
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			int i=1;
			ps.setString(i++, jsjdm);
			if(!(sfzjlx==null || "".equals(sfzjlx))){
				ps.setString(i++, sfzjlx);
			}
			if(!(sfzjhm==null || "".equals(sfzjhm))){
				ps.setString(i++, sfzjhm);
			}
			ResultSet rs = ps.executeQuery();	
			while(rs.next())
			{
				String zjlxdm = rs.getString("ZJLXDM");
				String zjhm = rs.getString("ZJHM");
				
				GrxxModel gr = new GrxxModel();
				gr.setGr_tzzxm(rs.getString("TZZXM"));
				gr.setGr_sfzjlx(zjlxdm);
				gr.setGr_sfzjhm(zjhm);
				gr.setGr_fpbl(rs.getBigDecimal("FPBL")==null?"0.00":String.valueOf(rs.getBigDecimal("FPBL").setScale(4,BigDecimal.ROUND_HALF_UP)));
				gr.setGr_txzt(getTxzt(zjlxdm ,zjhm ,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), conn));
				
				ls.add(gr);
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}finally{
			if(conn!=null)
			conn.close();
		}
		return ls;
	}
	
	
	/**
	 * @Description: TODO 获取填写状态
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
	
	/**
	 * @Description: TODO 查询 （1）sbdb.SB_JL_TZZSJ2014，（2）djdb.dj_jl_tzf；                如果（2）有（1）无则进行数据同步
	 * @param jsjdm
	 * @param conn
	 * @throws BaseException
	 * @throws SQLException 
	 */
	private boolean operateTzzInf(String jsjdm ,Connection conn) throws BaseException, SQLException
	{ 
		//String为常量池对象，相同计算机代码的同步操作。
		synchronized(jsjdm){
			String sql1=("select count(1) num  from djdb.dj_jl_tzf       where jsjdm=?");
			String sql2=("select count(1) num  from sbdb.SB_JL_TZZSJ2014  where jsjdm=?");
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps1.setString(1, jsjdm);
			ps2.setString(1, jsjdm);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			if(rs1.next() && rs2.next())
			{
				int num1 = rs1.getInt("num");
				int num2 = rs2.getInt("num");
				if(num2==0 && num1!=0)
				{
					return synTzzInf(jsjdm, conn);
				}
			}
			return true;
		}
		
	}
	
	
	/**
	 * @Description: TODO 从登记中同步投资者数据
	 * @param jsjdm
	 * @param conn
	 * @return true 无重复数据；false有重复数据
	 * @throws BaseException
	 * @throws SQLException
	 */
	private boolean synTzzInf(String jsjdm ,Connection conn) throws BaseException, SQLException{
		StringBuffer querySQL = new StringBuffer();
		StringBuffer insertSQL = new StringBuffer();
		List ls = new ArrayList();
		boolean hasRepeat = true;
		
		querySQL.append("select jsjdm ,zjlxdm ,zjhm ,tzfmc ,gjdz ,fpbl from djdb.dj_jl_tzf tzf where jsjdm=?");
		insertSQL.append("insert into sbdb.SB_JL_TZZSJ2014(jsjdm ,zjlxdm ,zjhm ,tzzxm ,gj ,fpbl ,cjr ,cjsj) values(?,?,?,?,?,?,'system' ,sysdate)");
		
		PreparedStatement insertPs = null;
		PreparedStatement queryPs = conn.prepareStatement(querySQL.toString());
		queryPs.setString(1, jsjdm);
		ResultSet queryRs = queryPs.executeQuery();
		while(queryRs.next()){
			String tzf_jsjdm = queryRs.getString("jsjdm");
			String tzf_zjlxdm = queryRs.getString("zjlxdm");
			String tzf_zjhm = queryRs.getString("zjhm");
			String tzf_tzfmc = queryRs.getString("tzfmc");
			String tzf_gjdz = queryRs.getString("gjdz");
			String tzf_fpbl = queryRs.getBigDecimal("FPBL")==null?"0.0000":String.valueOf(queryRs.getBigDecimal("FPBL").setScale(4,BigDecimal.ROUND_HALF_UP));
			String key = tzf_jsjdm+tzf_zjlxdm+tzf_zjhm;
			if(ls.contains(key)){
				hasRepeat = false;
				continue;
			}else{
				ls.add(key);
				insertPs = conn.prepareStatement(insertSQL.toString());
				int i=1;
				insertPs.setString(i++, tzf_jsjdm);
				insertPs.setString(i++, tzf_zjlxdm);
				insertPs.setString(i++, tzf_zjhm);
				insertPs.setString(i++, tzf_tzfmc);
				insertPs.setString(i++, tzf_gjdz);
				insertPs.setBigDecimal(i++, new BigDecimal(tzf_fpbl));
				
				insertPs.executeUpdate();
			}
		}
		
		return hasRepeat;
	}
	
}
