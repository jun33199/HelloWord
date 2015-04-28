package com.lscdz.qysds.common.service.qysds.bo.qysds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lscdz.qysds.common.service.qysds.bo.CReportsDeclare;


public class QysdsReportsTableDeclare extends CReportsDeclare implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6556266870971392924L;

	/**
     * ����
     */
    private String tableId;

    /**
     * �������
     */
    private String tableName;

    /**
     * �������
     */
    private String tbblx;

    /**
     * ����д�����,keyΪitem��ID,��ԱΪcom.lscdz.qysds.application.qysdsnb2014.bo.qysds.QysdsReportsItemDeclare
     */
    @SuppressWarnings("rawtypes")
	private Map CellContentList = new HashMap();

    /**
     * У�����
     * @param rangeFlag У�鷶Χ������0-���淽������У�飬1-��ѯ��������У�飬2-ɾ����������У��
     * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
     */
    @SuppressWarnings("rawtypes")
	public boolean checkValid(int rangeFlag) {
        boolean flag = true;
        /////////////////////����ΪУ������� START////////////////////////////////
        /**
         * @todo У�������
         */
        /////////////////////����ΪУ������� END////////////////////////////////
        /////////////////////����Ϊ���涯��У������� START////////////////////////
        if (0 == rangeFlag) {
            /**
             * @todo ���涯��У�������
             */
        }
        /////////////////////����Ϊ���涯��У������� END//////////////////////////
        /////////////////////����Ϊ��ѯ����У������� START////////////////////////
        if (1 == rangeFlag) {
            /**
             * @todo ��ѯ����У�������
             */
        }
        /////////////////////����Ϊ��ѯ����У������� END//////////////////////////
        /////////////////////����Ϊɾ������У������� START////////////////////////
        if (2 == rangeFlag) {
            /**
             * @todo ɾ������У�������
             */
        }
        /////////////////////����Ϊɾ������У������� END//////////////////////////
        /////////////////////����Ϊ���浥����У������� START////////////////////////
        if (3 == rangeFlag) {
            /**
             * @todo ���浥����У�������
             */
        }
        /////////////////////����Ϊ���浥����У������� END//////////////////////////
        /////////////////////����Ϊ��ѯ������У������� START////////////////////////
        if (4 == rangeFlag) {
            /**
             * @todo ��ѯ������У�������
             */
        }
        /////////////////////����Ϊ��ѯ������У������� END//////////////////////////
        /////////////////////����Ϊɾ��������У������� START////////////////////////
        if (5 == rangeFlag) {
            /**
             * @todo ɾ��������У�������
             */
        }
        /////////////////////����Ϊɾ��������У������� END//////////////////////////
        Iterator iterator = CellContentList.keySet().iterator();
        QysdsReportsItemDeclare qid;
        while (iterator.hasNext()) {
            qid = (QysdsReportsItemDeclare) (CellContentList.get(iterator.next()));
            flag = flag && qid.checkValid(rangeFlag);
        }
        return flag;
    }

    /**
     * ��ȡһ�����������������
     *
     * @return ���������������
     */
    @SuppressWarnings("rawtypes")
	public String getContents() {
        StringBuffer sb = new StringBuffer();
        sb.append("[tableId:");
        sb.append(tableId);
        sb.append("|");
        sb.append("tableName:");
        sb.append(tableName);
        sb.append("|");
        sb.append("tbblx:");
        sb.append(tbblx);
        sb.append("|{");
        Iterator iterator = CellContentList.keySet().iterator();
        QysdsReportsItemDeclare qid;
        while (iterator.hasNext()) {
            qid = (QysdsReportsItemDeclare) (CellContentList.get(iterator.next()));
            sb.append(qid.getContents());
        }
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return the cellContentList
     */
    @SuppressWarnings("rawtypes")
	public Map getCellContentList() {
        return CellContentList;
    }

    /**
     * @param cellContentList
     *            the cellContentList to set
     */
    @SuppressWarnings("rawtypes")
	public void setCellContentList(Map cellContentList) {
        CellContentList = cellContentList;
    }

    /**
     * @return the tableId
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     *            the tableId to set
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     *            the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the tbblx
     */
    public String getTbblx() {
        return tbblx;
    }

    /**
     * @param tbblx
     *            the tbblx to set
     */
    public void setTbblx(String tbblx) {
        this.tbblx = tbblx;
    }

    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

}
