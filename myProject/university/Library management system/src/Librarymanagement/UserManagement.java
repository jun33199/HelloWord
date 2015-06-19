package Librarymanagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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




	public class  UserManagement extends JFrame
	  implements ActionListener//定义监听器接I口
	{
			JFrame frame;
			JMenuBar mainMenu;
			JMenu registerMenu,cancelMenu;
			JMenuItem rSMItem,rBMItem,rSItem,rTItem,cSMItem,cBMItem,cSItem,cTItem;
			JPanel [] panel;
			Label [] label;
			Label label1;
			JPanel panel1;
			JPanel panel2;
			JPanel panel3;
			JPanel panel4;
			JPanel panel5;
			JPanel panel6;
			JTextField [] jtf;//输入框
			JTextArea  jta;
			JButton buttonInput;
			

	   public UserManagement()
		  {  
		   		frame = new JFrame("用户管理界面");  
		   		buttonInput =new JButton("注册");
		   		buttonInput.addActionListener(this);
		   		label1=new Label("            用    户     注    册");
		   		Font font=new  Font("Serief", Font.BOLD, 22);
			    label1.setFont(font);
			  	this.mainMenu = new JMenuBar();
			    this.registerMenu = new JMenu("注册");
			    this.cancelMenu = new JMenu("销户");
			    this.rSMItem = new JMenuItem("系统管理员(R)");
			    this.rSMItem.addActionListener(this);
			    this.rBMItem = new JMenuItem("图书管理员(R)");
			    this.rBMItem.addActionListener(this);
			    this.rSItem = new JMenuItem("学生(R)");
			    this.rSItem.addActionListener(this);
			    this.rTItem = new JMenuItem("老师(R)");
			    this.rTItem.addActionListener(this);
			    this.cSMItem = new JMenuItem("系统管理员(C)");
			    this.cSMItem.addActionListener(this);
			    this.cBMItem = new JMenuItem("图书管理员(C)");
			    this.cBMItem.addActionListener(this);
			    this.cSItem = new JMenuItem("学生(C)");
			    this.cSItem.addActionListener(this);
			    this.cTItem = new JMenuItem("老师(C)");
			    this.cTItem.addActionListener(this);
			    this.registerMenu.add(this.rSMItem);
			    this.registerMenu.add(this.rBMItem);
			    this.registerMenu.add(this.rSItem);
			    this.registerMenu.add(this.rTItem);
			    this.cancelMenu.add(this.cSMItem);
			    this.cancelMenu.add(this.cBMItem);
			    this.cancelMenu.add(this.cSItem);
			    this.cancelMenu.add(this.cTItem);
			    this.mainMenu.add(this.registerMenu);
			    this.mainMenu.add(this.cancelMenu);
			    jtf = new JTextField[4];
			    panel=new JPanel[4];
			    label=new Label[4];
			    panel2=new JPanel();
			    panel2.setLayout(new GridLayout(4,1));
			    panel3=new JPanel();
			    panel3.setLayout(new BorderLayout());	
			    panel4=new JPanel();
			    panel4.setLayout(new FlowLayout());
			    panel4.add(buttonInput);
			    for (int i=0;i<4;i++)
			    {
			    	label[i]=new Label();
			    	panel[i]=new JPanel();
			    	jtf[i]=new JTextField("",15);
			    	panel[i].setLayout(new FlowLayout());
			    	panel[i].add(label[i]);
			    	panel[i].add(jtf[i]);
			      	panel2.add(this.panel[i]);
			    }
			    	label[0].setText("编  号:");
			    	label[1].setText("姓  名:");
			    	label[2].setText("权  限:");
			    	label[3].setText("密  码:");
			    panel1=new JPanel();
			    //panel1.setVisible( false );
			    panel1.setLayout(new BorderLayout());
			    this.panel1.add(this.mainMenu, "North");
			    panel3.add(label1,"North");
			    panel3.add(panel2,"Center");
			    this.panel1.add(panel3, "Center");
			    this.panel1.add(panel4, "South");
			    frame.getContentPane().add( panel1);
			    frame.show();//显示窗口
			    frame.setSize( 300, 250 );
			    frame.setVisible( true );
			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		  }
		  public void DateUpdate(Boolean b){
				
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
				      s1=this.jtf[0].getText();
				      ResultSet rs = stmt.executeQuery("select * from 管理员 where 编号='"+s1+"';"); 
				      
				     //String s= "";
				    // s=this.jtf1.getText();
				      //int rs1=stmt1.executeUpdate("INSERT INTO book VALUES('134','135')");
				         if (rs.next() && b==false)
				      {
				    	//  int a=Integer.parseInt(this.jtf6.getText());
					      PreparedStatement statement=connect.prepareStatement("delete from 管理员  where  编号='"+s1+"';"); 
											statement.executeUpdate();   
				      }
				      if(b==true)
				      {
					      PreparedStatement statement=connect.prepareStatement("INSERT INTO 管理员 VALUES(?,?,?,?)"); 
							statement.setString(1,this.jtf[0].getText()); 
							statement.setString(2,this.jtf[1].getText()); 
							statement.setString(3,this.jtf[2].getText()); 
							statement.setString(4,this.jtf[3].getText()); 
							statement.executeUpdate(); 
				    }
				      while (rs.next()) { 
				    	  System.out.println(rs.getString("编号")+rs.getString("姓名")); 
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

	    if (str=="系统管理员(R)")
	    {
	    	label1.setText("            用    户     注    册");
	    	buttonInput.setText("注册");
	    	jtf[2].setText("系统管理员");
	    	panel[1].setVisible( true );
	    	panel[3].setVisible(true );
	    	
	    	//DateUpdate(true);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="图书管理员(R)")
	    {
	    	label1.setText("            用    户     注    册");
	    	buttonInput.setText("注册");
	    	jtf[2].setText("图书管理员");
	    	panel[1].setVisible( true  );
	    	panel[3].setVisible(true  );
	    	//DateUpdate(true);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }

	    if (str=="系统管理员(C)")
	    {
	    	label1.setText("              销           户");
	    	buttonInput.setText("销户");
	    	jtf[2].setText("系统管理员");
	    	panel[1].setVisible( false );
	    	panel[3].setVisible( false );
	    	//DateUpdate(false);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="图书管理员(C)")
	    {
	    	label1.setText("              销           户");
	    	buttonInput.setText("销户");
	    	jtf[2].setText("图书管理员");
	    	panel[1].setVisible( false );
	    	panel[3].setVisible( false );
	    	//DateUpdate(false);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="注册")
	    {
	    	DateUpdate(true);
	    }
	    if (str=="销户")
	    {
	    	DateUpdate(false);
	    }
	    }//故障处理结束
	    catch (ArithmeticException localArithmeticException)
	    {
	       jtf[0].setText("overflow");

	    }
	  }

	}


