package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class GdzysJmsCancleForm extends BaseForm{
	private String localUser;//��ǰ�����û�
	private List jgList;//��˻����б�
	private String nsrmc;//��˰������
	private String jsjdm;//���������
	private String pzzdwh;//��׼ռ���ĺ�
	private String sbbxlh;//�걨�����к�
	private String jmszmbh;//����˰֤�����
	private String status;//��ѯ״̬
	private String shjg;//��˻���
	private String jsmj;//��˰���
	private String selfswjgdm;//�Լ�����˰��ִ���
	private List dataList =new ArrayList();//�������ݵļ���
	private List jmszmList=new ArrayList();//����˰֤���ļ���
	
	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}
	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}
	private String flag="";//�Ƿ����ɹ�
	

	//��ѯ�����ʾ��ʶ(1:��ʾ��ʾ��Ϣ��0������ʾ��ʾ��Ϣ)
	private String cxJgTsFlag="0";
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List getJmszmList() {
		return jmszmList;
	}
	public void setJmszmList(List jmszmList) {
		this.jmszmList = jmszmList;
	}
	public String getLocalUser() {
		return localUser;
	}
	public void setLocalUser(String localUser) {
		this.localUser = localUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShjg() {
		return shjg;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getSelfswjgdm() {
		return selfswjgdm;
	}
	public void setSelfswjgdm(String selfswjgdm) {
		this.selfswjgdm = selfswjgdm;
	}
	public List getJgList() {
		return jgList;
	}
	public void setJgList(List jgList) {
		this.jgList = jgList;
	}
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
	public String getPzzdwh() {
		return pzzdwh;
	}
	public void setPzzdwh(String pzzdwh) {
		this.pzzdwh = pzzdwh;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public String getJmszmbh() {
		return jmszmbh;
	}
	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
	}
	public String getJsmj() {
		return jsmj;
	}
	public void setJsmj(String jsmj) {
		this.jsmj = jsmj;
	}
	public String getJmmj() {
		return jmmj;
	}
	public void setJmmj(String jmmj) {
		this.jmmj = jmmj;
	}
	private String jmmj;//�������
	public GdzysJmsCancleForm(){
		
	}

}
