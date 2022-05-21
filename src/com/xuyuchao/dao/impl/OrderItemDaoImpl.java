package com.xuyuchao.dao.impl;

import com.xuyuchao.dao.BaseDAO;
import com.xuyuchao.dao.OrderItemDao;
import com.xuyuchao.pojo.OrderItem;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:00
 */
public class OrderItemDaoImpl extends BaseDAO implements OrderItemDao {
    /**
     * 保存订单项至数据库
     * @param item
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "insert into t_order_item(id,name,count,price,total_price,order_id) values(?,?,?,?,?,?)";
        int update = update(sql, item.getId(), item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), item.getOrderId());
        return update;
    }


}
