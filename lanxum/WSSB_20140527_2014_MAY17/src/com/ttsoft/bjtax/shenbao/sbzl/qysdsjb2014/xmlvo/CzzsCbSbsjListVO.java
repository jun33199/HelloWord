package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class CzzsCbSbsjListVO implements XMLVOInterface {

	

	private List cbMssrxmList=new ArrayList();
	private List cbJzmzxmList=new ArrayList();
	private List cbJmxmList=new ArrayList();
	 Map m = new HashMap();
	public CzzsCbSbsjListVO(){
        super();
        m.put("cbMssrxmList", "com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbMssrxmVO");
        m.put("cbJzmzxmList", "com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbJzmzxmVO");
        m.put("cbJmxmList", "com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbJmxmVO");
	}
	public Map getListTypeMap() {
		return m;
	}
	public String toXML() {
		
		String xmlstr="<cbsbsj>";
		xmlstr += toXMLChilds();
		xmlstr += "</cbsbsj>";
		return xmlstr;
	}
	public String toXMLChilds() {
		String xmlstr = "";
		//System.out.println(szsmdm.length+"(((((");

		if(cbMssrxmList!=null){
			for(int i=0;i<cbMssrxmList.size();i++){
				xmlstr += ((CzzsCbMssrxmVO)cbMssrxmList.get(i)).toXML();				
			}
		}
		if(cbJzmzxmList!=null){
			for(int i=0;i<cbJzmzxmList.size();i++){
				xmlstr += ((CzzsCbJzmzxmVO)cbJzmzxmList.get(i)).toXML();				
			}
		}
		if(cbJmxmList!=null){
			for(int i=0;i<cbJmxmList.size();i++){
				xmlstr += ((CzzsCbJmxmVO)cbJmxmList.get(i)).toXML();				
			}
		}		
			return xmlstr;
	}

	public List getCbJmxmList() {
		return cbJmxmList;
	}
	public void setCbJmxmList(List cbJmxmList) {
		this.cbJmxmList = cbJmxmList;
	}
	public List getCbMssrxmList() {
		return cbMssrxmList;
	}
	public void setCbMssrxmList(List cbMssrxmList) {
		this.cbMssrxmList = cbMssrxmList;
	}
	public List getCbJzmzxmList() {
		return cbJzmzxmList;
	}
	public void setCbJzmzxmList(List cbJzmzxmList) {
		this.cbJzmzxmList = cbJzmzxmList;
	}


}
