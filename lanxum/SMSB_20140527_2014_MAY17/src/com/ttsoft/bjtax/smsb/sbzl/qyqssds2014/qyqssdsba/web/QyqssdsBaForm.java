package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

public class QyqssdsBaForm extends QyqssdsBaseForm{
	/**
	 * ��˰�˼�������� String
	 */
	private String jsjdm;

	/**
	 * ��˰������ String
	 */
	private String nsrmc;
	/**
	 * ��˰��ʶ���
	 */
	private String nsrsbh;
	/**
	 * ������������-�Ǽ�ע�����ʹ��� String
	 */
	private String ssjjlxdm = "";

	/**
	 * ������������-�Ǽ�ע���������� String
	 */
	private String ssjjlxmc = "";
	
	/**
	 * ������ҵ����
	 */
	private String sshydm = "";

	/**
	 * ������ҵ����
	 */
	private String sshymc = "";
	/**
	 * ϵͳ���� String
	 */
	
	private String xtjb;
	
	/**
	 * ���������� String
	 */
	
	private String bbmsf;
	/**
	 * �Ƿ�ִ���˲�ѯ������ ��ֹ��ѯһ�κ��ڽ����޸�û�н��в�ѯҲ���Ա���
	 * ������һ��־ ����ֹ����ļ�������벻�� ���һ����Ա�֤ʱʱȡ��˰Դ��ʶ
	 * 1 �����ѯ  
	 */
	private String isQuery;
	/**
	 * �걨���� 0�����ϣ�1������
	 */
	private String sqlx;
	private String czlx;//��������
	private String saveSuccess;
	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getSsjjlxdm() {
		return ssjjlxdm;
	}
	public void setSsjjlxdm(String ssjjlxdm) {
		this.ssjjlxdm = ssjjlxdm;
	}
	public String getSsjjlxmc() {
		return ssjjlxmc;
	}
	public void setSsjjlxmc(String ssjjlxmc) {
		this.ssjjlxmc = ssjjlxmc;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}
	public String getSshymc() {
		return sshymc;
	}
	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}
	public String getXtjb() {
		return xtjb;
	}
	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}
	public String getBbmsf() {
		return bbmsf;
	}
	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	public String getSaveSuccess() {
		return saveSuccess;
	}
	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	
}
