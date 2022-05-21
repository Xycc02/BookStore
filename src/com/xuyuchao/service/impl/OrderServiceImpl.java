package com.xuyuchao.service.impl;

import com.xuyuchao.dao.BookDao;
import com.xuyuchao.dao.OrderDao;
import com.xuyuchao.dao.OrderItemDao;
import com.xuyuchao.dao.impl.BookDaoImpl;
import com.xuyuchao.dao.impl.OrderDaoImpl;
import com.xuyuchao.dao.impl.OrderItemDaoImpl;
import com.xuyuchao.pojo.*;
import com.xuyuchao.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:26
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return  返回订单号
     */
    @Override
    public String createOrder(Cart cart, int userId) {
        //订单号唯一,生成订单号
        String orderId = System.currentTimeMillis() + "" + userId;
        //保存至数据库
        orderDao.saveOrder(new Order(orderId,new Date(),cart.getTotalPrice(),0,userId));
        //模拟出错
        //System.out.println(10/0);
        //遍历购物车中每一个商品项转换为订单项保存至数据库
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取每一个购物车中的商品项
            CartItem item = entry.getValue();
            //将每个商品项转化为订单项
            OrderItem orderItem = new OrderItem(item.getId(), item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            //改变图书的销量和库存
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales()+item.getCount());
            book.setStock(book.getStock()-item.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
