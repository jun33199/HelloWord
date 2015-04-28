/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.util.JspUtil;


import com.ttsoft.common.model.Wsyhdllx;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Action。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class DzwszBO implements Serializable {
    public DzwszBO() {
    }

    private String sbrq = "       "; //申报日期
    private String yhmc = ""; //银行名称

    private String zh = ""; //银行账号

    private String swjgzzjgmc = ""; //税务机关组织机构名称
    private String gkzzjgdm = ""; //国库组织机构代码

    private String gkzzjgmc = ""; //国库组织机构名称
    private List szitem = new ArrayList(); //按税种条目的明细List
    private String hjjedx = ""; //合计金额大写

    private String hjjexx = "0.00"; //合计金额小写
    /**
     * 委托日期
     */
    private String wtrq;
    /**
     * 交易流水号
     */
    private String jylsh;
    /**
     * 计算机代码
     */
    private String jsjdm;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 税票号码
     */
    private String sphm;
    /**
     * 是否已经打印标志
     * @return String
     */
    private String sfdybz;
    /**
     * 年度字别
     */
    private String ndzb;
    /**
     * 完税证号
     */
    private String wszh;
    /**
     * 税务登记证号
     */
    private String swdjzh;
    /**
     * 备注1计算机代码
     */
    private String bz1;
    /**
     * 备注2税票号码
     */
    private String bz2;
    /**
     * 备注3交易流水号
     */
    private String bz3;
    /**
     * 打印日期
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
