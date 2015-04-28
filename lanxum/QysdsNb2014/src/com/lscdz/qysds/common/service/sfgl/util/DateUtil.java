package com.lscdz.qysds.common.service.sfgl.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class DateUtil
{

  public static String getDate()
  {
    return getDate(new Date());
  }

  public static String getDate(Date date)
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(date);
  }

  public static String getDateFromDatetime(String datetime)
  {
    return getFormatDatetime(datetime).substring(0, 10);
  }

  public static String getDatetime()
  {
    return getDatetime(new Date());
  }

  public static String getDatetime(Date date)
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return formatter.format(date);
  }

  public static String getFormatDatetime(String datetime)
  {
    try
    {
      String retStr = new String();
      StringTokenizer st = new StringTokenizer(datetime.trim());
      retStr = st.nextToken();

      if (retStr.substring(6, 7).equals("-")) {
        retStr = retStr.substring(0, 5) + "0" + retStr.substring(5);
      }
      if (retStr.length() < 10) {
        retStr = retStr.substring(0, 8) + "0" + retStr.substring(8);
      }

      if (st.countTokens() < 1) {
        return retStr + " 00:00:00.000";
      }
      String s = new String();
      s = st.nextToken();
      st = new StringTokenizer(s, ":");
      int count = st.countTokens();
      if (count == 1) {
        retStr = retStr + " " + s + ":00:00.000";
      }
      if (count == 2) {
        retStr = retStr + " " + s + ":00.000";
      }
      if (count == 3) {
        retStr = retStr + " " + s + ".000";
      }
      return retStr;
    } catch (Exception e) {
    }
    return null;
  }

  public static String getStdDatetime(String datetime)
  {
    String s = new String();
    s = getFormatDatetime(datetime);
    return s.substring(0, 10) + " " + s.substring(11, 13) + ":" + s.substring(14, 16) + ":" + s.substring(17);
  }
}
