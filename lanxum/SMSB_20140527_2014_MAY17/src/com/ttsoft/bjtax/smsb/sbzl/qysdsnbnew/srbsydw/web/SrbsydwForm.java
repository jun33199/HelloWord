/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbsydw.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:����һ��������ҵ��λ��������塢������ҵ��λ������Ŀ��ϸ��</p>
 * @author liwenhua
 * @version 1.1
 */

public class SrbsydwForm 
 extends QysdsNewForm
 {
	public SrbsydwForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ���� String[]
	 */
	private String[] srb_columns = { "hc", "je" };	

	public String[] getSrb_columns() {
		return srb_columns;
	}

	public void setSrb_columns(String[] srb_columns) {
		this.srb_columns = srb_columns;
	}
	  
 }