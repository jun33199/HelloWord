package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web;


/**
 * <p>Title: �����ǼǱ�BO</p>
 *
 * <p>Description: ��¼�����ǼǱ����Ӧ�õļ�ֵ�����ں�̨����</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.Serializable;
import java.util.List;



public class DztzBO implements Serializable
{
	
	
	/**
	 * ���������
	 */
	private String jsjdm;
	
	/**
     * �۽�����������
     */
	private String kjywrmc;
	/**
     * �۽���������˰ʶ���
     */
	private String kjywrnssbh;
	/**
     * ��ϵ��
     */
	private String lxr;
	/**
     * �绰
     */
	private String dh;
	/**
     * ��ַ
     */
	private String dz;
	/**
     * �Ǿ�����ҵ����
     */
	private String fjmqymc;
	/**
     * ���𣨵�����
     */
	private String gbdq;	
	/**
     * ̨����Ϣ
     */
	private List tzsjxx;
	
	

	public String getKjywrmc() {
		return kjywrmc;
	}
	public void setKjywrmc(String kjywrmc) {
		this.kjywrmc = kjywrmc;
	}
	public String getKjywrnssbh() {
		return kjywrnssbh;
	}
	public void setKjywrnssbh(String kjywrnssbh) {
		this.kjywrnssbh = kjywrnssbh;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getFjmqymc() {
		return fjmqymc;
	}
	public void setFjmqymc(String fjmqymc) {
		this.fjmqymc = fjmqymc;
	}
	public String getGbdq() {
		return gbdq;
	}
	public void setGbdq(String gbdq) {
		this.gbdq = gbdq;
	}
	public List getTzsjxx() {
		return tzsjxx;
	}
	public void setTzsjxx(List tzsjxx) {
		this.tzsjxx = tzsjxx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	
	/**
	 * �Ƿ�չʾ��ѯ���
	 * @return
	 */
	public boolean zscxjg(){		
		return (tzsjxx ==null || tzsjxx.size()<=0)?false:true;
	}
	
	

	
	}
