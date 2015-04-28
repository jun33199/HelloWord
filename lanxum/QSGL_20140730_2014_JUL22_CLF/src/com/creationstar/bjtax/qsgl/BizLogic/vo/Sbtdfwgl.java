package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 申报主表与土地、房屋信息表的关联表值对象
 */
public class Sbtdfwgl implements Serializable {

    /**
     * 申报表号
     */
    public java.lang.String sbbh;

    /**
     * 土地、房屋唯一标识
     */
    public java.lang.String tdfwid;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 创建人
     */
    public java.lang.String cjr;

    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 获得申报表号
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * 获得土地、房屋唯一标识
     */
    public String getTdfwid() {
        return tdfwid;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得创建人
     */
    public String getCjr() {
        return cjr;
    }

    /**
     * 获得创建日期
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 赋值申报表号
     * @param sbbh 申报表号
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * 赋值土地、房屋唯一标识
     * @param tdfwid 土地、房屋唯一标识
     */
    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值创建人
     * @param cjr 创建人
     */
    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    /**
     * 赋值创建日期
     * @param cjrq 创建日期
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }


}
