package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class FWChuzuVO implements XMLVOInterface
{
	String djbh; //�ǼǱ��
	String id; //Ψһ���
	
	String fwzl; //��������
	String cqzsh; //��Ȩ֤���
	String czfwyz;//���ⷿ��ԭֵ add by wofei 2009-2-6
	String xgrczbs;//�Ƿ��г��۸�����˳������ھ�ס��ס��
	String nzjsr; //���������
	String nynse; //��Ӧ��˰�� double
	String bz; //��ע
	
	String opFlag; //������ʶ
	
	
	
	
    public FWChuzuVO()
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
		xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
		xmlstr += XMLBuildUtil.appendStringElement("id",id);
		xmlstr += XMLBuildUtil.appendStringElement("fwzl",fwzl);
		xmlstr += XMLBuildUtil.appendStringElement("cqzsh",cqzsh);
		xmlstr += XMLBuildUtil.appendStringElement("czfwyz",getCzfwyz());
		xmlstr += XMLBuildUtil.appendStringElement("nzjsr",getNzjsr());
		xmlstr += XMLBuildUtil.appendStringElement("nynse",getNynse());
		xmlstr += XMLBuildUtil.appendStringElement("xgrczbs",xgrczbs);
		xmlstr += XMLBuildUtil.appendStringElement("bz",bz);
		xmlstr += XMLBuildUtil.appendStringElement("opFlag",opFlag);
		
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
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public String getNzjsr() {
		return Utils.formatNumber(nzjsr);
	}
	public void setNzjsr(String nzjsr) {
		this.nzjsr = nzjsr;
	}
	public String getCzfwyz() {
		return Utils.formatNumber(czfwyz);
	}
	public void setCzfwyz(String czfwyz) {
		this.czfwyz = czfwyz;
	}
	public String getXgrczbs() {
		return xgrczbs;
	}
	public void setXgrczbs(String xgrczbs) {
		this.xgrczbs = xgrczbs;
	}

}
