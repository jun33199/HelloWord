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
		  JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7;//�����
		  JTextArea  jta;
		  JButton buttonInput;


				
		//	}


	   public NewBookInput()
		  {  
			  frame = new JFrame("����¼�����");  
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

			  buttonInput= new JButton ("¼��");
			  buttonInput.setSize(5,3);
			  buttonInput.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(7,2));
			  Label label1= new Label("      ͼ   ��   ��   ��:");
			  panel2.add(label1);
			  panel2.add(jtf1);
			  Label label2= new Label("      ͼ        ��      ��:");
			  panel2.add(label2);
              panel2.add(jtf2);
              Label label3= new Label("      ��        ��      ��:");
              panel2.add(label3);
              panel2.add(jtf3);
              Label label4= new Label("      ��        ��      ��:");
              panel2.add(label4);
              panel2.add(jtf4);
              Label label5= new Label("       ��             ��:");
              panel2.add(label5);
              panel2.add(jtf5);
              Label label6= new Label("      ¼   ��   ��   ��:");
              panel2.add(label6);
              panel2.add(jtf6);
              Label label7= new Label("      ��   ��  ��    ��:");
              panel2.add(label7);
              panel2.add(jtf7);
			  panel3=new JPanel();
			  panel3.setLayout(new FlowLayout());
              panel3.add(buttonInput);
              Label label= new Label("                  ��      ��      ¼    ��\n\n");
              panel1.add(label,"North");
              panel1.add(panel2,"Center");
              panel1.add(panel3,"South");
			  frame.getContentPane().add( panel1);
			  frame.setDefaultCloseOperation(3);//�رմ���
			    // frame.pack();//���ڴ��ڴ�С
			  frame.show();//��ʾ����
			  frame.setSize( 300, 200 );
			  frame.setVisible( true );
			  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		  }
		  public void DateInput(){
				
			//	public static void main(String args[]) { 
				    try { 
				      Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������    
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
				           //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û��������� 
				 
				      System.out.println("Success connect Mysql server!"); 
				      Statement stmt = connect.createStatement(); 
				     // Statement stmt1 = connect.createStatement();
				      String s1;
				      s1=this.jtf1.getText();
				      ResultSet rs = stmt.executeQuery("select * from ͼ��� where ���='"+s1+"';"); 
				      
				     //String s= "";
				    // s=this.jtf1.getText();
				      //int rs1=stmt1.executeUpdate("INSERT INTO book VALUES('134','135')");
				      if (rs.next())
				      {
				    	  int a=Integer.parseInt(this.jtf6.getText());
					      PreparedStatement statement=connect.prepareStatement("update  ͼ��� set ���=���+"+a+" where ���='"+s1+"';"); 
											statement.executeUpdate();   
				      }
				      else
				      {
					      PreparedStatement statement=connect.prepareStatement("INSERT INTO ͼ��� VALUES(?,?,?,?,?,?,?,?)"); 
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
				    	  System.out.println(rs.getString("���")+rs.getString("�����")); 
				      	} 
				    } 
				    catch (Exception e2) { 
				      System.out.print("get data error!"); 
				      e2.printStackTrace(); 
				    } 
				  }
	 
	  public void actionPerformed(ActionEvent paramActionEvent)

	  {
		 
	    try//���ϴ���
	    {
	      String str = paramActionEvent.getActionCommand();//���ܼ��������������ַ���

	    if (str=="¼��")
	    {
	    	DateInput();
	    	//putString();
	    	//JDBCBookList("admin","admin");
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
	  }

/*	  public static void main(String[] paramArrayOfString)
	  {
	    new Library();
	  }*/
	}


