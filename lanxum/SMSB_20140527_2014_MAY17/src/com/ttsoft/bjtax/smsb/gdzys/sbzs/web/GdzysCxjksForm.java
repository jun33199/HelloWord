package com.ttsoft.bjtax.smsb.gdzys.sbzs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;
/**
 * <p>
 * Title: 北京地税核心征管系统--耕地占用税征收管理
 * </p>
 * <p>
 * Description: 撤销缴款书Form
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class GdzysCxjksForm extends BaseForm{
	//纳税人名称
	private String nsrmc="";
	//计算机代码
	private String jsjdm="";
	//批准占地文号
	private String pzjdwh="";
	//申报表序列号
	private String sbbxlh="";
	
	//申报表序列号
	private String cxSbbxlh="";
	//查询结果提示标识(1:显示提示信息；0：不显示提示信息)
	private String cxJgTsFlag="0";
	//缴款凭证号
	private String cxJkpzh="";
	
	//申报编号
	private String cxSbbh="";
	//计算机代码
	private String cxJsjdm="";
	
	//查询结果集合
	private List dataList = new ArrayList();
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

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

	public String getPzjdwh() {
		return pzjdwh;
	}

	public void setPzjdwh(String pzjdwh) {
		this.pzjdwh = pzjdwh;
	}

	public String getSbbxlh() {
		return sbbxlh;
	}

	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getCxSbbxlh() {
		return cxSbbxlh;
	}

	public void setCxSbbxlh(String cxSbbxlh) {
		this.cxSbbxlh = cxSbbxlh;
	}

	public String getCxJkpzh() {
		return cxJkpzh;
	}

	public void setCxJkpzh(String cxJkpzh) {
		this.cxJkpzh = cxJkpzh;
	}

	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}

	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}

	public String getCxSbbh() {
		return cxSbbh;
	}

	public void setCxSbbh(String cxSbbh) {
		this.cxSbbh = cxSbbh;
	}

	public String getCxJsjdm() {
		return cxJsjdm;
	}

	public void setCxJsjdm(String cxJsjdm) {
		this.cxJsjdm = cxJsjdm;
	}


}
