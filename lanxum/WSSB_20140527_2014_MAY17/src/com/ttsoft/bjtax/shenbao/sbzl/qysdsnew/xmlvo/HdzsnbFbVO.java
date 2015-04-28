package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo;

import java.util.*;

import com.syax.common.xml.*;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.bjtax.ca.vo.YWRootVO;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class HdzsnbFbVO extends YWRootVO implements XMLVOInterface {

    /**
     * 纳税人信息
     */
    private NsrxxVO nsrxx = new NsrxxVO();

    /**
     * 申报信息
     */
    private SbxxVO sbxx = new SbxxVO();

    /**
     * 申报数据
     */
    private HdzsFbSbsjVO sbsj = new HdzsFbSbsjVO();

    /**
     * 核定信息
     */
    private HdxxVO hdxx = new HdxxVO();

    public HdzsnbFbVO() {
        super();
    }

    /**
     * getListTypeMap
     *
     * @return Map
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public Map getListTypeMap() {
        return null;
    }

    /**
     * toXML
     *
     * @return String
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public String toXML() {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";

        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
    }

    /**
     * toXMLChilds
     *
     * @return String
     * @todo Implement this com.syax.common.xml.XMLVOInterface method
     */
    public String toXMLChilds() {
        String xmlstr = "";
        xmlstr += nsrxx.toXML();
        xmlstr += hdxx.toXML();
        xmlstr += sbxx.toXML();
        xmlstr += sbsj.toXML();
        return xmlstr;
    }

    public HdxxVO getHdxx() {
        return hdxx;
    }

    public NsrxxVO getNsrxx() {
        return nsrxx;
    }

    public HdzsFbSbsjVO getSbsj() {
        return sbsj;
    }

    public SbxxVO getSbxx() {
        return sbxx;
    }

    public void setHdxx(HdxxVO hdxx) {
        this.hdxx = hdxx;
    }

    public void setNsrxx(NsrxxVO nsrxx) {
        this.nsrxx = nsrxx;
    }

    public void setSbsj(HdzsFbSbsjVO sbsj) {
        this.sbsj = sbsj;
    }

    public void setSbxx(SbxxVO sbxx) {
        this.sbxx = sbxx;
    }
}
