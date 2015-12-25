package noTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	String name;
	int age;

	public static void main(String[] arg) throws Exception {
//		int idate =(int)(System.currentTimeMillis()/1000L) ;
//		System.out.println(idate);
//
//		String sdate = getDate(idate);
		String sdate = "2015-11-30 00:00:00";
		System.out.println(sdate);
		int idate = getDate(sdate);
		System.out.println(idate);
		
		System.out.println(getDate(idate));

	}

	public static String getDate(int date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormate = sdf.format(new Date(date * 1000L));
		return dateFormate;
	}
	
	public static int getDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) (d.getTime()/1000L);
	}
}
