package com.ttsoft.bjtax.smsb.jmssb.web;

import java.io.Serializable;

/**
 * <p>Title: 上门申报-补充申报资料录入-安置残疾人就业企业营业税减免税查询统计表</p>
 * <p>Description: 安置残疾人就业企业减免税显示信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmsInfo implements Serializable {
	
	/**
	 * 计算机代码
	 */
	private String jsjdm;
	
	/**
	 * 企业名称
	 */
	private String qymc;
	
	/**
	 * 查询结果序号
	 */
	private int indexId;
	
	/**
	 * 申请审批编号
	 */
	private String spbbh;
	
	/**
	 * 年减征营业税限额
	 */
	private String njzyysxe;
	
	/**
	 * 减征开始日期
	 */
	private String jzksrq;
	
	/**
	 * 减征截止日期
	 */
	private String jzjzrq;
	
	/**
	 * 实际减征营业税额
	 */
	private String sjjzyyse;
	
	/**
	 * 安置残疾职工人数
	 */
	private String azcjzgrs;

	public int getIndexId() {
		return indexId;
	}

	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}

	public String getSpbbh() {
		return spbbh;
	}

	public void setSpbbh(String spbbh) {
		this.spbbh = spbbh;
	}

	public String getNjzyysxe() {
		return njzyysxe;
	}

	public void setNjzyysxe(String njzyysxe) {
		this.njzyysxe = njzyysxe;
	}

	public String getJzksrq() {
		return jzksrq;
	}

	public void setJzksrq(String jzksrq) {
		this.jzksrq = jzksrq;
	}

	public String getJzjzrq() {
		return jzjzrq;
	}

	public void setJzjzrq(String jzjzrq) {
		this.jzjzrq = jzjzrq;
	}

	public String getSjjzyyse() {
		return sjjzyyse;
	}

	public void setSjjzyyse(String sjjzyyse) {
		this.sjjzyyse = sjjzyyse;
	}

	public String getAzcjzgrs() {
		return azcjzgrs;
	}

	public void setAzcjzgrs(String azcjzgrs) {
		this.azcjzgrs = azcjzgrs;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
}
