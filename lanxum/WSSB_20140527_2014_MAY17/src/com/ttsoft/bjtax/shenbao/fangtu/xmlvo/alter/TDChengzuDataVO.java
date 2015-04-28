package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChengzuVO;

/**
 * TDChengzu 行数据,包括登记数据,变更数据
 */
public class TDChengzuDataVO extends BaseDataVO implements XMLVOInterface
{

	TDChengzuVO regVO = new TDChengzuVO();
	TDChengzuAlterVO alterVO = new TDChengzuAlterVO();
	
    public TDChengzuDataVO()
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
	public TDChengzuAlterVO getAlterVO() {
		return alterVO;
	}
	public void setAlterVO(TDChengzuAlterVO alterVO) {
		this.alterVO = alterVO;
	}
	public TDChengzuVO getRegVO() {
		return regVO;
	}
	public void setRegVO(TDChengzuVO regVO) {
		this.regVO = regVO;
	}
    
    
}
