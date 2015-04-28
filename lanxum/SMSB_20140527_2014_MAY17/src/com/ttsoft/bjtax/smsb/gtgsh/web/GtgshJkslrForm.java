/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户缴款书录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshJkslrForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 页面元素列表数组
     */
    private String[] columns =
        {"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
        "skssksrq", "skssjsrq", "szdm", "szmc"};

    /**
     * 纳税人名称计算机代码
     */
    private String nsrjsjdm; //纳税人名称计算机代码

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
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm; //税务机关组织机构代码

    /**
     * 税务机关组织机构名称
     */
    private String swjgzzjgmc; //税务机关组织机构名称

    /**
     * 合计实缴金额
     */
    private String hjsjje; //合计实缴金额

    /**
     * 申报日期
     */
    private String sbrq; //申报日期

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
     * 前台显示选择列表时使用的所有js数组
     */
    private String scriptStr; //前台显示选择列表时使用的所有js数组

    /**
     * 缴款凭证号
     */
    private String jkpzh; //缴款凭证号**

    /**缴款书合计金额
     *
     */
    private String jkshjje; //缴款书合计金额**

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getNsrjsjdm ()
    {
        return nsrjsjdm;
    }

    public void setNsrjsjdm (String nsrjsjdm)
    {
        this.nsrjsjdm = nsrjsjdm;
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
        this.actionType = "Show";
        this.nsrjsjdm = "";
        this.nsrmc = "";
        this.djzclxdm = "";
        this.gjbzhydm = "";
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

    public String getHjsjje ()
    {
        return hjsjje;
    }

    public void setHjsjje (String hjsjje)
    {
        this.hjsjje = hjsjje;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
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

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJkshjje ()
    {
        return jkshjje;
    }

    public void setJkshjje (String jkshjje)
    {
        this.jkshjje = jkshjje;
    }
}
