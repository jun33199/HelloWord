package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ��˰��˰֤�걨��������ֵ����
 */
public class Qswszhz implements Serializable {

    /**
     * ���������
     */
    public java.lang.String jsjdm;

    /**
     * �걨���ܵ���
     */
    public java.lang.String sbhzdh;

    /**
     * �ɿ�ƾ֤��
     */
    public java.lang.String jkpzh;

    /**
     * ��������
     */
    public java.sql.Timestamp hzrq;

    /**
     * ʵ��˰��
     */
    public java.math.BigDecimal sjse;

    /**
     * ���ܿ�ʼ����
     */
    public java.sql.Timestamp hzksrq;

    /**
     * ���ܽ�������
     */
    public java.sql.Timestamp hzjsrq;

    /**
     * �����Ǵ���
     */
    public java.lang.String clbjdm;

    /**
     * ˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;

    /**
     * ���ܷ�ʽ����
     */
    public java.lang.String hzfs;

    /**
     * ���
     */
    public java.lang.String nd;

    /**
     * ¼��ʱ��
     */
    public java.sql.Timestamp lrrq;

    /**
     * ����ʱ��
     */
    public java.sql.Timestamp cjrq;

    /**
     * ¼���˴���
     */
    public java.lang.String lrr;

    /**
     * ������
     */
    public java.lang.String cjr;


    /**
     * ���ܷ�ʽ����
     */
    public java.lang.String hzfsmc;

    /**
     * ��˰��ʽ����
     */
    public java.lang.String jsfsmc;

    /**
     * ���յ����
     */
    public java.lang.String zsddm;

    /**
     * ���յ�����
     */
    public java.lang.String zsdmc;

    /**
     * ��ü��������
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * ����걨���ܵ���
     */
    public String getSbhzdh() {
        return sbhzdh;
    }

    /**
     * ��ýɿ�ƾ֤��
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * ��û�������
     */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /**
     * ���ʵ��˰��
     */
    public BigDecimal getSjse() {
        return sjse;
    }

    /**
     * ��û��ܿ�ʼ����
     */
    public Timestamp getHzksrq() {
        return hzksrq;
    }

    /**
     * ��û��ܽ�������
     */
    public Timestamp getHzjsrq() {
        return hzjsrq;
    }

    /**
     * ��ô����Ǵ���
     */
    public String getClbjdm() {
        return clbjdm;
    }

    /**
     * ���˰�������֯��������
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * ���¼���˴���
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ��ô���ʱ��
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * ���¼��ʱ��
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    public String getCjr() {
        return cjr;
    }

    public String getHzfsmc() {
        return hzfsmc;
    }

    public String getHzfs() {
        return hzfs;
    }

    public String getZsddm() {
        return zsddm;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    /**
     * ��ֵ���������
     * @param jsjdm ���������
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * ��ֵ�걨���ܵ���
     * @param sbhzdh �걨���ܵ���
     */
    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    /**
     * ��ֵ�ɿ�ƾ֤��
     * @param jkpzh �ɿ�ƾ֤��
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * ��ֵ��������
     * @param hzrq ��������
     */
    public void setHzrq(Timestamp hzrq) {
        this.hzrq = hzrq;
    }

    /**
     * ��ֵʵ��˰��
     * @param sjse ʵ��˰��
     */
    public void setSjse(BigDecimal sjse) {
        this.sjse = sjse;
    }

    /**
     * ��ֵ���ܿ�ʼ����
     * @param hzksrq ���ܿ�ʼ����
     */
    public void setHzksrq(Timestamp hzksrq) {
        this.hzksrq = hzksrq;
    }

    /**
     * ��ֵ���ܽ�������
     * @param hzjsrq ���ܽ�������
     */
    public void setHzjsrq(Timestamp hzjsrq) {
        this.hzjsrq = hzjsrq;
    }

    /**
     * ��ֵ�����Ǵ���
     * @param clbjdm �����Ǵ���
     */
    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    /**
     * ��ֵ˰�������֯��������
     * @param swjgzzjgdm ˰�������֯��������
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * ��ֵ¼���˴���
     * @param lrr ¼���˴���
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * ��ֵ����ʱ��
     * @param cjrq ����ʱ��
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * ��ֵ¼��ʱ��
     * @param lrrq ¼��ʱ��
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
    }

    public void setHzfs(String hzfs) {
        this.hzfs = hzfs;
    }

    public void setZsddm(String zsddm) {
        this.zsddm = zsddm;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }


}
