package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * <p>Title: 查询房屋土地使用情况业务对象</p>
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
public class QueryFtxxBo implements Serializable {
    /**
     * 土地、房屋系统唯一标识 此字段作为查询条件
     */
    private String tdfwid;

    // 以下为查询结果

    /**
     * 房地产项目名称
     */
    private String fdcxmmx;
    /**
     * 合同（契约）签订时间
     */
    private java.sql.Timestamp htqdsj;
    /**
     * 分类
     */
    public ArrayList flList = new ArrayList();
    public String flmc;
    public String fldm;

    /**
     * 土地、房屋权属转移类型
     */
    public ArrayList tdfwqszylxList = new ArrayList();
    private String tdfwqszylx;
    private String tdfwqszylxmc;

    /**
     * 房屋类别
     */
    public ArrayList fwlxList = new ArrayList();
    private String fwlxdm;
    private String fwlxmc;


    /**
     * 土地、房屋座落地址
     */
    private String tdfwzldz;
    /**
     * 土地、房屋权属转移面积
     */
    private BigDecimal tdfwqszymj;
    /**
     * 房屋建筑面积
     */
    private BigDecimal fwjzmj;
    /**
     * 成交价格（人民币）
     */
    private BigDecimal cjjgrmb;
    /**
     * 评估价格（人民币）
     */
    private BigDecimal pgjgrmb;
    /**
     * 成交价格（外币）
     */
    private BigDecimal cjjgwb;
    /**
     * 币种
     */
    public ArrayList bzList = new ArrayList();
    private String bzdm;
    private String bzmc;
    /**
     * 汇率
     */
    private BigDecimal hl;
    /**
     * 折合价格（人民币）
     */
    private BigDecimal zhjgrmb;
    /**
     * 申报人
     */
    private String cjr;
    /**
     * 申报日期
     */
    private java.sql.Timestamp cjrq;

    /**
     * 容积率
     */
    private String rjl;

    /**
     * 土地级次
     */
    private String tdjc;

    /**
     * 使用情况 Sbftgl值对象的列表
     */
    private ArrayList listSbxx = new ArrayList();
    public String getBzdm() {
        return bzdm;
    }

    public ArrayList getBzList() {
        return bzList;
    }

    public String getBzmc() {
        return bzmc;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public BigDecimal getCjjgwb() {
        return cjjgwb;
    }

    public String getFdcxmmx() {
        return fdcxmmx;
    }

    public String getFldm() {
        return fldm;
    }

    public ArrayList getFlList() {
        return flList;
    }

    public String getFlmc() {
        return flmc;
    }

    public BigDecimal getFwjzmj() {
        return fwjzmj;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public ArrayList getFwlxList() {
        return fwlxList;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public BigDecimal getHl() {
        return hl;
    }

    public Timestamp getHtqdsj() {
        return htqdsj;
    }

    public ArrayList getListSbxx() {
        return listSbxx;
    }

    public BigDecimal getPgjgrmb() {
        return pgjgrmb;
    }

    public String getTdfwid() {
        return tdfwid;
    }

    public String getTdfwqszylx() {
        return tdfwqszylx;
    }

    public ArrayList getTdfwqszylxList() {
        return tdfwqszylxList;
    }

    public String getTdfwqszylxmc() {
        return tdfwqszylxmc;
    }

    public BigDecimal getTdfwqszymj() {
        return tdfwqszymj;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public BigDecimal getZhjgrmb() {
        return zhjgrmb;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getRjl() {
        return rjl;
    }

    public String getTdjc() {
        return tdjc;
    }

    public void setZhjgrmb(BigDecimal zhjgrmb) {
        this.zhjgrmb = zhjgrmb;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setTdfwqszymj(BigDecimal tdfwqszymj) {
        this.tdfwqszymj = tdfwqszymj;
    }

    public void setTdfwqszylxmc(String tdfwqszylxmc) {
        this.tdfwqszylxmc = tdfwqszylxmc;
    }

    public void setTdfwqszylxList(ArrayList tdfwqszylxList) {
        this.tdfwqszylxList = tdfwqszylxList;
    }

    public void setTdfwqszylx(String tdfwqszylx) {
        this.tdfwqszylx = tdfwqszylx;
    }

    public void setTdfwid(String tdfwid) {
        this.tdfwid = tdfwid;
    }

    public void setPgjgrmb(BigDecimal pgjgrmb) {
        this.pgjgrmb = pgjgrmb;
    }

    public void setListSbxx(ArrayList listSbxx) {
        this.listSbxx = listSbxx;
    }

    public void setHtqdsj(Timestamp htqdsj) {
        this.htqdsj = htqdsj;
    }

    public void setHl(BigDecimal hl) {
        this.hl = hl;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxList(ArrayList fwlxList) {
        this.fwlxList = fwlxList;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setFwjzmj(BigDecimal fwjzmj) {
        this.fwjzmj = fwjzmj;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public void setFlList(ArrayList flList) {
        this.flList = flList;
    }

    public void setFldm(String fldm) {
        this.fldm = fldm;
    }

    public void setFdcxmmx(String fdcxmmx) {
        this.fdcxmmx = fdcxmmx;
    }

    public void setCjjgwb(BigDecimal cjjgwb) {
        this.cjjgwb = cjjgwb;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public void setBzList(ArrayList bzList) {
        this.bzList = bzList;
    }

    public void setBzdm(String bzdm) {
        this.bzdm = bzdm;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setRjl(String rjl) {
        this.rjl = rjl;
    }

    public void setTdjc(String tdjc) {
        this.tdjc = tdjc;
    }


}
