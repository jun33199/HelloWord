package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

/**
 * ��֧����
 * @author guow
 *
 */
public class Fzjg implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ��˰������
	 */
	private String nsrmc;
	/**
	 * ע���ַ
	 */
	private String zcdz;
	/**
	 * ��֧����˰��Ǽ�֤��
	 */
	private String fzjgswdjzh;
	/**
	 * ���������
	 */
	private String jsjdm;
	/**
	 * ����ʱ��
	 */
	private Date cjrq;
	/**
	 * ¼��ʱ��
	 */
	private Date lrrq;
	/**
	 * ˰�������֯��������
	 */
	private String swjgzzjgdm;
	private String fzdh;
	
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<fzjg>");
		sb.append(XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc));
		sb.append(XMLBuildUtil.appendStringElement("zcdz", this.zcdz));
		sb.append(XMLBuildUtil.appendStringElement("fzjgswdjzh", this.fzjgswdjzh));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("cjrq", DateUtil.dateFormat(this.cjrq)));
		sb.append(XMLBuildUtil.appendStringElement("lrrq", DateUtil.dateFormat(this.lrrq)));
		sb.append(XMLBuildUtil.appendStringElement("swjgzzjgdm", this.swjgzzjgdm));
		sb.append(XMLBuildUtil.appendStringElement("fzdh", this.fzdh));
		sb.append("</fzjg>");
		return sb.toString();
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getFzjgswdjzh() {
		return fzjgswdjzh;
	}

	public void setFzjgswdjzh(String fzjgswdjzh) {
		this.fzjgswdjzh = fzjgswdjzh;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public Date getLrrq() {
		return lrrq;
	}

	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getFzdh() {
		return fzdh;
	}

	public void setFzdh(String fzdh) {
		this.fzdh = fzdh;
	}
	
}
