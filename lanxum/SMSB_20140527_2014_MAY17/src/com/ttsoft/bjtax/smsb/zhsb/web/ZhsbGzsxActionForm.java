/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ۺ��걨��֪�������и�֪��������ʾ�б�������ȷ�Ϻ�ת���ۺ��걨ҳ�档<br>
 * ���û�и�֪������ֱ��ת���ۺ��걨ҳ�档 </p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbGzsxActionForm
    extends BaseForm
{
    /**
     * ����������
     */
  private String[] columns;
  /**
   * ��ϸ�����б�
   */
  private ArrayList dataList = new ArrayList();
  /**
   * ���������
   */
  private String jsjdm;
  /**
   * ��֪����
   */
  private String gzsx;
  /**
   * ��������ʾ
   */
  private String fzcbs;
  /**
   * �걨����
   */
  private String sbrq;
  public void setColumns(String[] columns)
  {
    this.columns = columns;
  }

  public String[] getColumns()
  {
    return columns;
  }

  public void setDataList(ArrayList dataList)
  {
    this.dataList = dataList;
  }

  public ArrayList getDataList()
  {
    return dataList;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest)
  {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest)
  {
    this.gzsx = "1";
  }

  public String getJsjdm()
  {
    return jsjdm;
  }

  public void setJsjdm(String jsjdm)
  {
    this.jsjdm = jsjdm;
  }

  public String getGzsx()
  {
    return gzsx;
  }

  public void setGzsx(String gzsx)
  {
    this.gzsx = gzsx;
  }

  public String getFzcbs()
  {
    return fzcbs;
  }

  public void setFzcbs(String fzcbs)
  {
    this.fzcbs = fzcbs;
  }

  public String getSbrq()
  {
    return sbrq;
  }

  public void setSbrq(String sbrq)
  {
    this.sbrq = sbrq;
  }
}