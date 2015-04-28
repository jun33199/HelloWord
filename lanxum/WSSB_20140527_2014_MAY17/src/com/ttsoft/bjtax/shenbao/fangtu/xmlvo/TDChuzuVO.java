package com.ttsoft.bjtax.shenbao.fangtu.xmlvo;

import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;


public class TDChuzuVO implements XMLVOInterface
{
	String djbh; //�ǼǱ��
	String id; //Ψһ���
	

	String tdzl  ;//��������
	String tdzsh ;//����֤���
	String tdmj  ;//�������
	String qzmsmj;//������˰���
	String qzysmj;//����Ӧ˰���
	String mpfmse;//ÿƽ����˰��
	String nynse ;//��Ӧ��˰��
	String bz    ;//��ע       
	
	String opFlag; //������ʶ
	
	
	
	
    public TDChuzuVO()
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
		xmlstr += XMLBuildUtil.appendStringElement("djbh",djbh);
		xmlstr += XMLBuildUtil.appendStringElement("id",id);
		
		xmlstr += XMLBuildUtil.appendStringElement("tdzl",tdzl);
		xmlstr += XMLBuildUtil.appendStringElement("tdzsh",tdzsh);
		xmlstr += XMLBuildUtil.appendStringElement("tdmj",getTdmj());
		xmlstr += XMLBuildUtil.appendStringElement("qzmsmj",getQzmsmj());
		xmlstr += XMLBuildUtil.appendStringElement("qzysmj",getQzysmj());
		xmlstr += XMLBuildUtil.appendStringElement("mpfmse",getMpfmse());
		xmlstr += XMLBuildUtil.appendStringElement("nynse",getNynse());
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
	public String getMpfmse() {
		return Utils.formatNumber(mpfmse);
	}
	public void setMpfmse(String mpfmse) {
		this.mpfmse = mpfmse;
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
	public String getQzmsmj() {
		return Utils.formatNumber(qzmsmj);
	}
	public void setQzmsmj(String qzmsmj) {
		this.qzmsmj = qzmsmj;
	}
	public String getQzysmj() {
		return Utils.formatNumber(qzysmj);
	}
	public void setQzysmj(String qzysmj) {
		this.qzysmj = qzysmj;
	}
	public String getTdmj() {
		return Utils.formatNumber(tdmj);
	}
	public void setTdmj(String tdmj) {
		this.tdmj = tdmj;
	}
	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	public String getTdzsh() {
		return tdzsh;
	}
	public void setTdzsh(String tdzsh) {
		this.tdzsh = tdzsh;
	}
	
    

}
