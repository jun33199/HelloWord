/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ������˰֤���ܽɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsHzjksForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();
    private List bhList = new ArrayList();

    /**
     * ҳ����ϸ�б�Ԫ�ؼ�
     */
    private String columns[] =
        {
        "szsmdm", "szdm", "szmc", "szsmmc",
        "kssl", "jsje", "sl", "yjhkc", "sjse",
        "skssksrq", "skssjsrq", "jzbz", "yskmdm",
        "ysjcdm"};

    /**
     * �������ͣ���λ/����
     */
    private String hzlx; //�������ͣ���λ/����

    /**
     * ���ܿ�ʼ����
     */
    private String hzksrq; //���ܿ�ʼ����

    /**
     * ���ܽ�������
     */
    private String hzjsrq; //���ܽ�������

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm; //˰�������֯��������

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc; //˰�������֯��������

    /**
     * �걨���ܵ���
     */
    private String sbhzdh; //�걨���ܵ���

    /**
     * �ϼ�����
     */
    private String hjzs; //�ϼ�����

    /**
     * �ϼƽ��
     */
    private String hjje; //�ϼƽ��

    /**
     * һƱһ˰��ʶ
     */
    private String ypys; //һƱһ˰��ʶ

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh; //�ɿ�ƾ֤��

    /**
     * ���������
     */
    private String jsjdm; //���������

    /**
     * �Ƿ�����
     */
    private String lw = "00";

    /**
     * �걨���
     */
    private String sbbh;

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }



	public List getBhList() {
		return bhList;
	}

	public void setBhList(List bhList) {
		this.bhList = bhList;
	}

	public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dataList = new ArrayList();
        bhList = new ArrayList();
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {

        return null;
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

    public String getSbhzdh ()
    {
        return sbhzdh;
    }

    public void setSbhzdh (String sbhzdh)
    {
        this.sbhzdh = sbhzdh;
    }

    public String getHjzs ()
    {
        return hjzs;
    }

    public void setHjzs (String hjzs)
    {
        this.hjzs = hjzs;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getYpys ()
    {
        return ypys;
    }

    public void setYpys (String ypys)
    {
        this.ypys = ypys;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
