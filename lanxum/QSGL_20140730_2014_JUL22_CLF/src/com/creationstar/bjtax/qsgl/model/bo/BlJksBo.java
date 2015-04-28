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
     * 申报表号，申报缴款书核对金额的时候要用到
     */
    private String sbbh;

    /**
     * 缴款凭证号
     */
    private String jkpzh;

    /**
     * 完税证号
     */
    private String wszh;

    /**
     * 年度字别
     */
    private String ndzb;

    /**
     * 票证种类代码
     */
    private String pzzldm;

    /**
     * 缴款书的类型，1代表普通，2代表调帐的
     */
    private String type;

    /**
     * 主表序号，调帐的缴款书要用到
     */
    private String zbxh;

    /**
     * 税款类型代码
     */
    private String sklxdm;

    /**
     * 帐务标识
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
