/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.web;

import java.util.*;

import com.ttsoft.framework.form.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Form��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class GdzysShqrForm extends BaseForm {

	
	public GdzysShqrForm() {}
   
	//��˰������
	private String nsrmc;
	
	//���������
	private String jsjdm;
	
	//ռ�����ĺ�
	private String zdpwh;
	
	//�걨�����к�
	private String sbbxlh;
	
	//��ѯ���
	private List infList; 
	
	//��Ϣ�������
	private int num = -1;
	
	//���ȷ����Ϣ����
	private int shqrnum;
	
	//�����Ϣ---�걨���к�
	private String[] shqrinf;
	
	//�������
	private String shzl;
	
	//�о����ʱ��
	private String sjshsj;
	
	//�걨״̬
	private String sbzt;
	
	//�Ƿ��о�����
	private String sfsjsp;
	
	//�����˲�ѯ
	private String cjrcx;
	
/*------------------------------------------------------------------------------------------------------------------------*/
	
	
	public String getNsrmc() {
		return nsrmc;
	}




	public String getSjshsj() {
		return sjshsj;
	}




	public void setSjshsj(String sjshsj) {
		this.sjshsj = sjshsj;
	}




	public String getCjrcx() {
		return cjrcx;
	}




	public void setCjrcx(String cjrcx) {
		this.cjrcx = cjrcx;
	}




	public String getShzl() {
		return shzl;
	}




	public void setShzl(String shzl) {
		this.shzl = shzl;
	}




	public String getSbzt() {
		return sbzt;
	}


	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}


	public String getSfsjsp() {
		return sfsjsp;
	}


	public void setSfsjsp(String sfsjsp) {
		this.sfsjsp = sfsjsp;
	}


	public String[] getShqrinf() {
		return shqrinf;
	}


	public void setShqrinf(String[] shqrinf) {
		this.shqrinf = shqrinf;
	}


	public int getShqrnum() {
		return shqrnum;
	}


	public void setShqrnum(int shqrnum) {
		this.shqrnum = shqrnum;
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

	
}
