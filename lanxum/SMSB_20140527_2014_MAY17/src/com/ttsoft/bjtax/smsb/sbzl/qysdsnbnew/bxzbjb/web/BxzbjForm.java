/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web;

import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class BxzbjForm extends QysdsNewForm {
	
	/**
	 * 固定行列内容
	 */
	private String[] gd_Colums={"hc","sjfse","sfgdkce","tze"};
	
	/**
	 * 其他准备金 子行列内容
	 */
	private String[] qtzbj_Columns={"qtzbj_xm","qtzbj_sjfse","qtzbj_sfgdkce","qtzbj_tze"};
	
	/**
	 * 其他 子行列内容
	 */
	private String[] qt_Columns={"qt_xm","qt_sjfse","qt_sfgdkce","qt_tze"};
	
	/**
	 * 其他准备金 子行列内容
	 */
	private List qtzbjList=new ArrayList();
	
	/**
	 * 其他 子行列内容
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
