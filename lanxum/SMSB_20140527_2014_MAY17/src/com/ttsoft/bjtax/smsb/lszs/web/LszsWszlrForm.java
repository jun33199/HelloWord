/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收完税证录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsWszlrForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 明细列表数据集
     */
    private String columns[] =
        {
        "szsmdm", "szdm", "szmc", "szsmmc", "kssl",
        "jsje", "sl", "sjse", "skssksrq", "skssjsrq", };

    /**
     * 纳税人名称
     */
    private String nsrmc; //纳税人名称

    /**
     * 证件类型代码
     */
    private String zjlxdm; //证件类型代码

    /**
     * 证件号码
     */
    private String zjhm; //证件号码

    /**
     * 税务机关组织结构代码
     */
    private String swjgzzjgdm; //税务机关组织结构代码

    /**
     * 税务机关组织结构名称
     */
    private String swjgzzjgmc; //税务机关组织结构名称

    /**
     * 完税证号
     */
    private String wszh; //完税证号

    /**
     * 帐户代码
     */
    private String zhdm; //帐户代码

    /**
     * 票证种类代码
     */
    private String pzzldm; //票证种类代码

    /**
     * 合计实缴金额
     */
    private String hjsjje; //合计实缴金额

    /**
     * 录入日期
     */
    private String lrrq; //录入日期

    /**
     * 录入人
     */
    private String lrr; //录入人

    /**
     * 国家标准行业代码
     */
    private String gjbzhydm; //国家标准行业代码

    /**
     * 登记注册类型代码
     */
    private String djzclxdm; //登记注册类型代码

    /**
     * 登记注册类型名称
     */
    private String djzclxmc; //登记注册类型名称

    /**
     * 地址
     */
    private String dz; //地址

    /**
     * 计算机代码
     */
    private String jsjdm; //计算机代码

    /**
     * 税务机关组织结构代码 2
     */
    private String swjgzzjgdm2; //税务机关组织结构代码 2

    /**
     * 保存脚本信息js
     */
    private String scriptStr; //保存脚本信息

    /**
     * 年度字别
     */
    private String ndzb; //年度字别
    
    /**
     * 税款类型代码
     */
    private String sklxdm;

    /**
     * 税款类型名称
     */
    private String sklxmc;

    public String getSklxdm() {
		return sklxdm;
	}

	public void setSklxdm(String sklxdm) {
		this.sklxdm = sklxdm;
	}

	public String getSklxmc() {
		return sklxmc;
	}

	public void setSklxmc(String sklxmc) {
		this.sklxmc = sklxmc;
	}

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
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

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        /**nsrmc = "";
             zjlxdm = "";
             zjhm = "";
             hjsjje = "";
             gjbzhydm = "";
             djzclxdm = "";
             djzclxmc = "";
             dz = "";
         */
        this.zjlxdm = "02";
        dataList = new ArrayList();
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getWszh ()
    {
        return wszh;
    }

    public void setWszh (String wszh)
    {
        this.wszh = wszh;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public String getZhdm ()
    {
        return zhdm;
    }

    public void setZhdm (String zhdm)
    {
        this.zhdm = zhdm;
    }

    public String getPzzldm ()
    {
        return pzzldm;
    }

    public void setPzzldm (String pzzldm)
    {
        this.pzzldm = pzzldm;
    }

    public String getHjsjje ()
    {
        return hjsjje;
    }

    public void setHjsjje (String hjsjje)
    {
        this.hjsjje = hjsjje;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getGjbzhydm ()
    {
        return gjbzhydm;
    }

    public void setGjbzhydm (String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
    }

    public String getDjzclxdm ()
    {
        return djzclxdm;
    }

    public void setDjzclxdm (String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public String getDjzclxmc ()
    {
        return djzclxmc;
    }

    public void setDjzclxmc (String djzclxmc)
    {
        this.djzclxmc = djzclxmc;
    }

    public String getDz ()
    {
        return dz;
    }

    public void setDz (String dz)
    {
        this.dz = dz;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getSwjgzzjgdm2 ()
    {
        return swjgzzjgdm2;
    }

    public void setSwjgzzjgdm2 (String swjgzzjgdm2)
    {
        this.swjgzzjgdm2 = swjgzzjgdm2;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getNdzb ()
    {
        return ndzb;
    }

    public void setNdzb (String ndzb)
    {
        this.ndzb = ndzb;
    }
}
