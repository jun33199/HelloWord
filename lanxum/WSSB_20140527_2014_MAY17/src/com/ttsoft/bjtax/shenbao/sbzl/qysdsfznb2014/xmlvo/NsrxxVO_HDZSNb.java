package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class NsrxxVO_HDZSNb extends NsrxxVO implements XMLVOInterface {
	/**
     * 纳税人识别号
     */
	private String nsrsbh="";
	private String sybs="";
	public NsrxxVO_HDZSNb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toXMLChilds() {

        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm",super.getJsjdm());
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc",super.getNsrmc());
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",super.getSwjgzzjgdm());
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh",nsrsbh);
        xmlstr += XMLBuildUtil.appendStringElement("sybs",sybs);
        return xmlstr;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getSybs() {
		return sybs;
	}
	public void setSybs(String sybs) {
		this.sybs = sybs;
	}
}
