package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ���ط���Ȩ��ת����ʽ�����ֵ����
 */
public class Qszyxs implements Serializable {

    /**
     * ���ط���Ȩ��ת����ʽ����
     */
    public java.lang.String qszyxsdm;

    /**
     * ���ط���Ȩ��ת����ʽ����
     */
    public java.lang.String qszyxsmc;

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
     * ������ط���Ȩ��ת����ʽ����
     */
    public String getQszyxsdm() {
        return qszyxsdm;
    }

    /**
     * ������ط���Ȩ��ת����ʽ����
     */
    public String getQszyxsmc() {
        return qszyxsmc;
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
     * ��ֵ���ط���Ȩ��ת����ʽ����
     * @param qszyxsdm ���ط���Ȩ��ת����ʽ����
     */
    public void setQszyxsdm(String qszyxsdm) {
        this.qszyxsdm = qszyxsdm;
    }

    /**
     * ��ֵ���ط���Ȩ��ת����ʽ����
     * @param qszyxsmc ���ط���Ȩ��ת����ʽ����
     */
    public void setQszyxsmc(String qszyxsmc) {
        this.qszyxsmc = qszyxsmc;
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
