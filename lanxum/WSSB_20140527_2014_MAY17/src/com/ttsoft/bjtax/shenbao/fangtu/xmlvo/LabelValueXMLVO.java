package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class LabelValueXMLVO implements XMLVOInterface{

    private String label;
    private String value;
    
	public LabelValueXMLVO() {
		super();
	}

	public LabelValueXMLVO(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
        String xmlstr = "";
        xmlstr += toXMLChilds();
        return xmlstr;
	}

	public String toXMLChilds() {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("label",label);
        xmlstr += XMLBuildUtil.appendStringElement("value",value);
		
        return xmlstr;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
