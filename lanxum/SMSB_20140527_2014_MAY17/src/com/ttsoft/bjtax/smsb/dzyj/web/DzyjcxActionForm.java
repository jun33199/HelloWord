/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.dzyj.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzyjcxActionForm
    extends BaseForm
{
    /**
     * ���������
     */
    private String jsjdm="";

    /**
     * ��ʼʱ��
     */
    private String qssj="";

    /**
     * ��ֹʱ��
     */
    private String jzsj="";

    /**
     * ҵ������
     */
    private String ywlx="";

    /**
     * ҵ��������ϸ�����б�
     */
    private java.util.List ywlxList = new ArrayList();

    /**
     * ��������
     */
    private String czlx="";

    /**
     * ҵ�����ֵ��ҵ��uid)
     */
    private String ywuid="";

    /**
     * ��˰������
     */
    private String nsrmc="";

    /**
     *�ܼ�¼��
     */
    private int totalRecord = 0;
    
    /**
     *��ҳ:ÿҳ��ʾ��¼����Ŀ
     */
    private int pageLength = 10;

    /**
     *��ҳ:��ѯ�����ҳ��
     */
    private int pgSum = 1;

    /**
     *��ҳ:��ǰҳ��
     */
    private int pgNum = 1;
    
    /**
     *��ʾ����
     */
    private java.util.List dataList = new ArrayList();

    /**
     *�������
     */
    private java.util.List resultList = new ArrayList();

    /**
     * �������ݵ���ˮ��
     */
    private long oneItemLsh = 0;
    
    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
        this.ywlxList.clear();
        this.czlx = "";
        this.jsjdm = "";
        this.jzsj = "";
        this.nsrmc = "";
        this.pgNum = 1;
        this.pgSum = 1;
        this.qssj = "";
        this.ywlx = "";
        this.ywuid = "";
    }

    public String getCzlx()
    {
        return czlx;
    }

    public void setCzlx(String czlx)
    {
        this.czlx = czlx;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getJzsj()
    {
        return jzsj;
    }

    public void setJzsj(String jzsj)
    {
        this.jzsj = jzsj;
    }

    public String getNsrmc()
    {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public int getPageLength()
    {
        return pageLength;
    }

    public void setPageLength(int pagelength)
    {
        this.pageLength = pagelength;
    }

    public int getPgNum()
    {
        return pgNum;
    }

    public void setPgNum(int pgNum)
    {
        this.pgNum = pgNum;
    }

    public int getPgSum()
    {
        return pgSum;
    }

    public void setPgSum(int pgSum)
    {
        this.pgSum = pgSum;
    }

    public String getQssj()
    {
        return qssj;
    }

    public void setQssj(String qssj)
    {
        this.qssj = qssj;
    }

    public String getYwlx()
    {
        return ywlx;
    }

    public void setYwlx(String ywlx)
    {
        this.ywlx = ywlx;
    }

    public java.util.List getYwlxList()
    {
        return ywlxList;
    }

    public void setYwlxList(java.util.List ywlxList)
    {
        this.ywlxList = ywlxList;
    }

    public String getYwuid()
    {
        return ywuid;
    }

    public void setYwuid(String ywuid)
    {
        this.ywuid = ywuid;
    }

    public java.util.List getDataList()
    {
        return dataList;
    }

    public void setDataList(java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getResultList()
    {
        return resultList;
    }

    public void setResultList(java.util.List resultList)
    {
        this.resultList = resultList;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalrecord)
    {
        this.totalRecord = totalrecord;
    }

    public long getOneItemLsh()
    {
        return oneItemLsh;
    }

    public void setOneItemLsh(long oneItemLsh)
    {
        this.oneItemLsh = oneItemLsh;
    }

}
