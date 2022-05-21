package com.xuyuchao.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther xuyuchao
 * @create 2022-03-17-12:16
 */
    public class  JDBCUtils {

        private static DataSource dataSource;
        //创建ThreadLocal,使得能够将一个连接存入ThreadLocal中,保证事务
        private static ThreadLocal<Connection> threadCon = new ThreadLocal<>();
        static {
            try {
                Properties pros = new Properties();
                //这样会空指针异常，我也不知道为什么
                //ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
                InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
                pros.load(is);
                dataSource = DruidDataSourceFactory.createDataSource(pros);
                Connection con = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    /**
     * 获取数据库连接池中的连接
     * 将登录SQL信息封装在Properties配置文件中
     * @return con
     * @throws Exception
     */
    public static Connection getDruidConnection() {
        //取出thread中的con对象
        Connection con = threadCon.get();

        if(con == null) {
            try {
                //获取连接池连接
                con = dataSource.getConnection();
                //将该连接保存到ThreadLocal对象中
                threadCon.set(con);
                //设置为手动管理
                con.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * 提交事务并释放连接
     */
    public static void commitAndClose() {
        //在ThreadLocal中获取存入的唯一连接
        Connection con = threadCon.get();
        if(con != null) {
            //说明之前使用过该连接操作数据库
            try {
                con.commit();//提交事务
                con.close();//释放连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //一定要执行remove,否则就会出错(因为Tomcat服务器底层使用线程池技术)
        threadCon.remove();
    }

    /**
     * 回滚事务并释放连接
     */
    public static void rollBackAndClose() {
        Connection con = threadCon.get();
        if(con != null) {
            //说明之前使用过该连接操作数据库
            try {
                con.rollback();//回滚事务
                con.close();//释放连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //一定要执行remove,否则就会出错(因为Tomcat服务器底层使用线程池技术)
        threadCon.remove();
    }
    /**
     * 释放资源
     * @param con
     * @param ps
     */
//    public static void closeResource(Connection con, PreparedStatement ps) {
//        if(con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(ps != null) {
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void closeResource(Connection con, PreparedStatement ps, ResultSet rs) {
//        if(con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(ps != null) {
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    }
