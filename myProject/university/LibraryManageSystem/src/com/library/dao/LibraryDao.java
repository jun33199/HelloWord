package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.library.util.AccessDBUtil;
import com.library.util.ConnectionFactory;

public class LibraryDao {
	public List doQuery(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=null;
		try {
			sql="";
			conn=ConnectionFactory.getInstance().getConnect();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			AccessDBUtil.closeDBResource(ps, rs, conn);
		}
		return null;
	}
}
