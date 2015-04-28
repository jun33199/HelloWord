package com.syax.bjtax.shenbao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.model.domain.Jmfl;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QueryDmUtil{
	public static Map map=new HashMap();
	static{
	map.put("0010", "sfdb.sf_jl_qysdsjmsba_01");
	map.put("0020", "sfdb.sf_jl_qysdsjmsba_02");
	map.put("0030", "sfdb.sf_jl_qysdsjmsba_03");
	map.put("0040", "sfdb.sf_jl_qysdsjmsba_04");
	map.put("0050", "sfdb.sf_jl_qysdsjmsba_05");
	map.put("0060", "sfdb.sf_jl_qysdsjmsba_06");
	map.put("0070", "sfdb.sf_jl_qysdsjmsba_07");
	map.put("0080", "sfdb.sf_jl_qysdsjmsba_08");
	map.put("0090", "sfdb.sf_jl_qysdsjmsba_09");
	map.put("0100", "sfdb.sf_jl_qysdsjmsba_10");
	map.put("0110", "sfdb.sf_jl_qysdsjmsba_11");
	map.put("0120", "sfdb.sf_jl_qysdsjmsba_12");
	map.put("013A", "sfdb.sf_jl_qysdsjmsba_13A");
	map.put("013B", "sfdb.sf_jl_qysdsjmsba_13B");
	map.put("014A", "sfdb.sf_jl_qysdsjmsba_14A");
	map.put("014B", "sfdb.sf_jl_qysdsjmsba_14B");
	map.put("0150", "sfdb.sf_jl_qysdsjmsba_15");
	map.put("0160", "sfdb.sf_jl_qysdsjmsba_16");
	map.put("0170", "sfdb.sf_jl_qysdsjmsba_17");
	map.put("0180", "sfdb.sf_jl_qysdsjmsba_18");
	map.put("0190", "sfdb.sf_jl_qysdsjmsba_19");
	map.put("0200", "sfdb.sf_jl_qysdsjmsba_20");
	}
	
	public static String getTableNameByJmbasxdm(String jmbasxdm){
		return (String)map.get(jmbasxdm);
	}

	
	/**
	 * 文化产业
	 * @param 
	 * @return list
	 * @throws BaseException 
	 */
	public List getJsdm() throws BaseException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBResource.getConnection();
			String sql = "select fwywfwdm,fwywfwmc  from dmdb.sf_dm_fwywfw";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Jmfl obj = new Jmfl();
				obj.setJmflmc(rs.getString("fwywfwmc"));
				obj.setJmfldm(rs.getString("fwywfwdm"));
				list.add(obj);
			}
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return list;
	}	
	
	/**
	 * 文化产业
	 * @param 
	 * @return list
	 * @throws BaseException 
	 */
	public List getWhdm() throws BaseException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBResource.getConnection();
			String sql = "select whsydwlxdm,whsydwlxmc  from dmdb.sf_dm_whsydwlx";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Jmfl obj = new Jmfl();
				obj.setJmflmc(rs.getString("whsydwlxmc"));
				obj.setJmfldm(rs.getString("whsydwlxdm"));
				list.add(obj);
			}
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return list;
	}	
	
	/**
	 * 经认定的动漫企业备案事项具体描述
	 * @param 
	 * @return list
	 * @throws BaseException 
	 */
	public List getDmdm() throws BaseException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBResource.getConnection();
			String sql = "select dmqylxdm,dmqylxmc  from dmdb.sf_dm_dmqylx";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Jmfl obj = new Jmfl();
				obj.setJmflmc(rs.getString("dmqylxmc"));
				obj.setJmfldm(rs.getString("dmqylxdm"));
				list.add(obj);
			}
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return list;
	}
	
	/**
	 * 获取序号
	 * @param 
	 * @return basqbh
	 * @throws BaseException 
	 */
	public  String getSequence() throws BaseException{
		String sequence = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DBResource.getConnection();
			String sql = "SELECT SFDB.SEQ_SF_QYSDSJMSBAJL.NEXTVAL FROM DUAL ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				sequence = rs.getString(1);
			}
			System.out.println("===QysdsUtil===getSequence==="+sequence);
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return sequence;
	}
	
	/**
	 * 生成备案申请编号
	 * @param basqwsh：备案文书号；sqzt：申请状态; czr：操作人
	 * @return basqbh
	 * @throws BaseException 
	 */
	public boolean updateSqzt(String basqwsh, String sqzt,String czr) throws BaseException{
		boolean czzt = false;
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DBResource.getConnection();
			String updateSql = "UPDATE sfdb.sf_jl_qysdsjmsbajl t " +
					"SET t.sqzt = '"+sqzt+"',t.tjr = '"+czr+"',t.tjsj = SYSDATE,t.lrr = '"+czr+"',t.lrrq = SYSDATE " +
					"WHERE t.basqwsh = '"+basqwsh+"'";
			System.out.println("===QysdsUtil===updateSqzt==="+updateSql);
			pst = conn.prepareStatement(updateSql);
			pst.executeUpdate();
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return czzt;
	}
	
	public static void main(String args[]){
		QysdsUtil qyds = new QysdsUtil();
		String qxdm = "1900";
		String band = "2009";
		String basqwsh = "062008000001";
		String sqzt = "3";
		String czr = "yx";
		try {
			qyds.getSequence();
			HashMap map =(HashMap)qyds.getBasqbh(qxdm, band);
			qyds.updateSqzt(basqwsh, sqzt, czr);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}