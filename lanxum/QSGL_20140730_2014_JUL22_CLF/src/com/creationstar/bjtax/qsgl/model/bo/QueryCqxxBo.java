package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: ��ѯ��Ǩʹ�����ҵ�����</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QueryCqxxBo implements Serializable {
    /**
     * ��ǨЭ����� ���ֶ���Ϊ��ѯ����
     */
    private String cqxyh;

    //�����ֶ���Ϊ��ѯ�����ʾ��Ǩ��Ϣ
    /**
     * ��Ǩ���������ַ
     */
    private String zldz;

    /**
     * ��Ǩ������
     */
    private BigDecimal cqbce;

    /**
     * ��Ǩ����ʣ���
     */
    private BigDecimal cqbcsye;
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
     * ʹ����� Sbcqglֵ������б�
     */
    private ArrayList listSbxx;


    public BigDecimal getCqbce() {
        return cqbce;
    }

    public BigDecimal getCqbcsye() {
        return cqbcsye;
    }

    public String getCqxyh() {
        return cqxyh;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getZldz() {
        return zldz;
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

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCqxyh(String cqxyhm) {
        this.cqxyh = cqxyhm;
    }

    public void setCqbcsye(BigDecimal cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    public void setCqbce(BigDecimal cqbce) {
        this.cqbce = cqbce;
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
}
