package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class FWChuzuAlterVO implements XMLVOInterface
{
	String jsjdm       ; //计算机代码
	String djbh        ; //登记编号
	String jcbh        ; //基础编号
	String id          ; //唯一编号
	String bglx        ; //变更类型
	String bgqfwzl     ; //变更前房屋坐落
	String bgqcqzsh    ; //变更前产权证书号
	String bgqnzjsr    ; //变更前年租金收入
	String bgqnynse    ; //变更前年应纳税额
	String bgqbz       ; //变更前备注
	String bghfwzl     ; //变更后房屋坐落
	String bghcqzsh    ; //变更后产权证书号
	String bghnzjsr    ; //变更后年租金收入
	String bghnynse    ; //变更后年应纳税额
	String bghbz       ; //变更后备注
	String fhbs        ; //复核标识
	String fhr         ; //复核人
	String fhrq        ; //复核日期
	String tbrq        ; //填表日期
	String swjgzzjgdm  ; //税务机关组织机构代码
	String qxdm        ; //区县代码
	String lrr         ; //录入人
	String lrrq        ; //录入日期
	String cjr         ; //创建人
	String cjrq        ; //创建日期             
	
	/*add by wofei 2009-2-6 */
	String bgqczfwyz   ; //变更前出租房屋原值
	String bghczfwyz   ; //变更后出租房屋原值
	String bgqxgrczbs  ; //变更前是否按市场价格向个人出租用于居住的住房
	String bghxgrczbs  ; //变更后是否按市场价格向个人出租用于居住的住房
	
	public String getBghczfwyz() {
		return Utils.formatNumber(bghczfwyz);
	}
	public void setBghczfwyz(String bghczfwyz) {
		this.bghczfwyz = bghczfwyz;
	}
	public String getBghxgrczbs() {
		return bghxgrczbs;
	}
	public void setBghxgrczbs(String bghxgrczbs) {
		this.bghxgrczbs = bghxgrczbs;
	}
	public String getBgqczfwyz() {
		return Utils.formatNumber(bgqczfwyz);
	}
	public void setBgqczfwyz(String bgqczfwyz) {
		this.bgqczfwyz = bgqczfwyz;
	}
	public String getBgqxgrczbs() {
		return bgqxgrczbs;
	}
	public void setBgqxgrczbs(String bgqxgrczbs) {
		this.bgqxgrczbs = bgqxgrczbs;
	}
	public FWChuzuAlterVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<fwChuzuList>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</fwChuzuList>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";

        xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
        xmlstr += XMLBuildUtil.appendStringElement("jcbh",jcbh);
        xmlstr += XMLBuildUtil.appendStringElement("id",id);
        xmlstr += XMLBuildUtil.appendStringElement("bglx",bglx);
        xmlstr += XMLBuildUtil.appendStringElement("bgqfwzl",bgqfwzl);
        xmlstr += XMLBuildUtil.appendStringElement("bgqcqzsh",bgqcqzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bgqczfwyz",getBgqczfwyz());
        xmlstr += XMLBuildUtil.appendStringElement("bgqnzjsr",getBgqnzjsr());
        xmlstr += XMLBuildUtil.appendStringElement("bgqnynse",getBgqnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqxgrczbs",bgqxgrczbs);
        xmlstr += XMLBuildUtil.appendStringElement("bgqbz",bgqbz);
        xmlstr += XMLBuildUtil.appendStringElement("bghfwzl",bghfwzl);
        xmlstr += XMLBuildUtil.appendStringElement("bghcqzsh",bghcqzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bghczfwyz",getBghczfwyz());
        xmlstr += XMLBuildUtil.appendStringElement("bghnzjsr",getBghnzjsr());
        xmlstr += XMLBuildUtil.appendStringElement("bghnynse",getBghnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bghxgrczbs",bghxgrczbs);
        xmlstr += XMLBuildUtil.appendStringElement("bghbz",bghbz);
        xmlstr += XMLBuildUtil.appendStringElement("fhbs",fhbs);
        xmlstr += XMLBuildUtil.appendStringElement("fhr",fhr);
        xmlstr += XMLBuildUtil.appendStringElement("fhrq",fhrq);
        xmlstr += XMLBuildUtil.appendStringElement("tbrq",tbrq);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm",swjgzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("qxdm",qxdm);
        xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
        xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);
        xmlstr += XMLBuildUtil.appendStringElement("cjr",cjr);
        xmlstr += XMLBuildUtil.appendStringElement("cjrq",cjrq);


		
        return xmlstr;
    }
	public String getBghbz() {
		return bghbz;
	}
	public void setBghbz(String bghbz) {
		this.bghbz = bghbz;
	}
	public String getBghcqzsh() {
		return bghcqzsh;
	}
	public void setBghcqzsh(String bghcqzsh) {
		this.bghcqzsh = bghcqzsh;
	}
	public String getBghfwzl() {
		return bghfwzl;
	}
	public void setBghfwzl(String bghfwzl) {
		this.bghfwzl = bghfwzl;
	}
	public String getBghnynse() {
		return Utils.formatNumber(bghnynse);
	}
	public void setBghnynse(String bghnynse) {
		this.bghnynse = bghnynse;
	}
	public String getBghnzjsr() {
		return Utils.formatNumber(bghnzjsr);
	}
	public void setBghnzjsr(String bghnzjsr) {
		this.bghnzjsr = bghnzjsr;
	}
	public String getBglx() {
		return bglx;
	}
	public void setBglx(String bglx) {
		this.bglx = bglx;
	}
	public String getBgqbz() {
		return bgqbz;
	}
	public void setBgqbz(String bgqbz) {
		this.bgqbz = bgqbz;
	}
	public String getBgqcqzsh() {
		return bgqcqzsh;
	}
	public void setBgqcqzsh(String bgqcqzsh) {
		this.bgqcqzsh = bgqcqzsh;
	}
	public String getBgqfwzl() {
		return bgqfwzl;
	}
	public void setBgqfwzl(String bgqfwzl) {
		this.bgqfwzl = bgqfwzl;
	}
	public String getBgqnynse() {
		return Utils.formatNumber(bgqnynse);
	}
	public void setBgqnynse(String bgqnynse) {
		this.bgqnynse = bgqnynse;
	}
	public String getBgqnzjsr() {
		return Utils.formatNumber(bgqnzjsr);
	}
	public void setBgqnzjsr(String bgqnzjsr) {
		this.bgqnzjsr = bgqnzjsr;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getDjbh() {
		return djbh;
	}
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}
	public String getFhbs() {
		return fhbs;
	}
	public void setFhbs(String fhbs) {
		this.fhbs = fhbs;
	}
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	public String getFhrq() {
		return fhrq;
	}
	public void setFhrq(String fhrq) {
		this.fhrq = fhrq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJcbh() {
		return jcbh;
	}
	public void setJcbh(String jcbh) {
		this.jcbh = jcbh;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getQxdm() {
		return qxdm;
	}
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getTbrq() {
		return tbrq;
	}
	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

    
}
