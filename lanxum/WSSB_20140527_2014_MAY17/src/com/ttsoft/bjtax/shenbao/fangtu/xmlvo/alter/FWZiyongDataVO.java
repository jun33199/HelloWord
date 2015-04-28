package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWZiyongVO;

/**
 * FWZiyong 行数据,包括登记数据,变更数据
 */
public class FWZiyongDataVO extends BaseDataVO implements XMLVOInterface
{

	FWZiyongVO regVO = new FWZiyongVO();
	FWZiyongAlterVO alterVO = new FWZiyongAlterVO();
	
    public FWZiyongDataVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "";
        xmlstr += toXMLChilds();
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        xmlstr += super.toXML();
        if ( regVO != null )
        	xmlstr += "<regVO>" + regVO.toXML() + "</regVO>";
        if ( alterVO != null )
        	xmlstr += "<alterVO>" + alterVO.toXML() + "</alterVO>";
		
        return xmlstr;
    }
	public FWZiyongAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(FWZiyongAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public FWZiyongVO getRegVO() {
		return regVO;
	}
	public void setRegVO(FWZiyongVO regVO) {
		this.regVO = regVO;
	}
    
    
}
