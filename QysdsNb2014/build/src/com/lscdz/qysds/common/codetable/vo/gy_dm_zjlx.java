package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Oct 31 14:25:17 CST 2014
 * Table:    DMDB.GY_DM_ZJLX
 * Comments: ֤�����ʹ����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import com.lscdz.util.codetable.CodeTableInterface;

public class gy_dm_zjlx implements CodeTableInterface,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	zjlxdm;	//֤�����ʹ���
	private String	clfs;	//����ʽ
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private Timestamp	zhgxsj;	//������ʱ��
	private String	zjlxmc;	//֤����������
	private String	zxbs;	//ע����ʶ

	public void setZjlxdm(String zjlxdm)
	{
		this.zjlxdm = zjlxdm;
	}
	public String getZjlxdm()
	{
		return (zjlxdm == null ? "" : zjlxdm);
	}
	public void setClfs(String clfs)
	{
		this.clfs = clfs;
	}
	public String getClfs()
	{
		return (clfs == null ? "" : clfs);
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
	public void setZhgxsj(Timestamp zhgxsj)
	{
		this.zhgxsj = zhgxsj;
	}
	public Timestamp getZhgxsj()
	{
		return zhgxsj;
	}
	public void setZjlxmc(String zjlxmc)
	{
		this.zjlxmc = zjlxmc;
	}
	public String getZjlxmc()
	{
		return (zjlxmc == null ? "" : zjlxmc);
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
		return zjlxdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return zjlxmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "zjlxdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.GY_DM_ZJLX";
	}
}
