package com.syax.bjtax.shenbao.jmba.xmlvo;


import java.util.*;

import com.syax.common.xml.util.*;
public class Jmba14AVO  implements JmbamxVoInterface {


//    private String cjr = "";
//    private String cjrq = "";
//    private String lrr = "";
//    private String lrrq = "";
    private String xh = "";
//    private String basqwsh = "";
//    private String jsjdm = "";
//    private String band = "";

    private String zysblxdm = "";
    private String zysbmc = "";
    private String gznd = "";
//    private String sfgmfpjqd = "";
//    private String sfsykphmxz = "";
//    private String sfsygdsm = "";
//    private String sfsyqksm = "";
    private String tze = "";
    private String dmynse = "";
//    private String qtzl = "";
//    private String swjgzzjgdm = "";



    public Jmba14AVO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String toXML() {
        String xmlstr = "";
        xmlstr += "<qysdsjmba>";
        xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
//        xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
//        xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
//        xmlstr += XMLBuildUtil.appendStringElement("band", band);
        xmlstr += XMLBuildUtil.appendStringElement("zysblxdm", zysblxdm);
        xmlstr += XMLBuildUtil.appendStringElement("zysbmc", zysbmc);
        xmlstr += XMLBuildUtil.appendStringElement("gznd", gznd);
//        xmlstr += XMLBuildUtil.appendStringElement("sfgmfpjqd", sfgmfpjqd);
//        xmlstr += XMLBuildUtil.appendStringElement("sfsykphmxz", sfsykphmxz);
//        xmlstr += XMLBuildUtil.appendStringElement("sfsygdsm", sfsygdsm);
//        xmlstr += XMLBuildUtil.appendStringElement("sfsyqksm", sfsyqksm);
        xmlstr += XMLBuildUtil.appendStringElement("tze", tze);
        xmlstr += XMLBuildUtil.appendStringElement("dmynse", dmynse);
//        xmlstr += XMLBuildUtil.appendStringElement("qtzl", qtzl);
//        xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
//        xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
//        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
//        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
//        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);
        xmlstr += "</qysdsjmba>";

        return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

	public String getDmynse() {
		return dmynse;
	}

	public void setDmynse(String dmynse) {
		this.dmynse = dmynse;
	}

	public String getGznd() {
		return gznd;
	}

	public void setGznd(String gznd) {
		this.gznd = gznd;
	}

	public String getTze() {
		return tze;
	}

	public void setTze(String tze) {
		this.tze = tze;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZysblxdm() {
		return zysblxdm;
	}

	public void setZysblxdm(String zysblxdm) {
		this.zysblxdm = zysblxdm;
	}

	public String getZysbmc() {
		return zysbmc;
	}

	public void setZysbmc(String zysbmc) {
		this.zysbmc = zysbmc;
	}

    
}
