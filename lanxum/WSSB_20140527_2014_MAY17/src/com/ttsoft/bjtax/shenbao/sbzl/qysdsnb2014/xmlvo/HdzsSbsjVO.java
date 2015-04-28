package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;

/**
 * 与xml文件中业务数据部分对应
 *
 */
public class HdzsSbsjVO implements XMLVOInterface
{
	
	private String syze="";
	private String bzssr="";
	private String mssr="";
	private String yssre="";
	private String yssdl="";
	private String ynssde="";
	private String sl="";
	private String ynsdse="";
	private String yyjsdse="";
	private String ybsdse="";
	private String sbrqshow="";
	
	private String zczb="";
	private String zcze="";
	private String zgrs="";
	private String sshy="";	
	private String sshydm="";	
	//减：符合条件的小型微利企业减免所得税额
	private String xwqyjmsdse = ""; //12h
	//税务机关核定应纳所得税额
	private String swjghdynsdse = "";  //15h
		

	//税款所属年度等于开业登记日期Y 否N
	private String sfxkh;
	//获取税款所属期所在年度上一年度征收方式
	private String syndZsfsdm;
	//获取上一年度核定征收主表行6数据
	private String syndZbh6;
	//获取上一年度汇算清缴主表行9数据
	private String syndZbh25;
	//获取上一年度汇算清缴附表5行45、46、47的校验结果
	private String syndFb5jyjg;

	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getZcze() {
		return zcze;
	}
	public void setZcze(String zcze) {
		this.zcze = zcze;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getSyze() {
		return syze;
	}
	public void setSyze(String syze) {
		this.syze = syze;
	}
	public String getYbsdse() {
		return ybsdse;
	}
	public void setYbsdse(String ybsdse) {
		this.ybsdse = ybsdse;
	}
	public String getYnsdse() {
		return ynsdse;
	}
	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}
	public String getYnssde() {
		return ynssde;
	}
	public void setYnssde(String ynssde) {
		this.ynssde = ynssde;
	}
	public String getYssdl() {
		return yssdl;
	}
	public void setYssdl(String yssdl) {
		this.yssdl = yssdl;
	}
	public String getYyjsdse() {
		return yyjsdse;
	}
	public void setYyjsdse(String yyjsdse) {
		this.yyjsdse = yyjsdse;
	}
	public String getSbrqshow() {
		return sbrqshow;
	}
	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}
	public HdzsSbsjVO()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<sbsj>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
        
        xmlstr += XMLBuildUtil.appendStringElement("sbrqshow",sbrqshow);
        xmlstr += XMLBuildUtil.appendStringElement("syze",syze);
        xmlstr += XMLBuildUtil.appendStringElement("bzssr",bzssr);
        xmlstr += XMLBuildUtil.appendStringElement("mssr",mssr);
        xmlstr += XMLBuildUtil.appendStringElement("yssre",yssre);
        xmlstr += XMLBuildUtil.appendStringElement("yssdl",yssdl);
        xmlstr += XMLBuildUtil.appendStringElement("ynssde",ynssde);
        xmlstr += XMLBuildUtil.appendStringElement("sl",sl);
        xmlstr += XMLBuildUtil.appendStringElement("ynsdse",ynsdse);
        xmlstr += XMLBuildUtil.appendStringElement("yyjsdse",yyjsdse);
        xmlstr += XMLBuildUtil.appendStringElement("ybsdse",ybsdse);
        
        xmlstr += XMLBuildUtil.appendStringElement("zczb",zczb);
        xmlstr += XMLBuildUtil.appendStringElement("zcze",zcze);        
        xmlstr += XMLBuildUtil.appendStringElement("zgrs",zgrs);
        xmlstr += XMLBuildUtil.appendStringElement("sshy",sshy); 
        xmlstr += XMLBuildUtil.appendStringElement("sshydm",sshydm); 
        
        xmlstr += XMLBuildUtil.appendStringElement("xwqyjmsdse",xwqyjmsdse);
        xmlstr += XMLBuildUtil.appendStringElement("swjghdynsdse",swjghdynsdse);
        
    	xmlstr += XMLBuildUtil.appendStringElement("sfxkh", sfxkh);
		xmlstr += XMLBuildUtil.appendStringElement("syndzsfsdm", syndZsfsdm);
		xmlstr += XMLBuildUtil.appendStringElement("syndzbh6", syndZbh6);
		xmlstr += XMLBuildUtil.appendStringElement("syndzbh25", syndZbh25);
		xmlstr += XMLBuildUtil.appendStringElement("syndfb5jyjg", syndFb5jyjg);
        return xmlstr;
    }
	public String getBzssr() {
		return bzssr;
	}
	public void setBzssr(String bzssr) {
		this.bzssr = bzssr;
	}
	public String getMssr() {
		return mssr;
	}
	public void setMssr(String mssr) {
		this.mssr = mssr;
	}
	public String getYssre() {
		return yssre;
	}
	public void setYssre(String yssre) {
		this.yssre = yssre;
	}
	public String getXwqyjmsdse() {
		return xwqyjmsdse;
	}
	public void setXwqyjmsdse(String xwqyjmsdse) {
		this.xwqyjmsdse = xwqyjmsdse;
	}
	public String getSwjghdynsdse() {
		return swjghdynsdse;
	}
	public void setSwjghdynsdse(String swjghdynsdse) {
		this.swjghdynsdse = swjghdynsdse;
	}
	public String getSfxkh() {
		return sfxkh;
	}
	public void setSfxkh(String sfxkh) {
		this.sfxkh = sfxkh;
	}
	public String getSyndZsfsdm() {
		return syndZsfsdm;
	}
	public void setSyndZsfsdm(String syndZsfsdm) {
		this.syndZsfsdm = syndZsfsdm;
	}
	public String getSyndZbh6() {
		return syndZbh6;
	}
	public void setSyndZbh6(String syndZbh6) {
		this.syndZbh6 = syndZbh6;
	}
	public String getSyndZbh25() {
		return syndZbh25;
	}
	public void setSyndZbh25(String syndZbh25) {
		this.syndZbh25 = syndZbh25;
	}
	public String getSyndFb5jyjg() {
		return syndFb5jyjg;
	}
	public void setSyndFb5jyjg(String syndFb5jyjg) {
		this.syndFb5jyjg = syndFb5jyjg;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}

   

}
