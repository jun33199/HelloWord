package com.ttsoft.bjtax.shenbao.zhsb.zyjks.xmlvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.common.xml.XMLVOInterface;
import com.syax.common.xml.util.XMLBuildUtil;


public class SbsjVO implements XMLVOInterface{

	List nxxm=new ArrayList();
	private Map m = new HashMap();
    public SbsjVO(){
        super();
        m.put("nxxm", "com.ttsoft.bjtax.shenbao.zhsbzyjks.xmlvo.Nxxm02VO");
    }
    public Map getListTypeMap(){
        return m;
    }
    public String toXML(){
        String xmlstr = "<sbsj>";        
        xmlstr += toXMLChilds();
        xmlstr += "</sbsj>";
    	return xmlstr;	
    }
    public String toXMLChilds(){
        String xmlstr = "";
        xmlstr += XMLBuildUtil.appendStringElement("zwbs",zwbs);
        xmlstr += XMLBuildUtil.appendStringElement("yhdm",yhdm);
        xmlstr += XMLBuildUtil.appendStringElement("yhmc",yhmc);
        xmlstr += XMLBuildUtil.appendStringElement("zh",zh);
        xmlstr += XMLBuildUtil.appendStringElement("sbrq",sbrq);
        xmlstr += XMLBuildUtil.appendStringElement("sbbh",sbbh);
        xmlstr += XMLBuildUtil.appendStringElement("skgk",skgk);
        xmlstr += XMLBuildUtil.appendStringElement("gkzzjgdm",gkzzjgdm);
        xmlstr += XMLBuildUtil.appendStringElement("swjgzzjgmc",swjgzzjgmc);
        xmlstr += XMLBuildUtil.appendStringElement("djzclxmc",djzclxmc);        
        xmlstr += XMLBuildUtil.appendStringElement("lxdh",lxdh);
        xmlstr += XMLBuildUtil.appendStringElement("lsgxmc",lsgxmc);
        xmlstr += XMLBuildUtil.appendStringElement("hjsjje",hjsjje);
        xmlstr += XMLBuildUtil.appendStringElement("hjsjjedx",hjsjjedx);
        xmlstr += XMLBuildUtil.appendStringElement("xjrq",xjrq);
        if (nxxm != null && nxxm.size()>0)
        {
            for (int i = 0; i < nxxm.size(); i++)
            {
                xmlstr += ((NxxmVO) nxxm.get(i)).toXML();
            }
        }
        return xmlstr;
    }
    
    //˰��˰Ŀ���룻
    String yhdm;
    String yhmc;    
    //˰�ִ���
    String zh;
    //˰������
    String sbrq;
    //Ԥ�㼶�δ���
    String sbbh;

    //���������
    String skgk;
    //��˰������
    String gkzzjgdm;
    //˰�����ʹ���
    String zwbs;
    //˰����������
    String hjsjje;
    //���ջ��ش���
    String hjsjjedx;
    
    String swjgzzjgmc;
    //¼����
    String djzclxmc;
    //˰��������ʼ����
    String lxdh;
    //˰��������������
    String lsgxmc;
    
    //�޽�����
    String xjrq = "";
    
	public String getXjrq() {
		return xjrq;
	}
	public void setXjrq(String xjrq) {
		this.xjrq = xjrq;
	}
	public String getDjzclxmc() {
		return djzclxmc;
	}
	public void setDjzclxmc(String djzclxmc) {
		this.djzclxmc = djzclxmc;
	}
	public String getGkzzjgdm() {
		return gkzzjgdm;
	}
	public void setGkzzjgdm(String gkzzjgdm) {
		this.gkzzjgdm = gkzzjgdm;
	}
	public String getHjsjje() {
		return hjsjje;
	}
	public void setHjsjje(String hjsjje) {
		this.hjsjje = hjsjje;
	}
	public String getHjsjjedx() {
		return hjsjjedx;
	}
	public void setHjsjjedx(String hjsjjedx) {
		this.hjsjjedx = hjsjjedx;
	}
	public String getLsgxmc() {
		return lsgxmc;
	}
	public void setLsgxmc(String lsgxmc) {
		this.lsgxmc = lsgxmc;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public List getNxxm() {
		return nxxm;
	}
	public void setNxxm(List nxxm) {
		this.nxxm = nxxm;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public String getSbrq() {
		return sbrq;
	}
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	public String getSkgk() {
		return skgk;
	}
	public void setSkgk(String skgk) {
		this.skgk = skgk;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public String getZwbs() {
		return zwbs;
	}
	public void setZwbs(String zwbs) {
		this.zwbs = zwbs;
	}
}
