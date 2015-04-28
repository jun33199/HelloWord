package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 契税减免类别代码表即"契税减免原因代码表值对象
 */
public class Qsjmlb implements Serializable {

    /**
     * 契税申请减免类别代码
     */
    public java.lang.String qsjmlbdm;

    /**
     * 契税申请减免类别名称
     */
    public java.lang.String qsjmlbmc;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;


    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 契税申请减免类别政策依据
     */
    public java.lang.String qsjmlbzcyj;

    /**
     * 契税申请减免类别描述
     */
    public java.lang.String qsjmlbms;
    
    /**
     * 减免性质代码
     */
    public java.lang.String jmxzdm;


    /**
     * 获得契税申请减免类别代码
     */
    public String getQsjmlbdm() {
        return qsjmlbdm;
    }

    /**
     * 获得契税申请减免类别名称
     */
    public String getQsjmlbmc() {
        return qsjmlbmc;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得null
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 赋值契税申请减免类别代码
     * @param qsjmlbdm 契税申请减免类别代码
     */
    public void setQsjmlbdm(String qsjmlbdm) {
        this.qsjmlbdm = qsjmlbdm;
    }

    /**
     * 赋值契税申请减免类别名称
     * @param qsjmlbmc 契税申请减免类别名称
     */
    public void setQsjmlbmc(String qsjmlbmc) {
        this.qsjmlbmc = qsjmlbmc;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值null
     * @param zxbs null
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * @return bz
     */
    public java.lang.String getBz() {
        return bz;
    }

    /**
     * @param bz 要设置的 bz
     */
    public void setBz(java.lang.String bz) {
        this.bz = bz;
    }

    /**
     * @return qsjmlbms
     */
    public java.lang.String getQsjmlbms() {
        return qsjmlbms;
    }

    /**
     * @param qsjmlbms 要设置的 qsjmlbms
     */
    public void setQsjmlbms(java.lang.String qsjmlbms) {
        this.qsjmlbms = qsjmlbms;
    }

    /**
     * @return qsjmlbzcyj
     */
    public java.lang.String getQsjmlbzcyj() {
        return qsjmlbzcyj;
    }

    /**
     * @param qsjmlbzcyj 要设置的 qsjmlbzcyj
     */
    public void setQsjmlbzcyj(java.lang.String qsjmlbzcyj) {
        this.qsjmlbzcyj = qsjmlbzcyj;
    }

	public java.lang.String getJmxzdm() {
		return jmxzdm;
	}

	public void setJmxzdm(java.lang.String jmxzdm) {
		this.jmxzdm = jmxzdm;
	}


}
