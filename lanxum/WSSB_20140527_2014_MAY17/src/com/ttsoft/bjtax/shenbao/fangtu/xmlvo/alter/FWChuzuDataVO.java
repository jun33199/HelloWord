package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChuzuVO;

/**
 * FWChuzu 行数据,包括登记数据,变更数据
 */
public class FWChuzuDataVO extends BaseDataVO implements XMLVOInterface
{

	FWChuzuVO regVO = new FWChuzuVO();
	FWChuzuAlterVO alterVO = new FWChuzuAlterVO();
	
    public FWChuzuDataVO()
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
	public FWChuzuAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(FWChuzuAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public FWChuzuVO getRegVO() {
		return regVO;
	}
	public void setRegVO(FWChuzuVO regVO) {
		this.regVO = regVO;
	}
    
    
}
