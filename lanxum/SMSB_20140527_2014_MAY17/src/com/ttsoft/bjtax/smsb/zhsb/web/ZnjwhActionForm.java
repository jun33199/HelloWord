package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class ZnjwhActionForm extends BaseForm{
	
	  /**
	   * ���������
	   */
	  private String jsjdm;
	  
	  /**
	   * ��ʼ����
	   */
	  private String qsrq;
	  
	  /**
	   * ��ֹ����
	   */
	  private String jzrq;
	  
	  //������Ա¼��ϼ�
	  //ϵͳ����ϼ�
	  /**
	   * ��ϸ�����б�
	   */
	  private List dataList = new ArrayList();

	  /**
	   * �ɿ�ƾ֤��
	   */
	  private String jkpzhStr;

	  /**
	   * ������Դ
	   */
	  private String sjly;

	  /**
	   * �걨���
	   */
	  private String sbbh;

	  /**
	   * Ԥ�趨�걨���
	   */
	  private String presbbh;

	  /**
	   * �ɿ������� һƱһ˰��һƱ��˰
	   * added by qianchao 2005.10.27
	   */
	  private int jksType;
	  
	  /**
	   * ��˰������
	   */
	  private String nsrmc;

	  /**
	   * �걨����
	   */
	  private String sbrq; //�걨����

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
