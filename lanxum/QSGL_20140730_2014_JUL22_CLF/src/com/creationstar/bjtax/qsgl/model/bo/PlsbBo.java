package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PlsbBo implements Serializable {

    //批量提供者信息
    private String tgzmc;
    private String tgzjsjdm;
    private String drbs;

    //缴款方式代码
    private String jsfsdm;

    //批次信息
    private String drpch;

    //导入时间
    private String drsj;

    //序号
    private String xh;

    //纳税人名称
    private String nsrmc;

    //受理标志位 all全部 checked,received
    private String sl = "";

    //导入主表信息drzb
    private Drzb drzb;

    //导入批次信息
    private Drpcinfo drpcInfo;

    // 删除信息list
    private ArrayList list;


    public String getNsrmc() {
        return nsrmc;
    }

    public String getDrbs() {
        return drbs;
    }

    public String getXh() {
        return xh;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getTgzmc() {
        return tgzmc;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getDrsj() {
        return drsj;
    }

    public Drpcinfo getDrpcInfo() {
        return drpcInfo;
    }

    public Drzb getDrzb() {
        return drzb;
    }

    public ArrayList getList() {
        return list;
    }

    public String getSl() {
        return sl;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }


    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setDrbs(String drbs) {
        this.drbs = drbs;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setTgzmc(String tgzmc) {
        this.tgzmc = tgzmc;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setDrsj(String drsj) {
        this.drsj = drsj;
    }

    public void setDrpcInfo(Drpcinfo drpcInfo) {
        this.drpcInfo = drpcInfo;
    }

    public void setDrzb(Drzb drzb) {
        this.drzb = drzb;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    private void jbInit() throws Exception {
    }

}
