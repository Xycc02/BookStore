package com.xuyuchao.test;

import com.xuyuchao.pojo.User;
import com.xuyuchao.service.UserService;
import com.xuyuchao.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-03-23-22:49
 */
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(1,"xu","123456","123456@126.com"));
    }

    @Test
    public void login() {
        User user = userService.login(new User(1, "ss", "123456", "123456@126.com"));
        if(user != null) {
            System.out.println("登录成功!");
        }else{
            System.out.println("用户名或密码错误!");
        }
    }

    @Test
    public void existUsername() {
        boolean isExist = userService.existUsername("xu");
        if(isExist != true) {
            System.out.println("用户名不存在!");
        }else{
            System.out.println("用户名已存在!");
        }
    }
}