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
		  JButton buttonLogin;
		  JPasswordField jpf;


				
		//	}


	   public Login()
		  {  
			  frame = new JFrame("��¼����");  
			  jtf1 = new JTextField("",124);
			  jtf1.setEditable(true);
			  jpf = new JPasswordField("",124);
			  jpf.setEditable(true);

			  buttonLogin= new JButton ("��¼");
			  buttonLogin.setSize(5,3);
			  buttonLogin.addActionListener(this);
			  panel1=new JPanel();
			  panel1.setLayout(new BorderLayout());
			  panel2=new JPanel();
			  panel2.setLayout(new GridLayout(2,2));
			  panel3=new JPanel();
			  panel3.setLayout(new FlowLayout());
			  Label label1= new Label("      ��         ��:");
			  Label label2= new Label("      ��         ��:");
			  panel2.add(label1);
			  panel2.add(jtf1);
			  panel2.add(label2);
			  panel2.add(jpf);
			  panel1.add(panel2,"Center");
			  panel3.add(buttonLogin);
			  panel1.add(panel3,"South");
			  Label label = new Label("\n\n      �� �� Ա �� ¼\n\n\n");
		       Font font=new  Font("Serief", Font.BOLD, 22);
		        label.setFont(font);

			  panel1.add(label,"North");
			  frame.getContentPane().add( panel1);
			  frame.setDefaultCloseOperation(3);//�رմ���
			    // frame.pack();//���ڴ��ڴ�С
			  frame.show();//��ʾ����
			  frame.setSize( 220, 160 );
			  frame.setVisible( true );
		  }
		  public boolean Identify(){
				
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
				      String s1,s2;
				      s1=jtf1.getText();s2=jpf.getText();
				      ResultSet rs = stmt.executeQuery("select * from ����Ա where ���= '"+s1+"'"+ "and ����='"+s2+"';"); 
				      if(rs.next()) 
				      { 
				    	  
				        System.out.println(rs.getString("���")+rs.getString("����")); 
				        //if(jtf1.getText()==rs.getString("����")+"" && jtf2.getText()==rs.getString("����")+"")
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
		 
	    try//���ϴ���
	    {
	      String str = paramActionEvent.getActionCommand();//���ܼ��������������ַ���

	    if (str=="��¼")
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
	    		JOptionPane.showMessageDialog( null,  "              �û������������"
        			, str, JOptionPane.PLAIN_MESSAGE );
	    	}
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
*/
	  public static void main(String[] paramArrayOfString)
	  {
	    new Login();
	    

	  }
	}


