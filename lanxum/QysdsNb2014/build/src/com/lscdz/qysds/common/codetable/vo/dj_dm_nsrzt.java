package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Tue Dec 30 15:46:55 CST 2014
 * Table:    DMDB.DJ_DM_NSRZT
 * Comments: ��˰��״̬�����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import com.lscdz.util.codetable.CodeTableInterface;

public class dj_dm_nsrzt implements CodeTableInterface ,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	nsrztdm;	//��˰��״̬����
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	nsrztmc;	//��˰��״̬����
	private String	zxbs;	//ע����ʶ

	public void setNsrztdm(String nsrztdm)
	{
		this.nsrztdm = nsrztdm;
	}
	public String getNsrztdm()
	{
		return (nsrztdm == null ? "" : nsrztdm);
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
	public void setNsrztmc(String nsrztmc)
	{
		this.nsrztmc = nsrztmc;
	}
	public String getNsrztmc()
	{
		return (nsrztmc == null ? "" : nsrztmc);
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
		return this.nsrztdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return this.nsrztmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "nsrztdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.DJ_DM_NSRZT";
	}
}
