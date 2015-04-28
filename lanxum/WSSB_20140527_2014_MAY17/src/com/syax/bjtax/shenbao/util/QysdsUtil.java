package com.syax.bjtax.shenbao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QysdsUtil{
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
	map.put("0210", "sfdb.sf_jl_qysdsjmsba_21");
	map.put("0220", "sfdb.sf_jl_qysdsjmsba_22");
	}
	
	public static String getTableNameByJmbasxdm(String jmbasxdm){
		return (String)map.get(jmbasxdm);
	}

	
	/**
	 * 生成备案申请编号
	 * @param qxdm:区县代码；band：办案年度
	 * @return basqbh
	 * @throws BaseException 
	 */
	public HashMap getBasqbh(String qxdm,String band) throws BaseException{
		HashMap basq = new HashMap() ;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DBResource.getConnection();
			String sql = " select to_char('"+qxdm+"'||'"+band+"'||sxh)  basqwsh, (select '京地税'||wsjc||'减免备企字[' "+
			" from dmdb.gy_dm_swjgzzjg t  where swjgzzjgdm=qxfjdm  and swjgzzjgdm like '"+qxdm+"%'  and rownum=1) "+
			" ||"+band+"||']'||sxh from "+
			" (select trim(decode(to_char(to_number(max(substr(t2.basqbh, -6, 6))) + 1,'000000'), null, '000001', "+
			" to_char(to_number(max(substr(t2.basqbh, -6, 6))) + 1,'000000')))sxh from sfdb.sf_jl_qysdsjmsbajl t2 "+
			" where substr(t2.swjgzzjgdm,0,2) = '"+qxdm+"'  and t2.band = '"+band+"' ) ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				basq.put("basqwsh", rs.getString(1));
				basq.put("basqbh", rs.getString(2));
			}
			//System.out.println("===QysdsUtil===getBasqbh===basqbh==="+basq.get("basqbh")+"===basqwsh==="+basq.get("basqwsh"));
			
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
		return basq;
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