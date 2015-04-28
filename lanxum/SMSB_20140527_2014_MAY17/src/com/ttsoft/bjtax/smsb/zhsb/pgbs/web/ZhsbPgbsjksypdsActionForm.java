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

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块_评估补税</p>
 * <p>Description: 一票一税缴款书维护。</p>
 * @author zzb  20090330
 * @version 1.1
 */

public class ZhsbPgbsjksypdsActionForm
    extends BaseForm
{
    /**
     * 域名称数组
     */
    private String[] columns =
        {
        "szsmdm", "jkpzh", "jsjdm", "yskmdm", "yskmmc",
        "ysjcdm", "ysjcmc",
        "szsmmc", "kssl", "jsje", "sjse", "skssksrq",
        "skssjsrq", "rkje", "sbbh",
        "sjfc", "qjfc", "nd"};

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 缴款凭证号
     */
    private String jkpzh; //完税凭证号

    /**
     * 明细数据列表
     */
    private java.util.ArrayList dataList = new ArrayList();

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgmc;

    /**
     * 银行名称
     */
    private String yhmc;

    /**
     * 录入日起
     */
    private String lrrq;

    /**
     * 实缴金额
     */
    private String[] sjje;

    /**
     * js数组
     */
    private String scriptStr;

    /**
     * 转移目标
     */
    private String forward;

    /**
     * 数据来源
     */
    private String sjly;

    /**
     * 返回时用来判断是否返回申报编号
     */
    private String presbbh;

    /**
     * 申报编号
     */
    private String sbbh;

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
        this.actionType = "Query";
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
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

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String[] getSjje ()
    {
        return sjje;
    }

    public void setSjje (String[] sjje)
    {
        this.sjje = sjje;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getForward ()
    {
        return forward;
    }

    public void setForward (String forward)
    {
        this.forward = forward;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public String getPresbbh ()
    {
        return presbbh;
    }

    public void setPresbbh (String presbbh)
    {
        this.presbbh = presbbh;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }
}