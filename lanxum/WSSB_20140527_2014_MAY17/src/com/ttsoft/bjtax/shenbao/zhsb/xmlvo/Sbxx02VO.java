package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Sbxx02VO implements XMLVOInterface
{
    /**
     * …Í±®∑Ω Ω= "5"
     */
    private String fsdm ;
    private String sbxgrq;
    
    public Sbxx02VO()
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
        String xmlstr = "<sbxx>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbxx>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("fsdm",fsdm);
        xmlstr += XMLBuildUtil.appendStringElement("sbxgrq",sbxgrq);
        return xmlstr;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }


	/**
	 * @return Returns the sbxgrq.
	 */
	public String getSbxgrq() {
		return sbxgrq;
	}
	/**
	 * @param sbxgrq The sbxgrq to set.
	 */
	public void setSbxgrq(String sbxgrq) {
		this.sbxgrq = sbxgrq;
	}
}
