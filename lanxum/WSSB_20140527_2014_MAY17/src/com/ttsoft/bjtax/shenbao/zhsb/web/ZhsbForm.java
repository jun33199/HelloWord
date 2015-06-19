package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.*;

import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.framework.form.*;

/**
 * @author Ding Chenggang
 * @version 1.0
 *
 * �ۺ��걨
 */
public abstract class ZhsbForm extends BaseForm
{
    public ZhsbForm()
    {
    }
    /**
     * �ɿ���������
     */
    private String yhmc;


    /**
     * �ɿ����д���
     */
    private String yhdm;



    /**
     * �����ɿ�
     */
    private String zcjk[];

    /**
     * ����Ƿ˰
     */
    private String bjqs[];

    /**
     * �������
     */
    private String sdjj[];

    /**
     * ˰����������
     */
    private String jsjdm;

    /**
     * ��λ����
     */
    private String dwmc;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * �ɿ������ʺ�
     */
    private String jkyhzh;

    /**
     * �����ɿ�˰��˰Ŀ����
     */
    private String szsmID_zcjk[];

    /**
     * �����ɿ��˰����
     */
    private String kssl_zcjk[];

    /**
     * �����ɿ��˰���
     */
    private String jsje_zcjk[];

    /**
     * �����ɿ�ʵ��˰��
     */
    private String sjse_zcjk[];

    /**
     * ����Ƿ˰˰��˰Ŀ����
     */
    private String szsmID_bjqs[];

    /**
     * ����Ƿ˰��˰����
     */
    private String kssl_bjqs[];

    /**
     * ����Ƿ˰��˰���
     */
    private String jsje_bjqs[];

    /**
     * ����Ƿ˰ʵ��˰��
     */
    private String sjse_bjqs[];

    /**
     * �Ĵ����˰��˰Ŀ����
     */
    private String szsmID_sdjj[];

    /**
     * �Ĵ���ɿ�˰����
     */
    private String kssl_sdjj[];

    /**
     * �Ĵ���ɼ�˰���
     */
    private String jsje_sdjj[];

    /**
     * �Ĵ����ʵ��˰��
     */
    private String sjse_sdjj[];

    /**
     * �����ɿ�
     */
    private List zcjkList;

    /**
     * ����Ƿ˰
     */
    private List bjqsList;

    /**
     * �Ĵ����
     */
    private List sdjjList;

    /**
     * �������걨
     */
    private List byysbList;
    
    /**
     * ˰������
     */
    private String sklx;

    /**
     * ˰�����ʹ���
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
