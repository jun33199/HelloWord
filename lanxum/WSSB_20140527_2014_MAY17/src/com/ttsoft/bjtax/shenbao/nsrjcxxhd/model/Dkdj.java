package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

public class Dkdj implements Serializable {

	public String toXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<dkdj>");
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("dkdjyw", this.dkdjyw));
		sb.append(XMLBuildUtil.appendStringElement("dkdjsz", this.dkdjsz));
		sb.append(XMLBuildUtil.appendStringElement("cjrq", DateUtil.dateFormat(this.cjrq)));
		sb.append("</dkdj>");
		return sb.toString();
	}
	private static final long serialVersionUID = 1L;

	private String jsjdm;
	private String dkdjyw;
	private String dkdjsz;
	private Date cjrq;
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getDkdjyw() {
		return dkdjyw;
	}
	public void setDkdjyw(String dkdjyw) {
		this.dkdjyw = dkdjyw;
	}
	public String getDkdjsz() {
		return dkdjsz;
	}
	public void setDkdjsz(String dkdjsz) {
		this.dkdjsz = dkdjsz;
	}
	public Date getCjrq() {
		return cjrq;
	}
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}
}
