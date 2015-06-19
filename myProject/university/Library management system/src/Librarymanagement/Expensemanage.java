package Librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Expensemanage {

	/**
	 * @param args
	 */
	public Expensemanage(){}
	public void Expensemanage(String readId,double account,char identify,double outdays){
	
	//public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		//String readId="82112";
		//double account=0;

	//	char identify='s';
	//	double outdays=0;
		//读取当前归还的学生（老师）的学号或工号，找到ta的当前欠费;
		
		//读取此人的先前欠费；
		try { 
	          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序    
	       
	    
	          Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/library","root","58583130075"); 
	          outdays=Math.abs(outdays);//读取这个人归还的这本书的超期天数（取绝对值）;
	          account=account+outdays*0.1;//欠费=欠费+超期天数*0.1;
	          
	          //读取身份（s,还是t）
	          if(identify=='s'){
	        	  PreparedStatement Sta=connect.prepareStatement("UPDATE 学生 set 欠费数额="+account+"  where 学生学号='"+readId+"';");
	        	  Sta.executeUpdate(); 
	          }
	          if(identify=='t'){
	        	  PreparedStatement Sta=connect.prepareStatement("UPDATE 教师 set 欠费数额="+account+"  where 教师工号='"+readId+"';");
	        	  Sta.executeUpdate(); 
	          }//写回数据库
		
		
		
		
		}
		catch (Exception e) { 
	          e.printStackTrace(); 
	    } 

		
	}

}
