/**
 * @Title:       GrListActionForm.java
 * @Description: TODO
 * @date:        2014-12-10����02:10:09
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.cx.web;

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
	 * Description��
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Description��������Ϣ�б�
	 */
	private List grList = new ArrayList();
	
	private List sfzjlxList = new ArrayList();
	
	/**
	 * Description�����������
	 */
	private String jsjdm;
	
	/**
	 * Description�����֤������
	 */
	private String sfzjlx;
	
	/**
	 * Description�����֤������
	 */
	private String sfzjhm;
	
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
	 * @description: getter-- sfzjlx
	 * @return the sfzjlx
	 */
	public String getSfzjlx() {
		return sfzjlx;
	}



	/**
	 * @description: setter-- sfzjlx
	 * @param sfzjlx the sfzjlx to set
	 */
	public void setSfzjlx(String sfzjlx) {
		this.sfzjlx = sfzjlx;
	}



	/**
	 * @description: getter-- sfzjhm
	 * @return the sfzjhm
	 */
	public String getSfzjhm() {
		return sfzjhm;
	}



	/**
	 * @description: setter-- sfzjhm
	 * @param sfzjhm the sfzjhm to set
	 */
	public void setSfzjhm(String sfzjhm) {
		this.sfzjhm = sfzjhm;
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
