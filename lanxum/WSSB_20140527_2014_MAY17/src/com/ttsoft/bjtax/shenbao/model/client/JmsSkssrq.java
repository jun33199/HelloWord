package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �����걨ģ��</p>
 * <p>Description: ����˰�걨�ض�˰����������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author tujb
 * @version 1.0 201404
 */
public class JmsSkssrq implements Serializable
{

	/**
     * ˰��������ʼ���ڳ���
     */
	private String skssksrq;

    /**
     * ˰�������������ڳ���
     */
	private String skssjsrq;

	public String getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
}
