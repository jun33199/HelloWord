package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 与xml文件中业务数据部分对应
 *
 */
public class HdzsSbsjVO implements XMLVOInterface
{
	
	private String srze_bqs="";
	private String srze_ljs="";
	private String yssdl_bqs="";
	private String yssdl_ljs="";
	private String ynssde_bqs="";
	private String ynssde_ljs="";
	private String sysl_bqs="";
	private String sysl_ljs="";
	private String yjsdse_bqs="";
	private String yjsdse_ljs="";
	private String sjyyjdsdse_bqs="";
	private String sjyyjdsdse_ljs="";
	private String ybdsdse_bqs="";
	private String ybdsdse_ljs="";
	private String sbrqshow="";
	private String xxwl_je="";
	
	private String zcze_ljs="";
	
	private String zczbje="";
	

    public String getSbrqshow() {
		return sbrqshow;
	}
	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}
	public HdzsSbsjVO()
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
        xmlstr += XMLBuildUtil.appendStringElement("srze_bqs",srze_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("srze_ljs",srze_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("yssdl_bqs",yssdl_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("yssdl_ljs",yssdl_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde_bqs",ynssde_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde_ljs",ynssde_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("sysl_bqs",sysl_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("sysl_ljs",sysl_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("yjsdse_bqs",yjsdse_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("yjsdse_ljs",yjsdse_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("sjyyjdsdse_bqs",sjyyjdsdse_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("sjyyjdsdse_ljs",sjyyjdsdse_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("ybdsdse_bqs",ybdsdse_bqs);
        xmlstr += XMLBuildUtil.appendStringElement("ybdsdse_ljs",ybdsdse_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("xxwl_je",xxwl_je);
        
        xmlstr += XMLBuildUtil.appendStringElement("zcze_ljs",zcze_ljs);
        xmlstr += XMLBuildUtil.appendStringElement("zczbje",zczbje);
        return xmlstr;
    }
	public String getSjyyjdsdse_bqs() {
		return sjyyjdsdse_bqs;
	}
	public void setSjyyjdsdse_bqs(String sjyyjdsdse_bqs) {
		this.sjyyjdsdse_bqs = sjyyjdsdse_bqs;
	}
	public String getSjyyjdsdse_ljs() {
		return sjyyjdsdse_ljs;
	}
	public void setSjyyjdsdse_ljs(String sjyyjdsdse_ljs) {
		this.sjyyjdsdse_ljs = sjyyjdsdse_ljs;
	}
	public String getSrze_bqs() {
		return srze_bqs;
	}
	public void setSrze_bqs(String srze_bqs) {
		this.srze_bqs = srze_bqs;
	}
	public String getSrze_ljs() {
		return srze_ljs;
	}
	public void setSrze_ljs(String srze_ljs) {
		this.srze_ljs = srze_ljs;
	}
	public String getSysl_bqs() {
		return sysl_bqs;
	}
	public void setSysl_bqs(String sysl_bqs) {
		this.sysl_bqs = sysl_bqs;
	}
	public String getSysl_ljs() {
		return sysl_ljs;
	}
	public void setSysl_ljs(String sysl_ljs) {
		this.sysl_ljs = sysl_ljs;
	}
	public String getYbdsdse_bqs() {
		return ybdsdse_bqs;
	}
	public void setYbdsdse_bqs(String ybdsdse_bqs) {
		this.ybdsdse_bqs = ybdsdse_bqs;
	}
	public String getYbdsdse_ljs() {
		return ybdsdse_ljs;
	}
	public void setYbdsdse_ljs(String ybdsdse_ljs) {
		this.ybdsdse_ljs = ybdsdse_ljs;
	}
	public String getYjsdse_bqs() {
		return yjsdse_bqs;
	}
	public void setYjsdse_bqs(String yjsdse_bqs) {
		this.yjsdse_bqs = yjsdse_bqs;
	}
	public String getYjsdse_ljs() {
		return yjsdse_ljs;
	}
	public void setYjsdse_ljs(String yjsdse_ljs) {
		this.yjsdse_ljs = yjsdse_ljs;
	}
	public String getYnssde_bqs() {
		return ynssde_bqs;
	}
	public void setYnssde_bqs(String ynssde_bqs) {
		this.ynssde_bqs = ynssde_bqs;
	}
	public String getYnssde_ljs() {
		return ynssde_ljs;
	}
	public void setYnssde_ljs(String ynssde_ljs) {
		this.ynssde_ljs = ynssde_ljs;
	}
	public String getYssdl_bqs() {
		return yssdl_bqs;
	}
	public void setYssdl_bqs(String yssdl_bqs) {
		this.yssdl_bqs = yssdl_bqs;
	}
	public String getYssdl_ljs() {
		return yssdl_ljs;
	}
	public void setYssdl_ljs(String yssdl_ljs) {
		this.yssdl_ljs = yssdl_ljs;
	}
	public String getXxwl_je() {
		return xxwl_je;
	}
	public void setXxwl_je(String xxwl_je) {
		this.xxwl_je = xxwl_je;
	}
    
   

	/**
	 * @return Returns the zcze_ljs.
	 */
	public String getZcze_ljs() {
		return zcze_ljs;
	}
	/**
	 * @param zcze_ljs The zcze_ljs to set.
	 */
	public void setZcze_ljs(String zcze_ljs) {
		this.zcze_ljs = zcze_ljs;
	}
	/**
	 * @return Returns the zczbje.
	 */
	public String getZczbje() {
		return zczbje;
	}
	/**
	 * @param zczbje The zczbje to set.
	 */
	public void setZczbje(String zczbje) {
		this.zczbje = zczbje;
	}
}
