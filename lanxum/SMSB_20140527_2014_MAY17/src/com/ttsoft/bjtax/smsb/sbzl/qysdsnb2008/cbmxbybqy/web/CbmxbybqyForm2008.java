/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:附表二（１）成本费用明细表</p>
 * @author liwenhua
 * @version 1.1
 */

public class CbmxbybqyForm2008 
  extends QysdsNewForm
  {
	public CbmxbybqyForm2008() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、金额 String[]
	 */
	private String[] cbmxb_columns = { "hc", "je" };	

	public String[] getColumns() {
		return cbmxb_columns;
	}

	public void setColumns(String[] srb_columns) {
		this.cbmxb_columns = srb_columns;
	}
  }