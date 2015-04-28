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
     * 操作类型 1：转入，2：转出，3：减少，4：新增，5：临时登记
     */
    private String czlb;
    /**
     * 查询类型 1：入库，2：退税
     */
    private String cxlx;
    /**
     * 查询级别（1：分局级别查询；2：税务所级别查询）
     */
    private String cxjb;

    /**
     * 汇总查询类型 1：分局入库情况查询，2：分局退税情况查询，
     *            3：税务所入库情况查询，4：税务所退税情况查询
     */
    private String hzcxlx;
    /**
     * 分户清单的查询条件(税务机关组织机构代码)
     */
    private String swjgdmforfhqd;
    /**
     * 要跳转的页面在struct-config对应的配置
     */
    private String nextPage;

    /**
     * 返回查询的数据
     */
    private List rstList = new ArrayList();
    /**
     * 返回查询的数据
     */
    private List hzList = new ArrayList();
    /**
     * 返回查询的数据
     */
    private List mxList = new ArrayList();

    /**
     * 页面序号
     */
    private int pageIndex = 0;

    /**
     * 当前页码
     */
    private String pageNum;

    /**
     * 查询出的总页数
     */
    private String totalPage;

    /**
     * 查询出的记录总数
     */
    private String totalCount;

    /**
     * 主管税务机关
     */
    private String zgswjg;

    /**
     * 主管税务所
     */
    private String zgsws;

    /**
     * 起始时间
     */
    private String qsrq;

    /**
     * 截止时间
     */
    private String jzrq;

    /**
     * 税额下限
     */
    private String jexx;
    /**
     *税种税目代码
     */
    private String szsmdm;
    /**
     * 明细清单计算机代码
     */
    private String mx_jsjdm;
    /**
     * 登记信息计算机代码
     */
    private String dj_jsjdm;


    /**
     * 标题
     */
    private String title;
    /**
     * 其他标题
     */
    private String qttitle;
    /**
     * 页面返回时使用的隐藏变量（隐藏返回的Action）
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