package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class TDZiyongAlterVO implements XMLVOInterface
{
	String jsjdm         ; //计算机代码          
	String djbh          ; //登记编号            
	String jcbh          ; //基础编号            
	String id            ; //唯一编号            
	String bglx          ; //变更类型            
	String ysmjbgyy      ; //应税面积变更的原因  
	String jmsyzbgyy     ; //减免税面积变更原因  
	String jmzcdm        ; //减免政策代码        
	String jmsqxq        ; //减免税期限起        
	String jmsqxz        ; //减免税期限止        
	String bgqtdzl       ; //变更前土地坐落      
	String bgqtdzsh      ; //变更前土地证书号
	String bgqtdmj       ; //变更前土地面积      
	String bgqqzmsmj     ; //变更前其中免税面积  
	String bgqqzysmj     ; //变更前其中应税面积  
	String bgqmpfmse     ; //变更前每平方米税额  
	String bgqnynse      ; //变更前年应纳税额
	String bgqsfdj       ; //变更前是否代缴  
	String bgqsfjnws     ; //是否缴纳外商投资企业土地使用费
	String bgqbz         ; //变更前备注          
	String bghtdzl       ; //变更后土地坐落      
	String bghtdzsh      ; //变更后土地证书号
	String bghtdmj       ; //变更后土地面积      
	String bghqzmsmj     ; //变更后其中免税面积  
	String bghqzysmj     ; //变更后其中应税面积  
	String bghmpfmse     ; //变更后每平方米税额  
	String bghnynse      ; //变更后年应纳税额 
	String bghsfdj       ; //变更后是否代缴      
	String bghsfjnws     ; //是否缴纳外商投资企业土地使用费
	String bghbz         ; //变更后备注          
	String fhbs          ; //复核标识            
	String fhr           ; //复核人              
	String fhrq          ; //复核日期            
	String tbrq          ; //填表日期            
	String swjgzzjgdm    ; //税务机关组织机构代码
	String qxdm          ; //区县代码            
	String lrr           ; //录入人              
	String lrrq          ; //录入日期            
	String cjr           ; //创建人              
	String cjrq          ; //创建日期            
	
	
    public TDZiyongAlterVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<tdZiyongList>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</tdZiyongList>";
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
        xmlstr += XMLBuildUtil.appendStringElement("ysmjbgyy",ysmjbgyy);
        xmlstr += XMLBuildUtil.appendStringElement("jmsyzbgyy",jmsyzbgyy);
        xmlstr += XMLBuildUtil.appendStringElement("jmzcdm",jmzcdm);
        xmlstr += XMLBuildUtil.appendStringElement("jmsqxq",jmsqxq);
        xmlstr += XMLBuildUtil.appendStringElement("jmsqxz",jmsqxz);
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdzl",bgqtdzl);
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdzsh",bgqtdzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdmj",getBgqtdmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzmsmj",getBgqqzmsmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzysmj",getBgqqzysmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqmpfmse",getBgqmpfmse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqnynse",getBgqnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqsfdj",bgqsfdj);
        xmlstr += XMLBuildUtil.appendStringElement("bgqsfjnws",bgqsfjnws);
        xmlstr += XMLBuildUtil.appendStringElement("bgqbz",bgqbz);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdzl",bghtdzl);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdzsh",bghtdzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdmj",getBghtdmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzmsmj",getBghqzmsmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzysmj",getBghqzysmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghmpfmse",getBghmpfmse());
        xmlstr += XMLBuildUtil.appendStringElement("bghnynse",getBghnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bghsfdj",bghsfdj);
        xmlstr += XMLBuildUtil.appendStringElement("bghsfjnws",bghsfjnws);
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
	public String getBghmpfmse() {
		return Utils.formatNumber(bghmpfmse);
	}
	public void setBghmpfmse(String bghmpfmse) {
		this.bghmpfmse = bghmpfmse;
	}
	public String getBghnynse() {
		return Utils.formatNumber(bghnynse);
	}
	public void setBghnynse(String bghnynse) {
		this.bghnynse = bghnynse;
	}
	public String getBghqzmsmj() {
		return Utils.formatNumber(bghqzmsmj);
	}
	public void setBghqzmsmj(String bghqzmsmj) {
		this.bghqzmsmj = bghqzmsmj;
	}
	public String getBghqzysmj() {
		return Utils.formatNumber(bghqzysmj);
	}
	public void setBghqzysmj(String bghqzysmj) {
		this.bghqzysmj = bghqzysmj;
	}
	public String getBghsfdj() {
		return bghsfdj;
	}
	public void setBghsfdj(String bghsfdj) {
		this.bghsfdj = bghsfdj;
	}
	public String getBghtdmj() {
		return Utils.formatNumber(bghtdmj);
	}
	public void setBghtdmj(String bghtdmj) {
		this.bghtdmj = bghtdmj;
	}
	public String getBghtdzl() {
		return bghtdzl;
	}
	public void setBghtdzl(String bghtdzl) {
		this.bghtdzl = bghtdzl;
	}
	public String getBghtdzsh() {
		return bghtdzsh;
	}
	public void setBghtdzsh(String bghtdzsh) {
		this.bghtdzsh = bghtdzsh;
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
	public String getBgqmpfmse() {
		return Utils.formatNumber(bgqmpfmse);
	}
	public void setBgqmpfmse(String bgqmpfmse) {
		this.bgqmpfmse = bgqmpfmse;
	}
	public String getBgqnynse() {
		return Utils.formatNumber(bgqnynse);
	}
	public void setBgqnynse(String bgqnynse) {
		this.bgqnynse = bgqnynse;
	}
	public String getBgqqzmsmj() {
		return Utils.formatNumber(bgqqzmsmj);
	}
	public void setBgqqzmsmj(String bgqqzmsmj) {
		this.bgqqzmsmj = bgqqzmsmj;
	}
	public String getBgqqzysmj() {
		return Utils.formatNumber(bgqqzysmj);
	}
	public void setBgqqzysmj(String bgqqzysmj) {
		this.bgqqzysmj = bgqqzysmj;
	}
	public String getBgqsfdj() {
		return bgqsfdj;
	}
	public void setBgqsfdj(String bgqsfdj) {
		this.bgqsfdj = bgqsfdj;
	}
	public String getBgqtdmj() {
		return Utils.formatNumber(bgqtdmj);
	}
	public void setBgqtdmj(String bgqtdmj) {
		this.bgqtdmj = bgqtdmj;
	}
	public String getBgqtdzl() {
		return bgqtdzl;
	}
	public void setBgqtdzl(String bgqtdzl) {
		this.bgqtdzl = bgqtdzl;
	}
	public String getBgqtdzsh() {
		return bgqtdzsh;
	}
	public void setBgqtdzsh(String bgqtdzsh) {
		this.bgqtdzsh = bgqtdzsh;
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
	public String getJmsqxq() {
		return jmsqxq;
	}
	public void setJmsqxq(String jmsqxq) {
		this.jmsqxq = jmsqxq;
	}
	public String getJmsqxz() {
		return jmsqxz;
	}
	public void setJmsqxz(String jmsqxz) {
		this.jmsqxz = jmsqxz;
	}
	public String getJmsyzbgyy() {
		return jmsyzbgyy;
	}
	public void setJmsyzbgyy(String jmsyzbgyy) {
		this.jmsyzbgyy = jmsyzbgyy;
	}
	public String getJmzcdm() {
		return jmzcdm;
	}
	public void setJmzcdm(String jmzcdm) {
		this.jmzcdm = jmzcdm;
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
	public String getYsmjbgyy() {
		return ysmjbgyy;
	}
	public void setYsmjbgyy(String ysmjbgyy) {
		this.ysmjbgyy = ysmjbgyy;
	}
	public String getBghsfjnws() {
		return bghsfjnws;
	}
	public void setBghsfjnws(String bghsfjnws) {
		this.bghsfjnws = bghsfjnws;
	}
	public String getBgqsfjnws() {
		return bgqsfjnws;
	}
	public void setBgqsfjnws(String bgqsfjnws) {
		this.bgqsfjnws = bgqsfjnws;
	}
    
    
    
}
