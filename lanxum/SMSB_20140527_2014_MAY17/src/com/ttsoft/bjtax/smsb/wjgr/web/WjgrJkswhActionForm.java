/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrJkswhActionForm
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

    /**
     * ���Ҵ���
     */
    private String gjdm;

    /**
     * ֤�����ʹ���
     */
    private String zjlxdm;

    /**
     * ֤������
     */
    private String zjhm;

    /**
     * �����걨���б�
     */
    private List dataList = new ArrayList();

    /**
     * �������ɿ����б�
     */
    private List nlwDataList = new ArrayList();

    /**
     * �ɿ�������
     */
    private int jksType = 0;

    /**
     * �ɿ�ƾ֤��
     */
    private String headJkpzh;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * �걨���
     */
    private String sbbh;

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getGjdm ()
    {
        return gjdm;
    }

    public void setGjdm (String gjdm)
    {
        this.gjdm = gjdm;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

  public List getNlwDataList()
  {
    return nlwDataList;
  }

  public int getJksType()
  {
    return jksType;
  }

  public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
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
