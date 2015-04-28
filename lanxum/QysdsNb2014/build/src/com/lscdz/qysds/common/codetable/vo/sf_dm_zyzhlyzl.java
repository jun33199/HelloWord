package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Thu Jan 08 14:34:37 CST 2015
 * Table:    DMDB.SF_DM_ZYZHLYZL
 * Comments: 资源综合利用种类代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import com.lscdz.util.codetable.CodeTableInterface;

public class sf_dm_zyzhlyzl implements CodeTableInterface,java.io.Serializable
{
	private static final long serialVersionUID = 5695138642315639055L;
	private String	zyzhlyzldm;	//资源综合利用种类代码
	private String	zyzhlyzlmc;	//资源综合利用种类名称
	private String	zfbs;	//作废标识，0：有效，1：作废

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
