package com.lscdz.qysds.application.qysdsnb2014.vo.request;

public class DownloadQysdsDataReq {
	 /**
     * 计算机代码
     */
    private String jsjdm;
    /**
     * 密码
     */
    private String password;
    /**
     * 证书
     */
    private String certificate;
    /**
     * 登陆方式
     */
    private int loginType;
    /**
     * 报表数据
     */
    private String reportData;
    /**
     * 报表数据签名值
     */
    private String idiographData;
    /**
     * 证书信封名称
     */
    private String containerName;
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
     * 证书序列号
     */
    private String zsxlh;
    /**
     * 财会制度
     */
    private String ckzd;
    /**
     * 法定减免类型
     */
    private String jmlx;
    /**
     * 工资管理形式
     */
    private String gzglxs;
    
    /**
     * 需要签名保存的报表数据
     */
    private String reportDataCA;
    /**
     * 需要签名保存的报表数据签名值
     */
    private String idiographDataCA;
    
    /**
     * 客户端版本号
     */
    private String clientVersion;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 报表数据流
     */
//    private InputStream reportDataStream;
    
    public String getPassword()
    {
        return password;
    }

    public int getLoginType()
    {
        return loginType;
    }

    public String getCertificate()
    {
        return certificate;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setLoginType(int loginType)
    {
        this.loginType = loginType;
    }

    public void setCertificate(String certificate)
    {
        this.certificate = certificate;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getReportData()
    {
        return reportData;
    }

    public void setReportData(String reportData)
    {
        this.reportData = reportData;
    }

    public String getContainerName()
    {
        return containerName;
    }

    public void setContainerName(String containerName)
    {
        this.containerName = containerName;
    }

    public String getIdiographData()
    {
        return idiographData;
    }

    public String getReportType()
    {
        return reportType;
    }

    public String getNd()
    {
        return nd;
    }

    public String getBbqlx()
    {
        return bbqlx;
    }

    public String getQh()
    {
        return qh;
    }

    public String getZsxlh()
    {
        return zsxlh;
    }

    public String getAID()
    {
        return AID;
    }

    public void setIdiographData(String idiographData)
    {
        this.idiographData = idiographData;
    }

    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public void setNd(String nd)
    {
        this.nd = nd;
    }

    public void setBbqlx(String bbqlx)
    {
        this.bbqlx = bbqlx;
    }

    public void setQh(String qh)
    {
        this.qh = qh;
    }

    public void setZsxlh(String zsxlh)
    {
        this.zsxlh = zsxlh;
    }

    public void setAID(String AID)
    {
        this.AID = AID;
    }

    public void setCKZD(String str)
    {
        this.ckzd = str;
    }

    public String getCKZD()
    {
        return this.ckzd;
    }

    public void setJMLX(String str)
    {
        this.jmlx = str;
    }

    public String getJMLX()
    {
        return this.jmlx;
    }

    public void setGZGLXS(String str)
    {
        this.gzglxs = str;
    }

    public String getGZGLXS()
    {
        return this.gzglxs;
    }



	public String getIdiographDataCA() {
		return idiographDataCA;
	}

	public void setIdiographDataCA(String idiographDataCA) {
		this.idiographDataCA = idiographDataCA;
	}



	public String getReportDataCA() {
		return reportDataCA;
	}

	public void setReportDataCA(String reportDataCA) {
		this.reportDataCA = reportDataCA;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
