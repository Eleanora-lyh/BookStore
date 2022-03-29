package com.lyh.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    //ThreadLocal中的数据都是Connection类型,创建一个ThreadLocal对象conns储存连接
    private static ThreadLocal<Connection> conns= new ThreadLocal<Connection>();
    //再类进行加载的时候，获取数据库连接所需的信息，并创建数据库连接池
    static {
        try {
            //获取配置文件中的信息，将其加载到properties的对象中
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            //利用properties中的xml配置信息，创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    获取数据库连接池中的连接
    public static Connection getConnection(){
        Connection conn = conns.get();//获取ThreadLocal中的连接，确保是同一个连接
        if(conn == null){//如果ThreadLocal中还没有连接就创建一个
            try {
                conn=dataSource.getConnection();//创建一个连接，并保存到ThreadLocal中
                conns.set(conn);
                conn.setAutoCommit(false);//设置为手动关闭
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    //提交事务，释放连接
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                conn.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close(); //关闭连接
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//移除ThreadLocal对象，因为Tomcat底层使用了线程池
    }
    //提交事务，回滚连接
    public static void rollBackAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                conn.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close(); //关闭连接
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();//移除ThreadLocal对象，因为Tomcat底层使用了线程池
    }
//    关闭连接，放回数据库连接池
/*    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
