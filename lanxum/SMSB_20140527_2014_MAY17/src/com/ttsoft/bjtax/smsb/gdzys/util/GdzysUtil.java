package com.ttsoft.bjtax.smsb.gdzys.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.syax.creports.util.MoneyUtils;

public class GdzysUtil {
	/**
	 * ��ȡ��ʱ��֮����������
	 * 
	 * @param date1
	 *            �����ַ���yyyymmdd
	 * @param date2
	 *            �����ַ���yyyymmdd
	 * @return String ��������
	 */

	public static String getTheDayNumber(String dateks, String datejs) {
		Calendar c = Calendar.getInstance();

		c
				.set(Integer.parseInt(dateks.substring(0, 4)), Integer
						.parseInt(dateks.substring(4, 6)) - 1, Integer
						.parseInt(dateks.substring(6, 8)));
		long tks = c.getTimeInMillis();

		c
				.set(Integer.parseInt(datejs.substring(0, 4)), Integer
						.parseInt(datejs.substring(4, 6)) - 1, Integer
						.parseInt(datejs.substring(6, 8)));
		long tjs = c.getTimeInMillis();
		long l = 0;
		if (tjs > tks) {
			l = Math.round(Math.abs(tjs - tks)
					/ (Double.parseDouble("86400000")));
		}
		return (String.valueOf(l));
	}
	/**
	 * �������ɽ�
	 * 
	 * @param dateInTax
	 *            ������˰��ʼ����yyyymmdd
	 * @param datesb
	 *            �걨����yyyymmdd
	 * @param bqynse
	 *            ����Ӧ��˰��
	 * @return String ���ɽ� znj String �������� znts
	 */

	public static HashMap getZnj(String dateInTax, String datesb, String bqynse ) {
		// ����ֵ
		HashMap ListZnj = new HashMap();
		double znj = 0.0;
		long znts = 0;

		znts = Long.parseLong(getTheDayNumber(dateInTax, datesb));
		znj = (Double.parseDouble(bqynse) * znts)
				* Double.parseDouble("0.0005");
		System.out.println("######bqynse = "+bqynse);
		System.out.println("######Znj = " + MoneyUtils.format(Double.toString(znj)) + "########znts=" + znts);
		ListZnj.put("znj",MoneyUtils.format(Double.toString(znj)));
		ListZnj.put("znts", String.valueOf(znts));
		return (ListZnj);
	}
	
	/** 
	   * �õ�����ǰ��ʱ�� 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public static Date getDateBefore(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
	   return now.getTime();  
	  }  
	    
	  /** 
	   * �õ�������ʱ�� 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public static Date getDateAfter(Date d,int day){  
	   Calendar now =Calendar.getInstance();  
	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
	   return now.getTime();  
	  }  
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//GdzysDateUtil.parsePlainDate("20140108");
		//System.out.println(GdzysDateUtil.getDate(getDateAfter(GdzysDateUtil.parsePlainDate("20140108"),1)));
		
		
		
		// TODO Auto-generated method stub
		   //System.out.println(getZnj("20131231","20140101","3500"));

	}

}
