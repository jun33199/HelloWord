package com.lscdz.qysds.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TinyTools {

	  /**
	   * 得到时间序号
	   * @param jsjdm
	   * @return String 序号
	   */
	  public static String getXh(String jsjdm) {
	    return jsjdm+ TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
	  }
	  /**
	   * 将时间类型转换为格式字符串
	   * @param date 日期
	   * @param format String
	   * @return 格式化后的字符串
	   */
	  public static String Date2String(java.util.Date date, String format) {
	    if (date == null) {
	      return null;
	    }
	    // 格式化Class
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.format(date);
	  }
}
