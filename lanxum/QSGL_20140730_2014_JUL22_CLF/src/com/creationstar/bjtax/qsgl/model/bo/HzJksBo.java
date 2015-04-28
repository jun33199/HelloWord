package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;

/**
 *
 * <p>Title:补录汇总生成的缴款书的bo </p>
 *
 * <p>Description: 补录汇总生成的缴款书的bo</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public class HzJksBo implements Serializable {
    /**
     * 申报缴款主表的vo
     */
    private Sbjkzb sbjkzb;

    private String[] mxxmdm; //税种税目代码

    private String[] mxxmmc; //税种税目名称

    private String[] mxkssl; //课税数量

    private String[] mxjsje; //计税金额

    private String[] mxsjse; //实缴税额

    private String fp; //是否有分票的情况，0为没有，1为有

    private String hzfsdm; //汇总方式代码

    private String hzfsmc; //汇总方式名称

    private String[] jkpzh; //分票的缴款凭证号，更新完税证汇总表（生成相同的sbhzdh）时使用

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
