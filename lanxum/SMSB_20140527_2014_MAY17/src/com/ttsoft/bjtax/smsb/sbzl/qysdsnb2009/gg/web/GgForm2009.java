/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gg.web;
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

public class GgForm2009 extends QysdsNewForm {
	public GgForm2009() {

	}

	/**
	 * 行次 项目 金额 String[]
	 */
	private String[] gkfzc_columns = { "hc", "je" };

	public String[] getGkfzc_columns() {
		return gkfzc_columns;
	}

	public void setGkfzc_columns(String[] gkfzc_columns) {
		this.gkfzc_columns = gkfzc_columns;
	}

}
