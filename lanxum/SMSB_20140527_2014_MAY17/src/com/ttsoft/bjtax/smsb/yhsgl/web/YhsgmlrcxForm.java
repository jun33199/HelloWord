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
 * <p>Description: ����ӡ��˰����������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmlrcxForm
    extends BaseForm
{

    public YhsgmlrcxForm ()
    {
        ghxz = "1";
        dataList = new ArrayList();
        mxDataList = new ArrayList();
    }

    /**
     *¼����ʼ����
     */
    private String lrqsrq; //¼����ʼ����

    /**
     *¼���������
     */
    private String lrjzrq; //¼���������

    /**
     *¼����
     */
    private String lrr; //¼����

    /**
     *����ƾ֤��
     */
    private String xspzh; //����ƾ֤��

    /**
     *���ʽ��ϼ�
     */
    private String dbjehj; //���ʽ��ϼ�

    /**
     *��������
     */
    private String ghxz; //��������

    /**
     *������λ���������
     */
    private String ghdwjsjdm; //������λ���������

    /**
     *������λ����
     */
    private String ghdwmc; //������λ����

    /**
     *֤������
     */
    private String zjhm; //֤������

    /**
     *����������
     */
    private String ghrmc; //����������

    /**
     *��ҳ:ÿҳ��ʾ��¼����Ŀ
     */
    private int length;

    /**
     *��ҳ:��ѯ�����ҳ��
     */
    private int pgSum;

    /**
     *��ҳ:��ǰҳ��
     */
    private int pgNum;

    /**
     * ��ϸ��Ŀ����
     */
    private java.util.List dataList;

    /**
     *��ϸ��Ŀ����
     */
    private java.util.List mxDataList;

    /**
     *ɾ����Ŀ���б�
     */
    private String[] deleteCheckbox;

    /**
     *��Դ��ʾ
     */
    private boolean fromDelete;

    /**
     *�鿴ID
     */
    private String viewId;

    /**
     *������������
     */
    private String mxCjrq;

    /**
     *��ϸ�ϼƽ��
     */
    private String mxHjje;

    /**
     *��ϸ��λ����
     */
    private String mxDwmc;

    /**
     *��ϸ��λ����
     */
    private String mxDwdm;

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        length = 0;
        pgSum = 0;
        pgNum = 0;
    }

    public String getLrqsrq ()
    {
        return lrqsrq;
    }

    public void setLrqsrq (String lrqsrq)
    {
        this.lrqsrq = lrqsrq;
    }

    public String getLrjzrq ()
    {
        return lrjzrq;
    }

    public void setLrjzrq (String lrjzrq)
    {
        this.lrjzrq = lrjzrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getXspzh ()
    {
        return xspzh;
    }

    public void setXspzh (String xkpzh)
    {
        this.xspzh = xkpzh;
    }

    public String getDbjehj ()
    {
        return dbjehj;
    }

    public void setDbjehj (String dbjehj)
    {
        this.dbjehj = dbjehj;
    }

    public String getGhxz ()
    {
        return ghxz;
    }

    public void setGhxz (String ghxz)
    {
        this.ghxz = ghxz;
    }

    public String getGhdwjsjdm ()
    {
        return ghdwjsjdm;
    }

    public void setGhdwjsjdm (String ghdwjsjdm)
    {
        this.ghdwjsjdm = ghdwjsjdm;
    }

    public String getGhdwmc ()
    {
        return ghdwmc;
    }

    public void setGhdwmc (String ghdwmc)
    {
        this.ghdwmc = ghdwmc;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public String getGhrmc ()
    {
        return ghrmc;
    }

    public void setGhrmc (String ghrmc)
    {
        this.ghrmc = ghrmc;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getMxDataList ()
    {
        return mxDataList;
    }

    public void setMxDataList (java.util.List mxDataList)
    {
        this.mxDataList = mxDataList;
    }

    public String[] getDeleteCheckbox ()
    {
        return deleteCheckbox;
    }

    public void setDeleteCheckbox (String[] deleteCheckbox)
    {
        this.deleteCheckbox = deleteCheckbox;
    }

    public boolean isFromDelete ()
    {
        return fromDelete;
    }

    public void setFromDelete (boolean fromDelete)
    {
        this.fromDelete = fromDelete;
    }

    public String getViewId ()
    {
        return viewId;
    }

    public void setViewId (String viewId)
    {
        this.viewId = viewId;
    }

    public String getMxCjrq ()
    {
        return mxCjrq;
    }

    public void setMxCjrq (String mxCjrq)
    {
        this.mxCjrq = mxCjrq;
    }

    public String getMxHjje ()
    {
        return mxHjje;
    }

    public void setMxHjje (String mxHjje)
    {
        this.mxHjje = mxHjje;
    }

    public String getMxDwmc ()
    {
        return mxDwmc;
    }

    public void setMxDwmc (String mxDwmc)
    {
        this.mxDwmc = mxDwmc;
    }

    public String getMxDwdm ()
    {
        return mxDwdm;
    }

    public void setMxDwdm (String mxDwdm)
    {
        this.mxDwdm = mxDwdm;
    }

}