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
public class CzzssdsjbVO extends YWRootVO implements XMLVOInterface
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
    private CzzsJbSbsjVO sbsj = new CzzsJbSbsjVO();
    
    /**
     * �˶���Ϣ
     */
    private HdxxVO hdxx = new HdxxVO();
    
    public CzzssdsjbVO()
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
    public CzzsJbSbsjVO getSbsj()
    {
        return sbsj;
    }
    public void setSbsj(CzzsJbSbsjVO sbsj)
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
