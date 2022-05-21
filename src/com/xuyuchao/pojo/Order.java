package com.xuyuchao.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单类
 * @auther xuyuchao
 * @create 2022-04-13-12:50
 */
public class Order {
    private String orderId;//订单号
    private Date createTime;//下单时间
    private BigDecimal price;//总金额
    private int status = 0;//订单状态(0未发货,1以发货,2已签收)
    private int userId;//用户编号

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, int status, int userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
