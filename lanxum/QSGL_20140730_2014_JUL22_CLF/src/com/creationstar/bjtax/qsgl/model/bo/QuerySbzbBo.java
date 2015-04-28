package com.creationstar.bjtax.qsgl.model.bo;


import java.io.Serializable;
import java.sql.Timestamp;

public class QuerySbzbBo implements Serializable {


    /**
     * 是否个人
     */
    private boolean person;
    /**
     * 是否录入房土信息
     */
    private boolean existFtxx;
    /**
     * 是否录入拆迁信息
     */
    private boolean existCqxx;
    /**
     * 是否录入公有住房信息
     */
    private boolean existGyzf;
    /**
     * 是否录入房屋交换信息
     */
    private boolean existFwjh;
    /**
     * 申报表号
     */
    private String sbbh;
    /**
     * 申报日期
     */
    private java.sql.Timestamp sbrq;
    /**
     * 申报状态
     */

    private String ztbs;


    public String getIsPersonName() {
        if (person) {
            return "个人";
        } else {
            return "非个人";
        }
    }

    public String getBooleanName(boolean flag) {
        if (flag) {
            return "是";
        } else {
            return "否";
        }
    }

    public boolean isExistCqxx() {
        return existCqxx;
    }

    public boolean isExistFtxx() {
        return existFtxx;
    }

    public boolean isExistFwjh() {
        return existFwjh;
    }

    public boolean isExistGyzf() {
        return existGyzf;
    }

    public boolean isPerson() {
        return person;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Timestamp getSbrq() {
        return sbrq;
    }

    public String getZtbs() {
        return ztbs;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setPerson(boolean isPerson) {
        this.person = isPerson;
    }

    public void setExistGyzf(boolean existGyzf) {
        this.existGyzf = existGyzf;
    }

    public void setExistFwjh(boolean existFwjh) {
        this.existFwjh = existFwjh;
    }

    public void setExistFtxx(boolean existFtxx) {
        this.existFtxx = existFtxx;
    }

    public void setExistCqxx(boolean existCqxx) {
        this.existCqxx = existCqxx;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }
}
