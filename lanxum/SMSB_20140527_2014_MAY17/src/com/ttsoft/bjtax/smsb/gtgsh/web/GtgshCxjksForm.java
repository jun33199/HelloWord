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
 * <p>Description: ���幤�̻������ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshCxjksForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * �ɿ�ƾ֤�ţ���������룬˰�ִ��룬��˰�����ƣ�˰�������֯���ƣ�
     * �걨���ڣ�ʵ�ɽ��
     */
    private String columns[] =
        {
        "jkpzh", "jsjdm", "szdm", "nsrmc", "swjgzzjgmc",
        "sbrq",
        "sjje"};

    /**
     * ¼����
     */
    private String lrrdm; //¼����

    /**
     * �ɿ�ƾ֤��
     */
    private String headJkpzh; //�ɿ�ƾ֤��

    /**
     * ���������
     */
    private String headJsjdm; //���������

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
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

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getLrrdm ()
    {
        return lrrdm;
    }

    public void setLrrdm (String lrrdm)
    {
        this.lrrdm = lrrdm;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

}
