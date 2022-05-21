package com.xuyuchao.service;

import com.xuyuchao.pojo.User;

/**
 * @auther xuyuchao
 * @create 2022-03-23-14:43
 */
public interface UserService {
    /**
     *注册
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，否则表示不存在
     */
    public boolean existUsername(String username);

 }