package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class HdzssdsFbBO implements Serializable {
    private String jsjdm = "";

    private String nsrmc = "";

    private String swjgzzjgdm = "";

    private String jmzg = "";

    private String ybjmsl = "";

    private String qyzslx = "";

    private String cyl = "";

    private String xzqy = "";

    private String dezsse = "";

    private String fsdm = "";

    private String jd = "";

    private String nd = "";

    private Timestamp sbrq;

    private Timestamp skssjsrq;

    private Timestamp skssksrq;

    private String sbrqshow = "";

    private HashMap sbsj = new HashMap();

    private String jmshj_je = "";
    private String xxwl_je = "";
    private String gxqy_je = "";
    private String mzzz_je = "";
    private String gdqyh_je = "";
    private String qt_je = "";
    private String qyrs = "";
    private String zcze = "";
    private String sshy = "";
    private String lrrdm = "";

    public HdzssdsFbBO() {
    }

    public String getCyl() {
        return cyl;
    }

    public String getDezsse() {
        return dezsse;
    }

    public String getFsdm() {
        return fsdm;
    }

    public String getGdqyh_je() {
        return gdqyh_je;
    }

    public String getGxqy_je() {
        return gxqy_je;
    }

    public String getJd() {
        return jd;
    }

    public String getJmshj_je() {
        return jmshj_je;
    }

    public String getJmzg() {
        return jmzg;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getLrrdm() {
        return lrrdm;
    }

    public String getMzzz_je() {
        return mzzz_je;
    }

    public String getNd() {
        return nd;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getQt_je() {
        return qt_je;
    }

    public String getQyrs() {
        return qyrs;
    }

    public String getQyzslx() {
        return qyzslx;
    }

    public Timestamp getSbrq() {
        return sbrq;
    }

    public String getSbrqshow() {
        return sbrqshow;
    }

    public HashMap getSbsj() {
        return sbsj;
    }

    public Timestamp getSkssjsrq() {
        return skssjsrq;
    }

    public Timestamp getSkssksrq() {
        return skssksrq;
    }

    public String getSshy() {
        return sshy;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getXxwl_je() {
        return xxwl_je;
    }

    public String getXzqy() {
        return xzqy;
    }

    public String getYbjmsl() {
        return ybjmsl;
    }

    public String getZcze() {
        return zcze;
    }

    public void setCyl(String cyl) {
        this.cyl = cyl;
    }

    public void setDezsse(String dezsse) {
        this.dezsse = dezsse;
    }

    public void setFsdm(String fsdm) {
        this.fsdm = fsdm;
    }

    public void setGdqyh_je(String gdqyh_je) {
        this.gdqyh_je = gdqyh_je;
    }

    public void setGxqy_je(String gxqy_je) {
        this.gxqy_je = gxqy_je;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public void setJmshj_je(String jmshj_je) {
        this.jmshj_je = jmshj_je;
    }

    public void setJmzg(String jmzg) {
        this.jmzg = jmzg;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setLrrdm(String lrrdm) {
        this.lrrdm = lrrdm;
    }

    public void setMzzz_je(String mzzz_je) {
        this.mzzz_je = mzzz_je;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setQt_je(String qt_je) {
        this.qt_je = qt_je;
    }

    public void setQyrs(String qyrs) {
        this.qyrs = qyrs;
    }

    public void setQyzslx(String qyzslx) {
        this.qyzslx = qyzslx;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    public void setSbrqshow(String sbrqshow) {
        this.sbrqshow = sbrqshow;
    }

    public void setSbsj(HashMap sbsj) {
        this.sbsj = sbsj;
        System.out.println("========"+sbsj);
        if (sbsj != null&&sbsj.size()>0) {
                this.jmshj_je = sbsj.get("jmshj_je").toString();
                this.xxwl_je = sbsj.get("xxwl_je").toString();
                this.gxqy_je = sbsj.get("gxqy_je").toString();
                this.mzzz_je = sbsj.get("mzzz_je").toString();
                this.gdqyh_je = sbsj.get("gdqyh_je").toString();
                this.qt_je = sbsj.get("qt_je").toString();
                this.qyrs = sbsj.get("qyrs").toString();
                this.zcze = sbsj.get("zcze").toString();
                this.sshy = sbsj.get("sshy").toString();
        }
    }

    public void setSkssjsrq(Timestamp skssjsrq) {
        this.skssjsrq = skssjsrq;
    }

    public void setSkssksrq(Timestamp skssksrq) {
        this.skssksrq = skssksrq;
    }

    public void setSshy(String sshy) {
        this.sshy = sshy;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setXxwl_je(String xxwl_je) {
        this.xxwl_je = xxwl_je;
    }

    public void setXzqy(String xzqy) {
        this.xzqy = xzqy;
    }

    public void setYbjmsl(String ybjmsl) {
        this.ybjmsl = ybjmsl;
    }

    public void setZcze(String zcze) {
        this.zcze = zcze;
    }
}
