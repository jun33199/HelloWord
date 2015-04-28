package com.lscdz.qysds.application.jmsba2014.main.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSqlx;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSxdm;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaZgswjg;

/**
 * ����˰������ʼ��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�JmsbaInitRes   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2015-1-6 ����3:37:35   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2015-1-6 ����3:37:35   
 * �޸ı�ע��   
 * @version  1.0
 */
public class JmsbaInitRes implements java.io.Serializable{

	private static final long serialVersionUID = -6370588162599672740L;
	
	private String add_band="";

	// ����˰�����������
	List<JmsbaSxdm> jmsbaSxdmList = new ArrayList<JmsbaSxdm>();

	//����˰������������
	List<JmsbaSqlx> jmsbaSqlxList = new ArrayList<JmsbaSqlx>();
	//����˰�����
	List<JmsbaZgswjg> jmsbaZgswjgList = new ArrayList<JmsbaZgswjg>();
	public List<JmsbaSxdm> getJmsbaSxdmList() {
		return jmsbaSxdmList;
	}

	public void setJmsbaSxdmList(List<JmsbaSxdm> jmsbaSxdmList) {
		this.jmsbaSxdmList = jmsbaSxdmList;
	}

	public String getAdd_band() {
		return add_band;
	}

	public void setAdd_band(String add_band) {
		this.add_band = add_band;
	}

	public List<JmsbaSqlx> getJmsbaSqlxList() {
		return jmsbaSqlxList;
	}

	public void setJmsbaSqlxList(List<JmsbaSqlx> jmsbaSqlxList) {
		this.jmsbaSqlxList = jmsbaSqlxList;
	}

	public List<JmsbaZgswjg> getJmsbaZgswjgList() {
		return jmsbaZgswjgList;
	}

	public void setJmsbaZgswjgList(List<JmsbaZgswjg> jmsbaZgswjgList) {
		this.jmsbaZgswjgList = jmsbaZgswjgList;
	}
	
	
}
