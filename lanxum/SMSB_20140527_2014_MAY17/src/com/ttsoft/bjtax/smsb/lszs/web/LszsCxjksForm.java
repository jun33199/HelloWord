/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ���ճ����ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsCxjksForm
    extends BaseForm
{
  /**
   * �걨����(������)
   */
  private List dataList = new ArrayList();

  /**
   * �걨����(��������)
   */
  private List nlwDataList = new ArrayList();

  /**
   * ¼����
   */
  private String lrrdm; //¼����

  /**
   * �ɿ�ƾ֤��
   */
  private String headJkpzh; //�ɿ�ƾ֤��

  /**
   * added by qianchao 2005.10.26
   * �ɿ�ƾ֤��
   */
  private String headSbbh; //�걨��ź�

  /**
   * added by qianchao 2005.10.26
   * �ɿ�������
   */
  private int jksType;

  /**
   * ���������
   */
  private String headJsjdm; //���������

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
  }

  public String getLrrdm()
  {
    return lrrdm;
  }

  public void setLrrdm(String lrrdm)
  {
    this.lrrdm = lrrdm;
  }

  public String getHeadJkpzh()
  {
    return headJkpzh;
  }

  public void setHeadJkpzh(String headJkpzh)
  {
    this.headJkpzh = headJkpzh;
  }

  public String getHeadJsjdm()
  {
    return headJsjdm;
  }

  public String getHeadSbbh()
  {
    return headSbbh;
  }

  public List getDataList()
  {
    return dataList;
  }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public int getJksType()
  {
    return jksType;
  }

  public void setHeadJsjdm(String headJsjdm)
  {
    this.headJsjdm = headJsjdm;
  }

  public void setHeadSbbh(String headSbbh)
  {
    this.headSbbh = headSbbh;
  }

  public void setDataList(List dataList)
  {
    this.dataList = dataList;
  }

  public void setNlwDataList(List nlwDataList)
  {
    this.nlwDataList = nlwDataList;
  }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }

}
