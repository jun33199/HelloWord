/*
 * <p>Title: 北京地税核心征管系统－－上门申报--耕地占用税</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web;

import java.util.*;

import com.ttsoft.framework.form.*;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 耕地占用税税源信息查询Form。</p>
 * @author 开发部 - 李剑楠
 * @version 1.0
 */
public class GdzysSydjcxForm extends BaseForm {

	
	public GdzysSydjcxForm() {}
   
	//纳税人名称
	private String nsrmc;
	
	//计算机代码
	private String jsjdm;
	
	//占地批文号
	private String zdpwh;
	
	//申报序列号
	private String sbxlh;
		
	//查询结果
	private List infList; 
	
	//信息结果条数
	private int num = -1;
	
	//多结果详情
	private boolean detail = false;
	
	//查询人的税务机关代码
	private String swjgdm;
	
/*------------------------------------------------------------------------------------------------------------------------*/
	
	
	public String getNsrmc() {
		return nsrmc;
	}

	public boolean isDetail() {
		return detail;
	}

	
	
	public String getSwjgdm() {
		return swjgdm;
	}

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
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

	public String getSbxlh() {
		return sbxlh;
	}

	public void setSbxlh(String sbxlh) {
		this.sbxlh = sbxlh;
	}

	public List getInfList() {
		return infList;
	}

	public void setInfList(List infList) {
		this.infList = infList;
	}

	
}
