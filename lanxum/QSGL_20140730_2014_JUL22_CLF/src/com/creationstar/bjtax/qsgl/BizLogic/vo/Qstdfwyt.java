package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *  ��˰���ط�����;ֵ����
 */
public class Qstdfwyt implements Serializable {

    /**
     * ��˰���ط�����;����
     */
    public java.lang.String qstdfwytdm;

    /**
     * ��˰���ط�����;����
     */
    public java.lang.String qstdfwytmc;

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
     * �����˰���ط�����;����
     */
    public String getQstdfwytdm() {
        return qstdfwytdm;
    }

    /**
     * �����˰���ط�����;����
     */
    public String getQstdfwytmc() {
        return qstdfwytmc;
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
     * ��ֵ��˰���ط�����;����
     * @param qstdfwytdm ��˰���ط�����;����
     */
    public void setQstdfwytdm(String qstdfwytdm) {
        this.qstdfwytdm = qstdfwytdm;
    }

    /**
     * ��ֵ��˰���ط�����;����
     * @param qstdfwytmc ��˰���ط�����;����
     */
    public void setQstdfwytmc(String qstdfwytmc) {
        this.qstdfwytmc = qstdfwytmc;
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
