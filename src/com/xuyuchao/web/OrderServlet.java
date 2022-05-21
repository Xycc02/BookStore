package com.xuyuchao.web;

import com.xuyuchao.pojo.Cart;
import com.xuyuchao.pojo.User;
import com.xuyuchao.service.OrderService;
import com.xuyuchao.service.impl.OrderServiceImpl;
import com.xuyuchao.utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @auther xuyuchao
 * @create 2022-04-13-13:50
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        int id = loginUser.getId();
        //创建订单在一个事务中
        String orderId = orderId = orderService.createOrder(cart, id);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect("/pages/cart/checkout.jsp");
    }
}
