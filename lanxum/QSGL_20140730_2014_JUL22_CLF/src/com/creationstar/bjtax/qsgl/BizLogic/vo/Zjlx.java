package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ֤�����ʹ����ֵ����
 */
public class Zjlx implements Serializable {

    /**
     * ֤�����ʹ���
     */
    public java.lang.String zjlxdm;

    /**
     * ֤����������
     */
    public java.lang.String zjlxmc;

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
     * ����ʽ
     */
    public java.lang.String clfs;

    /**
     * ���֤�����ʹ���
     */
    public String getZjlxdm() {
        return zjlxdm;
    }

    /**
     * ���֤����������
     */
    public String getZjlxmc() {
        return zjlxmc;
    }

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
     * ��ô���ʽ
     */
    public String getClfs() {
        return clfs;
    }

    /**
     * ��ֵ֤�����ʹ���
     * @param zjlxdm ֤�����ʹ���
     */
    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    /**
     * ��ֵ֤����������
     * @param zjlxmc ֤����������
     */
    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
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
     * ��ֵ����ʽ
     * @param clfs ����ʽ
     */
    public void setClfs(String clfs) {
        this.clfs = clfs;
    }


}
