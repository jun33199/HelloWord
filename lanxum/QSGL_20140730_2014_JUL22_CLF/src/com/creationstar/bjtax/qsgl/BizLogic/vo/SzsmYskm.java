package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ˰��˰Ŀ��Ԥ���Ŀ���ձ�ֵ����
 */
public class SzsmYskm implements Serializable {

    /**
     * ˰��˰Ŀ����
     */
    public java.lang.String szsmdm;

    /**
     * Ԥ���Ŀ����
     */
    public java.lang.String yskmdm;

    /**
     * Ĭ��Ԥ���Ŀ����
     */
    public java.lang.String mryskmdm;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ���˰��˰Ŀ����
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * ���Ԥ���Ŀ����
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * ���Ĭ��Ԥ���Ŀ����
     */
    public String getMryskmdm() {
        return mryskmdm;
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
     * ��ֵ˰��˰Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * ��ֵԤ���Ŀ����
     * @param yskmdm Ԥ���Ŀ����
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * ��ֵĬ��Ԥ���Ŀ����
     * @param mryskmdm Ĭ��Ԥ���Ŀ����
     */
    public void setMryskmdm(String mryskmdm) {
        this.mryskmdm = mryskmdm;
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


}
