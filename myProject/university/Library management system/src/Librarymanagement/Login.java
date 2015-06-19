package Librarymanagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.lang.Integer;
import java.lang.String;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
//import java.beans.Statement;
import java.util.Calendar;
import java.util.ResourceBundle.Control;
import java.lang.ThreadDeath;
import javax.swing.*;
import java.sql.*;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;




	public class  Login extends JFrame
	  implements ActionListener//定义监听器接I口
	{

		  JFrame frame;
		  JPanel panel;
		  JPanel panel1;
		  JPanel panel2;
		  JPanel panel3;
		  JPanel panel4;
		  JPanel panel5;
		  JPanel panel6;
		  JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7;//输入框
		  JTextArea  jta;
		  JButton buttonLogin;
		  JPasswordField jpf;


				
		//	}


	   public Login()
		  {  
			  frame = new JFrame("登录界面");  
			  jtf1 = new JTextField("",124);
			  jtf1.setEditable(true);
			  jpf = new JPasswordField("",124);
			  jpf.setEditable(true);

			  buttonLogin= new JButton ("登录");
			  buttonLogin.setSize(5,3);
			  buttonLogin.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(2,2));
			  panel3=new JPanel();
			  panel3.setLayout(new FlowLayout());
			  Label label1= new Label("      编         号:");
			  Label label2= new Label("      密         码:");
			  panel2.add(label1);
			  panel2.add(jtf1);
			  panel2.add(label2);
			  panel2.add(jpf);
			  panel1.add(panel2,"Center");
			  panel3.add(buttonLogin);
			  panel1.add(panel3,"South");
			  Label label = new Label("\n\n      管 理 员 登 录\n\n\n");
		       Font font=new  Font("Serief", Font.BOLD, 22);
		        label.setFont(font);

			  panel1.add(label,"North");
			  frame.getContentPane().add( panel1);
			  frame.setDefaultCloseOperation(3);//关闭窗口
			    // frame.pack();//调节窗口大小
			  frame.show();//显示窗口
			  frame.setSize( 220, 160 );
			  frame.setVisible( true );
		  }
		  public boolean Identify(){
				
			//	public static void main(String args[]) { 
				    try { 
				      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序    
				      //Class.forName("org.gjt.mm.mysql.Driver"); 
				     System.out.println("Success loading Mysql Driver!"); 
				    } 
				    catch (Exception e1) { 
				      System.out.print("Error loading Mysql Driver!"); 
				      e1.printStackTrace(); 
				    } 
				    try { 
				      Connection connect = DriverManager.getConnection( 
				          "jdbc:mysql://localhost:3306/mysql","root","admin"); 
				           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码 
				 
				      System.out.println("Success connect Mysql server!"); 
				      Statement stmt = connect.createStatement(); 
				     // Statement stmt1 = connect.createStatement();
				      String s1,s2;
				      s1=jtf1.getText();s2=jpf.getText();
				      ResultSet rs = stmt.executeQuery("select * from 管理员 where 编号= '"+s1+"'"+ "and 密码='"+s2+"';"); 
				      if(rs.next()) 
				      { 
				    	  
				        System.out.println(rs.getString("编号")+rs.getString("密码")); 
				        //if(jtf1.getText()==rs.getString("姓名")+"" && jtf2.getText()==rs.getString("密码")+"")
				        return true;				       	       
				      } 
				          
				    } 
				    catch (Exception e2) { 
				      System.out.print("get data error!"); 
				      e2.printStackTrace(); 
				    }
				    
				    return false;	
				  }
	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
	    try//故障处理
	    {
	      String str = paramActionEvent.getActionCommand();//接受监听器传过来的字符串

	    if (str=="登录")
	    {
	    	boolean b;
	    	//DateInput();
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    	b=Identify();
	    	if(b==true)
	    	{
	    		new UserManagement();
	    		new Library();
	    	}
	    	if(b==false)
	    	{
	    		JOptionPane.showMessageDialog( null,  "              用户名或密码错误"
        			, str, JOptionPane.PLAIN_MESSAGE );
	    	}
	    }

	    }//故障处理结束
	    catch (ArithmeticException localArithmeticException)
	    {
	       jtf1.setText("overflow");

	    }
	  }
	/*  public  void putString()
	  {
		 // data = new Object[num][5];//存放分类下的书籍信息

		   // Object [] clumnNames = {"编号","书名 ","作者","出版社","数量"};

		 // Object [] temp2 = new Object[]{book_read.getNumber(),book_read.getName(),book_read.getAuthor(),book_read.getPress(),book_read.getCount()};   
		  String bookMessage="";
		  bookMessage =jtf1.getText()+"\n"+jta.getText()+"\n"+clumnNames;
		  jta.setText(bookMessage);
	  }
*/
	  public static void main(String[] paramArrayOfString)
	  {
	    new Login();
	    

	  }
	}


