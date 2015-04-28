/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.DBUtil;


/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ���ݿ�����࣬�л�ȡ���ر����ӵȲ���</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class QSDBUtil extends DBUtil {
    /**
     * ��ȡ���ݿ����ӡ�
     *
     * @return Connection the Connection.
     * @throws BaseException �����׳����쳣��
     */
    public static Connection getConnection() throws BaseException {
        Properties prop = QsglPropertiesUtil.getProperties(QsglPropertiesUtil.
                getPROPERTIES_FILE_NAME());
        String datasource_jndi_name = prop.getProperty(Constants.JNDI_DS_Name);
        Connection conn = null;
        if (datasource_jndi_name == null) {
            throw new SystemException(
                    "SystemException.datasource_jndi_name.notfound");
        }
        try {
            conn = DBUtil.getConnection(datasource_jndi_name);
        } catch (Exception e) {
            String errmsg = "��ȡ���ݿ�����ʱ��������datasource_jndi_name=" +
                            datasource_jndi_name + "����";
            Debug.out(errmsg);
            throw ExceptionUtil.getBaseException(e, errmsg);
        }
        return conn;
    }

    /**
     * ִ��SQL��䡣
     *
     * @param sql the SQL.
     * @return int  either the row count for INSERT,
     *         UPDATE or DELETE statements,
     *         or 0 for SQL statements that return nothing.
     * @throws BaseException �����׳����쳣��
     */
    public static int executeUpdate(String sql) throws BaseException {
        Connection conn = null;

        try {
            conn = getConnection();

            Statement stmt = conn.createStatement();

            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        } finally {
            freeConnection(conn);
        }
    }

    /**
     * ִ��һ��SQL��䡣
     *
     * @param statements sql�������
     * @return an array of update counts containing one element for
     *         each command in the batch. The elements of the array
     *         are ordered according to the order in which commands
     *         were added to the batch.
     * @throws BaseException �����׳����쳣��
     */
    public static int[] executeBatch(String[] statements) throws BaseException {
        Connection conn = null;

        try {
            conn = getConnection();

            Statement stmt = conn.createStatement();

            for (int i = 0; i < statements.length; i++) {
                String sql = (String) statements[i];
                stmt.addBatch(sql);
            }

            return stmt.executeBatch();
        } catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        } finally {
            freeConnection(conn);
        }
    }
}
