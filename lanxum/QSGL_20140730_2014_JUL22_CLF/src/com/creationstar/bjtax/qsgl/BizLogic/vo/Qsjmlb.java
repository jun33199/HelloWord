package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ��˰�����������"��˰����ԭ������ֵ����
 */
public class Qsjmlb implements Serializable {

    /**
     * ��˰�������������
     */
    public java.lang.String qsjmlbdm;

    /**
     * ��˰��������������
     */
    public java.lang.String qsjmlbmc;

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
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ��˰������������������
     */
    public java.lang.String qsjmlbzcyj;

    /**
     * ��˰��������������
     */
    public java.lang.String qsjmlbms;
    
    /**
     * �������ʴ���
     */
    public java.lang.String jmxzdm;


    /**
     * �����˰�������������
     */
    public String getQsjmlbdm() {
        return qsjmlbdm;
    }

    /**
     * �����˰��������������
     */
    public String getQsjmlbmc() {
        return qsjmlbmc;
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
     * ���null
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * ��ֵ��˰�������������
     * @param qsjmlbdm ��˰�������������
     */
    public void setQsjmlbdm(String qsjmlbdm) {
        this.qsjmlbdm = qsjmlbdm;
    }

    /**
     * ��ֵ��˰��������������
     * @param qsjmlbmc ��˰��������������
     */
    public void setQsjmlbmc(String qsjmlbmc) {
        this.qsjmlbmc = qsjmlbmc;
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
     * ��ֵnull
     * @param zxbs null
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * @return bz
     */
    public java.lang.String getBz() {
        return bz;
    }

    /**
     * @param bz Ҫ���õ� bz
     */
    public void setBz(java.lang.String bz) {
        this.bz = bz;
    }

    /**
     * @return qsjmlbms
     */
    public java.lang.String getQsjmlbms() {
        return qsjmlbms;
    }

    /**
     * @param qsjmlbms Ҫ���õ� qsjmlbms
     */
    public void setQsjmlbms(java.lang.String qsjmlbms) {
        this.qsjmlbms = qsjmlbms;
    }

    /**
     * @return qsjmlbzcyj
     */
    public java.lang.String getQsjmlbzcyj() {
        return qsjmlbzcyj;
    }

    /**
     * @param qsjmlbzcyj Ҫ���õ� qsjmlbzcyj
     */
    public void setQsjmlbzcyj(java.lang.String qsjmlbzcyj) {
        this.qsjmlbzcyj = qsjmlbzcyj;
    }

	public java.lang.String getJmxzdm() {
		return jmxzdm;
	}

	public void setJmxzdm(java.lang.String jmxzdm) {
		this.jmxzdm = jmxzdm;
	}


}
