package com.ttsoft.bjtax.shenbao.util;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨ģ��</p>
 * <p>Description: ʵ�ֱ�����˰�걨ģ��</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class ResponseUtils {

  public static String filter(String value) {

      if (value == null)
          return "";

      char content[] = new char[value.length()];
      value.getChars(0, value.length(), content, 0);
      StringBuffer result = new StringBuffer(content.length + 50);
      for (int i = 0; i < content.length; i++) {
          switch (content[i]) {
          case ' ':
                result.append("&nbsp;");
                break;
          case '<':
              result.append("&lt;");
              break;
          case '>':
              result.append("&gt;");
              break;
          case '&':
              result.append("&amp;");
              break;
          case '"':
              result.append("&quot;");
              break;
          default:
              result.append(content[i]);
          }
      }
      return (result.toString());

  }


  public static String filter(Object value) {
     if(value == null) return "";

     return filter(value.toString());
  }


}