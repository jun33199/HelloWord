/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO;



/**
 * @author guzx
 * 电子缴库专用缴款书
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZyJks02VO extends YWRootVO implements XMLVOInterface{

	
	private NsrxxVO nsrxx = new NsrxxVO() ; 

    List sbsj = new ArrayList();
    
	private Map m = new HashMap();

	public ZyJks02VO() {
		super();
        m.put("sbsj", "com.ttsoft.bjtax.shenbao.zhsbzyjks.xmlvo.SbsjVO");
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
        return m;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
//		System.out.println("toXMLChilds---------");
        String xmlstr = "";
//        System.out.println(nsrxx);
        //if (nsrxx!=null)
            xmlstr += nsrxx.toXML();
        //if(sbsj!=null)
            //xmlstr += sbsj.toXML();
        System.out.println(sbsj);
            for (int i = 0; i < sbsj.size(); i++)
            {
                xmlstr += ((SbsjVO) sbsj.get(i)).toXML();
            }    
        return xmlstr;
	}
	public static void main(String[] args){
		ZyJks02VO zyjks = new ZyJks02VO();
		System.out.println(zyjks.toXML());
	}

	public NsrxxVO getNsrxx() {
		return nsrxx;
	}

	public void setNsrxx(NsrxxVO nsrxx) {
		this.nsrxx = nsrxx;
	}

	public List getSbsj() {
		return sbsj;
	}

	public void setSbsj(List sbsj) {
		this.sbsj = sbsj;
	}





}
