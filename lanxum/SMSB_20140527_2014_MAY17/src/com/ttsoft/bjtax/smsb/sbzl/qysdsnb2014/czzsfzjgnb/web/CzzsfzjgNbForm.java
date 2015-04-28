package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:2012 企业所得税分支机构年度纳税申报表</p>
 * @author wangcy
 * 
 * @version 1.0
 */
public class CzzsfzjgNbForm  extends QysdsNewForm{
	
	/**
	 * 所得税年报列表标题项目代码 行次、本期数、累计数
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje" };

	/**
	 * 用于存储明细中具体数值 List
	 */
	private List qysdsnbList = new ArrayList();
	
	private String SAVE_FLAG="0";//0-初始值,1-保存成功	
	
	private String xzqy;
	
	//一般减免税率
	private String ybjmsl;
	
	//减免资格
	private String jmzg;
	
	//税源标识
	private String sybs;
	
	//纳税方式
	private String nsfs;
	//总分机构
	private String zfjg;
	//是否执行了查询   否则查询不出最新的税源标识 1 代表查询了
	private String isQuery;
	//用于标识是否有保存过数据 ，根据不同的标识走不同的方法
	private String queryFlag;
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

	public List getQysdsnbList() {
		return qysdsnbList;
	}

	public void setQysdsnbList(List qysdsnbList) {
		this.qysdsnbList = qysdsnbList;
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

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	
}
