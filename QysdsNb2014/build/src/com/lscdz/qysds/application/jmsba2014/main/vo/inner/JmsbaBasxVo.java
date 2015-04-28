package com.lscdz.qysds.application.jmsba2014.main.vo.inner;

public class JmsbaBasxVo {
	/**
	 * 计算机代码
	 */
	private String jsjdm;
	/**
	 * 纳税人名称
	 */
	private String nsrmc;
	/**
	 * 税务机关组织机构名称
	 */
	private String swjgzzjgmc;
	/**
	 * 税务机关组织机构代码
	 */
	private String swjgzzjgdm;
	/**
	 * 备案年度
	 */
	private String band;
	/**
	 * 备案申请编号
	 */
	private String basqbh;
	/**
	 * 减免备案事项代码
	 */
	private String jmbasxdm;
	/**
	 * 减免备案事项名称
	 */
	private String jmbasxmc;
	/**
	 * 申请类型代码，0：网上申请，1：上门申请
	 */
	private String sqlxdm;
	private String sqlxmc;
	/**
	 * 申请状态代码，1：保存未提交，2：保存未审核，3：提交未审核，4：审核已通过，5：审核未通过
	 */
	private String sqzt;
	private String sqztmc;
	/**
	 * 报表期类型,0-月度,1-季度,2-年度
	 */
	private String bbqlx;
	/**
	 * 期号 根据BBLX区分不同期的申报,例如,BBQLX=2则期号开始为1
	 */
	private String qh;
	/**
	 * 税款所属时期起
	 */
	private String sksssqq;
	/**
	 * 税款所属时期止
	 */
	private String sksssqz;
	
	
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getBasqbh() {
		return basqbh;
	}
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	public String getJmbasxmc() {
		return jmbasxmc;
	}
	public void setJmbasxmc(String jmbasxmc) {
		this.jmbasxmc = jmbasxmc;
	}
	public String getSqlxdm() {
		return sqlxdm;
	}
	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getBbqlx() {
		return bbqlx;
	}
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}
	public String getQh() {
		return qh;
	}
	public void setQh(String qh) {
		this.qh = qh;
	}
	public String getSksssqq() {
		return sksssqq;
	}
	public void setSksssqq(String sksssqq) {
		this.sksssqq = sksssqq;
	}
	public String getSksssqz() {
		return sksssqz;
	}
	public void setSksssqz(String sksssqz) {
		this.sksssqz = sksssqz;
	}
	public String getSqlxmc() {
		return sqlxmc;
	}
	public void setSqlxmc(String sqlxmc) {
		this.sqlxmc = sqlxmc;
	}
	public String getSqztmc() {
		return sqztmc;
	}
	public void setSqztmc(String sqztmc) {
		this.sqztmc = sqztmc;
	}
	
	
}
