package com.lscdz.qysds.common.vo;

/**
 * Created by CodeGenerator at Wed Jan 07 16:12:39 CST 2015
 * Table:    SBDB.SB_JL_QYSDS_NSRJBXXB_2014
 * Comments: 2014����ҵ����˰��˰�˻�����Ϣ��
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;
import java.util.*;
import yangjian.frame.util.*;

public class sb_jl_qysds_nsrjbxxb_2014 implements java.io.Serializable
{
	private String	jsjdm;	//���������
	private String	nd;	//˰�����
	private String	nsrzt;	//��˰��״̬
	private String	sbnd;	//�걨���
	private String	syjdlx;	//˰Դ��������
	private String	bbmsf;	//���������� �籨��id,���硰1;3;4;5��
	private String	chcbjjff;	//����ɱ��Ƽ۷������μ������DMDB.SB_DM_QYSDS_CHCBJJFF �ɶ�ѡ|������
	private String	cjr;	//������
	private Timestamp	cjrq;	//��������
	private String	csgjfxzhjzhy;	//���¹��ҷ����ƺͽ�ֹ��ҵ Y���� N����
	private long	cyrs;	//��ҵ����
	private String	czjwgljy;	//���ھ���������� Y���� N����
	private String	dwtzqyid;	//����Ͷ����ҵID
	private String	fzjgsfftqysds;	//��֧�����Ƿ��̯��ҵ����˰  Y���� N����
	private String	gdzczjff;	//�̶��ʲ��۾ɷ������μ������DMDB.SB_DM_QYSDS_GDZCZJFF �ɶ�ѡ|������
	private String	hznsqy;	//������˰��ҵ Y���� N����
	private String	hznsqylx;	//������˰��ҵ���� 01���ܻ��� 02�������������ܻ���
	private String	hzxshsff;	//������ʧ���㷽�� 01�����ַ� 02��ֱ�Ӻ�����
	private String	jwzzkgjmqy;	//�������ʿعɾ�����ҵ Y���� N����
	private String	jzbwb;	//���˱�λ�� 01������� 02������
	private String	kjdacfd;	//��Ƶ����Ĵ�ŵ�
	private String	kjhsrj;	//��ƺ������
	private String	kjzchgjsffsbh;	//������ߺ͹����Ƿ����仯 Y���� N����
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	nsrmc;	//��˰������
	private String	nsrsbh;	//��˰��ʶ��ţ�˰��Ǽ�֤�ţ�
	private String	qtsykjzzhkjzz;	//�������õĻ��׼������ƶȣ�01��һ����ҵ 02��������ҵ 03����ҵ��λ��
	private String	qyzygdid;	//��ҵ��Ҫ�ɶ�ID
	private String	sblx;	//�걨���� 01�������걨 02�������걨 03�������걨
	private String	sdsjsff;	//����˰���㷽�� 01��Ӧ��˰� 02���ʲ���ծ��ծ�� 03������
	private String	sfjrqsq;	//�Ƿ����������  Y���� N����
	private String	sfwcqs;	//�Ƿ��������    Y���� N����
	private Timestamp	sksssqq;	//˰������ʱ����
	private Timestamp	sksssqz;	//˰������ʱ��ֹ
	private String	sndsfxxwlqy;	//������Ƿ�ΪС��΢����ҵ  Y���� N����
	private String	ssgs;	//���й�˾
	private String	ssgslx;	//���й�˾���� 01������ 02������
	private String	sshy;	//������ҵ
	private String	sshymc;	//������ҵ����
	private String	swjgzzjgdm;	//˰�������֯��������
	private String	swjgzzjgmc;	//˰�������֯��������
	private String	sykjzzhkjzz;	//���õĻ��׼������ƶȣ��μ������DMDB.SB_DM_QYSDS_KJZD��
	private Timestamp	syqdjzrq;	//˰Դ������ֹ����
	private Timestamp	syqdqsrq;	//˰Դ������ʼ����
	private String	version;	//�汾��
	private BigDecimal	zczbje;	//ע���ʱ����
	private BigDecimal	zcze;	//�ʲ��ܶ�
	private String sqlx; //��������
	
	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
	}
	public void setNd(String nd)
	{
		this.nd = nd;
	}
	public String getNd()
	{
		return (nd == null ? "" : nd);
	}
	public void setNsrzt(String nsrzt)
	{
		this.nsrzt = nsrzt;
	}
	public String getNsrzt()
	{
		return (nsrzt == null ? "" : nsrzt);
	}
	public void setSbnd(String sbnd)
	{
		this.sbnd = sbnd;
	}
	public String getSbnd()
	{
		return (sbnd == null ? "" : sbnd);
	}
	public void setSyjdlx(String syjdlx)
	{
		this.syjdlx = syjdlx;
	}
	public String getSyjdlx()
	{
		return (syjdlx == null ? "" : syjdlx);
	}
	public void setBbmsf(String bbmsf)
	{
		this.bbmsf = bbmsf;
	}
	public String getBbmsf()
	{
		return (bbmsf == null ? "" : bbmsf);
	}
	public void setChcbjjff(String chcbjjff)
	{
		this.chcbjjff = chcbjjff;
	}
	public String getChcbjjff()
	{
		return (chcbjjff == null ? "" : chcbjjff);
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
	public void setCsgjfxzhjzhy(String csgjfxzhjzhy)
	{
		this.csgjfxzhjzhy = csgjfxzhjzhy;
	}
	public String getCsgjfxzhjzhy()
	{
		return (csgjfxzhjzhy == null ? "" : csgjfxzhjzhy);
	}
	public void setCyrs(long cyrs)
	{
		this.cyrs = cyrs;
	}
	public long getCyrs()
	{
		return cyrs;
	}
	public void setCzjwgljy(String czjwgljy)
	{
		this.czjwgljy = czjwgljy;
	}
	public String getCzjwgljy()
	{
		return (czjwgljy == null ? "" : czjwgljy);
	}
	public void setDwtzqyid(String dwtzqyid)
	{
		this.dwtzqyid = dwtzqyid;
	}
	public String getDwtzqyid()
	{
		return (dwtzqyid == null ? "" : dwtzqyid);
	}
	public void setFzjgsfftqysds(String fzjgsfftqysds)
	{
		this.fzjgsfftqysds = fzjgsfftqysds;
	}
	public String getFzjgsfftqysds()
	{
		return (fzjgsfftqysds == null ? "" : fzjgsfftqysds);
	}
	public void setGdzczjff(String gdzczjff)
	{
		this.gdzczjff = gdzczjff;
	}
	public String getGdzczjff()
	{
		return (gdzczjff == null ? "" : gdzczjff);
	}
	public void setHznsqy(String hznsqy)
	{
		this.hznsqy = hznsqy;
	}
	public String getHznsqy()
	{
		return (hznsqy == null ? "" : hznsqy);
	}
	public void setHznsqylx(String hznsqylx)
	{
		this.hznsqylx = hznsqylx;
	}
	public String getHznsqylx()
	{
		return (hznsqylx == null ? "" : hznsqylx);
	}
	public void setHzxshsff(String hzxshsff)
	{
		this.hzxshsff = hzxshsff;
	}
	public String getHzxshsff()
	{
		return (hzxshsff == null ? "" : hzxshsff);
	}
	public void setJwzzkgjmqy(String jwzzkgjmqy)
	{
		this.jwzzkgjmqy = jwzzkgjmqy;
	}
	public String getJwzzkgjmqy()
	{
		return (jwzzkgjmqy == null ? "" : jwzzkgjmqy);
	}
	public void setJzbwb(String jzbwb)
	{
		this.jzbwb = jzbwb;
	}
	public String getJzbwb()
	{
		return (jzbwb == null ? "" : jzbwb);
	}
	public void setKjdacfd(String kjdacfd)
	{
		this.kjdacfd = kjdacfd;
	}
	public String getKjdacfd()
	{
		return (kjdacfd == null ? "" : kjdacfd);
	}
	public void setKjhsrj(String kjhsrj)
	{
		this.kjhsrj = kjhsrj;
	}
	public String getKjhsrj()
	{
		return (kjhsrj == null ? "" : kjhsrj);
	}
	public void setKjzchgjsffsbh(String kjzchgjsffsbh)
	{
		this.kjzchgjsffsbh = kjzchgjsffsbh;
	}
	public String getKjzchgjsffsbh()
	{
		return (kjzchgjsffsbh == null ? "" : kjzchgjsffsbh);
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
	public void setNsrmc(String nsrmc)
	{
		this.nsrmc = nsrmc;
	}
	public String getNsrmc()
	{
		return (nsrmc == null ? "" : nsrmc);
	}
	public void setNsrsbh(String nsrsbh)
	{
		this.nsrsbh = nsrsbh;
	}
	public String getNsrsbh()
	{
		return (nsrsbh == null ? "" : nsrsbh);
	}
	public void setQtsykjzzhkjzz(String qtsykjzzhkjzz)
	{
		this.qtsykjzzhkjzz = qtsykjzzhkjzz;
	}
	public String getQtsykjzzhkjzz()
	{
		return (qtsykjzzhkjzz == null ? "" : qtsykjzzhkjzz);
	}
	public void setQyzygdid(String qyzygdid)
	{
		this.qyzygdid = qyzygdid;
	}
	public String getQyzygdid()
	{
		return (qyzygdid == null ? "" : qyzygdid);
	}
	public void setSblx(String sblx)
	{
		this.sblx = sblx;
	}
	public String getSblx()
	{
		return (sblx == null ? "" : sblx);
	}
	public void setSdsjsff(String sdsjsff)
	{
		this.sdsjsff = sdsjsff;
	}
	public String getSdsjsff()
	{
		return (sdsjsff == null ? "" : sdsjsff);
	}
	public void setSfjrqsq(String sfjrqsq)
	{
		this.sfjrqsq = sfjrqsq;
	}
	public String getSfjrqsq()
	{
		return (sfjrqsq == null ? "" : sfjrqsq);
	}
	public void setSfwcqs(String sfwcqs)
	{
		this.sfwcqs = sfwcqs;
	}
	public String getSfwcqs()
	{
		return (sfwcqs == null ? "" : sfwcqs);
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
	public void setSndsfxxwlqy(String sndsfxxwlqy)
	{
		this.sndsfxxwlqy = sndsfxxwlqy;
	}
	public String getSndsfxxwlqy()
	{
		return (sndsfxxwlqy == null ? "" : sndsfxxwlqy);
	}
	public void setSsgs(String ssgs)
	{
		this.ssgs = ssgs;
	}
	public String getSsgs()
	{
		return (ssgs == null ? "" : ssgs);
	}
	public void setSsgslx(String ssgslx)
	{
		this.ssgslx = ssgslx;
	}
	public String getSsgslx()
	{
		return (ssgslx == null ? "" : ssgslx);
	}
	public void setSshy(String sshy)
	{
		this.sshy = sshy;
	}
	public String getSshy()
	{
		return (sshy == null ? "" : sshy);
	}
	public void setSshymc(String sshymc)
	{
		this.sshymc = sshymc;
	}
	public String getSshymc()
	{
		return (sshymc == null ? "" : sshymc);
	}
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setSwjgzzjgmc(String swjgzzjgmc)
	{
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getSwjgzzjgmc()
	{
		return (swjgzzjgmc == null ? "" : swjgzzjgmc);
	}
	public void setSykjzzhkjzz(String sykjzzhkjzz)
	{
		this.sykjzzhkjzz = sykjzzhkjzz;
	}
	public String getSykjzzhkjzz()
	{
		return (sykjzzhkjzz == null ? "" : sykjzzhkjzz);
	}
	public void setSyqdjzrq(Timestamp syqdjzrq)
	{
		this.syqdjzrq = syqdjzrq;
	}
	public Timestamp getSyqdjzrq()
	{
		return syqdjzrq;
	}
	public void setSyqdqsrq(Timestamp syqdqsrq)
	{
		this.syqdqsrq = syqdqsrq;
	}
	public Timestamp getSyqdqsrq()
	{
		return syqdqsrq;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getVersion()
	{
		return (version == null ? "" : version);
	}
	public void setZczbje(BigDecimal zczbje)
	{
		this.zczbje = zczbje;
	}
	public BigDecimal getZczbje()
	{
		return zczbje;
	}
	public void setZcze(BigDecimal zcze)
	{
		this.zcze = zcze;
	}
	public BigDecimal getZcze()
	{
		return zcze;
	}
}
