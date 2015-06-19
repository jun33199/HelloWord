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




	public class  NewBookInput extends JFrame
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
		  JButton buttonInput;


				
		//	}


	   public NewBookInput()
		  {  
			  frame = new JFrame("新书录入界面");  
			  jtf1 = new JTextField("",124);
			  jtf1.setEditable(true);
			  jtf2 = new JTextField("",124);
			  jtf2.setEditable(true);
			  jtf3 = new JTextField("",124);
			  jtf3.setEditable(true);
			  jtf4 = new JTextField("",124);
			  jtf4.setEditable(true);
			  jtf5 = new JTextField("",124);
			  jtf5.setEditable(true);
			  jtf6 = new JTextField("",124);
			  jtf6.setEditable(true);
			  jtf7 = new JTextField("",124);
			  jtf7.setEditable(true);

			  buttonInput= new JButton ("录入");
			  buttonInput.setSize(5,3);
			  buttonInput.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(7,2));
			  Label label1= new Label("      图   书   编   号:");
			  panel2.add(label1);
			  panel2.add(jtf1);
			  Label label2= new Label("      图        书      名:");
			  panel2.add(label2);
              panel2.add(jtf2);
              Label label3= new Label("      出        版      社:");
              panel2.add(label3);
              panel2.add(jtf3);
              Label label4= new Label("      作        者      名:");
              panel2.add(label4);
              panel2.add(jtf4);
              Label label5= new Label("       价             格:");
              panel2.add(label5);
              panel2.add(jtf5);
              Label label6= new Label("      录   入   数   量:");
              panel2.add(label6);
              panel2.add(jtf6);
              Label label7= new Label("      索   引  卡    号:");
              panel2.add(label7);
              panel2.add(jtf7);
			  panel3=new JPanel();
			  panel3.setLayout(new FlowLayout());
              panel3.add(buttonInput);
              Label label= new Label("                  新      书      录    入\n\n");
              panel1.add(label,"North");
              panel1.add(panel2,"Center");
              panel1.add(panel3,"South");
			  frame.getContentPane().add( panel1);
			  frame.setDefaultCloseOperation(3);//关闭窗口
			    // frame.pack();//调节窗口大小
			  frame.show();//显示窗口
			  frame.setSize( 300, 200 );
			  frame.setVisible( true );
			  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		  }
		  public void DateInput(){
				
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
				      String s1;
				      s1=this.jtf1.getText();
				      ResultSet rs = stmt.executeQuery("select * from 图书表 where 编号='"+s1+"';"); 
				      
				     //String s= "";
				    // s=this.jtf1.getText();
				      //int rs1=stmt1.executeUpdate("INSERT INTO book VALUES('134','135')");
				      if (rs.next())
				      {
				    	  int a=Integer.parseInt(this.jtf6.getText());
					      PreparedStatement statement=connect.prepareStatement("update  图书表 set 库存=库存+"+a+" where 编号='"+s1+"';"); 
											statement.executeUpdate();   
				      }
				      else
				      {
					      PreparedStatement statement=connect.prepareStatement("INSERT INTO 图书表 VALUES(?,?,?,?,?,?,?,?)"); 
							statement.setString(1,this.jtf1.getText()); 
							statement.setString(2,this.jtf2.getText()); 
							statement.setString(3,this.jtf3.getText()); 
							statement.setString(4,this.jtf4.getText()); 
							statement.setString(5,this.jtf5.getText()); 
							statement.setInt(6,Integer.parseInt(this.jtf6.getText())); //
							statement.setString(7,this.jtf7.getText()); 
							statement.setString(8,"124"); 
							statement.executeUpdate(); 
				    }
				      while (rs.next()) { 
				    	  System.out.println(rs.getString("编号")+rs.getString("库存量")); 
				      	} 
				    } 
				    catch (Exception e2) { 
				      System.out.print("get data error!"); 
				      e2.printStackTrace(); 
				    } 
				  }
	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
	    try//故障处理
	    {
	      String str = paramActionEvent.getActionCommand();//接受监听器传过来的字符串

	    if (str=="录入")
	    {
	    	DateInput();
	    	//putString();
	    	//JDBCBookList("admin","admin");
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

/*	  public static void main(String[] paramArrayOfString)
	  {
	    new Library();
	  }*/
	}


