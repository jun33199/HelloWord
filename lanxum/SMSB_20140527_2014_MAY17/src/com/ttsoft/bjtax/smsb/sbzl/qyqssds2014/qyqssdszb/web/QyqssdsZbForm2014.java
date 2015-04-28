package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web;


import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

/**
 * 
 * 项目名称：smsb   
 * 类名称：QyqssdsZbForm2014   
 * 类描述：    中华人民共和国企业清算所得税申报表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:25:43   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:25:43   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsZbForm2014 extends QyqssdsBaseForm {


	public QyqssdsZbForm2014() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、累计金额 String[]
	 */
	private String[] zb_columns = { "hc", "ljje" };

	public String[] getColumns() {
		return zb_columns;
	}

	public void setColumns(String[] zb_columns) {
		this.zb_columns = zb_columns;
	}

}