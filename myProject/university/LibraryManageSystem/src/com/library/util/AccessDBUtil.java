package com.library.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;

public class AccessDBUtil {
	/**
	 * 关闭数据库资源
	 * @param st
	 * @param rs
	 * @param conn
	 */
	public static void closeDBResource(Statement st,ResultSet rs,Connection conn){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
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
}
