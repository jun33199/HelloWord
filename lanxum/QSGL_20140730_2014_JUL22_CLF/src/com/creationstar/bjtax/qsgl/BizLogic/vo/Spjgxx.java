package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ������������˰���������Ϣֵ����
 */
public class Spjgxx implements Serializable {

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * ����˰���
     */
    public java.math.BigDecimal jmsje;

    /**
     * �˶�֪ͨ���ֺ�
     */
    public java.lang.String hdtzszh;

    /**
     * �������ɴ���
     */
    public java.lang.String jmlydm;

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
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ��ü���˰���
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    /**
     * ��ú˶�֪ͨ���ֺ�
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * ��ü������ɴ���
     */
    public String getJmlydm() {
        return jmlydm;
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
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ����˰���
     * @param jmsje ����˰���
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * ��ֵ�˶�֪ͨ���ֺ�
     * @param hdtzszh �˶�֪ͨ���ֺ�
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * ��ֵ�������ɴ���
     * @param jmlydm �������ɴ���
     */
    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
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


}
