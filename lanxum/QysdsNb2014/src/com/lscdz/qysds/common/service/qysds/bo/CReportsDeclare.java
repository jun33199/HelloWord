package com.lscdz.qysds.common.service.qysds.bo;

/**
 * @author ������
 *
 */
public abstract class CReportsDeclare {

    /**
     * ��ȡһ���������������
     *
     * @return �������������
     */
    public abstract String getContents();

    /**
     * У�����
     * @param rangeFlag У�鷶Χ����
     * @return boolean У��ͨ����־ true-ͨ����false-δͨ��
     */
    public abstract boolean checkValid(int rangeFlag);


}
