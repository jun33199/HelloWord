package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.main.web;

import java.io.Serializable;

public class QysdsTabelInfo2008 implements Serializable{
	
	/**
	 * ��ID
	 */
	private String tableID ;
	
	/**
	 * ����
	 */
	private String tableName;
	
	/**
	 * �����Action URL
	 */
	private String URL;
	
	/**
	 * ������˽��
	 * 0����ʾδͨ�����
	 * 1����ʾ��ͨ���������
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
