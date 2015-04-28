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
     * 表编号
     */
    private String tableId;

    /**
     * 填报表名称
     */
    private String tableName;

    /**
     * 填报表类型
     */
    private String tbblx;

    /**
     * 填报表行次内容,key为item的ID,成员为com.lscdz.qysds.application.qysdsnb2014.bo.qysds.QysdsReportsItemDeclare
     */
    @SuppressWarnings("rawtypes")
	private Map CellContentList = new HashMap();

    /**
     * 校验参数
     * @param rangeFlag 校验范围参数，0-保存方法参数校验，1-查询方法参数校验，2-删除方法参数校验
     * @return boolean 校样通过标志 true-通过，false-未通过
     */
    @SuppressWarnings("rawtypes")
	public boolean checkValid(int rangeFlag) {
        boolean flag = true;
        /////////////////////以下为校验必添项 START////////////////////////////////
        /**
         * @todo 校验必添项
         */
        /////////////////////以上为校验必添项 END////////////////////////////////
        /////////////////////以下为保存动作校验必添项 START////////////////////////
        if (0 == rangeFlag) {
            /**
             * @todo 保存动作校验必添项
             */
        }
        /////////////////////以上为保存动作校验必添项 END//////////////////////////
        /////////////////////以下为查询动作校验必添项 START////////////////////////
        if (1 == rangeFlag) {
            /**
             * @todo 查询动作校验必添项
             */
        }
        /////////////////////以上为查询动作校验必添项 END//////////////////////////
        /////////////////////以下为删除动作校验必添项 START////////////////////////
        if (2 == rangeFlag) {
            /**
             * @todo 删除动作校验必添项
             */
        }
        /////////////////////以上为删除动作校验必添项 END//////////////////////////
        /////////////////////以下为保存单表动作校验必添项 START////////////////////////
        if (3 == rangeFlag) {
            /**
             * @todo 保存单表动作校验必添项
             */
        }
        /////////////////////以上为保存单表动作校验必添项 END//////////////////////////
        /////////////////////以下为查询单表动作校验必添项 START////////////////////////
        if (4 == rangeFlag) {
            /**
             * @todo 查询单表动作校验必添项
             */
        }
        /////////////////////以上为查询单表动作校验必添项 END//////////////////////////
        /////////////////////以下为删除单表动作校验必添项 START////////////////////////
        if (5 == rangeFlag) {
            /**
             * @todo 删除单表动作校验必添项
             */
        }
        /////////////////////以上为删除单表动作校验必添项 END//////////////////////////
        Iterator iterator = CellContentList.keySet().iterator();
        QysdsReportsItemDeclare qid;
        while (iterator.hasNext()) {
            qid = (QysdsReportsItemDeclare) (CellContentList.get(iterator.next()));
            flag = flag && qid.checkValid(rangeFlag);
        }
        return flag;
    }

    /**
     * 获取一个报表单表的内容描述
     *
     * @return 报表单表的内容描述
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
