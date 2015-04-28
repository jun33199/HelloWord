package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;

public class JmsVO extends YWRootVO implements XMLVOInterface {
	/**
     * 纳税人信息
     */
    private NsrxxVO_JMS nsrxx = new NsrxxVO_JMS();

    /**
     * 申报信息
     */
    private SbxxVO_JMS sbxx = new SbxxVO_JMS();
    
    /**
     * 申报数据列表
     */
    private SbsjlistVO sbsjlist=new SbsjlistVO();
    
	public JmsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
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
	     xmlstr+=sbsjlist.toXML();
	     return xmlstr;
	}

	public NsrxxVO_JMS getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(NsrxxVO_JMS nsrxx) {
		this.nsrxx = nsrxx;
	}

	public SbsjlistVO getSbsjlist() {
		return sbsjlist;
	}

	public void setSbsjlist(SbsjlistVO sbsjlist) {
		this.sbsjlist = sbsjlist;
	}

	public SbxxVO_JMS getSbxx() {
		return sbxx;
	}

	public void setSbxx(SbxxVO_JMS sbxx) {
		this.sbxx = sbxx;
	}

}
