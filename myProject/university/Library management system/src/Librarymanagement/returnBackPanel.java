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




	public class  returnBackPanel extends JFrame
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
		  JTextField jtf00,jtf01,jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10;//输入框
		  JTextArea  jta;
		  JButton buttonInput;


				
		//	}


	    public returnBackPanel()
		  {  
			  frame = new JFrame("还书界面");  
			  //Label label1 =new Label("借   书   号:");
			  jtf00 = new JTextField("输入学号或教师工号",24);//输入学号或教师工号
			  jtf00.setEditable(true);
			 // Label label2 =new Label("借   书   号2:");
			  jtf01 = new JTextField("输入归还数目",24);//输入借书数目
			  jtf01.setEditable(true);
			 // Label label3 =new Label("借   书   号3:");
			  jtf1 = new JTextField("接下来是归还的十本书的书号",24);//接下来是十本书的书号
			  jtf1.setEditable(true);
			  jtf2 = new JTextField("",24);
			  jtf2.setEditable(true);
			  jtf3 = new JTextField("",24);
			  jtf3.setEditable(true);
			  jtf4 = new JTextField("",24);
			  jtf4.setEditable(true);
			  jtf5 = new JTextField("",24);
			  jtf5.setEditable(true);
			  jtf6 = new JTextField("",24);
			  jtf6.setEditable(true);
			  jtf7 = new JTextField("",24);
			  jtf7.setEditable(true);
			  jtf8 = new JTextField("",24);
			  jtf8.setEditable(true);
			  jtf9 = new JTextField("",24);
			  jtf9.setEditable(true);
			  jtf10 = new JTextField("",24);
			  jtf10.setEditable(true);

			  buttonInput= new JButton ("点击归还");
			  buttonInput.setSize(5,3);
			  buttonInput.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(7,1));
			 // panel2.add(label1);
			  
			  panel2.add(jtf00);
			 // panel2.add(label2);
			  panel2.add(jtf01);
			 // panel2.add(label3);
              panel2.add(jtf1);
              panel2.add(jtf2);
              panel2.add(jtf3);
              panel2.add(jtf4);
              panel2.add(jtf5);
              panel2.add(jtf6);
              panel2.add(jtf7);
              panel2.add(jtf8);
              panel2.add(jtf9);
              panel2.add(jtf10);
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
		  }
	   	public static int getSystemDate()
	   	{
			int year,month,day;
			
			Calendar c=Calendar.getInstance();//获得系统当前日期
			year=c.get(Calendar.YEAR);
			month=c.get(Calendar.MONTH)+1;//系统日期从0开始算起
			day=c.get(Calendar.DAY_OF_MONTH);
			return year*10000+month*100+day;
		}
	   	
	   	/*
	   	public char judge (String identify,int requirenum,int remainnum){
	   		if(identify.charAt(0)=='s'){
	   			if(requirenum+remainnum>10){
	   				return 'o';
	   			}
	   			else
	   				return 'y';
	   		}
	   		else if(identify.charAt(0)=='t'){
	   			if(requirenum+remainnum>15){
	   				return 'o';
	   			}
	   			else
	   				return 'y';
	   		}
	   		else{
	   			return 'n';
	   		}
	   	}*/
		  
			   
	   	public void DateInput(){
				   		String readerId = this.jtf00.getText();
				   		int requirenum = Integer.parseInt(this.jtf01.getText()) ;	   		
				   		String bookId[]={this.jtf1.getText(),this.jtf2.getText(),this.jtf3.getText(),
				   					this.jtf4.getText(),this.jtf5.getText(),this.jtf6.getText(),
				   					this.jtf7.getText(),this.jtf9.getText(),this.jtf10.getText()};
						String mark="";
					//	String query="";
						//int i= (int)(Math.random()*991);
						String identify="n";
						int remaintime=0;
						int remainnum=0;
						int i;
						double account=0;
						Expensemanage ex = new Expensemanage();

						try { 
					          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序    
					      } 
					    catch (Exception e) { 
					          e.printStackTrace(); 
					    } 
						try { 
							Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/library","root","58583130075"); 
							Statement stmt = connect.createStatement(); 
							ResultSet rs = stmt.executeQuery("select *  from 借阅者 where 读者号='"+readerId+"';");//
							if(rs.next()){
								mark=rs.getString("读者号");
								identify=rs.getString("读者类");
							}
							//char judge=judge(identify,requirenum,remainnum);
							//int judge2=15;
							//System.out.println(identify);

							if(mark.length()!=0){//判断是否为本学院学生或者老师
							//	if(judge=='y'){//判断此人是否借超过十本
									while(requirenum!=0){
										//寻找是否有想要的书
											//以下是用于制作唯一借阅号用的

												//以下是更新库存、更新借阅者、增加一条借阅记录
											
												PreparedStatement Sta1=connect.prepareStatement("delete from 借阅表 where 书号= '"+bookId[requirenum-1]+"' and 读者号='"+readerId+"';");
												Sta1.executeUpdate(); 
												
												PreparedStatement Sta2=connect.prepareStatement("UPDATE 图书表 set 库存量=库存量+1  where 编号='"+bookId[requirenum-1]+"';");// 
												Sta2.executeUpdate(); 
												PreparedStatement Sta3=connect.prepareStatement("UPDATE 借阅者 set 已借书数目=已借书数目-1  where 读者号='"+readerId+"';");// 
												Sta3.executeUpdate();
												Statement stmt2 = connect.createStatement(); 
												ResultSet rs2 = stmt2.executeQuery("select 借出时间  from 借阅表 where 书号='"+bookId[requirenum-1]+"' and 读者号='"+readerId+"';");
												i=getSystemDate();
												if(rs2.next()){
													remaintime=rs.getInt("借出时间");
												}
												if(i-remaintime<=30)
													JOptionPane.showMessageDialog( null, "归还操作成功："+bookId[requirenum-1]+"该书未超过归还期限");
												else{
													
													Statement stmt3 = connect.createStatement(); 
													if(identify.charAt(0)=='t'){
														ResultSet rs3 = stmt3.executeQuery("select *  from 教师 where 教师工号='"+readerId+"';");
														if(rs3.next()){
															account=rs.getDouble("欠费数额");
														}
										
														
													}
													if(identify.charAt(0)=='s'){
														ResultSet rs3 = stmt3.executeQuery("select *  from 学生  where 学生学号='"+readerId+"';");
														if(rs3.next()){
															account=rs3.getDouble("欠费数额");
														}

														
													}
													ex.Expensemanage(readerId,account,identify.charAt(0),i-remaintime);
													JOptionPane.showMessageDialog( null, "归还操作成功："+bookId[requirenum-1]+"该书已超过归还期限");
												}
												//System.out.println("借书成功");
												
										requirenum--;
									}
								}
							//	if(judge=='n')
							//		JOptionPane.showMessageDialog( null, "未识别借书者身份");
							//	if(judge=='o')
								//	JOptionPane.showMessageDialog( null, "你已借出过多的书");
					//		}
						//	else{
						//		JOptionPane.showMessageDialog( null, "找不到相应的读者号。本系统不对外院人士开放借书");
						//	}
						}//结束
						catch(SQLException e) 
						 { 
							e.printStackTrace();
						 }
			} 
					
				
			

	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
	    try//故障处理
	    {
	      String str = paramActionEvent.getActionCommand();//接受监听器传过来的字符串

	    if (str=="点击归还")
	    {
	    	DateInput();
	    }

	    }//故障处理结束
	    catch (ArithmeticException localArithmeticException)
	    {
	       jtf1.setText("overflow");
	    }
	  }
	  
	  public static void main(String[] paramArrayOfString)
	  {
	  new returnBackPanel();
	  }

	}


