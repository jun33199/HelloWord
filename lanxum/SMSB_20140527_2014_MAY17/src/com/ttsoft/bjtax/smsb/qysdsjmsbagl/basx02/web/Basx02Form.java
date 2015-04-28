package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx02.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx02Form extends QysdsJmsbajlMainForm {
    /**
     * 
     */
    private static final long serialVersionUID = -826435697733749923L;

    /*
     * 序号
     */
    private String xh;

    /*
     * 
     */
    private String ckbox;

    /*
     * 研发费用领域
     */
    private String yffyly;

    /*
     * 研发费用领域代码
     */
    private String yffylydm;
    /*
     * 研发费用领域代码
     */
    private String YFFYLYDM;

    /*
     * 研发费用领域名称
     */
    private String yffylymc;
    
    /*
     * 研发费用领域列表
     */
    private List yffylyList;

    /*
     * 是否有计划书和预算,0:是,1:否
     */
    private String sfyjhys = "1";

    /*
     * 是否有编制情况和专业人员名单,0:是,1:否
     */
    private String sfybzry = "1";

    /*
     * 是否有研发费用情况归集表,0:是,1:否
     */
    private String sfyyffyqk = "1";

    /*
     * 是否有决议文件,0:是,1:否
     */
    private String sfyjywj = "1";

    /*
     * 是否有合同协议,0:是,1:否
     */
    private String sfyhtxy = "1";

    /*
     * 是否有研究成果报告,0:是,1:否
     */
    private String sfyyjcg = "1";

    /*
     * 中介机构鉴证报告,0:是,1:否
     */
    private String sfyzjjzbg = "1";

    /*
     * 当期扣除金额
     */
    private String dqkcje;

    /*
     * 形成无形资产金额
     */
    private String wxzcje;

    /*
     * 加计扣除额
     */
    private String jjkcje;

    /*
     * 其他资料
     */
    private String qtzl;

    /*
     * 项目名称
     */
    private String xmmc;

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
     * 证书有效开始日期
     */
    private String zsyxksrq;

    /*
     * 证书有效截止日期
     */
    private String zsyxjzrq;

    /*
     * 备案年度代码
     */
    private String banddm;

    public String getClbs() {
        return clbs;
    }

    public void setClbs(String clbs) {
        this.clbs = clbs;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getCkbox() {
        return ckbox;
    }

    public void setCkbox(String ckbox) {
        this.ckbox = ckbox;
    }

    public String getCheckedStr() {
        return checkedStr;
    }

    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDqkcje() {
        return dqkcje;
    }

    public void setDqkcje(String dqkcje) {
        this.dqkcje = dqkcje;
    }

    public String getJjkcje() {
        return jjkcje;
    }

    public void setJjkcje(String jjkcje) {
        this.jjkcje = jjkcje;
    }

    public String getQtzl() {
        return qtzl;
    }

    public void setQtzl(String qtzl) {
        this.qtzl = qtzl;
    }

    public String getSfybzry() {
        return sfybzry;
    }

    public void setSfybzry(String sfybzry) {
        this.sfybzry = sfybzry;
    }

    public String getSfyhtxy() {
        return sfyhtxy;
    }

    public void setSfyhtxy(String sfyhtxy) {
        this.sfyhtxy = sfyhtxy;
    }

    public String getSfyjhys() {
        return sfyjhys;
    }

    public void setSfyjhys(String sfyjhys) {
        this.sfyjhys = sfyjhys;
    }

    public String getSfyjywj() {
        return sfyjywj;
    }

    public void setSfyjywj(String sfyjywj) {
        this.sfyjywj = sfyjywj;
    }

    public String getSfyyffyqk() {
        return sfyyffyqk;
    }

    public void setSfyyffyqk(String sfyyffyqk) {
        this.sfyyffyqk = sfyyffyqk;
    }

    public String getSfyyjcg() {
        return sfyyjcg;
    }

    public void setSfyyjcg(String sfyyjcg) {
        this.sfyyjcg = sfyyjcg;
    }

    public String getSfyzjjzbg() {
        return sfyzjjzbg;
    }

    public void setSfyzjjzbg(String sfyzjjzbg) {
        this.sfyzjjzbg = sfyzjjzbg;
    }

    public String getWxzcje() {
        return wxzcje;
    }

    public void setWxzcje(String wxzcje) {
        this.wxzcje = wxzcje;
    }

    public String getYffyly() {
        return yffyly;
    }

    public void setYffyly(String yffyly) {
        this.yffyly = yffyly;
    }

    public String getYffylydm() {
        return yffylydm;
    }

    public void setYffylydm(String yffylydm) {
        this.yffylydm = yffylydm;
    }

    public String getYffylymc() {
        return yffylymc;
    }

    public void setYffylymc(String yffylymc) {
        this.yffylymc = yffylymc;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getZsyxjzrq() {
        return zsyxjzrq;
    }

    public void setZsyxjzrq(String zsyxjzrq) {
        this.zsyxjzrq = zsyxjzrq;
    }

    public String getZsyxksrq() {
        return zsyxksrq;
    }

    public void setZsyxksrq(String zsyxksrq) {
        this.zsyxksrq = zsyxksrq;
    }

    public String getBanddm() {
        return banddm;
    }

    public void setBanddm(String banddm) {
        this.banddm = banddm;
    }

    public List getYffylyList() {
        return yffylyList;
    }

    public void setYffylyList(List yffylyList) {
        this.yffylyList = yffylyList;
    }

    public String getYFFYLYDM() {
        return YFFYLYDM;
    }

    public void setYFFYLYDM(String yffylydm) {
        YFFYLYDM = yffylydm;
    }


}