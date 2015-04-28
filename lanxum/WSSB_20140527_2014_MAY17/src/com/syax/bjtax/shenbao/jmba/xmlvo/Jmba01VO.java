/*
 * Created on 2009-12-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Jmba01VO implements JmbamxVoInterface {
	private String hc;   
	/** 资源综合利用种类代码
	    * 
	    * @pdOid e9478d76-b36b-442a-aacb-96c08b67b908 */
	   private java.lang.String zyzhlyzldm;
	   /** 资源综合利用种类名称
	    * 
	    * @pdOid e9478d76-b36b-442a-aacb-96c08b67b908 */
	   private java.lang.String zyzhlyzlmc;
	   /** 文件名称
	    * 
	    * @pdOid 09045daa-9e3b-478b-97d0-7007fa1d34e9 */
	   private java.lang.String wjmc;
	   /** 文号
	    * 
	    * @pdOid 3705ff41-8fdf-43f0-841f-5eac69c5ed31 */
	   private java.lang.String wh;
	   /** 证书编号
	    * 
	    * @pdOid a39ba878-1d9d-4e53-8467-86ef6bd55e46 */
	   private java.lang.String zsbh;
	   /** 证书有效开始日期
	    * 
	    * @pdOid b44585ce-c87a-459f-9998-3dc75f4ff0c3 */
	   private String zsyxksrq;
	   /** 证书有效截止日期
	    * 
	    * @pdOid 3d0f8fb2-8c01-4091-a6ec-cb324f3b9d89 */
	   private String zsyxjzrq;
	   /** 是否提交声明,0:是,1:否
	    * 
	    * @pdOid a60e2734-3f51-4f87-a2a2-683707f04f8e */
	   private java.lang.String sftjsm;
	   /** 是否提交声明,0:是,1:否
	    * 
	    * @pdOid a60e2734-3f51-4f87-a2a2-683707f04f8e */
	   private java.lang.String sftjsmmc;
	   /** 取得收入(单位:元)
	    * 
	    * @pdOid b1e39906-3341-45ce-87fd-4dc4e4149203 */
	   private String qdsr;
	   /** 减计收入(单位:元)
	    * 
	    * @pdOid 4a6202ce-60f4-45c4-9411-f7e1c6c50a65 */
	   private String jjsr;
	   /** 审核标记,0:通过,1:不通过
	    * 
	    * @pdOid e0c3cdd2-978f-45ff-964a-1008c1ddc27e */
	   private java.lang.String shbj;
	   /** 序号
	    * 
	    * @pdOid f2c79ffb-fdfb-4bba-99ae-51bc77cf80ec */
	   private java.lang.String xh;


	

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
	 */
	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
		String xmlstr = "";
				xmlstr += "<qysdsjmba>";
				xmlstr += XMLBuildUtil.appendStringElement("hc", hc);
				xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
				xmlstr += XMLBuildUtil.appendStringElement("zyzhlyzldm", zyzhlyzldm);
				xmlstr += XMLBuildUtil.appendStringElement("zyzhlyzlmc", zyzhlyzlmc);
				xmlstr += XMLBuildUtil.appendStringElement("wjmc", wjmc);
				xmlstr += XMLBuildUtil.appendStringElement("wh", wh);
				xmlstr += XMLBuildUtil.appendStringElement("zsbh", zsbh);
				xmlstr += XMLBuildUtil.appendStringElement("zsyxksrq", zsyxksrq);
				xmlstr += XMLBuildUtil.appendStringElement("zsyxjzrq", zsyxjzrq);
				//xmlstr += XMLBuildUtil.appendStringElement("sftjsm", sftjsm);
				//xmlstr += XMLBuildUtil.appendStringElement("sftjsmmc", sftjsmmc);
				xmlstr += XMLBuildUtil.appendStringElement("qdsr", qdsr);
				xmlstr += XMLBuildUtil.appendStringElement("jjsr", jjsr);
				xmlstr += "</qysdsjmba>";
		
			return xmlstr;
	}

	/* (non-Javadoc)
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the jJSR.
	 */
	public String getJjsr() {
		return jjsr;
	}
	/**
	 * @param jjsr The jJSR to set.
	 */
	public void setJjsr(String jjsr) {
		this.jjsr = jjsr;
	}
	/**
	 * @return Returns the qDSR.
	 */
	public String getQdsr() {
		return qdsr;
	}
	/**
	 * @param qdsr The qDSR to set.
	 */
	public void setQdsr(String qdsr) {
		this.qdsr = qdsr;
	}
	/**
	 * @return Returns the sFTJSM.
	 */
	public java.lang.String getSftjsm() {
		return sftjsm;
	}
	/**
	 * @param sftjsm The sFTJSM to set.
	 */
	public void setSftjsm(java.lang.String sftjsm) {
		this.sftjsm = sftjsm;
	}
	/**
	 * @return Returns the sHBJ.
	 */
	public java.lang.String getShbj() {
		return shbj;
	}
	/**
	 * @param shbj The sHBJ to set.
	 */
	public void setShbj(java.lang.String shbj) {
		this.shbj = shbj;
	}
	/**
	 * @return Returns the wH.
	 */
	public java.lang.String getWh() {
		return wh;
	}
	/**
	 * @param wh The wH to set.
	 */
	public void setWh(java.lang.String wh) {
		this.wh = wh;
	}
	/**
	 * @return Returns the wJMC.
	 */
	public java.lang.String getWjmc() {
		return wjmc;
	}
	/**
	 * @param wjmc The wJMC to set.
	 */
	public void setWjmc(java.lang.String wjmc) {
		this.wjmc = wjmc;
	}
	/**
	 * @return Returns the xH.
	 */
	public java.lang.String getXh() {
		return xh;
	}
	/**
	 * @param xh The xH to set.
	 */
	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}
	/**
	 * @return Returns the zSBH.
	 */
	public java.lang.String getZsbh() {
		return zsbh;
	}
	/**
	 * @param zsbh The zSBH to set.
	 */
	public void setZsbh(java.lang.String zsbh) {
		this.zsbh = zsbh;
	}
	/**
	 * @return Returns the zSYXJZRQ.
	 */
	public String getZsyxjzrq() {
		return zsyxjzrq;
	}
	/**
	 * @param zsyxjzrq The zSYXJZRQ to set.
	 */
	public void setZsyxjzrq(String zsyxjzrq) {
		this.zsyxjzrq = zsyxjzrq;
	}
	/**
	 * @return Returns the zSYXKSRQ.
	 */
	public String getZsyxksrq() {
		return zsyxksrq;
	}
	/**
	 * @param zsyxksrq The zSYXKSRQ to set.
	 */
	public void setZsyxksrq(String zsyxksrq) {
		this.zsyxksrq = zsyxksrq;
	}
	/**
	 * @return Returns the zYZHLYZLDM.
	 */
	public java.lang.String getZyzhlyzldm() {
		return zyzhlyzldm;
	}
	/**
	 * @param zyzhlyzldm The zYZHLYZLDM to set.
	 */
	public void setZyzhlyzldm(java.lang.String zyzhlyzldm) {
		this.zyzhlyzldm = zyzhlyzldm;
	}
	/**
	 * @return Returns the zYZHLYZLMC.
	 */
	public java.lang.String getZyzhlyzlmc() {
		return zyzhlyzlmc;
	}
	/**
	 * @param zyzhlyzlmc The zYZHLYZLMC to set.
	 */
	public void setZyzhlyzlmc(java.lang.String zyzhlyzlmc) {
		this.zyzhlyzlmc = zyzhlyzlmc;
	}
	/**
	 * @return Returns the hc.
	 */
	public String getHc() {
		return hc;
	}
	/**
	 * @param hc The hc to set.
	 */
	public void setHc(String hc) {
		this.hc = hc;
	}
	/**
	 * @return Returns the sftjsmmc.
	 */
	public java.lang.String getSftjsmmc() {
		return sftjsmmc;
	}
	/**
	 * @param sftjsmmc The sftjsmmc to set.
	 */
	public void setSftjsmmc(java.lang.String sftjsmmc) {
		this.sftjsmmc = sftjsmmc;
	}
}
