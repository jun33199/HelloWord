/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 三代申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class DsdzdkForm
    extends BaseForm
{
    /**
     * 税种代码、税种名称、税种税目代码、税种税目名称、计税金额（课税数量）、税率、实缴税额、备注、申报汇总单号、票证种类代码、被代征人名称、完税证号
     *
     * 明细信息标示 String[]
     */
    private String columns[] =
                               {
                               "szdm", "szmc", "szsmdm", "szsmmc", "sl",
                               "jsje", "sjse", "pzzldm", "bdzrmc", "wszh"};

    /**
     *  上传的文件 excelFile
     */
    private FormFile excelFile;

    /**
     * 申报年度 String
     */
    private String nd;

    /**
     * 税务计算机代码 String
     *
     * 从录入数据中取得
     */
    private String jsjdm; //计算机代码

    /**
     * 纳税人名称 String
     *
     * 根据输入的税务计算机代码查询得到
     */
    private String nsrmc; //纳税单位名称

    /**
     * 创建时间 String
     */
    private String cjsj; //创建时间

    /**
     * 登记申报方式代码 String
     *
     * 从登录数据中取得
     */
    private String fsdm; //登记申报方式代码

    /**
     * 录入人代码 String
     *
     * 从登录数据中取得
     */
    private String lrr; //录入人

    /**
     * 录入日期 String
     */
    private String lrrq; //录入日期

    /**
     * 企业联系电话 String
     *
     * 根据计算机代码查询得到
     */
    private String qylxdh; //企业联系电话

    /**
     * 税款所属结束日期 String
     *
     * 系统根据申报日期自动计算
     */
    private String skssjsrq; //录入结束日期

    /**
     * 税款所属开始日期 String
     *
     * 系统根据申报日期自动计算
     */
    private String skssksrq; //录入开始日期

    /**
     * 税务机关组织机构代码 String
     *
     * 从登录数据中取得
     */
    private String swjgzzjgdm; //税务机关组织机构代码

    /**
     * 登记注册类型代码 String
     *
     * 根据计算机代码查询得到
     */
    private String djzclxdm; //登记注册类型代码

    /**
     * 国家标准行业代码 String
     *
     * 根据计算机代码查询得到
     */
    private String gjbzhydm; //国家标准行业代码

    /**
     * 隶属关系代码 String
     *
     * 根据计算机代码查询得到
     */
    private String lsgxdm; //隶属关系代码

    /**
     * 申报编号 String
     */
    private String sbbh; //申报编号

    /**
     * 申报汇总编号 String
     */
    private String sbhzdh; //申报汇总编号

    /**
     * 汇总日期 String
     */
    private String hzrq; //汇总日期

    /**
     * 汇总开始日期 String
     *
     * 系统根据汇总日期自动计算
     */
    private String hzksrq; // 汇总开始日期

    /**
     * 汇总结束日期 String
     *
     * 系统根据汇总日期自动计算
     */
    private String hzjsrq; // 汇总结束日期

    /**
     * 处理标志代码 String
     */
    private String clbjdm; // 处理标志代码

    /**
     * 数据来源 String
     */
    private String sjly; //数据来源

    /**
     * 银行代码 String
     */
    private String yhdm; // 银行代码

    /**
     * 银行名称 String
     */
    private String yhmc; // 银行名称

    /**
     * 银行帐号 String
     */
    private String zh; // 银行帐号

    /**
     * 申报日期 String
     */
    private String sbrq; //申报日期

    /**
     * 页面下拉菜框的显示内容 String
     */
    private String scriptStr;

    /**
     * 票证种类代码 String
     */
    private String pzzl; //票证种类代码

    /**
     * 分页长度 String
     */
    //分页用
    private int length;

    /**
     * 当前页 int
     */
    private int pgNum;

    /**
     * 总页 int
     */
    private int pgSum;

    private String iszhsb;

    /**
     * 区县代码
     */
    private String qxdm;

    public String getQxdm ()
    {
        return this.qxdm;
    }

    public void setQxdm (String _qxdm)
    {
        this.qxdm = _qxdm;
    }

    public String getIszhsb ()
    {
        return this.iszhsb;
    }

    public void setIszhsb (String _iszhsb)
    {
        this.iszhsb = _iszhsb;
    }

    public String getSjly ()
    {
        return this.sjly;
    }

    public void setSjly (String _sjly)
    {
        this.sjly = _sjly;
    }

    /**
     * 记税金额合计 BigDecimal
     */
    //记税金额合计
    private BigDecimal jsjehj = new BigDecimal(0.00);

    /**
     * 实缴税额合计 BigDecimal
     */
    //实缴税额合计
    private BigDecimal sjsehj = new BigDecimal(0.00);

    /**
     * 录入明细 List
     */
    //录入明细
    private List dataList = new ArrayList();

    /**
     * 三代明细 List
     */
    //三代明细
    private List sdmxDataList = new ArrayList();

    /**
     * 汇总单 List
     */
    //汇总单list
    private List hzdList = new ArrayList();

    /**
     * 汇总单中的缴款书 List
     */
    //汇总单中的缴款书list
    private List jkpzList = new ArrayList();

    private String headJkpzh;

    private String headJsjdm;

    private String nsrzt;

    public String getNd ()
    {
        return nd;
    }

    public void setNd (String _nd)
    {
        nd = _nd;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public void setHzdDataList (List _hzdList)
    {
        this.hzdList = _hzdList;
    }

    public List getHzdDataList ()
    {
        return this.hzdList;
    }

    public List getJkpzDataList ()
    {
        return this.jkpzList;
    }

    public void setJkpzDataList (List _jkpzList)
    {
        this.jkpzList = _jkpzList;
    }

    public List getMxDataList ()
    {
        return this.sdmxDataList;
    }

    public void setMxDataList (List _mxList)
    {
        this.sdmxDataList = _mxList;
    }

    public BigDecimal getJsjehj ()
    {
        return this.jsjehj;
    }

    public void setJsjehj (BigDecimal _jsjehj)
    {
        this.jsjehj = _jsjehj;
    }

    public BigDecimal getSjsehj ()
    {
        return this.sjsehj;
    }

    public void setSjsehj (BigDecimal _sjsehj)
    {
        this.sjsehj = _sjsehj;
    }

    public FormFile getExcelFile ()
    {
        return excelFile;
    }

    public void setExcelFile (FormFile _excelFile)
    {
        this.excelFile = _excelFile;
    }

    public String getSbrq ()
    {
        return this.sbrq;
    }

    public void setSbrq (String _sbrq)
    {
        this.sbrq = _sbrq;
    }

    public String getLsgxdm ()
    {
        return this.lsgxdm;
    }

    public void setLsgxdm (String _lsgxdm)
    {
        this.lsgxdm = _lsgxdm;
    }

    public String getGjbzhydm ()
    {
        return this.gjbzhydm;
    }

    public void setGjbzhydm (String _gjbzhydm)
    {
        this.gjbzhydm = _gjbzhydm;
    }

    public String getDjzclxdm ()
    {
        return this.djzclxdm;
    }

    public void setDjzclxdm (String _djzclxdm)
    {
        this.djzclxdm = _djzclxdm;
    }

    public String getYhdm ()
    {
        return this.yhdm;
    }

    public void setYhdm (String _yhdm)
    {
        this.yhdm = _yhdm;
    }

    public String getYhmc ()
    {
        return this.yhmc;
    }

    public void setYhmc (String _yhmc)
    {
        this.yhmc = _yhmc;
    }

    public String getZh ()
    {
        return this.zh;
    }

    public void setZh (String _zh)
    {
        this.zh = _zh;
    }

    public String getSbbh ()
    {
        return this.sbbh;
    }

    public void setSbbh (String _sbbh)
    {
        this.sbbh = _sbbh;
    }

    public String getSbhzdh ()
    {
        return this.sbhzdh;
    }

    public void setSbhzdh (String _sbhzdh)
    {
        this.sbhzdh = _sbhzdh;
    }

    public String getHzrq ()
    {
        return this.hzrq;
    }

    public void setHzrq (String _hzrq)
    {
        this.hzrq = _hzrq;
    }

    public String getHzksrq ()
    {
        return getSkssksrq();
    }

    public void setHzksrq (String _hzksrq)
    {
        this.hzksrq = _hzksrq;
    }

    public String getHzjsrq ()
    {
        return getSkssjsrq();
    }

    public void setHzjsrq (String _hzjsrq)
    {
        this.hzjsrq = _hzjsrq;
    }

    public String getClbjdm ()
    {
        return this.clbjdm;
    }

    public void setClbjdm (String _clbjdm)
    {
        this.clbjdm = _clbjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getCjrq ()
    {
        return cjsj;
    }

    public void setCjrq (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
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

    public String getQylxdh ()
    {
        return qylxdh;
    }

    public void setQylxdh (String qylxdh)
    {
        this.qylxdh = qylxdh;
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

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public String getPzzl ()
    {
        return pzzl;
    }

    public void setPzzl (String pzzl)
    {
        this.pzzl = pzzl;
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

    /**
     * 页面要素清除重设
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        pgNum = 0;
        pgSum = 0;
        length = 0;
        this.nsrmc = null;
        this.jsjdm = null;
        this.qylxdh = null;
        this.nsrzt = CodeConstant.NSRZT_ZC;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
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

    public String getNsrzt ()
    {
        return nsrzt;
    }

    public void setNsrzt (String nsrzt)
    {
        this.nsrzt = nsrzt;
    }

}