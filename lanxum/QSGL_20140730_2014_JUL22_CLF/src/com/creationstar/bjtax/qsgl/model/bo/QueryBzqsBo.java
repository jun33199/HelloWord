package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

public class QueryBzqsBo implements Serializable {
    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ��˰������
     */
    private String nsrlx;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ���ز���Ŀ����
     */
    private String fdcxmmc;

    /**
     * ���֤�����ʹ���
     */
    private String zjlxdm;

    /**
     * ���֤������
     */
    private String sfzjhm;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ����
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();


    /**
     * �����漰�����걨����������Ϣ���Ǹ�����Ϣ�����ط�����Ϣ��
     * �걨���������ط�����Ϣ��Ĺ������ֵ����
     */
    private Sbzb sbzb;
    private Grxx grxx;
    private Tufwxx tufwxx;
    private Sbtdfwgl sbtdfwg;
    private Fgrxx fgrxx;

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public Grxx getGrxx() {
        return grxx;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Sbtdfwgl getSbtdfwg() {
        return sbtdfwg;
    }

    public Sbzb getSbzb() {
        return sbzb;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }

    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbtdfwg(Sbtdfwgl sbtdfwg) {
        this.sbtdfwg = sbtdfwg;
    }

    public void setSbzb(Sbzb sbzb) {
        this.sbzb = sbzb;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }


    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }
}
