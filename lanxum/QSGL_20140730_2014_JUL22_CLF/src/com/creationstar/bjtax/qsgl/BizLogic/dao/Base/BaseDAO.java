package com.creationstar.bjtax.qsgl.BizLogic.dao.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����DAO,�ṩһ��ֱ�����и���sql���Ľӿ�
 */
public class BaseDAO {
    protected Connection conn = null;

    /**
     * ֱ�����и���sql���Ľӿ�
     * @param sql ������sql���
     * @param conn ���ݿ�����
     */
    public static void execute(String sql, Connection conn) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            stmt.close();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


}
