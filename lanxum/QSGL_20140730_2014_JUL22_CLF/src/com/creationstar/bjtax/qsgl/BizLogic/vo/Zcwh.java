package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ����ά��ֵ����
 */
public class Zcwh implements Serializable {

    /**
     * ָ�����
     */
    public java.lang.String zbdm;

    /**
     * ָ������
     */
    public java.lang.String zbmc;

    /**
     * ָ��ֵ
     */
    public java.lang.String zbz;

    /**
     * ��Ч��ʼ����
     */
    public java.sql.Timestamp sxqsrq;

    /**
     * ��Ч��ֹ����
     */
    public java.sql.Timestamp sxjzrq;

    /**
     * ��ע
     */
    public java.lang.String bz;

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
     * ���ָ�����
     */
    public String getZbdm() {
        return zbdm;
    }

    /**
     * ���ָ������
     */
    public String getZbmc() {
        return zbmc;
    }

    /**
     * ���ָ��ֵ
     */
    public String getZbz() {
        return zbz;
    }

    /**
     * �����Ч��ʼ����
     */
    public Timestamp getSxqsrq() {
        return sxqsrq;
    }

    /**
     * �����Ч��ֹ����
     */
    public Timestamp getSxjzrq() {
        return sxjzrq;
    }

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
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
     * ��ֵָ�����
     * @param zbdm ָ�����
     */
    public void setZbdm(String zbdm) {
        this.zbdm = zbdm;
    }

    /**
     * ��ֵָ������
     * @param zbmc ָ������
     */
    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    /**
     * ��ֵָ��ֵ
     * @param zbz ָ��ֵ
     */
    public void setZbz(String zbz) {
        this.zbz = zbz;
    }

    /**
     * ��ֵ��Ч��ʼ����
     * @param sxqsrq ��Ч��ʼ����
     */
    public void setSxqsrq(Timestamp sxqsrq) {
        this.sxqsrq = sxqsrq;
    }

    /**
     * ��ֵ��Ч��ֹ����
     * @param sxjzrq ��Ч��ֹ����
     */
    public void setSxjzrq(Timestamp sxjzrq) {
        this.sxjzrq = sxjzrq;
    }

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
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
