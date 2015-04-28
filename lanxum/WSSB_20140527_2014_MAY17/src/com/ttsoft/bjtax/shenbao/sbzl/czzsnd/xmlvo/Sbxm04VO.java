package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 申报项目（对应于一次申报的数据）
 * @author guzx
 *
 */
public class Sbxm04VO implements XMLVOInterface
{
    private String xmmc ;
    String hc;
    String bqljs;

   
    public Sbxm04VO()
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
        String xmlstr = "<sbxm>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbxm>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("xmmc",xmmc);
        xmlstr += XMLBuildUtil.appendStringElement("hc",hc);
        xmlstr += XMLBuildUtil.appendStringElement("bqljs",bqljs);
        return xmlstr;
    }

	/**
	 * @return Returns the bqljs.
	 */
	public String getBqljs() {
		return bqljs;
	}
	/**
	 * @param bqljs The bqljs to set.
	 */
	public void setBqljs(String bqljs) {
		this.bqljs = bqljs;
	}
	/**
	 * @return Returns the hc.
	 */
	public String getHc() {
		return hc;
	}
	/**
	 * @param hc The hc to set.
	 */
	public void setHc(String hc) {
		this.hc = hc;
	}
	/**
	 * @return Returns the xmmc.
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc The xmmc to set.
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
}
