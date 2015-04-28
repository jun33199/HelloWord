package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx22Form extends QysdsJmsbajlMainForm {
	/**
     * 
     */
    private static final long serialVersionUID = -4161613909822877797L;

    /*
     * 序号
     */
    private String xh;

    /*
     * 节能减排技术改造项目类型
     */
    private String jnjpjsgzxmlx;

    /*
     * 节能减排技术改造项目类型名称
     */
    private String jnjpjsgzxmmc;

    /*
     * 节能减排技术改造项目类型代码
     */
    private String jnjpjsgzxmdm;
    /*
     * 节能减排技术改造项目类型代码list
     */
    private List jnjpjsgzxmList;    


    /*
     * 取得第一笔收入的相关证明资料名称
     */
    private String dybzlmc;

    /*
     * 取得第一笔生产经营收入的时间
     */
    private String dybrq;

    /*
     * 免征起始年度
     */
    private String mzqsnd;

    /*
     * 免征终止年度
     */
    private String mzzznd;

    /*
     * 减半征收起始年度
     */
    private String jbzsqsnd;

    /*
     * 减半征终止年度
     */
    private String jbzszznd;
    
    /**
     * 是否发生合同能源管理项目转让、受让
     */
    private String ZRHTXM="";
    /**
     * 项目转让合同、项目原享受优惠备案文件
     */
    private String ZRHTXMYHWJ="";
    /**
     * 项目转让合同、项目原享受优惠
     */
    private String ZRHTXMYH="";
    /*
     * 
     */
    private String ckbox;

    /*
     * 处理标示
     */
    private String clbs = "0";

    /*
     * 多条数据列表
     */
    private List resultList;

    /*
     * 往年结果集
     */
    private List resultList_wn;

    /*
     * 
     */
    private String checkedStr;

    /*
     * 备案年度代码
     */
    private String banddm;

    
    
    public String getZRHTXM() {
		return ZRHTXM;
	}

	public void setZRHTXM(String zRHTXM) {
		ZRHTXM = zRHTXM;
	}

	public String getZRHTXMYHWJ() {
		return ZRHTXMYHWJ;
	}

	public void setZRHTXMYHWJ(String zRHTXMYHWJ) {
		ZRHTXMYHWJ = zRHTXMYHWJ;
	}

	public String getZRHTXMYH() {
		return ZRHTXMYH;
	}

	public void setZRHTXMYH(String zRHTXMYH) {
		ZRHTXMYH = zRHTXMYH;
	}

	public String getBanddm() {
        return banddm;
    }

    public void setBanddm(String banddm) {
        this.banddm = banddm;
    }

    public String getCheckedStr() {
        return checkedStr;
    }

    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    public String getCkbox() {
        return ckbox;
    }

    public void setCkbox(String ckbox) {
        this.ckbox = ckbox;
    }

    public String getClbs() {
        return clbs;
    }

    public void setClbs(String clbs) {
        this.clbs = clbs;
    }

    public String getDybrq() {
        return dybrq;
    }

    public void setDybrq(String dybrq) {
        this.dybrq = dybrq;
    }

    public String getDybzlmc() {
        return dybzlmc;
    }

    public void setDybzlmc(String dybzlmc) {
        this.dybzlmc = dybzlmc;
    }
    public String getJnjpjsgzxmlx() {
		return jnjpjsgzxmlx;
	}

	public void setJnjpjsgzxmlx(String jnjpjsgzxmlx) {
		this.jnjpjsgzxmlx = jnjpjsgzxmlx;
	}

	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}

	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}

	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}

	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}

	public List getJnjpjsgzxmList() {
		return jnjpjsgzxmList;
	}

	public void setJnjpjsgzxmList(List jnjpjsgzxmList) {
		this.jnjpjsgzxmList = jnjpjsgzxmList;
	}

    public String getJbzsqsnd() {
		return jbzsqsnd;
	}

	public void setJbzsqsnd(String jbzsqsnd) {
		this.jbzsqsnd = jbzsqsnd;
	}

	public String getJbzszznd() {
		return jbzszznd;
	}

	public void setJbzszznd(String jbzszznd) {
		this.jbzszznd = jbzszznd;
	}

	public String getMzqsnd() {
        return mzqsnd;
    }

    public void setMzqsnd(String mzqsnd) {
        this.mzqsnd = mzqsnd;
    }

    public String getMzzznd() {
        return mzzznd;
    }

    public void setMzzznd(String mzzznd) {
        this.mzzznd = mzzznd;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public List getResultList_wn() {
        return resultList_wn;
    }

    public void setResultList_wn(List resultList_wn) {
        this.resultList_wn = resultList_wn;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }	

	
	


}