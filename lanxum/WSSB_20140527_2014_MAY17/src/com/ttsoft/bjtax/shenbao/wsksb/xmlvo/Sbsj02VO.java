package com.ttsoft.bjtax.shenbao.wsksb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Sbsj02VO implements XMLVOInterface
{
    /**
     * �걨����
     */
    private String sbcz = "";

    /**
     * �걨���
     */
    private String sbbh = "";
    
    /**
     * ��˰�걨ԭ��
     */
    private String wssbyydm = "";
    
    /**
     * ��˰�걨ԭ������
     */
    private String wssbyymc = "";
    
    /**
     * 99��˰�걨ԭ������
     */
    private String QtWssbyymc = "";
   
    public Sbsj02VO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<sbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("sbcz",sbcz);
        xmlstr += XMLBuildUtil.appendStringElement("sbbh",sbbh);
        xmlstr += XMLBuildUtil.appendStringElement("wssbyydm", wssbyydm);
        xmlstr += XMLBuildUtil.appendStringElement("wssbyymc", wssbyymc);
        xmlstr += XMLBuildUtil.appendStringElement("QtWssbyymc", QtWssbyymc);
        return xmlstr;
    }
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public String getSbcz() {
		return sbcz;
	}
	public void setSbcz(String sbcz) {
		this.sbcz = sbcz;
	}
	public String getWssbyymc() {
		return wssbyymc;
	}
	public void setWssbyymc(String wssbyymc) {
		this.wssbyymc = wssbyymc;
	}
	public String getQtWssbyymc() {
		return QtWssbyymc;
	}
	public void setQtWssbyymc(String qtWssbyymc) {
		QtWssbyymc = qtWssbyymc;
	}
	public String getWssbyydm() {
		return wssbyydm;
	}
	public void setWssbyydm(String wssbyydm) {
		this.wssbyydm = wssbyydm;
	}
	

}

