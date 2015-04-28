package com.lscdz.qysds.common.service.qysds.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import yangjian.frame.util.FrameException;

public class DateUtils {
	/**
     * 获取日期字符串。
     *
     * <pre>
     *    日期字符串格式： yyyyMMdd
     *    其中：
     *        yyyy   表示4位年。
     *        MM     表示2位月。
     *        dd     表示2位日。
     * </pre>
     *
     * @param date 需要转化的日期。
     * @return String "yyyyMMdd"格式的日期字符串。
     */
    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(date);
    }
    
    /**
     * 将字符串转换为日期类型
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
			throw new FrameException("日期转换发生错误");
		}
    }
    /**
     * 获取当前年
     * @return nowYear
     */
   public static String getNowYear(){
	   Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		int nowYear=ca.get(Calendar.YEAR);
		String nowyear=String.valueOf(nowYear);
		return nowyear;
   }
}
