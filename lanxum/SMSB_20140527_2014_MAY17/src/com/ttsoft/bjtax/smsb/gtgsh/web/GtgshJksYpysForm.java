/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypysActionForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: һƱ��˰</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJksYpysForm
    extends ZhsbjksypysActionForm
{
    /**
     * ���ر�ʶ
     */
    private String fhbs;

    /**
     * һƱ��˰��ʶ
     */
    private String ypys;

    /**
     * ��������
     */
    private String hzlx;

    /**
     * ���ܿ�ʼ����
     */
    private String hzksrq;

    /**
     * ���ܽ�������
     */
    private String hzjsrq;

    /**
     * �걨���ܵ���
     */
    private String sbhzdh;

    /**
     * ���������
     */
    private String gtgshJsjdm;

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getFhbs ()
    {
        return fhbs;
    }

    public void setFhbs (String fhbs)
    {
        this.fhbs = fhbs;
    }

    public String getYpys ()
    {
        return ypys;
    }

    public void setYpys (String ypys)
    {
        this.ypys = ypys;
    }

    public String getHzlx ()
    {
        return hzlx;
    }

    public void setHzlx (String hzlx)
    {
        this.hzlx = hzlx;
    }

    public String getHzksrq ()
    {
        return hzksrq;
    }

    public void setHzksrq (String hzksrq)
    {
        this.hzksrq = hzksrq;
    }

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
    }

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public String getGtgshJsjdm ()
    {
        return gtgshJsjdm;
    }

    public void setGtgshJsjdm (String gtgshJsjdm)
    {
        this.gtgshJsjdm = gtgshJsjdm;
    }
}