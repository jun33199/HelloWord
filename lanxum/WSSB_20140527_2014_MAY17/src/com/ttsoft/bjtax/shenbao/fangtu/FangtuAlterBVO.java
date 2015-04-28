package com.ttsoft.bjtax.shenbao.fangtu;

public class FangtuAlterBVO implements java.io.Serializable {
	
	public static final String OP_NEW = "new";
	public static final String OP_DELETE = "delete";
	public static final String OP_UPDATE = "update";
	public static final String OP_OLD = "old";
	public static final String OP_OLD_UNCHECK = "old_uncheck";
	
	
	
	
	//变更数据
	private Object alterData;
	//登记数据
	private Object regData;
	
	//操作标识: new, delete, update, old, old_uncheck
	private String opFlag;
	
	public Object getAlterData() {
		return alterData;
	}
	public void setAlterData(Object alterData) {
		this.alterData = alterData;
	}
	public Object getRegData() {
		return regData;
	}
	public void setRegData(Object regData) {
		this.regData = regData;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	
	public String toString() {
		String s = "opFlag: " + opFlag + "\n";
		s += "alterData: " + alterData + "\n";
		s += "regData: " + regData + "\n";
		return s;
	}


}
