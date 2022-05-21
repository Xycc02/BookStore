package com.xuyuchao.dao;

import com.xuyuchao.pojo.User;

/**
 * UserDao接口
 * @auther xuyuchao
 * @create 2022-03-17-20:21
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

}
