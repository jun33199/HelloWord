package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �����걨ģ��</p>
 * <p>Description: ����˰�걨������Ŀ������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author tujb
 * @version 1.0 201404
 */
public class JmsxmInfor implements Serializable
{

	// ������Ŀ����
    private String jmslxdm;
    // ������Ŀ����
    private String jmslxmc;
    //�����
    private String wh;
    // ������Ŀ˰�ִ���
    private String szdm;
	public String getJmslxdm() {
		return jmslxdm;
	}
	public void setJmslxdm(String jmslxdm) {
		this.jmslxdm = jmslxdm;
	}
	public String getJmslxmc() {
		return jmslxmc;
	}
	public void setJmslxmc(String jmslxmc) {
		this.jmslxmc = jmslxmc;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}

}
