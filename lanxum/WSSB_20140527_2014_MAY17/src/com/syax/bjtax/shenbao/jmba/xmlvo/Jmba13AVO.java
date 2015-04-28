package com.syax.bjtax.shenbao.jmba.xmlvo;


import java.util.*;

import com.syax.common.xml.util.*;
//13 A
public class Jmba13AVO  implements JmbamxVoInterface {

    private String xh = "";
    private String basqwsh = "";
    private String jsjdm = "";
    private String band = "";
    private String gxjslydm = "";
    private String ctqyzsjbh = "";
    private String btzqymcjzsbh = "";
    private String sfynjhgtzs = "";
    private String sftjtzyzqk = "";
    private String sftjyzzm = "";
    private String sftjwsssm = "";
    private String sfrsyyezcbcx = "";
    private String qtzl = "";
    private String cjr = "";
    private String cjrq = "";
    private String lrr = "";
    private String lrrq = "";
    private String swjgzzjgdm = "";

    public Jmba13AVO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {

        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("gxjslydm", gxjslydm);
        xmlstr += XMLBuildUtil.appendStringElement("ctqyzsjbh", ctqyzsjbh);
        xmlstr += XMLBuildUtil.appendStringElement("btzqymcjzsbh", btzqymcjzsbh);
        xmlstr += XMLBuildUtil.appendStringElement("sfynjhgtzs", sfynjhgtzs);
        xmlstr += XMLBuildUtil.appendStringElement("sftjtzyzqk", sftjtzyzqk);
        xmlstr += XMLBuildUtil.appendStringElement("sftjyzzm", sftjyzzm);
        xmlstr += XMLBuildUtil.appendStringElement("sftjwsssm", sftjwsssm);
        xmlstr += XMLBuildUtil.appendStringElement("sfrsyyezcbcx", sfrsyyezcbcx);
        xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
        xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
        xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += "</qysdsjmba>";

        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

    public String getBand() {
        return band;
    }

    public String getBasqwsh() {
        return basqwsh;
    }

    public String getBtzqymcjzsbh() {
        return btzqymcjzsbh;
    }

    public String getCjr() {
        return cjr;
    }

    public String getCjrq() {
        return cjrq;
    }

    public String getCtqyzsjbh() {
        return ctqyzsjbh;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getGxjslydm() {
        return gxjslydm;
    }

    public String getLrr() {
        return lrr;
    }

    public String getLrrq() {
        return lrrq;
    }

    public String getQtzl() {
        return qtzl;
    }

    public String getSfrsyyezcbcx() {
        return sfrsyyezcbcx;
    }

    public String getSftjtzyzqk() {
        return sftjtzyzqk;
    }

    public String getSftjwsssm() {
        return sftjwsssm;
    }

    public String getSftjyzzm() {
        return sftjyzzm;
    }

    public String getSfynjhgtzs() {
        return sfynjhgtzs;
    }

    public String getXh() {
        return xh;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setLrrq(String lrrq) {
        this.lrrq = lrrq;
    }

    public void setGxjslydm(String gxjslydm) {
        this.gxjslydm = gxjslydm;
    }

    public void setCtqyzsjbh(String ctqyzsjbh) {
        this.ctqyzsjbh = ctqyzsjbh;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public void setBtzqymcjzsbh(String btzqymcjzsbh) {
        this.btzqymcjzsbh = btzqymcjzsbh;
    }

    public void setBasqwsh(String basqwsh) {
        this.basqwsh = basqwsh;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setQtzl(String qtzl) {
        this.qtzl = qtzl;
    }

    public void setSfrsyyezcbcx(String sfrsyyezcbcx) {
        this.sfrsyyezcbcx = sfrsyyezcbcx;
    }

    public void setSftjtzyzqk(String sftjtzyzqk) {
        this.sftjtzyzqk = sftjtzyzqk;
    }

    public void setSftjwsssm(String sftjwsssm) {
        this.sftjwsssm = sftjwsssm;
    }

    public void setSftjyzzm(String sftjyzzm) {
        this.sftjyzzm = sftjyzzm;
    }

    public void setSfynjhgtzs(String sfynjhgtzs) {
        this.sfynjhgtzs = sfynjhgtzs;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }
}
