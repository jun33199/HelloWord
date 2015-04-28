package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 投资方数据
 * @author guzx
 *
 */
public class Tzfsj04VO implements XMLVOInterface
{
    private String tzzxm ;
    String zjhm;
    String zjlxdm;
    String cwfzr;
    String jmsjcontrol;
    private Map m = new HashMap();
    
     /**
      * 申报数据
      */
     List tzfmx=new ArrayList();

   
    public Tzfsj04VO()
    {
		super();
        m.put("tzfmx", "com.ttsoft.bjtax.shenbao.sbzl.czzsnd.xmlvo.Tzfmx04VO");
    }
    public Map getListTypeMap()
    {
    	return m;
    }
    public String toXML()
    {
        String xmlstr = "<tzfsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</tzfsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("tzzxm",tzzxm);
        xmlstr += XMLBuildUtil.appendStringElement("zjhm",zjhm);
        xmlstr += XMLBuildUtil.appendStringElement("zjlxdm",zjlxdm);
        xmlstr += XMLBuildUtil.appendStringElement("cwfzr",cwfzr);
        xmlstr += XMLBuildUtil.appendStringElement("jmsjcontrol",jmsjcontrol);
        if (tzfmx != null )
        {
            for (int i = 0; i < tzfmx.size(); i++)
            {
                xmlstr += ((Tzfmx04VO) tzfmx.get(i)).toXML();
            }
        }
       return xmlstr;
    }
	/**
	 * @return Returns the cwfzr.
	 */
	public String getCwfzr() {
		return cwfzr;
	}
	/**
	 * @param cwfzr The cwfzr to set.
	 */
	public void setCwfzr(String cwfzr) {
		this.cwfzr = cwfzr;
	}
	/**
	 * @return Returns the jmsjcontrol.
	 */
	public String getJmsjcontrol() {
		return jmsjcontrol;
	}
	/**
	 * @param jmsjcontrol The jmsjcontrol to set.
	 */
	public void setJmsjcontrol(String jmsjcontrol) {
		this.jmsjcontrol = jmsjcontrol;
	}
	/**
	 * @return Returns the tzzxm.
	 */
	public String getTzzxm() {
		return tzzxm;
	}
	/**
	 * @param tzzxm The tzzxm to set.
	 */
	public void setTzzxm(String tzzxm) {
		this.tzzxm = tzzxm;
	}
	/**
	 * @return Returns the zjhm.
	 */
	public String getZjhm() {
		return zjhm;
	}
	/**
	 * @param zjhm The zjhm to set.
	 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	/**
	 * @return Returns the zjlxdm.
	 */
	public String getZjlxdm() {
		return zjlxdm;
	}
	/**
	 * @param zjlxdm The zjlxdm to set.
	 */
	public void setZjlxdm(String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}
	/**
	 * @return Returns the tzfmx.
	 */
	public List getTzfmx() {
		return tzfmx;
	}
	/**
	 * @param tzfmx The tzfmx to set.
	 */
	public void setTzfmx(List tzfmx) {
		this.tzfmx = tzfmx;
	}
}
