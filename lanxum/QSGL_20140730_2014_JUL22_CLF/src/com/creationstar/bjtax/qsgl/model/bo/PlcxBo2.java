package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
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
 *
 * 说明：该Bo是针对页面中"批量受理(税务人员)"模块
 */
public class PlcxBo2 implements Serializable {
    //数据提供者信息
    private String sjtgz;

    //批量号
    private String pch;

    //导入时间段
    private String drsjBegin;
    private String drsjEnd;

    //提交时间段
    private String tjsjBegin;
    private String tjsjEnd;

    //导入时间
    private String drsj;

    //提交时间
    private String tjsj;

    //导入笔数
    private String drbs;

    //提交笔数
    private String tjbs;

    //受理通过笔数
    private String sltgbs;

    //复核通过的笔数
    private String fstgbs;

    //审核通过笔数
    private String shtgbs;

    //审核时间
    private String shsj;

    //复核通过的申报的应纳税额
    private String sumYnse;

    public String getDrbs() {
        return drbs;
    }

    public String getDrsj() {
        return drsj;
    }

    public String getFstgbs() {
        return fstgbs;
    }

    public String getShsj() {
        return shsj;
    }

    public String getPch() {
        return pch;
    }

    public String getShtgbs() {
        return shtgbs;
    }

    public String getSjtgz() {
        return sjtgz;
    }

    public String getSltgbs() {
        return sltgbs;
    }

    public String getSumYnse() {
        return sumYnse;
    }

    public String getTjbs() {
        return tjbs;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setDrbs(String drbs) {
        this.drbs = drbs;
    }

    public void setDrsj(String drsj) {
        this.drsj = drsj;
    }

    public void setFstgbs(String fstgbs) {
        this.fstgbs = fstgbs;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setShtgbs(String shtgbs) {
        this.shtgbs = shtgbs;
    }

    public void setSjtgz(String sjtgz) {
        this.sjtgz = sjtgz;
    }

    public void setSltgbs(String sltgbs) {
        this.sltgbs = sltgbs;
    }

    public void setSumYnse(String sumYnse) {
        this.sumYnse = sumYnse;
    }

    public void setTjbs(String tjbs) {
        this.tjbs = tjbs;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }

    public String getTjsjBegin() {
        return tjsjBegin;
    }

    public void setTjsjBegin(String tjsjBegin) {
        this.tjsjBegin = tjsjBegin;
    }

    public String getTjsjEnd() {
        return tjsjEnd;
    }

    public void setTjsjEnd(String tjsjEnd) {
        this.tjsjEnd = tjsjEnd;
    }

}
