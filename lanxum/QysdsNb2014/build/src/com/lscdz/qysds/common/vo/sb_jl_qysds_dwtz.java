package com.lscdz.qysds.common.vo;

/**
 * Created by CodeGenerator at Tue Jan 06 09:22:47 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_DWTZ
 * Comments: 
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;

public class sb_jl_qysds_dwtz implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	dwtzqyid;	//对外投资企业ID
	private String	id;	//表ID
	private String	btzzmc;	//被投资名称
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	jjxz;	//经济性质
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	nsrsbh;	//纳税人识别号
	private String	swjgzzjgdm;	//税务机关组织机构代码
	private String	swjgzzjgmc;	//税务机关组织机构名称
	private BigDecimal	tzbl;	//投资比例
	private BigDecimal	tzje;	//投资金额
	private String	zcdz;	//注册地址

	public void setDwtzqyid(String dwtzqyid)
	{
		this.dwtzqyid = dwtzqyid;
	}
	public String getDwtzqyid()
	{
		return (dwtzqyid == null ? "" : dwtzqyid);
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return (id == null ? "" : id);
	}
	public void setBtzzmc(String btzzmc)
	{
		this.btzzmc = btzzmc;
	}
	public String getBtzzmc()
	{
		return (btzzmc == null ? "" : btzzmc);
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
	public void setNsrsbh(String nsrsbh)
	{
		this.nsrsbh = nsrsbh;
	}
	public String getNsrsbh()
	{
		return (nsrsbh == null ? "" : nsrsbh);
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
	public void setTzje(BigDecimal tzje)
	{
		this.tzje = tzje;
	}
	public BigDecimal getTzje()
	{
		return tzje;
	}
	public void setZcdz(String zcdz)
	{
		this.zcdz = zcdz;
	}
	public String getZcdz()
	{
		return (zcdz == null ? "" : zcdz);
	}
}
