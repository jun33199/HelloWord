/**
 * @Title:       Djzclx.java
 * @Description: TODO
 * @date:        2014-12-15ÏÂÎç06:04:52
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-15
 */
public class Djzclx implements XMLVOInterface{

	private static final long serialVersionUID = 1L;
	private String text;
	private String value;

	

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<djzclx>");
		sb.append(XMLBuildUtil.appendStringElement("value", this.value));
		sb.append(XMLBuildUtil.appendStringElement("text", this.text));
		sb.append("</djzclx>");
		return sb.toString();
	}
	
	
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}


	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @description: getter-- text
	 * @return the text
	 */
	public String getText() {
		return text;
	}


	/**
	 * @description: setter-- text
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}


	/**
	 * @description: getter-- value
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @description: setter-- value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
