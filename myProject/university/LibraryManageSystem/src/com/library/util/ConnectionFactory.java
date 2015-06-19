package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
    private static ConnectionFactory  connect=null;
    private static Connection connectInstance=null;
    /**
     * 创建工厂实例
     * @return
     */
    public static ConnectionFactory getInstance()throws Exception{
        if (connect == null) {          
            synchronized (ConnectionFactory.class) {
                if (connect == null)
                    connect = new ConnectionFactory();
            }
        }
        
        return connect;
    }
    /**
     * 构造方法
     */
    public ConnectionFactory() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");

       System.out.println("Success loading Mysql Driver!"); 

       connectInstance = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_work","zhangj","zhangj123");
  
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }  
    }
    /**
     * 获取数据库连接实例
     * @return
     */
    public static  synchronized Connection getConnect(){   
        return connectInstance;
    }
}
