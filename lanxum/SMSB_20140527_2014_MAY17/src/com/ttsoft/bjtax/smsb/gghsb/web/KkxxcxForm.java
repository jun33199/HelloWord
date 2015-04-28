package com.ttsoft.bjtax.smsb.gghsb.web;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003-2004北京市地方税务局，清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author not attributable
 * @version 1.0
 */

import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.*;


/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description: 扣款信息查询Form</p>
 * @author not attributable
 * @version 1.0
 */

public class KkxxcxForm extends BaseForm{
    public KkxxcxForm() {
        dataList = new ArrayList();
    }

    /**
     * 明细项目集合
     */
    private java.util.ArrayList dataList;


    /**
     * 分页：每页纪录数
     */
    private int length;

    /**
     * 分页：当前页数
     */
    private int pgNum;

    /**
     * 分页：总页数
     */
    private int pgSum;

    /** 年度          */
    private String jsjdm;

    /** 年度          */
    private String nd;
    /** 征期          */
    private String zq;
    /** 区县          */
    private String qx;
    /** 税务所        */
    private String swjs;
    /** 街乡          */
    private String jx;
    /** 街乡名称*/
    private String jxmc;
    /** 扣款标志      */
    private String kkbz;
    /** 扣款不成功原因*/
    private String kkbcgyy;

    /** 街乡列表*/
    private ArrayList jxList =null;
    /** 税务所列表*/
    private ArrayList swjsList = null;
    /**区县列表 */
    private ArrayList qxList = null;
    /** 扣款金额合计*/
    private String kkjehj;
    /**期应纳税额合计 */
    private String ynjehj;
    /**记录数 */
    private String jls;
    /**户数合计 */
    private String hshj;

    /**扣款信息中文名称 */
    private String[] dataListTitle;
    /**扣款信息key */
    private String[] dataListKey;
    /** 合计信息数据集*/
    private List sumList;
    /** 合计信息名称*/
    private String[] sumListTitle;
    /** 合计信息key*/
    private String[] sumListKey;




    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        pgNum = 0;
        pgSum = 0;
        length = 0;
    }
    public java.util.ArrayList getDataList() {
        return dataList;
    }
    public int getPgNum() {
        return pgNum;
    }
    public int getLength() {
        return length;
    }
    public int getPgSum() {
        return pgSum;
    }
    public void setPgSum(int pgSum) {
        this.pgSum = pgSum;
    }
    public void setPgNum(int pgNum) {
        this.pgNum = pgNum;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDataList(java.util.ArrayList dataList) {
        this.dataList = dataList;
    }
    public String getJx() {
        return jx;
    }
    public String getKkbcgyy() {
        return kkbcgyy;
    }
    public String getKkbz() {
        return kkbz;
    }
    public String getNd() {
        return nd;
    }
    public String getQx() {
        return qx;
    }
    public String getSwjs() {
        return swjs;
    }
    public String getZq() {
        return zq;
    }
    public void setZq(String zq) {
        this.zq = zq;
    }
    public void setSwjs(String swjs) {
        this.swjs = swjs;
    }
    public void setNd(String nd) {
        this.nd = nd;
    }
    public void setQx(String qx) {
        this.qx = qx;
    }
    public void setKkbcgyy(String kkbcgyy) {
        this.kkbcgyy = kkbcgyy;
    }
    public void setJx(String jx) {
        this.jx = jx;
    }
    public void setKkbz(String kkbz) {
        this.kkbz = kkbz;
    }

    public ArrayList getJxList() {
        return jxList;
    }

    public void setJxList(ArrayList jxList) {
        this.jxList = jxList;
    }

    public ArrayList getSwjsList() {
        return swjsList;
    }

    public void setSwjsList(ArrayList swjsList) {
        this.swjsList = swjsList;
    }

    public String getKkjehj() {
        if (kkjehj == null){
            kkjehj="";
        }
        return kkjehj;
    }

    public void setKkjehj(String kkjehj) {
        this.kkjehj = kkjehj;
    }

    public String getYnjehj() {
        if(ynjehj == null){
            ynjehj="";
        }
        return ynjehj;
    }

    public void setYnjehj(String ynjehj) {
        this.ynjehj = ynjehj;
    }

    public String getJls() {
        if(jls == null){
            jls ="";
        }
        return jls;
    }

    public void setJls(String jls) {
        this.jls = jls;
    }

    public String[] getDataListTitle() {
        return dataListTitle;
    }

    public void setDataListTitle(String[] dataListTitle) {
        this.dataListTitle = dataListTitle;
    }

    public String[] getDataListKey() {
        return dataListKey;
    }

    public void setDataListKey(String[] dataListKey) {
        this.dataListKey = dataListKey;
    }

    public List getSumList() {
        return sumList;
    }

    public void setSumList(List sumList) {
        this.sumList = sumList;
    }

    public String[] getSumListTitle() {
        return sumListTitle;
    }

    public void setSumListTitle(String[] sumListTitle) {
        this.sumListTitle = sumListTitle;
    }

    public String[] getSumListKey() {
        return sumListKey;
    }

    public void setSumListKey(String[] sumListKey) {
        this.sumListKey = sumListKey;
    }

    public String getJxmc() {
        return jxmc;
    }

    public void setJxmc(String jxmc) {
        this.jxmc = jxmc;
    }

    public ArrayList getQxList() {
        return qxList;
    }

    public void setQxList(ArrayList qxList) {
        this.qxList = qxList;
    }
  public String getHshj() {
    return hshj;
  }
  public void setHshj(String hshj) {
    this.hshj = hshj;
  }
  public String getJsjdm() {
    return jsjdm;
  }
  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }
}