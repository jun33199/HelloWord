package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Ԥ�㼶�δ����ֵ����
 */
public class Ysjc implements Serializable {

    /**
     * Ԥ�㼶�δ���
     */
    public java.lang.String ysjcdm;

    /**
     * Ԥ�㼶������
     */
    public java.lang.String ysjcmc;

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
     * ���Ԥ�㼶�δ���
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * ���Ԥ�㼶������
     */
    public String getYsjcmc() {
        return ysjcmc;
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
     * ��ֵԤ�㼶�δ���
     * @param ysjcdm Ԥ�㼶�δ���
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * ��ֵԤ�㼶������
     * @param ysjcmc Ԥ�㼶������
     */
    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
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


}
