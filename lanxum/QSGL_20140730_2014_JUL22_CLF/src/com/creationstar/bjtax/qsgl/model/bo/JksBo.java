package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;


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
 * @author �Բ�
 * @version 1.0
 */
public class JksBo implements Serializable {
    /**
     * �걨���
     */
    public String sbbh;

    /**
     * ���������
     */
    public String jsjdm;

    /**
     * nsrmc
     */
    private String nsrmc;

    /**
     * �ɿ�ƾ֤��
     */
    public String jkpzh;

    /**
     * �걨��ʼ����
     */
    public String sbqsrq;

    /**
     * �걨��ֹ����
     */
    public String sbjzrq;

    /**
     * ������ʼ����
     */
    public String hzqsrq;

    /**
     * ���ܽ�ֹ����
     */
    public String hzjzrq;

    /**
     * ��˰��ʽ
     */
    public String jsfs;

    /**
     * ���ܷ�ʽ
     */
    public String hzfs;

    /**
     * �걨�ɿ������vo
     */
    public Sbjkzb sbjkzb;

    /**
     * �걨�ɿ���ϸ��vo
     */
    public Sbjkmx sbjkmx;

    /**
     * ��ѯ�ɿ���Ľ����
     */
    public ArrayList resultList;

    public String getSbbh() {
        return sbbh;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getHzfs() {
        return hzfs;
    }

    public String getHzjzrq() {
        return hzjzrq;
    }

    public String getHzqsrq() {
        return hzqsrq;
    }

    public String getJsfs() {
        return jsfs;
    }

    public ArrayList getResultList() {
        return resultList;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String getSbjzrq() {
        return sbjzrq;
    }

    public String getSbqsrq() {
        return sbqsrq;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setHzfs(String hzfs) {
        this.hzfs = hzfs;
    }

    public void setHzjzrq(String hzjzrq) {
        this.hzjzrq = hzjzrq;
    }

    public void setHzqsrq(String hzqsrq) {
        this.hzqsrq = hzqsrq;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setSbjzrq(String sbjzrq) {
        this.sbjzrq = sbjzrq;
    }

    public void setSbqsrq(String sbqsrq) {
        this.sbqsrq = sbqsrq;
    }

    /**
     * @return Returns the nsrmc.
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * @param nsrmc The nsrmc to set.
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }
}
