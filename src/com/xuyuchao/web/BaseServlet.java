package com.xuyuchao.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet继承HttpServlet类，实现doPost(),并作为抽象类
 * 所有的Servlet业务都继承BaseServlet
 * @auther xuyuchao
 * @create 2022-03-31-17:11
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1、获取隐藏域action的值
         * 2、通过反射获取action对应的业务方法
         * 3、通过反射调用业务方法
         */
        String action = req.getParameter("action");
        try {
            //反射，通过action得到应该处理的业务方法,注意：方法的参数的class类要写到可变长形参中
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //执行该方法,注意，在invoke方法中要加上req和resp参数
            try {
                method.invoke(this,req,resp);
            } catch (Exception e) {
                System.out.println("invoke出现了异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //把异常抛给Filter过滤器,此处不往外抛异常的话,底层出错Filter中会将捕获不到异常从而提交事务而不会回滚
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
