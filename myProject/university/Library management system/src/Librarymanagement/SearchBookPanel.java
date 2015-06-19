package Librarymanagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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


	public class  SearchBookPanel extends JFrame
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
		  JTextField jtf1,jtf2,jtf3,jtf4,jtf5;//输入框
		  JTextArea  jta;
		  JButton buttonInput;
		  
		  

		//	}


	   public SearchBookPanel()
		  {  
			  frame = new JFrame("图书查询");  
			  jtf1 = new JTextField("输入图书编号",24);//输入图书编号
			  jtf1.setEditable(true);
			  jtf2 = new JTextField("输入图书名",24);//输入图书名
			  jtf2.setEditable(true);
			  jtf3 = new JTextField("输入出版社",24);//输入出版社
			  jtf3.setEditable(true);
			  jtf4 = new JTextField("输入作者名",24);//输入作者名
			  jtf4.setEditable(true);
			  jtf5 = new JTextField("输入类别",24);//输入类别
			  jtf5.setEditable(true);

			  
			  buttonInput= new JButton ("查询");
			  buttonInput.setSize(5,3);
			  buttonInput.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(7,1));
              panel2.add(jtf1);
              panel2.add(jtf2);
              panel2.add(jtf3);
              panel2.add(jtf4);
              panel2.add(jtf5);
			  panel3=new JPanel();
			  panel3.setLayout(new FlowLayout());
              panel3.add(buttonInput);
              panel1.add(panel2,"Center");
              panel1.add(panel3,"South");
			  frame.getContentPane().add( panel1);
			  frame.setDefaultCloseOperation(3);//关闭窗口
			    // frame.pack();//调节窗口大小
			  frame.show();//显示窗口
			  frame.setSize( 390, 200 );
			  frame.setVisible( true );
			  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			  
			  try { 
					Connection connect = DriverManager.getConnection( 
							 "jdbc:mysql://localhost:3306/mysql","root","admin"); 
					PreparedStatement Sta=connect.prepareStatement("update 借阅表  set 剩余天数= 借出时间-"+BorrowBookPanel.getSystemDate()+"+30   where 借出时间-"+BorrowBookPanel.getSystemDate()+"+30 <>剩余天数");// 
					Sta.executeUpdate(); 
			    } 
			    catch (Exception e) { 
			          e.printStackTrace(); 
			    }
		  }
	   public void DateInput(){
			 // public void SearchBook(boolean[] conass,String stcondition1,String stcondition2,String stcondition3,String stcondition4,String stcondition5){
					// TODO 自动生成的方法存根
			
					String condition1,condition2,condition3,condition4,condition5;
					//传来的可能是书号、书名、作者、出版社、类别的组合
					//boolean conass[]={false,false,true,true,false};
					
					//update all 剩余天数+1 from  图书表  where  当前日期-借出日期<>剩余天数
					
					

					String stcondition1=this.jtf1.getText();
					String stcondition2=this.jtf2.getText();
					String stcondition3=this.jtf3.getText();
					String stcondition4=this.jtf4.getText();
					String stcondition5=this.jtf5.getText();
									
					if (stcondition1.length()!=0)   
						condition1 = " and " +"编号"+ " = '"+stcondition1+"'";//最后一个变量放的是传来的
					else
						condition1 = "";
					
					if (stcondition2.length()!=0)  
						condition2 = " and " +"书名"+ " = '"+stcondition2+"'";
					else
						condition2 = "";
					
					if (stcondition3.length()!=0)  
						condition3 = " and " +"出版社"+ " = '"+stcondition3+"'";
					else
						condition3 = "";
					
					if (stcondition4.length()!=0)  
						condition4 = " and " +"作者"+ " = '"+stcondition4+"'";
					else
						condition4 = "";
					
					if (stcondition5.length()!=0)  
						condition5 = " and " +"类别"+ " = '"+stcondition5+"'";
					else
						condition5 = "";
					

					try { 
						
						Connection connect = DriverManager.getConnection( 
								"jdbc:mysql://localhost:3306/mysql","root","admin"); 
						Statement stmt = connect.createStatement(); 
						ResultSet rs = stmt.executeQuery("select * from 图书表  where true "+condition1+condition2+condition3+condition4+condition5+";");     
						String output="查询结果：\n";
						if(stcondition1.length()+stcondition2.length()+stcondition3.length()+stcondition4.length()+stcondition5.length()==0)
							output="查询条件不能为空！";
						else{
							while (rs.next()) { 
							
								output=output+rs.getString("编号")+"   "+rs.getString("书名")+"   "+rs.getString("出版社")
									+"   "+rs.getString("作者")+"   "+rs.getString("类别")+"   "+rs.getString("价格")
									+"   "+rs.getString("库存")+"   "+rs.getString("索引卡号");
							//图书编号（编号） 书名  出版社  作者  类别  价格  库存数目  索引卡号 
							} 
							String str="查询结果（如果为空，则为查找的图书不存在）";
							 
						}
						JOptionPane.showMessageDialog( null, output);
					}
					catch (Exception e) { 
					      System.out.print("get data error!"); 
					      e.printStackTrace(); 
					} 
			
		        // }
				
		  }
	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
		 
		  
		  
	    try//故障处理
	    {
	    	String str = paramActionEvent.getActionCommand();//接受监听器传过来的字符串

	    if (str=="查询")
	    {
	    	DateInput();
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
	  }*/
/*
	  public static void main(String[] paramArrayOfString)
	  {
	    new SearchBookPanel();
	  }*/
	}


