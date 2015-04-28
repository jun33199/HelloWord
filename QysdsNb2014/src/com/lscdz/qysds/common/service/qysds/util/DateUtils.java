package com.lscdz.qysds.common.service.qysds.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import yangjian.frame.util.FrameException;

public class DateUtils {
	/**
     * ��ȡ�����ַ�����
     *
     * <pre>
     *    �����ַ�����ʽ�� yyyyMMdd
     *    ���У�
     *        yyyy   ��ʾ4λ�ꡣ
     *        MM     ��ʾ2λ�¡�
     *        dd     ��ʾ2λ�ա�
     * </pre>
     *
     * @param date ��Ҫת�������ڡ�
     * @return String "yyyyMMdd"��ʽ�������ַ�����
     */
    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }
    
    /**
     * ���ַ���ת��Ϊ��������
     * @param dateStr
     * @return
     * @throws FrameException
     */
    public static Timestamp getDateTime(String dateStr) throws FrameException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
			return new Timestamp(formatter.parse(dateStr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FrameException("����ת����������");
		}
    }
    /**
     * ��ȡ��ǰ��
     * @return nowYear
     */
   public static String getNowYear(){
	   Calendar ca = Calendar.getInstance();//�õ�һ��Calendar��ʵ�� 
		int nowYear=ca.get(Calendar.YEAR);
		String nowyear=String.valueOf(nowYear);
		return nowyear;
   }
}
