package com.ttsoft.bjtax.smsb.jmssb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统
 * <p>Description: 上门申报-补充申报资料录入-安置残疾人就业企业营业税减免税查询统计表
 * <p>Copyright: Copyright (c) 2005
 * <p>Company: SYAX
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmscxtjForm extends BaseForm {
	
	/**
	 * 查询计算机代码
	 */
	private String queryJsjdm;
	
	/**
	 * 查询企业名称
	 */
	private String queryQymc;
	
	/**
	 * 查询主管税务机关代码
	 */
	private String queryZgswjgdm;
	
	/**
	 * 查询起始日期
	 */
	private String queryCxqsrq;
	
	/**
	 * 查询截止日期
	 */
	private String queryCxjzrq;
	
	/**
	 * 显示计算机代码
	 */
	private String showJsjdm;
	
	/**
	 * 显示企业名称
	 */
	private String showQymc;
	
	/**
	 * 查询后得到的要显示结果集
	 */
	private List dataList = new ArrayList();
	
	/**
	 * 年减征营业税限额合计
	 */
	private String njzyysxeTotal;
	
	/**
	 * 实际减征营业税额合计
	 */
	private String sjjzyyseTotal;
	
	/**
	 * 安置残疾职工人数合计
	 */
	private String azcjzgrsTotal;
	
	/**
	  * 录入人税务机关组织机构代码
	  */
	private String headSwjgzzjgdm;
	
	/**
	  * 税务所列表容器
	  */
	private List zgswjgList = new ArrayList();
	
	/**
	  * 页面显示尺寸
	  */
	private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

	/**
	  * 页码
	  */
	private String nextPage = "1";

	/**
	  * 页数
	  */
	private String totalpage = "0";

	/**
	  * 页面提示信息
	  */
	private String message;

	/**
	  * 当前页码
	  */
	private String curPage;
	

	public String getQueryJsjdm() {
		return queryJsjdm;
	}

	public void setQueryJsjdm(String queryJsjdm) {
		this.queryJsjdm = queryJsjdm;
	}

	public String getQueryQymc() {
		return queryQymc;
	}

	public void setQueryQymc(String queryQymc) {
		this.queryQymc = queryQymc;
	}

	public String getQueryZgswjgdm() {
		return queryZgswjgdm;
	}

	public void setQueryZgswjgdm(String queryZgswjgdm) {
		this.queryZgswjgdm = queryZgswjgdm;
	}

	public String getQueryCxqsrq() {
		return queryCxqsrq;
	}

	public void setQueryCxqsrq(String queryCxqsrq) {
		this.queryCxqsrq = queryCxqsrq;
	}

	public String getQueryCxjzrq() {
		return queryCxjzrq;
	}

	public void setQueryCxjzrq(String queryCxjzrq) {
		this.queryCxjzrq = queryCxjzrq;
	}

	public String getShowJsjdm() {
		return showJsjdm;
	}

	public void setShowJsjdm(String showJsjdm) {
		this.showJsjdm = showJsjdm;
	}

	public String getShowQymc() {
		return showQymc;
	}

	public void setShowQymc(String showQymc) {
		this.showQymc = showQymc;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getNjzyysxeTotal() {
		return njzyysxeTotal;
	}

	public void setNjzyysxeTotal(String njzyysxeTotal) {
		this.njzyysxeTotal = njzyysxeTotal;
	}

	public String getSjjzyyseTotal() {
		return sjjzyyseTotal;
	}

	public void setSjjzyyseTotal(String sjjzyyseTotal) {
		this.sjjzyyseTotal = sjjzyyseTotal;
	}

	public String getAzcjzgrsTotal() {
		return azcjzgrsTotal;
	}

	public void setAzcjzgrsTotal(String azcjzgrsTotal) {
		this.azcjzgrsTotal = azcjzgrsTotal;
	}

	public String getHeadSwjgzzjgdm() {
		return headSwjgzzjgdm;
	}

	public void setHeadSwjgzzjgdm(String headSwjgzzjgdm) {
		this.headSwjgzzjgdm = headSwjgzzjgdm;
	}

	public List getZgswjgList() {
		return zgswjgList;
	}

	public void setZgswjgList(List zgswjgList) {
		this.zgswjgList = zgswjgList;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	
}
