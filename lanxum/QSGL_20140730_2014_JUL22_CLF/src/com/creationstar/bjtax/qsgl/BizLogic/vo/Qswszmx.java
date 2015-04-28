package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 契税完税证明细数据值对象
 */
public class Qswszmx implements Serializable {

    /**
     * 完税证号
     */
    public java.lang.String wszh;

    /**
     * 年度字别
     */
    public java.lang.String ndzb;

    /**
     * 票证种类代码
     */
    public java.lang.String pzzldm;

    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 身份证件号码
     */
    public java.lang.String zjhm;

    /**
     * 税种代码
     */
    public java.lang.String szdm;

    /**
     * 计税金额
     */
    public java.math.BigDecimal jsje;

    /**
     * 税率
     */
    public java.math.BigDecimal sl;

    /**
     * 已缴或扣除
     */
    public java.math.BigDecimal yjhkc;

    /**
     * 实纳税额
     */
    public java.math.BigDecimal sjse;

    /**
     * 税款所属开始日期
     */
    public java.sql.Timestamp skssksrq;

    /**
     * 税款所属结束日期
     */
    public java.sql.Timestamp skssjsrq;

    /**
     * 记帐标志
     */
    public java.lang.String jzbz;

    /**
     * 预算科目代码
     */
    public java.lang.String yskmdm;

    /**
     * 预算级次代码
     */
    public java.lang.String ysjcdm;

    /**
     * 年度
     */
    public java.lang.String nd;
    public String lrr;

    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;

    /**
     * 房地产权属转移面积
     */
    public java.math.BigDecimal qszymj;


    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;
    public String cjr;

    /**
     * 税种税目名称
     */
    public String szsmmc;
    /**
     * 税种名称
     */
    public String szmc;
    /**
     * 预算科目名称
     */
    public String yskmmc;
    /**
     * 预算级次名称
     */
    public String ysjcmc;

    /**
     * 获得完税证号
     */
    public String getWszh() {
        return wszh;
    }

    /**
     * 获得年度字别
     */
    public String getNdzb() {
        return ndzb;
    }

    /**
     * 获得票证种类代码
     */
    public String getPzzldm() {
        return pzzldm;
    }

    /**
     * 获得税种税目代码
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得身份证件号码
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 获得税种代码
     */
    public String getSzdm() {
        return szdm;
    }

    /**
     * 获得计税金额
     */
    public BigDecimal getJsje() {
        return jsje;
    }

    /**
     * 获得税率
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * 获得已缴或扣除
     */
    public BigDecimal getYjhkc() {
        return yjhkc;
    }

    /**
     * 获得实纳税额
     */
    public BigDecimal getSjse() {
        return sjse;
    }

    /**
     * 获得税款所属开始日期
     */
    public Timestamp getSkssksrq() {
        return skssksrq;
    }

    /**
     * 获得税款所属结束日期
     */
    public Timestamp getSkssjsrq() {
        return skssjsrq;
    }

    /**
     * 获得记帐标志
     */
    public String getJzbz() {
        return jzbz;
    }

    /**
     * 获得预算科目代码
     */
    public String getYskmdm() {
        return yskmdm;
    }

    /**
     * 获得预算级次代码
     */
    public String getYsjcdm() {
        return ysjcdm;
    }

    /**
     * 获得年度
     */
    public String getNd() {
        return nd;
    }

    /**
     * 获得创建时间
     */
    public Timestamp getCjrq() {
        return cjrq;
    }

    /**
     * 获得录入时间
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得房地产权属转移面积
     */
    public BigDecimal getQszymj() {
        return qszymj;
    }

    public String getLrr() {
        return lrr;
    }

    public String getCjr() {
        return cjr;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    public String getYsjcmc() {
        return ysjcmc;
    }

    public String getYskmmc() {
        return yskmmc;
    }

    /**
     * 赋值完税证号
     * @param wszh 完税证号
     */
    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    /**
     * 赋值年度字别
     * @param ndzb 年度字别
     */
    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    /**
     * 赋值票证种类代码
     * @param pzzldm 票证种类代码
     */
    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    /**
     * 赋值税种税目代码
     * @param szsmdm 税种税目代码
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值身份证件号码
     * @param zjhm 身份证件号码
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 赋值税种代码
     * @param szdm 税种代码
     */
    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    /**
     * 赋值计税金额
     * @param jsje 计税金额
     */
    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    /**
     * 赋值税率
     * @param sl 税率
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * 赋值已缴或扣除
     * @param yjhkc 已缴或扣除
     */
    public void setYjhkc(BigDecimal yjhkc) {
        this.yjhkc = yjhkc;
    }

    /**
     * 赋值实纳税额
     * @param sjse 实纳税额
     */
    public void setSjse(BigDecimal sjse) {
        this.sjse = sjse;
    }

    /**
     * 赋值税款所属开始日期
     * @param skssksrq 税款所属开始日期
     */
    public void setSkssksrq(Timestamp skssksrq) {
        this.skssksrq = skssksrq;
    }

    /**
     * 赋值税款所属结束日期
     * @param skssjsrq 税款所属结束日期
     */
    public void setSkssjsrq(Timestamp skssjsrq) {
        this.skssjsrq = skssjsrq;
    }

    /**
     * 赋值记帐标志
     * @param jzbz 记帐标志
     */
    public void setJzbz(String jzbz) {
        this.jzbz = jzbz;
    }

    /**
     * 赋值预算科目代码
     * @param yskmdm 预算科目代码
     */
    public void setYskmdm(String yskmdm) {
        this.yskmdm = yskmdm;
    }

    /**
     * 赋值预算级次代码
     * @param ysjcdm 预算级次代码
     */
    public void setYsjcdm(String ysjcdm) {
        this.ysjcdm = ysjcdm;
    }

    /**
     * 赋值年度
     * @param nd 年度
     */
    public void setNd(String nd) {
        this.nd = nd;
    }

    /**
     * 赋值创建时间
     * @param cjrq 创建时间
     */
    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    /**
     * 赋值录入时间
     * @param lrrq 录入时间
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值房地产权属转移面积
     * @param qszymj 房地产权属转移面积
     */
    public void setQszymj(BigDecimal qszymj) {
        this.qszymj = qszymj;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }

    public void setYsjcmc(String ysjcmc) {
        this.ysjcmc = ysjcmc;
    }

    public void setYskmmc(String yskmmc) {
        this.yskmmc = yskmmc;
    }


}
