package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ��˰�걨����ֵ����
 */
public class Sbzb implements Serializable {

    /**
     * �걨���
     */
    public java.lang.String sbbh;
    /**
     * ���ӽ�ίҵ�����ֶΣ� fujx��20081125
     */
    private String jwywbh;

    /**
     * ���Ӻ�ͬ����ֶΣ� fujx��20081125
     */
    private String htbh;


    /**
     * �걨����
     */
    public java.sql.Timestamp sbrq;

    /**
     * ������ϵ����
     */
    public String lsgxmc;

    /**
     * ע����������
     */
    public String zclxmc;

    /**
     * ˰����������
     */
    public String sklxmc;

    /**
     * �绰
     */
    public String dh;

    /**
     * ��˰��ʽ
     */
    public java.lang.String jsfsdm;

    /**
     * ��˰��ʽ����
     */
    public java.lang.String jsfsmc;

    /**
     * �������ز��������
     */
    public java.lang.String fwtdbmdm;

    /**
     * ��˰�����ʹ���
     */
    public java.lang.String nsrlxdm;

    /**
     * ��˰����������
     */
    public java.lang.String nsrlxmc;

    /**
     * ����˰�������֯��������
     */
    public java.lang.String swjgzzjgdm;

    /**
     * ����˰�������֯��������
     */
    public java.lang.String swjgzzjgmc;

    /**
     * ������Ա����
     */
    public java.lang.String zsrymc;

    /**
     * ˰��
     */
    public java.math.BigDecimal sl;

    /**
     * ��˰���
     */
    public java.math.BigDecimal jsje;

    /**
     * Ӧ��˰��
     */
    public java.math.BigDecimal ynse;

    /**
     * �������˰��ʶ
     */
    public java.lang.String bljmsbs;

    /**
     * ��¼��ʶ
     */
    public java.lang.String blbs;

    /**
     * �û���ʶ
     */
    public java.lang.String yhbs;

    /**
     * ״̬��ʶ
     */
    public java.lang.String ztbs;

    /**
     * ��ע
     */
    public java.lang.String bz;

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
     * �Է��걨���
     */
    public String dfsbbh;

    /**
     * Ʊ֤�ʻ�����
     */
    public String pzzhdm;

    /**
     * Ʊ֤�ʻ�����
     */
    public String pzzhmc;

    /**
     * �������κ�
     */
    public String drpch;

    /**
     * ˰�����
     */
    public String setz;

    /**
     * ������Դ
     */
    public String sjly;

    /**
     * ����걨���
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * ����걨����
     */
    public Timestamp getSbrq() {
        return sbrq;
    }

    /**
     * ��ý�˰��ʽ
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
     * ��÷������ز��������
     */
    public String getFwtdbmdm() {
        return fwtdbmdm;
    }

    /**
     * �����˰�����ʹ���
     */
    public String getNsrlxdm() {
        return nsrlxdm;
    }

    /**
     * �������˰�������֯��������
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * ���������Ա����
     */
    public String getZsrymc() {
        return zsrymc;
    }

    /**
     * ���˰��
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * ��ü�˰���
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * ���Ӧ��˰��
     */
    public BigDecimal getYnse() {
        return ynse;
    }

    /**
     * ��ð������˰��ʶ
     */
    public String getBljmsbs() {
        return bljmsbs;
    }

    /**
     * ��ò�¼��ʶ
     */
    public String getBlbs() {
        return blbs;
    }

    /**
     * ����û���ʶ
     */
    public String getYhbs() {
        return yhbs;
    }

    /**
     * ���״̬��ʶ
     */
    public String getZtbs() {
        return ztbs;
    }

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
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

    public String getDfsbbh() {
        return dfsbbh;
    }

    public String getDh() {
        return dh;
    }

    public String getLsgxmc() {
        return lsgxmc;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getSklxmc() {
        return sklxmc;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getZclxmc() {
        return zclxmc;
    }

    public String getPzzhdm() {
        return pzzhdm;
    }

    public String getPzzhmc() {
        return pzzhmc;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getSetz() {
        return setz;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getSjly() {
        return sjly;
    }

    /**
     * ��ֵ�걨���
     * @param sbbh �걨���
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * ��ֵ�걨����
     * @param sbrq �걨����
     */
    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * ��ֵ��˰��ʽ
     * @param jsfsdm ��˰��ʽ
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
     * ��ֵ�������ز��������
     * @param fwtdbmdm �������ز��������
     */
    public void setFwtdbmdm(String fwtdbmdm) {
        this.fwtdbmdm = fwtdbmdm;
    }

    /**
     * ��ֵ��˰�����ʹ���
     * @param nsrlxdm ��˰�����ʹ���
     */
    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    /**
     * ��ֵ����˰�������֯��������
     * @param swjgzzjgdm ����˰�������֯��������
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * ��ֵ������Ա����
     * @param zsrymc ������Ա����
     */
    public void setZsrymc(String zsrymc) {
        this.zsrymc = zsrymc;
    }

    /**
     * ��ֵ˰��
     * @param sl ˰��
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * ��ֵ��˰���
     * @param jsje ��˰���
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * ��ֵӦ��˰��
     * @param ynse Ӧ��˰��
     */
    public void setYnse(BigDecimal ynse) {
        this.ynse = ynse;
    }

    /**
     * ��ֵ�������˰��ʶ
     * @param bljmsbs �������˰��ʶ
     */
    public void setBljmsbs(String bljmsbs) {
        this.bljmsbs = bljmsbs;
    }

    /**
     * ��ֵ��¼��ʶ
     * @param blbs ��¼��ʶ
     */
    public void setBlbs(String blbs) {
        this.blbs = blbs;
    }

    /**
     * ��ֵ�û���ʶ
     * @param yhbs �û���ʶ
     */
    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    /**
     * ��ֵ״̬��ʶ
     * @param ztbs ״̬��ʶ
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
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

    public void setDfsbbh(String dfsbbh) {
        this.dfsbbh = dfsbbh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public void setLsgxmc(String lsgxmc) {
        this.lsgxmc = lsgxmc;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setSklxmc(String sklxmc) {
        this.sklxmc = sklxmc;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setZclxmc(String zclxmc) {
        this.zclxmc = zclxmc;
    }

    public void setPzzhdm(String pzzhdm) {
        this.pzzhdm = pzzhdm;
    }

    public void setPzzhmc(String pzzhmc) {
        this.pzzhmc = pzzhmc;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }
}
