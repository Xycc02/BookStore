package com.xuyuchao.dao.impl;

import com.xuyuchao.dao.BaseDAO;
import com.xuyuchao.dao.UserDao;
import com.xuyuchao.pojo.User;

/**
 * @auther xuyuchao
 * @create 2022-03-17-20:30
 */
public class UserDaoImpl extends BaseDAO implements UserDao {

    /**
     * 查询数据库中有没有username这个人
     * @param username
     * @return 有，返回user对象   没有，返回null
     */
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        User user = queryForOne(User.class, sql, username);
        return user;
    }

    /**
     * 查询数据库中有没有username、password都对应的这个人
     * @param username
     * @param password
     * @return  有，返回user 没有，返回空
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        User user = queryForOne(User.class, sql, username, password);
        return user;
    }

    /**
     * 保存user到数据库
     * @param user
     * @return
     */
    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        int update = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update;
    }
}
