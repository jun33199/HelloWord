package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * 自定义报表
 * 项目名称：QysdsNb2014   
 * 类名称：ReportInputItem   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:09:39   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:09:39   
 * 修改备注：   
 * @version  1.0
 */
public class ReportInputItem extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8146044080262662936L;

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
	 * 报表录入项名称
	 */
	private String riiname;

	/**
	 * 报表录入项类型
	 */
	private String riitype;

	/**
	 * 报表录入项长度
	 */
	private String riilength;

	/**
	 * 报表录入项默认初始化行数
	 */
	private String defaultrow;

	/**
	 * 报表录入项描述符
	 */
	private String riidescriptor;

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
	 * @param rangeFlag
	 *            校验范围参数
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 * @todo Implement this
	 *       com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
	 */
	public boolean checkValid(int rangeFlag) {
		return false;
	}

	/**
	 * 
	 * @return 报表的内容描述
	 * @todo Implement this
	 *       com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
	 */
	public String getContents() {
		return "";
	}

	@SuppressWarnings("unused")
	private void jbInit() throws Exception {
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setDefaultrow(String defaultrow) {
		this.defaultrow = defaultrow;
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

	public void setRiidescriptor(String riidescriptor) {
		this.riidescriptor = riidescriptor;
	}

	public void setRiilength(String riilength) {
		this.riilength = riilength;
	}

	public void setRiiname(String riiname) {
		this.riiname = riiname;
	}

	public void setRiitype(String riitype) {
		this.riitype = riitype;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getActivity() {
		return activity;
	}

	public String getAppid() {
		return appid;
	}

	public String getDefaultrow() {
		return defaultrow;
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

	public String getRiidescriptor() {
		return riidescriptor;
	}

	public String getRiilength() {
		return riilength;
	}

	public String getRiiname() {
		return riiname;
	}

	public String getRiitype() {
		return riitype;
	}

	public String getVersion() {
		return version;
	}
}
