package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * null值对象
 */
public class Zh implements Serializable {

    /**
     * 帐户代码
     */
    public java.lang.String zhdm;

    /**
     * 帐户名称
     */
    public java.lang.String zhmc;

    /**
     * 联系电话(账户)
     */
    public java.lang.String lxdh;

    /**
     * 上级帐户代码
     */
    public java.lang.String sjzhdm;

    /**
     * 状态标识
     */
    public java.lang.String ztbs;

    /**
     * 录入人
     */
    public java.lang.String lrr;

    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;

    /**
     * 税务机关组织机构代码
     */
    public java.lang.String swjgzzjgdm;

    /**
     * 类型标识
     */
    public java.lang.String lxbs;

    /**
     * 计算机代码
     */
    public java.lang.String jsjdm;

    /**
     * 获得帐户代码
     */
    public String getZhdm() {
        return zhdm;
    }

    /**
     * 获得帐户名称
     */
    public String getZhmc() {
        return zhmc;
    }

    /**
     * 获得联系电话(账户)
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得上级帐户代码
     */
    public String getSjzhdm() {
        return sjzhdm;
    }

    /**
     * 获得状态标识
     */
    public String getZtbs() {
        return ztbs;
    }

    /**
     * 获得录入人
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * 获得录入日期
     */
    public Timestamp getLrrq() {
        return lrrq;
    }

    /**
     * 获得税务机关组织机构代码
     */
    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * 获得类型标识
     */
    public String getLxbs() {
        return lxbs;
    }

    /**
     * 获得计算机代码
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * 赋值帐户代码
     * @param zhdm 帐户代码
     */
    public void setZhdm(String zhdm) {
        this.zhdm = zhdm;
    }

    /**
     * 赋值帐户名称
     * @param zhmc 帐户名称
     */
    public void setZhmc(String zhmc) {
        this.zhmc = zhmc;
    }

    /**
     * 赋值联系电话(账户)
     * @param lxdh 联系电话(账户)
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 赋值上级帐户代码
     * @param sjzhdm 上级帐户代码
     */
    public void setSjzhdm(String sjzhdm) {
        this.sjzhdm = sjzhdm;
    }

    /**
     * 赋值状态标识
     * @param ztbs 状态标识
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    /**
     * 赋值录入人
     * @param lrr 录入人
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * 赋值录入日期
     * @param lrrq 录入日期
     */
    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    /**
     * 赋值税务机关组织机构代码
     * @param swjgzzjgdm 税务机关组织机构代码
     */
    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    /**
     * 赋值类型标识
     * @param lxbs 类型标识
     */
    public void setLxbs(String lxbs) {
        this.lxbs = lxbs;
    }

    /**
     * 赋值计算机代码
     * @param jsjdm 计算机代码
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }


}
