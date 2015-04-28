package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;


public class Nxxm02VO implements XMLVOInterface
{
    /**
     * 计算机代码
     */
    private String nxxmmc = "";
    
    /**
     * 纳税人名称
     */
    private String sjje = "";

    public Nxxm02VO()
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
        String xmlstr = "<nxxm>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</nxxm>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("nxxmmc",nxxmmc);
        xmlstr += XMLBuildUtil.appendStringElement("sjje",sjje);
        return xmlstr;
    }


	/**
	 * @return Returns the nxxmmc.
	 */
	public String getNxxmmc() {
		return nxxmmc;
	}
	/**
	 * @param nxxmmc The nxxmmc to set.
	 */
	public void setNxxmmc(String nxxmmc) {
		this.nxxmmc = nxxmmc;
	}
	/**
	 * @return Returns the sjje.
	 */
	public String getSjje() {
		return sjje;
	}
	/**
	 * @param sjje The sjje to set.
	 */
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
}
