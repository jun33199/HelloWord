/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author guzx
 * 电子缴库专用缴款书
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZyJks02VO implements XMLVOInterface{

    //税种税目代码；
    String yhdm;

    String yhmc;
    
    
    //税种代码
    String zh;
    //税种名称
    String sbrq;
    //预算级次代码
    String sbbh;

    //计算机代码
    String skgk;
    //纳税人名称
    String gkzzjgdm;
    //税款类型代码
    String zwbs;
    //税款类型名称
    String hjsjje;
    //征收机关代码
    String hjsjjedx;
    //限缴日期
    String swjgzzjgmc;
    //录入人
    String djzclxmc;
    //税款所属开始日期
    String lxdh;
    //税款所属结束日期
    String lsgxmc;
    List nxxm=new ArrayList();
    private Map m = new HashMap();
  
	/**
	 * 
	 */
	public ZyJks02VO() {
		super();
        m.put("nxxmlist", "com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Nxxm02VO");
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
        return m;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
        String xmlstr = "<sbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("zwbs",zwbs);
        xmlstr += XMLBuildUtil.appendStringElement("yhdm",yhdm);
        xmlstr += XMLBuildUtil.appendStringElement("yhmc",yhmc);
        xmlstr += XMLBuildUtil.appendStringElement("zh",zh);
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("sbbh",sbbh);
        xmlstr += XMLBuildUtil.appendStringElement("skgk",skgk);
        xmlstr += XMLBuildUtil.appendStringElement("gkzzjgdm",gkzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgmc",swjgzzjgmc);
        xmlstr += XMLBuildUtil.appendStringElement("djzclxmc",djzclxmc);
        
        xmlstr += XMLBuildUtil.appendStringElement("lxdh",lxdh);
        xmlstr += XMLBuildUtil.appendStringElement("lsgxmc",lsgxmc);
        xmlstr += XMLBuildUtil.appendStringElement("hjsjje",hjsjje);
        xmlstr += XMLBuildUtil.appendStringElement("hjsjjedx",hjsjjedx);
        
        if (nxxm != null && nxxm.size()>0)
        {
            //xmlstr += "<nxxmlist>";
            for (int i = 0; i < nxxm.size(); i++)
            {
                xmlstr += ((Nxxm02VO) nxxm.get(i)).toXML();
            }
            //xmlstr += "</nxxmlist>";
        }
        return xmlstr;
	}

	/**
	 * @return Returns the lxdh.
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh The lxdh to set.
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return Returns the djzclxmc.
	 */
	public String getDjzclxmc() {
		return djzclxmc;
	}
	/**
	 * @param djzclxmc The djzclxmc to set.
	 */
	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}
	/**
	 * @return Returns the gkzzjgdm.
	 */
	public String getGkzzjgdm() {
		return gkzzjgdm;
	}
	/**
	 * @param gkzzjgdm The gkzzjgdm to set.
	 */
	public void setGkzzjgdm(String gkzzjgdm) {
		this.gkzzjgdm = gkzzjgdm;
	}
	/**
	 * @return Returns the hjsjje.
	 */
	public String getHjsjje() {
		return hjsjje;
	}
	/**
	 * @param hjsjje The hjsjje to set.
	 */
	public void setHjsjje(String hjsjje) {
		this.hjsjje = hjsjje;
	}
	/**
	 * @return Returns the hjsjjedx.
	 */
	public String getHjsjjedx() {
		return hjsjjedx;
	}
	/**
	 * @param hjsjjedx The hjsjjedx to set.
	 */
	public void setHjsjjedx(String hjsjjedx) {
		this.hjsjjedx = hjsjjedx;
	}
	/**
	 * @return Returns the lsgxmc.
	 */
	public String getLsgxmc() {
		return lsgxmc;
	}
	/**
	 * @param lsgxmc The lsgxmc to set.
	 */
	public void setLsgxmc(String lsgxmc) {
		this.lsgxmc = lsgxmc;
	}
	/**
	 * @return Returns the sbbh.
	 */
	public String getSbbh() {
		return sbbh;
	}
	/**
	 * @param sbbh The sbbh to set.
	 */
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	/**
	 * @return Returns the sbrq.
	 */
	public String getSbrq() {
		return sbrq;
	}
	/**
	 * @param sbrq The sbrq to set.
	 */
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	/**
	 * @return Returns the skgk.
	 */
	public String getSkgk() {
		return skgk;
	}
	/**
	 * @param skgk The skgk to set.
	 */
	public void setSkgk(String skgk) {
		this.skgk = skgk;
	}
	/**
	 * @return Returns the swjgzzjgmc.
	 */
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	/**
	 * @param swjgzzjgmc The swjgzzjgmc to set.
	 */
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	/**
	 * @return Returns the yhdm.
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdm The yhdm to set.
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return Returns the yhmc.
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmc The yhmc to set.
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * @return Returns the zh.
	 */
	public String getZh() {
		return zh;
	}
	/**
	 * @param zh The zh to set.
	 */
	public void setZh(String zh) {
		this.zh = zh;
	}
	/**
	 * @return Returns the zwbs.
	 */
	public String getZwbs() {
		return zwbs;
	}
	/**
	 * @param zwbs The zwbs to set.
	 */
	public void setZwbs(String zwbs) {
		this.zwbs = zwbs;
	}
	/**
	 * @return Returns the nxxm.
	 */
	public List getNxxm() {
		return nxxm;
	}
	/**
	 * @param nxxm The nxxm to set.
	 */
	public void setNxxm(List nxxm) {
		this.nxxm = nxxm;
	}
}
