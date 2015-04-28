/*
 * Created on 2005-12-20
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author guzx
 *  机关核定数据,后台计算数据
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JghdsjBo implements Serializable {
    /**
     * @return Returns the cjjgrmb.
     */
    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    /**
     * @param cjjgrmb The cjjgrmb to set.
     */
    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    /**
     * @return Returns the cqjmje.
     */
    public BigDecimal getCqjmje() {
        return cqjmje;
    }

    /**
     * @param cqjmje The cqjmje to set.
     */
    public void setCqjmje(BigDecimal cqjmje) {
        this.cqjmje = cqjmje;
    }

    /**
     * @return Returns the fwjhCjjg.
     */
    public BigDecimal getFwjhCjjg() {
        return fwjhCjjg;
    }

    /**
     * @param fwjhCjjg The fwjhCjjg to set.
     */
    public void setFwjhCjjg(BigDecimal fwjhCjjg) {
        this.fwjhCjjg = fwjhCjjg;
    }

    /**
     * @return Returns the gyzfjmje.
     */
    public BigDecimal getGyzfjmje() {
        return gyzfjmje;
    }

    /**
     * @param gyzfjmje The gyzfjmje to set.
     */
    public void setGyzfjmje(BigDecimal gyzfjmje) {
        this.gyzfjmje = gyzfjmje;
    }

    /**
     * @return Returns the jmzje.
     */
    public BigDecimal getJmzje() {
        return jmzje;
    }

    /**
     * @param jmzje The jmzje to set.
     */
    public void setJmzje(BigDecimal jmzje) {
        this.jmzje = jmzje;
    }

    /**
     * @return Returns the jsyj.
     */
    public BigDecimal getJsyj() {
        return jsyj;
    }

    /**
     * @param jsyj The jsyj to set.
     */
    public void setJsyj(BigDecimal jsyj) {
        this.jsyj = jsyj;
    }

    /**
     * @return Returns the jzqs.
     */
    public BigDecimal getJzqs() {
        return jzqs;
    }

    /**
     * @param jzqs The jzqs to set.
     */
    public void setJzqs(BigDecimal jzqs) {
        this.jzqs = jzqs;
    }

    /**
     * @return Returns the jzse.
     */
    public BigDecimal getJzse() {
        return jzse;
    }

    /**
     * @param jzse The jzse to set.
     */
    public void setJzse(BigDecimal jzse) {
        this.jzse = jzse;
    }

    /**
     * @return Returns the ptzzjmje.
     */
    public BigDecimal getPtzzjmje() {
        return ptzzjmje;
    }

    /**
     * @param ptzzjmje The ptzzjmje to set.
     */
    public void setPtzzjmje(BigDecimal ptzzjmje) {
        this.ptzzjmje = ptzzjmje;
    }

    /**
     * @return Returns the sl.
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * @param sl The sl to set.
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * @return Returns the ynse.
     */
    public BigDecimal getYnse() {
        return ynse;
    }

    /**
     * @param ynse The ynse to set.
     */
    public void setYnse(BigDecimal ynse) {
        this.ynse = ynse;
    }

    /**
     * 成交价格人民币
     */
    private BigDecimal cjjgrmb;

    /**
     * 拆迁减免金额
     */
    private BigDecimal cqjmje;

    /**
     * 房屋交换成交价格人民币
     */
    private BigDecimal fwjhCjjg;

    /**
     * 减税金额
     */
    private BigDecimal jmzje;

    /**
     * 计税依据
     */
    private BigDecimal jsyj;

    /**
     * 计征契税额
     */
    private BigDecimal jzqs;

    /**
     * 计征税额
     */
    private BigDecimal jzse;

    /**
     * 普通住宅减税金额
     */
    private BigDecimal ptzzjmje;

    /**
     * 经济适用房减税金额
     */
    private BigDecimal jjsyfjmje;

    /**
     * 减税金额
     */
    private BigDecimal gyzfjmje;

    /**
     * 税率
     */
    private BigDecimal sl;


    /**
     * 应纳税额
     */
    private BigDecimal ynse;

    /**
     * 实际应征
     */
    private BigDecimal sjyz;


    /**
     *
     */
    public JghdsjBo() {
        super();
        //
    }

    /**
     * @return Returns the sjyz.
     */
    public BigDecimal getSjyz() {
        return sjyz;
    }

    public BigDecimal getJjsyfjmje() {
        return jjsyfjmje;
    }

    /**
     * @param sjyz The sjyz to set.
     */
    public void setSjyz(BigDecimal sjyz) {
        this.sjyz = sjyz;
    }

    public void setJjsyfjmje(BigDecimal jjsyfjmje) {
        this.jjsyfjmje = jjsyfjmje;
    }
}
