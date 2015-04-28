package com.lscdz.qysds.common.service.sfgl.bo;

/**
 * Created by CodeGenerator at Wed Dec 17 10:31:30 CST 2014
 * Table:    DMDB.KJ_DM_YSKM
 * Comments: 预算科目代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;



@SuppressWarnings("serial")
public class Yskm implements java.io.Serializable
{
	private String	nd;	//年度
	private String	yskmdm;	//预算科目代码
	private String	yskmmc;	//预算科目名称
	private String	zxbs;	//注销标识
	private String	budget_type;	//预算科目类型 2表示地税适用科目，其他为非地税适用科目
	private String	ccbs;	//层次标识
	private String	fjddm;	//父节点代码
	private String	late_fee_sign;	//罚款滞纳金标志,表明该预算科目属于滞纳金罚款预算科目
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	mrszsmdm;	//默认税种税目代码
	private BigDecimal	qxfcbl;	//区县分成比例
	private String	revenue_plan;	//计划税收收入分类标志,1表示税收收入，0表示非税收收入
	private String	revenue_type;	//税收收入类型，1表示税收收入，0表示其他收入
	private BigDecimal	sjfcbl;	//市局分成比例
	private Timestamp	sxrq;	//生效日期
	private String	ysjcdm;	//预算级次代码
	private BigDecimal	zyfcbl;	//中央分成比例

	public void setNd(String nd)
	{
		this.nd = nd;
	}
	public String getNd()
	{
		return (nd == null ? "" : nd);
	}
	public void setYskmdm(String yskmdm)
	{
		this.yskmdm = yskmdm;
	}
	public String getYskmdm()
	{
		return (yskmdm == null ? "" : yskmdm);
	}
	public void setYskmmc(String yskmmc)
	{
		this.yskmmc = yskmmc;
	}
	public String getYskmmc()
	{
		return (yskmmc == null ? "" : yskmmc);
	}
	public void setZxbs(String zxbs)
	{
		this.zxbs = zxbs;
	}
	public String getZxbs()
	{
		return (zxbs == null ? "" : zxbs);
	}
	public void setBudget_type(String budget_type)
	{
		this.budget_type = budget_type;
	}
	public String getBudget_type()
	{
		return (budget_type == null ? "" : budget_type);
	}
	public void setCcbs(String ccbs)
	{
		this.ccbs = ccbs;
	}
	public String getCcbs()
	{
		return (ccbs == null ? "" : ccbs);
	}
	public void setFjddm(String fjddm)
	{
		this.fjddm = fjddm;
	}
	public String getFjddm()
	{
		return (fjddm == null ? "" : fjddm);
	}
	public void setLate_fee_sign(String late_fee_sign)
	{
		this.late_fee_sign = late_fee_sign;
	}
	public String getLate_fee_sign()
	{
		return (late_fee_sign == null ? "" : late_fee_sign);
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
	public void setMrszsmdm(String mrszsmdm)
	{
		this.mrszsmdm = mrszsmdm;
	}
	public String getMrszsmdm()
	{
		return (mrszsmdm == null ? "" : mrszsmdm);
	}
	public void setQxfcbl(BigDecimal qxfcbl)
	{
		this.qxfcbl = qxfcbl;
	}
	public BigDecimal getQxfcbl()
	{
		return qxfcbl;
	}
	public void setRevenue_plan(String revenue_plan)
	{
		this.revenue_plan = revenue_plan;
	}
	public String getRevenue_plan()
	{
		return (revenue_plan == null ? "" : revenue_plan);
	}
	public void setRevenue_type(String revenue_type)
	{
		this.revenue_type = revenue_type;
	}
	public String getRevenue_type()
	{
		return (revenue_type == null ? "" : revenue_type);
	}
	public void setSjfcbl(BigDecimal sjfcbl)
	{
		this.sjfcbl = sjfcbl;
	}
	public BigDecimal getSjfcbl()
	{
		return sjfcbl;
	}
	public void setSxrq(Timestamp sxrq)
	{
		this.sxrq = sxrq;
	}
	public Timestamp getSxrq()
	{
		return sxrq;
	}
	public void setYsjcdm(String ysjcdm)
	{
		this.ysjcdm = ysjcdm;
	}
	public String getYsjcdm()
	{
		return (ysjcdm == null ? "" : ysjcdm);
	}
	public void setZyfcbl(BigDecimal zyfcbl)
	{
		this.zyfcbl = zyfcbl;
	}
	public BigDecimal getZyfcbl()
	{
		return zyfcbl;
	}
}
