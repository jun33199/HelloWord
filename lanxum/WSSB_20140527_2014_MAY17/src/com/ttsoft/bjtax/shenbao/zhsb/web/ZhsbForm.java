package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.*;

import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.framework.form.*;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * 综合申报
 */
public abstract class ZhsbForm extends BaseForm
{
    public ZhsbForm()
    {
    }
    /**
     * 缴款银行名称
     */
    private String yhmc;


    /**
     * 缴款银行代码
     */
    private String yhdm;



    /**
     * 正常缴款
     */
    private String zcjk[];

    /**
     * 补缴欠税
     */
    private String bjqs[];

    /**
     * 三代解缴
     */
    private String sdjj[];

    /**
     * 税务计算机代码
     */
    private String jsjdm;

    /**
     * 单位名称
     */
    private String dwmc;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 缴款银行帐号
     */
    private String jkyhzh;

    /**
     * 正常缴款税种税目代码
     */
    private String szsmID_zcjk[];

    /**
     * 正常缴款课税数量
     */
    private String kssl_zcjk[];

    /**
     * 正常缴款计税金额
     */
    private String jsje_zcjk[];

    /**
     * 正常缴款实缴税额
     */
    private String sjse_zcjk[];

    /**
     * 补缴欠税税种税目代码
     */
    private String szsmID_bjqs[];

    /**
     * 补缴欠税课税数量
     */
    private String kssl_bjqs[];

    /**
     * 补缴欠税计税金额
     */
    private String jsje_bjqs[];

    /**
     * 补缴欠税实缴税额
     */
    private String sjse_bjqs[];

    /**
     * 四代解缴税种税目代码
     */
    private String szsmID_sdjj[];

    /**
     * 四代解缴课税数量
     */
    private String kssl_sdjj[];

    /**
     * 四代解缴计税金额
     */
    private String jsje_sdjj[];

    /**
     * 四代解缴实缴税额
     */
    private String sjse_sdjj[];

    /**
     * 正常缴款
     */
    private List zcjkList;

    /**
     * 补缴欠税
     */
    private List bjqsList;

    /**
     * 四代解缴
     */
    private List sdjjList;

    /**
     * 本月已申报
     */
    private List byysbList;
    
    /**
     * 税款类型
     */
    private String sklx;

    /**
     * 税款类型代码
     */
    private String sklxdm;
    
    private String sbsybs;
    
    private String sbfs ="0";

	public String getSbfs() {
		return sbfs;
	}

	public void setSbfs(String sbfs) {
		this.sbfs = sbfs;
	}

	public String[] getSzsmdm(String sklx)
    {
        if(sklx.equals(CodeConstant.SKLXDM_ZCJK))
        {
            return szsmID_zcjk;
        }
        else if(sklx.equals(CodeConstant.SKLXDM_BJQS))
        {
            return szsmID_bjqs;
        }
        else if(sklx.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return szsmID_sdjj;
        }

        return null;
    }

    public String[] getKssl(String sklx)
    {
        if (sklx.equals(CodeConstant.SKLXDM_ZCJK))
        {
            return kssl_zcjk;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_BJQS))
        {
            return kssl_bjqs;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return kssl_sdjj;
        }

        return null;
    }

    public String[] getJsje(String sklx)
    {
        if (sklx.equals(CodeConstant.SKLXDM_ZCJK))
        {
            return jsje_zcjk;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_BJQS))
        {
            return jsje_bjqs;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return jsje_sdjj;
        }
        return null;
    }

    public String[] getSjse(String sklx)
    {
        if (sklx.equals(CodeConstant.SKLXDM_ZCJK))
        {
            return sjse_zcjk;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_BJQS))
        {
            return sjse_bjqs;
        }
        else if (sklx.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return sjse_sdjj;
        }
        return null;
    }

    public String getDwmc()
    {
        return dwmc;
    }

    public void setDwmc(String dwmc)
    {
        this.dwmc = dwmc;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public List getSdjjList()
    {
        return sdjjList;
    }

    public void setSdjjList(List sdjjList)
    {
        this.sdjjList = sdjjList;
    }

    public List getZcjkList()
    {
        return zcjkList;
    }

    public void setZcjkList(List zcjkList)
    {
        this.zcjkList = zcjkList;
    }

    public List getBjqsList()
    {
        return bjqsList;
    }

    public void setBjqsList(List bjqsList)
    {
        this.bjqsList = bjqsList;
    }
    public String[] getJsje_bjqs()
    {
        return jsje_bjqs;
    }
    public void setJsje_bjqs(String[] jsje_bjqs)
    {
        this.jsje_bjqs = jsje_bjqs;
    }
    public String[] getJsje_sdjj()
    {
        return jsje_sdjj;
    }
    public void setJsje_sdjj(String[] jsje_sdjj)
    {
        this.jsje_sdjj = jsje_sdjj;
    }
    public String[] getJsje_zcjk()
    {
        return jsje_zcjk;
    }
    public void setJsje_zcjk(String[] jsje_zcjk)
    {
        this.jsje_zcjk = jsje_zcjk;
    }
    public String[] getKssl_bjqs()
    {
        return kssl_bjqs;
    }
    public void setKssl_bjqs(String[] kssl_bjqs)
    {
        this.kssl_bjqs = kssl_bjqs;
    }
    public String[] getKssl_sdjj()
    {
        return kssl_sdjj;
    }
    public void setKssl_sdjj(String[] kssl_sdjj)
    {
        this.kssl_sdjj = kssl_sdjj;
    }
    public String[] getKssl_zcjk()
    {
        return kssl_zcjk;
    }
    public void setKssl_zcjk(String[] kssl_zcjk)
    {
        this.kssl_zcjk = kssl_zcjk;
    }
    public String[] getSjse_bjqs()
    {
        return sjse_bjqs;
    }
    public void setSjse_bjqs(String[] sjse_bjqs)
    {
        this.sjse_bjqs = sjse_bjqs;
    }
    public String[] getSjse_sdjj()
    {
        return sjse_sdjj;
    }
    public void setSjse_sdjj(String[] sjse_sdjj)
    {
        this.sjse_sdjj = sjse_sdjj;
    }
    public String[] getSjse_zcjk()
    {
        return sjse_zcjk;
    }
    public void setSjse_zcjk(String[] sjse_zcjk)
    {
        this.sjse_zcjk = sjse_zcjk;
    }
    public String getSbrq()
    {
        return sbrq;
    }
    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }
    public String[] getBjqs()
    {
        return bjqs;
    }
    public void setBjqs(String[] bjqs)
    {
        this.bjqs = bjqs;
    }
    public String[] getSdjj()
    {
        return sdjj;
    }
    public void setSdjj(String[] sdjj)
    {
        this.sdjj = sdjj;
    }
    public String[] getZcjk()
    {
        return zcjk;
    }
    public void setZcjk(String[] zcjk)
    {
        this.zcjk = zcjk;
    }

    public String getJkyhzh()
    {
        return jkyhzh;
    }

    public void setJkyhzh(String jkyhzh)
    {
        this.jkyhzh = jkyhzh;
    }
    public void setSzsmID_bjqs(String[] szsmID_bjqs)
    {
        this.szsmID_bjqs = szsmID_bjqs;
    }
    public void setSzsmID_sdjj(String[] szsmID_sdjj)
    {
        this.szsmID_sdjj = szsmID_sdjj;
    }
    public void setSzsmID_zcjk(String[] szsmID_zcjk)
    {
        this.szsmID_zcjk = szsmID_zcjk;
    }
    public String[] getSzsmID_bjqs()
    {
        return szsmID_bjqs;
    }
    public String[] getSzsmID_sdjj()
    {
        return szsmID_sdjj;
    }
    public String[] getSzsmID_zcjk()
    {
        return szsmID_zcjk;
    }
	/**
	 * @return Returns the yhmc.
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmc The yhmc to set.
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYhdm() {
		return yhdm;
	}

    public List getByysbList() {
        return byysbList;
    }

    public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

    public void setByysbList(List byysbList) {
        this.byysbList = byysbList;
    }

	public String getSklx() {
		return sklx;
	}

	public void setSklx(String sklx) {
		this.sklx = sklx;
	}

	public String getSklxdm() {
		return sklxdm;
	}

	public void setSklxdm(String sklxdm) {
		this.sklxdm = sklxdm;
	}
	
	public String getSbsybs() {
		return sbsybs;
	}

	public void setSbsybs(String sbsybs) {
		this.sbsybs = sbsybs;
	}
}
