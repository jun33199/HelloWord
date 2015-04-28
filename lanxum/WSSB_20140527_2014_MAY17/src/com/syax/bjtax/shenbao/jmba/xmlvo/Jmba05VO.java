package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: ��ʮ�ˣ��⹺��������۾ɻ�̯������ VO</p>
 *
 * <p>Copyright: ��һ����</p>
 *
 * <p>Company: ��һ����</p>
 *
 * @author �׾�
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba05VO implements JmbamxVoInterface {

	// ���
	private String xh;

	// ũ�����������Ŀ����
	private String nlmyjmxmdm;

	// �������ö�
	private String jmsde;

	public Jmba05VO() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", this.xh);
		xmlstr += XMLBuildUtil.appendStringElement("nlmyjmxmdm",
				this.nlmyjmxmdm);
		xmlstr += XMLBuildUtil.appendStringElement("jmsde", this.jmsde);
		xmlstr += "</qysdsjmba>";

		return xmlstr;

	}

	public String toXMLChilds() {
		return "";
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getJmsde() {
		return jmsde;
	}

	public void setJmsde(String jmsde) {
		this.jmsde = jmsde;
	}

	public String getNlmyjmxmdm() {
		return nlmyjmxmdm;
	}

	public void setNlmyjmxmdm(String nlmyjmxmdm) {
		this.nlmyjmxmdm = nlmyjmxmdm;
	}

}
