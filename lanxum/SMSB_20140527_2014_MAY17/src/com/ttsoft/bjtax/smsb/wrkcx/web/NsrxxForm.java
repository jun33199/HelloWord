package com.ttsoft.bjtax.smsb.wrkcx.web;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.ttsoft.framework.form.BaseForm;






/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class NsrxxForm extends BaseForm
    implements Serializable{
  public NsrxxForm() {
  }

  /**
     * �������� 1��ת�룬2��ת����3�����٣�4��������5����ʱ�Ǽ�
     */
    private String czlb;
    /**
     * ��ѯ���� 1����⣬2����˰
     */
    private String cxlx;
    /**
     * ��ѯ����1���־ּ����ѯ��2��˰���������ѯ��
     */
    private String cxjb;

    /**
     * ���ܲ�ѯ���� 1���־���������ѯ��2���־���˰�����ѯ��
     *            3��˰������������ѯ��4��˰������˰�����ѯ
     */
    private String hzcxlx;
    /**
     * �ֻ��嵥�Ĳ�ѯ����(˰�������֯��������)
     */
    private String swjgdmforfhqd;
    /**
     * Ҫ��ת��ҳ����struct-config��Ӧ������
     */
    private String nextPage;

    /**
     * ���ز�ѯ������
     */
    private List rstList = new ArrayList();
    /**
     * ���ز�ѯ������
     */
    private List hzList = new ArrayList();
    /**
     * ���ز�ѯ������
     */
    private List mxList = new ArrayList();

    /**
     * ҳ�����
     */
    private int pageIndex = 0;

    /**
     * ��ǰҳ��
     */
    private String pageNum;

    /**
     * ��ѯ������ҳ��
     */
    private String totalPage;

    /**
     * ��ѯ���ļ�¼����
     */
    private String totalCount;

    /**
     * ����˰�����
     */
    private String zgswjg;

    /**
     * ����˰����
     */
    private String zgsws;

    /**
     * ��ʼʱ��
     */
    private String qsrq;

    /**
     * ��ֹʱ��
     */
    private String jzrq;

    /**
     * ˰������
     */
    private String jexx;
    /**
     *˰��˰Ŀ����
     */
    private String szsmdm;
    /**
     * ��ϸ�嵥���������
     */
    private String mx_jsjdm;
    /**
     * �Ǽ���Ϣ���������
     */
    private String dj_jsjdm;


    /**
     * ����
     */
    private String title;
    /**
     * ��������
     */
    private String qttitle;
    /**
     * ҳ�淵��ʱʹ�õ����ر��������ط��ص�Action��
     */
    private String backAction;

    public String getJexx()
    {
        return jexx;
    }

    public String getJzrq()
    {
        return jzrq;
    }

    public String getNextPage()
    {
        return nextPage;
    }

    public int getPageIndex()
    {
        return pageIndex;
    }

    public String getPageNum()
    {
        return pageNum;
    }

    public String getQsrq()
    {
        return qsrq;
    }

    public String getTotalCount()
    {
        return totalCount;
    }

    public String getTotalPage()
    {
        return totalPage;
    }

    public String getZgswjg()
    {
        return zgswjg;
    }

    public String getZgsws()
    {
        return zgsws;
    }

    public void setJexx(String jexx)
    {
        this.jexx = jexx;
    }

    public void setJzrq(String jzrq)
    {
        this.jzrq = jzrq;
    }

    public void setNextPage(String nextPage)
    {
        this.nextPage = nextPage;
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public void setPageNum(String pageNum)
    {
        this.pageNum = pageNum;
    }

    public void setQsrq(String qsrq)
    {
        this.qsrq = qsrq;
    }

    public void setTotalCount(String totalCount)
    {
        this.totalCount = totalCount;
    }

    public void setTotalPage(String totalPage)
    {
        this.totalPage = totalPage;
    }

    public void setZgswjg(String zgswjg)
    {
        this.zgswjg = zgswjg;
    }

    public void setZgsws(String zgsws)
    {
        this.zgsws = zgsws;
    }

    public String getSzsmdm()
    {
        return szsmdm;
    }

    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public String getCzlb()
    {
        return czlb;
    }

    public void setCzlb(String czlb)
    {
        this.czlb = czlb;
    }

    public String getCxlx()
    {
        return cxlx;
    }

    public void setCxlx(String cxlx)
    {
        this.cxlx = cxlx;
    }

    public String getHzcxlx()
    {
        return hzcxlx;
    }

    public void setHzcxlx(String hzcxlx)
    {
        this.hzcxlx = hzcxlx;
    }

    public String getSwjgdmforfhqd()
    {
        return swjgdmforfhqd;
    }

    public void setSwjgdmforfhqd(String swjgdmforfhqd)
    {
        this.swjgdmforfhqd = swjgdmforfhqd;
    }

    public String getMx_jsjdm()
    {
        return mx_jsjdm;
    }

    public void setMx_jsjdm(String mx_jsjdm)
    {
        this.mx_jsjdm = mx_jsjdm;
    }

    public List getRstList()
    {
        return rstList;
    }

    public void setRstList(List rstList)
    {
        this.rstList = rstList;
    }




    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getQttitle()
    {
        return qttitle;
    }

    public void setQttitle(String qttitle)
    {
        this.qttitle = qttitle;
    }

    public String getCxjb()
    {
        return cxjb;
    }

    public void setCxjb(String cxjb)
    {
        this.cxjb = cxjb;
    }

    public String getDj_jsjdm()
    {
        return dj_jsjdm;
    }

    public void setDj_jsjdm(String dj_jsjdm)
    {
        this.dj_jsjdm = dj_jsjdm;
    }

    public String getBackAction()
    {
        return backAction;
    }

    public void setBackAction(String backAction)
    {
        this.backAction = backAction;
    }

    public List getHzList()
    {
        return hzList;
    }

    public List getMxList()
    {
        return mxList;
    }

    public void setHzList(List hzList)
    {
        this.hzList = hzList;
    }

    public void setMxList(List mxList)
    {
        this.mxList = mxList;
    }


}