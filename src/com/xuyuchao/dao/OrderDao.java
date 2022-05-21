package com.xuyuchao.dao;

import com.xuyuchao.pojo.Order;

/**
 * @auther xuyuchao
 * @create 2022-04-13-13:51
 */
public interface OrderDao {
    //保存订单
    public int saveOrder(Order order);
}
