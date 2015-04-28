package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * 税种税目form
 *
 * @author Ding Chenggang
 * @version 1.0
 */
public class SzsmForm extends BaseForm
{
    /**
     * 税种税目信息
     */
    private List szsm;

    /**
     * 常用税种税目
     */
    private List favoriteSzsm;
    
    /**
     * 已过征期，要过滤的税种税目
     */
    private List toBeFilterdSzsm;

    /**
     * 本月缴款信息
     */
    private List jkInfor;
    
    /**
     * 要提示的税种税目
     */
    //start added by zhangyj 20120905
    private List toBeAlertSzsm;
    //end added by zhangyj 20120905
    
    /**
     * 要wtdz税种税目
     */
    //start added by tujb 201406
    private List wtdzszsm;
    //end added by tujb 201406
    
    /**
     * 申报使用标识
     */
    private String sbsybs = "100";
    
    /**
     * 委托单位
     */
    private List wtdwInfor;


	public SzsmForm()
    {
    }

    public List getToBeFilterdSzsm()
    {
        return toBeFilterdSzsm;
    }

    public void setToBeFilterdSzsm(List toBeFilterdSzsm)
    {
        this.toBeFilterdSzsm = toBeFilterdSzsm;
    }
    
    public List getSzsm()
    {
        return szsm;
    }

    public void setSzsm(List szsm)
    {
        this.szsm = szsm;
    }
    public List getFavoriteSzsm()
    {
        return favoriteSzsm;
    }
    public void setFavoriteSzsm(List favoriteSzsm)
    {
        this.favoriteSzsm = favoriteSzsm;
    }
    public List getJkInfor()
    {
        return jkInfor;
    }
    public void setJkInfor(List jkInfor)
    {
        this.jkInfor = jkInfor;
    }
    public List getToBeAlertSzsm()
    {
        return toBeAlertSzsm;
    }

    public void setToBeAlertSzsm(List toBeAlertSzsm)
    {
        this.toBeAlertSzsm = toBeAlertSzsm;
    }
    
    public List getWtdwInfor() {
		return wtdwInfor;
	}

	public void setWtdwInfor(List wtdwInfor) {
		this.wtdwInfor = wtdwInfor;
	}

	public String getSbsybs() {
		return sbsybs;
	}

	public void setSbsybs(String sbsybs) {
		this.sbsybs = sbsybs;
	}

	public List getWtdzszsm() {
		return wtdzszsm;
	}

	public void setWtdzszsm(List wtdzszsm) {
		this.wtdzszsm = wtdzszsm;
	}
}