package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * 框架VO与前台传输xml文件对应
 *
 */
public class CzzssdsjbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * 纳税人信息
     */
    private NsrxxVO_HDZS nsrxx = new NsrxxVO_HDZS();

    /**
     * 申报信息
     */
    private SbxxVO sbxx = new SbxxVO();
    
    /**
     * 申报数据
     */
    private CzzsSbsjVO sbsj = new CzzsSbsjVO();
    
    /**
     * 核定信息
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
    
    public NsrxxVO_HDZS getNsrxx()
    {
        return nsrxx;
    }
    public void setNsrxx(NsrxxVO_HDZS nsrxx)
    {
        this.nsrxx = nsrxx;
    }
    public CzzsSbsjVO getSbsj()
    {
        return sbsj;
    }
    public void setSbsj(CzzsSbsjVO sbsj)
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
