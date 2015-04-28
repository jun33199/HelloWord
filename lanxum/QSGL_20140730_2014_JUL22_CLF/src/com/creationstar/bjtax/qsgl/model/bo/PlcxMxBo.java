package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PlcxMxBo implements Serializable {
    //批次号
    private String pch;
    //状态信息(已导入,已提交,已审核,已复核,已受理通过)
    private String zt = "";
    //申报表号
    private String sbbh = "";
    //纳税人名称
    private String nsrmc;
    //纳税人计算机代码
    private String jsjdm;
    //房地产项目名称
    private String fdcxmmc;
    //房地产地址
    private String fdcdz;
    //合同签订日期
    private String htqdrq;
    //计税金额
    private String jsje;
    //应纳税额
    private String ynse;

    public void setFdcdz(String fdcdz) {
        this.fdcdz = fdcdz;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setHtqdrq(String htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setYnse(String ynse) {
        this.ynse = ynse;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getHtqdrq() {
        return htqdrq;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getJsje() {
        return jsje;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getPch() {
        return pch;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getYnse() {
        return ynse;
    }

    public String getZt() {
        return zt;
    }

    public String getFdcdz() {
        return fdcdz;
    }
}
