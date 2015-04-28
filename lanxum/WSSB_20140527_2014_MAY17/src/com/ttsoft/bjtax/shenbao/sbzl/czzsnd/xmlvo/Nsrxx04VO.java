package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 纳税人信息
 * @author guzx
 *
 */
public class Nsrxx04VO implements XMLVOInterface
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
    private String swjgzzjgdm ;
    
    private String qylxdh ;

    public Nsrxx04VO()
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
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("qylxdh",qylxdh);
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

    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }


	/**
	 * @return Returns the qylxdh.
	 */
	public String getQylxdh() {
		return qylxdh;
	}
	/**
	 * @param qylxdh The qylxdh to set.
	 */
	public void setQylxdh(String qylxdh) {
		this.qylxdh = qylxdh;
	}
}
