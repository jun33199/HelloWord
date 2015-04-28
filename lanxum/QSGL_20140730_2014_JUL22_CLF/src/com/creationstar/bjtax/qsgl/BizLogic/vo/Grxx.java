package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 个人信息值对象
 */
public class Grxx implements Serializable {
    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 纳税人名称
     */
    public java.lang.String nsrmc;

    /**
     * 证件类型
     */
    public java.lang.String sfzjlx;

    /**
     * 身份证件名称
     */
    public java.lang.String sfzjlxmc;

    /**
     * 证件号码
     */
    public java.lang.String sfzjhm;

    /**
     * 国籍代码
     */
    public String gjdm;

    /**
     * 国籍名称
     */
    public String gjmc;

    /**
     * 联系电话
     */
    public java.lang.String lxdh;

    /**
     * 房屋交换标识
     */
    public java.lang.String fwjhbs;

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
     * 产权人类型
     */
    public java.lang.String cqrlx;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得纳税人名称
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * 获得证件类型
     */
    public String getSfzjlx() {
        return sfzjlx;
    }

    /**
     * 获得null
     */
    public String getSfzjlxmc() {
        return sfzjlxmc;
    }

    /**
     * 获得证件号码
     */
    public String getSfzjhm() {
        return sfzjhm;
    }

    /**
     * 获得联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得房屋交换标识
     */
    public String getFwjhbs() {
        return fwjhbs;
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

    public String getGjdm() {
        return gjdm;
    }

    public String getGjmc() {
        return gjmc;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值纳税人名称
     * @param nsrmc 纳税人名称
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * 赋值证件类型
     * @param sfzjlx 证件类型
     */
    public void setSfzjlx(String sfzjlx) {
        this.sfzjlx = sfzjlx;
    }

    /**
     * 赋值null
     * @param sfzjlxmc null
     */
    public void setSfzjlxmc(String sfzjlxmc) {
        this.sfzjlxmc = sfzjlxmc;
    }

    /**
     * 赋值证件号码
     * @param sfzjhm 证件号码
     */
    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    /**
     * 赋值联系电话
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 赋值房屋交换标识
     * @param fwjhbs 房屋交换标识
     */
    public void setFwjhbs(String fwjhbs) {
        this.fwjhbs = fwjhbs;
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

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    public void setGjmc(String gjmc) {
        this.gjmc = gjmc;
    }


    /**
     * @return Returns the cqrlx.
     */
    public java.lang.String getCqrlx() {
        return cqrlx;
    }

    /**
     * @param cqrlx The cqrlx to set.
     */
    public void setCqrlx(java.lang.String cqrlx) {
        this.cqrlx = cqrlx;
    }
}
