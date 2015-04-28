package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 汇率代码表值对象
 */
public class Wbhs implements Serializable {

    /**
     * 币种
     */
    public java.lang.String bzdm;

    /**
     * 序号
     */
    public java.math.BigDecimal xh;

    /**
     * 金额
     */
    public java.math.BigDecimal je;

    /**
     * 外汇牌价
     */
    public java.math.BigDecimal whpj;

    /**
     * 折合人民币
     */
    public java.math.BigDecimal zhrmb;

    /**
     * 币种名称
     */
    public java.lang.String bzmc;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 月份
     */
    public java.lang.String yf;

    /**
     * 获得币种
     */
    public String getBzdm() {
        return bzdm;
    }

    /**
     * 获得序号
     */
    public BigDecimal getXh() {
        return xh;
    }

    /**
     * 获得金额
     */
    public BigDecimal getJe() {
        return je;
    }

    /**
     * 获得外汇牌价
     */
    public BigDecimal getWhpj() {
        return whpj;
    }

    /**
     * 获得折合人民币
     */
    public BigDecimal getZhrmb() {
        return zhrmb;
    }

    /**
     * 获得币种名称
     */
    public String getBzmc() {
        return bzmc;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得月份
     */
    public String getYf() {
        return yf;
    }

    /**
     * 赋值币种
     * @param bzdm 币种
     */
    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    /**
     * 赋值序号
     * @param xh 序号
     */
    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    /**
     * 赋值金额
     * @param je 金额
     */
    public void setJe(BigDecimal je) {
        this.je = je;
    }

    /**
     * 赋值外汇牌价
     * @param whpj 外汇牌价
     */
    public void setWhpj(BigDecimal whpj) {
        this.whpj = whpj;
    }

    /**
     * 赋值折合人民币
     * @param zhrmb 折合人民币
     */
    public void setZhrmb(BigDecimal zhrmb) {
        this.zhrmb = zhrmb;
    }

    /**
     * 赋值币种名称
     * @param bzmc 币种名称
     */
    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值月份
     * @param yf 月份
     */
    public void setYf(String yf) {
        this.yf = yf;
    }


}
