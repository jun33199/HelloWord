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
 * @author wangys
 * @version 1.1
 */


import java.util.*;

import com.syax.common.xml.util.*;


public class Jmba20VO implements JmbamxVoInterface {

    //序号
    private String xh = "";
//    //备案申请文书号
//    private String BASQWSH = "";
//    //计算机代码
//    private String JSJDM = "";
//    //备案年度
//    private String BAND = "";
    //动漫企业类型代码
    private String dmqylxdm= "";
//    //有动漫企业证书
//    private String SFYDMQYZS = "";
    //证书编号
    private String zsbh= "";
    //证书起始日期
    private String zsqsrq= "";
    //证书终止日期
    private String zszzrq= "";
//    //有年审合格证明
//    private String SFYNSHGZM = "";
//    //已享受优惠
//    private String SFYXSYH = "";
    //获利年度
    private String hlnd= "";    
    //免征起始年度
    private String mzqsnd= "";    
    //免征终止年度
    private String mzzznd= "";
    //减征起始年度
    private String jzqsnd= "";    
    //减征终止年度
    private String jzzznd= "";     
    //减免税额
    private String jmse= "";
//    //其他资料
//    private String QTZL = "";
    //创建人
//    private String CJR = "";
//    //创建日期
//    private String CJRQ = "";
//    //录入人
//    private String LRR = "";
//    //录入日期
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
