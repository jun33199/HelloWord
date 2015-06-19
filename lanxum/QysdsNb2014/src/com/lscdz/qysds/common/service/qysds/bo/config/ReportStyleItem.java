package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * �Զ��屨��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ReportStyleItem   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:10:06   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:10:06   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ReportStyleItem extends CReportsDeclare implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5282839569439983273L;

	public ReportStyleItem() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * �汾��
     */
    private String version;

    /**
     * �״̬
     */
    private String activity;

    /**
     * Ӧ�ñ��
     */
    private String appid;

    /**
     * �������
     */
    private String reportcode;

    /**
     * ����¼�������
     */
    private String riicode;

    /**
     * ������ʽ�����
     */
    private String rsicode;

    /**
     * ������ʽ������
     */
    private String rsiname;

    /**
     * ������ʽ������
     */
    private String rsitype;

    /**
     * ������ʽ���
     */
    private String rsilength;

    /**
     * ������ʽ����ʼ������
     */
    private String xsc;

    /**
     * ������ʽ����ʼ������
     */
    private String ysc;

    /**
     * ������ʽ�����
     */
    private String colspan;

    /**
     * ������ʽ�����
     */
    private String rowspan;

    /**
     * ��ע1
     */
    private String remark1;

    /**
     * ��ע2
     */
    private String remark2;

    /**
     *
     * @param rangeFlag У�鷶Χ����
     * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
     * @todo Implement this com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
     */
    public boolean checkValid(int rangeFlag) {
        return false;
    }

    /**
     *
     * @return �������������
     * @todo Implement this com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
     */
    public String getContents() {
        return "";
    }

    private void jbInit() throws Exception {
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public void setReportcode(String reportcode) {
        this.reportcode = reportcode;
    }

    public void setRiicode(String riicode) {
        this.riicode = riicode;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public void setRsicode(String rsicode) {
        this.rsicode = rsicode;
    }

    public void setRsilength(String rsilength) {
        this.rsilength = rsilength;
    }

    public void setRsiname(String rsiname) {
        this.rsiname = rsiname;
    }

    public void setRsitype(String rsitype) {
        this.rsitype = rsitype;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setXsc(String xsc) {
        this.xsc = xsc;
    }

    public void setYsc(String ysc) {
        this.ysc = ysc;
    }

    public String getActivity() {
        return activity;
    }

    public String getAppid() {
        return appid;
    }

    public String getColspan() {
        return colspan;
    }

    public String getRemark1() {
        return remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public String getReportcode() {
        return reportcode;
    }

    public String getRiicode() {
        return riicode;
    }

    public String getRowspan() {
        return rowspan;
    }

    public String getRsicode() {
        return rsicode;
    }

    public String getRsilength() {
        return rsilength;
    }

    public String getRsiname() {
        return rsiname;
    }

    public String getRsitype() {
        return rsitype;
    }

    public String getVersion() {
        return version;
    }

    public String getXsc() {
        return xsc;
    }

    public String getYsc() {
        return ysc;
    }

}
