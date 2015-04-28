/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.mssdmxb.web;



import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author cao gang
 * @version 1.1
 */

public class MssdmxbForm extends QysdsNewForm {
	public MssdmxbForm() {
		
	}
	
	/**
	 * 表标题项目代码 String[]
	 */
	private String[] mssd_columns = {"hc","je"};

	public String[] getMssd_columns() {
		return mssd_columns;
	}

	public void setMssd_columns(String[] mssd_columns) {
		this.mssd_columns = mssd_columns;
	}

}
