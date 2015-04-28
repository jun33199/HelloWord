package com.lscdz.qysds.common.service.qysds.persistent;

import java.sql.Connection;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.IAppAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.QysdsAppAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.QysdsJBAppAccess;

/**
 * @author 哈正则 配置获取器初始化工厂
 */
public class AppAccessFactory {

    /**
     * 其他应用管理器
     */
    public static int ACCESS_MODEL_APP_OTHER = 0;

    /**
     * 企业所得税应用管理器
     */
    public static int ACCESS_MODEL_APP_QYSDS = 1;
    
    /**
     * 企业清算所得税应用管理器
     */
    public static int ACCESS_MODEL_APP_QYQSSDS = 2;
    /**
     * 企业所得税季报应用管理器
     */
    public static int ACCESS_MODEL_APP_QYSDSJB = 3;
    /**
     * 获取应用管理器实例
     *
     * @param conn 数据库联接
     * @param accessModel 管理器类型参数
     * @return 配置管理器
     */
    public static IAppAccess getAInstance(Connection conn,int accessModel) throws FrameException {
        DBAccess dba = new DBAccess(conn);
        IAppAccess iaa1 = null;
        if (ACCESS_MODEL_APP_QYSDS == accessModel) {
            iaa1 = new QysdsAppAccess(dba);
        }else if(ACCESS_MODEL_APP_QYSDSJB== accessModel){
        	iaa1 = new QysdsJBAppAccess(dba);
        }else {
            throw new FrameException("无法找到对应的应用管理器数据!");
        }
        return iaa1;
    }
}
