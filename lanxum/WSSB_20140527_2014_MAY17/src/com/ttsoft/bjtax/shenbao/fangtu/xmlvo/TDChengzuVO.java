package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class TDChengzuVO implements XMLVOInterface
{
	String djbh; //�ǼǱ��
	String id; //Ψһ���

    
	String czrmc      ;//����������                 
	String zjlxdm     ;//֤�����ʹ���               
	String czrzjhm    ;//������֤������             
	String cztdzl     ;//������������   
	String zlqxdm     ;//�������ش���
	String tdmj       ;//�������     
	String sfjnws     ;//�Ƿ��������Ͷ����ҵ����ʹ�÷�
	String bz         ;//��ע
	
	String opFlag; //������ʶ
	
    public TDChengzuVO()
    {
        super();
    }
    public Map getListTypeMap()
    {
        return null;
    }
    public String toXML()
    {
        String xmlstr = "<tdChengzuList>";
        
        xmlstr += toXMLChilds();
        xmlstr += "</tdChengzuList>";
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
		xmlstr += XMLBuildUtil.appendStringElement("cztdzl",cztdzl);
		xmlstr += XMLBuildUtil.appendStringElement("zlqxdm",zlqxdm);
		xmlstr += XMLBuildUtil.appendStringElement("tdmj",getTdmj());
		xmlstr += XMLBuildUtil.appendStringElement("sfjnws",sfjnws);
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
	public String getCztdzl() {
		return cztdzl;
	}
	public void setCztdzl(String cztdzl) {
		this.cztdzl = cztdzl;
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
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public String getTdmj() {
		return Utils.formatNumber(tdmj);
	}
	public void setTdmj(String tdmj) {
		this.tdmj = tdmj;
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
	public String getSfjnws() {
		return sfjnws;
	}
	public void setSfjnws(String sfjnws) {
		this.sfjnws = sfjnws;
	}

}
