package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;


public class QyqssdsNsrJbxxVo extends YWRootVO implements XMLVOInterface {


	/**
     * 纳税人基本信息数据
     */
    private NsrJbxxVo nsrjbxx = new NsrJbxxVo();
    
   
    public QyqssdsNsrJbxxVo()
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
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += nsrjbxx.toXML();
        return xmlstr;
    }
	public NsrJbxxVo getNsrjbxx() {
		return nsrjbxx;
	}
	public void setNsrjbxx(NsrJbxxVo nsrjbxx) {
		this.nsrjbxx = nsrjbxx;
	}
	
	
}
