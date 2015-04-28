package com.lscdz.util;

import org.w3c.dom.Document;

import yangjian.frame.sso.UserData;

/**
 * 客户端发送过来的报文，包括：
 * （1）type: 报文类型，用于确定请求的业务，如：项目申报业务；
 * （2）action：操作类型，用于确定请求业务的具体操作，如：项目申报业务的维护或查看；
 * （3）privilege：权限信息；
 * （4）doc：报文数据 Document；
 * （5）userData：用户权限数据；
 * （6）xmlText：原始 xml。（2014/02/28）
 * 
 * 报文格式：
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
