package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: 北京地税核心征管系统-上门申报-申报不一致处理页面对象</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class SbbyzclForm extends BaseForm {

    /**
     * 前台提示信息
     */
    private String opeMessage ="";

    /**
     * 查询参数：计算机代码
     */
    private String queryJsjdm;

    /**
     * 查询参数：年度
     */
    private String queryNd;

    /**
     * 查询参数：开始日期
     */
    private String queryKsrq;

    /**
     * 查询参数：结束日期
     */
    private String queryJsrq;

    /**
     * 输出参数：纳税人名称
     */
    private String nsrmc;

    /**
     * 输出参数：纳税人状态
     */
    private String nsrzt;

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     * 税务机关组织机构名称
     */
    private String swjgzzjgmc;

    /**
     * 主管税务机关组织机构代码
     */
    private String zgswjgzzjgdm;

    /**
     * 主管税务机关组织机构名称
     */
    private String zgswjgzzjgmc;

    /**
     * 日期
     */
    private String rq;

    /**
     * 操作人
     */
    private String czr;

    /**
     * 数据集合
     * 内存对象为SingleSbInfo.java
     */
    private ArrayList dataList = new ArrayList();

    /**
     * 数据明细集合
     * 内存明细为Sbjkzb.java
     */
    private ArrayList datamxList = new ArrayList();

    /**
     * 申报编号
     */
    private String parSbbh;

    /**
     * 计算机代码
     */
    private String parJsjdm;

    private String opeFlag = "0"; //0-初始化操作，1-已查询基本信息，2-已查询申报信息

    // 增加录入欠税确认期、打印申报表功能 added by zhangyj 20070720
    /**
     * 欠税确认期
     */
    private String qsqrq;    

    /**
     * 合计金额
     */
    private BigDecimal hj;

    /**
     * 主表
     */
    private Sbjkzb zb;

    /**
     * 主表明细
     */
    private List mxList = new ArrayList();

    //end
    
    public String getCzr() {
        return czr;
    }

    public ArrayList getDataList() {
        return dataList;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getQueryJsjdm() {
        return queryJsjdm;
    }

    public String getQueryNd() {
        return queryNd;
    }

    public String getRq() {
        return rq;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public ArrayList getDatamxList() {
        return datamxList;
    }

    public String getZgswjgzzjgdm() {
        return zgswjgzzjgdm;
    }

    public String getZgswjgzzjgmc() {
        return zgswjgzzjgmc;
    }

    public String getNsrzt() {
        return nsrzt;
    }

    public String getQueryJsrq() {
        return queryJsrq;
    }

    public String getQueryKsrq() {
        return queryKsrq;
    }

    public String getParSbbh() {
        return parSbbh;
    }

    public String getParJsjdm() {
        return parJsjdm;
    }

    public String getOpeFlag() {
        return opeFlag;
    }

    public String getOpeMessage() {

        return opeMessage;
    }

    public String getQsqrq() {

        return qsqrq;
    } 

    public BigDecimal getHj() {
        return hj;
    }

    public Sbjkzb getZb() {
        return zb;
    }
    
    public List getMxList() {
        return mxList;
    }    

    public String getSjsehjdx()
    {
        return Currency.convert(this.hj);
    }    
    
    public void setCzr(String czr) {
        this.czr = czr;
    }

    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setQueryJsjdm(String queryJsjdm) {
        this.queryJsjdm = queryJsjdm;
    }

    public void setQueryNd(String queryNd) {
        this.queryNd = queryNd;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setDatamxList(ArrayList datamxList) {
        this.datamxList = datamxList;
    }

    public void setZgswjgzzjgdm(String zgswjgzzjgdm) {
        this.zgswjgzzjgdm = zgswjgzzjgdm;
    }

    public void setZgswjgzzjgmc(String zgswjgzzjgmc) {
        this.zgswjgzzjgmc = zgswjgzzjgmc;
    }

    public void setNsrzt(String nsrzt) {
        this.nsrzt = nsrzt;
    }

    public void setQueryJsrq(String queryJsrq) {
        this.queryJsrq = queryJsrq;
    }

    public void setQueryKsrq(String queryKsrq) {
        this.queryKsrq = queryKsrq;
    }

    public void setParSbbh(String parSbbh) {
        this.parSbbh = parSbbh;
    }

    public void setParJsjdm(String parJsjdm) {
        this.parJsjdm = parJsjdm;
    }

    public void setOpeFlag(String opeFlag) {
        this.opeFlag = opeFlag;
    }

    public void setOpeMessage(String opeMessage) {

        this.opeMessage = opeMessage;
    }
    
    public void setQsqrq(String qsqrq) {

        this.qsqrq = qsqrq;
    }

    public void setHj(BigDecimal hj) {
        this.hj = hj;
    }

    public void setZb(Sbjkzb zb) {
        this.zb = zb;
    }
    
    public void setMxList(List mxList) {
        this.mxList = mxList;
    }  
      
}
