package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo;

import java.util.ArrayList;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

public class GdzcjszjyjqkjblistVO implements XMLVOInterface {
		
	private GdzcjszjyjqkjbSbsjVO sbsj=new GdzcjszjyjqkjbSbsjVO();
	private String zj_hc;
	private String zj_xm;	
	private String zj_fwjzw_yz;
	private String zj_fwjzw_bqzje;
	private String zj_fwjzw_ljzje;
	private String zj_jqsbhqtgdzc_yz;	
	private String zj_jqsbhqtgdzc_bqzje;
	private String zj_jqsbhqtgdzc_ljzje;
	private String zj_hj_yz;
	private String zj_hj_bqzje_zczje;	
	private String zj_hj_bqzje_jszje;
	private String zj_hj_ljzje_zczje;
	private String zj_hj_ljzje_jszje;
	
	public GdzcjszjyjqkjblistVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}


	public String toXML() {
		String xmlstr ="<sbsjlist>";
		xmlstr+=sbsj.toXML();

		xmlstr += XMLBuildUtil.appendStringElement("zj_hc", zj_hc);
		xmlstr += XMLBuildUtil.appendStringElement("zj_xm", zj_xm);
		xmlstr += XMLBuildUtil.appendStringElement("zj_fwjzw_yz", zj_fwjzw_yz);
		xmlstr += XMLBuildUtil.appendStringElement("zj_fwjzw_bqzje", zj_fwjzw_bqzje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_fwjzw_ljzje", zj_fwjzw_ljzje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_jqsbhqtgdzc_yz", zj_jqsbhqtgdzc_yz);
		xmlstr += XMLBuildUtil.appendStringElement("zj_jqsbhqtgdzc_bqzje", zj_jqsbhqtgdzc_bqzje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_jqsbhqtgdzc_ljzje", zj_jqsbhqtgdzc_ljzje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_hj_yz", zj_hj_yz);
		xmlstr += XMLBuildUtil.appendStringElement("zj_hj_bqzje_zczje", zj_hj_bqzje_zczje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_hj_bqzje_jszje", zj_hj_bqzje_jszje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_hj_ljzje_zczje", zj_hj_ljzje_zczje);
		xmlstr += XMLBuildUtil.appendStringElement("zj_hj_ljzje_jszje", zj_hj_ljzje_jszje);
		xmlstr+="</sbsjlist>";

		return xmlstr;
	}

	public String toXMLChilds() {

		return null;
	}




	public GdzcjszjyjqkjbSbsjVO getSbsj() {
		return sbsj;
	}

	public void setSbsj(GdzcjszjyjqkjbSbsjVO sbsj) {
		this.sbsj = sbsj;
	}

	public String getZj_hc() {
		return zj_hc;
	}

	public void setZj_hc(String zjHc) {
		zj_hc = zjHc;
	}

	public String getZj_xm() {
		return zj_xm;
	}

	public void setZj_xm(String zjXm) {
		zj_xm = zjXm;
	}

	public String getZj_fwjzw_yz() {
		return zj_fwjzw_yz;
	}

	public void setZj_fwjzw_yz(String zjFwjzwYz) {
		zj_fwjzw_yz = zjFwjzwYz;
	}

	public String getZj_fwjzw_bqzje() {
		return zj_fwjzw_bqzje;
	}

	public void setZj_fwjzw_bqzje(String zjFwjzwBqzje) {
		zj_fwjzw_bqzje = zjFwjzwBqzje;
	}

	public String getZj_fwjzw_ljzje() {
		return zj_fwjzw_ljzje;
	}

	public void setZj_fwjzw_ljzje(String zjFwjzwLjzje) {
		zj_fwjzw_ljzje = zjFwjzwLjzje;
	}

	public String getZj_jqsbhqtgdzc_yz() {
		return zj_jqsbhqtgdzc_yz;
	}

	public void setZj_jqsbhqtgdzc_yz(String zjJqsbhqtgdzcYz) {
		zj_jqsbhqtgdzc_yz = zjJqsbhqtgdzcYz;
	}

	public String getZj_jqsbhqtgdzc_bqzje() {
		return zj_jqsbhqtgdzc_bqzje;
	}

	public void setZj_jqsbhqtgdzc_bqzje(String zjJqsbhqtgdzcBqzje) {
		zj_jqsbhqtgdzc_bqzje = zjJqsbhqtgdzcBqzje;
	}

	public String getZj_jqsbhqtgdzc_ljzje() {
		return zj_jqsbhqtgdzc_ljzje;
	}

	public void setZj_jqsbhqtgdzc_ljzje(String zjJqsbhqtgdzcLjzje) {
		zj_jqsbhqtgdzc_ljzje = zjJqsbhqtgdzcLjzje;
	}

	public String getZj_hj_yz() {
		return zj_hj_yz;
	}

	public void setZj_hj_yz(String zjHjYz) {
		zj_hj_yz = zjHjYz;
	}

	public String getZj_hj_bqzje_zczje() {
		return zj_hj_bqzje_zczje;
	}

	public void setZj_hj_bqzje_zczje(String zjHjBqzjeZczje) {
		zj_hj_bqzje_zczje = zjHjBqzjeZczje;
	}

	public String getZj_hj_bqzje_jszje() {
		return zj_hj_bqzje_jszje;
	}

	public void setZj_hj_bqzje_jszje(String zjHjBqzjeJszje) {
		zj_hj_bqzje_jszje = zjHjBqzjeJszje;
	}

	public String getZj_hj_ljzje_zczje() {
		return zj_hj_ljzje_zczje;
	}

	public void setZj_hj_ljzje_zczje(String zjHjLjzjeZczje) {
		zj_hj_ljzje_zczje = zjHjLjzjeZczje;
	}

	public String getZj_hj_ljzje_jszje() {
		return zj_hj_ljzje_jszje;
	}

	public void setZj_hj_ljzje_jszje(String zjHjLjzjeJszje) {
		zj_hj_ljzje_jszje = zjHjLjzjeJszje;
	}

}
