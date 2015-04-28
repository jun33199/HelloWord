package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class FWZiyongVO implements XMLVOInterface
{
	String djbh; //登记编号
	String id; //唯一编号
	
	String fwzl; //房屋坐落
	String cqzsh; //产权证书号
	String fcyz; //房产原值 double
	String swjggz; //税务机关估值 double
	String qzmsyz; //其中免税原值 double
	String qzysyz; //其中应税原值 double
	String nynse; //年应纳税额 double
	String sfdj; //是否代缴
	String bz; //备注
	
	String opFlag; //操作标识
	String fhbs; //复核标识
	
	
	
    public FWZiyongVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<fwZiyongList>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</fwZiyongList>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
		xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
		xmlstr += XMLBuildUtil.appendStringElement("id",id);
		xmlstr += XMLBuildUtil.appendStringElement("fwzl",fwzl);
		xmlstr += XMLBuildUtil.appendStringElement("cqzsh",cqzsh);
		xmlstr += XMLBuildUtil.appendStringElement("fcyz",getFcyz());
		xmlstr += XMLBuildUtil.appendStringElement("swjggz",getSwjggz());
		xmlstr += XMLBuildUtil.appendStringElement("qzmsyz",getQzmsyz());
		xmlstr += XMLBuildUtil.appendStringElement("qzysyz",getQzysyz());
		xmlstr += XMLBuildUtil.appendStringElement("nynse",getNynse());
		xmlstr += XMLBuildUtil.appendStringElement("sfdj",sfdj);
		xmlstr += XMLBuildUtil.appendStringElement("bz",bz);
		xmlstr += XMLBuildUtil.appendStringElement("opFlag",opFlag);
		xmlstr += XMLBuildUtil.appendStringElement("fhbs",fhbs);
		
        return xmlstr;
    }
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCqzsh() {
		return cqzsh;
	}
	public void setCqzsh(String cqzsh) {
		this.cqzsh = cqzsh;
	}
	public String getDjbh() {
		return djbh;
	}
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}
	public String getFcyz() {
		return Utils.formatNumber(fcyz);
	}
	public void setFcyz(String fcyz) {
		this.fcyz = fcyz;
	}
	public String getFwzl() {
		return fwzl;
	}
	public void setFwzl(String fwzl) {
		this.fwzl = fwzl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNynse() {
		return Utils.formatNumber(nynse);
	}
	public void setNynse(String nynse) {
		this.nynse = nynse;
	}
	public String getQzmsyz() {
		return Utils.formatNumber(qzmsyz);
	}
	public void setQzmsyz(String qzmsyz) {
		this.qzmsyz = qzmsyz;
	}
	public String getQzysyz() {
		return Utils.formatNumber(qzysyz);
	}
	public void setQzysyz(String qzysyz) {
		this.qzysyz = qzysyz;
	}
	public String getSfdj() {
		return sfdj;
	}
	public void setSfdj(String sfdj) {
		this.sfdj = sfdj;
	}
	public String getSwjggz() {
		return Utils.formatNumber(swjggz);
	}
	public void setSwjggz(String swjggz) {
		this.swjggz = swjggz;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public String getFhbs() {
		return fhbs;
	}
	public void setFhbs(String fhbs) {
		this.fhbs = fhbs;
	}

}
