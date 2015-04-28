/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 告知事项维护</p>
 * @author 开发六组－－石岩峰
 * @version 1.1
 */
public class GzsxwhForm
    extends BaseForm
{

    public GzsxwhForm ()
    {
        //默认值 -> 选择计算机名称
        chooseTypeRadio = "1";
        dataList = new java.util.ArrayList();
        //得到当前时间
        strNow = SfDateUtil.getDate();
    }
    private String  cxdqjs1;
    /**
     * 选择类型
     */
    private String chooseTypeRadio;
    /**
     * 查询使用的地区局所
     */
    private String cxdqjs;

    /**
     * 记录数
     */
    private String jlcount;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * 纳税人名称
     */
    private String nsrmc;

    /**
     * 企业类型
     */
    private String qylx;
    private String qylx1;

    /**
     * 告知类型
     */
    private String gzlx;

    /**
     * 告知起始日期
     */
    private String gzqsrq;

    /**
     * 告知截止日期
     */
    private String gzjzrq;

    /**
     * 行业类别
     */
    private String hylb;
    private String hylb1;
    /**
     * 阅读参数
     */
    private String ydcs;

    /**
     * 地区局所
     */
    private String dqjs;

    /**
     * 结合方式
     */
    private String jhfs;

    /**
     * 保存类型:新增或更新
     */
    private String saveType;

    /**
     * 明细项目集合
     */
    private java.util.ArrayList dataList;

    /**
     * 明细计算机代码
     */
    private String mxJsjdm;

    /**
     * 明细纳税人名称
     */
    private String mxNsrmc;

    /**
     * 明细告知事项
     */
    private String mxGzsxxxxx;

    /**
     * 明细告知类型
     */
    private String mxGzlx;

    /**
     * 明细告知起始日期
     */
    private String mxGzqsrq;

    /**
     * 明细告知截止日期
     */
    private String mxGzjzrq;

    /**
     * 删除使用的check box标志
     */
    private String[] deleteCheckbox;

    /**
     * 明细企业类型
     */
    private String mxQylx;

    /**
     * 明细列表的选中rodio值
     */
    private String mxChooseTypeRadio;

    /**
     * 修改纪录的序号
     */
    private String modifyIndex;

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

    /**
     * 明细行业类别
     */
    private String mxHylb;

    /**
     * 明细地区局所
     */
    private String mxDqjs;

    /**
     * 明细结合方式
     */
    private String mxJhfs;

    /**
     * 明细列表的radio值
     */
    private String mxChooseTypeRadioHidden;

    /**
     * 当前时间
     */
    private String strNow;

    /**
     * 包含计算机代码的excel文件
     */
    private org.apache.struts.upload.FormFile excelFile;

    /**
     * excel文件中的计算机代码列表
     */
    private java.util.ArrayList jsjdmList = new ArrayList();

    ;

    /**
     * 批号list
     */
    private java.util.ArrayList phList = new ArrayList();

    /**
     * 批号
     */
    private String ph;
    /**
     * 针对一个纳税人的告知事项2009.4.7wcl增加。
     * 
     */
    private List jsjdmgzsxlilst;
    /**
     * 告知事项反馈查询中针对一组条件的查询结果2009.4.7wcl增加。
     * 
     */
    private List tjgzsxlilst;
    /**
     * 告知事项反馈查询中登记类型条件2009.4.7wcl增加。
     * 
     */
    private List nsrztlilst;
    /**
     * 告知事项反馈查询中所处街乡条件2009.4.7wcl增加。
     * 
     */
    private List scjxlilst;
    /**
     * 告知事项反馈查询告知发出税务机关条件2009.4.7wcl增加。
     * 
     */
    private List swdwlilst;
    /**
     * 告知事项反馈查询范围税务机关条件2009.4.7wcl增加。
     * 
     */
    private List cxswdwlilst;
    /**
     * 告知事项反馈查询中纳税人状态条件2009.4.7wcl增加。
     * 
     */
    private List djlxlilst;
    /**
     * 告知事项反馈查询中行业类型条件2009.4.7wcl增加。
     * 
     */
    private List hylxlilst;
    /**
     * 告知事项反馈查询中每一条告知事项对应的id,2009.4.7wcl增加。
     * 
     */
    private String gzsx_id;
    /**
     * 告知事项反馈查寻中纳税人状态条件,2009.4.7wcl增加。
     * 
     */
    private String nsrzt;
    /**
     * 告知事项反馈查询中告知事项的标题,2009.4.7wcl增加。
     * 
     */
    private String gzsxnrbt;
    /**
     * 告知事项反馈查询中所处街乡,2009.4.7wcl增加。
     * 
     */
    private String jxdm;
    private String jxdm1;
    /**
     * 告知事项反馈查询中反馈的详细内容,2009.4.7wcl增加。
     * 
     */
    private String fknr;
    /**
     * 告知事项反馈查询中获得告知详细的详细内容,2009.4.7wcl增加。
     * 
     */
    
    private String gzsxnr;
    public String getGzsx_id() {
		return gzsx_id;
	}

	public String getJxdm1() {
		return jxdm1;
	}

	public void setJxdm1(String jxdm1) {
		this.jxdm1 = jxdm1;
	}

	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}

	public List getJsjdmgzsxlilst() {
		return jsjdmgzsxlilst;
	}

	public void setJsjdmgzsxlilst(List jsjdmgzsxlilst) {
		this.jsjdmgzsxlilst = jsjdmgzsxlilst;
	}

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        pgNum = 0;
        pgSum = 0;
        length = 0;
    }

    public String getChooseTypeRadio ()
    {
        return chooseTypeRadio;
    }

    public void setChooseTypeRadio (String chooseTypeRadio)
    {
        this.chooseTypeRadio = chooseTypeRadio;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getQylx ()
    {
        return qylx;
    }

    public void setQylx (String qylx)
    {
        this.qylx = qylx;
    }

    public String getGzlx ()
    {
        return gzlx;
    }

    public void setGzlx (String gzlx)
    {
        this.gzlx = gzlx;
    }

    public String getGzqsrq ()
    {
        return gzqsrq;
    }

    public void setGzqsrq (String gzqsrq)
    {
        this.gzqsrq = gzqsrq;
    }

    public String getGzjzrq ()
    {
        return gzjzrq;
    }

    public void setGzjzrq (String gzjzrq)
    {
        this.gzjzrq = gzjzrq;
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getSaveType ()
    {
        return saveType;
    }

    public void setSaveType (String saveType)
    {
        this.saveType = saveType;
    }

    public String getMxJsjdm ()
    {
        return mxJsjdm;
    }

    public void setMxJsjdm (String mxJsjdm)
    {
        this.mxJsjdm = mxJsjdm;
    }

    public String getMxNsrmc ()
    {
        return mxNsrmc;
    }

    public void setMxNsrmc (String mxNsrmc)
    {
        this.mxNsrmc = mxNsrmc;
    }

    public String getMxGzsxxxxx ()
    {
        return mxGzsxxxxx;
    }

    public void setMxGzsxxxxx (String mxGzsxxxxx)
    {
        this.mxGzsxxxxx = mxGzsxxxxx;
    }

    public String getMxGzlx ()
    {
        return mxGzlx;
    }

    public void setMxGzlx (String mxGzlx)
    {
        this.mxGzlx = mxGzlx;
    }

    public String getMxGzqsrq ()
    {
        return mxGzqsrq;
    }

    public void setMxGzqsrq (String mxGzqsrq)
    {
        this.mxGzqsrq = mxGzqsrq;
    }

    public String getMxGzjzrq ()
    {
        return mxGzjzrq;
    }

    public void setMxGzjzrq (String mxGzjzrq)
    {
        this.mxGzjzrq = mxGzjzrq;
    }

    public String[] getDeleteCheckbox ()
    {
        return deleteCheckbox;
    }

    public void setDeleteCheckbox (String[] deleteCheckbox)
    {
        this.deleteCheckbox = deleteCheckbox;
    }

    public String getMxQylx ()
    {
        return mxQylx;
    }

    public void setMxQylx (String mxQylx)
    {
        this.mxQylx = mxQylx;
    }

    public String getMxChooseTypeRadio ()
    {
        return mxChooseTypeRadio;
    }

    public void setMxChooseTypeRadio (String mxChooseTypeRadio)
    {
        this.mxChooseTypeRadio = mxChooseTypeRadio;
    }

    public String getModifyIndex ()
    {
        return modifyIndex;
    }

    public void setModifyIndex (String modifyIndex)
    {
        this.modifyIndex = modifyIndex;
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

    public String getHylb ()
    {
        return hylb;
    }

    public void setHylb (String hylb)
    {
        this.hylb = hylb;
    }

    public String getDqjs ()
    {
        return dqjs;
    }

    public void setDqjs (String dqjs)
    {
        this.dqjs = dqjs;
    }

    public String getJhfs ()
    {
        return jhfs;
    }

    public void setJhfs (String jhfs)
    {
        this.jhfs = jhfs;
    }

    public String getMxHylb ()
    {
        return mxHylb;
    }

    public void setMxHylb (String mxHylb)
    {
        this.mxHylb = mxHylb;
    }

    public String getMxDqjs ()
    {
        return mxDqjs;
    }

    public void setMxDqjs (String mxDqjs)
    {
        this.mxDqjs = mxDqjs;
    }

    public String getMxJhfs ()
    {
        return mxJhfs;
    }

    public void setMxJhfs (String mxJhfs)
    {
        this.mxJhfs = mxJhfs;
    }

    public String getMxChooseTypeRadioHidden ()
    {
        return mxChooseTypeRadioHidden;
    }

    public void setMxChooseTypeRadioHidden (String mxChooseTypeRadioHidden)
    {
        this.mxChooseTypeRadioHidden = mxChooseTypeRadioHidden;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public org.apache.struts.upload.FormFile getExcelFile ()
    {
        return excelFile;
    }

    public void setExcelFile (org.apache.struts.upload.FormFile excelFile)
    {
        this.excelFile = excelFile;
    }

    public java.util.ArrayList getJsjdmList ()
    {
        return jsjdmList;
    }

    public void setJsjdmList (java.util.ArrayList jsjdmList)
    {
        this.jsjdmList = jsjdmList;
    }

    public java.util.ArrayList getPhList ()
    {
        return phList;
    }

    public void setPhList (java.util.ArrayList phList)
    {
        this.phList = phList;
    }

    public String getPh ()
    {
        return ph;
    }

    public void setPh (String ph)
    {
        this.ph = ph;
    }

	public List getTjgzsxlilst() {
		return tjgzsxlilst;
	}

	public void setTjgzsxlilst(List tjgzsxlilst) {
		this.tjgzsxlilst = tjgzsxlilst;
	}

	public String getNsrzt() {
		return nsrzt;
	}

	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}

	public String getGzsxnrbt() {
		return gzsxnrbt;
	}

	public void setGzsxnrbt(String gzsxnrbt) {
		this.gzsxnrbt = gzsxnrbt;
	}

	public String getJxdm() {
		return jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	public List getHylxlilst() {
		return hylxlilst;
	}

	public void setHylxlilst(List hylxlilst) {
		this.hylxlilst = hylxlilst;
	}

	public List getDjlxlilst() {
		return djlxlilst;
	}

	public void setDjlxlilst(List djlxlilst) {
		this.djlxlilst = djlxlilst;
	}

	public List getNsrztlilst() {
		return nsrztlilst;
	}

	public void setNsrztlilst(List nsrztlilst) {
		this.nsrztlilst = nsrztlilst;
	}

	public List getScjxlilst() {
		return scjxlilst;
	}

	public void setScjxlilst(List scjxlilst) {
		this.scjxlilst = scjxlilst;
	}

	public List getSwdwlilst() {
		return swdwlilst;
	}

	public void setSwdwlilst(List swdwlilst) {
		this.swdwlilst = swdwlilst;
	}

	public String getGzsxnr() {
		return gzsxnr;
	}

	public void setGzsxnr(String gzsxnr) {
		this.gzsxnr = gzsxnr;
	}

	public String getJlcount() {
		return jlcount;
	}

	public void setJlcount(String jlcount) {
		this.jlcount = jlcount;
	}

	public String getYdcs() {
		return ydcs;
	}

	public void setYdcs(String ydcs) {
		this.ydcs = ydcs;
	}

	public List getCxswdwlilst() {
		return cxswdwlilst;
	}

	public void setCxswdwlilst(List cxswdwlilst) {
		this.cxswdwlilst = cxswdwlilst;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getCxdqjs1() {
		return cxdqjs1;
	}

	public void setCxdqjs1(String cxdqjs1) {
		this.cxdqjs1 = cxdqjs1;
	}

	public String getQylx1() {
		return qylx1;
	}

	public void setQylx1(String qylx1) {
		this.qylx1 = qylx1;
	}

	public String getHylb1() {
		return hylb1;
	}

	public void setHylb1(String hylb1) {
		this.hylb1 = hylb1;
	}

	
	
}