/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻��ɿ���¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJkslrForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ҳ��Ԫ���б�����
     */
    private String[] columns =
        {"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
        "skssksrq", "skssjsrq", "szdm", "szmc"};

    /**
     * ��˰�����Ƽ��������
     */
    private String nsrjsjdm; //��˰�����Ƽ��������

    /**
     * ��˰������
     */
    private String nsrmc; //��˰������

    /**
     * ֤�����ʹ���
     */
    private String zjlxdm; //֤�����ʹ���

    /**
     * ֤������
     */
    private String zjhm; //֤������

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm; //˰�������֯��������

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc; //˰�������֯��������

    /**
     * �ϼ�ʵ�ɽ��
     */
    private String hjsjje; //�ϼ�ʵ�ɽ��

    /**
     * �걨����
     */
    private String sbrq; //�걨����

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * ���ұ�׼��ҵ����
     */
    private String gjbzhydm; //���ұ�׼��ҵ����

    /**
     * �Ǽ�ע�����ʹ���
     */
    private String djzclxdm; //�Ǽ�ע�����ʹ���

    /**
     * �Ǽ�ע����������
     */
    private String djzclxmc; //�Ǽ�ע����������

    /**
     * ��ַ
     */
    private String dz; //��ַ

    /**
     * ���������
     */
    private String jsjdm; //���������

    /**
     * ǰ̨��ʾѡ���б�ʱʹ�õ�����js����
     */
    private String scriptStr; //ǰ̨��ʾѡ���б�ʱʹ�õ�����js����

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh; //�ɿ�ƾ֤��**

    /**�ɿ���ϼƽ��
     *
     */
    private String jkshjje; //�ɿ���ϼƽ��**

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getNsrjsjdm ()
    {
        return nsrjsjdm;
    }

    public void setNsrjsjdm (String nsrjsjdm)
    {
        this.nsrjsjdm = nsrjsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
        this.nsrjsjdm = "";
        this.nsrmc = "";
        this.djzclxdm = "";
        this.gjbzhydm = "";
        dataList = new ArrayList();
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

    public String getHjsjje ()
    {
        return hjsjje;
    }

    public void setHjsjje (String hjsjje)
    {
        this.hjsjje = hjsjje;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getGjbzhydm ()
    {
        return gjbzhydm;
    }

    public void setGjbzhydm (String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
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

    public String getDz ()
    {
        return dz;
    }

    public void setDz (String dz)
    {
        this.dz = dz;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJkshjje ()
    {
        return jkshjje;
    }

    public void setJkshjje (String jkshjje)
    {
        this.jkshjje = jkshjje;
    }
}
