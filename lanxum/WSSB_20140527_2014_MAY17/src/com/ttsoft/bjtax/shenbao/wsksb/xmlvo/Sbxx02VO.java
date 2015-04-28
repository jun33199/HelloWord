package com.ttsoft.bjtax.shenbao.wsksb.xmlvo;


import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class Sbxx02VO implements XMLVOInterface
{
    /**
     * ˰��������ʼ����
     */
    private String skssksrq = "";

    /**
     * ˰��������������
     */
    private String skssjsrq = "";

    /**
     * �걨����
     */
    private String sbrq = "";

    /**
     * �걨��ʽ
     */
    private String fsdm = "";
    
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
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq);
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

    public String getSbrq()
    {
        return sbrq;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }


}
