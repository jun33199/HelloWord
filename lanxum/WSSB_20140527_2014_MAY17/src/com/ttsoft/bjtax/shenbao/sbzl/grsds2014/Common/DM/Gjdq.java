/**
 * @Title:       Gjdq.java
 * @Description: TODO
 * @date:        2014-12-6上午11:46:06
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.DM;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @Description: TODO 国际地区代码表
 * @author: 	 Lijn
 * @time:        2014-12-6
 */
public class Gjdq implements XMLVOInterface{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String value;

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<gj>");
		sb.append(XMLBuildUtil.appendStringElement("value", this.value));
		sb.append(XMLBuildUtil.appendStringElement("text", this.text));
		sb.append("</gj>");
		return sb.toString();
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description: getter-- text
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @description: setter-- text
	 * @param text the text to set
	 */
	public final void setText(String text) {
		this.text = text;
	}

	/**
	 * @description: getter-- value
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @description: setter-- value
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	
	
}
