package com.ttsoft.bjtax.smsb.zjyjmsb.cx.web;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.*;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 再就业减免税申报查询Form</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjyjmcxForm extends BaseForm{
	public ZjyjmcxForm(){
		
	}
	
	  /**
	   * 明细项目集合
	   */
	  private ArrayList dataList;

	  /**
	   * 区县分局列表
	   */
	  private List fjList ;
	  /**
	   * 税务所列表
	   */
	  private List swsList;
	  /**
	   * 纳税人名称
	   */
	  private String nsrmc;
	  /**
	   * 计算机代码
	   */
	  private String jsjdm;
	  /**
	   * 查询分局
	   */
	  private String queryfj;

	  /**
	   * 查询税务所
	   */
	  private String querysws;
	  /**
	   * 主管税务机关代码
	   */
	  private String swjgdm;

	  /**
	   * 主管税务所代码
	   */
	  private String swsdm;
	  /**
	   * 减免税类别list
	   */
	  private List jmslblist;
	  /**
	   * 减免税种List
	   */
	  private List jmszArray;
	  /**
	   * 季度List
	   */
	  private List jdlist;
	  /**
	   * 年度
	   */
	  private String nd;
	  /**
	   * 选中的减免税类别
	   */
	  private String[] jmslb;
	  /**
	   * 选中的减免税种
	   */
	  private String jmsz;
	  /**
	   * 季度
	   */
	  private String jd;
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
	public java.util.ArrayList getDataList() {
		return dataList;
	}
	public void setDataList(java.util.ArrayList dataList) {
		this.dataList = dataList;
	}
	public List getFjList() {
		return fjList;
	}
	public void setFjList(List fjList) {
		this.fjList = fjList;
	}
	public String[] getJmslb() {
		return jmslb;
	}
	public void setJmslb(String[] jmslb) {
		this.jmslb = jmslb;
	}
	public String getJmsz() {
		return jmsz;
	}
	public void setJmsz(String jmsz) {
		this.jmsz = jmsz;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public List getSwsList() {
		return swsList;
	}
	public void setSwsList(List swsList) {
		this.swsList = swsList;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getQueryfj() {
		return queryfj;
	}
	public void setQueryfj(String queryfj) {
		this.queryfj = queryfj;
	}
	public String getQuerysws() {
		return querysws;
	}
	public void setQuerysws(String querysws) {
		this.querysws = querysws;
	}
	public String getSwjgdm() {
		return swjgdm;
	}
	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}
	public String getSwsdm() {
		return swsdm;
	}
	public void setSwsdm(String swsdm) {
		this.swsdm = swsdm;
	}
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public List getJmslblist() {
		return jmslblist;
	}
	public void setJmslblist(List jmslblist) {
		this.jmslblist = jmslblist;
	}
	public List getJmszArray() {
		return jmszArray;
	}
	public void setJmszArray(List jmszArray) {
		this.jmszArray = jmszArray;
	}
	public List getJdlist() {
		return jdlist;
	}
	public void setJdlist(List jdlist) {
		this.jdlist = jdlist;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
}
