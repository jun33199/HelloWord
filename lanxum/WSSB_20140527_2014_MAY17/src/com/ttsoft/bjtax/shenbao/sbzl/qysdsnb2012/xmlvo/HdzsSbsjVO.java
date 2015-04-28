package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2012.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 与xml文件中业务数据部分对应
 *
 */
public class HdzsSbsjVO implements XMLVOInterface
{
	
	private String syze="";
	private String bzssr="";
	private String mssr="";
	private String yssre="";
	private String yssdl="";
	private String ynssde="";
	private String sl="";
	private String ynsdse="";
	private String yyjsdse="";
	private String ybsdse="";
	private String sbrqshow="";
	
	private String zczb="";
	private String zcze="";
	

	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getZcze() {
		return zcze;
	}
	public void setZcze(String zcze) {
		this.zcze = zcze;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getSyze() {
		return syze;
	}
	public void setSyze(String syze) {
		this.syze = syze;
	}
	public String getYbsdse() {
		return ybsdse;
	}
	public void setYbsdse(String ybsdse) {
		this.ybsdse = ybsdse;
	}
	public String getYnsdse() {
		return ynsdse;
	}
	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}
	public String getYnssde() {
		return ynssde;
	}
	public void setYnssde(String ynssde) {
		this.ynssde = ynssde;
	}
	public String getYssdl() {
		return yssdl;
	}
	public void setYssdl(String yssdl) {
		this.yssdl = yssdl;
	}
	public String getYyjsdse() {
		return yyjsdse;
	}
	public void setYyjsdse(String yyjsdse) {
		this.yyjsdse = yyjsdse;
	}
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
        xmlstr += XMLBuildUtil.appendStringElement("syze",syze);
        xmlstr += XMLBuildUtil.appendStringElement("bzssr",bzssr);
        xmlstr += XMLBuildUtil.appendStringElement("mssr",mssr);
        xmlstr += XMLBuildUtil.appendStringElement("yssre",yssre);
        xmlstr += XMLBuildUtil.appendStringElement("yssdl",yssdl);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde",ynssde);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse",ynsdse);
        xmlstr += XMLBuildUtil.appendStringElement("yyjsdse",yyjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("ybsdse",ybsdse);
        
        xmlstr += XMLBuildUtil.appendStringElement("zczb",zczb);
        xmlstr += XMLBuildUtil.appendStringElement("zcze",zcze);
        return xmlstr;
    }
	public String getBzssr() {
		return bzssr;
	}
	public void setBzssr(String bzssr) {
		this.bzssr = bzssr;
	}
	public String getMssr() {
		return mssr;
	}
	public void setMssr(String mssr) {
		this.mssr = mssr;
	}
	public String getYssre() {
		return yssre;
	}
	public void setYssre(String yssre) {
		this.yssre = yssre;
	}

   

}
