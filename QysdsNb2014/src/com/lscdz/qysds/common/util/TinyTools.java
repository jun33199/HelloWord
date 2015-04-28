package com.lscdz.qysds.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TinyTools {

	  /**
	   * �õ�ʱ�����
	   * @param jsjdm
	   * @return String ���
	   */
	  public static String getXh(String jsjdm) {
	    return jsjdm+ TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
	  }
	  /**
	   * ��ʱ������ת��Ϊ��ʽ�ַ���
	   * @param date ����
	   * @param format String
	   * @return ��ʽ������ַ���
	   */
	  public static String Date2String(java.util.Date date, String format) {
	    if (date == null) {
	      return null;
	    }
	    // ��ʽ��Class
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.format(date);
	  }
}
