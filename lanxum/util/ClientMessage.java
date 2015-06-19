package com.lscdz.util;

import org.w3c.dom.Document;

import yangjian.frame.sso.UserData;

/**
 * �ͻ��˷��͹����ı��ģ�������
 * ��1��type: �������ͣ�����ȷ�������ҵ���磺��Ŀ�걨ҵ��
 * ��2��action���������ͣ�����ȷ������ҵ��ľ���������磺��Ŀ�걨ҵ���ά����鿴��
 * ��3��privilege��Ȩ����Ϣ��
 * ��4��doc���������� Document��
 * ��5��userData���û�Ȩ�����ݣ�
 * ��6��xmlText��ԭʼ xml����2014/02/28��
 * 
 * ���ĸ�ʽ��
 * <ClientMsg>
 * 	  <RequestAction Type=\"xxx\" Action=\"yyy\" Privilege=\"zzz\" />
 *    ...
 * </ClientMsg>
 * @author jasper
 * 2013/03/08
 */
public class ClientMessage {
	private String type;
	private String action;
	private String privilege;
	private Document doc;
	private UserData userData;
	private String xmlText;
	
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	public String getXmlText() {
		return xmlText;
	}
	public void setXmlText(String xmlText) {
		this.xmlText = xmlText;
	}
	public String getType() {
		return (type == null ? "" : type);
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYhid() {
		return userData.getYhid();
	}
	public String getYhmc() {
		return userData.getYhxm();
	}
	public String getAction() {
		return (action == null ? "" : action);
	}
	public void setAction(String action) {
		this.action = action;
	}	
	public String getPrivilege() {
		return (privilege == null ? "" : privilege);
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
}
