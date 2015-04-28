package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 契税完税证申报汇总数据值对象
 */
public class Qswszhz implements Serializable {

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 申报汇总单号
     */
    public java.lang.String sbhzdh;

    /**
     * 缴款凭证号
     */
    public java.lang.String jkpzh;

    /**
     * 汇总日期
     */
    public java.sql.Timestamp hzrq;

    /**
     * 实缴税额
     */
    public java.math.BigDecimal sjse;

    /**
     * 汇总开始日期
     */
    public java.sql.Timestamp hzksrq;

    /**
     * 汇总结束日期
     */
    public java.sql.Timestamp hzjsrq;

    /**
     * 处理标记代码
     */
    public java.lang.String clbjdm;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 汇总方式代码
     */
    public java.lang.String hzfs;

    /**
     * 年度
     */
    public java.lang.String nd;

    /**
     * 录入时间
     */
    public java.sql.Timestamp lrrq;

    /**
     * 创建时间
     */
    public java.sql.Timestamp cjrq;

    /**
     * 录入人代码
     */
    public java.lang.String lrr;

    /**
     * 创建人
     */
    public java.lang.String cjr;


    /**
     * 汇总方式名称
     */
    public java.lang.String hzfsmc;

    /**
     * 缴税方式名称
     */
    public java.lang.String jsfsmc;

    /**
     * 征收点代码
     */
    public java.lang.String zsddm;

    /**
     * 征收点名称
     */
    public java.lang.String zsdmc;

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 获得申报汇总单号
     */
    public String getSbhzdh() {
        return sbhzdh;
    }

    /**
     * 获得缴款凭证号
     */
    public String getJkpzh() {
        return jkpzh;
    }

    /**
     * 获得汇总日期
     */
    public Timestamp getHzrq() {
        return hzrq;
    }

    /**
     * 获得实缴税额
     */
    public BigDecimal getSjse() {
        return sjse;
    }

    /**
     * 获得汇总开始日期
     */
    public Timestamp getHzksrq() {
        return hzksrq;
    }

    /**
     * 获得汇总结束日期
     */
    public Timestamp getHzjsrq() {
        return hzjsrq;
    }

    /**
     * 获得处理标记代码
     */
    public String getClbjdm() {
        return clbjdm;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得录入人代码
     */
    public String getLrr() {
        return lrr;
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

    public String getCjr() {
        return cjr;
    }

    public String getHzfsmc() {
        return hzfsmc;
    }

    public String getHzfs() {
        return hzfs;
    }

    public String getZsddm() {
        return zsddm;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getJsfsmc() {
        return jsfsmc;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * 赋值申报汇总单号
     * @param sbhzdh 申报汇总单号
     */
    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    /**
     * 赋值缴款凭证号
     * @param jkpzh 缴款凭证号
     */
    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    /**
     * 赋值汇总日期
     * @param hzrq 汇总日期
     */
    public void setHzrq(Timestamp hzrq) {
        this.hzrq = hzrq;
    }

    /**
     * 赋值实缴税额
     * @param sjse 实缴税额
     */
    public void setSjse(BigDecimal sjse) {
        this.sjse = sjse;
    }

    /**
     * 赋值汇总开始日期
     * @param hzksrq 汇总开始日期
     */
    public void setHzksrq(Timestamp hzksrq) {
        this.hzksrq = hzksrq;
    }

    /**
     * 赋值汇总结束日期
     * @param hzjsrq 汇总结束日期
     */
    public void setHzjsrq(Timestamp hzjsrq) {
        this.hzjsrq = hzjsrq;
    }

    /**
     * 赋值处理标记代码
     * @param clbjdm 处理标记代码
     */
    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值录入人代码
     * @param lrr 录入人代码
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
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

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
    }

    public void setHzfs(String hzfs) {
        this.hzfs = hzfs;
    }

    public void setZsddm(String zsddm) {
        this.zsddm = zsddm;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setJsfsmc(String jsfsmc) {
        this.jsfsmc = jsfsmc;
    }


}
