/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������۵�λӡ��˰������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsxsdrForm
    extends BaseForm
{
    //�����ֶ�
    /**
     * ���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     * ���۵�λ����
     */
    private String dsdwmc = "";

    /**
     * ¼������
     */
    private String lrrq = "";

    /**
     * ����ʱ��
     */
    private String cjsj = "";

    /**
     * ������Դ
     */
    private String sjly = "";

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm = "";

    /**
     * ��ʽ����
     */
    private String fsdm = "";

    /**
     * ¼����
     */
    private String lrr = "";

    /**
     * �ϼƽ��
     */
    private String hjje = "";

    //��ҳ����ʾ
    /**
     * ��Ʊ�����ϼ�
     */
    private String gpslhj = "";

    /**
     * ���۵�λ��ϵ�绰
     */
    private String dsdwlxdh = "";

    /**
     *�����ļ�
     */
    private FormFile theFile;

    /**
     * ���������б�
     */
    private ArrayList xsList = new ArrayList();

    /**
     *��ϸ�ֶ�
     */
    private String columns[] =
        {
        "xspzh", "jsjdm", "dwmc", "gjdqdm", "zjlxdm",
        "zjhm", "spmzdm", "spmzje",
        "gpsl", "je"};

    /**
     * ��ϸ��Ŀ����
     */
    private ArrayList dataList = new ArrayList();

    /**
     * ��ҳ��ÿҳ��ʾ��¼��
     */
    private int length;

    /**
     * ��ҳ����ǰҳ��
     */
    private int pgNum;

    /**
     * ��ҳ����ѯ�����ҳ��
     */
    private int pgSum;

    private java.util.Map mapFile = new HashMap();

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dsjsjdm = "";
        dsdwmc = "";
        dsdwlxdh = "";
        swjgzzjgdm = "";
        lrrq = "";
        cjsj = "";
        lrr = "";
        hjje = "0.0";
        gpslhj = "0";
        sjly = "1";
        fsdm = "1";
        pgNum = 0;
        pgSum = 0;
        length = 0;

        dataList.clear();
        xsList.clear();
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getCjsj ()
    {
        return this.cjsj;
    }

    public String getDsjsjdm ()
    {
        return this.dsjsjdm;
    }

    public String getDsdwmc ()
    {
        return this.dsdwmc;
    }

    public String getDsdwlxdh ()
    {
        return this.dsdwlxdh;
    }

    public FormFile getTheFile ()
    {
        return theFile;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public String getGpslhj ()
    {
        return gpslhj;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setCjsj (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public void setDsdwlxdh (String dsdwlxdh)
    {
        this.dsdwlxdh = dsdwlxdh;
    }

    public void setTheFile (FormFile theFile)
    {
        this.theFile = theFile;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setGpslhj (String gpslhj)
    {
        this.gpslhj = gpslhj;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void setXsList (ArrayList xsList)
    {
        this.xsList = xsList;
    }

    public ArrayList getXsList ()
    {
        return xsList;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public java.util.Map getMapFile ()
    {
        return mapFile;
    }

    public void setMapFile (java.util.Map mapFile)
    {
        this.mapFile = mapFile;
    }

}