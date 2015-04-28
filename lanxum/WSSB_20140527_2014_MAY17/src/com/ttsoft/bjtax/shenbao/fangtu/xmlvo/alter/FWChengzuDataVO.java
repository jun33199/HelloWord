package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChengzuVO;

/**
 * FWChengzu 行数据,包括登记数据,变更数据
 */
public class FWChengzuDataVO extends BaseDataVO implements XMLVOInterface
{

	FWChengzuVO regVO = new FWChengzuVO();
	FWChengzuAlterVO alterVO = new FWChengzuAlterVO();
	
    public FWChengzuDataVO()
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
	public FWChengzuAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(FWChengzuAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public FWChengzuVO getRegVO() {
		return regVO;
	}
	public void setRegVO(FWChengzuVO regVO) {
		this.regVO = regVO;
	}
    
    
}
