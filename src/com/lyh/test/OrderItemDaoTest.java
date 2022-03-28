package com.lyh.test;

import com.lyh.dao.OrderItemDao;
import com.lyh.dao.impl.OrderItemDaoImpl;
import com.lyh.pojo.Order;
import com.lyh.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        List<OrderItem> orderList = new ArrayList<>();
        orderList =  orderItemDao.queryOrderItemByOrderId("16484507147141");
        for (OrderItem orderItem : orderList) {
            System.out.println(orderItem);
        }

    }
}