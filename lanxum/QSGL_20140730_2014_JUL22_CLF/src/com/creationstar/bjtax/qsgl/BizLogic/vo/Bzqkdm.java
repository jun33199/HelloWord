package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;

/**
 * ���ִ����ֵ����
 */
public class Bzqkdm implements Serializable {
    public String bzqkdm; //�����������
    public String bzqkmc; //�����������
    public String lrr; //¼����
    public String zxbs; //ע����ʶ
    public String bz; //��ע
    public String bzqkms; //�����������
    public String bzzcly; //������������

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
