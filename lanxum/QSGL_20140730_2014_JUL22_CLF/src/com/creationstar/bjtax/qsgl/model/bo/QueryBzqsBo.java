package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

public class QueryBzqsBo implements Serializable {
    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 纳税人类型
     */
    private String nsrlx;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 房地产项目名称
     */
    private String fdcxmmc;

    /**
     * 身份证件类型代码
     */
    private String zjlxdm;

    /**
     * 身份证件号码
     */
    private String sfzjhm;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 个人
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();


    /**
     * 定义涉及到的申报主表、个人信息表，非个人信息表、土地房屋信息表、
     * 申报主表与土地房屋信息表的关联表的值对象
     */
    private Sbzb sbzb;
    private Grxx grxx;
    private Tufwxx tufwxx;
    private Sbtdfwgl sbtdfwg;
    private Fgrxx fgrxx;

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public Grxx getGrxx() {
        return grxx;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Sbtdfwgl getSbtdfwg() {
        return sbtdfwg;
    }

    public Sbzb getSbzb() {
        return sbzb;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }

    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbtdfwg(Sbtdfwgl sbtdfwg) {
        this.sbtdfwg = sbtdfwg;
    }

    public void setSbzb(Sbzb sbzb) {
        this.sbzb = sbzb;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }


    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }
}
