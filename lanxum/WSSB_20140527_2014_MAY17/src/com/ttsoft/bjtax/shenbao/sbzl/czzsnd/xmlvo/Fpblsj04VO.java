package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 非配比例数据
 * @author guzx
 *
 */
public class Fpblsj04VO implements XMLVOInterface
{
    private String xmmc ;
    String hc;
    String bqljs;
    String fpbl;

   
    public Fpblsj04VO()
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
        String xmlstr = "<fpblsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</fpblsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("xmmc",xmmc);
        xmlstr += XMLBuildUtil.appendStringElement("hc",hc);
        xmlstr += XMLBuildUtil.appendStringElement("fpbl",fpbl);
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
	/**
	 * @return Returns the fpbl.
	 */
	public String getFpbl() {
		return fpbl;
	}
	/**
	 * @param fpbl The fpbl to set.
	 */
	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}
}
