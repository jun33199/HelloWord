package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ��˰��˰֤��ϸ����ֵ����
 */
public class Qswszmx implements Serializable {

    /**
     * ��˰֤��
     */
    public java.lang.String wszh;

    /**
     * ����ֱ�
     */
    public java.lang.String ndzb;

    /**
     * Ʊ֤�������
     */
    public java.lang.String pzzldm;

    /**
     * ˰��˰Ŀ����
     */
    public java.lang.String szsmdm;

    /**
     * ���������
     */
    public java.lang.String jsjdm;

    /**
     * ˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;

    /**
     * ���֤������
     */
    public java.lang.String zjhm;

    /**
     * ˰�ִ���
     */
    public java.lang.String szdm;

    /**
     * ��˰���
     */
    public java.math.BigDecimal jsje;

    /**
     * ˰��
     */
    public java.math.BigDecimal sl;

    /**
     * �ѽɻ�۳�
     */
    public java.math.BigDecimal yjhkc;

    /**
     * ʵ��˰��
     */
    public java.math.BigDecimal sjse;

    /**
     * ˰��������ʼ����
     */
    public java.sql.Timestamp skssksrq;

    /**
     * ˰��������������
     */
    public java.sql.Timestamp skssjsrq;

    /**
     * ���ʱ�־
     */
    public java.lang.String jzbz;

    /**
     * Ԥ���Ŀ����
     */
    public java.lang.String yskmdm;

    /**
     * Ԥ�㼶�δ���
     */
    public java.lang.String ysjcdm;

    /**
     * ���
     */
    public java.lang.String nd;
    public String lrr;

    /**
     * ����ʱ��
     */
    public java.sql.Timestamp cjrq;

    /**
     * ���ز�Ȩ��ת�����
     */
    public java.math.BigDecimal qszymj;


    /**
     * ¼��ʱ��
     */
    public java.sql.Timestamp lrrq;
    public String cjr;

    /**
     * ˰��˰Ŀ����
     */
    public String szsmmc;
    /**
     * ˰������
     */
    public String szmc;
    /**
     * Ԥ���Ŀ����
     */
    public String yskmmc;
    /**
     * Ԥ�㼶������
     */
    public String ysjcmc;

    /**
     * �����˰֤��
     */
    public String getWszh() {
        return wszh;
    }

    /**
     * �������ֱ�
     */
    public String getNdzb() {
        return ndzb;
    }

    /**
     * ���Ʊ֤�������
     */
    public String getPzzldm() {
        return pzzldm;
    }

    /**
     * ���˰��˰Ŀ����
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * ��ü��������
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * ���˰�������֯��������
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * ������֤������
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * ���˰�ִ���
     */
    public String getSzdm() {
        return szdm;
    }

    /**
     * ��ü�˰���
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * ���˰��
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * ����ѽɻ�۳�
     */
    public BigDecimal getYjhkc() {
        return yjhkc;
    }

    /**
     * ���ʵ��˰��
     */
    public BigDecimal getSjse() {
        return sjse;
    }

    /**
     * ���˰��������ʼ����
     */
    public Timestamp getSkssksrq() {
        return skssksrq;
    }

    /**
     * ���˰��������������
     */
    public Timestamp getSkssjsrq() {
        return skssjsrq;
    }

    /**
     * ��ü��ʱ�־
     */
    public String getJzbz() {
        return jzbz;
    }

    /**
     * ���Ԥ���Ŀ����
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * ���Ԥ�㼶�δ���
     */
    public String getYsjcdm() {
        return ysjcdm;
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

    /**
     * ��÷��ز�Ȩ��ת�����
     */
    public BigDecimal getQszymj() {
        return qszymj;
    }

    public String getLrr() {
        return lrr;
    }

    public String getCjr() {
        return cjr;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    /**
     * ��ֵ��˰֤��
     * @param wszh ��˰֤��
     */
    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    /**
     * ��ֵ����ֱ�
     * @param ndzb ����ֱ�
     */
    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    /**
     * ��ֵƱ֤�������
     * @param pzzldm Ʊ֤�������
     */
    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    /**
     * ��ֵ˰��˰Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * ��ֵ���������
     * @param jsjdm ���������
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * ��ֵ˰�������֯��������
     * @param swjgzzjgdm ˰�������֯��������
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * ��ֵ���֤������
     * @param zjhm ���֤������
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * ��ֵ˰�ִ���
     * @param szdm ˰�ִ���
     */
    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    /**
     * ��ֵ��˰���
     * @param jsje ��˰���
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * ��ֵ˰��
     * @param sl ˰��
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * ��ֵ�ѽɻ�۳�
     * @param yjhkc �ѽɻ�۳�
     */
    public void setYjhkc(BigDecimal yjhkc) {
        this.yjhkc = yjhkc;
    }

    /**
     * ��ֵʵ��˰��
     * @param sjse ʵ��˰��
     */
    public void setSjse(BigDecimal sjse) {
        this.sjse = sjse;
    }

    /**
     * ��ֵ˰��������ʼ����
     * @param skssksrq ˰��������ʼ����
     */
    public void setSkssksrq(Timestamp skssksrq) {
        this.skssksrq = skssksrq;
    }

    /**
     * ��ֵ˰��������������
     * @param skssjsrq ˰��������������
     */
    public void setSkssjsrq(Timestamp skssjsrq) {
        this.skssjsrq = skssjsrq;
    }

    /**
     * ��ֵ���ʱ�־
     * @param jzbz ���ʱ�־
     */
    public void setJzbz(String jzbz) {
        this.jzbz = jzbz;
    }

    /**
     * ��ֵԤ���Ŀ����
     * @param yskmdm Ԥ���Ŀ����
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * ��ֵԤ�㼶�δ���
     * @param ysjcdm Ԥ�㼶�δ���
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
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

    /**
     * ��ֵ���ز�Ȩ��ת�����
     * @param qszymj ���ز�Ȩ��ת�����
     */
    public void setQszymj(BigDecimal qszymj) {
        this.qszymj = qszymj;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }


}
