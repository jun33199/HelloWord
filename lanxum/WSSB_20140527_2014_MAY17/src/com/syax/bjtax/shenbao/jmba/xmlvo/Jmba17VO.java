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

public class Jmba17VO implements JmbamxVoInterface {

	// ���
	private String xh = "";
//
//	// �������������
//	private String basqwsh = "";
//
//	// ���������
//	private String jsjdm = "";
//
//	// �������
//	private String band = "";
//
//	// ˰�������֯���ش���
//	private String swjgzzjgdm = "";

	// ת��������
	private String zrsrje = "";

	// �Ͻɸ����ҵ�HFC��PFC��CDM��Ŀ�Ľ��
	private String sjje1 = "";

	// �Ͻɸ����ҵ�N2O��CDM��Ŀ�Ľ��
	private String sjje2 = "";

//	// ��ת��֤������
//	private String sfyzrzmcl = "";
//
//	// ���Ͻ�֤������
//	private String sfysjzmcl = "";
//
//	// ������֤������
//	private String sfysrzmcl = "";
//
//	// �к����������
//	private String sfyhsqksm = "";

	// �������
	private String hlnd = "";

	// ��������
	//private String qtzl = "";

	// ������ʼ���
	private String mzqsnd = "";

	// ������ֹ���
	private String mzzznd = "";

	// ������ʼ���
	private String jzqsnd = "";

	// ������ֹ���
	private String jzzznd = "";

//	// ������
//	private String cjr = "";
//
//	// ��������
//	private String cjrq = "";
//
//	// ¼����
//	private String lrr = "";
//
//	// ¼������
//	private String lrrq = "";

	public Jmba17VO() {
	}

	public Map getListTypeMap() {
		return null;
	}

	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", this.xh);
//		xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
//		xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
//		xmlstr += XMLBuildUtil.appendStringElement("band", band);
//		xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("zrsrje", this.zrsrje);
		xmlstr += XMLBuildUtil.appendStringElement("sjje1", this.sjje1);
		xmlstr += XMLBuildUtil.appendStringElement("sjje2", this.sjje2);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyzrzmcl", sfyzrzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfysjzmcl", sfysjzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfysrzmcl", sfysrzmcl);
//		xmlstr += XMLBuildUtil.appendStringElement("sfyhsqksm", sfyhsqksm);
		xmlstr += XMLBuildUtil.appendStringElement("hlnd", this.hlnd);
//		xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
		xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", this.mzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("mzzznd", this.mzzznd);
		xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", this.jzqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jzzznd", this.jzzznd);
//		xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
//		xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
//		xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
//		xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
		xmlstr += "</qysdsjmba>";

		return xmlstr;

	}

	public String toXMLChilds() {
		return "";
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

	public String getZrsrje() {
		return zrsrje;
	}

	public void setZrsrje(String zrsrje) {
		this.zrsrje = zrsrje;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

}
