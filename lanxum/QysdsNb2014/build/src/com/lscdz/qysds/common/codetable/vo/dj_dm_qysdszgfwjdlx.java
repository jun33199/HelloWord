package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Dec 26 15:38:17 CST 2014
 * Table:    DMDB.DJ_DM_QYSDSZGFWJDLX
 * Comments: 企业所得税征管范围鉴定类型代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;

import com.lscdz.util.codetable.CodeTableInterface;

public class dj_dm_qysdszgfwjdlx implements CodeTableInterface ,java.io.Serializable
{

	private static final long serialVersionUID = -2053210585445238280L;
	private String	jdlxdm;	//鉴定类型代码
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	jdlxmc;	//鉴定类型名称
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	zxbz;	//注销标识 0：未注销 1：已注销

	public void setJdlxdm(String jdlxdm)
	{
		this.jdlxdm = jdlxdm;
	}
	public String getJdlxdm()
	{
		return (jdlxdm == null ? "" : jdlxdm);
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
	public void setJdlxmc(String jdlxmc)
	{
		this.jdlxmc = jdlxmc;
	}
	public String getJdlxmc()
	{
		return (jdlxmc == null ? "" : jdlxmc);
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
	public void setZxbz(String zxbz)
	{
		this.zxbz = zxbz;
	}
	public String getZxbz()
	{
		return (zxbz == null ? "" : zxbz);
	}
	@Override
	public String getOptionCode() {
		// TODO Auto-generated method stub
		return this.jdlxdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return this.jdlxmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "jdlxdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.DJ_DM_QYSDSZGFWJDLX";
	}
}
