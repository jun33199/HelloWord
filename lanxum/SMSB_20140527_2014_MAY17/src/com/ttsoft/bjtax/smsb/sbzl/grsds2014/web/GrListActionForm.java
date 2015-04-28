/**
 * @Title:       GrListActionForm.java
 * @Description: TODO
 * @date:        2014-12-10下午02:10:09
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-12-10
 */
public class GrListActionForm extends BaseForm{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Description：个人信息列表
	 */
	private List grList = new ArrayList();
	
	private List sfzjlxList = new ArrayList();
	
	private String jsjdm;
	
	private String msg;

	private String sumPage="0";
	
	private String currentPage="1";
	
	/**
	 * @description: getter-- grList
	 * @return the grList
	 */
    	public List getGrList() {
		return grList;
	}

    	
   	
	/**
	 * @description: getter-- msg
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}



	/**
	 * @description: setter-- msg
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}



	/**
	 * @description: setter-- grList
	 * @param grList the grList to set
	 */
	public void setGrList(List grList) {
		this.grList = grList;
	}

	/**
	 * @description: getter-- jsjdm
	 * @return the jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
	}

	/**
	 * @description: setter-- jsjdm
	 * @param jsjdm the jsjdm to set
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	/**
	 * @description: getter-- sfzjlxList
	 * @return the sfzjlxList
	 */
	public List getSfzjlxList() {
		return sfzjlxList;
	}

	/**
	 * @description: setter-- sfzjlxList
	 * @param sfzjlxList the sfzjlxList to set
	 */
	public void setSfzjlxList(List sfzjlxList) {
		this.sfzjlxList = sfzjlxList;
	}



	/**
	 * @description: getter-- sumPage
	 * @return the sumPage
	 */
	public String getSumPage() {
		return sumPage;
	}



	/**
	 * @description: setter-- sumPage
	 * @param sumPage the sumPage to set
	 */
	public void setSumPage(String sumPage) {
		this.sumPage = sumPage;
	}



	/**
	 * @description: getter-- currentPage
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}



	/**
	 * @description: setter-- currentPage
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
