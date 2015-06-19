package com.lscdz.qysds.common.codetable.vo;

/**
 * Created by CodeGenerator at Fri Jan 16 17:47:57 CST 2015
 * Table:    DMDB.SB_DM_QYSDS_KJZD
 * Comments: ��ҵ����˰���õĻ��׼������ƶ�
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import com.lscdz.util.codetable.CodeTableInterface;

public class sb_dm_qysds_kjzz implements CodeTableInterface ,java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String	kjzzdm;	//����ƶȴ���
	private String	kjzzmc;	//����ƶ�����
	private String	kjzdqc;	//����ƶ�ȫ��
	private String	cjr;	//������
	private Timestamp	cjrq;	//��������
	private String	lrr;	//¼����
	private Timestamp	lrrq;	//¼������
	private String	zfbs;	//���ϱ�ʶ��0����Ч��1������

	
	public String getKjzzdm() {
		return kjzzdm;
	}
	public void setKjzzdm(String kjzzdm) {
		this.kjzzdm = kjzzdm;
	}
	public String getKjzzmc() {
		return kjzzmc;
	}
	public void setKjzzmc(String kjzzmc) {
		this.kjzzmc = kjzzmc;
	}
	public void setKjzdqc(String kjzdqc)
	{
		this.kjzdqc = kjzdqc;
	}
	public String getKjzdqc()
	{
		return (kjzdqc == null ? "" : kjzdqc);
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
		return kjzzdm;
	}
	@Override
	public String getOptionName() {
		// TODO Auto-generated method stub
		return kjzzmc;
	}
	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return "kjzzdm,asc";
	}
	@Override
	public String getSqlWhere() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTableName() {
		return "DMDB.SB_DM_QYSDS_KJZZ";
	}
}
