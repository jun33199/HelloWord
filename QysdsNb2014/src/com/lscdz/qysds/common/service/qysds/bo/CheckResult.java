package com.lscdz.qysds.common.service.qysds.bo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * 检查类结果值对象
 * 项目名称：QysdsNb2014   
 * 类名称：CheckResult   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午6:13:05   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午6:13:05   
 * 修改备注：   
 * @version  1.0
 */
public class CheckResult extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6756014767597064087L;

	/**
	 * 检查结果，true-通过，false-未通过
	 */
	private boolean result;

	/**
	 * 是否允许强制通过标志，true-允许强制通过，false-允许强制通过
	 */
	private boolean flagSubmit;

	/**
	 * 结果描述
	 */
	private String resultDescription;

	/**
	 * 检查依据
	 */
	private String checkBy;

	/**
	 * 校验参数
	 * @param rangeFlag 校验范围参数   
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 */
	public boolean checkValid(int rangeFlag) {
		return true;
	}

	/**
	 * 获取一个检查类结果值对象的内容描述
	 * @return 报表单表的内容描述
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
