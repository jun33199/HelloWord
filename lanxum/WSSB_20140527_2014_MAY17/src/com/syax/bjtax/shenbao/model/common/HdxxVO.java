package com.syax.bjtax.shenbao.model.common;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class HdxxVO implements XMLVOInterface
{
    /**
     * 减免资格 0：无资格 1：有资格
     */
    private String jmzg = "0";
    /**
     * 一般减免税率
     */
    private String ybjmsl = "";
    
    /**
     * 企业征收类型
     */
    private String qyzslx = "-1";

    /**
     * 纯意率
     */
    private String cyl = "";
    
    /**
     * 乡镇企业标识
     */
    private String xzqy = "";
    
    /**
     * 定额征收税额
     */
    private String dezsse = "";

    public HdxxVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public String toXML()
    {
        String xmlstr = "<hdxx>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</hdxx>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("jmzg",jmzg);
        xmlstr += XMLBuildUtil.appendStringElement("ybjmsl",ybjmsl);
        xmlstr += XMLBuildUtil.appendStringElement("qyzslx",qyzslx);
        xmlstr += XMLBuildUtil.appendStringElement("cyl",cyl);
        xmlstr += XMLBuildUtil.appendStringElement("xzqy",xzqy);
        xmlstr += XMLBuildUtil.appendStringElement("dezsse",dezsse);
        return xmlstr;
    }

    public String getCyl()
    {
        return cyl;
    }

    public void setCyl(String cyl)
    {
        this.cyl = cyl;
    }

    public String getDezsse()
    {
        return dezsse;
    }

    public void setDezsse(String dezsse)
    {
        this.dezsse = dezsse;
    }

    public String getJmzg()
    {
        return jmzg;
    }

    public void setJmzg(String jmzg)
    {
        this.jmzg = jmzg;
    }

    public String getQyzslx()
    {
        return qyzslx;
    }

    public void setQyzslx(String qyzslx)
    {
        this.qyzslx = qyzslx;
    }

    public String getYbjmsl()
    {
        return ybjmsl;
    }

    public void setYbjmsl(String ybjmsl)
    {
        this.ybjmsl = ybjmsl;
    }

    public String getXzqy()
    {
        return xzqy;
    }

    public void setXzqy(String xzqy)
    {
        this.xzqy = xzqy;
    }
    public Map getListTypeMap()
    {
        return null;
    }

}
