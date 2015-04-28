package com.lscdz.qysds.common.service.qysds.persistent.access;

import com.lscdz.qysds.common.service.qysds.db.DBAccess;

/**
 * 数据管理器
 * 项目名称：QysdsNb2014   
 * 类名称：AppAccess   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 上午10:44:22   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 上午10:44:22   
 * 修改备注：   
 * @version  1.0
 */
public abstract class AppAccess {

    public DBAccess idba;

    public AppAccess(DBAccess idba) {
        this.idba = idba;
    }

}
