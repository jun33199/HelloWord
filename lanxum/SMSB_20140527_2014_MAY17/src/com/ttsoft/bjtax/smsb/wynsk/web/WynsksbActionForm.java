/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ����Ӧ��˰�ѿ��걨ģ�顣</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbActionForm
    extends BaseForm {
  /**
   * ���������
   */
  private String jsjdm;

  /**
   * ��˰������
   */
  private String nsrmc;

  /**
   * ˰��������ʼ����
   */
  private String skssksrq;

  /**
   * ˰��������������
   */
  private String skssjsrq;

  /**
   * �걨����
   */
  private String sbrq;

  /**
   * ��˰��˰�������֯��������
   */
  private String swjgzzjgdm;

  /**
   * ˰�������֯��������
   */
  private String swjgzzjgmc;

  /**
   * ��˰��ע���ַ��ϵ�绰
   *
   */
  private String zcdzlxdh;

  /**
   * ���걨����
   */
  private java.util.ArrayList dataList = new ArrayList();

  /**
   * �걨���
   */
  private String sbbh;

  /**
   * ˰����������
   */
  private String skssrqArr;
  
  
  //-------------------add  by tangchangfu ��˰��˰�걨��Ŀ 2014-04-08  start
  /**
   * ��˰�걨ԭ�����
   */
  private String wssbyydm;
  
  private String wssbyynr;
  /**
   * ��˰�걨��ע
   */
  private String bz;
  
  
  
  //-------------------add  by tangchangfu ��˰��˰�걨��Ŀ 2014-04-08 end
  
  
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  public String getJsjdm() {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }

  public String getNsrmc() {
    return nsrmc;
  }

  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }

  public String getSkssksrq() {
    return skssksrq;
  }

  public void setSkssksrq(String skssksrq) {
    this.skssksrq = skssksrq;
  }

  public String getSkssjsrq() {
    return skssjsrq;
  }

  public void setSkssjsrq(String skssjsrq) {
    this.skssjsrq = skssjsrq;
  }

  public String getSbrq() {
    return sbrq;
  }

  public void setSbrq(String sbrq) {
    this.sbrq = sbrq;
  }

  public String getSwjgzzjgdm() {
    return swjgzzjgdm;
  }

  public void setSwjgzzjgdm(String swjgzzjgdm) {
    this.swjgzzjgdm = swjgzzjgdm;
  }

  public String getSwjgzzjgmc() {
    return swjgzzjgmc;
  }

  public void setSwjgzzjgmc(String swjgzzjgmc) {
    this.swjgzzjgmc = swjgzzjgmc;
  }

  public String getZcdzlxdh() {
    return zcdzlxdh;
  }

  public void setZcdzlxdh(String zcdzlxdh) {
    this.zcdzlxdh = zcdzlxdh;
  }

  public java.util.ArrayList getDataList() {
    return dataList;
  }

  public void setDataList(java.util.ArrayList dataList) {
    this.dataList = dataList;
  }

  public String getSbbh() {
    return sbbh;
  }

  public void setSbbh(String sbbh) {
    this.sbbh = sbbh;
  }

  public String getSkssrqArr() {
    return skssrqArr;
  }

  public void setSkssrqArr(String skssrqArr) {
    this.skssrqArr = skssrqArr;
  }

  /* start added by huxiaofeng 2005.8.16*/
  /**
   * ��˰��״̬
   */
  private String nsrzt; //��˰��״̬

  public void setNsrzt(String nsrzt) {
    this.nsrzt = nsrzt;
  }

  public String getNsrzt() {
    return nsrzt;
  }

public String getWssbyydm() {
	return wssbyydm;
}

public void setWssbyydm(String wssbyydm) {
	this.wssbyydm = wssbyydm;
}

public String getWssbyynr() {
	return wssbyynr;
}

public void setWssbyynr(String wssbyynr) {
	this.wssbyynr = wssbyynr;
}

public String getBz() {
	return bz;
}

public void setBz(String bz) {
	this.bz = bz;
}

  /* end added by huxiaofeng 2005.8.16*/

}
