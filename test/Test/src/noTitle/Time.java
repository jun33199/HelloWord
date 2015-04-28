package noTitle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public Time(){
		String s; 
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		s = format1.format(new Date());  
			
		System.out.println(s+System.currentTimeMillis());
		
		String gs = "2002-01-01";  
		
		gs=gs.replaceAll("-", "");
		System.out.println(gs);
		
	}
	
public static void main(String [] arg) throws Exception{
	new Time();
}
}
