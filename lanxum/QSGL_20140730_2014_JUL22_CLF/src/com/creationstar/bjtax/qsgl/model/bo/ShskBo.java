package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class ShskBo implements Serializable {
    /**
     * 交税方式
     */
    private String jsfsdm;
    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 土地房屋坐落地址
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
     * 税率
     */
    private BigDecimal sl;

    /**
     * 计征税额
     */
    private BigDecimal jsje;

    /**
     * 计税依据
     */
    private BigDecimal jsyj;

    /**
     * 应纳税额
     */
    private BigDecimal ynse;

    /**
     * 拆迁减免金额
     */
    private BigDecimal cqjmje;

    /**
     * 普通住宅减税金额
     */
    private BigDecimal ptzzjsje;

    /**
     * 经济适用房减税金额
     */
    private BigDecimal jjsyfjsje;


    /**
     * 对应的是显示的核定通知书信息HdtzsBo
     */
    private ArrayList hdList = new ArrayList();

    /**
     * 是否已经审核
     */
    private boolean checked = false;
    /**
     * 纳税人列表
     */
    public List nsrList = new ArrayList();

    /**
     * 是否是批次导入的数据
     */
    private boolean pc = false;

    public String getBz() {
        return bz;
    }

    public ArrayList getHdList() {
        return hdList;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public BigDecimal getCqjmje() {
        return cqjmje;
    }

    public BigDecimal getJsje() {
        return jsje;
    }

    public BigDecimal getPtzzjsje() {
        return ptzzjsje;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public BigDecimal getYnse() {
        return ynse;
    }

    public boolean isChecked() {
        return checked;
    }

    public BigDecimal getJsyj() {
        return jsyj;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public boolean isPc() {
        return pc;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setHdList(ArrayList hdList) {
        this.hdList = hdList;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setCqjmje(BigDecimal cqjmje) {
        this.cqjmje = cqjmje;
    }

    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    public void setPtzzjsje(BigDecimal ptzzjsje) {
        this.ptzzjsje = ptzzjsje;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public void setYnse(BigDecimal ynse) {
        this.ynse = ynse;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setJsyj(BigDecimal jsyj) {
        this.jsyj = jsyj;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    public BigDecimal getJjsyfjsje() {
        return jjsyfjsje;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    public void setJjsyfjsje(BigDecimal jjsyfjsje) {
        this.jjsyfjsje = jjsyfjsje;
    }

    /**
     * 判断该申报是否是个人
     * @return boolean
     */
    public boolean isPerson() {
        if (nsrList == null || nsrList.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断该申报是否是个人
     * @return boolean
     */
    public boolean isPersonChecked() {

        if (isPerson() && isChecked() && getYnse().doubleValue() > 0) {
            return true;
        }
        return false;
    }

}
