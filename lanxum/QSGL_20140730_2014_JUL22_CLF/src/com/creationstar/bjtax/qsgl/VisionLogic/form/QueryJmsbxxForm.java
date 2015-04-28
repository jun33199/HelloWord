package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;

/**
 *
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
public class QueryJmsbxxForm extends QueryBaseForm {
    /**
     * JmsbblBo作为查询条件时，指明从那个查询页面而来
     * 0：减免申报查询
     */
    private int fromPage;


    /**
     * 缴款书号
     */
    private String jksh;

    /**
     * 纳税人类型列表
     */
    private ArrayList nsrlxList = new ArrayList();


    /**
     * 完税证号
     */
    private String wszh;

    /**
     * 身份证件类型下拉框显示信息
     */
    private ArrayList sfzjlxList = new ArrayList();

    /**
     * 身份证件类型代码
     */
    private String zjlxdm;

    /**
     * 房地产项目名称
     */
    private String fdcxmmc;

    /**
     * 土地房屋坐落地址
     */
    private String tdfwzldz;

    /**
     * 备注
     */
    private String bz;

    /**
     * 状态标识
     */
    private String ztbs;

    /**
     * 假的状态标识,查询条件用,现在审核收款查询页面使用
     */
    private String fztbs;

    /**
     * 减免税标识
     */
    private String jmsbs;

    /**
     * 身份证件号码
     */
    private String sfzjhm;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 批次号
     */
    private String pch;

    /**
     * 核定通知书号
     */
    private String hdtzsh;

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setJksh(String jksh) {
        this.jksh = jksh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setJmsbs(String jmsbs) {
        this.jmsbs = jmsbs;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setFztbs(String fztbs) {
        this.fztbs = fztbs;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public String getJksh() {
        return jksh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getBz() {
        return bz;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }

    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getJmsbs() {
        return jmsbs;
    }

    public String getSbrq() {
        return sbrq;
    }

    public String getFztbs() {
        return fztbs;
    }

    public int getFromPage() {
        return fromPage;
    }

    public String getPch() {
        return pch;
    }

    /**
     * 前台页面的查询条件
     * @return Object
     */
    public Object getData() {
        JmsbblBo bo = new JmsbblBo();
        bo.setSbbh(this.getSbbh());
        //bo.setWszh(this.getWszh());
        bo.setJksh(this.getJksh());
        bo.setNsrlxdm(this.getNsrlx());
        bo.setNsrmc(this.getNsrmc());
        bo.setJsjdm(this.getJsjdm());
        bo.setSfzjhm(this.getSfzjhm());
        bo.setSfzjlx(this.zjlxdm);
        bo.setZtbs(this.ztbs);
        bo.setJmsbs(this.jmsbs);
        bo.setSbrq(this.sbrq);
        bo.setFromPage(this.fromPage);
        bo.setFztbs(this.fztbs);
        bo.setDrpch(this.pch);
        bo.setHdtzsh(this.hdtzsh);
        return bo;
    }

    public String getHdtzsh() {
        return hdtzsh;
    }

    public void setHdtzsh(String hdtzsh) {
        this.hdtzsh = hdtzsh;
    }
}
