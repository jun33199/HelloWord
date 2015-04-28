package com.ttsoft.bjtax.smsb.gdzys.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;

public class GdzysGy {
	
	
	//获取申报序列号
	public static String getSBXLH(){
		Connection conn = null;
		PreparedStatement ps = null;
		String sbxlh = "";
		String sql = " SELECT SBDB.SEQ_SB_GDZYS_SBBXLH.nextval val FROM DUAL";
		try {
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				java.text.NumberFormat nf=new java.text.DecimalFormat("00000000");
				sbxlh = nf.format(rs.getLong("val"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	   	     SfDBResource.freeConnection(conn);
	       }
		return sbxlh;
	}
	
}
