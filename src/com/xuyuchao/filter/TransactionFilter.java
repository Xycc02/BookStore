package com.xuyuchao.filter;

import com.xuyuchao.utils.JDBCUtils;
import jakarta.servlet.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @auther xuyuchao
 * @create 2022-04-15-18:47
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 拦截所有目标资源,使其在一个事务当中
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollBackAndClose();
            e.printStackTrace();
            //把异常抛给Tomcat
            throw new RuntimeException(e);
        }
    }
}
