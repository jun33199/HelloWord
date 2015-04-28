package com.creationstar.bjtax.qsgl.BizLogic.dao.Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 基础DAO,提供一个直接运行更新sql语句的接口
 */
public class BaseDAO {
    protected Connection conn = null;

    /**
     * 直接运行更新sql语句的接口
     * @param sql 完整的sql语句
     * @param conn 数据库连接
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
