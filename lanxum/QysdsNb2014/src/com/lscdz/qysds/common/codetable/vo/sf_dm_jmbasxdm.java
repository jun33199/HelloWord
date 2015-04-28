package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Tue Jan 06 15:24:01 CST 2015
 * Table:    DMDB.SF_DM_JMBASXDM_2014
 * Comments: 减免备案事项代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;

import com.lscdz.util.codetable.CodeTableInterface;

public class sf_dm_jmbasxdm implements CodeTableInterface,java.io.Serializable
{
	private static final long serialVersionUID = -1888911473198364956L;
	private String	jmbasxdm;	//减免备案事项代码
	private String	jmbasxmc;	//减免备案事项名称
	private String	jmlbdm;	//减免类别代码，0：免税额 ，1：免税基
	private String	balxdm;	//备案类型代码，0：单笔，1：多条
	private String  hjbs;
	private String  yjbs;
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	zfbs;	//作废标识，0：有效，1：作废

	public void setJmbasxdm(String jmbasxdm)
	{
		this.jmbasxdm = jmbasxdm;
	}
	public String getJmbasxdm()
	{
		return (jmbasxdm == null ? "" : jmbasxdm);
	}
	public void setJmbasxmc(String jmbasxmc)
	{
		this.jmbasxmc = jmbasxmc;
	}
	public String getJmbasxmc()
	{
		return (jmbasxmc == null ? "" : jmbasxmc);
	}
	public void setJmlbdm(String jmlbdm)
	{
		this.jmlbdm = jmlbdm;
	}
	public String getJmlbdm()
	{
		return (jmlbdm == null ? "" : jmlbdm);
	}
	public void setBalxdm(String balxdm)
	{
		this.balxdm = balxdm;
	}
	public String getBalxdm()
	{
		return (balxdm == null ? "" : balxdm);
	}
	public void setCjr(String cjr)
	{
		this.cjr = cjr;
	}
	public String getCjr()
	{
		return (cjr == null ? "" : cjr);
	}
	public void setCjrq(Timestamp cjrq)
	{
		this.cjrq = cjrq;
	}
	public Timestamp getCjrq()
	{
		return cjrq;
	}
	public void setLrr(String lrr)
	{
		this.lrr = lrr;
	}
	public String getLrr()
	{
		return (lrr == null ? "" : lrr);
	}
	public void setLrrq(Timestamp lrrq)
	{
		this.lrrq = lrrq;
	}
	public Timestamp getLrrq()
	{
		return lrrq;
	}
	public void setZfbs(String zfbs)
	{
		this.zfbs = zfbs;
	}
	public String getZfbs()
	{
		return (zfbs == null ? "" : zfbs);
	}
	
	public String getHjbs() {
		return hjbs;
	}
	public void setHjbs(String hjbs) {
		this.hjbs = hjbs;
	}
	public String getYjbs() {
		return yjbs;
	}
	public void setYjbs(String yjbs) {
		this.yjbs = yjbs;
	}
	@Override
	public String getOptionCode() {
		// TODO Auto-generated method stub
		return jmbasxdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return jmbasxmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "JMBASXDM,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "WHERE ZFBS='0'";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.SF_DM_JMBASXDM_2014";
	}
}
