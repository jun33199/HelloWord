package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Mar 06 17:12:54 CST 2015
 * Table:    DMDB.SB_DM_QYSDS_GXJSLYMX
 * Comments: ���¼�����ϸ�����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;

import com.lscdz.util.codetable.CodeTableInterface;

public class sb_dm_qysds_gxjslymx implements CodeTableInterface,java.io.Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String	gxjslymxdm;	//���¼�����ϸ����
	private String	gxjslymxmc;	//���¼�����ϸ����
	private String	cjr;	//������
	private Timestamp	cjrq;	//��������
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	zcbs;	//��α�ʶ
	private String	zfbs;	//���ϱ�ʶ��0����Ч��1������

	public void setGxjslymxdm(String gxjslymxdm)
	{
		this.gxjslymxdm = gxjslymxdm;
	}
	public String getGxjslymxdm()
	{
		return (gxjslymxdm == null ? "" : gxjslymxdm);
	}
	public void setGxjslymxmc(String gxjslymxmc)
	{
		this.gxjslymxmc = gxjslymxmc;
	}
	public String getGxjslymxmc()
	{
		return (gxjslymxmc == null ? "" : gxjslymxmc);
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
	public void setZcbs(String zcbs)
	{
		this.zcbs = zcbs;
	}
	public String getZcbs()
	{
		return (zcbs == null ? "" : zcbs);
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
		return gxjslymxdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return gxjslymxmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "gxjslymxdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.SB_DM_QYSDS_GXJSLYMX";
	}
}
