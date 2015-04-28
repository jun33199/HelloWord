/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信息科技有限公司，版权所有. </p>
 * <p>Company: 四一安信息科技有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Description: 扣缴企业所得税生成税收缴款书 </p>
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
     * 计税金额
     */
    private String jsje;
    /**
     * 实款税额
     */
    private String sjse;
    /**
     * 合同编号
     */
    private String htbh;
    /**
     * 合同名称
     */
    private String htmc;
	/**
	 * 税种税目代码
	 */
	private String szsmdm;
	/**
	 * 税种名称_企业所得税
	 */
	private String szmc;
	/**
	 * 税种代码_30
	 */
	private String szdm;
	/**
	 * 纳税项目名称_税种税目名称
	 */
	private String szsmmc;
	/**
	 * 税款所属开始日期
	 */
	private String skssksrq;
	/**
	 * 税款所属结束日期
	 */
	private String skssjsrq;

	/**
	 * 录入人员
	 */
	private String lrr;


    /**
     * 备案登记序号
     */
     private String badjxh;
     /**
      * 报告表序号
      */
     private String bgbxh;
    
    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 银行名称
     */
    private String yhmc;

    /**
     * 银行代码
     */
    private String yhdm;

    /**
     * 账号
     */
    private String zh;

    /**
     * 单位名称
     */
    private String dwmc;

    /**
     * 税款类型代码
     */
    private String sklxdm;

    /**
     * 税款类型名称
     */
    private String sklxmc;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 操作人员录入合计
     */
    private String lrhj;

    /**
     * 系统计算合计
     */
    private String xthj;
    /**
     * 明晰数据列表
     */
    private List dataList = new ArrayList();
    /**
     * 银行信息列表
     */
    private List bankList = new ArrayList();

    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 初始化列表
     */
    private java.util.List initMxList;
    /**
     * js数组字符串
     */
    private String scriptStr;
    /**
     * 银行信息js数组字符串
     */
    private String bankJsArray;

    /**
     * 申报编号
     */
    private String sbbh;

    /**
     * 税款所属日期js数组['月','','季度','年','']
     */
    private String skssrqArr;


    /**
     * 纳税人状态
     */
    private String nsrzt;

    /**
     * 缴款书类型
     */
    private int jksType;
    /**
     * 生成缴款书List
     */
    private List jksList;
	/**
	 * 税率
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
