package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDZiyongVO;

/**
 * FWChengzu 行数据,包括登记数据,变更数据
 */
public class TDZiyongDataVO extends BaseDataVO implements XMLVOInterface
{

	TDZiyongVO regVO = new TDZiyongVO();
	TDZiyongAlterVO alterVO = new TDZiyongAlterVO();
	
    public TDZiyongDataVO()
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
	public TDZiyongAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(TDZiyongAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public TDZiyongVO getRegVO() {
		return regVO;
	}
	public void setRegVO(TDZiyongVO regVO) {
		this.regVO = regVO;
	}
    
    
}
