package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: ��һ����</p>
 *
 * <p>Company: ��һ����</p>
 *
 * @author wangys
 * @version 1.1
 */


import java.util.*;

import com.syax.common.xml.util.*;


public class Jmba20VO implements JmbamxVoInterface {

    //���
    private String xh = "";
//    //�������������
//    private String BASQWSH = "";
//    //���������
//    private String JSJDM = "";
//    //�������
//    private String BAND = "";
    //������ҵ���ʹ���
    private String dmqylxdm= "";
//    //�ж�����ҵ֤��
//    private String SFYDMQYZS = "";
    //֤����
    private String zsbh= "";
    //֤����ʼ����
    private String zsqsrq= "";
    //֤����ֹ����
    private String zszzrq= "";
//    //������ϸ�֤��
//    private String SFYNSHGZM = "";
//    //�������Ż�
//    private String SFYXSYH = "";
    //�������
    private String hlnd= "";    
    //������ʼ���
    private String mzqsnd= "";    
    //������ֹ���
    private String mzzznd= "";
    //������ʼ���
    private String jzqsnd= "";    
    //������ֹ���
    private String jzzznd= "";     
    //����˰��
    private String jmse= "";
//    //��������
//    private String QTZL = "";
    //������
//    private String CJR = "";
//    //��������
//    private String CJRQ = "";
//    //¼����
//    private String LRR = "";
//    //¼������
//    private String LRRQ = "";


    public Jmba20VO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {
        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
//        xmlstr += XMLBuildUtil.appendStringElement("BASQWSH", BASQWSH);
//        xmlstr += XMLBuildUtil.appendStringElement("JSJDM", JSJDM);
//        xmlstr += XMLBuildUtil.appendStringElement("BAND", BAND);
        xmlstr += XMLBuildUtil.appendStringElement("dmqylxdm", dmqylxdm);
//        xmlstr += XMLBuildUtil.appendStringElement("SFYDMQYZS", SFYDMQYZS);
        xmlstr += XMLBuildUtil.appendStringElement("zsbh", zsbh);
        xmlstr += XMLBuildUtil.appendStringElement("zsqsrq", zsqsrq);
        xmlstr += XMLBuildUtil.appendStringElement("zszzrq", zszzrq);
//        xmlstr += XMLBuildUtil.appendStringElement("SFYNSHGZM", SFYNSHGZM);
//        xmlstr += XMLBuildUtil.appendStringElement("SFYXSYH", SFYXSYH);
        xmlstr += XMLBuildUtil.appendStringElement("hlnd", hlnd);
        xmlstr += XMLBuildUtil.appendStringElement("mzqsnd", mzqsnd);
        xmlstr += XMLBuildUtil.appendStringElement("mzzznd", mzzznd);
        xmlstr += XMLBuildUtil.appendStringElement("jzqsnd", jzqsnd);
        xmlstr += XMLBuildUtil.appendStringElement("jzzznd", jzzznd);
        xmlstr += XMLBuildUtil.appendStringElement("jmse", jmse);       
//        xmlstr += XMLBuildUtil.appendStringElement("QTZL", QTZL);
//        xmlstr += XMLBuildUtil.appendStringElement("CJR", CJR);
//        xmlstr += XMLBuildUtil.appendStringElement("CJRQ", CJRQ);
//        xmlstr += XMLBuildUtil.appendStringElement("LRR", LRR);
//        xmlstr += XMLBuildUtil.appendStringElement("LRRQ", LRRQ);
        xmlstr += "</qysdsjmba>";
        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

	public String getDmqylxdm() {
		return dmqylxdm;
	}

	public void setDmqylxdm(String dmqylxdm) {
		this.dmqylxdm = dmqylxdm;
	}

	public String getHlnd() {
		return hlnd;
	}

	public void setHlnd(String hlnd) {
		this.hlnd = hlnd;
	}

	public String getJmse() {
		return jmse;
	}

	public void setJmse(String jmse) {
		this.jmse = jmse;
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

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZsbh() {
		return zsbh;
	}

	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}

	public String getZsqsrq() {
		return zsqsrq;
	}

	public void setZsqsrq(String zsqsrq) {
		this.zsqsrq = zsqsrq;
	}

	public String getZszzrq() {
		return zszzrq;
	}

	public void setZszzrq(String zszzrq) {
		this.zszzrq = zszzrq;
	}

}
