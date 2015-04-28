package com.lscdz.qysds.common.vo;

/**
 * Created by CodeGenerator at Tue Jan 06 09:22:41 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_QYZYGD
 * Comments: 
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;

public class sb_jl_qysds_qyzygd implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	id;	//表ID
	private String	qyzygdid;	//企业主要股东ID
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	gdmc;	//股东名称
	private String	gj;	//国籍（注册地址）
	private String	jjxz;	//经济性质
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	swjgzzjgdm;	//税务机关组织机构代码
	private String	swjgzzjgmc;	//税务机关组织机构名称
	private BigDecimal	tzbl;	//投资比例
	private String	zjhm;	//证件号码
	private String	zjzl;	//证件种类

	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return (id == null ? "" : id);
	}
	public void setQyzygdid(String qyzygdid)
	{
		this.qyzygdid = qyzygdid;
	}
	public String getQyzygdid()
	{
		return (qyzygdid == null ? "" : qyzygdid);
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
	public void setGdmc(String gdmc)
	{
		this.gdmc = gdmc;
	}
	public String getGdmc()
	{
		return (gdmc == null ? "" : gdmc);
	}
	public void setGj(String gj)
	{
		this.gj = gj;
	}
	public String getGj()
	{
		return (gj == null ? "" : gj);
	}
	public void setJjxz(String jjxz)
	{
		this.jjxz = jjxz;
	}
	public String getJjxz()
	{
		return (jjxz == null ? "" : jjxz);
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
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setSwjgzzjgmc(String swjgzzjgmc)
	{
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getSwjgzzjgmc()
	{
		return (swjgzzjgmc == null ? "" : swjgzzjgmc);
	}
	public void setTzbl(BigDecimal tzbl)
	{
		this.tzbl = tzbl;
	}
	public BigDecimal getTzbl()
	{
		return tzbl;
	}
	public void setZjhm(String zjhm)
	{
		this.zjhm = zjhm;
	}
	public String getZjhm()
	{
		return (zjhm == null ? "" : zjhm);
	}
	public void setZjzl(String zjzl)
	{
		this.zjzl = zjzl;
	}
	public String getZjzl()
	{
		return (zjzl == null ? "" : zjzl);
	}
}
