package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.Date;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 *
 * <p>Title: 房屋、土地Form </p>
 *
 * <p>Description: 房屋土地的Form，可以作为父类，继承后获得房屋土地表单的属性 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company:北京创讯益达软件技术有限公司 </p>
 *
 * @author 赵博
 * @version 1.0
 */
public class FwTdForm extends BaseForm {
    public FwTdForm() {
    }

    public String getBzdm() {
        return bzdm;
    }

    public String getBzmc() {
        return bzmc;
    }

    public String getCjjg1() {
        return cjjg1;
    }

    public String getCjjg2() {
        return cjjg2;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getFwlbdm() {
        return fwlbdm;
    }

    public String getFwlbmc() {
        return fwlbmc;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getHldm() {
        return hldm;
    }

    public String getHlmc() {
        return hlmc;
    }

    public Date getHtqdsj() {
        return htqdsj;
    }

    public String getPgjg() {
        return pgjg;
    }

    public String getQszylxdm() {
        return qszylxdm;
    }

    public String getQszymc() {
        return qszymc;
    }

    public String getQszymj() {
        return qszymj;
    }

    public String getZhjg1() {
        return zhjg1;
    }

    public String getZldz() {
        return zldz;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setCjjg1(String cjjg1) {
        this.cjjg1 = cjjg1;
    }

    public void setCjjg2(String cjjg2) {
        this.cjjg2 = cjjg2;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setFwlbdm(String fwlbdm) {
        this.fwlbdm = fwlbdm;
    }

    public void setFwlbmc(String fwlbmc) {
        this.fwlbmc = fwlbmc;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setHldm(String hldm) {
        this.hldm = hldm;
    }

    public void setHlmc(String hlmc) {
        this.hlmc = hlmc;
    }

    public void setHtqdsj(Date htqdsj) {
        this.htqdsj = htqdsj;
    }

    public void setPgjg(String pgjg) {
        this.pgjg = pgjg;
    }

    public void setQszylxdm(String qszylxdm) {
        this.qszylxdm = qszylxdm;
    }

    public void setQszymc(String qszymc) {
        this.qszymc = qszymc;
    }

    public void setQszymj(String qszymj) {
        this.qszymj = qszymj;
    }

    public void setZhjg1(String zhjg1) {
        this.zhjg1 = zhjg1;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    /**
     * 房地产项目名称
     */
    private String fdcxmmc;

    /**
     * 合同（契约）签订时间
     */
    private Date htqdsj;

    /**
     * 房屋类型代码
     */
    private String fwlxdm;

    /**
     * 房屋类型名称
     */
    private String fwlxmc;

    /**
     * 土地房屋坐落地址
     */
    private String zldz;

    /**
     * 土地、房屋权属转移类型代码
     */
    private String qszylxdm;

    /**
     * 土地、房屋权属转移类型名称
     */
    private String qszymc;

    /**
     * 房屋类别代码
     */
    private String fwlbdm;

    /**
     * 房屋类别名称
     */
    private String fwlbmc;

    /**
     * 土地、房屋权属转移面积
     */
    private String qszymj;

    /**
     * 成交价格（人民币）
     */
    private String cjjg1;

    /**
     * 成交价格（外币）
     */
    private String cjjg2;

    /**
     * 币种代码
     */
    private String bzdm;

    /**
     * 币种名称
     */
    private String bzmc;

    /**
     * 汇率代码
     */
    private String hldm;

    /**
     * 汇率名称
     */
    private String hlmc;

    /**
     * 折合价格（人民币）
     */
    private String zhjg1;

    /**
     * 评估价格（人民币）
     */
    private String pgjg;
}
