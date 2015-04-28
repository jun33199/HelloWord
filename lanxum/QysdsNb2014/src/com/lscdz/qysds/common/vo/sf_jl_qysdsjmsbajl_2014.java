package com.lscdz.qysds.common.vo;

/**
 * Created by CodeGenerator at Thu Mar 19 17:49:04 CST 2015
 * Table:    SFDB.SF_JL_QYSDSJMSBAJL_2014
 * Comments: 企业所得税减免税备案记录主表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;
import java.util.*;
import yangjian.frame.util.*;

public class sf_jl_qysdsjmsbajl_2014 implements java.io.Serializable
{
	private String	bafs;	//备案方式,1-预缴备案,2-汇缴备案
	private String	band;	//备案年度
	private String	basqbh;	//备案申请编号
	private String	basqwsh;	//备案申请文书号
	private String	bbqlx;	//报表期类型,0-月度,1-季度,2-年度
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	jmbasxdm;	//减免备案事项代码
	private String	jsjdm;	//计算机代码
	private String	jyxgzgdpzwjjwh;	//具有相关资格的批准文件（证书）及文号（编号）
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	qh;	//期号 根据BBLX区分不同期的申报,例如,BBQLX=2则期号开始为1
	private Timestamp	sksssqq;	//税款所属时期起
	private Timestamp	sksssqz;	//税款所属时期止
	private String	sqlxdm;	//申请类型代码，0：网上申请，1：上门申请
	private String	swjgzzjgdm;	//税务机关组织机构代码
	private String	szdm;	//税种代码
	private Timestamp	xsyhqjq;	//享受优惠期间起
	private Timestamp	xsyhqjz;	//享受优惠期间止
	private String	zyzcyjwjjwh;	//主要政策依据文件及文号
	private BigDecimal	bajmbl;	//备案减免比例
	private BigDecimal	bajmse;	//备案减免税额
	private String	htr;	//回退人
	private Timestamp	htrq;	//回退日期
	private String	shr;	//审核人
	private Timestamp	shsj;	//审核时间
	private String	sqzt;	//申请状态代码，1：保存未提交，2：保存未审核，3：提交未审核，4：审核已通过，5：审核未通过
	private String	tjr;	//提交人
	private Timestamp	tjsj;	//提交时间
	private Timestamp	wjyxqjzrq;	//文件（证书）有效期截止日期
	private Timestamp	wjyxqqsrq;	//文件（证书）有效期起始日期
	private String	ygqksm;	//有关情况说明
	private String	zfr;	//作废人
	private Timestamp	zfrq;	//作废日期
	private String	zfsm;	//作废说明

	public void setBafs(String bafs)
	{
		this.bafs = bafs;
	}
	public String getBafs()
	{
		return (bafs == null ? "" : bafs);
	}
	public void setBand(String band)
	{
		this.band = band;
	}
	public String getBand()
	{
		return (band == null ? "" : band);
	}
	public void setBasqbh(String basqbh)
	{
		this.basqbh = basqbh;
	}
	public String getBasqbh()
	{
		return (basqbh == null ? "" : basqbh);
	}
	public void setBasqwsh(String basqwsh)
	{
		this.basqwsh = basqwsh;
	}
	public String getBasqwsh()
	{
		return (basqwsh == null ? "" : basqwsh);
	}
	public void setBbqlx(String bbqlx)
	{
		this.bbqlx = bbqlx;
	}
	public String getBbqlx()
	{
		return (bbqlx == null ? "" : bbqlx);
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
	public void setJmbasxdm(String jmbasxdm)
	{
		this.jmbasxdm = jmbasxdm;
	}
	public String getJmbasxdm()
	{
		return (jmbasxdm == null ? "" : jmbasxdm);
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
	}
	public void setJyxgzgdpzwjjwh(String jyxgzgdpzwjjwh)
	{
		this.jyxgzgdpzwjjwh = jyxgzgdpzwjjwh;
	}
	public String getJyxgzgdpzwjjwh()
	{
		return (jyxgzgdpzwjjwh == null ? "" : jyxgzgdpzwjjwh);
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
	public void setQh(String qh)
	{
		this.qh = qh;
	}
	public String getQh()
	{
		return (qh == null ? "" : qh);
	}
	public void setSksssqq(Timestamp sksssqq)
	{
		this.sksssqq = sksssqq;
	}
	public Timestamp getSksssqq()
	{
		return sksssqq;
	}
	public void setSksssqz(Timestamp sksssqz)
	{
		this.sksssqz = sksssqz;
	}
	public Timestamp getSksssqz()
	{
		return sksssqz;
	}
	public void setSqlxdm(String sqlxdm)
	{
		this.sqlxdm = sqlxdm;
	}
	public String getSqlxdm()
	{
		return (sqlxdm == null ? "" : sqlxdm);
	}
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setSzdm(String szdm)
	{
		this.szdm = szdm;
	}
	public String getSzdm()
	{
		return (szdm == null ? "" : szdm);
	}
	public void setXsyhqjq(Timestamp xsyhqjq)
	{
		this.xsyhqjq = xsyhqjq;
	}
	public Timestamp getXsyhqjq()
	{
		return xsyhqjq;
	}
	public void setXsyhqjz(Timestamp xsyhqjz)
	{
		this.xsyhqjz = xsyhqjz;
	}
	public Timestamp getXsyhqjz()
	{
		return xsyhqjz;
	}
	public void setZyzcyjwjjwh(String zyzcyjwjjwh)
	{
		this.zyzcyjwjjwh = zyzcyjwjjwh;
	}
	public String getZyzcyjwjjwh()
	{
		return (zyzcyjwjjwh == null ? "" : zyzcyjwjjwh);
	}
	public void setBajmbl(BigDecimal bajmbl)
	{
		this.bajmbl = bajmbl;
	}
	public BigDecimal getBajmbl()
	{
		return bajmbl;
	}
	public void setBajmse(BigDecimal bajmse)
	{
		this.bajmse = bajmse;
	}
	public BigDecimal getBajmse()
	{
		return bajmse;
	}
	public void setHtr(String htr)
	{
		this.htr = htr;
	}
	public String getHtr()
	{
		return (htr == null ? "" : htr);
	}
	public void setHtrq(Timestamp htrq)
	{
		this.htrq = htrq;
	}
	public Timestamp getHtrq()
	{
		return htrq;
	}
	public void setShr(String shr)
	{
		this.shr = shr;
	}
	public String getShr()
	{
		return (shr == null ? "" : shr);
	}
	public void setShsj(Timestamp shsj)
	{
		this.shsj = shsj;
	}
	public Timestamp getShsj()
	{
		return shsj;
	}
	public void setSqzt(String sqzt)
	{
		this.sqzt = sqzt;
	}
	public String getSqzt()
	{
		return (sqzt == null ? "" : sqzt);
	}
	public void setTjr(String tjr)
	{
		this.tjr = tjr;
	}
	public String getTjr()
	{
		return (tjr == null ? "" : tjr);
	}
	public void setTjsj(Timestamp tjsj)
	{
		this.tjsj = tjsj;
	}
	public Timestamp getTjsj()
	{
		return tjsj;
	}
	public void setWjyxqjzrq(Timestamp wjyxqjzrq)
	{
		this.wjyxqjzrq = wjyxqjzrq;
	}
	public Timestamp getWjyxqjzrq()
	{
		return wjyxqjzrq;
	}
	public void setWjyxqqsrq(Timestamp wjyxqqsrq)
	{
		this.wjyxqqsrq = wjyxqqsrq;
	}
	public Timestamp getWjyxqqsrq()
	{
		return wjyxqqsrq;
	}
	public void setYgqksm(String ygqksm)
	{
		this.ygqksm = ygqksm;
	}
	public String getYgqksm()
	{
		return (ygqksm == null ? "" : ygqksm);
	}
	public void setZfr(String zfr)
	{
		this.zfr = zfr;
	}
	public String getZfr()
	{
		return (zfr == null ? "" : zfr);
	}
	public void setZfrq(Timestamp zfrq)
	{
		this.zfrq = zfrq;
	}
	public Timestamp getZfrq()
	{
		return zfrq;
	}
	public void setZfsm(String zfsm)
	{
		this.zfsm = zfsm;
	}
	public String getZfsm()
	{
		return (zfsm == null ? "" : zfsm);
	}
}
