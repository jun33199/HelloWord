package com.ttsoft.bjtax.smsb.sjjh.web;


/**
 * <p>Title: 备案登记表BO</p>
 *
 * <p>Description: 记录备案登记表相关应用的键值，用于后台交互</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.Serializable;
import java.util.List;



public class SjjhBO implements Serializable
{
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}