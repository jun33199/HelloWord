package com.lscdz.qysds.common.service.qysds.persistent;

import java.sql.Connection;
import com.lscdz.qysds.common.service.qysds.persistent.access.IConfigAccess;

/**
 *  配置获取器初始化工厂
 * 项目名称：QysdsNb2014   
 * 类名称：ConfigAccessFactory   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 上午10:49:30   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 上午10:49:30   
 * 修改备注：   
 * @version  1.0
 */
public class ConfigAccessFactory {

    /**
     * 其他配置管理器
     */
    public static int ACCESS_MODEL_CONFIG_OTHER = 0;

    /**
     * 基础配置管理器
     */
    public static int ACCESS_MODEL_CONFIG_BASE = 1;

    /**
     * 配置管理器句柄
     */
    private IConfigAccess ica = null;

    /**
     * 获取配置管理器实例
     * @param conn 数据库联接
     * @param accessModel 管理器类型参数
     * @return 配置管理器
     */
    public IConfigAccess getAInstance(Connection conn, int accessModel) {
        return ica;
    }

}
