package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ������Ϣֵ����
 */
public class Grxx implements Serializable {
    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * ���������
     */
    public java.lang.String jsjdm;

    /**
     * ��˰������
     */
    public java.lang.String nsrmc;

    /**
     * ֤������
     */
    public java.lang.String sfzjlx;

    /**
     * ���֤������
     */
    public java.lang.String sfzjlxmc;

    /**
     * ֤������
     */
    public java.lang.String sfzjhm;

    /**
     * ��������
     */
    public String gjdm;

    /**
     * ��������
     */
    public String gjmc;

    /**
     * ��ϵ�绰
     */
    public java.lang.String lxdh;

    /**
     * ���ݽ�����ʶ
     */
    public java.lang.String fwjhbs;

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
     * ��Ȩ������
     */
    public java.lang.String cqrlx;

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ��ü��������
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * �����˰������
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * ���֤������
     */
    public String getSfzjlx() {
        return sfzjlx;
    }

    /**
     * ���null
     */
    public String getSfzjlxmc() {
        return sfzjlxmc;
    }

    /**
     * ���֤������
     */
    public String getSfzjhm() {
        return sfzjhm;
    }

    /**
     * �����ϵ�绰
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * ��÷��ݽ�����ʶ
     */
    public String getFwjhbs() {
        return fwjhbs;
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

    public String getGjdm() {
        return gjdm;
    }

    public String getGjmc() {
        return gjmc;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ���������
     * @param jsjdm ���������
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * ��ֵ��˰������
     * @param nsrmc ��˰������
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * ��ֵ֤������
     * @param sfzjlx ֤������
     */
    public void setSfzjlx(String sfzjlx) {
        this.sfzjlx = sfzjlx;
    }

    /**
     * ��ֵnull
     * @param sfzjlxmc null
     */
    public void setSfzjlxmc(String sfzjlxmc) {
        this.sfzjlxmc = sfzjlxmc;
    }

    /**
     * ��ֵ֤������
     * @param sfzjhm ֤������
     */
    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    /**
     * ��ֵ��ϵ�绰
     * @param lxdh ��ϵ�绰
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * ��ֵ���ݽ�����ʶ
     * @param fwjhbs ���ݽ�����ʶ
     */
    public void setFwjhbs(String fwjhbs) {
        this.fwjhbs = fwjhbs;
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

    public void setGjdm(String gjdm) {
        this.gjdm = gjdm;
    }

    public void setGjmc(String gjmc) {
        this.gjmc = gjmc;
    }


    /**
     * @return Returns the cqrlx.
     */
    public java.lang.String getCqrlx() {
        return cqrlx;
    }

    /**
     * @param cqrlx The cqrlx to set.
     */
    public void setCqrlx(java.lang.String cqrlx) {
        this.cqrlx = cqrlx;
    }
}
