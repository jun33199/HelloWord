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
	  implements ActionListener//�����������I��
	{

		  JFrame frame;
		  JPanel panel;
		  JPanel panel1;
		  JPanel panel2;
		  JPanel panel3;
		  JPanel panel4;
		  JPanel panel5;
		  JPanel panel6;
		  JTextField jtf00,jtf01,jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9,jtf10;//�����
		  JTextArea  jta;
		  JButton buttonInput;


				
		//	}


	    public returnBackPanel()
		  {  
			  frame = new JFrame("�������");  
			  //Label label1 =new Label("��   ��   ��:");
			  jtf00 = new JTextField("����ѧ�Ż��ʦ����",24);//����ѧ�Ż��ʦ����
			  jtf00.setEditable(true);
			 // Label label2 =new Label("��   ��   ��2:");
			  jtf01 = new JTextField("����黹��Ŀ",24);//���������Ŀ
			  jtf01.setEditable(true);
			 // Label label3 =new Label("��   ��   ��3:");
			  jtf1 = new JTextField("�������ǹ黹��ʮ��������",24);//��������ʮ��������
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

			  buttonInput= new JButton ("����黹");
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
			  frame.setDefaultCloseOperation(3);//�رմ���
			    // frame.pack();//���ڴ��ڴ�С
			  frame.show();//��ʾ����
			  frame.setSize( 390, 200 );
			  frame.setVisible( true );
		  }
	   	public static int getSystemDate()
	   	{
			int year,month,day;
			
			Calendar c=Calendar.getInstance();//���ϵͳ��ǰ����
			year=c.get(Calendar.YEAR);
			month=c.get(Calendar.MONTH)+1;//ϵͳ���ڴ�0��ʼ����
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
					          Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������    
					      } 
					    catch (Exception e) { 
					          e.printStackTrace(); 
					    } 
						try { 
							Connection connect = DriverManager.getConnection( "jdbc:mysql://localhost:3306/library","root","58583130075"); 
							Statement stmt = connect.createStatement(); 
							ResultSet rs = stmt.executeQuery("select *  from ������ where ���ߺ�='"+readerId+"';");//
							if(rs.next()){
								mark=rs.getString("���ߺ�");
								identify=rs.getString("������");
							}
							//char judge=judge(identify,requirenum,remainnum);
							//int judge2=15;
							//System.out.println(identify);

							if(mark.length()!=0){//�ж��Ƿ�Ϊ��ѧԺѧ��������ʦ
							//	if(judge=='y'){//�жϴ����Ƿ�賬��ʮ��
									while(requirenum!=0){
										//Ѱ���Ƿ�����Ҫ����
											//��������������Ψһ���ĺ��õ�

												//�����Ǹ��¿�桢���½����ߡ�����һ�����ļ�¼
											
												PreparedStatement Sta1=connect.prepareStatement("delete from ���ı� where ���= '"+bookId[requirenum-1]+"' and ���ߺ�='"+readerId+"';");
												Sta1.executeUpdate(); 
												
												PreparedStatement Sta2=connect.prepareStatement("UPDATE ͼ��� set �����=�����+1  where ���='"+bookId[requirenum-1]+"';");// 
												Sta2.executeUpdate(); 
												PreparedStatement Sta3=connect.prepareStatement("UPDATE ������ set �ѽ�����Ŀ=�ѽ�����Ŀ-1  where ���ߺ�='"+readerId+"';");// 
												Sta3.executeUpdate();
												Statement stmt2 = connect.createStatement(); 
												ResultSet rs2 = stmt2.executeQuery("select ���ʱ��  from ���ı� where ���='"+bookId[requirenum-1]+"' and ���ߺ�='"+readerId+"';");
												i=getSystemDate();
												if(rs2.next()){
													remaintime=rs.getInt("���ʱ��");
												}
												if(i-remaintime<=30)
													JOptionPane.showMessageDialog( null, "�黹�����ɹ���"+bookId[requirenum-1]+"����δ�����黹����");
												else{
													
													Statement stmt3 = connect.createStatement(); 
													if(identify.charAt(0)=='t'){
														ResultSet rs3 = stmt3.executeQuery("select *  from ��ʦ where ��ʦ����='"+readerId+"';");
														if(rs3.next()){
															account=rs.getDouble("Ƿ������");
														}
										
														
													}
													if(identify.charAt(0)=='s'){
														ResultSet rs3 = stmt3.executeQuery("select *  from ѧ��  where ѧ��ѧ��='"+readerId+"';");
														if(rs3.next()){
															account=rs3.getDouble("Ƿ������");
														}

														
													}
													ex.Expensemanage(readerId,account,identify.charAt(0),i-remaintime);
													JOptionPane.showMessageDialog( null, "�黹�����ɹ���"+bookId[requirenum-1]+"�����ѳ����黹����");
												}
												//System.out.println("����ɹ�");
												
										requirenum--;
									}
								}
							//	if(judge=='n')
							//		JOptionPane.showMessageDialog( null, "δʶ����������");
							//	if(judge=='o')
								//	JOptionPane.showMessageDialog( null, "���ѽ���������");
					//		}
						//	else{
						//		JOptionPane.showMessageDialog( null, "�Ҳ�����Ӧ�Ķ��ߺš���ϵͳ������Ժ��ʿ���Ž���");
						//	}
						}//����
						catch(SQLException e) 
						 { 
							e.printStackTrace();
						 }
			} 
					
				
			

	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
	    try//���ϴ���
	    {
	      String str = paramActionEvent.getActionCommand();//���ܼ��������������ַ���

	    if (str=="����黹")
	    {
	    	DateInput();
	    }

	    }//���ϴ������
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


