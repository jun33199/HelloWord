/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ��������޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author Cao Gang
 * @version 1.1
 */

public class JskffmxbForm extends QysdsNewForm {
	public JskffmxbForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �д� ���1 ���2 ���3 ���4 ����5 �ϼ� String[]
	 */
	private String[] jskff_columns = { "hc", "nd1", "nd2", "nd3", "nd4", "nd5",
			"nd6" };

	public String[] getJskff_columns() {
		return jskff_columns;
	}

	public void setJskff_columns(String[] jskff_columns) {
		this.jskff_columns = jskff_columns;
	}

}
