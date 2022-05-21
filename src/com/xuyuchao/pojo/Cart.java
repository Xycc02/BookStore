package com.xuyuchao.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 * 此项目购物车模块直接将购物车信息保存在Session域中,不与数据库相连
 * @auther xuyuchao
 * @create 2022-04-12-14:44
 */
public class Cart {
//    private int totalCount;//购物车商品总数量
//    private BigDecimal totalPrice;//购物车商品总价格
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();//购物车商品信息,List集合存储

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //获取要添加商品信息
        CartItem item = items.get(cartItem.getId());

        if(item == null) {
            //商品项中没有此商品
            items.put(cartItem.getId(),cartItem);
        }else{
            //商品项中有此商品
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleItem(int id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(int id,int count){
        //获取id的商品项
        CartItem item = items.get(id);
        //重新赋予商品数量,以及总金额
        if(item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Cart() {
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPricel=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public int getTotalCount() {
        //得到商品总数量
        int totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

}
