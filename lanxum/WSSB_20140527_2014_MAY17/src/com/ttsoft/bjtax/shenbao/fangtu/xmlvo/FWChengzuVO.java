package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class FWChengzuVO implements XMLVOInterface
{
	String djbh; //�ǼǱ��
	String id; //Ψһ���
	
	String czrmc      ;//����������                 
	String zjlxdm     ;//֤�����ʹ���               
	String czrzjhm    ;//������֤������             
	String czfwzl     ;//���ⷿ������               
	String zlqxdm     ;//�������ش���               
	String nzj        ;//�����                     
	String bz         ;//��ע  
	
	String opFlag; //������ʶ
	
	
	
	
    public FWChengzuVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "";
        xmlstr = "<fwChengzuList>";
        xmlstr += toXMLChilds();
        xmlstr += "</fwChengzuList>";
        return xmlstr;
    }
    public String toXMLChilds()
    {
        String xmlstr = "";
		xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
		xmlstr += XMLBuildUtil.appendStringElement("id",id);
		xmlstr += XMLBuildUtil.appendStringElement("czrmc",czrmc);
		xmlstr += XMLBuildUtil.appendStringElement("zjlxdm",zjlxdm);
		xmlstr += XMLBuildUtil.appendStringElement("czrzjhm",czrzjhm);
		xmlstr += XMLBuildUtil.appendStringElement("czfwzl",czfwzl);
		xmlstr += XMLBuildUtil.appendStringElement("zlqxdm",zlqxdm);
		xmlstr += XMLBuildUtil.appendStringElement("nzj",getNzj());
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
	public String getCzfwzl() {
		return czfwzl;
	}
	public void setCzfwzl(String czfwzl) {
		this.czfwzl = czfwzl;
	}
	public String getCzrmc() {
		return czrmc;
	}
	public void setCzrmc(String czrmc) {
		this.czrmc = czrmc;
	}
	public String getCzrzjhm() {
		return czrzjhm;
	}
	public void setCzrzjhm(String czrzjhm) {
		this.czrzjhm = czrzjhm;
	}
	public String getDjbh() {
		return djbh;
	}
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNzj() {
		return Utils.formatNumber(nzj);
	}
	public void setNzj(String nzj) {
		this.nzj = nzj;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public String getZjlxdm() {
		return zjlxdm;
	}
	public void setZjlxdm(String zjlxdm) {
		this.zjlxdm = zjlxdm;
	}
	public String getZlqxdm() {
		return zlqxdm;
	}
	public void setZlqxdm(String zlqxdm) {
		this.zlqxdm = zlqxdm;
	}

}
