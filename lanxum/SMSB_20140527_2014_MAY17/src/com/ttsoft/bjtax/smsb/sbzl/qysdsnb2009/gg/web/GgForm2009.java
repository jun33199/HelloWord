/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gg.web;
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

public class GgForm2009 extends QysdsNewForm {
	public GgForm2009() {

	}

	/**
	 * �д� ��Ŀ ��� String[]
	 */
	private String[] gkfzc_columns = { "hc", "je" };

	public String[] getGkfzc_columns() {
		return gkfzc_columns;
	}

	public void setGkfzc_columns(String[] gkfzc_columns) {
		this.gkfzc_columns = gkfzc_columns;
	}

}
