/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻�������˰֤</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshCxwszForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ҳ��Ԫ���б�����
     */
    private String columns[] =
        {
        "pzzldm", "wszh", "nsrjsjdm", "jsjdm",
        "swjgzzjgdm",
        "djzclxdm", "dz", "hjsjje", "clbjdm", "sbhzdh",
        "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
        "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
        "lrrq", "qxdm", "ndzb", "printflag", "nsrmc"};

    /**
     * ��˰֤����
     */
    private String headWszh; //��˰֤����

    /**
     * ¼���˴���
     */
    private String headLrr; //¼���˴���

    /**
     * �ʻ�����
     */
    private String headZhdm; //�ʻ�����

    /**
     * ����ֱ�
     */
    private String headNdzb; //����ֱ�

    /**
     * ����ֱ���ʱ
     */
    private String tempNdzb;

    /**
     * ��˰�˼�������룬��ѯʹ��
     */
    private String headNsrjsjdm;

    /**
     * Ʊ��������룬��ѯʹ��
     */
    private String headPzzldm;

    /**
     * ��˰֤��ţ�����ʹ��
     */
    private String headWszxh;

    /**
     * �����Ǵ��룬���ô�ӡ���ʹ��
     */
    private String headClbjdm;

    public GtgshCxwszForm ()
    {
        Calendar c = Calendar.getInstance();
        tempNdzb = "" + c.get(Calendar.YEAR);
    }

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
        headWszh = "";
        headWszxh = "";
        dataList = new ArrayList();

    }

    public String getHeadWszh ()
    {
        return headWszh;
    }

    public void setHeadWszh (String headWszh)
    {
        this.headWszh = headWszh;
    }

    public String getHeadLrr ()
    {
        return headLrr;
    }

    public void setHeadLrr (String headLrr)
    {
        this.headLrr = headLrr;
    }

    public String getHeadZhdm ()
    {
        return headZhdm;
    }

    public void setHeadZhdm (String headZhdm)
    {
        this.headZhdm = headZhdm;
    }

    public String getHeadNdzb ()
    {
        return headNdzb;
    }

    public void setHeadNdzb (String headNdzb)
    {
        this.headNdzb = headNdzb;
    }

    public String getTempNdzb ()
    {
        return tempNdzb;
    }

    public void setTempNdzb (String tempNdzb)
    {
        this.tempNdzb = tempNdzb;
    }

    public String getHeadNsrjsjdm ()
    {
        return headNsrjsjdm;
    }

    public void setHeadNsrjsjdm (String headNsrjsjdm)
    {
        this.headNsrjsjdm = headNsrjsjdm;
    }

    public String getHeadPzzldm ()
    {
        return headPzzldm;
    }

    public void setHeadPzzldm (String headPzzldm)
    {
        this.headPzzldm = headPzzldm;
    }

    public String getHeadWszxh ()
    {
        return headWszxh;
    }

    public void setHeadWszxh (String headWszxh)
    {
        this.headWszxh = headWszxh;
    }

    public String getHeadClbjdm ()
    {
        return headClbjdm;
    }

    public void setHeadClbjdm (String headClbjdm)
    {
        this.headClbjdm = headClbjdm;
    }

}