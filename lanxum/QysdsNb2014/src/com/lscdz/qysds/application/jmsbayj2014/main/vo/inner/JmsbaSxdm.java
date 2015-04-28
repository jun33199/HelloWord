package com.lscdz.qysds.application.jmsbayj2014.main.vo.inner;

public class JmsbaSxdm implements java.io.Serializable{
	
	private static final long serialVersionUID = 7270574421526326677L;
	private String	jmbasxDm;	//减免备案事项代码
	private String	jmbasxMc;	//减免备案事项名称
	private String	jmlbdm;	//减免类别代码，0：免税额 ，1：免税基
	private String	balxdm;	//备案类型代码，0：单笔，1：多条
	public void setJmbasxDm(String jmbasxDm)
	{
		this.jmbasxDm = jmbasxDm;
	}
	public String getJmbasxDm()
	{
		return (jmbasxDm == null ? "" : jmbasxDm);
	}
	public void setJmbasxMc(String jmbasxMc)
	{
		this.jmbasxMc = jmbasxMc;
	}
	public String getJmbasxMc()
	{
		return (jmbasxMc == null ? "" : jmbasxMc);
	}
	public void setJmlbdm(String jmlbdm)
	{
		this.jmlbdm = jmlbdm;
	}
	public String getJmlbdm()
	{
		return (jmlbdm == null ? "" : jmlbdm);
	}
	public void setBalxdm(String balxdm)
	{
		this.balxdm = balxdm;
	}
	public String getBalxdm()
	{
		return (balxdm == null ? "" : balxdm);
	}
}
