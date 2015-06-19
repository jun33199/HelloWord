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
 * ���ݲ�������
 * @author zhangj
 */
public class DBAccess {

    /**
     * �������ݻ�������
     */
    private CachedRowSet crs = null;

    /**
     * ���ݿ�����
     */
    private Connection conn = null;

    /**
     * ���ݿ�
     */
    private Statement stat = null;

    /**
     * ���ݿ�����
     */
    private ResultSet rs = null;
    MyLogger myLogger=new MyLogger(DBAccess.class);
    /**
     * ��ʼ������
     * @param conn  ���ݿ�����    
     * @throws FrameException �����쳣         
     */
    public DBAccess(Connection conn) throws FrameException {
        if (conn == null) {
            throw new FrameException("���ݿ�����Ϊ��");
        }
        this.conn = conn;
    }

    /**
     * ��ѯִ�з���
     * @param sql ��ѯ���
     * @return ���������
     * @throws FrameException �����쳣         
     */
    public CachedRowSet executeQuery(String sql) throws FrameException {
        try {
            crs = new CachedRowSetImpl();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            crs.populate(rs);
            rs.close();
            stat.close();
            // ������ʼ
            crs.beforeFirst();
            long count = 0;
            while (crs.next()) {
                count++;
            }
            crs.beforeFirst();
            myLogger.log("���ݿ��ѯ!" + count + "����¼...");
            // ��������
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("��ѯ��ҵ����˰���ݳ���");
        } 
        return crs;
    }

    /**
     * ɾ�������ִ�з���
     * @param sql ��ѯ��� 
     * @return ���
     * @throws FrameException �����쳣        
     */
    public int executeUpdate(String sql) throws FrameException {
        int result = -1;
        try {
            stat = conn.createStatement();
            result = stat.executeUpdate(sql);
            stat.close();
            myLogger.log("���ݿ����!" + result + "����¼...");
            // ��������
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("���³���");
        } finally {

        }
        return result;
    }

    /**
     * ������ִ�з���
     * @param psql ��ѯ���         
     * @param pars ��ά����Ĳ���        
     * @return ���
     * @throws FrameException �����쳣            
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
            myLogger.log("���ݿ���������!" + result.length + "�β���...");
            // ��������
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameException("����ִ�г���");
        } finally {

        }
        return result;
    }

    public Connection getConn() {
        return conn;
    }
}
