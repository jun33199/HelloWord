package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �걨���������ء�������Ϣ��Ĺ�����ֵ����
 */
public class Sbtdfwgl implements Serializable {

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * ���ء�����Ψһ��ʶ
     */
    public java.lang.String tdfwid;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ������
     */
    public java.lang.String cjr;

    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ������ء�����Ψһ��ʶ
     */
    public String getTdfwid() {
        return tdfwid;
    }

    /**
     * ���¼����
     */
    public String getLrr() {
        return lrr;
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
     * ���¼������
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ���ء�����Ψһ��ʶ
     * @param tdfwid ���ء�����Ψһ��ʶ
     */
    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    /**
     * ��ֵ¼����
     * @param lrr ¼����
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
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
     * ��ֵ¼������
     * @param lrrq ¼������
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }


}
