package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �������ʹ�������ֵ����
 */
public class Fwlb implements Serializable {

    /**
     * �������ʹ���
     */
    public java.lang.String fwlxdm;

    /**
     * ������������
     */
    public java.lang.String fwlxmc;

    /**
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ע����ʶ
     */
    public java.lang.String zxbs;

    /**
     * ��÷������ʹ���
     */
    public String getFwlxdm() {
        return fwlxdm;
    }

    /**
     * ��÷�����������
     */
    public String getFwlxmc() {
        return fwlxmc;
    }

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
    }

    /**
     * ���¼������
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * ���¼����
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * ���ע����ʶ
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * ��ֵ�������ʹ���
     * @param fwlxdm �������ʹ���
     */
    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    /**
     * ��ֵ������������
     * @param fwlxmc ������������
     */
    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ��ֵ¼������
     * @param lrrq ¼������
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * ��ֵ¼����
     * @param lrr ¼����
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * ��ֵע����ʶ
     * @param zxbs ע����ʶ
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }


}
