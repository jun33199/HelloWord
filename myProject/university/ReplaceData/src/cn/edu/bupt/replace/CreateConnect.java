package cn.edu.bupt.replace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.edu.bupt.conf.Configurations;



public class CreateConnect {
    private static CreateConnect  connect=null;
    private static Connection connectInstance=null;
    public static CreateConnect getInstance(){
        if (connect == null) {
            // 加锁，并发
            synchronized (CreateConnect.class) {
                if (connect == null)
                    connect = new CreateConnect();
            }
        }
        
        return connect;
    }
    public CreateConnect(){
        Configurations cfg = Configurations.getInstance ();
        try {
            Class.forName(cfg.getDb_driver());
//加载MYSQL JDBC驱动程序    
        //Class.forName("org.gjt.mm.mysql.Driver"); 
       System.out.println("Success loading Mysql Driver!"); 

       connectInstance = DriverManager.getConnection( 
            cfg.getDb_url(),cfg.getDb_user(),cfg.getDb_password()); 
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码 
   
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
    public static  synchronized Connection getConnect(){   
        return connectInstance;
    }
}
