package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Hdxx02VO implements XMLVOInterface
{
    

    public Hdxx02VO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public String toXML()
    {
        String xmlstr = "<hdxx>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</hdxx>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

}
