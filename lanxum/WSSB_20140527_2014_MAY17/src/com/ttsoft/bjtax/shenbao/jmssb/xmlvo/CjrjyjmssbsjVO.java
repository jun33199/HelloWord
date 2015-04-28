package com.ttsoft.bjtax.shenbao.jmssb.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 安置残疾人就业减免税申报数据VO
 * 
 * @author xinyy
 */
public class CjrjyjmssbsjVO implements XMLVOInterface {

	private String jsjdm="";
	private String skssrq="";
	private String swdjzh="";
	private String nsrmc="";
	private String zcdz="";
	private String dwxz="";
	private String zgzrs="";
	private String cjrzgrs="";
	private String cjrybl="";
	private String jyfw="";
	private String ynyyssr="";
	private String yjyysse="";
	private String xsyhzzse="";
	private String byyjzyysxe="";
	private String syyjzyysxe="";
	private String bykjzyysxe="";
	private String bysjjzyysye="";
	private String bysjyesse="";
	private String bymjzyysxe="";
	private String sqspbh = "";
	private String qxdm = "";
	private String signFlagVal = "";
	private String isError = "";
	private String bz = "";
	private String fsdm ="";
	private String lrrq = "";
	private String lrr = "";
	private String success = "";
	public String getSignFlagVal() {
		return signFlagVal;
	}
	public void setSignFlagVal(String signFlagVal) {
		this.signFlagVal = signFlagVal;
	}
	public String getSqspbh() {
		return sqspbh;
	}
	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public String getIsError() {
		return isError;
	}
	public void setIsError(String isError) {
		this.isError = isError;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getSkssrq() {
		return skssrq;
	}
	public void setSkssrq(String skssrq) {
		this.skssrq = skssrq;
	}
	public String getSwdjzh() {
		return swdjzh;
	}
	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getDwxz() {
		return dwxz;
	}
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	public String getZgzrs() {
		return zgzrs;
	}
	public void setZgzrs(String zgzrs) {
		this.zgzrs = zgzrs;
	}
	public String getCjrzgrs() {
		return cjrzgrs;
	}
	public void setCjrzgrs(String cjrzgrs) {
		this.cjrzgrs = cjrzgrs;
	}
	public String getCjrybl() {
		return cjrybl;
	}
	public void setCjrybl(String cjrybl) {
		this.cjrybl = cjrybl;
	}
	public String getJyfw() {
		return jyfw;
	}
	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
	public String getYnyyssr() {
		return ynyyssr;
	}
	public void setYnyyssr(String ynyyssr) {
		this.ynyyssr = ynyyssr;
	}
	public String getYjyysse() {
		return yjyysse;
	}
	public void setYjyysse(String yjyysse) {
		this.yjyysse = yjyysse;
	}
	public String getXsyhzzse() {
		return xsyhzzse;
	}
	public void setXsyhzzse(String xsyhzzse) {
		this.xsyhzzse = xsyhzzse;
	}
	public String getByyjzyysxe() {
		return byyjzyysxe;
	}
	public void setByyjzyysxe(String byyjzyysxe) {
		this.byyjzyysxe = byyjzyysxe;
	}
	public String getSyyjzyysxe() {
		return syyjzyysxe;
	}
	public void setSyyjzyysxe(String syyjzyysxe) {
		this.syyjzyysxe = syyjzyysxe;
	}
	public String getBykjzyysxe() {
		return bykjzyysxe;
	}
	public void setBykjzyysxe(String bykjzyysxe) {
		this.bykjzyysxe = bykjzyysxe;
	}
	public String getBysjjzyysye() {
		return bysjjzyysye;
	}
	public void setBysjjzyysye(String bysjjzyysye) {
		this.bysjjzyysye = bysjjzyysye;
	}
	public String getBysjyesse() {
		return bysjyesse;
	}
	public void setBysjyesse(String bysjyesse) {
		this.bysjyesse = bysjyesse;
	}
	public String getBymjzyysxe() {
		return bymjzyysxe;
	}
	public void setBymjzyysxe(String bymjzyysxe) {
		this.bymjzyysxe = bymjzyysxe;
	}
	
	public Map getListTypeMap() {
		return null;
	}
	
	public String toXML() {
		String xmlstr = "<sbxx>";
        xmlstr += toXMLChilds();
        xmlstr += "</sbxx>";
        return xmlstr;
	}
	
	public String toXMLChilds() {
		String xmlstr = "";
		
		//xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
		//xmlstr += XMLBuildUtil.appendStringElement("swdjzh",swdjzh);
		//xmlstr += XMLBuildUtil.appendStringElement("nsrmc",nsrmc);
		//xmlstr += XMLBuildUtil.appendStringElement("zcdz",zcdz);
		//xmlstr += XMLBuildUtil.appendStringElement("jyfw",jyfw);
		xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
		xmlstr += XMLBuildUtil.appendStringElement("signFlagVal",signFlagVal);
		xmlstr += XMLBuildUtil.appendStringElement("sqspbh",sqspbh);
		xmlstr += XMLBuildUtil.appendStringElement("success",success);
		xmlstr += XMLBuildUtil.appendStringElement("isError",isError);
		xmlstr += XMLBuildUtil.appendStringElement("fsdm",fsdm);
        xmlstr += XMLBuildUtil.appendStringElement("skssrq",skssrq);
        xmlstr += XMLBuildUtil.appendStringElement("dwxz",dwxz);
        xmlstr += XMLBuildUtil.appendStringElement("zgzrs",zgzrs);
        xmlstr += XMLBuildUtil.appendStringElement("cjrzgrs",cjrzgrs);
        xmlstr += XMLBuildUtil.appendStringElement("cjrybl",cjrybl); 
        xmlstr += XMLBuildUtil.appendStringElement("ynyyssr",ynyyssr);
        xmlstr += XMLBuildUtil.appendStringElement("yjyysse",yjyysse);
        xmlstr += XMLBuildUtil.appendStringElement("xsyhzzse",xsyhzzse);
        xmlstr += XMLBuildUtil.appendStringElement("byyjzyysxe",byyjzyysxe);
        xmlstr += XMLBuildUtil.appendStringElement("syyjzyysxe",syyjzyysxe);
        xmlstr += XMLBuildUtil.appendStringElement("bykjzyysxe",bykjzyysxe);
        xmlstr += XMLBuildUtil.appendStringElement("bysjjzyysye",bysjjzyysye);
        xmlstr += XMLBuildUtil.appendStringElement("bysjyesse",bysjyesse);
        xmlstr += XMLBuildUtil.appendStringElement("bymjzyysxe",bymjzyysxe);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);
        return xmlstr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFsdm() {
		return fsdm;
	}
	public void setFsdm(String fsdm) {
		this.fsdm = fsdm;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
}
