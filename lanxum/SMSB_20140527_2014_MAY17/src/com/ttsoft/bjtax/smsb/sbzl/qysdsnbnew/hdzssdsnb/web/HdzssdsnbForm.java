/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.hdzssdsnb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:�˶�������ҵ����˰�걨
 * </p>
 * 
 * @author Li Wenhua
 * @version 1.1
 */

public class HdzssdsnbForm extends QysdsNewForm  {
	public HdzssdsnbForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ��� String[]
	 */
	private String[] hdnb_columns = { "hc", "bqs", "ljs" };

	
	//
	private String xzqy;
	
	//һ�����˰��
	private String ybjmsl;
	
	//�����ʸ�
	private String jmzg;

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.ybjmsl = ybjmsl;
	}

	public String[] getColumns() {
		return hdnb_columns;
	}

	public void setColumns(String[] hdnb_columns) {
		this.hdnb_columns = hdnb_columns;
	}
}
