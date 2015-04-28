package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Jan 16 10:46:02 CST 2015
 * Table:    DMDB.DJ_DM_DJZCLX
 * Comments: 登记注册类型代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;

import com.lscdz.util.codetable.CodeTableInterface;

public class dj_dm_djzclx implements CodeTableInterface ,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	djzclxdm;	//登记注册类型代码
	private String	nwzfldm;	//内外资分类代码
	private String	pclxdm;	//判重类型代码
	private String	qylxdm;	//企业类型代码
	private String	djzclxmc;	//登记注册类型名称
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	swdjzzldm;	//税务登记证种类代码
	private Timestamp	zhgxsj;	//最后更新时间
	private String	zxbs;	//注销标识

	public void setDjzclxdm(String djzclxdm)
	{
		this.djzclxdm = djzclxdm;
	}
	public String getDjzclxdm()
	{
		return (djzclxdm == null ? "" : djzclxdm);
	}
	public void setNwzfldm(String nwzfldm)
	{
		this.nwzfldm = nwzfldm;
	}
	public String getNwzfldm()
	{
		return (nwzfldm == null ? "" : nwzfldm);
	}
	public void setPclxdm(String pclxdm)
	{
		this.pclxdm = pclxdm;
	}
	public String getPclxdm()
	{
		return (pclxdm == null ? "" : pclxdm);
	}
	public void setQylxdm(String qylxdm)
	{
		this.qylxdm = qylxdm;
	}
	public String getQylxdm()
	{
		return (qylxdm == null ? "" : qylxdm);
	}
	public void setDjzclxmc(String djzclxmc)
	{
		this.djzclxmc = djzclxmc;
	}
	public String getDjzclxmc()
	{
		return (djzclxmc == null ? "" : djzclxmc);
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
	public void setSwdjzzldm(String swdjzzldm)
	{
		this.swdjzzldm = swdjzzldm;
	}
	public String getSwdjzzldm()
	{
		return (swdjzzldm == null ? "" : swdjzzldm);
	}
	public void setZhgxsj(Timestamp zhgxsj)
	{
		this.zhgxsj = zhgxsj;
	}
	public Timestamp getZhgxsj()
	{
		return zhgxsj;
	}
	public void setZxbs(String zxbs)
	{
		this.zxbs = zxbs;
	}
	public String getZxbs()
	{
		return (zxbs == null ? "" : zxbs);
	}
	@Override
	public String getOptionCode() {
		// TODO Auto-generated method stub
		return this.djzclxdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return this.djzclxmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "djzclxdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.DJ_DM_DJZCLX";
	}
}
