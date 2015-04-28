package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 契税减免申报表值对象
 */
public class Jmsbb implements Serializable {

    /**
     * 减免申报表号
     */
//    public java.lang.String jmsbbh;

    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 减免政策代码
     */
    public java.lang.String jmzcdm;

    /**
     * 减免类型代码
     */
    public java.lang.String jmlxdm;

    /**
     * 打印标识
     */
    public java.lang.String dybs;

    /**
     * 状态标识
     */
    public java.lang.String ztbs;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 年度
     */
    public java.lang.String nd;

//    /**
//     * 获得减免申报表号
//     */
//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    /**
     * 审批机构－机关
     */
    public java.lang.String spjg;

    /**
     * 审批日期
     */
    public java.sql.Timestamp sprq;
    
    /**
     * 契税减免性质
     */
    public String jmxzdm;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得减免政策代码
     */
    public String getJmzcdm() {
        return jmzcdm;
    }

    /**
     * 获得打印标识
     */
    public String getDybs() {
        return dybs;
    }

    /**
     * 获得状态标识
     */
    public String getZtbs() {
        return ztbs;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
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
     * 获得创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 获得创建日期
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
//     * 赋值减免申报表号
//     * @param jmsbbh 减免申报表号
//     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值减免政策代码
     * @param jmzcdm 减免政策代码
     */
    public void setJmzcdm(String jmzcdm) {
        this.jmzcdm = jmzcdm;
    }

    /**
     * 赋值打印标识
     * @param dybs 打印标识
     */
    public void setDybs(String dybs) {
        this.dybs = dybs;
    }

    /**
     * 赋值状态标识
     * @param ztbs 状态标识
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
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
     * 赋值创建人
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * 赋值创建日期
     * @param cjrq 创建日期
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * @return jmlxdm
     */
    public java.lang.String getJmlxdm() {
        return jmlxdm;
    }

    /**
     * @param jmlxdm 要设置的 jmlxdm
     */
    public void setJmlxdm(java.lang.String jmlxdm) {
        this.jmlxdm = jmlxdm;
    }

    public java.lang.String getSpjg() {
        return spjg;
    }

    public void setSpjg(java.lang.String spjg) {
        this.spjg = spjg;
    }

    public java.sql.Timestamp getSprq() {
        return sprq;
    }

    public void setSprq(java.sql.Timestamp sprq) {
        this.sprq = sprq;
    }

	public String getJmxzdm() {
		return jmxzdm;
	}

	public void setJmxzdm(String jmxzdm) {
		this.jmxzdm = jmxzdm;
	}


}
