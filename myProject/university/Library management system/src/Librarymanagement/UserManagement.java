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
	  implements ActionListener//�����������I��
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
			JTextField [] jtf;//�����
			JTextArea  jta;
			JButton buttonInput;
			

	   public UserManagement()
		  {  
		   		frame = new JFrame("�û��������");  
		   		buttonInput =new JButton("ע��");
		   		buttonInput.addActionListener(this);
		   		label1=new Label("            ��    ��     ע    ��");
		   		Font font=new  Font("Serief", Font.BOLD, 22);
			    label1.setFont(font);
			  	this.mainMenu = new JMenuBar();
			    this.registerMenu = new JMenu("ע��");
			    this.cancelMenu = new JMenu("����");
			    this.rSMItem = new JMenuItem("ϵͳ����Ա(R)");
			    this.rSMItem.addActionListener(this);
			    this.rBMItem = new JMenuItem("ͼ�����Ա(R)");
			    this.rBMItem.addActionListener(this);
			    this.rSItem = new JMenuItem("ѧ��(R)");
			    this.rSItem.addActionListener(this);
			    this.rTItem = new JMenuItem("��ʦ(R)");
			    this.rTItem.addActionListener(this);
			    this.cSMItem = new JMenuItem("ϵͳ����Ա(C)");
			    this.cSMItem.addActionListener(this);
			    this.cBMItem = new JMenuItem("ͼ�����Ա(C)");
			    this.cBMItem.addActionListener(this);
			    this.cSItem = new JMenuItem("ѧ��(C)");
			    this.cSItem.addActionListener(this);
			    this.cTItem = new JMenuItem("��ʦ(C)");
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
			    	label[0].setText("��  ��:");
			    	label[1].setText("��  ��:");
			    	label[2].setText("Ȩ  ��:");
			    	label[3].setText("��  ��:");
			    panel1=new JPanel();
			    //panel1.setVisible( false );
			    panel1.setLayout(new BorderLayout());
			    this.panel1.add(this.mainMenu, "North");
			    panel3.add(label1,"North");
			    panel3.add(panel2,"Center");
			    this.panel1.add(panel3, "Center");
			    this.panel1.add(panel4, "South");
			    frame.getContentPane().add( panel1);
			    frame.show();//��ʾ����
			    frame.setSize( 300, 250 );
			    frame.setVisible( true );
			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		  }
		  public void DateUpdate(Boolean b){
				
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
				      s1=this.jtf[0].getText();
				      ResultSet rs = stmt.executeQuery("select * from ����Ա where ���='"+s1+"';"); 
				      
				     //String s= "";
				    // s=this.jtf1.getText();
				      //int rs1=stmt1.executeUpdate("INSERT INTO book VALUES('134','135')");
				         if (rs.next() && b==false)
				      {
				    	//  int a=Integer.parseInt(this.jtf6.getText());
					      PreparedStatement statement=connect.prepareStatement("delete from ����Ա  where  ���='"+s1+"';"); 
											statement.executeUpdate();   
				      }
				      if(b==true)
				      {
					      PreparedStatement statement=connect.prepareStatement("INSERT INTO ����Ա VALUES(?,?,?,?)"); 
							statement.setString(1,this.jtf[0].getText()); 
							statement.setString(2,this.jtf[1].getText()); 
							statement.setString(3,this.jtf[2].getText()); 
							statement.setString(4,this.jtf[3].getText()); 
							statement.executeUpdate(); 
				    }
				      while (rs.next()) { 
				    	  System.out.println(rs.getString("���")+rs.getString("����")); 
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

	    if (str=="ϵͳ����Ա(R)")
	    {
	    	label1.setText("            ��    ��     ע    ��");
	    	buttonInput.setText("ע��");
	    	jtf[2].setText("ϵͳ����Ա");
	    	panel[1].setVisible( true );
	    	panel[3].setVisible(true );
	    	
	    	//DateUpdate(true);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="ͼ�����Ա(R)")
	    {
	    	label1.setText("            ��    ��     ע    ��");
	    	buttonInput.setText("ע��");
	    	jtf[2].setText("ͼ�����Ա");
	    	panel[1].setVisible( true  );
	    	panel[3].setVisible(true  );
	    	//DateUpdate(true);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }

	    if (str=="ϵͳ����Ա(C)")
	    {
	    	label1.setText("              ��           ��");
	    	buttonInput.setText("����");
	    	jtf[2].setText("ϵͳ����Ա");
	    	panel[1].setVisible( false );
	    	panel[3].setVisible( false );
	    	//DateUpdate(false);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="ͼ�����Ա(C)")
	    {
	    	label1.setText("              ��           ��");
	    	buttonInput.setText("����");
	    	jtf[2].setText("ͼ�����Ա");
	    	panel[1].setVisible( false );
	    	panel[3].setVisible( false );
	    	//DateUpdate(false);
	    	//putString();
	    	//JDBCBookList("admin","admin");
	    }
	    if (str=="ע��")
	    {
	    	DateUpdate(true);
	    }
	    if (str=="����")
	    {
	    	DateUpdate(false);
	    }
	    }//���ϴ������
	    catch (ArithmeticException localArithmeticException)
	    {
	       jtf[0].setText("overflow");

	    }
	  }

	}


