package com.lscdz.qysds.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class GetXhUtil {
	public static String getXh(Connection conn){
		String sql = "select SPDB.SEQ_SP_XH.nextVal from dual ";
		//Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//conn = ConnFactory.getServerConn();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psmt!=null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			/*if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
		}
		return "";
	}
	public static int getMaxXh(String field,String tableName,String sqlWhere) throws FrameException{
		String sql = " SELECT MAX("+field+") FROM "+tableName+"  "+sqlWhere;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = ResourceManager.getConnectionEx1();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				return rs.getInt(1)+1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: "+tableName);
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(psmt!=null){
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
