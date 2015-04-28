package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Tue Nov 25 10:15:57 CST 2014
 * Table:    DMDB.GY_DM_GJDQ
 * Comments: ���������
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import com.lscdz.util.codetable.CodeTableInterface;

public class gy_dm_gjdq implements  CodeTableInterface,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	gjdqdm;	//�������������
	private String	gjdqmc;	//�������������
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	xssx;	//��ʾ˳��
	private Timestamp	zhgxsj;	//������ʱ��
	private String	zxbs;	//ע����ʶ

	public void setGjdqdm(String gjdqdm)
	{
		this.gjdqdm = gjdqdm;
	}
	public String getGjdqdm()
	{
		return (gjdqdm == null ? "" : gjdqdm);
	}
	public void setGjdqmc(String gjdqmc)
	{
		this.gjdqmc = gjdqmc;
	}
	public String getGjdqmc()
	{
		return (gjdqmc == null ? "" : gjdqmc);
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
	public void setXssx(String xssx)
	{
		this.xssx = xssx;
	}
	public String getXssx()
	{
		return (xssx == null ? "" : xssx);
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
		return "gjdqdm";
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return "gjdqmc";
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "xssx,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.Gy_Dm_Gjdq";
	}
}
