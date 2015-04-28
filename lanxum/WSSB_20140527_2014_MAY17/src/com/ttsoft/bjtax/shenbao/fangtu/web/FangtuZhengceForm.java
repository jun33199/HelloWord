package com.ttsoft.bjtax.shenbao.fangtu.web;

import com.ttsoft.bjtax.shenbao.model.domain.JMZC;
import com.ttsoft.framework.form.BaseForm;

public class FangtuZhengceForm extends BaseForm{
	private JMZC jmzc = new JMZC();

	public JMZC getJmzc() {
		return jmzc;
	}

	public void setJmzc(JMZC jmzc) {
		this.jmzc = jmzc;
	}
	
}
