package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * �걨�ɿ���ϸ����ֵ����
 */
public class Sbjkmx implements Serializable {

    /**
     * ˰��˰Ŀ����
     */
    public java.lang.String szsmdm;
    public String szsmmc;

    /**
     * �ɿ�ƾ֤��
     */
    public java.lang.String jkpzh;

    /**
     * ���������
     */
    public java.lang.String jsjdm;

    /**
     * Ԥ���Ŀ����
     */
    public java.lang.String yskmdm;
    public String yskmmc;

    /**
     * Ԥ�㼶��
     */
    public java.lang.String ysjcdm;
    public String ysjcmc;

    /**
     * Ԥ���Ŀ�ֳɱ���
     */
    public String yskmfcbl;
    
    /**
     * ��˰����
     */
    public java.math.BigDecimal kssl;

    /**
     * ��˰���
     */
    public java.math.BigDecimal jsje;

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
     * �����
     */
    public java.math.BigDecimal rkje;

    /**
     * �걨���
     */
    public java.lang.String sbbh;

    /**
     * �м��ֳ�
     */
    public java.math.BigDecimal sjfc;

    /**
     * �����ֳ�
     */
    public java.math.BigDecimal qjfc;

    /**
     * ˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;
    public String swjgzzjgmc;

    /**
     * ���
     */
    public java.lang.String nd;

    /**
     * ˰��
     */
    public java.math.BigDecimal sl;

    /**
     * ����ʱ��
     */
    public java.sql.Timestamp cjrq;

    /**
     * ¼��ʱ��
     */
    public java.sql.Timestamp lrrq;

    /**
     * ���ش���
     */
    public java.lang.String qxdm;

    /**
     * ���˰��˰Ŀ����
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * ��ýɿ�ƾ֤��
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * ��ü��������
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * ���Ԥ���Ŀ����
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * ���Ԥ�㼶��
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * ���Ԥ��ֳɱ���
     */
    public String getYskmfcbl() {
        return yskmfcbl;
    }
    
    /**
     * ��ÿ�˰����
     */
    public BigDecimal getKssl() {
        return kssl;
    }

    /**
     * ��ü�˰���
     */
    public BigDecimal getJsje() {
        return jsje;
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
     * ��������
     */
    public BigDecimal getRkje() {
        return rkje;
    }

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ����м��ֳ�
     */
    public BigDecimal getSjfc() {
        return sjfc;
    }

    /**
     * ��������ֳ�
     */
    public BigDecimal getQjfc() {
        return qjfc;
    }

    /**
     * ���˰�������֯��������
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ���˰��
     */
    public BigDecimal getSl() {
        return sl;
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
     * ������ش���
     */
    public String getQxdm() {
        return qxdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    /**
     * ��ֵ˰��˰Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * ��ֵ�ɿ�ƾ֤��
     * @param jkpzh �ɿ�ƾ֤��
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * ��ֵ���������
     * @param jsjdm ���������
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * ��ֵԤ���Ŀ�ֳɱ���
     * @param yskmfcbl Ԥ���Ŀ�ֳɱ���
     */
    public void setYskmfcbl(String yskmfcbl) {
        this.yskmfcbl = yskmfcbl;
    }
    
    /**
     * ��ֵԤ���Ŀ����
     * @param yskmdm Ԥ���Ŀ����
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * ��ֵԤ�㼶��
     * @param ysjcdm Ԥ�㼶��
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * ��ֵ��˰����
     * @param kssl ��˰����
     */
    public void setKssl(BigDecimal kssl) {
        this.kssl = kssl;
    }

    /**
     * ��ֵ��˰���
     * @param jsje ��˰���
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
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
     * ��ֵ�����
     * @param rkje �����
     */
    public void setRkje(BigDecimal rkje) {
        this.rkje = rkje;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ�м��ֳ�
     * @param sjfc �м��ֳ�
     */
    public void setSjfc(BigDecimal sjfc) {
        this.sjfc = sjfc;
    }

    /**
     * ��ֵ�����ֳ�
     * @param qjfc �����ֳ�
     */
    public void setQjfc(BigDecimal qjfc) {
        this.qjfc = qjfc;
    }

    /**
     * ��ֵ˰�������֯��������
     * @param swjgzzjgdm ˰�������֯��������
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * ��ֵ˰��
     * @param sl ˰��
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
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
     * ��ֵ���ش���
     * @param qxdm ���ش���
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }


}
