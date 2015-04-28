package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChuzuVO;

/**
 * TDChuzu 行数据,包括登记数据,变更数据
 */
public class TDChuzuDataVO extends BaseDataVO implements XMLVOInterface
{

	TDChuzuVO regVO = new TDChuzuVO();
	TDChuzuAlterVO alterVO = new TDChuzuAlterVO();
	
    public TDChuzuDataVO()
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
	public TDChuzuAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(TDChuzuAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public TDChuzuVO getRegVO() {
		return regVO;
	}
	public void setRegVO(TDChuzuVO regVO) {
		this.regVO = regVO;
	}
    
    
}
