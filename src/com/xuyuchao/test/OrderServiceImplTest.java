package com.xuyuchao.test;

import com.xuyuchao.pojo.Cart;
import com.xuyuchao.pojo.CartItem;
import com.xuyuchao.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-04-13-14:57
 */
public class OrderServiceImplTest {

    @Test
    public void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"book1",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"book1",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"book2",1,new BigDecimal(20),new BigDecimal(20)));

        OrderServiceImpl orderService = new OrderServiceImpl();
        String order = orderService.createOrder(cart, 1);
        System.out.println(order);

    }
}