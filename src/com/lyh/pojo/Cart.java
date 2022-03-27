package com.lyh.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 购物车对象
 **/
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    // items中存放<id,一行商品> 一行商品可能是多个
    private Map<Integer,CartItem> items = new LinkedHashMap<>();
    /**
     * @Description 传入一行商品，并加入到购物车
     **/
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否存在此商品，如果存在，则数量累加，总金额更新，如果不存在，直接放到集合中即可

        //先获取待添加cartItem的id，通过id获取map中的对应商品，为item
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 之前没添加过此商品
            items.put(cartItem.getId(), cartItem);
        } else {
            // 已经 添加过的情况
            item.setCount( item.getCount() + 1 ); // 数量累加
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) ); // 更新总金额
        }
    }
    /**
     * @Description 删除商品项
     **/
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * @Description 清空购物车
     **/
    public void clear(){
        items.clear();
    }
    /**
     * @Description 修改商品数量
     **/
    public void updateCount(Integer id,Integer count){
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id); //根据id获取cartItem
        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) ); // 更新总金额
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
    /**
     * @Description 获取商品的总数量
     **/
    public Integer getTotalCount() {
        Integer totalCount =0 ;
        //遍历购物车中的items商品列表，每行都是一个entry，对应一个map
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            //entry.getValue()获取值列表， entry.getValue().getCount()获取值列表对应的元素个数
            totalCount+=entry.getValue().getCount(); //单行商品的数量
        }
/*        for(CartItem item : items.values()){
            //items.values()获取的是id值，items.values()获取得商品list
        }*/
        return totalCount;
    }
    /**
     * @Description 获取商品的总价钱
     **/
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            //entry.getValue()获取值列表， entry.getValue().getTotalPrice()获取该行商品的总价格
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
