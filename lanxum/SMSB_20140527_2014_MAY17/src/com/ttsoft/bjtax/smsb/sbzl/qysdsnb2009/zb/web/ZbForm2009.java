/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�����˰�걨��</p>
 * @author liwenhua
 * @version 1.1
 */

public class ZbForm2009 extends QysdsNewForm {
	public ZbForm2009() {

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