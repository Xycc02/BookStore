package com.xuyuchao.filter;

import com.xuyuchao.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @auther xuyuchao
 * @create 2022-04-14-22:12
 */
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        User user = (User) req.getSession().getAttribute("loginUser");
        if(user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else{
            //登录放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
