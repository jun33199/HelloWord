package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ���ʴ����ֵ����
 */
public class Wbhs implements Serializable {

    /**
     * ����
     */
    public java.lang.String bzdm;

    /**
     * ���
     */
    public java.math.BigDecimal xh;

    /**
     * ���
     */
    public java.math.BigDecimal je;

    /**
     * ����Ƽ�
     */
    public java.math.BigDecimal whpj;

    /**
     * �ۺ������
     */
    public java.math.BigDecimal zhrmb;

    /**
     * ��������
     */
    public java.lang.String bzmc;

    /**
     * ���
     */
    public java.lang.String nd;

    /**
     * �·�
     */
    public java.lang.String yf;

    /**
     * ��ñ���
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * ������
     */
    public BigDecimal getXh() {
        return xh;
    }

    /**
     * ��ý��
     */
    public BigDecimal getJe() {
        return je;
    }

    /**
     * �������Ƽ�
     */
    public BigDecimal getWhpj() {
        return whpj;
    }

    /**
     * ����ۺ������
     */
    public BigDecimal getZhrmb() {
        return zhrmb;
    }

    /**
     * ��ñ�������
     */
    public String getBzmc() {
        return bzmc;
    }

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ����·�
     */
    public String getYf() {
        return yf;
    }

    /**
     * ��ֵ����
     * @param bzdm ����
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * ��ֵ���
     * @param xh ���
     */
    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    /**
     * ��ֵ���
     * @param je ���
     */
    public void setJe(BigDecimal je) {
        this.je = je;
    }

    /**
     * ��ֵ����Ƽ�
     * @param whpj ����Ƽ�
     */
    public void setWhpj(BigDecimal whpj) {
        this.whpj = whpj;
    }

    /**
     * ��ֵ�ۺ������
     * @param zhrmb �ۺ������
     */
    public void setZhrmb(BigDecimal zhrmb) {
        this.zhrmb = zhrmb;
    }

    /**
     * ��ֵ��������
     * @param bzmc ��������
     */
    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * ��ֵ�·�
     * @param yf �·�
     */
    public void setYf(String yf) {
        this.yf = yf;
    }


}
