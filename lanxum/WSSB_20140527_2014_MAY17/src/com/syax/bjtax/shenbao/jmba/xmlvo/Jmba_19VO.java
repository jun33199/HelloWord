package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description:  (ʮ��)��෢չ������Ŀ������vo </p>
 *
 * <p>Copyright: ��һ����</p>
 *
 * <p>Company: ��һ����</p>
 *
 * @author mijun
 * @version 1.0
 */

import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba_19VO implements JmbamxVoInterface {

	// ���
	private String xh = "";

	// �������������
	private String basqwsh = "";

	// ���������
	private String jsjdm = "";

	// �������
	private String band = "";

	// ˰�������֯���ش���
	private String swjgzzjgdm = "";

	// ת��������
	private String zrsrje = "";

	// �Ͻɸ����ҵ�HFC��PFC��CDM��Ŀ�Ľ��
	private String sjje1 = "";

	// �Ͻɸ����ҵ�N2O��CDM��Ŀ�Ľ��
	private String sjje2 = "";

	// ��ת��֤������
	private String sfyzrzmcl = "";

	// ���Ͻ�֤������
	private String sfysjzmcl = "";

	// ������֤������
	private String sfysrzmcl = "";

	// �к����������
	private String sfyhsqksm = "";

	// �������
	private String hlnd = "";

	// ��������
	private String qtzl = "";

	// ������ʼ���
	private String mzqsnd = "";

	// ������ֹ���
	private String mzzznd = "";

	// ������ʼ���
	private String jzqsnd = "";

	// ������ֹ���
	private String jzzznd = "";

	// ������
	private String cjr = "";

	// ��������
	private String cjrq = "";

	// ¼����
	private String lrr = "";

	// ¼������
	private String lrrq = "";

	public Jmba_19VO() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
		xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
		xmlstr += XMLBuildUtil.appendStringElement("band", band);
		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("zrsrje", zrsrje);
		xmlstr += XMLBuildUtil.appendStringElement("sjje1", sjje1);
		xmlstr += XMLBuildUtil.appendStringElement("sjje2", sjje2);
		xmlstr += XMLBuildUtil.appendStringElement("sfyzrzmcl", sfyzrzmcl);
		xmlstr += XMLBuildUtil.appendStringElement("sfysjzmcl", sfysjzmcl);
		xmlstr += XMLBuildUtil.appendStringElement("sfysrzmcl", sfysrzmcl);
		xmlstr += XMLBuildUtil.appendStringElement("sfyhsqksm", sfyhsqksm);
		xmlstr += XMLBuildUtil.appendStringElement("hlnd", hlnd);
		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
		xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
		xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
		xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
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

	public String getQtzl() {
		return qtzl;
	}

	public void setQtzl(String qtzl) {
		this.qtzl = qtzl;
	}

	public String getSfyhsqksm() {
		return sfyhsqksm;
	}

	public void setSfyhsqksm(String sfyhsqksm) {
		this.sfyhsqksm = sfyhsqksm;
	}

	public String getSfysjzmcl() {
		return sfysjzmcl;
	}

	public void setSfysjzmcl(String sfysjzmcl) {
		this.sfysjzmcl = sfysjzmcl;
	}

	public String getSfysrzmcl() {
		return sfysrzmcl;
	}

	public void setSfysrzmcl(String sfysrzmcl) {
		this.sfysrzmcl = sfysrzmcl;
	}

	public String getSfyzrzmcl() {
		return sfyzrzmcl;
	}

	public void setSfyzrzmcl(String sfyzrzmcl) {
		this.sfyzrzmcl = sfyzrzmcl;
	}

	public String getSjje1() {
		return sjje1;
	}

	public void setSjje1(String sjje1) {
		this.sjje1 = sjje1;
	}

	public String getSjje2() {
		return sjje2;
	}

	public void setSjje2(String sjje2) {
		this.sjje2 = sjje2;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZrsrje() {
		return zrsrje;
	}

	public void setZrsrje(String zrsrje) {
		this.zrsrje = zrsrje;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getJzqsnd() {
		return jzqsnd;
	}

	public void setJzqsnd(String jzqsnd) {
		this.jzqsnd = jzqsnd;
	}

	public String getJzzznd() {
		return jzzznd;
	}

	public void setJzzznd(String jzzznd) {
		this.jzzznd = jzzznd;
	}

	public String getMzqsnd() {
		return mzqsnd;
	}

	public void setMzqsnd(String mzqsnd) {
		this.mzqsnd = mzqsnd;
	}

	public String getMzzznd() {
		return mzzznd;
	}

	public void setMzzznd(String mzzznd) {
		this.mzzznd = mzzznd;
	}

}
