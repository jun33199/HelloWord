/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨�� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsjdForm
    extends BaseForm
{
    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ˰��Ǽ�֤��
     */
    private String swdjzh;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     * ¼���˴���
     */
    private String lrr;

    /**
     * �Ǽ��걨��ʽ����
     */
    private String fsdm;

    /**
     * �����ܶ�
     */
    private String lrze;

    /**
     * ����
     */
    private String jd;

    /**
     * ���
     */
    private String nd;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * ˰��������������
     */
    private String skssjsrq;

    /**
     * ˰��������ʼ����
     */
    private String skssksrq;

    /**
     *  ֤���Ŵ��롢֤�����ʹ��롢Ͷ�������������������Ӧ��˰���ö����˰�ʡ�����۳�����Ӧ������˰�����˰��ڳ�δ��������˰��ѽ�������˰�ʵ��Ӧ��˰��
     */
    private String columns[] =
        {"zjlxdm", "zjhm", "tzzxm", "fpbl", "ynssde",
        "sysl", "sskcs", "ynsdse", "jmse", "qcwjsdse",
        "yjnsdse", "sjyjse"};

    /**
     * ���ڴ洢����ҳ�е�����µ�datalist�е����ݣ�ע���������ʼ���������ϵͳ����
     */
    private List dataList = new ArrayList();

    /**
     * ���շ�ʽ
     */
    private String zsfs;

    /**
     * �Ƿ������ۺ��걨
     *
     */
    private String iszhsb;

    /**
     * ���ش���
     */
    private String qxdm;

    /**
     * ��������
     */
    private String cjrq;

    /**
     * ���ڴ洢��̨�������ĸ��幤��˰Ŀ�����е��й�˰�ʡ�����۳�����Ӧ��˰��ʼ����Ӧ��˰��ֹ���ŵ�ҳ���д洢���Ա�JavaScript���ü��㴦��
     */
    private String lrrq;

    //�õ�����ʱ��
    public String getCjrq ()
    {
        return cjrq;
    }

    //���ô���ʱ��
    public void setCjrq (String cjrq)
    {
        this.cjrq = cjrq;
    }

    //�õ��Ǽ��걨��ʽ����
    public String getFsdm ()
    {
        return fsdm;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public String getJd ()
    {
        return jd;
    }

    public void setJd (String jd)
    {
        this.jd = jd;
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

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getLrze ()
    {
        return lrze;
    }

    public void setLrze (String lrze)
    {
        this.lrze = lrze;
    }

    public String getNd ()
    {
        return nd;
    }

    public void setNd (String nd)
    {
        this.nd = nd;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getSkssjsrq ()
    {
        return skssjsrq;
    }

    public void setSkssjsrq (String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq ()
    {
        return skssksrq;
    }

    public void setSkssksrq (String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getSwdjzh ()
    {
        return swdjzh;
    }

    public void setSwdjzh (String swdjzh)
    {
        this.swdjzh = swdjzh;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public String getZsfs ()
    {
        return zsfs;
    }

    public void setZsfs (String zsfs)
    {
        this.zsfs = zsfs;
    }

    /**
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     * @return null
     */
    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    /**
     * ҳ��Ҫ�����
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */
    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        this.actionType = "Show";
        this.jsjdm = null; //���������
        this.swdjzh = null; //˰��Ǽ�֤��
        this.swjgzzjgdm = null; //˰�������֯��������
        this.lrze = null; //�����ܶ�
        this.nsrmc = null; //��˰������
    }

    public String getIszhsb ()
    {
        return iszhsb;
    }

    public void setIszhsb (String iszhsb)
    {
        this.iszhsb = iszhsb;
    }

    public String getQxdm ()
    {
        return qxdm;
    }

    public void setQxdm (String qxdm)
    {
        this.qxdm = qxdm;
    }
}