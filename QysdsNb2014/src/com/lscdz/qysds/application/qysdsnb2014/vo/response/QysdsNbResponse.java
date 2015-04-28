package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.io.Serializable;

public class QysdsNbResponse implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 2341234577633894321L;

	/**
     * 错误描述
     */
    private String errorXX;

    /**
     * 错误编号
     */
    private String errorNo;
    /**
     * 计算机代码
     */
    private String jsjdm;
    /**
     * 查询返回值
     */
    private String reportData;
    /**
     * 基本信息
     */
    private String userInfo;
    /**
     * 下载的报表数据
     */
    private String dataApps;
    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm;
    
    /**
     * 电子签名回执信息
     */
    private String dzyjQmhz;
    
    /**
     * @return dataApps
     */
    public String getDataApps() {
        return dataApps;
    }

    /**
     * @param dataApps 要设置的 dataApps
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
     * @param errorNo 要设置的 errorNo
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
     * @param errorXX 要设置的 errorXX
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
     * @param jsjdm 要设置的 jsjdm
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
     * @param reportData 要设置的 reportData
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
     * @param userInfo 要设置的 userInfo
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
