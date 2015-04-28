/*
 * Created on 2006-2-21
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SjqyForm extends BaseForm {

    /**
     * 缴款方式代码
     */
    private String jsfsdm = "01";

    private String ztbs = "7";

    private String blbs = "2";

    private String cc = "1000";
//迁移第几步
    private String qybs = "1";

    /**
     * @return Returns the qybs.
     */
    public String getQybs() {
        return qybs;
    }

    /**
     * @param qybs The qybs to set.
     */
    public void setQybs(String qybs) {
        this.qybs = qybs;
    }

    private List ret = new ArrayList();

    /**
     * @return Returns the ret.
     */
    public List getRet() {
        return ret;
    }

    /**
     * @param ret The ret to set.
     */
    public void setRet(List ret) {
        this.ret = ret;
    }

    /**
     * @return Returns the jsfsdm.
     */
    public String getJsfsdm() {
        return jsfsdm;
    }

    /**
     * @param jsfsdm The jsfsdm to set.
     */
    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    /**
     * @return Returns the jsfsList.
     */
    public ArrayList getJsfsList() {
        return jsfsList;
    }

    /**
     * @param jsfsList The jsfsList to set.
     */
    public void setJsfsList(ArrayList jsfsList) {
        this.jsfsList = jsfsList;
    }

    /**
     * 缴款方式
     */
    public ArrayList jsfsList = new ArrayList();

    /**
     * 区县代码
     */
    private String qxdm;

    /**
     * 信息种类
     */
    private String xxzl;

    /**
     * 对应的是显示的迁移批次信息
     */
    private ArrayList pcList = new ArrayList();

    /**
     *
     */
    public SjqyForm() {
        super();
        //
    }

    /**
     * @return Returns the pcList.
     */
    public ArrayList getPcList() {
        return pcList;
    }

    /**
     * @param pcList The pcList to set.
     */
    public void setPcList(ArrayList pcList) {
        this.pcList = pcList;
    }

    /**
     * @return Returns the qxdm.
     */
    public String getQxdm() {
        return qxdm;
    }

    /**
     * @param qxdm The qxdm to set.
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    /**
     * @return Returns the xxzl.
     */
    public String getXxzl() {
        return xxzl;
    }

    /**
     * @param xxzl The xxzl to set.
     */
    public void setXxzl(String xxzl) {
        this.xxzl = xxzl;
    }

    /**
     * @return Returns the blbs.
     */
    public String getBlbs() {
        return blbs;
    }

    /**
     * @param blbs The blbs to set.
     */
    public void setBlbs(String blbs) {
        this.blbs = blbs;
    }

    /**
     * @return Returns the ztbs.
     */
    public String getZtbs() {
        return ztbs;
    }

    /**
     * @param ztbs The ztbs to set.
     */
    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    /**
     * @return Returns the cc.
     */
    public String getCc() {
        if (cc == null || cc.equals("")) {
            cc = "1000";
        }
        return cc;
    }

    /**
     * @param cc The cc to set.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }
}
