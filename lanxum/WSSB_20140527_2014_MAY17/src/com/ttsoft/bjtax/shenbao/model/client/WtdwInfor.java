package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ί�д��������ۡ����ۡ��ල���۵�λ�϶����
 * @author lsc-tujb
 * 2014.03.28
 */
public class WtdwInfor implements Serializable
{

	public WtdwInfor()
    {
    }
	
	String jsjdm;  //���������
	
	String szsmdm; //˰��˰Ŀ
	
	String sklxbs; //˰�����ͱ�ʶ
	
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getSzsmdm() {
		return szsmdm;
	}
	public void setSzsmdm(String szsmdm) {
		this.szsmdm = szsmdm;
	}
	public String getSklxbs() {
		return sklxbs;
	}
	public void setSklxbs(String sklxbs) {
		this.sklxbs = sklxbs;
	}
	
}
