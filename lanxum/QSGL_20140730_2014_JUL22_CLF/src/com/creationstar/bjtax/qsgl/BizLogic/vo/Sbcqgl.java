package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * �걨�������Ǩ��Ĺ�����ֵ����
 */
public class Sbcqgl implements Serializable {

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * ��ǨЭ�����
     */
    public java.lang.String cqxyh;

    /**
     * ����ʹ�ò�����
     */
    public BigDecimal bcsybce;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ������
     */
    public java.lang.String cjr;

    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ��ò�ǨЭ�����
     */
    public String getCqxyh() {
        return cqxyh;
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

    public BigDecimal getBcsybce() {
        return bcsybce;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ��ǨЭ�����
     * @param cqxyh ��ǨЭ�����
     */
    public void setCqxyh(String cqxyh) {
        this.cqxyh = cqxyh;
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

    public void setBcsybce(BigDecimal bcsybce) {
        this.bcsybce = bcsybce;
    }


}
