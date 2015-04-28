package com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;

public class QycwzbVO extends YWRootVO implements XMLVOInterface {
	/**
     * 纳税人信息
     */
    private NsrxxVO_QYCW nsrxx = new NsrxxVO_QYCW();

    /**
     * 申报信息
     */
    private SbxxVO_QYCW sbxx = new SbxxVO_QYCW();
    
    /**
     * 申报数据列表
     */
    private SbsjlistVO sbsjlist=new SbsjlistVO();
   
	public QycwzbVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
        return null;
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
	     xmlstr += sbsjlist.toXML();
	     return xmlstr;
	}

	public NsrxxVO_QYCW getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(NsrxxVO_QYCW nsrxx) {
		this.nsrxx = nsrxx;
	}

	public SbxxVO_QYCW getSbxx() {
		return sbxx;
	}

	public void setSbxx(SbxxVO_QYCW sbxx) {
		this.sbxx = sbxx;
	}

	public SbsjlistVO getSbsjlist() {
		return sbsjlist;
	}

	public void setSbsjlist(SbsjlistVO sbsjlist) {
		this.sbsjlist = sbsjlist;
	}

}
