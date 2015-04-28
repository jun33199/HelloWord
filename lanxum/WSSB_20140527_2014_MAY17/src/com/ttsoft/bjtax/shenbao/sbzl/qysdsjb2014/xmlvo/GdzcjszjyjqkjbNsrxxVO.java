package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.sql.Timestamp;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;


public class GdzcjszjyjqkjbNsrxxVO implements XMLVOInterface
{
    /**
     * 计算机代码
     */
    private String jsjdm ;
    
    /**
     * 纳税人名称
     */
    private String nsrmc ;

    /**
     * 纳税人识别号
     */
    private String nsrsbh = "";
    
    /**
     * 所属行业
     */
    private String sshy="";
    /**
     * 所属行业代码
     */
    private String sshydm="";
    
    public GdzcjszjyjqkjbNsrxxVO()
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
        String xmlstr = "<nsrxx>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</nsrxx>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";

        xmlstr += XMLBuildUtil.appendStringElement("nsrmc",nsrmc);
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh",nsrsbh);
        xmlstr += XMLBuildUtil.appendStringElement("sshy",sshy); 
        xmlstr += XMLBuildUtil.appendStringElement("sshydm",sshydm); 
        return xmlstr;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}



}
