package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx07.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx07Form extends QysdsJmsbajlMainForm {
	/**
     * 
     */
    private static final long serialVersionUID = -7273538125175999557L;
    /*
     * ���
     */
    private String xh;

    /*
     * 
     */
    private String ckbox;

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
     * ����ת������
     */
    private String jszrlxdm;

    /*
     * ����ת������
     */
    private String jszrlx;

    /*
     * ����ת������
     */
    private String jszrlxmc;
    
    /*
     * ����ת������
     */
    private List jszrlxList;

    /*
     * ����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ
     */
    private String sfyjszrht = "0";

    /*
     * ����ת�ú�ͬ�϶��Ǽ�֤�����϶��ǼǱ�
     */
    private String sfyrddjzm = "0";

    /*
     * ʵ�ʷ����ļ�����������ϸ��
     */
    private String sfysrmxb = "0";

    /*
     * ��Ŀ���ú����������
     */
    private String sfyhsqksm = "0";

    /*
     * ����˰�����Ҫ���͵���������
     */
    private String qtzl;

    /*
     * ȡ�ü���ת������
     */
    private String jszrsd;

    /*
     *������ȴ��� 
     */
    private String banddm;
    
    /*
     *���������ĵ����� 
     */
    private String zlo;

    /*
     *����ѧ�������������϶��Ǽǵļ���ת�ú�ͬ���� 
     */
    private String jszrhtmc;
    
    /*
     *���ھ����ʶ��0�����ڣ�1������ 
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