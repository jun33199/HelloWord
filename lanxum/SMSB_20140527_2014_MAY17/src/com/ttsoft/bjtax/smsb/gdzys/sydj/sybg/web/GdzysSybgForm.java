/*
 * <p>Title: ������˰��������ϵͳ���������걨--����ռ��˰</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;
/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ռ��˰˰Դ��Ϣ��ѯForm��</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysSybgForm  extends BaseForm {
	

	//��˰������
	private String nsrmc;
	
	//���������
	private String jsjdm;
	
	//ռ�����ĺ�
	private String zdpwh;
	
	//�걨���к�
	private String sbbxlh;
		
	//��ѯ���
	private List infList; 
	
	//��Ϣ�������
	private int num = -1;
	
	
	/*------------------------------------------------------------------------------------------------------------------------*/
	
	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getZdpwh() {
		return zdpwh;
	}

	public void setZdpwh(String zdpwh) {
		this.zdpwh = zdpwh;
	}

	

	public String getSbbxlh() {
		return sbbxlh;
	}

	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}

	public List getInfList() {
		return infList;
	}

	public void setInfList(List infList) {
		this.infList = infList;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
