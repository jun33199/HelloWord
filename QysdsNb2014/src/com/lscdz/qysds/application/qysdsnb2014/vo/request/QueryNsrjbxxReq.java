
package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import java.io.Serializable;

public class QueryNsrjbxxReq  implements Serializable{
	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 120934719283748761L;
	/**
	 * ���������
	 */
    private String jsjdm;
    /**
     * ��������
     */
    private String reportType;
    /**
     * ���
     */
    private String nd;
    /**
     * ����������
     */
    private String bbqlx;
    /**
     * �ں�
     */
    private String qh;
    /**
     * aid
     */
    private String AID;
    /**
     * �ͻ��˰汾��
     */
    private String clientVersion;

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
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

	public String getAID() {
		return AID;
	}

	public void setAID(String aID) {
		AID = aID;
	}
	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

}
