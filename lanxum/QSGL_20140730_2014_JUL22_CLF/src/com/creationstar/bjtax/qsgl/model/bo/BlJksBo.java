package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
 * <p>Title: </p>
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
public class BlJksBo implements Serializable {
    /**
     * �걨��ţ��걨�ɿ���˶Խ���ʱ��Ҫ�õ�
     */
    private String sbbh;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

    /**
     * ��˰֤��
     */
    private String wszh;

    /**
     * ����ֱ�
     */
    private String ndzb;

    /**
     * Ʊ֤�������
     */
    private String pzzldm;

    /**
     * �ɿ�������ͣ�1������ͨ��2������ʵ�
     */
    private String type;

    /**
     * ������ţ����ʵĽɿ���Ҫ�õ�
     */
    private String zbxh;

    /**
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * �����ʶ
     */
    private String zwbs;

    public String getJkpzh() {
        return jkpzh;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getPzzldm() {
        return pzzldm;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getType() {
        return type;
    }

    public String getZbxh() {
        return zbxh;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public String getZwbs() {
        return zwbs;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

}
