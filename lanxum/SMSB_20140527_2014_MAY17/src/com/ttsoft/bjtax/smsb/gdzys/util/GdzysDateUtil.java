package com.ttsoft.bjtax.smsb.gdzys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
/**
 * <p>Title: 北京地税核心征管系统－－耕地占有税</p>
 * <p>Description: 耕地占有税日期格式化公共方法类</p>
 * @author wangxq
 * @version 1.1
 */
public class GdzysDateUtil {
	 public static final String[] months =
	    {
	        "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月",
	    };
	    public static final String[] quarters = { "一季度", "二季度", "三季度", "四季度" };

	    public GdzysDateUtil()
	    {
	    }

	    /**
	     * 获取日期字符串。
	     *
	     * <pre>
	     * 日期字符串格式： yyyyMMdd
	     * 其中：
	     *     yyyy   表示4位年。
	     *     MM     表示2位月。
	     *     dd     表示2位日。
	     * </pre>
	     *
	     * @return String "yyyyMMdd"格式的日期字符串。
	     */
	    public static String getDate()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(new Date());
	    }

	    /**
	     * 获取当前年度字符串。
	     *
	     * <pre>
	     * 日期字符串格式： yyyy
	     * 其中：
	     *     yyyy   表示4位年。
	     * </pre>
	     *
	     * @return String "yyyy"格式的当前年度字符串。
	     */
	    public static String getNowYear()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");


	        return formatter.format(new Date());
	    }

	    /**
	     * 获取当前月度字符串。
	     *
	     * <pre>
	     * 日期字符串格式： MM
	     * 其中：
	     *     MM   表示4位年。
	     * </pre>
	     *
	     * @return String "yyyy"格式的当前月度字符串。
	     */
	    public static String getNowMonth()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("MM");

	        return formatter.format(new Date());
	    }

	    /**
	     * 获取当前月度字符串。
	     *
	     * <pre>
	     * 日期字符串格式： dd
	     * 其中：
	     *     dd   表示4位年。
	     * </pre>
	     *
	     * @return String "yyyy"格式的当前月度字符串。
	     */
	    public static String getNowDay()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("dd");

	        return formatter.format(new Date());
	    }

	    /**
	     * 获取日期字符串。
	     *
	     * <pre>
	     * 日期字符串格式： yyyyMMdd
	     * 其中：
	     *     yyyy   表示4位年。
	     *     MM     表示2位月。
	     *     dd     表示2位日。
	     * </pre>
	     *
	     * @param date 需要转化的日期。
	     * @return String "yyyyMMdd"格式的日期字符串。
	     */
	    public static String getDate(Date date)
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(date);
	    }
	    


	    /**
	     * 获取日期字符串。
	     *
	     * <pre>
	     * 日期字符串格式： yyyy-MM-dd
	     * 其中：
	     *     yyyy   表示4位年。
	     *     MM     表示2位月。
	     *     dd     表示2位日。
	     * </pre>
	     *
	     * @return String "yyyy-MM-dd"格式的日期字符串。
	     */
	    public static String getHyphenDate()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(new Date());
	    }

	    /**
	     * 获取日期字符串。
	     *
	     * <pre>
	     * 日期字符串格式： yyyy-MM-dd
	     * 其中：
	     *     yyyy   表示4位年。
	     *     MM     表示2位月。
	     *     dd     表示2位日。
	     * </pre>
	     *
	     * @param date 需要转化的日期。
	     * @return String "yyyy-MM-dd"格式的日期字符串。
	     */
	    public static String getHyphenDate(Date date)
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(date);
	    }

	    /**
	     * 将"yyyyMMdd"格式的日期字符串转换为日期对象。
	     *
	     * @param source 日期字符串。
	     * @return Date 日期对象。
	     */
	    public static Date parsePlainDate(String source)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * 将“yyyy-MM-dd”格式的日期字符串转换为日期对象。
	     *
	     * @param source 日期字符串。
	     * @return Date 日期对象。
	     */
	    public static Date parseHyphenDate(String source)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * 将指定格式的日期字符串转换为日期对象。
	     *
	     * @param source 日期字符串。
	     * @param pattern 模式。
	     * @return Date 日期对象。
	     */
	    public static Date parseDate(String source, String pattern)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * 将“yyyy-MM-dd”格式的日期字符串转换为“yyyyMMdd”格式的日期字符串。
	     *
	     * @param source 日期字符串。
	     * @return String "yyyymmdd"格式的日期字符串。
	     */
	    public static String toPlainDate(String source)
	    {
	        Date date = parseHyphenDate(source);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(date);
	    }

	    /**
	     * 将“yyyyMMdd”格式的日期字符串转换为“yyyy-MM-dd”格式的日期字符串。
	     *
	     * @param source 日期字符串。
	     * @return String "yyyy-MM-dd"格式的日期字符串。
	     */
	    public static String toHyphenDate(String source)
	    {
	        Date date = parsePlainDate(source);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(date);
	    }

	    /**
	     * 获取时间戳，将日期对象转换为时间戳类型。
	     *
	     * @param date 日期对象
	     * @return Timestamp 时间戳
	     */
	    public static Timestamp getTimestamp(Date date)
	    {
	        return new Timestamp(date.getTime());
	    }

	    /**
	     * 获取时间戳，将当前日期转换为时间戳类型。
	     *
	     * @return Timestamp 时间戳
	     */
	    public static Timestamp getTimestamp()
	    {
	        return new Timestamp(new Date().getTime());
	    }

	    /**
	     * 将“yyyyMMdd”格式的日期字符串转换为Timestamp类型的对象。
	     *
	     * @param source 日期字符串
	     * @return Timestamp 时间戳
	     */
	    public static Timestamp parseTimestamp(String source)
	    {
	        Date date = parsePlainDate(source);

	        return getTimestamp(date);
	    }
	    
	    /**
	     * 得到系统Timestamp时间格式
	     */
		public static Timestamp  getsysDate(){
			Timestamp systemTime = new Timestamp((new Date()).getTime());
			return   systemTime;

		}
	    /**
	     * 获得年度周期
	     * <br>Example:<br>
	     * CcsDateUtil.getPeriodYear("20040229" , -1);<br>
	     * CcsDateUtil.getPeriodYear("20040228" , -1);<br>
	     * CcsDateUtil.getPeriodYear("20020230" , 2);<br>
	     *
	     * @param source 时间串
	     * @param yearPeriod 年度周期 -1代表本时间的上一年度,以次类推。
	     * @return String 时间。
	     */
	    public static String getPeriodYear(String source, int yearPeriod)
	    {
	        int p = Integer.parseInt(source.substring(0, 4)) + yearPeriod;
	        String newYear = String.valueOf(p) +
	            source.substring(4, source.length());
	        Date date = parsePlainDate(newYear);
	        String s = getDate(date);
	        int ny = Integer.parseInt(s);
	        int sy = Integer.parseInt(newYear);

	        while (ny > sy)
	        {
	            sy--;
	            ny = Integer.parseInt(getDate(parsePlainDate(String.valueOf(sy))));
	        }

	        return String.valueOf(sy);
	    }
	    
		public static String  get30daysLater(String enddate){   	
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	        long oneday = 86400000 ;
			long time = 0;
			try {
				time = df.parse(enddate).getTime();
			} catch (ParseException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return  getDate( new java.sql.Date(time+oneday*30));
		
	}
		 /**
		 * 取得申报日期
		 * @return 申报日期
		 * @throws BaseException
		 */
		public static Timestamp  getTimestampFromDB() throws BaseException {
			
			Connection conn = null;
			StringBuffer sqlSb=new StringBuffer();
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			Timestamp sbrq=null;
			try {
				sqlSb.append("select sysdate sbrq from dual");
				conn = SfDBResource.getConnection();
				pstmt = conn.prepareStatement(sqlSb.toString());
			    rs = pstmt.executeQuery();
			   
			    while(rs.next()){
			    	sbrq=rs.getTimestamp("sbrq");
			    }
			    
			    rs.close();
			    pstmt.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("查询被盗抢信息出错！");
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
			return sbrq;
		}	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
