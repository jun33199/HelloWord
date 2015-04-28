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
	 * 项目编号
	 */
	private String itemID;

	/**
	 * 项目值
	 */
	private String itemValue;

	/**
	 * 项目类型
	 */
	private String itemType; // （00表头11域值）

	/**
	 * 校验参数
	 * 
	 * @param rangeFlag
	 *            校验范围参数，0-保存方法参数校验，1-查询方法参数校验，2-删除方法参数校验
	 * @return boolean 校样通过标志 true-通过，false-未通过
	 */
	public boolean checkValid(int rangeFlag) {
		// ///////////////////以下为校验必添项 START////////////////////////////////
		/**
		 * @todo 校验必添项
		 */
		// ///////////////////以上为校验必添项 END////////////////////////////////
		// ///////////////////以下为保存动作校验必添项 START////////////////////////
		if (0 == rangeFlag) {
			/**
			 * @todo 保存动作校验必添项
			 */
		}
		// ///////////////////以上为保存动作校验必添项 END//////////////////////////
		// ///////////////////以下为查询动作校验必添项 START////////////////////////
		if (1 == rangeFlag) {
			/**
			 * @todo 查询动作校验必添项
			 */
		}
		// ///////////////////以上为查询动作校验必添项 END//////////////////////////
		// ///////////////////以下为删除动作校验必添项 START////////////////////////
		if (2 == rangeFlag) {
			/**
			 * @todo 删除动作校验必添项
			 */
		}
		// ///////////////////以上为删除动作校验必添项 END//////////////////////////
		// ///////////////////以下为保存单表动作校验必添项 START////////////////////////
		if (3 == rangeFlag) {
			/**
			 * @todo保存单表动作校验必添项
			 */
		}
		// ///////////////////以上为保存单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为查询单表动作校验必添项 START////////////////////////
		if (4 == rangeFlag) {
			/**
			 * @todo查询单表动作校验必添项
			 */
		}
		// ///////////////////以上为查询单表动作校验必添项 END//////////////////////////
		// ///////////////////以下为删除单表动作校验必添项 START////////////////////////
		if (5 == rangeFlag) {
			/**
			 * @todo删除单表动作校验必添项
			 */
		}
		// ///////////////////以上为删除单表动作校验必添项 END//////////////////////////
		return true;
	}

	/**
	 * 获取一个报表单表项目的内容描述
	 * 
	 * @return 报表单表的内容描述
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
