package com.lscdz.qysds.application.qysdsnb2014.vo.request;

public class DownloadQysdsDataReq {
	 /**
     * ���������
     */
    private String jsjdm;
    /**
     * ����
     */
    private String password;
    /**
     * ֤��
     */
    private String certificate;
    /**
     * ��½��ʽ
     */
    private int loginType;
    /**
     * ��������
     */
    private String reportData;
    /**
     * ��������ǩ��ֵ
     */
    private String idiographData;
    /**
     * ֤���ŷ�����
     */
    private String containerName;
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
     * ֤�����к�
     */
    private String zsxlh;
    /**
     * �ƻ��ƶ�
     */
    private String ckzd;
    /**
     * ������������
     */
    private String jmlx;
    /**
     * ���ʹ�����ʽ
     */
    private String gzglxs;
    
    /**
     * ��Ҫǩ������ı�������
     */
    private String reportDataCA;
    /**
     * ��Ҫǩ������ı�������ǩ��ֵ
     */
    private String idiographDataCA;
    
    /**
     * �ͻ��˰汾��
     */
    private String clientVersion;
    /**
     * �û�����
     */
    private String userType;
    /**
     * ����������
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
