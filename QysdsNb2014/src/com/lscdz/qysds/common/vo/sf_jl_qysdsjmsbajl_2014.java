package com.lscdz.qysds.common.vo;

/**
 * Created by CodeGenerator at Thu Mar 19 17:49:04 CST 2015
 * Table:    SFDB.SF_JL_QYSDSJMSBAJL_2014
 * Comments: ��ҵ����˰����˰������¼����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;
import java.util.*;
import yangjian.frame.util.*;

public class sf_jl_qysdsjmsbajl_2014 implements java.io.Serializable
{
	private String	bafs;	//������ʽ,1-Ԥ�ɱ���,2-��ɱ���
	private String	band;	//�������
	private String	basqbh;	//����������
	private String	basqwsh;	//�������������
	private String	bbqlx;	//����������,0-�¶�,1-����,2-���
	private String	cjr;	//������
	private Timestamp	cjrq;	//��������
	private String	jmbasxdm;	//���ⱸ���������
	private String	jsjdm;	//���������
	private String	jyxgzgdpzwjjwh;	//��������ʸ����׼�ļ���֤�飩���ĺţ���ţ�
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	qh;	//�ں� ����BBLX���ֲ�ͬ�ڵ��걨,����,BBQLX=2���ںſ�ʼΪ1
	private Timestamp	sksssqq;	//˰������ʱ����
	private Timestamp	sksssqz;	//˰������ʱ��ֹ
	private String	sqlxdm;	//�������ʹ��룬0���������룬1����������
	private String	swjgzzjgdm;	//˰�������֯��������
	private String	szdm;	//˰�ִ���
	private Timestamp	xsyhqjq;	//�����Ż��ڼ���
	private Timestamp	xsyhqjz;	//�����Ż��ڼ�ֹ
	private String	zyzcyjwjjwh;	//��Ҫ���������ļ����ĺ�
	private BigDecimal	bajmbl;	//�����������
	private BigDecimal	bajmse;	//��������˰��
	private String	htr;	//������
	private Timestamp	htrq;	//��������
	private String	shr;	//�����
	private Timestamp	shsj;	//���ʱ��
	private String	sqzt;	//����״̬���룬1������δ�ύ��2������δ��ˣ�3���ύδ��ˣ�4�������ͨ����5�����δͨ��
	private String	tjr;	//�ύ��
	private Timestamp	tjsj;	//�ύʱ��
	private Timestamp	wjyxqjzrq;	//�ļ���֤�飩��Ч�ڽ�ֹ����
	private Timestamp	wjyxqqsrq;	//�ļ���֤�飩��Ч����ʼ����
	private String	ygqksm;	//�й����˵��
	private String	zfr;	//������
	private Timestamp	zfrq;	//��������
	private String	zfsm;	//����˵��

	public void setBafs(String bafs)
	{
		this.bafs = bafs;
	}
	public String getBafs()
	{
		return (bafs == null ? "" : bafs);
	}
	public void setBand(String band)
	{
		this.band = band;
	}
	public String getBand()
	{
		return (band == null ? "" : band);
	}
	public void setBasqbh(String basqbh)
	{
		this.basqbh = basqbh;
	}
	public String getBasqbh()
	{
		return (basqbh == null ? "" : basqbh);
	}
	public void setBasqwsh(String basqwsh)
	{
		this.basqwsh = basqwsh;
	}
	public String getBasqwsh()
	{
		return (basqwsh == null ? "" : basqwsh);
	}
	public void setBbqlx(String bbqlx)
	{
		this.bbqlx = bbqlx;
	}
	public String getBbqlx()
	{
		return (bbqlx == null ? "" : bbqlx);
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
	public void setJmbasxdm(String jmbasxdm)
	{
		this.jmbasxdm = jmbasxdm;
	}
	public String getJmbasxdm()
	{
		return (jmbasxdm == null ? "" : jmbasxdm);
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
	}
	public void setJyxgzgdpzwjjwh(String jyxgzgdpzwjjwh)
	{
		this.jyxgzgdpzwjjwh = jyxgzgdpzwjjwh;
	}
	public String getJyxgzgdpzwjjwh()
	{
		return (jyxgzgdpzwjjwh == null ? "" : jyxgzgdpzwjjwh);
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
	public void setQh(String qh)
	{
		this.qh = qh;
	}
	public String getQh()
	{
		return (qh == null ? "" : qh);
	}
	public void setSksssqq(Timestamp sksssqq)
	{
		this.sksssqq = sksssqq;
	}
	public Timestamp getSksssqq()
	{
		return sksssqq;
	}
	public void setSksssqz(Timestamp sksssqz)
	{
		this.sksssqz = sksssqz;
	}
	public Timestamp getSksssqz()
	{
		return sksssqz;
	}
	public void setSqlxdm(String sqlxdm)
	{
		this.sqlxdm = sqlxdm;
	}
	public String getSqlxdm()
	{
		return (sqlxdm == null ? "" : sqlxdm);
	}
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setSzdm(String szdm)
	{
		this.szdm = szdm;
	}
	public String getSzdm()
	{
		return (szdm == null ? "" : szdm);
	}
	public void setXsyhqjq(Timestamp xsyhqjq)
	{
		this.xsyhqjq = xsyhqjq;
	}
	public Timestamp getXsyhqjq()
	{
		return xsyhqjq;
	}
	public void setXsyhqjz(Timestamp xsyhqjz)
	{
		this.xsyhqjz = xsyhqjz;
	}
	public Timestamp getXsyhqjz()
	{
		return xsyhqjz;
	}
	public void setZyzcyjwjjwh(String zyzcyjwjjwh)
	{
		this.zyzcyjwjjwh = zyzcyjwjjwh;
	}
	public String getZyzcyjwjjwh()
	{
		return (zyzcyjwjjwh == null ? "" : zyzcyjwjjwh);
	}
	public void setBajmbl(BigDecimal bajmbl)
	{
		this.bajmbl = bajmbl;
	}
	public BigDecimal getBajmbl()
	{
		return bajmbl;
	}
	public void setBajmse(BigDecimal bajmse)
	{
		this.bajmse = bajmse;
	}
	public BigDecimal getBajmse()
	{
		return bajmse;
	}
	public void setHtr(String htr)
	{
		this.htr = htr;
	}
	public String getHtr()
	{
		return (htr == null ? "" : htr);
	}
	public void setHtrq(Timestamp htrq)
	{
		this.htrq = htrq;
	}
	public Timestamp getHtrq()
	{
		return htrq;
	}
	public void setShr(String shr)
	{
		this.shr = shr;
	}
	public String getShr()
	{
		return (shr == null ? "" : shr);
	}
	public void setShsj(Timestamp shsj)
	{
		this.shsj = shsj;
	}
	public Timestamp getShsj()
	{
		return shsj;
	}
	public void setSqzt(String sqzt)
	{
		this.sqzt = sqzt;
	}
	public String getSqzt()
	{
		return (sqzt == null ? "" : sqzt);
	}
	public void setTjr(String tjr)
	{
		this.tjr = tjr;
	}
	public String getTjr()
	{
		return (tjr == null ? "" : tjr);
	}
	public void setTjsj(Timestamp tjsj)
	{
		this.tjsj = tjsj;
	}
	public Timestamp getTjsj()
	{
		return tjsj;
	}
	public void setWjyxqjzrq(Timestamp wjyxqjzrq)
	{
		this.wjyxqjzrq = wjyxqjzrq;
	}
	public Timestamp getWjyxqjzrq()
	{
		return wjyxqjzrq;
	}
	public void setWjyxqqsrq(Timestamp wjyxqqsrq)
	{
		this.wjyxqqsrq = wjyxqqsrq;
	}
	public Timestamp getWjyxqqsrq()
	{
		return wjyxqqsrq;
	}
	public void setYgqksm(String ygqksm)
	{
		this.ygqksm = ygqksm;
	}
	public String getYgqksm()
	{
		return (ygqksm == null ? "" : ygqksm);
	}
	public void setZfr(String zfr)
	{
		this.zfr = zfr;
	}
	public String getZfr()
	{
		return (zfr == null ? "" : zfr);
	}
	public void setZfrq(Timestamp zfrq)
	{
		this.zfrq = zfrq;
	}
	public Timestamp getZfrq()
	{
		return zfrq;
	}
	public void setZfsm(String zfsm)
	{
		this.zfsm = zfsm;
	}
	public String getZfsm()
	{
		return (zfsm == null ? "" : zfsm);
	}
}
