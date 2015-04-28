package com.ttsoft.bjtax.shenbao.wsksb.xmlvo;

import java.util.HashMap;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;


public class WsksbVO extends YWRootVO implements XMLVOInterface{

	public WsksbVO(){
		super();
	}
	private Map m = new HashMap();
	public Map getListTypeMap() {
        return m;
	}
	public String toXML() {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";
        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
		System.out.print("xmlstr:");
		System.out.println(xmlstr);
        return xmlstr;
	}
	public String toXMLChilds() {
        String xmlstr = "";
        if (nsrxx!=null )
            xmlstr += nsrxx.toXML();
        if (sbxx!=null )
            xmlstr += sbxx.toXML();
        if (sbsj != null )
            xmlstr += sbsj.toXML();
        return xmlstr;
	}
    /**
     * 纳税人信息 = new Nsrxx02VO()
     */
    private Nsrxx02VO nsrxx = new Nsrxx02VO();

    /**
     * 申报信息
     */
    private Sbxx02VO sbxx = new Sbxx02VO();
    /**
     * 申报数据
     */
    private Sbsj02VO sbsj = new Sbsj02VO();  
    public static void main(String[] args){
    	WsksbVO wsksbvo = new WsksbVO();
    	System.out.println(wsksbvo.toXML());
    }
	public Nsrxx02VO getNsrxx() {
		return nsrxx;
	}
	public void setNsrxx(Nsrxx02VO nsrxx) {
		this.nsrxx = nsrxx;
	}
	public Sbsj02VO getSbsj() {
		return sbsj;
	}
	public void setSbsj(Sbsj02VO sbsj) {
		this.sbsj = sbsj;
	}
	public Sbxx02VO getSbxx() {
		return sbxx;
	}
	public void setSbxx(Sbxx02VO sbxx) {
		this.sbxx = sbxx;
	}
}

