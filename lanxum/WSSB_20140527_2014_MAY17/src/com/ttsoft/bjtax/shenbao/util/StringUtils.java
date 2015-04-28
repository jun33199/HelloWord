package com.ttsoft.bjtax.shenbao.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.text.SimpleDateFormat;

/**
 * <p>Title: </p>
 * <p>Description: 申报征收用字符串工具</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class StringUtils {

  public static String getCurrentYear() {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    return nd;
  }

  public static String getPartBeforeSeparatorOfStr(String str,String separator) throws Exception{
    if("".equals(str)||"".equals(separator)||str==null||separator==null){
      return null;
    }else if(str.indexOf(separator)==-1){
      return str;
    }else{
      return str.substring(0,str.indexOf(separator));
    }
  }

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
    returnStr = StringUtils.killNull(str);
    returnStr = returnStr.trim();
    return returnStr;
  }

  /**
   * 得到一个SQL用字符串类型字段值
   * @param str 输入字符串
   * @return 输出字符串
   */
  public static String getSQLStr(String str) {
    String tmp = StringUtils.killNull(str);
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
   * @param str 输入字符串 yyyy-mm-dd或yyyymmdd
   * @return 输出字符串
   */
  public static String getSQLDate(String str) {
    //1.杀空
    String tmp = StringUtils.killNull(str);
    //2.去除"-"
    tmp = StringUtils.replaceAll(tmp, "-", "");
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
   * 得到一个SQL用日期类型字段值2,加上了时分秒235959
   * @param str 输入字符串 yyyy-mm-dd或yyyymmdd
   * @return 输出字符串
   */
  public static String getSQLDate1(String str) {
    //1.杀空
    String tmp = StringUtils.killNull(str);
    //2.去除"-"
    tmp = StringUtils.replaceAll(tmp, "-", "");
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
   * 得到一个SQL用日期类型字段值
   * @param str 输入字符串 yyyy-mm-dd
   * @return 输出字符串
   */
  public static String getSQLDate2(String str) {
    //1.杀空
    String tmp = StringUtils.killNull(str);
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
   * 得到一个日期，yyyy-mm-dd
   * @param date
   * @return
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
   * 得到一个日期，yyyy年mm月dd日
   * @param date 入口日期
   * @return 格式化日期
   */
  public static String getStrFromDate1(String date) {
    //1.杀空
    date = StringUtils.killNull(date);
    if ("".equals(date)) {
      return null;
    }
    //2.去除"-"
    date = StringUtils.replaceAll(date, "-", "");
    //3.去除两端的空格
    date = date.trim();
    //
    StringBuffer str = new StringBuffer();
    Date tmp = new Date();
    str.append(String.valueOf(tmp.getYear() + 1900));
    str.append("年");
    str.append(String.valueOf(tmp.getMonth() + 1));
    str.append("月");
    str.append(String.valueOf(tmp.getDate()));
    str.append("日");
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
   * 替换字符串
   * @param original 源字符串
   * @param find 查找字符串
   * @param replacement 替换字符串
   * @return 替换后的字符串
   */
  public final static String replaceAll(String original, String find,
                                        String replacement) {
    StringBuffer buffer = new StringBuffer(original);

    int idx = original.length();
    int offset = find.length();

    while ( (idx = original.lastIndexOf(find, idx - 1)) > -1) {
      buffer.replace(idx, idx + offset, replacement);
    }

    return buffer.toString();
  }

  public final static String bigDeciaml2String(BigDecimal bd,String defstr)
  {
      if (bd == null)
          return defstr;
      if (bd.scale() != 2)
      {
          bd = bd.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
      }
      return bd.toString();
  }

  public final static String bigDeciaml2String(BigDecimal bd)
  {
      return bigDeciaml2String(bd,"");
  }

  public final static String bigDeciaml2WholeString(BigDecimal bd,String defstr)
  {
      if (bd == null)
          return defstr;
      return bd.toString();
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

      while (dif_length > 0) {
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
//    Timestamp ts=CcsTimeUtil.getNowTimestamp();
//    try{
//    System.out.println(ts);
//      System.out.println(SBStringUtils.getSQLFromTimestamp(ts));
//    }catch(Exception e){
//      e.printStackTrace();
//    }
  }
}
