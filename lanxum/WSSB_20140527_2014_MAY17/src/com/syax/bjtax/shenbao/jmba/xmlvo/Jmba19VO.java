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


public class Jmba19VO implements JmbamxVoInterface {

    //序号
    private String XH = "";
    //备案申请文书号
    private String BASQWSH = "";
    //计算机代码
    private String JSJDM = "";
    //备案年度
    private String BAND = "";
    //文化事业单位类型代码
    private String WHSYDWLXDM = "";
    //有专制企业名单
    private String SFYQYMD = "";
    //有专制反感批复函
    private String SFYZZFAPFH = "";
    //办理工商营业执照
    private String SFBLGSYYZZ = "";
    //有注销事业单位证明
    private String SFYZXSYDWZM = "";
    //参加社会保险
    private String SFCJSHBX = "";
    //变更资本机构
    private String SFYBGZBJG = "";
    //减免税额
    private String JMSE = "";    
    //其他资料
    private String QTZL = "";
    //创建人
    private String CJR = "";
    //创建日期
    private String CJRQ = "";
    //录入人
    private String LRR = "";
    //录入日期
    private String LRRQ = "";


    public Jmba19VO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {
        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("XH", XH);
        xmlstr += XMLBuildUtil.appendStringElement("BASQWSH", BASQWSH);
        xmlstr += XMLBuildUtil.appendStringElement("JSJDM", JSJDM);
        xmlstr += XMLBuildUtil.appendStringElement("BAND", BAND);
        xmlstr += XMLBuildUtil.appendStringElement("WHSYDWLXDM", WHSYDWLXDM);
        xmlstr += XMLBuildUtil.appendStringElement("SFYQYMD", SFYQYMD);
        xmlstr += XMLBuildUtil.appendStringElement("SFYZZFAPFH", SFYZZFAPFH);
        xmlstr += XMLBuildUtil.appendStringElement("SFBLGSYYZZ", SFBLGSYYZZ);
        xmlstr += XMLBuildUtil.appendStringElement("SFYZXSYDWZM", SFYZXSYDWZM);
        xmlstr += XMLBuildUtil.appendStringElement("SFCJSHBX", SFCJSHBX);
        xmlstr += XMLBuildUtil.appendStringElement("SFYBGZBJG", SFYBGZBJG);
        xmlstr += XMLBuildUtil.appendStringElement("JMSE", JMSE);       
        xmlstr += XMLBuildUtil.appendStringElement("QTZL", QTZL);
        xmlstr += XMLBuildUtil.appendStringElement("CJR", CJR);
        xmlstr += XMLBuildUtil.appendStringElement("CJRQ", CJRQ);
        xmlstr += XMLBuildUtil.appendStringElement("LRR", LRR);
        xmlstr += XMLBuildUtil.appendStringElement("LRRQ", LRRQ);
        xmlstr += "</qysdsjmba>";
        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }
    public String getXH() {
        return XH;
    }    
    public void setXH(String XH) {
        this.XH = XH;
    }
    public String getBASQWSH() {
        return BASQWSH;
    }
    public void setBASQWSH(String BASQWSH) {
        this.BASQWSH = BASQWSH;
    }
    public String getJSJDM() {
        return JSJDM;
    }    
    public void setJSJDM(String JSJDM) {
        this.JSJDM = JSJDM;
    }    
    public String getBAND() {
        return BAND;
    }
    public void setBAND(String BAND) {
        this.BAND = BAND;
    }
    public String getWHSYDWLXDM() {
        return WHSYDWLXDM;
    }
    public void setWHSYDWLXDM(String WHSYDWLXDM) {
        this.WHSYDWLXDM = WHSYDWLXDM;
    }
    public String getSFYQYMD() {
        return SFYQYMD;
    }
    public void setSFYQYMD(String SFYQYMD) {
        this.SFYQYMD = SFYQYMD;
    }
    public String getSFYZZFAPFH() {
        return SFYZZFAPFH;
    }
    public void setSFYZZFAPFH(String SFYZZFAPFH) {
        this.SFYZZFAPFH = SFYZZFAPFH;
    }
    public String getSFBLGSYYZZ() {
        return SFBLGSYYZZ;
    }
    public void setSFBLGSYYZZ(String SFBLGSYYZZ) {
        this.SFBLGSYYZZ = SFBLGSYYZZ;
    }    
    public String getSFYZXSYDWZM() {
        return SFYZXSYDWZM;
    }
    public void setSFYZXSYDWZM(String SFYZXSYDWZM) {
        this.SFYZXSYDWZM = SFYZXSYDWZM;
    }
    public String getSFCJSHBX() {
        return SFCJSHBX;
    }
    public void setSFCJSHBX(String SFCJSHBX) {
        this.SFCJSHBX = SFCJSHBX;
    }
    public String getSFYBGZBJG() {
        return SFYBGZBJG;
    }
    public void setSFYBGZBJG(String SFYBGZBJG) {
        this.SFYBGZBJG = SFYBGZBJG;
    }    
    public String getJMSE() {
        return JMSE;
    }
    public void setJMSE(String JMSE) {
        this.JMSE = JMSE;
    } 
    public void setQTZL(String QTZL) {
        this.QTZL = QTZL;
    }
    public String getQTZL() {
        return QTZL;
    }    
    public String getCJR() {
        return CJR;
    }
    public String getCJRQ() {
        return CJRQ;
    }
    public void setCJR(String CJR) {
        this.CJR = CJR;
    }
    public void setCJRQ(String CJRQ) {
        this.CJRQ = CJRQ;
    }
    public void setLRR(String LRR) {
        this.LRR = LRR;
    }
    public void setLRRQ(String LRRQ) {
        this.LRRQ = LRRQ;
    }
    public String getLRR() {
        return LRR;
    }
    public String getLRRQ() {
        return LRRQ;
    }
}
