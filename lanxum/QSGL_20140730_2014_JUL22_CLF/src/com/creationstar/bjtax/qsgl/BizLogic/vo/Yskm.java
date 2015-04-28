package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 预算科目代码表值对象
 */
public class Yskm implements Serializable {

    /**
     * 预算科目代码
     */
    public java.lang.String yskmdm;

    /**
     * 预算科目名称
     */
    public java.lang.String yskmmc;

    /**
     * 中央分成比例
     */
    public java.math.BigDecimal zyfcbl;

    /**
     * 区县分成比例
     */
    public java.math.BigDecimal qxfcbl;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 市局分成比例
     */
    public java.math.BigDecimal sjfcbl;

    /**
     * 预算级次代码
     */
    public java.lang.String ysjcdm;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 默认税种税目代码
     */
    public java.lang.String mrszsmdm;

    /**
     * 生效日期
     */
    public java.sql.Timestamp sxrq;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 父节点代码
     */
    public java.lang.String fjddm;

    /**
     * 层次标识
     */
    public java.lang.String ccbs;

    /**
     * 获得预算科目代码
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * 获得预算科目名称
     */
    public String getYskmmc() {
        return yskmmc;
    }

    /**
     * 获得中央分成比例
     */
    public BigDecimal getZyfcbl() {
        return zyfcbl;
    }

    /**
     * 获得区县分成比例
     */
    public BigDecimal getQxfcbl() {
        return qxfcbl;
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
     * 获得市局分成比例
     */
    public BigDecimal getSjfcbl() {
        return sjfcbl;
    }

    /**
     * 获得预算级次代码
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 获得默认税种税目代码
     */
    public String getMrszsmdm() {
        return mrszsmdm;
    }

    /**
     * 获得生效日期
     */
    public Timestamp getSxrq() {
        return sxrq;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得父节点代码
     */
    public String getFjddm() {
        return fjddm;
    }

    /**
     * 获得层次标识
     */
    public String getCcbs() {
        return ccbs;
    }

    /**
     * 赋值预算科目代码
     * @param yskmdm 预算科目代码
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * 赋值预算科目名称
     * @param yskmmc 预算科目名称
     */
    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }

    /**
     * 赋值中央分成比例
     * @param zyfcbl 中央分成比例
     */
    public void setZyfcbl(BigDecimal zyfcbl) {
        this.zyfcbl = zyfcbl;
    }

    /**
     * 赋值区县分成比例
     * @param qxfcbl 区县分成比例
     */
    public void setQxfcbl(BigDecimal qxfcbl) {
        this.qxfcbl = qxfcbl;
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
     * 赋值市局分成比例
     * @param sjfcbl 市局分成比例
     */
    public void setSjfcbl(BigDecimal sjfcbl) {
        this.sjfcbl = sjfcbl;
    }

    /**
     * 赋值预算级次代码
     * @param ysjcdm 预算级次代码
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * 赋值默认税种税目代码
     * @param mrszsmdm 默认税种税目代码
     */
    public void setMrszsmdm(String mrszsmdm) {
        this.mrszsmdm = mrszsmdm;
    }

    /**
     * 赋值生效日期
     * @param sxrq 生效日期
     */
    public void setSxrq(Timestamp sxrq) {
        this.sxrq = sxrq;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值父节点代码
     * @param fjddm 父节点代码
     */
    public void setFjddm(String fjddm) {
        this.fjddm = fjddm;
    }

    /**
     * 赋值层次标识
     * @param ccbs 层次标识
     */
    public void setCcbs(String ccbs) {
        this.ccbs = ccbs;
    }


}
