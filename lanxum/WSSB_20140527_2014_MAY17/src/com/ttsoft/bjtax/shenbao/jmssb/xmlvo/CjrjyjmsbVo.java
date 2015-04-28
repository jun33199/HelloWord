package com.ttsoft.bjtax.shenbao.jmssb.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * ���òм��˾�ҵ����˰�걨VO
 * 
 * @author xinyy
 */
public class CjrjyjmsbVo  extends YWRootVO implements XMLVOInterface{

	 /**
     * ��˰����Ϣ
     */
    private NsrxxVO_Cjrjy nsrxx = new NsrxxVO_Cjrjy();

    /**
     * �걨��Ϣ
     */
    private CjrjyjmssbsjVO sbxx = new CjrjyjmssbsjVO();
    
    /**
     * �걨����
     */
    //private CjrjyjmssbsjVO sbsj = new CjrjyjmssbsjVO();
    
    /**
     * �˶���Ϣ
     */
    //private HdxxVO hdxx = new HdxxVO();
    
    public CjrjyjmsbVo()
    {
        super();
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
        xmlstr += nsrxx.toXML();
        //xmlstr += hdxx.toXML();
        xmlstr += sbxx.toXML();
        //xmlstr += sbsj.toXML();
        return xmlstr;
    }
    
    public NsrxxVO_Cjrjy getNsrxx()
    {
        return nsrxx;
    }
    
    public void setNsrxx(NsrxxVO_Cjrjy nsrxx)
    {
        this.nsrxx = nsrxx;
    }

	public CjrjyjmssbsjVO getSbxx() {
		return sbxx;
	}

	public void setSbxx(CjrjyjmssbsjVO sbxx) {
		this.sbxx = sbxx;
	}
    
}
