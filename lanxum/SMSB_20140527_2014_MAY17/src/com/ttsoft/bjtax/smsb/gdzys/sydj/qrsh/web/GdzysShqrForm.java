/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.web;

import java.util.*;

import com.ttsoft.framework.form.*;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Form。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class GdzysShqrForm extends BaseForm {

	
	public GdzysShqrForm() {}
   
	//纳税人名称
	private String nsrmc;
	
	//计算机代码
	private String jsjdm;
	
	//占地批文号
	private String zdpwh;
	
	//申报表序列号
	private String sbbxlh;
	
	//查询结果
	private List infList; 
	
	//信息结果条数
	private int num = -1;
	
	//审核确认信息条数
	private int shqrnum;
	
	//审核信息---申报序列号
	private String[] shqrinf;
	
	//审核种类
	private String shzl;
	
	//市局审核时间
	private String sjshsj;
	
	//申报状态
	private String sbzt;
	
	//是否市局审批
	private String sfsjsp;
	
	//创建人查询
	private String cjrcx;
	
/*------------------------------------------------------------------------------------------------------------------------*/
	
	
	public String getNsrmc() {
		return nsrmc;
	}




	public String getSjshsj() {
		return sjshsj;
	}




	public void setSjshsj(String sjshsj) {
		this.sjshsj = sjshsj;
	}




	public String getCjrcx() {
		return cjrcx;
	}




	public void setCjrcx(String cjrcx) {
		this.cjrcx = cjrcx;
	}




	public String getShzl() {
		return shzl;
	}




	public void setShzl(String shzl) {
		this.shzl = shzl;
	}




	public String getSbzt() {
		return sbzt;
	}


	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}


	public String getSfsjsp() {
		return sfsjsp;
	}


	public void setSfsjsp(String sfsjsp) {
		this.sfsjsp = sfsjsp;
	}


	public String[] getShqrinf() {
		return shqrinf;
	}


	public void setShqrinf(String[] shqrinf) {
		this.shqrinf = shqrinf;
	}


	public int getShqrnum() {
		return shqrnum;
	}


	public void setShqrnum(int shqrnum) {
		this.shqrnum = shqrnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getZdpwh() {
		return zdpwh;
	}

	public void setZdpwh(String zdpwh) {
		this.zdpwh = zdpwh;
	}

	

	public String getSbbxlh() {
		return sbbxlh;
	}




	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}




	public List getInfList() {
		return infList;
	}

	public void setInfList(List infList) {
		this.infList = infList;
	}

	
}
