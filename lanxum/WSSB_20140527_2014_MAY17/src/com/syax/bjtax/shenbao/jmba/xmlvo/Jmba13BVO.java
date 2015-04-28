package com.syax.bjtax.shenbao.jmba.xmlvo;


import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba13BVO  implements JmbamxVoInterface {


    private String XH = "";
    private String ROW = "";
    private String BASQWSH = "";
    private String JSJDM = "";
    private String BAND = "";
    private String SWJGZZJGDM = "";
    private String GXJSLYDM = "";
    private String BTZQYJSJDM = "";
    private String BTZQYSWDJZH = "";
    private String BTZQYMC = "";
    private String BTZQYSSD = "";
    private String TZND = "";
    private String TZE = "";
    private String DKE = "";
    private String SHBJ = "";
    private String ZCBA = "";
    private String CJR = "";
    private String CJRQ = "";
    private String LRR = "";
    private String LRRQ = "";


    public Jmba13BVO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {
        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("XH", XH);
        xmlstr += XMLBuildUtil.appendStringElement("ROW", ROW);
        xmlstr += XMLBuildUtil.appendStringElement("BASQWSH", BASQWSH);
        xmlstr += XMLBuildUtil.appendStringElement("JSJDM", JSJDM);
        xmlstr += XMLBuildUtil.appendStringElement("BAND", BAND);

        xmlstr += XMLBuildUtil.appendStringElement("SWJGZZJGDM", SWJGZZJGDM);
        xmlstr += XMLBuildUtil.appendStringElement("GXJSLYDM", GXJSLYDM);
        xmlstr += XMLBuildUtil.appendStringElement("BTZQYJSJDM", BTZQYJSJDM);
        xmlstr += XMLBuildUtil.appendStringElement("BTZQYSWDJZH", BTZQYSWDJZH);
        xmlstr += XMLBuildUtil.appendStringElement("BTZQYMC", BTZQYMC);
        xmlstr += XMLBuildUtil.appendStringElement("BTZQYSSD", BTZQYSSD);
        xmlstr += XMLBuildUtil.appendStringElement("TZND", TZND);
        xmlstr += XMLBuildUtil.appendStringElement("TZE", TZE);
        xmlstr += XMLBuildUtil.appendStringElement("DKE", DKE);
        xmlstr += XMLBuildUtil.appendStringElement("SHBJ", SHBJ);
        xmlstr += XMLBuildUtil.appendStringElement("ZCBA", ZCBA);
        xmlstr += XMLBuildUtil.appendStringElement("CJR", CJR);

        xmlstr += XMLBuildUtil.appendStringElement("CJRQ", CJRQ);
        xmlstr += XMLBuildUtil.appendStringElement("LRR", LRR);
        xmlstr += XMLBuildUtil.appendStringElement("LRRQ", LRRQ);
        xmlstr += "</qysdsjmba>";


        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

    public String getBAND() {
        return BAND;
    }

    public String getBASQWSH() {
        return BASQWSH;
    }

    public String getBTZQYJSJDM() {
        return BTZQYJSJDM;
    }

    public String getBTZQYMC() {
        return BTZQYMC;
    }

    public String getBTZQYSSD() {
        return BTZQYSSD;
    }

    public String getBTZQYSWDJZH() {
        return BTZQYSWDJZH;
    }

    public String getCJR() {
        return CJR;
    }

    public String getCJRQ() {
        return CJRQ;
    }

    public String getDKE() {
        return DKE;
    }

    public String getGXJSLYDM() {
        return GXJSLYDM;
    }

    public String getJSJDM() {
        return JSJDM;
    }

    public void setBAND(String BAND) {
        this.BAND = BAND;
    }

    public void setBASQWSH(String BASQWSH) {
        this.BASQWSH = BASQWSH;
    }

    public void setBTZQYJSJDM(String BTZQYJSJDM) {
        this.BTZQYJSJDM = BTZQYJSJDM;
    }

    public void setBTZQYMC(String BTZQYMC) {
        this.BTZQYMC = BTZQYMC;
    }

    public void setBTZQYSSD(String BTZQYSSD) {
        this.BTZQYSSD = BTZQYSSD;
    }

    public void setBTZQYSWDJZH(String BTZQYSWDJZH) {
        this.BTZQYSWDJZH = BTZQYSWDJZH;
    }

    public void setCJR(String CJR) {
        this.CJR = CJR;
    }

    public void setCJRQ(String CJRQ) {
        this.CJRQ = CJRQ;
    }

    public void setDKE(String DKE) {
        this.DKE = DKE;
    }

    public void setGXJSLYDM(String GXJSLYDM) {
        this.GXJSLYDM = GXJSLYDM;
    }

    public void setJSJDM(String JSJDM) {
        this.JSJDM = JSJDM;
    }

    public void setLRR(String LRR) {
        this.LRR = LRR;
    }

    public String getLRR() {
        return LRR;
    }

    public String getLRRQ() {
        return LRRQ;
    }

    public String getROW() {
        return ROW;
    }

    public String getSHBJ() {
        return SHBJ;
    }

    public String getSWJGZZJGDM() {
        return SWJGZZJGDM;
    }

    public String getTZE() {
        return TZE;
    }

    public String getTZND() {
        return TZND;
    }

    public String getXH() {
        return XH;
    }

    public String getZCBA() {
        return ZCBA;
    }

    public void setLRRQ(String LRRQ) {
        this.LRRQ = LRRQ;
    }

    public void setROW(String ROW) {
        this.ROW = ROW;
    }

    public void setSHBJ(String SHBJ) {
        this.SHBJ = SHBJ;
    }

    public void setSWJGZZJGDM(String SWJGZZJGDM) {
        this.SWJGZZJGDM = SWJGZZJGDM;
    }

    public void setTZE(String TZE) {
        this.TZE = TZE;
    }

    public void setTZND(String TZND) {
        this.TZND = TZND;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public void setZCBA(String ZCBA) {
        this.ZCBA = ZCBA;
    }

}
