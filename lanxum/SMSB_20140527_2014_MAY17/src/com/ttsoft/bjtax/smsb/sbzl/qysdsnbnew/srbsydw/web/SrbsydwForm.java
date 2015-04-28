/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.srbsydw.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:附表一（３）事业单位、社会团体、民办非企业单位收入项目明细表</p>
 * @author liwenhua
 * @version 1.1
 */

public class SrbsydwForm 
 extends QysdsNewForm
 {
	public SrbsydwForm() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、金额 String[]
	 */
	private String[] srb_columns = { "hc", "je" };	

	public String[] getSrb_columns() {
		return srb_columns;
	}

	public void setSrb_columns(String[] srb_columns) {
		this.srb_columns = srb_columns;
	}
	  
 }