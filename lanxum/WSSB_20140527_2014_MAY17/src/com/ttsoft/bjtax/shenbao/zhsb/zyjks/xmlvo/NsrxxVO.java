package com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;


public class NsrxxVO implements XMLVOInterface
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
     * 税务机关组织机构代码
     */

    
    public NsrxxVO()
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
        
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc",nsrmc);
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




}
