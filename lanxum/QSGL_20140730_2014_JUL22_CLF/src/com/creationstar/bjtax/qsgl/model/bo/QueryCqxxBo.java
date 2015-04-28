package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: 查询拆迁使用情况业务对象</p>
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
public class QueryCqxxBo implements Serializable {
    /**
     * 拆迁协议号码 此字段作为查询条件
     */
    private String cqxyh;

    //以下字段作为查询结果显示拆迁信息
    /**
     * 拆迁房屋坐落地址
     */
    private String zldz;

    /**
     * 拆迁补偿额
     */
    private BigDecimal cqbce;

    /**
     * 拆迁补偿剩余额
     */
    private BigDecimal cqbcsye;
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
     * 使用情况 Sbcqgl值对象的列表
     */
    private ArrayList listSbxx;


    public BigDecimal getCqbce() {
        return cqbce;
    }

    public BigDecimal getCqbcsye() {
        return cqbcsye;
    }

    public String getCqxyh() {
        return cqxyh;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public String getZldz() {
        return zldz;
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

    public void setZldz(String zldz) {
        this.zldz = zldz;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setCqxyh(String cqxyhm) {
        this.cqxyh = cqxyhm;
    }

    public void setCqbcsye(BigDecimal cqbcsye) {
        this.cqbcsye = cqbcsye;
    }

    public void setCqbce(BigDecimal cqbce) {
        this.cqbce = cqbce;
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
}
