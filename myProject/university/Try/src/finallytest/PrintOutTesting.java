package finallytest;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;

public class PrintOutTesting {
public static void main(String[] args){
	try{
		(new PrintOutTesting()).printout(1);
		System.out.println();
		(new PrintOutTesting()).printout(1);
	}
	catch (Exception e){}
}
private void printout(int para){
	try{
		System.out.println(1);
		if(para!=1){
			throw new RuntimeException();
		}
	}
	catch (RuntimeException e){
		System.out.println(2);
		throw e;		
	}
	finally{
		System.out.println(3);
	}
	System.out.println(4);
	
}
}
