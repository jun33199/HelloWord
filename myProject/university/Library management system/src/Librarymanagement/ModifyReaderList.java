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
		//���Ҫ��ȥ�Ľ�������Ϣ�����Ƿ���ˣ���Ҫ�ɷ�
		
		double expense=-1;
		
		try { 
	          Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������    
	          Connection connect = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/mysql","root","admin");  
		
	          Statement sta = connect.createStatement();  
	          ResultSet rs = null;
	          if(identify=='s')
	        	  rs = sta.executeQuery("select Ƿ������  from ѧ��  where ѧ��ѧ�� = ''"+id+"';");
	          if(identify=='t')
	        	  rs = sta.executeQuery("select Ƿ������  from ��ʦ  where ��ʦ���� = ''"+id+"';");
		
		
	          if (rs.next()) { 
	        	  expense=rs.getDouble("Ƿ������"); //Ѱ���Ƿ�����Ҫ����
	          } 
	          if(expense!=0){
	        	  JOptionPane.showMessageDialog( null, "Ҫ����Ľ�������Ƿ�ѣ������Ƚɷѣ��ɷѽ������ȷ����");
	        	  PreparedStatement Sta4 = null;
	        	  if(identify=='s')
	        		  Sta4=connect.prepareStatement("UPDATE ѧ�� set Ƿ������=0  where ѧ��ѧ��='"+id+"';");// 
	        	  if(identify=='t')
	        		  Sta4=connect.prepareStatement("UPDATE ��ʦ set Ƿ������=0  where ��ʦ����='"+id+"';");
	        	  Sta4.executeUpdate(); 
	                           //��ӡǷ��expense������ʾҪ�ɷѲ�����ȥ����Ϣ;
	          }
	          else if(expense==-1){
	        	  
	          }
	          else{
	        	  PreparedStatement sta2 = null;
	        	  if(identify=='s'){
			          sta2 = connect.prepareStatement("delete ѧ��  where ���ߺ�='"+id+"';");
	        	  }
	        	  if(identify=='t'){
	        		  sta2 = connect.prepareStatement("delete ��ʦ  where ���ߺ�='"+id+"';");
	        	  }
	        	  sta2.executeUpdate();
	        	  PreparedStatement Sta3=connect.prepareStatement("delete ���ı� where ���ߺ�='"+id+"';");//
	        	  Sta3.executeUpdate();
	        		  
	          }
		
		} 
	    catch (Exception e) { 
	          e.printStackTrace(); 
	    } 

	}

}
