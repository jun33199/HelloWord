package com.ttsoft.bjtax.shenbao.gzsx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * 告知事项
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 告知事项Form</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class GzsxForm extends BaseForm
{
    //正常告知的告知内容列表
    private List zcGzList = new ArrayList();

    //非正常告知的内容列表
    private List fzcGzList = new ArrayList();

    //是否有非正常信息,1表示有
    private String hasFzcInfo = "0";

    //计算机代码
    private String jsjdm;
    //2009.4.2wcl修改为了通过标题查看告知详细
    private String gzsxnr;
    //2009.4.2wcl修改为了获得纳税人名称
    private String nsrmc;
    //2009.4.2wcl增加,为了获得纳税人反馈意见
    private String nsrfk;
    //2009.4.2wcl增加,为了获得纳税人阅读时间
    private String ydsj;
    //2009.4.2.wcl增加，为了过得对应的告知事项。
    private String gzsx_id;
    //2009.4.2wcl增加，为了判断是否对同一条告知事项做反馈修改
    private String savetype;
    //2009.4.3wcl增加，为了通过告知标题，查看告知详细
    private String gzsxnrbt;
    //2009.4.3wcl增加。反馈内容修改标示
    private String fknrsavebs;
   //2009.4.3wcl增加。保存提供反馈是的时间
    private Date fksj ;
    public String getGzsxnrbt() {
		return gzsxnrbt;
	}

	public void setGzsxnrbt(String gzsxnrbt) {
		this.gzsxnrbt = gzsxnrbt;
	}

    public GzsxForm()
    {
    }

    public List getZcGzList()
    {
        return zcGzList;
    }

    public void setZcGzList(List zcGzList)
    {
        this.zcGzList = zcGzList;
    }

    public List getFzcGzList()
    {
        return fzcGzList;
    }

    public void setFzcGzList(List fzcGzList)
    {
        this.fzcGzList = fzcGzList;
    }

    public String getHasFzcInfo()
    {
        return hasFzcInfo;
    }
    public void setHasFzcInfo(String hasFzcInfo)
    {
        this.hasFzcInfo = hasFzcInfo;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

	public String getGzsxnr() {
		return gzsxnr;
	}

	public void setGzsxnr(String gzsxnr) {
		this.gzsxnr = gzsxnr;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getNsrfk() {
		return nsrfk;
	}

	public void setNsrfk(String nsrfk) {
		this.nsrfk = nsrfk;
	}

	public String getYdsj() {
		return ydsj;
	}

	public void setYdsj(String ydsj) {
		this.ydsj = ydsj;
	}

	public String getGzsx_id() {
		return gzsx_id;
	}

	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}

	public String getSavetype() {
		return savetype;
	}

	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}

	public String getFknrsavebs() {
		return fknrsavebs;
	}

	public void setFknrsavebs(String fknrsavebs) {
		this.fknrsavebs = fknrsavebs;
	}

	public Date getFksj() {
		return fksj;
	}

	public void setFksj(Date fksj) {
		this.fksj = fksj;
	}
}