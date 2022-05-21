package com.xuyuchao.service;

import com.xuyuchao.pojo.Cart;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:23
 */
public interface OrderService {
    //创建订单,返回订单id
    public String createOrder(Cart cart,int userId);
}
