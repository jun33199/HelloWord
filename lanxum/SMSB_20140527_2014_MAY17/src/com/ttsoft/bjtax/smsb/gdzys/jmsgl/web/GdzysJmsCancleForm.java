package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class GdzysJmsCancleForm extends BaseForm{
	private String localUser;//当前操作用户
	private List jgList;//审核机关列表
	private String nsrmc;//纳税人名称
	private String jsjdm;//计算机代码
	private String pzzdwh;//批准占地文号
	private String sbbxlh;//申报表序列号
	private String jmszmbh;//减免税证明编号
	private String status;//查询状态
	private String shjg;//审核机关
	private String jsmj;//计税面积
	private String selfswjgdm;//自己所在税务局代码
	private List dataList =new ArrayList();//返回数据的集合
	private List jmszmList=new ArrayList();//减免税证明的集合
	
	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}
	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}
	private String flag="";//是否撤销成功
	

	//查询结果提示标识(1:显示提示信息；0：不显示提示信息)
	private String cxJgTsFlag="0";
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List getJmszmList() {
		return jmszmList;
	}
	public void setJmszmList(List jmszmList) {
		this.jmszmList = jmszmList;
	}
	public String getLocalUser() {
		return localUser;
	}
	public void setLocalUser(String localUser) {
		this.localUser = localUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShjg() {
		return shjg;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getSelfswjgdm() {
		return selfswjgdm;
	}
	public void setSelfswjgdm(String selfswjgdm) {
		this.selfswjgdm = selfswjgdm;
	}
	public List getJgList() {
		return jgList;
	}
	public void setJgList(List jgList) {
		this.jgList = jgList;
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
	public String getPzzdwh() {
		return pzzdwh;
	}
	public void setPzzdwh(String pzzdwh) {
		this.pzzdwh = pzzdwh;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public String getJmszmbh() {
		return jmszmbh;
	}
	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
	}
	public String getJsmj() {
		return jsmj;
	}
	public void setJsmj(String jsmj) {
		this.jsmj = jsmj;
	}
	public String getJmmj() {
		return jmmj;
	}
	public void setJmmj(String jmmj) {
		this.jmmj = jmmj;
	}
	private String jmmj;//减免面积
	public GdzysJmsCancleForm(){
		
	}

}
