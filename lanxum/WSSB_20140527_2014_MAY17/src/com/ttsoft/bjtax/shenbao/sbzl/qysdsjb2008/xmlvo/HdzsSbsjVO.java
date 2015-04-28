package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * ��xml�ļ���ҵ�����ݲ��ֶ�Ӧ
 *
 */
public class HdzsSbsjVO implements XMLVOInterface
{
	
	private String syze="";
	private String yssdl="";
	private String ynssde="";
	private String sl="";
	private String ynsdse="";
	private String jmsdse="";
	private String yyjsdse="";
	private String ybsdse="";
	private String sbrqshow="";
	
	
	

    public String getJmsdse() {
		return jmsdse;
	}
	public void setJmsdse(String jmsdse) {
		this.jmsdse = jmsdse;
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
        xmlstr += XMLBuildUtil.appendStringElement("yssdl",yssdl);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde",ynssde);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse",ynsdse);
        xmlstr += XMLBuildUtil.appendStringElement("jmsdse",jmsdse);
        xmlstr += XMLBuildUtil.appendStringElement("yyjsdse",yyjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("ybsdse",ybsdse);
        return xmlstr;
    }

   

}
