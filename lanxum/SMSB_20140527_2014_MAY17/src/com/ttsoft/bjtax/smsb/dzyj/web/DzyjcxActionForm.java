/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.dzyj.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzyjcxActionForm
    extends BaseForm
{
    /**
     * 计算机代码
     */
    private String jsjdm="";

    /**
     * 起始时间
     */
    private String qssj="";

    /**
     * 截止时间
     */
    private String jzsj="";

    /**
     * 业务类型
     */
    private String ywlx="";

    /**
     * 业务类型明细数据列表
     */
    private java.util.List ywlxList = new ArrayList();

    /**
     * 操作类型
     */
    private String czlx="";

    /**
     * 业务关联值（业务uid)
     */
    private String ywuid="";

    /**
     * 纳税人名称
     */
    private String nsrmc="";

    /**
     *总记录数
     */
    private int totalRecord = 0;
    
    /**
     *分页:每页显示纪录的数目
     */
    private int pageLength = 10;

    /**
     *分页:查询结果总页数
     */
    private int pgSum = 1;

    /**
     *分页:当前页数
     */
    private int pgNum = 1;
    
    /**
     *显示数据
     */
    private java.util.List dataList = new ArrayList();

    /**
     *结果数据
     */
    private java.util.List resultList = new ArrayList();

    /**
     * 单条数据的流水号
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
