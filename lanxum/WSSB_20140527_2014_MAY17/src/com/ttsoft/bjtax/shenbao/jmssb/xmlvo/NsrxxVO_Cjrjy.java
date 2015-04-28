package com.ttsoft.bjtax.shenbao.jmssb.xmlvo;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class NsrxxVO_Cjrjy extends NsrxxVO implements XMLVOInterface {
	/**
	 * 税务登记帐号
	 */
	private String swdjzh;
	/**
	 * 注册地址
	 */
	private String zcdz;
	/**
	 * 经营范围
	 */
	private String jyfw;

	public NsrxxVO_Cjrjy() {
		super();
	}
    
	
	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", super.getJsjdm());
		xmlstr += XMLBuildUtil.appendStringElement("nsrmc", super.getNsrmc());
		xmlstr += XMLBuildUtil.appendStringElement("swdjzh", swdjzh);
		xmlstr += XMLBuildUtil.appendStringElement("zcdz", zcdz);
		xmlstr += XMLBuildUtil.appendStringElement("jyfw", jyfw);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",super.getSwjgzzjgdm());
		return xmlstr;
	}
	
	
	public String getSwdjzh() {
		return swdjzh;
	}

	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
}
