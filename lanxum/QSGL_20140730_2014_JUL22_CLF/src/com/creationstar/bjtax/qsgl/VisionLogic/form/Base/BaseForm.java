package com.creationstar.bjtax.qsgl.VisionLogic.form.Base;

import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.util.DataConvert;
import org.apache.struts.action.ActionForm;


/**
 * Form
 */
public class BaseForm extends ActionForm {
    /**
     * ϵͳ��ǰʱ��
     */
    protected String xtdqsj;

    /**
     * ��ǰ����ִ�еĲ���
     */
    protected String operationType = "Show";

    /**
     * ����ǰ��������˰�˵ļ��������
     */
    protected String jsjdm;

    /**
     * ����ǰ��������˰�˵�����
     */
    protected String nsrmc;

    /**
     * ����ǰ��������˰�˵�����
     * ���һ��ء���ҵ��λ������Ŷӡ����µ�λ������������
     */
    protected String nsrlx;

    /**
     * �����걨���
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
     * ��ȡ��Form���������ݡ��շ���������������ʵ�֡�
     *
     * @return Object
     */
    public Object getData() {
        return null;
    }

    /**
     * �����ݱ��浽Form�ĸ������������ʵ�֡�
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
     * ��ո���
     * �ɸ�����ʵ��
     */
    public void clear() {

    }

    private void jbInit() throws Exception {
    }
}
