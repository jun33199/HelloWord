/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.web;



import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author cao gang
 * @version 1.1
 */

public class MssdmxbForm extends QysdsNewForm {
	public MssdmxbForm() {
		
	}
	
	/**
	 * �������Ŀ���� String[]
	 */
	private String[] mssd_columns = {"hc","je"};

	public String[] getMssd_columns() {
		return mssd_columns;
	}

	public void setMssd_columns(String[] mssd_columns) {
		this.mssd_columns = mssd_columns;
	}

}
