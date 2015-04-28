package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;

/**
 * 币种代码表值对象
 */
public class Bzqkdm implements Serializable {
    public String bzqkdm; //不征情况代码
    public String bzqkmc; //不征情况名称
    public String lrr; //录入人
    public String zxbs; //注销标识
    public String bz; //备注
    public String bzqkms; //不征情况描述
    public String bzzcly; //不征政策理由

    public String getBz() {
        return bz;
    }

    public String getBzqkdm() {
        return bzqkdm;
    }

    public String getBzqkmc() {
        return bzqkmc;
    }

    public String getBzqkms() {
        return bzqkms;
    }

    public String getBzzcly() {
        return bzzcly;
    }

    public String getLrr() {
        return lrr;
    }

    public String getZxbs() {
        return zxbs;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setBzqkdm(String bzqkdm) {
        this.bzqkdm = bzqkdm;
    }

    public void setBzqkmc(String bzqkmc) {
        this.bzqkmc = bzqkmc;
    }

    public void setBzqkms(String bzqkms) {
        this.bzqkms = bzqkms;
    }

    public void setBzzcly(String bzzcly) {
        this.bzzcly = bzzcly;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }
}
