package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ��ʱ���������Ϣ-��Ǩֵ����
 */
public class Jsblcq implements Serializable {
    /**
     * ����걨������
     */
    public String sbbh;

    /**
     * ��ǨЭ�����
     */
    public java.lang.String cqxyh;

    /**
     * ����Ǩ���������ַ
     */
    public java.lang.String zldz;

    /**
     * ��Ǩ������
     */
    public java.math.BigDecimal cqbce;

    /**
     * ����ʹ�ò�����
     */
    public java.math.BigDecimal bcsybce;

    /**
     * ��Ǩ����ʣ���
     */
    public java.math.BigDecimal cqbcsye;

    /**
     * ���ο�ʹ�ò�����
     */
    public java.math.BigDecimal bcksybce;

    /**
     * �û���ʶ
     */
    public java.lang.String yhbs;

    /**
     * ״̬��ʶ
     */
    public java.lang.String ztbs;

    /**
     * ������
     */
    public java.lang.String cjr;

    /**
     * ��������
     */
    public java.sql.Timestamp cjrq;

    /**
     * ¼����
     */
    public java.lang.String lrr;

    /**
     * ¼������
     */
    public java.sql.Timestamp lrrq;

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
     * ��ò�ǨЭ�����
     */
    public String getCqxyh() {
        return cqxyh;
    }

    /**
     * ��ñ���Ǩ���������ַ
     */
    public String getZldz() {
        return zldz;
    }

    /**
     * ��ò�Ǩ������
     */
    public BigDecimal getCqbce() {
        return cqbce;
    }

    /**
     * ��ñ���ʹ�ò�����
     */
    public BigDecimal getBcsybce() {
        return bcsybce;
    }

    /**
     * ��ò�Ǩ����ʣ���
     */
    public BigDecimal getCqbcsye() {
        return cqbcsye;
    }

    /**
     * ��ñ��ο�ʹ�ò�����
     */
    public BigDecimal getBcksybce() {
        return bcksybce;
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

    public String getWsjc() {
        return wsjc;
    }

    public String getXh() {
        return xh;
    }

    public String getZsdjc() {
        return zsdjc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    /**
     * ��ֵ��ǨЭ�����
     * @param cqxyh ��ǨЭ�����
     */
    public void setCqxyh(String cqxyh) {
        this.cqxyh = cqxyh;
    }

    /**
     * ��ֵ����Ǩ���������ַ
     * @param zldz ����Ǩ���������ַ
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    /**
     * ��ֵ��Ǩ������
     * @param cqbce ��Ǩ������
     */
    public void setCqbce(BigDecimal cqbce) {
        this.cqbce = cqbce;
    }

    /**
     * ��ֵ����ʹ�ò�����
     * @param bcsybce ����ʹ�ò�����
     */
    public void setBcsybce(BigDecimal bcsybce) {
        this.bcsybce = bcsybce;
    }

    /**
     * ��ֵ��Ǩ����ʣ���
     * @param cqbcsye ��Ǩ����ʣ���
     */
    public void setCqbcsye(BigDecimal cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    /**
     * ��ֵ���ο�ʹ�ò�����
     * @param bcksybce ���ο�ʹ�ò�����
     */
    public void setBcksybce(BigDecimal bcksybce) {
        this.bcksybce = bcksybce;
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

    public void setWsjc(String wsjc) {
        this.wsjc = wsjc;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setZsdjc(String zsdjc) {
        this.zsdjc = zsdjc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }


}
