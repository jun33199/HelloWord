package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Thu Jan 08 14:34:37 CST 2015
 * Table:    DMDB.SF_DM_ZYZHLYZL
 * Comments: ��Դ�ۺ�������������
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import com.lscdz.util.codetable.CodeTableInterface;

public class sf_dm_zyzhlyzl implements CodeTableInterface,java.io.Serializable
{
	private static final long serialVersionUID = 5695138642315639055L;
	private String	zyzhlyzldm;	//��Դ�ۺ������������
	private String	zyzhlyzlmc;	//��Դ�ۺ�������������
	private String	zfbs;	//���ϱ�ʶ��0����Ч��1������

	public void setZyzhlyzldm(String zyzhlyzldm)
	{
		this.zyzhlyzldm = zyzhlyzldm;
	}
	public String getZyzhlyzldm()
	{
		return (zyzhlyzldm == null ? "" : zyzhlyzldm);
	}
	public void setZyzhlyzlmc(String zyzhlyzlmc)
	{
		this.zyzhlyzlmc = zyzhlyzlmc;
	}
	public String getZyzhlyzlmc()
	{
		return (zyzhlyzlmc == null ? "" : zyzhlyzlmc);
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
		return zyzhlyzldm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return zyzhlyzlmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "ZYZHLYZLDM,ASC";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "WHERE ZFBS='0'";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.SF_DM_ZYZHLYZL";
	}
}
