/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.util.JspUtil;


import com.ttsoft.common.model.Wsyhdllx;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Action��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class DzwszBO implements Serializable {
    public DzwszBO() {
    }

    private String sbrq = "       "; //�걨����
    private String yhmc = ""; //��������

    private String zh = ""; //�����˺�

    private String swjgzzjgmc = ""; //˰�������֯��������
    private String gkzzjgdm = ""; //������֯��������

    private String gkzzjgmc = ""; //������֯��������
    private List szitem = new ArrayList(); //��˰����Ŀ����ϸList
    private String hjjedx = ""; //�ϼƽ���д

    private String hjjexx = "0.00"; //�ϼƽ��Сд
    /**
     * ί������
     */
    private String wtrq;
    /**
     * ������ˮ��
     */
    private String jylsh;
    /**
     * ���������
     */
    private String jsjdm;
    /**
     * ��˰������
     */
    private String nsrmc;
    /**
     * ˰Ʊ����
     */
    private String sphm;
    /**
     * �Ƿ��Ѿ���ӡ��־
     * @return String
     */
    private String sfdybz;
    /**
     * ����ֱ�
     */
    private String ndzb;
    /**
     * ��˰֤��
     */
    private String wszh;
    /**
     * ˰��Ǽ�֤��
     */
    private String swdjzh;
    /**
     * ��ע1���������
     */
    private String bz1;
    /**
     * ��ע2˰Ʊ����
     */
    private String bz2;
    /**
     * ��ע3������ˮ��
     */
    private String bz3;
    /**
     * ��ӡ����
     */
    private String dyrq;

    public String getDyrq() {
        return dyrq;
    }

    public void setDyrq(String dyrq) {
        this.dyrq = dyrq;
    }
        
    public String getJylsh() {
        return jylsh;
    }

    public String getWtrq() {
        return wtrq;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSphm() {
        return sphm;
    }

    public String getSbrq() {
        return sbrq;
    }

    public String getYhmc() {
        return yhmc;
    }

    public String getZh() {
        return zh;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public String getGkzzjgdm() {
        return gkzzjgdm;
    }

    public String getGkzzjgmc() {
        return gkzzjgmc;
    }

    public String getHjjedx() {
        return hjjedx;
    }

    public String getHjjexx() {
        return hjjexx;
    }

    public List getSzitem() {
        return szitem;
    }

    public String getSfdybz() {
        return sfdybz;
    }

    public String getBz1() {

        return bz1;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getWszh() {
        return wszh;
    }

    public String getSwdjzh() {
        return swdjzh;
    }

    public String getBz2() {
        return bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setWtrq(String wtrq) {
        this.wtrq = wtrq;
    }

    public void setJylsh(String jylsh) {
        this.jylsh = jylsh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSphm(String sphm) {
        this.sphm = sphm;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setGkzzjgdm(String gkzzjgdm) {
        this.gkzzjgdm = gkzzjgdm;
    }

    public void setGkzzjgmc(String gkzzjgmc) {
        this.gkzzjgmc = gkzzjgmc;
    }

    public void setHjjedx(String hjjedx) {
        this.hjjedx = hjjedx;
    }

    public void setHjjexx(String hjjexx) {
        this.hjjexx = hjjexx;
    }

    public void setSzitem(List szitem) {
        this.szitem = szitem;
    }

    public void setSfdybz(String sfdybz) {
        this.sfdybz = sfdybz;
    }

    public void setBz1(String bz1) {

        this.bz1 = bz1;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setSwdjzh(String swdjzh) {
        this.swdjzh = swdjzh;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

}
