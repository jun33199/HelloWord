/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbybqy.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author ����ϼ
 * @version 1.1
 */

public class SrbybqyForm 
 extends QysdsNewForm
 {
	/**
	 * ���ҳ�����ݵ��ַ�������
	 */
	private String[] sb_columns ={"hc","je"};
	/**
	 * ������ݽ����LIST
	 */
	 private List srbybqy = new ArrayList();

	public String[] getSb_columns() {
		return sb_columns;
	}

	public void setSb_columns(String[] sb_columns) {
		this.sb_columns = sb_columns;
	}

	public List getSrbybqy() {
		return srbybqy;
	}

	public void setSrbybqy(List srbybqy) {
		this.srbybqy = srbybqy;
	} 
	 
	
 }