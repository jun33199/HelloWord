package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.UI.LabelTextPanel;

public class Test extends JFrame {

	private JPanel contentPane;
	private JPanel labeltextpanel;
	private Label label=new Label("sheng");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		labeltextpanel=this.LabelTextPanel("kdk");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(labeltextpanel, BorderLayout.NORTH);
		contentPane.add(label, BorderLayout.SOUTH);
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	public  JPanel LabelTextPanel(String labelName){
		Label label;
		JPanel lableTextPanel;
		JTextField text;
		label=new Label(labelName);
		text =new JTextField();
		lableTextPanel= new 	JPanel();
		lableTextPanel.setLayout(new GridLayout(1,2));
		lableTextPanel.add(label);
		lableTextPanel.add(text);
		return lableTextPanel;
	}

}
