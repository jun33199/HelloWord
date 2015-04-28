package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * ˰��˰Ŀform
 *
 * @author Ding Chenggang
 * @version 1.0
 */
public class SzsmForm extends BaseForm
{
    /**
     * ˰��˰Ŀ��Ϣ
     */
    private List szsm;

    /**
     * ����˰��˰Ŀ
     */
    private List favoriteSzsm;
    
    /**
     * �ѹ����ڣ�Ҫ���˵�˰��˰Ŀ
     */
    private List toBeFilterdSzsm;

    /**
     * ���½ɿ���Ϣ
     */
    private List jkInfor;
    
    /**
     * Ҫ��ʾ��˰��˰Ŀ
     */
    //start added by zhangyj 20120905
    private List toBeAlertSzsm;
    //end added by zhangyj 20120905
    
    /**
     * Ҫwtdz˰��˰Ŀ
     */
    //start added by tujb 201406
    private List wtdzszsm;
    //end added by tujb 201406
    
    /**
     * �걨ʹ�ñ�ʶ
     */
    private String sbsybs = "100";
    
    /**
     * ί�е�λ
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