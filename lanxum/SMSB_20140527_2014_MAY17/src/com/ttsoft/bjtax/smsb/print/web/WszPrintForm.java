/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ӡ��˰֤</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class WszPrintForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ע������
     */
    private String zclx; //ע������

    /**
     * �����
     */
    private String tfrq; //�����

    /**
     * ���ջ���
     */
    private String zsjg; //���ջ���

    /**
     * ��˰�˼�������룬������ɢ�����ǵ�λ���������
     */
    private String nsrjsjdm; //��˰�˼�������룬������ɢ�����ǵ�λ���������

    /**
     * ��ַ
     */
    private String dz; //��ַ

    /**
     * ��˰������
     */
    private String nsrmc; //��˰������

    /**
     * ˰��������ʼ����
     */
    private String skssksrq; //˰��������ʼ����

    /**
     * ˰��������������
     */
    private String skssjsrq; //˰��������������

    /**
     * �ϼƽ��
     */
    private String hjje; //�ϼƽ��

    /**
     * �ϼƽ���д
     */
    private String hjjedx; //�ϼƽ���д

    /**
     * �ط�˰�����
     */
    private String dfswjg; //�ط�˰�����

    /**
     * ί�е�λ
     */
    private String wtdzdw; //ί�е�λ

    /**
     * ��Ʊ��
     */
    private String tpr; //��Ʊ��

    /**
     * ��ע
     */
    private String bz; //��ע

    /**
     * ����ҳ��ı�־
     */
    private String fromPage; //����ҳ��ı�־

    /**
     * ���������
     */
    private String jsjdm; //���������

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * ���ջ�����֯��������
     */
    private String swjgzzjgdm; //���ջ�����֯��������

    /**
     * ���ջ�����֯��������
     */
    private String swjgzzjgmc; //���ջ�����֯��������

    /**
     * ��ϸ��Ϣ��˰������
     */
    private String mxSz; //��ϸ��Ϣ��˰������

    /**
     * ��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����
     */
    private String mxPmmc; //��ϸ��Ϣ��ƷĿ���ƣ���ʵ��˰��˰Ŀ����

    /**
     * ��ϸ��Ϣ����˰����
     */
    private String mxKssl; //��ϸ��Ϣ����˰����

    /**
     * ��ϸ��Ϣ����˰���
     */
    private String mxJsje; //��ϸ��Ϣ����˰���

    /**
     * ��ϸ��Ϣ��˰��
     */
    private String mxSl; //��ϸ��Ϣ��˰��
    
    /**
     * ��ϸ��Ϣ��˰����������
     */
    private String mxSkssrq; //˰��������ʼ����

    /**
     * ��ϸ��Ϣ���ѽɻ�۳�
     */
    private String mxYjhkc; //��ϸ��Ϣ���ѽɻ�۳�

    /**
     * ��ϸ��Ϣ��ʵ��˰��
     */
    private String mxSjse; //��ϸ��Ϣ��ʵ��˰��

    /**
     * ����ڣ���
     */
    private String tfrqYear; //����ڣ���

    /**
     * ����ڣ���
     */
    private String tfrqMonth; //����ڣ���

    /**
     * ����ڣ���
     */
    private String tfrqDate; //����ڣ���

    /**
     * �ʻ�����
     */
    private String zhdm; //�ʻ�����

    /**
     * Ʊ֤�������
     */
    private String pzzldm; //Ʊ֤�������

    /**
     * ��˰֤��
     */
    private String headWszh; //��˰֤��

    /**
     * ����ֱ�
     */
    private String ndzb; //����ֱ�

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dataList = new ArrayList();
    }

    public String getZclx ()
    {
        return zclx;
    }

    public void setZclx (String zclx)
    {
        this.zclx = zclx;
    }

    public String getTfrq ()
    {
        return tfrq;
    }

    public void setTfrq (String tfrq)
    {
        this.tfrq = tfrq;
    }

    public String getZsjg ()
    {
        return zsjg;
    }

    public void setZsjg (String zsjg)
    {
        this.zsjg = zsjg;
    }

    public String getNsrjsjdm ()
    {
        return nsrjsjdm;
    }

    public void setNsrjsjdm (String nsrjsjdm)
    {
        this.nsrjsjdm = nsrjsjdm;
    }

    public String getDz ()
    {
        return dz;
    }

    public void setDz (String dz)
    {
        this.dz = dz;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSkssksrq ()
    {
        return skssksrq;
    }

    public void setSkssksrq (String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getSkssjsrq ()
    {
        return skssjsrq;
    }

    public void setSkssjsrq (String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getHjjedx ()
    {
        return hjjedx;
    }

    public void setHjjedx (String hjjedx)
    {
        this.hjjedx = hjjedx;
    }

    public String getDfswjg ()
    {
        return dfswjg;
    }

    public void setDfswjg (String dfswjg)
    {
        this.dfswjg = dfswjg;
    }

    public String getWtdzdw ()
    {
        return wtdzdw;
    }

    public void setWtdzdw (String wtdzdw)
    {
        this.wtdzdw = wtdzdw;
    }

    public String getTpr ()
    {
        return tpr;
    }

    public void setTpr (String tpr)
    {
        this.tpr = tpr;
    }

    public String getBz ()
    {
        return bz;
    }

    public void setBz (String bz)
    {
        this.bz = bz;
    }

    public String getFromPage ()
    {
        return fromPage;
    }

    public void setFromPage (String fromPage)
    {
        this.fromPage = fromPage;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getMxSz ()
    {
        return mxSz;
    }

    public void setMxSz (String mxSz)
    {
        this.mxSz = mxSz;
    }

    public String getMxPmmc ()
    {
        return mxPmmc;
    }

    public void setMxPmmc (String mxPmmc)
    {
        this.mxPmmc = mxPmmc;
    }

    public String getMxKssl ()
    {
        return mxKssl;
    }

    public void setMxKssl (String mxKssl)
    {
        this.mxKssl = mxKssl;
    }

    public String getMxJsje ()
    {
        return mxJsje;
    }

    public void setMxJsje (String mxJsje)
    {
        this.mxJsje = mxJsje;
    }

    public String getMxSl ()
    {
        return mxSl;
    }

    public void setMxSl (String mxSl)
    {
        this.mxSl = mxSl;
    }

    public String getMxYjhkc ()
    {
        return mxYjhkc;
    }

    public void setMxYjhkc (String mxYjhkc)
    {
        this.mxYjhkc = mxYjhkc;
    }

    public String getMxSjse ()
    {
        return mxSjse;
    }

    public void setMxSjse (String mxSjse)
    {
        this.mxSjse = mxSjse;
    }

    public String getTfrqYear ()
    {
        return tfrqYear;
    }

    public void setTfrqYear (String tfrqYear)
    {
        this.tfrqYear = tfrqYear;
    }

    public String getTfrqMonth ()
    {
        return tfrqMonth;
    }

    public void setTfrqMonth (String tfrqMonth)
    {
        this.tfrqMonth = tfrqMonth;
    }

    public String getTfrqDate ()
    {
        return tfrqDate;
    }

    public void setTfrqDate (String tfrqDate)
    {
        this.tfrqDate = tfrqDate;
    }

    public String getZhdm ()
    {
        return zhdm;
    }

    public void setZhdm (String zhdm)
    {
        this.zhdm = zhdm;
    }

    public String getPzzldm ()
    {
        return pzzldm;
    }

    public void setPzzldm (String pzzldm)
    {
        this.pzzldm = pzzldm;
    }

    public String getHeadWszh ()
    {
        return headWszh;
    }

    public void setHeadWszh (String headWszh)
    {
        this.headWszh = headWszh;
    }

    public String getNdzb ()
    {
        return ndzb;
    }

    public void setNdzb (String ndzb)
    {
        this.ndzb = ndzb;
    }

	public String getMxSkssrq() {
		return mxSkssrq;
	}

	public void setMxSkssrq(String mxSkssrq) {
		this.mxSkssrq = mxSkssrq;
	}


}
