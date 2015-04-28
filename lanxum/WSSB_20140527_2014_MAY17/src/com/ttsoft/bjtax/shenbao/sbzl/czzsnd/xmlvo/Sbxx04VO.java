package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 申报信息
 * @author guzx
 *
 */
public class Sbxx04VO implements XMLVOInterface
{
    /**
     * 申报方式= "5"
     */
    private String fsdm ;
    String sbrq;
    String done;
    String skssksrq;
    String skssjsrq;
    String nd;

    /**
	 * @return Returns the done.
	 */
	public String getDone() {
		return done;
	}
	/**
	 * @param done The done to set.
	 */
	public void setDone(String done) {
		this.done = done;
	}
	/**
	 * @return Returns the sbrq.
	 */
	public String getSbrq() {
		return sbrq;
	}
	/**
	 * @param sbrq The sbrq to set.
	 */
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
   
    public Sbxx04VO()
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
        
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("fsdm",fsdm);
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq",skssksrq);
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq",skssjsrq);
        xmlstr += XMLBuildUtil.appendStringElement("nd",nd);
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
	 * @return Returns the skssjsrq.
	 */
	public String getSkssjsrq() {
		return skssjsrq;
	}
	/**
	 * @param skssjsrq The skssjsrq to set.
	 */
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	/**
	 * @return Returns the skssksrq.
	 */
	public String getSkssksrq() {
		return skssksrq;
	}
	/**
	 * @param skssksrq The skssksrq to set.
	 */
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	/**
	 * @return Returns the nd.
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param nd The nd to set.
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
}
