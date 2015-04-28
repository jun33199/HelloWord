/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.zb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年度纳税申报表</p>
 * @author liwenhua
 * @version 1.1
 */

public class ZbForm2009 extends QysdsNewForm {
	public ZbForm2009() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、累计金额 String[]
	 */
	private String[] zb_columns = { "hc", "ljje" };

	public String[] getColumns() {
		return zb_columns;
	}

	public void setColumns(String[] zb_columns) {
		this.zb_columns = zb_columns;
	}

}