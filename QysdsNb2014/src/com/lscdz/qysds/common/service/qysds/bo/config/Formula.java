package com.lscdz.qysds.common.service.qysds.bo.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;
import com.lscdz.qysds.common.util.StringUtil;

/**
 * 
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�Formula   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:08:04   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:08:04   
 * �޸ı�ע��   
 * @version  1.0
 */
public class Formula extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1606952489556803347L;

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
	 * ��ʽ����
	 */
	private String formulacode;

	/**
	 * ��ʽ����
	 */
	private String formulacontent;

	/**
	 * ��ʽ����
	 */
	private String formulatype;

	/**
	 * ��ʽ����
	 */
	private String formulalevel;

	/**
	 * ��ע1
	 */
	private String remark1;

	/**
	 * ��ע2
	 */
	private String remark2;

	/**
	 * У�����
	 * 
	 * @param rangeFlag
	 *            У�鷶Χ����
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 */
	public boolean checkValid(int rangeFlag) {
		if (StringUtil.killNull2(version) == null) {
			return false;
		} else if (StringUtil.killNull2(appid) == null) {
			return false;
		} else if (StringUtil.killNull2(formulacode) == null) {
			return false;
		} else if (StringUtil.killNull2(formulacontent) == null) {
			return false;
		} else if (StringUtil.killNull2(formulatype) == null) {
			return false;
		} else if (StringUtil.killNull2(formulalevel) == null) {
			return false;
		}
		return true;
	}

	/**
	 * ��ȡһ����ʽ����������
	 * 
	 * @return ��ʽ����������
	 */
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		sb.append("[version:");
		sb.append(version);
		sb.append("|appcode:");
		sb.append(appid);
		sb.append("|formulacode:");
		sb.append(formulacode);
		sb.append("|formulacontent:");
		sb.append(formulacontent);
		sb.append("|formulatype:");
		sb.append(formulatype);
		sb.append("|formulalevel:");
		sb.append(formulalevel);
		sb.append("|remark1:");
		sb.append(remark1);
		sb.append("|remark2:");
		sb.append(remark1);
		sb.append("]");
		return sb.toString();
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

	public void setFormulacode(String formulacode) {
		this.formulacode = formulacode;
	}

	public void setFormulacontent(String formulacontent) {
		this.formulacontent = formulacontent;
	}

	public void setFormulalevel(String formulalevel) {
		this.formulalevel = formulalevel;
	}

	public void setFormulatype(String formulatype) {
		this.formulatype = formulatype;
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

	public String getActivity() {
		return activity;
	}

	public String getAppid() {
		return appid;
	}

	public String getFormulacode() {
		return formulacode;
	}

	public String getFormulacontent() {
		return formulacontent;
	}

	public String getFormulalevel() {
		return formulalevel;
	}

	public String getFormulatype() {
		return formulatype;
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
}
