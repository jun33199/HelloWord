package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: ��ѯ�ѹ�����ס��ʹ�����ҵ�����</p>
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
public class QueryYggyzfBo implements Serializable {
    /**
     *  �ѹ�����ס��Ȩ��֤��� ���ֶ���Ϊ��ѯ����
     */
    private String yggyzfqszsh;

    //�����ֶ���Ϊ��ѯ�����ʾ��Ǩ��Ϣ
    /**
     * ���������ַ
     */
    private String zldz;

    /**
     * ���ۺ�ͬ����Լ��ǩ��ʱ��
     */
    private java.sql.Timestamp qdsj;

    /**
     * �������
     */
    private BigDecimal jzmj;
    /**
     * �ɽ��۸�
     */
    private BigDecimal cjjg;
    /**
     * ʣ���
     */
    private BigDecimal sye;
    /**
     * �걨��
     */
    private String cjr;
    /**
     * �걨����
     */
    private java.sql.Timestamp cjrq;
    /**
     * ʣ��������־
     */
    private String syeywbz;
    /**
     * ����Ȩ��֤��� add by zhangyj 20090219
     */
    private String fwqszsh;

    /**
     * ʹ����� Sbgyzfֵ������б�
     */
    private ArrayList listSbxx;
    public BigDecimal getJzmj() {
        return jzmj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public Timestamp getQdsj() {
        return qdsj;
    }

    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    public String getZldz() {
        return zldz;
    }

    public BigDecimal getCjjg() {
        return cjjg;
    }

    public BigDecimal getSye() {
        return sye;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    public String getFwqszsh()
    {
        return fwqszsh;
    }
    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setQdsj(Timestamp qdsj) {
        this.qdsj = qdsj;
    }

    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    public void setSye(BigDecimal sye) {
        this.sye = sye;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }
    public void setFwqszsh(String fwqszsh)
    {
        this.fwqszsh = fwqszsh;
    }    
}
