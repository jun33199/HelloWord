package com.ttsoft.bjtax.smsb.sbzl.cx.qyjbcwzb.web;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: ˰�������֯������Ϣ����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class SwjgzzjgInfo implements Serializable {

	/**
	 * ��ѯ������
	 */
	private int index;

	private String swjgzzjgdm;

	private String swjgzzjgmc;

	private int data1;

	private int data2;

	private int data3;

	/**
	 * ���캯��
	 */
	public SwjgzzjgInfo() {
	}

	////////////////////////getter and setter//////////////////////////////////

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getData1() {
		return data1;
	}

	public void setData1(int data1) {
		this.data1 = data1;
	}

	public int getData2() {
		return data2;
	}

	public void setData2(int data2) {
		this.data2 = data2;
	}

	public int getData3() {
		return data3;
	}

	public void setData3(int data3) {
		this.data3 = data3;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}

}