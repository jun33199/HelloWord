package com.lscdz.qysds.common.service.qysds.persistent;

import java.sql.Connection;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.db.DBAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.IAppAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.QysdsAppAccess;
import com.lscdz.qysds.common.service.qysds.persistent.access.QysdsJBAppAccess;

/**
 * @author ������ ���û�ȡ����ʼ������
 */
public class AppAccessFactory {

    /**
     * ����Ӧ�ù�����
     */
    public static int ACCESS_MODEL_APP_OTHER = 0;

    /**
     * ��ҵ����˰Ӧ�ù�����
     */
    public static int ACCESS_MODEL_APP_QYSDS = 1;
    
    /**
     * ��ҵ��������˰Ӧ�ù�����
     */
    public static int ACCESS_MODEL_APP_QYQSSDS = 2;
    /**
     * ��ҵ����˰����Ӧ�ù�����
     */
    public static int ACCESS_MODEL_APP_QYSDSJB = 3;
    /**
     * ��ȡӦ�ù�����ʵ��
     *
     * @param conn ���ݿ�����
     * @param accessModel ���������Ͳ���
     * @return ���ù�����
     */
    public static IAppAccess getAInstance(Connection conn,int accessModel) throws FrameException {
        DBAccess dba = new DBAccess(conn);
        IAppAccess iaa1 = null;
        if (ACCESS_MODEL_APP_QYSDS == accessModel) {
            iaa1 = new QysdsAppAccess(dba);
        }else if(ACCESS_MODEL_APP_QYSDSJB== accessModel){
        	iaa1 = new QysdsJBAppAccess(dba);
        }else {
            throw new FrameException("�޷��ҵ���Ӧ��Ӧ�ù���������!");
        }
        return iaa1;
    }
}
