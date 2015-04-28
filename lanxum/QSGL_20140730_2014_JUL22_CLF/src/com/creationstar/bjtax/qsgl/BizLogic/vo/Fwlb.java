package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 房屋类型代码数据值对象
 */
public class Fwlb implements Serializable {

    /**
     * 房屋类型代码
     */
    public java.lang.String fwlxdm;

    /**
     * 房屋类型名称
     */
    public java.lang.String fwlxmc;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 获得房屋类型代码
     */
    public String getFwlxdm() {
        return fwlxdm;
    }

    /**
     * 获得房屋类型名称
     */
    public String getFwlxmc() {
        return fwlxmc;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 赋值房屋类型代码
     * @param fwlxdm 房屋类型代码
     */
    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    /**
     * 赋值房屋类型名称
     * @param fwlxmc 房屋类型名称
     */
    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }


}
