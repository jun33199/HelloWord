package com.lscdz.qysds.application.jmsba2014.base.vo;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


public class JmsbaBaseVo {
	/**
	 * ���������
	 */
	private String jsjdm;
	/**
	 * ��˰������
	 */
	private String nsrmc;
	/**
	 * ��˰��ʶ���
	 */
	private String nsrsbh;
	/**
	 * ��˰��״̬
	 */
	private String nsrzt;
	
	/**
	 * ��ϵ�˵绰
	 */
	private String lxrdh;
	/**
	 * ˰�������֯��������
	 */
	private String swjgzzjgmc;
	/**
	 * ˰�������֯��������
	 */
	private String swjgzzjgdm;
	/**
	 * �������
	 */
	private String band;
	/**
	 * ����������
	 */
	private String basqbh;
	/**
	 * ���ⱸ���������
	 */
	private String jmbasxdm;
	/**
	 * ���ⱸ����������
	 */
	private String jmbasxmc;
	/**
	 * �������ʹ��룬0���������룬1����������
	 */
	private String sqlxdm;
	/**
	 * ����״̬���룬1������δ�ύ��2������δ��ˣ�3���ύδ��ˣ�4�������ͨ����5�����δͨ��
	 */
	private String sqzt;
	/**
	 * ����������,0-�¶�,1-����,2-���
	 */
	private String bbqlx;
	/**
	 * �ں� ����BBLX���ֲ�ͬ�ڵ��걨,����,BBQLX=2���ںſ�ʼΪ1
	 */
	private String qh;
	/**
	 * ˰������ʱ����
	 */
	private String sksssqq;
	/**
	 * ˰������ʱ��ֹ
	 */
	private String sksssqz;
	/**
	 * ��������
	 */
	private int czlx;
	/**
	 * ����˰���������嵥
	 */
	private List<JmsbaZlqdVo> zlqdList=new ArrayList<JmsbaZlqdVo>();
	
	/**
	 * ��Ҫ���������ļ���
	 */
	private String zyzcyjwjm;
	/**
	 * ��Ҫ���������ļ���
	 */
	private String zyzcyjwjh;
	/**
	 * �������������
	 */
	private String basqwsh;
	
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getBasqbh() {
		return basqbh;
	}
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	public String getJmbasxmc() {
		return jmbasxmc;
	}
	public void setJmbasxmc(String jmbasxmc) {
		this.jmbasxmc = jmbasxmc;
	}
	public String getSqlxdm() {
		return sqlxdm;
	}
	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getBbqlx() {
		return bbqlx;
	}
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}
	public String getQh() {
		return qh;
	}
	public void setQh(String qh) {
		this.qh = qh;
	}
	public String getSksssqq() {
		return sksssqq;
	}
	public void setSksssqq(String sksssqq) {
		this.sksssqq = sksssqq;
	}
	public String getSksssqz() {
		return sksssqz;
	}
	public void setSksssqz(String sksssqz) {
		this.sksssqz = sksssqz;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrzt() {
		return nsrzt;
	}
	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}
	public int getCzlx() {
		return czlx;
	}
	public void setCzlx(int czlx) {
		this.czlx = czlx;
	}
	public List<JmsbaZlqdVo> getZlqdList() {
		return zlqdList;
	}
	public void setZlqdList(List<JmsbaZlqdVo> zlqdList) {
		this.zlqdList = zlqdList;
	}
	public String getLxrdh() {
		return lxrdh;
	}
	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}
	public String getZyzcyjwjm() {
		return zyzcyjwjm;
	}
	public void setZyzcyjwjm(String zyzcyjwjm) {
		this.zyzcyjwjm = zyzcyjwjm;
	}
	public String getZyzcyjwjh() {
		return zyzcyjwjh;
	}
	public void setZyzcyjwjh(String zyzcyjwjh) {
		this.zyzcyjwjh = zyzcyjwjh;
	}
	public String getBasqwsh() {
		return basqwsh;
	}
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	
}
