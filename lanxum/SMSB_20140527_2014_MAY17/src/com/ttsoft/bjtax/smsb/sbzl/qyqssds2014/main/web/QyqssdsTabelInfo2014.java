package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.main.web;

import java.io.Serializable;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsTabelInfo2014   
 * 类描述：   企业所得税清算申报　上门模块
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:26:57   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:26:57   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsTabelInfo2014 implements Serializable{
	
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
