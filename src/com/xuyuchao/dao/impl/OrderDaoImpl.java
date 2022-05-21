package com.xuyuchao.dao.impl;

import com.xuyuchao.dao.BaseDAO;
import com.xuyuchao.dao.OrderDao;
import com.xuyuchao.pojo.Order;

/**
 * @auther xuyuchao
 * @create 2022-04-13-13:53
 */
public class OrderDaoImpl extends BaseDAO implements OrderDao {
    /**
     * 将订单保存至数据库
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        int update = update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        return update;
    }
}
