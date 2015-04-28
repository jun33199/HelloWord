package com.ttsoft.bjtax.smsb.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.StringUtil;
import java.text.SimpleDateFormat;

/**
 * <p>Title: 上门申报</p>
 * <p>Description: 申报征收用字符串工具</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SBStringUtils extends StringUtil{

  /**
   * 杀空函数，将"null"和null对象转换为""
   * @param str 输入字符串
   * @return 输出字符串
   */
  public static String killNull(String str) {
    String returnStr = null;
    if (str == null || "null".equals(str.toLowerCase())) {
      returnStr = "";
    }
    else {
      returnStr = str;
    }
    return returnStr;
  }

  /**
   * 去除字符串两边的空格并处理空字符串
   * @param str 输入字符串
   * @return 输出字符串
   */
  public static String trim(String str) {
    String returnStr = null;
    returnStr = SBStringUtils.killNull(str);
    returnStr = returnStr.trim();
    return returnStr;
  }

  /**
   * 得到一个SQL用字符串类型字段值
   * @param str 输入字符串
   * @return 输出字符串
   */
  public static String getSQLStr(String str) {
    String tmp = SBStringUtils.killNull(str);
    if (!tmp.equals("")) {
      tmp = "'" + tmp + "'";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * 得到一个SQL用日期类型字段值
   * @param str 输入字符串 yyyymmdd
   * @return 输出字符串
   */
  public static String getSQLDate(String str) {
    //1.杀空
    String tmp = SBStringUtils.killNull(str);
    //2.去除"-"
    tmp = tmp.replaceAll("-", "");
    //3.去除两端的空格
    tmp = tmp.trim();
    //4.转换格式
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + "','yyyymmdd')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * 得到一个SQL用日期类型字段值
   * @param str 输入字符串 yyyy-mm-dd
   * @return 输出字符串
   */
  public static String getSQLDate2(String str) {
    //1.杀空
    String tmp = SBStringUtils.killNull(str);
    //2.去除"-"
    //tmp = tmp.replaceAll("-", "");
    //3.去除两端的空格
    tmp = tmp.trim();
    //4.转换格式
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + "','yyyy-mm-dd')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * 得到一个SQL用日期类型字段值2,加上了时分秒235959
   * @param str 输入字符串 yyyy-mm-dd或yyyymmdd
   * @return 输出字符串
   */
  public static String getSQLDate1(String str) {
    //1.杀空
    String tmp = SBStringUtils.killNull(str);
    //2.去除"-"
    tmp = tmp.replaceAll("-", "");
    //3.去除两端的空格
    tmp = tmp.trim();
    //4.转换格式
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + " 235959','yyyymmdd hh24miss')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * 得到一个日期，yyyy-mm-dd
   * @param date 日期
   * @return 格式化后的日期
   */
  public static String getStrFromDate(Timestamp date) {
    StringBuffer str = new StringBuffer();
    str.append(date.getYear() + 1900);
    str.append("-");
    str.append(date.getMonth() + 1);
    str.append("-");
    str.append(date.getDate());
    return str.toString();
  }

  /**
   * 获取一个TimeStamp类型的SQL转换
   * @param time 时间
   * @return SQL
   */
  public static String getSQLFromTimestamp(Timestamp time) throws BaseException {
    String result = "";
    String strTime = "";
    StringBuffer sb = null;
    if (time == null) {
      result = "null";
    }
    else {
      strTime = String.valueOf(time);
      if (strTime.length() >= 19) {
        strTime = strTime.substring(0, 19);
      }
      sb = new StringBuffer();
      sb.append("to_date('");
      sb.append(strTime);
      sb.append("','yyyy-mm-dd hh24:mi:ss')");
      result = sb.toString();
    }
    return result;
  }

  /**
   * 对一组数字字符串进行排序
   * @param iterator 输入数字轮寻器
   * @param type 0-升序排列，1-降序排列
   * @return 排序完的list
   */
  public static List sortKeys(Iterator iterator, int type) {
    //1.句柄申明
    List list = new ArrayList();
    String key0;
    String key1;
    //2.生成基准List
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    //3.生成数组
    String[] keys = {};
    keys = (String[]) list.toArray(keys);
    //4.排序
    String tmpKey;
    for (int i = 0; i < keys.length; i++) {
      for (int j = keys.length - 1; j > i; j--) {
        if (type == 0) {
          if (Integer.parseInt(keys[i]) > Integer.parseInt(keys[j])) {
            tmpKey = keys[i];
            keys[i] = keys[j];
            keys[j] = tmpKey;
          }
        }
        else if (type == 1) {
          if (Integer.parseInt(keys[i]) < Integer.parseInt(keys[j])) {
            tmpKey = keys[i];
            keys[i] = keys[j];
            keys[j] = tmpKey;
          }
        }
      }
    }
    //5.生成返回容器
    List tmpList = new ArrayList();
    for (int i = 0; i < keys.length; i++) {
      tmpList.add(keys[i]);
    }
    //6.返回
    return tmpList;
  }

  /**
   * 比较函数
   * @param str1 第一个参数A
   * @param str2 第二个参数B
   * @return A>=B则返回true,A<B则返回False
   */
  public static boolean compare(String str1, String str2) throws BaseException {
    boolean flag = false;
    try {
      if (Integer.parseInt(str1) >= Integer.parseInt(str2)) {
        flag = true;
      }
      else {
        flag = false;
      }
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    return flag;
  }

  /**
   * 比较函数
   * @param str1 第一个参数A
   * @param str2 第二个参数B
   * @return A>B则返回true,A<=B则返回False
   */
  public static boolean compare1(String str1, String str2) throws BaseException {
    boolean flag = false;
    try {
      if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
        flag = true;
      }
      else {
        flag = false;
      }
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
    return flag;
  }

  /**
   * 获取从指定年度到当前年度的所有年度列表(不包括当前年度)
   * @param nd 指定年度,应当不大于当前年度
   * @return 年度列表
   */
  public static List getNdListFromNd(String nd) {
    List tmpList = new ArrayList();
    int startNd = Integer.parseInt(nd);
    int lastNd = (new Date()).getYear() + 1900 - 1;
    while (lastNd >= startNd) {
      tmpList.add(String.valueOf(startNd));
      startNd++;
    }
    return tmpList;
  }

  /**
   * 将指定的日期字符串转化为日期对象
   * @param dateStr 日期字符串
   * @return java.util.Date
   * @roseuid 3F39FE450385
   */
  public static Date getDate(String dateStr) throws BaseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    Date date = null;
    try {
      date = df.parse(dateStr);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return date;
  }

  /**
   * 将指定的日期字符串转化为日期对象
   * @param dateStr 日期字符串
   * @return java.util.Date
   * @roseuid 3F39FE450385
   */
  public static Date getDate(String dateStr,String format) throws BaseException {
    SimpleDateFormat df = new SimpleDateFormat(format);
    Date date = null;
    try {
      date = df.parse(dateStr);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return date;
  }

  /**
     * 在指定字符串左边补充指定字符到指定长度
     * @param str String 需要补充的字符串
     * @param totalLength int 补充字符后的字符串长度
     * @param paddingStr String 指定的补充字符
     * @return String 补充后的字符串
     * add by tum at 2009-5-26
     */
    public static String LPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // 获取待补充字符串长度
        int strLength = str.trim().length();
        // 计算与总长度的差值
        int dif_length = totalLength - strLength;

        while(dif_length > 0)
        {
            reStr.append(paddingStr);
            dif_length--;
        }

        reStr.append(str.trim());

        return reStr.toString();
    }


  /**
   * 运行主函数
   * @param args
   */
  public static void main(String[] args) {
    //System.out.println(SBStringUtils.getDateValue("20040201").getTime()-SBStringUtils.getDateValue("20040101").getTime());
  }

}