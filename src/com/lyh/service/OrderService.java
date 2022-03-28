package com.lyh.service;

import com.lyh.pojo.Cart;
import com.lyh.pojo.Order;
import com.lyh.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();
    public void sendOrder(String orderId);
    public List<OrderItem> showOrderDetail(String orderId);
    public List<Order> showMyOrders(Integer userId);
    public void receiveOrder(String orderId);
}
