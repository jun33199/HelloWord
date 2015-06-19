package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.sfgl.common.util.SfStringUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.Debug;

/**
 * ����JS����
 */
public class EArray {

  /**
   * û��conn
   */
  public EArray() {
    try {

    }
    catch (Exception ex) {

    }
  }


  /**
   * ����1ά����������ʾ�����б�
   * @param nameHead ����
   * @param code ����
   * @param smList �б�]
   * @return String
   */
  public String getMsgsByCode(String nameHead, String code, ArrayList smList) {
    SfHashList hl = CodeManager.getCodeList(code,CodeConstants.CODE_MAP_MAPLIST);
    String keys[] = hl.getKeys();//{"szsmdm","descr"};
    StringBuffer msg_value = new StringBuffer("");
    StringBuffer msg_name = new StringBuffer("");
    StringBuffer msg_value_all = new StringBuffer("");
    StringBuffer msg_name_all = new StringBuffer("");
    StringBuffer msg_value_del = new StringBuffer("");
    StringBuffer msg_name_del = new StringBuffer("");
    String temp = "";
    StringBuffer result = new StringBuffer("");
    String smListStr = ",";
    try {
      for (int i = 0; i < smList.size(); i++) {
        smListStr += ( (HashMap) smList.get(i)).get("szsmdm") + ",";
      }

      String name = "";
      String value = "";
      //��������1ά����
      for (int ii = 0; ii < hl.size(); ii++) {
        value = hl.get(ii,keys[0]) == null ? "" :hl.get(ii,keys[0]);
        name = hl.get(ii,keys[1]) == null ? "" :hl.get(ii,keys[1]);

        if (smListStr.indexOf("," + value + ",") < 0) {
          msg_value.append("\"").append(value).append("\",");
          msg_name.append("\"").append(name).append("\",");
        }
        else {
          msg_value_del.append("\"").append(value).append("\",");
          msg_name_del.append("\"").append(name).append("\",");
        }
        msg_value_all.append("\"").append(value).append("\",");
        msg_name_all.append("\"").append(name).append("\",");
      }

      //conn.close();
    }
    catch (Exception ex) {
      Debug.out("EReader getMsgsByCode error : " + ex.getMessage());
      return "";
    } //end try catch

    result.append("var ").append(nameHead).append("_value = ").append(
        getFormatArrayStr(msg_value.toString())).append(";\n");
    result.append("var ").append(nameHead).append("_name = ").append(
        getFormatArrayStr(msg_name.toString())).append(";\n");
    result.append("var ").append(nameHead).append("_value_all = ").append(
        getFormatArrayStr(msg_value_all.toString())).append(";\n");
    result.append("var ").append(nameHead).append("_name_all = ").append(
        getFormatArrayStr(msg_name_all.toString())).append(";\n");
    if (msg_value_del.toString().length() > 0) {
      result.append("var ").append(nameHead).append("_value_del = ").append(
          getFormatArrayStr(msg_value_del.toString())).append(";\n");
      result.append("var ").append(nameHead).append("_name_del = ").append(
          getFormatArrayStr(msg_name_del.toString())).append(";\n");
    }
    else {
      result.append("var ").append(nameHead).append(
          "_name_del = new Array();\n");
      result.append("var ").append(nameHead).append(
          "_value_del = new Array();\n");
    }
    return result.toString();
  }

  /**
   * ����һ��2ά���飨���ݼ�����1ά���飨�ֶ�������
   * @param name ����
   * @param code ����
   * @return String
   */
  public String getArrayByCode(String name, String code) {
    SfHashList hl = CodeManager.getCodeList(code,CodeConstants.CODE_MAP_MAPLIST);
    String keys[] = hl.getKeys();
    for(int i=0;i<keys.length;i++){
      Debug.out(keys[i]);
    }
    StringBuffer result = new StringBuffer("");
    StringBuffer field = new StringBuffer("");
    StringBuffer list = new StringBuffer("");
    String temp = "";
    try {

      //����1ά����
      for (int jj = 0; jj < keys.length ; jj++) {
        field.append("\"").append(keys[jj]).append("\",");
      } //end for

      //����2ά����
      for (int ii = 0; ii < hl.size() ; ii++) {
        StringBuffer tempStr = new StringBuffer("");
        for (int jj = 0; jj < keys.length ; jj++) {
          temp = hl.get(ii,keys[jj])==null?"":hl.get(ii,keys[jj]);
          tempStr.append("\"").append(temp).append("\",");
        } //end for
        list.append(getFormatArrayStr(tempStr.toString())).append(",");
      }
    }
    catch (Exception ex) {
      Debug.out("EReader getArrayByCode error : " + ex.getMessage());
      return "";
    } //end try catch
    result.append("var ").append(name).append("_fields = ").append(
        getFormatArrayStr(field.toString())).append(";\n");
    result.append("var ").append(name).append(" = ").append(getFormatArrayStr(
        list.toString())).append(";\n");
    return result.toString();
  }


  /**
   * ��ʽ����ö�ٱ��ʽ����β�����ź�ȥ��ĩβ��","
   * @param arrayStr ����
   * @return ����ö���ִ�
   */
  private String getFormatArrayStr(String arrayStr) {
    if (arrayStr.equals("")) {
      //return "";
      /*******************************************/
      /**              Shi Yanfeng              **/
      return "new Array()";
      /*******************************************/

    }
    return "[" + arrayStr.substring(0, arrayStr.length() - 1) + "]";
  }


}
