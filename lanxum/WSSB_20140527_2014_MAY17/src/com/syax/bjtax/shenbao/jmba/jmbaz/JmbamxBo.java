/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.jmbaz;

import java.io.Serializable;

import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JmbamxBo implements Serializable{
	public static final String FORM_ATTRIBUTE_NAME="jmbamxbo";
	public static final String BAMXXH_ATTRIBUTE_NAME="xh";
	public static final String BAZTDM_ATTRIBUTE_NAME="baztdm";
	/**
	 * ����������
	 */
	private String basqbh;
	/**
	 * �������������
	 */
	private String basqwsh;

	/**
	 *	�������
	 */
	private String band;

	/**
	 * ���ⱸ���������
	 */
	private String jmbasxdm;


	/**
	 * ״̬���� 0������1�޸ģ�2�ٴΣ�9�鿴
	 */
	private String ztdm;

	/**
	 * ����07���ھ����ʶ 
	 */
	private String jnjwbz;

	/**
	 * ��������
	 */
	private JmbaZbVO zbvo;

	/**
	 * ���
	 */
	private String xh;

	/**
	 * @return Returns the band.
	 */
	public String getBand() {
		return band;
	}
	/**
	 * @param band The band to set.
	 */
	public void setBand(String band) {
		this.band = band;
	}
	/**
	 * @return Returns the basqbh.
	 */
	public String getBasqbh() {
		return basqbh;
	}
	/**
	 * @param basqbh The basqbh to set.
	 */
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	/**
	 * @return Returns the basqwsh.
	 */
	public String getBasqwsh() {
		return basqwsh;
	}
	/**
	 * @param basqwsh The basqwsh to set.
	 */
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	/**
	 * @return Returns the jmbasxdm.
	 */
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	/**
	 * @param jmbasxdm The jmbasxdm to set.
	 */
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	/**
	 * @return Returns the ztdm.
	 */
	public String getZtdm() {
		return ztdm;
	}
	/**
	 * @param ztdm The ztdm to set.
	 */
	public void setZtdm(String ztdm) {
		this.ztdm = ztdm;
	}
	/**
	 * @return Returns the xh.
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh The xh to set.
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	
	/**
	 * @return Returns the zbvo.
	 */
	public JmbaZbVO getZbvo() {
		return zbvo;
	}
	/**
	 * @param zbvo The zbvo to set.
	 */
	public void setZbvo(JmbaZbVO zbvo) {
		this.zbvo = zbvo;
	}
	/**
	 * @return Returns the jnjwbz.
	 */
	public String getJnjwbz() {
		return jnjwbz;
	}
	/**
	 * @param jnjwbz The jnjwbz to set.
	 */
	public void setJnjwbz(String jnjwbz) {
		this.jnjwbz = jnjwbz;
	}
}
