/*
 * Created on 2009-12-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.xmlvo;

import java.util.Map;

import com.syax.common.xml.util.XMLBuildUtil;

/**
 * @author ��������-mijun
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Jmba16VO implements JmbamxVoInterface {
	// ���
	private String xh = "";

	// �������������
	// private String basqwsh = "";
	//
	// // ���������
	// private String jsjdm = "";
	//
	// // �������
	// private String band = "";
	//
	// // ˰�������֯���ش���
	// private String swjgzzjgdm ;

	// ��ҵ���������ƾ֤����
	private String gjrjpzmc;

	// // ����ȷ��������0���ǣ�1����
	// private String sffhqrtj = "0";
	//
	// // �������������ɣ�0���ǣ�1����
	// private String sfysdnxly = "0";
	//
	// // ����������֤����0���ǣ�1����
	// private String sfysdnxzm = "0";
	//
	// // ����������˵����0���ǣ�1����
	// private String sfyxgqksm = "0";

	// �⹺��������۾ɻ�̯��������
	private String sdtxnx;

	// �����۾���ʼ���
	private String jtzjqsnd;

	// �����۾���ֹ���
	private String jtzjzznd;

	// ÿ��ɿ۳����۾ɶ�
	private String kkczje;

	// �̶��ʲ�(�����ʲ�)ԭֵ
	private String zcyz;

	// �Ѽ����۾ɵ�����
	private String yjtzjnx;

	// �Ѽ�����۾ɶ�
	private String yjtzje;

	// // ��˱�ǣ�0��ͨ����1����ͨ��
	// private String shbj;
	//
	// // ������
	// private String cjr;
	//
	// // ��������
	// private String cjrq;
	//
	// // ¼����
	// private String lrr;
	//
	// // ¼������
	// private String lrrq;

	public Map getListTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syax.common.xml.XMLVOInterface#toXML()
	 */
	public String toXML() {
		String xmlstr = "";
		xmlstr += "<qysdsjmba>";
		xmlstr += XMLBuildUtil.appendStringElement("xh", this.xh);
		// xmlstr += XMLBuildUtil.appendStringElement("basqwsh",basqwsh);
		// xmlstr += XMLBuildUtil.appendStringElement("jsjdm",jsjdm);
		// xmlstr += XMLBuildUtil.appendStringElement("band", band);
		// xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
		xmlstr += XMLBuildUtil.appendStringElement("gjrjpzmc", this.gjrjpzmc);
		// xmlstr += XMLBuildUtil.appendStringElement("sffhqrtj",sffhqrtj);
		// xmlstr += XMLBuildUtil.appendStringElement("sfysdnxly",sfysdnxly);
		// xmlstr += XMLBuildUtil.appendStringElement("sfysdnxzm",sfysdnxzm);
		// xmlstr += XMLBuildUtil.appendStringElement("sfyxgqksm",sfyxgqksm);
		xmlstr += XMLBuildUtil.appendStringElement("sdtxnx", this.sdtxnx);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjqsnd", this.jtzjqsnd);
		xmlstr += XMLBuildUtil.appendStringElement("jtzjzznd", this.jtzjzznd);
		xmlstr += XMLBuildUtil.appendStringElement("kkczje", this.kkczje);
		xmlstr += XMLBuildUtil.appendStringElement("zcyz", this.zcyz);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzjnx", this.yjtzjnx);
		xmlstr += XMLBuildUtil.appendStringElement("yjtzje", this.yjtzje);
		// xmlstr += XMLBuildUtil.appendStringElement("shbj",shbj);
		// xmlstr += XMLBuildUtil.appendStringElement("cjr",cjr);
		// xmlstr += XMLBuildUtil.appendStringElement("cjrq",cjrq);
		// xmlstr += XMLBuildUtil.appendStringElement("lrr",lrr);
		// xmlstr += XMLBuildUtil.appendStringElement("lrrq",lrrq);

		xmlstr += "</qysdsjmba>";

		return xmlstr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
	 */
	public String toXMLChilds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGjrjpzmc() {
		return gjrjpzmc;
	}

	public void setGjrjpzmc(String gjrjpzmc) {
		this.gjrjpzmc = gjrjpzmc;
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

	public String getSdtxnx() {
		return sdtxnx;
	}

	public void setSdtxnx(String sdtxnx) {
		this.sdtxnx = sdtxnx;
	}

	// public String getSffhqrtj() {
	// return sffhqrtj;
	// }
	//
	// public void setSffhqrtj(String sffhqrtj) {
	// this.sffhqrtj = sffhqrtj;
	// }
	//
	// public String getSfysdnxly() {
	// return sfysdnxly;
	// }
	//
	// public void setSfysdnxly(String sfysdnxly) {
	// this.sfysdnxly = sfysdnxly;
	// }
	//
	// public String getSfysdnxzm() {
	// return sfysdnxzm;
	// }
	//
	// public void setSfysdnxzm(String sfysdnxzm) {
	// this.sfysdnxzm = sfysdnxzm;
	// }
	//
	// public String getSfyxgqksm() {
	// return sfyxgqksm;
	// }
	//
	// public void setSfyxgqksm(String sfyxgqksm) {
	// this.sfyxgqksm = sfyxgqksm;
	// }

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
