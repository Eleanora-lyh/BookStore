package com.lyh.test;

import com.lyh.dao.OrderDao;
import com.lyh.dao.impl.OrderDaoImpl;
import com.lyh.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
//        orderDao.saveOrder(new Order("1234567891", new Date(), new BigDecimal(100), 0, 1));
        orderDao.saveOrder(new Order("1111111111", new Date(), new BigDecimal(111), 0, 3));
    }

    @Test
    public void queryOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orderList = new ArrayList<>();
        orderList = orderDao.queryOrders();
        for (Order order : orderList) {
            System.out.println(order);
        }

    }

    @Test
    public void changeOrderStatus() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.changeOrderStatus("16484507147141",1);
    }

    @Test
    public void queryOrderByUserId() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orderList = new ArrayList<>();
        orderList = orderDao.queryOrderByUserId(1);
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}