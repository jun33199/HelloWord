package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: ������˰��������ϵͳ--���ڶ������пۿ�-�ۿ�״̬��ѯ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)�����еط�˰��֣�������һ���ſƼ����޹�˾ ��Ȩ���� 2005</p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 * @author not attributable
 * @version 1.0
 */

public class KkztcxForm
    extends BaseForm {
  public KkztcxForm() {
  }

  /** ���          */
  private String nd;
  /** ����          */
  private String zq;




  /**
   * ��ϸ��Ŀ����
   */
  private java.util.ArrayList dataList;

  /**
   * ������
   * @return
   */
  public String getNd() {
    return nd;
  }

  /**
   * �������
   * @return
   */
  public String getZq() {
    return zq;
  }

  /**
   * ��������
   * @param zq
   */
  public void setZq(String zq) {
    this.zq = zq;
  }

  /**
   * �������
   * @param nd
   */
  public void setNd(String nd) {
    this.nd = nd;
  }
  /**
   * ������ݼ�
   * @return
   */
  public java.util.ArrayList getDataList() {
      return dataList;
  }
  /**
   * �������ݼ�
   * @param dataList
   */
  public void setDataList(java.util.ArrayList dataList) {
         this.dataList = dataList;
     }


}