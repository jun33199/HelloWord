package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx12.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx12Form extends QysdsJmsbajlMainForm {
    /**
     * 
     */
    private static final long serialVersionUID = -676461425674527814L;

    /*
     * 序号
     */
    private String xh;

    /*
     * 是否属于国家发展和改革委员会、工业信息化部、财政部、海关总署、国家税务总局下发的集成电路线宽小于0.25um的集成电路生产企业名单或投资额超过80亿元人民币的证明材料
     */
    private String sfsyjcdlqy = "0";

    /*
     * 获利年度声明：获利年度
     */
    private String hlnd;

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
     * 预计减免税额
     */
    private String yjjmse;

    /*
     * 处理标示
     */
    private String clbs = "0";

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

    public String getClbs() {
        return clbs;
    }

    public void setClbs(String clbs) {
        this.clbs = clbs;
    }

    public String getHlnd() {
        return hlnd;
    }

    public void setHlnd(String hlnd) {
        this.hlnd = hlnd;
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

    public String getSfsyjcdlqy() {
        return sfsyjcdlqy;
    }

    public void setSfsyjcdlqy(String sfsyjcdlqy) {
        this.sfsyjcdlqy = sfsyjcdlqy;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getYjjmse() {
        return yjjmse;
    }

    public void setYjjmse(String yjjmse) {
        this.yjjmse = yjjmse;
    }

}