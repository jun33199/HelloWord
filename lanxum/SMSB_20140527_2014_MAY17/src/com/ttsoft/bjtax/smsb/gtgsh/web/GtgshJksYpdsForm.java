/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsActionForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: һƱ��˰</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJksYpdsForm
    extends ZhsbjksypdsActionForm
{
    /**
     * ���ر�ʶ
     */
    private String fhbs;

    /**
     * һƱһ˰��ʶ
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
     * ���幤�̻����������
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

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public String getHzksrq ()
    {
        return hzksrq;
    }

    public String getHzlx ()
    {
        return hzlx;
    }

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public String getYpys ()
    {
        return ypys;
    }

    public void setYpys (String ypys)
    {
        this.ypys = ypys;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public void setHzlx (String hzlx)
    {
        this.hzlx = hzlx;
    }

    public void setHzksrq (String hzksrq)
    {
        this.hzksrq = hzksrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
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