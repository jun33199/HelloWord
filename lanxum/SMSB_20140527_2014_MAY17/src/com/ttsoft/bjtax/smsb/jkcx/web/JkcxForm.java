package com.ttsoft.bjtax.smsb.jkcx.web;

import java.math.*;
import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.framework.form.*;
import com.ttsoft.framework.util.Currency;
import org.apache.struts.action.*;

public class JkcxForm extends BaseForm {
    /**
     * 计算机代码
     */
    private String jsjdm;
    /**
     * 申报日期的年度月份（系统默认带出系统当前月份）
     */
    private String sbrq;
    /**
     * 税款类型
     */
    private String sklx;

    private List dataList = new ArrayList();

    private List mxList = new ArrayList();

    private String index = "0";

    private String jkpzh;

    private String sbbh;

    private String narmc;

    private BigDecimal hj;

    private Sbjkzb zb;

    private String sjly;
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
     *总记录数
     */
    private int totalRecord = 0;

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
        this.jsjdm = "";
        this.sbrq = "";
        this.pgNum = 1;
        this.pgSum = 1;
        this.sklx = "";
    }

    public JkcxForm() {

    }

    public String getSjsehjdx()
    {
        return Currency.convert(this.hj);
    }

    public Object getData()
    {
        Map dataMap = new HashMap();
        dataMap.put("jsjdm",this.jsjdm);
        dataMap.put("sbrq",this.sbrq);
        dataMap.put("sklx",this.sklx);
        return dataMap;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getSklx() {
        return sklx;
    }

    public List getDataList() {
        return dataList;
    }

    public int getPageLength() {
        return pageLength;
    }

    public int getPgNum() {
        return pgNum;
    }

    public int getPgSum() {
        return pgSum;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public String getSbrq() {
        return sbrq;
    }

    public String getIndex() {
        return index;
    }

    public List getMxList() {
        return mxList;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public BigDecimal getHj() {
        return hj;
    }

    public Sbjkzb getZb() {
        return zb;
    }

    public String getSjly() {
        return sjly;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getNarmc() {
        return narmc;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setSklx(String sklx) {
        this.sklx = sklx;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public void setPgNum(int pgNum) {
        this.pgNum = pgNum;
    }

    public void setPgSum(int pgSum) {
        this.pgSum = pgSum;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setMxList(List mxList) {
        this.mxList = mxList;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setHj(BigDecimal hj) {
        this.hj = hj;
    }

    public void setZb(Sbjkzb zb) {
        this.zb = zb;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setNarmc(String narmc) {
        this.narmc = narmc;
    }
}
