package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import com.lscdz.util.PageInfo;

public class NsrjbxxVo2014 extends PageInfo{
	/**
	 * ���������
	 */
    private String jsjdm;
    /**
     * ���
     */
    private String nd;
    /**
     * ��˰������
     */
    private String nsrmc;
    /**
     * ����˰����֯����
     */
    private String zgswjgdm;
    /**
     * ��������
     */
    private String sqlx;
    private String smwsbz;//���ţ�0�������ϣ�1����־
    
	public String getSmwsbz() {
		return smwsbz;
	}
	public void setSmwsbz(String smwsbz) {
		this.smwsbz = smwsbz;
	}
	public String getSqlx() {
		return sqlx;
	}
	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZgswjgdm() {
		return zgswjgdm;
	}
	public void setZgswjgdm(String zgswjgdm) {
		this.zgswjgdm = zgswjgdm;
	}
    
}
