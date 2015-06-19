package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;

public class FWChengzuAlterVO implements XMLVOInterface {

	String jsjdm       ; //���������
	String djbh        ; //�ǼǱ��
	String jcbh        ; //�������
	String id          ; //Ψһ���
	String bglx        ; //�������
	String bgqczrmc    ; //���ǰ����������
	String bgqzjlxdm   ; //���ǰ֤�����ʹ���
	String bgqczrzjhm  ; //���ǰ������֤������
	String bgqczfwzl   ; //���ǰ���ⷿ������
	String bgqzlqxdm   ; //���ǰ�������ش���
	String bgqnzj      ; //���ǰ�����
	String bgqbz       ; //���ǰ��ע
	String bghczrmc    ; //��������������
	String bghzjlxdm   ; //�����֤�����ʹ���
	String bghczrzjhm  ; //����������֤������
	String bghczfwzl   ; //�������ⷿ������
	String bghzlqxdm   ; //������������ش���
	String bghnzj      ; //����������
	String bghbz       ; //�����ע
	String fhbs        ; //���˱�ʶ
	String fhr         ; //������
	String fhrq        ; //��������
	String tbrq        ; //�������
	String swjgzzjgdm  ; //˰�������֯��������
	String qxdm        ; //���ش���
	String lrr         ; //¼����
	String lrrq        ; //¼������
	String cjr         ; //������
	String cjrq        ; //��������         
	
	
	
	public FWChengzuAlterVO() {
		super();
	}
	public Map getListTypeMap() {
		return null;
	}
	public String toXMLChilds() {
		String xmlstr = "";

		xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
		xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
		xmlstr += XMLBuildUtil.appendStringElement("jcbh",jcbh);
		xmlstr += XMLBuildUtil.appendStringElement("id",id);
		xmlstr += XMLBuildUtil.appendStringElement("bglx",bglx);
		xmlstr += XMLBuildUtil.appendStringElement("bgqczrmc",bgqczrmc);
		xmlstr += XMLBuildUtil.appendStringElement("bgqzjlxdm",bgqzjlxdm);
		xmlstr += XMLBuildUtil.appendStringElement("bgqczrzjhm",bgqczrzjhm);
		xmlstr += XMLBuildUtil.appendStringElement("bgqczfwzl",bgqczfwzl);
		xmlstr += XMLBuildUtil.appendStringElement("bgqzlqxdm",bgqzlqxdm);
		xmlstr += XMLBuildUtil.appendStringElement("bgqnzj",getBgqnzj());
		xmlstr += XMLBuildUtil.appendStringElement("bgqbz",bgqbz);
		xmlstr += XMLBuildUtil.appendStringElement("bghczrmc",bghczrmc);
		xmlstr += XMLBuildUtil.appendStringElement("bghzjlxdm",bghzjlxdm);
		xmlstr += XMLBuildUtil.appendStringElement("bghczrzjhm",bghczrzjhm);
		xmlstr += XMLBuildUtil.appendStringElement("bghczfwzl",bghczfwzl);
		xmlstr += XMLBuildUtil.appendStringElement("bghzlqxdm",bghzlqxdm);
		xmlstr += XMLBuildUtil.appendStringElement("bghnzj",getBghnzj());
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
	public String toXML() {
		String xmlstr = "<fwChengzuList>";
        xmlstr += toXMLChilds();
        xmlstr += "</fwChengzuList>";
        return xmlstr;
	}
	public String getBghbz() {
		return bghbz;
	}
	public void setBghbz(String bghbz) {
		this.bghbz = bghbz;
	}
	public String getBghczfwzl() {
		return bghczfwzl;
	}
	public void setBghczfwzl(String bghczfwzl) {
		this.bghczfwzl = bghczfwzl;
	}
	public String getBghczrmc() {
		return bghczrmc;
	}
	public void setBghczrmc(String bghczrmc) {
		this.bghczrmc = bghczrmc;
	}
	public String getBghczrzjhm() {
		return bghczrzjhm;
	}
	public void setBghczrzjhm(String bghczrzjhm) {
		this.bghczrzjhm = bghczrzjhm;
	}
	public String getBghnzj() {
		return Utils.formatNumber(bghnzj);
	}
	public void setBghnzj(String bghnzj) {
		this.bghnzj = bghnzj;
	}
	public String getBghzjlxdm() {
		return bghzjlxdm;
	}
	public void setBghzjlxdm(String bghzjlxdm) {
		this.bghzjlxdm = bghzjlxdm;
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
	public String getBgqczfwzl() {
		return bgqczfwzl;
	}
	public void setBgqczfwzl(String bgqczfwzl) {
		this.bgqczfwzl = bgqczfwzl;
	}
	public String getBgqczrmc() {
		return bgqczrmc;
	}
	public void setBgqczrmc(String bgqczrmc) {
		this.bgqczrmc = bgqczrmc;
	}
	public String getBgqczrzjhm() {
		return bgqczrzjhm;
	}
	public void setBgqczrzjhm(String bgqczrzjhm) {
		this.bgqczrzjhm = bgqczrzjhm;
	}
	public String getBgqnzj() {
		return Utils.formatNumber(bgqnzj);
	}
	public void setBgqnzj(String bgqnzj) {
		this.bgqnzj = bgqnzj;
	}
	public String getBgqzjlxdm() {
		return bgqzjlxdm;
	}
	public void setBgqzjlxdm(String bgqzjlxdm) {
		this.bgqzjlxdm = bgqzjlxdm;
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
	public String getBghzlqxdm() {
		return bghzlqxdm;
	}
	public void setBghzlqxdm(String bghzlqxdm) {
		this.bghzlqxdm = bghzlqxdm;
	}
	public String getBgqzlqxdm() {
		return bgqzlqxdm;
	}
	public void setBgqzlqxdm(String bgqzlqxdm) {
		this.bgqzlqxdm = bgqzlqxdm;
	}
	
	

}
