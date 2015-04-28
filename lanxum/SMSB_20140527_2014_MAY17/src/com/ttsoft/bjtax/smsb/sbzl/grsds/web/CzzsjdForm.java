/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 查帐征收个人独资企业和合伙企业投资者个人所得税季度申报表 ActionForm</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class CzzsjdForm
    extends BaseForm
{
    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 税务登记证号
     */
    private String swdjzh;

    /**
     * 税务机关组织机构代码
     */
    private String swjgzzjgdm;

    /**
     * 录入人代码
     */
    private String lrr;

    /**
     * 登记申报方式代码
     */
    private String fsdm;

    /**
     * 利润总额
     */
    private String lrze;

    /**
     * 季度
     */
    private String jd;

    /**
     * 年度
     */
    private String nd;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 税款所属结束日期
     */
    private String skssjsrq;

    /**
     * 税款所属开始日期
     */
    private String skssksrq;

    /**
     *  证件号代码、证件类型代码、投资者姓名、分配比例、应纳税所得额、适用税率、速算扣除数、应纳所得税额、减免税额、期初未缴纳所得税额、已缴纳所得税额、实际应缴税额
     */
    private String columns[] =
        {"zjlxdm", "zjhm", "tzzxm", "fpbl", "ynssde",
        "sysl", "sskcs", "ynsdse", "jmse", "qcwjsdse",
        "yjnsdse", "sjyjse"};

    /**
     * 用于存储在网页中的域表下的datalist中的数据，注意如果不初始化将会出现系统错误
     */
    private List dataList = new ArrayList();

    /**
     * 征收方式
     */
    private String zsfs;

    /**
     * 是否来自综合申报
     *
     */
    private String iszhsb;

    /**
     * 区县代码
     */
    private String qxdm;

    /**
     * 创建日期
     */
    private String cjrq;

    /**
     * 用于存储后台调出来的个体工商税目代码中的有关税率、速算扣除数、应纳税起始数、应纳税终止数放到页面中存储，以便JavaScript调用计算处理
     */
    private String lrrq;

    //得到创建时间
    public String getCjrq ()
    {
        return cjrq;
    }

    //设置创建时间
    public void setCjrq (String cjrq)
    {
        this.cjrq = cjrq;
    }

    //得到登记申报方式代码
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
     * 页面要素清除
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */
    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        this.actionType = "Show";
        this.jsjdm = null; //计算机代码
        this.swdjzh = null; //税务登记证号
        this.swjgzzjgdm = null; //税务机关组织机构代码
        this.lrze = null; //利润总额
        this.nsrmc = null; //纳税人名称
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