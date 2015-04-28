package com.lscdz.qysds.common.service.sfgl.bo;

/**
 * Created by CodeGenerator at Wed Dec 17 10:31:30 CST 2014
 * Table:    DMDB.KJ_DM_YSKM
 * Comments: Ԥ���Ŀ�����
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;



@SuppressWarnings("serial")
public class Yskm implements java.io.Serializable
{
	private String	nd;	//���
	private String	yskmdm;	//Ԥ���Ŀ����
	private String	yskmmc;	//Ԥ���Ŀ����
	private String	zxbs;	//ע����ʶ
	private String	budget_type;	//Ԥ���Ŀ���� 2��ʾ��˰���ÿ�Ŀ������Ϊ�ǵ�˰���ÿ�Ŀ
	private String	ccbs;	//��α�ʶ
	private String	fjddm;	//���ڵ����
	private String	late_fee_sign;	//�������ɽ��־,������Ԥ���Ŀ�������ɽ𷣿�Ԥ���Ŀ
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	mrszsmdm;	//Ĭ��˰��˰Ŀ����
	private BigDecimal	qxfcbl;	//���طֳɱ���
	private String	revenue_plan;	//�ƻ�˰����������־,1��ʾ˰�����룬0��ʾ��˰������
	private String	revenue_type;	//˰���������ͣ�1��ʾ˰�����룬0��ʾ��������
	private BigDecimal	sjfcbl;	//�оֳַɱ���
	private Timestamp	sxrq;	//��Ч����
	private String	ysjcdm;	//Ԥ�㼶�δ���
	private BigDecimal	zyfcbl;	//����ֳɱ���

	public void setNd(String nd)
	{
		this.nd = nd;
	}
	public String getNd()
	{
		return (nd == null ? "" : nd);
	}
	public void setYskmdm(String yskmdm)
	{
		this.yskmdm = yskmdm;
	}
	public String getYskmdm()
	{
		return (yskmdm == null ? "" : yskmdm);
	}
	public void setYskmmc(String yskmmc)
	{
		this.yskmmc = yskmmc;
	}
	public String getYskmmc()
	{
		return (yskmmc == null ? "" : yskmmc);
	}
	public void setZxbs(String zxbs)
	{
		this.zxbs = zxbs;
	}
	public String getZxbs()
	{
		return (zxbs == null ? "" : zxbs);
	}
	public void setBudget_type(String budget_type)
	{
		this.budget_type = budget_type;
	}
	public String getBudget_type()
	{
		return (budget_type == null ? "" : budget_type);
	}
	public void setCcbs(String ccbs)
	{
		this.ccbs = ccbs;
	}
	public String getCcbs()
	{
		return (ccbs == null ? "" : ccbs);
	}
	public void setFjddm(String fjddm)
	{
		this.fjddm = fjddm;
	}
	public String getFjddm()
	{
		return (fjddm == null ? "" : fjddm);
	}
	public void setLate_fee_sign(String late_fee_sign)
	{
		this.late_fee_sign = late_fee_sign;
	}
	public String getLate_fee_sign()
	{
		return (late_fee_sign == null ? "" : late_fee_sign);
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
	public void setMrszsmdm(String mrszsmdm)
	{
		this.mrszsmdm = mrszsmdm;
	}
	public String getMrszsmdm()
	{
		return (mrszsmdm == null ? "" : mrszsmdm);
	}
	public void setQxfcbl(BigDecimal qxfcbl)
	{
		this.qxfcbl = qxfcbl;
	}
	public BigDecimal getQxfcbl()
	{
		return qxfcbl;
	}
	public void setRevenue_plan(String revenue_plan)
	{
		this.revenue_plan = revenue_plan;
	}
	public String getRevenue_plan()
	{
		return (revenue_plan == null ? "" : revenue_plan);
	}
	public void setRevenue_type(String revenue_type)
	{
		this.revenue_type = revenue_type;
	}
	public String getRevenue_type()
	{
		return (revenue_type == null ? "" : revenue_type);
	}
	public void setSjfcbl(BigDecimal sjfcbl)
	{
		this.sjfcbl = sjfcbl;
	}
	public BigDecimal getSjfcbl()
	{
		return sjfcbl;
	}
	public void setSxrq(Timestamp sxrq)
	{
		this.sxrq = sxrq;
	}
	public Timestamp getSxrq()
	{
		return sxrq;
	}
	public void setYsjcdm(String ysjcdm)
	{
		this.ysjcdm = ysjcdm;
	}
	public String getYsjcdm()
	{
		return (ysjcdm == null ? "" : ysjcdm);
	}
	public void setZyfcbl(BigDecimal zyfcbl)
	{
		this.zyfcbl = zyfcbl;
	}
	public BigDecimal getZyfcbl()
	{
		return zyfcbl;
	}
}
