package com.ttsoft.bjtax.smsb.lwpk.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ttsoft.framework.form.BaseForm;



/**
 * <p>Title: ������˰��������ϵͳ--˰����</p>
 * <p>Description: ��ʱ�ƻ���Form</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author �����з����ţ���������
 * @version 1.0
 */
public class PKTaskForm extends BaseForm {

	
	//���
	private String xh;
	/*��ѯ������*/
	//ִ�����
	private String nd;
	//ִ���¶�
	private String yd;
	//��ѯ��������
	private String cxrwlx;
	
	//�Ƿ��ʼ��
	private String isinit;
	//ִ������
	private String zxrq;
	//ִ��ʱ��
	private String zxsj;
	//��������
	private String zxrwlx;
	//��ʱ�����б�
	private List pkTaskList = new ArrayList();
	
	/*��ʾ�����˵�ѡ��*/
	//ʱ���б�
	List zxsjList = new ArrayList();
	
	//�¶��б�
	List cxydList = new ArrayList();
	//�����б�
	List cxlxList = new ArrayList();
	//���������б�
	List cxlxmcList = new ArrayList();

	

	public String getCxrwlx() {
		return cxrwlx;
	}

	public void setCxrwlx(String cxrwlx) {
		this.cxrwlx = cxrwlx;
	}

	public String getZxrwlx() {
		return zxrwlx;
	}

	public void setZxrwlx(String zxrwlx) {
		this.zxrwlx = zxrwlx;
	}

	public List getCxlxList() {
		return cxlxList;
	}

	public void setCxlxList(List cxlxList) {
		this.cxlxList = cxlxList;
	}

	public List getCxlxmcList() {
		return cxlxmcList;
	}

	public void setCxlxmcList(List cxlxmcList) {
		this.cxlxmcList = cxlxmcList;
	}

	public List getCxydList() {
		return cxydList;
	}

	public void setCxydList(List cxydList) {
		this.cxydList = cxydList;
	}

	public List getZxsjList() {
		return zxsjList;
	}

	public void setZxsjList(List zxsjList) {
		this.zxsjList = zxsjList;
	}

	public String getIsinit() {
		return isinit;
	}

	public void setIsinit(String isinit) {
		this.isinit = isinit;
	}

	public String getNd() {
		return nd;
	}
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	public List getPkTaskList() {
		return pkTaskList;
	}
	public void setPkTaskList(List pkTaskList) {
		this.pkTaskList = pkTaskList;
	}
	public String getZxrq() {
		return zxrq;
	}
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	public String getZxsj() {
		return zxsj;
	}
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	
}
