package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.util.Constants;

/**
 * <p>Title: </p>
 *
 * <p>Description:核定通知书业务对象，主要用于核定通知书的生成，打印 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class HdtzsBo implements Serializable {
    /**
     * 申报表号
     */
    private String sbbh;

    /**
     * 核定通知书号_修该
     */
    private String hdtzsh_xg;
    /**
     * 房屋类型 不征的房屋使用该类型名称作为减税依据
     */
    private String fwlxmc;

    /**
     * 房屋类型 不征的房屋使用该类型名称作为减税依据
     */
    private String fwlxdm;

    /**
     * 审批结果的减免税额
     */
    private BigDecimal spjmse;

    /**
     * 核定通知书值对象
     */
    private Hdtzs voHdtzs;

    /**
     * 房屋基本信息
     */
    private Tufwxx voTufwxx;

    /**
     * 减免内容映射
     */
    private HashMap jmnrMap;


    public HdtzsBo() {
    }

    public boolean isBz() {
        return this.voHdtzs.bzbs.equals(Constants.BZBS_BZ);
    }

    public HashMap getJmnrMap() {
        return jmnrMap;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Hdtzs getVoHdtzs() {
        return voHdtzs;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public String getFwlxdm() {
        return fwlxdm;
    }

    public BigDecimal getSpjmse() {
        return spjmse;
    }

    public void setVoHdtzs(Hdtzs voHdtzs) {
        this.voHdtzs = voHdtzs;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setJmnrMap(HashMap jmnrMap) {
        this.jmnrMap = jmnrMap;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public void setFwlxdm(String fwlxdm) {
        this.fwlxdm = fwlxdm;
    }

    public void setSpjmse(BigDecimal spjmse) {
        this.spjmse = spjmse;
    }

    /**
     * @return voTufwxx
     */
    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    /**
     * @param voTufwxx 要设置的 voTufwxx
     */
    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public String getHdtzsh_xg() {
        return hdtzsh_xg;
    }

    public void setHdtzsh_xg(String hdtzsh_xg) {
        this.hdtzsh_xg = hdtzsh_xg;
    }

}
