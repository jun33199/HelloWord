/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��_������˰</p>
 * <p>Description: һƱһ˰�ɿ���ά����</p>
 * @author zzb  20090330
 * @version 1.1
 */

public class ZhsbPgbsjksypdsActionForm
    extends BaseForm
{
    /**
     * ����������
     */
    private String[] columns =
        {
        "szsmdm", "jkpzh", "jsjdm", "yskmdm", "yskmmc",
        "ysjcdm", "ysjcmc",
        "szsmmc", "kssl", "jsje", "sjse", "skssksrq",
        "skssjsrq", "rkje", "sbbh",
        "sjfc", "qjfc", "nd"};

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh; //��˰ƾ֤��

    /**
     * ��ϸ�����б�
     */
    private java.util.ArrayList dataList = new ArrayList();

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc;

    /**
     * ��������
     */
    private String yhmc;

    /**
     * ¼������
     */
    private String lrrq;

    /**
     * ʵ�ɽ��
     */
    private String[] sjje;

    /**
     * js����
     */
    private String scriptStr;

    /**
     * ת��Ŀ��
     */
    private String forward;

    /**
     * ������Դ
     */
    private String sjly;

    /**
     * ����ʱ�����ж��Ƿ񷵻��걨���
     */
    private String presbbh;

    /**
     * �걨���
     */
    private String sbbh;

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Query";
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String[] getSjje ()
    {
        return sjje;
    }

    public void setSjje (String[] sjje)
    {
        this.sjje = sjje;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getForward ()
    {
        return forward;
    }

    public void setForward (String forward)
    {
        this.forward = forward;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public String getPresbbh ()
    {
        return presbbh;
    }

    public void setPresbbh (String presbbh)
    {
        this.presbbh = presbbh;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }
}