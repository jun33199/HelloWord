package com.syax.bjtax.shenbao.jmba.xmlvo;


import java.util.*;

import com.syax.common.xml.util.*;

public class Jmba14BVO  implements JmbamxVoInterface {

//    private String cjr = "";
//    private String cjrq = "";
//    private String lrr = "";
//    private String lrrq = "";
    private String xh;
    private String basqwsh = "";
    private String jsjdm = "";
//    private String band = "";


    //ר���豸���ʹ���
    private String   zysblxdm;
    //ר���豸����
    private String   zysbmc;
//    private String  tzezs2008 ="";
//    private String  dmynse2008="";
//    private String  tzezs2009="";
//    private String  dmynse2009="";
    //���깺���豸Ͷ�ʶ�
    private String  tzezs;
//  ����ɵ����Ӧ��˰�� 
    private String dnkdmse;
    //�������
    private String dmnd;
    //����ʵ�ʵ����Ӧ��˰��
    private String  dmynse;
    //����ɱ�����ʶ 0:����ɣ�1��δ���
    private String ywcbabs="1";
    
//  ��ת�Ժ���ȵ����Ӧ��˰��
    private String jze;
    


    public Jmba14BVO() {
    }

    public Map getListTypeMap() {
        return null;
    }

    public String getDmnd() {
		return dmnd;
	}

	public void setDmnd(String dmnd) {
		this.dmnd = dmnd;
	}

	public String getDmynse() {
		return dmynse;
	}

	public void setDmynse(String dmynse) {
		this.dmynse = dmynse;
	}

	public String getDnkdmse() {
		return dnkdmse;
	}

	public void setDnkdmse(String dnkdmse) {
		this.dnkdmse = dnkdmse;
	}

	public String getJze() {
		return jze;
	}

	public void setJze(String jze) {
		this.jze = jze;
	}

	public String getTzezs() {
		return tzezs;
	}

	public void setTzezs(String tzezs) {
		this.tzezs = tzezs;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYwcbabs() {
		return ywcbabs;
	}

	public void setYwcbabs(String ywcbabs) {
		this.ywcbabs = ywcbabs;
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

	public String toXML() {
        String xmlstr = "";
         xmlstr += "<qysdsjmba>";
         xmlstr += XMLBuildUtil.appendStringElement("xh", xh);
         xmlstr += XMLBuildUtil.appendStringElement("basqwsh", basqwsh);
         xmlstr += XMLBuildUtil.appendStringElement("jsjdm", jsjdm);
//         xmlstr += XMLBuildUtil.appendStringElement("band", band);


         xmlstr += XMLBuildUtil.appendStringElement("zysblxdm",zysblxdm );
         xmlstr += XMLBuildUtil.appendStringElement("zysbmc", zysbmc);
//         xmlstr += XMLBuildUtil.appendStringElement("tzezs2008", tzezs2008);
//         xmlstr += XMLBuildUtil.appendStringElement("dmynse2008",dmynse2008 );
//         xmlstr += XMLBuildUtil.appendStringElement("tzezs2009",tzezs2009 );
//         xmlstr += XMLBuildUtil.appendStringElement("dmynse2009",dmynse2009 );
         xmlstr += XMLBuildUtil.appendStringElement("tzezs", tzezs);
         xmlstr += XMLBuildUtil.appendStringElement("dmnd", dmnd);

         xmlstr += XMLBuildUtil.appendStringElement("dmynse", dmynse);

         xmlstr += XMLBuildUtil.appendStringElement("ywcbabs", ywcbabs);
         xmlstr += XMLBuildUtil.appendStringElement("dnkdmse", dnkdmse);

         xmlstr += XMLBuildUtil.appendStringElement("jze", jze);

//         xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgdm", swjgzzjgdm);

//         xmlstr += XMLBuildUtil.appendStringElement("cjr", cjr);
//        xmlstr += XMLBuildUtil.appendStringElement("cjrq", cjrq);
//        xmlstr += XMLBuildUtil.appendStringElement("lrr", lrr);
//        xmlstr += XMLBuildUtil.appendStringElement("lrrq", lrrq);
        xmlstr += "</qysdsjmba>";

         return xmlstr;

    }

    public String toXMLChilds() {
        return "";
    }

	public String getBasqwsh() {
		return basqwsh;
	}

	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

    
}
