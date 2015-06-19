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
 * <p>Title: �����걨</p>
 * <p>Description: �걨�������ַ�������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SBStringUtils extends StringUtil{

  /**
   * ɱ�պ�������"null"��null����ת��Ϊ""
   * @param str �����ַ���
   * @return ����ַ���
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
   * ȥ���ַ������ߵĿո񲢴�����ַ���
   * @param str �����ַ���
   * @return ����ַ���
   */
  public static String trim(String str) {
    String returnStr = null;
    returnStr = SBStringUtils.killNull(str);
    returnStr = returnStr.trim();
    return returnStr;
  }

  /**
   * �õ�һ��SQL���ַ��������ֶ�ֵ
   * @param str �����ַ���
   * @return ����ַ���
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
   * �õ�һ��SQL�����������ֶ�ֵ
   * @param str �����ַ��� yyyymmdd
   * @return ����ַ���
   */
  public static String getSQLDate(String str) {
    //1.ɱ��
    String tmp = SBStringUtils.killNull(str);
    //2.ȥ��"-"
    tmp = tmp.replaceAll("-", "");
    //3.ȥ�����˵Ŀո�
    tmp = tmp.trim();
    //4.ת����ʽ
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + "','yyyymmdd')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * �õ�һ��SQL�����������ֶ�ֵ
   * @param str �����ַ��� yyyy-mm-dd
   * @return ����ַ���
   */
  public static String getSQLDate2(String str) {
    //1.ɱ��
    String tmp = SBStringUtils.killNull(str);
    //2.ȥ��"-"
    //tmp = tmp.replaceAll("-", "");
    //3.ȥ�����˵Ŀո�
    tmp = tmp.trim();
    //4.ת����ʽ
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + "','yyyy-mm-dd')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * �õ�һ��SQL�����������ֶ�ֵ2,������ʱ����235959
   * @param str �����ַ��� yyyy-mm-dd��yyyymmdd
   * @return ����ַ���
   */
  public static String getSQLDate1(String str) {
    //1.ɱ��
    String tmp = SBStringUtils.killNull(str);
    //2.ȥ��"-"
    tmp = tmp.replaceAll("-", "");
    //3.ȥ�����˵Ŀո�
    tmp = tmp.trim();
    //4.ת����ʽ
    if (!tmp.equals("")) {
      tmp = "to_date('" + tmp + " 235959','yyyymmdd hh24miss')";
    }
    else {
      tmp = "null";
    }
    return tmp;
  }

  /**
   * �õ�һ�����ڣ�yyyy-mm-dd
   * @param date ����
   * @return ��ʽ���������
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
   * ��ȡһ��TimeStamp���͵�SQLת��
   * @param time ʱ��
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
   * ��һ�������ַ�����������
   * @param iterator ����������Ѱ��
   * @param type 0-�������У�1-��������
   * @return �������list
   */
  public static List sortKeys(Iterator iterator, int type) {
    //1.�������
    List list = new ArrayList();
    String key0;
    String key1;
    //2.���ɻ�׼List
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    //3.��������
    String[] keys = {};
    keys = (String[]) list.toArray(keys);
    //4.����
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
    //5.���ɷ�������
    List tmpList = new ArrayList();
    for (int i = 0; i < keys.length; i++) {
      tmpList.add(keys[i]);
    }
    //6.����
    return tmpList;
  }

  /**
   * �ȽϺ���
   * @param str1 ��һ������A
   * @param str2 �ڶ�������B
   * @return A>=B�򷵻�true,A<B�򷵻�False
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
   * �ȽϺ���
   * @param str1 ��һ������A
   * @param str2 �ڶ�������B
   * @return A>B�򷵻�true,A<=B�򷵻�False
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
   * ��ȡ��ָ����ȵ���ǰ��ȵ���������б�(��������ǰ���)
   * @param nd ָ�����,Ӧ�������ڵ�ǰ���
   * @return ����б�
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
   * ��ָ���������ַ���ת��Ϊ���ڶ���
   * @param dateStr �����ַ���
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
   * ��ָ���������ַ���ת��Ϊ���ڶ���
   * @param dateStr �����ַ���
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
     * ��ָ���ַ�����߲���ָ���ַ���ָ������
     * @param str String ��Ҫ������ַ���
     * @param totalLength int �����ַ�����ַ�������
     * @param paddingStr String ָ���Ĳ����ַ�
     * @return String �������ַ���
     * add by tum at 2009-5-26
     */
    public static String LPAD(String str, int totalLength, String paddingStr)
    {
        StringBuffer reStr = new StringBuffer();
        // ��ȡ�������ַ�������
        int strLength = str.trim().length();
        // �������ܳ��ȵĲ�ֵ
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
   * ����������
   * @param args
   */
  public static void main(String[] args) {
    //System.out.println(SBStringUtils.getDateValue("20040201").getTime()-SBStringUtils.getDateValue("20040101").getTime());
  }

}