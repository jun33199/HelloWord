package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;
/**
 * 
 * <p>Title: </p>
 * <p>Description:减免税证明Form </p>
 * @author 开发部-霍洪波
 * @version 1.0
 */
public class GdzysJmszmPrintForm extends BaseForm{
	private List jmszmList;//减免税证明信息List
	public GdzysJmszmPrintForm(){
		
	}
	public List getJmszmList() {
		return jmszmList;
	}
	public void setJmszmList(List jmszmList) {
		this.jmszmList = jmszmList;
	}
	

}
