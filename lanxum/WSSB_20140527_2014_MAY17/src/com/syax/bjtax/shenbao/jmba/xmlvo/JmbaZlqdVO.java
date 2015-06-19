package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.bjtax.ca.vo.YWRootVO;
import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
/**
 * 
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: �����嵥VO
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
	 * ���
	 */
	private String xh;
	
	/**
	 * �����嵥
	 */
	private String zlqd;

	/**
	 * �Ƿ����ɾ��
	 */
	private String sfkysc;

	/**
	 * �Ƿ����ͨ��
	 */
	private String sfshtg;

	/**
	 * �Ƿ����ͨ������
	 */
	private String sfshtgmc;

	/**
	 * �����嵥����
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
