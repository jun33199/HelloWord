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
		// TODO �Զ����ɵķ������
		
		//String readId="82112";
		//double account=0;

	//	char identify='s';
	//	double outdays=0;
		//��ȡ��ǰ�黹��ѧ������ʦ����ѧ�Ż򹤺ţ��ҵ�ta�ĵ�ǰǷ��;
		
		//��ȡ���˵���ǰǷ�ѣ�
		try { 
	          Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������    
	       
	    
	          Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/library","root","58583130075"); 
	          outdays=Math.abs(outdays);//��ȡ����˹黹���Ȿ��ĳ���������ȡ����ֵ��;
	          account=account+outdays*0.1;//Ƿ��=Ƿ��+��������*0.1;
	          
	          //��ȡ��ݣ�s,����t��
	          if(identify=='s'){
	        	  PreparedStatement Sta=connect.prepareStatement("UPDATE ѧ�� set Ƿ������="+account+"  where ѧ��ѧ��='"+readId+"';");
	        	  Sta.executeUpdate(); 
	          }
	          if(identify=='t'){
	        	  PreparedStatement Sta=connect.prepareStatement("UPDATE ��ʦ set Ƿ������="+account+"  where ��ʦ����='"+readId+"';");
	        	  Sta.executeUpdate(); 
	          }//д�����ݿ�
		
		
		
		
		}
		catch (Exception e) { 
	          e.printStackTrace(); 
	    } 

		
	}

}
