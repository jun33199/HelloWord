package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * 自定义报表
 * 项目名称：QysdsNb2014   
 * 类名称：App   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:07:15   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:07:15   
 * 修改备注：   
 * @version  1.0
 */
public class App extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903501833989811198L;

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
	 * 应用名称
	 */
	private String appName;

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
	 * @todo Implement this
	 *       com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
	 */
	public boolean checkValid(int rangeFlag) {
		return false;
	}

	/**
	 * 报表的内容描述
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

	public String getAppName() {
		return appName;
	}

	public String getRemark1() {
		return remark1;
	}

	public String getRemark2() {
		return remark2;
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

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
