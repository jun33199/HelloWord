package com.ttsoft.bjtax.smsb.jmssb.web;

import java.io.Serializable;

/**
 * <p>Title: �����걨-�����걨����¼��-���òм��˾�ҵ��ҵӪҵ˰����˰��ѯͳ�Ʊ�</p>
 * <p>Description: ���òм��˾�ҵ��ҵ����˰��ʾ��Ϣ</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author xinyy
 * @version 1.0
 */
public class CjrjyjmsInfo implements Serializable {
	
	/**
	 * ���������
	 */
	private String jsjdm;
	
	/**
	 * ��ҵ����
	 */
	private String qymc;
	
	/**
	 * ��ѯ������
	 */
	private int indexId;
	
	/**
	 * �����������
	 */
	private String spbbh;
	
	/**
	 * �����Ӫҵ˰�޶�
	 */
	private String njzyysxe;
	
	/**
	 * ������ʼ����
	 */
	private String jzksrq;
	
	/**
	 * ������ֹ����
	 */
	private String jzjzrq;
	
	/**
	 * ʵ�ʼ���Ӫҵ˰��
	 */
	private String sjjzyyse;
	
	/**
	 * ���òм�ְ������
	 */
	private String azcjzgrs;

	public int getIndexId() {
		return indexId;
	}

	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}

	public String getSpbbh() {
		return spbbh;
	}

	public void setSpbbh(String spbbh) {
		this.spbbh = spbbh;
	}

	public String getNjzyysxe() {
		return njzyysxe;
	}

	public void setNjzyysxe(String njzyysxe) {
		this.njzyysxe = njzyysxe;
	}

	public String getJzksrq() {
		return jzksrq;
	}

	public void setJzksrq(String jzksrq) {
		this.jzksrq = jzksrq;
	}

	public String getJzjzrq() {
		return jzjzrq;
	}

	public void setJzjzrq(String jzjzrq) {
		this.jzjzrq = jzjzrq;
	}

	public String getSjjzyyse() {
		return sjjzyyse;
	}

	public void setSjjzyyse(String sjjzyyse) {
		this.sjjzyyse = sjjzyyse;
	}

	public String getAzcjzgrs() {
		return azcjzgrs;
	}

	public void setAzcjzgrs(String azcjzgrs) {
		this.azcjzgrs = azcjzgrs;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
}
