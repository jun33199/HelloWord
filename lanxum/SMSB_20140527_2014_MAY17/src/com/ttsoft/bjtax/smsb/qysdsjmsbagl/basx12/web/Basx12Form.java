package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx12.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx12Form extends QysdsJmsbajlMainForm {
    /**
     * 
     */
    private static final long serialVersionUID = -676461425674527814L;

    /*
     * ���
     */
    private String xh;

    /*
     * �Ƿ����ڹ��ҷ�չ�͸ĸ�ίԱ�ᡢ��ҵ��Ϣ���������������������𡢹���˰���ܾ��·��ļ��ɵ�·�߿�С��0.25um�ļ��ɵ�·������ҵ������Ͷ�ʶ��80��Ԫ����ҵ�֤������
     */
    private String sfsyjcdlqy = "0";

    /*
     * ��������������������
     */
    private String hlnd;

    /*
     * ����˰�����Ҫ���͵���������
     */
    private String qtzl;

    /*
     * ������ʼ���
     */
    private String mzqsnd;

    /*
     * ������ֹ���
     */
    private String mzzznd;

    /*
     * ������ʼ���
     */
    private String jzqsnd;

    /*
     * ������ֹ���
     */
    private String jzzznd;

    /*
     * Ԥ�Ƽ���˰��
     */
    private String yjjmse;

    /*
     * �����ʾ
     */
    private String clbs = "0";

    /*
     * ������ȴ���
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