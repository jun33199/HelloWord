package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Cqxxb implements Serializable {
    private String cqrmc; //'��Ǩ������'
    private String cqfw; //'��Ǩ��Χ'
    private String bcqrmc; //'����Ǩ������'
    private String bcqrlxdm; //'����Ǩ�����ʹ���'
    private String bcqrlxmc; //'����Ǩ����������'
    private String zjlxdm; //'֤�����ʹ���'
    private String zjlxmc; //'֤����������'
    private String zjhm; //'֤������'
    private String cqxxdz; //'��Ǩ��ϸ��ַ'
    private BigDecimal bcje; //'�������'
    private String bclxdm; //'�������ʹ���'
    private String bclxmc; //'������������'
    private BigDecimal bcmj; //'�������'
    private String bcfwdz; //'�������ݵ�ַ'
    private String cqxkzh; //'��Ǩ���֤��'
    private String qxdm; //'���ش���'
    private String lrr; //'¼����'
    private String cjr; //'������'
    private Timestamp lrrq; //'¼������'
    private Timestamp cjrq; //'��������'
    private String cqxxbh; //'��Ǩ��Ϣ���'
    private String sjly; //'������Դ'
    private String szqx; //'��������'
    private String swjgzzjgdm; //'˰�������֯��������'
    private String cqxmmc; //'��Ǩ��Ŀ����'
    private BigDecimal cqmj; //'��Ǩ���'
    private String swjgzzjgmc; //'˰�������֯��������'

    private String bcjeBegin; //�������
    private String bcjeEnd; //�������
    private String sfwh; //'�Ƿ�ά����ѯ 0��ѯ1ά��'

    private String lrrqBegin; //'¼������'
    private String lrrqEnd; //'¼������'

    private String gjrmc; // ����������
    private String zsfwjs; // ��ʽ���ݼ���

    private Timestamp cqxkzspsj; //'��Ǩ���֤����ʱ��'

    public String getBcfwdz() {
        return bcfwdz;
    }

    public BigDecimal getBcje() {
        return bcje;
    }

    public String getBclxdm() {
        return bclxdm;
    }

    public String getBclxmc() {
        return bclxmc;
    }

    public BigDecimal getBcmj() {
        return bcmj;
    }

    public String getBcqrlxdm() {
        return bcqrlxdm;
    }

    public String getBcqrlxmc() {
        return bcqrlxmc;
    }

    public String getBcqrmc() {
        return bcqrmc;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getCqrmc() {
        return cqrmc;
    }

    public String getCqxkzh() {
        return cqxkzh;
    }

    public String getCqxxbh() {
        return cqxxbh;
    }

    public String getCqxxdz() {
        return cqxxdz;
    }

    public String getLrr() {
        return lrr;
    }

    public Timestamp getLrrq() {
        return lrrq;
    }

    public String getQxdm() {
        return qxdm;
    }

    public String getSjly() {
        return sjly;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSzqx() {
        return szqx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getZjlxmc() {
        return zjlxmc;
    }

    public String getCqfw() {
        return cqfw;
    }

    public BigDecimal getCqmj() {
        return cqmj;
    }

    public String getCqxmmc() {
        return cqxmmc;
    }

    public String getBcjeBegin() {
        return bcjeBegin;
    }

    public String getBcjeEnd() {
        return bcjeEnd;
    }

    public String getLrrqBegin() {
        return lrrqBegin;
    }

    public String getLrrqEnd() {
        return lrrqEnd;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public void setSzqx(String szqx) {
        this.szqx = szqx;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setCqxxdz(String cqxxdz) {
        this.cqxxdz = cqxxdz;
    }

    public void setCqxxbh(String cqxxbh) {
        this.cqxxbh = cqxxbh;
    }

    public void setCqxkzh(String cqxkzh) {
        this.cqxkzh = cqxkzh;
    }

    public void setCqrmc(String cqrmc) {
        this.cqrmc = cqrmc;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setBcqrmc(String bcqrmc) {
        this.bcqrmc = bcqrmc;
    }

    public void setBcqrlxmc(String bcqrlxmc) {
        this.bcqrlxmc = bcqrlxmc;
    }

    public void setBcqrlxdm(String bcqrlxdm) {
        this.bcqrlxdm = bcqrlxdm;
    }

    public void setBcmj(BigDecimal bcmj) {
        this.bcmj = bcmj;
    }

    public void setBclxmc(String bclxmc) {
        this.bclxmc = bclxmc;
    }

    public void setBclxdm(String bclxdm) {
        this.bclxdm = bclxdm;
    }

    public void setBcje(BigDecimal bcje) {
        this.bcje = bcje;
    }

    public void setBcfwdz(String bcfwdz) {
        this.bcfwdz = bcfwdz;
    }

    public void setCqfw(String cqfw) {
        this.cqfw = cqfw;
    }

    public void setCqmj(BigDecimal cqmj) {
        this.cqmj = cqmj;
    }

    public void setCqxmmc(String cqxmmc) {
        this.cqxmmc = cqxmmc;
    }

    public void setBcjeBegin(String bcjeBegin) {
        this.bcjeBegin = bcjeBegin;
    }

    public void setBcjeEnd(String bcjeEnd) {
        this.bcjeEnd = bcjeEnd;
    }

    public void setLrrqBegin(String lrrqBegin) {
        this.lrrqBegin = lrrqBegin;
    }

    public void setLrrqEnd(String lrrqEnd) {
        this.lrrqEnd = lrrqEnd;
    }

    /**
     * @return Returns the sfwh.
     */
    public String getSfwh() {
        return sfwh;
    }

    /**
     * @param sfwh The sfwh to set.
     */
    public void setSfwh(String sfwh) {
        this.sfwh = sfwh;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public Timestamp getCqxkzspsj() {
        return cqxkzspsj;
    }

    public void setCqxkzspsj(Timestamp cqxkzspsj) {
        this.cqxkzspsj = cqxkzspsj;
    }

    public String getGjrmc() {
        return gjrmc;
    }

    public void setGjrmc(String gjrmc) {
        this.gjrmc = gjrmc;
    }

    public String getZsfwjs() {
        return zsfwjs;
    }

    public void setZsfwjs(String zsfwjs) {
        this.zsfwjs = zsfwjs;
    }
}
