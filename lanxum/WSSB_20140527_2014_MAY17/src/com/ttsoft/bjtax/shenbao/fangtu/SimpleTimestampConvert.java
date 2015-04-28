package com.ttsoft.bjtax.shenbao.fangtu;

import java.sql.Timestamp;

import org.apache.commons.beanutils.Converter;

public class SimpleTimestampConvert implements Converter {

	public SimpleTimestampConvert() {
		super();
	}
	public SimpleTimestampConvert(Object obj) {
		super();
	}

	public Object convert(Class arg0, Object arg1)  {
		Timestamp ts = null;
		if ( arg1 == null ) return ts;
		
		String date = arg1.toString();
//		if ( date.trim().equals("")) return new Timestamp(System.currentTimeMillis());
		if ( date.trim().equals("")) return null;
		
		//System.out.println("------------------------date: " + date);
		if ( date.length() == 8 ) {
			try {
				ts = DateHelper.toTimestamp(date,"yyyyMMdd");
			} catch (Exception e)  {
				e.printStackTrace();
			}
		}
		else if ( date.length() == 10 ) {
			try {
				ts = DateHelper.toTimestamp(date, "yyyy-MM-dd");
			} catch (Exception e)  {
				e.printStackTrace();
			}
		}
		else if ( date.length() > 8 ) {
			try {
				ts = DateHelper.toTimestamp(date, "yyyy-MM-dd HH:mm:ss.SSS");
			} catch (Exception e)  {
				e.printStackTrace();
			}
		}
		return ts;
	}

}
