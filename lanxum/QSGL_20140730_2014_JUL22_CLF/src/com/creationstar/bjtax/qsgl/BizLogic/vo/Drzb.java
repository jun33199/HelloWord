package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *������Ϣ��������
 */
public class Drzb implements Serializable {
    //�������κ�
    public String drpch;
    //���
    public BigDecimal xh;
    //��˰������
    public String nsrmc;
    //��˰�˼��������
    public String nsrjsjdm;
    //���ز���Ŀ����
    public String fdcxmmc;
    //���ز���ַ
    public String fdcdz;
    //��ͬǩ������
    public Timestamp htqdrq;
    //�ɽ��۸�
    public BigDecimal cjjg;
    //������������
    public String drsjnr;
    //״̬��ʶ
    public String ztbs;
    //���������
    public String drczr;
    //����ʱ��
    public Timestamp drsj;
    //��ע
    public String bz;
    //�걨���
    public String sbbh;
    //֤������
    public String zjlx;
    //֤������
    public String zjhm;

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZjlx() {
        return zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public String getBz() {
        return bz;
    }

    public BigDecimal getCjjg() {
        return cjjg;
    }

    public String getDrczr() {
        return drczr;
    }

    public String getDrpch() {
        return drpch;
    }

    public Timestamp getDrsj() {
        return drsj;
    }

    public String getDrsjnr() {
        return drsjnr;
    }

    public String getFdcdz() {
        return fdcdz;
    }

    public Timestamp getHtqdrq() {
        return htqdrq;
    }

    public String getNsrjsjdm() {
        return nsrjsjdm;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public BigDecimal getXh() {
        return xh;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    public void setDrczr(String drczr) {
        this.drczr = drczr;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setDrsj(Timestamp drsj) {
        this.drsj = drsj;
    }

    public void setDrsjnr(String drsjnr) {
        this.drsjnr = drsjnr;
    }

    public void setFdcdz(String fdcdz) {
        this.fdcdz = fdcdz;
    }

    public void setHtqdrq(Timestamp htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setNsrjsjdm(String nsrjsjdm) {
        this.nsrjsjdm = nsrjsjdm;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }
}
