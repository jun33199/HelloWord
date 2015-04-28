package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * 自定义报表
 * 项目名称：QysdsNb2014   
 * 类名称：ReportStyleItem   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:10:06   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:10:06   
 * 修改备注：   
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
     * 版本号
     */
    private String version;

    /**
     * 活动状态
     */
    private String activity;

    /**
     * 应用编号
     */
    private String appid;

    /**
     * 报表编码
     */
    private String reportcode;

    /**
     * 报表录入项编码
     */
    private String riicode;

    /**
     * 报表样式项编码
     */
    private String rsicode;

    /**
     * 报表样式项名称
     */
    private String rsiname;

    /**
     * 报表样式项类型
     */
    private String rsitype;

    /**
     * 报表样式项长度
     */
    private String rsilength;

    /**
     * 报表样式项起始横坐标
     */
    private String xsc;

    /**
     * 报表样式项起始纵坐标
     */
    private String ysc;

    /**
     * 报表样式项跨列
     */
    private String colspan;

    /**
     * 报表样式项跨行
     */
    private String rowspan;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;

    /**
     *
     * @param rangeFlag 校验范围参数
     * @return boolean 校样通过标志 true-通过，false-未通过
     * @todo Implement this com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
     */
    public boolean checkValid(int rangeFlag) {
        return false;
    }

    /**
     *
     * @return 报表的内容描述
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
