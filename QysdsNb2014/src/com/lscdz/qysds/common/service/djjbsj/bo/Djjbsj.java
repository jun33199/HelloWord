package com.lscdz.qysds.common.service.djjbsj.bo;

/**
 * Created by CodeGenerator at Wed Dec 17 09:46:39 CST 2014
 * Table:    DJDB.DJ_JL_JBSJ
 * Comments: ˰��Ǽ�-��������
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;

@SuppressWarnings("serial")
public class Djjbsj implements java.io.Serializable
{
	private Timestamp	cjrq;	//����ʱ��
	private String	djsllx;	//�Ǽ���������
	private String	djzclxdm;	//�Ǽ�ע�����ʹ���
	private String	djzzldm;	//˰��Ǽ�֤�������
	private int	gdsgghbs;	//����˰���ܻ���ʶ
	private String	gjbzhydm;	//���ұ�׼��ҵ����
	private String	hsxsdm;	//������ʽ��Ԥ����ʽ����
	private String	jsjdm;	//���������
	private String	jydz;	//��Ӫ��ַ
	private String	jydzyb;	//��Ӫ��ַ�ʱ�
	private String	kjzddm;	//����ƶȴ���
	private Timestamp	kydjrq;	//˰��Ǽ�����
	private Timestamp	lrrq;	//¼��ʱ��
	private String	lsgxdm;	//������ϵ����
	private String	nsrmc;	//��˰������
	private String	nsrzt;	//��˰��״̬
	private Timestamp	qrrq;	//�Ǽ�ȷ������
	private String	qrry;	//�Ǽ�ȷ����Ա
	private String	scjxdm;	//�����������
	private Timestamp	slrq;	//�Ǽ���������
	private String	slry;	//�Ǽ�������Ա
	private String	swdjlx;	//˰��Ǽ����ʹ���
	private String	swdjzh;	//˰��Ǽ�֤��
	private String	swjgzzjgdm;	//˰�������֯��������
	private String	zcdz;	//ע���ַ
	private String	zcdzyb;	//ע���ַ�ʱ�
	private String	zczbbzdm;	//ע���ʱ���Ͷ���ܶ���ִ���
	private BigDecimal	zczbje;	//ע���ʱ���Ͷ���ܶ����Ԫ��
	private String	zgbmdm;	//�������ܲ��Ŵ���
	private Timestamp	cjsj;	//����ʱ��
	private Timestamp	hzfhrq;	//��׼�ֻ�����
	private String	hzfhry;	//��׼�ֻ�����
	private String	jydzlxdm;	//��Ӫ��ַ��ϵ�绰
	private String	jyfw;	//��Ӫ��Χ
	private String	qyzy;	//��ҵ��ҳ��ַ
	private String	sbdm;	//�걨��ʽ����
	private String	scbs;	//ɾ����ʶ
	private String	sfzjhm;	//�������֤������
	private String	sjlylxdm;	//������Դ���ʹ���
	private BigDecimal	wzztzblhj;	//����Ͷ�ʱ����ϼƣ�%��
	private Timestamp	xgsj;	//�޸�ʱ��
	private Timestamp	yhzrq;	//�黻֤����
	private String	yhzry;	//�黻֤��Ա
	private String	yyzzh;	//Ӫҵִ�պŻ���׼����֤�ջ���׼�����������
	private String	zcdzlxdh;	//ע���ַ��ϵ�绰
	private String	zhgxsj;	//������ʱ��
	private BigDecimal	zrrtzblhj;	//��Ȼ��Ͷ�ʱ����ϼƣ�%��
	private String	zzjgdm;	//��֯��������

	public void setCjrq(Timestamp cjrq)
	{
		this.cjrq = cjrq;
	}
	public Timestamp getCjrq()
	{
		return cjrq;
	}
	public void setDjsllx(String djsllx)
	{
		this.djsllx = djsllx;
	}
	public String getDjsllx()
	{
		return (djsllx == null ? "" : djsllx);
	}
	public void setDjzclxdm(String djzclxdm)
	{
		this.djzclxdm = djzclxdm;
	}
	public String getDjzclxdm()
	{
		return (djzclxdm == null ? "" : djzclxdm);
	}
	public void setDjzzldm(String djzzldm)
	{
		this.djzzldm = djzzldm;
	}
	public String getDjzzldm()
	{
		return (djzzldm == null ? "" : djzzldm);
	}
	public void setGdsgghbs(int gdsgghbs)
	{
		this.gdsgghbs = gdsgghbs;
	}
	public int getGdsgghbs()
	{
		return gdsgghbs;
	}
	public void setGjbzhydm(String gjbzhydm)
	{
		this.gjbzhydm = gjbzhydm;
	}
	public String getGjbzhydm()
	{
		return (gjbzhydm == null ? "" : gjbzhydm);
	}
	public void setHsxsdm(String hsxsdm)
	{
		this.hsxsdm = hsxsdm;
	}
	public String getHsxsdm()
	{
		return (hsxsdm == null ? "" : hsxsdm);
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
	}
	public void setJydz(String jydz)
	{
		this.jydz = jydz;
	}
	public String getJydz()
	{
		return (jydz == null ? "" : jydz);
	}
	public void setJydzyb(String jydzyb)
	{
		this.jydzyb = jydzyb;
	}
	public String getJydzyb()
	{
		return (jydzyb == null ? "" : jydzyb);
	}
	public void setKjzddm(String kjzddm)
	{
		this.kjzddm = kjzddm;
	}
	public String getKjzddm()
	{
		return (kjzddm == null ? "" : kjzddm);
	}
	public void setKydjrq(Timestamp kydjrq)
	{
		this.kydjrq = kydjrq;
	}
	public Timestamp getKydjrq()
	{
		return kydjrq;
	}
	public void setLrrq(Timestamp lrrq)
	{
		this.lrrq = lrrq;
	}
	public Timestamp getLrrq()
	{
		return lrrq;
	}
	public void setLsgxdm(String lsgxdm)
	{
		this.lsgxdm = lsgxdm;
	}
	public String getLsgxdm()
	{
		return (lsgxdm == null ? "" : lsgxdm);
	}
	public void setNsrmc(String nsrmc)
	{
		this.nsrmc = nsrmc;
	}
	public String getNsrmc()
	{
		return (nsrmc == null ? "" : nsrmc);
	}
	public void setNsrzt(String nsrzt)
	{
		this.nsrzt = nsrzt;
	}
	public String getNsrzt()
	{
		return (nsrzt == null ? "" : nsrzt);
	}
	public void setQrrq(Timestamp qrrq)
	{
		this.qrrq = qrrq;
	}
	public Timestamp getQrrq()
	{
		return qrrq;
	}
	public void setQrry(String qrry)
	{
		this.qrry = qrry;
	}
	public String getQrry()
	{
		return (qrry == null ? "" : qrry);
	}
	public void setScjxdm(String scjxdm)
	{
		this.scjxdm = scjxdm;
	}
	public String getScjxdm()
	{
		return (scjxdm == null ? "" : scjxdm);
	}
	public void setSlrq(Timestamp slrq)
	{
		this.slrq = slrq;
	}
	public Timestamp getSlrq()
	{
		return slrq;
	}
	public void setSlry(String slry)
	{
		this.slry = slry;
	}
	public String getSlry()
	{
		return (slry == null ? "" : slry);
	}
	public void setSwdjlx(String swdjlx)
	{
		this.swdjlx = swdjlx;
	}
	public String getSwdjlx()
	{
		return (swdjlx == null ? "" : swdjlx);
	}
	public void setSwdjzh(String swdjzh)
	{
		this.swdjzh = swdjzh;
	}
	public String getSwdjzh()
	{
		return (swdjzh == null ? "" : swdjzh);
	}
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setZcdz(String zcdz)
	{
		this.zcdz = zcdz;
	}
	public String getZcdz()
	{
		return (zcdz == null ? "" : zcdz);
	}
	public void setZcdzyb(String zcdzyb)
	{
		this.zcdzyb = zcdzyb;
	}
	public String getZcdzyb()
	{
		return (zcdzyb == null ? "" : zcdzyb);
	}
	public void setZczbbzdm(String zczbbzdm)
	{
		this.zczbbzdm = zczbbzdm;
	}
	public String getZczbbzdm()
	{
		return (zczbbzdm == null ? "" : zczbbzdm);
	}
	public void setZczbje(BigDecimal zczbje)
	{
		this.zczbje = zczbje;
	}
	public BigDecimal getZczbje()
	{
		return zczbje;
	}
	public void setZgbmdm(String zgbmdm)
	{
		this.zgbmdm = zgbmdm;
	}
	public String getZgbmdm()
	{
		return (zgbmdm == null ? "" : zgbmdm);
	}
	public void setCjsj(Timestamp cjsj)
	{
		this.cjsj = cjsj;
	}
	public Timestamp getCjsj()
	{
		return cjsj;
	}
	public void setHzfhrq(Timestamp hzfhrq)
	{
		this.hzfhrq = hzfhrq;
	}
	public Timestamp getHzfhrq()
	{
		return hzfhrq;
	}
	public void setHzfhry(String hzfhry)
	{
		this.hzfhry = hzfhry;
	}
	public String getHzfhry()
	{
		return (hzfhry == null ? "" : hzfhry);
	}
	public void setJydzlxdm(String jydzlxdm)
	{
		this.jydzlxdm = jydzlxdm;
	}
	public String getJydzlxdm()
	{
		return (jydzlxdm == null ? "" : jydzlxdm);
	}
	public void setJyfw(String jyfw)
	{
		this.jyfw = jyfw;
	}
	public String getJyfw()
	{
		return (jyfw == null ? "" : jyfw);
	}
	public void setQyzy(String qyzy)
	{
		this.qyzy = qyzy;
	}
	public String getQyzy()
	{
		return (qyzy == null ? "" : qyzy);
	}
	public void setSbdm(String sbdm)
	{
		this.sbdm = sbdm;
	}
	public String getSbdm()
	{
		return (sbdm == null ? "" : sbdm);
	}
	public void setScbs(String scbs)
	{
		this.scbs = scbs;
	}
	public String getScbs()
	{
		return (scbs == null ? "" : scbs);
	}
	public void setSfzjhm(String sfzjhm)
	{
		this.sfzjhm = sfzjhm;
	}
	public String getSfzjhm()
	{
		return (sfzjhm == null ? "" : sfzjhm);
	}
	public void setSjlylxdm(String sjlylxdm)
	{
		this.sjlylxdm = sjlylxdm;
	}
	public String getSjlylxdm()
	{
		return (sjlylxdm == null ? "" : sjlylxdm);
	}
	public void setWzztzblhj(BigDecimal wzztzblhj)
	{
		this.wzztzblhj = wzztzblhj;
	}
	public BigDecimal getWzztzblhj()
	{
		return wzztzblhj;
	}
	public void setXgsj(Timestamp xgsj)
	{
		this.xgsj = xgsj;
	}
	public Timestamp getXgsj()
	{
		return xgsj;
	}
	public void setYhzrq(Timestamp yhzrq)
	{
		this.yhzrq = yhzrq;
	}
	public Timestamp getYhzrq()
	{
		return yhzrq;
	}
	public void setYhzry(String yhzry)
	{
		this.yhzry = yhzry;
	}
	public String getYhzry()
	{
		return (yhzry == null ? "" : yhzry);
	}
	public void setYyzzh(String yyzzh)
	{
		this.yyzzh = yyzzh;
	}
	public String getYyzzh()
	{
		return (yyzzh == null ? "" : yyzzh);
	}
	public void setZcdzlxdh(String zcdzlxdh)
	{
		this.zcdzlxdh = zcdzlxdh;
	}
	public String getZcdzlxdh()
	{
		return (zcdzlxdh == null ? "" : zcdzlxdh);
	}
	public void setZhgxsj(String zhgxsj)
	{
		this.zhgxsj = zhgxsj;
	}
	public String getZhgxsj()
	{
		return (zhgxsj == null ? "" : zhgxsj);
	}
	public void setZrrtzblhj(BigDecimal zrrtzblhj)
	{
		this.zrrtzblhj = zrrtzblhj;
	}
	public BigDecimal getZrrtzblhj()
	{
		return zrrtzblhj;
	}
	public void setZzjgdm(String zzjgdm)
	{
		this.zzjgdm = zzjgdm;
	}
	public String getZzjgdm()
	{
		return (zzjgdm == null ? "" : zzjgdm);
	}
}
