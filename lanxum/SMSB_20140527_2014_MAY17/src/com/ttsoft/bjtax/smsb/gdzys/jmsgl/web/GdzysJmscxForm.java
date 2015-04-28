package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * 
 * <p>Title: </p>
 * <p>Description:查询减免税Form </p>
 * @author 开发部-霍洪波
 * @version 1.0
 */
public class GdzysJmscxForm extends BaseForm{
	public GdzysJmscxForm(){
		
	}
	private String choice;
	private String nsrmc;//纳税人名称
	private String jsjdm;//计算机代码
	private String pzzdwh;//批准占地文号

	//查询结果提示标识(1:显示提示信息；0：不显示提示信息)
	private String cxJgTsFlag="0";
	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}
	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	private String sbxlh;//申报序列号
	private List ztList;//状态列表
	private String status;//状态
	private List jgList;//审核机关列表
	private String shjg;//审核机关
	private String cjr;//创建人
	private String cjrq;//创建日期
	public String getJmszmbh() {
		return jmszmbh;
	}
	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
	}
	private String jmszmbh;//减免税证明编号
	
	private String sjzdmj;//实际占地面积
	private String ynse;//应纳税额
	private String sbsj;//申报时间
	private List dataList=new ArrayList();//查询结果集合
	private List jmszmList=new ArrayList();//减免税证明list
	private String selfswjgdm;//自己所在税务局代码
	

	
	public String getSelfswjgdm() {
		return selfswjgdm;
	}
	public void setSelfswjgdm(String selfswjgdm) {
		this.selfswjgdm = selfswjgdm;
	}
	public List getJmszmList() {
		return jmszmList;
	}
	public void setJmszmList(List jmszmList) {
		this.jmszmList = jmszmList;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public List getZtList() {
		return ztList;
	}
	public void setZtList(List ztList) {
		this.ztList = ztList;
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
	public String getSbxlh() {
		return sbxlh;
	}
	public void setSbxlh(String sbxlh) {
		this.sbxlh = sbxlh;
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
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getSjzdmj() {
		return sjzdmj;
	}
	public void setSjzdmj(String sjzdmj) {
		this.sjzdmj = sjzdmj;
	}
	public String getYnse() {
		return ynse;
	}
	public void setYnse(String ynse) {
		this.ynse = ynse;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	

}
