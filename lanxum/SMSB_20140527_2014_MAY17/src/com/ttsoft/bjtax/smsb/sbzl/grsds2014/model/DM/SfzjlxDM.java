/**
 * @Title:       Sfzjlxdm.java
 * @Description: TODO
 * @date:        2014-11-28����05:20:11
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM;

import java.io.Serializable;

/**
 * @Description: TODO ���֤�����ʹ����
 * @author: 	 Lijn
 * @time:        2014-11-28
 */
public class SfzjlxDM implements Serializable{

	/**
	 * Description��
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
	
}
