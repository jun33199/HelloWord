package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �������������ֵ����
 */
public class Szqy implements Serializable {

    /**
     * ���������������
     */
    private java.lang.String fwszqydm;

    /**
     * ����������������
     */
    private java.lang.String fwszqymc;

    /**
     * ���������������Ԫ��
     */
    private java.lang.String fwszqyje;
    
    /**
     * ������������ÿƽ�׼۸����ޣ�Ԫ��
     */
    private java.lang.String  fwszqympmjgsx;

    /**
     * ��ע
     */
    private java.lang.String bz;

    /**
     * ¼����
     */
    private java.lang.String lrr;

    /**
     * ¼������
     */
    private java.sql.Timestamp lrrq;

    /**
     * ע����ʶ
     */
    private java.lang.String zxbs;


    public String getBz() {
        return bz;
    }

    public String getFwszqydm() {
        return fwszqydm;
    }

    public String getFwszqyje() {
        return fwszqyje;
    }

    public String getFwszqymc() {
        return fwszqymc;
    }

    public String getLrr() {
        return lrr;
    }

    public Timestamp getLrrq() {
        return lrrq;
    }

    public String getZxbs() {
        return zxbs;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setFwszqydm(String fwszqydm) {
        this.fwszqydm = fwszqydm;
    }

    public void setFwszqyje(String fwszqyje) {
        this.fwszqyje = fwszqyje;
    }

    public void setFwszqymc(String fwszqymc) {
        this.fwszqymc = fwszqymc;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

	public java.lang.String getFwszqympmjgsx() {
		return fwszqympmjgsx;
	}

	public void setFwszqympmjgsx(java.lang.String fwszqympmjgsx) {
		this.fwszqympmjgsx = fwszqympmjgsx;
	}
}
