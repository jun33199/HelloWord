package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Jan 16 10:46:02 CST 2015
 * Table:    DMDB.DJ_DM_DJZCLX
 * Comments: �Ǽ�ע�����ʹ����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;

import com.lscdz.util.codetable.CodeTableInterface;

public class dj_dm_djzclx implements CodeTableInterface ,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	djzclxdm;	//�Ǽ�ע�����ʹ���
	private String	nwzfldm;	//�����ʷ������
	private String	pclxdm;	//�������ʹ���
	private String	qylxdm;	//��ҵ���ʹ���
	private String	djzclxmc;	//�Ǽ�ע����������
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	swdjzzldm;	//˰��Ǽ�֤�������
	private Timestamp	zhgxsj;	//������ʱ��
	private String	zxbs;	//ע����ʶ

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
