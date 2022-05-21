package com.xuyuchao.test;

import com.xuyuchao.dao.impl.OrderItemDaoImpl;
import com.xuyuchao.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:08
 */
public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDaoImpl orderItem = new OrderItemDaoImpl();
        int res = orderItem.saveOrderItem(new OrderItem(1, "javaweb", 4, new BigDecimal(10), new BigDecimal(40), "10001"));
        if(res == 1) {
            System.out.println("创建订单项成功!");
        }else{
            System.out.println("失败!");
        }
    }
}