package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: 作为查询申报数据的一个业务对象</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JsblBo implements Serializable {
    /**
     * JsblBo作为查询条件时，指明从那个查询页面而来
     * 0：申报查询
     * 1：不征查询
     * 2：审核查询
     * 3：复核查询
     */
    private int fromPage;

    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 建委业务编号，modify by fujx
     */
    private String jwywbh;
    /**
     * 合同编号，modify by fujx
     */
    private String htbh;


    /**
     * 作当前操作的纳税人的计算机代码
     */
    private String jsjdm;

    /**
     * 作当前操作的纳税人的名称
     */
    private String nsrmc;

    /**
     * 作当前操作的纳税人的联系电话
     */
    private String lxdh;

    /**
     * 作当前操作的纳税人的类型
     * 国家机关、事业单位、社会团队、军事单位、其他、个人
     */
    private String nsrlxdm;

    /**
     * 减免申报表号
     */
//    private String jmsbbh;

    /**
     * 完税证号
     */
    private String wszh;

    /**
     * 缴款书号
     */
    private String jksh;

    /**
     * 身份证件类型
     */
    private String sfzjlx;

    /**
     * 个人合法身份证件号码
     */
    private String sfzjhm;

    /**
     * 房地产项目名称
     */
    private String fdcxmmc;


    /**
     * 土地、房屋座落地址
     */
    private String tdfwzldz;

    /**
     * 成交价格人民币
     */
    private BigDecimal cjjgrmb;

    /**
     * 备注
     */
    private String bz;

    /**
     * 用户标识 0代表个人，1代表非个人
     */
    private String yhbs;

    /**
     * 状态标识
     */
    private String ztbs;

    /**
     * 减免税标识
     */
    private String jmsbs;

    /**
     * 假的状态标识,查询条件用,现在审核收款查询页面使用
     */
    private String fztbs;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 导入批次号
     */
    private String drpch;

    public String getFdcxmmc() {
        return fdcxmmc;
    }


//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    public String getJsjdm() {
        return jsjdm;
    }


    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }


    public String getJksh() {
        return jksh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getSfzjlx() {
        return sfzjlx;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public String getBz() {
        return bz;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getYhbs() {
        return yhbs;
    }

    public String getNsrlxdm() {
        return nsrlxdm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getJmsbs() {
        return jmsbs;
    }

    public String getSbrq() {
        return sbrq;
    }

    public int getFromPage() {
        return fromPage;
    }

    public String getFztbs() {
        return fztbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }


//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }


    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }


    public void setJksh(String jksh) {
        this.jksh = jksh;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setSfzjlx(String sfzjlx) {
        this.sfzjlx = sfzjlx;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setYhbs(String yhbs) {
        this.yhbs = yhbs;
    }

    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setJmsbs(String jmsbs) {
        this.jmsbs = jmsbs;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public void setFztbs(String fztbs) {
        this.fztbs = fztbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    /**
     * 返回当前这个bo的纳税人类型是否为个人
     * @param nsrlxdm String
     * @return boolean
     */
    public boolean ifPersonal() {
        Debug.out("bo.nsrlxdm: " + this.nsrlxdm);
        if (this.nsrlxdm.equals("99")) {
            return true;
        } else {
            return false;
        }
    }
}
