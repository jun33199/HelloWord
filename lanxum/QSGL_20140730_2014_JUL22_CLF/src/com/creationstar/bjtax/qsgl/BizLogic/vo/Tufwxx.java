package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * ���ء�������Ϣ��ֵ����
 */
public class Tufwxx implements Serializable {
    /**
     * ����걨������
     */
    public String sbbh;

    /**
     * ���ء�����ϵͳΨһ��ʶ
     */
    public java.lang.String tdfwid;

    /**
     * ���ز���Ŀ����
     */
    public java.lang.String fdcxmmc;

    /**
     * ��ͬ����Լ��ǩ��ʱ��
     */
    public java.sql.Timestamp htqdsj;

    /**
     * ����
     */
    public java.lang.String fldm;

    /**
     * ��������
     */
    public java.lang.String flmc;

    /**
     * �Ƿ�Ϊ���ַ�
     * Ĭ��Ϊ�Ƕ��ַ�
     */
    public String sfesf = Constants.TUFWXX_SFESF_TRUE;
//    public String sfesf;

    /**
     * ���ء����������ַ
     */
    public java.lang.String tdfwzldz;

    /**
     * ���ء�����Ȩ��ת������
     */
    public java.lang.String tdfwqszylx;
    public java.lang.String tdfwqszymc;
    /**
     * �������
     */
    public java.lang.String fwlxdm;
    public java.lang.String fwlxmc;

    /**
     * ���ء�����Ȩ��ת�����
     */
    public java.math.BigDecimal tdfwqszymj;

    /**
     * ���ݽ������
     */
    public java.math.BigDecimal fwjzmj;

    /**
     * �ɽ��۸�����ң�
     */
    public java.math.BigDecimal cjjgrmb;

    /**
     * �ɽ��۸���ң�
     */
    public java.math.BigDecimal cjjgwb;

    /**
     * ���ִ���
     */
    public java.lang.String bzdm;
    public java.lang.String bzmc;

    /**
     * ����
     */
    public java.math.BigDecimal hldm;

    /**
     * �ۺϼ۸�����ң�
     */
    public java.math.BigDecimal zhjgrmb;

    /**
     * �����۸�����ң�
     */
    public java.math.BigDecimal pgjgrmb;

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
     * ��ע
     */
    public java.lang.String bz;

    /**
     * ���
     */
    public java.lang.String nd;


    /**
     * �������ز��������
     */
    public java.lang.String fwtdbmdm;

    /**
     * ˰�����
     */
    public String setz;

    /**
     * �ݻ���
     */
    public String rjl;

    /**
     * ���ؼ��Σ���ת��Ϊ������������
     */
    public String tdjc;


    /**
     * ������ء�����ϵͳΨһ��ʶ
     */
    public String getTdfwid() {
        return tdfwid;
    }

    /**
     * ��÷��ز���Ŀ����
     */
    public String getFdcxmmc() {
        return fdcxmmc;
    }

    /**
     * ��ú�ͬ����Լ��ǩ��ʱ��
     */
    public Timestamp getHtqdsj() {
        return htqdsj;
    }

    /**
     * ��÷���
     */
    public String getFldm() {
        return fldm;
    }

    /**
     * ������ء����������ַ
     */
    public String getTdfwzldz() {
        return tdfwzldz;
    }

    /**
     * ��÷������
     */
    public String getFwlxdm() {
        return fwlxdm;
    }

    /**
     * ������ء�����Ȩ��ת�����
     */
    public BigDecimal getTdfwqszymj() {
        return tdfwqszymj;
    }

    /**
     * ��÷��ݽ������
     */
    public BigDecimal getFwjzmj() {
        return fwjzmj;
    }

    /**
     * ��óɽ��۸�����ң�
     */
    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    /**
     * ��óɽ��۸���ң�
     */
    public BigDecimal getCjjgwb() {
        return cjjgwb;
    }

    /**
     * ��ñ��ִ���
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * ��û��ʴ���
     */
    public BigDecimal getHldm() {
        return hldm;
    }

    /**
     * ����ۺϼ۸�����ң�
     */
    public BigDecimal getZhjgrmb() {
        return zhjgrmb;
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

    public BigDecimal getPgjgrmb() {
        return pgjgrmb;
    }

    public String getFlmc() {
        return flmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getBz() {
        return bz;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public String getTdfwqszymc() {
        return tdfwqszymc;
    }

    public String getFwtdbmdm() {
        return fwtdbmdm;
    }

    public String getTdjc() {
        return tdjc;
    }

    public String getRjl() {
        return rjl;
    }

    public String getSfesf() {
        return sfesf;
    }


    /**
     * ��ֵ���ء�����ϵͳΨһ��ʶ
     * @param tdfwid ���ء�����ϵͳΨһ��ʶ
     */
    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    /**
     * ��ֵ���ز���Ŀ����
     * @param fdcxmmc ���ز���Ŀ����
     */
    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    /**
     * ��ֵ��ͬ����Լ��ǩ��ʱ��
     * @param htqdsj ��ͬ����Լ��ǩ��ʱ��
     */
    public void setHtqdsj(Timestamp htqdsj) {
        this.htqdsj = htqdsj;
    }

    /**
     * ��ֵ����
     * @param fldm ����
     */
    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    /**
     * ��ֵ���ء����������ַ
     * @param tdfwzldz ���ء����������ַ
     */
    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    /**
     * ��ֵ�������
     * @param fwlxdm �������
     */
    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    /**
     * ��ֵ���ء�����Ȩ��ת�����
     * @param tdfwqszymj ���ء�����Ȩ��ת�����
     */
    public void setTdfwqszymj(BigDecimal tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    /**
     * ��ֵ���ݽ������
     * @param fwjzmj ���ݽ������
     */
    public void setFwjzmj(BigDecimal fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    /**
     * ��ֵ�ɽ��۸�����ң�
     * @param cjjgrmb �ɽ��۸�����ң�
     */
    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    /**
     * ��ֵ�ɽ��۸���ң�
     * @param cjjgwb �ɽ��۸���ң�
     */
    public void setCjjgwb(BigDecimal cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    /**
     * ��ֵ���ִ���
     * @param bzdm ���ִ���
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * ��ֵ���ʴ���
     * @param hldm ���ʴ���
     */
    public void setHldm(BigDecimal hldm) {
        this.hldm = hldm;
    }

    /**
     * ��ֵ�ۺϼ۸�����ң�
     * @param zhjgrmb �ۺϼ۸�����ң�
     */
    public void setZhjgrmb(BigDecimal zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
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

    public void setPgjgrmb(BigDecimal pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwqszymc(String tdfwqszymc) {
        this.tdfwqszymc = tdfwqszymc;
    }

    public void setFwtdbmdm(String fwtdbmdm) {
        this.fwtdbmdm = fwtdbmdm;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setSfesf(String sfesf) {
        this.sfesf = sfesf;
    }


    /**
     * @return Returns the setz.
     */
    public String getSetz() {
        return setz;
    }

    /**
     * @param setz The setz to set.
     */
    public void setSetz(String setz) {
        this.setz = setz;
    }
}
