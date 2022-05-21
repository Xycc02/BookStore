package com.xuyuchao.pojo;

import java.math.BigDecimal;

/**
 * 订单项类,展示订单详情
 * @auther xuyuchao
 * @create 2022-04-13-12:50
 */
public class OrderItem {
    private int id;//商品编号
    private String name;//商品名称
    private int count;//商品数量
    private BigDecimal price;//单价
    private BigDecimal totalPrice;//总价
    private String orderId;//订单号



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return getPrice().multiply(new BigDecimal(getCount()));
    }


    public OrderItem() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public OrderItem(int id, String name, int count, BigDecimal price, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }
}
