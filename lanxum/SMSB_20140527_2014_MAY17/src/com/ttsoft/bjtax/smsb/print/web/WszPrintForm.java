/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 打印完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class WszPrintForm
    extends BaseForm
{
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 注册类型
     */
    private String zclx; //注册类型

    /**
     * 填发日期
     */
    private String tfrq; //填发日期

    /**
     * 征收机关
     */
    private String zsjg; //征收机关

    /**
     * 纳税人计算机代码，对于零散，则是单位计算机代码
     */
    private String nsrjsjdm; //纳税人计算机代码，对于零散，则是单位计算机代码

    /**
     * 地址
     */
    private String dz; //地址

    /**
     * 纳税人名称
     */
    private String nsrmc; //纳税人名称

    /**
     * 税款所属开始日期
     */
    private String skssksrq; //税款所属开始日期

    /**
     * 税款所属结束日期
     */
    private String skssjsrq; //税款所属结束日期

    /**
     * 合计金额
     */
    private String hjje; //合计金额

    /**
     * 合计金额大写
     */
    private String hjjedx; //合计金额大写

    /**
     * 地方税务机关
     */
    private String dfswjg; //地方税务机关

    /**
     * 委托单位
     */
    private String wtdzdw; //委托单位

    /**
     * 填票人
     */
    private String tpr; //填票人

    /**
     * 备注
     */
    private String bz; //备注

    /**
     * 来自页面的标志
     */
    private String fromPage; //来自页面的标志

    /**
     * 计算机代码
     */
    private String jsjdm; //计算机代码

    /**
     * 录入人
     */
    private String lrr; //录入人

    /**
     * 征收机关组织机构代码
     */
    private String swjgzzjgdm; //征收机关组织机构代码

    /**
     * 征收机关组织机构名称
     */
    private String swjgzzjgmc; //征收机关组织机构名称

    /**
     * 名细信息，税种名称
     */
    private String mxSz; //名细信息，税种名称

    /**
     * 明细信息，品目名称，其实是税种税目名称
     */
    private String mxPmmc; //名细信息，品目名称，其实是税种税目名称

    /**
     * 明细信息，课税数量
     */
    private String mxKssl; //名细信息，课税数量

    /**
     * 明细信息，缴税金额
     */
    private String mxJsje; //名细信息，缴税金额

    /**
     * 明细信息，税率
     */
    private String mxSl; //名细信息，税率
    
    /**
     * 明细信息，税款所属日期
     */
    private String mxSkssrq; //税款所属开始日期

    /**
     * 明细信息，已缴或扣除
     */
    private String mxYjhkc; //名细信息，已缴或扣除

    /**
     * 明细信息，实缴税额
     */
    private String mxSjse; //名细信息，实缴税额

    /**
     * 填发日期，年
     */
    private String tfrqYear; //填发日期，年

    /**
     * 填发日期，月
     */
    private String tfrqMonth; //填发日期，月

    /**
     * 填发日期，日
     */
    private String tfrqDate; //填发日期，日

    /**
     * 帐户代码
     */
    private String zhdm; //帐户代码

    /**
     * 票证种类代码
     */
    private String pzzldm; //票证种类代码

    /**
     * 完税证号
     */
    private String headWszh; //完税证号

    /**
     * 年度字别
     */
    private String ndzb; //年度字别

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dataList = new ArrayList();
    }

    public String getZclx ()
    {
        return zclx;
    }

    public void setZclx (String zclx)
    {
        this.zclx = zclx;
    }

    public String getTfrq ()
    {
        return tfrq;
    }

    public void setTfrq (String tfrq)
    {
        this.tfrq = tfrq;
    }

    public String getZsjg ()
    {
        return zsjg;
    }

    public void setZsjg (String zsjg)
    {
        this.zsjg = zsjg;
    }

    public String getNsrjsjdm ()
    {
        return nsrjsjdm;
    }

    public void setNsrjsjdm (String nsrjsjdm)
    {
        this.nsrjsjdm = nsrjsjdm;
    }

    public String getDz ()
    {
        return dz;
    }

    public void setDz (String dz)
    {
        this.dz = dz;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
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

    public String getHjje ()
    {
        return hjje;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getHjjedx ()
    {
        return hjjedx;
    }

    public void setHjjedx (String hjjedx)
    {
        this.hjjedx = hjjedx;
    }

    public String getDfswjg ()
    {
        return dfswjg;
    }

    public void setDfswjg (String dfswjg)
    {
        this.dfswjg = dfswjg;
    }

    public String getWtdzdw ()
    {
        return wtdzdw;
    }

    public void setWtdzdw (String wtdzdw)
    {
        this.wtdzdw = wtdzdw;
    }

    public String getTpr ()
    {
        return tpr;
    }

    public void setTpr (String tpr)
    {
        this.tpr = tpr;
    }

    public String getBz ()
    {
        return bz;
    }

    public void setBz (String bz)
    {
        this.bz = bz;
    }

    public String getFromPage ()
    {
        return fromPage;
    }

    public void setFromPage (String fromPage)
    {
        this.fromPage = fromPage;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
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

    public String getMxSz ()
    {
        return mxSz;
    }

    public void setMxSz (String mxSz)
    {
        this.mxSz = mxSz;
    }

    public String getMxPmmc ()
    {
        return mxPmmc;
    }

    public void setMxPmmc (String mxPmmc)
    {
        this.mxPmmc = mxPmmc;
    }

    public String getMxKssl ()
    {
        return mxKssl;
    }

    public void setMxKssl (String mxKssl)
    {
        this.mxKssl = mxKssl;
    }

    public String getMxJsje ()
    {
        return mxJsje;
    }

    public void setMxJsje (String mxJsje)
    {
        this.mxJsje = mxJsje;
    }

    public String getMxSl ()
    {
        return mxSl;
    }

    public void setMxSl (String mxSl)
    {
        this.mxSl = mxSl;
    }

    public String getMxYjhkc ()
    {
        return mxYjhkc;
    }

    public void setMxYjhkc (String mxYjhkc)
    {
        this.mxYjhkc = mxYjhkc;
    }

    public String getMxSjse ()
    {
        return mxSjse;
    }

    public void setMxSjse (String mxSjse)
    {
        this.mxSjse = mxSjse;
    }

    public String getTfrqYear ()
    {
        return tfrqYear;
    }

    public void setTfrqYear (String tfrqYear)
    {
        this.tfrqYear = tfrqYear;
    }

    public String getTfrqMonth ()
    {
        return tfrqMonth;
    }

    public void setTfrqMonth (String tfrqMonth)
    {
        this.tfrqMonth = tfrqMonth;
    }

    public String getTfrqDate ()
    {
        return tfrqDate;
    }

    public void setTfrqDate (String tfrqDate)
    {
        this.tfrqDate = tfrqDate;
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

    public String getHeadWszh ()
    {
        return headWszh;
    }

    public void setHeadWszh (String headWszh)
    {
        this.headWszh = headWszh;
    }

    public String getNdzb ()
    {
        return ndzb;
    }

    public void setNdzb (String ndzb)
    {
        this.ndzb = ndzb;
    }

	public String getMxSkssrq() {
		return mxSkssrq;
	}

	public void setMxSkssrq(String mxSkssrq) {
		this.mxSkssrq = mxSkssrq;
	}


}
