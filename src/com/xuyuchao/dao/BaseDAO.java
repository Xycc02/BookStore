package com.xuyuchao.dao;

import com.xuyuchao.pojo.User;
import com.xuyuchao.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

/**
 * 抽象类，不能实例化，子类继承拥有其所有方法
 * @auther xuyuchao
 * @create 2022-03-22-16:20
 */
public abstract class BaseDAO {
    //使用dbutils操作数据库
    private QueryRunner runner = new QueryRunner();

    /**
     * update()用来执行Insert、Update、Delete语句
     * @return 影响的行数
     */
    public int update(String sql,Object ...args) {
        Connection con = null;
        try {
            con = JDBCUtils.getDruidConnection();
            return runner.update(con,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一条或一个javabean
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args  sql对应的参数值
     * @param <T>   返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args) {
        T t = null;
        Connection con = null;
        try {
            con = JDBCUtils.getDruidConnection();
            t = runner.query(con, sql, new BeanHandler<T>(type), args);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多个javabean并以List的形式返回
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args) {
        Connection con = null;
        List<T> list = null;
        try {
            con = JDBCUtils.getDruidConnection();
            list = runner.query(con, sql, new BeanListHandler<T>(type), args);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的SQL语句
     * @param sql 执行的sql
     * @param args sql对应的参数
     * @return
     */
    public Object queryForSingleValue(String sql,Object ...args) {
        Connection con = null;
        try {
            con = JDBCUtils.getDruidConnection();
            Object query = runner.query(con, sql, new ScalarHandler(), args);
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
