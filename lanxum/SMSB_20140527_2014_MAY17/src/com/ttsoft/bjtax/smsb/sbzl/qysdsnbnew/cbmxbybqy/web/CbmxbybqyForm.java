/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbybqy.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:������������ɱ�������ϸ��</p>
 * @author liwenhua
 * @version 1.1
 */

public class CbmxbybqyForm 
  extends QysdsNewForm
  {
	public CbmxbybqyForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ���� String[]
	 */
	private String[] cbmxb_columns = { "hc", "je" };	

	public String[] getColumns() {
		return cbmxb_columns;
	}

	public void setColumns(String[] srb_columns) {
		this.cbmxb_columns = srb_columns;
	}
  }