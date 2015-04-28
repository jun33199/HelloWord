package com.lscdz.qysds.common.service.qysds.check;

import java.sql.Connection;

import com.lscdz.qysds.common.service.qysds.check.qysds.QysdsChecker;
import com.lscdz.qysds.common.service.qysds.db.DBAccess;

import yangjian.frame.util.FrameException;

/**
 * <p>Title: �Զ��屨��-��˹��߹�����</p>
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
     * ����Ӧ����˹�����
     */
    public static int ACCESS_MODEL_APP_OTHER = 0;

    /**
     * ��ҵ����˰��˹�����
     */
    public static int ACCESS_MODEL_APP_QYSDS = 1;
    
    /**
     * ��ȡӦ�ù�����ʵ��
     *
     * @param conn ���ݿ�����
     * @param accessModel ���������Ͳ���
     * @return ���ù�����
     */
    public static Checker getAInstance(Connection conn,int accessModel) throws
            FrameException {
        DBAccess dba = new DBAccess(conn);
        Checker checker = null;
        if (ACCESS_MODEL_APP_QYSDS == accessModel) {
            checker = new QysdsChecker(dba);
        }else {
            throw new FrameException("�޷��ҵ���Ӧ��Ӧ�ù���������!");
        }
        return checker;
    }

}
