package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;


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



public class DztzBO implements Serializable
{
	
	
	/**
	 * 计算机代码
	 */
	private String jsjdm;
	
	/**
     * 扣缴义务人名称
     */
	private String kjywrmc;
	/**
     * 扣缴义务人纳税识别号
     */
	private String kjywrnssbh;
	/**
     * 联系人
     */
	private String lxr;
	/**
     * 电话
     */
	private String dh;
	/**
     * 地址
     */
	private String dz;
	/**
     * 非居民企业名称
     */
	private String fjmqymc;
	/**
     * 国别（地区）
     */
	private String gbdq;	
	/**
     * 台帐信息
     */
	private List tzsjxx;
	
	

	public String getKjywrmc() {
		return kjywrmc;
	}
	public void setKjywrmc(String kjywrmc) {
		this.kjywrmc = kjywrmc;
	}
	public String getKjywrnssbh() {
		return kjywrnssbh;
	}
	public void setKjywrnssbh(String kjywrnssbh) {
		this.kjywrnssbh = kjywrnssbh;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getFjmqymc() {
		return fjmqymc;
	}
	public void setFjmqymc(String fjmqymc) {
		this.fjmqymc = fjmqymc;
	}
	public String getGbdq() {
		return gbdq;
	}
	public void setGbdq(String gbdq) {
		this.gbdq = gbdq;
	}
	public List getTzsjxx() {
		return tzsjxx;
	}
	public void setTzsjxx(List tzsjxx) {
		this.tzsjxx = tzsjxx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	
	/**
	 * 是否展示查询结果
	 * @return
	 */
	public boolean zscxjg(){		
		return (tzsjxx ==null || tzsjxx.size()<=0)?false:true;
	}
	
	

	
	}
