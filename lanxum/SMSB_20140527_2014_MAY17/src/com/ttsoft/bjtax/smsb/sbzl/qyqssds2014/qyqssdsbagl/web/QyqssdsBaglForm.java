package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

public class QyqssdsBaglForm extends QyqssdsBaseForm {

	// ˰�������֯��������
	private String swjgzzjgdm;
	// ����״̬����
	private String sqzt;
	// �������ʹ��� 0���������룬1����������
	private String sqlxdm;

	// ��ѯ���������������
	private String filter_jsjdm;
	// ��ѯ��������˰������
	private String filter_nsrmc;
	// ��ѯ�������������
	private String filter_band;
	// ��ѯ��������������
	private String filter_sqlx;
	// ��ѯ����������״̬
	private String filter_sqzt;
	// ��ѯ��������������˰�����
	private String filter_zgswjg;
	// ��ѯ��������������˰������б�
	private List filter_zgswjgList;
	// �������������������
	private String add_jsjdm;
	// ��������
	private String czlx;

	private String rowsPerPage;//��¼��ûҳ��¼��
	private String currentPage;//��ؼ�¼��ǰ����ҳ
	private boolean isExistedBa;//������Ϣ�Ƿ��Ѿ�����
	
	
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSqzt() {
		return sqzt;
	}

	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}

	public String getSqlxdm() {
		return sqlxdm;
	}

	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}

	public String getFilter_jsjdm() {
		return filter_jsjdm;
	}

	public void setFilter_jsjdm(String filter_jsjdm) {
		this.filter_jsjdm = filter_jsjdm;
	}

	public String getFilter_nsrmc() {
		return filter_nsrmc;
	}

	public void setFilter_nsrmc(String filter_nsrmc) {
		this.filter_nsrmc = filter_nsrmc;
	}

	public String getFilter_band() {
		return filter_band;
	}

	public void setFilter_band(String filter_band) {
		this.filter_band = filter_band;
	}

	public String getFilter_sqlx() {
		return filter_sqlx;
	}

	public void setFilter_sqlx(String filter_sqlx) {
		this.filter_sqlx = filter_sqlx;
	}

	public String getFilter_sqzt() {
		return filter_sqzt;
	}

	public void setFilter_sqzt(String filter_sqzt) {
		this.filter_sqzt = filter_sqzt;
	}

	public String getFilter_zgswjg() {
		return filter_zgswjg;
	}

	public void setFilter_zgswjg(String filter_zgswjg) {
		this.filter_zgswjg = filter_zgswjg;
	}

	public List getFilter_zgswjgList() {
		return filter_zgswjgList;
	}

	public void setFilter_zgswjgList(List filter_zgswjgList) {
		this.filter_zgswjgList = filter_zgswjgList;
	}

	public String getAdd_jsjdm() {
		return add_jsjdm;
	}

	public void setAdd_jsjdm(String add_jsjdm) {
		this.add_jsjdm = add_jsjdm;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public boolean getIsExistedBa(){
		return isExistedBa;
	}
	public void setIsExistedBa(boolean isExistedBa){
		this.isExistedBa = isExistedBa;
	}

}
