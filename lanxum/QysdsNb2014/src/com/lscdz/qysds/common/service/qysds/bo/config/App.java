package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * �Զ��屨��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�App   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:07:15   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:07:15   
 * �޸ı�ע��   
 * @version  1.0
 */
public class App extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903501833989811198L;

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
	 * Ӧ������
	 */
	private String appName;

	/**
	 * ��ע1
	 */
	private String remark1;

	/**
	 * ��ע2
	 */
	private String remark2;

	/**
	 * @param rangeFlag У�鷶Χ����  
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 * @todo Implement this
	 *       com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
	 */
	public boolean checkValid(int rangeFlag) {
		return false;
	}

	/**
	 * �������������
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
