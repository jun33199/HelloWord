package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ���ִ����ֵ����
 */
public class Bz implements Serializable {

    /**
     * ���ִ���
     */
    public java.lang.String bzdm;

    /**
     * ��������
     */
    public java.lang.String bzmc;

    /**
     * ������ʱ��
     */
    public java.sql.Timestamp zhgxsj;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ע����ʶ
     */
    public java.lang.String zxbs;

    /**
     * ��ʾ˳��
     */
    public java.math.BigDecimal xssx;

    /**
     * ��ñ��ִ���
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * ��ñ�������
     */
    public String getBzmc() {
        return bzmc;
    }

    /**
     * ���������ʱ��
     */
    public Timestamp getZhgxsj() {
        return zhgxsj;
    }

    /**
     * ���¼����
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * ���¼������
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * ���ע����ʶ
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * �����ʾ˳��
     */
    public BigDecimal getXssx() {
        return xssx;
    }

    /**
     * ��ֵ���ִ���
     * @param bzdm ���ִ���
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * ��ֵ��������
     * @param bzmc ��������
     */
    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    /**
     * ��ֵ������ʱ��
     * @param zhgxsj ������ʱ��
     */
    public void setZhgxsj(Timestamp zhgxsj) {
        this.zhgxsj = zhgxsj;
    }

    /**
     * ��ֵ¼����
     * @param lrr ¼����
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * ��ֵ¼������
     * @param lrrq ¼������
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * ��ֵע����ʶ
     * @param zxbs ע����ʶ
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * ��ֵ��ʾ˳��
     * @param xssx ��ʾ˳��
     */
    public void setXssx(BigDecimal xssx) {
        this.xssx = xssx;
    }


}
