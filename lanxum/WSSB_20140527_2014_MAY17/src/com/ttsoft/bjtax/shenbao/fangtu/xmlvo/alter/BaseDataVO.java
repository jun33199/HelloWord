package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class BaseDataVO implements XMLVOInterface {

	//²Ù×÷±êÊ¶
	protected String opFlag;
	protected String deleteFlag;
	protected String updateFlag;

	public BaseDataVO() {
		super();
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	
	public String toXML() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("opFlag", opFlag);
		xmlstr += XMLBuildUtil.appendStringElement("deleteFlag", deleteFlag);
		xmlstr += XMLBuildUtil.appendStringElement("updateFlag", updateFlag);
		
		return xmlstr;
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXMLChilds() {
		return null;
	}
	
	

    

}