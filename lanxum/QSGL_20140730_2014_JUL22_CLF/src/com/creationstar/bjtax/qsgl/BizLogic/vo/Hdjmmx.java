package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * �˶�������ϸ��ֵ����
 */
public class Hdjmmx implements Serializable {

    /**
     * ���߱��
     */
    public java.lang.String zcbh;

    /**
     * �˶�֪ͨ���
     */
    public java.lang.String hdtzsh;

    /**
     * ������
     */
    public java.math.BigDecimal jmje;

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
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ���
     */
    public java.lang.String nd;

    /**
     * ������߱��
     */
    public String getZcbh() {
        return zcbh;
    }

    /**
     * ��ú˶�֪ͨ���
     */
    public String getHdtzsh() {
        return hdtzsh;
    }

    /**
     * ��ü�����
     */
    public BigDecimal getJmje() {
        return jmje;
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

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
    }

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ��ֵ���߱��
     * @param zcbh ���߱��
     */
    public void setZcbh(String zcbh) {
        this.zcbh = zcbh;
    }

    /**
     * ��ֵ�˶�֪ͨ���
     * @param hdtzsh �˶�֪ͨ���
     */
    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }

    /**
     * ��ֵ������
     * @param jmje ������
     */
    public void setJmje(BigDecimal jmje) {
        this.jmje = jmje;
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

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }


}
