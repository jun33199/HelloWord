package com.creationstar.bjtax.qsgl.VisionLogic.form.Base;

import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.util.DataConvert;
import org.apache.struts.action.ActionForm;


/**
 * Form
 */
public class BaseForm extends ActionForm {
    /**
     * 系统当前时间
     */
    protected String xtdqsj;

    /**
     * 当前正在执行的操作
     */
    protected String operationType = "Show";

    /**
     * 作当前操作的纳税人的计算机代码
     */
    protected String jsjdm;

    /**
     * 作当前操作的纳税人的名称
     */
    protected String nsrmc;

    /**
     * 作当前操作的纳税人的类型
     * 国家机关、事业单位、社会团队、军事单位、其他、个人
     */
    protected String nsrlx;

    /**
     * 关联申报表号
     */
    protected String sbbh;

    /**
     * Access method for the action property.
     *
     * @return   the current value of the action property
     */
    public String getOperationType() {
        return operationType;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    /**
     * Sets the value of the action property.
     *
     * @param aAction the new value of the action property
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setXtdqsj(String xtdqsj) {
        this.xtdqsj = xtdqsj;
    }

    /**
     * 获取该Form包含的数据。空方法，具体由子类实现。
     *
     * @return Object
     */
    public Object getData() {
        return null;
    }

    /**
     * 将数据保存到Form的各域。由子类具体实现。
     *
     * @param Object
     */
    public void setData(Object Object) {

    }

    public String getXtdqsj() {
        xtdqsj = DataConvert.TimeStamp2String(new Timestamp(System.
                currentTimeMillis()));
        return xtdqsj;
    }


    /**
     * 清空各域
     * 由各子类实现
     */
    public void clear() {

    }

    private void jbInit() throws Exception {
    }
}
