package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 币种代码表值对象
 */
public class Bz implements Serializable {

    /**
     * 币种代码
     */
    public java.lang.String bzdm;

    /**
     * 币种名称
     */
    public java.lang.String bzmc;

    /**
     * 最后更新时间
     */
    public java.sql.Timestamp zhgxsj;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 显示顺序
     */
    public java.math.BigDecimal xssx;

    /**
     * 获得币种代码
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * 获得币种名称
     */
    public String getBzmc() {
        return bzmc;
    }

    /**
     * 获得最后更新时间
     */
    public Timestamp getZhgxsj() {
        return zhgxsj;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 获得显示顺序
     */
    public BigDecimal getXssx() {
        return xssx;
    }

    /**
     * 赋值币种代码
     * @param bzdm 币种代码
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * 赋值币种名称
     * @param bzmc 币种名称
     */
    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    /**
     * 赋值最后更新时间
     * @param zhgxsj 最后更新时间
     */
    public void setZhgxsj(Timestamp zhgxsj) {
        this.zhgxsj = zhgxsj;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * 赋值显示顺序
     * @param xssx 显示顺序
     */
    public void setXssx(BigDecimal xssx) {
        this.xssx = xssx;
    }


}
