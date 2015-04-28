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
 * <p>Description: 打印缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class JksPrintForm
    extends BaseForm
{
	//保存按钮是不是显示（true:显示;false:不显示）
	private boolean saveBtnVisible=true ; 
	
	
	//限缴日期是否可编辑（true:可编辑;false:不可编辑）
	private boolean xjrqEdit=true ; 

	
	//是否取备注（true:显示;false:不显示）
	private boolean bzVisible=false ; 
	
    /**
     * 页面数据集
     */
    private List dataList = new ArrayList();

    /**
     * 录入人
     */
    private String lrr; //录入人

    /**
     * 缴款凭证号
     */
    private String headJkpzh; //缴款凭证号

    /**
     * 计算机代码
     */
    private String headJsjdm; //计算机代码

    /**
     * 隶属关系
     */
    private String headLsgx; //隶属关系

    /**
     * 征收机关名称
     */
    private String headZsjgmc; //征收机关名称

    /**
     * 征收类型名称
     */
    private String headZclxmc; //征收类型名称

    /**
     * 填发日期
     */
    private String headTfrq; //填发日期

    /**
     * 代码
     */
    private String dm; //缴款单位信息，代码

    /**
     * 电话
     */
    private String dh; //缴款单位信息，电话

    /**
     * 全称
     */
    private String qc; //缴款单位信息，全称

    /**
     * 账号
     */
    private String zh; //缴款单位信息，账号

    /**
     * 税款所属开始日期
     */
    private String skssksrq; //税款所属开始日期

    /**
     * 税款所属结束日期
     */
    private String skssjsrq; //税款所属结束日期

    /**
     * 预算科目代码
     */
    private String yskmdm; //预算科目代码

    /**
     * 算科目名称
     */
    private String yskmmc; //算科目名称

    /**
     * 预算级次名称
     */
    private String yskmjc; //预算级次名称

    /**
     * 税款国库
     */
    private String skgk; //税款国库

    /**
     * 税款限缴日期
     */
    private String skxjrq; //税款限缴日期

    /**
     * 合计金额
     */
    private String hjje; //合计金额

    /**
     * 缴款单位
     */
    private String jkdw; //缴款单位，==qc

    /**
     * 地方税务机关
     */
    private String dfswjg; //地方税务机关 ==headZsjgmc

    /**
     * 填票人
     */
    private String tpr; //填票人

    /**
     * 编码
     */
    private String bm; //编码

    /**
     * 合计金额大写
     */
    private String hjjedx; //合计金额大写

    /**
     * 备注
     */
    private String bz; //备注

    /**
     * 开户银行
     */
    private String khyh; //开户银行

    /**
     * 税种税目代码
     */
    private String szsmdm; //税种税目代码

    /**
     * 申报，数据来源
     */
    private String headSjly; //申报，数据来源

    /**
     * 税种名称
     */
    private String szdm; //税种名称

    /**
     * 明细品目名称
     */
    private String mxPmmc; //明细品目名称

    /**
     * 明细课税数量
     */
    private String mxKssl; //明细课税数量

    /**
     * 明细缴税金额
     */
    private String mxJsje; //明细缴税金额

    /**
     * 明细，税率
     */
    private String mxSl; //明细，税率

    /**
     * 明细实缴税额
     */
    private String mxSjse; //明细实缴税额

    /**
     * 明细分成比例
     */
    private String mxFcbl; //明细分成比例
    
    /**
     * 税款类型
     */
    private String sklx;

    /**
     * 可编辑的税款所属开始日期
     */
    private String editSkssksrq;

    /**
     * 可编辑的税款所属结束日期
     */
    private String editSkssjsrq;

    /**
     * 税款类型
     */
    private String editSkxjrq; //税款类型
    
    /**
     * 填发日期年
     */
    private String headTfrqn; //填发日期年
    
    /**
     * 填发日期月
     */
    private String headTfrqy; //填发日期月
    
    /**
     * 填发日期日
     */
    private String headTfrqr; //填发日期日

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

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

    public String getHeadLsgx ()
    {
        return headLsgx;
    }

    public void setHeadLsgx (String headLsgx)
    {
        this.headLsgx = headLsgx;
    }

    public String getHeadZsjgmc ()
    {
        return headZsjgmc;
    }

    public void setHeadZsjgmc (String headZsjgmc)
    {
        this.headZsjgmc = headZsjgmc;
    }

    public String getHeadZclxmc ()
    {
        return headZclxmc;
    }

    public void setHeadZclxmc (String headZclxmc)
    {
        this.headZclxmc = headZclxmc;
    }

    public String getHeadTfrq ()
    {
        return headTfrq;
    }

    public void setHeadTfrq (String headTfrq)
    {
        this.headTfrq = headTfrq;
    }

    public String getDm ()
    {
        return dm;
    }

    public void setDm (String dm)
    {
        this.dm = dm;
    }

    public String getDh ()
    {
        return dh;
    }

    public void setDh (String dh)
    {
        this.dh = dh;
    }

    public String getQc ()
    {
        return qc;
    }

    public void setQc (String qc)
    {
        this.qc = qc;
    }

    public String getZh ()
    {
        return zh;
    }

    public void setZh (String zh)
    {
        this.zh = zh;
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

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public String getYskmjc ()
    {
        return yskmjc;
    }

    public void setYskmjc (String yskmjc)
    {
        this.yskmjc = yskmjc;
    }

    public String getSkgk ()
    {
        return skgk;
    }

    public void setSkgk (String skgk)
    {
        this.skgk = skgk;
    }

    public String getSkxjrq ()
    {
        return skxjrq;
    }

    public void setSkxjrq (String skxjrq)
    {
        this.skxjrq = skxjrq;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getJkdw ()
    {
        return jkdw;
    }

    public void setJkdw (String jkdw)
    {
        this.jkdw = jkdw;
    }

    public String getDfswjg ()
    {
        return dfswjg;
    }

    public void setDfswjg (String dfswjg)
    {
        this.dfswjg = dfswjg;
    }

    public String getTpr ()
    {
        return tpr;
    }

    public void setTpr (String tpr)
    {
        this.tpr = tpr;
    }

    public String getBm ()
    {
        return bm;
    }

    public void setBm (String bm)
    {
        this.bm = bm;
    }

    public String getHjjedx ()
    {
        return hjjedx;
    }

    public void setHjjedx (String hjjedx)
    {
        this.hjjedx = hjjedx;
    }

    public String getBz ()
    {
        return bz;
    }

    public void setBz (String bz)
    {
        this.bz = bz;
    }

    public String getKhyh ()
    {
        return khyh;
    }

    public void setKhyh (String khyh)
    {
        this.khyh = khyh;
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getHeadSjly ()
    {
        return headSjly;
    }

    public void setHeadSjly (String headSjly)
    {
        this.headSjly = headSjly;
    }

    public String getSzdm ()
    {
        return szdm;
    }

    public void setSzdm (String szdm)
    {
        this.szdm = szdm;
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

    public String getMxSjse ()
    {
        return mxSjse;
    }

    public void setMxSjse (String mxSjse)
    {
        this.mxSjse = mxSjse;
    }

    public String getMxSl ()
    {
        return mxSl;
    }

    public void setMxSl (String mxSl)
    {
        this.mxSl = mxSl;
    }

    public String getMxFcbl ()
    {
        return mxFcbl;
    }

    public void setMxFcbl (String mxFcbl)
    {
        this.mxFcbl = mxFcbl;
    }
    
    public String getSklx ()
    {
        return sklx;
    }

    public void setSklx (String sklx)
    {
        this.sklx = sklx;
    }

    public String getEditSkssksrq ()
    {
        return editSkssksrq;
    }

    public void setEditSkssksrq (String editSkssksrq)
    {
        this.editSkssksrq = editSkssksrq;
    }

    public String getEditSkssjsrq ()
    {
        return editSkssjsrq;
    }

    public void setEditSkssjsrq (String editSkssjsrq)
    {
        this.editSkssjsrq = editSkssjsrq;
    }

    public String getEditSkxjrq ()
    {
        return editSkxjrq;
    }

    public void setEditSkxjrq (String editSkxjrq)
    {
        this.editSkxjrq = editSkxjrq;
    }
    
    public String getHeadTfrqn ()
    {
        return headTfrqn;
    }

    public void setHeadTfrqn (String headTfrqn)
    {
        this.headTfrqn = headTfrqn;
    }
    public String getHeadTfrqy ()
    {
        return headTfrqy;
    }

    public void setHeadTfrqy (String headTfrqy)
    {
        this.headTfrqy = headTfrqy;
    }

    public String getHeadTfrqr ()
    {
        return headTfrqr;
    }

    public void setHeadTfrqr (String headTfrqr)
    {
        this.headTfrqr = headTfrqr;
    }

	public boolean isSaveBtnVisible() {
		return saveBtnVisible;
	}

	public void setSaveBtnVisible(boolean saveBtnVisible) {
		this.saveBtnVisible = saveBtnVisible;
	}

	public boolean isXjrqEdit() {
		return xjrqEdit;
	}

	public void setXjrqEdit(boolean xjrqEdit) {
		this.xjrqEdit = xjrqEdit;
	}

	public boolean isBzVisible() {
		return bzVisible;
	}

	public void setBzVisible(boolean bzVisible) {
		this.bzVisible = bzVisible;
	}


    
}
