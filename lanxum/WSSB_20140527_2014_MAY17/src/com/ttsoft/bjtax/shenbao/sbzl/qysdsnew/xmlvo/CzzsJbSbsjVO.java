package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * ��xml�ļ���ҵ�����ݲ��ֶ�Ӧ
 *
 */
public class CzzsJbSbsjVO implements XMLVOInterface
{
	
	private String lrze="";
	private String nstzzje="";
	private String nstzjse="";
	private String mbyqndks="";
	private String ynssde="";
	private String sysl="";
	private String ynsdse="";
	private String jmsdse="";
	private String hznscyqyjdyjbl="";
	private String sjyyjdsdse="";
	private String ybdsdse="";
	private String sbrqshow="";
	
	
	

    public String getSbrqshow() {
		return sbrqshow;
	}
	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}
	public CzzsJbSbsjVO()
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
        
        xmlstr += XMLBuildUtil.appendStringElement("sbrqshow",sbrqshow);
        xmlstr += XMLBuildUtil.appendStringElement("lrze",lrze);
        xmlstr += XMLBuildUtil.appendStringElement("nstzzje",nstzzje);
        xmlstr += XMLBuildUtil.appendStringElement("nstzjse",nstzjse);
        xmlstr += XMLBuildUtil.appendStringElement("mbyqndks",mbyqndks);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde",ynssde);
        xmlstr += XMLBuildUtil.appendStringElement("sysl",sysl);
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse",ynsdse);
        xmlstr += XMLBuildUtil.appendStringElement("jmsdse",jmsdse);
        xmlstr += XMLBuildUtil.appendStringElement("hznscyqyjdyjbl",hznscyqyjdyjbl);
        xmlstr += XMLBuildUtil.appendStringElement("sjyyjdsdse",sjyyjdsdse);
        xmlstr += XMLBuildUtil.appendStringElement("ybdsdse",ybdsdse);
        return xmlstr;
    }
	/**
	 * @return hznscyqyjdyjbl
	 */
	public String getHznscyqyjdyjbl() {
		return hznscyqyjdyjbl;
	}
	/**
	 * @param hznscyqyjdyjbl Ҫ���õ� hznscyqyjdyjbl
	 */
	public void setHznscyqyjdyjbl(String hznscyqyjdyjbl) {
		this.hznscyqyjdyjbl = hznscyqyjdyjbl;
	}
	/**
	 * @return jmsdse
	 */
	public String getJmsdse() {
		return jmsdse;
	}
	/**
	 * @param jmsdse Ҫ���õ� jmsdse
	 */
	public void setJmsdse(String jmsdse) {
		this.jmsdse = jmsdse;
	}
	/**
	 * @return lrze
	 */
	public String getLrze() {
		return lrze;
	}
	/**
	 * @param lrze Ҫ���õ� lrze
	 */
	public void setLrze(String lrze) {
		this.lrze = lrze;
	}
	/**
	 * @return mbyqndks
	 */
	public String getMbyqndks() {
		return mbyqndks;
	}
	/**
	 * @param mbyqndks Ҫ���õ� mbyqndks
	 */
	public void setMbyqndks(String mbyqndks) {
		this.mbyqndks = mbyqndks;
	}
	/**
	 * @return nstzjse
	 */
	public String getNstzjse() {
		return nstzjse;
	}
	/**
	 * @param nstzjse Ҫ���õ� nstzjse
	 */
	public void setNstzjse(String nstzjse) {
		this.nstzjse = nstzjse;
	}
	/**
	 * @return nstzzje
	 */
	public String getNstzzje() {
		return nstzzje;
	}
	/**
	 * @param nstzzje Ҫ���õ� nstzzje
	 */
	public void setNstzzje(String nstzzje) {
		this.nstzzje = nstzzje;
	}
	/**
	 * @return sjyyjdsdse
	 */
	public String getSjyyjdsdse() {
		return sjyyjdsdse;
	}
	/**
	 * @param sjyyjdsdse Ҫ���õ� sjyyjdsdse
	 */
	public void setSjyyjdsdse(String sjyyjdsdse) {
		this.sjyyjdsdse = sjyyjdsdse;
	}
	/**
	 * @return sysl
	 */
	public String getSysl() {
		return sysl;
	}
	/**
	 * @param sysl Ҫ���õ� sysl
	 */
	public void setSysl(String sysl) {
		this.sysl = sysl;
	}
	/**
	 * @return ybdsdse
	 */
	public String getYbdsdse() {
		return ybdsdse;
	}
	/**
	 * @param ybdsdse Ҫ���õ� ybdsdse
	 */
	public void setYbdsdse(String ybdsdse) {
		this.ybdsdse = ybdsdse;
	}
	/**
	 * @return ynsdse
	 */
	public String getYnsdse() {
		return ynsdse;
	}
	/**
	 * @param ynsdse Ҫ���õ� ynsdse
	 */
	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}
	/**
	 * @return ynssde
	 */
	public String getYnssde() {
		return ynssde;
	}
	/**
	 * @param ynssde Ҫ���õ� ynssde
	 */
	public void setYnssde(String ynssde) {
		this.ynssde = ynssde;
	}
	
    
   

}
