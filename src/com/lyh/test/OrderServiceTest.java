package com.lyh.test;

import com.lyh.pojo.Cart;
import com.lyh.pojo.CartItem;
import com.lyh.pojo.OrderItem;
import com.lyh.service.OrderService;
import com.lyh.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders() {
        OrderService orderService = new OrderServiceImpl();
    }

    @Test
    public void sendOrder() {
        OrderService orderService = new OrderServiceImpl();
        orderService.sendOrder("16484507147141");
    }

    @Test
    public void showOrderDetail() {
        OrderService orderService = new OrderServiceImpl();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems = orderService.showOrderDetail("16484507147141");
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrders() {
    }

    @Test
    public void receiveOrder() {
    }
}