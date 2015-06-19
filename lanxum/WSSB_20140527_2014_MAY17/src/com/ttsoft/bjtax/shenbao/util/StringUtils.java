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
 * <p>Description: �걨�������ַ�������</p>
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
    returnStr = StringUtils.killNull(str);
    returnStr = returnStr.trim();
    return returnStr;
  }

  /**
   * �õ�һ��SQL���ַ��������ֶ�ֵ
   * @param str �����ַ���
   * @return ����ַ���
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
   * �õ�һ��SQL�����������ֶ�ֵ
   * @param str �����ַ��� yyyy-mm-dd��yyyymmdd
   * @return ����ַ���
   */
  public static String getSQLDate(String str) {
    //1.ɱ��
    String tmp = StringUtils.killNull(str);
    //2.ȥ��"-"
    tmp = StringUtils.replaceAll(tmp, "-", "");
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
   * �õ�һ��SQL�����������ֶ�ֵ2,������ʱ����235959
   * @param str �����ַ��� yyyy-mm-dd��yyyymmdd
   * @return ����ַ���
   */
  public static String getSQLDate1(String str) {
    //1.ɱ��
    String tmp = StringUtils.killNull(str);
    //2.ȥ��"-"
    tmp = StringUtils.replaceAll(tmp, "-", "");
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
   * �õ�һ��SQL�����������ֶ�ֵ
   * @param str �����ַ��� yyyy-mm-dd
   * @return ����ַ���
   */
  public static String getSQLDate2(String str) {
    //1.ɱ��
    String tmp = StringUtils.killNull(str);
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
   * �õ�һ�����ڣ�yyyy-mm-dd
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
   * �õ�һ�����ڣ�yyyy��mm��dd��
   * @param date �������
   * @return ��ʽ������
   */
  public static String getStrFromDate1(String date) {
    //1.ɱ��
    date = StringUtils.killNull(date);
    if ("".equals(date)) {
      return null;
    }
    //2.ȥ��"-"
    date = StringUtils.replaceAll(date, "-", "");
    //3.ȥ�����˵Ŀո�
    date = date.trim();
    //
    StringBuffer str = new StringBuffer();
    Date tmp = new Date();
    str.append(String.valueOf(tmp.getYear() + 1900));
    str.append("��");
    str.append(String.valueOf(tmp.getMonth() + 1));
    str.append("��");
    str.append(String.valueOf(tmp.getDate()));
    str.append("��");
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
   * �滻�ַ���
   * @param original Դ�ַ���
   * @param find �����ַ���
   * @param replacement �滻�ַ���
   * @return �滻����ַ���
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

      while (dif_length > 0) {
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
//    Timestamp ts=CcsTimeUtil.getNowTimestamp();
//    try{
//    System.out.println(ts);
//      System.out.println(SBStringUtils.getSQLFromTimestamp(ts));
//    }catch(Exception e){
//      e.printStackTrace();
//    }
  }
}
