package com.ttsoft.bjtax.shenbao.fangtu;

public class LabelValueBean implements java.io.Serializable {
	private String label;
	private String value;
	
	
	public LabelValueBean(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	public LabelValueBean() {
		super();
		// TODO Auto-generated constructor stub
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
