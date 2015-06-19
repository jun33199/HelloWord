package Librarymanagement;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;



public class SystemPanel{
	
	public static void main(String args[]) { 
	    try { 
	      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������    
	      //Class.forName("org.gjt.mm.mysql.Driver"); 
	     System.out.println("Success loading Mysql Driver!"); 
	    } 
	    catch (Exception e) { 
	      System.out.print("Error loading Mysql Driver!"); 
	      e.printStackTrace(); 
	    } 
	    try { 
	      Connection connect = DriverManager.getConnection( 
	          "jdbc:mysql://localhost:3306/mysql","root","admin"); 
	           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û��������� 
	 
	      System.out.println("Success connect Mysql server!"); 
	      Statement stmt = connect.createStatement(); 
	      Statement stmt1 = connect.createStatement();
	      ResultSet rs = stmt.executeQuery("select * from book;"); 
	     int rs1=stmt1.executeUpdate("INSERT INTO book VALUES('47','a')");
	                                                            //user Ϊ�������� 
	      while (rs.next()) { 
	        System.out.println(rs.getString("BookNumber")+rs.getString("BookName")); 
	      } 
	    } 
	    catch (Exception e) { 
	      System.out.print("get data error!"); 
	      e.printStackTrace(); 
	    } 
	  }
	
}
