package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ���������ֵ����
 */
public class Gjdq implements Serializable {

    /**
     * ������ʱ��
     */
    public java.sql.Timestamp zhgxsj;

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
     * �������������
     */
    public java.lang.String gjdqdm;

    /**
     * �������������
     */
    public java.lang.String gjdqmc;

    /**
     * ��ʾ˳��
     */
    public java.lang.String xssx;

    /**
     * ���������ʱ��
     */
    public Timestamp getZhgxsj() {
        return zhgxsj;
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
     * ��ù������������
     */
    public String getGjdqdm() {
        return gjdqdm;
    }

    /**
     * ��ù������������
     */
    public String getGjdqmc() {
        return gjdqmc;
    }

    /**
     * �����ʾ˳��
     */
    public String getXssx() {
        return xssx;
    }

    /**
     * ��ֵ������ʱ��
     * @param zhgxsj ������ʱ��
     */
    public void setZhgxsj(Timestamp zhgxsj) {
        this.zhgxsj = zhgxsj;
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

    /**
     * ��ֵ�������������
     * @param gjdqdm �������������
     */
    public void setGjdqdm(String gjdqdm) {
        this.gjdqdm = gjdqdm;
    }

    /**
     * ��ֵ�������������
     * @param gjdqmc �������������
     */
    public void setGjdqmc(String gjdqmc) {
        this.gjdqmc = gjdqmc;
    }

    /**
     * ��ֵ��ʾ˳��
     * @param xssx ��ʾ˳��
     */
    public void setXssx(String xssx) {
        this.xssx = xssx;
    }


}
