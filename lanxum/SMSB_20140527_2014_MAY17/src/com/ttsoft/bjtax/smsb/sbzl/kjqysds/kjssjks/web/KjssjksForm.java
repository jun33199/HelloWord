/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ����Ϣ�Ƽ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ����Ϣ�Ƽ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.List;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Description: �۽���ҵ����˰����˰�սɿ��� </p>
 * @author wangxm
 * @version 1.1
 */

public class KjssjksForm
    extends BaseForm
{
    private String[] columns =
                               {"szsmdm", "szsmmc", "jsje", "sjse",
                               "skssksrq", "skssjsrq", "szdm", "szmc","sl"};
    /**
     * ��˰���
     */
    private String jsje;
    /**
     * ʵ��˰��
     */
    private String sjse;
    /**
     * ��ͬ���
     */
    private String htbh;
    /**
     * ��ͬ����
     */
    private String htmc;
	/**
	 * ˰��˰Ŀ����
	 */
	private String szsmdm;
	/**
	 * ˰������_��ҵ����˰
	 */
	private String szmc;
	/**
	 * ˰�ִ���_30
	 */
	private String szdm;
	/**
	 * ��˰��Ŀ����_˰��˰Ŀ����
	 */
	private String szsmmc;
	/**
	 * ˰��������ʼ����
	 */
	private String skssksrq;
	/**
	 * ˰��������������
	 */
	private String skssjsrq;

	/**
	 * ¼����Ա
	 */
	private String lrr;


    /**
     * �����Ǽ����
     */
     private String badjxh;
     /**
      * ��������
      */
     private String bgbxh;
    
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
     * �걨���
     */
    private String sbbh;

    /**
     * ˰����������js����['��','','����','��','']
     */
    private String skssrqArr;


    /**
     * ��˰��״̬
     */
    private String nsrzt;

    /**
     * �ɿ�������
     */
    private int jksType;
    /**
     * ���ɽɿ���List
     */
    private List jksList;
	/**
	 * ˰��
	 */
	private String Sl;



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

public String getBadjxh() {
	return badjxh;
}

public void setBadjxh(String badjxh) {
	this.badjxh = badjxh;
}

public String getBgbxh() {
	return bgbxh;
}

public void setBgbxh(String bgbxh) {
	this.bgbxh = bgbxh;
}

public String getSzsmdm() {
	return szsmdm;
}

public void setSzsmdm(String szsmdm) {
	this.szsmdm = szsmdm;
}

public String getSzmc() {
	return szmc;
}

public void setSzmc(String szmc) {
	this.szmc = szmc;
}

public String getSzdm() {
	return szdm;
}

public void setSzdm(String szdm) {
	this.szdm = szdm;
}

public String getSzsmmc() {
	return szsmmc;
}

public void setSzsmmc(String szsmmc) {
	this.szsmmc = szsmmc;
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


public String getLrr() {
	return lrr;
}

public void setLrr(String lrr) {
	this.lrr = lrr;
}

public String getHtbh() {
	return htbh;
}

public void setHtbh(String htbh) {
	this.htbh = htbh;
}

public String getJsje() {
	return jsje;
}

public void setJsje(String jsje) {
	this.jsje = jsje;
}

public String getSjse() {
	return sjse;
}

public void setSjse(String sjse) {
	this.sjse = sjse;
}

public String getHtmc() {
	return htmc;
}

public void setHtmc(String htmc) {
	this.htmc = htmc;
}

public List getJksList() {
	return jksList;
}

public void setJksList(List jksList) {
	this.jksList = jksList;
}

public String getSl() {
	return Sl;
}

public void setSl(String sl) {
	Sl = sl;
}
}
