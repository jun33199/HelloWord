package com.ttsoft.bjtax.shenbao.util;

import com.ttsoft.framework.util.DateUtil;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtilPro
    extends DateUtil {

  public static String getCurYearStr(){
    Date date=new Date();
    String year=String.valueOf(date.getYear()+1900);
    year=year.substring(2,4);
    return year;
  }
  public static String getCurYearStr4(){
    Date date=new Date();
    String year=String.valueOf(date.getYear()+1900);
    //year=year.substring(2,4);
    return year;
  }

  //�����ʽ��Timestampת��Ϊ�����ʽ��Timestamp
  public static java.sql.Timestamp string2Date(java.util.Date date)
  {
      if (date==null)
          return null;
      java.sql.Timestamp tempStamp = null;

      try
      {
          String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
          tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
      }
      catch (Exception ex) {}

      return tempStamp;
  }
  //�����ʽ��Timestampת��Ϊ�����ʽ��Timestamp
  public static java.sql.Timestamp string2Date(String sdate)
  {
      if (sdate==null)
          return null;
      java.sql.Timestamp tempStamp = null;

      try
      {
        Date date =getDate(sdate,"yyyy-MM-dd");
         String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
          tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
      }
      catch (Exception ex) {}

      return tempStamp;
  }

  /**
   * ��ָ���������ַ���ת��Ϊ���ڶ���
   * @param dateStr �����ַ���
   * @return java.util.Date
   * @roseuid 3F39FE450385
   */
  public static Date getDate(String dateStr,String format) throws Exception {
    if(dateStr==null||format==null){
      throw new Exception("���������쳣"+dateStr+"|"+format);
    }
    SimpleDateFormat df = new SimpleDateFormat(format);
    try {
      java.util.Date date = df.parse(dateStr);
      return date;
    }
    catch (Exception ex) {
      return null;
    } //end try - catch
  }


  public static void main(String[] args) {
    System.out.println(DateUtilPro.getCurYearStr());
  }
  
  /**
   * ��ҳ������ʾDate��ֵ��
   *
   * @param dateValue ��Ҫ��ʾ��dateֵ��
   * @param formatString ��Ҫ����ĸ�ʽ������java.text.SimpleDateFormat��Ҫ��
   * @return String ���ص��ַ�����
   */
  public static String displayValue(Date dateValue, String formatString)
  {
      if (dateValue == null)
      {
          return "";
      }

      SimpleDateFormat sdf = new SimpleDateFormat(formatString);

      return sdf.format(dateValue).trim();
  }
}