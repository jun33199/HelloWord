package com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Hdxx12VO implements XMLVOInterface
{
    
    private String zsffdm ;

    public Hdxx12VO()
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
        xmlstr += XMLBuildUtil.appendStringElement("zsffdm",zsffdm);
        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }
	/**
	 * @return Returns the zsffdm.
	 */
	public String getZsffdm() {
		return zsffdm;
	}
	/**
	 * @param zsffdm The zsffdm to set.
	 */
	public void setZsffdm(String zsffdm) {
		this.zsffdm = zsffdm;
	}

}
