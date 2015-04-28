package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 非个人信息值对象
 */
public class Fgrxx implements Serializable {

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
     * 开户银行代码
     */
    public java.lang.String khyhdm;

    /**
     * 开户银行名称
     */
    public java.lang.String khyhmc;

    /**
     * 银行账号
     */
    public java.lang.String yhzh;

    /**
     * 联系人姓名
     */
    public java.lang.String lxrxm;

    /**
     * 联系电话
     */
    public java.lang.String lxdh;

    /**
     * 纳税人类型代码
     */
    public java.lang.String nsrlxdm;

    /**
     * 纳税人类型名称
     */
    public java.lang.String nsrlxmc;

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
    public String cqrlx;


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
     * 获得开户银行代码
     */
    public String getKhyhdm() {
        return khyhdm;
    }

    /**
     * 获得开户银行名称
     */
    public String getKhyhmc() {
        return khyhmc;
    }

    /**
     * 获得银行账号
     */
    public String getYhzh() {
        return yhzh;
    }

    /**
     * 获得联系人姓名
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * 获得联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得纳税人类型代码
     */
    public String getNsrlxdm() {
        return nsrlxdm;
    }

    /**
     * 获得纳税人类型名称
     */
    public String getNsrlxmc() {
        return nsrlxmc;
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

    public String getCqrlx() {
        return cqrlx;
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
     * 赋值开户银行代码
     * @param khyhdm 开户银行代码
     */
    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    /**
     * 赋值开户银行名称
     * @param khyhmc 开户银行名称
     */
    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    /**
     * 赋值银行账号
     * @param yhzh 银行账号
     */
    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    /**
     * 赋值联系人姓名
     * @param lxrxm 联系人姓名
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * 赋值联系电话
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 赋值纳税人类型代码
     * @param nsrlxdm 纳税人类型代码
     */
    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    /**
     * 赋值纳税人类型名称
     * @param nsrlxmc 纳税人类型名称
     */
    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
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

    public void setCqrlx(String cqrlx) {
        this.cqrlx = cqrlx;
    }


}
