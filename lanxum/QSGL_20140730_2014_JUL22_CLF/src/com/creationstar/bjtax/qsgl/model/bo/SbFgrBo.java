package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

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
 *  @author ����
 * @version 1.0
 */
public class SbFgrBo implements Serializable {


    /**
     * �걨����
     */
    public Timestamp sbrq;

    /**
     * ��¼��ʾ
     */
    public boolean bl;

    /**
     * ��˰�걨���
     */
    public String sbbh;
    /**
     * 20081125,modify by fujx,���ӽ�ίҵ����
     */
    private String jwywbh;

    /**
     * 20081125,modify by fujx,���Ӻ�ͬ���
     */
    private String htbh;


    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbbh() {
        return sbbh;
    }

    /**
     * ��˰������
     */
    public String nsrmc;
    public void setNsrmc(String s) {
        this.nsrmc = s;
    }

    public String getNsrmc() {
        return nsrmc;
    }


    /**
     * �ɿʽ
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm;
    public String jkfsmc;
    /**
     * �����걨���
     */
//    public String jmsbbh;

    /**
     * �������ط����������
     */
    public String fcjslh;

    /**
     * ��ϵ�绰
     */
    public String lxdh;


    /**
     * ����˰���
     */
    public BigDecimal jmsje;

    /**
     * �˶�֪ͨ���ֺ�
     */
    public String hdtzszh;

    /**
     * �������� ���ݺ˶�֪ͨ���ȡ
     */
    public String jmlydm;

    /**
     * ��˰���������
     */
    public String nsjsjdm;
    /**
     * ˰�������ʹ���
     */
    public String nsrlxdm;

    /**
     * ˰������������
     */
    public String nsrlxmc;

    /**
     * ��������
     */
    public String khyhdm;
    public String khyhmc;
    /**
     * �����˺�
     */
    public String yhzh;
    /**
     * ��ϵ������
     */
    public String lxrxm;
    /**
     * ��ע
     */
    public String bz;
    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList = new ArrayList();
    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    /**
     * ����ס��������Ϣ����
     * @return  Jsblgyzf
     */
    public Jsblgyzf jsblgyzf = null;
    public void setJsblgyzf(Jsblgyzf jsblgyzf) {
        this.jsblgyzf = jsblgyzf;
    }

    public Jsblgyzf getJsblgyzf() {
        return jsblgyzf;
    }

    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList = new ArrayList();
    public void setJmList(ArrayList jmList) {
        this.jmList = jmList;
    }

    public ArrayList getJmList() {
        return jmList;
    }

    /**
     * ���ݻ�����Ϣ����
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = null;
    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }


    /**
     * ��ñ�ע
     * @return String
     */
    public String getBz() {
        return bz;
    }

    /**
     * ��÷������ط����������
     * @return String
     */
    public String getFcjslh() {
        return fcjslh;
    }

    /**
     * ��ú˶�֪ͨ���ֺ�
     * @return String
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * ��ýɿʽ
     * @return ArrayList
     */
    public ArrayList getJkfsList() {
        return jkfsList;
    }

    /**
     *  ��ü����걨���
     * @return String
     */
//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    /**
     * ��ü���˰���
     * @return String
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    /**
     * �����ϵ�绰
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * �����ϵ������
     * @return String
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * �����˰���������
     * @return String
     */
    public String getNsjsjdm() {
        return nsjsjdm;
    }

    /**
     * ��������˺�
     * @return String
     */
    public String getYhzh() {
        return yhzh;
    }

    /**
     * ���ñ�ע
     * @param bz String
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ���÷������ط����������
     * @param fcjslh String
     */
    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    /**
     * ���ú˶�֪ͨ���ֺ�
     * @param hdtzszh String
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * ���ýɿʽ����
     * @param String jkfsdm
     */
    public String getJkfsdm() {
        return jkfsdm;
    }

    /**
     * ���ýɿʽ����
     * @param String jkfsdm
     */

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getKhyhdm() {
        return khyhdm;
    }

    public String getKhyhmc() {
        return khyhmc;
    }

    public String getNsrlxdm() {
        return nsrlxdm;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public String getJmlydm() {
        return jmlydm;
    }

    public boolean isBl() {
        return bl;
    }

    public Timestamp getSbrq() {
        return sbrq;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    /**
     * ���ü����걨���
     * @param jmsbbh String
     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    /**
     * ���ü���˰���
     * @param jmsje String
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * ������ϵ�绰
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * ������ϵ������
     * @param lxrxm String
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * ������˰���������
     * @param nsjsjdm String
     */
    public void setNsjsjdm(String nsjsjdm) {
        this.nsjsjdm = nsjsjdm;
    }

    /**
     * ���������˺�
     * @param yhzh String
     */
    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setKhyhdm(String khyhdm) {
        this.khyhdm = khyhdm;
    }

    public void setKhyhmc(String khyhmc) {
        this.khyhmc = khyhmc;
    }

    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

}
