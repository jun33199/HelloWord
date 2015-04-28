package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: 查询已购公有住房使用情况业务对象</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class QueryYggyzfBo implements Serializable {
    /**
     *  已购公有住房权属证书号 此字段作为查询条件
     */
    private String yggyzfqszsh;

    //以下字段作为查询结果显示拆迁信息
    /**
     * 房屋坐落地址
     */
    private String zldz;

    /**
     * 出售合同（契约）签订时间
     */
    private java.sql.Timestamp qdsj;

    /**
     * 建筑面积
     */
    private BigDecimal jzmj;
    /**
     * 成交价格
     */
    private BigDecimal cjjg;
    /**
     * 剩余额
     */
    private BigDecimal sye;
    /**
     * 申报人
     */
    private String cjr;
    /**
     * 申报日期
     */
    private java.sql.Timestamp cjrq;
    /**
     * 剩余额用完标志
     */
    private String syeywbz;
    /**
     * 房屋权属证书号 add by zhangyj 20090219
     */
    private String fwqszsh;

    /**
     * 使用情况 Sbgyzf值对象的列表
     */
    private ArrayList listSbxx;
    public BigDecimal getJzmj() {
        return jzmj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public Timestamp getQdsj() {
        return qdsj;
    }

    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    public String getZldz() {
        return zldz;
    }

    public BigDecimal getCjjg() {
        return cjjg;
    }

    public BigDecimal getSye() {
        return sye;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    public String getFwqszsh()
    {
        return fwqszsh;
    }
    public void setJzmj(BigDecimal jzmj) {
        this.jzmj = jzmj;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setQdsj(Timestamp qdsj) {
        this.qdsj = qdsj;
    }

    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setCjjg(BigDecimal cjjg) {
        this.cjjg = cjjg;
    }

    public void setSye(BigDecimal sye) {
        this.sye = sye;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }
    public void setFwqszsh(String fwqszsh)
    {
        this.fwqszsh = fwqszsh;
    }    
}
