/*
 * <p>Title: ������˰��������ϵͳ���������걨--����ռ��˰</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web;

import java.util.*;

import com.ttsoft.framework.form.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ռ��˰˰Դ��Ϣ��ѯForm��</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysSydjcxForm extends BaseForm {

	
	public GdzysSydjcxForm() {}
   
	//��˰������
	private String nsrmc;
	
	//���������
	private String jsjdm;
	
	//ռ�����ĺ�
	private String zdpwh;
	
	//�걨���к�
	private String sbxlh;
		
	//��ѯ���
	private List infList; 
	
	//��Ϣ�������
	private int num = -1;
	
	//��������
	private boolean detail = false;
	
	//��ѯ�˵�˰����ش���
	private String swjgdm;
	
/*------------------------------------------------------------------------------------------------------------------------*/
	
	
	public String getNsrmc() {
		return nsrmc;
	}

	public boolean isDetail() {
		return detail;
	}

	
	
	public String getSwjgdm() {
		return swjgdm;
	}

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getSbxlh() {
		return sbxlh;
	}

	public void setSbxlh(String sbxlh) {
		this.sbxlh = sbxlh;
	}

	public List getInfList() {
		return infList;
	}

	public void setInfList(List infList) {
		this.infList = infList;
	}

	
}
