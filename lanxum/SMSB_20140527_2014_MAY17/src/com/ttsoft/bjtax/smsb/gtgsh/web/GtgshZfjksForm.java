/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻����Ͻɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshZfjksForm
    extends BaseForm
{
    /**
     * ���ܷ�ʽ
     */
    private String hzfs; //���ܷ�ʽ

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * �Ƿ�����
     */
    private String lw="00";

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm; //˰�������֯��������

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc; //˰�������֯��������

    /**
     * ����list
     */
    private List dataList = new ArrayList(); //����list

    /**
     * ��ϸlist
     */
    private List detailList = new ArrayList(); //��ϸlist

    /**
     * ���������
     */
    private String jsjdm; //���������

    /**
     * ��������
     */
    private String hzrq; //��������

    /**
     * �걨���ܵ���
     */
    private String sbhzdh; //�걨���ܵ���

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh; //�ɿ�ƾ֤��

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {

    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public String getHzfs ()
    {
        return hzfs;
    }

    public void setHzfs (String hzfs)
    {
        this.hzfs = hzfs;
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

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getDetailList ()
    {
        return detailList;
    }

    public void setDetailList (java.util.List detailList)
    {
        this.detailList = detailList;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getHzrq ()
    {
        return hzrq;
    }

    public void setHzrq (String hzrq)
    {
        this.hzrq = hzrq;
    }

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
