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


public class Jmba18VO implements JmbamxVoInterface {

    //���
    private String xh = "";
//    //�������������
//    private String BASQWSH = "";
//    //���������
//    private String JSJDM = "";
//    //�������
//    private String BAND = "";
    //����ҵ��������
    private String fwywfwdm = "";
//    //���ڼ����Ƚ���ҵ
//    private String SFYJSXJFWQY = "";
    //֤����
    private String zsbh= "";
    //֤����ʼ����
    private String  zsqsrq= "";
    //֤����ֹ����
    private String  zszzrq= "";
//    //������ϸ�֤��
//    private String SFYNSHGZM = "";
    //����˰��
    private String jmse= "";
//    //��������
//    private String QTZL = "";
//    //������
//    private String CJR = "";
//    //��������
//    private String CJRQ = "";
//    //¼����
//    private String LRR = "";
//    //¼������
//    private String LRRQ = "";


    public Jmba18VO() {
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
        xmlstr += XMLBuildUtil.appendStringElement("fwywfwdm", fwywfwdm);
//        xmlstr += XMLBuildUtil.appendStringElement("SFYJSXJFWQY", SFYJSXJFWQY);
        xmlstr += XMLBuildUtil.appendStringElement("zsbh", zsbh);
        xmlstr += XMLBuildUtil.appendStringElement("zsqsrq", zsqsrq);
        xmlstr += XMLBuildUtil.appendStringElement("zszzrq", zszzrq);
//        xmlstr += XMLBuildUtil.appendStringElement("SFYNSHGZM", SFYNSHGZM);
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

	public String getFwywfwdm() {
		return fwywfwdm;
	}

	public void setFwywfwdm(String fwywfwdm) {
		this.fwywfwdm = fwywfwdm;
	}

	public String getJmse() {
		return jmse;
	}

	public void setJmse(String jmse) {
		this.jmse = jmse;
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
