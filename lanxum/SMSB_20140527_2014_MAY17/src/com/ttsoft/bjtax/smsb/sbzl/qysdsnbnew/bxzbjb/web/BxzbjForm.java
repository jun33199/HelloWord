/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web;

import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class BxzbjForm extends QysdsNewForm {
	
	/**
	 * �̶���������
	 */
	private String[] gd_Colums={"hc","sjfse","sfgdkce","tze"};
	
	/**
	 * ����׼���� ����������
	 */
	private String[] qtzbj_Columns={"qtzbj_xm","qtzbj_sjfse","qtzbj_sfgdkce","qtzbj_tze"};
	
	/**
	 * ���� ����������
	 */
	private String[] qt_Columns={"qt_xm","qt_sjfse","qt_sfgdkce","qt_tze"};
	
	/**
	 * ����׼���� ����������
	 */
	private List qtzbjList=new ArrayList();
	
	/**
	 * ���� ����������
	 */
	private List qtList=new ArrayList();
	
	public String[] getGd_Colums() {
		return gd_Colums;
	}

	public void setGd_Colums(String[] gd_Colums) {
		this.gd_Colums = gd_Colums;
	}

	public String[] getQt_Columns() {
		return qt_Columns;
	}

	public void setQt_Columns(String[] qt_Columns) {
		this.qt_Columns = qt_Columns;
	}

	public String[] getQtzbj_Columns() {
		return qtzbj_Columns;
	}

	public void setQtzbj_Columns(String[] qtzbj_Columns) {
		this.qtzbj_Columns = qtzbj_Columns;
	}

	public List getQtList() {
		return qtList;
	}

	public void setQtList(List qtList) {
		this.qtList = qtList;
	}

	public List getQtzbjList() {
		return qtzbjList;
	}

	public void setQtzbjList(List qtzbjList) {
		this.qtzbjList = qtzbjList;
	}

}
