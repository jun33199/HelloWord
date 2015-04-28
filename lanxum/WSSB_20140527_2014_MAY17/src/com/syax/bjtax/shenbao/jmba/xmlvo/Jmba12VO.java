package com.syax.bjtax.shenbao.jmba.xmlvo;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 四一安信</p>
 *
 * <p>Company: 四一安信</p>
 *
 * @author niuy
 * @version 1.1
 */


import java.util.*;

import com.syax.common.xml.util.*;


public class Jmba12VO implements JmbamxVoInterface {

    //序号
    private String XH = "";
//    //备案申请文书号
//    private String BASQWSH = "";
//    //计算机代码
//    private String JSJDM = "";
//    //备案年度
//    private String BAND = "";
//    //属于集成电路企业
//    private String SFSYJCDLQY = "";
    //获利年度
    private String HLND = "";
    //其他资料
//    private String QTZL = "";
    //免征起始年度
    private String MZQSND = "";
    //免征终止年度
    private String MZZZND = "";
    //减征起始年度
    private String JZQSND = "";
    //减征终止年度
    private String JZZZND = "";
    //预计减免税额
    private String YJJMSE = "";
    //创建人
//    private String CJR = "";
//    //创建日期
//    private String CJRQ = "";
//    //录入人
//    private String LRR = "";
//    //录入日期
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
