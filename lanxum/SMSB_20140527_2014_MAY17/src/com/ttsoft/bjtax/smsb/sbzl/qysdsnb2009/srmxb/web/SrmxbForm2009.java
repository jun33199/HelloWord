/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author zhangyj
 * @version 1.1
 */

public class SrmxbForm2009 
 extends QysdsNewForm
 {
	/**
	 * ���ҳ�����ݵ��ַ�������
	 */
	private String[] sb_columns ={"hc","je"};
	/**
	 * ������ݽ����LIST
	 */
	 private List srmxb = new ArrayList();

	public String[] getSb_columns() {
		return sb_columns;
	}

	public void setSb_columns(String[] sb_columns) {
		this.sb_columns = sb_columns;
	}

	public List getSrmxb() {
		return srmxb;
	}

	public void setSrmxb(List srmxb) {
		this.srmxb = srmxb;
	} 
	 
	
 }