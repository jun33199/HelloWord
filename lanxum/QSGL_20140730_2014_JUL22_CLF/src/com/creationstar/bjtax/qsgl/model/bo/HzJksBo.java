package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;

/**
 *
 * <p>Title:��¼�������ɵĽɿ����bo </p>
 *
 * <p>Description: ��¼�������ɵĽɿ����bo</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class HzJksBo implements Serializable {
    /**
     * �걨�ɿ������vo
     */
    private Sbjkzb sbjkzb;

    private String[] mxxmdm; //˰��˰Ŀ����

    private String[] mxxmmc; //˰��˰Ŀ����

    private String[] mxkssl; //��˰����

    private String[] mxjsje; //��˰���

    private String[] mxsjse; //ʵ��˰��

    private String fp; //�Ƿ��з�Ʊ�������0Ϊû�У�1Ϊ��

    private String hzfsdm; //���ܷ�ʽ����

    private String hzfsmc; //���ܷ�ʽ����

    private String[] jkpzh; //��Ʊ�Ľɿ�ƾ֤�ţ�������˰֤���ܱ�������ͬ��sbhzdh��ʱʹ��

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String[] getMxjsje() {
        return mxjsje;
    }

    public String[] getMxkssl() {
        return mxkssl;
    }

    public String[] getMxsjse() {
        return mxsjse;
    }

    public String[] getMxxmmc() {
        return mxxmmc;
    }

    public String getFp() {
        return fp;
    }

    public String getHzfsdm() {
        return hzfsdm;
    }

    public String getHzfsmc() {
        return hzfsmc;
    }

    public String[] getMxxmdm() {
        return mxxmdm;
    }

    public String[] getJkpzh() {
        return jkpzh;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setMxjsje(String[] mxjsje) {
        this.mxjsje = mxjsje;
    }

    public void setMxkssl(String[] mxkssl) {
        this.mxkssl = mxkssl;
    }

    public void setMxsjse(String[] mxsjse) {
        this.mxsjse = mxsjse;
    }

    public void setMxxmmc(String[] mxxmmc) {
        this.mxxmmc = mxxmmc;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public void setHzfsdm(String hzfsdm) {
        this.hzfsdm = hzfsdm;
    }

    public void setHzfsmc(String hzfsmc) {
        this.hzfsmc = hzfsmc;
    }

    public void setMxxmdm(String[] mxxmdm) {
        this.mxxmdm = mxxmdm;
    }

    public void setJkpzh(String[] jkpzh) {
        this.jkpzh = jkpzh;
    }

}
