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


public class Zjgxx2014VO implements XMLVOInterface
{
    /**
     * 总机构计算机代码
     */
    private String jsjdm = "";

    /**
     * 总机构纳税人识别号
     */
    private String nsrsbh = "";

    /**
     * 总机构名称
     */
    private String nsrmc = "";

    /**
     * 总机构收入总额
     */
    private String srze = "";
    private String ynsdse = "";

    /**
     * 总机构工资总额
     */
    private String gzze = "";
    private String ftsdse = "";

    /**
     * 总机构资产总额
     */
    private String zcze = "";
    private String fpsdse = "";

    /**
     * 总机构合计金额
     */
    private String hj = "";

    /**
     * 总机购分支机构分摊税额
     */
    private String ftse = "";
    private String fzjgftse = "";

    public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getFtsdse() {
		return ftsdse;
	}

	public void setFtsdse(String ftsdse) {
		this.ftsdse = ftsdse;
	}

	public String getFpsdse() {
		return fpsdse;
	}

	public void setFpsdse(String fpsdse) {
		this.fpsdse = fpsdse;
	}

	public String getFzjgftse() {
		return fzjgftse;
	}

	public void setFzjgftse(String fzjgftse) {
		this.fzjgftse = fzjgftse;
	}

	public Zjgxx2014VO()
    {
    }

    public Map getListTypeMap()
    {
        return null;
    }

    public String toXML()
    {
        String xmlstr = "<zjgxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</zjgxx>";
        return xmlstr;
    }

    public String toXMLChilds()
    {
        String xmlstr = "";
        //总机构计算机代码
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", this.jsjdm);
        //总机构纳税人识别号
        xmlstr += XMLBuildUtil.appendStringElement("nsrsbh", this.nsrsbh);
        //总机构名称
        xmlstr += XMLBuildUtil.appendStringElement("nsrmc", this.nsrmc);
        //总机构收入总额
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse", this.ynsdse);
        //总机构工资总额
        xmlstr += XMLBuildUtil.appendStringElement("ftsdse", this.ftsdse);
        //总机构资产总额
        xmlstr += XMLBuildUtil.appendStringElement("fpsdse", this.fpsdse);
        //总机购分支机构分摊税额
        xmlstr += XMLBuildUtil.appendStringElement("fzjgftse", this.fzjgftse);
        return xmlstr;
    }

    public String getFtse()
    {
        return ftse;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
    }

    public String getJsjdm()
    {
        return jsjdm;
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

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setNsrsbh(String nsrsbh)
    {
        this.nsrsbh = nsrsbh;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

}
