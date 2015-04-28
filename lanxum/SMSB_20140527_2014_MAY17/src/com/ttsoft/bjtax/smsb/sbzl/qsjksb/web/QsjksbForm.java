/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.List;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 欠税缴款申报JavaBean </p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbForm
    extends BaseForm
{
    private String mxstrings ;

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
     * 欠税合计
     */
    private String qshj;
    

    /**
     * 申报金额合计
     */
    
    private String sbhj ="0";
    

    /**
     * 滞纳金合计
     */
    private String znjhj ="0";
    
    /**
     * 系统计算合计
     */
    private String xthj ="0";
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
     * 银行信息js数组字符串
     */
    private String bankJsArray;

    /**
     * 申报编号
     */
    private String sbbh;

    /**
     * 纳税人状态
     */
    private String nsrzt;

    /**
     * 缴款书类型
     */
    private int jksType;
    
    /**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm;

    /**
     * 登记注册类型代码
     */
   private String djzclxdm;

    /**
     * 国家标准行业代码
     */
    private String gjbzhydm;

    /**
     * 区县代码
     */
    private String qxdm;
    
    /**
     * 申报编号数据列表
     */
    private List sbbhList = new ArrayList();
    
    /**
     * 修改权限
     */
    private String xgqx;

    public void setMxstrings (String mxstrings)
    {
        this.mxstrings = mxstrings;
    }

    public String getMxstrings ()
    {
        return mxstrings;
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
        this.mxstrings = null;
        this.xgqx = null;
        this.sbbhList.clear();
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
 
  public String getSwjgzzjgdm ()
  {
      return swjgzzjgdm;
  }

  public void setSwjgzzjgdm (String swjgzzjgdm)
  {
      this.swjgzzjgdm = swjgzzjgdm;
  } 
 
  public String getDjzclxdm ()
  {
      return djzclxdm;
  }

  public void setDjzclxdm (String djzclxdm)
  {
      this.djzclxdm = djzclxdm;
  }  
  public String getGjbzhydm ()
  {
      return gjbzhydm;
  }

  public void setGjbzhydm (String gjbzhydm)
  {
      this.gjbzhydm = gjbzhydm;
  } 
  
  public String getQxdm ()
  {
      return qxdm;
  }

  public void setQxdm (String qxdm)
  {
      this.qxdm = qxdm;
  }  

  public List getSbbhList ()
  {
      return sbbhList;
  }

  public void setSbbhList (List sbbhList)
  {
      this.sbbhList = sbbhList;
  }
  
  public String getXgqx ()
  {
      return xgqx;
  }

  public void setXgqx (String xgqx)
  {
      this.xgqx = xgqx;
  }

public String getQshj() {
	return qshj;
}

public void setQshj(String qshj) {
	this.qshj = qshj;
}

public String getSbhj() {
	return sbhj;
}

public void setSbhj(String sbhj) {
	this.sbhj = sbhj;
}

public String getZnjhj() {
	return znjhj;
}

public void setZnjhj(String znjhj) {
	this.znjhj = znjhj;
}   
}

