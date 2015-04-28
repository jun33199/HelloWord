/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.srmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报</p>
 * @author zhangyj
 * @version 1.1
 */

public class SrmxbForm2009 
 extends QysdsNewForm
 {
	/**
	 * 存放页面数据的字符串数组
	 */
	private String[] sb_columns ={"hc","je"};
	/**
	 * 存放数据结果的LIST
	 */
	 private List srmxb = new ArrayList();

	public String[] getSb_columns() {
		return sb_columns;
	}

	public void setSb_columns(String[] sb_columns) {
		this.sb_columns = sb_columns;
	}

	public List getSrmxb() {
		return srmxb;
	}

	public void setSrmxb(List srmxb) {
		this.srmxb = srmxb;
	} 
	 
	
 }