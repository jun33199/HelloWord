package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Wed Jan 14 15:52:35 CST 2015
 * Table:    DMDB.SB_DM_QYSDS_QTKJZD
 * Comments: 企业所得税适用其他会计制度
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;
import java.util.*;

import com.lscdz.util.codetable.CodeTableInterface;

import yangjian.frame.util.*;

public class sb_dm_qysds_qtkjzz implements  CodeTableInterface , java.io.Serializable
{
	private String	kjzzdm;	//会计制度代码
	private String	kjzzmc;	//会计制度名称
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	zfbs;	//作废标识，0：有效，1：作废

	public void setKjzzdm(String kjzzdm)
	{
		this.kjzzdm = kjzzdm;
	}
	public String getKjzzdm()
	{
		return (kjzzdm == null ? "" : kjzzdm);
	}
	public void setKjzzmc(String kjzzmc)
	{
		this.kjzzmc = kjzzmc;
	}
	public String getKjzzmc()
	{
		return (kjzzmc == null ? "" : kjzzmc);
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
	@Override
	public String getOptionCode() {
		// TODO Auto-generated method stub
		return kjzzdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return kjzzmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "kjzzdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.SB_DM_QYSDS_QTKJZZ";
	}
}
