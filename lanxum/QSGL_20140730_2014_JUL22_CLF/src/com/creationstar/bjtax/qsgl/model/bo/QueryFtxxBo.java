package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: ��ѯ��������ʹ�����ҵ�����</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ������
 * @version 1.0
 */
public class QueryFtxxBo implements Serializable {
    /**
     * ���ء�����ϵͳΨһ��ʶ ���ֶ���Ϊ��ѯ����
     */
    private String tdfwid;

    // ����Ϊ��ѯ���

    /**
     * ���ز���Ŀ����
     */
    private String fdcxmmx;
    /**
     * ��ͬ����Լ��ǩ��ʱ��
     */
    private java.sql.Timestamp htqdsj;
    /**
     * ����
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm;

    /**
     * ���ء�����Ȩ��ת������
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    private String tdfwqszylx;
    private String tdfwqszylxmc;

    /**
     * �������
     */
    public ArrayList fwlxList = new ArrayList();
    private String fwlxdm;
    private String fwlxmc;


    /**
     * ���ء����������ַ
     */
    private String tdfwzldz;
    /**
     * ���ء�����Ȩ��ת�����
     */
    private BigDecimal tdfwqszymj;
    /**
     * ���ݽ������
     */
    private BigDecimal fwjzmj;
    /**
     * �ɽ��۸�����ң�
     */
    private BigDecimal cjjgrmb;
    /**
     * �����۸�����ң�
     */
    private BigDecimal pgjgrmb;
    /**
     * �ɽ��۸���ң�
     */
    private BigDecimal cjjgwb;
    /**
     * ����
     */
    public ArrayList bzList = new ArrayList();
    private String bzdm;
    private String bzmc;
    /**
     * ����
     */
    private BigDecimal hl;
    /**
     * �ۺϼ۸�����ң�
     */
    private BigDecimal zhjgrmb;
    /**
     * �걨��
     */
    private String cjr;
    /**
     * �걨����
     */
    private java.sql.Timestamp cjrq;

    /**
     * �ݻ���
     */
    private String rjl;

    /**
     * ���ؼ���
     */
    private String tdjc;

    /**
     * ʹ����� Sbftglֵ������б�
     */
    private ArrayList listSbxx = new ArrayList();
    public String getBzdm() {
        return bzdm;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzmc() {
        return bzmc;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public BigDecimal getCjjgwb() {
        return cjjgwb;
    }

    public String getFdcxmmx() {
        return fdcxmmx;
    }

    public String getFldm() {
        return fldm;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public String getFlmc() {
        return flmc;
    }

    public BigDecimal getFwjzmj() {
        return fwjzmj;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public ArrayList getFwlxList() {
        return fwlxList;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public BigDecimal getHl() {
        return hl;
    }

    public Timestamp getHtqdsj() {
        return htqdsj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public BigDecimal getPgjgrmb() {
        return pgjgrmb;
    }

    public String getTdfwid() {
        return tdfwid;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public BigDecimal getTdfwqszymj() {
        return tdfwqszymj;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public BigDecimal getZhjgrmb() {
        return zhjgrmb;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getRjl() {
        return rjl;
    }

    public String getTdjc() {
        return tdjc;
    }

    public void setZhjgrmb(BigDecimal zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setTdfwqszymj(BigDecimal tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    public void setPgjgrmb(BigDecimal pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setHtqdsj(Timestamp htqdsj) {
        this.htqdsj = htqdsj;
    }

    public void setHl(BigDecimal hl) {
        this.hl = hl;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxList(ArrayList fwlxList) {
        this.fwlxList = fwlxList;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setFwjzmj(BigDecimal fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFdcxmmx(String fdcxmmx) {
        this.fdcxmmx = fdcxmmx;
    }

    public void setCjjgwb(BigDecimal cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }


}
