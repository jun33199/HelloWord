/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸ��¼ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxwhForm
    extends BaseForm
{

    /**
     * ˰��˰Ŀ����
     */
    private String szsmdm;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmmc;

    /**
     * �������ʹ���
     */
    private String zqlxdm;

    /**
     * ������������
     */
    private String zqlxmc;

    /**
     * ��������������
     */
    private String zqssrqq;

    /**
     * ������������ֹ
     */
    private String zqssrqz;

    /**
     * ������ʼ����
     */
    private String zqqsrq;

    /**
     * ������ֹ����
     */
    private String zqzzrq;

    /**
     * �Ǽ�ע�����ʹ���
     */
    private String djzclxdm;

    /**
     * �Ǽ�ע����������
     */
    private String djzclxmc;

    /**
     * ���
     */
    private String nd;

    /**
     * ��ȣ���ѯʹ��
     */
    private String queryNd;

    /**
     * ������ʼ���ڣ���ѯʹ��
     */
    private String queryZqqsrq;

    /**
     * ˰�֣���ѯʹ��
     */
    private String querySz;

    /**
     * �������ͣ���ѯʹ��
     */
    private String queryZqlx;

    /**
     * �Ǽ�ע�����ͣ���ѯʹ��
     */
    private String queryDjzclx;

    /**
     * �·ݣ���ѯʹ��
     */
    private String queryMonth;

    public ZqrlmxwhForm ()
    {
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public String getSzsmmc ()
    {
        return szsmmc;
    }

    public void setSzsmmc (String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public String getZqlxdm ()
    {
        return zqlxdm;
    }

    public void setZqlxdm (String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public String getZqlxmc ()
    {
        return zqlxmc;
    }

    public void setZqlxmc (String zqlxmc)
    {
        this.zqlxmc = zqlxmc;
    }

    public String getZqssrqq ()
    {
        return zqssrqq;
    }

    public void setZqssrqq (String zqssrqq)
    {
        this.zqssrqq = zqssrqq;
    }

    public String getZqssrqz ()
    {
        return zqssrqz;
    }

    public void setZqssrqz (String zqssrqz)
    {
        this.zqssrqz = zqssrqz;
    }

    public String getZqqsrq ()
    {
        return zqqsrq;
    }

    public void setZqqsrq (String zqqsrq)
    {
        this.zqqsrq = zqqsrq;
    }

    public String getZqzzrq ()
    {
        return zqzzrq;
    }

    public void setZqzzrq (String zqzzrq)
    {
        this.zqzzrq = zqzzrq;
    }

    public String getDjzclxdm ()
    {
        return djzclxdm;
    }

    public void setDjzclxdm (String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public String getDjzclxmc ()
    {
        return djzclxmc;
    }

    public void setDjzclxmc (String djzclxmc)
    {
        this.djzclxmc = djzclxmc;
    }

    public String getNd ()
    {
        return nd;
    }

    public void setNd (String nd)
    {
        this.nd = nd;
    }

    public String getQueryDjzclx ()
    {
        return queryDjzclx;
    }

    public String getQueryNd ()
    {
        return queryNd;
    }

    public String getQuerySz ()
    {
        return querySz;
    }

    public String getQueryZqlx ()
    {
        return queryZqlx;
    }

    public String getQueryZqqsrq ()
    {
        return queryZqqsrq;
    }

    public void setQueryZqqsrq (String queryZqqsrq)
    {
        this.queryZqqsrq = queryZqqsrq;
    }

    public void setQueryZqlx (String queryZqlx)
    {
        this.queryZqlx = queryZqlx;
    }

    public void setQuerySz (String querySz)
    {
        this.querySz = querySz;
    }

    public void setQueryNd (String queryNd)
    {
        this.queryNd = queryNd;
    }

    public void setQueryDjzclx (String queryDjzclx)
    {
        this.queryDjzclx = queryDjzclx;
    }

    public String getQueryMonth ()
    {
        return queryMonth;
    }

    public void setQueryMonth (String queryMonth)
    {
        this.queryMonth = queryMonth;
    }

}
