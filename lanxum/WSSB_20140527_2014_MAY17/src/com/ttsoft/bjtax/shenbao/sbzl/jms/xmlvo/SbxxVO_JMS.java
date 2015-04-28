package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbxxVO_JMS extends SbxxVO implements XMLVOInterface {

	public SbxxVO_JMS() {
		super();
		// TODO Auto-generated constructor stub
	}
	 public String toXMLChilds()
	 {
	     String xmlstr = "";
	        
	     xmlstr += XMLBuildUtil.appendStringElement("fsdm",super.getFsdm());
	     xmlstr += XMLBuildUtil.appendStringElement("nd",super.getNd());
	     xmlstr += XMLBuildUtil.appendStringElement("sbrq",super.getSbrq());
	     //xmlstr += XMLBuildUtil.appendStringElement("skssksrq",super.getSkssksrq());
	     //xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",super.getSkssjsrq());    
	     return xmlstr;
	 }
}
