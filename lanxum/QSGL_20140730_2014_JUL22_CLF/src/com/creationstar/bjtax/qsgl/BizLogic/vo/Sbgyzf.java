package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * �걨�������ѹ�����ס�����������Ĺ�����ֵ����
 */
public class Sbgyzf implements Serializable {

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * �ѹ�����ס��Ȩ��֤���
     */
    public java.lang.String yggyzfqszsh;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ������
     */
    public java.lang.String cjr;

    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ���εֿ۶�
     */
    public java.math.BigDecimal bcdke;

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ����ѹ�����ס��Ȩ��֤���
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * ���¼����
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * ��ô�����
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * ��ô�������
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * ���¼������
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    public BigDecimal getBcdke() {
        return bcdke;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ�ѹ�����ס��Ȩ��֤���
     * @param yggyzfqszsh �ѹ�����ס��Ȩ��֤���
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * ��ֵ¼����
     * @param lrr ¼����
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * ��ֵ������
     * @param cjr ������
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * ��ֵ��������
     * @param cjrq ��������
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * ��ֵ¼������
     * @param lrrq ¼������
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setBcdke(BigDecimal bcdke) {
        this.bcdke = bcdke;
    }


}
