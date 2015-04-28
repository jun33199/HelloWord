package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *批量信息导入主表
 */
public class Drzb implements Serializable {
    //导入批次号
    public String drpch;
    //序号
    public BigDecimal xh;
    //纳税人名称
    public String nsrmc;
    //纳税人计算机代码
    public String nsrjsjdm;
    //房地产项目名称
    public String fdcxmmc;
    //房地产地址
    public String fdcdz;
    //合同签订日期
    public Timestamp htqdrq;
    //成交价格
    public BigDecimal cjjg;
    //导入数据内容
    public String drsjnr;
    //状态标识
    public String ztbs;
    //导入操作人
    public String drczr;
    //导入时间
    public Timestamp drsj;
    //备注
    public String bz;
    //申报表号
    public String sbbh;
    //证件类型
    public String zjlx;
    //证件号码
    public String zjhm;

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZjlx() {
        return zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public String getBz() {
        return bz;
    }

    public BigDecimal getCjjg() {
        return cjjg;
    }

    public String getDrczr() {
        return drczr;
    }

    public String getDrpch() {
        return drpch;
    }

    public Timestamp getDrsj() {
        return drsj;
    }

    public String getDrsjnr() {
        return drsjnr;
    }

    public String getFdcdz() {
        return fdcdz;
    }

    public Timestamp getHtqdrq() {
        return htqdrq;
    }

    public String getNsrjsjdm() {
        return nsrjsjdm;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public BigDecimal getXh() {
        return xh;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    public void setDrczr(String drczr) {
        this.drczr = drczr;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setDrsj(Timestamp drsj) {
        this.drsj = drsj;
    }

    public void setDrsjnr(String drsjnr) {
        this.drsjnr = drsjnr;
    }

    public void setFdcdz(String fdcdz) {
        this.fdcdz = fdcdz;
    }

    public void setHtqdrq(Timestamp htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setNsrjsjdm(String nsrjsjdm) {
        this.nsrjsjdm = nsrjsjdm;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setXh(BigDecimal xh) {
        this.xh = xh;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }
}
