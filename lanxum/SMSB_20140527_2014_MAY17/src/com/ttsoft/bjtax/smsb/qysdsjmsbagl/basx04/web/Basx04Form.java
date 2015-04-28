package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx04.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx04Form extends QysdsJmsbajlMainForm {
	/**
     * 
     */
    private static final long serialVersionUID = -4161613909800877797L;

    /*
     * 序号
     */
    private String xh;

    /*
     * 公共基础设施项目类型
     */
    private String ggjcssxmlx;

    /*
     * 公共基础设施项目类型名称
     */
    private String ggjcssxmlxmc;

    /*
     * 公共基础设施项目类型代码
     */
    private String ggjcssxmlxdm;
    /*
     * 公共基础设施项目类型代码
     */
    private List ggjcssxmlxList;    

    /*
     * 文件名称
     */
    private String wjmc;

    /*
     * 文号
     */
    private String wh;

    /*
     * 取得第一笔收入的相关证明资料名称
     */
    private String dybzlmc;

    /*
     * 取得第一笔生产经营收入的时间
     */
    private String dybrq;

    /*
     * 项目所得核算情况声明 1:是,0:否
     */
    private String sfyhssm = "1";

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

    public String getDybrq() {
        return dybrq;
    }

    public void setDybrq(String dybrq) {
        this.dybrq = dybrq;
    }

    public String getDybzlmc() {
        return dybzlmc;
    }

    public void setDybzlmc(String dybzlmc) {
        this.dybzlmc = dybzlmc;
    }

    public String getGgjcssxmlx() {
        return ggjcssxmlx;
    }

    public void setGgjcssxmlx(String ggjcssxmlx) {
        this.ggjcssxmlx = ggjcssxmlx;
    }

    public String getGgjcssxmlxdm() {
        return ggjcssxmlxdm;
    }

    public void setGgjcssxmlxdm(String ggjcssxmlxdm) {
        this.ggjcssxmlxdm = ggjcssxmlxdm;
    }

    public List getGgjcssxmlxList() {
        return ggjcssxmlxList;
    }

    public void setGgjcssxmlxList(List ggjcssxmlxList) {
        this.ggjcssxmlxList = ggjcssxmlxList;
    }

    public String getGgjcssxmlxmc() {
        return ggjcssxmlxmc;
    }

    public void setGgjcssxmlxmc(String ggjcssxmlxmc) {
        this.ggjcssxmlxmc = ggjcssxmlxmc;
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

    public String getSfyhssm() {
        return sfyhssm;
    }

    public void setSfyhssm(String sfyhssm) {
        this.sfyhssm = sfyhssm;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }	

	
	


}