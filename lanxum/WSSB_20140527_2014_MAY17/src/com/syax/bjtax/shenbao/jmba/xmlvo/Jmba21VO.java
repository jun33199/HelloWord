package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

public class Jmba21VO implements JmbamxVoInterface 
{
	
	//��˱��,0:ͨ��,1:��ͨ��
	private String shbj;
	   
	// ���
	private String xh;
	
	//������ʼ���
	private String mzqsnd;
	
	//������ֹ���
	private String mzzznd = "2015";
	
/*------------------------------------------------------------------------*/
	public Map getListTypeMap() 
	{
		return null;
	}

	public String toXML() 
	{
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("shbj", shbj);
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		xmlstr += "</qysdsjmba>";

		return xmlstr;
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}


/*-------------------------------------------------------------------------*/
	
	
	public String getShbj() {
		return shbj;
	}

	public String getMzzznd() {
		return mzzznd;
	}

	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}

	public void setShbj(String shbj) {
		this.shbj = shbj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getMzqsnd() {
		return mzqsnd;
	}

	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}

	
	
}
