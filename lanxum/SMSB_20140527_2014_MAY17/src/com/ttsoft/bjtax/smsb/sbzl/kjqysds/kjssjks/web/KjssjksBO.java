package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.smsb.model.kjqysds.*;

/**
 * 扣缴企业所得税报告表BO
 * @author Administrator
 *
 */
public class KjssjksBO implements Serializable{

	public KjssjksBO() {
	}
	/**
	 * 合同名称
	 */
	private String htmc;
	/**
	 * 合同编号
	 */
	private String htbh;
	/**
	 * 税种税目代码
	 */
	private String szsmdm;
	/**
	 * 税种名称_企业所得税
	 */
	private String szmc;
	/**
	 * 税种代码_30
	 */
	private String szdm;
	/**
	 * 纳税项目名称_税种税目名称
	 */
	private String szsmmc;
	/**
	 * 税款所属开始日期
	 */
	private String skssksrq;
	/**
	 * 税款所属结束日期
	 */
	private String skssjsrq;
	/**
	 * 实际缴税额
	 */
	private String sjse;
	/**
	 * 计税金额
	 */
	private String jsje;
	/**
	 * 税率
	 */
	private String Sl;
	/**
	 * 税款申报日期
	 */
	private String sbrq;
	/**
	 * 计算机代码
	 */
	private String jsjdm;
    /**
     * 纳税人名称
     */
    private String nsrmc;
	/**
	 * 录入人员
	 */
	private String lrr;
	/**
	 * 备案登记序号
	 */
	private String badjxh;

   /**
    * 报告表序号
    */
   private String bgbxh;
   /**
    * 明晰数据列表
    */
   private List dataList = new ArrayList();
   /**
    * 银行名称
    */
   private String yhmc;

   /**
    * 银行代码
    */
   private String yhdm;

   /**
    * 账号
    */
   private String zh;
   /**
    * 缴款书类型
    */
   private int jksType;
   /**
    * 税款类型代码
    */
   private String sklxdm;

   /**
    * 税款类型名称
    */
   private String sklxmc;
   /**
    * 申报编号
    */
   private String sbbh;
   /**
    * 生成缴款书List
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
