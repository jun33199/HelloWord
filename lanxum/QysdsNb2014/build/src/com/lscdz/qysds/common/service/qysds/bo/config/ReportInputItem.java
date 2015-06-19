package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

/**
 * �Զ��屨��
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ReportInputItem   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:09:39   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:09:39   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ReportInputItem extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8146044080262662936L;

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
	 * ����¼��������
	 */
	private String riiname;

	/**
	 * ����¼��������
	 */
	private String riitype;

	/**
	 * ����¼�����
	 */
	private String riilength;

	/**
	 * ����¼����Ĭ�ϳ�ʼ������
	 */
	private String defaultrow;

	/**
	 * ����¼����������
	 */
	private String riidescriptor;

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
	 * @param rangeFlag
	 *            У�鷶Χ����
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 * @todo Implement this
	 *       com.lscdz.qysds.application.qysdsnb2014.bo.CReportsDeclare method
	 */
	public boolean checkValid(int rangeFlag) {
		return false;
	}

	/**
	 * 
	 * @return �������������
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
