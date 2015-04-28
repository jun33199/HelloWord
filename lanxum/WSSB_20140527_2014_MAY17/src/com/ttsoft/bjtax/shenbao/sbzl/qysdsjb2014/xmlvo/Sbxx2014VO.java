package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

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

public class Sbxx2014VO implements XMLVOInterface
{
    /**
     * 方式代码
     */
    private String fsdm = "";

    /**
     * 季度
     */
    private String jd = "";

    /**
     * 年度
     */
    private String nd = "";

    /**
     * 申报日期
     */
    private String sbrq = "";

    /**
     * 展现申报日期
     */
    private String sbrqShow = "";

    /**
     * 税款所属开始日期
     */
    private String skssksrq = "";

    /**
     * 税款所属结束日期
     */
    private String skssjsrq = "";

    /**
     * 分配比例有效期起
     */
    private String fpblyxqq = "";

    /**
     * 分配比例有效期止
     */
    private String fpblyxqz = "";


    public Sbxx2014VO()
    {
        super();
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String toXML()
    {
        String xmlstr = "<sbxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</sbxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //方式代码
        xmlstr += XMLBuildUtil.appendStringElement("fsdm", this.fsdm);
        //季度
        xmlstr += XMLBuildUtil.appendStringElement("jd", this.jd);
        //年度
        xmlstr += XMLBuildUtil.appendStringElement("nd", this.nd);
        //申报日期
        xmlstr += XMLBuildUtil.appendStringElement("sbrq", this.sbrq);
        //展现申报日期
        xmlstr += XMLBuildUtil.appendStringElement("sbrqshow", this.sbrqShow);
        //税款所属开始日期
        xmlstr += XMLBuildUtil.appendStringElement("skssksrq", this.skssksrq);
        //税款所属结束日期
        xmlstr += XMLBuildUtil.appendStringElement("skssjsrq", this.skssjsrq);
        //分配比例有效期起
        xmlstr += XMLBuildUtil.appendStringElement("fpblyxqq", this.fpblyxqq);
        //分配比例有效期止
        xmlstr += XMLBuildUtil.appendStringElement("fpblyxqz", this.fpblyxqz);
        return xmlstr;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getFsdm()
    {
        return fsdm;
    }

    public String getJd()
    {
        return jd;
    }

    public String getNd()
    {
        return nd;
    }

    public String getSbrq()
    {
        return sbrq;
    }

    public String getSbrqShow()
    {
        return sbrqShow;
    }

    public String getSkssjsrq()
    {
        return skssjsrq;
    }

    public String getSkssksrq()
    {
        return skssksrq;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setFsdm(String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setJd(String jd)
    {
        this.jd = jd;
    }

    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSbrqShow(String sbrqShow)
    {
        this.sbrqShow = sbrqShow;
    }

    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }
}
