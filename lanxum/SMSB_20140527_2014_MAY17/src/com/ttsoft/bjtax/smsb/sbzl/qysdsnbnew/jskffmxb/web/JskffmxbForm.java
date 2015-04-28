/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.jskffmxb.web;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author Cao Gang
 * @version 1.1
 */

public class JskffmxbForm extends QysdsNewForm {
	public JskffmxbForm() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次 年度1 年度2 年度3 年度4 本年5 合计 String[]
	 */
	private String[] jskff_columns = { "hc", "nd1", "nd2", "nd3", "nd4", "nd5",
			"nd6" };

	public String[] getJskff_columns() {
		return jskff_columns;
	}

	public void setJskff_columns(String[] jskff_columns) {
		this.jskff_columns = jskff_columns;
	}

}
