package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;


/**
 * <p>
 * Title: ��ҵ����˰���ⱸ��-����From
 * </p>
 * <p>
 * Description: ��ҵ����˰���ⱸ��-����From
 * </p>
 * 
 * @author �������� - ����
 * @version 1.0
 */
public class QysdsJmsbajlMainForm extends BaseForm{
	// �������������
	private String basqwsh;
	// ����������
	private String basqbh;
	// �������
	private String band;
	// ���������
	private String jsjdm;
	// ���ⱸ���������
	private String jmbasxdm;
	// ˰�������֯��������
	private String swjgzzjgdm;
	// ˰�ִ���
	private String szdm;
	// ����״̬����
	private String sqzt;
	// �������ʹ��� 0���������룬1����������
	private String sqlxdm;
	// �����ӡ¼�����������˰��
	private String bajmse;
	// �����ӡ¼��������������
	private String bajmbl;
	// �����ӡ¼�������˰����ִ�����
	private String jmszczxqk;
	// �����ӡ¼�����ʼ����
	private String qsrq;
	// �����ӡ¼�����������
	private String jzrq;
	//�ύ��
	private String tjr;
	//�ύʱ��
	private String tjsj;
	//�����
	private String shr;
	//���ʱ��
	private String shsj;
	//��ѯ����������������
	private String filter_basqbh;	
	//��ѯ���������������
	private String filter_jsjdm;
	//��ѯ��������˰������
	private String filter_nsrmc;
	//��ѯ�������������
	private String filter_band;
	//��ѯ��������������
	private String filter_sqlx;
	//��ѯ����������״̬
	private String filter_sqzt;
	//��ѯ��������������˰�����
	private String filter_zgswjg;	
	//��ѯ����������˰��������
	private String filter_jmsbasx;
	//��ѯ��������������˰������б�
	private List filter_zgswjgList;
	//��ѯ����������˰���������б�
	private List filter_jmsbasxList;
	
	//�������������������
	private String add_jsjdm;
	//�����������������
	private String add_band;
	//���������� ����˰��������
	private String add_jmsbasx;
	
	//Ĭ�ϲ��ɱ༭��¼������
	private String mr_lrrq;
	//Ĭ�ϲ��ɱ༭���������
	private String mr_band;
	//Ĭ�ϲ��ɱ༭��¼����
	private String mr_lrr;
	
	//��������
	private String czlx;
	
	//���������ĵ�����
    private String zl ;
    //��˼��������ĵ�����
    private String shzl ;
	

	
	private String nsrmc;	
	private String zgsws;
	private String jjlx;
	private String sshy;
	private String qylb;	
	private String lxr;
	private String lxdh;

	
	private String rowsPerPage;//��¼��ûҳ��¼��
	private String currentPage;//��ؼ�¼��ǰ����ҳ
	
	private String zfsm;
	private String zfyy;
	private String qtzfyy;
	private String zfbglx;
	// �����ӡ¼�������˰����ִ�����
	
	public String getQtzfyy() {
		return qtzfyy;
	}
	public void setQtzfyy(String qtzfyy) {
		this.qtzfyy = qtzfyy;
	}
	public String getBasqwsh() {
		return basqwsh;
	}
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	public String getBasqbh() {
		return basqbh;
	}
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getSqlxdm() {
		return sqlxdm;
	}
	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}
	public String getQsrq() {
		return qsrq;
	}
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public String getFilter_jsjdm() {
		return filter_jsjdm;
	}
	public void setFilter_jsjdm(String filter_jsjdm) {
		this.filter_jsjdm = filter_jsjdm;
	}
	public String getFilter_nsrmc() {
		return filter_nsrmc;
	}
	public void setFilter_nsrmc(String filter_nsrmc) {
		this.filter_nsrmc = filter_nsrmc;
	}
	public String getFilter_band() {
		return filter_band;
	}
	public void setFilter_band(String filter_band) {
		this.filter_band = filter_band;
	}
	public String getFilter_sqlx() {
		return filter_sqlx;
	}
	public void setFilter_sqlx(String filter_sqlx) {
		this.filter_sqlx = filter_sqlx;
	}
	public String getFilter_sqzt() {
		return filter_sqzt;
	}
	public void setFilter_sqzt(String filter_sqzt) {
		this.filter_sqzt = filter_sqzt;
	}
	public String getFilter_jmsbasx() {
		return filter_jmsbasx;
	}
	public void setFilter_jmsbasx(String filter_jmsbasx) {
		this.filter_jmsbasx = filter_jmsbasx;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZgsws() {
		return zgsws;
	}
	public void setZgsws(String zgsws) {
		this.zgsws = zgsws;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getQylb() {
		return qylb;
	}
	public void setQylb(String qylb) {
		this.qylb = qylb;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getAdd_band() {
		return add_band;
	}
	public void setAdd_band(String add_band) {
		this.add_band = add_band;
	}
	public String getAdd_jmsbasx() {
		return add_jmsbasx;
	}
	public void setAdd_jmsbasx(String add_jmsbasx) {
		this.add_jmsbasx = add_jmsbasx;
	}
	public String getAdd_jsjdm() {
		return add_jsjdm;
	}
	public void setAdd_jsjdm(String add_jsjdm) {
		this.add_jsjdm = add_jsjdm;
	}
	public String getBajmbl() {
		return bajmbl;
	}
	public void setBajmbl(String bajmbl) {
		this.bajmbl = bajmbl;
	}
	public String getBajmse() {
		return bajmse;
	}
	public void setBajmse(String bajmse) {
		this.bajmse = bajmse;
	}
	public String getJmszczxqk() {
		return jmszczxqk;
	}
	public void setJmszczxqk(String jmszczxqk) {
		this.jmszczxqk = jmszczxqk;
	}
	public String getFilter_zgswjg() {
		return filter_zgswjg;
	}
	public void setFilter_zgswjg(String filter_zgswjg) {
		this.filter_zgswjg = filter_zgswjg;
	}
	public List getFilter_jmsbasxList() {
		return filter_jmsbasxList;
	}
	public void setFilter_jmsbasxList(List filter_jmsbasxList) {
		this.filter_jmsbasxList = filter_jmsbasxList;
	}
	public List getFilter_zgswjgList() {
		return filter_zgswjgList;
	}
	public void setFilter_zgswjgList(List filter_zgswjgList) {
		this.filter_zgswjgList = filter_zgswjgList;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getShzl() {
		return shzl;
	}
	public void setShzl(String shzl) {
		this.shzl = shzl;
	}
	public String getMr_band() {
		return mr_band;
	}
	public void setMr_band(String mr_band) {
		this.mr_band = mr_band;
	}
	public String getMr_lrr() {
		return mr_lrr;
	}
	public void setMr_lrr(String mr_lrr) {
		this.mr_lrr = mr_lrr;
	}
	public String getMr_lrrq() {
		return mr_lrrq;
	}
	public void setMr_lrrq(String mr_lrrq) {
		this.mr_lrrq = mr_lrrq;
	}
	public String getFilter_basqbh() {
		return filter_basqbh;
	}
	public void setFilter_basqbh(String filter_basqbh) {
		this.filter_basqbh = filter_basqbh;
	}
	public String getZfsm() {
		return zfsm;
	}
	public void setZfsm(String zfsm) {
		this.zfsm = zfsm;
	}
	public String getZfyy() {
		return zfyy;
	}
	public void setZfyy(String zfyy) {
		this.zfyy = zfyy;
	}
	public String getZfbglx() {
		return zfbglx;
	}
	public void setZfbglx(String zfbglx) {
		this.zfbglx = zfbglx;
	}

}
