/*
 * Created on 2006-2-22
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SjqyErrBo implements Serializable {

    //纳税人名称
    private String nsrmc;

    //身份证
    private String sfzjlx;
    //身份证号，计算机代码
    private String sfzjhm;

    //完税证号
    private String wszh;

    //税务机关代码
    private String swjgdm;

    //应纳税额
    private String ynse;


    //房地产项目地址
    private String fdcxmdz;

    //错误编号
    private String errcode;

    //错误信息
    private String errmsg;

    //区县代码
    private String qxdm;
    //申报编号
    private String sbbh;
    //受理人
    private String slr;

    //信息种类
    private String xxzl;

    /**
     *
     */
    public SjqyErrBo() {
        super();
        //
    }

    /**
     * @return Returns the errcode.
     */
    public String getErrcode() {
        return errcode;
    }

    /**
     * @param errcode The errcode to set.
     */
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    /**
     * @return Returns the errmsg.
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * @param errmsg The errmsg to set.
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * @return Returns the fdcxmdz.
     */
    public String getFdcxmdz() {
        return fdcxmdz;
    }

    /**
     * @param fdcxmdz The fdcxmdz to set.
     */
    public void setFdcxmdz(String fdcxmdz) {
        this.fdcxmdz = fdcxmdz;
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

    /**
     * @return Returns the qxdm.
     */
    public String getQxdm() {
        return qxdm;
    }

    /**
     * @param qxdm The qxdm to set.
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    /**
     * @return Returns the sbbh.
     */
    public String getSbbh() {
        return sbbh;
    }

    /**
     * @param sbbh The sbbh to set.
     */
    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * @return Returns the sfzjhm.
     */
    public String getSfzjhm() {
        return sfzjhm;
    }

    /**
     * @param sfzjhm The sfzjhm to set.
     */
    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    /**
     * @return Returns the sfzjlx.
     */
    public String getSfzjlx() {
        return sfzjlx;
    }

    /**
     * @param sfzjlx The sfzjlx to set.
     */
    public void setSfzjlx(String sfzjlx) {
        this.sfzjlx = sfzjlx;
    }

    /**
     * @return Returns the slr.
     */
    public String getSlr() {
        return slr;
    }

    /**
     * @param slr The slr to set.
     */
    public void setSlr(String slr) {
        this.slr = slr;
    }

    /**
     * @return Returns the xxzl.
     */
    public String getXxzl() {
        return xxzl;
    }

    /**
     * @param xxzl The xxzl to set.
     */
    public void setXxzl(String xxzl) {
        this.xxzl = xxzl;
    }

    /**
     * @return Returns the swjgdm.
     */
    public String getSwjgdm() {
        return swjgdm;
    }

    /**
     * @param swjgdm The swjgdm to set.
     */
    public void setSwjgdm(String swjgdm) {
        this.swjgdm = swjgdm;
    }

    /**
     * @return Returns the wszh.
     */
    public String getWszh() {
        return wszh;
    }

    /**
     * @param wszh The wszh to set.
     */
    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    /**
     * @return Returns the ynse.
     */
    public String getYnse() {
        return ynse;
    }

    /**
     * @param ynse The ynse to set.
     */
    public void setYnse(String ynse) {
        this.ynse = ynse;
    }
}
