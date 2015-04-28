/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 数据库操作类，有获取、关闭连接等操作</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class QSDBUtil extends DBUtil {
    /**
     * 获取数据库连接。
     *
     * @return Connection the Connection.
     * @throws BaseException 可能抛出的异常。
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
            String errmsg = "获取数据库连接时发生错误（datasource_jndi_name=" +
                            datasource_jndi_name + "）！";
            Debug.out(errmsg);
            throw ExceptionUtil.getBaseException(e, errmsg);
        }
        return conn;
    }

    /**
     * 执行SQL语句。
     *
     * @param sql the SQL.
     * @return int  either the row count for INSERT,
     *         UPDATE or DELETE statements,
     *         or 0 for SQL statements that return nothing.
     * @throws BaseException 可能抛出的异常。
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
     * 执行一批SQL语句。
     *
     * @param statements sql语句数组
     * @return an array of update counts containing one element for
     *         each command in the batch. The elements of the array
     *         are ordered according to the order in which commands
     *         were added to the batch.
     * @throws BaseException 可能抛出的异常。
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
