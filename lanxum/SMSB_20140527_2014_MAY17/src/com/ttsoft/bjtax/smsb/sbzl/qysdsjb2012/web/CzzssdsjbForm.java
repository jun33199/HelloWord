package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2012.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2008查帐征收企业所得税季报</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class CzzssdsjbForm  extends QysdsNewForm{
	
	/**
	 * 所得税年报列表标题项目代码 行次、本期数、累计数
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje" };

	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsjbList = new ArrayList();
	
	private String SAVE_FLAG="0";//0-初始值,1-保存成功	
	
	private String xzqy;
	
	//一般减免税率
	private String ybjmsl;
	
	//减免资格
	private String jmzg;
	
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}

	//获取数据库中的纳税方法和总分机构的域值
	private HashMap nsfs_fsjg = new HashMap();
	
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
		return hdjb_columns;
	}

	public void setColumns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public List getQysdsjbList() {
		return qysdsjbList;
	}

	public void setQysdsjbList(List qysdsjbList) {
		this.qysdsjbList = qysdsjbList;
	}

	/**
	 * @return Returns the sAVE_FLAG.
	 */
	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	/**
	 * @param save_flag The sAVE_FLAG to set.
	 */
	public void setSAVE_FLAG(String save_flag) {
		SAVE_FLAG = save_flag;
	}

}
