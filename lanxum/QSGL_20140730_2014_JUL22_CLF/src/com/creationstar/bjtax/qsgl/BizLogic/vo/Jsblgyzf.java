package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ��ʱ���������Ϣ-����ס��ֵ����
 */
public class Jsblgyzf implements Serializable {
    /**
     * ����걨������
     */
    public String sbbh;

    /**
     * �ѹ�����ס��Ȩ��֤���
     */
    public java.lang.String yggyzfqszsh;

    /**
     * �����ַ
     */
    public java.lang.String zldz;

    /**
     * ���ۺ�ͬ����Լ��ǩ��ʱ��
     */
    public java.sql.Timestamp qdsj;

    /**
     * �������
     */
    public java.math.BigDecimal jzmj;

    /**
     * �ɽ��۸�
     */
    public java.math.BigDecimal cjjg;

    /**
     * ���εֿ۶�
     */
    public java.math.BigDecimal bcdke;

    /**
     * ʣ���
     */
    public java.math.BigDecimal sye;

    /**
     * ״̬��ʶ
     */
    public java.lang.String ztbs;

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
     * ���
     */
    public java.lang.String nd;

    /**
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ����־ּ��
     */
    public java.lang.String wsjc;

    /**
     * ���յ���
     */
    public java.lang.String zsdjc;

    /**
     * ���
     */
    public java.lang.String xh;

    /**
     * ʣ��������־
     */
    public String syeywbz;
    
    /**
     * ����Ȩ��֤��� add by zhangyj 20090219
     */
    public java.lang.String fwqszsh;    

    /**
     * ����ѹ�����ס��Ȩ��֤���
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * ��������ַ
     */
    public String getZldz() {
        return zldz;
    }

    /**
     * ��ó��ۺ�ͬ����Լ��ǩ��ʱ��
     */
    public Timestamp getQdsj() {
        return qdsj;
    }

    /**
     * ��ý������
     */
    public BigDecimal getJzmj() {
        return jzmj;
    }

    /**
     * ��óɽ��۸�
     */
    public BigDecimal getCjjg() {
        return cjjg;
    }

    /**
     * ��ñ��εֿ۶�
     */
    public BigDecimal getBcdke() {
        return bcdke;
    }

    /**
     * ���ʣ���
     */
    public BigDecimal getSye() {
        return sye;
    }

    /**
     * ���״̬��ʶ
     */
    public String getZtbs() {
        return ztbs;
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

    /**
     * ������
     */
    public String getNd() {
        return nd;
    }

    /**
     * ��ñ�ע
     */
    public String getBz() {
        return bz;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getZsdjc() {
        return zsdjc;
    }

    public String getXh() {
        return xh;
    }

    public String getWsjc() {
        return wsjc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    /**
     * ��÷���Ȩ��֤���
     */
    public String getFwqszsh()
    {
        return fwqszsh;
    }    
    
    /**
     * ��ֵ�ѹ�����ס��Ȩ��֤���
     * @param yggyzfqszsh �ѹ�����ס��Ȩ��֤���
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * ��ֵ�����ַ
     * @param zldz �����ַ
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    /**
     * ��ֵ���ۺ�ͬ����Լ��ǩ��ʱ��
     * @param qdsj ���ۺ�ͬ����Լ��ǩ��ʱ��
     */
    public void setQdsj(Timestamp qdsj) {
        this.qdsj = qdsj;
    }

    /**
     * ��ֵ�������
     * @param jzmj �������
     */
    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    /**
     * ��ֵ�ɽ��۸�
     * @param cjjg �ɽ��۸�
     */
    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    /**
     * ��ֵ���εֿ۶�
     * @param bcdke ���εֿ۶�
     */
    public void setBcdke(BigDecimal bcdke) {
        this.bcdke = bcdke;
    }

    /**
     * ��ֵʣ���
     * @param sye ʣ���
     */
    public void setSye(BigDecimal sye) {
        this.sye = sye;
    }

    /**
     * ��ֵ״̬��ʶ
     * @param ztbs ״̬��ʶ
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
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

    /**
     * ��ֵ���
     * @param nd ���
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * ��ֵ��ע
     * @param bz ��ע
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setZsdjc(String zsdjc) {
        this.zsdjc = zsdjc;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setWsjc(String wsjc) {
        this.wsjc = wsjc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }

    /**
     * ��ֵ����Ȩ��֤���
     * @param fwqszsh ����Ȩ��֤���
     */
    public void setFwqszsh(String fwqszsh)
    {
        this.fwqszsh = fwqszsh;
    }

}
