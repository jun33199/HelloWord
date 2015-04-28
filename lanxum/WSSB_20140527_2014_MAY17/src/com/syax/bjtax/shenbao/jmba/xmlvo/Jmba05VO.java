package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: （十八）外购软件缩短折旧或摊销年限 VO</p>
 *
 * <p>Copyright: 四一安信</p>
 *
 * <p>Company: 四一安信</p>
 *
 * @author 米军
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba05VO implements JmbamxVoInterface {

	// 序号
	private String xh;

	// 农林牧渔减免项目代码
	private String nlmyjmxmdm;

	// 减免所得额
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
