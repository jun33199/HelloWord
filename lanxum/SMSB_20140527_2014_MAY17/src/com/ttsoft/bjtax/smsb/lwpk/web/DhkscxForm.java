package com.ttsoft.bjtax.smsb.lwpk.web;

import java.math.BigDecimal;
import java.util.List;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.framework.form.BaseForm;
/**
 *批量扣款查询 form
 * 201307
 * kanght
 */
public class DhkscxForm extends BaseForm{

	//计算机代码
	private String jsjdm;
	//年度
	private String nd;
	//扣款起始月份
	private String kkqsyf;
	//扣款终止月份
	private String kkzzyf;
	//基本数据model
	private PKJBSJModel pkjbsjModel;
	//单户查询model
	private List plkkdhcxlist;
	//总条数
	private int zts;
	/**
	   * 页面显示尺寸
	   */
	  private String pageSize = String.valueOf(CodeConstant.SMSB_PK_PG_LENGTH);

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
	  
	  
	public int getZts() {
		return zts;
	}
	public void setZts(int zts) {
		this.zts = zts;
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
	public List getPlkkdhcxlist() {
		return plkkdhcxlist;
	}
	public void setPlkkdhcxlist(List plkkdhcxlist) {
		this.plkkdhcxlist = plkkdhcxlist;
	}
	
	public PKJBSJModel getPkjbsjModel() {
		return pkjbsjModel;
	}
	public void setPkjbsjModel(PKJBSJModel pkjbsjModel) {
		this.pkjbsjModel = pkjbsjModel;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getKkqsyf() {
		return kkqsyf;
	}
	public void setKkqsyf(String kkqsyf) {
		this.kkqsyf = kkqsyf;
	}
	public String getKkzzyf() {
		return kkzzyf;
	}
	public void setKkzzyf(String kkzzyf) {
		this.kkzzyf = kkzzyf;
	}
	
}
