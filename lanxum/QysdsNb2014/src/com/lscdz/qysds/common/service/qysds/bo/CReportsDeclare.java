package com.lscdz.qysds.common.service.qysds.bo;

/**
 * @author 哈正则
 *
 */
public abstract class CReportsDeclare {

    /**
     * 获取一个报表的内容描述
     *
     * @return 报表的内容描述
     */
    public abstract String getContents();

    /**
     * 校验参数
     * @param rangeFlag 校验范围参数
     * @return boolean 校样通过标志 true-通过，false-未通过
     */
    public abstract boolean checkValid(int rangeFlag);


}
