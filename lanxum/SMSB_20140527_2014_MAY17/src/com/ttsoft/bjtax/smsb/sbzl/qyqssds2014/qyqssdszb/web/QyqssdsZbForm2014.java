package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdszb.web;


import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsZbForm2014   
 * ��������    �л����񹲺͹���ҵ��������˰�걨��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:25:43   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:25:43   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsZbForm2014 extends QyqssdsBaseForm {


	public QyqssdsZbForm2014() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ��ۼƽ�� String[]
	 */
	private String[] zb_columns = { "hc", "ljje" };

	public String[] getColumns() {
		return zb_columns;
	}

	public void setColumns(String[] zb_columns) {
		this.zb_columns = zb_columns;
	}

}