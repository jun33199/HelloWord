package com.lscdz.qysds.common.codetable.vo;

import com.lscdz.util.codetable.CodeTableInterface;

/**
 * Created by CodeGenerator at Wed Jan 14 11:03:16 CST 2015
 * Table:    DMDB.GY_DM_GJBZHY
 * Comments: 国家标准行业代码表
 * Any question, ask the author YangJian! jasperjobs@163.com
 */


public class gy_dm_gjbzhy implements  CodeTableInterface , java.io.Serializable
{
	private static final long serialVersionUID = -8111485827862960737L;
	private String	gjbzhydm;	//国家标准行业代码(小类)
	private String	ccbs;	//层次标识 0表示一级节点 1表示中间节点 2表示叶子节点
	private String	cyfldm;	//产业分类代码
	private String	dfshydm;	//地方税行业代码
	private String	gjbzhymc;	//国家标准行业名称
	private String	hymldm;	//行业门类代码
	private String	qysdshydm;	//企业所得税行业代码
	private String	qysdszsfsqbdm;	//企业所得税征收方式区别代码
	private String	zghydm;	//征管行业代码
	private String	zxbs;	//注销标识

	public void setGjbzhydm(String gjbzhydm)
	{
		this.gjbzhydm = gjbzhydm;
	}
	public String getGjbzhydm()
	{
		return (gjbzhydm == null ? "" : gjbzhydm);
	}
	public void setCcbs(String ccbs)
	{
		this.ccbs = ccbs;
	}
	public String getCcbs()
	{
		return (ccbs == null ? "" : ccbs);
	}
	public void setCyfldm(String cyfldm)
	{
		this.cyfldm = cyfldm;
	}
	public String getCyfldm()
	{
		return (cyfldm == null ? "" : cyfldm);
	}
	public void setDfshydm(String dfshydm)
	{
		this.dfshydm = dfshydm;
	}
	public String getDfshydm()
	{
		return (dfshydm == null ? "" : dfshydm);
	}
	public void setGjbzhymc(String gjbzhymc)
	{
		this.gjbzhymc = gjbzhymc;
	}
	public String getGjbzhymc()
	{
		return (gjbzhymc == null ? "" : gjbzhymc);
	}
	public void setHymldm(String hymldm)
	{
		this.hymldm = hymldm;
	}
	public String getHymldm()
	{
		return (hymldm == null ? "" : hymldm);
	}
	public void setQysdshydm(String qysdshydm)
	{
		this.qysdshydm = qysdshydm;
	}
	public String getQysdshydm()
	{
		return (qysdshydm == null ? "" : qysdshydm);
	}
	public void setQysdszsfsqbdm(String qysdszsfsqbdm)
	{
		this.qysdszsfsqbdm = qysdszsfsqbdm;
	}
	public String getQysdszsfsqbdm()
	{
		return (qysdszsfsqbdm == null ? "" : qysdszsfsqbdm);
	}
	public void setZghydm(String zghydm)
	{
		this.zghydm = zghydm;
	}
	public String getZghydm()
	{
		return (zghydm == null ? "" : zghydm);
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
		return gjbzhydm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return gjbzhymc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "gjbzhydm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "DMDB.GY_DM_GJBZHY";
	}
}
