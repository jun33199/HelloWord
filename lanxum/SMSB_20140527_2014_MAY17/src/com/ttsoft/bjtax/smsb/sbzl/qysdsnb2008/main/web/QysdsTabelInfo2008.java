package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.main.web;

import java.io.Serializable;

public class QysdsTabelInfo2008 implements Serializable{
	
	/**
	 * 表ID
	 */
	private String tableID ;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 各表的Action URL
	 */
	private String URL;
	
	/**
	 * 单表审核结果
	 * 0：表示未通过审核
	 * 1：表示已通过单表审核
	 */
	private String checkFlag;
	
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getTableID() {
		return tableID;
	}

	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String url) {
		URL = url;
	}
	
	
}
