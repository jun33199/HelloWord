package com.ttsoft.bjtax.shenbao.szsm.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;

public class SzsmListVO implements XMLVOInterface{
	private SzsmVO szsm = new SzsmVO();
	public Map getListTypeMap() {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * @return ���� szsm��
	 */
	public SzsmVO getSzsm() {
		return szsm;
	}

	/**
	 * @param szsm Ҫ���õ� szsm��
	 */
	public void setSzsm(SzsmVO szsm) {
		this.szsm = szsm;
	}

	public String toXML() {
		// TODO �Զ����ɷ������
		String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String xmlStr =xmlHeader+"<taxdoc>";
		xmlStr += "<xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion>";
		xmlStr += szsm.toXML();
		xmlStr += "</taxdoc>";
		return xmlStr;
	}

	public String toXMLChilds() {
		// TODO �Զ����ɷ������
		return null;
	}

}
