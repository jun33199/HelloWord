package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;

/**
 * 查询完税证的bo
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QueryWszBo implements Serializable {
    //这些是前台页面的查询条件

    /**
     * 年度字别
     */
    private String ndzb;
    
    /**
     * 票证种类代码
     */
    public String pzzldm;

    /**
     * nsrmc
     */
    private String nsrmc;

    /**
     * jsjdm
     */
    private String jsjdm;

    /**
     * 起始完税证号
     */
    private String startWszh;

    /**
     * 截止完税证号
     */
    private String endWszh;

    /**
     * 创建（填发）起始日期
     */
    private String startCjrq;

    /**
     * 创建（填发）截止日期
     */
    private String endCjrq;

    //以下是查询结果

    /**
     * 缴款凭证号
     */
    private String jkpzh;

    /**
     * 契税完税证主表vo
     */
    private Qswszz qswszzVo = new Qswszz();

    /**
     * 明细表vo
     */
    private Qswszmx mxVo;

    /**
     * 用来存放明细表vo的ArrayList
     */
    private ArrayList resultList;

    /**
     * 申报表号
     */
    private String sbbh;

    public String getEndCjrq() {
        return endCjrq;
    }

    public String getEndWszh() {
        return endWszh;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getStartCjrq() {
        return startCjrq;
    }

    public String getStartWszh() {
        return startWszh;
    }

    public Qswszmx getMxVo() {
        return mxVo;
    }

    public Qswszz getQswszzVo() {
        return qswszzVo;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public ArrayList getResultList() {
        return resultList;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setEndCjrq(String endCjrq) {
        this.endCjrq = endCjrq;
    }

    public void setEndWszh(String endWszh) {
        this.endWszh = endWszh;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setStartCjrq(String startCjrq) {
        this.startCjrq = startCjrq;
    }

    public void setStartWszh(String startWszh) {
        this.startWszh = startWszh;
    }

    public void setMxVo(Qswszmx mxVo) {
        this.mxVo = mxVo;
    }

    public void setQswszzVo(Qswszz qswszzVo) {
        this.qswszzVo = qswszzVo;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    /**
     * @return Returns the nsrmc.
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * @param nsrmc The nsrmc to set.
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * @return Returns the jsjdm.
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * @param jsjdm The jsjdm to set.
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

	public String getPzzldm() {
		return pzzldm;
	}

	public void setPzzldm(String pzzldm) {
		this.pzzldm = pzzldm;
	}
}
