/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jwsdmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:��ҵ����˰�걨
 * </p>
 * 
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JwsdmxbForm extends QysdsNewForm {
	public JwsdmxbForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ��� String[]
	 */
	private String[] jwsd_columns = {"L1", "L2", "L3", "L4", "L5", "L6",
			"L7", "L8", "L9", "L10", "L11", "L12", "L13" };

	private String[] add_columns = { "addL1", "addL2", "addL3", "addL4", "addL5", "addL6", "addL7",
			"addL8", "addL9", "addL10", "addL11", "addL12", "addL13" };
	
	/**
     * ������������˰��ֿ۷���
     */
    private String jwsddk;
    
	private List addlist = new ArrayList();
	
	public String[] getColumns() {
		return jwsd_columns;
	}

	public void setColumns(String[] jwsd_columns) {
		this.jwsd_columns = jwsd_columns;
	}

	public String[] getAdd_columns() {
		return add_columns;
	}

	public void setAdd_columns(String[] add_columns) {
		this.add_columns = add_columns;
	}

	public List getAddlist() {
		return addlist;
	}

	public void setAddlist(List addlist) {
		this.addlist = addlist;
	}

	public String getJwsddk() {
		return jwsddk;
	}

	public void setJwsddk(String jwsddk) {
		this.jwsddk = jwsddk;
	}

	

}
