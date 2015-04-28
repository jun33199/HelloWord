package com.ttsoft.bjtax.shenbao.util;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.connection.Manager;
import com.ttsoft.framework.util.ResourceLocator;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;

/**
 * 用于获取 ORManager 和 数据库连接 的辅助类
 */
public  class DBResource
{
    public DBResource()
    {
    }

    public final static String DB_SHENBAO = "jdbc/Shenbao";

    public final static String OR_SHENBAO = "ORMgr";

    /**
     * 得到申报的ORManager
     * @return 申报的ORManager的实例
     * @throws BaseException
     */
    public static ORManager getORManager() throws BaseException
    {
        return getORManager(OR_SHENBAO);
    }

    /**
     * 获取一个ORManager  对象
     * @param ManagerName ORManager Name
     * @return ORManager
     * @throws BaseException
     */
    public static ORManager getORManager(String ManagerName) throws BaseException
    {
        try
        {
            ORManager orManager = ResourceLocator.getORManager(ManagerName);
            if (null == orManager)
            {
                throw new SystemException("IDeclaration cannot get the SB ORManager!");
            }
            return orManager;
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 得到申报的连接
     * @return 申报datasource的连接
     * @throws BaseException
     */
    public static Connection getConnection() throws BaseException
    {
        //单元测试时使用
        if(null != System.getProperty("isTest"))
        {
            return getTestConnection();
        }
        return getConnection(DB_SHENBAO);
    }

    //单元测试时使用的连接池
    private static Connection getTestConnection() throws BaseException
    {
        String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
        String jdbc_uri = "jdbc:oracle:thin:@192.29.1.2:1521:bjldb";
        String jdbc_username = "swzg";
        String jdbc_password = "swzg001223";
        try
        {
            Class.forName(jdbc_driver);
            Connection conn = java.sql.DriverManager.getConnection(
                jdbc_uri,jdbc_username,jdbc_password);
            return conn;
        }
        catch (Exception e)
        {
            String errmsg = "获取数据库连接(driver="+jdbc_driver+
                   ",uri="+jdbc_uri+",username="+jdbc_username+
                   ",password="+jdbc_password+")时发生错误！"+e.getMessage();
            throw ExceptionUtil.getBaseException(e,errmsg);
        }
    }

    /**
     * 获取一个java.sql.Connection 对象
     * @methodName getConnection()
     * @param DSName JNDI name of DataSource
     * @return java.sql.Connection
     * @throws BaseException
     */
    public static Connection getConnection(String DSName) throws BaseException
    {
        try
        {
            Connection conn = null;
            conn = ResourceLocator.getConnection(DSName);

            if (null == conn)
            {
                throw(new Exception("IDeclaration cannot get the SB DB connection!"));
            }
//            com.ttsoft.common.util.Debug.out("IDeclaration SB DB Connection count:" + ++connCount);
            return conn;
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 关闭某个数据库连接
     * @param conn 数据库连接
     * @throws Exception
     */
    public static void destroyConnection(Connection conn)
    {
        try
        {
            if(conn != null)
            {
                conn.close();
                conn = null;
//                com.ttsoft.common.util.Debug.out(
//                    "IDeclaration SB DB Connection count:" + --connCount);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

//    private static int connCount = 0;
}
