/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

import java.util.ArrayList;
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

public class WjgrActionForm
    extends BaseForm
{
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
     * ���������
     */
    private String jsjdm;

    /**
     * ��ϸ�����б�
     */
    private java.util.ArrayList dataList = new ArrayList();

    /**
     * ��������
     */
    private String[] columns =
        {"szsmdm", "sdksrq", "sdjsrq", "srermb", "zhrmb",
        "rmbhj", "jfye", "ynssde", "sl", "sskcs", "ynse",
        "ykjse", "ybtsk", "yjzgsk", "fdkce", "bzmx"};

    /**
     * �����б���ʹ�õ�js����
     */
    private String scriptStr;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ���Ҵ���
     */
    private String gjdm;

    /**
     * ��������
     */
    private String gjmc;

    /**
     * �ֻ�����
     */
    private String dhrq;

    /**
     * ְҵ����
     */
    private String zydm;

    /**
     * ְҵ����
     */
    private String zymc;

    /**
     * ����λ
     */
    private String fwdw;

    /**
     * ����ص�
     */
    private String fwdd;

    /**
     * ���й�����סַ
     */
    private String jnzz;

    /**
     * �Ƿ�פ
     */
    private String sfczbs;

    /**
     * ֤�����ʹ���
     */
    private String zjlxdm;

    /**
     * ֤������
     */
    private String zjhm;

    /**
     * �ⷿ�ѿ۳���
     */
    private String zffkcs;

    /**
     * ������ʽ
     */
    private String fdfs;

    /**
     * ��λ��������
     */
    private String dwfdbl;

    /**
     * ���д���
     */
    private String yhdm;

    /**
     * ��������
     */
    private String yhmc;

    /**
     * �˺�
     */
    private String zh;

    /**
     * һƱһ˰����һƱ��˰
     * added by qianchao 2005.11.1
     */
    private int jksType;

    /**
     * �����˺�
     */
    private java.util.List bankList = new ArrayList();

    /**
     * �����˺�ʹ�õ�js����
     */
    private String bankJsArray;

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
        this.dataList.clear();
        this.bankList.clear();
        this.bankJsArray = "var bankInfo=new Array();";
        this.dhrq = "";
        this.dwfdbl = "";
        this.fdfs = "";
        this.fwdd = "";
        this.fwdw = "";
        this.jnzz = "";
        this.nsrmc = "";
        this.sfczbs = "";
        this.zffkcs = "";
        this.zjhm = "";
        this.zydm = "";
    }

    public String getSkssksrq ()
    {
        return skssksrq;
    }

    public void setSkssksrq (String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getSkssjsrq ()
    {
        return skssjsrq;
    }

    public void setSkssjsrq (String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
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

    public String getGjmc ()
    {
        return gjmc;
    }

    public void setGjmc (String gjmc)
    {
        this.gjmc = gjmc;
    }

    public String getDhrq ()
    {
        return dhrq;
    }

    public void setDhrq (String dhrq)
    {
        this.dhrq = dhrq;
    }

    public String getZydm ()
    {
        return zydm;
    }

    public void setZydm (String zydm)
    {
        this.zydm = zydm;
    }

    public String getZymc ()
    {
        return zymc;
    }

    public void setZymc (String zymc)
    {
        this.zymc = zymc;
    }

    public String getFwdw ()
    {
        return fwdw;
    }

    public void setFwdw (String fwdw)
    {
        this.fwdw = fwdw;
    }

    public String getFwdd ()
    {
        return fwdd;
    }

    public void setFwdd (String fwdd)
    {
        this.fwdd = fwdd;
    }

    public String getJnzz ()
    {
        return jnzz;
    }

    public void setJnzz (String jnzz)
    {
        this.jnzz = jnzz;
    }

    public String getSfczbs ()
    {
        return sfczbs;
    }

    public void setSfczbs (String sfczbs)
    {
        this.sfczbs = sfczbs;
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

    public String getZffkcs ()
    {
        return zffkcs;
    }

    public void setZffkcs (String zffkcs)
    {
        this.zffkcs = zffkcs;
    }

    public String getFdfs ()
    {
        return fdfs;
    }

    public void setFdfs (String fdfs)
    {
        this.fdfs = fdfs;
    }

    public String getDwfdbl ()
    {
        return dwfdbl;
    }

    public void setDwfdbl (String dwfdbl)
    {
        this.dwfdbl = dwfdbl;
    }

    public String getYhdm ()
    {
        return yhdm;
    }

    public void setYhdm (String yhdm)
    {
        this.yhdm = yhdm;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getZh ()
    {
        return zh;
    }

    public void setZh (String zh)
    {
        this.zh = zh;
    }

    public java.util.List getBankList ()
    {
        return bankList;
    }

    public void setBankList (java.util.List bankList)
    {
        this.bankList = bankList;
    }

    public String getBankJsArray ()
    {
        return bankJsArray;
    }

    public void setBankJsArray (String bankJsArray)
    {
        this.bankJsArray = bankJsArray;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

  public int getJksType()
  {
    return jksType;
  }

  public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }
}
