package com.xuyuchao.test;

import com.xuyuchao.dao.impl.UserDaoImpl;
import com.xuyuchao.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-03-23-14:22
 */
public class UserDaoImplTest {

    UserDaoImpl userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
        System.out.println(userDao.queryUserByUsernameAndPassword("admin1","admin1"));
    }

    @Test
    public void saveUser() {
        User user = new User(1,"xxx","123456","2672424338@qq.com");
        int isSave = userDao.saveUser(user);
        if(isSave >= 1) {
            System.out.println("注册成功!");
        }else{
            System.out.println("注册失败!");
        }
    }
}