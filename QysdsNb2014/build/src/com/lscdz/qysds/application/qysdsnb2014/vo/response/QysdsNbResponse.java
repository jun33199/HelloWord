package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.io.Serializable;

public class QysdsNbResponse implements Serializable{
	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 2341234577633894321L;

	/**
     * ��������
     */
    private String errorXX;

    /**
     * ������
     */
    private String errorNo;
    /**
     * ���������
     */
    private String jsjdm;
    /**
     * ��ѯ����ֵ
     */
    private String reportData;
    /**
     * ������Ϣ
     */
    private String userInfo;
    /**
     * ���صı�������
     */
    private String dataApps;
    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm;
    
    /**
     * ����ǩ����ִ��Ϣ
     */
    private String dzyjQmhz;
    
    /**
     * @return dataApps
     */
    public String getDataApps() {
        return dataApps;
    }

    /**
     * @param dataApps Ҫ���õ� dataApps
     */
    public void setDataApps(String dataApps) {
        this.dataApps = dataApps;
    }

    /**
     * @return errorNo
     */
    public String getErrorNo() {
        return errorNo;
    }

    /**
     * @param errorNo Ҫ���õ� errorNo
     */
    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    /**
     * @return errorXX
     */
    public String getErrorXX() {
        return errorXX;
    }

    /**
     * @param errorXX Ҫ���õ� errorXX
     */
    public void setErrorXX(String errorXX) {
        this.errorXX = errorXX;
    }

    /**
     * @return jsjdm
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * @param jsjdm Ҫ���õ� jsjdm
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * @return reportData
     */
    public String getReportData() {
        return reportData;
    }

    /**
     * @param reportData Ҫ���õ� reportData
     */
    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    /**
     * @return userInfo
     */
    public String getUserInfo() {
        return userInfo;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    /**
     * @param userInfo Ҫ���õ� userInfo
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

	public String getDzyjQmhz() {
		return dzyjQmhz;
	}

	public void setDzyjQmhz(String dzyjQmhz) {
		this.dzyjQmhz = dzyjQmhz;
	}
}
