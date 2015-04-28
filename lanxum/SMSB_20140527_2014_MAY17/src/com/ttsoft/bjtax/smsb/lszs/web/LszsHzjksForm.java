/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收完税证汇总缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsHzjksForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();
    private List bhList = new ArrayList();

    /**
     * 页面明细列表元素集
     */
    private String columns[] =
        {
        "szsmdm", "szdm", "szmc", "szsmmc",
        "kssl", "jsje", "sl", "yjhkc", "sjse",
        "skssksrq", "skssjsrq", "jzbz", "yskmdm",
        "ysjcdm"};

    /**
     * 汇总类型，单位/个人
     */
    private String hzlx; //汇总类型，单位/个人

    /**
     * 汇总开始日期
     */
    private String hzksrq; //汇总开始日期

    /**
     * 汇总结束日期
     */
    private String hzjsrq; //汇总结束日期

    /**
     * 录入人
     */
    private String lrr; //录入人

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm; //税务机关组织机构代码

    /**
     * 税务机关组织机构名称
     */
    private String swjgzzjgmc; //税务机关组织机构名称

    /**
     * 申报汇总单号
     */
    private String sbhzdh; //申报汇总单号

    /**
     * 合计张数
     */
    private String hjzs; //合计张数

    /**
     * 合计金额
     */
    private String hjje; //合计金额

    /**
     * 一票一税标识
     */
    private String ypys; //一票一税标识

    /**
     * 缴款凭证号
     */
    private String jkpzh; //缴款凭证号

    /**
     * 计算机代码
     */
    private String jsjdm; //计算机代码

    /**
     * 是否联网
     */
    private String lw = "00";

    /**
     * 申报编号
     */
    private String sbbh;

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }



	public List getBhList() {
		return bhList;
	}

	public void setBhList(List bhList) {
		this.bhList = bhList;
	}

	public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dataList = new ArrayList();
        bhList = new ArrayList();
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

    public String getHzlx ()
    {
        return hzlx;
    }

    public void setHzlx (String hzlx)
    {
        this.hzlx = hzlx;
    }

    public String getHzksrq ()
    {
        return hzksrq;
    }

    public void setHzksrq (String hzksrq)
    {
        this.hzksrq = hzksrq;
    }

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
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

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public String getHjzs ()
    {
        return hjzs;
    }

    public void setHjzs (String hjzs)
    {
        this.hjzs = hjzs;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getYpys ()
    {
        return ypys;
    }

    public void setYpys (String ypys)
    {
        this.ypys = ypys;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
