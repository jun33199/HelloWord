/**
 * @Title:       Sfzjlxdm.java
 * @Description: TODO
 * @date:        2014-11-28下午05:20:11
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @Description: TODO 身份证件类型代码表
 * @author: 	 Lijn
 * @time:        2014-11-28
 */
public class Sfzjlx implements XMLVOInterface{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String value;
	
	
	
	
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
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<sfzjlx>");
		sb.append(XMLBuildUtil.appendStringElement("value", this.value));
		sb.append(XMLBuildUtil.appendStringElement("text", this.text));
		sb.append("</sfzjlx>");
		return sb.toString();
	}
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
