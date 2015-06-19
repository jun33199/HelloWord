package com.lscdz.qysds.common.service.qysds.persistent;

import java.sql.Connection;
import com.lscdz.qysds.common.service.qysds.persistent.access.IConfigAccess;

/**
 *  ���û�ȡ����ʼ������
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ConfigAccessFactory   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����10:49:30   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����10:49:30   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ConfigAccessFactory {

    /**
     * �������ù�����
     */
    public static int ACCESS_MODEL_CONFIG_OTHER = 0;

    /**
     * �������ù�����
     */
    public static int ACCESS_MODEL_CONFIG_BASE = 1;

    /**
     * ���ù��������
     */
    private IConfigAccess ica = null;

    /**
     * ��ȡ���ù�����ʵ��
     * @param conn ���ݿ�����
     * @param accessModel ���������Ͳ���
     * @return ���ù�����
     */
    public IConfigAccess getAInstance(Connection conn, int accessModel) {
        return ica;
    }

}
