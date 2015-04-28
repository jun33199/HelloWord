package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: 扣缴企业所得税-备案登记表Form</p>
 *
 * <p>Description: 记录备案登记表页面域值</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.FJMQYXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.framework.form.BaseForm;

public class BadjbForm extends BaseForm
{
    /**
     * 备案登记序号（主键）
     */
    private String badjxh;

    /**
     * 备案登记表编号
     */
    private String badjbbh;

    /**
     * 填报日期
     */
    private String tbrq;

    /**
     * 扣缴人计算机代码
     */
    private String jsjdm;

    /**
     * 扣缴人信息
     */
    private KJYWRXX kjywrxx = new KJYWRXX();

    /**
     * 非居民企业信息
     */
    private FJMQYXX fjmqyxx = new FJMQYXX();

    /**
     * 合同信息
     */
    private BAHTXX htxx = new BAHTXX();

    /**
     * 记录数
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 查询记录
     */
    private List recordList;

    /**
     * 审核状态标识
     *     状态标识:0-初始状态|1-确认备案通过|2-确认备案不通过
     */
    private String ztbz;
    
    /**
     * 修改标识
     *    true--修改备案登记表操作
     *    false--非修改备案登记表操作
     */
    private boolean modifyFlag = false;


    public BadjbForm()
    {
    }

    public String getBadjbbh()
    {
        return badjbbh;
    }

    public BAHTXX getHtxx()
    {
        return htxx;
    }

    public String getTbrq()
    {
        return tbrq;
    }

    public FJMQYXX getFjmqyxx()
    {
        return fjmqyxx;
    }

    public KJYWRXX getKjywrxx()
    {
        return kjywrxx;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public List getRecordList()
    {
        return recordList;
    }

    public String getBadjxh()
    {
        return badjxh;
    }

    public String getZtbz()
    {
        return ztbz;
    }

    public void setTbrq(String tbrq)
    {
        this.tbrq = tbrq;
    }

    public void setKjywrxx(KJYWRXX kjywrxx)
    {
        this.kjywrxx = kjywrxx;
    }

    public void setHtxx(BAHTXX htxx)
    {
        this.htxx = htxx;
    }

    public void setFjmqyxx(FJMQYXX fjmqyxx)
    {
        this.fjmqyxx = fjmqyxx;
    }

    public void setBadjbbh(String badjbbh)
    {
        this.badjbbh = badjbbh;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public void setRecordList(List recordList)
    {
        this.recordList = recordList;
    }

    public void setBadjxh(String badjxh)
    {
        this.badjxh = badjxh;
    }

    public void setZtbz(String ztbz)
    {
        this.ztbz = ztbz;
    }

	public boolean getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(boolean modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

}
