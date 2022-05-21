package com.xuyuchao.test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-03-31-16:43
 */
public class userServletTest {
    public void login() {
        System.out.println("处理登录业务!");
    }
    public void regist() {
        System.out.println("处理注册业务!");
    }
    public void modifyPassword() {
        System.out.println("处理修改密码业务!");
    }
    public void modifyUsername() {
        System.out.println("处理修改用户名业务!");
    }

    public static void main(String[] args) {
        String action = "regist";
        try {
            Method method = userServletTest.class.getDeclaredMethod(action);
            System.out.println(method);
            //调用目标业务method方法
            method.invoke(new userServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}