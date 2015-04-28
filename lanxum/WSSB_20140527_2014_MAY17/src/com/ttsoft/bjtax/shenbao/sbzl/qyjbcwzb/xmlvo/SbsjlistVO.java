package com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbsjlistVO implements XMLVOInterface {

	private SbsjVO sbsj=new SbsjVO();
	public SbsjlistVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
/*		Map map = new HashMap();
    	map.put("sbsj","com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.SbsjVO");
        return map;*/
		return null;
	}

	public String toXML() {
		String xmlstr ="<sbsjlist>";
		xmlstr+=sbsj.toXML();
		xmlstr+="</sbsjlist>";

		return xmlstr;
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public SbsjVO getSbsj() {
		return sbsj;
	}

	public void setSbsj(SbsjVO sbsj) {
		this.sbsj = sbsj;
	}


}
