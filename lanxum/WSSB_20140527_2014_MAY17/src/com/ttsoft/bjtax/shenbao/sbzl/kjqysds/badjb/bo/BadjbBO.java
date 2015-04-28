package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo;

/**
 * <p>Title: 备案登记表BO</p>
 *
 * <p>Description: 记录备案登记表相关应用的键值，用于后台交互</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.Serializable;
import java.util.List;

import com.syax.bjtax.shenbao.model.kjqysds.*;


public class BadjbBO implements Serializable
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
     * 展示填报日期
     *     格式为yyyy年mm月dd日
     */
    private String tbrqShow;

    /**
     * 扣缴人计算机代码
     */
    private String jsjdm;
    
    /**
     * 修改标记
     *    0-未修改操作|1-修改操作
     */
    private String modifyFlag;

    /**
     * 扣缴人信息
     */
    private KJYWRXX kjywrxx;

    /**
     * 非居民企业信息
     */
    private FJMQYXX fjmqyxx;

    /**
     * 合同信息
     */
    private BAHTXX htxx;
    

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
     *     状态标识:0-初始状态|1-确认备案通过|2-确认备案不通过|3-同一用户合同编号重复
     */
    private String ztbz;
    
    /**
     * 提示信息
     */
    private String message;


    public BadjbBO()
    {
    }

    public String getBadjbbh()
    {
        return badjbbh;
    }

    public FJMQYXX getFjmqyxx()
    {
        return fjmqyxx;
    }

    public BAHTXX getHtxx()
    {
        return htxx;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public KJYWRXX getKjywrxx()
    {
        return kjywrxx;
    }

    public String getTbrq()
    {
        return tbrq;
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

    public String getBadjxh()
    {
        return badjxh;
    }

    public List getRecordList()
    {
        return recordList;
    }

    public String getZtbz()
    {
        return ztbz;
    }

    public String getTbrqShow()
    {
        return tbrqShow;
    }

    public void setTbrq(String tbrq)
    {
        this.tbrq = tbrq;
    }

    public void setKjywrxx(KJYWRXX kjywrxx)
    {
        this.kjywrxx = kjywrxx;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
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

    public void setBadjxh(String badjxh)
    {
        this.badjxh = badjxh;
    }

    public void setRecordList(List recordList)
    {
        this.recordList = recordList;
    }

    public void setZtbz(String ztbz)
    {
        this.ztbz = ztbz;
    }

    public void setTbrqShow(String tbrqShow)
    {
        this.tbrqShow = tbrqShow;
    }

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
