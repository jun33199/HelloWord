package com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;


public class YhsbgmxVO extends YWRootVO implements XMLVOInterface {

	/**
     * 纳税人信息
     */
    private NsrxxVO_YHS nsrxx = new NsrxxVO_YHS();

    /**
     * 申报信息
     */
    private SbxxVO_YHS sbxx = new SbxxVO_YHS();
    
    /**
     * 申报数据列表
     */
    private SbsjlistVO sbsjlist=new SbsjlistVO();
    
	public YhsbgmxVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		Map map = new HashMap();
    	map.put("sbsjlist","com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.SbsjlistVO");
        return map;
	}

	public String toXML() {
		String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
	}

	public String toXMLChilds() {
		 String xmlstr = "";
	     xmlstr += nsrxx.toXML();
	     xmlstr += sbxx.toXML();
	     xmlstr+=sbsjlist.toXML();
	     return xmlstr;
	}

	public NsrxxVO_YHS getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(NsrxxVO_YHS nsrxx) {
		this.nsrxx = nsrxx;
	}

	public SbsjlistVO getSbsjlist() {
		return sbsjlist;
	}

	public void setSbsjlist(SbsjlistVO sbsjlist) {
		this.sbsjlist = sbsjlist;
	}

	public SbxxVO_YHS getSbxx() {
		return sbxx;
	}

	public void setSbxx(SbxxVO_YHS sbxx) {
		this.sbxx = sbxx;
	}

}
