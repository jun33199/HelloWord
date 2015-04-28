/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jwsdmxb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:企业所得税年报
 * </p>
 * 
 * @author wofei
 * @version 1.1
 */

public class JwsdmxbForm2008 extends QysdsNewForm {
	public JwsdmxbForm2008() {

	}
	
	/**
	 * 抵扣方法
	 */
	private String jwsddk;
	
	/**
	 * 直接抵免数组
	 */
	private String[] zjdm_columns = 
		{"ZJDM_L1", "ZJDM_L2", "ZJDM_L3", "ZJDM_L4", "ZJDM_L5", "ZJDM_L6","ZJDM_L7", "ZJDM_L8", "ZJDM_L9", "ZJDM_L10", "ZJDM_L11", "ZJDM_L12", "ZJDM_L13" , "ZJDM_L14", "ZJDM_L15", "ZJDM_L16", "ZJDM_L17"};
	
	/**
	 * 间接抵免数组
	 */
	private String[] jjdm_columns = 
		{"JJDM_L1", "JJDM_L2", "JJDM_L3", "JJDM_L4", "JJDM_L5", "JJDM_L6","JJDM_L7", "JJDM_L8", "JJDM_L9", "JJDM_L10", "JJDM_L11", "JJDM_L12"};
	
	/**
	 * 合计行次
	 * 该数组不同于其他数组
	 * 其他数组以某一列数据组成数组
	 * 该数组以某一行数据组成数组
	 */
	private String[] hj_columns={"hjhc","hjje"};
	
	/**
	 * 直接抵免数据表
	 */
	private List zjdmList = new ArrayList();
	
	/**
	 * 间接抵免数据表
	 */
	private List jjdmList = new ArrayList();
	
	/**
	 * 合计行数据表
	 */
	private List hjList = new ArrayList();
	
	
	
	public String[] getHj_columns() {
		return hj_columns;
	}

	public void setHj_columns(String[] hj_columns) {
		this.hj_columns = hj_columns;
	}

	public String[] getJjdm_columns() {
		return jjdm_columns;
	}

	public void setJjdm_columns(String[] jjdm_columns) {
		this.jjdm_columns = jjdm_columns;
	}

	public String[] getZjdm_columns() {
		return zjdm_columns;
	}

	public void setZjdm_columns(String[] zjdm_columns) {
		this.zjdm_columns = zjdm_columns;
	}

	public List getHjList() {
		return hjList;
	}

	public void setHjList(List hjList) {
		this.hjList = hjList;
	}

	public List getJjdmList() {
		return jjdmList;
	}

	public void setJjdmList(List jjdmList) {
		this.jjdmList = jjdmList;
	}

	public List getZjdmList() {
		return zjdmList;
	}

	public void setZjdmList(List zjdmList) {
		this.zjdmList = zjdmList;
	}

	public String getJwsddk() {
		return jwsddk;
	}

	public void setJwsddk(String jwsddk) {
		this.jwsddk = jwsddk;
	}

}
