/**
 * @Title:       Gjdq.java
 * @Description: TODO
 * @date:        2014-12-6上午11:46:06
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.DM;

import java.io.Serializable;

/**
 * @Description: TODO 国际地区代码表
 * @author: 	 Lijn
 * @time:        2014-12-6
 */
public class GjdqDM implements Serializable{

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
