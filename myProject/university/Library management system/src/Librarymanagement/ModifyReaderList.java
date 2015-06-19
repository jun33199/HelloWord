package Librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ModifyReaderList {

	//public static void main(String[] args) {

	public void ModifyReaderList(char identify,String id){
		//检查要除去的借阅者信息，如果欠费了，先要缴费
		
		double expense=-1;
		
		try { 
	          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序    
	          Connection connect = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/mysql","root","admin");  
		
	          Statement sta = connect.createStatement();  
	          ResultSet rs = null;
	          if(identify=='s')
	        	  rs = sta.executeQuery("select 欠费数额  from 学生  where 学生学号 = ''"+id+"';");
	          if(identify=='t')
	        	  rs = sta.executeQuery("select 欠费数额  from 教师  where 教师工号 = ''"+id+"';");
		
		
	          if (rs.next()) { 
	        	  expense=rs.getDouble("欠费数额"); //寻找是否有想要的书
	          } 
	          if(expense!=0){
	        	  JOptionPane.showMessageDialog( null, "要清除的借阅者有欠费，必须先缴费，缴费结束后点确定。");
	        	  PreparedStatement Sta4 = null;
	        	  if(identify=='s')
	        		  Sta4=connect.prepareStatement("UPDATE 学生 set 欠费数额=0  where 学生学号='"+id+"';");// 
	        	  if(identify=='t')
	        		  Sta4=connect.prepareStatement("UPDATE 教师 set 欠费数额=0  where 教师工号='"+id+"';");
	        	  Sta4.executeUpdate(); 
	                           //打印欠费expense，并提示要缴费才能消去该信息;
	          }
	          else if(expense==-1){
	        	  
	          }
	          else{
	        	  PreparedStatement sta2 = null;
	        	  if(identify=='s'){
			          sta2 = connect.prepareStatement("delete 学生  where 读者号='"+id+"';");
	        	  }
	        	  if(identify=='t'){
	        		  sta2 = connect.prepareStatement("delete 教师  where 读者号='"+id+"';");
	        	  }
	        	  sta2.executeUpdate();
	        	  PreparedStatement Sta3=connect.prepareStatement("delete 借阅表 where 读者号='"+id+"';");//
	        	  Sta3.executeUpdate();
	        		  
	          }
		
		} 
	    catch (Exception e) { 
	          e.printStackTrace(); 
	    } 

	}

}
