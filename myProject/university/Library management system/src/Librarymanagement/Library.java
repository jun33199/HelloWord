package Librarymanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import java.beans.Statement;
import java.util.Calendar;
import java.util.ResourceBundle.Control;
import java.lang.ThreadDeath;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
//import javax.swing.WindowConstants;

public class  Library extends JFrame
  implements ActionListener//定义监听器接I口
{

	  JFrame frame;
	  JPanel panel;
	  JPanel panel1;
	  JPanel panel2;
	  JPanel panel3;
	  JPanel panel4;
	  JPanel panel5;
	  JPanel panel6,panel7,panel8,panel9;
	  JPanel [] panelarray;
	  JTextField jtf1,jtf2;//输入框
	 // JTextArea  jta1,jta2,jta3,jta4,jta5,jta6;
	  JTextArea [] jta;
	  JButton [] button;
	  JButton buttonInput;
	  JButton buttonLend;
	  JButton buttonReturn;
	  JButton buttonQuery,buttonIdentify;
      private Label label;
   public Library()
	  {  
		  frame = new JFrame("图书管理系统");  
//下方文本区域
		  jta = new JTextArea[6];
		  button=new JButton[6];
		  panelarray=new JPanel[6];
		  panel=new JPanel();
		  panel.setLayout(new GridLayout(1,6));
		  for (int i=0;i<6;i++)
		  {
			  jta[i]= new JTextArea ();
			  jta[i].setEditable(true);
			  jta[i].setPreferredSize(new Dimension(100, 300));
			  button[i]= new JButton ();
			  button[i].setBackground(Color.lightGray );
			  panelarray[i]=new JPanel();
			  panelarray[i].setLayout(new BorderLayout());
			  panelarray[i].add(button[i],"North");
			  panelarray[i].add(jta[i],"Center");
			  panel.add(panelarray[i]);
		  }
		  button[0].setText("借书号");
		  button[1].setText("姓名");
		  button[2].setText("书号");
		  button[3].setText("书名");
		  button[4].setText("借出时间");
		  button[5].setText("归还时间");
//文本区域代码结束
		  
		  jtf1 = new JTextField("",24);
		  jtf1.setEditable(true);
		  jtf2 = new JTextField("",24);
		  jtf2.setEditable(true);
		  buttonInput= new JButton ("录入");
		  buttonInput.addActionListener(this);
		  buttonLend= new JButton ("借出");
		  buttonLend.addActionListener(this);
		  buttonReturn= new JButton ("归还");
		  buttonReturn.addActionListener(this);
		  buttonQuery= new JButton ("查询");
		  buttonQuery.addActionListener(this);
		  buttonIdentify=new JButton("身份验证");
		  buttonIdentify.setPreferredSize(new Dimension(100, 30));
		  buttonIdentify.addActionListener(this);
		  
		  panel1=new JPanel();
		  panel1.setLayout(new BorderLayout());
		  panel2=new JPanel();
		  panel2.setLayout(new BorderLayout());
		  panel4=new JPanel();
		  panel4.setLayout(new GridLayout(2,1));
		  panel6=new JPanel();
		  panel6.setLayout(new FlowLayout());
		  Label label1 =new Label("借   书   号:");
		  panel6.add(label1);
		  panel6.add(jtf2);
		  panel8=new JPanel();
		  panel8.setLayout(new FlowLayout());
		  panel8.add(buttonIdentify);
		  panel4.add(panel6,"North");
		  panel4.add(panel8,"South");
		  panel5=new JPanel();
		  panel5.setLayout(new FlowLayout());
		  panel5.add(buttonInput);
		  panel5.add(buttonLend);
		  panel5.add(buttonReturn);
		  panel5.add(buttonQuery);
		  Label label2 =new Label("图   书   号:");
		  panel7=new JPanel();
		  panel7.setLayout(new FlowLayout());
		  panel7.add(label2);
		  panel7.add(jtf1);
		  panel3=new JPanel();
		  panel3.setLayout(new BorderLayout());
		  panel3.add(panel5,"South");
		  panel3.add(panel7,"North");
		  panel2.add(panel3,"West");
		  panel2.add(panel4,"East");
		  panel1.add(panel2,"North");
		  panel1.add(panel,"Center");
		  frame.getContentPane().add( panel1);
		  frame.setDefaultCloseOperation(3);//关闭窗口
		    // frame.pack();//调节窗口大小
		  frame.show();//显示窗口
		  frame.setSize( 900, 683 );
		  frame.setVisible( true );
		  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	  }
  


public void actionPerformed(ActionEvent paramActionEvent)

  {
	 
    try//故障处理
    {
      String str = paramActionEvent.getActionCommand();//接受监听器传过来的字符串

    if (str=="录入")
    {
    	new NewBookInput();
    }

    if (str=="借出")
    {	/*Calendar now;
    	now = Calendar.getInstance();
    	String s;
    	s= now.get(Calendar.YEAR)+"--"+now.get(Calendar.MONTH)+"--"+now.get(Calendar.DATE)+"";
    	jta[0].setText("10001\n");
    	jta[1].setText("张军\n");
    	jta[2].setText("20001\n");
    	jta[3].setText("OOAD\n");
    	jta[4].setText(s+"\n");
    	jta[5].setText("\n");*/
    	new BorrowBookPanel();
    }
    if (str=="查询")
    {
    	new SearchBookPanel();
    }
    if (str=="归还")
    {
    	/*Calendar now;
    	now = Calendar.getInstance();
    	String s;
    	s= now.get(Calendar.YEAR)+"--"+now.get(Calendar.MONTH)+"--"+now.get(Calendar.DATE)+"";
    	jta[0].setText(jta[0].getText()+"10001\n");
    	jta[1].setText(jta[1].getText()+"张军");
    	jta[2].setText(jta[2].getText()+"20001\n");
    	jta[3].setText(jta[3].getText()+"OOAD\n");
    	jta[4].setText(jta[4].getText()+"\n");
    	jta[5].setText(jta[5].getText()+s+"\n");*/
    	new returnBackPanel();
    }
    }//故障处理结束

    catch (ArithmeticException localArithmeticException)
    {
       jtf1.setText("overflow");

    }
  }
  /*public  void putString()
  {
	 // data = new Object[num][5];//存放分类下的书籍信息

	    Object [] clumnNames = {"编号","书名 ","作者","出版社","数量"};

	 // Object [] temp2 = new Object[]{book_read.getNumber(),book_read.getName(),book_read.getAuthor(),book_read.getPress(),book_read.getCount()};   
	  String bookMessage="";
	  bookMessage =jtf1.getText()+"\n"+jta.getText()+"\n"+clumnNames;
	  jta.setText(bookMessage);
  }*/
/*
  public static void main(String[] paramArrayOfString)
  {
    new Library();
  }*/

}
