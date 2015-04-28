package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba13BViewWnVO implements JmbamxVoInterface {

    private String xh;
    private String band;
    private String gxjslydm;
    private String gxjslymc;
    private String btzqyjsjdm;
    private String btzqyswdjzh;
    private String btzqymc;
    private String basqwsh;
    private String last_one_year;
    private String last_one_tze;
    private String last_one_dke;

    private String last_two_year;
    private String last_two_tze;
    private String last_two_dke;

    private String last_three_year;
    private String last_three_tze;
    private String last_three_dke;

    private String last_four_year;
    private String last_four_tze;
    private String last_four_dke;





    public Jmba13BViewWnVO() {
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
        String xmlstr = "";
        xmlstr += "<wnqysdsjmba>";

        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("gxjslydm", gxjslydm);
        xmlstr += XMLBuildUtil.appendStringElement("gxjslymc", gxjslymc);
        xmlstr += XMLBuildUtil.appendStringElement("btzqyjsjdm", btzqyjsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("btzqyswdjzh", btzqyswdjzh);
        xmlstr += XMLBuildUtil.appendStringElement("btzqymc", btzqymc);
        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
        xmlstr += XMLBuildUtil.appendStringElement("last_one_year", last_one_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_one_tze", last_one_tze);
        xmlstr += XMLBuildUtil.appendStringElement("last_one_dke", last_one_dke);
        xmlstr += XMLBuildUtil.appendStringElement("last_two_year", last_two_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_two_tze", last_two_tze);
        xmlstr += XMLBuildUtil.appendStringElement("last_two_dke", last_two_dke);
        xmlstr += XMLBuildUtil.appendStringElement("last_three_year", last_three_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_three_tze", last_three_tze);
        xmlstr += XMLBuildUtil.appendStringElement("last_three_dke", last_three_dke);
        xmlstr += XMLBuildUtil.appendStringElement("last_four_year", last_four_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_four_tze", last_four_tze);
        xmlstr += XMLBuildUtil.appendStringElement("last_four_dke", last_four_dke);
        xmlstr += "</wnqysdsjmba>";

        return xmlstr;

    }

    /**
     * toXMLChilds
     *
     * @return String
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public String toXMLChilds() {
        return "";
    }

    public String getXh() {
        return xh;
    }

    public String getLast_two_year() {
        return last_two_year;
    }

    public String getLast_two_tze() {
        return last_two_tze;
    }

    public String getLast_two_dke() {
        return last_two_dke;
    }

    public String getLast_three_year() {
        return last_three_year;
    }

    public String getLast_three_tze() {
        return last_three_tze;
    }

    public String getLast_three_dke() {
        return last_three_dke;
    }

    public String getLast_one_year() {
        return last_one_year;
    }

    public String getLast_one_tze() {
        return last_one_tze;
    }

    public String getLast_one_dke() {
        return last_one_dke;
    }

    public String getLast_four_year() {
        return last_four_year;
    }

    public String getLast_four_tze() {
        return last_four_tze;
    }

    public String getLast_four_dke() {
        return last_four_dke;
    }

    public String getGxjslymc() {
        return gxjslymc;
    }

    public String getGxjslydm() {
        return gxjslydm;
    }

    public String getBtzqyswdjzh() {
        return btzqyswdjzh;
    }

    public String getBtzqymc() {
        return btzqymc;
    }

    public String getBtzqyjsjdm() {
        return btzqyjsjdm;
    }

    public String getBand() {
        return band;
    }

    public String getBasqwsh() {
        return basqwsh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setLast_two_year(String last_two_year) {
        this.last_two_year = last_two_year;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setBtzqyjsjdm(String btzqyjsjdm) {
        this.btzqyjsjdm = btzqyjsjdm;
    }

    public void setBtzqymc(String btzqymc) {
        this.btzqymc = btzqymc;
    }

    public void setBtzqyswdjzh(String btzqyswdjzh) {
        this.btzqyswdjzh = btzqyswdjzh;
    }

    public void setGxjslydm(String gxjslydm) {
        this.gxjslydm = gxjslydm;
    }

    public void setGxjslymc(String gxjslymc) {
        this.gxjslymc = gxjslymc;
    }

    public void setLast_four_dke(String last_four_dke) {
        this.last_four_dke = last_four_dke;
    }

    public void setLast_four_tze(String last_four_tze) {
        this.last_four_tze = last_four_tze;
    }

    public void setLast_four_year(String last_four_year) {
        this.last_four_year = last_four_year;
    }

    public void setLast_one_dke(String last_one_dke) {
        this.last_one_dke = last_one_dke;
    }

    public void setLast_one_tze(String last_one_tze) {
        this.last_one_tze = last_one_tze;
    }

    public void setLast_one_year(String last_one_year) {
        this.last_one_year = last_one_year;
    }

    public void setLast_three_dke(String last_three_dke) {
        this.last_three_dke = last_three_dke;
    }

    public void setLast_three_tze(String last_three_tze) {
        this.last_three_tze = last_three_tze;
    }

    public void setLast_three_year(String last_three_year) {
        this.last_three_year = last_three_year;
    }

    public void setLast_two_dke(String last_two_dke) {
        this.last_two_dke = last_two_dke;
    }

    public void setLast_two_tze(String last_two_tze) {
        this.last_two_tze = last_two_tze;
    }

    public void setBasqwsh(String basqwsh) {
        this.basqwsh = basqwsh;
    }
}
