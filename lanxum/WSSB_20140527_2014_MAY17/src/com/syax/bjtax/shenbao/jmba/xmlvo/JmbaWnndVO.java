package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.*;

import com.syax.common.xml.util.*;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;

public class JmbaWnndVO implements JmbamxVoInterface {
    private String last_one_year;
    private String last_two_year;
    private String last_three_year;
    private String last_four_year;
    private String title;
    public JmbaWnndVO() {
    }

    public JmbaWnndVO(String title) {
        this.title = title;
        int band = 0;
        if(this.title.equals("wnband")){
            band = Integer.parseInt(DateUtilPro.getCurYearStr4())-2;
        }else if(this.title.equals("dnband")){
            band = Integer.parseInt(DateUtilPro.getCurYearStr4())-1;
        }else{

        }
        this.last_one_year = band+"";
        this.last_two_year = (band - 1)+"";
        this.last_three_year = (band - 2)+"";
        this.last_four_year = (band -3)+"";
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
        xmlstr += "<"+this.title+">";

        xmlstr += XMLBuildUtil.appendStringElement("last_one_year", last_one_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_two_year", last_two_year );
        xmlstr += XMLBuildUtil.appendStringElement("last_three_year",last_three_year);
        xmlstr += XMLBuildUtil.appendStringElement("last_four_year", last_four_year);

        xmlstr += "</"+this.title+">";
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

    public String getLast_four_year() {
        return last_four_year;
    }

    public String getLast_one_year() {
        return last_one_year;
    }

    public String getLast_three_year() {
        return last_three_year;
    }

    public String getLast_two_year() {
        return last_two_year;
    }

    public String getTitle() {
        return title;
    }

    public void setLast_four_year(String last_four_year) {
        this.last_four_year = last_four_year;
    }

    public void setLast_one_year(String last_one_year) {
        this.last_one_year = last_one_year;
    }

    public void setLast_three_year(String last_three_year) {
        this.last_three_year = last_three_year;
    }

    public void setLast_two_year(String last_two_year) {
        this.last_two_year = last_two_year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
