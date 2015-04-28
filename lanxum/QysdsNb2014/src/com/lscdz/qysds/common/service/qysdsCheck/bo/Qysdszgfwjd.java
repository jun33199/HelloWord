package com.lscdz.qysds.common.service.qysdsCheck.bo;

/**
 * Created by CodeGenerator at Thu Dec 18 10:15:23 CST 2014
 * Table:    DJDB.DJ_JL_QYSDSZGFWJD
 * Comments: 企业所得税征管范围鉴定表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;


public class Qysdszgfwjd implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	jdbxlh;	//鉴定表序列号
	private String	jdnd;	//鉴定年度
	private Timestamp	jdqsrq;	//鉴定起始日期
	private String	jsjdm;	//计算机代码
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private Timestamp	jdjzrq;	//鉴定截止日期
	private String	jdlxdm;	//鉴定类型代码
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期

	public void setJdbxlh(String jdbxlh)
	{
		this.jdbxlh = jdbxlh;
	}
	public String getJdbxlh()
	{
		return (jdbxlh == null ? "" : jdbxlh);
	}
	public void setJdnd(String jdnd)
	{
		this.jdnd = jdnd;
	}
	public String getJdnd()
	{
		return (jdnd == null ? "" : jdnd);
	}
	public void setJdqsrq(Timestamp jdqsrq)
	{
		this.jdqsrq = jdqsrq;
	}
	public Timestamp getJdqsrq()
	{
		return jdqsrq;
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
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
	public void setJdjzrq(Timestamp jdjzrq)
	{
		this.jdjzrq = jdjzrq;
	}
	public Timestamp getJdjzrq()
	{
		return jdjzrq;
	}
	public void setJdlxdm(String jdlxdm)
	{
		this.jdlxdm = jdlxdm;
	}
	public String getJdlxdm()
	{
		return (jdlxdm == null ? "" : jdlxdm);
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
}
