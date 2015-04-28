package com.lscdz.qysds.common.service.qysds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;

import yangjian.frame.util.FrameException;

import com.lscdz.util.MyLogger;
import com.sun.rowset.CachedRowSetImpl;


/**
 * 数据操作容器
 * @author zhangj
 */
public class DBAccess {

    /**
     * 基础数据缓冲容器
     */
    private CachedRowSet crs = null;

    /**
     * 数据库联接
     */
    private Connection conn = null;

    /**
     * 数据库
     */
    private Statement stat = null;

    /**
     * 数据库结果集
     */
    private ResultSet rs = null;
    MyLogger myLogger=new MyLogger(DBAccess.class);
    /**
     * 初始化方法
     * @param conn  数据库联接    
     * @throws FrameException 基础异常         
     */
    public DBAccess(Connection conn) throws FrameException {
        if (conn == null) {
            throw new FrameException("数据库联接为空");
        }
        this.conn = conn;
    }

    /**
     * 查询执行方法
     * @param sql 查询语句
     * @return 结果集缓存
     * @throws FrameException 基础异常         
     */
    public CachedRowSet executeQuery(String sql) throws FrameException {
        try {
            crs = new CachedRowSetImpl();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            crs.populate(rs);
            rs.close();
            stat.close();
            // 计数开始
            crs.beforeFirst();
            long count = 0;
            while (crs.next()) {
                count++;
            }
            crs.beforeFirst();
            myLogger.log("数据库查询!" + count + "条记录...");
            // 计数结束
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("查询企业所得税数据出错");
        } 
        return crs;
    }

    /**
     * 删除或更新执行方法
     * @param sql 查询语句 
     * @return 结果
     * @throws FrameException 基础异常        
     */
    public int executeUpdate(String sql) throws FrameException {
        int result = -1;
        try {
            stat = conn.createStatement();
            result = stat.executeUpdate(sql);
            stat.close();
            myLogger.log("数据库操作!" + result + "条记录...");
            // 计数结束
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("更新出错");
        } finally {

        }
        return result;
    }

    /**
     * 批处理执行方法
     * @param psql 查询语句         
     * @param pars 二维数组的参数        
     * @return 结果
     * @throws FrameException 基础异常            
     */
    public int[] executeBatch(String psql, String[][] pars) throws FrameException {
        int[] result = new int[pars.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        try {
            PreparedStatement pstat = conn.prepareStatement(psql);
            for (int i = 0; i < pars.length; i++) {
                for (int j = 0; j < pars[i].length; j++) {
                    pstat.setString(j, pars[i][j]);
                }
                pstat.addBatch();
            }
            result = pstat.executeBatch();
            pstat.close();
            myLogger.log("数据库批量操作!" + result.length + "次操作...");
            // 计数结束
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("批量执行出错");
        } finally {

        }
        return result;
    }

    public Connection getConn() {
        return conn;
    }
}
