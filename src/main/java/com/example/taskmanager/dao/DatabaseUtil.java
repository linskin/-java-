package com.example.taskmanager.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *数据库连接工具类
 */
public class DatabaseUtil {
    private static Properties dbProperties = new Properties();
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    //静态代码块，在类加载时执行一次，加载配置并注册驱动
    static {
        try(InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            if (input == null){
                System.err.println("错误，找不到db.properties文件");
                // 严重错误，可能需要退出
                System.exit(1);
            }
            dbProperties.load(input);

            url = dbProperties.getProperty("db.url");
            username = dbProperties.getProperty("db.username");
            password = dbProperties.getProperty("db.password");
            driver = dbProperties.getProperty("db.driver");

            // 注册JDBC驱动
            Class.forName(driver);

        }catch (IOException | ClassNotFoundException e){
            System.err.println("数据库配置加载或驱动配置失败：" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    /**
     * 获取数据库连接
     * @return Connection 对象
     * @throws SQLException 如果连接失败
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 关闭数据库连接 (以及 Statement, ResultSet - 虽然推荐使用 try-with-resources)
     * @param connection 要关闭的连接 (可以为 null)
     */
//    public static void close(Connection connection){
//        if (connection != null){
//            try{
//                connection.close();
//            }catch (SQLException e){
//                System.err.println("关闭数据库连接时出错：" + e.getMessage());
//            }
//        }
//    }
    //close(AutoCloseable... closeables) 方法的设计是为了方便关闭多个资源（如数据库连接、Statement、ResultSet 等），而无需多次调用 close 方法。
    public static void close(AutoCloseable...closeables){//语法糖“...”，表示可变参数
        for (AutoCloseable closeable : closeables){
            if (closeable != null){
                try{
                    closeable.close();
                }catch (Exception e){
                    // 一般在关闭时忽略异常，或记录日志
                    System.err.println("关闭资源时出错：" + e.getMessage());
                }
            }
        }
    }
}
