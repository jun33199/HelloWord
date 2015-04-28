package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 房屋所在区域表值对象
 */
public class Szqy implements Serializable {

    /**
     * 房屋所在区域代码
     */
    private java.lang.String fwszqydm;

    /**
     * 房屋所在区域名称
     */
    private java.lang.String fwszqymc;

    /**
     * 房屋所在区域金额（万元）
     */
    private java.lang.String fwszqyje;
    
    /**
     * 房屋所在区域每平米价格上限（元）
     */
    private java.lang.String  fwszqympmjgsx;

    /**
     * 备注
     */
    private java.lang.String bz;

    /**
     * 录入人
     */
    private java.lang.String lrr;

    /**
     * 录入日期
     */
    private java.sql.Timestamp lrrq;

    /**
     * 注销标识
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
