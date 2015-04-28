package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 
 * <p>
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 资料清单VO
 *  
 * 
 *     
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 *  * @author Chenmt
 * @version 1.0
 */
public class JmbaZlqdVO  implements XMLVOInterface{

	
	/**
	 * 序号
	 */
	private String xh;
	
	/**
	 * 资料清单
	 */
	private String zlqd;

	/**
	 * 是否可以删除
	 */
	private String sfkysc;

	/**
	 * 是否审核通过
	 */
	private String sfshtg;

	/**
	 * 是否审核通过名称
	 */
	private String sfshtgmc;

	/**
	 * 资料清单代码
	 */
	private String zlqddm;

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		//if (xh != null && xh.length > 0){
			//for(int i=0;i<xh.length;i++){
				xmlstr += "<bajlzlqd>";
				xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
				xmlstr += XMLBuildUtil.appendStringElement("zlqd", zlqd);
				xmlstr += XMLBuildUtil.appendStringElement("sfkysc", sfkysc);
				xmlstr += XMLBuildUtil.appendStringElement("sfshtg", sfshtg);
				xmlstr += XMLBuildUtil.appendStringElement("sfshtgmc", sfshtgmc);
				xmlstr += XMLBuildUtil.appendStringElement("zlqddm", zlqddm);
				xmlstr += "</bajlzlqd>";
			//}
		//}
		
			return xmlstr;
	}

	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZlqd() {
		return zlqd;
	}

	public void setZlqd(String zlqd) {
		this.zlqd = zlqd;
	}


	/**
	 * @return Returns the sfkysc.
	 */
	public String getSfkysc() {
		return sfkysc;
	}
	/**
	 * @param sfkysc The sfkysc to set.
	 */
	public void setSfkysc(String sfkysc) {
		this.sfkysc = sfkysc;
	}
	/**
	 * @return Returns the sfshtg.
	 */
	public String getSfshtg() {
		return sfshtg;
	}
	/**
	 * @param sfshtg The sfshtg to set.
	 */
	public void setSfshtg(String sfshtg) {
		this.sfshtg = sfshtg;
	}
	/**
	 * @return Returns the zlqddm.
	 */
	public String getZlqddm() {
		return zlqddm;
	}
	/**
	 * @param zlqddm The zlqddm to set.
	 */
	public void setZlqddm(String zlqddm) {
		this.zlqddm = zlqddm;
	}
	/**
	 * @return Returns the sfshtgmc.
	 */
	public String getSfshtgmc() {
		return sfshtgmc;
	}
	/**
	 * @param sfshtgmc The sfshtgmc to set.
	 */
	public void setSfshtgmc(String sfshtgmc) {
		this.sfshtgmc = sfshtgmc;
	}
}
