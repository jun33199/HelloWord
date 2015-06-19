package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * ���VO��ǰ̨����xml�ļ���Ӧ
 *
 */
public class NsrJbxxdjbVO extends YWRootVO implements XMLVOInterface
{
  
    
    /**
     * ��˰�˻�����Ϣ����
     */
    private NsrJbxxVO nsrjbxx = new NsrJbxxVO();
    
   
    public NsrJbxxdjbVO()
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
	public NsrJbxxVO getNsrjbxx() {
		return nsrjbxx;
	}
	public void setNsrjbxx(NsrJbxxVO nsrjbxx) {
		this.nsrjbxx = nsrjbxx;
	}
    
   
}
