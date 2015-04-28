package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CqxxImportErrbvo implements Serializable {
    private String cqrmc; //'拆迁人名称'
    private String cqfw; //'拆迁范围'
    private String bcqrmc; //'被拆迁人名称'
    private String bcqrlxdm; //'被拆迁人类型代码'
    private String bcqrlxmc; //'被拆迁人类型名称'
    private String zjlxdm; //'证件类型代码'
    private String zjlxmc; //'证件类型名称'
    private String zjhm; //'证件号码'
    private String cqxxdz; //'拆迁详细地址'
    private String bcje; //'补偿金额'
    private String bclxdm; //'补偿类型代码'
    private String bclxmc; //'补偿类型名称'
    private String bcmj; //'补偿面积'
    private String bcfwdz; //'补偿房屋地址'
    private String cqxkzh; //'拆迁许可证号'
    private String qxdm; //'区县代码'
    private String lrr; //'录入人'
    private String cjr; //'创建人'
    private Timestamp lrrq; //'录入日期'
    private Timestamp cjrq; //'创建日期'
    private String cqxxbh; //'拆迁信息编号'
    private String sjly; //'数据来源'
    private String szqx; //'所在区县'
    private String swjgzzjgdm; //'税务机关组织机构代码'
    private String cqxmmc; //'拆迁项目名称'
    private String cqmj; //'拆迁面积'
    private String swjgzzjgmc; //'税务机关组织机构名称'

    private String bcjeBegin;
    private String bcjeEnd;
    private String sfwh; //'是否维护查询 0查询1维护'

    private String lrrqBegin; //'录入日期'
    private String lrrqEnd; //'录入日期'

    private String gjrmc; // 共居人名称
    private String zsfwjs; // 正式房屋间数

    private String cwlx; //错误类型
    private String cwlxmc; //错误类型名称

    private String cqxkzspsj; //'拆迁许可证审批时间'

    public String getCwlx() {
        return cwlx;
    }

    public void setCwlx(String cwlx) {
        this.cwlx = cwlx;
    }

    public String getCwlxmc() {
        return cwlxmc;
    }

    public void setCwlxmc(String cwlxmc) {
        this.cwlxmc = cwlxmc;
    }

    public String getBcfwdz() {
        return bcfwdz;
    }


    public String getBclxdm() {
        return bclxdm;
    }

    public String getBclxmc() {
        return bclxmc;
    }


    public String getBcqrlxdm() {
        return bcqrlxdm;
    }

    public String getBcqrlxmc() {
        return bcqrlxmc;
    }

    public String getBcqrmc() {
        return bcqrmc;
    }

    public String getCjr() {
        return cjr;
    }

    public Timestamp getCjrq() {
        return cjrq;
    }

    public String getCqrmc() {
        return cqrmc;
    }

    public String getCqxkzh() {
        return cqxkzh;
    }

    public String getCqxxbh() {
        return cqxxbh;
    }

    public String getCqxxdz() {
        return cqxxdz;
    }

    public String getLrr() {
        return lrr;
    }

    public Timestamp getLrrq() {
        return lrrq;
    }

    public String getQxdm() {
        return qxdm;
    }

    public String getSjly() {
        return sjly;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSzqx() {
        return szqx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getZjlxmc() {
        return zjlxmc;
    }

    public String getCqfw() {
        return cqfw;
    }


    public String getCqxmmc() {
        return cqxmmc;
    }

    public String getBcjeBegin() {
        return bcjeBegin;
    }

    public String getBcjeEnd() {
        return bcjeEnd;
    }

    public String getLrrqBegin() {
        return lrrqBegin;
    }

    public String getLrrqEnd() {
        return lrrqEnd;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public void setSzqx(String szqx) {
        this.szqx = szqx;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public void setLrrq(Timestamp lrrq) {
        this.lrrq = lrrq;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setCqxxdz(String cqxxdz) {
        this.cqxxdz = cqxxdz;
    }

    public void setCqxxbh(String cqxxbh) {
        this.cqxxbh = cqxxbh;
    }

    public void setCqxkzh(String cqxkzh) {
        this.cqxkzh = cqxkzh;
    }

    public void setCqrmc(String cqrmc) {
        this.cqrmc = cqrmc;
    }

    public void setCjrq(Timestamp cjrq) {
        this.cjrq = cjrq;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setBcqrmc(String bcqrmc) {
        this.bcqrmc = bcqrmc;
    }

    public void setBcqrlxmc(String bcqrlxmc) {
        this.bcqrlxmc = bcqrlxmc;
    }

    public void setBcqrlxdm(String bcqrlxdm) {
        this.bcqrlxdm = bcqrlxdm;
    }


    public void setBclxmc(String bclxmc) {
        this.bclxmc = bclxmc;
    }

    public String getBcje() {
        return bcje;
    }

    public void setBcje(String bcje) {
        this.bcje = bcje;
    }

    public String getBcmj() {
        return bcmj;
    }

    public void setBcmj(String bcmj) {
        this.bcmj = bcmj;
    }

    public String getCqmj() {
        return cqmj;
    }

    public void setCqmj(String cqmj) {
        this.cqmj = cqmj;
    }

    public void setBclxdm(String bclxdm) {
        this.bclxdm = bclxdm;
    }


    public void setBcfwdz(String bcfwdz) {
        this.bcfwdz = bcfwdz;
    }

    public void setCqfw(String cqfw) {
        this.cqfw = cqfw;
    }


    public void setCqxmmc(String cqxmmc) {
        this.cqxmmc = cqxmmc;
    }

    public void setBcjeBegin(String bcjeBegin) {
        this.bcjeBegin = bcjeBegin;
    }

    public void setBcjeEnd(String bcjeEnd) {
        this.bcjeEnd = bcjeEnd;
    }

    public void setLrrqBegin(String lrrqBegin) {
        this.lrrqBegin = lrrqBegin;
    }

    public void setLrrqEnd(String lrrqEnd) {
        this.lrrqEnd = lrrqEnd;
    }

    /**
     * @return Returns the sfwh.
     */
    public String getSfwh() {
        return sfwh;
    }

    /**
     * @param sfwh The sfwh to set.
     */
    public void setSfwh(String sfwh) {
        this.sfwh = sfwh;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getCqxkzspsj() {
        return cqxkzspsj;
    }

    public void setCqxkzspsj(String cqxkzspsj) {
        this.cqxkzspsj = cqxkzspsj;
    }

    public String getGjrmc() {
        return gjrmc;
    }

    public void setGjrmc(String gjrmc) {
        this.gjrmc = gjrmc;
    }

    public String getZsfwjs() {
        return zsfwjs;
    }

    public void setZsfwjs(String zsfwjs) {
        this.zsfwjs = zsfwjs;
    }
}
