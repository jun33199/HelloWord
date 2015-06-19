package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;

import java.io.Serializable;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * ˰��Ǽ�-˰�����
 * @author guow
 *
 */
public class Swdl implements Serializable {
	public String toXml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<swdl>");
		sb.append(XMLBuildUtil.appendStringElement("dzyx", this.dzyx));
		sb.append(XMLBuildUtil.appendStringElement("gddh", this.gddh));
		sb.append(XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm));
		sb.append(XMLBuildUtil.appendStringElement("mc", this.mc));
		sb.append(XMLBuildUtil.appendStringElement("swdjzh", this.swdjzh));
		sb.append(XMLBuildUtil.appendStringElement("swdlyddh", this.swdlyddh));
		sb.append("</swdl>");
		return sb.toString();
	}

	private static final long serialVersionUID = 1L;
	/**
	 * ���������
	 */
	String jsjdm;
	/**
	 * �̶��绰
	 */
	String gddh;
	/**
	 * ��������
	 */
	String dzyx;
	/**
	 * ˰��Ǽ�֤��
	 */
	String swdjzh;
	/**
	 * ����
	 */
	String mc;
	/**
	 * ˰������ƶ��绰
	 */
	String swdlyddh;
	/**
	 * ˰�������֯��������
	 */
	String swjgzzjgdm;

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getGddh() {
		return gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}

	public String getDzyx() {
		return dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getSwdjzh() {
		return swdjzh;
	}

	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSwdlyddh() {
		return swdlyddh;
	}

	public void setSwdlyddh(String swdlyddh) {
		this.swdlyddh = swdlyddh;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
}
