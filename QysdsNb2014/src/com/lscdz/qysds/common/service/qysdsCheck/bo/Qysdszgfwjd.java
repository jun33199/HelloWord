package com.lscdz.qysds.common.service.qysdsCheck.bo;

/**
 * Created by CodeGenerator at Thu Dec 18 10:15:23 CST 2014
 * Table:    DJDB.DJ_JL_QYSDSZGFWJD
 * Comments: ��ҵ����˰���ܷ�Χ������
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;


public class Qysdszgfwjd implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	jdbxlh;	//���������к�
	private String	jdnd;	//�������
	private Timestamp	jdqsrq;	//������ʼ����
	private String	jsjdm;	//���������
	private String	cjr;	//������
	private Timestamp	cjrq;	//��������
	private Timestamp	jdjzrq;	//������ֹ����
	private String	jdlxdm;	//�������ʹ���
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������

	public void setJdbxlh(String jdbxlh)
	{
		this.jdbxlh = jdbxlh;
	}
	public String getJdbxlh()
	{
		return (jdbxlh == null ? "" : jdbxlh);
	}
	public void setJdnd(String jdnd)
	{
		this.jdnd = jdnd;
	}
	public String getJdnd()
	{
		return (jdnd == null ? "" : jdnd);
	}
	public void setJdqsrq(Timestamp jdqsrq)
	{
		this.jdqsrq = jdqsrq;
	}
	public Timestamp getJdqsrq()
	{
		return jdqsrq;
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
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
	public void setJdjzrq(Timestamp jdjzrq)
	{
		this.jdjzrq = jdjzrq;
	}
	public Timestamp getJdjzrq()
	{
		return jdjzrq;
	}
	public void setJdlxdm(String jdlxdm)
	{
		this.jdlxdm = jdlxdm;
	}
	public String getJdlxdm()
	{
		return (jdlxdm == null ? "" : jdlxdm);
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
}
