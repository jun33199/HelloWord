package com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class NsrxxVO_JMS extends NsrxxVO implements XMLVOInterface {
	/**
     * ��ҵ��ϵ�绰
     */
	private String qylxdh = "";
	/**
	 * �������۶�
	 */
	private String dqxse;
	/**
	 * ���������ܶ�
	 */
	private String dqlrze;
	/**
	 * ��ҵ����
	 */
	private String qyrs;
	/**
	 * ��������
	 */
	private String azrs;
	/**
	 * ���ñ���
	 */
	//private String azbl;
	public NsrxxVO_JMS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", super.getJsjdm());
		xmlstr += XMLBuildUtil.appendStringElement("nsrmc", super.getNsrmc());
		xmlstr += XMLBuildUtil.appendStringElement("qylxdh", qylxdh);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",super.getSwjgzzjgdm());
		xmlstr += XMLBuildUtil.appendStringElement("dqxse", dqxse);
		xmlstr += XMLBuildUtil.appendStringElement("dqlrze", dqlrze);
		xmlstr += XMLBuildUtil.appendStringElement("qyrs", qyrs);
		xmlstr += XMLBuildUtil.appendStringElement("azrs", azrs);
		//xmlstr += XMLBuildUtil.appendStringElement("azbl", azbl);
		return xmlstr;
	}
	public String getQylxdh() {
		return qylxdh;
	}
	public void setQylxdh(String qylxdh) {
		this.qylxdh = qylxdh;
	}
	
	public String getDqxse() {
		return dqxse;
	}


	public void setDqxse(String dqxse) {
		this.dqxse = dqxse;
	}


	public String getDqlrze() {
		return dqlrze;
	}


	public void setDqlrze(String dqlrze) {
		this.dqlrze = dqlrze;
	}


	public String getQyrs() {
		return qyrs;
	}


	public void setQyrs(String qyrs) {
		this.qyrs = qyrs;
	}


	public String getAzrs() {
		return azrs;
	}


	public void setAzrs(String azrs) {
		this.azrs = azrs;
	}


/*	public String getAzbl() {
		return azbl;
	}


	public void setAzbl(String azbl) {
		this.azbl = azbl;
	}*/

}
