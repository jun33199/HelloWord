/*
 * <p>Title: 北京地税核心征管系统－－上门申报--耕地占用税</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;
/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 耕地占用税税源信息查询Form。</p>
 * @author 开发部 - 李剑楠
 * @version 1.0
 */
public class GdzysSybgForm  extends BaseForm {
	

	//纳税人名称
	private String nsrmc;
	
	//计算机代码
	private String jsjdm;
	
	//占地批文号
	private String zdpwh;
	
	//申报序列号
	private String sbbxlh;
		
	//查询结果
	private List infList; 
	
	//信息结果条数
	private int num = -1;
	
	
	/*------------------------------------------------------------------------------------------------------------------------*/
	
	public String getNsrmc() {
		return nsrmc;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
