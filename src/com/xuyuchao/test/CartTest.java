package com.xuyuchao.test;

import com.xuyuchao.pojo.Cart;
import com.xuyuchao.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-04-12-15:17
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaweb",1,new BigDecimal(30),new BigDecimal(30)));
        System.out.println(cart);
        cart.addItem(new CartItem(1,"javaweb",1,new BigDecimal(30),new BigDecimal(30)));
        System.out.println(cart);

    }

    @Test
    public void deleItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaweb",5,new BigDecimal(30),new BigDecimal(60)));
        System.out.println(cart);
        cart.deleItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaweb",5,new BigDecimal(30),new BigDecimal(60)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaweb",5,new BigDecimal(30),new BigDecimal(60)));
        System.out.println(cart);
        cart.updateCount(1,1);
        System.out.println(cart);
    }
}