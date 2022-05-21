package com.xuyuchao.service.impl;

import com.xuyuchao.dao.UserDao;
import com.xuyuchao.dao.impl.UserDaoImpl;
import com.xuyuchao.pojo.User;
import com.xuyuchao.service.UserService;

/**
 * @auther xuyuchao
 * @create 2022-03-23-22:42
 */
public class UserServiceImpl implements UserService {

    private UserDao userdao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userdao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User user1 = userdao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return user1;
    }

    @Override
    public boolean existUsername(String username) {
        User user = userdao.queryUserByUsername(username);
        if(user != null) {
            //存在则返回true
            return true;
        }
        //不存在返回false
        return false;
    }
}
