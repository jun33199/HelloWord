package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;


public class QysdsReportsItemDeclare extends CReportsDeclare implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2662124206865277533L;

	/**
	 * ��Ŀ���
	 */
	private String itemID;

	/**
	 * ��Ŀֵ
	 */
	private String itemValue;

	/**
	 * ��Ŀ����
	 */
	private String itemType; // ��00��ͷ11��ֵ��

	/**
	 * У�����
	 * 
	 * @param rangeFlag
	 *            У�鷶Χ������0-���淽������У�飬1-��ѯ��������У�飬2-ɾ����������У��
	 * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
	 */
	public boolean checkValid(int rangeFlag) {
		// ///////////////////����ΪУ������� START////////////////////////////////
		/**
		 * @todo У�������
		 */
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
		return true;
	}

	/**
	 * ��ȡһ����������Ŀ����������
	 * 
	 * @return ���������������
	 */
	public String getContents() {
		StringBuffer sb = new StringBuffer();
		sb.append("[itemID:");
		sb.append(itemID);
		sb.append("|");
		sb.append("itemValue:");
		sb.append(itemValue);
		sb.append("|");
		sb.append("itemType:");
		sb.append(itemType);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * @return the itemValue
	 */
	public String getItemValue() {
		return itemValue;
	}

	/**
	 * @param itemValue
	 *            the itemValue to set
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	private void readObject(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

}
