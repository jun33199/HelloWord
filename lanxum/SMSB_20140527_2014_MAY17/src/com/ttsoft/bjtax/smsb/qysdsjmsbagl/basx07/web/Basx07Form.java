package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx07.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx07Form extends QysdsJmsbajlMainForm {
	/**
     * 
     */
    private static final long serialVersionUID = -7273538125175999557L;
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
     * 
     */
    private String checkedStr;

    /*
     * 技术转让类型
     */
    private String jszrlxdm;

    /*
     * 技术转让类型
     */
    private String jszrlx;

    /*
     * 技术转让类型
     */
    private String jszrlxmc;
    
    /*
     * 技术转让类型
     */
    private List jszrlxList;

    /*
     * 经科学技术行政部门认定登记的技术转让合同
     */
    private String sfyjszrht = "0";

    /*
     * 技术转让合同认定登记证明和认定登记表
     */
    private String sfyrddjzm = "0";

    /*
     * 实际发生的技术性收入明细表
     */
    private String sfysrmxb = "0";

    /*
     * 项目所得核算情况声明
     */
    private String sfyhsqksm = "0";

    /*
     * 主管税务机关要求报送的其他资料
     */
    private String qtzl;

    /*
     * 取得技术转让所得
     */
    private String jszrsd;

    /*
     *备案年度代码 
     */
    private String banddm;
    
    /*
     *减免资料文档数据 
     */
    private String zlo;

    /*
     *经科学技术行政部门认定登记的技术转让合同名称 
     */
    private String jszrhtmc;
    
    /*
     *境内境外标识，0：境内，1：境外 
     */    
    private String jnjwbs = "0";
    
    private String bm;
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

    public String getJszrlx() {
        return jszrlx;
    }

    public void setJszrlx(String jszrlx) {
        this.jszrlx = jszrlx;
    }

    public String getJszrlxdm() {
        return jszrlxdm;
    }

    public void setJszrlxdm(String jszrlxdm) {
        this.jszrlxdm = jszrlxdm;
    }

    public String getJszrlxmc() {
        return jszrlxmc;
    }

    public void setJszrlxmc(String jszrlxmc) {
        this.jszrlxmc = jszrlxmc;
    }

    public String getJszrsd() {
        return jszrsd;
    }

    public void setJszrsd(String jszrsd) {
        this.jszrsd = jszrsd;
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

    public String getSfyhsqksm() {
        return sfyhsqksm;
    }

    public void setSfyhsqksm(String sfyhsqksm) {
        this.sfyhsqksm = sfyhsqksm;
    }

    public String getSfyjszrht() {
        return sfyjszrht;
    }

    public void setSfyjszrht(String sfyjszrht) {
        this.sfyjszrht = sfyjszrht;
    }

    public String getSfyrddjzm() {
        return sfyrddjzm;
    }

    public void setSfyrddjzm(String sfyrddjzm) {
        this.sfyrddjzm = sfyrddjzm;
    }

    public String getSfysrmxb() {
        return sfysrmxb;
    }

    public void setSfysrmxb(String sfysrmxb) {
        this.sfysrmxb = sfysrmxb;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZlo() {
        return zlo;
    }

    public void setZlo(String zlo) {
        this.zlo = zlo;
    }

    public List getJszrlxList() {
        return jszrlxList;
    }

    public void setJszrlxList(List jszrlxList) {
        this.jszrlxList = jszrlxList;
    }

    public String getJszrhtmc() {
        return jszrhtmc;
    }

    public void setJszrhtmc(String jszrhtmc) {
        this.jszrhtmc = jszrhtmc;
    }

    public String getJnjwbs() {
        return jnjwbs;
    }

    public void setJnjwbs(String jnjwbs) {
        this.jnjwbs = jnjwbs;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

}