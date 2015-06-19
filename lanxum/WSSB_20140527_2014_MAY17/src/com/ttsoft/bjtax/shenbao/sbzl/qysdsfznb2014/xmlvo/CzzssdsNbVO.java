package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.xml.XMLVOInterface;

/**
 * ���VO��ǰ̨����xml�ļ���Ӧ
 *
 */
public class CzzssdsNbVO extends YWRootVO implements XMLVOInterface
{
    /**
     * ��˰����Ϣ
     */
    private NsrxxVO_HDZSNb nsrxx = new NsrxxVO_HDZSNb();

    /**
     * �걨��Ϣ
     */
    private SbxxVO sbxx = new SbxxVO();
    
    /**
     * �걨����
     */
    private CzzsNbSbsjVO sbsj = new CzzsNbSbsjVO();
    
    /**
     * �˶���Ϣ
     */
    private HdxxVO hdxx = new HdxxVO();
    
    public CzzssdsNbVO()
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
    
    public NsrxxVO_HDZSNb getNsrxx()
    {
        return nsrxx;
    }
    public void setNsrxx(NsrxxVO_HDZSNb nsrxx)
    {
        this.nsrxx = nsrxx;
    }
    public CzzsNbSbsjVO getSbsj()
    {
        return sbsj;
    }
    public void setSbsj(CzzsNbSbsjVO sbsj)
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
