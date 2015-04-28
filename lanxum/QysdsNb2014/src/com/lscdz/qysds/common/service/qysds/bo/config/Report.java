package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * 自定义报表
 * 项目名称：QysdsNb2014   
 * 类名称：Report   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:08:33   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:08:33   
 * 修改备注：   
 * @version  1.0
 */
public class Report extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3568963791248635094L;

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
	 * 报表名称
	 */
	private String reportname;

	/**
	 * 报表类型
	 */
	private String reporttype;

	/**
	 * 备注1
	 */
	private String remark1;

	/**
	 * 备注2
	 */
	private String remark2;

	/**
	 * @param rangeFlag 校验范围参数 
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 */
	public boolean checkValid(int rangeFlag) {
		return false;
	}

	/**
	 * @return 报表的内容描述
	 */
	public String getContents() {
		return "";
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	public String getActivity() {
		return activity;
	}

	public String getAppid() {
		return appid;
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

	public String getReportname() {
		return reportname;
	}

	public String getReporttype() {
		return reporttype;
	}

	public String getVersion() {
		return version;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setAppid(String appid) {
		this.appid = appid;
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

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
