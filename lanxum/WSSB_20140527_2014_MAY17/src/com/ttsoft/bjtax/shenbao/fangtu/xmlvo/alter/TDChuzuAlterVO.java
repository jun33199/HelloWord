package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class TDChuzuAlterVO implements XMLVOInterface
{
	String jsjdm      ; //���������          
	String djbh       ; //�ǼǱ��            
	String jcbh       ; //�������            
	String id         ; //Ψһ���            
	String bglx       ; //�������            
	String bgqtdzl    ; //���ǰ��������      
	String bgqtdzsh   ; //���ǰ����֤���    
	String bgqtdmj    ; //���ǰ�������      
	String bgqqzmsmj  ; //���ǰ������˰���  
	String bgqqzysmj  ; //���ǰ����Ӧ˰���  
	String bgqmpfmse  ; //���ǰÿƽ����˰��  
	String bgqnynse   ; //���ǰ��Ӧ��˰��    
	String bgqbz      ; //���ǰ��ע          
	String bghtdzl    ; //�������������      
	String bghtdzsh   ; //���������֤���    
	String bghtdmj    ; //������������      
	String bghqzmsmj  ; //�����������˰���  
	String bghqzysmj  ; //���������Ӧ˰���  
	String bghmpfmse  ; //�����ÿƽ����˰��  
	String bghnynse   ; //�������Ӧ��˰��    
	String bghbz      ; //�����ע          
	String fhbs       ; //���˱�ʶ            
	String fhr        ; //������              
	String fhrq       ; //��������            
	String tbrq       ; //�������            
	String swjgzzjgdm ; //˰�������֯��������
	String qxdm       ; //���ش���            
	String lrr        ; //¼����              
	String lrrq       ; //¼������            
	String cjr        ; //������              
	String cjrq       ; //��������            


    public TDChuzuAlterVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<tdChuzuList>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</tdChuzuList>";
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
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdzl",bgqtdzl);
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdzsh",bgqtdzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bgqtdmj",getBgqtdmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzmsmj",getBgqqzmsmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzysmj",getBgqqzysmj());
        xmlstr += XMLBuildUtil.appendStringElement("bgqmpfmse",getBgqmpfmse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqnynse",getBgqnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqbz",bgqbz);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdzl",bghtdzl);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdzsh",bghtdzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bghtdmj",getBghtdmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzmsmj",getBghqzmsmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzysmj",getBghqzysmj());
        xmlstr += XMLBuildUtil.appendStringElement("bghmpfmse",getBghmpfmse());
        xmlstr += XMLBuildUtil.appendStringElement("bghnynse",getBghnynse());
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
