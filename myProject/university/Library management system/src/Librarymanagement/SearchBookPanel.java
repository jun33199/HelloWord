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
		  JTextField jtf1,jtf2,jtf3,jtf4,jtf5;//�����
		  JTextArea  jta;
		  JButton buttonInput;
		  
		  

		//	}


	   public SearchBookPanel()
		  {  
			  frame = new JFrame("ͼ���ѯ");  
			  jtf1 = new JTextField("����ͼ����",24);//����ͼ����
			  jtf1.setEditable(true);
			  jtf2 = new JTextField("����ͼ����",24);//����ͼ����
			  jtf2.setEditable(true);
			  jtf3 = new JTextField("���������",24);//���������
			  jtf3.setEditable(true);
			  jtf4 = new JTextField("����������",24);//����������
			  jtf4.setEditable(true);
			  jtf5 = new JTextField("�������",24);//�������
			  jtf5.setEditable(true);

			  
			  buttonInput= new JButton ("��ѯ");
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
			  frame.setDefaultCloseOperation(3);//�رմ���
			    // frame.pack();//���ڴ��ڴ�С
			  frame.show();//��ʾ����
			  frame.setSize( 390, 200 );
			  frame.setVisible( true );
			  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			  
			  try { 
					Connection connect = DriverManager.getConnection( 
							 "jdbc:mysql://localhost:3306/mysql","root","admin"); 
					PreparedStatement Sta=connect.prepareStatement("update ���ı�  set ʣ������= ���ʱ��-"+BorrowBookPanel.getSystemDate()+"+30   where ���ʱ��-"+BorrowBookPanel.getSystemDate()+"+30 <>ʣ������");// 
					Sta.executeUpdate(); 
			    } 
			    catch (Exception e) { 
			          e.printStackTrace(); 
			    }
		  }
	   public void DateInput(){
			 // public void SearchBook(boolean[] conass,String stcondition1,String stcondition2,String stcondition3,String stcondition4,String stcondition5){
					// TODO �Զ����ɵķ������
			
					String condition1,condition2,condition3,condition4,condition5;
					//�����Ŀ�������š����������ߡ������硢�������
					//boolean conass[]={false,false,true,true,false};
					
					//update all ʣ������+1 from  ͼ���  where  ��ǰ����-�������<>ʣ������
					
					

					String stcondition1=this.jtf1.getText();
					String stcondition2=this.jtf2.getText();
					String stcondition3=this.jtf3.getText();
					String stcondition4=this.jtf4.getText();
					String stcondition5=this.jtf5.getText();
									
					if (stcondition1.length()!=0)   
						condition1 = " and " +"���"+ " = '"+stcondition1+"'";//���һ�������ŵ��Ǵ�����
					else
						condition1 = "";
					
					if (stcondition2.length()!=0)  
						condition2 = " and " +"����"+ " = '"+stcondition2+"'";
					else
						condition2 = "";
					
					if (stcondition3.length()!=0)  
						condition3 = " and " +"������"+ " = '"+stcondition3+"'";
					else
						condition3 = "";
					
					if (stcondition4.length()!=0)  
						condition4 = " and " +"����"+ " = '"+stcondition4+"'";
					else
						condition4 = "";
					
					if (stcondition5.length()!=0)  
						condition5 = " and " +"���"+ " = '"+stcondition5+"'";
					else
						condition5 = "";
					

					try { 
						
						Connection connect = DriverManager.getConnection( 
								"jdbc:mysql://localhost:3306/mysql","root","admin"); 
						Statement stmt = connect.createStatement(); 
						ResultSet rs = stmt.executeQuery("select * from ͼ���  where true "+condition1+condition2+condition3+condition4+condition5+";");     
						String output="��ѯ�����\n";
						if(stcondition1.length()+stcondition2.length()+stcondition3.length()+stcondition4.length()+stcondition5.length()==0)
							output="��ѯ��������Ϊ�գ�";
						else{
							while (rs.next()) { 
							
								output=output+rs.getString("���")+"   "+rs.getString("����")+"   "+rs.getString("������")
									+"   "+rs.getString("����")+"   "+rs.getString("���")+"   "+rs.getString("�۸�")
									+"   "+rs.getString("���")+"   "+rs.getString("��������");
							//ͼ���ţ���ţ� ����  ������  ����  ���  �۸�  �����Ŀ  �������� 
							} 
							String str="��ѯ��������Ϊ�գ���Ϊ���ҵ�ͼ�鲻���ڣ�";
							 
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
		 
		 
		  
		  
	    try//���ϴ���
	    {
	    	String str = paramActionEvent.getActionCommand();//���ܼ��������������ַ���

	    if (str=="��ѯ")
	    {
	    	DateInput();
	    }

	    }//���ϴ������
	    catch (ArithmeticException localArithmeticException)
	    {
	       jtf1.setText("overflow");
	    }
	  }
	/*  public  void putString()
	  {
		 // data = new Object[num][5];//��ŷ����µ��鼮��Ϣ

		   // Object [] clumnNames = {"���","���� ","����","������","����"};

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


