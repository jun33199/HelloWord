package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ��˰�����ʹ���ֵ����
 */
public class Qsdwxz implements Serializable {

    /**
     * ��˰��λ���ʴ���
     */
    public java.lang.String nsrlxdm;

    /**
     * ��˰��λ��������
     */
    public java.lang.String nsrlxmc;

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
     * �����˰��λ���ʴ���
     */
    public String getNsrlxdm() {
        return nsrlxdm;
    }

    /**
     * �����˰��λ��������
     */
    public String getNsrlxmc() {
        return nsrlxmc;
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
     * ��ֵ��˰��λ���ʴ���
     * @param nsrlxdm ��˰��λ���ʴ���
     */
    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    /**
     * ��ֵ��˰��λ��������
     * @param nsrlxmc ��˰��λ��������
     */
    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
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
