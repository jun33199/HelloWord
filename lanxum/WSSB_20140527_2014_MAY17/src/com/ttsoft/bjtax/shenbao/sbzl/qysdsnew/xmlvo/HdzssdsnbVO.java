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
public class HdzssdsnbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * ��˰����Ϣ
     */
    private NsrxxVO nsrxx = new NsrxxVO();

    /**
     * �걨��Ϣ
     */
    private SbxxVO sbxx = new SbxxVO();
    
    /**
     * �걨����
     */
    private HdzsSbsjVO sbsj = new HdzsSbsjVO();
    
    /**
     * �˶���Ϣ
     */
    private HdxxVO hdxx = new HdxxVO();
    
    public HdzssdsnbVO()
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
        xmlstr += nsrxx.toXML();
        xmlstr += hdxx.toXML();
        xmlstr += sbxx.toXML();
        xmlstr += sbsj.toXML();
        return xmlstr;
    }
    
    public NsrxxVO getNsrxx()
    {
        return nsrxx;
    }
    public void setNsrxx(NsrxxVO nsrxx)
    {
        this.nsrxx = nsrxx;
    }
    public HdzsSbsjVO getSbsj()
    {
        return sbsj;
    }
    public void setSbsj(HdzsSbsjVO sbsj)
    {
        this.sbsj = sbsj;
    }
    public SbxxVO getSbxx()
    {
        return sbxx;
    }
    public void setSbxx(SbxxVO sbxx)
    {
        this.sbxx = sbxx;
    }
    public HdxxVO getHdxx()
    {
        return hdxx;
    }
    public void setHdxx(HdxxVO hdxx)
    {
        this.hdxx = hdxx;
    }
}
