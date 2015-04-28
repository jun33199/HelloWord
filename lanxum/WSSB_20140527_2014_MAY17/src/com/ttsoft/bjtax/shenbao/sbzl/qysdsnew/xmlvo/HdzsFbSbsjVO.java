package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.XMLBuildUtil;

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
public class HdzsFbSbsjVO implements XMLVOInterface {
    private String jmshj_je="";
    private String xxwl_je="";
    private String gxqy_je="";
    private String mzzz_je="";
    private String gdqyh_je="";
    private String qt_je="";
    private String qyrs="";
    private String zcze="";
    private String sshy="";
    private String sbrqshow="";
    private String zbYnsdse="";

    public HdzsFbSbsjVO() {
        super();
    }

    /**
     * getListTypeMap
     *
     * @return Map
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public Map getListTypeMap() {
        return null;
    }

    /**
     * toXML
     *
     * @return String
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public String toXML() {
        String xmlstr = "<sbsj>";

        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
    }

    /**
     * toXMLChilds
     *
     * @return String
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public String toXMLChilds() {
        String xmlstr = "";

        xmlstr += XMLBuildUtil.appendStringElement("sbrqshow",sbrqshow);
        xmlstr += XMLBuildUtil.appendStringElement("jmshj_je",jmshj_je);
        xmlstr += XMLBuildUtil.appendStringElement("xxwl_je",xxwl_je);
        xmlstr += XMLBuildUtil.appendStringElement("gxqy_je",gxqy_je);
        xmlstr += XMLBuildUtil.appendStringElement("mzzz_je",mzzz_je);
        xmlstr += XMLBuildUtil.appendStringElement("gdqyh_je",gdqyh_je);
        xmlstr += XMLBuildUtil.appendStringElement("qt_je",qt_je);
        xmlstr += XMLBuildUtil.appendStringElement("qyrs",qyrs);
        xmlstr += XMLBuildUtil.appendStringElement("zcze",zcze);
        xmlstr += XMLBuildUtil.appendStringElement("sshy",sshy);
        xmlstr += XMLBuildUtil.appendStringElement("zbYnsdse",zbYnsdse);
        return xmlstr;
    }

    public String getGdqyh_je() {
        return gdqyh_je;
    }

    public String getGxqy_je() {
        return gxqy_je;
    }

    public String getJmshj_je() {
        return jmshj_je;
    }

    public String getMzzz_je() {
        return mzzz_je;
    }

    public String getQt_je() {
        return qt_je;
    }

    public String getQyrs() {
        return qyrs;
    }

    public String getSbrqshow() {
        return sbrqshow;
    }

    public String getSshy() {
        return sshy;
    }

    public String getXxwl_je() {
        return xxwl_je;
    }

    public String getZcze() {
        return zcze;
    }

    public void setGdqyh_je(String gdqyh_je) {
        this.gdqyh_je = gdqyh_je;
    }

    public void setGxqy_je(String gxqy_je) {
        this.gxqy_je = gxqy_je;
    }

    public void setJmshj_je(String jmshj_je) {
        this.jmshj_je = jmshj_je;
    }

    public void setMzzz_je(String mzzz_je) {
        this.mzzz_je = mzzz_je;
    }

    public void setQt_je(String qt_je) {
        this.qt_je = qt_je;
    }

    public void setSbrqshow(String sbrqshow) {
        this.sbrqshow = sbrqshow;
    }

    public void setQyrs(String qyrs) {
        this.qyrs = qyrs;
    }

    public void setSshy(String sshy) {
        this.sshy = sshy;
    }

    public void setXxwl_je(String xxwl_je) {
        this.xxwl_je = xxwl_je;
    }

    public void setZcze(String zcze) {
        this.zcze = zcze;
    }

	public String getZbYnsdse() {
		return zbYnsdse;
	}

	public void setZbYnsdse(String zbYnsdse) {
		this.zbYnsdse = zbYnsdse;
	}
}
