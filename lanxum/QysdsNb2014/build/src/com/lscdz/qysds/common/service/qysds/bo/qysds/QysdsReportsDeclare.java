package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;
import com.lscdz.qysds.common.util.StringUtil;

/**
 * ��ҵ����˰������������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�QysdsReportsDeclare   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����6:11:11   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����6:11:11   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QysdsReportsDeclare extends CReportsDeclare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7247138197942969139L;

	/**
	 * ��˰�˻�����Ϣ
	 */
	private Jbxx jbxx;

	/**
	 * Ӧ�ñ��
	 */
	private String appid;

	/**
	 * �汾��
	 */
	private String version;

	/**
	 * ��˰�˼��������
	 */
	private String nsrjsjdm;

	/**
	 * ��˰������
	 */
	private String nsrmc;

	/**
	 * ����������
	 */
	private String bbqlx;

	/**
	 * �ں�
	 */
	private String qh;

	/**
	 * ���ͨ����ʶ
	 */
	private boolean CheckFlag = false;

	/**
	 * ˰��������ʼ����
	 */
	private Timestamp skssksrq;

	/**
	 * ˰��������������
	 */
	private Timestamp skssjsrq;

	/**
	 * �걨����
	 */
	private Timestamp sbrq;

	/**
	 * ˰�����
	 */
	private String sknd;

	/**
	 * ˰����������
	 */
	private String swjsjdm;

	/**
	 * ˰�������֯��������
	 */
	private String swjgzzjgdm;

	/**
	 * ���ش���
	 */
	private String qxdm;

	/**
	 * ¼����
	 */
	private String lrr;

	/**
	 * ¼������
	 */
	private Timestamp lrrq;

	/**
	 * ������
	 */
	private String cjr;

	/**
	 * ��������
	 */
	private Timestamp cjrq;

	/**
	 * �������,keyΪ������,��ԱΪcom.lscdz.qysds.application.qysdsnb2014.bo.qysds.
	 * QysdsReportsTableDeclare
	 */
	@SuppressWarnings("rawtypes")
	private Map tableContentList = new HashMap();

	/**
	 * У�����
	 * 
	 * @param rangeFlag
	 *            У�鷶Χ������0-���淽������У�飬1-��ѯ��������У�飬2-ɾ����������У��
	 *            ��3-���浥��������У�飬4-��ѯ����������У�飬5-ɾ������������У��
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkValid(int rangeFlag) {
		boolean flag = true;
		// ///////////////////����ΪУ������� START////////////////////////////////
		if (StringUtil.killNull2(this.appid) == null) {
			return false;
		} else if (StringUtil.killNull2(this.nsrjsjdm) == null) {
			return false;
		} else if (StringUtil.killNull2(this.sknd) == null) {
			return false;
		} else if (StringUtil.killNull2(this.bbqlx) == null) {
			return false;
		} else if (StringUtil.killNull2(this.qh) == null) {
			return false;
		}
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
			 * @todo���浥����У�������
			 */
		}
		// ///////////////////����Ϊ���浥����У������� END//////////////////////////
		// ///////////////////����Ϊ��ѯ������У������� START////////////////////////
		if (4 == rangeFlag) {
			/**
			 * @todo��ѯ������У�������
			 */
		}
		// ///////////////////����Ϊ��ѯ������У������� END//////////////////////////
		// ///////////////////����Ϊɾ��������У������� START////////////////////////
		if (5 == rangeFlag) {
			/**
			 * @todoɾ��������У�������
			 */
		}
		// ///////////////////����Ϊɾ��������У������� END//////////////////////////
		// У���ֽڵ���Ϣ
		Iterator iterator = tableContentList.keySet().iterator();
		QysdsReportsTableDeclare qrtd;
		while (iterator.hasNext()) {
			qrtd = (QysdsReportsTableDeclare) (tableContentList.get(iterator
					.next()));
			flag = flag && qrtd.checkValid(rangeFlag);
		}
		// У�������Ϣ
		if (jbxx != null) {
			flag = flag && this.jbxx.checkValid(rangeFlag);
		} else {
			throw new ArithmeticException("������ϢΪ�գ������������������");
		}
		//
		return flag;
	}

	/**
	 * ��ȡһ���������������
	 * 
	 * @return �������������
	 */
	@SuppressWarnings("rawtypes")
	public String getContents() {

		StringBuffer sb = new StringBuffer();
		sb.append(jbxx.getContents());
		sb.append("|");
		sb.append("appid:");
		sb.append(appid);
		sb.append("|");
		sb.append("version:");
		sb.append(version);
		sb.append("|");
		sb.append("nsrjsjdm:");
		sb.append(nsrjsjdm);
		sb.append("|");
		sb.append("nsrmc:");
		sb.append(nsrmc);
		sb.append("|");
		sb.append("bbqlx:");
		sb.append(bbqlx);
		sb.append("|");
		sb.append("qh:");
		sb.append(qh);
		sb.append("|");
		sb.append("skssksrq:");
		sb.append(skssksrq);
		sb.append("|");
		sb.append("skssjsrq:");
		sb.append(skssjsrq);
		sb.append("|");
		sb.append("sbrq:");
		sb.append(sbrq);
		sb.append("|");
		sb.append("sknd:");
		sb.append(sknd);
		sb.append("|");
		sb.append("swjgzzjgdm:");
		sb.append(swjgzzjgdm);
		sb.append("|");
		sb.append("qxdm:");
		sb.append(qxdm);
		sb.append("|");
		sb.append("lrr:");
		sb.append(lrr);
		sb.append("|");
		sb.append("lrrq:");
		sb.append(lrrq);
		sb.append("|");
		sb.append("cjr:");
		sb.append(cjr);
		sb.append("|");
		sb.append("cjrq:");
		sb.append(cjrq);
		sb.append("|{");
		Iterator iterator = tableContentList.keySet().iterator();
		QysdsReportsTableDeclare qrtd;
		while (iterator.hasNext()) {
			qrtd = (QysdsReportsTableDeclare) (tableContentList.get(iterator
					.next()));
			sb.append(qrtd.getContents());
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid
	 *            the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the bbqlx
	 */
	public String getBbqlx() {
		return bbqlx;
	}

	/**
	 * @param bbqlx
	 *            the bbqlx to set
	 */
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	/**
	 * @return the cjr
	 */
	public String getCjr() {
		return cjr;
	}

	/**
	 * @param cjr
	 *            the cjr to set
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	/**
	 * @return the cjrq
	 */
	public Timestamp getCjrq() {
		return cjrq;
	}

	/**
	 * @param cjrq
	 *            the cjrq to set
	 */
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}

	/**
	 * @param lrr
	 *            the lrr to set
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	/**
	 * @return the lrrq
	 */
	public Timestamp getLrrq() {
		return lrrq;
	}

	/**
	 * @param lrrq
	 *            the lrrq to set
	 */
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	/**
	 * @return the nsrjsjdm
	 */
	public String getNsrjsjdm() {
		return nsrjsjdm;
	}

	/**
	 * @param nsrjsjdm
	 *            the nsrjsjdm to set
	 */
	public void setNsrjsjdm(String nsrjsjdm) {
		this.nsrjsjdm = nsrjsjdm;
	}

	/**
	 * @return the nsrmc
	 */
	public String getNsrmc() {
		return nsrmc;
	}

	/**
	 * @param nsrmc
	 *            the nsrmc to set
	 */
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	/**
	 * @return the qh
	 */
	public String getQh() {
		return qh;
	}

	/**
	 * @param qh
	 *            the qh to set
	 */
	public void setQh(String qh) {
		this.qh = qh;
	}

	/**
	 * @return the qxdm
	 */
	public String getQxdm() {
		return qxdm;
	}

	/**
	 * @param qxdm
	 *            the qxdm to set
	 */
	public void setQxdm(String qxdm) {
		this.qxdm = qxdm;
	}

	/**
	 * @return the sbrq
	 */
	public Timestamp getSbrq() {
		return sbrq;
	}

	/**
	 * @param sbrq
	 *            the sbrq to set
	 */
	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	/**
	 * @return the sknd
	 */
	public String getSknd() {
		return sknd;
	}

	/**
	 * @param sknd
	 *            the sknd to set
	 */
	public void setSknd(String sknd) {
		this.sknd = sknd;
	}

	/**
	 * @return the skssjsrq
	 */
	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	/**
	 * @param skssjsrq
	 *            the skssjsrq to set
	 */
	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	/**
	 * @return the skssksrq
	 */
	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	/**
	 * @param skssksrq
	 *            the skssksrq to set
	 */
	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	/**
	 * @return the swjgzzjgdm
	 */
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	/**
	 * @param swjgzzjgdm
	 *            the swjgzzjgdm to set
	 */
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}

	/**
	 * @return the tableContentList
	 */
	@SuppressWarnings("rawtypes")
	public Map getTableContentList() {
		return tableContentList;
	}

	/**
	 * @param tableContentList
	 *            the tableContentList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setTableContentList(Map tableContentList) {
		this.tableContentList = tableContentList;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the jbxx
	 */
	public Jbxx getJbxx() {
		return jbxx;
	}

	public String getSwjsjdm() {
		return swjsjdm;
	}

	public boolean isCheckFlag() {
		return CheckFlag;
	}

	/**
	 * @param jbxx
	 *            the jbxx to set
	 */
	public void setJbxx(Jbxx jbxx) {
		this.jbxx = jbxx;
	}

	public void setSwjsjdm(String swjsjdm) {
		this.swjsjdm = swjsjdm;
	}

	public void setCheckFlag(boolean CheckFlag) {
		this.CheckFlag = CheckFlag;
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

}
