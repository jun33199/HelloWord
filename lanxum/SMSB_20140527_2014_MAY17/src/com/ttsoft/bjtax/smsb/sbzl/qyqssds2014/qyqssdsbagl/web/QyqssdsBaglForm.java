package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsbagl.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

public class QyqssdsBaglForm extends QyqssdsBaseForm {

	// 税务机关组织机构代码
	private String swjgzzjgdm;
	// 申请状态代码
	private String sqzt;
	// 申请类型代码 0：网上申请，1：上门申请
	private String sqlxdm;

	// 查询条件：计算机代码
	private String filter_jsjdm;
	// 查询条件：纳税人名称
	private String filter_nsrmc;
	// 查询条件：备案年度
	private String filter_band;
	// 查询条件：申请类型
	private String filter_sqlx;
	// 查询条件：申请状态
	private String filter_sqzt;
	// 查询条件：所属主管税务机关
	private String filter_zgswjg;
	// 查询条件：所属主管税务机关列表
	private List filter_zgswjgList;
	// 新增条件：计算机代码
	private String add_jsjdm;
	// 操作类型
	private String czlx;

	private String rowsPerPage;//记录中没页记录数
	private String currentPage;//监控记录当前所在页
	private boolean isExistedBa;//备案信息是否已经存在
	
	
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
