package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class ZnjwhActionForm extends BaseForm{
	
	  /**
	   * 计算机代码
	   */
	  private String jsjdm;
	  
	  /**
	   * 起始日期
	   */
	  private String qsrq;
	  
	  /**
	   * 截止日期
	   */
	  private String jzrq;
	  
	  //操作人员录入合计
	  //系统计算合计
	  /**
	   * 明细数据列表
	   */
	  private List dataList = new ArrayList();

	  /**
	   * 缴款凭证号
	   */
	  private String jkpzhStr;

	  /**
	   * 数据来源
	   */
	  private String sjly;

	  /**
	   * 申报编号
	   */
	  private String sbbh;

	  /**
	   * 预设定申报编号
	   */
	  private String presbbh;

	  /**
	   * 缴款书类型 一票一税、一票多税
	   * added by qianchao 2005.10.27
	   */
	  private int jksType;
	  
	  /**
	   * 纳税人名称
	   */
	  private String nsrmc;

	  /**
	   * 申报日期
	   */
	  private String sbrq; //申报日期

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getJkpzhStr() {
		return jkpzhStr;
	}

	public void setJkpzhStr(String jkpzhStr) {
		this.jkpzhStr = jkpzhStr;
	}

	public int getJksType() {
		return jksType;
	}

	public void setJksType(int jksType) {
		this.jksType = jksType;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJzrq() {
		return jzrq;
	}

	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}

	public String getPresbbh() {
		return presbbh;
	}

	public void setPresbbh(String presbbh) {
		this.presbbh = presbbh;
	}

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
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

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}


}
