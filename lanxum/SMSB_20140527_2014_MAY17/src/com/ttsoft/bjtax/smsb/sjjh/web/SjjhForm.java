package com.ttsoft.bjtax.smsb.sjjh.web;

/**
 * <p>Title: 扣缴企业所得税-电子台帐Form</p>
 *
 * <p>Description: 记录电子台帐页面域值</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author liuc
 * @version 1.0
 */


import com.ttsoft.framework.form.*;

public class SjjhForm extends BaseForm
{
    public SjjhForm()
    {
    }
	


    /**
     * 扣缴人计算机代码
     */
    private String skssksrq;
    
    private String skssjsrq;
    
    private String dept;
    
    private String bwlx;
    
    private String bwlxmc;
    
    private String context;

	public String getBwlxmc() {
		return bwlxmc;
	}

	public void setBwlxmc(String bwlxmc) {
		this.bwlxmc = bwlxmc;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getBwlx() {
		return bwlx;
	}

	public void setBwlx(String bwlx) {
		this.bwlx = bwlx;
	}

	public String getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
    
    



}
