package com.ttsoft.bjtax.smsb.zjyjmsb.cx;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: �پ�ҵ����˰�걨��ѯVo</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjycxlxVO 
        implements Serializable {
	public ZjycxlxVO() {
	  }
    //��˰������
	private String nsrmc;
	//���������
	private String jsjdm;
	//�����¸�ʧҵ��Ա����
	private String xnxgsyrs;
	//����˰���
	private String jmslb;
	//���ܼ���ʱ��  --��ʱд���걨����
	private String xsjmsj;
	//Ӫҵ˰
	private String yys;
	//����ά������˰
	private String cjs;
	//�����Ѹ���
	private String jyffj;
	//��������˰
	private String grsds;
	//��ҵ����˰
	private String qysds;
	//�ϼ�
	private String hj;
	//˰�������֯��������
	private String swjgzzjgmc;
	
	public String getCjs() {
		return cjs;
	}
	public void setCjs(String cjs) {
		this.cjs = cjs;
	}
	public String getGrsds() {
		return grsds;
	}
	public void setGrsds(String grsds) {
		this.grsds = grsds;
	}
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getJmslb() {
		return jmslb;
	}
	public void setJmslb(String jmslb) {
		this.jmslb = jmslb;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getJyffj() {
		return jyffj;
	}
	public void setJyffj(String jyffj) {
		this.jyffj = jyffj;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getQysds() {
		return qysds;
	}
	public void setQysds(String qysds) {
		this.qysds = qysds;
	}
	public String getXnxgsyrs() {
		return xnxgsyrs;
	}
	public void setXnxgsyrs(String xnxgsyrs) {
		this.xnxgsyrs = xnxgsyrs;
	}
	public String getXsjmsj() {
		return xsjmsj;
	}
	public void setXsjmsj(String xsjmsj) {
		this.xsjmsj = xsjmsj;
	}
	public String getYys() {
		return yys;
	}
	public void setYys(String yys) {
		this.yys = yys;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

}
