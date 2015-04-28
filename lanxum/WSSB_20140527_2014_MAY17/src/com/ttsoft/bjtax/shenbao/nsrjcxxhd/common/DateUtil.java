package com.ttsoft.bjtax.shenbao.nsrjcxxhd.common;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * ÈÕÆÚ×ª»»
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		String rtn = "";
		if (date != null) {
			try {
				rtn = sdf.format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rtn;
	}
}
