package com.ttsoft.bjtax.shenbao.gzsx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

/**
 * ��֪����
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��֪����Form</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class GzsxForm extends BaseForm
{
    //������֪�ĸ�֪�����б�
    private List zcGzList = new ArrayList();

    //��������֪�������б�
    private List fzcGzList = new ArrayList();

    //�Ƿ��з�������Ϣ,1��ʾ��
    private String hasFzcInfo = "0";

    //���������
    private String jsjdm;
    //2009.4.2wcl�޸�Ϊ��ͨ������鿴��֪��ϸ
    private String gzsxnr;
    //2009.4.2wcl�޸�Ϊ�˻����˰������
    private String nsrmc;
    //2009.4.2wcl����,Ϊ�˻����˰�˷������
    private String nsrfk;
    //2009.4.2wcl����,Ϊ�˻����˰���Ķ�ʱ��
    private String ydsj;
    //2009.4.2.wcl���ӣ�Ϊ�˹��ö�Ӧ�ĸ�֪���
    private String gzsx_id;
    //2009.4.2wcl���ӣ�Ϊ���ж��Ƿ��ͬһ����֪�����������޸�
    private String savetype;
    //2009.4.3wcl���ӣ�Ϊ��ͨ����֪���⣬�鿴��֪��ϸ
    private String gzsxnrbt;
    //2009.4.3wcl���ӡ����������޸ı�ʾ
    private String fknrsavebs;
   //2009.4.3wcl���ӡ������ṩ�����ǵ�ʱ��
    private Date fksj ;
    public String getGzsxnrbt() {
		return gzsxnrbt;
	}

	public void setGzsxnrbt(String gzsxnrbt) {
		this.gzsxnrbt = gzsxnrbt;
	}

    public GzsxForm()
    {
    }

    public List getZcGzList()
    {
        return zcGzList;
    }

    public void setZcGzList(List zcGzList)
    {
        this.zcGzList = zcGzList;
    }

    public List getFzcGzList()
    {
        return fzcGzList;
    }

    public void setFzcGzList(List fzcGzList)
    {
        this.fzcGzList = fzcGzList;
    }

    public String getHasFzcInfo()
    {
        return hasFzcInfo;
    }
    public void setHasFzcInfo(String hasFzcInfo)
    {
        this.hasFzcInfo = hasFzcInfo;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

	public String getGzsxnr() {
		return gzsxnr;
	}

	public void setGzsxnr(String gzsxnr) {
		this.gzsxnr = gzsxnr;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getNsrfk() {
		return nsrfk;
	}

	public void setNsrfk(String nsrfk) {
		this.nsrfk = nsrfk;
	}

	public String getYdsj() {
		return ydsj;
	}

	public void setYdsj(String ydsj) {
		this.ydsj = ydsj;
	}

	public String getGzsx_id() {
		return gzsx_id;
	}

	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}

	public String getSavetype() {
		return savetype;
	}

	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}

	public String getFknrsavebs() {
		return fknrsavebs;
	}

	public void setFknrsavebs(String fknrsavebs) {
		this.fknrsavebs = fknrsavebs;
	}

	public Date getFksj() {
		return fksj;
	}

	public void setFksj(Date fksj) {
		this.fksj = fksj;
	}
}