package com.lscdz.qysds.application.jmsbayj2014.main.vo.inner;

public class JmsbaSxdm implements java.io.Serializable{
	
	private static final long serialVersionUID = 7270574421526326677L;
	private String	jmbasxDm;	//���ⱸ���������
	private String	jmbasxMc;	//���ⱸ����������
	private String	jmlbdm;	//���������룬0����˰�� ��1����˰��
	private String	balxdm;	//�������ʹ��룬0�����ʣ�1������
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
