package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
 * 
 * ��Ŀ���ƣ�wssb   
 * �����ƣ�Jnjpjsgzlx  ���ܼ��ż���������Ŀ 
 * ��������   ����˰���� 
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-14 ����9:59:03   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-14 ����9:59:03   
 * �޸ı�ע��   
 * @version  1.0
 */

public class Jnjpjsgzlx implements ORObject
{
	//���ܼ��ż���������Ŀ����
    private String jnjpjsgzxmdm;
    //���ܼ��ż���������Ŀ����
    private String jnjpjsgzxmmc;
    //��������
    private java.sql.Timestamp cjrq;
    //¼������
    private java.sql.Timestamp lrrq;
    //������
    private String cjr;
    //¼����
    private String lrr;
    //���ϱ�ʶ
    private String zfbs;
    public Jnjpjsgzlx()
    {
    }
	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}
	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}
	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}
	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}
	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getZfbs() {
		return zfbs;
	}
	public void setZfbs(String zfbs) {
		this.zfbs = zfbs;
	}
    
}