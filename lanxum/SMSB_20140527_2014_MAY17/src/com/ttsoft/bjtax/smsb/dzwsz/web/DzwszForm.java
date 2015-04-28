/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.web;

import java.util.*;

import com.ttsoft.bjtax.smsb.dzwsz.processor.*;
import com.ttsoft.framework.form.*;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Form。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class DzwszForm extends BaseForm {
    public DzwszForm() {
    }

    /**
     * 交易流水号
     */
    private String jylsh;
    /**
     * 计算机代码
     */
    private String jsjdm;
    /**
     * 税票号码
     */
    private String sphm;
    /**
     * 当前页数
     */
    private String currentPage;

    /**
     * 前台展示bo
     */
    private DzwszBO bo;
    /**
     * 打印bo
     */
    private List boList;
    /**
     *  是否打印 有值为已打印
     */
    private String dy;
    
   /* 201306 kanght*/
    
    /**
     *完税证查询 
     */
    private String fkpzjsjdm;
    /**
     *完税证查询 
     */
    private String fkpzsphm;
    /**
     *完税证查询 
     */
    private String czjsjdm;
    /**
     *依存折查询条件--查询终止日期
     */
    private String czcxqrq;
    /**
     *完税证查询 
     */
    private String czcxzrq;
    
    /**
     *完税证列表 
     */
    private List wszList;
    /**
     *打印完税证--计算机代码
     */
    private String dyjsjdm;
    /**
     *打印完税证--交易流水
     */
    private String dyjylsh;
    /**
     *打印完税证--税票号码
     */
    private String dysphm;
    /**
     *查询完税证--条件标识
     */
    private String flag;
    
    
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFkpzjsjdm() {
		return fkpzjsjdm;
	}

	public void setFkpzjsjdm(String fkpzjsjdm) {
		this.fkpzjsjdm = fkpzjsjdm;
	}

	public String getFkpzsphm() {
		return fkpzsphm;
	}

	public void setFkpzsphm(String fkpzsphm) {
		this.fkpzsphm = fkpzsphm;
	}

	public String getCzjsjdm() {
		return czjsjdm;
	}

	public void setCzjsjdm(String czjsjdm) {
		this.czjsjdm = czjsjdm;
	}

	public String getCzcxqrq() {
		return czcxqrq;
	}

	public void setCzcxqrq(String czcxqrq) {
		this.czcxqrq = czcxqrq;
	}

	public String getCzcxzrq() {
		return czcxzrq;
	}

	public void setCzcxzrq(String czcxzrq) {
		this.czcxzrq = czcxzrq;
	}

	public String getDyjsjdm() {
		return dyjsjdm;
	}

	public void setDyjsjdm(String dyjsjdm) {
		this.dyjsjdm = dyjsjdm;
	}

	public String getDyjylsh() {
		return dyjylsh;
	}

	public void setDyjylsh(String dyjylsh) {
		this.dyjylsh = dyjylsh;
	}

	public String getDysphm() {
		return dysphm;
	}

	public void setDysphm(String dysphm) {
		this.dysphm = dysphm;
	}

	public List getWszList() {
		return wszList;
	}

	public void setWszList(List wszList) {
		this.wszList = wszList;
	}

	public String getJylsh() {
        return jylsh;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getSphm() {
        return sphm;
    }

    public List getBoList() {
        return boList;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public DzwszBO getBo() {
        return bo;
    }

    public String getDy() {
        return dy;
    }

    public void setJylsh(String jylsh) {
        this.jylsh = jylsh;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setSphm(String sphm) {
        this.sphm = sphm;
    }

    public void setBoList(List boList) {
        this.boList = boList;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setBo(DzwszBO bo) {
        this.bo = bo;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }
}
