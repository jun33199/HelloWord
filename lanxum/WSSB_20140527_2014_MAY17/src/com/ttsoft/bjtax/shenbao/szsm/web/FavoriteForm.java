package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class FavoriteForm extends BaseForm
{
    public FavoriteForm()
    {
    }

    private String[] checked_szsmdm;

    private List szsm;

    private List previousSzsm;
    
    /**
     * 要提示的税种税目
     */
    //start added by zhangyj 20120913
    private List toBeAlertSzsm;
    //end added by zhangyj 20120913
    
    /**
     * 要wtdz税种税目
     */
    //start added by tujb 201406
    private List wtdzszsm;
    //end added by tujb 201406

    public List getSzsm()
    {
        return szsm;
    }
    public void setSzsm(List szsm)
    {
        this.szsm = szsm;
    }
    public List getPreviousSzsm()
    {
        return previousSzsm;
    }
    public void setPreviousSzsm(List previousSzsm)
    {
        this.previousSzsm = previousSzsm;
    }
	public String[] getChecked_szsmdm() {
		return checked_szsmdm;
	}
	public void setChecked_szsmdm(String[] checked_szsmdm) {
		this.checked_szsmdm = checked_szsmdm;
	}
    public List getToBeAlertSzsm()
    {
        return toBeAlertSzsm;
    }
    public void setToBeAlertSzsm(List toBeAlertSzsm)
    {
        this.toBeAlertSzsm = toBeAlertSzsm;
    }
	public List getWtdzszsm() {
		return wtdzszsm;
	}
	public void setWtdzszsm(List wtdzszsm) {
		this.wtdzszsm = wtdzszsm;
	} 
    
}