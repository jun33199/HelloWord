package com.ttsoft.bjtax.smsb.sjjh.web;

/**
 * <p>Title: �۽���ҵ����˰-����̨��Form</p>
 *
 * <p>Description: ��¼����̨��ҳ����ֵ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
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
     * �۽��˼��������
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
