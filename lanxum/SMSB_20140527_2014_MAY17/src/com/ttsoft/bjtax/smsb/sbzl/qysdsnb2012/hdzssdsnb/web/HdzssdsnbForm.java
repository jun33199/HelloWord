/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.hdzssdsnb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:2012核定征收企业所得税年报
 * </p>
 * 
 * @author gaoyh
 * @version 1.1
 */

public class HdzssdsnbForm extends QysdsNewForm {
	public HdzssdsnbForm() {

	}

	/**
	 * 所得税年报列表标题项目代码 行次、本期数、累计数 String[]
	 */
	private String[] hdnb_columns = { "hc", "lje" };

	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsnbList = new ArrayList();

	// 乡镇企业
	private String xzqy;

	// 一般减免税率
	private String ybjmsl;

	// 减免资格
	private String jmzg;

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}

	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}

	public void setYbjmsl(String ybjmsl) {
		this.xzqy = ybjmsl;
	}

	public String[] getColumns() {
		return hdnb_columns;
	}

	public void setColumns(String[] hdnb_columns) {
		this.hdnb_columns = hdnb_columns;
	}

	public List getQysdsnbList() {
		return qysdsnbList;
	}

	public void setQysdsnbList(List qysdsnbList) {
		this.qysdsnbList = qysdsnbList;
	}

}
