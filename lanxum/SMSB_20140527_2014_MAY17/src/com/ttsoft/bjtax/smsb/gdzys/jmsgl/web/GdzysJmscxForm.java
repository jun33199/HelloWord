package com.ttsoft.bjtax.smsb.gdzys.jmsgl.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * 
 * <p>Title: </p>
 * <p>Description:��ѯ����˰Form </p>
 * @author ������-���鲨
 * @version 1.0
 */
public class GdzysJmscxForm extends BaseForm{
	public GdzysJmscxForm(){
		
	}
	private String choice;
	private String nsrmc;//��˰������
	private String jsjdm;//���������
	private String pzzdwh;//��׼ռ���ĺ�

	//��ѯ�����ʾ��ʶ(1:��ʾ��ʾ��Ϣ��0������ʾ��ʾ��Ϣ)
	private String cxJgTsFlag="0";
	public String getCxJgTsFlag() {
		return cxJgTsFlag;
	}
	public void setCxJgTsFlag(String cxJgTsFlag) {
		this.cxJgTsFlag = cxJgTsFlag;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	private String sbxlh;//�걨���к�
	private List ztList;//״̬�б�
	private String status;//״̬
	private List jgList;//��˻����б�
	private String shjg;//��˻���
	private String cjr;//������
	private String cjrq;//��������
	public String getJmszmbh() {
		return jmszmbh;
	}
	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
	}
	private String jmszmbh;//����˰֤�����
	
	private String sjzdmj;//ʵ��ռ�����
	private String ynse;//Ӧ��˰��
	private String sbsj;//�걨ʱ��
	private List dataList=new ArrayList();//��ѯ�������
	private List jmszmList=new ArrayList();//����˰֤��list
	private String selfswjgdm;//�Լ�����˰��ִ���
	

	
	public String getSelfswjgdm() {
		return selfswjgdm;
	}
	public void setSelfswjgdm(String selfswjgdm) {
		this.selfswjgdm = selfswjgdm;
	}
	public List getJmszmList() {
		return jmszmList;
	}
	public void setJmszmList(List jmszmList) {
		this.jmszmList = jmszmList;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public List getZtList() {
		return ztList;
	}
	public void setZtList(List ztList) {
		this.ztList = ztList;
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
	public String getSbxlh() {
		return sbxlh;
	}
	public void setSbxlh(String sbxlh) {
		this.sbxlh = sbxlh;
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
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getSjzdmj() {
		return sjzdmj;
	}
	public void setSjzdmj(String sjzdmj) {
		this.sjzdmj = sjzdmj;
	}
	public String getYnse() {
		return ynse;
	}
	public void setYnse(String ynse) {
		this.ynse = ynse;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	

}
