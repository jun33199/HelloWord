package com.ttsoft.bjtax.shenbao.fangtu.xmlvo.alter;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class FWZiyongAlterVO implements XMLVOInterface
{
	String jsjdm; //���������
	String djbh; //�ǼǱ��
	String jcbh; //�������
	String id; //Ψһ���
	String bglx; //�������
	String ysfcyzbgyy; //Ӧ˰����ԭֵ���ԭ��
	String jmsyzbgyy; //����˰ԭֵ���ԭ��
	String jmzcdm; //�������ߴ���
	String jmsqxq; //����˰������
	String jmsqxz; //����˰����ֹ
	String bgqfwzl; //���ǰ��������
	String bgqcqzsh; //���ǰ��Ȩ֤���
	String bgqfcyz; //���ǰ����ԭֵ
	String bgqswjggz; //���ǰ˰����ع�ֵstring 
	String bgqqzmsyz; //���ǰ������˰ԭֵ
	String bgqqzysyz; //���ǰ����Ӧ˰ԭֵ
	String bgqnynse; //���ǰ��Ӧ��˰��
	String bgqsfdj; //���ǰ�Ƿ����
	String bgqbz; //���ǰ��ע
	String bghfwzl; //�����������
	String bghcqzsh; //������Ȩ֤���
	String bghfcyz; //����󷿲�ԭֵ
	String bghswjggz; //�����˰����ع�ֵ
	String bghqzmsyz; //�����������˰ԭֵ
	String bghqzysyz; //���������Ӧ˰ԭֵ
	String bghnynse; //�������Ӧ��˰��
	String bghsfdj; //������Ƿ����
	String bghbz; //�����ע
	String fhbs; //���˱�ʶ
	String fhr; //������
	String fhrq; //��������
	String tbrq; //�������
	String swjgzzjgdm; //˰�������֯��������
	String qxdm; //���ش���
	String lrr; //¼����
	String lrrq; //¼������
	String cjr; //������
	String cjrq; //��������
	
	
	
	
    public FWZiyongAlterVO()
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
        xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
        xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
        xmlstr += XMLBuildUtil.appendStringElement("jcbh",jcbh);
        xmlstr += XMLBuildUtil.appendStringElement("id",id);
        xmlstr += XMLBuildUtil.appendStringElement("bglx",bglx);
        xmlstr += XMLBuildUtil.appendStringElement("ysfcyzbgyy",ysfcyzbgyy);
        xmlstr += XMLBuildUtil.appendStringElement("jmsyzbgyy",jmsyzbgyy);
        xmlstr += XMLBuildUtil.appendStringElement("jmzcdm",jmzcdm);
        xmlstr += XMLBuildUtil.appendStringElement("jmsqxq",jmsqxq);
        xmlstr += XMLBuildUtil.appendStringElement("jmsqxz",jmsqxz);
        xmlstr += XMLBuildUtil.appendStringElement("bgqfwzl",bgqfwzl);
        xmlstr += XMLBuildUtil.appendStringElement("bgqcqzsh",bgqcqzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bgqfcyz",getBgqfcyz());
        xmlstr += XMLBuildUtil.appendStringElement("bgqswjggz",getBgqswjggz());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzmsyz",getBgqqzmsyz());
        xmlstr += XMLBuildUtil.appendStringElement("bgqqzysyz",getBgqqzysyz());
        xmlstr += XMLBuildUtil.appendStringElement("bgqnynse",getBgqnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bgqsfdj",bgqsfdj);
        xmlstr += XMLBuildUtil.appendStringElement("bgqbz",bgqbz);
        xmlstr += XMLBuildUtil.appendStringElement("bghfwzl",bghfwzl);
        xmlstr += XMLBuildUtil.appendStringElement("bghcqzsh",bghcqzsh);
        xmlstr += XMLBuildUtil.appendStringElement("bghfcyz",getBghfcyz());
        xmlstr += XMLBuildUtil.appendStringElement("bghswjggz",getBghswjggz());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzmsyz",getBghqzmsyz());
        xmlstr += XMLBuildUtil.appendStringElement("bghqzysyz",getBghqzysyz());
        xmlstr += XMLBuildUtil.appendStringElement("bghnynse",getBghnynse());
        xmlstr += XMLBuildUtil.appendStringElement("bghsfdj",bghsfdj);
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
	public String getBghfcyz() {
		return Utils.formatNumber(bghfcyz);
	}
	public void setBghfcyz(String bghfcyz) {
		this.bghfcyz = bghfcyz;
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
	public String getBghqzmsyz() {
		return Utils.formatNumber(bghqzmsyz);
	}
	public void setBghqzmsyz(String bghqzmsyz) {
		this.bghqzmsyz = bghqzmsyz;
	}
	public String getBghqzysyz() {
		return Utils.formatNumber(bghqzysyz);
	}
	public void setBghqzysyz(String bghqzysyz) {
		this.bghqzysyz = bghqzysyz;
	}
	public String getBghsfdj() {
		return bghsfdj;
	}
	public void setBghsfdj(String bghsfdj) {
		this.bghsfdj = bghsfdj;
	}
	public String getBghswjggz() {
		return Utils.formatNumber(bghswjggz);
	}
	public void setBghswjggz(String bghswjggz) {
		this.bghswjggz = bghswjggz;
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
	public String getBgqfcyz() {
		return Utils.formatNumber(bgqfcyz);
	}
	public void setBgqfcyz(String bgqfcyz) {
		this.bgqfcyz = bgqfcyz;
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
	public String getBgqqzmsyz() {
		return Utils.formatNumber(bgqqzmsyz);
	}
	public void setBgqqzmsyz(String bgqqzmsyz) {
		this.bgqqzmsyz = bgqqzmsyz;
	}
	public String getBgqqzysyz() {
		return Utils.formatNumber(bgqqzysyz);
	}
	public void setBgqqzysyz(String bgqqzysyz) {
		this.bgqqzysyz = bgqqzysyz;
	}
	public String getBgqsfdj() {
		return bgqsfdj;
	}
	public void setBgqsfdj(String bgqsfdj) {
		this.bgqsfdj = bgqsfdj;
	}
	public String getBgqswjggz() {
		return Utils.formatNumber(bgqswjggz);
	}
	public void setBgqswjggz(String bgqswjggz) {
		this.bgqswjggz = bgqswjggz;
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
	public String getYsfcyzbgyy() {
		return ysfcyzbgyy;
	}
	public void setYsfcyzbgyy(String ysfcyzbgyy) {
		this.ysfcyzbgyy = ysfcyzbgyy;
	}
    
    
}
