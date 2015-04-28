/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 综合申报_评估补税JavaBean </p>
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
     * 告知事项
     */
    private String gzsx;
    /**
     * 告知事项列表
     */
    private java.util.List gzsxList = new ArrayList();
    /**
     * 附加税标志
     */
    private boolean isadditons;

    /**
     * 申报编号
     */
    private String sbbh;

    /**
     * 税款所属日期js数组['月','','季度','年','']
     */
    private String skssrqArr;

    /**
     * 内外资分类标志
     */
    private boolean wz;

    /**
     * 纳税人状态
     */
    private String nsrzt;

    /**
     * 缴款书类型
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
