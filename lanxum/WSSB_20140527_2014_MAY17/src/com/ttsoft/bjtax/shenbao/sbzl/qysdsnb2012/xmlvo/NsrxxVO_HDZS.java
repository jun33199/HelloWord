package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class NsrxxVO_HDZS extends NsrxxVO implements XMLVOInterface {
	/**
     * 纳税人识别号
     */
	private String nsrsbh="";
	public NsrxxVO_HDZS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toXMLChilds() {

        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm",super.getJsjdm());
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc",super.getNsrmc());
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",super.getSwjgzzjgdm());
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh",nsrsbh);
        return xmlstr;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}


}
