package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model;

import java.io.Serializable;
import java.util.List;

public class DJXX implements Serializable {
	//���������
	public String jsjdm;
	//��˰������
	public String nsrmc;
	//��˰����ϸ��ַ
	public String nsrxxdz;
	//��˰��������ҵ
	public String nsrsshy;
	//��˰��������ҵ����
	public String nsrsshymc;
	//��ҵ�Ǽ�ע������
	public String qydjzclxdm;
	//��ҵ�Ǽ�ע����������
	public String qydjzclxmc;
	//��˰��ʶ���
	public String nsrsbh;
	//��������
	public String yhmc;
	//���д���
	public String yhdm;
	//�����ʺ�
	public String yhzh;
	//��ϵ������
	public String lxrxm;
	//��ϵ�绰
	public String lxdh;
	//zrr
	public String dm;
	//˰�������֯��������
	public String swjgzzjgdm;
	
/*	20131213 ��� start*/
	//���д����б�
	public String[] yhdm_list; 
	//���������б�
	public String[] yhmc_list;
	//�����˺��б�
	public String[] yhzh_list;
	/*	20131213 ��� end*/	
	
	
	public String[] getYhdm_list() {
		return yhdm_list;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public void setYhdm_list(String[] yhdm_list) {
		this.yhdm_list = yhdm_list;
	}
	public String[] getYhmc_list() {
		return yhmc_list;
	}
	public void setYhmc_list(String[] yhmc_list) {
		this.yhmc_list = yhmc_list;
	}
	public String[] getYhzh_list() {
		return yhzh_list;
	}
	public void setYhzh_list(String[] yhzh_list) {
		this.yhzh_list = yhzh_list;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getNsrsshymc() {
		return nsrsshymc;
	}
	public void setNsrsshymc(String nsrsshymc) {
		this.nsrsshymc = nsrsshymc;
	}
	public String getQydjzclxmc() {
		return qydjzclxmc;
	}
	public void setQydjzclxmc(String qydjzclxmc) {
		this.qydjzclxmc = qydjzclxmc;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrxxdz() {
		return nsrxxdz;
	}
	public void setNsrxxdz(String nsrxxdz) {
		this.nsrxxdz = nsrxxdz;
	}
	public String getNsrsshy() {
		return nsrsshy;
	}
	public void setNsrsshy(String nsrsshy) {
		this.nsrsshy = nsrsshy;
	}
	public String getQydjzclxdm() {
		return qydjzclxdm;
	}
	public void setQydjzclxdm(String qydjzclxdm) {
		this.qydjzclxdm = qydjzclxdm;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	

	
}
