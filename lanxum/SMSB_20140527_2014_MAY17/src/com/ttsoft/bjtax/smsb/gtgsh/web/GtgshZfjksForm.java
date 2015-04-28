/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户作废缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshZfjksForm
    extends BaseForm
{
    /**
     * 汇总方式
     */
    private String hzfs; //汇总方式

    /**
     * 录入人
     */
    private String lrr; //录入人

    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 是否联网
     */
    private String lw="00";

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm; //税务机关组织机构代码

    /**
     * 税务机关组织机构名称
     */
    private String swjgzzjgmc; //税务机关组织机构名称

    /**
     * 主表list
     */
    private List dataList = new ArrayList(); //主表list

    /**
     * 明细list
     */
    private List detailList = new ArrayList(); //明细list

    /**
     * 计算机代码
     */
    private String jsjdm; //计算机代码

    /**
     * 汇总日期
     */
    private String hzrq; //汇总日期

    /**
     * 申报汇总单号
     */
    private String sbhzdh; //申报汇总单号

    /**
     * 缴款凭证号
     */
    private String jkpzh; //缴款凭证号

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {

    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public String getHzfs ()
    {
        return hzfs;
    }

    public void setHzfs (String hzfs)
    {
        this.hzfs = hzfs;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
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

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getDetailList ()
    {
        return detailList;
    }

    public void setDetailList (java.util.List detailList)
    {
        this.detailList = detailList;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getHzrq ()
    {
        return hzrq;
    }

    public void setHzrq (String hzrq)
    {
        this.hzrq = hzrq;
    }

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
