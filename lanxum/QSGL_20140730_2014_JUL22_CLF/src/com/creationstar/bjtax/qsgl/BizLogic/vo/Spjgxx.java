package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 正常审批减免税审批结果信息值对象
 */
public class Spjgxx implements Serializable {

    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 减免税金额
     */
    public java.math.BigDecimal jmsje;

    /**
     * 核定通知书字号
     */
    public java.lang.String hdtzszh;

    /**
     * 减免理由代码
     */
    public java.lang.String jmlydm;

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
     * 备注
     */
    public java.lang.String bz;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得减免税金额
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    /**
     * 获得核定通知书字号
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * 获得减免理由代码
     */
    public String getJmlydm() {
        return jmlydm;
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
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值减免税金额
     * @param jmsje 减免税金额
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * 赋值核定通知书字号
     * @param hdtzszh 核定通知书字号
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * 赋值减免理由代码
     * @param jmlydm 减免理由代码
     */
    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
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
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }


}
