package com.lscdz.qysds.common.service.qysds.check;

import java.sql.Connection;

import com.lscdz.qysds.common.service.qysds.check.qysds.QysdsChecker;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;

import yangjian.frame.util.FrameException;

/**
 * <p>Title: 自定义报表-审核工具工厂类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class CheckerFactory {

    /**
     * 其他应用审核管理器
     */
    public static int ACCESS_MODEL_APP_OTHER = 0;

    /**
     * 企业所得税审核管理器
     */
    public static int ACCESS_MODEL_APP_QYSDS = 1;
    
    /**
     * 获取应用管理器实例
     *
     * @param conn 数据库联接
     * @param accessModel 管理器类型参数
     * @return 配置管理器
     */
    public static Checker getAInstance(Connection conn,int accessModel) throws
            FrameException {
        DBAccess dba = new DBAccess(conn);
        Checker checker = null;
        if (ACCESS_MODEL_APP_QYSDS == accessModel) {
            checker = new QysdsChecker(dba);
        }else {
            throw new FrameException("无法找到对应的应用管理器数据!");
        }
        return checker;
    }

}
