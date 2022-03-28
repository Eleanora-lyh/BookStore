package com.lyh.dao;

import com.lyh.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public void changeOrderStatus(String orderId,Integer status);
    public List<Order> queryOrderByUserId(Integer userId);
}
