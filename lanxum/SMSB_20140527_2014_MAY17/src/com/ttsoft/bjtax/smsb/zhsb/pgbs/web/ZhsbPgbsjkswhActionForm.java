/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ���ۺ��걨_������˰���ܣ������ɿ���¼�룬ά����</p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsjkswhActionForm
    extends BaseForm
{

  /**
   * ���������
   */
  private String jsjdm;
  /**
   * ��˰������
   */
  private String nsrmc;

  //������Ա¼��ϼ�
  //ϵͳ����ϼ�
  /**
   * ������ϸ�����б�
   */
  private List dataList = new ArrayList();

  /**
   * ��������ϸ�����б�
   */
  private List nlwDataList = new ArrayList();

  /**
   * �ɿ�ƾ֤��
   */
  private String jkpzhStr;

  /**
   * ������Դ
   */
  private String sjly;

  /**
   * �걨���
   */
  private String sbbh;

  /**
   * Ԥ�趨�걨���
   */
  private String presbbh;

  /**
   * �ɿ������� һƱһ˰��һƱ��˰
   * added by qianchao 2005.10.27
   */
  private int jksType;

  /**
   * �����ɿ������ʾ�����ʾ��Ϣ
   */
  private String cxStr;

  /**
   * �������Ľɿ������ؽɿ����б�
   */
  private java.util.ArrayList coList = new ArrayList();

  /**
   * �걨����
   */
  private String sbrq; //�걨����

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
    this.actionType = "Query";

  }

  public String getJsjdm()
  {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm)
  {
    this.jsjdm = jsjdm;
  }

  public List getDataList()
  {
    return dataList;
  }

  public void setDataList(List dataList)
  {
    this.dataList = dataList;
  }

  public String getJkpzhStr()
  {
    return jkpzhStr;
  }

  public void setJkpzhStr(String jkpzhStr)
  {
    this.jkpzhStr = jkpzhStr;
  }

  public String getSjly()
  {
    return sjly;
  }

  public void setSjly(String sjly)
  {
    this.sjly = sjly;
  }

  public String getSbbh()
  {
    return sbbh;
  }

  public void setSbbh(String sbbh)
  {
    this.sbbh = sbbh;
  }

  public String getPresbbh()
  {
    return presbbh;
  }

  public void setPresbbh(String presbbh)
  {
    this.presbbh = presbbh;
  }

  public String getCxStr()
  {
    return cxStr;
  }

  public void setCxStr(String cxStr)
  {
    this.cxStr = cxStr;
  }

  public java.util.ArrayList getCoList()
  {
    return coList;
  }

  public void setCoList(java.util.ArrayList coList)
  {
    this.coList = coList;
  }

  public String getSbrq()
  {
    return sbrq;
  }

  public int getJksType()
  {
    return jksType;
  }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public String getNsrmc()
  {
    return nsrmc;
  }

  public void setSbrq(String sbrq)
  {
    this.sbrq = sbrq;
  }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }

  public void setNlwDataList(List nlwDataList)
  {
    this.nlwDataList = nlwDataList;
  }

  public void setNsrmc(String nsrmc)
  {
    this.nsrmc = nsrmc;
  }
}
