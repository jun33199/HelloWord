package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx02.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx02Form extends QysdsJmsbajlMainForm {
    /**
     * 
     */
    private static final long serialVersionUID = -826435697733749923L;

    /*
     * ���
     */
    private String xh;

    /*
     * 
     */
    private String ckbox;

    /*
     * �з���������
     */
    private String yffyly;

    /*
     * �з������������
     */
    private String yffylydm;
    /*
     * �з������������
     */
    private String YFFYLYDM;

    /*
     * �з�������������
     */
    private String yffylymc;
    
    /*
     * �з����������б�
     */
    private List yffylyList;

    /*
     * �Ƿ��мƻ����Ԥ��,0:��,1:��
     */
    private String sfyjhys = "1";

    /*
     * �Ƿ��б��������רҵ��Ա����,0:��,1:��
     */
    private String sfybzry = "1";

    /*
     * �Ƿ����з���������鼯��,0:��,1:��
     */
    private String sfyyffyqk = "1";

    /*
     * �Ƿ��о����ļ�,0:��,1:��
     */
    private String sfyjywj = "1";

    /*
     * �Ƿ��к�ͬЭ��,0:��,1:��
     */
    private String sfyhtxy = "1";

    /*
     * �Ƿ����о��ɹ�����,0:��,1:��
     */
    private String sfyyjcg = "1";

    /*
     * �н������֤����,0:��,1:��
     */
    private String sfyzjjzbg = "1";

    /*
     * ���ڿ۳����
     */
    private String dqkcje;

    /*
     * �γ������ʲ����
     */
    private String wxzcje;

    /*
     * �Ӽƿ۳���
     */
    private String jjkcje;

    /*
     * ��������
     */
    private String qtzl;

    /*
     * ��Ŀ����
     */
    private String xmmc;

    /*
     * �����ʾ
     */
    private String clbs = "0";

    /*
     * ���������б�
     */
    private List resultList;

    /*
     * 
     */
    private String checkedStr;

    /*
     * ֤����Ч��ʼ����
     */
    private String zsyxksrq;

    /*
     * ֤����Ч��ֹ����
     */
    private String zsyxjzrq;

    /*
     * ������ȴ���
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