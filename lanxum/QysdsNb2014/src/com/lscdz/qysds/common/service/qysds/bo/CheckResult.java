package com.lscdz.qysds.common.service.qysds.bo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * �������ֵ����
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�CheckResult   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:13:05   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:13:05   
 * �޸ı�ע��   
 * @version  1.0
 */
public class CheckResult extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6756014767597064087L;

	/**
	 * �������true-ͨ����false-δͨ��
	 */
	private boolean result;

	/**
	 * �Ƿ�����ǿ��ͨ����־��true-����ǿ��ͨ����false-����ǿ��ͨ��
	 */
	private boolean flagSubmit;

	/**
	 * �������
	 */
	private String resultDescription;

	/**
	 * �������
	 */
	private String checkBy;

	/**
	 * У�����
	 * @param rangeFlag У�鷶Χ����   
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 */
	public boolean checkValid(int rangeFlag) {
		return true;
	}

	/**
	 * ��ȡһ���������ֵ�������������
	 * @return ���������������
	 */
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		sb.append("[result:");
		sb.append(result);
		sb.append("|flagSubmit:");
		sb.append(flagSubmit);
		sb.append("|resultDescription:");
		sb.append(resultDescription);
		sb.append("|checkBy:");
		sb.append(checkBy);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return the checkBy
	 */
	public String getCheckBy() {
		return checkBy;
	}

	/**
	 * @param checkBy
	 *            the checkBy to set
	 */
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	/**
	 * @return the flagSubmit
	 */
	public boolean isFlagSubmit() {
		return flagSubmit;
	}

	/**
	 * @param flagSubmit
	 *            the flagSubmit to set
	 */
	public void setFlagSubmit(boolean flagSubmit) {
		this.flagSubmit = flagSubmit;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the resultDescription
	 */
	public String getResultDescription() {
		return resultDescription;
	}

	/**
	 * @param resultDescription
	 *            the resultDescription to set
	 */
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

}
