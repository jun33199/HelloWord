package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: ��ʮ�ˣ��⹺��������۾ɻ�̯������ VO</p>
 *
 * <p>Copyright: ��һ����</p>
 *
 * <p>Company: ��һ����</p>
 *
 * @author �׾�
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba_18VO implements JmbamxVoInterface {

	// ���
	private String xh = "";

	// �������������
	private String basqwsh = "";

	// ���������
	private String jsjdm = "";

	// �������
	private String band = "";
//	 ˰�������֯���ش���
	private String swjgzzjgdm = "";

	// ��ҵ���������ƾ֤����
	private String gjrjpzmc = "";

	// ����ȷ��������0���ǣ�1����
	private String sffhqrtj = "";

	// �������������ɣ�0���ǣ�1����
	private String sfysdnxly = "";

	// ����������֤����0���ǣ�1����
	private String sfysdnxzm = "";

	// ����������˵����0���ǣ�1����
	private String sfyxgqksm = "";

	// �⹺��������۾ɻ�̯��������
	private String sdtxnx = "";

	// �����۾���ʼ���
	private String jtzjqsnd = "";

	// �����۾���ֹ���
	private String jtzjzznd = "";

	// ÿ��ɿ۳����۾ɶ�
	private String kkczje = "";

	// �̶��ʲ�(�����ʲ�)ԭֵ
	private String zcyz = "";

	// �Ѽ����۾ɵ�����
	private String yjtzjnx = "";

	// �Ѽ�����۾ɶ�
	private String yjtzje = "";

	// ��˱�ǣ�0��ͨ����1����ͨ��
	private String shbj = "";

	// ������
	private String cjr = "";

	// ��������
	private String cjrq = "";

	// ¼����
	private String lrr = "";

	// ¼������
	private String lrrq = "";

	public Jmba_18VO(){
		
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("basqwsh",basqwsh);
		xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
		xmlstr += XMLBuildUtil.appendStringElement("band", band);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("gjrjpzmc",gjrjpzmc);
		xmlstr += XMLBuildUtil.appendStringElement("sffhqrtj",sffhqrtj);
		xmlstr += XMLBuildUtil.appendStringElement("sfysdnxly",sfysdnxly);
		xmlstr += XMLBuildUtil.appendStringElement("sfysdnxzm",sfysdnxzm);
		xmlstr += XMLBuildUtil.appendStringElement("sfyxgqksm",sfyxgqksm);
		xmlstr += XMLBuildUtil.appendStringElement("sdtxnx",sdtxnx);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjqsnd",jtzjqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjzznd",jtzjzznd);
		xmlstr += XMLBuildUtil.appendStringElement("kkczje",kkczje);
		xmlstr += XMLBuildUtil.appendStringElement("zcyz",zcyz);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzjnx",yjtzjnx);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzje",yjtzje);
		xmlstr += XMLBuildUtil.appendStringElement("shbj",shbj);
		xmlstr += XMLBuildUtil.appendStringElement("cjr",cjr);
		xmlstr += XMLBuildUtil.appendStringElement("cjrq",cjrq);
		xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
		xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);
		xmlstr += "</qysdsjmba>";

		return xmlstr;

	}

	public String toXMLChilds() {
		return "";
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getBasqwsh() {
		return basqwsh;
	}

	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
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

	public String getGjrjpzmc() {
		return gjrjpzmc;
	}

	public void setGjrjpzmc(String gjrjpzmc) {
		this.gjrjpzmc = gjrjpzmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJtzjqsnd() {
		return jtzjqsnd;
	}

	public void setJtzjqsnd(String jtzjqsnd) {
		this.jtzjqsnd = jtzjqsnd;
	}

	public String getJtzjzznd() {
		return jtzjzznd;
	}

	public void setJtzjzznd(String jtzjzznd) {
		this.jtzjzznd = jtzjzznd;
	}

	public String getKkczje() {
		return kkczje;
	}

	public void setKkczje(String kkczje) {
		this.kkczje = kkczje;
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

	public String getSdtxnx() {
		return sdtxnx;
	}

	public void setSdtxnx(String sdtxnx) {
		this.sdtxnx = sdtxnx;
	}

	public String getSffhqrtj() {
		return sffhqrtj;
	}

	public void setSffhqrtj(String sffhqrtj) {
		this.sffhqrtj = sffhqrtj;
	}

	public String getSfysdnxly() {
		return sfysdnxly;
	}

	public void setSfysdnxly(String sfysdnxly) {
		this.sfysdnxly = sfysdnxly;
	}

	public String getSfysdnxzm() {
		return sfysdnxzm;
	}

	public void setSfysdnxzm(String sfysdnxzm) {
		this.sfysdnxzm = sfysdnxzm;
	}

	public String getSfyxgqksm() {
		return sfyxgqksm;
	}

	public void setSfyxgqksm(String sfyxgqksm) {
		this.sfyxgqksm = sfyxgqksm;
	}

	public String getShbj() {
		return shbj;
	}

	public void setShbj(String shbj) {
		this.shbj = shbj;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYjtzje() {
		return yjtzje;
	}

	public void setYjtzje(String yjtzje) {
		this.yjtzje = yjtzje;
	}

	public String getYjtzjnx() {
		return yjtzjnx;
	}

	public void setYjtzjnx(String yjtzjnx) {
		this.yjtzjnx = yjtzjnx;
	}

	public String getZcyz() {
		return zcyz;
	}

	public void setZcyz(String zcyz) {
		this.zcyz = zcyz;
	}



	
}
