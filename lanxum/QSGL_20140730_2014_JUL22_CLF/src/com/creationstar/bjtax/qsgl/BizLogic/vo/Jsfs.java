package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ��˰��ʽ�����ֵ����
 */
public class Jsfs implements Serializable {

    /**
     * ��˰��ʽ����
     */
    public java.lang.String jsfsdm;

    /**
     * ��˰��ʽ����
     */
    public java.lang.String jsfsmc;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

    /**
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ע����ʶ
     */
    public java.lang.String zxbs;

    /**
     * ��ý�˰��ʽ����
     */
    public String getJsfsdm() {
        return jsfsdm;
    }

    /**
     * ��ý�˰��ʽ����
     */
    public String getJsfsmc() {
        return jsfsmc;
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
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
    }

    /**
     * ���ע����ʶ
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * ��ֵ��˰��ʽ����
     * @param jsfsdm ��˰��ʽ����
     */
    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    /**
     * ��ֵ��˰��ʽ����
     * @param jsfsmc ��˰��ʽ����
     */
    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
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
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ��ֵע����ʶ
     * @param zxbs ע����ʶ
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }


}
