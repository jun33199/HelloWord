package com.ttsoft.bjtax.smsb.lwpk.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * <p>Title: 北京地税核心征管系统-税库行</p>
 * <p>Description: 批扣任务对象</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */
public class PKTaskModel implements Serializable {


	//序号
	private String xh;
	//日期
	private String zxrq; 
	//时间
	private String sj;
	//执行时间
	private String zxsj;
	//执行状态
	private String zxzt;
	//执行结果
	private String zxjg;
	//任务类型 01--生成代扣02--发送扣款
	private String rwlx;
	//任务类型名称
	private String rwlxmc;
	//创建人
	private String cjr;
	//创建日期
	private Date cjrq;
	//录入人
	private String lrr;
	//录入日期
	private Date lrrq;
	/*构造批扣发包属性*/
	//列表
	private List pkfsList;
	
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZxsj() {
		return zxsj;
	}
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	public String getZxzt() {
		return zxzt;
	}
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}
	public String getZxjg() {
		return zxjg;
	}
	public void setZxjg(String zxjg) {
		this.zxjg = zxjg;
	}
	public String getRwlx() {
		return rwlx;
	}
	public void setRwlx(String rwlx) {
		this.rwlx = rwlx;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Date getCjrq() {
		return cjrq;
	}
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Date getLrrq() {
		return lrrq;
	}
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	public String getZxrq() {
		return zxrq;
	}
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getRwlxmc() {
		return rwlxmc;
	}
	public void setRwlxmc(String rwlxmc) {
		this.rwlxmc = rwlxmc;
	}
	public List getPkfsList() {
		return pkfsList;
	}
	public void setPkfsList(List pkfsList) {
		this.pkfsList = pkfsList;
	}
	
}
