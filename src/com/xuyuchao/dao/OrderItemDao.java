package com.xuyuchao.dao;

import com.xuyuchao.pojo.OrderItem;

/**
 * @auther xuyuchao
 * @create 2022-04-13-13:59
 */
public interface OrderItemDao {
    //保存订单项
    public int saveOrderItem(OrderItem item);
}
