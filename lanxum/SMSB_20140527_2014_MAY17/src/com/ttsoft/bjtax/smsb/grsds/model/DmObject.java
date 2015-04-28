package com.ttsoft.bjtax.smsb.grsds.model;

import java.io.Serializable;

public class DmObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dm;
	private String name;

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
