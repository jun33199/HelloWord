package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *导入批次信息表
 */
public class Drpcinfo implements Serializable {
    //导入批次号
    public String drpch;
    //导入笔数
    public BigDecimal drbs;
    //数据提供者类型
    public String tgzlx;
    //数据提供者名称
    public String tgzmc;
    //数据提供者国籍代码
    public String tgzgjdm;
    //数据提供者国籍名称
    public String tgzgjmc;
    //数据提供者身份证件类型
    public String tgzsfzjlx;
    //数据提供者身份证件号码
    public String tgzsfzjhm;
    //数据提供者计算机代码
    public String tgzjsjdm;
    //提交时间
    public java.sql.Timestamp tjsj;
    //导入时间
    public java.sql.Timestamp drsj;
    //缴税方式代码
    public String jsfsdm;
    //缴税方式名称
    public String jsfmc;

    public BigDecimal getDrbs() {
        return drbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public Timestamp getDrsj() {
        return drsj;
    }

    public String getTgzgjdm() {
        return tgzgjdm;
    }

    public String getTgzgjmc() {
        return tgzgjmc;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getTgzlx() {
        return tgzlx;
    }

    public String getTgzmc() {
        return tgzmc;
    }

    public String getTgzsfzjhm() {
        return tgzsfzjhm;
    }

    public String getTgzsfzjlx() {
        return tgzsfzjlx;
    }

    public Timestamp getTjsj() {
        return tjsj;
    }

    public String getJsfmc() {
        return jsfmc;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public void setDrbs(BigDecimal drbs) {
        this.drbs = drbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setDrsj(Timestamp drsj) {
        this.drsj = drsj;
    }

    public void setTgzgjdm(String tgzgjdm) {
        this.tgzgjdm = tgzgjdm;
    }

    public void setTgzgjmc(String tgzgjmc) {
        this.tgzgjmc = tgzgjmc;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setTgzlx(String tgzlx) {
        this.tgzlx = tgzlx;
    }

    public void setTgzmc(String tgzmc) {
        this.tgzmc = tgzmc;
    }

    public void setTgzsfzjhm(String tgzsfzjhm) {
        this.tgzsfzjhm = tgzsfzjhm;
    }

    public void setTgzsfzjlx(String tgzsfzjlx) {
        this.tgzsfzjlx = tgzsfzjlx;
    }

    public void setTjsj(Timestamp tjsj) {
        this.tjsj = tjsj;
    }

    public void setJsfmc(String jsfmc) {
        this.jsfmc = jsfmc;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }
}
