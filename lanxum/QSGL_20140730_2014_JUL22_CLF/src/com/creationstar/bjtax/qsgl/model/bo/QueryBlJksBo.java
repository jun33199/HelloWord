package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;

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
 */
public class QueryBlJksBo implements Serializable {

    /**
     * 查询条件，计税金额
     */
    private String jsje;

    /**
     * 查询条件，实缴税额
     */
    private String sjse;

    /**
     * 申报缴款主表的vo
     */
    private Sbjkzb sbjkzb;

    /**
     * 申报缴款明细的vo
     */
    private Sbjkmx sbjkmx;

    /**
     * 缴款书的明细
     */
    private ArrayList mxList;

    /**
     * 税款类型代码
     */
    private String sklxdm;

    /**
     * 申报表号，申报缴款书核对金额的时候要用到
     */
    private String sbbh;

    /**
     * 缴款凭证号
     */
    private String jkpzh;

    /**
     * 缴款书的类型，1代表普通，2代表调帐的
     */
    private String type;

    /**
     * 数据来源，撤销时候用，通过这个可以知道是汇总的还是申报的缴款书，1代表申报的，2代表汇总的
     */
    private String sjly;

    /**
     * 主表序号，调帐的缴款书要用到
     */
    private String zbxh;

    /**
     * 帐务标识
     */
    private String zwbs;

    /**
     * 存放申报表号的ArrayList，汇总生成的缴款书核对金额的时候要用到，存放的都是String类型的sbbh
     */
    private ArrayList sbbhList;

    public ArrayList getMxList() {
        return mxList;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public String getSbbh() {
        return sbbh;
    }

    public ArrayList getSbbhList() {
        return sbbhList;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getType() {
        return type;
    }

    public String getSjly() {
        return sjly;
    }

    public String getZbxh() {
        return zbxh;
    }

    public String getJsje() {
        return jsje;
    }

    public String getSjse() {
        return sjse;
    }

    public String getZwbs() {
        return zwbs;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbbhList(ArrayList sbbhList) {
        this.sbbhList = sbbhList;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setZwbs(String zwbs) {
        this.zwbs = zwbs;
    }

}
