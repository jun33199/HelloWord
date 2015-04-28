package com.syax.bjtax.shenbao.model.common;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class SbxxVO implements XMLVOInterface
{
	//Description：征管范围鉴定类型
	private String zgfwjdlx = "";
	
    /**
     * 年度
     */
    private String nd = "";

    /**
     * 季度
     */
    private String jd = "";

    /**
     * 税款所属开始日期
     */
    private String skssksrq = "";

    /**
     * 税款所属结束日期
     */
    private String skssjsrq = "";
    /**
     * 税款所属日期
     */
    private String skssrq = "";

    /**
     * 申报日期
     */
    private String sbrq = "";
    /**
     * 录入日期
     */
    private String Lrrq = "";

    /**
     * 申报方式
     */
    private String fsdm = "";

    
    public SbxxVO()
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
        xmlstr += XMLBuildUtil.appendStringElement("jd",jd);
        xmlstr += XMLBuildUtil.appendStringElement("nd",nd);
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq);
        xmlstr += XMLBuildUtil.appendStringElement("zgfwjdlx",zgfwjdlx);
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

    public String getJd()
    {
        return jd;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public String getNd()
    {
        return nd;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
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

	/**
	 * @description: getter-- zgfwjdlx
	 * @return the zgfwjdlx
	 */
	public String getZgfwjdlx() {
		return zgfwjdlx;
	}
	/**
	 * @description: setter-- zgfwjdlx
	 * @param zgfwjdlx the zgfwjdlx to set
	 */
	public void setZgfwjdlx(String zgfwjdlx) {
		this.zgfwjdlx = zgfwjdlx;
	}
	
	public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
	public String getLrrq() {
		return Lrrq;
	}
	public void setLrrq(String lrrq) {
		Lrrq = lrrq;
	}
	public void setSkssrq(String skssrq) {
		// TODO Auto-generated method stub
		this.skssrq = skssrq;
	}
	public String getSkssrq() {
		return skssrq;
	}


}
