package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.smsb.model.kjqysds.*;

/**
 * �۽���ҵ����˰�����BO
 * @author Administrator
 *
 */
public class KjssjksBO implements Serializable{

	public KjssjksBO() {
	}
	/**
	 * ��ͬ����
	 */
	private String htmc;
	/**
	 * ��ͬ���
	 */
	private String htbh;
	/**
	 * ˰��˰Ŀ����
	 */
	private String szsmdm;
	/**
	 * ˰������_��ҵ����˰
	 */
	private String szmc;
	/**
	 * ˰�ִ���_30
	 */
	private String szdm;
	/**
	 * ��˰��Ŀ����_˰��˰Ŀ����
	 */
	private String szsmmc;
	/**
	 * ˰��������ʼ����
	 */
	private String skssksrq;
	/**
	 * ˰��������������
	 */
	private String skssjsrq;
	/**
	 * ʵ�ʽ�˰��
	 */
	private String sjse;
	/**
	 * ��˰���
	 */
	private String jsje;
	/**
	 * ˰��
	 */
	private String Sl;
	/**
	 * ˰���걨����
	 */
	private String sbrq;
	/**
	 * ���������
	 */
	private String jsjdm;
    /**
     * ��˰������
     */
    private String nsrmc;
	/**
	 * ¼����Ա
	 */
	private String lrr;
	/**
	 * �����Ǽ����
	 */
	private String badjxh;

   /**
    * ��������
    */
   private String bgbxh;
   /**
    * ���������б�
    */
   private List dataList = new ArrayList();
   /**
    * ��������
    */
   private String yhmc;

   /**
    * ���д���
    */
   private String yhdm;

   /**
    * �˺�
    */
   private String zh;
   /**
    * �ɿ�������
    */
   private int jksType;
   /**
    * ˰�����ʹ���
    */
   private String sklxdm;

   /**
    * ˰����������
    */
   private String sklxmc;
   /**
    * �걨���
    */
   private String sbbh;
   /**
    * ���ɽɿ���List
    */
   private List jksList;
   
	public String getSzsmdm() {
		return szsmdm;
	}
	
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	
	public String getSkssksrq() {
		return skssksrq;
	}
	
	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}
	
	public String getSkssjsrq() {
		return skssjsrq;
	}
	
	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
	
	public String getSbrq() {
		return sbrq;
	}
	
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	
	public String getJsjdm() {
		return jsjdm;
	}
	
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	
	public String getLrr() {
		return lrr;
	}
	
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	
	public String getBadjxh() {
		return badjxh;
	}
	
	public void setBadjxh(String badjxh) {
		this.badjxh = badjxh;
	}
	
	public String getBgbxh() {
		return bgbxh;
	}
	
	public void setBgbxh(String bgbxh) {
		this.bgbxh = bgbxh;
	}
	
	public String getSzmc() {
		return szmc;
	}
	
	public void setSzmc(String szmc) {
		this.szmc = szmc;
	}
	
	public String getSzsmmc() {
		return szsmmc;
	}
	
	public void setSzsmmc(String szsmmc) {
		this.szsmmc = szsmmc;
	}
	
	public String getSzdm() {
		return szdm;
	}
	
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	
	
	public String getHtmc() {
		return htmc;
	}
	
	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}
	
	public String getHtbh() {
		return htbh;
	}
	
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	
	public List getDataList() {
		return dataList;
	}
	
	public void setDataList(List dataList) {
		this.dataList = dataList;
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

	public int getJksType() {
		return jksType;
	}

	public void setJksType(int jksType) {
		this.jksType = jksType;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getSklxdm() {
		return sklxdm;
	}

	public void setSklxdm(String sklxdm) {
		this.sklxdm = sklxdm;
	}

	public String getSklxmc() {
		return sklxmc;
	}

	public void setSklxmc(String sklxmc) {
		this.sklxmc = sklxmc;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public List getJksList() {
		return jksList;
	}

	public void setJksList(List jksList) {
		this.jksList = jksList;
	}

	public String getSjse() {
		return sjse;
	}

	public void setSjse(String sjse) {
		this.sjse = sjse;
	}

	public String getJsje() {
		return jsje;
	}

	public void setJsje(String jsje) {
		this.jsje = jsje;
	}

	public String getSl() {
		return Sl;
	}

	public void setSl(String sl) {
		Sl = sl;
	}
	

}
