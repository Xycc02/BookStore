package com.xuyuchao.test;

import com.xuyuchao.dao.impl.OrderDaoImpl;
import com.xuyuchao.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:05
 */
public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        int res = orderDao.saveOrder(new Order("10001", new Date(), new BigDecimal(100), 1, 3));
        if(res == 1 ){
            System.out.println("创建订单成功!");
        }else{
            System.out.println("创建订单失败!");
        }
    }
}