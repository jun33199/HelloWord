package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model;

import java.io.Serializable;
import java.util.List;

public class DJXX implements Serializable {
	//计算机代码
	public String jsjdm;
	//纳税人名称
	public String nsrmc;
	//纳税人详细地址
	public String nsrxxdz;
	//纳税人所属行业
	public String nsrsshy;
	//纳税人所属行业名称
	public String nsrsshymc;
	//企业登记注册类型
	public String qydjzclxdm;
	//企业登记注册类型名称
	public String qydjzclxmc;
	//纳税人识别号
	public String nsrsbh;
	//开户银行
	public String yhmc;
	//银行代码
	public String yhdm;
	//银行帐号
	public String yhzh;
	//联系人姓名
	public String lxrxm;
	//联系电话
	public String lxdh;
	//zrr
	public String dm;
	//税务机关组织机构代码
	public String swjgzzjgdm;
	
/*	20131213 变更 start*/
	//银行代码列表
	public String[] yhdm_list; 
	//银行名称列表
	public String[] yhmc_list;
	//银行账号列表
	public String[] yhzh_list;
	/*	20131213 变更 end*/	
	
	
	public String[] getYhdm_list() {
		return yhdm_list;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public void setYhdm_list(String[] yhdm_list) {
		this.yhdm_list = yhdm_list;
	}
	public String[] getYhmc_list() {
		return yhmc_list;
	}
	public void setYhmc_list(String[] yhmc_list) {
		this.yhmc_list = yhmc_list;
	}
	public String[] getYhzh_list() {
		return yhzh_list;
	}
	public void setYhzh_list(String[] yhzh_list) {
		this.yhzh_list = yhzh_list;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getNsrsshymc() {
		return nsrsshymc;
	}
	public void setNsrsshymc(String nsrsshymc) {
		this.nsrsshymc = nsrsshymc;
	}
	public String getQydjzclxmc() {
		return qydjzclxmc;
	}
	public void setQydjzclxmc(String qydjzclxmc) {
		this.qydjzclxmc = qydjzclxmc;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrxxdz() {
		return nsrxxdz;
	}
	public void setNsrxxdz(String nsrxxdz) {
		this.nsrxxdz = nsrxxdz;
	}
	public String getNsrsshy() {
		return nsrsshy;
	}
	public void setNsrsshy(String nsrsshy) {
		this.nsrsshy = nsrsshy;
	}
	public String getQydjzclxdm() {
		return qydjzclxdm;
	}
	public void setQydjzclxdm(String qydjzclxdm) {
		this.qydjzclxdm = qydjzclxdm;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	

	
}
