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
 *  @author 蒋勇
 * @version 1.0
 */
public class SbFgrBo implements Serializable {


    /**
     * 申报日期
     */
    public Timestamp sbrq;

    /**
     * 补录标示
     */
    public boolean bl;

    /**
     * 纳税申报表号
     */
    public String sbbh;
    /**
     * 20081125,modify by fujx,增加建委业务编号
     */
    private String jwywbh;

    /**
     * 20081125,modify by fujx,增加合同编号
     */
    private String htbh;


    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbbh() {
        return sbbh;
    }

    /**
     * 纳税人名称
     */
    public String nsrmc;
    public void setNsrmc(String s) {
        this.nsrmc = s;
    }

    public String getNsrmc() {
        return nsrmc;
    }


    /**
     * 缴款方式
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm;
    public String jkfsmc;
    /**
     * 减免申报表号
     */
//    public String jmsbbh;

    /**
     * 房屋土地房产局受理号
     */
    public String fcjslh;

    /**
     * 联系电话
     */
    public String lxdh;


    /**
     * 减免税金额
     */
    public BigDecimal jmsje;

    /**
     * 核定通知书字号
     */
    public String hdtzszh;

    /**
     * 减免理由 根据核定通知书获取
     */
    public String jmlydm;

    /**
     * 纳税计算机代码
     */
    public String nsjsjdm;
    /**
     * 税务人类型代码
     */
    public String nsrlxdm;

    /**
     * 税务人类型名称
     */
    public String nsrlxmc;

    /**
     * 开户银行
     */
    public String khyhdm;
    public String khyhmc;
    /**
     * 银行账号
     */
    public String yhzh;
    /**
     * 联系人姓名
     */
    public String lxrxm;
    /**
     * 备注
     */
    public String bz;
    /**
     * 拆迁信息
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
     * 公有住房基本信息对象
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
     * 拆迁信息
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
     * 房屋基本信息对象
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
     * 获得备注
     * @return String
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得房屋土地房产局受理号
     * @return String
     */
    public String getFcjslh() {
        return fcjslh;
    }

    /**
     * 获得核定通知书字号
     * @return String
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * 获得缴款方式
     * @return ArrayList
     */
    public ArrayList getJkfsList() {
        return jkfsList;
    }

    /**
     *  获得减免申报表号
     * @return String
     */
//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    /**
     * 获得减免税金额
     * @return String
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    /**
     * 获得联系电话
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得联系人姓名
     * @return String
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * 获得纳税计算机代码
     * @return String
     */
    public String getNsjsjdm() {
        return nsjsjdm;
    }

    /**
     * 获得银行账号
     * @return String
     */
    public String getYhzh() {
        return yhzh;
    }

    /**
     * 设置备注
     * @param bz String
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 设置房屋土地房产局受理号
     * @param fcjslh String
     */
    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    /**
     * 设置核定通知书字号
     * @param hdtzszh String
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * 设置缴款方式代码
     * @param String jkfsdm
     */
    public String getJkfsdm() {
        return jkfsdm;
    }

    /**
     * 设置缴款方式名称
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
     * 设置减免申报表号
     * @param jmsbbh String
     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    /**
     * 设置减免税金额
     * @param jmsje String
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * 设置联系电话
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 设置联系人姓名
     * @param lxrxm String
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * 设置纳税计算机代码
     * @param nsjsjdm String
     */
    public void setNsjsjdm(String nsjsjdm) {
        this.nsjsjdm = nsjsjdm;
    }

    /**
     * 设置银行账号
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
