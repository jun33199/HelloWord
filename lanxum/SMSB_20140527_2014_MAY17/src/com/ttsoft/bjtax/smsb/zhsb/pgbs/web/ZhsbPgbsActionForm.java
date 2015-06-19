/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.List;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ۺ��걨_������˰JavaBean </p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsActionForm
    extends BaseForm
{
    private String[] columns =
                               {"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
                               "skssksrq", "skssjsrq", "szdm", "szmc", "sl"};

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * ��������
     */
    private String yhmc;

    /**
     * ���д���
     */
    private String yhdm;

    /**
     * �˺�
     */
    private String zh;

    /**
     * ��λ����
     */
    private String dwmc;

    /**
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * ˰����������
     */
    private String sklxmc;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ������Ա¼��ϼ�
     */
    private String lrhj;

    /**
     * ϵͳ����ϼ�
     */
    private String xthj;
    /**
     * ���������б�
     */
    private List dataList = new ArrayList();
    /**
     * ������Ϣ�б�
     */
    private List bankList = new ArrayList();

    /**
     * ��˰������
     */
    private String nsrmc;
    /**
     * ��ʼ���б�
     */
    private java.util.List initMxList;
    /**
     * js�����ַ���
     */
    private String scriptStr;
    /**
     * ������Ϣjs�����ַ���
     */
    private String bankJsArray;
    /**
     * ��֪����
     */
    private String gzsx;
    /**
     * ��֪�����б�
     */
    private java.util.List gzsxList = new ArrayList();
    /**
     * ����˰��־
     */
    private boolean isadditons;

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ˰����������js����['��','','����','��','']
     */
    private String skssrqArr;

    /**
     * �����ʷ����־
     */
    private boolean wz;

    /**
     * ��˰��״̬
     */
    private String nsrzt;

    /**
     * �ɿ�������
     */
    private int jksType;


    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public String[] getColumns ()
    {
        return columns;
    }

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
        this.dwmc = null;
        this.dataList.clear();
        this.yhmc = null;
        this.bankList.clear();
        //this.sbrq=null;
        //this.jsjdm=null;
        this.nsrmc = null;
        this.bankJsArray = "var bankInfo=new Array();";
        this.gzsxList.clear();
        this.isadditons = true;
        this.nsrzt = CodeConstant.NSRZT_ZC;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getYhdm ()
    {
        return yhdm;
    }

    public void setYhdm (String yhdm)
    {
        this.yhdm = yhdm;
    }

    public String getZh ()
    {
        return zh;
    }

    public void setZh (String zh)
    {
        this.zh = zh;
    }

    public String getDwmc ()
    {
        return dwmc;
    }

    public void setDwmc (String dwmc)
    {
        this.dwmc = dwmc;
    }

    public String getSklxdm ()
    {
        return sklxdm;
    }

    public void setSklxdm (String sklxdm)
    {
        this.sklxdm = sklxdm;
    }

    public String getSklxmc ()
    {
        return sklxmc;
    }

    public void setSklxmc (String sklxmc)
    {
        this.sklxmc = sklxmc;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getLrhj ()
    {
        return lrhj;
    }

    public void setLrhj (String lrhj)
    {
        this.lrhj = lrhj;
    }

    public String getXthj ()
    {
        return xthj;
    }

    public void setXthj (String xthj)
    {
        this.xthj = xthj;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getBankList ()
    {
        return bankList;
    }

    public void setBankList (java.util.List bankList)
    {
        this.bankList = bankList;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public java.util.List getInitMxList ()
    {
        return initMxList;
    }

    public void setInitMxList (java.util.List initMxList)
    {
        this.initMxList = initMxList;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getBankJsArray ()
    {
        return bankJsArray;
    }

    public void setBankJsArray (String bankJsArray)
    {
        this.bankJsArray = bankJsArray;
    }

    public String getGzsx ()
    {
        return gzsx;
    }

    public void setGzsx (String gzsx)
    {
        this.gzsx = gzsx;
    }

    public java.util.List getGzsxList ()
    {
        return gzsxList;
    }

    public void setGzsxList (java.util.List gzsxList)
    {
        this.gzsxList = gzsxList;
    }

    public boolean isIsadditons ()
    {
        return isadditons;
    }

    public void setIsadditons (boolean isadditons)
    {
        this.isadditons = isadditons;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

    public String getSkssrqArr ()
    {
        return skssrqArr;
    }

    public void setSkssrqArr (String skssrqArr)
    {
        this.skssrqArr = skssrqArr;
    }

    public boolean isWz ()
    {
        return wz;
    }

    public void setWz (boolean wz)
    {
        this.wz = wz;
    }

    public String getNsrzt ()
    {
        return nsrzt;
    }

  public int getJksType()
  {
    return jksType;
  }

  public void setNsrzt (String nsrzt)
    {
        this.nsrzt = nsrzt;
    }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }
}
