package com.lscdz.qysds.common.service.qysds.persistent.access;

import com.lscdz.qysds.common.service.qysds.db.DBAccess;

/**
 * ���ݹ�����
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�AppAccess   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����10:44:22   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����10:44:22   
 * �޸ı�ע��   
 * @version  1.0
 */
public abstract class AppAccess {

    public DBAccess idba;

    public AppAccess(DBAccess idba) {
        this.idba = idba;
    }

}
