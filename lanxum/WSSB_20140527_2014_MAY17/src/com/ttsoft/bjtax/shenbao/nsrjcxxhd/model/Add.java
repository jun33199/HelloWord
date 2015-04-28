package com.ttsoft.bjtax.shenbao.nsrjcxxhd.model;
import java.io.Serializable;
import java.util.Date;

import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.DateUtil;

/**
* This is the entity / business object
* We will use this business object to demonstrate OR Mapping in Kernl can
* be used for composition.
**/
public class Add implements Serializable {

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<add>");
		sb.append(XMLBuildUtil.appendStringElement("pzcljgdm", this.pzcljgdm));
		sb.append(XMLBuildUtil.appendStringElement("kyslrq", DateUtil.dateFormat(this.kyslrq)));
		sb.append(XMLBuildUtil.appendStringElement("xcjyqx", DateUtil.dateFormat(this.xcjyqx_begin)) + "-" + DateUtil.dateFormat(this.xcjyqx_end));
		sb.append(XMLBuildUtil.appendStringElement("zjmc", this.zjmc));
		sb.append(XMLBuildUtil.appendStringElement("zjhm", this.zjhm));
		sb.append(XMLBuildUtil.appendStringElement("dwxz", this.dwxz));
		sb.append(XMLBuildUtil.appendStringElement("cyrs", this.cyrs));
		sb.append(XMLBuildUtil.appendStringElement("wjrs", this.wjrs));
		
		sb.append(XMLBuildUtil.appendStringElement("zczbbzdm_one", this.zczbbzdm_one));
		sb.append(XMLBuildUtil.appendStringElement("zczbje_one", this.zczbje_one + ""));
		sb.append(XMLBuildUtil.appendStringElement("zcbbzdm_two", this.zcbbzdm_two));
		sb.append(XMLBuildUtil.appendStringElement("zczbje_two", this.zczbje_two + ""));
		sb.append(XMLBuildUtil.appendStringElement("zcbbzdm_three", this.zcbbzdm_three));
		sb.append(XMLBuildUtil.appendStringElement("zczbje_three", this.zczbje_three + ""));
		
		sb.append("</add>");
		return sb.toString();
	}
	
	private static final long serialVersionUID = 1L;
	String cyrs;
	Date kyslrq;
	double gytzbl;
	Date fzrq;
	String gjbzhydm_three;
	double zczbje_one;
	String nsrlx;
	Date xcjyqx_begin;
	double zczbje_three;
	String gjbzhydm_two;
	String hhr;
	String gjhdq;
	Date xcjyqx_end;
	String ggrs;
	String zcbbzdm_three;
	String wjrs;
	String gdgrs;
	String pzcljgdm;
	Date cjrq;
	String zjmc;
	String gjbzhydm_one;
	String yhkhdjzh;
	double zczbje_two;
	String zczbbzdm_one;
	String jsjdm;
	String zjhm;
	String zcbbzdm_two;
	String lsswdjyxq;
	String dwxz;
	public Add () {}
	public String getCyrs() {
		return cyrs;
	}
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}
	public Date getKyslrq() {
		return kyslrq;
	}
	public void setKyslrq(Date kyslrq) {
		this.kyslrq = kyslrq;
	}
	public double getGytzbl() {
		return gytzbl;
	}
	public void setGytzbl(double gytzbl) {
		this.gytzbl = gytzbl;
	}
	public Date getFzrq() {
		return fzrq;
	}
	public void setFzrq(Date fzrq) {
		this.fzrq = fzrq;
	}
	public String getGjbzhydm_three() {
		return gjbzhydm_three;
	}
	public void setGjbzhydm_three(String gjbzhydm_three) {
		this.gjbzhydm_three = gjbzhydm_three;
	}
	public double getZczbje_one() {
		return zczbje_one;
	}
	public void setZczbje_one(double zczbje_one) {
		this.zczbje_one = zczbje_one;
	}
	public String getNsrlx() {
		return nsrlx;
	}
	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}
	public Date getXcjyqx_begin() {
		return xcjyqx_begin;
	}
	public void setXcjyqx_begin(Date xcjyqx_begin) {
		this.xcjyqx_begin = xcjyqx_begin;
	}
	public double getZczbje_three() {
		return zczbje_three;
	}
	public void setZczbje_three(double zczbje_three) {
		this.zczbje_three = zczbje_three;
	}
	public String getGjbzhydm_two() {
		return gjbzhydm_two;
	}
	public void setGjbzhydm_two(String gjbzhydm_two) {
		this.gjbzhydm_two = gjbzhydm_two;
	}
	public String getHhr() {
		return hhr;
	}
	public void setHhr(String hhr) {
		this.hhr = hhr;
	}
	public String getGjhdq() {
		return gjhdq;
	}
	public void setGjhdq(String gjhdq) {
		this.gjhdq = gjhdq;
	}
	public Date getXcjyqx_end() {
		return xcjyqx_end;
	}
	public void setXcjyqx_end(Date xcjyqx_end) {
		this.xcjyqx_end = xcjyqx_end;
	}
	public String getGgrs() {
		return ggrs;
	}
	public void setGgrs(String ggrs) {
		this.ggrs = ggrs;
	}
	public String getZcbbzdm_three() {
		return zcbbzdm_three;
	}
	public void setZcbbzdm_three(String zcbbzdm_three) {
		this.zcbbzdm_three = zcbbzdm_three;
	}
	public String getWjrs() {
		return wjrs;
	}
	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}
	public String getGdgrs() {
		return gdgrs;
	}
	public void setGdgrs(String gdgrs) {
		this.gdgrs = gdgrs;
	}
	public String getPzcljgdm() {
		return pzcljgdm;
	}
	public void setPzcljgdm(String pzcljgdm) {
		this.pzcljgdm = pzcljgdm;
	}
	public Date getCjrq() {
		return cjrq;
	}
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}
	public String getZjmc() {
		return zjmc;
	}
	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}
	public String getGjbzhydm_one() {
		return gjbzhydm_one;
	}
	public void setGjbzhydm_one(String gjbzhydm_one) {
		this.gjbzhydm_one = gjbzhydm_one;
	}
	public String getYhkhdjzh() {
		return yhkhdjzh;
	}
	public void setYhkhdjzh(String yhkhdjzh) {
		this.yhkhdjzh = yhkhdjzh;
	}
	public double getZczbje_two() {
		return zczbje_two;
	}
	public void setZczbje_two(double zczbje_two) {
		this.zczbje_two = zczbje_two;
	}
	public String getZczbbzdm_one() {
		return zczbbzdm_one;
	}
	public void setZczbbzdm_one(String zczbbzdm_one) {
		this.zczbbzdm_one = zczbbzdm_one;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getZcbbzdm_two() {
		return zcbbzdm_two;
	}
	public void setZcbbzdm_two(String zcbbzdm_two) {
		this.zcbbzdm_two = zcbbzdm_two;
	}
	public String getLsswdjyxq() {
		return lsswdjyxq;
	}
	public void setLsswdjyxq(String lsswdjyxq) {
		this.lsswdjyxq = lsswdjyxq;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	
	
}
