package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 税种税目代码表值对象
 */
public class Szsm implements Serializable {

    /**
     * 税种税目代码
     */
    public java.lang.String szsmdm;

    /**
     * 税种税目名称
     */
    public java.lang.String szsmmc;

    /**
     * 税率
     */
    public java.math.BigDecimal sl;

    /**
     * 计税基数
     */
    public java.math.BigDecimal jsjs;

    /**
     * 速算扣除数
     */
    public java.math.BigDecimal sskcs;

    /**
     * 减费用额
     */
    public java.math.BigDecimal jfys;

    /**
     * 计量单位
     */
    public java.lang.String jldw;

    /**
     * 执行开始日期
     */
    public java.sql.Timestamp zxksrq;

    /**
     * 执行结束日期
     */
    public java.sql.Timestamp zxjsrq;

    /**
     * 按数量计标识
     */
    public java.lang.String asljbs;

    /**
     * 应纳税起始数
     */
    public java.math.BigDecimal ynsqss;

    /**
     * 应纳税终止数
     */
    public java.math.BigDecimal ynszzs;

    /**
     * 计算公式
     */
    public java.lang.String jsgs;

    /**
     * 数据来源字段
     */
    public java.lang.String sjlyzd;

    /**
     * 界面字段可编辑标志
     */
    public java.lang.String zdkbjbz;

    /**
     * 拼音索引
     */
    public java.lang.String pysy;

    /**
     * 备注
     */
    public java.lang.String bz;

    /**
     * 是否附加税
     */
    public java.lang.String sffjs;

    /**
     * 征期类型代码
     */
    public java.lang.String zqlxdm;

    /**
     * 父节点代码
     */
    public java.lang.String fjddm;

    /**
     * 不含税所得税率
     */
    public java.math.BigDecimal bhsdssl;

    /**
     * 不含税所得起始
     */
    public java.math.BigDecimal bhsdsqs;

    /**
     * 不含税所得终止
     */
    public java.math.BigDecimal bhsdszz;

    /**
     * 不含税所得速算扣除数
     */
    public java.math.BigDecimal bhsdskcs;

    /**
     * 注销标识
     */
    public java.lang.String zxbs;

    /**
     * 层次标识
     */
    public java.lang.String ccbs;

    /**
     * 获得税种税目代码
     */
    public String getSzsmdm() {
        return szsmdm;
    }

    /**
     * 获得税种税目名称
     */
    public String getSzsmmc() {
        return szsmmc;
    }

    /**
     * 获得税率
     */
    public BigDecimal getSl() {
        return sl;
    }

    /**
     * 获得计税基数
     */
    public BigDecimal getJsjs() {
        return jsjs;
    }

    /**
     * 获得速算扣除数
     */
    public BigDecimal getSskcs() {
        return sskcs;
    }

    /**
     * 获得减费用额
     */
    public BigDecimal getJfys() {
        return jfys;
    }

    /**
     * 获得计量单位
     */
    public String getJldw() {
        return jldw;
    }

    /**
     * 获得执行开始日期
     */
    public Timestamp getZxksrq() {
        return zxksrq;
    }

    /**
     * 获得执行结束日期
     */
    public Timestamp getZxjsrq() {
        return zxjsrq;
    }

    /**
     * 获得按数量计标识
     */
    public String getAsljbs() {
        return asljbs;
    }

    /**
     * 获得应纳税起始数
     */
    public BigDecimal getYnsqss() {
        return ynsqss;
    }

    /**
     * 获得应纳税终止数
     */
    public BigDecimal getYnszzs() {
        return ynszzs;
    }

    /**
     * 获得计算公式
     */
    public String getJsgs() {
        return jsgs;
    }

    /**
     * 获得数据来源字段
     */
    public String getSjlyzd() {
        return sjlyzd;
    }

    /**
     * 获得界面字段可编辑标志
     */
    public String getZdkbjbz() {
        return zdkbjbz;
    }

    /**
     * 获得拼音索引
     */
    public String getPysy() {
        return pysy;
    }

    /**
     * 获得备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得是否附加税
     */
    public String getSffjs() {
        return sffjs;
    }

    /**
     * 获得征期类型代码
     */
    public String getZqlxdm() {
        return zqlxdm;
    }

    /**
     * 获得父节点代码
     */
    public String getFjddm() {
        return fjddm;
    }

    /**
     * 获得不含税所得税率
     */
    public BigDecimal getBhsdssl() {
        return bhsdssl;
    }

    /**
     * 获得不含税所得起始
     */
    public BigDecimal getBhsdsqs() {
        return bhsdsqs;
    }

    /**
     * 获得不含税所得终止
     */
    public BigDecimal getBhsdszz() {
        return bhsdszz;
    }

    /**
     * 获得不含税所得速算扣除数
     */
    public BigDecimal getBhsdskcs() {
        return bhsdskcs;
    }

    /**
     * 获得注销标识
     */
    public String getZxbs() {
        return zxbs;
    }

    /**
     * 获得层次标识
     */
    public String getCcbs() {
        return ccbs;
    }

    /**
     * 赋值税种税目代码
     * @param szsmdm 税种税目代码
     */
    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    /**
     * 赋值税种税目名称
     * @param szsmmc 税种税目名称
     */
    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }

    /**
     * 赋值税率
     * @param sl 税率
     */
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    /**
     * 赋值计税基数
     * @param jsjs 计税基数
     */
    public void setJsjs(BigDecimal jsjs) {
        this.jsjs = jsjs;
    }

    /**
     * 赋值速算扣除数
     * @param sskcs 速算扣除数
     */
    public void setSskcs(BigDecimal sskcs) {
        this.sskcs = sskcs;
    }

    /**
     * 赋值减费用额
     * @param jfys 减费用额
     */
    public void setJfys(BigDecimal jfys) {
        this.jfys = jfys;
    }

    /**
     * 赋值计量单位
     * @param jldw 计量单位
     */
    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    /**
     * 赋值执行开始日期
     * @param zxksrq 执行开始日期
     */
    public void setZxksrq(Timestamp zxksrq) {
        this.zxksrq = zxksrq;
    }

    /**
     * 赋值执行结束日期
     * @param zxjsrq 执行结束日期
     */
    public void setZxjsrq(Timestamp zxjsrq) {
        this.zxjsrq = zxjsrq;
    }

    /**
     * 赋值按数量计标识
     * @param asljbs 按数量计标识
     */
    public void setAsljbs(String asljbs) {
        this.asljbs = asljbs;
    }

    /**
     * 赋值应纳税起始数
     * @param ynsqss 应纳税起始数
     */
    public void setYnsqss(BigDecimal ynsqss) {
        this.ynsqss = ynsqss;
    }

    /**
     * 赋值应纳税终止数
     * @param ynszzs 应纳税终止数
     */
    public void setYnszzs(BigDecimal ynszzs) {
        this.ynszzs = ynszzs;
    }

    /**
     * 赋值计算公式
     * @param jsgs 计算公式
     */
    public void setJsgs(String jsgs) {
        this.jsgs = jsgs;
    }

    /**
     * 赋值数据来源字段
     * @param sjlyzd 数据来源字段
     */
    public void setSjlyzd(String sjlyzd) {
        this.sjlyzd = sjlyzd;
    }

    /**
     * 赋值界面字段可编辑标志
     * @param zdkbjbz 界面字段可编辑标志
     */
    public void setZdkbjbz(String zdkbjbz) {
        this.zdkbjbz = zdkbjbz;
    }

    /**
     * 赋值拼音索引
     * @param pysy 拼音索引
     */
    public void setPysy(String pysy) {
        this.pysy = pysy;
    }

    /**
     * 赋值备注
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 赋值是否附加税
     * @param sffjs 是否附加税
     */
    public void setSffjs(String sffjs) {
        this.sffjs = sffjs;
    }

    /**
     * 赋值征期类型代码
     * @param zqlxdm 征期类型代码
     */
    public void setZqlxdm(String zqlxdm) {
        this.zqlxdm = zqlxdm;
    }

    /**
     * 赋值父节点代码
     * @param fjddm 父节点代码
     */
    public void setFjddm(String fjddm) {
        this.fjddm = fjddm;
    }

    /**
     * 赋值不含税所得税率
     * @param bhsdssl 不含税所得税率
     */
    public void setBhsdssl(BigDecimal bhsdssl) {
        this.bhsdssl = bhsdssl;
    }

    /**
     * 赋值不含税所得起始
     * @param bhsdsqs 不含税所得起始
     */
    public void setBhsdsqs(BigDecimal bhsdsqs) {
        this.bhsdsqs = bhsdsqs;
    }

    /**
     * 赋值不含税所得终止
     * @param bhsdszz 不含税所得终止
     */
    public void setBhsdszz(BigDecimal bhsdszz) {
        this.bhsdszz = bhsdszz;
    }

    /**
     * 赋值不含税所得速算扣除数
     * @param bhsdskcs 不含税所得速算扣除数
     */
    public void setBhsdskcs(BigDecimal bhsdskcs) {
        this.bhsdskcs = bhsdskcs;
    }

    /**
     * 赋值注销标识
     * @param zxbs 注销标识
     */
    public void setZxbs(String zxbs) {
        this.zxbs = zxbs;
    }

    /**
     * 赋值层次标识
     * @param ccbs 层次标识
     */
    public void setCcbs(String ccbs) {
        this.ccbs = ccbs;
    }


}
