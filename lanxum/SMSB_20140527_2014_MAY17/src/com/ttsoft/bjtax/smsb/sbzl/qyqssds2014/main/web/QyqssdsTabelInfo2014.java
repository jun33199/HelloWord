package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.web;

import java.io.Serializable;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsTabelInfo2014   
 * ��������   ��ҵ����˰�����걨������ģ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:26:57   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:26:57   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsTabelInfo2014 implements Serializable{
	
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
