/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰���۵�λ���ۻ��� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsxshzcxForm
    extends BaseForm
{
    /**
     * ���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     * ���۵�λ����
     */
    private String dsdwmc = "";

    /**
     * ���۵�λ��ϵ�绰
     */
    private String dsdwlxdh = "";

    /**
     * �����ɿ�ƾ֤��
     */
    private String cxjkpzh = "";

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh = "";

    /**
     * ʵ�ɽ��
     */
    private String sjje = "";

    /**
     * �ж��Ƿ��ǳ������Զ����ò�ѯ����
     */
    private boolean isFromCx = false; // �ж��Ƿ��ǳ������Զ����ò�ѯ����

    /**
     *�ӱ����ݣ�list�����ģ�
     */
    private String columns[] =
        {
        "jkpzh", "sjje"};

    /**
     *��ϸ��Ŀ����
     */
    private ArrayList dataList = new ArrayList();

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        cxjkpzh = "";
        dataList.clear();
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public String getCxjkpzh ()
    {
        return cxjkpzh;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setCxjkpzh (String cxjkpzh)
    {
        this.cxjkpzh = cxjkpzh;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public String getDsdwlxdh ()
    {
        return dsdwlxdh;
    }

    public String getDsdwmc ()
    {
        return dsdwmc;
    }

    public void setDsdwlxdh (String dsdwlxdh)
    {
        this.dsdwlxdh = dsdwlxdh;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public boolean isIsFromCx ()
    {
        return isFromCx;
    }

    public void setIsFromCx (boolean isFromCx)
    {
        this.isFromCx = isFromCx;
    }
}