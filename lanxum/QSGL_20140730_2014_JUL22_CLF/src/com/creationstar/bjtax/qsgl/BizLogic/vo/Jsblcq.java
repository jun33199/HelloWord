package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 即时办理基础信息-拆迁值对象
 */
public class Jsblcq implements Serializable {
    /**
     * 相关申报主表表号
     */
    public String sbbh;

    /**
     * 拆迁协议号码
     */
    public java.lang.String cqxyh;

    /**
     * 被拆迁房屋坐落地址
     */
    public java.lang.String zldz;

    /**
     * 拆迁补偿额
     */
    public java.math.BigDecimal cqbce;

    /**
     * 本次使用补偿额
     */
    public java.math.BigDecimal bcsybce;

    /**
     * 拆迁补偿剩余额
     */
    public java.math.BigDecimal cqbcsye;

    /**
     * 本次可使用补偿额
     */
    public java.math.BigDecimal bcksybce;

    /**
     * 用户标识
     */
    public java.lang.String yhbs;

    /**
     * 状态标识
     */
    public java.lang.String ztbs;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 文书分局简称
     */
    public java.lang.String wsjc;

    /**
     * 征收点简称
     */
    public java.lang.String zsdjc;

    /**
     * 序号
     */
    public java.lang.String xh;

    /**
     * 剩余额用完标志
     */
    public String syeywbz;

    /**
     * 获得拆迁协议号码
     */
    public String getCqxyh() {
        return cqxyh;
    }

    /**
     * 获得被拆迁房屋坐落地址
     */
    public String getZldz() {
        return zldz;
    }

    /**
     * 获得拆迁补偿额
     */
    public BigDecimal getCqbce() {
        return cqbce;
    }

    /**
     * 获得本次使用补偿额
     */
    public BigDecimal getBcsybce() {
        return bcsybce;
    }

    /**
     * 获得拆迁补偿剩余额
     */
    public BigDecimal getCqbcsye() {
        return cqbcsye;
    }

    /**
     * 获得本次可使用补偿额
     */
    public BigDecimal getBcksybce() {
        return bcksybce;
    }

    /**
     * 获得用户标识
     */
    public String getYhbs() {
        return yhbs;
    }

    /**
     * 获得状态标识
     */
    public String getZtbs() {
        return ztbs;
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
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getWsjc() {
        return wsjc;
    }

    public String getXh() {
        return xh;
    }

    public String getZsdjc() {
        return zsdjc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    /**
     * 赋值拆迁协议号码
     * @param cqxyh 拆迁协议号码
     */
    public void setCqxyh(String cqxyh) {
        this.cqxyh = cqxyh;
    }

    /**
     * 赋值被拆迁房屋坐落地址
     * @param zldz 被拆迁房屋坐落地址
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    /**
     * 赋值拆迁补偿额
     * @param cqbce 拆迁补偿额
     */
    public void setCqbce(BigDecimal cqbce) {
        this.cqbce = cqbce;
    }

    /**
     * 赋值本次使用补偿额
     * @param bcsybce 本次使用补偿额
     */
    public void setBcsybce(BigDecimal bcsybce) {
        this.bcsybce = bcsybce;
    }

    /**
     * 赋值拆迁补偿剩余额
     * @param cqbcsye 拆迁补偿剩余额
     */
    public void setCqbcsye(BigDecimal cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    /**
     * 赋值本次可使用补偿额
     * @param bcksybce 本次可使用补偿额
     */
    public void setBcksybce(BigDecimal bcksybce) {
        this.bcksybce = bcksybce;
    }

    /**
     * 赋值用户标识
     * @param yhbs 用户标识
     */
    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    /**
     * 赋值状态标识
     * @param ztbs 状态标识
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
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
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setWsjc(String wsjc) {
        this.wsjc = wsjc;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setZsdjc(String zsdjc) {
        this.zsdjc = zsdjc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }


}
