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
 * @author niuy
 * @version 1.1
 */


import java.util.*;

import com.syax.common.xml.util.*;


public class Jmba12VO implements JmbamxVoInterface {

    //���
    private String XH = "";
//    //�������������
//    private String BASQWSH = "";
//    //���������
//    private String JSJDM = "";
//    //�������
//    private String BAND = "";
//    //���ڼ��ɵ�·��ҵ
//    private String SFSYJCDLQY = "";
    //�������
    private String HLND = "";
    //��������
//    private String QTZL = "";
    //������ʼ���
    private String MZQSND = "";
    //������ֹ���
    private String MZZZND = "";
    //������ʼ���
    private String JZQSND = "";
    //������ֹ���
    private String JZZZND = "";
    //Ԥ�Ƽ���˰��
    private String YJJMSE = "";
    //������
//    private String CJR = "";
//    //��������
//    private String CJRQ = "";
//    //¼����
//    private String LRR = "";
//    //¼������
//    private String LRRQ = "";
//    private String swjgzzjgdm = "";

    public Jmba12VO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {
        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("XH", XH);
//        xmlstr += XMLBuildUtil.appendStringElement("BASQWSH", BASQWSH);
//        xmlstr += XMLBuildUtil.appendStringElement("JSJDM", JSJDM);
//        xmlstr += XMLBuildUtil.appendStringElement("BAND", BAND);
//        xmlstr += XMLBuildUtil.appendStringElement("SFSYJCDLQY", SFSYJCDLQY);
        xmlstr += XMLBuildUtil.appendStringElement("HLND", HLND);
//        xmlstr += XMLBuildUtil.appendStringElement("QTZL", QTZL);
        xmlstr += XMLBuildUtil.appendStringElement("MZQSND", MZQSND);
        xmlstr += XMLBuildUtil.appendStringElement("MZZZND", MZZZND);
        xmlstr += XMLBuildUtil.appendStringElement("JZQSND", JZQSND);
        xmlstr += XMLBuildUtil.appendStringElement("JZZZND", JZZZND);
        xmlstr += XMLBuildUtil.appendStringElement("YJJMSE", YJJMSE);
//        xmlstr += XMLBuildUtil.appendStringElement("CJR", CJR);
//        xmlstr += XMLBuildUtil.appendStringElement("CJRQ", CJRQ);
//        xmlstr += XMLBuildUtil.appendStringElement("LRR", LRR);
//        String showTime = LRRQ.substring(0,10);
//        xmlstr += XMLBuildUtil.appendStringElement("LRRQ", showTime);
//        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += "</qysdsjmba>";

        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

	public String getHLND() {
		return HLND;
	}

	public void setHLND(String hlnd) {
		HLND = hlnd;
	}

	public String getJZQSND() {
		return JZQSND;
	}

	public void setJZQSND(String jzqsnd) {
		JZQSND = jzqsnd;
	}

	public String getJZZZND() {
		return JZZZND;
	}

	public void setJZZZND(String jzzznd) {
		JZZZND = jzzznd;
	}

	public String getMZQSND() {
		return MZQSND;
	}

	public void setMZQSND(String mzqsnd) {
		MZQSND = mzqsnd;
	}

	public String getMZZZND() {
		return MZZZND;
	}

	public void setMZZZND(String mzzznd) {
		MZZZND = mzzznd;
	}

	public String getXH() {
		return XH;
	}

	public void setXH(String xh) {
		XH = xh;
	}

	public String getYJJMSE() {
		return YJJMSE;
	}

	public void setYJJMSE(String yjjmse) {
		YJJMSE = yjjmse;
	}

    
}
