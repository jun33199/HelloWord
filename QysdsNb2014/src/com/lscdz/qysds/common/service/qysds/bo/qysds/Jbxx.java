package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;

public class Jbxx extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7487743428169578159L;

	/**
	 * ���������
	 */
	public String jsjdm;

	/**
	 * ��˰������
	 */
	public String nsrmc;

	/**
	 * ������������
	 */
	public String ssjjlx;

	/**
	 * ��ϵ�绰
	 */
	public String lxdh;

	/**
	 * ��Ӫ��ַ
	 */
	public String jydz;

	/**
	 * ������ҵ
	 */
	public String sshy;

	/*
	 * ���շ�ʽ
	 */
	public String zsfs;

	/**
	 * �ƻ��ƶ�
	 */
	public String ckzd;

	/**
	 * ���ʹ�����ʽ
	 */
	public String gzglxs;

	/**
	 * ��������
	 */
	public String jmlx;

	/**
	 * ϵͳ����
	 */
	public String xtjb;

	/**
	 * ����������
	 */
	public String bbmsf;

	public String sybs;
	/**
	 * У�����
	 * 
	 * @param rangeFlag
	 *            У�鷶Χ������0-���淽������У�飬1-��ѯ��������У�飬2-ɾ����������У��
	 *            ��3-���浥��������У�飬1-��ѯ����������У�飬2-ɾ������������У��
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 */
	public boolean checkValid(int rangeFlag) {
		boolean flag = true;
		// ///////////////////����ΪУ������� START////////////////////////////////

		// ///////////////////����ΪУ������� END////////////////////////////////
		// ///////////////////����Ϊ���涯��У������� START////////////////////////
		if (0 == rangeFlag) {
			/**
			 * @todo ���涯��У�������
			 */
		}
		// ///////////////////����Ϊ���涯��У������� END//////////////////////////
		// ///////////////////����Ϊ��ѯ����У������� START////////////////////////
		if (1 == rangeFlag) {
			/**
			 * @todo ��ѯ����У�������
			 */
		}
		// ///////////////////����Ϊ��ѯ����У������� END//////////////////////////
		// ///////////////////����Ϊɾ������У������� START////////////////////////
		if (2 == rangeFlag) {
			/**
			 * @todo ɾ������У�������
			 */
		}
		// ///////////////////����Ϊɾ������У������� END//////////////////////////
		// ///////////////////����Ϊ���浥����У������� START////////////////////////
		if (3 == rangeFlag) {
			/**
			 * @todo ���浥����У�������
			 */
		}
		// ///////////////////����Ϊ���浥����У������� END//////////////////////////
		// ///////////////////����Ϊ��ѯ������У������� START////////////////////////
		if (4 == rangeFlag) {
			/**
			 * @todo ��ѯ������У�������
			 */
		}
		// ///////////////////����Ϊ��ѯ������У������� END//////////////////////////
		// ///////////////////����Ϊɾ��������У������� START////////////////////////
		if (5 == rangeFlag) {
			/**
			 * @todo ɾ��������У�������
			 */
		}
		// ///////////////////����Ϊɾ��������У������� END//////////////////////////

		return flag;
	}

	/**
	 * ��ȡһ�����������Ϣ����������
	 * 
	 * @return ���������Ϣ����������
	 */
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		sb.append("[jsjdm:");
		sb.append(jsjdm);
		sb.append("|");
		sb.append("nsrmc:");
		sb.append(nsrmc);
		sb.append("|");
		sb.append("ssjjlx:");
		sb.append(ssjjlx);
		sb.append("|");
		sb.append("lxdh:");
		sb.append(lxdh);
		sb.append("|");
		sb.append("sshy:");
		sb.append(sshy);
		sb.append("|");
		sb.append("zsfs:");
		sb.append(zsfs);
		sb.append("|");
		sb.append("ckzd:");
		sb.append(ckzd);
		sb.append("|");
		sb.append("gzglxs:");
		sb.append(gzglxs);
		sb.append("|");
		sb.append("jmlx:");
		sb.append(jmlx);
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

	public String getCkzd() {
		return ckzd;
	}

	public String getGzglxs() {
		return gzglxs;
	}

	public String getJmlx() {
		return jmlx;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public String getSshy() {
		return sshy;
	}

	public String getSsjjlx() {
		return ssjjlx;
	}

	public String getZsfs() {
		return zsfs;
	}

	public String getBbmsf() {
		return bbmsf;
	}

	public String getJydz() {
		return jydz;
	}

	public String getXtjb() {
		return xtjb;
	}

	public void setCkzd(String ckzd) {
		this.ckzd = ckzd;
	}

	public void setGzglxs(String gzglxs) {
		this.gzglxs = gzglxs;
	}

	public void setJmlx(String jmlx) {
		this.jmlx = jmlx;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}

	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}

	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}
}
