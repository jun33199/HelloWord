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
 * <p>Title: ������˰��������ϵͳ��������ռ��˰</p>
 * <p>Description: ����ռ��˰���ڸ�ʽ������������</p>
 * @author wangxq
 * @version 1.1
 */
public class GdzysDateUtil {
	 public static final String[] months =
	    {
	        "һ��", "����", "����", "����", "����", "����", "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����",
	    };
	    public static final String[] quarters = { "һ����", "������", "������", "�ļ���" };

	    public GdzysDateUtil()
	    {
	    }

	    /**
	     * ��ȡ�����ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� yyyyMMdd
	     * ���У�
	     *     yyyy   ��ʾ4λ�ꡣ
	     *     MM     ��ʾ2λ�¡�
	     *     dd     ��ʾ2λ�ա�
	     * </pre>
	     *
	     * @return String "yyyyMMdd"��ʽ�������ַ�����
	     */
	    public static String getDate()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(new Date());
	    }

	    /**
	     * ��ȡ��ǰ����ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� yyyy
	     * ���У�
	     *     yyyy   ��ʾ4λ�ꡣ
	     * </pre>
	     *
	     * @return String "yyyy"��ʽ�ĵ�ǰ����ַ�����
	     */
	    public static String getNowYear()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");


	        return formatter.format(new Date());
	    }

	    /**
	     * ��ȡ��ǰ�¶��ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� MM
	     * ���У�
	     *     MM   ��ʾ4λ�ꡣ
	     * </pre>
	     *
	     * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
	     */
	    public static String getNowMonth()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("MM");

	        return formatter.format(new Date());
	    }

	    /**
	     * ��ȡ��ǰ�¶��ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� dd
	     * ���У�
	     *     dd   ��ʾ4λ�ꡣ
	     * </pre>
	     *
	     * @return String "yyyy"��ʽ�ĵ�ǰ�¶��ַ�����
	     */
	    public static String getNowDay()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("dd");

	        return formatter.format(new Date());
	    }

	    /**
	     * ��ȡ�����ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� yyyyMMdd
	     * ���У�
	     *     yyyy   ��ʾ4λ�ꡣ
	     *     MM     ��ʾ2λ�¡�
	     *     dd     ��ʾ2λ�ա�
	     * </pre>
	     *
	     * @param date ��Ҫת�������ڡ�
	     * @return String "yyyyMMdd"��ʽ�������ַ�����
	     */
	    public static String getDate(Date date)
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(date);
	    }
	    


	    /**
	     * ��ȡ�����ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� yyyy-MM-dd
	     * ���У�
	     *     yyyy   ��ʾ4λ�ꡣ
	     *     MM     ��ʾ2λ�¡�
	     *     dd     ��ʾ2λ�ա�
	     * </pre>
	     *
	     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	     */
	    public static String getHyphenDate()
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(new Date());
	    }

	    /**
	     * ��ȡ�����ַ�����
	     *
	     * <pre>
	     * �����ַ�����ʽ�� yyyy-MM-dd
	     * ���У�
	     *     yyyy   ��ʾ4λ�ꡣ
	     *     MM     ��ʾ2λ�¡�
	     *     dd     ��ʾ2λ�ա�
	     * </pre>
	     *
	     * @param date ��Ҫת�������ڡ�
	     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	     */
	    public static String getHyphenDate(Date date)
	    {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(date);
	    }

	    /**
	     * ��"yyyyMMdd"��ʽ�������ַ���ת��Ϊ���ڶ���
	     *
	     * @param source �����ַ�����
	     * @return Date ���ڶ���
	     */
	    public static Date parsePlainDate(String source)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ���ڶ���
	     *
	     * @param source �����ַ�����
	     * @return Date ���ڶ���
	     */
	    public static Date parseHyphenDate(String source)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * ��ָ����ʽ�������ַ���ת��Ϊ���ڶ���
	     *
	     * @param source �����ַ�����
	     * @param pattern ģʽ��
	     * @return Date ���ڶ���
	     */
	    public static Date parseDate(String source, String pattern)
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

	        return sdf.parse(source, new ParsePosition(0));
	    }

	    /**
	     * ����yyyy-MM-dd����ʽ�������ַ���ת��Ϊ��yyyyMMdd����ʽ�������ַ�����
	     *
	     * @param source �����ַ�����
	     * @return String "yyyymmdd"��ʽ�������ַ�����
	     */
	    public static String toPlainDate(String source)
	    {
	        Date date = parseHyphenDate(source);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

	        return formatter.format(date);
	    }

	    /**
	     * ����yyyyMMdd����ʽ�������ַ���ת��Ϊ��yyyy-MM-dd����ʽ�������ַ�����
	     *
	     * @param source �����ַ�����
	     * @return String "yyyy-MM-dd"��ʽ�������ַ�����
	     */
	    public static String toHyphenDate(String source)
	    {
	        Date date = parsePlainDate(source);
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        return formatter.format(date);
	    }

	    /**
	     * ��ȡʱ����������ڶ���ת��Ϊʱ������͡�
	     *
	     * @param date ���ڶ���
	     * @return Timestamp ʱ���
	     */
	    public static Timestamp getTimestamp(Date date)
	    {
	        return new Timestamp(date.getTime());
	    }

	    /**
	     * ��ȡʱ���������ǰ����ת��Ϊʱ������͡�
	     *
	     * @return Timestamp ʱ���
	     */
	    public static Timestamp getTimestamp()
	    {
	        return new Timestamp(new Date().getTime());
	    }

	    /**
	     * ����yyyyMMdd����ʽ�������ַ���ת��ΪTimestamp���͵Ķ���
	     *
	     * @param source �����ַ���
	     * @return Timestamp ʱ���
	     */
	    public static Timestamp parseTimestamp(String source)
	    {
	        Date date = parsePlainDate(source);

	        return getTimestamp(date);
	    }
	    
	    /**
	     * �õ�ϵͳTimestampʱ���ʽ
	     */
		public static Timestamp  getsysDate(){
			Timestamp systemTime = new Timestamp((new Date()).getTime());
			return   systemTime;

		}
	    /**
	     * ����������
	     * <br>Example:<br>
	     * CcsDateUtil.getPeriodYear("20040229" , -1);<br>
	     * CcsDateUtil.getPeriodYear("20040228" , -1);<br>
	     * CcsDateUtil.getPeriodYear("20020230" , 2);<br>
	     *
	     * @param source ʱ�䴮
	     * @param yearPeriod ������� -1����ʱ�����һ���,�Դ����ơ�
	     * @return String ʱ�䡣
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
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			return  getDate( new java.sql.Date(time+oneday*30));
		
	}
		 /**
		 * ȡ���걨����
		 * @return �걨����
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
				throw new ApplicationException("��ѯ��������Ϣ����");
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
