package com.weizhu.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class Util {
	/**
	 * <pre>
	 * join ","
	 * </pre>
	 */
	public static final Joiner COMMA_JOINER = Joiner.on(",").skipNulls();

	/**
	 * <pre>
	 * split ","
	 * </pre>
	 */
	public static final Splitter COMMA_SPLITTER = Splitter.on(",").omitEmptyStrings();

	public static String getDate(int date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormate = sdf.format(new Date(date * 1000L));
		return dateFormate;
	}

	public static void closeQuietly(ResultSet rs, Statement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO : print to log
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weizhu_test", "root", "");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getDate(java.sql.Date date) {
		
		return (int) (date.getTime()/1000L);
	}
}
