package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 查账征收主表数据
 * @author guzx
 *
 */
public class Czzbsj04VO implements XMLVOInterface
{
    private String lrr ;
    String lrrq;
    String cjsj;

   
    public Czzbsj04VO()
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
        String xmlstr = "<czzbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</czzbsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);
        xmlstr += XMLBuildUtil.appendStringElement("cjsj",cjsj);
        return xmlstr;
    }

	/**
	 * @return Returns the cjsj.
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj The cjsj to set.
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return Returns the lrr.
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr The lrr to set.
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return Returns the lrrq.
	 */
	public String getLrrq() {
		return lrrq;
	}
	/**
	 * @param lrrq The lrrq to set.
	 */
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
}
