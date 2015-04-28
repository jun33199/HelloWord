package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo;


/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.util.*;

import com.syax.common.xml.*;
import com.syax.common.xml.util.*;

public class FzjgxxNb2014VO implements XMLVOInterface
{
    /**
     * 索引
     */
    private String index = "";

    /**
     * 分支机构纳税人识别号
     */
    private String nsrsbh = "";

    /**
     * 分支机构名称
     */
    private String nsrmc = "";

    /**
     * 分支机构收入总额
     */
    private String srze = "";

    /**
     * 分支机构工资总额
     */
    private String gzze = "";

    /**
     * 分支机构资产总额
     */
    private String zcze = "";

    /**
     * 分支机构合计金额
     */
    private String hj = "";

    /**
     * 分支机构分配比例
     */
    private String fpbl = "";

    /**
     * 分支机构分配税额
     */
    private String fpse = "";


    public FzjgxxNb2014VO()
    {
    }

    public String toXML()
    {
        String xmlstr = "<mxxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</mxxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //索引
        xmlstr += XMLBuildUtil.appendStringElement("index", this.index);
        //分支机构纳税人识别号
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh", this.nsrsbh);
        //分支机构名称
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc);
        //分支机构收入总额
        xmlstr += XMLBuildUtil.appendStringElement("srze", this.srze);
        //分支机构工资总额
        xmlstr += XMLBuildUtil.appendStringElement("gzze", this.gzze);
        //分支机构资产总额
        xmlstr += XMLBuildUtil.appendStringElement("zcze", this.zcze);
        //分支机构分配比例
        xmlstr += XMLBuildUtil.appendStringElement("fpbl", this.fpbl);
        //分支机构分配税额
        xmlstr += XMLBuildUtil.appendStringElement("fpse", this.fpse);

        return xmlstr;
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String getFpbl()
    {
        return fpbl;
    }

    public String getFpse()
    {
        return fpse;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public String getNsrsbh()
    {
        return nsrsbh;
    }

    public String getSrze()
    {
        return srze;
    }

    public String getZcze()
    {
        return zcze;
    }

    public String getIndex()
    {
        return index;
    }

    public void setFpbl(String fpbl)
    {
        this.fpbl = fpbl;
    }

    public void setFpse(String fpse)
    {
        this.fpse = fpse;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setIndex(String index)
    {
        this.index = index;
    }

}
