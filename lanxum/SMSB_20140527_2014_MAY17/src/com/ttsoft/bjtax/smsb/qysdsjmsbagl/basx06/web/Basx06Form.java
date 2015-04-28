package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx06.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx06Form extends QysdsJmsbajlMainForm {
    /**
     * 
     */
    private static final long serialVersionUID = -3509186914402090888L;

    /*
     * 序号
     */
    private String xh;

    /*
     * 
     */
    private String ckbox;

    /*
     * 处理标示
     */
    private String clbs = "0";

    /*
     * 多条数据列表
     */
    private List resultList;

    /*
     * 往年结果集
     */
    private List resultList_wn;

    /*
     * 
     */
    private String checkedStr;

    /*
     * 环境保护、节能节水项目类型代码
     */
    private String jnjsxmlxdm;

    /*
     * 环境保护、节能节水项目类型
     */
    private String jnjsxmlx;

    /*
     * 环境保护、节能节水项目类型名称
     */
    private String jnjsxmlxmc;

    /*
     * 环境保护、节能节水项目类型名称
     */
    private List jnjsxmlxList;

    /*
     * 有关部门认定的项目证明资料名称
     */
    private String zlmcdm;

    /*
     * 取得第一笔收入的时间
     */
    private String dybsrnd;

    /*
     * 项目所得核算情况声明
     */
    private String sfyhsqksm = "1";

    /*
     * 主管税务机关要求报送的其他资料
     */
    private String qtzl;

    /*
     * 免征起始年度
     */
    private String mzqsnd;

    /*
     * 免征终止年度
     */
    private String mzzznd;

    /*
     * 减征起始年度
     */
    private String jzqsnd;

    /*
     * 减征终止年度
     */
    private String jzzznd;

    /*
     * 备案年度代码
     */
    private String banddm;

    public String getBanddm() {
        return banddm;
    }

    public void setBanddm(String banddm) {
        this.banddm = banddm;
    }

    public String getCheckedStr() {
        return checkedStr;
    }

    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    public String getCkbox() {
        return ckbox;
    }

    public void setCkbox(String ckbox) {
        this.ckbox = ckbox;
    }

    public String getClbs() {
        return clbs;
    }

    public void setClbs(String clbs) {
        this.clbs = clbs;
    }

    public String getDybsrnd() {
        return dybsrnd;
    }

    public void setDybsrnd(String dybsrnd) {
        this.dybsrnd = dybsrnd;
    }

    public String getJnjsxmlx() {
        return jnjsxmlx;
    }

    public void setJnjsxmlx(String jnjsxmlx) {
        this.jnjsxmlx = jnjsxmlx;
    }

    public String getJnjsxmlxdm() {
        return jnjsxmlxdm;
    }

    public void setJnjsxmlxdm(String jnjsxmlxdm) {
        this.jnjsxmlxdm = jnjsxmlxdm;
    }

    public List getJnjsxmlxList() {
        return jnjsxmlxList;
    }

    public void setJnjsxmlxList(List jnjsxmlxList) {
        this.jnjsxmlxList = jnjsxmlxList;
    }

    public String getJnjsxmlxmc() {
        return jnjsxmlxmc;
    }

    public void setJnjsxmlxmc(String jnjsxmlxmc) {
        this.jnjsxmlxmc = jnjsxmlxmc;
    }

    public String getJzqsnd() {
        return jzqsnd;
    }

    public void setJzqsnd(String jzqsnd) {
        this.jzqsnd = jzqsnd;
    }

    public String getJzzznd() {
        return jzzznd;
    }

    public void setJzzznd(String jzzznd) {
        this.jzzznd = jzzznd;
    }

    public String getMzqsnd() {
        return mzqsnd;
    }

    public void setMzqsnd(String mzqsnd) {
        this.mzqsnd = mzqsnd;
    }

    public String getMzzznd() {
        return mzzznd;
    }

    public void setMzzznd(String mzzznd) {
        this.mzzznd = mzzznd;
    }

    public String getQtzl() {
        return qtzl;
    }

    public void setQtzl(String qtzl) {
        this.qtzl = qtzl;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public List getResultList_wn() {
        return resultList_wn;
    }

    public void setResultList_wn(List resultList_wn) {
        this.resultList_wn = resultList_wn;
    }

    public String getSfyhsqksm() {
        return sfyhsqksm;
    }

    public void setSfyhsqksm(String sfyhsqksm) {
        this.sfyhsqksm = sfyhsqksm;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZlmcdm() {
        return zlmcdm;
    }

    public void setZlmcdm(String zlmcdm) {
        this.zlmcdm = zlmcdm;
    }


}