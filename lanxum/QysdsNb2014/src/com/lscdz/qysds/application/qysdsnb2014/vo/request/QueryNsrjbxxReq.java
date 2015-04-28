
package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import java.io.Serializable;

public class QueryNsrjbxxReq  implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 120934719283748761L;
	/**
	 * 计算机代码
	 */
    private String jsjdm;
    /**
     * 报表类型
     */
    private String reportType;
    /**
     * 年度
     */
    private String nd;
    /**
     * 报表期类型
     */
    private String bbqlx;
    /**
     * 期号
     */
    private String qh;
    /**
     * aid
     */
    private String AID;
    /**
     * 客户端版本号
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
